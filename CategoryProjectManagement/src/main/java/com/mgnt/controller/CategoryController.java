package com.mgnt.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mgnt.config.Constants;
import com.mgnt.dto.ApiResponse;
import com.mgnt.dto.CategoryDto;
import com.mgnt.exception.ResourceNotCreatedException;
import com.mgnt.exception.ResourceNotFoundException;
import com.mgnt.service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	private CategoryService categoryServiceImpl;
	
	@PostMapping("/category/create")
	public ResponseEntity<ApiResponse> createCategoryInfo(@RequestBody CategoryDto categoryDto)
	{
		ApiResponse apiResponse = new ApiResponse();

		try {
			CategoryDto courses = this.categoryServiceImpl.createCategory(categoryDto);

			apiResponse.setData(courses);
			apiResponse.setMessage("Category details created succesfully!!!");
			apiResponse.setSuccess(true);
			apiResponse.setError(false);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);
		}

		catch (ResourceNotCreatedException resourceNotCreated) {
			apiResponse.setMessage(resourceNotCreated.getMessage());
			apiResponse.setSuccess(false);
			apiResponse.setError(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("category/update/{categoryId}")
	public ResponseEntity<ApiResponse> updateCategoryInfo(@PathVariable Integer categoryId,
			@RequestBody CategoryDto categoryDto)
	{
		ApiResponse apiResponse=new ApiResponse();
		
		try {
			CategoryDto category= this.categoryServiceImpl.updateCategory(categoryDto, categoryId);
			
			apiResponse.setData(category);
			apiResponse.setMessage("Category info updated successfully");
			apiResponse.setSuccess(true);
			apiResponse.setError(false);
			
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
		} catch (ResourceNotFoundException resourceNotFound) {
			
			apiResponse.setMessage("Category info not updated successfully");
			apiResponse.setSuccess(false);
			apiResponse.setError(false);
			
			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<ApiResponse> getCategoryInfoById(@PathVariable Integer categoryId)
	{
		ApiResponse apiResponse = new ApiResponse();

		try {
			CategoryDto category = this.categoryServiceImpl.getCategoryById(categoryId);
			apiResponse.setData(category);
			apiResponse.setMessage("Category info get by id succesfully!!!");
			apiResponse.setSuccess(true);
			apiResponse.setError(false);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		}

		catch (ResourceNotFoundException resourceNotFound) {
			apiResponse.setMessage("Category details can't fetch succesfully!!!");
			apiResponse.setSuccess(false);
			apiResponse.setError(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/category")
	public ResponseEntity<ApiResponse> getAllCategoriesInfo(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber, @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize, @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIRECTION, required = false) String sortDirection)
	{
		List<CategoryDto> allCategory = this.categoryServiceImpl.getAllCategory(pageNumber, pageSize, sortBy, sortDirection);

		ApiResponse apiResponse = new ApiResponse();

		if (!allCategory.isEmpty()) {
			apiResponse.setData(allCategory);
			apiResponse.setMessage("All category info get succesfully!!!");
			apiResponse.setSuccess(true);
			apiResponse.setError(false);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setMessage("All category details can't fetch succesfully!!!");
			apiResponse.setSuccess(false);
			apiResponse.setError(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategoryInfoById(@PathVariable Integer categoryId)
	{
		boolean deleteCategoryById = this.categoryServiceImpl.deleteCategory(categoryId);
		ApiResponse apiResponse = new ApiResponse();

		if (deleteCategoryById == true) {

			apiResponse.setMessage("Category info deleted  Succesfully!!!");
			apiResponse.setSuccess(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		}

		else {
			apiResponse.setMessage("Category info can't deleted  Succesfully!!!");
			apiResponse.setSuccess(false);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}
	
}
