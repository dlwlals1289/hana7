package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.*;
import com.hana7.springdemo.jpa.entity.Board;
import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.entity.Reply;
import com.hana7.springdemo.jpa.repository.BoardRepository;
import com.hana7.springdemo.jpa.repository.MemberRepository;
import com.hana7.springdemo.jpa.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BoardServiceImpl implements BoardService {
	private final BoardRepository repository;
	private final ReplyRepository replyRepository;
	private final MemberRepository memberRepository;

	public static BoardResponseDTO toDTO(Board board) {
		return BoardResponseDTO.builder()
				.id(board.getId())
				.title(board.getTitle())
				.writer(toMemberResponseDTO(board.getWriter()))
				.hit(board.getHit())
				.createdAt(board.getCreatedAt()).build();
	}

	public static MemberResponseDTO toMemberResponseDTO(Member member) {
		return MemberResponseDTO.builder()
				.id(member.getId())
				.nickname(member.getNickname())
				.email(member.getEmail())
				.bloodType(member.getBloodType())
				.build();
	}

	@Override
	public List<BoardResponseDTO> getPageList(int page, int countPerPage) {
		Page<Board> results = repository.findAll(
				PageRequest.of(page - 1, countPerPage, Sort.by(Sort.Order.desc("id"))));

		return new PageResponseDTO<>(results, BoardServiceImpl::toDTO).getDtoList();
	}

	@Override
	public BoardResponseDTO getBoard(int id) {
		Board board = repository.findById(id).orElseThrow();
		List<Reply> replies = board.getReplies();

		return toDetailDTO(board, replies);
	}

	@Override
	@Transactional
	public HttpStatus createBoard(BoardRequestDTO requestDTO) {
		Board board = toEntity(requestDTO);
		log.info(board.toString());
		repository.save(board);
		log.info("게시판 추가 성공");
		return HttpStatus.CREATED;
	}

	@Override
	@Transactional
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

		Member writer = board.getWriter();

		List<ReplyResponseDto> replyDto = replies.stream()
				.map((r) -> ReplyResponseDto.builder()
						.id(r.getId())
						.reply(r.getReply())
						.replyer(toMemberResponseDTO(r.getReplyer()))
						.build())
				.toList();

		return BoardDetailResponseDTO.builder()
				.id(board.getId())
				.title(board.getTitle())
				.writer(toMemberResponseDTO(board.getWriter()))
				.hit(board.getHit())
				.content(board.getContent().getContent())
				.replyResponseDto(replyDto)
				.createdAt(board.getCreatedAt()).build();
	}
}
