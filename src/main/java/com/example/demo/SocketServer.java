package com.example.demo;

import java.io.*;
import java.net.*;
import java.util.*;

public class SocketServer {
	
	// 소켓을 만들기 위한 인스턴스들
	private ServerSocket serverSocket=null;
	private Socket socket=null;
	
	// 소켓에서 스트림을 얻어오기 위한 인스턴스들
	private InputStream is = null;
	private InputStreamReader reader = null;		
	
	public SocketServer(int port) throws IOException {
		try {		
			// 서버 소켓을 생성하고, 클라이언트에서 스트림을 받아드릴 소켓을 하나 더 생성합니다.
			serverSocket=new ServerSocket(port);
			System.out.println("serverSocket:::"+serverSocket);
			System.out.println("소켓을 생성하여 클라이언트의 연결을 기다립니다. 포트번호="+port);
			socket=serverSocket.accept();
			
			System.out.println("클라이언트와 연결되었습니다.");
		} catch (IOException e) { 
			throw e;
		}
	}
	
	public void startSocket() {
		System.out.println("444444444444444");
		String str="";
		System.out.println("55555555555555");
		try {		
			
			// 소켓에 스트림을 연결하고, 스트림을 읽어들일 reader를 만듭니다.
			is = socket.getInputStream();
			reader = new InputStreamReader(is);
		} catch (IOException e) { System.out.println("소켓 연결에 실패했습니다."); }
		
		for (int i=0; i<10; i++) {
			try {
				str=str + (char) reader.read();
			} catch (IOException e) {
				System.out.println("데이터를 받는 데에 실패했습니다.");
			}
		}
		
		System.out.println("받은 데이터:"+str);
		
		try {
			serverSocket.close();
			socket.close();
			reader.close();
			is.close();
		} catch (IOException e) { 
			System.out.println("소켓을 닫는데에 실패했습니다.");
		}

	}	
	
	public static void main(String args[]) {
		System.out.println("1111111111111111");
		SocketServer s;
		int port=8080;
		
		if (args.length>0) {
			//string ->int 변환
			port=Integer.parseInt(args[0]);
		}
		System.out.println("22222222222222 ::"+args.length);
		System.out.println("333333333333333");
		try {
			s=new SocketServer(port);
			System.out.println("54353535"+s);
			s.startSocket();
		} catch (IOException e) {
			System.out.println("소켓 생성에 실패했습니다.");
		}

	}
	
}