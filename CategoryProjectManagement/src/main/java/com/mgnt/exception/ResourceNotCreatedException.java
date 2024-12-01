package com.mgnt.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResourceNotCreatedException extends RuntimeException{

	private String resourceName;
	
	public ResourceNotCreatedException(String resourceName)
	{
		super(String.format("%s resource not created ", resourceName));
		this.resourceName=resourceName;
	}
}
