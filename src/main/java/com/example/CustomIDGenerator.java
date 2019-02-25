package com.example;


import java.util.UUID;


public final class CustomIDGenerator {
	

	
	public static String  getId(){
		String id  = UUID.randomUUID().toString();
		return id;
	}
}
