package eum.project.service;

import java.util.List;

import egovframework.example.sample.service.SampleVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;



public interface BoardService {
	
	/**
	 * 글 등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	String insertBoard(BoardVO vo) throws Exception;
	
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
	
	/**
	 * 글을 수정한다.
	 * @param vo - 수정할 정보가 담긴 SampleVO
	 * @return void형
	 * @exception Exception
	 */
	void updateBoard(BoardVO vo) throws Exception;
	
	
	/**
	 * 글을 삭제한다.
	 */
	void deleteBoard(BoardVO vo) throws Exception;
	
}
