package com.hana7.springdemo.jpa.repository;

import java.util.Optional;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hana7.springdemo.jpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByNicknameContains(String keyword);
//	Optional<Object> findByNickname(String writer);
	// Optional<Member> findById(Long id);
}
