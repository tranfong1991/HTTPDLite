package com.andytran.httpdlite.core;

import java.io.BufferedReader;
import java.io.IOException;

import com.andytran.httpdlite.domain.HttpSession;

public class HttpParser {

	public static HttpSession parse(BufferedReader in) throws IOException{
		HttpSession session = new HttpSession();
		
		String line;
		while((line = in.readLine()) != null && !line.isEmpty()){
			System.out.println(line);
		}
		
		return session;
	}
	
}
