package com.springboot.project.services;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.query.Page;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.springboot.project.entities.Category;
import com.springboot.project.entities.Post;
import com.springboot.project.entities.User;
import com.springboot.project.exceptions.ResourceNotFoundException;
import com.springboot.project.payloads.PostDto;
import com.springboot.project.repositories.CategoryRepo;
import com.springboot.project.repositories.PostRepo;
import com.springboot.project.repositories.UserRepo;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User foundUser = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
		
		
		Post newPost = this.modelMapper.map(postDto, Post.class);
		Date todaysDate = new Date(System.currentTimeMillis());
		newPost.setAddedDate(todaysDate);
		newPost.setImageName("Default.png");
		newPost.setUser(foundUser);
		newPost.setCategory(category);
		postRepo.save(newPost);
		return this.modelMapper.map(newPost,PostDto.class );
		
		
	}

	@Override
	public PostDto updatePost(PostDto postDto,Integer postId) {
		Post updatePost = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postID",postId));
		updatePost.setTitle(postDto.getTitle());
		updatePost.setContent(postDto.getContent());
		updatePost.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepo.save(updatePost);
		return this.modelMapper.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post deletePost = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postID",postId));
		this.postRepo.delete(deletePost);
		
	}

	@Override
	public List<PostDto> getAllPost(Integer pageNumber, Integer pageSize) {
		PageRequest pageable = PageRequest.of(pageNumber, pageSize);
		List<Post> post=this.postRepo.findAll(pageable).getContent();
		
		return post.stream().map((eachPost)->this.modelMapper.map(eachPost,PostDto.class)).collect(Collectors.toList());
		
	}

	@Override
	public PostDto getSinglePost(Integer postId) {
		
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postID",postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category findCategory = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryid",categoryId));
		List<PostDto> postByCategory = this.postRepo.findAllByCategory(findCategory).stream().map((eachPost)->this.modelMapper.map(eachPost, PostDto.class)).collect(Collectors.toList());				
		return postByCategory;
	}
	
	public List<PostDto> getPostByUser(Integer userId) {
		User findUser = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userid",userId));
		List<PostDto> postByUser = this.postRepo.findAllByUser(findUser).stream().map((eachPost)->this.modelMapper.map(eachPost, PostDto.class)).collect(Collectors.toList());				
		return postByUser;
	}

	
	
	

	

}
