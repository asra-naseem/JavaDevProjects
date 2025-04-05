package com.springboot.project.services;

import java.util.List;

import com.springboot.project.payloads.PostDto;
import com.springboot.project.payloads.PostResponse;

public interface PostService  {

	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	public void deletePost(Integer postId);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);
	PostDto getSinglePost(Integer postId);
	List<PostDto>getPostByCategory(Integer categoryId);
	List<PostDto>getPostByUser(Integer userId);
	PostDto updatePost(PostDto postDto, Integer postId);
	List<PostDto> getPostByTitle(String keyword);
	
}
