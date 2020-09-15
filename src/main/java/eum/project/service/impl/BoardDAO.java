package eum.project.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import eum.project.service.BoardDefaultVO;
import eum.project.service.BoardVO;

@Repository("boardDAO")
public class BoardDAO extends EgovAbstractDAO {
	
	/**
	 * 글 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public String insertBoard(BoardVO vo) throws Exception {
		return (String) insert("BoardDAO.insertBoard", vo);
	}
	
	/**
	 * 글 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public BoardVO selectBoard(BoardVO vo) throws Exception {
		return (BoardVO) select("boardDAO.selectBoard", vo);
	}
	
//	/**
//	 * 글 목록 조회
//	 * @param searchVO
//	 * @return
//	 * @throws Exception
//	 */
//	public List<?> selectBoardList(BoardDefaultVO searchVO) throws Exception {
//		return list("boardDAO.selectBoardList", searchVO);
//	}
	
	/**
	 * 글 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 글 총 갯수
	 * @exception
	 */
	public int selectBoardListTotCnt(BoardDefaultVO searchVO) {
		return (Integer) select("boardDAO.selectBoardListTotCnt", searchVO);
	}

}
