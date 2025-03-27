package com.springboot.project.services;

import java.util.List;

import com.springboot.project.payloads.PostDto;

public interface PostService  {

	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	public void deletePost(Integer postId);
	List<PostDto> getAllPost(Integer pageNumber, Integer pageSize);
	PostDto getSinglePost(Integer postId);
	List<PostDto>getPostByCategory(Integer categoryId);
	List<PostDto>getPostByUser(Integer userId);
	PostDto updatePost(PostDto postDto, Integer postId);
	
}
