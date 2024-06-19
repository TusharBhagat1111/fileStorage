package com.javatechie.util;

public class TusharException extends Exception {
	
//	String resourcename;
//	String fieldName;
	
	public TusharException(String resourcename, String fieldName) {
		super(String.format("%s image not found in database or file with name %s",resourcename,fieldName));
//		this.resourcename = resourcename;
//		this.fieldName = fieldName;
	
	}

	public TusharException(Integer id, Integer id2) {
		super(String.format("%s is id number of image not found in database or file with this id number %s",id,id2));
//		this.resourcename = resourcename;
//		this.fieldName = fieldName;
	}

//	public TusharException(String msg) {
//		super(msg);
//	}

}
