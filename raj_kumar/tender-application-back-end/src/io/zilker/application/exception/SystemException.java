package io.zilker.application.exception;

public class SystemException extends Exception {

	private static final long serialVersionUID = 1L;

	public SystemException(String errorMessage) {
		super(errorMessage);
	}
}
