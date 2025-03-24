package com.springboot.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.entities.Category;
import com.springboot.project.entities.Post;
import com.springboot.project.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {
	
	List<Post> findAllByUser(User user);
	List<Post> findAllByCategory(Category findCategory);

}
