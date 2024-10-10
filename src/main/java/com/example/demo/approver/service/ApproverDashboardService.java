package com.example.demo.approver.service;

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
import com.example.demo.approver.mapper.ApproverDashboardMapper;
import com.example.demo.user.model.AtsBeginningEnding;
import com.example.demo.user.model.BeginningEnding;
import com.example.demo.utils.Utils;

@Service
public class ApproverDashboardService {

	@Autowired
	private ApproverDashboardMapper mapperDashboard;

	@Autowired
	private Utils util;

	public Map<String, Object> getDashboardData(HttpServletRequest request) {

		try {
			if (util.isPermitted(request, "Approver", "get_dashboard_data")) {
				
				System.out.println(mapperDashboard.getNumberOfCurrencyRemarks(util.get_user_id(request)));
				System.out.println(mapperDashboard.getNumberOfAccountRemarks(util.get_user_id(request)));
				System.out.println("id: " + util.get_user_id(request));

				Map<String, Object> response = new HashMap<>();
				response.put("total_number_of_accounts", mapperDashboard.getTotalNumberOfAccounts());
				response.put("total_number_of_currencies", mapperDashboard.getTotalNumberOfCurrencies());
				response.put("number_of_currency_approved", mapperDashboard.getNumberOfApprovedCurrency());
				response.put("number_of_currency_pending", mapperDashboard.getNumberOfPendingCurrency());
				response.put("number_of_currency_rejected", mapperDashboard.getNumberOfRejectedCurrency());
				response.put("number_of_account_approved", mapperDashboard.getNumberOfApprovedAccount());
				response.put("number_of_account_pending", mapperDashboard.getNumberOfPendingAccount());
				response.put("number_of_account_rejected", mapperDashboard.getNumberOfRejectedAccount());
				response.put("number_of_Currency_remark",
						mapperDashboard.getNumberOfCurrencyRemarks(util.get_user_id(request)));
				response.put("number_of_account_remark",
						mapperDashboard.getNumberOfAccountRemarks(util.get_user_id(request)));
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
