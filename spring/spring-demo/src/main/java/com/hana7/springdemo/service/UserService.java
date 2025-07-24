package com.hana7.springdemo.service;

import com.hana7.springdemo.dao.UserDAO;
import com.hana7.springdemo.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private final UserDAO repository;

	public UserService(UserDAO repository) {
		this.repository = repository;
	}

	public User getUser(int id){
		return repository.getUser(id);
	}

	public List<User> getUsers(){
		return repository.getUsers();
	}

	public void insert(User user) {
		repository.insert(user);
	}

	public void update(User user){
		repository.update(user);
	}

	public void delete(int id){
		repository.delete(id);
	}

}
