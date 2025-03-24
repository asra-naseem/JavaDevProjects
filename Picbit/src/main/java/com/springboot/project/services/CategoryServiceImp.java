package com.springboot.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.project.entities.Category;
import com.springboot.project.entities.User;
import com.springboot.project.exceptions.ResourceNotFoundException;
import com.springboot.project.payloads.CategoryDto;
import com.springboot.project.payloads.UserDto;
import com.springboot.project.repositories.CategoryRepo;

@Service
public class CategoryServiceImp implements CategoryService{

	
	@Autowired
	private CategoryRepo categoryRepo ;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCateory(CategoryDto categoryDto) {
		Category category = this.dtoToCategory(categoryDto);
		return this.CategoryTodto(this.categoryRepo.save(category));
		
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
		category.setCategoryName(categoryDto.getCategoryName());
		this.categoryRepo.save(category);
		return this.CategoryTodto(category);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
		
		return this.CategoryTodto(category);
	}

	@Override
	public List<CategoryDto> getAllCategoriess() {
		
		return this.categoryRepo.findAll().stream().map((eachcategory)->this.CategoryTodto(eachcategory)).collect(Collectors.toList());
		
		
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","id",categoryId));
		this.categoryRepo.delete(category);
		
		
	}
	public Category dtoToCategory(CategoryDto categoryDto) {
		return this.modelMapper.map(categoryDto, Category.class);
		

	}

	public CategoryDto CategoryTodto(Category category) {
		return this.modelMapper.map(category, CategoryDto.class);
		
	}

}
