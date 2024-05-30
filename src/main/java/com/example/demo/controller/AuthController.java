package com.example.demo.controller;

import static org.springframework.http.HttpStatus.OK;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import com.example.demo.admin.dto.RegisterActorDto;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LogoutRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.SubjectDto;
import com.example.demo.dto.TokenDto;
import com.example.demo.response.APIResponse;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> login(HttpServletResponse httpServletResponse, HttpServletRequest request,
			@Validated @RequestBody LoginRequest loginRequest) {
		return APIResponse.response(authService.login(loginRequest, httpServletResponse, request));
	}
	@RequestMapping(value = "login_", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> login_(HttpServletResponse httpServletResponse, HttpServletRequest request,
			@Validated @RequestBody LoginRequest loginRequest) {
		return APIResponse.response(authService.login_(loginRequest, httpServletResponse, request));
	}
	@RequestMapping(value = "login_other_device_browser/{email}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> login_other_device_browser( @PathVariable("email") String email) {
		Boolean result = authService.login_other_device_browser(email);
		System.out.println("result="+result);
		if (result != null)
			if (result == true)
				return APIResponse.response(true);
		return APIResponse.response(false);
		
	}
	@RequestMapping(value = "email/{email}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> check_email(HttpServletResponse httpServletResponse,
			@PathVariable("email") String email) {
		System.out.println("email="+email);
		Boolean result = authService.check_email(email);
		System.out.println("result="+result);
		if (result != null)
			if (result == true)
				return APIResponse.response(true);
		return APIResponse.response(false);
		
	}
	@RequestMapping(value = "signup", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> signup(
			@Validated @RequestBody RegisterActorDto registerActorDto) {
	
				Boolean result=	authService.signup(registerActorDto);
				if (result != null)
					if (result == true)
						return APIResponse.response(true);
				return APIResponse.response(false);
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> logout(HttpServletResponse response, HttpServletRequest request, 
			@Validated @RequestBody LogoutRequest logoutRequest) {
		return APIResponse.response(authService.logout(response, request, logoutRequest));
	}
	
	@RequestMapping(value = "logout-all", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> logoutAll(HttpServletResponse response, HttpServletRequest request, 
			@Validated @RequestBody LogoutRequest logoutRequest) {
		return APIResponse.response(authService.logoutAll(response, request, logoutRequest));
	}

	@RequestMapping(value = "clear-cookies", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> clearCookies(HttpServletResponse response, HttpServletRequest request) {
		return APIResponse.response(authService.clearCookies(response, request));
	}

	@RequestMapping(value = "check_username/{username}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> check_username(HttpServletResponse httpServletResponse,
			@PathVariable("username") String username) {
		Boolean result = authService.check_username(username);
		if (result != null)
			if (result == true)
				return APIResponse.response(true);
		return APIResponse.response(false);
	}
	
	@RequestMapping(value = "forgot_password/{email}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> forgot_password(HttpServletRequest request,
			@PathVariable("email") String email) {
		return APIResponse.response(authService.forgot_password(email, request));
	}

	
	@RequestMapping(value = "refresh-token", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("/refresh-token");
		return APIResponse.response(authService.refreshToken(request, response));
	}
	
	@RequestMapping(value = "token-not-expired", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> checkAccessTokenDoesNotExpired( HttpServletRequest request) {
		return APIResponse.response(authService.checkAccessTokenDoesNotExpired( request));
	}

	@RequestMapping(value = "access-token", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> accessToken(HttpServletResponse httpServletResponse, @RequestBody TokenDto dto) {
		return APIResponse.response(authService.accessToken(dto));
	}

	@RequestMapping(value = "get_login_trial/{email}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> get_login_trial(HttpServletResponse httpServletResponse, @PathVariable("email") String email) {
		return APIResponse.response(authService.get_login_trial(email));
	}
	
	private String getPath(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, "path");
		return cookie != null ? cookie.getValue() : null;
	}
}
