package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Memo;
import com.hana7.springdemo.jpa.entity.QMemo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

// @SpringBootTest
// @Transactional
// @DataJpaTest
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// @Rollback(false)
class MemoRepositoryTest extends RepositoryTest {
	@Autowired
	MemoRepository repository;

	private static Sort getSorting(String field) {
		return Sort.by(Sort.Order.desc(field));
	}

	private static Pageable getPageable(int pageNo, Sort sorting) {
		return PageRequest.of(pageNo - 1, 10, sorting);
	}

	@Test
	@Order(1)
	void testClass() {
		Memo m = Memo.builder().memoText("TTT").build();
		Memo savedM = repository.save(m);
		assertEquals(m, savedM);

		Memo foundMemo = repository.findById(savedM.getMno()).orElseThrow();
		assertEquals(savedM, foundMemo);

		System.out.println("foundMemo = " + foundMemo);
		System.out.println("repository.getClass().getName() = " + repository.getClass().getName());

		foundMemo.setMemoText("New MemoText!!");
		repository.saveAndFlush(foundMemo);
		System.out.println("foundMemo = " + foundMemo);

		savedM.setMemoText("SSSS");
		repository.saveAndFlush(savedM);
		System.out.println("savedM = " + savedM);

		repository.deleteById(savedM.getMno());
		Optional<Memo> byId = repository.findById(savedM.getMno());

		byId.ifPresent(memo -> System.out.println("byId = " + memo));
	}

	@Test
	@Commit
	@Order(2)
	void add100Test() {
		List<Memo> list = Stream.iterate(1, n -> n + 1).limit(100)
				.map(n -> Memo.builder().memoText("Text" + n).build())
				.collect(Collectors.toList());

		repository.saveAll(list);

		assertEquals(100, repository.count());
	}

	@Test
	@Order(3)
	void pagingTest() {
		// Sort sorting = Sort.by("mno").descending();
		Sort sorting = getSorting("mno");
		Page<Memo> p1 = repository.findAll(getPageable(1, sorting));
		p1.stream().forEach(this::print);

		repository.findAll(getPageable(2, sorting)).stream().forEach(this::print);
	}

	@Test
	@Order(3)
	void queryMethodTest() {
		List<Memo> memo10To20 = repository.findByMnoBetweenOrderByMnoDesc(10, 20);
		printList(memo10To20);

		printList(repository.findByMnoBetween(2, 20, getSorting("memoText")));
	}

	@Test
	@Order(4)
	@Commit
	void deleteTest() {
		repository.deleteById(100);
		assertFalse(repository.findById(100).isPresent());

		repository.deleteByMnoBetween(81, 90);
		assertEquals(89, repository.count());

		long removeCnt = repository.removeByMnoBetween(91, 100);
		System.out.println("removeCnt = " + removeCnt);
		assertEquals(80, repository.count());
	}

	@Test
	@Order(5)
	void queryAnnotationTest() {
		List<Memo> list = repository.getListOverDesc(70);
		list.forEach(this::print);

		List<Object[]> listSome = repository.getListSomeDesc();
		for (Object[] objs : listSome) {
			System.out.println(Arrays.toString(objs));
		}
	}

	@Test
	@Order(6)
	void queryDslTest() {
		Iterable<Memo> memo5s = repository.findAll(QMemo.memo.memoText.contains("5"));
		memo5s.forEach(this::print);

		repository.findAll(
						QMemo.memo.mno.goe(60)
								.and(QMemo.memo.memoText.contains("5")))
				.forEach(this::print);

		BooleanBuilder bb = new BooleanBuilder();
		// BooleanExpression over60 = QMemo.memo.mno.goe(60);
		BooleanExpression over60 = getBoolExp(0);
		bb.and(over60).and(getContainsText("5"));
		repository.findAll(bb).forEach(this::print);
	}

	private BooleanExpression getBoolExp(int mno) {
		if (mno > 0)
			return QMemo.memo.mno.goe(mno);

		return null;
	}

	private BooleanExpression getContainsText(String txt) {
		if (StringUtils.hasText(txt))
			return QMemo.memo.memoText.contains(txt);

		return null;
	}

	private void printList(List<Memo> list) {
		list.forEach(this::print);
	}

	private void print(Memo memo) {
		System.out.println(memo.getMno() + " - " + memo.getMemoText());
	}
}
