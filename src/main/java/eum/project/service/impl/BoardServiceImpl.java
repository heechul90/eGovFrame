package eum.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.example.sample.service.impl.EgovSampleServiceImpl;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.psl.dataaccess.util.EgovMap;
import eum.project.service.BoardDefaultVO;
import eum.project.service.BoardService;
import eum.project.service.BoardVO;

@Service("boardService")
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSampleServiceImpl.class);
	
	@Resource(name="boardDAO")
	private BoardDAO boardDAO;
	
	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	@Resource
	private BoardMapper boardMapper;
	
	
	/**
	 * 글 등록
	 */
	public String insertBoard(BoardVO vo) throws Exception {
		LOGGER.debug(vo.toString());
		
		String id = egovIdGnrService.getNextStringId();
		vo.setId(id);
		LOGGER.debug(vo.toString());
		
		boardDAO.insertBoard(vo);
		return id;
	}
	
	
	/**
	 * 글 조회
	 */
	@Override
	public BoardVO selectBoard(BoardVO vo) throws Exception {
		BoardVO resultVO = boardDAO.selectBoard(vo);
		if (resultVO == null)
			throw processException("info.nodata.msg");
		return resultVO;
	}
	
	/**
	 * 글 목록 조회
	 */
	@Override
	public List<?> selectBoardList(BoardDefaultVO searchVO) throws Exception {
		return boardMapper.selectBoardList(searchVO);
	}
	
	/**
	 * 글 총 갯수 조회
	 */
	@Override
	public int selectBoardListTotCnt(BoardDefaultVO searchVO) {
		return boardMapper.selectBoardListTotCnt(searchVO);
	}
	
	/**
	 * 테스트
	 */
	@Override
	public List<EgovMap> selectTestList() throws Exception{
		
		return boardMapper.selectTestList();
	}
	
	


}
