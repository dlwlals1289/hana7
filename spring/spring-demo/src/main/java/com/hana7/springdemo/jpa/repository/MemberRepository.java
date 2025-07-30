package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Page<Member> findAll(Pageable pageable);

	void deleteById(Long id);

	Optional<Member> findByNicknameContains(String keyword);
}
