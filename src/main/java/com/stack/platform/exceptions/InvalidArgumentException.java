package com.stack.platform.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidArgumentException extends BaseException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidArgumentException(String msg) {
		super(msg, HttpStatus.BAD_REQUEST);
	}

}
