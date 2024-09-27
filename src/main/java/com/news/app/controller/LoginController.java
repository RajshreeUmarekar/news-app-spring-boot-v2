package com.news.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



@RestController
@CrossOrigin("http://localhost:3000")
public class LoginController {
	
	@Value("${spring.security.user.name}")
	private String security_username;
	
	@Value("${spring.security.user.password}")
	private String security_password;
	
	ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
	
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password) {
		String output = "";
		if(username.equals(security_username) && password.equals(security_password)) {
			output = "Login Success";
		}else {
			output = "Login Failure";
		}
		ResponseEntity<String> response = new ResponseEntity<>(output, HttpStatus.OK);
		return response;
		
	}

}
