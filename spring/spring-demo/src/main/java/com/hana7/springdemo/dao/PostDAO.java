package com.hana7.springdemo.dao;

import com.hana7.springdemo.dto.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PostDAO {
	public Post getPost(int id);

	public List<Post> getPosts();

	public void insert(Post post);

	public void update(Post post);

	public void delete(int id);
}
