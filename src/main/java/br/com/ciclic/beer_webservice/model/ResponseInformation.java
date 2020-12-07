package br.com.ciclic.beer_webservice.model;

import org.springframework.http.HttpStatus;

public class ResponseInformation {

	private HttpStatus httpStatus;
	private String cause;

	public ResponseInformation(HttpStatus httpStatus, String cause) {
		this.httpStatus = httpStatus;
		this.cause = cause;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
}
