package com.hana7.springdemo.jpa.controller;

import java.util.List;

import com.hana7.springdemo.jpa.dto.MemberDTO;
import com.hana7.springdemo.jpa.dto.MemberDetailResponseDto;
import com.hana7.springdemo.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.hana7.springdemo.jpa.dto.MemberRequestDTO;
import com.hana7.springdemo.jpa.dto.MemberResponseDTO;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("")
	List<MemberDTO> findAll() {
		return memberService.findAll();
	}

	@GetMapping("/search")
	MemberDetailResponseDto findOne(@RequestParam String keyword) {
		return memberService.findOne(keyword);

	}


	@PostMapping
	MemberResponseDTO save(MemberRequestDTO dto) {
		return null;
	}
}
