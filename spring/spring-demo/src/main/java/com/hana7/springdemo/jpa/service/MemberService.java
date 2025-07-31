package com.hana7.springdemo.jpa.service;

import com.hana7.springdemo.jpa.dto.SearchCond;
import com.hana7.springdemo.jpa.dto.member.MemberDTO;

import java.util.List;

public interface MemberService {
	List<MemberDTO> findAll(SearchCond searchCond);

	MemberDTO findOne(long id);

	int remove(long id);
}
