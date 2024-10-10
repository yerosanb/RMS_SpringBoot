package com.example.demo.user.service;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.example.demo.user.mapper.MapperDashboard;
import com.example.demo.user.model.AtsBeginningEnding;
import com.example.demo.user.model.BeginningEnding;
import com.example.demo.utils.Utils;

@Service
public class DashboardService {

	@Autowired
	private MapperDashboard mapperDashboard;

	@Autowired
	private Utils util;

	public Map<String, Object> getDashboardData(HttpServletRequest request) {

		try {
			if (util.isPermitted(request, "User", "get_dashboard_data")) {
				Map<String, Object> response = new HashMap<>();
				response.put("total_number_of_accounts", mapperDashboard.getTotalNumberOfAccounts());
				response.put("total_number"
						+ "_of_currencies", mapperDashboard.getTotalNumberOfCurrencies());
				response.put("total_number_of_files", mapperDashboard.getTotalNumberOfFiles());
				response.put("total_number_of_transactions_ats", mapperDashboard.getTotalNumberOfTransactionsAts());
				response.put("total_number_of_transactions_", mapperDashboard.getTotalNumberOfTransactions());

				response.put("outstanding_transactions_on__credit",
						mapperDashboard.getOutstandingTransactionsOnCredit());
				response.put("outstanding_transactions_on__debit",
						mapperDashboard.getOutstandingTransactionsOnDebit());
				response.put("outstanding_transactions_on_ats_credit",
						mapperDashboard.getOutstandingTransactionsOnAtsCredit());
				response.put("outstanding_transactions_on_ats_debit",
						mapperDashboard.getOutstandingTransactionsOnAtsDebit());

				response.put("beginning_balance_ats", mapperDashboard.getBeginningBalanceAts());
				response.put("beginning_balance__ifb", mapperDashboard.getBeginningBalanceIfb());
				response.put("beginning_balance__conv", mapperDashboard.getBeginningBalanceConv());

				response.put("ending_balance_ats", mapperDashboard.getEndingBalanceAts());
				response.put("ending_balance__ifb", mapperDashboard.getEndingBalanceIfb());
				response.put("ending_balance__conv", mapperDashboard.getEndingBalanceConv());

				response.put("outstanding_balance_on__credit", mapperDashboard.getOutstandingBalanceOnCredit());
				response.put("outstanding_balance_on__debit", mapperDashboard.getOutstandingBalanceOnDebit());
				response.put("outstanding_balance_on_ats_credit", mapperDashboard.getOutstandingBalanceOnAtsCredit());
				response.put("outstanding_balance_on_ats_debit", mapperDashboard.getOutstandingBalanceOnAtsDebit());

				response.put("number_of_edited_transactions", mapperDashboard.getNumberOfEditedTransactions());
				response.put("number_of_deleted_transactions", mapperDashboard.getNumberOfDeletedTransactions());

				response.put("number_of_matched_transactions_ats_rtgs",
						mapperDashboard.getNumberOfMatchedTransactionsAtsRTGS());
				response.put("number_of_matched_transactions_ats_sos",
						mapperDashboard.getNumberOfMatchedTransactionsAtsSOS());
				response.put("number_of_matched_transactions_ats_b2b",
						mapperDashboard.getNumberOfMatchedTransactionsAtsB2B());
				response.put("number_of_matched_transactions_ats_erca",
						mapperDashboard.getNumberOfMatchedTransactionsAtsERCA());
				response.put("number_of_matched_transactions__rtgs",
						mapperDashboard.getNumberOfMatchedTransactionsRTGS());
				response.put("number_of_matched_transactions__sos",
						mapperDashboard.getNumberOfMatchedTransactionsSOS());
				response.put("number_of_matched_transactions__b2b",
						mapperDashboard.getNumberOfMatchedTransactionsB2B());
				response.put("number_of_matched_transactions__erca",
						mapperDashboard.getNumberOfMatchedTransactionsERCA());

				LocalDateTime current_date = LocalDateTime.now();
				String current_date_string = current_date.getYear() + "-"
						+ (String.valueOf(current_date.getMonthValue()).length() == 1
								? "0" + current_date.getMonthValue()
								: current_date.getMonthValue())
						+ "-"
						+ (String.valueOf(current_date.getDayOfMonth()).length() == 1
								? "0" + current_date.getDayOfMonth()
								: current_date.getDayOfMonth());
				String lastDate_ats_string = mapperDashboard.getAtsFileLastDate();
				String lastDate__string = mapperDashboard.getFileLastDate();

				int lastDate_ats; 
				if (lastDate_ats_string == null) {
					lastDate_ats = Integer.parseInt(current_date_string.replace("-", ""));
				} else {
					lastDate_ats = Integer.parseInt(lastDate_ats_string.replace("-", ""));
				}

				int lastDate_;
				if (lastDate__string == null) {
					lastDate_ = Integer.parseInt(current_date_string.replace("-", ""));
				} else {
					lastDate_ = Integer.parseInt(lastDate_ats_string.replace("-", ""));
				}

				Date date_7 = Date
						.from(LocalDate
								.parse(String.valueOf(lastDate_ats >= lastDate_ ? lastDate_ : lastDate_ats),
										DateTimeFormatter.BASIC_ISO_DATE)
								.atStartOfDay(ZoneId.systemDefault()).toInstant());
				String date_7_string = (date_7.getYear() + "-"
						+ (String.valueOf(date_7.getMonth()).length() == 1 ? "0" + date_7.getMonth()
								: date_7.getMonth())
						+ "-"
						+ (String.valueOf(date_7.getDate()).length() == 1 ? "0" + date_7.getDate() : date_7.getDate()));
				Date date_6 = new DateTime(date_7).minusDays(1).toDate();
				Date date_5 = new DateTime(date_6).minusDays(1).toDate();
				Date date_4 = new DateTime(date_5).minusDays(1).toDate();
				Date date_3 = new DateTime(date_4).minusDays(1).toDate();
				Date date_2 = new DateTime(date_3).minusDays(1).toDate();
				Date date_1 = new DateTime(date_2).minusDays(1).toDate();

				response.put("date_1_1", new SimpleDateFormat("MM-dd").format(date_1).toString());
				response.put("date_2_1", new SimpleDateFormat("MM-dd").format(date_2).toString());
				response.put("date_3_1", new SimpleDateFormat("MM-dd").format(date_3).toString());
				response.put("date_4_1", new SimpleDateFormat("MM-dd").format(date_4).toString());
				response.put("date_5_1", new SimpleDateFormat("MM-dd").format(date_5).toString());
				response.put("date_6_1", new SimpleDateFormat("MM-dd").format(date_6).toString());
				response.put("date_7_1", new SimpleDateFormat("MM-dd").format(date_7).toString());
				// ATS
				response.put("date_1_total_upload_ats", mapperDashboard
						.getTotalUploadedTransactionsAts(new SimpleDateFormat("yyyy-MM-dd").format(date_1).toString()));
				response.put("date_1_matched_ats", mapperDashboard
						.getMatchedTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_1).toString()));
				response.put("date_1_outstanding_ats", mapperDashboard
						.getOutstandingTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_1).toString()));
//				System.out.println("day1: " + new SimpleDateFormat("yyyy-MM-dd").format(date_1).toString() + " : " + 
//				response.get("date_1_total_upload_ats") + " : " + response.get("date_1_matched_ats") + " : "+ response.get("date_1_outstanding_ats"));

				response.put("date_2_total_upload_ats", mapperDashboard
						.getTotalUploadedTransactionsAts(new SimpleDateFormat("yyyy-MM-dd").format(date_2).toString()));
				response.put("date_2_matched_ats", mapperDashboard
						.getMatchedTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_2).toString()));
				response.put("date_2_outstanding_ats", mapperDashboard
						.getOutstandingTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_2).toString()));
//				System.out.println("day2: " + new SimpleDateFormat("yyyy-MM-dd").format(date_2).toString() + " : " + 
//				response.get("date_2_total_upload_ats") + " : " + response.get("date_2_matched_ats") + " : "+ response.get("date_2_outstanding_ats"));

				response.put("date_3_total_upload_ats", mapperDashboard
						.getTotalUploadedTransactionsAts(new SimpleDateFormat("yyyy-MM-dd").format(date_3).toString()));
				response.put("date_3_matched_ats", mapperDashboard
						.getMatchedTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_3).toString()));
				response.put("date_3_outstanding_ats", mapperDashboard
						.getOutstandingTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_3).toString()));
//				System.out.println("day3: " + new SimpleDateFormat("yyyy-MM-dd").format(date_3).toString() + " : " + 
//				response.get("date_3_total_upload_ats") + " : " + response.get("date_3_matched_ats") + " : "+ response.get("date_3_outstanding_ats"));

				response.put("date_4_total_upload_ats", mapperDashboard
						.getTotalUploadedTransactionsAts(new SimpleDateFormat("yyyy-MM-dd").format(date_4).toString()));
				response.put("date_4_matched_ats", mapperDashboard
						.getMatchedTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_4).toString()));
				response.put("date_4_outstanding_ats", mapperDashboard
						.getOutstandingTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_4).toString()));
//				System.out.println("day4: " + new SimpleDateFormat("yyyy-MM-dd").format(date_4).toString() + " : " + 
//				response.get("date_4_total_upload_ats") + " : " + response.get("date_4_matched_ats") + " : "+ response.get("date_4_outstanding_ats"));

				response.put("date_5_total_upload_ats", mapperDashboard
						.getTotalUploadedTransactionsAts(new SimpleDateFormat("yyyy-MM-dd").format(date_5).toString()));
				response.put("date_5_matched_ats", mapperDashboard
						.getMatchedTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_5).toString()));
				response.put("date_5_outstanding_ats", mapperDashboard
						.getOutstandingTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_5).toString()));
//				System.out.println("day5: " + new SimpleDateFormat("yyyy-MM-dd").format(date_5).toString() + " : " + 
//				response.get("date_5_total_upload_ats") + " : " + response.get("date_5_matched_ats") + " : "+ response.get("date_5_outstanding_ats"));

				response.put("date_6_total_upload_ats", mapperDashboard
						.getTotalUploadedTransactionsAts(new SimpleDateFormat("yyyy-MM-dd").format(date_6).toString()));
				response.put("date_6_matched_ats", mapperDashboard
						.getMatchedTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_6).toString()));
				response.put("date_6_outstanding_ats", mapperDashboard
						.getOutstandingTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_6).toString()));
//				System.out.println("day6: " + new SimpleDateFormat("yyyy-MM-dd").format(date_6).toString() + " : " + 
//				response.get("date_6_total_upload_ats") + " : " + response.get("date_6_matched_ats") + " : "+ response.get("date_6_outstanding_ats"));

				response.put("date_7_total_upload_ats", mapperDashboard
						.getTotalUploadedTransactionsAts(new SimpleDateFormat("yyyy-MM-dd").format(date_7).toString()));
				response.put("date_7_matched_ats", mapperDashboard
						.getMatchedTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_7).toString()));
				response.put("date_7_outstanding_ats", mapperDashboard
						.getOutstandingTransactionsAts(new SimpleDateFormat("yyyyMMdd").format(date_7).toString()));
//				System.out.println("day7: " + new SimpleDateFormat("yyyy-MM-dd").format(date_7).toString() + " : " + 
//				response.get("date_7_total_upload_ats") + " : " + response.get("date_7_matched_ats") + " : "+ response.get("date_7_outstanding_ats"));

				// 
				response.put("date_1_total_upload_", mapperDashboard.getTotalUploadedTransactions(
						new SimpleDateFormat("yyyy-MM-dd").format(date_1).toString()));
				response.put("date_1_matched_", mapperDashboard
						.getMatchedTransactions(new SimpleDateFormat("yyyyMMdd").format(date_1).toString()));
				response.put("date_1_outstanding_", mapperDashboard
						.getOutstandingTransactions(new SimpleDateFormat("yyyyMMdd").format(date_1).toString()));
//				System.out.println("\nday1: " + new SimpleDateFormat("yyyy-MM-dd").format(date_1).toString() + " : " + 
//						response.get("date_1_total_upload_") + " : " + response.get("date_1_matched_") + " : "+ response.get("date_1_matched_"));

				response.put("date_2_total_upload_", mapperDashboard.getTotalUploadedTransactions(
						new SimpleDateFormat("yyyy-MM-dd").format(date_2).toString()));
				response.put("date_2_matched_", mapperDashboard
						.getMatchedTransactions(new SimpleDateFormat("yyyyMMdd").format(date_2).toString()));
				response.put("date_2_outstanding_", mapperDashboard
						.getOutstandingTransactions(new SimpleDateFormat("yyyyMMdd").format(date_2).toString()));

				response.put("date_3_total_upload_", mapperDashboard.getTotalUploadedTransactions(
						new SimpleDateFormat("yyyy-MM-dd").format(date_3).toString()));
				response.put("date_3_matched_", mapperDashboard
						.getMatchedTransactions(new SimpleDateFormat("yyyyMMdd").format(date_3).toString()));
				response.put("date_3_outstanding_", mapperDashboard
						.getOutstandingTransactions(new SimpleDateFormat("yyyyMMdd").format(date_3).toString()));

				response.put("date_4_total_upload_", mapperDashboard.getTotalUploadedTransactions(
						new SimpleDateFormat("yyyy-MM-dd").format(date_4).toString()));
				response.put("date_4_matched_", mapperDashboard
						.getMatchedTransactions(new SimpleDateFormat("yyyyMMdd").format(date_4).toString()));
				response.put("date_4_outstanding_", mapperDashboard
						.getOutstandingTransactions(new SimpleDateFormat("yyyyMMdd").format(date_4).toString()));

				response.put("date_5_total_upload_", mapperDashboard.getTotalUploadedTransactions(
						new SimpleDateFormat("yyyy-MM-dd").format(date_5).toString()));
				response.put("date_5_matched_", mapperDashboard
						.getMatchedTransactions(new SimpleDateFormat("yyyyMMdd").format(date_5).toString()));
				response.put("date_5_outstanding_", mapperDashboard
						.getOutstandingTransactions(new SimpleDateFormat("yyyyMMdd").format(date_5).toString()));

				response.put("date_6_total_upload_", mapperDashboard.getTotalUploadedTransactions(
						new SimpleDateFormat("yyyy-MM-dd").format(date_6).toString()));
				response.put("date_6_matched_", mapperDashboard
						.getMatchedTransactions(new SimpleDateFormat("yyyyMMdd").format(date_6).toString()));
				response.put("date_6_outstanding_", mapperDashboard
						.getOutstandingTransactions(new SimpleDateFormat("yyyyMMdd").format(date_6).toString()));

				response.put("date_7_total_upload_", mapperDashboard.getTotalUploadedTransactions(
						new SimpleDateFormat("yyyy-MM-dd").format(date_7).toString()));
				response.put("date_7_matched_", mapperDashboard
						.getMatchedTransactions(new SimpleDateFormat("yyyyMMdd").format(date_7).toString()));
				response.put("date_7_outstanding_", mapperDashboard
						.getOutstandingTransactions(new SimpleDateFormat("yyyyMMdd").format(date_7).toString()));
//				System.out.println("\nday7: " + new SimpleDateFormat("yyyy-MM-dd").format(date_7).toString() + " : " + 
//						response.get("date_7_total_upload_") + " : " + response.get("date_7_matched_") + " : "+ response.get("date_7_matched_"));

				// Beginning and Ending
				// ATS
				AtsBeginningEnding ats_beg_end1 = mapperDashboard
						.getBeginningEndingBalanceAts(new SimpleDateFormat("yyyy-MM-dd").format(date_1).toString());
				response.put("date_1_beginning_balance_ats",
						ats_beg_end1 == null ? 0 : ats_beg_end1.getBeginning_balance_ats());
				response.put("date_1_ending_balance_ats",
						ats_beg_end1 == null ? 0 : ats_beg_end1.getEnding_balance_ats());

				// ATS
				AtsBeginningEnding ats_beg_end2 = mapperDashboard
						.getBeginningEndingBalanceAts(new SimpleDateFormat("yyyy-MM-dd").format(date_2).toString());
				response.put("date_2_beginning_balance_ats",
						ats_beg_end2 == null ? 0 : ats_beg_end2.getBeginning_balance_ats());
				response.put("date_2_ending_balance_ats",
						ats_beg_end2 == null ? 0 : ats_beg_end2.getEnding_balance_ats());

				// ATS
				AtsBeginningEnding ats_beg_end3 = mapperDashboard
						.getBeginningEndingBalanceAts(new SimpleDateFormat("yyyy-MM-dd").format(date_3).toString());
				response.put("date_3_beginning_balance_ats",
						ats_beg_end3 == null ? 0 : ats_beg_end3.getBeginning_balance_ats());
				response.put("date_3_ending_balance_ats",
						ats_beg_end3 == null ? 0 : ats_beg_end3.getEnding_balance_ats());

				// ATS
				AtsBeginningEnding ats_beg_end4 = mapperDashboard
						.getBeginningEndingBalanceAts(new SimpleDateFormat("yyyy-MM-dd").format(date_4).toString());
				response.put("date_4_beginning_balance_ats",
						ats_beg_end4 == null ? 0 : ats_beg_end4.getBeginning_balance_ats());
				response.put("date_4_ending_balance_ats",
						ats_beg_end4 == null ? 0 : ats_beg_end4.getEnding_balance_ats());

				// ATS
				AtsBeginningEnding ats_beg_end5 = mapperDashboard
						.getBeginningEndingBalanceAts(new SimpleDateFormat("yyyy-MM-dd").format(date_5).toString());
				response.put("date_5_beginning_balance_ats",
						ats_beg_end5 == null ? 0 : ats_beg_end5.getBeginning_balance_ats());
				response.put("date_5_ending_balance_ats",
						ats_beg_end5 == null ? 0 : ats_beg_end5.getEnding_balance_ats());

				// ATS
				AtsBeginningEnding ats_beg_end6 = mapperDashboard
						.getBeginningEndingBalanceAts(new SimpleDateFormat("yyyy-MM-dd").format(date_6).toString());
				response.put("date_6_beginning_balance_ats",
						ats_beg_end6 == null ? 0 : ats_beg_end6.getBeginning_balance_ats());
				response.put("date_6_ending_balance_ats",
						ats_beg_end6 == null ? 0 : ats_beg_end6.getEnding_balance_ats());

				// ATS
				AtsBeginningEnding ats_beg_end7 = mapperDashboard
						.getBeginningEndingBalanceAts(new SimpleDateFormat("yyyy-MM-dd").format(date_7).toString());
				response.put("date_7_beginning_balance_ats",
						ats_beg_end7 == null ? 0 : ats_beg_end7.getBeginning_balance_ats());
				response.put("date_7_ending_balance_ats",
						ats_beg_end7 == null ? 0 : ats_beg_end7.getEnding_balance_ats());
//				System.out.println("date_7: " + ats_beg_end7 + " : "+ response.get("date_7_beginning_balance_ats") + " : " + response.get("date_7_ending_balance_ats"));
//				System.out.println("from database: " + new SimpleDateFormat("yyyy-MM-dd").format(date_7).toString() + 
//						" : " + mapperDashboard.getBeginningEndingBalanceAts(new SimpleDateFormat("yyyy-MM-dd").format(date_7).toString()));
				// 
				BeginningEnding _beg_end1 = mapperDashboard
						.getBeginningEndingBalance(new SimpleDateFormat("yyyy-MM-dd").format(date_1).toString());
				response.put("date_1_beginning_balance_conv",
						_beg_end1 == null ? 0 : _beg_end1.getBeginning_balance_con());
				response.put("date_1_beginning_balance_ifb",
						_beg_end1 == null ? 0 : _beg_end1.getBeginning_balance_ifb());
				response.put("date_1_ending_balance_conv",
						_beg_end1 == null ? 0 : _beg_end1.getEnding_balance_con());
				response.put("date_1_ending_balance_ifb",
						_beg_end1 == null ? 0 : _beg_end1.getEnding_balance_ifb());

				// 
				BeginningEnding _beg_end2 = mapperDashboard
						.getBeginningEndingBalance(new SimpleDateFormat("yyyy-MM-dd").format(date_2).toString());
				response.put("date_2_beginning_balance_conv",
						_beg_end2 == null ? 0 : _beg_end2.getBeginning_balance_con());
				response.put("date_2_beginning_balance_ifb",
						_beg_end2 == null ? 0 : _beg_end2.getBeginning_balance_ifb());
				response.put("date_2_ending_balance_conv",
						_beg_end2 == null ? 0 : _beg_end2.getEnding_balance_con());
				response.put("date_2_ending_balance_ifb",
						_beg_end2 == null ? 0 : _beg_end2.getEnding_balance_ifb());

				// 
				BeginningEnding _beg_end3 = mapperDashboard
						.getBeginningEndingBalance(new SimpleDateFormat("yyyy-MM-dd").format(date_3).toString());
				response.put("date_3_beginning_balance_conv",
						_beg_end3 == null ? 0 : _beg_end3.getBeginning_balance_con());
				response.put("date_3_beginning_balance_ifb",
						_beg_end3 == null ? 0 : _beg_end3.getBeginning_balance_ifb());
				response.put("date_3_ending_balance_conv",
						_beg_end3 == null ? 0 : _beg_end3.getEnding_balance_con());
				response.put("date_3_ending_balance_ifb",
						_beg_end3 == null ? 0 : _beg_end3.getEnding_balance_ifb());

				// 
				BeginningEnding _beg_end4 = mapperDashboard
						.getBeginningEndingBalance(new SimpleDateFormat("yyyy-MM-dd").format(date_4).toString());
				response.put("date_4_beginning_balance_conv",
						_beg_end4 == null ? 0 : _beg_end4.getBeginning_balance_con());
				response.put("date_4_beginning_balance_ifb",
						_beg_end4 == null ? 0 : _beg_end4.getBeginning_balance_ifb());
				response.put("date_4_ending_balance_conv",
						_beg_end4 == null ? 0 : _beg_end4.getEnding_balance_con());
				response.put("date_4_ending_balance_ifb",
						_beg_end4 == null ? 0 : _beg_end4.getEnding_balance_ifb());

				// 
				BeginningEnding _beg_end5 = mapperDashboard
						.getBeginningEndingBalance(new SimpleDateFormat("yyyy-MM-dd").format(date_5).toString());
				response.put("date_5_beginning_balance_conv",
						_beg_end5 == null ? 0 : _beg_end5.getBeginning_balance_con());
				response.put("date_5_beginning_balance_ifb",
						_beg_end5 == null ? 0 : _beg_end5.getBeginning_balance_ifb());
				response.put("date_5_ending_balance_conv",
						_beg_end5 == null ? 0 : _beg_end5.getEnding_balance_con());
				response.put("date_5_ending_balance_ifb",
						_beg_end5 == null ? 0 : _beg_end5.getEnding_balance_ifb());

				// 
				BeginningEnding _beg_end6 = mapperDashboard
						.getBeginningEndingBalance(new SimpleDateFormat("yyyy-MM-dd").format(date_6).toString());
				response.put("date_6_beginning_balance_conv",
						_beg_end6 == null ? 0 : _beg_end6.getBeginning_balance_con());
				response.put("date_6_beginning_balance_ifb",
						_beg_end6 == null ? 0 : _beg_end6.getBeginning_balance_ifb());
				response.put("date_6_ending_balance_conv",
						_beg_end6 == null ? 0 : _beg_end6.getEnding_balance_con());
				response.put("date_6_ending_balance_ifb",
						_beg_end6 == null ? 0 : _beg_end6.getEnding_balance_ifb());

				// 
				BeginningEnding _beg_end7 = mapperDashboard
						.getBeginningEndingBalance(new SimpleDateFormat("yyyy-MM-dd").format(date_7).toString());
				response.put("date_7_beginning_balance_conv",
						_beg_end7 == null ? 0 : _beg_end7.getBeginning_balance_con());
				response.put("date_7_beginning_balance_ifb",
						_beg_end7 == null ? 0 : _beg_end7.getBeginning_balance_ifb());
				response.put("date_7_ending_balance_conv",
						_beg_end7 == null ? 0 : _beg_end7.getEnding_balance_con());
				response.put("date_7_ending_balance_ifb",
						_beg_end7 == null ? 0 : _beg_end7.getEnding_balance_ifb());
				
				System.out.println("it is here here: " + response);
				return response;
			} else {
				System.out.println("it is here here =======");
				return null;
			}

		} catch (Exception e) {
			throw new ExceptionsList(e);
		}

	}
}

//accountIns.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
