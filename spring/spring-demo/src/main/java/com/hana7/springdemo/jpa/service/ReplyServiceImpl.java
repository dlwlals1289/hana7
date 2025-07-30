package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.ReplyRequestDto;
import com.hana7.springdemo.jpa.entity.Board;
import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.entity.Reply;
import com.hana7.springdemo.jpa.repository.BoardRepository;
import com.hana7.springdemo.jpa.repository.MemberRepository;
import com.hana7.springdemo.jpa.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

	private final BoardRepository boardRepository;
	private final ReplyRepository replyRepository;
	private final MemberRepository memberRepository;

	@Override
	public void insertReply(ReplyRequestDto replyRequestDto) {
		Board board = boardRepository.findById(replyRequestDto.getBoardId()).orElseThrow();

		Member member = memberRepository.findById(replyRequestDto.getReplyerId()).orElseThrow();
		Reply reply = Reply.builder()
				.reply(replyRequestDto.getReply())
				.replyer(member)
				.board(board)
				.build();

		replyRepository.save(reply);
	}
}
