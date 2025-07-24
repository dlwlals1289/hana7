package com.hana7.springdemo.service;

import com.hana7.springdemo.dao.PostDAO;
import com.hana7.springdemo.dao.UserDAO;
import com.hana7.springdemo.dto.Post;
import com.hana7.springdemo.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
	private final PostDAO repository;
	private final UserDAO userRepository;

	public PostService(PostDAO repository, UserDAO userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

	public Post getPost(int id){
		System.out.println(repository.getPost(id));
		return repository.getPost(id);
	}

	public List<Post> getPosts(){
		System.out.println("adfasd");
		return repository.getPosts();
	}

	public void insert(Post post) {

		repository.insert(post);
	}

	public void update(Post post){
		repository.update(post);
	}

	public void delete(int id){
		repository.delete(id);
	}
}
