package com.springboot.project.payloads;
import java.sql.Date;

import com.springboot.project.entities.Category;
import com.springboot.project.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class PostDto {
	
	
	private String title;
	
	
	private String content;
	
	private Date addedDate;
	
	private String imageName;
	
	private CategoryDto category;
	
	private UserDto user;
	
	
	

}
