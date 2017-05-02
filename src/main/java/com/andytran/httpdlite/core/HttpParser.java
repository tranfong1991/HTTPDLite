package com.andytran.httpdlite.core;

import java.io.BufferedReader;
import java.io.IOException;

import com.andytran.httpdlite.domain.HttpMethod;
import com.andytran.httpdlite.domain.HttpSession;

public class HttpParser {

	public static HttpSession parse(BufferedReader in) throws IOException{
		HttpSession session = new HttpSession();
		
//		parseRequestLine(session, in.readLine());
//		
//		String line;
//		while(in.ready()){
//			if((line = in.readLine()) != null 
//					&& !line.isEmpty()){
//				parseHeader(session, line);
//			}
//		}
//		
//		while(in.ready()){
//			if((line = in.readLine()) != null)
//				parseBody(session, line);
//		}
		
		char[] buffer = new char[1024];
		while(in.ready()){
			in.read(buffer, 0, 1024);
		}
		System.out.println(buffer);
		
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
	
	private static void parseBody(HttpSession session, String line){
		session.setBody(line);
	}
	
}
