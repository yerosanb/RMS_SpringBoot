package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.APIResponse;

@RestController
public class UtilController {

	@RequestMapping(value = "/test1", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> test1(@CookieValue(name = "user-id", defaultValue = "default-user-id") String userId) {
		System.out.println("This response is from /test1.: " + userId);
		return APIResponse.response(true);
	}
}
