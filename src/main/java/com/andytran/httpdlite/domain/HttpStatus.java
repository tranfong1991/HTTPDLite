package com.andytran.httpdlite.domain;

public final class HttpStatus {
	public static final int OK = 200;
	public static final int CREATED = 201;
	public static final int BAD_REQUEST = 400;
	public static final int UNAUTHORIZED = 401;
	public static final int NOT_FOUND = 404;
	public static final int INTERNAL_SERVER_ERROR = 500;
	
	public static final String OK_STR = "OK";
	public static final String NOT_FOUND_STR = "Not Found";
	public static final String BAD_REQUEST_STR = "Bad Request";
	public static final String UNAUTHORIZED_STR = "Unauthorized";
	public static final String CREATED_STR = "Created";
	public static final String INTERNAL_SERVER_ERROR_STR = "Internal Server Error";
	
	public static String getCodeStr(int code){
		String str = null;
		switch(code){
		case OK:
			str = OK_STR;
			break;
		case CREATED:
			str = CREATED_STR;
			break;
		case BAD_REQUEST:
			str = BAD_REQUEST_STR;
			break;
		case UNAUTHORIZED:
			str = UNAUTHORIZED_STR;
			break;
		case NOT_FOUND:
			str = NOT_FOUND_STR;
			break;
		case INTERNAL_SERVER_ERROR:
			str = INTERNAL_SERVER_ERROR_STR;
			break;
		}
		return str;
	}
}
