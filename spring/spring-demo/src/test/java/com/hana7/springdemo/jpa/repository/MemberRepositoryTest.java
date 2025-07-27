package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {
	@Autowired
	MemberRepository memberRepository;

	@Test
	void save() {
	    // given
		Member member = Member.builder()
				.nickName("kim")
				.email("kim@gmail.com")
				.build();

		Member afterSave = memberRepository.save(member);
		System.out.println(afterSave);

		Member findUser = memberRepository.findById(member.getId()).orElse(null);

	    Assertions.assertThat(afterSave).isEqualTo(findUser);

	}

	@Test
	void saveTest() {
		// give
		Member m = Member.builder().nickName("Kim").email("kim@gmail.com").build();

		Member mbr = new Member();
		// mbr.setNickname("Hong");
		mbr.setEmail("hong@gmail.com");

		// when
		Member savedM = memberRepository.save(m);
		Member savedMbr = memberRepository.save(mbr);

		System.out.println("savedMbr = " + savedMbr);

		// then
		Member foundM = memberRepository.findById(savedM.getId()).orElseThrow();
		Member foundMbr = memberRepository.findById(savedMbr.getId()).orElseThrow();


		assertEquals(savedM.getNickName(), foundM.getNickName());
		assertEquals(savedM, foundM);
		assertEquals(savedMbr, foundMbr);
		System.out.println("foundM = " + foundM);
		System.out.println("foundMbr = " + foundMbr);
	}

}
