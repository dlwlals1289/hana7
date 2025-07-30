package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Board;
import com.hana7.springdemo.jpa.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
	List<Reply> findAllByBoard(Board board);
}
