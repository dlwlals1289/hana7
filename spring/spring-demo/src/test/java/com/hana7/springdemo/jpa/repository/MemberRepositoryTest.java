package com.hana7.springdemo.jpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.hana7.springdemo.jpa.entity.BloodType;
import com.hana7.springdemo.jpa.entity.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hana7.springdemo.jpa.entity.Member;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.stream.Stream;

@Rollback(false)
class MemberRepositoryTest extends RepositoryTest{
 	@Autowired
	MemberRepository repository;

	private static final int LIMIT = 10;

	@Test
	void saveTest() {
		// give
		Member m = Member.builder().nickname("Kim").email("kim@gmail.com").build();
		
		Member mbr = new Member();
		// mbr.setNickname("Hong");
		mbr.setEmail("hong@gmail.com");

		// when
		Member savedM = repository.save(m);
		Member savedMbr = repository.save(mbr);

		System.out.println("savedMbr = " + savedMbr);

		// then
		Member foundM = repository.findById(savedM.getId()).orElseThrow();
		Member foundMbr = repository.findById(savedMbr.getId()).orElseThrow();

		assertEquals(savedM.getNickname(), foundM.getNickname());
		assertEquals(savedM, foundM);
		assertEquals(savedMbr, foundMbr);
		System.out.println("foundM = " + foundM);
		System.out.println("foundMbr = " + foundMbr);
	}

	@Test
	void addTest() {
	    // given
		List<Member> list = Stream.iterate(1, n -> n + 1)
				.limit(LIMIT)
				.map(n -> Member.builder()
						.nickname("member"+n)
						.email("email"+n+"@gmail.com")
						.bloodType(BloodType.O)
						.passwd("password"+n)
						.build())
				.toList();
		repository.saveAll(list);
	}

}
