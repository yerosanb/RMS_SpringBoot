package com.example.demo.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.example.demo.user.model.TransactionHistory22;
import com.example.demo.user.model.Transactionhistory;

@Mapper
public interface SearchMapper {
	
	@SelectProvider(type = SearchSqlProvider.class, method = "getPayableSearchQuery")
	List<Transactionhistory> getPayableSearch(
			@Param("min_amount") String min_amount,
			@Param("max_amount") String max_amount, 
			@Param("reference") String reference,
			@Param("value_date") String value_date, 
			@Param("branch_code") String branch_code,
			@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date
			);
	
	@SelectProvider(type = SearchSqlProvider.class, method = "getReciavableSearchQuery")
	List<Transactionhistory> getRecievableSearch(
			@Param("min_amount") String min_amount,
			@Param("max_amount") String max_amount, 
			@Param("reference") String reference,
			@Param("value_date") String value_date, 
			@Param("branch_code") String branch_code,
			@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date);
	
	
	@SelectProvider(type = SearchSqlProvider.class, method = "getStockSearchQuery")
	List<Transactionhistory> getStockSearch(
			@Param("min_amount") String min_amount,
			@Param("max_amount") String max_amount, 
			@Param("reference") String reference,
			@Param("category") String category,
			@Param("source_branch") String source_branch,
//			@Param("value_date") String value_date, 
			@Param("branch_code") String branch_code,
			@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date
			);
	
	@SelectProvider(type = SearchSqlProvider.class, method = "getStockMMSSearchQuery")
	List<Transactionhistory> getStockMMSSearch(
			@Param("min_amount") String min_amount,
			@Param("max_amount") String max_amount, 
			@Param("reference") String reference,
			@Param("category") String category,
//			@Param("value_date") String value_date, 
			@Param("branch_code") String branch_code,
			@Param("store_name") String store_name,
 			@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date
			);

}
