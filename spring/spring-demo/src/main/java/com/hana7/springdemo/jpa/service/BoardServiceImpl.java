package com.hana7.springdemo.jpa.service;

import java.util.List;
import java.util.Optional;

import com.hana7.springdemo.jpa.dto.*;
import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.entity.Reply;
import com.hana7.springdemo.jpa.repository.MemberRepository;
import com.hana7.springdemo.jpa.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hana7.springdemo.jpa.entity.Board;
import com.hana7.springdemo.jpa.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardRepository repository;
	private final ReplyRepository replyRepository;
	private final MemberRepository memberRepository;

	@Override
	public List<BoardResponseDTO> getPageList(int page, int countPerPage) {
		Page<Board> results = repository.findAll(
			PageRequest.of(page - 1, countPerPage, Sort.by(Sort.Order.desc("id"))));

		return new PageResponseDTO<>(results, BoardServiceImpl::toDTO).getDtoList();
	}

	@Override
	public BoardResponseDTO getBoard(int id) {
		Board board = repository.findById(id).orElseThrow();
		List<Reply> replies = replyRepository.findAllByBoard(board);

		return toDetailDTO(board, replies);
	}

	@Override
	public HttpStatus createBoard(BoardRequestDTO requestDTO) {
		Board board = toEntity(requestDTO);
		repository.save(board);
		return HttpStatus.CREATED;
	}

	@Override
	public BoardResponseDTO changeBoard(BoardRequestDTO requestDTO) {
		Board board = repository.findById(requestDTO.getId()).orElseThrow();
		List<Reply> replies = replyRepository.findAllByBoard(board);

		return toDetailDTO(board, replies);
	}

	public Board toEntity(BoardRequestDTO dto) {
		Member writer = memberRepository.findById(dto.getWriterId()).orElseThrow();

		return Board.builder()
			.id(dto.getId())
			.title(dto.getTitle())
			.writer(writer)
			.build();
	}

	public BoardDetailResponseDTO toDetailDTO(Board board, List<Reply> replies) {

		Long writerId = board.getWriter().getId();
		Member writer = memberRepository.findById(writerId).orElseThrow();

		List<ReplyResponseDto> replyDto = replies.stream()
				.map((r) -> ReplyResponseDto.builder()
						.id(r.getId())
						.reply(r.getReply())
						.replyer(r.getReplyer().getNickname())
						.build())
				.toList();

		return BoardDetailResponseDTO.builder()
			.id(board.getId())
			.title(board.getTitle())
			.writer(writer.getNickname())
			.hit(board.getHit())
			.content(board.getContent().getContent())
			.replyResponseDto(replyDto)
			.createdAt(board.getCreatedAt()).build();
	}

	public static BoardResponseDTO toDTO(Board board) {
		return BoardResponseDTO.builder()
				.id(board.getId())
				.title(board.getTitle())
				.writer(board.getWriter().getNickname())
				.hit(board.getHit())
				.createdAt(board.getCreatedAt()).build();
	}
}
