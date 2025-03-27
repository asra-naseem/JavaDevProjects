package com.springboot.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.payloads.PostDto;
import com.springboot.project.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		return new ResponseEntity<>(postService.createPost(postDto, userId, categoryId),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategoryId(@PathVariable Integer categoryId){
		return new ResponseEntity<List<PostDto>>(this.postService.getPostByCategory(categoryId),HttpStatus.OK);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPost(@RequestParam(value="pageNumber",defaultValue="0",required=false) Integer pageNumber,@RequestParam(value="pageSize",defaultValue="2",required=false) Integer pageSize){
		return new ResponseEntity<List<PostDto>>(this.postService.getAllPost(pageNumber,pageSize),HttpStatus.OK);
		
	}
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		return new ResponseEntity<>(this.postService.getSinglePost(postId),HttpStatus.OK);
		
	}
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUserId(@PathVariable Integer userId){
		return new ResponseEntity<List<PostDto>>(this.postService.getPostByUser(userId),HttpStatus.OK);
		
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePostByPostId(@RequestBody PostDto postDto,@PathVariable Integer postId){
		return new ResponseEntity<PostDto>(this.postService.updatePost(postDto,postId),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<?> deletePostByPostId(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ResponseEntity(Map.of("message","User deleted successfully"),HttpStatus.OK);
		
	}
	
	

}
