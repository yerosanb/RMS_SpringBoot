package com.example.demo.user.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class SearchSqlProvider {
	
	public String getPayableSearchQuery(@Param("min_amount") String min_amount, @Param("max_amount") String max_amount,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date) {
		
		System.out.println("--------info before query------------"+ reference);
 		SQL sql = new SQL().SELECT_DISTINCT(""	
				+ "dp.id, dp.additional_information, dp.amount, dp.dr_cr, dp.match_status, dp.transaction_reference,"
				+ "dp.branch_code, dp.value_date, dp.upload_date, dp.posting_date")
				.FROM("data_payable dp").WHERE("1 = 1");
 		if (reference.trim() != "") {
			System.out.println("from reference: " + reference);
			sql.AND().WHERE("dp.additional_information like '%' + #{reference} + '%'");
		}
 
		if (min_amount != "") {
			System.out.println("from min_amount: " + min_amount);
			sql.AND().WHERE("dp.amount >= #{min_amount, jdbcType=FLOAT}");
		}
		
		if (max_amount != "") {
			System.out.println("from max_amount: " + max_amount);
			sql.AND().WHERE("dp.amount <= #{max_amount}");
		}
		
//		if (reference.trim()  != "") {
//			System.out.println("from reference: " + reference);
//			sql.AND().WHERE("dp.additional_information like '%#{reference}%'");
//		}
		
		if (value_date.trim()  != "") {
			System.out.println("from value_date: " + value_date);
			sql.AND().WHERE("dp.value_date like '%'+#{value_date}+'%'");
		}
		
		if (branch_code.trim()  != "") {
			System.out.println("from branch_code: " + branch_code);
			sql.AND().WHERE("dp.branch_code like '%'+#{branch_code}+'%'");
		}
		
		if (min_upload_date != 0) {
			System.out.println("from min_upload_date: " + min_upload_date);
			sql.AND().WHERE("dp.upload_date >= #{min_upload_date}");
		}
		
		if (max_upload_date != 0) {
			System.out.println("from max_upload_date: " + max_upload_date);
			sql.AND().WHERE("dp.upload_date <= #{max_upload_date}");
		}
		if (min_match_date != 0) {
			System.out.println("from min_match_date: " + min_match_date);
			sql.AND().WHERE("dp.status=12");
		}
		
		if (max_match_date != 0) {
			System.out.println("from max_match_date: " + max_match_date);
			sql.AND().WHERE("dp.status=12");
		}
		
		sql.AND().WHERE("dp.status = 1");
		sql.AND().WHERE("dp.availability = 1");
		
		
		SQL sql_payable_credit = new SQL().SELECT_DISTINCT(""
				+ "dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,"
				+ "dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date")
				.FROM("payable_credit dp, payable_matched pm").WHERE("1 = 1");
		if (reference.trim() != "") {
			System.out.println("from reference: " + reference);
			sql_payable_credit.AND().WHERE("dp.additional_information like '%' + #{reference} + '%'");
		}
		if (min_amount != "") {
			System.out.println("from min_amount: " + min_amount);
			sql_payable_credit.AND().WHERE("dp.amount >= #{min_amount}");
		}
		if (max_amount != "") {
			System.out.println("from max_amount: " + max_amount);
			sql_payable_credit.AND().WHERE("dp.amount <= #{max_amount}");
		}
//		if (reference.trim()  != "") {
//			System.out.println("from reference: " + reference);
//			sql_payable_credit.AND().WHERE("dp.additional_information like '%#{reference}%'");
//		}
		if (value_date.trim()  != "") {
			System.out.println("from value_date: " + value_date);
			sql_payable_credit.AND().WHERE("dp.value_date like  '%'+#{value_date}+'%'");
		}
		if (branch_code.trim()  != "") {
			System.out.println("from branch_code: " + branch_code);
			sql_payable_credit.AND().WHERE("dp.branch_code like '%'+#{branch_code}+'%'");
		}
		if (min_upload_date != 0) {
			System.out.println("from min_upload_date: " + min_upload_date);
			sql_payable_credit.AND().WHERE("dp.upload_date >= #{min_upload_date}");
		}
		if (max_upload_date != 0) {
			System.out.println("from max_upload_date: " + max_upload_date);
			sql_payable_credit.AND().WHERE("dp.upload_date <= #{max_upload_date}");
		}
		if (min_match_date != 0) {
			System.out.println("from min_match_date: " + min_match_date);
			sql_payable_credit.AND().WHERE("pm.match_date >= #{min_match_date}");
		}
		if (max_match_date != 0) {
			System.out.println("from max_match_date: " + max_match_date);
			sql_payable_credit.AND().WHERE("pm.match_date <= #{max_match_date}");
		}
	
		sql_payable_credit.AND().WHERE("pm.payable_credit_id = dp.id");
		sql_payable_credit.AND().WHERE("dp.status = 1");
		sql_payable_credit.AND().WHERE("dp.availability = 1");
		

		sql_payable_credit.AND().WHERE("pm.status = 1");
		sql_payable_credit.AND().WHERE("pm.availability = 1");
		
		SQL sql_payable_debit = new SQL().SELECT_DISTINCT(""
				+ "dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,"
				+ "dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date")
				.FROM("payable_debit dp, payable_matched pm").WHERE("1 = 1");

		if (reference.trim() != "") {
			System.out.println("from reference: " + reference);
			sql_payable_debit.AND().WHERE("dp.additional_information like '%' + #{reference} + '%'");
		}
		if (min_amount != "") {
			System.out.println("from min_amount: " + min_amount);
			sql_payable_debit.AND().WHERE("dp.amount >= #{min_amount}");
		}
		if (max_amount != "") {
			System.out.println("from max_amount: " + max_amount);
			sql_payable_debit.AND().WHERE("dp.amount <= #{max_amount}");
		}
		
		if (value_date.trim()  != "") {
			System.out.println("from value_date: " + value_date);
			sql_payable_debit.AND().WHERE("dp.value_date like '%'+#{value_date}+'%'");
		}
		
		if (branch_code.trim()  != "") {
			System.out.println("from branch_code: " + branch_code);
			sql_payable_debit.AND().WHERE("dp.branch_code like '%'+#{branch_code}+'%'");
		}
		if (min_upload_date != 0) {
			System.out.println("from min_upload_date: " + min_upload_date);
			sql_payable_debit.AND().WHERE("dp.upload_date >= #{min_upload_date}");
		}
		if (max_upload_date != 0) {
			System.out.println("from max_upload_date: " + max_upload_date);
			sql_payable_debit.AND().WHERE("dp.upload_date <= #{max_upload_date}");
		}
		if (min_match_date != 0) {
			System.out.println("from min_match_date: " + min_match_date);
			sql_payable_debit.AND().WHERE("pm.match_date >= #{min_match_date}");
		}
		if (max_match_date != 0) {
			System.out.println("from max_match_date: " + max_match_date);
			sql_payable_debit.AND().WHERE("pm.match_date <= #{max_match_date}");
		}
		sql_payable_debit.AND().WHERE("pm.match_id = dp.match_id");
		sql_payable_debit.AND().WHERE("dp.status = 1");
		sql_payable_debit.AND().WHERE("dp.availability = 1");

		sql_payable_debit.AND().WHERE("pm.status = 1");
		sql_payable_debit.AND().WHERE("pm.availability = 1");
		
//		String sql_all = sql_payable_debit.toString();
		
		String sql_all = sql.toString() + " UNION " + sql_payable_credit.toString() + " UNION " + sql_payable_debit.toString();
        System.out.println("the returnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"+sql_all+"the end");
		return sql_all;
	}

	public String getReciavableSearchQuery(@Param("min_amount") String min_amount, @Param("max_amount") String max_amount,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date) {
		
		SQL sql = new SQL().SELECT_DISTINCT(""
				+ "dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,"
				+ "dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date")
				.FROM("data_recivable dp").WHERE("1 = 1") ;
 
		if (min_amount != "") {
			System.out.println("from min_amount: " + min_amount);
			sql.AND().WHERE("dp.amount >= #{min_amount}");
		}
		if (max_amount != "") {
			System.out.println("from max_amount: " + max_amount);
			sql.AND().WHERE("dp.amount <= #{max_amount}");
		}
		if (reference != "") {
			System.out.println("from reference: " + reference);
			sql.AND().WHERE("dp.additional_information like '%'+#{reference}+'%'");
		}
		if (value_date != "") {
			System.out.println("from value_date: " + value_date);
			sql.AND().WHERE("dp.value_date like '%'+#{value_date}+'%'");
		}
		if (branch_code != "") {
			System.out.println("from branch_code: " + branch_code);
			sql.AND().WHERE("dp.branch_code like '%'+#{branch_code}+'%'");
		}
		if (min_upload_date != 0) {
			System.out.println("from min_upload_date: " + min_upload_date);
			sql.AND().WHERE("dp.upload_date >= min_upload_date");
		}
		if (max_upload_date != 0) {
			System.out.println("from max_upload_date: " + max_upload_date);
			sql.AND().WHERE("dp.upload_date <= max_upload_date");
		}
		if (min_match_date != 0) {
			System.out.println("from min_match_date: " + min_match_date);
			sql.AND().WHERE("dp.status=12");
		}
		if (max_match_date != 0) {
			System.out.println("from max_match_date: " + max_match_date);
			sql.AND().WHERE("dp.status=12");
		}
		sql.AND().WHERE("dp.status = 1");
		sql.AND().WHERE("dp.availability = 1");
		
		
		SQL sql_payable_credit = new SQL().SELECT_DISTINCT(""
				+ "dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,"
				+ "dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date")
				.FROM("receivable_credit dp, receivable_matched pm").WHERE("1 = 1");

		if (min_amount != "") {
			System.out.println("from min_amount: " + min_amount);
			sql_payable_credit.AND().WHERE("dp.amount >= #{min_amount}");
		}
		if (max_amount != "") {
			System.out.println("from max_amount: " + max_amount);
			sql_payable_credit.AND().WHERE("dp.amount <= #{max_amount}");
		}
		if (reference != "") {
			System.out.println("from reference: " + reference);
			sql_payable_credit.AND().WHERE("dp.additional_information like '%'+#{reference}+'%'");
		}
		if (value_date != "") {
			System.out.println("from value_date: " + value_date);
			sql_payable_credit.AND().WHERE("dp.value_date like '%'+#{value_date}+'%'");
		}
		if (branch_code != "") {
			System.out.println("from branch_code: " + branch_code);
			sql_payable_credit.AND().WHERE("dp.branch_code like '%'+#{branch_code}%'");
		}
		if (min_upload_date != 0) {
			System.out.println("from min_upload_date: " + min_upload_date);
			sql_payable_credit.AND().WHERE("dp.upload_date >= #{min_upload_date}");
		}
		if (max_upload_date != 0) {
			System.out.println("from max_upload_date: " + max_upload_date);
			sql_payable_credit.AND().WHERE("dp.upload_date <= #{max_upload_date}");
		}
		if (min_match_date != 0) {
			System.out.println("from min_match_date: " + min_match_date);
			sql_payable_credit.AND().WHERE("pm.match_date >= #{min_match_date}");
		}
		if (max_match_date != 0) {
			System.out.println("from max_match_date: " + max_match_date);
			sql_payable_credit.AND().WHERE("pm.match_date <= #{max_match_date}");
		}
		sql_payable_credit.AND().WHERE("pm.credit_id = dp.id");
		sql_payable_credit.AND().WHERE("dp.status = 1");
		sql_payable_credit.AND().WHERE("dp.availability = 1");
         
		sql_payable_credit.AND().WHERE("pm.status = 1");
		sql_payable_credit.AND().WHERE("pm.availability = 1");
		
		SQL sql_payable_debit = new SQL().SELECT_DISTINCT(""
				+ "dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,"
				+ "dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date")
				.FROM("receivable_debit dp, receivable_matched pm").WHERE("1 = 1");

		if (min_amount != "") {
			System.out.println("from min_amount: " + min_amount);
			sql_payable_debit.AND().WHERE("dp.amount >= #{min_amount}");
		}
		if (max_amount != "") {
			System.out.println("from max_amount: " + max_amount);
			sql_payable_debit.AND().WHERE("dp.amount <= #{max_amount}");
		}
		if (reference != "") {
			System.out.println("from reference: " + reference);
			sql_payable_debit.AND().WHERE("dp.additional_information like '%'+#{reference}+'%'");
		}
		if (value_date != "") {
			System.out.println("from value_date: " + value_date);
			sql_payable_debit.AND().WHERE("dp.value_date like '%'+#{value_date}+'%'");
		}
		if (branch_code != "") {
			System.out.println("from branch_code: " + branch_code);
			sql_payable_debit.AND().WHERE("dp.branch_code like '%'+#{branch_code}+'%'");
		}
		if (min_upload_date != 0) {
			System.out.println("from min_upload_date: " + min_upload_date);
			sql_payable_debit.AND().WHERE("dp.upload_date >= #{min_upload_date}");
		}
		if (max_upload_date != 0) {
			System.out.println("from max_upload_date: " + max_upload_date);
			sql_payable_debit.AND().WHERE("dp.upload_date <= #{max_upload_date}");
		}
		if (min_match_date != 0) {
			System.out.println("from min_match_date: " + min_match_date);
			sql_payable_debit.AND().WHERE("pm.match_date >= #{min_match_date}");
		}
		if (max_match_date != 0) {
			System.out.println("from max_match_date: " + max_match_date);
			sql_payable_debit.AND().WHERE("pm.match_date <= #{max_match_date}");
		}
		
		sql_payable_debit.AND().WHERE("pm.match_id = dp.match_id");
		sql_payable_debit.AND().WHERE("dp.status = 1");
		sql_payable_debit.AND().WHERE("dp.availability = 1");

		sql_payable_debit.AND().WHERE("pm.status = 1");
		sql_payable_debit.AND().WHERE("pm.availability = 1");
		
//		String sql_all = sql_payable_debit.toString();
		
		String sql_all = sql.toString() + " UNION " + sql_payable_credit.toString() + " UNION " + sql_payable_debit.toString();
		
		System.out.println ("the returnnnnnnnnnnn"+ sql_all);

		return sql_all;
	}
	
	public String getStockCoreSearchQuery(@Param("min_amount") String min_amount, @Param("max_amount") String max_amount,
			@Param("reference") String reference, @Param("category") String category, @Param("source_branch") String source_branch,  
			@Param("branch_code") String branch_code,@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date) {
		
		System.out.println("--------info before query-------------------------------------------------------------------------------"+ reference);
 		SQL sql = new SQL().SELECT_DISTINCT(""	
				+ "dp.id, dp.description, dp.amount, dp.dr_cr, dp.match_status, dp.transaction_reference,"
				+ "dp.source_branch, dp.value_date, dp.posting_date, dp.account as stock_account_segment, dp.branch_name as branch_code")
				.FROM("data_stock_core dp").WHERE("1 = 1");
 		
 		if (reference != "") {
			System.out.println("from reference stockkkkkk: " + reference);
			sql.AND().WHERE("dp.description like '%'+#{reference}+'%'");
		}
 		if (category != "") {
			System.out.println("from category: " + category);
			sql.AND().WHERE("dp.account like '%'+#{category}+'%'");
		}
 		if (source_branch != "") {
			System.out.println("from source_branch: " + source_branch);
			sql.AND().WHERE("dp.source_branch like '%'+#{source_branch}+'%'");
		}
        
 		if (min_upload_date != 0) {
			System.out.println("from min_upload_date: " + min_upload_date);
			sql.AND().WHERE("CONVERT(date, dp.value_date) >= CONVERT(date, CAST(#{min_upload_date} AS char(8)))");
		}
		if (max_upload_date != 0) {
			System.out.println("from max_upload_date: " + max_upload_date);
			sql.AND().WHERE("CONVERT(date, dp.value_date) <= CONVERT(date, CAST(#{max_upload_date} AS char(8)))");
		}
		
		if (min_amount != "") {
			System.out.println("from min_amount: " + min_amount);
			sql.AND().WHERE("dp.amount >= #{min_amount}");
		}

		if (max_amount != "") {
			System.out.println("from max_amount: " + max_amount);
			sql.AND().WHERE("dp.amount <= #{max_amount}");
		}
		
//		if (value_date != "") {
//			System.out.println("from value_date: " + value_date);
//			sql.AND().WHERE("dp.value_date like '%'+#{value_date}+'%'");
//		}
//		
		if (branch_code != "") {
			System.out.println("from branch_code: " + branch_code);
			sql.AND().WHERE("dp.branch_name like '%'+#{branch_code}+'%'");
		}
		
		if (min_match_date != 0) {
			System.out.println("from min_match_date: " + min_match_date);
			sql.AND().WHERE("dp.status=12");
		}
		
		if (max_match_date != 0) {
			System.out.println("from max_match_date: " + max_match_date);
			sql.AND().WHERE("dp.status=12");
		}
		
		sql.AND().WHERE("dp.status = 1");
		sql.AND().WHERE("dp.availability = 1");
		
		
		SQL sql_payable_credit = new SQL().SELECT_DISTINCT(""
				+ "dp.id, dp.description,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,"
				+ "dp.source_branch,dp.value_date, dp.posting_date, dp.account as stock_account_segment, dp.branch_name as branch_code")
				.FROM("stock_core dp, stock_matched pm").WHERE("1 = 1");
		if (reference.trim() != "") {
			System.out.println("from reference: " + reference);
			sql_payable_credit.AND().WHERE("dp.description like '%' + #{reference} + '%'");
		}
		if (category != "") {
			System.out.println("from category: " + category);
			sql_payable_credit.AND().WHERE("dp.account like '%'+#{category}+'%'");
		}
		
		if (source_branch != "") {
			System.out.println("from category: " + category);
			sql_payable_credit.AND().WHERE("dp.source_branch like '%'+#{source_branch}+'%'");
		}
		if (min_upload_date != 0) {
			System.out.println("from min_upload_date: " + min_upload_date);
			sql_payable_credit.AND().WHERE("CONVERT(date, dp.value_date) >= CONVERT(date, CAST(#{min_upload_date} AS char(8)))");
		}
		if (max_upload_date != 0) {
			System.out.println("from max_upload_date: " + max_upload_date);
			sql_payable_credit.AND().WHERE("CONVERT(date, dp.value_date) <= CONVERT(date, CAST(#{max_upload_date} AS char(8)))");
		}
		
		if (min_amount != "") {
			System.out.println("from min_amount: " + min_amount);
			sql_payable_credit.AND().WHERE("dp.amount >= #{min_amount}");
		}
		if (max_amount != "") {
			System.out.println("from max_amount: " + max_amount);
			sql_payable_credit.AND().WHERE("dp.amount <= #{max_amount}");
		}
        
//		if (value_date  != "") {
//			System.out.println("from value_date: " + value_date);
//			sql_payable_credit.AND().WHERE("dp.value_date like  '%' + #{value_date} + '%'");
//		}
		
		if (branch_code  != "") {
			System.out.println("from branch_code: " + branch_code);
			sql_payable_credit.AND().WHERE("dp.branch_name like '%' + #{branch_code} + '%'");
		}
		
		if (min_match_date != 0) {
			System.out.println("from min_match_date: " + min_match_date);
			sql_payable_credit.AND().WHERE("pm.match_date >= #{min_match_date}");
		}
		
		if (max_match_date != 0) {
			System.out.println("from max_match_date: " + max_match_date);
			sql_payable_credit.AND().WHERE("pm.match_date <= #{max_match_date}");
		}
	    
		sql_payable_credit.AND().WHERE("pm.match_id = dp.match_id");
		sql_payable_credit.AND().WHERE("dp.status = 1");
		sql_payable_credit.AND().WHERE("dp.availability = 1");
        
		sql_payable_credit.AND().WHERE("pm.status = 1");
		sql_payable_credit.AND().WHERE("pm.availability = 1");
		 
		String sql_all = sql.toString() + " UNION " + sql_payable_credit.toString();
        System.out.println("the returnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"+sql_all+"the end");
		return sql_all;
	}
	
	public String getStockMMSSearchQuery(@Param("min_amount") String min_amount, @Param("max_amount") String max_amount,
			@Param("reference") String reference, @Param("category") String category,
			@Param("branch_code") String branch_code, @Param("store_name") String store_name,@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date) {
		
		System.out.println("--------info before query------------"+ reference);
 		SQL sql = new SQL().SELECT_DISTINCT(""	
				+ "dp.id, dp.store_code, dp.reference, dp.account_segment as stock_account_segment, dp.match_status, dp.date as stock_date,"
				+ "dp.description, dp.period, dp.amount, dp.dr_cr, dp.transaction_code as tran_code, dp.store_name, dp.category_description")
				.FROM("data_stock_mms dp").WHERE("1 = 1");
 		
 		if (reference.trim() != "") {
			System.out.println("from reference: " + reference);
			sql.AND().WHERE("dp.reference like '%' + #{reference} + '%'");
		}
 		if (category != "") {
			System.out.println("from category: " + category);
			
			sql.AND().WHERE("dp.account_segment like '%'+#{category}");
		}
 		
 		if (min_upload_date != 0) {
			System.out.println("from min_upload_date: " + min_upload_date);
			sql.AND().WHERE("CONVERT(date, dp.date) >= CONVERT(date, CAST(#{min_upload_date} AS char(8)))");
		}
		if (max_upload_date != 0) {
			System.out.println("from max_upload_date: " + max_upload_date);
			sql.AND().WHERE("CONVERT(date, dp.date) <= CONVERT(date, CAST(#{max_upload_date} AS char(8)))");
		}
		
 		if (store_name != "") {
			System.out.println("from category: " + category);
			
			sql.AND().WHERE("dp.store_name like '%'+#{store_name}");
		}
 	 
		if (min_amount != "") {
			System.out.println("from min_amount: " + min_amount);
			sql.AND().WHERE("dp.amount >= #{min_amount}");
		}
		
		if (max_amount != "") {
			System.out.println("from max_amount: " + max_amount);
			sql.AND().WHERE("dp.amount <= #{max_amount}");
		}
		
		if (min_match_date != 0) {
			System.out.println("from min_match_date: " + min_match_date);
			sql.AND().WHERE("dp.status=12");
		}
		
		if (max_match_date != 0) {
			System.out.println("from max_match_date: " + max_match_date);
			sql.AND().WHERE("dp.status=12");
		}
		
		sql.AND().WHERE("dp.status = 1");
		sql.AND().WHERE("dp.availability = 1");
		
		SQL sql_payable_credit = new SQL().SELECT_DISTINCT(""
				+ "dp.id, dp.store_code, dp.reference, dp.account_segment as stock_account_segment, dp.match_status, dp.date as stock_date,"
				+ "dp.description, dp.period, dp.amount, dp.dr_cr, dp.transaction_code as tran_code, dp.store_name, dp.category_description")
				.FROM("stock_mms dp, stock_matched pm").WHERE("1 = 1");
		
		if (reference.trim() != "") {
			System.out.println("from reference: " + reference);
			sql_payable_credit.AND().WHERE("dp.reference like '%' + #{reference} + '%'");
		}
		if (category != "") {
			System.out.println("from category: " + category);
			sql_payable_credit.AND().WHERE("dp.account_segment like '%'+#{category}");
		}
		if (store_name != "") {
			System.out.println("from category: " + category);
			sql_payable_credit.AND().WHERE("dp.store_name like '%'+#{store_name}");
		}
		if (min_amount != "") {
			System.out.println("from min_amount: " + min_amount);
			sql_payable_credit.AND().WHERE("dp.amount >= #{min_amount}");
		}
		if (min_upload_date != 0) {
			System.out.println("from min_upload_date: " + min_upload_date);
			sql_payable_credit.AND().WHERE("CONVERT(date, dp.date) >= CONVERT(date, CAST(#{min_upload_date} AS char(8)))");
		}
		if (max_upload_date != 0) {
			System.out.println("from max_upload_date: " + max_upload_date);
			sql_payable_credit.AND().WHERE("CONVERT(date, dp.date) <= CONVERT(date, CAST(#{max_upload_date} AS char(8)))");
		}
		if (max_amount != "") {
			System.out.println("from max_amount: " + max_amount);
			sql_payable_credit.AND().WHERE("dp.amount <= #{max_amount}");
		}
        
		if (min_match_date != 0) {
			System.out.println("from min_match_date: " + min_match_date);
			sql_payable_credit.AND().WHERE("pm.match_date >= #{min_match_date}");
		}
		
		if (max_match_date != 0) {
			System.out.println("from max_match_date: " + max_match_date);
			sql_payable_credit.AND().WHERE("pm.match_date <= #{max_match_date}");
		}
	
		sql_payable_credit.AND().WHERE("pm.stock_mms_id = dp.id");
		sql_payable_credit.AND().WHERE("dp.status = 1");
		sql_payable_credit.AND().WHERE("dp.availability = 1");

		sql_payable_credit.AND().WHERE("pm.status = 1");
		sql_payable_credit.AND().WHERE("pm.availability = 1");
		
		String sql_all = sql.toString() + " UNION " + sql_payable_credit.toString();
        System.out.println("the returnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"+sql_all+"the end");
		return sql_all;
	}

	
	
	


}
