package com.medteamb.medteamb.service.exception.doctorExceptions;

import io.jsonwebtoken.lang.Supplier;

public class DoctorNotFoundException extends RuntimeException implements Supplier<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3906241308497232882L;
	
	private Integer doctorID;

	public DoctorNotFoundException() {
		super();
	}

	public DoctorNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DoctorNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public DoctorNotFoundException(String message) {
		super(message);
	}

	public DoctorNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public DoctorNotFoundException(Integer doctorID) {
		this.doctorID = doctorID;
	}

	@Override
	public String get() {
		return "The Doctor "+doctorID+" was not found";
	}

	
}
