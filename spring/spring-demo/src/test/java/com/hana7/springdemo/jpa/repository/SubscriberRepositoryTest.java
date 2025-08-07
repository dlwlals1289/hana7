package com.hana7.springdemo.jpa.repository;

import com.hana7.springdemo.jpa.entity.Subscriber;
import com.hana7.springdemo.jpa.entity.SubscriberRole;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubscriberRepositoryTest {
	@Autowired
	SubscriberRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	@Order(1)
	void addTest() {
		int limit = 5;
		List<Subscriber> roleList = Stream.iterate(1, n -> n + 1).limit(limit)
				.map(n -> Subscriber.builder()
						.email(n + "@gmail.com")
						.nickname("sub" + n)
						.pwd(passwordEncoder.encode("pwd" + n))
						.social(false)
						.build()
						.addRole(SubscriberRole.ROLE_USER)
						.addRole(n > 3 ? SubscriberRole.ROLE_ADMIN : SubscriberRole.ROLE_MANAGER)
				).toList();

		repository.saveAll(roleList);

		Assertions.assertEquals(limit, repository.findAll().size());
	}

	@Test
	@Order(2)
	void readTest() {
		String email = "1@gmail.com";
		Subscriber subscriber = repository.getWithRoles(email);
		System.out.println("subscriber = " + subscriber);
		System.out.println("subscriber.roles = " + subscriber.getRoles());
		Assertions.assertEquals(email, subscriber.getEmail());
		Assertions.assertEquals(List.of(SubscriberRole.ROLE_USER, SubscriberRole.ROLE_MANAGER), subscriber.getRoles());
	}

	@Test
	void passwordEncodingTest() {
		String pwd = "pwd00";
		System.out.println("passwordEncoder.encode(pwd) = " + passwordEncoder.encode(pwd));
	}

}
