package com.mgnt.service;

import java.util.List;

import com.mgnt.dto.ProductDto;

public interface ProductService {

	// create
	public ProductDto createProduct(ProductDto productDto, Integer categoryId);

	// update
	public ProductDto updateProduct(ProductDto productDto, Integer productId);

	// getById
	public ProductDto getProductById(Integer productId);

	// getAll
	public List<ProductDto> getAllProduct(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection);

	// delete
	public boolean deleteProduct(Integer productId);

}
