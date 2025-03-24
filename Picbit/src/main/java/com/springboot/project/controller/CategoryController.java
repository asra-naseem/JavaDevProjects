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
import org.springframework.web.bind.annotation.RestController;

import com.springboot.project.payloads.CategoryDto;
import com.springboot.project.payloads.UserDto;
import com.springboot.project.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory( @RequestBody CategoryDto categoryDto)
	{
		
		CategoryDto categoryDto1 = categoryService.createCateory(categoryDto);
		return new ResponseEntity<>(categoryDto1,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer id){
		return ResponseEntity.ok(categoryService.updateCategory(categoryDto, id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
		categoryService.deleteCategory(id);
		return new ResponseEntity(Map.of("message","User deleted successfully"),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> allCategories = categoryService.getAllCategoriess();
		return new ResponseEntity<>(allCategories,HttpStatus.OK);
		
	}
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId){
		
		return new ResponseEntity<>(categoryService.getCategoryById(categoryId),HttpStatus.OK);
		
	}

	

}
