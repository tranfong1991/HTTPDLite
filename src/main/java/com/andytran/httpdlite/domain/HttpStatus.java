package com.andytran.httpdlite.domain;

public final class HttpStatus {
	public static final int OK = 200;
	public static final int NOT_FOUND = 404;
	public static final String OK_STR = "OK";
	public static final String NOT_FOUND_STR = "Not Found";
	
	public static String getCodeStr(int code){
		String str = null;
		switch(code){
		case OK:
			str = OK_STR;
			break;
		case NOT_FOUND:
			str = NOT_FOUND_STR;
			break;
		}
		return str;
	}
}
