package com.hana7.springdemo;

import com.hana7.springdemo.dto.User;
import com.hana7.springdemo.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Log4j2
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping("")
	public ResponseEntity<User> regist(@RequestBody @Valid User user){
		log.debug(user.getName());
		service.insert(user);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findUser(@PathVariable("id") int id){
		log.info("UserId={}", id);

		return ResponseEntity.ok(service.getUser(id));
	}

	@GetMapping("")
	public ResponseEntity<List<User>> findUsers() {
		return ResponseEntity.ok(service.getUsers());
	}


	@PatchMapping("{id}")
	public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") int id, @RequestBody @Valid User user){
		log.debug(user.getName());
		user.setId(id);
		service.update(user);
		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") int id){
		log.info("UserId={}", id);
		service.delete(id);
		return ResponseEntity.ok(HttpStatus.ACCEPTED);
	}
}
