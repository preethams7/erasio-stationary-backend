package com.hospital.base.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -7536413725363126904L;
	private String exceptionDetail;
	private Object fieldValue;

	public RecordNotFoundException(String exceptionDetail, Object object) {
		super(exceptionDetail + " - " + object);
		this.exceptionDetail = exceptionDetail;
		this.fieldValue = object;
	}

	public RecordNotFoundException(String exceptionDetail, String email, String adminsizespecificationid) {
		super(exceptionDetail + " - " + email);
		this.exceptionDetail = exceptionDetail;
		this.fieldValue = email;
	}

	public String getExceptionDetail() {
		return exceptionDetail;
	}

	public Object getFieldValue() {
		return fieldValue;
	}
}