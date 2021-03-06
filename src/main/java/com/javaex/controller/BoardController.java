package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 조회수+1 , 게시글정보 불러오기
	@RequestMapping("/board/read")
	public String read(@RequestParam("no") int no, Model model) {
		System.out.println("[보더컨트롤러.리드()]");

		BoardVo boardVo = boardService.getBoard(no);
		model.addAttribute("boardVo", boardVo);
		System.out.println(boardVo);
		return "board/read";
	}

	// 리스트전체 불러오기
	@RequestMapping("/board/list")
	public String boardList(@RequestParam(required = false, defaultValue = "") String op,
			@RequestParam(required = false, defaultValue = "") String searchContent, Model model) {
		System.out.println("[보더컨트롤러.보더리스트()]");

		Map<String, Object> searchMap = new HashMap<String, Object>();
		searchMap.put("op", op);
		searchMap.put("searchContent", searchContent);

		List<BoardVo> boardList = boardService.getboardList(searchMap);

		model.addAttribute("boardList", boardList);
		return "board/list";
	}

	// 보더수정폼
	@RequestMapping("/board/modifyForm")
	public String modifyForm(@ModelAttribute BoardVo boardVo, Model model) {
		System.out.println("[보더컨트롤러.모디폼()]");

		model.addAttribute("boardVo", boardVo);
		return "board/modifyForm";
	}

	// 보더수정
	@RequestMapping("/board/modify")
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("[보더컨트롤러.모디()]");

		boardService.boardUpdate(boardVo);

		return "redirect:/board/list";
	}

	// 보더글쓰기폼
	@RequestMapping("/board/writeForm")
	public String writeForm() {
		System.out.println("[보더컨트롤러.글쓰기폼()]");

		return "/board/writeForm";
	}

	// 보더글쓰기
	@RequestMapping("/board/write")
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("[보더컨트롤러.글쓰기()]");

		boardService.boardWrite(boardVo);

		return "redirect:/board/list";
	}

	// 보더글삭제
	@RequestMapping("/board/delete")
	public String delete(@RequestParam("no") int no) {
		System.out.println("[보더컨트롤러.글삭제()]");

		boardService.boardDelete(no);

		return "redirect:/board/list";
	}

}
