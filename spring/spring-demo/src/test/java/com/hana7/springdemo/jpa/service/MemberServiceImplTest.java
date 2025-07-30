package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceImplTest {
	@Autowired
	private MemberService service;

	@Autowired
	private MemberRepository memberRepository;

//	@Test
//	void findAll() {
//	    // given
//		List<MemberDTO> memberList = service.findAll();
//		long allMembers = memberRepository.count();
//
//	    Assertions.assertThat(memberList.size()).isEqualTo((int)allMembers);
//	}
//
//
//	@Test
//	void findOne() {
//		//given
//		MemberDetailResponseDto member = service.findOne("지민");
//
//		Assertions.assertThat(member.getEmail()).isEqualTo("dlwlals1289@gmail.com");
//		Assertions.assertThat(member.getBoards().size()).isEqualTo(10);
//	}

	@Test
	void removeMember() {
		long memberId = 1;
		service.remove(memberId);
	}

}
