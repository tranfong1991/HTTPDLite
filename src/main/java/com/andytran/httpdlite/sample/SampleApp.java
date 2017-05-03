package com.andytran.httpdlite.sample;

import java.io.IOException;

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
			HttpResponse response = new HttpResponse.Builder()
					.statusCode(HttpStatus.OK)
					.httpVersion("1.0")
					.body("Hello There")
					.param("Accept", "application/json")
					.param("Server", "HttpdLite")
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
