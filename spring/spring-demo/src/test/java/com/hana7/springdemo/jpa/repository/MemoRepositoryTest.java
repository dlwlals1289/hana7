package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Memo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemoRepositoryTest {
	@Autowired
	MemoRepository memoRepository;

	@Test
	public void testClass(){
		System.out.println(memoRepository.getClass().getName());
	}

	@Test
	void saveMemo() {
	    // given
		Memo memo = Memo.builder()
				.memoText("메모1")
				.build();

		Memo saveMemo = memoRepository.save(memo);
		Memo findMemo = memoRepository.findById(memo.getId()).orElseThrow();

	    Assertions.assertThat(saveMemo).isEqualTo(findMemo);
	}

	@Test
	public void testInsertDummies() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			Memo memo = Memo.builder().memoText("Sample ... " +i).build();
			memoRepository.save(memo);
		});
	}






}
