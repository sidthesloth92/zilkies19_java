package io.zilker.application.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1L;

	public BusinessException(String errorMessage) {
		super(errorMessage);
	}
}
