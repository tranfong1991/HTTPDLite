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
//		while(true){
			System.out.println("Waiting for connection");
			Socket newConnection = this.serverSocket.accept();
			
			System.out.println("Connected to " + newConnection.getRemoteSocketAddress());
			BufferedReader in = new BufferedReader(new InputStreamReader(newConnection.getInputStream()));
			
			HttpSession session = HttpParser.parse(in);
			HttpResponse response = handle(session);
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(newConnection.getOutputStream()));
			
			System.out.println(response.toString());
			
			out.write(response.toString());
			
			out.flush();
			out.close();
			
			newConnection.close();
//		}
	}
	
	public void stop() throws IOException{
		this.serverSocket.close();
	}
	
}
