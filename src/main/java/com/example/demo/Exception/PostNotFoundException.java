package com.example.demo.Exception;

public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException(String message) {
		super(message);
	}
}
