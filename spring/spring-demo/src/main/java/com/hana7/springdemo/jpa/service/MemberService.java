package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.MemberDTO;
import com.hana7.springdemo.jpa.dto.MemberDetailResponseDto;
import com.hana7.springdemo.jpa.dto.MemberRequestDTO;
import com.hana7.springdemo.jpa.dto.MemberResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberService {
	Page<MemberResponseDTO> findAll(Pageable pageable);

	Page<MemberResponseDTO> findMember(String keyword, Pageable pageable);

	MemberDetailResponseDto findOne(Long id);

	MemberDTO save(MemberRequestDTO dto);

	void remove(Long memberId);


}
