package com.andytran.httpdlite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.andytran.httpdlite.core.HttpParser;
import com.andytran.httpdlite.domain.HttpSession;

public abstract class HttpdLite {
	
	private int port;
	private ServerSocket serverSocket; 
	
	public HttpdLite(int port) throws IOException{
		this.setPort(port);
		this.serverSocket = new ServerSocket(port);
	}
	
	public abstract void handle(HttpSession session);
	
	public void start() throws IOException{
//		while(true){
			System.out.println("Waiting for connection");
			Socket newConnection = this.serverSocket.accept();
			
			System.out.println("Connected to " + newConnection.getRemoteSocketAddress());
			BufferedReader in = new BufferedReader(new InputStreamReader(newConnection.getInputStream()));
			
			HttpSession session = HttpParser.parse(in);
			handle(session);
			
			newConnection.close();
//		}
	}
	
	public void stop() throws IOException{
		this.serverSocket.close();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
