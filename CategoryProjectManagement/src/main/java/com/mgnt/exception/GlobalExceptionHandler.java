package com.mgnt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mgnt.dto.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException resourceNotFound) {
		String message = resourceNotFound.getMessage();

		ApiResponse apiResponse = new ApiResponse();

		apiResponse.setMessage(message);
		apiResponse.setData(null);
		apiResponse.setSuccess(false);
		apiResponse.setError(false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}
	
	public ResponseEntity<ApiResponse> resourceNotCreatedException(ResourceNotCreatedException resourceNotCreated)
	{
		String message= resourceNotCreated.getMessage();
		
		ApiResponse apiResponse=new ApiResponse();
		
		apiResponse.setMessage(message);
		apiResponse.setData(null);
		apiResponse.setSuccess(false);
		apiResponse.setError(false);

		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
	}
}
