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
		
		List<?> boardList = boardService.selectBoardList(searchVO);
		model.addAttribute("resultList", boardList);
		
		
		return "eum/boardList";
	}

	
	/**
	 * 글 등록
	 * @param searchVO
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addBoard.do", method = RequestMethod.GET)
	public String addBoardView(@ModelAttribute("searchVO") BoardDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("boardVO", new BoardVO());
		return "eum/boardRegister";
	}
	
	
	/**
	 * 글 등록
	 * @param searchVO
	 * @param boardVO
	 * @param bindingResult
	 * @param model
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/addBoard.do", method = RequestMethod.POST)
	public String addBoard(@ModelAttribute("searchVO") BoardDefaultVO searchVO, BoardVO boardVO, BindingResult bindingResult, Model model, SessionStatus status) 
			throws Exception {
		
		// server-side Validation
//		beanValidator.validate(boardVO, bindingResult);
//		
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("boardVO", boardVO);
//			return "eum/boardRegister";
//		}
//		
//		boardService.insertBoard(boardVO);
//		status.setComplete();
		
		return "forward:/home.do";
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
	
	
}











