package com.example.demo.abebayehu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.demo.abebayehu.entity.Mms_trial_balance;
import com.example.demo.abebayehu.entity.Raw_fixed_core;
import com.example.demo.abebayehu.entity.Raw_fixed_mms;
import com.example.demo.abebayehu.entity.core_detail_deleted;
import com.example.demo.abebayehu.entity.mms_detail_deleted;
//import com.example.demo.user.model.File_rtgs_awb_core;
import com.example.demo.abebayehu.entity.view_fixed_core_deleted;
import com.example.demo.abebayehu.entity.view_fixed_mms_deleted;

@Mapper
public interface MapperFixedAsset {

	@Select("select * from data_fixed_core where (account_number LIKE '01197%' OR account_number like '01A97%') and status = 1 and availability = 1  ")
	List<Raw_fixed_core> get_raw_fixed_core_for_recon_computer();

	@Select("select * from data_fixed_core where (account_number LIKE '01196%' OR account_number like '01A96%') and status = 1 and availability = 1  ")
	List<Raw_fixed_core> get_raw_fixed_core_for_recon_equipment();

	@Select("select * from data_fixed_core where  (account_number LIKE '01198%' OR account_number like '01A98%') and status = 1 and availability = 1  ")
	List<Raw_fixed_core> get_raw_fixed_core_for_recon_vehicle();

	@Select("select * from data_fixed_core where  (account_number LIKE '01195%' OR account_number like '01A95%') and status = 1 and availability = 1  ")
	List<Raw_fixed_core> get_raw_fixed_core_for_recon_furniture();

	@Select("select *  from data_fixed_mms where main_pg= 'AB' and status = 1 and availability = 1 and created_date<=#{transaction_date} ")
	List<Raw_fixed_mms> get_raw_fixed_mms_for_recon_computer(@Param("transaction_date") String transaction_date);

	@Select("select *  from data_fixed_mms where main_pg= 'GX' and status = 1 and availability = 1 and created_date<=#{transaction_date} ")
	List<Raw_fixed_mms> get_raw_fixed_mms_for_recon_equipment(@Param("transaction_date") String transaction_date);

	@Select("select *  from data_fixed_mms where main_pg= 'BF' and status = 1 and availability = 1 and created_date<=#{transaction_date} ")
	List<Raw_fixed_mms> get_raw_fixed_mms_for_recon_furniture(@Param("transaction_date") String transaction_date);

	@Select("select *  from data_fixed_mms where main_pg= 'CP' and status = 1 and availability = 1 and created_date<=#{transaction_date} ")
	List<Raw_fixed_mms> get_raw_fixed_mms_for_recon_vehicle(@Param("transaction_date") String transaction_date);

	@Select("INSERT INTO fixed_mms(created_date, asset_id, asset_description, tag_number, branch_name, quantity, grv_number, giv_number, original_cost, book_value, main_pg, old_main_pg, "
			+ "match_status, status, availability) OUTPUT Inserted.id SELECT created_date, asset_id, asset_description, tag_number, branch_name, quantity, grv_number, giv_number, original_cost, book_value, main_pg, old_main_pg, '1', status, availability FROM data_fixed_mms "
			+ "where id = #{id}; delete from data_fixed_mms where id = #{id};")
	Long moveFixedAssetMmsData(@Param("id") Long long1);

	@Select("INSERT INTO fixed_core(account_number, transaction_date, posting_date, value_date, credit, debit, branch_code, REFERENCE, naration, account_description, account_name, "
			+ "match_status, match_id, status, availability) OUTPUT Inserted.id SELECT account_number, transaction_date, posting_date, "
			+ "value_date, credit, debit, branch_code, REFERENCE, naration, account_description, account_name, '1',  #{match_id}, status, availability FROM data_fixed_core "
			+ "where id = #{id}; delete from data_fixed_core where id = #{id};")
	Long moveFixedAssetCoreData(@Param("id") Long long1, @Param("match_id") String match_id);

	@Select("INSERT INTO reversal_fixed_core(account_number, transaction_date, posting_date, value_date, credit, debit, branch_code, REFERENCE, naration, account_description, account_name, "
			+ " match_id,match_date,firstname, status, availability) OUTPUT Inserted.id SELECT account_number, transaction_date, posting_date, "
			+ "value_date, credit, debit, branch_code, REFERENCE, naration, account_description, account_name,  #{match_id}, #{match_date}, #{match_by}, status, availability FROM data_fixed_core "
			+ "where id = #{id}; delete from data_fixed_core where id = #{id};")
	Long moveFixedAssetCoreReversalData(@Param("id") Long long1, @Param("match_id") String match_id,
			@Param("match_date") Long match_date, @Param("match_by") String match_by);

	@Select(" select  CONCAT(firstname, ' ', middlename) as fullname from users where id=#{id} ")
	String fullName(Long id);

	@Select("insert into fixed_matched (fixed_mms_id, match_id, match_date, reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
			+ "values(#{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many}, #{status}, #{availability})")
	Long addFixedAssetMatched(@Param("id") Long id, @Param("match_id") String match_id, @Param("date") Long date_now,
			@Param("recon_type") String recon_type, @Param("many_to_many") String many_to_many,
			@Param("status") String status, @Param("availability") String availability);

	@Insert("insert into user_fixed_matched (user_id, fixed_matched_id, date, status, availability, type) values "
			+ "(#{user_id}, #{fixed_matched_id}, #{date},  #{status}, #{availability}, #{type})")
	void addUserFixedMatched(@Param("user_id") Long user_id, @Param("fixed_matched_id") Long fixed_matched_id,
			@Param("date") Long date, @Param("status") String status, @Param("availability") String availability,
			@Param("type") String type);

	@Insert("insert into match_reason (match_id, reason, type, reference_amount, status, availability) values "
			+ " (#{match_id}, #{reason}, #{type}, #{reference_amount}, #{status}, #{availability});")
	void addMatchReason(@Param("match_id") String match_id, @Param("reason") String reason, @Param("type") String type,
			@Param("reference_amount") String reference_amount, @Param("status") String status,
			@Param("availability") String availability);

	@Select("insert into edit_reason (user_id,current_id, type, reason, date, status, availability, edit_delete)OUTPUT Inserted.Id values "
			+ "(#{user_id},#{id}, #{type}, #{reason}, #{date}, #{status}, #{availability}, #{edit_delete})")
	Long addReasonForEdit(@Param("id") Long id, @Param("user_id") Long user_id, @Param("type") String type,
			@Param("reason") String reason, @Param("date") String string, @Param("status") String status,
			@Param("availability") String availability, @Param("edit_delete") String edit_delete);

	@Select("INSERT INTO fixed_mms_edit_history(created_date, asset_id, asset_description, tag_number, branch_name, quantity, grv_number, giv_number, original_cost, book_value, main_pg, old_main_pg, "
			+ "match_status, status, availability, edit_reason_id, new_old) OUTPUT Inserted.id SELECT created_date, asset_id, asset_description, tag_number, branch_name, quantity, grv_number, giv_number,"
			+ " original_cost, book_value, main_pg, old_main_pg, match_status, status, availability,#{reason_id}, 'deleted' FROM data_fixed_mms "
			+ "where id = #{id};")
	Long moveDeletedMmsData(@Param("id") Long id, @Param("reason_id") Long reason_id);

	@Select("INSERT INTO fixed_mms_edit_history(created_date, asset_id, asset_description, tag_number, branch_name, quantity, grv_number, giv_number, original_cost, book_value, main_pg, old_main_pg, "
			+ "match_status, status, availability, edit_reason_id, new_old) OUTPUT Inserted.id SELECT created_date, asset_id, asset_description, tag_number, branch_name, quantity, grv_number, giv_number,"
			+ " original_cost, book_value, main_pg, old_main_pg, match_status, status, availability,#{reason_id}, 'deleted' FROM data_fixed_mms_waiting "
			+ "where id = #{id};")
	Long moveDeletedMmsWaitingData(@Param("id") Long id, @Param("reason_id") Long reason_id);

	@Select("INSERT INTO fixed_core_edit_history(account_number, transaction_date, posting_date, value_date, credit, debit, branch_code, REFERENCE, naration, account_description, account_name, "
			+ "match_status, status, availability, edit_reason_id, new_old) OUTPUT Inserted.id SELECT account_number, transaction_date, posting_date, "
			+ "value_date, credit, debit, branch_code, REFERENCE, naration, account_description, account_name, '1', status, availability, #{reason_id}, 'deleted' FROM data_fixed_core "
			+ "where id = #{id};")
	Long moveDeletedFixedCoreData(@Param("id") Long id, @Param("reason_id") Long reason_id);

	@Update("update data_fixed_mms set availability = '0' where id = #{id}")
	void deleteTransaction(@Param("id") Long id);

	@Update("update data_fixed_mms_waiting set availability = '0' where id = #{id}")
	void deleteWaitingTransaction(@Param("id") Long id);

	@Update("update data_fixed_core set availability = '0' where id = #{id}")
	void deleteFixedCoreTransaction(@Param("id") Long id);

	@Select("select distinct dna.id, dna.value_date, dna.credit, dna.debit, dna.branch_code, dna.naration,  dna.account_name , er.edit_delete from data_fixed_core dna, edit_reason er where dna.id=er.current_id and er.type='data_fixed_core' "
			+ "union select distinct rna.id, rna.value_date, rna.credit, rna.debit, rna.branch_code, rna.naration,  rna.account_name , er.edit_delete from fixed_core rna, edit_reason er where rna.id=er.current_id and er.type='fixed_core' and er.edit_delete='1' ")
	List<view_fixed_core_deleted> get_edited_fixed_core();

	@Select("select distinct dna.id, dna.new_old,  dna.value_date, dna.credit, dna.debit, dna.branch_code,  dna.naration,  dna.account_name, er.reason, u.firstname, u.lastname, er.DATE, er.edit_delete , dna.edit_reason_id from fixed_core_edit_history dna, edit_reason er, users u where er.current_id=#{id} and er.id=dna.edit_reason_id and u.id=er.user_id")
	List<core_detail_deleted> get_edited_detail_fixed_core(Long id);

	@Select("select distinct dna.id, dna.giv_number, dna.grv_number, dna.original_cost, dna.created_date, dna.tag_number, er.edit_delete from data_fixed_mms dna, edit_reason er where dna.id=er.current_id and er.type='data_fixed_mms' "
			+ "union select distinct rna.id, rna.giv_number, rna.grv_number, rna.original_cost, rna.created_date, rna.tag_number, er.edit_delete from fixed_mms rna, edit_reason er where rna.id=er.current_id and er.type='fixed_mms' and er.edit_delete='1' "
			+ "union select distinct dna.id, dna.giv_number, dna.grv_number, dna.original_cost, dna.created_date, dna.tag_number, er.edit_delete from data_fixed_mms_waiting dna, edit_reason er where dna.id=er.current_id and er.type='data_fixed_mms_waiting' ")
	List<view_fixed_mms_deleted> get_deleted_fixed_mms();

	@Select("select distinct dna.id, dna.new_old,  dna.giv_number, dna.grv_number, dna.original_cost, dna.created_date, dna.tag_number,  er.reason, u.firstname, u.lastname, er.DATE, er.edit_delete , dna.edit_reason_id from fixed_mms_edit_history dna, edit_reason er, users u where er.current_id=#{id} and er.id=dna.edit_reason_id and u.id=er.user_id")
	List<mms_detail_deleted> get_edited_detail_fixed_mms(Long id);

	@Select("SELECT TOP (1) mms_fur, mms_comp, mms_mv, mms_oe FROM data_trial_balance order by date desc")
	Mms_trial_balance getInitialFixedAssetEndingBalances();

	@Select("SELECT TOP (1) stock_stationary,stock_tools,stock_spares,stock_uniform,stock_accessory,stock_check,stock_sanitory,stock_computer,stock_furniture,stock_office_equipment FROM data_trial_balance order by date desc")
	Mms_trial_balance getInitialsStockEndingBalances();

	@Update("update data_trial_balance set mms_comp = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(mms_comp), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_com_add(@Param("amount") String amount);

	@Update("update data_trial_balance set mms_comp = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(mms_comp), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_com_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set mms_fur = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(mms_fur), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_fur_add(@Param("amount") String amount);

	@Update("update data_trial_balance set mms_fur = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(mms_fur), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_fur_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set mms_oe = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(mms_oe), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_oe_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set mms_oe = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(mms_oe), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_oe_add(@Param("amount") String amount);

	@Update("update data_trial_balance set mms_mv = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(mms_mv), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_mv_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set mms_mv = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(mms_mv), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_mv_add(@Param("amount") String amount);

	// stock
	@Update("update data_trial_balance set stock_stationary = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_stationary), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_Stationary_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_stationary = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_stationary), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_Stationary_add(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_tools = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_tools), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_tools_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_tools = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_tools), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_tools_add(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_spares = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_spares), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_spares_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_spares = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_spares), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_spares_add(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_uniform = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_uniform), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_uniform_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_uniform = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_uniform), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_uniform_add(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_accessory = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_accessory), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_accessory_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_accessory = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_accessory), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_accessory_add(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_check = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_check), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_check_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_check = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_check), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_check_add(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_sanitory = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_sanitory), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_sanitory_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_sanitory = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_sanitory), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_sanitory_add(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_computer = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_computer), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_computer_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_computer = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_computer), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_computer_add(@Param("amount") String amount);
	@Update("update data_trial_balance set stock_furniture = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_furniture), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_furniture_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_furniture = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_furniture), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_furniture_add(@Param("amount") String amount);
	@Update("update data_trial_balance set stock_office_equipment = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_office_equipment), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) - (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_office_equipment_sub(@Param("amount") String amount);

	@Update("update data_trial_balance set stock_office_equipment = " + "( " + "	select " + "	( " + "		( "
			+ "		select ISNULL(SUM(stock_office_equipment), 0) from data_trial_balance where id = (select top 1 id from data_trial_balance order by id desc) "
			+ "		) + (#{amount}) " + "	)  "
			+ ") where id = (select top 1 id from data_trial_balance order by id desc)")
	Boolean updateFixedAssetBalance_stock_office_equipment_add(@Param("amount") String amount);
    
	@Select("select date from settings_data where key_identifier='disposed_date'")
	String getDisposedDate();
	
	@Select("select date from settings_data where key_identifier='removed_date'")
	String getRemovedDate();
	
	@Select("select date from settings_data where key_identifier='waiting_date'")
	String getWaitingDate();
   
	@Update("UPDATE settings_data SET date = CASE " +
	        "WHEN key_identifier = 'disposed_date' THEN #{disposed_date} " +
	        "WHEN key_identifier = 'removed_date' THEN #{removed_date} " +
	        "WHEN key_identifier = 'waiting_date' THEN #{waiting_date} " +
	        "ELSE date END")
	Boolean updateDate(@Param("disposed_date")String disposed_date, @Param("removed_date")String removed_date, @Param("waiting_date")String waiting_date);
}