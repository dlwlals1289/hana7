package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Board;
import com.hana7.springdemo.jpa.entity.BoardContent;
import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.entity.Reply;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Rollback(false)
class BoardRepositoryTest extends RepositoryTest {
	private static final int LIMIT = 10;
	private static Member member;
	@Autowired
	BoardRepository repository;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	ReplyRepository replyRepository;

	@BeforeEach
	public void beforeEach() {
		member = memberRepository.findById(3L).orElseThrow();
	}

	@Test
	@Order(1)
	void addTest() {
		long preCount = repository.count();


		List<Board> list = Stream.iterate(1, n -> n + 1)
				.limit(LIMIT)
				.map(n -> Board.builder()
						.title("Title" + n)
						.writer(member)
						.build())
				.toList();

		list.forEach(b -> b.setContent(new BoardContent("xxx", b)));

		repository.saveAll(list);
		assertEquals(preCount + LIMIT, repository.count());
	}

	@Test
	@Order(2)
	void pageListTest() {
		repository.findAll(
				PageRequest.of(0, 10, Sort.by(Sort.Order.desc("id")))).forEach(this::print);
	}

	@Test
	void getReplies() {
		// given
		Board board = repository.findById(1).orElseThrow();
		// when
		List<Reply> replies = board.getReplies();
		List<Reply> replies1 = replyRepository.findAllByBoard(board);
		// then
		Assertions.assertThat(replies.size()).isEqualTo(replies1.size());
	}


}
