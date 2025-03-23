package com.springboot.project.services;

import com.springboot.project.payloads.UserDto;
import java.util.*;

import org.springframework.stereotype.Service;

public interface UserService {
	
	public UserDto createUser(UserDto userDto);
	public UserDto updateUser(UserDto userDto, Integer userId);
	public UserDto getUserById(Integer userId);
	public List<UserDto> getAllUsers();
	public void deleteUser(Integer userId);
	

}
