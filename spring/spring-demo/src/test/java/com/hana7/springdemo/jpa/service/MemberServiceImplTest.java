package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.MemberDTO;
import com.hana7.springdemo.jpa.dto.MemberDetailResponseDto;
import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {
	@Autowired
	private MemberService service;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	void findAll() {
	    // given
		List<MemberDTO> memberList = service.findAll();
		long allMembers = memberRepository.count();

	    Assertions.assertThat(memberList.size()).isEqualTo((int)allMembers);
	}


	@Test
	void findOne() {
		//given
		MemberDetailResponseDto member = service.findOne("지민");

		Assertions.assertThat(member.getEmail()).isEqualTo("dlwlals1289@gmail.com");
		Assertions.assertThat(member.getBoards().size()).isEqualTo(10);
	}

	@Test
	void removeMember() {
		long memberId = 22;
	    service.remove(memberId++);
	}

}
