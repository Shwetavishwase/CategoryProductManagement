package com.mgnt.service.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mgnt.Repository.CategoryRepository;
import com.mgnt.Repository.ProductRepository;
import com.mgnt.dto.ProductDto;
import com.mgnt.entity.CategoryEntity;
import com.mgnt.entity.ProductEntity;
import com.mgnt.exception.ResourceNotFoundException;
import com.mgnt.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDto createProduct(ProductDto productDto, Integer categoryId) {

		CategoryEntity categoryEntity = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("CategoryEntity", "categoryId", categoryId));

		ProductEntity product = this.modelMapper.map(productDto, ProductEntity.class);
		
		product.setCategory(categoryEntity);

		ProductEntity productEntity = this.productRepo.save(product);

		return this.modelMapper.map(productEntity, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Integer productId) {

		ProductEntity product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("ProductEntity", "productId", productId));

		if (productDto.getDescription() != null) {
			product.setDescription(productDto.getDescription());
		}
		if (productDto.getProductName() != null) {
			product.setDescription(productDto.getProductName());
		}
		if (productDto.getProductPrice() != null) {
			product.setProductPrice(productDto.getProductPrice());
		}

		ProductEntity updatedProduct = this.productRepo.save(product);

		return this.modelMapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public ProductDto getProductById(Integer productId) {

		ProductEntity product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("ProductEntity", "productId", productId));

		return this.modelMapper.map(product, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProduct(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

//		Sort sort = null;
//		if (sortDirection.equalsIgnoreCase("asc")) {
//			sort = Sort.by(sortBy).ascending();
//		} else {
//			sort = Sort.by(sortBy).descending();
//		}

		PageRequest post = PageRequest.of(pageNumber, pageSize);

		List<ProductEntity> products = this.productRepo.findAll();

		if (products.isEmpty()) {
			return Collections.emptyList();
		}

		return products.stream().map((product) -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
	}

	@Override
	public boolean deleteProduct(Integer productId) {

		Optional<ProductEntity> product = this.productRepo.findById(productId);

		if (product.isPresent()) {
			this.productRepo.deleteById(productId);
			return true;
		}

		return false;
	}

}
