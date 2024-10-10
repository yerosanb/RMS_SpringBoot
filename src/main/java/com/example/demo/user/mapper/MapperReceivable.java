package com.example.demo.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;

@Mapper
public interface MapperReceivable {
	@Select("select * from data_recivable where status = 1 and availability = 1 and dr_cr='CR'")
	List<File_rtgs__> get_credit_for_recon();

	@Select("select * from data_recivable where status = 1 and availability = 1 and dr_cr='DR'")
	List<File_rtgs__> get_debit_for_recon();

//	@Select("  select distinct drc.id," + "   drc.posting_date," + "   drc.transaction_reference,"
//			+ "   drc.branch_code," + "   drc.amount," + "   drc.dr_cr," + "   drc.upload_date,"
//			+ "   drc.match_status," + "   drc.status," + "   drc.availability," + "   drc.value_date,"
//			+ "   drc.additional_information," + "   drc.file_id"
//			+ "   from data_recivable drc, data_recivable drd,file_account fa where drc.dr_cr = 'CR' and drd.dr_cr = 'DR'"
//			+ "	  and drc.amount = drd.amount and drc.branch_code=drd.branch_code "
//			+ "	  and drc.file_id = fa.file_id and fa.account_id = #{account_id} "
//			+ "	  and drc.availability=1 and drc.status=1 and drc.match_status=0"
//			+ "	   and drd.availability=1 and drd.status=1 order by drc.amount asc")
//	List<File_rtgs__> get_receivable_credit_for_recon_auto(@Param("account_id") Long account_id);

	@Select("  select distinct drc.id," + "   drc.posting_date," + "   drc.transaction_reference,"
			+ "   drc.branch_code," + "   drc.amount," + "   drc.dr_cr," + "   drc.upload_date,"
			+ "   drc.match_status," + "   drc.status," + "   drc.availability," + "   drc.value_date,"
			+ "   drc.additional_information," + "   drc.file_id"
			+ "   from data_recivable drc, data_recivable drd where drc.dr_cr = 'CR' and drd.dr_cr = 'DR'"
			+ "	  and drc.amount = drd.amount and drc.branch_code=drd.branch_code "
			+ "	  and drc.availability=1 and drc.status=1"
			+ "	   and drd.availability=1 and drd.status=1 order by drc.amount asc")
	List<File_rtgs__> get_receivable_credit_for_recon_auto(@Param("account_id") Long account_id);

//	@Select("  select distinct drd.id," + "   drd.posting_date," + "   drd.transaction_reference,"
//			+ "   drd.branch_code," + "   drd.amount," + "   drd.dr_cr," + "   drd.upload_date,"
//			+ "   drd.match_status," + "   drd.status," + "   drd.availability," + "   drd.value_date,"
//			+ "   drd.additional_information," + "   drd.file_id"
//			+ " from data_recivable drd, data_recivable drc,file_account fa where drd.dr_cr = 'DR' and drc.dr_cr = 'CR' "
//			+ "	  and drc.amount = drd.amount and drc.branch_code=drd.branch_code "
//			+ "	  and drd.file_id = fa.file_id and fa.account_id = #{account_id} "
//			+ "	  and drd.availability=1 and drd.status=1 and drd.match_status=0"
//			+ "	  and drc.availability=1 and drc.status=1 order by drd.amount asc")
//	List<File_rtgs__> get_receivable_debit_for_recon_auto(@Param("account_id") Long account_id);

	@Select("  select distinct drd.id," + "   drd.posting_date," + "   drd.transaction_reference,"
			+ "   drd.branch_code," + "   drd.amount," + "   drd.dr_cr," + "   drd.upload_date,"
			+ "   drd.match_status," + "   drd.status," + "   drd.availability," + "   drd.value_date,"
			+ "   drd.additional_information," + "   drd.file_id"
			+ " from data_recivable drd, data_recivable drc where drd.dr_cr = 'DR' and drc.dr_cr = 'CR' "
			+ "	  and drc.amount = drd.amount and drc.branch_code=drd.branch_code "
			+ "	  and drd.availability=1 and drd.status=1 "
			+ "	  and drc.availability=1 and drc.status=1 order by drd.amount asc")
	List<File_rtgs__> get_receivable_debit_for_recon_auto(@Param("account_id") Long account_id);

	@Select("SELECT CASE WHEN EXISTS (" + " SELECT * FROM data_recivable where id = #{id} and dr_cr='CR')"
			+ " THEN 1 ELSE 0 END")
	int isCredit(@Param("id") Long long1);

	@Select("INSERT INTO receivable_credit(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, match_id, balance, ctr)OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{match_id}, balance, ctr  "
			+ "FROM data_recivable where id = #{id}; delete from data_recivable where id = #{id};")
	Long moveReceivableCredit(@Param("id") Long long1, @Param("match_id") String match_id);

	@Select("INSERT INTO receivable_debit(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, match_id, balance, ctr)OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{match_id}, balance, ctr  "
			+ "FROM data_recivable where id = #{id}; delete from data_recivable where id = #{id};")
	Long moveReceivableDebit(@Param("id") Long long1, @Param("match_id") String match_id);

	@Select("insert into receivable_matched (credit_id, match_id, match_date, reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
			+ "values(#{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many}, #{status}, #{availability})")
	Long addReceivableMatched(@Param("id") Long id, @Param("match_id") String match_id, @Param("date") Long date_now,
			@Param("recon_type") String recon_type, @Param("many_to_many") String many_to_many,
			@Param("status") String status, @Param("availability") String availability);

	@Insert("insert into user_receivable_matched (user_id, receivable_matched_id, date, status, availability, type) values "
			+ "(#{user_id}, #{rtgs_matched_id}, #{date},  #{status}, #{availability}, #{type})")
	void addUserReceivableMatched(@Param("user_id") Long user_id, @Param("rtgs_matched_id") Long RtgsMatchedId,
			@Param("date") Long date, @Param("status") String status, @Param("availability") String availability,
			@Param("type") String type);

	@Update("update edit_reason set current_id=#{current_id},match_data_id=#{match_data_id},type=#{type} where current_id=#{id}")
	boolean updateEditReason(@Param("current_id") Long current_id, @Param("match_data_id") Long matched_data_id,
			@Param("type") String type, @Param("id") Long long1);

	@Insert("insert into match_reason (match_id, reason, type, reference_amount, status, availability) values "
			+ " (#{match_id}, #{reason}, #{type}, #{reference_amount}, #{status}, #{availability});")
	void addMatchReason(@Param("match_id") String match_id, @Param("reason") String reason, @Param("type") String type,
			@Param("reference_amount") String reference_amount, @Param("status") String status,
			@Param("availability") String availability);

	@Select("insert into edit_reason (user_id, current_id, type, reason, date, status, availability, edit_delete)OUTPUT Inserted.Id values "
			+ "(#{user_id}, #{id},#{type}, #{reason}, #{date}, #{status}, #{availability}, #{edit_delete})")
	Long addReasonForEdit(@Param("id") Long id, @Param("user_id") Long user_id, @Param("type") String type,
			@Param("reason") String reason, @Param("date") String string, @Param("status") String status,
			@Param("availability") String availability, @Param("edit_delete") String edit_delete);

	@Select("INSERT INTO __edit_history(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{reason_id},'delete' FROM data_recivable where id = #{id}")
	void moveDeletedData(@Param("id") Long id, @Param("reason_id") Long reason_id);

	@Select("INSERT INTO __edit_history(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, edit_reason_id,new_old) "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{edit_reason_id},'old' FROM data_recivable where id = #{id}")
	void moveOldData(@Param("id") Long id, @Param("edit_reason_id") Long edit_reason_id);

	@Select("INSERT INTO __edit_history(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{edit_reason_id},'new' FROM data_recivable where id = #{id}")
	void moveEditedData(@Param("id") Long id, @Param("edit_reason_id") Long edit_reason_id);

	@Update("update data_recivable set amount =#{amount}, value_date = #{value_date_type}, "
			+ "dr_cr = #{dr_cr}, upload_date = #{upload_date}, additional_information = #{additional_information} where id = #{id}")
	Boolean updateTransaction(File_rtgs__ats edit_data);

	@Select("select rc.posting_date,rc.additional_information,rc.amount,rc.branch_code,rc.file_id,rc.match_status,rc.value_date,u.firstname,u.middlename,"
			+ "u.lastname,rm.match_date,rm.match_id,rc.id,rc.upload_date,rc.dr_cr,rc.transaction_reference from receivable_credit rc, users u,user_receivable_matched urm, receivable_matched rm where rc.status = 1 and rc.availability = 1 and rc.match_status = 1"
			+ " and rm.credit_id= rc.id  and u.id=urm.user_id and urm.receivable_matched_id=rm.id and urm.availability=1 and urm.status=1 and rm.match_date=#{match_date};")
	List<File_rtgs__> get_receivable_credit_matched(@Param("account_id") Long account_id,
			@Param("match_date") String match_date);

	@Select("select * from receivable_debit rd, users u,user_receivable_matched urm, receivable_matched rm where rd.status = 1 and rd.availability = 1 and rd.match_status = 1"
			+ " and rm.match_id= rd.match_id  and u.id=urm.user_id and urm.receivable_matched_id=rm.id and urm.availability=1 and urm.status=1 and rm.match_date=#{match_date};")
	List<File_rtgs__> get_receivable_debit_matched(@Param("account_id") Long account_id,
			@Param("match_date") String match_date);

	@Update("update data_recivable set availability = '0' where id = #{id}")
	void deleteTransaction(@Param("id") Long long1);

	@Select("select id from receivable_matched where credit_id = #{id}")
	Long getReceivableMatchedId(@Param("id") long id);

	@Update("update user_receivable_matched set availability=0 where receivable_matched_id = #{id}")
	void deleteUserReceivableMatched(@Param("id") Long id);

	@Update("update receivable_matched set availability=0 where credit_id=#{id}")
	public void deleteReceivableMatched(@Param("id") Long long1);

	@Select("INSERT INTO data_recivable(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, balance, ctr )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id, balance, ctr "
			+ "FROM receivable_debit where id = #{id}; update receivable_debit set availability = 0 where id = #{id};")
	Long moveReceivableDebitMatched(@Param("id") Long long1);

	@Select("INSERT INTO data_recivable(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, balance, ctr )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id, balance, ctr "
			+ "FROM receivable_credit where id = #{id}; update receivable_credit set availability = 0 where id = #{id};")
	Long moveReceivableCreditMatched(@Param("id") Long long1);

	@Select("		select  distinct rac.id, rac.posting_date, rac.transaction_reference, rac.branch_code,"
			+ "				rac.amount, rac.dr_cr, rac.upload_date, rac.match_status, rac.status, rac.availability,u.firstname,u.lastname,rm.match_date,"
			+ "					rac.value_date, rac.additional_information, rac.file_id, rm.match_id, mr.reason from receivable_credit rac, match_reason mr, "
			+ "					users u,user_receivable_matched urm,receivable_matched rm where rac.status = 1 and rac.availability = 1 and rac.match_status = 1"
			+ "					 and rm.credit_id=rac.id"
			+ "					 and urm.user_id=u.id and urm.receivable_matched_id=rm.id and urm.availability=1 and rm.match_id=mr.match_id and urm.status=1")
	List<File_rtgs__> get_receivable_credit_matched_with_reason(@Param("account_id") Long account_id);

	@Select("		select  distinct rac.id, rac.posting_date, rac.transaction_reference, rac.branch_code,"
			+ "				rac.amount, rac.dr_cr, rac.upload_date, rac.match_status, rac.status, rac.availability,u.firstname,u.lastname,rm.match_date,"
			+ "					rac.value_date, rac.additional_information, rac.file_id, rm.match_id, mr.reason from receivable_debit rac, match_reason mr, "
			+ "					users u ,user_receivable_matched urm, receivable_matched rm where rac.status = 1 and rac.availability = 1 and rac.match_status = 1"
			+ "					  and rm.match_id=rac.match_id"
			+ "					 and urm.user_id=u.id and urm.receivable_matched_id=rm.id and urm.availability=1 and rm.match_id=mr.match_id and urm.status=1")
	List<File_rtgs__> get_receivable_debit_matched_with_reason(@Param("account_id") Long account_id);

	@Select("select distinct  dp.value_date, dp.amount, dp.branch_code, dp.additional_information from data_recivable dp where upload_date <= #{date} and dr_cr = 'DR' and dp.status = 1 and dp.availability = 1 union "
			+ "select distinct pd.value_date, pd.amount, pd.branch_code, pd.additional_information from receivable_debit pd, receivable_matched pm, receivable_credit pc "
			+ "where pd.status = 1 and pd.availability = 1 and pm.status = 1 and pm.availability = 1 and pc.status = 1 and pc.availability = 1 and pd.dr_cr = 'DR' and pd.upload_date <= #{date} and ((pc.id = pm.credit_id and pd.match_id = pm.match_id and pc.upload_date >#{date})) ")
	List<File_rtgs__> reportReceivableDebit(@Param("date") String date);

	@Select("select distinct dp.value_date, dp.amount, dp.branch_code, dp.additional_information from data_recivable dp where dp.status=1 and dp.availability=1 and upload_date <= #{date} and dr_cr = 'CR' union "
			+ "select distinct pc.value_date, pc.amount, pc.branch_code, pc.additional_information from receivable_credit pc, receivable_matched pm, receivable_debit pd "
			+ "where  pc.status=1 and pc.availability=1 and pm.status=1 and pm.availability=1 and pd.status=1 and pd.availability=1 and  pc.dr_cr = 'CR' and pc.upload_date <= #{date} and ((pd.id = pm.credit_id and pc.match_id = pm.match_id and pd.upload_date > #{date})) ")
	List<File_rtgs__> reportReceivableCredit(@Param("date") String date);

	@Select("select ending_balance_ifb FROM files where upload_date = #{date} and type = 'Receivable' and status = 1 and availability = 1")
	Double getReceivableEndingbalance(@Param("date") String date);

	@Select("select ((select ISNULL(sum(dp.amount), 0) from data_recivable dp where dp.status=1 and dp.availability=1 and upload_date <= #{date} and dr_cr = 'DR')) "
			+ "+ ("
			+ "select (select ISNULL(sum(pd.amount), 0) from receivable_debit pd, receivable_matched pm, receivable_credit pc where "
			+ "pd.status=1 and pd.availability=1 and pm.status=1 and pm.availability=1 and pc.status=1 and pc.availability=1 and pd.dr_cr = 'DR' and pd.upload_date <= #{date} "
			+ "and ((pc.id = pm.credit_id and pd.match_id = pm.match_id and pc.upload_date > #{date})))) ")
	Double getTotalReceivableDebit(@Param("date") String date);

	@Select("select ((select ISNULL(sum(dp.amount), 0) from data_recivable dp where dp.status=1 and dp.availability=1 and upload_date <= #{date} and dr_cr = 'CR')) "
			+ "+ ("
			+ "select (select ISNULL(sum(pc.amount), 0) from receivable_credit pc, receivable_matched pm, receivable_debit pd where  pc.status=1 and pc.availability=1 and pm.status=1 and pm.availability=1 and pd.status=1 and pd.availability=1 and "
			+ "pc.dr_cr = 'CR' and pc.upload_date <= #{date} "
			+ "and ((pd.id = pm.credit_id and pc.match_id = pm.match_id and pd.upload_date > #{date}))))")
	Double getTotalReceivableCredit(@Param("date") String date);

	@Select("select type from files where id = #{id}")
	String getFileType(@Param("id") long id);

	@Select("select * from data_payable where id = #{id}")
	List<File_rtgs__> getPayableRawData(@Param("id") long id);

	@Select("select upload_date from files where id = #{id}")
	String getUploadDate(@Param("id") long id);

}
