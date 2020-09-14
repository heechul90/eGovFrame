package eum.project.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;



public interface BoardService {
	
	/**
	 * 글 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	BoardVO selectBoard(BoardVO vo) throws Exception;
	
	/**
	 * 글 목록 조회
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<?> selectBoardList(BoardDefaultVO searchVO) throws Exception;
	
	/**
	 * 글 총 객수 조회
	 * @param searchVO
	 * @return
	 */
	int selectBoardListTotCnt(BoardDefaultVO searchVO);
	
	/**
	 * 테스트
	 * @return
	 * @throws Exception
	 */
	List<EgovMap> selectTestList() throws Exception;
	
}
