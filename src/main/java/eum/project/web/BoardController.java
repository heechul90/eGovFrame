package eum.project.web;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import eum.project.service.BoardDefaultVO;
import eum.project.service.BoardService;
import eum.project.service.BoardVO;

@Controller
public class BoardController {
	
	
	@Resource(name="boardService")
	private BoardService boardService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	
	/**
	 * Board List 출력
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/home.do")
	public String board(ModelMap model) throws Exception {
		
		List<EgovMap> testList = boardService.selectTestList();
		model.addAttribute("resultTestList", testList);
		
		return "eum/eumHome";
	}
	
	
	/**
	 * 글 목록 조회
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/boardList.do")
	public String selectBoardList(@ModelAttribute("searchVO") BoardDefaultVO searchVO, ModelMap model) throws Exception {
		
		/** EgovPropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));

		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());

		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<?> boardList = boardService.selectBoardList(searchVO);
		model.addAttribute("resultList", boardList);
		
		int totCnt = boardService.selectBoardListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		
		return "eum/boardList";
	}

	
	/**
	 * 글 등록 조회 화면
	 */
	@RequestMapping(value = "/addBoard.do", method = RequestMethod.GET)
	public String addBoardView(@ModelAttribute("searchVO") BoardDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("boardVO", new BoardVO());
		return "eum/boardRegister";
	}
	
	
	/**
	 * 글 등록
	 */
	@RequestMapping(value = "/addBoard.do", method = RequestMethod.POST)
	public String addBoard(@ModelAttribute("searchVO") BoardDefaultVO searchVO, BoardVO boardVO, BindingResult bindingResult, Model model, SessionStatus status) 
			throws Exception {
		
		// server-side Validation
		beanValidator.validate(boardVO, bindingResult);
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("boardVO", boardVO);
			return "eum/boardRegister";
		}
		
		boardService.insertBoard(boardVO);
		status.setComplete();
		
		return "forward:/boardList.do";
	}
	
	
	
	/**
	 * 수정 조회
	 * @param id
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "updateBoardView.do")
	public String updateBoardView(@RequestParam("selectedId") String id, @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setId(id);
		// 변수명은 CoC 에 따라 sampleVO
		model.addAttribute(selectBoard(boardVO, boardVO));
		
		
		return "eum/boardRegister";
	}
	
	
	/**
	 * 글을 조회한다.
	 * @param boardVO
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	public BoardVO selectBoard(BoardVO boardVO, @ModelAttribute("boardVO") BoardDefaultVO searchVO) throws Exception {
		return boardService.selectBoard(boardVO);
	}
	
	
	/**
	 * 글을 수정한다.
	 */
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("searchVO") BoardDefaultVO searchVO, BoardVO boardVO, BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {

		beanValidator.validate(boardVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("boardVO", boardVO);
			return "eum/boardRegister";
		}

		boardService.updateBoard(boardVO);
		status.setComplete();
		return "forward:/boardList.do";
	}
	
	/**
	 * 글을 삭제한다.
	 */
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO boardVO, @ModelAttribute("searchVO") BoardDefaultVO searchVO, SessionStatus status) throws Exception {
		boardService.deleteBoard(boardVO);
		status.setComplete();
		return "forward:/boardList.do";
	}
	
	
	
}











