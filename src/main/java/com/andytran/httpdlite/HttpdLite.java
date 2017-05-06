package com.andytran.httpdlite;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.andytran.httpdlite.core.HttpParser;
import com.andytran.httpdlite.domain.HttpResponse;
import com.andytran.httpdlite.domain.HttpSession;

public abstract class HttpdLite {
	
	private int port;
	private ServerSocket serverSocket; 
	
	public HttpdLite(int port) throws IOException{
		this.port = port;
		this.serverSocket = new ServerSocket(port);
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public abstract HttpResponse handle(HttpSession session);
	
	public void start() throws IOException{
		System.out.println("Server started.");
		while(true){
			Socket newConnection = this.serverSocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(newConnection.getInputStream()));
			HttpSession session = HttpParser.parse(in);
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					HttpResponse response = handle(session);
										
					try {
						BufferedWriter out = new BufferedWriter(new OutputStreamWriter(newConnection.getOutputStream()));

						out.write(response.toString());
						out.flush();
						out.close();
						
						newConnection.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	public void stop() throws IOException{
		this.serverSocket.close();
	}
	
}
