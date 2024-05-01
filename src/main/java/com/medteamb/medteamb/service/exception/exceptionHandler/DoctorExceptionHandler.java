package com.medteamb.medteamb.service.exception.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import com.medteamb.medteamb.service.exception.doctorExceptions.DoctorNotFoundException;

@RestControllerAdvice
public class DoctorExceptionHandler {
	
	@ExceptionHandler(DoctorNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public BaseResponse handleDoctorNotFoundException(DoctorNotFoundException e) {
		return new BaseResponse(HttpStatus.NOT_FOUND,e.get());
	}

}
