package com.hana7.springdemo.jpa.controller;

import com.hana7.springdemo.jpa.dto.SearchCond;
import com.hana7.springdemo.jpa.dto.member.MemberDTO;
import com.hana7.springdemo.jpa.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
	private final MemberService service;

	public MemberController(MemberService service) {
		this.service = service;
	}

	@GetMapping()
	List<MemberDTO> findMembers(SearchCond searchCond) {
		System.out.println("searchCond = " + searchCond.getPager());
		return service.findAll(searchCond);
	}

	@GetMapping("{id}")
	MemberDTO getMember(@PathVariable Long id) {
		return service.findOne(id);
	}

	@DeleteMapping("{id}")
	long remove(@PathVariable Long id) {
		return service.remove(id);
	}
}
