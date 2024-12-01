package com.mgnt.service;

import java.util.List;

import com.mgnt.dto.CategoryDto;

public interface CategoryService {

	//create
	public CategoryDto createCategory(CategoryDto categoryDto);

	// update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

	// getById
	public CategoryDto getCategoryById(Integer categoryId);

	// getAll
	public List<CategoryDto> getAllCategory(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection);
	
	// delete
		public boolean deleteCategory(Integer categoryId);
}
