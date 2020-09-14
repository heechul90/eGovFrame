package eum.project.web;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		
		return "eum/eumBoardList";
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
	
	@RequestMapping(value = "/addBoard.do", method = RequestMethod.GET)
	public String addBoardView(@ModelAttribute("searchVO") BoardDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("boardVO", new BoardVO());
		return "eum/boardRegister";
	}
	
}
