package com.hana7.springdemo.jpa.service;

import java.util.List;

import com.hana7.springdemo.jpa.dto.BoardRequestDTO;
import com.hana7.springdemo.jpa.dto.BoardResponseDTO;
import org.springframework.http.HttpStatus;

public interface BoardService {
	List<BoardResponseDTO> getPageList(int page, int countPerPage);

	BoardResponseDTO getBoard(int id);

	HttpStatus createBoard(BoardRequestDTO requestDTO);

	BoardResponseDTO changeBoard(BoardRequestDTO requestDTO);
}
