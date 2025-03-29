package com.springboot.project.payloads;

import java.util.List;

import com.springboot.project.entities.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
	
	private List<PostDto> postsList;
	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private  boolean isLast;
	private long totalElements;
	

}
