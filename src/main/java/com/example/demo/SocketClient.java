package com.example.demo;

import java.io.*;
import java.net.*;
import java.util.*;

public class SocketClient {
	
	// 소켓을 만들기 위한 인스턴스들
	private Socket socket=null;
	
	// 소켓에서 스트림을 얻어오기 위한 인스턴스들
	private OutputStream os = null;
	private OutputStreamWriter writer = null;		
	

	// 생성자. 주어진 IP와 포트번호로 소켓을 생성한다.
	public SocketClient(String ip, int port) throws IOException {

		try {		
			// 서버 소켓을 생성하고, 클라이언트에서 스트림을 받아드릴 소켓을 하나 더 생성합니다.
			socket=new Socket(ip, port);
			System.out.println("소켓을 생성하여 서버와 연결하였습니다.");			
		} catch (IOException e) { 
			throw e;
		}
	}
	
	public void startSocket() {

//		String str="abcdefghij";
		String str="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<book>\r\n" + 
				"    <code id=\"1\">\r\n" + 
				"        <name>사람은 무엇으로 사는가?</name>\r\n" + 
				"        <writer>톨스토이</writer>\r\n" + 
				"        <price>100</price>\r\n" + 
				"    </code>\r\n" + 
				"    <code id=\"2\" type=\"novel\">\r\n" + 
				"        <name>홍길동 전</name>\r\n" + 
				"        <writer>허균</writer>\r\n" + 
				"        <price>300</price>\r\n" + 
				"    </code>\r\n" + 
				"</book>";
		
		try {		
			
			// 소켓에 스트림을 연결하고, 스트림을 보낼 Writer를 만듭니다.
			os = socket.getOutputStream();
			
			writer = new OutputStreamWriter(os);

		} catch (IOException e) {
			System.out.println("소켓 연결에 실패했습니다.");
		}
		
		try {
			writer.write(str,0,10);
			writer.flush();
		} catch (IOException e) { 
			System.out.println("데이터 전송에 실패했습니다.");
		}
		
		System.out.println("보낸 데이터:"+str);
		
		try {
			socket.close();
			writer.close();			
			os.close();
		} catch (IOException e) {
			System.out.println("소켓 닫기에 실패했습니다");
		}

	}	
	
	public static void main(String args[]) {
		
		SocketClient c;
		String ip="127.0.0.1";
		int port=8080;
		
		if (args.length>1) {
			ip=args[1];
			port=Integer.parseInt(args[0]);
		} else if (args.length==1) {
			port=Integer.parseInt(args[0]);
		}
		
		try {
			c=new SocketClient(ip, port);
			c.startSocket();
		} catch (IOException e) {
			System.out.println("소켓 생성에 실패했습니다.");
		}

	}
	
}
