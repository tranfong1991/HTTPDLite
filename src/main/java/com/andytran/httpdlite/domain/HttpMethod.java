package com.andytran.httpdlite.domain;

public enum HttpMethod {
	GET, POST, DELETE, PUT, HEAD;
	
	public static HttpMethod toHttpMethod(String method){
		HttpMethod m = null;
		switch(method){
			case "GET": 
				m = HttpMethod.GET;
				break;
			case "POST":
				m = HttpMethod.POST;
				break;
			case "DELETE": 
				m = HttpMethod.DELETE;
				break;
			case "PUT": 
				m = HttpMethod.PUT;
				break;
			case "HEAD":
				m = HttpMethod.HEAD;
				break;
		}
		return m;
	}
}
