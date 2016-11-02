package com.cs.test.week10.reactor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Client {
    String hostIp;
    int hostPort;
 
    public Client(String hostIp, int hostPort) {
        this.hostIp = hostIp;
        this.hostPort = hostPort;
    }
 
    private static int getRandom(int count) {
	    return (int) Math.round(Math.random() * (count));
	}
	private static String string = "abcdefghijklmnopqrstuvwxyz";   
	private static String getRandomString(int length){
	    StringBuffer sb = new StringBuffer();
	    int len = string.length();
	    for (int i = 0; i < length; i++) {
	        sb.append(string.charAt(getRandom(len-1)));
	    }
	    return sb.toString();
	}
    
    public void runClient() throws IOException {
        Socket clientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
 
        try {
            clientSocket = new Socket(hostIp, 9000);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unknown host: " + hostIp);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't connect to: " + hostIp);
            System.exit(1);
        }
        
        System.out.println("Client connected to host : " + hostIp + " port: " + hostPort);
        System.out.println("Tell what  to the Server.....");
 
        while (true) {
        	String  userInput = getRandomString(new Random().nextInt(70000));
        	System.out.println(userInput.length());
        	out.println(userInput);
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
    
    public static void main(String[] args) throws IOException {
		Client client = new Client("127.0.0.1", 9900);
		client.runClient();
	}
}