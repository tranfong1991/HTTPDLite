package com.andytran.httpdlite.domain;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class HttpResponse {
	public static final class Builder{
		private HttpResponse response;
		
		public Builder(){
			this.response = new HttpResponse();
		}
		
		public Builder httpVersion(String version){
			response.setHttpVersion(version);
			return this;
		}
		
		public Builder statusCode(int code){
			response.setStatusCode(code);
			return this;
		}
		
		public Builder body(String body){
			response.setBody(body);
			return this;
		}
		
		public Builder body(JSONObject json){
			response.setBody(json.toString());
			return this;
		}
		
		public Builder param(String key, String value){
			response.getParams().put(key, value);
			return this;
		}
		
		public HttpResponse build(){
			return response;
		}
	}
	
	private String httpVersion;
	private int statusCode;
	private Map<String, String> params;
	private String body;
	
	public HttpResponse(){
		this.httpVersion = "1.1";
		this.statusCode = HttpStatus.OK;
		
		this.params = new HashMap<>();
		params.put("Server", "HttpdLite 1.0");
		params.put("Accept", "application/json");
		
		this.body = null;
	}
	
	public String getHttpVersion() {
		return httpVersion;
	}
	
	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	public Map<String, String> getParams() {
		return params;
	}
	
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HTTP/")
			.append(httpVersion)
			.append(" ")
			.append(statusCode)
			.append(" ")
			.append(HttpStatus.getCodeStr(statusCode))
			.append("\r\n");
		
		for(String key : params.keySet()){
			builder.append(key)
				.append(": ")
				.append(params.get(key))
				.append("\r\n");
		}
		
		if(body != null && !body.isEmpty()){
			builder.append("\r\n");
			builder.append(body);
		}
				
		return builder.toString();
	}
	
}
