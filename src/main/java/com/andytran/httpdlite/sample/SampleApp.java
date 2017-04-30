package com.andytran.httpdlite.sample;

import java.io.IOException;

import com.andytran.httpdlite.HttpdLite;
import com.andytran.httpdlite.domain.HttpSession;

public class SampleApp {

	static class MyHttpdLite extends HttpdLite{

		public MyHttpdLite(int port) throws IOException{
			super(port);
		}

		@Override
		public void handle(HttpSession session) {
			System.out.println("HERE");
		}
		
	}
	
	public static void main(String[] args) {
		try {
			HttpdLite server = new MyHttpdLite(1003);
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
