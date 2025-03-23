package com.springboot.project.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	
	private int userId;
	
	@NotEmpty
	@Size(min=4,max=35,message="Length of name should be minimum of size 4")
	private String name;
	
	@NotEmpty
	@Size(min=9,max=35,message="Length of password should be minimum of size 9 ")
	private String password;
	
	@NotNull
	private String about;
	
	@Email
	private String emailId;
	

}
