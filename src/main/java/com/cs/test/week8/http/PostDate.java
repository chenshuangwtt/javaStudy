package com.cs.test.week8.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class PostDate {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1",8080);
		
		String requestLine="POST /login HTTP/1.1\r\n";
        String host="Host: localhost:8080\r\n";
        String contentType="Content-Type: application/x-www-form-urlencoded\r\n";
        String body = "name=hello&pwd=world";
        String contentLength="Content-Length: "+body.length()+"\r\n";
        
        OutputStream os = socket.getOutputStream();
        os.write(requestLine.getBytes());
        os.write(host.getBytes());
        os.write(contentType.getBytes());
        os.write(contentLength.getBytes());
        os.write("\r\n".getBytes());
        os.write(body.getBytes());
        os.flush();
       
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String tmp = null;
        while((tmp=reader.readLine())!=null) {
            System.out.println(tmp);
        }
        socket.close();
	}
}