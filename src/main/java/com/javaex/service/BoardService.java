package com.javaex.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	public BoardVo getBoard(int no) {
		System.out.println("[보더서비스.겟보더]");

		// 조회수 올리기
		int count = boardDao.updateHit(no);

		// 게시판 정보 가져오기
		BoardVo boardVo = boardDao.selectBoard(no);

		return boardVo;
	}

	// 리스트 출력(검색)
	public List<BoardVo> getboardList(Map<String, Object> searchMap) {
		System.out.println("[보더서비스.검색기능]");

		return boardDao.getboardList(searchMap);
	}

	// 보더수정
	public int boardUpdate(BoardVo boardVo) {
		System.out.println("[보더서비스.업데이트]");

		boardDao.boardUpdate(boardVo);

		return 1;
	}

	// 보더글쓰기
	public int boardWrite(BoardVo boardVo) {
		System.out.println("[보더서비스.업데이트]");

		int count = boardDao.boardWrite(boardVo);

		return count;
	}

	// 보더글삭제

	public int boardDelete(int no) {
		System.out.println("[보더서비스.삭제]");

		int count = boardDao.boardDelete(no);

		return count;
	}

}
