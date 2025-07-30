package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hana7.springdemo.jpa.entity.Board;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
	List<Board> findAllByWriter(Member writer);
}
