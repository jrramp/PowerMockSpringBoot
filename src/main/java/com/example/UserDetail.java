package com.example;

import org.springframework.stereotype.Component;
import com.example.CustomIDGenerator;

@Component
public class UserDetail {
	
	public String getUserDetail(){
		String uniqueID = CustomIDGenerator.getId();
		return  uniqueID ;
	}

}
