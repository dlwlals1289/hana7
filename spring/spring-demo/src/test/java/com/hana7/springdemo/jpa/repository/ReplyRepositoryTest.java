package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.BloodType;
import com.hana7.springdemo.jpa.entity.Board;
import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.entity.Reply;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Rollback(false)
class ReplyRepositoryTest extends RepositoryTest{
	
	@Autowired
	ReplyRepository replyRepository;

	@Autowired
	BoardRepository boardRepository;

	@Autowired
	MemberRepository memberRepository;

	private static final int LIMIT = 10;
	private static Member member;

	@BeforeEach
	public void beforeEach() {
		member = memberRepository.findById(1L).orElseThrow();
	}
	
	@Test
	@Order(1)
	void addTest() {
	    // given
			Board board = getBoard();
			System.out.println(board);

	    // when
			List<Reply> list = Stream.iterate(1, n -> n + 1)
					.limit(LIMIT)
					.map(n -> Reply.builder()
									.replyer(member)
									.reply("reply"+n)
									.board(board)
									.build())
					.toList();
			System.out.println(list.get(1));
			replyRepository.saveAll(list);

			Assertions.assertThat(LIMIT).isEqualTo(replyRepository.count());
	}

	@Test
	void listTest() {
	    // given
	    List<Reply> list = replyRepository.findAll();
	    long count = replyRepository.count();
	    Assertions.assertThat(list.size()).isEqualTo(count);
	}

	@Test
	void updateTest() {
	    // given
	    Reply reply = replyRepository.findById(55L).orElseThrow();
	    // when
	    reply = Reply.builder()
				.id(reply.getId())
				.reply("newReply")
				.replyer(member)
				.board(reply.getBoard())
				.build();
		replyRepository.save(reply);
	}
	
	@Test
	void deleteTest() {
	    // given
	    	replyRepository.deleteById(59L);
//	    Assertions.assertThat().isEqualTo();
	}
	



	private Board getBoard(){
		return boardRepository.findById(1).orElseThrow();
	}

}
