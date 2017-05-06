package com.andytran.httpdlite.sample;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.andytran.httpdlite.HttpdLite;
import com.andytran.httpdlite.domain.HttpResponse;
import com.andytran.httpdlite.domain.HttpSession;
import com.andytran.httpdlite.domain.HttpStatus;

public class SampleApp {

	static class MyHttpdLite extends HttpdLite{

		public MyHttpdLite(int port) throws IOException{
			super(port);
		}

		@Override
		public HttpResponse handle(HttpSession session) {
			JSONObject json = new JSONObject();
			try {
				json.put("status", "OK");
				
				JSONArray arr = new JSONArray();
				arr.put(200);
				arr.put(false);
				
				json.put("myArray", arr);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			HttpResponse response = new HttpResponse.Builder()
					.statusCode(HttpStatus.OK)
					.httpVersion("1.0")
					.param("Accept", "application/json")
					.param("Server", "HttpdLite1.0")
					.body(json)
					.build();
						
			return response;
		}
		
	}
	
	public static void main(String[] args) {
		try {
			HttpdLite server = new MyHttpdLite(8080);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
