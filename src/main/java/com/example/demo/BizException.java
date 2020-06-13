package com.example.demo;

public class BizException extends RuntimeException {
	
	//생성자(문자)
	public BizException(String msg) {
		super(msg);
	}
	
	//그냥 Exception
	public BizException(Exception e) {
		super(e);
	}

}
