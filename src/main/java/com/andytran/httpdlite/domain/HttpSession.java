package com.andytran.httpdlite.domain;

import java.util.Map;

public class HttpSession {
	
	public String queryPath;
	public Map<String, String> params;
	public HttpMethod method;
	public String body;
	
	public String getQueryPath() {
		return queryPath;
	}
	
	public void setQueryPath(String queryPath) {
		this.queryPath = queryPath;
	}
	
	public Map<String, String> getParams() {
		return params;
	}
	
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	public HttpMethod getMethod() {
		return method;
	}
	
	public void setMethod(HttpMethod method) {
		this.method = method;
	}
	
	public String getBody(){
		return this.body;
	}
	
	public void setBody(String body){
		this.body = body;
	}
	
}
