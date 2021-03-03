package com.form.apirest.exception;

import java.util.List;
import java.util.Map;

import com.form.apirest.exception.BaseException;

public class ValidateException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DEFAULT_TITLE_MESSAGE = "Validation service";

	public ValidateException(String codeMessage, Object[] args) {
		super(DEFAULT_TITLE_MESSAGE, codeMessage, args);
	}
	
	public ValidateException(String codeMessage, List<Map<String, Object>> args) {
		super(DEFAULT_TITLE_MESSAGE, codeMessage, args);
	}
	

	public ValidateException(String titulo, String codeMessage, Object[] args) {
		super(titulo, codeMessage, args);
	}

	public ValidateException(String codeMessage, Object[] args, Throwable cause) {
		super(DEFAULT_TITLE_MESSAGE, codeMessage, args, cause);
	}

	public ValidateException(String codeMessage, String message) {
		super(codeMessage, message, message);
	}
	
	public ValidateException(String codeMessage, String message, String messageAux) {
		super(codeMessage, message);
	}

}
