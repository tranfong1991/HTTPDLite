package com.andytran.httpdlite.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import com.andytran.httpdlite.domain.HttpMethod;
import com.andytran.httpdlite.domain.HttpSession;

public class HttpParser {
	
	public static HttpSession parse(BufferedReader in) throws IOException{
		HttpSession session = new HttpSession();
		
		parseRequestLine(session, in.readLine());
		
		String line;
		while((line = in.readLine()) != null){
			//when reach the end of the header
			if(line.isEmpty())
				break;
			parseHeader(session, line);
		}
		
		Map<String, String> params = session.getParams();
		int contentLength = 0;
		if(params.containsKey("Content-Length"))
			contentLength = Integer.valueOf(params.get("Content-Length"));
		
		if(contentLength > 0){
			char[] buffer = new char[contentLength];
			in.read(buffer);
			session.setBody(new String(buffer));
		}
		
		return session;
	}
	
	private static void parseRequestLine(HttpSession session, String line){
		String[] tokens = line.split("\\s+");
		
		if(tokens.length > 0){
			session.setMethod(HttpMethod.toHttpMethod(tokens[0]));
			session.setUri(tokens[1]);
		}
	}
	
	private static void parseHeader(HttpSession session, String line){
		String[] tokens = line.replaceAll("\\s+", "").split(":", 2);
		
		if(tokens.length > 0){
			session.addParam(tokens[0], tokens[1]);
		}
	}
	
}
