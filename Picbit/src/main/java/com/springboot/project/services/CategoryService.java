package com.springboot.project.services;

import java.util.List;

import com.springboot.project.payloads.CategoryDto;


public interface CategoryService {
	
	public CategoryDto createCateory (CategoryDto categoryDto);
	public CategoryDto updateCategory (CategoryDto categoryDto, Integer categoryId);
	public CategoryDto getCategoryById(Integer CategoryId);
	public List<CategoryDto> getAllCategoriess();
	public void deleteCategory(Integer CategoryId);
	

}
