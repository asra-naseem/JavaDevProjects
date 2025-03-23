package com.springboot.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.project.entities.User;
import com.springboot.project.exceptions.ResourceNotFoundException;
import com.springboot.project.payloads.UserDto;
import com.springboot.project.repositories.UserRepo;

import jakarta.transaction.Transactional;
@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userTodto(savedUser);
		
		
		
	}
   
	@Transactional
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User updateUser = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		updateUser.setName(userDto.getName());
		updateUser.setAbout(userDto.getAbout());
		updateUser.setEmailId(userDto.getEmailId());
		updateUser.setPassword(userDto.getPassword());
		  this.userRepo.save(updateUser);
		  return this.userTodto(updateUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User foundUser = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
		return this.userTodto(foundUser);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		return this.userRepo.findAll().stream().map(eachUser-> this.userTodto(eachUser)).collect(Collectors.toList());
		
	}

	@Override
	public void deleteUser(Integer userId) {
		User deleteUser = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		this.userRepo.delete(deleteUser);
		
	}
	
	public User dtoToUser(UserDto userDto) {
		return this.modelMapper.map(userDto, User.class);
		

	}

	public UserDto userTodto(User user) {
		return this.modelMapper.map(user, UserDto.class);
		
	}

}
