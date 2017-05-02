package com.andytran.httpdlite.domain;

import java.util.HashMap;
import java.util.Map;

public class HttpSession {
	
	public String uri;
	public Map<String, String> params;
	public HttpMethod method;
	public String body;
	
	public HttpSession(){
		this.uri = null;
		this.params = new HashMap<>();
		this.method = null;
		this.body = null;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public Map<String, String> getParams() {
		return params;
	}
	
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	public void addParam(String key, String val){
		if(key == null || key.isEmpty())
			return;
		this.params.put(key, val);
	}
	
	public void removeParam(String key){
		if(key == null || key.isEmpty())
			return;
		this.params.remove(key);
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

	@Override
	public String toString() {
		return "HttpSession [uri=" + uri + ", params=" + params + ", method=" + method + ", body=" + body + "]";
	}
	
}
