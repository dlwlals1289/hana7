package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.PageResponseDTO;
import com.hana7.springdemo.jpa.dto.ReplyResponseDTO;
import com.hana7.springdemo.jpa.dto.board.BoardDetailResponseDTO;
import com.hana7.springdemo.jpa.dto.board.BoardRequestDTO;
import com.hana7.springdemo.jpa.dto.board.BoardResponseDTO;
import com.hana7.springdemo.jpa.entity.Board;
import com.hana7.springdemo.jpa.entity.BoardContent;
import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.entity.Reply;
import com.hana7.springdemo.jpa.repository.BoardRepository;
import com.hana7.springdemo.jpa.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
	private final BoardRepository repository;
	private final MemberRepository memberRepository;

	public BoardServiceImpl(BoardRepository repository, MemberRepository memberRepository) {
		this.repository = repository;
		this.memberRepository = memberRepository;
	}

	public static BoardResponseDTO toDTO(Board board) {
		return BoardResponseDTO.builder()
				.id(board.getId())
				.title(board.getTitle())
				.writer(MemberServiceImpl.toDTO(board.getWriter()))
				.hit(board.getHit())
				.createdAt(board.getCreatedAt())
				.updatedAt(board.getUpdatedAt())
				.build();
	}

	public static BoardDetailResponseDTO toDetailDTO(Board board) {
		return BoardDetailResponseDTO.builder()
				.id(board.getId())
				.title(board.getTitle())
				.writer(MemberServiceImpl.toDTO(board.getWriter()))
				.hit(board.getHit())
				.content(board.getContent().getContent())
				.createdAt(board.getCreatedAt())
				.updatedAt(board.getUpdatedAt())
				.replies(board.getReplies().stream()
						.map(BoardServiceImpl::toReplyDTO)
						.toList()
				)
				.build();
	}

	public static ReplyResponseDTO toReplyDTO(Reply reply) {
		return ReplyResponseDTO.builder()
				.id(reply.getId())
				.reply(reply.getReply())
				.replyer(MemberServiceImpl.toDTO(reply.getReplyer()))
				.board(toDTO(reply.getBoard()))
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
		return repository.findById(id).map(BoardServiceImpl::toDetailDTO).orElse(null);
	}

	@Override
	public BoardResponseDTO createBoard(BoardRequestDTO requestDTO) {
		Board board = toEntity(requestDTO);
		board.setContent(new BoardContent(requestDTO.getContent()));
		return toDTO(repository.save(board));
	}

	@Override
	public BoardResponseDTO changeBoard(BoardRequestDTO requestDTO) {
		Board board = repository.findById(requestDTO.getId()).orElseThrow();
		board.setTitle(requestDTO.getTitle());
		board.setWriter(getMember(requestDTO.getWriter()));
		board.getContent().setContent(requestDTO.getContent());

		return toDetailDTO(repository.save(board));
	}

	@Override
	public void removeBoard(int id) {
		repository.deleteById(id);
	}

	private Board toEntity(BoardRequestDTO dto) {
		return Board.builder()
				.id(dto.getId())
				.title(dto.getTitle())
				.writer(getMember(dto.getWriter()))
				.build();
	}

	private Member getMember(long memberId) {
		return memberRepository.findById(memberId).orElseThrow();
	}
}
