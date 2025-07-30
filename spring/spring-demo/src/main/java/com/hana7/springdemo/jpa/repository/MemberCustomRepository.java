package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemberCustomRepository {
	Page<Member> findAllByNicknameOrEmailContains(String keyword, Pageable pageable);
}
