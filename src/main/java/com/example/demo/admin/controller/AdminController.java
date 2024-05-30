package com.example.demo.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.admin.dto.RegisterActorDto;
import com.example.demo.admin.model.ChangePassword;
import com.example.demo.admin.model.Users;
import com.example.demo.admin.service.AdminService;
import com.example.demo.model.User;
import com.example.demo.response.APIResponse;
import com.example.demo.user.model.Currency;

@RestController
@RequestMapping("/api/admin/")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "reset_password_by_admin/{user_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> reset_password_by_admin(HttpServletRequest request, @PathVariable("user_id") String user_id) {
		return APIResponse.response(adminService.reset_password_by_admin(user_id, request));
	}

	@RequestMapping(value = "reset_password", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> reset_password(HttpServletRequest request,
			@Validated @RequestBody ChangePassword changePassword) {
		Boolean result = adminService.resetPassword(changePassword, request);
		if (result != null)
			if (result == true)
				return APIResponse.response(true);
		return APIResponse.response(false);
	}

	@RequestMapping(value = "change_password", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> change_password(HttpServletRequest request,
			@Validated @RequestBody ChangePassword changePassword) {
		Boolean result = adminService.changePassword(changePassword, request);
		if (result != null)
			if (result == true)
				return APIResponse.response(true);
		return APIResponse.response(false);
	}
	@RequestMapping(value = "get_user_name", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_user_name(HttpServletRequest request) {
		return APIResponse.response(adminService.get_user_name(request));
	}

	@RequestMapping(value = "deactivate_user/{user_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> deactivate_user(@PathVariable("user_id") String user_id,
			HttpServletRequest request) {
		return APIResponse.response(adminService.deactivate_user(user_id, request));
	}

	@RequestMapping(value = "activate_user/{user_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> activate_user(@PathVariable("user_id") String user_id, HttpServletRequest request) {
		return APIResponse.response(adminService.activate_user(user_id, request));
	}

	@RequestMapping(value = "delete_user/{user_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> delete_user(HttpServletRequest request, @PathVariable("user_id") String user_id) {
		Boolean result = adminService.deleteUser(user_id, request);
		if (result != null)
			if (result == true)
				return APIResponse.response(true);
		return APIResponse.response(false);
	}
	@RequestMapping(value = "reject_pending_user/{user_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> reject_pending_user(HttpServletRequest request, @PathVariable("user_id") String user_id) {
		Boolean result = adminService.reject_pending_user(user_id, request);
		if (result != null)
			if (result == true)
				return APIResponse.response(true);
		return APIResponse.response(false);
	}
	@RequestMapping(value = "approve_pending_user/{user_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> approved_pending_user(HttpServletRequest request, @PathVariable("user_id") String user_id) {
		Boolean result = adminService.approved_pending_user(user_id, request);
		if (result != null)
			if (result == true)
				return APIResponse.response(true);
		return APIResponse.response(false);
	}
//
//	@RequestMapping(value = "delete_user/{user_id}", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<Object> delete_userr(HttpServletRequest request, @PathVariable("user_id") String user_id) {
//		return APIResponse.response(adminService.deleteUser(user_id, request));
//	}

	@RequestMapping(value = "get_user/{user_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_user(HttpServletRequest request, @PathVariable("user_id") String user_id) {
		return APIResponse.response(adminService.getUser(user_id, request));
	}
	@RequestMapping(value = "get_pending_user/{user_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_pending_userById(HttpServletRequest request, @PathVariable("user_id") String user_id) {
		return APIResponse.response(adminService.get_pending_userById(user_id, request));
	}
	@RequestMapping(value = "get_all_user_activities/{act_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity <Object> getAllUserActivities(HttpServletRequest request, @PathVariable("act_date") String act_date) {
		System.out.println("this is act dateeeeeeee"+ act_date);
		return APIResponse.response(adminService.getAllUserActivities(request, act_date));
	}
//	@RequestMapping(value = "get_all_user_activities", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<Object> getAllUserActivities(HttpServletRequest request) {
//		return APIResponse.response(adminService.getAllUserActivities(request));
//	}

	@RequestMapping(value = "get_all_users", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllUserUsers(HttpServletRequest request) {
		return APIResponse.response(adminService.getAllUserUsers(request));
	}
	@RequestMapping(value = "get_pending_users", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getPendingUsers(HttpServletRequest request) {
		return APIResponse.response(adminService.getPendingUsers(request));
	}
	
	@RequestMapping(value = "get_approved_rejected_users", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_approved_rejected_users(HttpServletRequest request) {
		return APIResponse.response(adminService.get_approved_rejected_users(request));
	}

	@RequestMapping(value = "update_functionality_status_no_role", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Boolean> update_functionality_status_no_role(HttpServletRequest request , @Validated @RequestBody String functionality_status_string) {
		return APIResponse
				.response(adminService.updateFunctionalityStatus_no_role(functionality_status_string, request));
	}

	@RequestMapping(value = "get_functionality_no_role", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_functionality_no_role(HttpServletRequest request) {
		return APIResponse.response(adminService.getFunctionality_no_role(request));
	}

	@RequestMapping(value = "update_functionality_status/{role_id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Boolean> update_functionality_status(HttpServletRequest request,
			@PathVariable("role_id") String role_id, @Validated @RequestBody String functionality_status_string) {
		return APIResponse
				.response(adminService.updateFunctionalityStatus(role_id, functionality_status_string, request));
	}

	@RequestMapping(value = "get_functionality/{role_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_functionality(HttpServletRequest request,
			@PathVariable("role_id") String role_id) {
		return APIResponse.response(adminService.getFunctionality(role_id, request));
	}

	@RequestMapping(value = "get_all_logs/{act_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllLogs(HttpServletRequest request,@PathVariable("act_date") String act_date) {
		return APIResponse.response(adminService.getAllLogs(request,act_date));
	}

	@RequestMapping(value = "get_all_roles", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllRoles(HttpServletRequest request) {
		return APIResponse.response(adminService.getAllRoles(request));
	}

	@RequestMapping(value = "check_email/{email}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> check_email(HttpServletRequest request, @PathVariable("email") String email) {
		Boolean result = adminService.check_email(email, request);
		if (result != null)
			if (result == true)
				return APIResponse.response(true);
		return APIResponse.response(false);
	}

	@RequestMapping(value = "register_user", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> register_user(HttpServletRequest request,
			@Validated @RequestBody RegisterActorDto registerActorDto) {
		if (registerActorDto.getId() != null)
			return APIResponse.response(adminService.update_user(registerActorDto, request));
		else
			return APIResponse.response(adminService.register_user(registerActorDto, request));
	}

	@RequestMapping(value = "deactivate_role/{role_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> deactivate_role(@PathVariable("role_id") String role_id,
			HttpServletRequest request) {
		return APIResponse.response(adminService.deactivate_role(role_id, request));
	}

	@RequestMapping(value = "activate_role/{role_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> activate_role(@PathVariable("role_id") String role_id, HttpServletRequest request) {
		return APIResponse.response(adminService.activate_role(role_id, request));
	}
	
	@RequestMapping(value = "/send-request", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> register_account(HttpServletRequest request,
			@Validated @RequestBody Currency role) {

		return APIResponse.response(adminService.register_role(role, request));
	}

//	@RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<APIResponse> login(HttpServletResponse httpServletResponse,
//			@Validated @RequestBody LoginRequest loginRequest) {
//		return APIResponse.responseMap(authService.login(loginRequest, httpServletResponse), OK);
//	}
//
//	@RequestMapping(value = "signup", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<APIResponse> signup(HttpServletResponse httpServletResponse,
//			@Validated @RequestBody RegisterRequest registerRequest) {
//		return APIResponse.responseMap(authService.signup(registerRequest, httpServletResponse), OK);
//	}
//
//	@RequestMapping(value = "logout", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<?> logout(HttpServletResponse response, HttpServletRequest request) {
//		authService.logout(response, request);
//		return APIResponse.success("success");
//	}
//
//	@RequestMapping(value = "clear-cookies", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<?> clearCookies(HttpServletResponse response, HttpServletRequest request) {
//		return APIResponse.response(authService.clearCookies(response, request));
//	}
//	
//	@RequestMapping(value = "logout-all", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<?> logoutAll(HttpServletResponse response, HttpServletRequest request) {
//		authService.logoutAll(response, request);
//		return APIResponse.success("success");
//	}
//
//	@RequestMapping(value = "register_subject", method = RequestMethod.POST, produces = "application/json")
//	public Long registerSubject(HttpServletResponse httpServletResponse, @RequestBody SubjectDto subject_dto) {
//		Long subject_id = authService.registerSubject(subject_dto);
//		return subject_id;
//	}
//
//	@RequestMapping(value = "check_username/{username}", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<Boolean> check_username(HttpServletResponse httpServletResponse,
//			@PathVariable("username") String username) {
//		Boolean result = authService.check_username(username);
//		if (result != null)
//			if (result == true)
//				return APIResponse.response(true);
//		return APIResponse.response(false);
//	}
//
//	@RequestMapping(value = "check_subject/{subject_name}", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<Boolean> check_subject(HttpServletResponse httpServletResponse,
//			@PathVariable("subject_name") String subject_name) {
//		Boolean result = authService.check_subject(subject_name);
//		if (result != null)
//			if (result == true)
//				return APIResponse.response(true);
//		return APIResponse.response(false);
//	}
//
//	@RequestMapping(value = "refresh-token", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		System.out.println("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
//		return APIResponse.response(authService.refreshToken(request, response));
//	}
//
//	@RequestMapping(value = "access-token", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<?> accessToken(HttpServletResponse httpServletResponse, @RequestBody TokenDto dto) {
//		return APIResponse.response(authService.accessToken(dto));
//	}
//	
//	private String getPath(HttpServletRequest request) {
//		Cookie cookie = WebUtils.getCookie(request, "path");
//		return cookie != null ? cookie.getValue() : null;
//	}
}
