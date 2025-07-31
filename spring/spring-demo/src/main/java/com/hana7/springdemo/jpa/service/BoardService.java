package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.board.BoardRequestDTO;
import com.hana7.springdemo.jpa.dto.board.BoardResponseDTO;

import java.util.List;

public interface BoardService {
	List<BoardResponseDTO> getPageList(int page, int countPerPage);

	BoardResponseDTO getBoard(int id);

	BoardResponseDTO createBoard(BoardRequestDTO requestDTO);

	BoardResponseDTO changeBoard(BoardRequestDTO requestDTO);

	void removeBoard(int id);
}
