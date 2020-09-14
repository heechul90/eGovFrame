package eum.project.service.impl;

import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import eum.project.service.BoardDefaultVO;
import eum.project.service.BoardVO;

@Mapper("boardMapper")
public interface BoardMapper {
	
	/**
	 * 글 조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	BoardVO selectBoard(BoardVO vo) throws Exception;
	
	/**
	 * 글 목록
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	List<?> selectBoardList(BoardDefaultVO searchVO) throws Exception;
	
	/**
	 * 글 총 갯수를 조회
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
