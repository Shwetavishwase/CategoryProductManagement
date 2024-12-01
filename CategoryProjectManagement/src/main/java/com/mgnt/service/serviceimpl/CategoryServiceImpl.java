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
import com.mgnt.dto.CategoryDto;
import com.mgnt.entity.CategoryEntity;
import com.mgnt.exception.ResourceNotFoundException;
import com.mgnt.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		CategoryEntity category=this.modelMapper.map(categoryDto, CategoryEntity.class);
		
		CategoryEntity categoryEntity=this.categoryRepo.save(category);
		
		return this.modelMapper.map(categoryEntity, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		CategoryEntity category = this.categoryRepo.findById(categoryId).
				orElseThrow(() -> new ResourceNotFoundException("CategoryEntity", "categoryId", categoryId));
		
		if(categoryDto.getCategoryDescription()!= null)
		{
			category.setCategoryDescription(categoryDto.getCategoryDescription());
		}
		if(categoryDto.getCategoryTitle()!=null)
		{
			category.setCategoryTitle(categoryDto.getCategoryTitle());
		}
		
		CategoryEntity updatedCategory=this.categoryRepo.save(category);
		
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		
		CategoryEntity category = this.categoryRepo.findById(categoryId).
				orElseThrow(() -> new ResourceNotFoundException("CategoryEntity", "categoryId", categoryId));

		return this.modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection) {
		

		Sort sort=null;
		if(sortDirection.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		
		PageRequest post=PageRequest.of(pageNumber, pageSize,sort);
		
		List<CategoryEntity> categories=this.categoryRepo.findAll();
		
		if(categories.isEmpty())
		{
			return Collections.emptyList();
		}
		return categories.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public boolean deleteCategory(Integer categoryId) {
		
		Optional<CategoryEntity> category = this.categoryRepo.findById(categoryId);
		
		if(category.isPresent())
		{
			this.categoryRepo.deleteById(categoryId);
			return true;
		}
		
		return false;
		

	}

}
