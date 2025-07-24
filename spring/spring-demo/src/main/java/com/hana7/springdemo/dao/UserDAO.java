package com.hana7.springdemo.dao;

import com.hana7.springdemo.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDAO {
	public User getUser(int id);

	public void insert(User user);

	public List<User> getUsers();

	public void update(User user);

	public void delete(int id);

}
