package com.example.demo.admin.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ExceptionsList;
import com.example.demo.admin.mapper.AdminDashboardMapper;
import com.example.demo.user.model.AtsBeginningEnding;
import com.example.demo.user.model.BeginningEnding;
import com.example.demo.utils.Utils;

@Service
public class AdminDashboardService {

	@Autowired
	private AdminDashboardMapper mapperDashboard;

	@Autowired
	private Utils util;
	public Map<String, Object> getDashboardData(HttpServletRequest request) {

		try {
			if (util.isPermitted(request, "admin", "get_dashboard_data") ) {
				Map<String, Object> response = new HashMap<>();
				response.put("total_number_of_Users", mapperDashboard.getTotalNumberOfUsers());
				response.put("total_number_active_users", mapperDashboard.getTotalNumberOfActiveUsers());
				response.put("total_number_inactive_users", mapperDashboard.getTotalNumberOfInActiveUsers());
				response.put("total_number_of_admin_functionalities", mapperDashboard.getTotalNumberOfAdminFunctionalities());
				response.put("total_number_of_user_functionalities", mapperDashboard.getTotalNumberOfUserFunctionalities());
				response.put("total_number_of_approver_functionalities", mapperDashboard.getTotalNumberOfApproverFunctionalities());
				response.put("total_number_of_functionalities", mapperDashboard.getTotalNumberOfFunctionalities());
				response.put("total_logins", mapperDashboard.getTotalLogins());
				response.put("total_number_of_user_activity", mapperDashboard.getTotalUserActivity());
				System.out.println("response: " + response);
				return response;
			} else {
				return null;
			}

		} catch (Exception e) {
			throw new ExceptionsList(e);
		}

	}

}
