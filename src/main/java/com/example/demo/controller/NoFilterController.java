package com.example.demo.controller;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.ExceptionsList;
import com.example.demo.response.APIResponse;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api/nofilter/")
public class NoFilterController {

	@Autowired
	private AuthService authService;
	
	@RequestMapping(value = "access-denied", method = RequestMethod.GET)
	public void accessDenied() {
		throw new ExceptionsList(new AccessDeniedException("Opps. resource is forbidden!"));
	}
	
	@RequestMapping(value = "token-expired", produces = "application/json")
	public ResponseEntity<?> tokenExpired() {
		return APIResponse.response("access-token-expired");
	}
	
	@RequestMapping(value = "auth-entry-point", method = RequestMethod.GET)
	public void authenticationEntryPoint(HttpServletRequest httpServletRequest) {
		System.out.println("from auth-entry-point controller.");
		throw new ExceptionsList(new AuthenticationException("Opps. you are not authorized!!"));
	}
	
	@RequestMapping(value = "request-rejected", method = RequestMethod.GET)
	public void LogAndSuppressRequestRejectedExceptionFilter() {
		throw new ExceptionsList(new RequestRejectedException("Opps. your request is rejected!"));
	}
	
//	===========================================================================
	
}
