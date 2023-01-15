package com.springSecurityForOrogeny.orogeny.domain;

import org.springframework.http.HttpStatus;

public class HttpResponse {
	
	private int httpStatusCode; // 200 response, 201 successful, -- 500 error, -- 400 not good url
	private HttpStatus httpStatus;
	private String reason;// reason like unauthorized, bad gateway 500 something
	private String message;// developers message like some json file {
		//   code:200
		//   httpStatus:OK
		//   reason: OK}
		//	 message: Your request was successful very descriptive message
	
	
	
	public HttpResponse(int httpStatusCode, HttpStatus httpStatus, String reason, String message) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.httpStatus = httpStatus;
		this.reason = reason;
		this.message = message;
	}
	
//	public HttpResponse() {
//			super();
//	}don't have this because status code something like 0 won't make any sense

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
