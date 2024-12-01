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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mgnt.config.Constants;
import com.mgnt.dto.ApiResponse;
import com.mgnt.dto.CategoryDto;
import com.mgnt.dto.ProductDto;
import com.mgnt.exception.ResourceNotCreatedException;
import com.mgnt.exception.ResourceNotFoundException;
import com.mgnt.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productServiceImpl;

	@PostMapping("/category/{categoryId}/product")
	public ResponseEntity<ApiResponse> createProductInfo(@RequestBody ProductDto productDto, @PathVariable Integer categoryId) {
		ApiResponse apiResponse = new ApiResponse();

		try {
			ProductDto product = this.productServiceImpl.createProduct(productDto,categoryId);

			apiResponse.setData(product);
			apiResponse.setMessage("Product info created succesfully!!!");
			apiResponse.setSuccess(true);
			apiResponse.setError(false);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);

		} catch (ResourceNotCreatedException resourceNotCreated) {
			apiResponse.setMessage(resourceNotCreated.getMessage());
			apiResponse.setSuccess(false);
			apiResponse.setError(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/product/update/{productId}")
	public ResponseEntity<ApiResponse> updateProductInfo(@RequestBody ProductDto productDto, @PathVariable Integer productId) {
		ApiResponse apiResponse = new ApiResponse();

		try {
			ProductDto product = this.productServiceImpl.updateProduct(productDto, productId);

			apiResponse.setData(product);
			apiResponse.setMessage("Product info updated succesfully!!!");
			apiResponse.setSuccess(true);
			apiResponse.setError(false);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

		} catch (ResourceNotFoundException resourceNotFound) {
			apiResponse.setMessage("Product info not updated successfully!!!");
			apiResponse.setSuccess(false);
			apiResponse.setError(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<ApiResponse> getProductInfoById(@PathVariable Integer productId) {
		ApiResponse apiResponse = new ApiResponse();

		try {
			ProductDto product = this.productServiceImpl.getProductById(productId);
			apiResponse.setData(product);
			apiResponse.setMessage("Product info get by id succesfully!!!");
			apiResponse.setSuccess(true);
			apiResponse.setError(false);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		}

		catch (ResourceNotFoundException resourceNotFound) {
			apiResponse.setMessage("Product details can't fetch succesfully!!!");
			apiResponse.setSuccess(false);
			apiResponse.setError(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/product")
	public ResponseEntity<ApiResponse> getAllProductInfo(
			@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize, 
			@RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy, @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIRECTION, required = false) String sortDirection) {

		List<ProductDto> allProducts = this.productServiceImpl.getAllProduct(pageNumber, pageSize, sortBy, sortDirection);

		ApiResponse apiResponse = new ApiResponse();

		if (!allProducts.isEmpty()) {
			apiResponse.setData(allProducts);
			apiResponse.setMessage("All Product info get succesfully!!!");
			apiResponse.setSuccess(true);
			apiResponse.setError(false);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

		} else {
			apiResponse.setMessage("All product details can't fetch succesfully!!!");
			apiResponse.setSuccess(false);
			apiResponse.setError(true);

		}
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<ApiResponse> deleteProductInfoById(@PathVariable Integer productId) {
		boolean deleteProductById = this.productServiceImpl.deleteProduct(productId);
		ApiResponse apiResponse = new ApiResponse();

		if (deleteProductById == true) {

			apiResponse.setMessage("Product info deleted  Succesfully!!!");
			apiResponse.setSuccess(true);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		}

		else {
			apiResponse.setMessage("Product info can't deleted  Succesfully!!!");
			apiResponse.setSuccess(false);

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
		}
	}

}
