package com.springboot.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springboot.project.entities.Category;
import com.springboot.project.entities.Post;
import com.springboot.project.entities.User;

public interface PostRepo extends JpaRepository<Post,Integer> {
	
	List<Post> findAllByUser(User user);
	List<Post> findAllByCategory(Category findCategory);
	@Query("select p from Post p where p.title like :key")
	List<Post> findAllByTitle(@Param("key") String title);

}
