package com.hana7.springdemo.jpa.controller;

import com.hana7.springdemo.jpa.dto.MemberDetailResponseDto;
import com.hana7.springdemo.jpa.dto.MemberRequestDTO;
import com.hana7.springdemo.jpa.dto.MemberResponseDTO;
import com.hana7.springdemo.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@GetMapping("")
	Page<MemberResponseDTO> findAll(@PageableDefault(size = 10) Pageable pageable) {
		return memberService.findAll(pageable);
	}

	@GetMapping("/search")
	Page<MemberResponseDTO> findMember(
			@RequestParam String keyword,
			@PageableDefault(size = 10, sort = "nickname") Pageable pageable) {
		return memberService.findMember(keyword, pageable);

	}

	@GetMapping("/{id}")
	ResponseEntity<MemberDetailResponseDto> findOne(@PathVariable Long id) {
		return ResponseEntity.ok(memberService.findOne(id));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> delete(@PathVariable Long id) {
		memberService.remove(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@PostMapping
	MemberResponseDTO save(MemberRequestDTO dto) {
		return null;
	}
}
