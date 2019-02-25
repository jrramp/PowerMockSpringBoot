package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloWorldRest {

	@Autowired
	UserDetail userDetail;

	@RequestMapping(path = "/hello")
	String greetings() {

		String greeting = "Hello World user " + userDetail.getUserDetail();

		return greeting;
	}
}
