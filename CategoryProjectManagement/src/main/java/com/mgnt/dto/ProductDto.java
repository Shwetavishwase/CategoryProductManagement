package com.mgnt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

	private Integer productId;

	private String productName;

	private String description;

	private Float productPrice;

}
