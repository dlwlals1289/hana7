package com.hana7.springdemo.jpa.controller;

import com.hana7.springdemo.jpa.dto.ErrorResponseDTO;
import com.hana7.springdemo.jpa.dto.board.BoardRequestDTO;
import com.hana7.springdemo.jpa.dto.board.BoardResponseDTO;
import com.hana7.springdemo.jpa.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
	private final BoardService service;

	public BoardController(BoardService service) {
		this.service = service;
	}

	@GetMapping
	public List<BoardResponseDTO> getPageList(@RequestParam(defaultValue = "1") int page,
											  @RequestParam(defaultValue = "10") int countPerPage) {
		return service.getPageList(page, countPerPage);
	}

	@PostMapping()
	public BoardResponseDTO createBoard(@RequestBody @Validated BoardRequestDTO requestDTO) {
		return service.createBoard(requestDTO);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getBoard(@PathVariable int id) {
		BoardResponseDTO board = service.getBoard(id);
		if (board != null)
			return ResponseEntity.ok(board);

		return ResponseEntity.status(404).body(
				new ErrorResponseDTO(id + "를 찾을 수 없습니다.", "NOT_FOUND"));
	}

	@PatchMapping("/{id}")
	public BoardResponseDTO chageBoard(@PathVariable int id, @RequestBody @Validated BoardRequestDTO requestDTO) {
		requestDTO.setId(id);
		return service.changeBoard(requestDTO);
	}

	@DeleteMapping("/{id}")
	public int removeBoard(@PathVariable int id) {
		service.removeBoard(id);
		return id;
	}

	// @PostMapping("/{id}/replies")
	// @PatchMapping("/{id}/replies/{replyId}")
	// @DeleteMapping("/{id}/replies/{replyId}")
}
