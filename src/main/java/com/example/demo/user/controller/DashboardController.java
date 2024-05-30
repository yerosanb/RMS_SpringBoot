package com.example.demo.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.admin.service.AdminDashboardService;
import com.example.demo.approver.service.ApproverDashboardService;
import com.example.demo.response.APIResponse;
import com.example.demo.user.service.DashboardService;

@RestController
@RequestMapping("api/dashboard/")
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;
	
	@Autowired
	private AdminDashboardService adminDashboardService;
	
	@Autowired
	private ApproverDashboardService approverDashboardService;

	@RequestMapping(value = "getData", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getDashboardData(HttpServletRequest request) {
		return APIResponse.response(dashboardService.getDashboardData(request));
	}
	
	@RequestMapping(value = "getAdminData", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAdminDashboardData(HttpServletRequest request) {
		return APIResponse.response(adminDashboardService.getDashboardData(request));
	}
	
	@RequestMapping(value = "getApproverData", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getApproverDashboardData(HttpServletRequest request) {
		return APIResponse.response(approverDashboardService.getDashboardData(request));
	}
}
