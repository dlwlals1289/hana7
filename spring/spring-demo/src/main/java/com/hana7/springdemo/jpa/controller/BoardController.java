package com.hana7.springdemo.jpa.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hana7.springdemo.jpa.dto.BoardRequestDTO;
import com.hana7.springdemo.jpa.dto.BoardResponseDTO;
import com.hana7.springdemo.jpa.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardController {
	private final BoardService service;
	public BoardController(BoardService service) {
		this.service = service;
	}

	@GetMapping
	public List<BoardResponseDTO> getPageList(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int countPerPage) {
		return service.getPageList(page, countPerPage);
	}

	@PostMapping()
	public ResponseEntity<String> createBoard(@RequestBody @Validated BoardRequestDTO requestDTO) {
		return ResponseEntity
				.status(service.createBoard(requestDTO))
				.body("게시글 생성 성공");
	}

	@GetMapping("/{id}")
	public BoardResponseDTO getBoard(@PathVariable int id) {
		return service.getBoard(id);
	}

	@PatchMapping("/{id}")
	public BoardResponseDTO chageBoard(@PathVariable int id, @RequestBody @Validated BoardRequestDTO requestDTO) {
		requestDTO.setId(id);
		return service.changeBoard(requestDTO);
	}
}
