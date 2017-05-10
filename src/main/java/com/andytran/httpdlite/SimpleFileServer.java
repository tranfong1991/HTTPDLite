package com.andytran.httpdlite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.andytran.httpdlite.domain.HttpResponse;
import com.andytran.httpdlite.domain.HttpSession;

public class SimpleFileServer extends HttpdLite {
	
	private final int MAX_BUF_SIZE = 2;
	
	private String rootDir;

	public SimpleFileServer(String rootDir) throws IOException {
		super(8080);
		this.rootDir = rootDir;
	}

	public String getRootDir() {
		return rootDir;
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	@Override
	public HttpResponse handle(HttpSession session) {
		String uri = session.getUri();
		BufferedReader reader = null;
		HttpResponse response = null;
		
		try {
			File file = new File(this.rootDir + uri);
			reader = new BufferedReader(new FileReader(file));
			
			char[] buf = new char[MAX_BUF_SIZE];
			int offset = 0;
			
			while(reader.ready()){
				if(offset >= buf.length - 1)
					buf = resize(buf);
				
				int bytes = reader.read(buf, offset, MAX_BUF_SIZE);
				if(bytes < 0)
					break;
				offset += bytes;
			}
			
			response = new HttpResponse.Builder()
						.body(new String(buf))
						.build();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return response;
	}

	private char[] resize(char[] buf){
		char[] old = buf;
		buf = new char[old.length * 2];
		
		for(int i = 0; i < old.length; i++){
			buf[i] = old[i];
		}
		return buf;
	}
	
}
