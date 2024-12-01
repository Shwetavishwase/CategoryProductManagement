package com.mgnt.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class CategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer categoryId;

	private String categoryTitle;

	private String categoryDescription;
	
	@OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ProductEntity> products= new ArrayList<>();
}
