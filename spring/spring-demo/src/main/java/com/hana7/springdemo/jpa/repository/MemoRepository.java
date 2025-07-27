package com.hana7.springdemo.jpa.repository;


import com.hana7.springdemo.jpa.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
