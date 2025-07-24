package com.hana7.springdemo;

import com.hana7.springdemo.dto.Post;
import com.hana7.springdemo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
	private final PostService service;

	public PostController(PostService service) {
		this.service = service;
	}


	@GetMapping("/{id}")
	public ResponseEntity<Post> findPost(@PathVariable("id") int id){
		return ResponseEntity.ok(service.getPost(id));
	}

	@GetMapping("")
	public ResponseEntity<List<Post>> findPosts(){
		return ResponseEntity.ok(service.getPosts());
	}

	@PostMapping("")
	public ResponseEntity<HttpStatus> insertPost(@RequestBody @Valid Post post){
		System.out.println(post);
		return ResponseEntity.ok(HttpStatus.CREATED);
	}
}
