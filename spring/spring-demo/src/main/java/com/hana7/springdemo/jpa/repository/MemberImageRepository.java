package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Member;
import com.hana7.springdemo.jpa.entity.MemberImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberImageRepository extends JpaRepository<MemberImage, Long> {
	List<MemberImage> findAllByMember(Member member);
}
