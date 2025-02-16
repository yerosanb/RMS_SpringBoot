package com.example.demo.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.HttpStatus;

import com.example.demo.model.Comment;
import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;
import com.example.demo.user.model.MatchDetailAts;
import com.example.demo.user.model.MatchDetail;
import com.example.demo.user.model.Transactionhistory;

@Mapper
public interface MapperRTGS {

	@Select("select * from data__ats dna, file_account fa where dna.status = 1 and dna.availability = 1 and dna.match_status = 0 "
			+ "and dna.file_id = fa.file_id and fa.account_id = #{account_id} and dna.upload_date <= #{date};")
	List<File_rtgs__ats> get_ats_for_recon(@Param("date") int recon_date, @Param("account_id") Long account_id);

	@Select("select * from rtgs__ats rna, file_account fa, rtgs_matched rm, rtgs__ rac where rna.status = 1 and rna.availability = 1 and rna.match_status = 1 "
			+ "and rna.file_id = fa.file_id and fa.account_id = #{account_id} and rm.match_date = #{date} and rm.match_id=rac.match_id and rna.id=rm.rtgs__ats_id")
	List<File_rtgs__ats> get_ats_matched(@Param("date") String matched_date, @Param("account_id") Long account_id);

	@Select("select * from rtgs__ats rna, file_account fa, rtgs_partially_matched rpm, rtgs__ rac where rna.status = 1 and rna.availability = 1 and rna.match_status = 1 "
			+ "and rna.file_id = fa.file_id and fa.account_id = #{account_id} and rpm.match_date = #{date} and rpm.match_id=rac.match_id and rna.id=rpm.rtgs__ats_id")
	List<File_rtgs__ats> get_ats_partially_matched(@Param("date") String matched_date,
			@Param("account_id") Long account_id);

	@Select("select * from data__ dac, file_account fa where dac.status = 1 and dac.availability = 1 and dac.match_status = 0 "
			+ "and dac.file_id = fa.file_id and fa.account_id = #{account_id};")
	List<File_rtgs__> get__for_recon(@Param("account_id") Long account_id);

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Select("select * from rtgs__ats rna, file_account fa, users u,user_rgts_matched urm, rtgs_matched rm where rna.status = 1 and rna.availability = 1 and rna.match_status=1  "
			+ " and rna.file_id = fa.file_id and fa.account_id = #{account_id} and  rm.match_date = #{date} and rm.rtgs__ats_id = rna.id "
			+ "	and rm.availability=1 and rm.status=1 and u.id=urm.user_id and urm.rtgs_matched_partial_id=rm.id and urm.availability=1 and urm.status=1")
	List<File_rtgs__ats> get_ats_for_view(@Param("date") String recon_date, @Param("account_id") Long account_id);

	@Select("select * from rtgs__ rac, file_account fa,users u,user_rgts_matched urm, rtgs_matched rm where rac.status = 1 and rac.availability = 1 and rac.match_status = 1 "
			+ "and rac.file_id = fa.file_id and fa.account_id = #{account_id} and rm.match_id= rac.match_id and rm.match_date=#{date} and u.id=urm.user_id and urm.rtgs_matched_partial_id=rm.id and urm.availability=1 and urm.status=1;")
	List<File_rtgs__> get_rtgs__for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);
	@Select("select * from pas___reversal  where  match_date=#{date} and  availability=1 and status=1;")
	List<File_rtgs__> get_Reversal_for_view(@Param("date") String recon_date);
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// ============================================== payable matched transaction
	// start ======================================
	// @Select("select
	// pc.posting_date,pc.additional_information,pc.amount,pc.branch_code,pc.file_id,pc.match_status,pc.value_date,u.firstname,u.middlename,
	// "
	// +
	// "u.lastname,pm.match_date,pm.match_id,pc.id,pc.upload_date,pc.dr_cr,pc.transaction_reference
	// from payable_credit pc, file_account fa,users u,user_payable_matched upm,
	// payable_matched pm where pc.status = 1 and pc.availability = 1 and
	// pc.match_status = 1 "
	// + "and pc.file_id = fa.file_id and fa.account_id = #{account_id} and
	// pm.payable_credit_id= pc.id and u.id=upm.user_id and
	// upm.rtgs_matched_partial_id=pm.id and upm.availability=1 and upm.status=1;")
	// List<File_rtgs__> get_payable_credit_for_view(
	// @Param("account_id") Long account_id);
	//
	// @Select("select * from payable_debit pd, file_account fa,users
	// u,user_payable_matched upm, payable_matched pm where pd.status = 1 and
	// pd.availability = 1 and pd.match_status = 1 "
	// + "and pd.file_id = fa.file_id and fa.account_id = #{account_id} and
	// pm.match_id= pd.match_id and u.id=upm.user_id and
	// upm.rtgs_matched_partial_id=pm.id and upm.availability=1 and upm.status=1;")
	// List<File_rtgs__> get_payable_debit_for_view(
	// @Param("account_id") Long account_id);

	// =============================================== payable matched transaction
	// end ========================================

	// ============================================== payable matched transaction
	// start ======================================
//	@Select("select pc.posting_date,pc.additional_information,pc.amount,pc.branch_code,pc.file_id,pc.match_status,pc.value_date,u.firstname,u.middlename, "
//			+ "u.lastname,pm.match_date,pm.match_id,pc.id,pc.upload_date,pc.dr_cr,pc.transaction_reference from payable_credit pc, file_account fa,users u,user_payable_matched upm, payable_matched pm where pc.status = 1 and pc.availability = 1 and pc.match_status = 1 "
//			+ "and pc.file_id = fa.file_id and fa.account_id = #{account_id} and pm.payable_credit_id= pc.id  and u.id=upm.user_id and upm.rtgs_matched_partial_id=pm.id and upm.availability=1 and upm.status=1 and pm.match_date=#{match_date};")
//	List<File_rtgs__> get_payable_credit_for_view(@Param("account_id") Long account_id,
//			@Param("match_date") String match_date);

	@Select("select pc.posting_date, pc.additional_information, pc.amount, pc.branch_code,pc.file_id, pc.match_status,pc.value_date,u.firstname,u.middlename, "
			+ "u.lastname, pm.match_date, pm.match_id, pc.id, pc.upload_date, pc.dr_cr, pc.transaction_reference from payable_credit pc, users u, user_payable_matched upm, payable_matched pm where pc.status = 1 and pc.availability = 1 "
			+ "and pm.payable_credit_id= pc.id and u.id=upm.user_id and upm.rtgs_matched_partial_id=pm.id and  upm.availability=1 and upm.status=1 and pm.match_date = #{match_date};")
	List<File_rtgs__> get_payable_credit_for_view(
			@Param("match_date") String match_date);

//	@Select("select * from payable_debit pd, file_account fa,users u,user_payable_matched upm, payable_matched pm where pd.status = 1 and pd.availability = 1 and pd.match_status = 1 "
//			+ "and pd.file_id = fa.file_id and fa.account_id = #{account_id} and pm.match_id= pd.match_id  and u.id=upm.user_id and upm.rtgs_matched_partial_id=pm.id and upm.availability=1 and upm.status=1 and pm.match_date=#{match_date};")
//	List<File_rtgs__> get_payable_debit_for_view(@Param("account_id") Long account_id,
//			@Param("match_date") String match_date);
//
//	@Select("select * from payable_debit pd,users u,user_payable_matched upm, payable_matched pm where pd.status = 1 and pd.availability = 1 "
//			+ "and pm.match_id= pd.match_id  and u.id=upm.user_id and upm.rtgs_matched_partial_id=pm.id and upm.availability=1 and upm.status=1 and pm.match_date=#{match_date};")
//	List<File_rtgs__> get_payable_debit_for_view(
//			@Param("match_date") String match_date);

//	@Select("select * from payable_debit pd, user_payable_matched upm, payable_matched pm where pd.status = 1 and pd.availability = 1"
//			+ "and upm.rtgs_matched_partial_id=pm.id and upm.availability=1 and upm.status=1 and pm.match_date=#{match_date};")
//	List<File_rtgs__> get_payable_debit_for_view(@Param("match_date") String match_date);

	@Select("select * from payable_debit pd, users u,user_payable_matched upm, payable_matched pm where pd.status = 1 and pd.availability = 1 "
			+ "and pm.match_id= pd.match_id  and u.id=upm.user_id and upm.rtgs_matched_partial_id=pm.id and upm.availability=1 and upm.status=1 and pm.match_date=#{match_date};")
	List<File_rtgs__> get_payable_debit_for_view(@Param("match_date") String match_date);

	// =============================================== payable matched transaction
	// end ========================================

	@Select("select * from rtgs__ rac, file_account fa, rtgs_matched rm where rac.status = 1 and rac.availability = 1 and rac.match_status = 1"
			+ "and rac.file_id = fa.file_id and fa.account_id = #{account_id} and rm.match_date = #{date} and rm.match_id=rac.match_id;")
	List<File_rtgs__> get__matched(@Param("date") String matched_date, @Param("account_id") Long account_id);

	@Select("select * from rtgs__ rac, file_account fa where rac.status = 1 and rac.availability = 1 and rac.match_status = 1"
			+ "and rac.file_id = fa.file_id and fa.account_id = #{account_id};")
	List<File_rtgs__> get__partially_matched(@Param("account_id") Long account_id);

	@Update("update rtgs__ats set match_status = 1 where id = #{id}")
	void updateMatchStatus_ats(@Param("id") Long id);

	@Update("update rtgs__ats set match_status = 0 where id = #{id}")
	void updateUnMatchStatus_ats(@Param("id") Long id);

	@Update("update rtgs__ set match_status = 1, match_id = #{match_id} where id = #{id}")
	void updateMatchStatus_(@Param("id") Long id, @Param("match_id") String match_id);

	@Update("update rtgs__ set match_status = 0, match_id = null where id = #{id}")
	void updateUnMatchStatus_(@Param("id") Long id);

	@Update("update rtgs_matched set availability=0 where match_id = #{id}")
	void deleteMatchTransaction(@Param("id") String matched_id);

	@Update("update rtgs_partially_matched set availability=0 where match_id = #{id}")
	void deletepartialMatchTransaction(@Param("id") String matched_id);
	// @Select("select id from ")
	// Long getId();

	@Select("select match_id from rtgs__ where id=#{id}")
	String findMatchedId(@Param("id") Long id);
	// ================================================= not main

	// =================================================
	//////////////////////////////////// insert into payable matched
	// start//////////////////////////
	@Select("insert into payable_matched (payable_credit_id, match_id, match_date, reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
			+ "values(#{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many}, #{status}, #{availability})")
	Long addPayableMatched(@Param("id") Long id, @Param("match_id") String match_id, @Param("date") Long date_now,
			@Param("recon_type") String recon_type, @Param("many_to_many") String many_to_many,
			@Param("status") String status, @Param("availability") String availability);

	//////////////////////////////////// insert into payable matched
	//////////////////////////////////// ending//////////////////////////
	@Select("insert into rtgs_matched (rtgs__ats_id, match_id, match_date, reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
			+ "values(#{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many}, #{status}, #{availability})")
	Long addRtgsMatched(@Param("id") Long id, @Param("match_id") String match_id, @Param("date") Long date_now,
			@Param("recon_type") String recon_type, @Param("many_to_many") String many_to_many,
			@Param("status") String status, @Param("availability") String availability);

	@Select("insert into b2b_matched (b2b__ats_id, match_id, match_date, reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
			+ "values(#{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many}, #{status}, #{availability})")
	Long addB2bMatched(@Param("id") Long id, @Param("match_id") String match_id, @Param("date") Long date_now,
			@Param("recon_type") String recon_type, @Param("many_to_many") String many_to_many,
			@Param("status") String status, @Param("availability") String availability);

	@Select("insert into erca_matched (erca__ats_id, match_id, match_date, reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
			+ "values(#{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many}, #{status}, #{availability})")
	Long addErcaMatched(@Param("id") Long id, @Param("match_id") String match_id, @Param("date") Long date_now,
			@Param("recon_type") String recon_type, @Param("many_to_many") String many_to_many,
			@Param("status") String status, @Param("availability") String availability);

	@Select("insert into sos_matched (sos__ats_id, match_id, match_date, reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
			+ "values(#{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many}, #{status}, #{availability})")
	Long addSosMatched(@Param("id") Long id, @Param("match_id") String match_id, @Param("date") Long date_now,
			@Param("recon_type") String recon_type, @Param("many_to_many") String many_to_many,
			@Param("status") String status, @Param("availability") String availability);

	// ================================================= main

	// @Insert("insert into user_rgts_matched (user_id, rtgs_matched_partial_id,
	// date, status, availability) values "
	// + "(#{user_id}, #{rtgs_matched_id}, #{date}, #{status}, #{availability})")
	// void addUserRtgsMatched(@Param("user_id") Long user_id,
	// @Param("rtgs_matched_id") Long RtgsMatchedId,
	// @Param("date") Long date, @Param("status") String status,
	// @Param("availability") String availability);

	@Select("select id from rtgs_matched where match_id = #{id}")
	Long getRtgsMatchedPartialIdFromRtgsMatchedByMatchId(@Param("id") String matched_id);

	@Update("update user_rgts_matched set availability=0 where rtgs_matched_partial_id = #{id}")
	void deleteUserRtgsMatched(@Param("id") Long id);

	@Update("update user_erca_matched set availability=0 where erca_matched_id  = #{id}")
	void deleteUserErcaMatched(@Param("id") Long id);

	@Update("update user_b2b_matched set availability=0 where b2b_matched_partial_id = #{id}")
	void deleteUserBtbMatched(@Param("id") Long id);

	@Update("update user_sos_matched set availability=0 where sos_matched_id = #{id}")
	void deleteUserSosMatched(@Param("id") Long id);

	@Update("update user_parial_match set availability=0 where partial_matched_id = #{id}")
	void deleteUserPartialMatched(@Param("id") Long id);

	// ================================================= not main
	@Select("select id from partially_matched where ats_id = #{id}")
	Long getpartialMatchedId(@Param("id") long id);

	@Select("select id from rtgs_matched where rtgs__ats_id = #{id}")
	Long getrtgsMatchedId(@Param("id") long id);

	@Select("select id from b2b_matched where b2b__ats_id = #{id}")
	Long getb2bMatchedId(@Param("id") long id);

	@Select("select id from sos_matched where sos__ats_id = #{id}")
	Long getsosMatchedId(@Param("id") long id);

	@Select("select id from erca_matched where erca__ats_id = #{id}")
	Long getercaMatchedId(@Param("id") long id);

	// =================================================
	// ============================================= insert user payable matched
	// start =====================
	@Insert("insert into user_payable_matched (user_id, rtgs_matched_partial_id, date, status, availability, type) values "
			+ "(#{user_id}, #{payable_matched_id}, #{date},  #{status}, #{availability}, #{type})")
	void addUserPayableMatched(@Param("user_id") Long user_id, @Param("payable_matched_id") Long RtgsMatchedId,
			@Param("date") Long date, @Param("status") String status, @Param("availability") String availability,
			@Param("type") String type);

	// ============================================= insert user payable matched
	// ending =================
	@Insert("insert into user_rgts_matched (user_id, rtgs_matched_partial_id, date, status, availability, type) values "
			+ "(#{user_id}, #{rtgs_matched_id}, #{date},  #{status}, #{availability}, #{type})")
	void addUserRtgsMatched(@Param("user_id") Long user_id, @Param("rtgs_matched_id") Long RtgsMatchedId,
			@Param("date") Long date, @Param("status") String status, @Param("availability") String availability,
			@Param("type") String type);

	@Insert("insert into user_parial_match (user_id, partial_matched_id, status, availability) values "
			+ "(#{user_id}, #{partial_matched_id},#{status}, #{availability})")
	void addUserPartialMatched(@Param("user_id") Long user_id, @Param("partial_matched_id") Long partial_matched_id,
			@Param("status") String status, @Param("availability") String availability);

	@Insert("insert into user_b2b_matched (user_id, b2b_matched_partial_id, date, status, availability, type) values "
			+ "(#{user_id}, #{b2b_matched_id}, #{date}, #{status}, #{availability}, #{type})")
	void addUserB2bMatched(@Param("user_id") Long user_id, @Param("b2b_matched_id") Long b2bMatchedId,
			@Param("date") Long date, @Param("status") String status, @Param("availability") String availability,
			@Param("type") String type);

	@Insert("insert into user_erca_matched (user_id, erca_matched_id, date, status, availability, type) values "
			+ "(#{user_id}, #{erca_matched_id}, #{date}, #{status}, #{availability}, #{type})")
	void addUserErcaMatched(@Param("user_id") Long user_id, @Param("erca_matched_id") Long RtgsMatchedId,
			@Param("date") Long date, @Param("status") String status, @Param("availability") String availability,
			@Param("type") String type);

	@Insert("insert into user_sos_matched (user_id, sos_matched_id, date, status, availability, type) values "
			+ "(#{user_id}, #{erca_matched_id}, #{date}, #{status}, #{availability}, #{type})")
	void addUserSosMatched(@Param("user_id") Long user_id, @Param("erca_matched_id") Long RtgsMatchedId,
			@Param("date") Long date, @Param("status") String status, @Param("availability") String availability,
			@Param("type") String type);

	@Select("select distinct dna.id, dac.id as _id, dna.value_date_type, dna.sender, dna.receiver,"
			+ "	dna.additional_information, dna.amount, dna.dr_cr, dna.upload_date, dna.reference from data__ats dna, data__ dac, file_account fa where dac.additional_information like CONCAT('%', dna.reference, '%') "
			+ " and dac.amount =dna.amount and dac.dr_cr != dna.dr_cr and dna.status = 1 and dna.availability = 1 and dna.match_status = 0 and dna.file_id = fa.file_id and fa.account_id = #{account_id} "
			+ "and dna.upload_date <= #{date} and dac.status = 1 and dac.availability = 1 and dac.match_status = 0 and dac.upload_date <= #{date} and LEN(CAST(dna.reference AS VARCHAR))>3"
			+ "union "
			+ "select distinct dna.id, dac.id as _id, dna.value_date_type, dna.sender, dna.receiver, "
			+ "dna.additional_information, dna.amount, dna.dr_cr, dna.upload_date, dna.reference from data__ats dna, data__ dac, file_account fa where REPLACE(dna.additional_information, ' ', '')  like CONCAT('%', REPLACE(LEFT(dac.additional_information, CASE WHEN LEN(dac.additional_information) >= 4 THEN LEN(dac.additional_information) - 4 ELSE 0 END), ' ', ''), '%') "
			+ "and dac.amount =dna.amount and dac.dr_cr != dna.dr_cr and dna.status = 1 and dna.availability = 1 and dna.match_status = 0 and dna.file_id = fa.file_id and fa.account_id = #{account_id} "
			+ "and dna.upload_date <= #{date} and dac.status = 1 and dac.availability = 1 and dac.match_status = 0 and dac.upload_date <= #{date} and LEN(CAST(dna.reference AS VARCHAR))>3")
	List<File_rtgs__ats> get_ats_for_recon_auto(@Param("date") int recon_date, @Param("account_id") Long account_id);

	@Select("select dna.id, dac.id as _id, dna.value_date_type, dna.sender, dna.receiver,"
			+ "	dna.additional_information, dna.amount, dna.dr_cr, dna.upload_date, dna.reference from data__ats dna, data__ dac, file_account fa where dac.additional_information like CONCAT('%', dna.reference, '%') "
			+ " and dna.value_date_type like '%202' and dac.amount !=dna.amount and dna.status = 1 and dna.availability = 1 and dna.match_status = 0 and dna.file_id = fa.file_id and fa.account_id = #{account_id} "
			+ "and dna.upload_date <= #{date} and dac.status = 1 and dac.availability = 1 and dac.match_status = 0 and dac.upload_date <= #{date} ")
	List<File_rtgs__ats> get_ats_for_recon_partial_auto(@Param("date") int recon_date,
			@Param("account_id") Long account_id);

	@Select("select distinct dac.id, dac.posting_date, dac.transaction_reference, dac.branch_code,"
			+ "dac.amount, dac.dr_cr, dac.upload_date, dac.match_status, dac.status, dac.availability, dac.value_date, dac.additional_information, dac.file_id, dac.match_id"
			+ " from data__ dac, data__ats dna, file_account fa where dac.additional_information like CONCAT('%', dna.reference, '%') "
			+ " and dac.amount = dna.amount and dac.dr_cr != dna.dr_cr and dac.status = 1 and dac.availability = 1 and dac.match_status = 0 and dac.file_id = fa.file_id and fa.account_id = #{account_id} "
			+ " and dna.status = 1 and dna.availability = 1 and dna.match_status = 0 and dna.upload_date <= #{date} and dac.upload_date <= #{date} and LEN(CAST(dna.reference AS VARCHAR))>3"
			+ "union"
			+ "	select distinct dac.id, dac.posting_date, dac.transaction_reference, dac.branch_code, "
			+ " dac.amount, dac.dr_cr, dac.upload_date, dac.match_status, dac.status, dac.availability, dac.value_date, dac.additional_information, dac.file_id, dac.match_id "
			+ "	from data__ dac, data__ats dna, file_account fa where REPLACE(dna.additional_information, ' ', '') LIKE CONCAT('%', REPLACE(CASE WHEN LEN(dac.additional_information) >= 4 THEN LEFT(dac.additional_information, LEN(dac.additional_information) - 4) ELSE '' END, ' ', ''), '%') "
			+ " AND dac.amount = dna.amount "
			+ "	and dac.amount = dna.amount and dac.dr_cr != dna.dr_cr and dac.status = 1 and dac.availability = 1 and dac.match_status = 0 and dac.file_id = fa.file_id and fa.account_id =#{account_id}  "
			+ "	and dna.status = 1 and dna.availability = 1 and dna.match_status = 0 and dna.upload_date <= #{date} and dac.upload_date <= #{date} and LEN(CAST(dna.reference AS VARCHAR))>3")
	List<File_rtgs__> get__for_recon_auto(@Param("account_id") Long account_id,
			@Param("date") int recon_date);
	// ======================================================== get payable
	// transaction for automatic match starting =============================

//	@Select("  select distinct dpc.id," + "   dpc.posting_date, " + "   dpc.transaction_reference, "
//			+ "   dpc.branch_code, " + "   dpc.amount, " + "   dpc.dr_cr, " + "   dpc.upload_date, "
//			+ "   dpc.match_status, " + "   dpc.status, " + "   dpc.availability, " + "   dpc.value_date, "
//			+ "   dpc.additional_information, " + "   dpc.file_id "
//			+ "   from data_payable dpc, data_payable dpd,file_account fa where dpc.dr_cr = 'CR' and dpd.dr_cr = 'DR' "
//			+ "	  and dpc.amount = dpd.amount  "
//			+ "	  and dpd.additional_information like CONCAT('%', dpc.additional_information, '%') "
//			+ "	  and  dpc.file_id = fa.file_id and fa.account_id = #{account_id}  "
//			+ "	  and dpc.availability=1 and dpc.status=1 and dpc.match_status=0 "
//			+ "	   and dpd.availability=1 and dpd.status=1 order by dpc.amount asc")
//	List<File_rtgs__> get_payable_credit_for_recon_auto(@Param("account_id") Long account_id);

	@Select("	  select distinct dpc.id, dpc.posting_date, dpc.transaction_reference,  "
			+ "			  dpc.branch_code,    dpc.amount,    dpc.dr_cr,    dpc.upload_date,  "
			+ "			  dpc.match_status,    dpc.status,  dpc.availability,   dpc.value_date,  "
			+ "		      dpc.additional_information, dpc.file_id  "
			+ "		   from data_payable dpc, data_payable dpd "
			+ "			WHERE dpc.amount = dpd.amount and dpc.dr_cr = 'CR' and dpd.dr_cr = 'DR' and  NOT EXISTS (SELECT 1 FROM STRING_SPLIT(dpd.additional_information, ' ') AS word WHERE dpc.additional_information NOT LIKE '%' + word.value + '%') "
			+ "			  and dpd.availability=1 and dpd.status=1 "
			+ "			  and dpc.availability=1 and dpc.status=1 order by dpc.amount asc")
	List<File_rtgs__> get_payable_credit_for_recon_auto();

//
//	@Select("  select distinct dpd.id, "
//
//			+ "   dpd.posting_date, " + "   dpd.transaction_reference, " + "   dpd.branch_code, " + "   dpd.amount, "
//			+ "   dpd.dr_cr, " + "   dpd.upload_date, " + "   dpd.match_status, " + "   dpd.status, "
//			+ "   dpd.availability, " + "   dpd.value_date, " + "   dpd.additional_information, " + "   dpd.file_id "
//			+ " from data_payable dpd, data_payable dpc,file_account fa where dpd.dr_cr = 'DR' and dpc.dr_cr = 'CR'  "
//			+ "	  and dpc.amount = dpd.amount  "
//			+ "	  and dpd.additional_information like CONCAT('%', dpc.additional_information, '%') "
//			+ "	   and  dpd.file_id = fa.file_id and fa.account_id = #{account_id}  "
//			+ "	  and dpd.availability=1 and dpd.status=1 and dpd.match_status=0 "
//			+ "	  and dpc.availability=1 and dpc.status=1 order by dpd.amount asc")
//	List<File_rtgs__> get_payable_debit_for_recon_auto(@Param("account_id") Long account_id);


	@Select(" 			 select distinct dpd.id,  "
			+ "			   dpd.posting_date,  dpd.transaction_reference,  dpd.branch_code,   dpd.amount,  "
			+ "			  dpd.dr_cr,  dpd.upload_date,   dpd.match_status,  dpd.status,  "
			+ "			  dpd.availability, dpd.value_date,   dpd.additional_information,   dpd.file_id  "
			+ "			 from data_payable dpd, data_payable dpc where dpd.dr_cr = 'DR' and dpc.dr_cr = 'CR'   "
			+ "			  and dpc.amount = dpd.amount "
			+ "			  and  NOT EXISTS (SELECT 1 FROM STRING_SPLIT(dpd.additional_information, ' ') AS word WHERE dpc.additional_information NOT LIKE '%' + word.value + '%') "
			+ "			  and dpd.availability=1 and dpd.status=1 "
			+ "			  and dpc.availability=1 and dpc.status=1 order by dpd.amount asc")
	List<File_rtgs__> get_payable_debit_for_recon_auto();



	// ======================================================== get payable
	// transaction for automatic match ending=============================
	@Select("select dac.id, dac.posting_date, dac.transaction_reference, dac.branch_code,"
			+ "dac.amount, dac.dr_cr, dac.upload_date, dac.match_status, dac.status, dac.availability, dac.value_date, dac.additional_information, dac.file_id, dac.match_id"
			+ " from data__ dac, data__ats dna, file_account fa where dac.additional_information like CONCAT('%', dna.reference, '%') "
			+ " and dna.value_date_type like '%202' and dac.amount !=dna.amount and dac.status = 1 and dac.availability = 1 and dac.match_status = 0 and dac.file_id = fa.file_id and fa.account_id = #{account_id} "
			+ " and dna.status = 1 and dna.availability = 1 and dna.match_status = 0 and dna.upload_date <= #{date} and dac.upload_date <= #{date}")
	List<File_rtgs__> get__for_recon_partial_auto(@Param("account_id") Long account_id,
			@Param("date") int recon_date);

	@Select("select distinct cpm.id, cpm.posting_date, cpm.transaction_reference, cpm.branch_code, "
			+ "			 		cpm.amount, cpm.dr_cr, cpm.upload_date, cpm.match_status, cpm.status, cpm.availability, "
			+ "		 			cpm.value_date, cpm.additional_information, cpm.file_id, cpm.match_id "
			+ "			       from _partially_matched cpm  where cpm.availability =1 and cpm.status=1 union  "
			+ "select distinct dac.id, dac.posting_date, dac.transaction_reference, dac.branch_code, "
			+ "			 		dac.amount, dac.dr_cr, dac.upload_date, dac.match_status, dac.status, dac.availability, "
			+ "		 			dac.value_date, dac.additional_information, dac.file_id, dac.match_id "
			+ "				from data__ dac, file_account fa, "
			+ "				ats_partially_matched apm, partially_matched pm, _partially_matched cpm where  "
			+ "				cpm.match_id = pm.match_id and pm.ats_id = apm.id and  "
			+ "				dac.additional_information like concat('%', apm.reference, '%') and cpm.file_id = fa.file_id and fa.account_id =#{account_id} and "
			+ "				cpm.availability=1 and cpm.status=1 and pm.availability=1 and pm.status=1 and apm.availability=1 and apm.status=1 "
			+ "				and dac.availability=1 and dac.status=1")
	List<File_rtgs__> get__for_recon_partial(@Param("account_id") Long account_id);

	@Select("select distinct rna.id from data__ats rna, data__ rac, file_account fa where rna.amount = "
			+ "(select amount from data__ats rna where rna.id = #{ats_id}) and rna.file_id = fa.file_id"
			+ " and rna.status = 1 and rna.availability = 1 and rna.match_status = 0 and rna.reference = "
			+ "(select reference from data__ats rna where rna.id = #{ats_id}) and rna.upload_date = #{date}")
	Long[] getMatchedIdsFromAtsByAtsId(@Param("ats_id") Long ats_id, @Param("date") String recon_date);

	@Select("select distinct rna.id from data__ats rna, data__ rac, file_account fa where rna.amount = "
			+ "(select amount from data__ats rna where rna.id = #{ats_id}) and rna.file_id = fa.file_id"
			+ " and rna.status = 1 and rna.availability = 1 and rna.match_status = 0 and rna.reference = "
			+ "(select reference from data__ats rna where rna.id = #{ats_id}) and rna.upload_date = #{date}")
	String[] getTypeFromAtsByAtsId(@Param("ats_id") Long ats_id, @Param("date") String recon_date);

	@Select("select distinct rac.id from data__ats rna, data__ rac, file_account fa where rac.amount = "
			+ " (select amount from data__ats rna where rna.id = #{ats_id}) and"
			+ " rac.additional_information like CONCAT('%', (select reference from data__ats rna where rna.id = #{ats_id}), '%') and rac.status = 1 "
			+ " and rac.availability = 1 and rac.match_status = 0 and rac.file_id = fa.file_id"
			+ "  and rna.status = 1 and rna.availability = 1 and rna.match_status = 0 and rna.upload_date = #{date}")
	Long[] getMatchedIdsFromByAtsId(@Param("ats_id") Long ats_id, @Param("date") String recon_date);

	// @Update("update rtgs__ats set match_status = 2 where id = #{id}")
	// void updateMatchStatus_ats_partial(@Param("id") Long id);

	@Insert("insert into user_rgts_matched (user_id, rtgs_matched_partial_id, date, status, availability, type) values "
			+ "(#{user_id}, #{rtgs_matched_partial_id}, #{date}, #{status}, #{availability}, #{type})")
	void addUserRtgsMatched_partial(@Param("user_id") Long user_id,

			@Param("rtgs_matched_partial_id") Long RtgsMatchedId, @Param("date") Long date,
			@Param("status") String status, @Param("availability") String availability, @Param("type") String type);

	@Select("insert into rtgs_partially_matched (rtgs__ats_id, match_id, match_date, reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
			+ "values(#{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many}, #{status}, #{availability})")
	Long addRtgsMatched_partial(@Param("id") Long id, @Param("match_id") String match_id, @Param("date") Long date_now,
			@Param("recon_type") String recon_type, @Param("many_to_many") String many_to_many,
			@Param("status") String status, @Param("availability") String availability);

	// @Update("update rtgs__ set match_status = 2, match_id = #{match_id}
	// where id = #{id}")
	// void updateMatchStatus__partial(@Param("id") Long id, @Param("match_id")
	// String match_id);

	// ================================================= not main
	@Select("SELECT CASE WHEN EXISTS ("
			+ " SELECT * FROM data__ats where id = #{id} and RIGHT( value_date_type, 3 ) = '103' )"
			+ " THEN 1 ELSE 0 END")
	int isRtgs(@Param("id") Long long1);

	@Select("SELECT CASE WHEN EXISTS ("
			+ " SELECT * FROM data__ats where id = #{id} and RIGHT( value_date_type, 3 ) = '204' )"
			+ " THEN 1 ELSE 0 END")
	int isErca(@Param("id") Long long1);

	@Select("SELECT CASE WHEN EXISTS ("
			+ " SELECT * FROM data__ats where id = #{id} and RIGHT( value_date_type, 3 ) = '202' )"
			+ " THEN 1 ELSE 0 END")
	int isB2b(@Param("id") Long long1);

	@Select("SELECT CASE WHEN EXISTS ("
			+ " SELECT * FROM data__ats where id = #{id} and ((value_date_type like '%298%' and (additional_information like '%sid%' or additional_information like '%TTC%')) or RIGHT( value_date_type, 3 ) = '205'))"
			+ " THEN 1 ELSE 0 END")
	int isSos(@Param("id") Long long1);

	@Select("INSERT INTO rtgs__ats(value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, REFERENCE, file_id) OUTPUT Inserted.id SELECT value_date_type, sender, receiver, "
			+ "additional_information, amount, dr_cr, upload_date, '1', status, availability, REFERENCE, file_id FROM data__ats "
			+ "where id = #{id}; delete from data__ats where id = #{id};")
	Long moveRtgsAtsData(@Param("id") Long long1);

	//////////////////// insert into payable credit start //////////////////

	@Select("INSERT INTO payable_credit(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, ctr, balance) OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, ctr, balance FROM data_payable where id = #{id}; delete from data_payable where id = #{id}; ")
	Long movePayableCreditData(@Param("id") Long long1);

	////////////////// insert payable credit ending ////////////// ///////

	@Select("INSERT INTO erca__ats(value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, REFERENCE, file_id) OUTPUT Inserted.id SELECT value_date_type, sender, receiver, "
			+ "additional_information, amount, dr_cr, upload_date, '1', status, availability, REFERENCE, file_id FROM data__ats "
			+ "where id = #{id}; delete from data__ats where id = #{id};")
	Long moveErcaAtsData(@Param("id") Long long1);

	@Select("INSERT INTO sos__ats(value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, REFERENCE, file_id) OUTPUT Inserted.id SELECT value_date_type, sender, receiver, "
			+ "additional_information, amount, dr_cr, upload_date, '1', status, availability, REFERENCE, file_id FROM data__ats "
			+ "where id = #{id}; delete from data__ats where id = #{id};")
	Long moveSosAtsData(@Param("id") Long long1);

	@Select("INSERT INTO rtgs__(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, match_id )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{match_id} "
			+ "FROM data__ where id = #{id}; delete from data__ where id = #{id};")
	Long moveRtgsData(@Param("id") Long long1, @Param("match_id") String match_id);

	// ======================================== insert into payable debit start
	// ===============================
	@Select("INSERT INTO payable_debit(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, match_id, ctr, balance) OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{match_id}, ctr, balance "
			+ "FROM data_payable where id = #{id}; delete from data_payable where id = #{id};")
	Long movePayableDebitData(@Param("id") Long long1, @Param("match_id") String match_id);

	// ========================================= insert into payable debit ending
	// ===========================
	@Select("INSERT INTO sos__(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, match_id )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{match_id} "
			+ "FROM data__ where id = #{id}; delete from data__ where id = #{id};")
	Long moveSosData(@Param("id") Long long1, @Param("match_id") String match_id);

	@Select("INSERT INTO erca__(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, match_id )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{match_id} "
			+ "FROM data__ where id = #{id}; delete from data__ where id = #{id};")
	Long moveErcaData(@Param("id") Long long1, @Param("match_id") String match_id);

	@Select("INSERT INTO b2b__ats(value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, REFERENCE, file_id) OUTPUT Inserted.id SELECT value_date_type, sender, receiver, "
			+ "additional_information, amount, dr_cr, upload_date, '1', status, availability, REFERENCE, file_id FROM data__ats "
			+ "where id = #{id}; delete from data__ats where id = #{id};")
	Long moveB2bAtsData(@Param("id") Long long1);

	@Select("INSERT INTO b2b__(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, match_id )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{match_id} "
			+ "FROM data__ where id = #{id}; delete from data__ where id = #{id};")
	Long moveB2bData(@Param("id") Long long1, @Param("match_id") String match_id);
	@Select("INSERT INTO pas___reversal(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, match_id,match_date,firstname )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{match_id} ,#{matched_date},#{matched_by} "
			+ "FROM data__ where id = #{id}; delete from data__ where id = #{id};")
	Long moveReversalData(@Param("id") Long long1, @Param("match_id") String match_id, @Param("matched_date") Long matched_date, @Param("matched_by") String matched_by);
	// =================================================main
	@Select("select ending_balance_ats from files where availability=1 and type='ATS' and upload_date=#{lastUploadDate}")
	String check_openig_with_closing_balance_ats(@Param("lastUploadDate") String lastUploadDate);
	@Select("select count(*) from files  where type='ATS' and availability=1 ")
	int check_ats_for_firstly();
	@Select(" select  CONCAT(firstname, ' ', middlename) as fullname from users where id=#{id} ")
	String fullName(Long id);

	@Select("select ending_balance_con from files where availability=1 and type=''and upload_date=#{lastUploadDate}")
	String check_openig_with_closing_balance__con(@Param("lastUploadDate") String lastUploadDate);

	@Select("select ending_balance_ifb from files where availability=1 and type='' and upload_date=#{lastUploadDate}")
	String check_openig_with_closing_balance__ifb(@Param("lastUploadDate") String lastUploadDate);

	@Select("select ending_balance_ifb from files where availability=1 and type='ISSUE_' and upload_date=#{lastUploadDate}")
	String check_openig_with_closing_balance_issue_(@Param("lastUploadDate") String lastUploadDate);

	@Select("select ending_balance_ifb from files where availability=1 and type='ISSUE_QBS' and upload_date=#{lastUploadDate}")
	String check_openig_with_closing_balance_issue_qbs(@Param("lastUploadDate") String lastUploadDate);

	@Select("select ending_balance_ifb from files where availability=1 and type='Receivable' and upload_date=#{lastUploadDate}")
	String check_openig_with_closing_balance_receivable(@Param("lastUploadDate") String lastUploadDate);

	@Select("select ending_balance_ifb from files where availability=1 and type='Payable' and upload_date=#{lastUploadDate}")
	String check_openig_with_closing_balancepayable(@Param("lastUploadDate") String lastUploadDate);

	@Select("select count(*) from files  where type='' and availability=1   ")
	int check__for_firstly();

	@Select("select count(*) from files  where type='Receivable' and availability=1   ")
	int check_recievable_for_firstly();

	@Select("select count(*) from files where type='ISSUE_' and availability = 1")
	int check_issue__for_firstly();

	@Select("select count(*) from files where type='ISSUE_QBS' and availability = 1")
	int check_issue_qbs_for_firstly();

	@Insert("insert into match_reason (match_id, reason, type, reference_amount, status, availability) values "
			+ " (#{match_id}, #{reason}, #{type}, #{reference_amount}, #{status}, #{availability});")
	void addMatchReason(@Param("match_id") String match_id, @Param("reason") String reason, @Param("type") String type,
			@Param("reference_amount") String reference_amount, @Param("status") String status,
			@Param("availability") String availability);

	@Update("update data__ats set reference = #{reference}, amount =#{amount}, value_date_type = #{value_date_type}, "
			+ "dr_cr = #{dr_cr}, upload_date = #{upload_date}, additional_information = #{additional_information} where id = #{id}")
	Boolean updateTransaction(File_rtgs__ats edit_data);

	@Update("update data__ set amount =#{amount}, value_date = #{value_date_type}, "
			+ "dr_cr = #{dr_cr}, upload_date = #{upload_date}, additional_information = #{additional_information} where id = #{id}")
	Boolean updateTransaction(File_rtgs__ats edit_data);

	@Select("insert into edit_reason (user_id,current_id, type, reason, date, status, availability, edit_delete)OUTPUT Inserted.Id values "
			+ "(#{user_id},#{id}, #{type}, #{reason}, #{date}, #{status}, #{availability}, #{edit_delete})")
	Long addReasonForEdit(@Param("id") Long id, @Param("user_id") Long user_id, @Param("type") String type,
			@Param("reason") String reason, @Param("date") String string, @Param("status") String status,
			@Param("availability") String availability, @Param("edit_delete") String edit_delete);

	@Select("insert into edit_reason (user_id, current_id, type, reason, date, status, availability, edit_delete)OUTPUT Inserted.Id values "
			+ "(#{user_id}, #{id},#{type}, #{reason}, #{date}, #{status}, #{availability}, #{edit_delete})")
	Long addReasonForEdit(@Param("id") Long id, @Param("user_id") Long user_id, @Param("type") String type,
			@Param("reason") String reason, @Param("date") String string, @Param("status") String status,
			@Param("availability") String availability, @Param("edit_delete") String edit_delete);

	@Update("update data__ats set availability = '0' where id = #{id}")
	void deleteTransaction(@Param("id") Long id);

	@Update("update data__ set availability = '0' where id = #{id}")
	void deleteTransaction(@Param("id") Long long1);

	/////////////////////////////////////////////////////////////////////////////////

	@Select("insert into partially_matched (ats_id, match_id, match_date, reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
			+ "values(#{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many}, #{status}, #{availability})")
	Long addPartialMatched(@Param("id") Long id, @Param("match_id") String match_id, @Param("date") Long date_now,
			@Param("recon_type") String recon_type, @Param("many_to_many") String many_to_many,
			@Param("status") String status, @Param("availability") String availability);

	@Select("INSERT INTO ats_partially_matched(value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, REFERENCE, file_id) OUTPUT Inserted.id SELECT value_date_type, sender, receiver, "
			+ "additional_information, amount, dr_cr, upload_date, '1', status, availability, REFERENCE, file_id FROM data__ats "
			+ "where id = #{id}; delete from data__ats where id = #{id};")
	Long movAtsPartiallyMatched(@Param("id") Long long1);

	@Select("INSERT INTO _partially_matched(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, match_id )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{match_id} "
			+ "FROM data__ where id = #{id}; delete from data__ where id = #{id};")
	Long movePartiallyMatched(@Param("id") Long long1, @Param("match_id") String match_id);

	@Select("select distinct apm.id, apm.value_date_type, apm.sender, apm.receiver, "
			+ "                apm.additional_information, apm.amount, apm.dr_cr, apm.upload_date, apm.match_status, apm.status, "

			+ "                apm.availability, apm.reference,apm.file_id from ats_partially_matched apm where apm.availability =1 and apm.status=1 union  "
			+ "				 " + "select distinct dna.id, dna.value_date_type, dna.sender, dna.receiver, "
			+ "                dna.additional_information, dna.amount, dna.dr_cr, dna.upload_date, dna.match_status, dna.status, "
			+ "                dna.availability, dna.reference,dna.file_id from data__ats dna, "
			+ "				ats_partially_matched apm, partially_matched pm where "
			+ "                apm.additional_information like concat('%', dna.reference, '%') and dna.value_date_type like '%103' "
			+ "select distinct dna.id, dna.value_date_type, dna.sender, dna.receiver, "
			+ "               dna.additional_information, dna.amount, dna.dr_cr, dna.upload_date, dna.match_status, dna.status, "
			+ "               dna.availability, dna.reference,dna.file_id from  data__ats dna,partially_matched pm,file_account fa,ats_partially_matched atspm "
			+ "               where atspm.id = pm.ats_id and dna.reference = atspm.REFERENCE and atspm.file_id = fa.file_id and fa.account_id = #{account_id} and atspm.availability=1 and atspm.status=1 and  "
			+ "               pm.availability=1 and pm.status=1 and dna.availability=1 and dna.status=1 and atspm.match_status=1 ")
	List<File_rtgs__ats> get_ats_for_recon_partial(@Param("account_id") Long account_id);

	@Select("insert into comment (title, amount_difference, description, status, availability)" + "OUTPUT Inserted.id "
			+ "values (#{title}, #{amount_difference}, #{description},1, 1)")
	public Long addReason(Comment comment);

	@Insert("insert into partial_comment (comment_id, match_id, status, availability) values ( #{comment_id},"
			+ " #{match_id}, 1, 1)")
	public void addPartialComment(@Param("comment_id") Long comment_id, @Param("match_id") String match_id);

	@Select("INSERT INTO data__ats(value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, REFERENCE, file_id)OUTPUT Inserted.id SELECT value_date_type, sender, receiver, "
			+ "additional_information, amount, dr_cr, upload_date, '0', status, availability, REFERENCE, file_id FROM rtgs__ats "
			+ "where id = #{id}; update rtgs__ats set availability= 0 where id = #{id};")
	public Long moveRtgsAtsMatchedToData(@Param("id") Long long1);

	@Update("update rtgs_matched set availability=0 where rtgs__ats_id=#{id}")
	public void deleteRtgsMatched(@Param("id") Long long1);

	@Select("INSERT INTO data__(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id "
			+ "FROM rtgs__ where id = #{id}; update rtgs__ set availability = 0 where id = #{id};")
	Long moveRtgsMatched(@Param("id") Long long1);

	@Select("INSERT INTO data__(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id "
			+ "FROM pas___reversal where id = #{id}; update pas___reversal set availability = 0 where id = #{id};")
	Long moveReversalMatched(@Param("id") Long long1);

	// ====================================================== unmatch payable
	// transaction start ==============
	@Select("INSERT INTO data_payable(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, ctr, balance )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id, ctr, balance "
			+ "FROM payable_credit where id = #{id}; update payable_credit set availability = 0 where id = #{id};")
	Long movepayableCreditMatched(@Param("id") Long long1);

	@Update("update payable_matched set availability=0 where payable_credit_id=#{id}")
	public void deletePayableMatched(@Param("id") Long long1);

	@Select("INSERT INTO data_payable(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, ctr, balance )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id, ctr, balance "
			+ "FROM payable_debit where id = #{id}; update payable_debit set availability = 0 where id = #{id};")
	Long movepayableDebitMatched(@Param("id") Long long1);

	@Select("select id from payable_matched where payable_credit_id = #{id}")
	Long getPayableMatchedId(@Param("id") long id);

	@Update("update user_payable_matched set availability=0 where rtgs_matched_partial_id = #{id}")
	void deleteUserPayableMatched(@Param("id") Long id);
	// ======================================================== unmatch payable
	// transaction end =====================

	@Select("INSERT INTO b2b__ats(value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, REFERENCE, file_id) OUTPUT Inserted.id SELECT value_date_type, sender, receiver, "
			+ "additional_information, amount, dr_cr, upload_date, '1', status, availability, REFERENCE, file_id FROM ats_partially_matched "
			+ "where id = #{id}; update ats_partially_matched set status = '0' where id = #{id};")
	Long moveB2bAtsDataFromPartial(@Param("id") Long long1);

	@Select("INSERT INTO b2b__(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id, match_id )OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{match_id} "
			+ "FROM _partially_matched where id = #{id}; update _partially_matched set status = '0' where id = #{id};")
	Long moveB2bDataFromPartial(@Param("id") Long long1, @Param("match_id") String match_id);

	// @Select("select top 20 rna.upload_date as Id, rna.reference as Name,
	// rna.sender as Designation, rna.additional_information as Designation from
	// rtgs__ats rna")
	// List<Ee> getDataListForReportFake();

	// @Select("select top 20 rna.upload_date as ID, rna.reference as FIRSTNAME,
	// rna.sender as LASTNAME, rna.additional_information as STREET,
	// rna.value_date_type as CITY from rtgs__ats rna")
	// List<Ee> getDataListForReportFake();

	// @Select("select top 20 rna.upload_date as ID, rna.reference as FIRSTNAME,
	// rna.sender as LASTNAME, rna.additional_information as STREET from
	// rtgs__ats rna")
	// List<Ee> getDataListForReportFake();

	@Select("select ending_balance_ats FROM files where upload_date = #{date} and type = 'ATS' and status = 1 and availability = 1")
	Double getAtsEndingbalance(@Param("date") String date);

	@Select("select \r\n" + //
				"\tdistinct dac.id, dac.amount, dac.branch_code, dac.additional_information, dac.value_date from data__ dac where upload_date <= #{date} and dr_cr = 'DR' and dac.status = 1 and dac.availability = 1 \r\n" + //
				"union \r\n" + //
				"select \r\n" + //
				"\tdistinct dac.id, dac.amount, dac.branch_code, dac.additional_information, dac.value_date from _partially_matched dac where upload_date <= #{date} and dr_cr = 'DR' and dac.status = 1 and dac.availability = 1 \r\n" + //
				"union \r\n" + //
				"select \r\n" + //
				"\tdistinct rac.id, rac.amount, rac.branch_code, rac.additional_information, rac.value_date from rtgs__ rac, rtgs_matched rm, rtgs__ats rna \r\n" + //
				"\t\twhere rac.dr_cr = 'DR' and rac.upload_date <= #{date} and (rna.id = rm.rtgs__ats_id and rac.match_id = rm.match_id and rna.upload_date > #{date}) and rac.status = 1 and rac.availability = 1  \r\n" + //
				"\t\tand rm.status = 1 and rm.availability = 1  and rna.status = 1 and rna.availability = 1  \r\n" + //
				"union \r\n" + //
				"select \r\n" + //
				"\tdistinct eac.id, eac.amount, eac.branch_code, eac.additional_information, eac.value_date from erca__ eac, erca_matched em, erca__ats ena \r\n" + //
				"\t\twhere eac.dr_cr = 'DR' and eac.upload_date <= #{date} and ((ena.id = em.erca__ats_id and eac.match_id = em.match_id and ena.upload_date > #{date})) and eac.status = 1 and eac.availability = 1  \r\n" + //
				"\t\tand em.status = 1 and em.availability = 1  and ena.status = 1 and ena.availability = 1\r\n" + //
				"union \r\n" + //
				"select \r\n" + //
				"\tdistinct sac.id, sac.amount, sac.branch_code, sac.additional_information, sac.value_date from sos__ sac, sos_matched sm, sos__ats sna \r\n" + //
				"\t\twhere sac.dr_cr = 'DR' and sac.upload_date <= #{date} and ((sna.id = sm.sos__ats_id and sac.match_id = sm.match_id and sna.upload_date > #{date})) and sac.additional_information != 'ECX Inter' and sac.status = 1 and sac.availability = 1  \r\n" + //
				"\t\tand sm.status = 1 and sm.availability = 1  and sna.status = 1 and sna.availability = 1 \r\n" + //
				"union \r\n" + //
				"select \r\n" + //
				"\tdistinct sac.id, sac.amount, sac.branch_code, sac.additional_information, sac.value_date from sos__ sac, sos_matched sm, sos__ats sna \r\n" + //
				"\t\twhere sac.dr_cr = 'DR' and sac.upload_date <= #{date} and ((sna.id = sm.sos__ats_id and sac.match_id = sm.match_id and sna.upload_date >= #{date})) and sac.additional_information = 'ECX Inter' and sac.upload_date < sna.upload_date and sac.status = 1 and sac.availability = 1  \r\n" + //
				"\t\tand sm.status = 1 and sm.availability = 1  and sna.status = 1 and sna.availability = 1 union \r\n" + //
				"select \r\n" + //
				"\tdistinct bac.id, bac.amount, bac.branch_code, bac.additional_information, bac.value_date from b2b__ bac, b2b_matched bm, b2b__ats bna \r\n" + //
				"\t\twhere bac.dr_cr = 'DR' and bac.upload_date <= #{date} and ((bna.id = bm.b2b__ats_id and bac.match_id = bm.match_id and bna.upload_date > #{date})) and bac.status = 1 and bac.availability = 1  \r\n" + //
				"\t\tand bm.status = 1 and bm.availability = 1  and bna.status = 1 and bna.availability = 1\r\n" + //
				"union\r\n" + //
				"select \r\n" + //
				"\tdistinct pacr1.id, pacr1.amount, pacr1.branch_code, pacr1.additional_information, pacr1.value_date from pas___reversal pacr1, pas___reversal pacr2 \r\n" + //
				"\t\twhere pacr1.dr_cr = 'DR' AND pacr1.upload_date <= #{date} AND (pacr1.match_id = pacr2.match_id AND pacr2.dr_cr = 'CR' AND pacr2.upload_date > #{date} AND pacr1.amount = pacr2.amount)\r\n" + //
				"\t\tAND pacr1.status = 1 AND pacr1.availability = 1 AND pacr2.status = 1 AND pacr2.availability = 1;")
	List<File_rtgs__> reportDebit(@Param("date") String date);

	// 	@Select("select distinct dac.id, dac.amount, dac.branch_code, dac.additional_information, dac.value_date from data__ dac where upload_date <= #{date} and dr_cr = 'DR' and dac.status = 1 and dac.availability = 1 union "
	// 		+ "select distinct dac.id, dac.amount, dac.branch_code, dac.additional_information, dac.value_date from _partially_matched dac where upload_date <= #{date} and dr_cr = 'DR' and dac.status = 1 and dac.availability = 1 union "
	// 		+ "select distinct rac.id, rac.amount, rac.branch_code, rac.additional_information, rac.value_date from rtgs__ rac, rtgs_matched rm, rtgs__ats rna "
	// 		+ "where rac.dr_cr = 'DR' and rac.upload_date <= #{date} and (rna.id = rm.rtgs__ats_id and rac.match_id = rm.match_id and rna.upload_date > #{date}) and rac.status = 1 and rac.availability = 1  "
	// 		+ " and rm.status = 1 and rm.availability = 1  and rna.status = 1 and rna.availability = 1  union "
	// 		+ "select distinct eac.id, eac.amount, eac.branch_code, eac.additional_information, eac.value_date from erca__ eac, erca_matched em, erca__ats ena "
	// 		+ "where eac.dr_cr = 'DR' and eac.upload_date <= #{date} and ((ena.id = em.erca__ats_id and eac.match_id = em.match_id and ena.upload_date > #{date})) and eac.status = 1 and eac.availability = 1  "
	// 		+ " and em.status = 1 and em.availability = 1  and ena.status = 1 and ena.availability = 1 union "
	// 		+ "select distinct sac.id, sac.amount, sac.branch_code, sac.additional_information, sac.value_date from sos__ sac, sos_matched sm, sos__ats sna "
	// 		+ "where sac.dr_cr = 'DR' and sac.upload_date <= #{date} and ((sna.id = sm.sos__ats_id and sac.match_id = sm.match_id and sna.upload_date > #{date}))and sac.additional_information != 'ECX Inter' and sac.status = 1 and sac.availability = 1  "
	// 		+ " and sm.status = 1 and sm.availability = 1  and sna.status = 1 and sna.availability = 1 union "
	// 		+ "select distinct sac.id, sac.amount, sac.branch_code, sac.additional_information, sac.value_date from sos__ sac, sos_matched sm, sos__ats sna "
	// 		+ "where sac.dr_cr = 'DR' and sac.upload_date <= #{date} and ((sna.id = sm.sos__ats_id and sac.match_id = sm.match_id and sna.upload_date > #{date})) and sac.additional_information = 'ECX Inter' and sac.upload_date < sna.upload_date and sac.status = 1 and sac.availability = 1  "
	// 		+ " and sm.status = 1 and sm.availability = 1  and sna.status = 1 and sna.availability = 1 union "
	// 		+ "select distinct bac.id, bac.amount, bac.branch_code, bac.additional_information, bac.value_date from b2b__ bac, b2b_matched bm, b2b__ats bna "
	// 		+ "where bac.dr_cr = 'DR' and bac.upload_date <= #{date} and ((bna.id = bm.b2b__ats_id and bac.match_id = bm.match_id and bna.upload_date > #{date})) and bac.status = 1 and bac.availability = 1  "
	// 		+ " and bm.status = 1 and bm.availability = 1  and bna.status = 1 and bna.availability = 1")
	// List<File_rtgs__> reportDebit(@Param("date") String date);

	// payable report query begin
	@Select("select distinct  dp.value_date, dp.amount, dp.branch_code, dp.additional_information from data_payable dp where upload_date <= #{date} and dr_cr = 'DR' and dp.status = 1 and dp.availability = 1 union "
			+ "select distinct pd.value_date, pd.amount, pd.branch_code, pd.additional_information from payable_debit pd, payable_matched pm, payable_credit pc "
			+ "where pd.status = 1 and pd.availability = 1 and pm.status = 1 and pm.availability = 1 and pc.status = 1 and pc.availability = 1 and pd.dr_cr = 'DR' and pd.upload_date <= #{date} and ((pc.id = pm.payable_credit_id and pd.match_id = pm.match_id and pc.upload_date >#{date})) ")
	List<File_rtgs__> reportPayableDebit(@Param("date") String date);

	@Select("select distinct dp.value_date, dp.amount, dp.branch_code, dp.additional_information from data_payable dp where dp.status=1 and dp.availability=1 and upload_date <= #{date} and dr_cr = 'CR' union "
			+ "select distinct pc.value_date, pc.amount, pc.branch_code, pc.additional_information from payable_credit pc, payable_matched pm, payable_debit pd "
			+ "where  pc.status=1 and pc.availability=1 and pm.status=1 and pm.availability=1 and pd.status=1 and pd.availability=1 and  pc.dr_cr = 'CR' and pc.upload_date <= #{date} and ((pd.id = pm.payable_credit_id and pc.match_id = pm.match_id and pd.upload_date > #{date})) ")
	List<File_rtgs__> reportPayableCredit(@Param("date") String date);

//	@Select("select dp.value_date, dp.dr_cr,dp.amount, dp.posting_date, dp.transaction_reference, dp.branch_code,   "
//			+ "dp.additional_information from data_payable dp where dp.status=1 and dp.availability=1 and upload_date =  #{date} "
//			+ " union  "
//			+ "select pc.value_date, pc.dr_cr, pc.amount, pc.posting_date, pc.transaction_reference, pc.branch_code,  "
//			+ "pc.additional_information from payable_credit pc where  pc.status=1 and pc.availability=1 and   "
//			+ "pc.upload_date =  #{date} "
//			+ " union  "
//			+ "select pd.value_date,pd.dr_cr, pd.amount, pd.posting_date, pd.transaction_reference, pd.branch_code,   "
//			+ "pd.additional_information from payable_debit pd where pd.status=1 and pd.availability=1  "
//			+ "and pd.upload_date =  #{date}"
//			+ "")
//	List<File_rtgs__> PayableRawData(@Param("date") String date);




	@Select("select ending_balance_ifb FROM files where upload_date = #{date} and type = 'Payable' and status = 1 and availability = 1")
	Double getPayableEndingbalance(@Param("date") String date);

	@Select("select ((select ISNULL(sum(dp.amount), 0) from data_payable dp where dp.status=1 and dp.availability=1 and upload_date <= #{date} and dr_cr = 'DR')) "
			+ "+ ("
			+ "select (select ISNULL(sum(pd.amount), 0) from payable_debit pd, payable_matched pm, payable_credit pc where "
			+ "pd.status=1 and pd.availability=1 and pm.status=1 and pm.availability=1 and pc.status=1 and pc.availability=1 and pd.dr_cr = 'DR' and pd.upload_date <= #{date} "
			+ "and ((pc.id = pm.payable_credit_id and pd.match_id = pm.match_id and pc.upload_date > #{date})))) ")
	Double getTotalPayableDebit(@Param("date") String date);

	@Select("select ((select ISNULL(sum(dp.amount), 0) from data_payable dp where dp.status=1 and dp.availability=1 and upload_date <= #{date} and dr_cr = 'CR')) "
			+ "+ ("
			+ "select (select ISNULL(sum(pc.amount), 0) from payable_credit pc, payable_matched pm, payable_debit pd where  pc.status=1 and pc.availability=1 and pm.status=1 and pm.availability=1 and pd.status=1 and pd.availability=1 and "
			+ "pc.dr_cr = 'CR' and pc.upload_date <= #{date} "
			+ "and ((pd.id = pm.payable_credit_id and pc.match_id = pm.match_id and pd.upload_date > #{date}))))")
	Double getTotalPayableCredit(@Param("date") String date);

	// payable report end

	// @Select("SELECT (\r\n" + //
	// 			"    (SELECT ISNULL(SUM(dac.amount), 0) FROM data__ dac WHERE upload_date <= #{date} AND dr_cr = 'DR' AND dac.status = 1 AND dac.availability = 1) +\r\n" + //
	// 			"    (SELECT ISNULL(SUM(dac.amount), 0) FROM _partially_matched dac WHERE upload_date <= #{date} AND dr_cr = 'DR' AND dac.status = 1 AND dac.availability = 1) +\r\n" + //
	// 			"    (\r\n" + //
	// 			"        SELECT ISNULL(SUM(rac.amount), 0) FROM rtgs__ rac, rtgs_matched rm, rtgs__ats rna\r\n" + //
	// 			"        WHERE rac.dr_cr = 'DR' AND rac.upload_date <= #{date} AND ((rna.id = rm.rtgs__ats_id AND rac.match_id = rm.match_id AND rna.upload_date > #{date}))\r\n" + //
	// 			"        AND rac.status = 1 AND rac.availability = 1 AND rm.status = 1 AND rm.availability = 1 AND rna.status = 1 AND rna.availability = 1\r\n" + //
	// 			"    ) +\r\n" + //
	// 			"    (\r\n" + //
	// 			"        SELECT ISNULL(SUM(eac.amount), 0) FROM erca__ eac, erca_matched em, erca__ats ena\r\n" + //
	// 			"        WHERE eac.dr_cr = 'DR' AND eac.upload_date <= #{date} AND ((ena.id = em.erca__ats_id AND eac.match_id = em.match_id AND ena.upload_date > #{date}))\r\n" + //
	// 			"        AND eac.status = 1 AND eac.availability = 1 AND em.status = 1 AND em.availability = 1 AND ena.status = 1 AND ena.availability = 1\r\n" + //
	// 			"    ) +\r\n" + //
	// 			"    (\r\n" + //
	// 			"        SELECT ISNULL(SUM(sac.amount), 0) FROM sos__ sac, sos_matched sm, sos__ats sna\r\n" + //
	// 			"        WHERE sac.dr_cr = 'DR' AND sac.upload_date <= #{date} AND ((sna.id = sm.sos__ats_id AND sac.match_id = sm.match_id AND sna.upload_date >= #{date}))\r\n" + //
	// 			"        AND sac.additional_information != 'ECX Inter' AND sac.status = 1 AND sac.availability = 1 AND sm.status = 1 AND sm.availability = 1 AND sna.status = 1 AND sna.availability = 1\r\n" + //
	// 			"    ) +\r\n" + //
	// 			"    (\r\n" + //
	// 			"        SELECT ISNULL(SUM(sac.amount), 0) FROM sos__ sac, sos_matched sm, sos__ats sna\r\n" + //
	// 			"        WHERE sac.dr_cr = 'DR' AND sac.upload_date <= #{date} AND ((sna.id = sm.sos__ats_id AND sac.match_id = sm.match_id AND sna.upload_date >= #{date}))\r\n" + //
	// 			"        AND sac.additional_information = 'ECX Inter' AND sna.upload_date > sac.upload_date AND sac.status = 1 AND sac.availability = 1 AND sm.status = 1 AND sm.availability = 1 AND sna.status = 1 AND sna.availability = 1\r\n" + //
	// 			"    ) +\r\n" + //
	// 			"    (\r\n" + //
	// 			"        SELECT ISNULL(SUM(bac.amount), 0) FROM b2b__ bac, b2b_matched bm, b2b__ats bna\r\n" + //
	// 			"        WHERE bac.dr_cr = 'DR' AND bac.upload_date <= #{date} AND ((bna.id = bm.b2b__ats_id AND bac.match_id = bm.match_id AND bna.upload_date > #{date})) \r\n" + //
	// 			"\t\tAND bac.status = 1 AND bac.availability = 1 AND bm.status = 1 AND bm.availability = 1 AND bna.status = 1 AND bna.availability = 1\r\n" + //
	// 			"    ) +\r\n" + //
	// 			"    (\r\n" + //
	// 			"       select ISNULL(SUM(pacr1.amount), 0) from pas___reversal pacr1, pas___reversal pacr2 \r\n" + //
	// 			"\t\twhere pacr1.dr_cr = 'DR' AND pacr1.upload_date <= #{date} AND (pacr1.match_id = pacr2.match_id AND pacr2.dr_cr = 'CR' AND pacr2.upload_date > #{date} \r\n" + //
	// 			"\t\tAND pacr1.amount = pacr2.amount) AND pacr1.status = 1 AND pacr1.availability = 1 AND pacr2.status = 1 AND pacr2.availability = 1\r\n" + //
	// 			"    )\r\n" + //
	// 			") AS total_amount;")
	// Double getTotalDebit(@Param("date") String string);
	@Select("WITH CombinedResult AS (\r\n" + //
				"\tSELECT sac.* FROM sos__ sac, sos_matched sm, sos__ats sna\r\n" + //
				"\tWHERE sac.dr_cr = 'DR' AND sac.upload_date <= #{date} AND ((sna.id = sm.sos__ats_id AND sac.match_id = sm.match_id AND sna.upload_date > #{date}))\r\n" + //
				"\tAND sac.additional_information != 'ECX Inter' AND sac.status = 1 AND sac.availability = 1 AND sm.status = 1 AND sm.availability = 1 AND sna.status = 1 AND sna.availability = 1\r\n" + //
				"\tunion\r\n" + //
				"\tselect sac.* from sos__ sac, sos__ sac2 where sac.dr_cr = 'DR'  AND sac.upload_date <= #{date} and sac2.upload_date > #{date} and sac.match_id = sac2.match_id\r\n" + //
				"\tand sac.status = 1 and sac.availability = 1 and sac2.status = 1 and sac2.availability = 1 and sac.additional_information != 'ECX Inter' and sac2.additional_information != 'ECX Inter'\r\n" + //
				")\r\n" + //
				"SELECT (\r\n" + //
				"\t\t(SELECT ISNULL(SUM(dac.amount), 0) FROM data__ dac WHERE upload_date <= #{date} AND dr_cr = 'DR' AND dac.status = 1 AND dac.availability = 1) +\r\n" + //
				"\t\t(SELECT ISNULL(SUM(dac.amount), 0) FROM _partially_matched dac WHERE upload_date <= #{date} AND dr_cr = 'DR' AND dac.status = 1 AND dac.availability = 1) +\r\n" + //
				"\t\t(\r\n" + //
				"\t\t\tSELECT ISNULL(SUM(rac.amount), 0) FROM rtgs__ rac, rtgs_matched rm, rtgs__ats rna\r\n" + //
				"\t\t\tWHERE rac.dr_cr = 'DR' AND rac.upload_date <= #{date} AND ((rna.id = rm.rtgs__ats_id AND rac.match_id = rm.match_id AND rna.upload_date > #{date}))\r\n" + //
				"\t\t\tAND rac.status = 1 AND rac.availability = 1 AND rm.status = 1 AND rm.availability = 1 AND rna.status = 1 AND rna.availability = 1\r\n" + //
				"\t\t) +\r\n" + //
				"\t\t(\r\n" + //
				"\t\t\tSELECT ISNULL(SUM(eac.amount), 0) FROM erca__ eac, erca_matched em, erca__ats ena\r\n" + //
				"\t\t\tWHERE eac.dr_cr = 'DR' AND eac.upload_date <= #{date} AND ((ena.id = em.erca__ats_id AND eac.match_id = em.match_id AND ena.upload_date > #{date}))\r\n" + //
				"\t\t\tAND eac.status = 1 AND eac.availability = 1 AND em.status = 1 AND em.availability = 1 AND ena.status = 1 AND ena.availability = 1\r\n" + //
				"\t\t) +\r\n" + //
				"\t\t(\r\n" + //
				"\t\t\tselect  ISNULL(SUM(amount), 0) from CombinedResult\t\r\n" + //
				"\t\t) +\r\n" + //
				"\t\t(\r\n" + //
				"\t\t\tSELECT ISNULL(SUM(sac.amount), 0) FROM sos__ sac, sos_matched sm, sos__ats sna\r\n" + //
				"\t\t\tWHERE sac.dr_cr = 'DR' AND sac.upload_date <= #{date} AND ((sna.id = sm.sos__ats_id AND sac.match_id = sm.match_id AND sna.upload_date >= #{date}))\r\n" + //
				"\t\t\tAND sac.additional_information = 'ECX Inter' AND sna.upload_date > sac.upload_date AND sac.status = 1 AND sac.availability = 1 AND sm.status = 1 AND sm.availability = 1 AND sna.status = 1 AND sna.availability = 1\r\n" + //
				"\t\t) +\r\n" + //
				"\t\t(\r\n" + //
				"\t\t\tSELECT ISNULL(SUM(bac.amount), 0) FROM b2b__ bac, b2b_matched bm, b2b__ats bna\r\n" + //
				"\t\t\tWHERE bac.dr_cr = 'DR' AND bac.upload_date <= #{date} AND ((bna.id = bm.b2b__ats_id AND bac.match_id = bm.match_id AND bna.upload_date > #{date})) \r\n" + //
				"\tAND bac.status = 1 AND bac.availability = 1 AND bm.status = 1 AND bm.availability = 1 AND bna.status = 1 AND bna.availability = 1\r\n" + //
				"\t\t) +\r\n" + //
				"\t\t(\r\n" + //
				"\t\t\tSELECT ISNULL(SUM(pacr1.amount), 0) AS TotalAmount\r\n"
				+ "FROM pas___reversal pacr1\r\n"
				+ "INNER JOIN (\r\n"
				+ "SELECT DISTINCT match_id, status ,availability\r\n"
				+ "FROM pas___reversal\r\n"
				+ "WHERE upload_date > #{date}\r\n"
				+ ") AS pacr2_filtered ON pacr1.match_id = pacr2_filtered.match_id\r\n"
				+ "WHERE pacr1.dr_cr = 'DR'\r\n"
				+ "AND pacr1.upload_date <= #{date}\r\n"
				+ "AND pacr1.status = 1\r\n"
				+ "AND pacr1.availability = 1 AND pacr2_filtered.status=1 AND pacr2_filtered.availability=1\r\n" + //
				"\t\t)\r\n" + //
				") AS total_amount;")
	Double getTotalDebit(@Param("date") String string);

	// @Select("select ((select ISNULL(sum(dac.amount), 0) from data__ dac where upload_date <= #{date} and dr_cr = 'DR' and dac.status = 1 and dac.availability = 1 ) "
	// 		+ " + (select ISNULL(sum(dac.amount), 0) from _partially_matched dac where upload_date <= #{date} and dr_cr = 'DR' and dac.status = 1 and dac.availability = 1 ) "
	// 		+ " + ( "
	// 		+ " select ISNULL(sum(rac.amount), 0) from rtgs__ rac, rtgs_matched rm, rtgs__ats rna where rac.dr_cr = 'DR' and rac.upload_date <= #{date}  "
	// 		+ " and ((rna.id = rm.rtgs__ats_id and rac.match_id = rm.match_id and rna.upload_date > #{date})) and rac.status = 1 and rac.availability = 1  "
	// 		+ "  and rm.status = 1 and rm.availability = 1  and rna.status = 1 and rna.availability = 1) " + " + ( "
	// 		+ " select ISNULL(sum(eac.amount), 0) from erca__ eac, erca_matched em, erca__ats ena where eac.dr_cr = 'DR' and eac.upload_date <= #{date} "
	// 		+ "  and ((ena.id = em.erca__ats_id and eac.match_id = em.match_id and ena.upload_date > #{date})) and eac.status = 1 and eac.availability = 1  "
	// 		+ "  and em.status = 1 and em.availability = 1  and ena.status = 1 and ena.availability = 1 ) " + " + ( "
	// 		+ " select ISNULL(sum(sac.amount), 0) from sos__ sac, sos_matched sm, sos__ats sna where sac.dr_cr = 'DR' and sac.upload_date <= #{date} "
	// 		+ "  and ((sna.id = sm.sos__ats_id and sac.match_id = sm.match_id and sna.upload_date > #{date})) and sac.additional_information != 'ECX Inter' and sac.status = 1 and sac.availability = 1  "
	// 		+ "  and sm.status = 1 and sm.availability = 1  and sna.status = 1 and sna.availability = 1) " + " + ( "
	// 		+ " select ISNULL(sum(sac.amount), 0) from sos__ sac, sos_matched sm, sos__ats sna where sac.dr_cr = 'DR' and sac.upload_date <= #{date} "
	// 		+ "  and ((sna.id = sm.sos__ats_id and sac.match_id = sm.match_id and sna.upload_date > #{date})) and sac.additional_information = 'ECX Inter' and sna.upload_date > sac.upload_date and sac.status = 1 and sac.availability = 1  "
	// 		+ "  and sm.status = 1 and sm.availability = 1  and sna.status = 1 and sna.availability = 1) " + " + ( "
	// 		+ " select ISNULL(sum(bac.amount), 0) from b2b__ bac, b2b_matched bm, b2b__ats bna where bac.dr_cr = 'DR' and bac.upload_date <= #{date}  "
	// 		+ " and ((bna.id = bm.b2b__ats_id and bac.match_id = bm.match_id and bna.upload_date > #{date})) and bac.status = 1 and bac.availability = 1  "
	// 		+ "  and bm.status = 1 and bm.availability = 1  and bna.status = 1 and bna.availability = 1) " + " )")
	// Double getTotalDebit(@Param("date") String string);

	@Select("select \r\n" + //
				"\tdistinct dac.id, dac.amount, dac.branch_code, dac.additional_information, dac.value_date from data__ dac where upload_date <= #{date} and dr_cr = 'CR' and dac.status = 1 and dac.availability = 1 \r\n" + //
				"union \r\n" + //
				"select \r\n" + //
				"\tdistinct dac.id, dac.amount, dac.branch_code, dac.additional_information, dac.value_date from _partially_matched dac where upload_date <= #{date} and dr_cr = 'CR' and dac.status = 1 and dac.availability = 1 \r\n" + //
				"union \r\n" + //
				"select \r\n" + //
				"\tdistinct rac.id, rac.amount, rac.branch_code, rac.additional_information, rac.value_date from rtgs__ rac, rtgs_matched rm, rtgs__ats rna \r\n" + //
				"\t\twhere rac.dr_cr = 'CR' and rac.upload_date <= #{date} and (rna.id = rm.rtgs__ats_id and rac.match_id = rm.match_id and rna.upload_date > #{date}) and rac.status = 1 and rac.availability = 1  \r\n" + //
				"\t\tand rm.status = 1 and rm.availability = 1  and rna.status = 1 and rna.availability = 1  \r\n" + //
				"union \r\n" + //
				"select \r\n" + //
				"\tdistinct eac.id, eac.amount, eac.branch_code, eac.additional_information, eac.value_date from erca__ eac, erca_matched em, erca__ats ena \r\n" + //
				"\t\twhere eac.dr_cr = 'CR' and eac.upload_date <= #{date} and ((ena.id = em.erca__ats_id and eac.match_id = em.match_id and ena.upload_date > #{date})) and eac.status = 1 and eac.availability = 1  \r\n" + //
				"\t\tand em.status = 1 and em.availability = 1  and ena.status = 1 and ena.availability = 1\r\n" + //
				"union \r\n" + //
				"select \r\n" + //
				"\tdistinct sac.id, sac.amount, sac.branch_code, sac.additional_information, sac.value_date from sos__ sac, sos_matched sm, sos__ats sna \r\n" + //
				"\t\twhere sac.dr_cr = 'CR' and sac.upload_date <= #{date} and ((sna.id = sm.sos__ats_id and sac.match_id = sm.match_id and sna.upload_date > #{date})) and sac.additional_information != 'ECX Inter' and sac.status = 1 and sac.availability = 1  \r\n" + //
				"\t\tand sm.status = 1 and sm.availability = 1  and sna.status = 1 and sna.availability = 1 \r\n" + //
				"union \r\n" + //
				"select \r\n" + //
				"\tdistinct sac.id, sac.amount, sac.branch_code, sac.additional_information, sac.value_date from sos__ sac, sos_matched sm, sos__ats sna \r\n" + //
				"\t\twhere sac.dr_cr = 'CR' and sac.upload_date <= #{date} and ((sna.id = sm.sos__ats_id and sac.match_id = sm.match_id and sna.upload_date >= #{date})) and sac.additional_information = 'ECX Inter' and sac.upload_date < sna.upload_date and sac.status = 1 and sac.availability = 1  \r\n" + //
				"\t\tand sm.status = 1 and sm.availability = 1  and sna.status = 1 and sna.availability = 1 union \r\n" + //
				"select \r\n" + //
				"\tdistinct bac.id, bac.amount, bac.branch_code, bac.additional_information, bac.value_date from b2b__ bac, b2b_matched bm, b2b__ats bna \r\n" + //
				"\t\twhere bac.dr_cr = 'CR' and bac.upload_date <= #{date} and ((bna.id = bm.b2b__ats_id and bac.match_id = bm.match_id and bna.upload_date > #{date})) and bac.status = 1 and bac.availability = 1  \r\n" + //
				"\t\tand bm.status = 1 and bm.availability = 1  and bna.status = 1 and bna.availability = 1\r\n" + //
				"union\r\n" + //
				"select \r\n" + //
				"\tdistinct pacr1.id, pacr1.amount, pacr1.branch_code, pacr1.additional_information, pacr1.value_date from pas___reversal pacr1, pas___reversal pacr2 \r\n" + //
				"\t\twhere pacr1.dr_cr = 'CR' AND pacr1.upload_date <= #{date} AND (pacr1.match_id = pacr2.match_id AND pacr2.dr_cr = 'DR' AND pacr2.upload_date > #{date} AND pacr1.amount = pacr2.amount)\r\n" + //
				"\t\tAND pacr1.status = 1 AND pacr1.availability = 1 AND pacr2.status = 1 AND pacr2.availability = 1;")
	List<File_rtgs__> reportCredit(@Param("date") String string);

	// 	@Select("select distinct dac.id, dac.amount, dac.branch_code, dac.additional_information, dac.value_date from data__ dac where upload_date <= #{date} and dr_cr = 'CR' and dac.status = 1 and dac.availability = 1 union "
	// 		+ "select distinct dac.id, dac.amount, dac.branch_code, dac.additional_information, dac.value_date from _partially_matched dac where upload_date <= #{date} and dr_cr = 'CR' and dac.status = 1 and dac.availability = 1 union "
	// 		+ "select distinct rac.id, rac.amount, rac.branch_code, rac.additional_information, rac.value_date from rtgs__ rac, rtgs_matched rm, rtgs__ats rna "
	// 		+ "where rac.dr_cr = 'CR' and rac.upload_date <= #{date} and (rna.id = rm.rtgs__ats_id and rac.match_id = rm.match_id and rna.upload_date > #{date}) and rac.status = 1 and rac.availability = 1  "
	// 		+ " and rm.status = 1 and rm.availability = 1  and rna.status = 1 and rna.availability = 1  union "
	// 		+ "select distinct eac.id, eac.amount, eac.branch_code, eac.additional_information, eac.value_date from erca__ eac, erca_matched em, erca__ats ena "
	// 		+ "where eac.dr_cr = 'CR' and eac.upload_date <= #{date} and ((ena.id = em.erca__ats_id and eac.match_id = em.match_id and ena.upload_date > #{date})) and eac.status = 1 and eac.availability = 1  "
	// 		+ " and em.status = 1 and em.availability = 1  and ena.status = 1 and ena.availability = 1 union "
	// 		+ "select distinct sac.id, sac.amount, sac.branch_code, sac.additional_information, sac.value_date from sos__ sac, sos_matched sm, sos__ats sna "
	// 		+ "where sac.dr_cr = 'CR' and sac.upload_date <= #{date} and ((sna.id = sm.sos__ats_id and sac.match_id = sm.match_id and sna.upload_date > #{date})) and sac.additional_information != 'ECX Inter' and sac.status = 1 and sac.availability = 1  "
	// 		+ " and sm.status = 1 and sm.availability = 1  and sna.status = 1 and sna.availability = 1 union "
	// 		+ "select distinct sac.id, sac.amount, sac.branch_code, sac.additional_information, sac.value_date from sos__ sac, sos_matched sm, sos__ats sna "
	// 		+ "where sac.dr_cr = 'CR' and sac.upload_date <= #{date} and ((sna.id = sm.sos__ats_id and sac.match_id = sm.match_id and sna.upload_date > #{date})) and sac.additional_information = 'ECX Inter' and sac.upload_date < sna.upload_date and sac.status = 1 and sac.availability = 1  "
	// 		+ " and sm.status = 1 and sm.availability = 1  and sna.status = 1 and sna.availability = 1 union "
	// 		+ "select distinct bac.id, bac.amount, bac.branch_code, bac.additional_information, bac.value_date from b2b__ bac, b2b_matched bm, b2b__ats bna "
	// 		+ "where bac.dr_cr = 'CR' and bac.upload_date <= #{date} and ((bna.id = bm.b2b__ats_id and bac.match_id = bm.match_id and bna.upload_date > #{date})) and bac.status = 1 and bac.availability = 1  "
	// 		+ " and bm.status = 1 and bm.availability = 1  and bna.status = 1 and bna.availability = 1")
	// List<File_rtgs__> reportCredit(@Param("date") String string);

	// @Select("\r\n" + //
	// 			"SELECT (\r\n" + //
	// 			"    (SELECT ISNULL(SUM(dac.amount), 0) FROM data__ dac WHERE upload_date <= #{date} AND dr_cr = 'CR' AND dac.status = 1 AND dac.availability = 1) +\r\n" + //
	// 			"    (SELECT ISNULL(SUM(dac.amount), 0) FROM _partially_matched dac WHERE upload_date <= #{date} AND dr_cr = 'CR' AND dac.status = 1 AND dac.availability = 1) +\r\n" + //
	// 			"    (\r\n" + //
	// 			"        SELECT ISNULL(SUM(rac.amount), 0) FROM rtgs__ rac, rtgs_matched rm, rtgs__ats rna\r\n" + //
	// 			"        WHERE rac.dr_cr = 'CR' AND rac.upload_date <= #{date} AND ((rna.id = rm.rtgs__ats_id AND rac.match_id = rm.match_id AND rna.upload_date > #{date}))\r\n" + //
	// 			"        AND rac.status = 1 AND rac.availability = 1 AND rm.status = 1 AND rm.availability = 1 AND rna.status = 1 AND rna.availability = 1\r\n" + //
	// 			"    ) +\r\n" + //
	// 			"    (\r\n" + //
	// 			"        SELECT ISNULL(SUM(eac.amount), 0) FROM erca__ eac, erca_matched em, erca__ats ena\r\n" + //
	// 			"        WHERE eac.dr_cr = 'CR' AND eac.upload_date <= #{date} AND ((ena.id = em.erca__ats_id AND eac.match_id = em.match_id AND ena.upload_date > #{date}))\r\n" + //
	// 			"        AND eac.status = 1 AND eac.availability = 1 AND em.status = 1 AND em.availability = 1 AND ena.status = 1 AND ena.availability = 1\r\n" + //
	// 			"    ) +\r\n" + //
	// 			"    (\r\n" + //
	// 			"        SELECT ISNULL(SUM(sac.amount), 0) FROM sos__ sac, sos_matched sm, sos__ats sna\r\n" + //
	// 			"        WHERE sac.dr_cr = 'CR' AND sac.upload_date <= #{date} AND ((sna.id = sm.sos__ats_id AND sac.match_id = sm.match_id AND sna.upload_date >= #{date}))\r\n" + //
	// 			"        AND sac.additional_information != 'ECX Inter' AND sac.status = 1 AND sac.availability = 1 AND sm.status = 1 AND sm.availability = 1 AND sna.status = 1 AND sna.availability = 1\r\n" + //
	// 			"    ) +\r\n" + //
	// 			"    (\r\n" + //
	// 			"        SELECT ISNULL(SUM(sac.amount), 0) FROM sos__ sac, sos_matched sm, sos__ats sna\r\n" + //
	// 			"        WHERE sac.dr_cr = 'CR' AND sac.upload_date <= #{date} AND ((sna.id = sm.sos__ats_id AND sac.match_id = sm.match_id AND sna.upload_date >= #{date}))\r\n" + //
	// 			"        AND sac.additional_information = 'ECX Inter' AND sna.upload_date > sac.upload_date AND sac.status = 1 AND sac.availability = 1 AND sm.status = 1 AND sm.availability = 1 AND sna.status = 1 AND sna.availability = 1\r\n" + //
	// 			"    ) +\r\n" + //
	// 			"    (\r\n" + //
	// 			"        SELECT ISNULL(SUM(bac.amount), 0) FROM b2b__ bac, b2b_matched bm, b2b__ats bna\r\n" + //
	// 			"        WHERE bac.dr_cr = 'CR' AND bac.upload_date <= #{date} AND ((bna.id = bm.b2b__ats_id AND bac.match_id = bm.match_id AND bna.upload_date > #{date})) \r\n" + //
	// 			"\t\tAND bac.status = 1 AND bac.availability = 1 AND bm.status = 1 AND bm.availability = 1 AND bna.status = 1 AND bna.availability = 1\r\n" + //
	// 			"    ) +\r\n" + //
	// 			"    (\r\n" + //
	// 			"       select ISNULL(SUM(pacr1.amount), 0) from pas___reversal pacr1, pas___reversal pacr2 \r\n" + //
	// 			"\t\twhere pacr1.dr_cr = 'CR' AND pacr1.upload_date <= #{date} AND (pacr1.match_id = pacr2.match_id AND pacr2.dr_cr = 'DR' AND pacr2.upload_date > #{date} \r\n" + //
	// 			"\t\tAND pacr1.amount = pacr2.amount) AND pacr1.status = 1 AND pacr1.availability = 1 AND pacr2.status = 1 AND pacr2.availability = 1\r\n" + //
	// 			"    )\r\n" + //
	// 			") AS total_amount;\r\n" + //
	// 			"")
	@Select("WITH CombinedResult AS (\r\n" + //
				"\tSELECT sac.* FROM sos__ sac, sos_matched sm, sos__ats sna\r\n" + //
				"\tWHERE sac.dr_cr = 'CR' AND sac.upload_date <= #{date} AND ((sna.id = sm.sos__ats_id AND sac.match_id = sm.match_id AND sna.upload_date > #{date}))\r\n" + //
				"\tAND sac.additional_information != 'ECX Inter' AND sac.status = 1 AND sac.availability = 1 AND sm.status = 1 AND sm.availability = 1 AND sna.status = 1 AND sna.availability = 1\r\n" + //
				"\tunion\r\n" + //
				"\tselect sac.* from sos__ sac, sos__ sac2 where sac.dr_cr = 'CR'  AND sac.upload_date <= #{date} and sac2.upload_date > #{date} and sac.match_id = sac2.match_id\r\n" + //
				"\tand sac.status = 1 and sac.availability = 1 and sac2.status = 1 and sac2.availability = 1 and sac.additional_information != 'ECX Inter' and sac2.additional_information != 'ECX Inter'\r\n" + //
				")\r\n" + //
				"SELECT (\r\n" + //
				"\t\t(SELECT ISNULL(SUM(dac.amount), 0) FROM data__ dac WHERE upload_date <= #{date} AND dr_cr = 'CR' AND dac.status = 1 AND dac.availability = 1) +\r\n" + //
				"\t\t(SELECT ISNULL(SUM(dac.amount), 0) FROM _partially_matched dac WHERE upload_date <= #{date} AND dr_cr = 'CR' AND dac.status = 1 AND dac.availability = 1) +\r\n" + //
				"\t\t(\r\n" + //
				"\t\t\tSELECT ISNULL(SUM(rac.amount), 0) FROM rtgs__ rac, rtgs_matched rm, rtgs__ats rna\r\n" + //
				"\t\t\tWHERE rac.dr_cr = 'CR' AND rac.upload_date <= #{date} AND ((rna.id = rm.rtgs__ats_id AND rac.match_id = rm.match_id AND rna.upload_date > #{date}))\r\n" + //
				"\t\t\tAND rac.status = 1 AND rac.availability = 1 AND rm.status = 1 AND rm.availability = 1 AND rna.status = 1 AND rna.availability = 1\r\n" + //
				"\t\t) +\r\n" + //
				"\t\t(\r\n" + //
				"\t\t\tSELECT ISNULL(SUM(eac.amount), 0) FROM erca__ eac, erca_matched em, erca__ats ena\r\n" + //
				"\t\t\tWHERE eac.dr_cr = 'CR' AND eac.upload_date <= #{date} AND ((ena.id = em.erca__ats_id AND eac.match_id = em.match_id AND ena.upload_date > #{date}))\r\n" + //
				"\t\t\tAND eac.status = 1 AND eac.availability = 1 AND em.status = 1 AND em.availability = 1 AND ena.status = 1 AND ena.availability = 1\r\n" + //
				"\t\t) +\r\n" + //
				"\t\t(\r\n" + //
				"\t\t\tselect  ISNULL(SUM(amount), 0) from CombinedResult\t\r\n" + //
				"\t\t) +\r\n" + //
				"\t\t(\r\n" + //
				"\t\t\tSELECT ISNULL(SUM(sac.amount), 0) FROM sos__ sac, sos_matched sm, sos__ats sna\r\n" + //
				"\t\t\tWHERE sac.dr_cr = 'CR' AND sac.upload_date <= #{date} AND ((sna.id = sm.sos__ats_id AND sac.match_id = sm.match_id AND sna.upload_date > #{date}))\r\n" + //
				"\t\t\tAND sac.additional_information = 'ECX Inter' AND sna.upload_date > sac.upload_date AND sac.status = 1 AND sac.availability = 1 AND sm.status = 1 AND sm.availability = 1 AND sna.status = 1 AND sna.availability = 1\r\n" + //
				"\t\t) +\r\n" + //
				"\t\t(\r\n" + //
				"\t\t\tSELECT ISNULL(SUM(bac.amount), 0) FROM b2b__ bac, b2b_matched bm, b2b__ats bna\r\n" + //
				"\t\t\tWHERE bac.dr_cr = 'CR' AND bac.upload_date <= #{date} AND ((bna.id = bm.b2b__ats_id AND bac.match_id = bm.match_id AND bna.upload_date > #{date})) \r\n" + //
				"\tAND bac.status = 1 AND bac.availability = 1 AND bm.status = 1 AND bm.availability = 1 AND bna.status = 1 AND bna.availability = 1\r\n" + //
				"\t\t) +\r\n" + //
				"\t\t(\r\n" + //
				"\t\t\tSELECT ISNULL(SUM(pacr1.amount), 0)\r\n"
				+ "FROM pas___reversal pacr1\r\n"
				+ "INNER JOIN (\r\n"
				+ "    SELECT DISTINCT match_id, status, availability\r\n"
				+ "    FROM pas___reversal\r\n"
				+ "    WHERE upload_date > #{date}\r\n"
				+ ") AS pacr2_filtered ON pacr1.match_id = pacr2_filtered.match_id\r\n"
				+ "WHERE pacr1.dr_cr = 'CR'\r\n"
				+ "    AND pacr1.upload_date <= #{date}\r\n"
				+ "    AND pacr1.status = 1\r\n"
				+ "    AND pacr1.availability = 1\r\n"
				+ "	AND pacr2_filtered.status=1\r\n"
				+ "	AND pacr2_filtered.availability=1\r\n" + //
				"\t\t)\r\n" + //
				") AS total_amount;")
	Double getTotalCredit(@Param("date") String string);

	// @Select("select ((select ISNULL(sum(dac.amount), 0) from data__ dac where upload_date <= #{date} and dr_cr = 'CR' and dac.status = 1 and dac.availability = 1 ) "
	// 		+ " + (select ISNULL(sum(dac.amount), 0) from _partially_matched dac where upload_date <= #{date} and dr_cr = 'CR' and dac.status = 1 and dac.availability = 1 ) "
	// 		+ " + ( "
	// 		+ " select ISNULL(sum(rac.amount), 0) from rtgs__ rac, rtgs_matched rm, rtgs__ats rna where rac.dr_cr = 'CR' and rac.upload_date <= #{date}  "
	// 		+ " and ((rna.id = rm.rtgs__ats_id and rac.match_id = rm.match_id and rna.upload_date > #{date})) and rac.status = 1 and rac.availability = 1  "
	// 		+ "  and rm.status = 1 and rm.availability = 1  and rna.status = 1 and rna.availability = 1) " + " + ( "
	// 		+ " select ISNULL(sum(eac.amount), 0) from erca__ eac, erca_matched em, erca__ats ena where eac.dr_cr = 'CR' and eac.upload_date <= #{date} "
	// 		+ "  and ((ena.id = em.erca__ats_id and eac.match_id = em.match_id and ena.upload_date > #{date})) and eac.status = 1 and eac.availability = 1  "
	// 		+ "  and em.status = 1 and em.availability = 1  and ena.status = 1 and ena.availability = 1 ) " + " + ( "
	// 		+ " select ISNULL(sum(sac.amount), 0) from sos__ sac, sos_matched sm, sos__ats sna where sac.dr_cr = 'CR' and sac.upload_date <= #{date} "
	// 		+ "  and ((sna.id = sm.sos__ats_id and sac.match_id = sm.match_id and sna.upload_date > #{date})) and sac.additional_information != 'ECX Inter' and sac.status = 1 and sac.availability = 1  "
	// 		+ "  and sm.status = 1 and sm.availability = 1  and sna.status = 1 and sna.availability = 1) " + " + ( "
	// 		+ " select ISNULL(sum(sac.amount), 0) from sos__ sac, sos_matched sm, sos__ats sna where sac.dr_cr = 'CR' and sac.upload_date <= #{date} "
	// 		+ "  and ((sna.id = sm.sos__ats_id and sac.match_id = sm.match_id and sna.upload_date > #{date})) and sac.additional_information = 'ECX Inter' and sna.upload_date > sac.upload_date and sac.status = 1 and sac.availability = 1  "
	// 		+ "  and sm.status = 1 and sm.availability = 1  and sna.status = 1 and sna.availability = 1) " + " + ( "
	// 		+ " select ISNULL(sum(bac.amount), 0) from b2b__ bac, b2b_matched bm, b2b__ats bna where bac.dr_cr = 'CR' and bac.upload_date <= #{date}  "
	// 		+ " and ((bna.id = bm.b2b__ats_id and bac.match_id = bm.match_id and bna.upload_date > #{date})) and bac.status = 1 and bac.availability = 1  "
	// 		+ "  and bm.status = 1 and bm.availability = 1  and bna.status = 1 and bna.availability = 1) " + " )")
	// Double getTotalCredit(@Param("date") String string);

	@Select("select ISNULL(sum(ending_balance_con), 0) from files where upload_date = #{date} and type = ''  and status = 1 and availability = 1")
	Double getConventionalEndingBalance(@Param("date") String string);

	@Select("select ISNULL(sum(ending_balance_ifb), 0) from files where upload_date = #{date} and type = '' and status = 1 and availability = 1")
	Double getIfbEndingBalance(@Param("date") String string);

	@Select("select distinct dna.id, dna.value_date_type, dna.reference, dna.sender, dna.receiver, dna.amount from data__ats dna where upload_date <= #{date} and dr_cr = 'CR' and status = 1 and availability = 1 union "
			+ "select distinct dna.id, dna.value_date_type, dna.reference, dna.sender, dna.receiver, dna.amount from ats_partially_matched dna where upload_date <= #{date} and dr_cr = 'CR' and status = 1 and availability = 1 union "
			+ "select distinct rna.id, rna.value_date_type, rna.reference, rna.sender, rna.receiver, rna.amount from rtgs__ats rna, rtgs_matched rm, rtgs__ rac "
			+ "where rna.dr_cr = 'CR' and rna.upload_date <= #{date} and rna.id = rm.rtgs__ats_id and rm.match_id = rac.match_id and rac.upload_date > #{date}  "
			+ "and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1 union "
			+ "select distinct ena.id, ena.value_date_type, ena.reference, ena.sender, ena.receiver, ena.amount from erca__ats ena, erca_matched em, erca__ eac "
			+ "where ena.dr_cr = 'CR' and ena.upload_date <= #{date} and ena.id = em.erca__ats_id and em.match_id = eac.match_id and eac.upload_date > #{date} "
			+ "and ena.status = 1 and ena.availability = 1 and em.status = 1 and em.availability = 1  and eac.status = 1 and eac.availability = 1 union "
			+ "select distinct sna.id, sna.value_date_type, sna.reference, sna.sender, sna.receiver, sna.amount from sos__ats sna, sos_matched sm, sos__ sac "
			+ "where sna.dr_cr = 'CR' and sna.upload_date <= #{date} and sna.id = sm.sos__ats_id and sm.match_id = sac.match_id and sac.upload_date > #{date} "
			+ "and sna.status = 1 and sna.availability = 1 and sm.status = 1 and sm.availability = 1  and sac.status = 1 and sac.availability = 1 union "
			+ "select distinct bna.id, bna.value_date_type, bna.reference, bna.sender, bna.receiver, bna.amount from b2b__ats bna, b2b_matched bm, b2b__ bac "
			+ "where bna.dr_cr = 'CR' and bna.upload_date <= #{date} and bna.id = bm.b2b__ats_id and bm.match_id = bac.match_id and bac.upload_date > #{date}  "
			+ "and bna.status = 1 and bna.availability = 1 and bm.status = 1 and bm.availability = 1  and bac.status = 1 and bac.availability = 1")
	List<File_rtgs__ats> reportAtsCredit(@Param("date") String string);

	@Select("select distinct dna.id, dna.value_date_type, dna.reference, dna.sender, dna.receiver, dna.amount from data__ats dna where upload_date <= #{date} and dr_cr = 'DR' and status = 1 and availability = 1 union "
			+ "select distinct dna.id, dna.value_date_type, dna.reference, dna.sender, dna.receiver, dna.amount from ats_partially_matched dna where upload_date <= #{date} and dr_cr = 'DR' and status = 1 and availability = 1 union "
			+ "select distinct rna.id, rna.value_date_type, rna.reference, rna.sender, rna.receiver, rna.amount from rtgs__ats rna, rtgs_matched rm, rtgs__ rac "
			+ "where rna.dr_cr = 'DR' and rna.upload_date <= #{date} and rna.id = rm.rtgs__ats_id and rm.match_id = rac.match_id and rac.upload_date > #{date}  "
			+ "and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1 union "
			+ "select distinct ena.id, ena.value_date_type, ena.reference, ena.sender, ena.receiver, ena.amount from erca__ats ena, erca_matched em, erca__ eac "
			+ "where ena.dr_cr = 'DR' and ena.upload_date <= #{date} and ena.id = em.erca__ats_id and em.match_id = eac.match_id and eac.upload_date > #{date} "
			+ "and ena.status = 1 and ena.availability = 1 and em.status = 1 and em.availability = 1  and eac.status = 1 and eac.availability = 1 union "
			+ "select distinct sna.id, sna.value_date_type, sna.reference, sna.sender, sna.receiver, sna.amount from sos__ats sna, sos_matched sm, sos__ sac "
			+ "where sna.dr_cr = 'DR' and sna.upload_date <= #{date} and sna.id = sm.sos__ats_id and sm.match_id = sac.match_id and sac.upload_date > #{date} "
			+ "and sna.status = 1 and sna.availability = 1 and sm.status = 1 and sm.availability = 1  and sac.status = 1 and sac.availability = 1 union "
			+ "select distinct bna.id, bna.value_date_type, bna.reference, bna.sender, bna.receiver, bna.amount from b2b__ats bna, b2b_matched bm, b2b__ bac "
			+ "where bna.dr_cr = 'DR' and bna.upload_date <= #{date} and bna.id = bm.b2b__ats_id and bm.match_id = bac.match_id and bac.upload_date > #{date}  "
			+ "and bna.status = 1 and bna.availability = 1 and bm.status = 1 and bm.availability = 1  and bac.status = 1 and bac.availability = 1")
	List<File_rtgs__ats> reportAtsDebit(@Param("date") String string);

	// @Select("select ((select ISNULL(sum(dna.amount), 0) from data__ats dna where upload_date <= #{date} and dr_cr = 'CR' and status = 1 and availability = 1)  +  "
	// 		+ " (select ISNULL(sum(dna.amount), 0) from ats_partially_matched dna where upload_date <= #{date} and dr_cr = 'CR' and status = 1 and availability = 1)  "
	// 		+ " + ( " + " select ISNULL(sum(rna.amount), 0) from rtgs__ats rna, rtgs_matched rm, rtgs__ rac "
	// 		+ " where rna.dr_cr = 'CR' and rna.upload_date <= #{date} and rna.id = rm.rtgs__ats_id and rm.match_id = rac.match_id and rac.upload_date > #{date}  "
	// 		+ " and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1) "
	// 		+ " + ( " + " select ISNULL(sum(ena.amount), 0) from erca__ats ena, erca_matched em, erca__ eac "
	// 		+ " where ena.dr_cr = 'CR' and ena.upload_date <= #{date} and ena.id = em.erca__ats_id and em.match_id = eac.match_id and eac.upload_date > #{date}  "
	// 		+ " and ena.status = 1 and ena.availability = 1 and em.status = 1 and em.availability = 1  and eac.status = 1 and eac.availability = 1)  "
	// 		+ " + ( " + " select ISNULL(sum(distinct sna.amount), 0) from sos__ats sna, sos_matched sm, sos__ sac "
	// 		+ " where sna.dr_cr = 'CR' and sna.upload_date <= #{date} and sna.id = sm.sos__ats_id and sm.match_id = sac.match_id and sac.upload_date > #{date}  "
	// 		+ " and sna.status = 1 and sna.availability = 1 and sm.status = 1 and sm.availability = 1  and sac.status = 1 and sac.availability = 1) "
	// 		+ " + ( " + " select ISNULL(sum(bna.amount), 0) from b2b__ats bna, b2b_matched bm, b2b__ bac "
	// 		+ " where bna.dr_cr = 'CR' and bna.upload_date <= #{date} and bna.id = bm.b2b__ats_id and bm.match_id = bac.match_id and bac.upload_date > #{date}  "
	// 		+ " and bna.status = 1 and bna.availability = 1 and bm.status = 1 and bm.availability = 1  and bac.status = 1 and bac.availability = 1) "
	// 		+ " )")
	// @Select("select ((select ISNULL(sum(dna.amount), 0) from data__ats dna where upload_date <= #{date} and dr_cr = 'CR' and status = 1 and availability = 1)  +   \r\n" + //
	// 			"\t\t\t \r\n" + //
	// 			"\t\t(select ISNULL(sum(dna.amount), 0) from ats_partially_matched dna where upload_date <= #{date} and dr_cr = 'CR' and status = 1 and availability = 1)  \r\n" + //
	// 			"\t\t\t \r\n" + //
	// 			"\t\t+ (   select ISNULL(sum(rna.amount), 0) from rtgs__ats rna, rtgs_matched rm, rtgs__ rac \r\n" + //
	// 			"\t\twhere rna.dr_cr = 'CR' and rna.upload_date <= #{date} and rna.id = rm.rtgs__ats_id and rm.match_id = rac.match_id and rac.upload_date > #{date}  \r\n" + //
	// 			"\t\tand rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1) \r\n" + //
	// 			"\t\t\t \r\n" + //
	// 			"\t\t+ (   select ISNULL(sum(ena.amount), 0) from erca__ats ena, erca_matched em, erca__ eac \r\n" + //
	// 			"\t\twhere ena.dr_cr = 'CR' and ena.upload_date <= #{date} and ena.id = em.erca__ats_id and em.match_id = eac.match_id and eac.upload_date > #{date}  \r\n" + //
	// 			"\t\tand ena.status = 1 and ena.availability = 1 and em.status = 1 and em.availability = 1  and eac.status = 1 and eac.availability = 1)  \r\n" + //
	// 			"\t\t\t \r\n" + //
	// 			"\t\t+ (   select ISNULL(sum(distinct sna.amount), 0) from sos__ats sna, sos_matched sm, sos__ sac \r\n" + //
	// 			"\t\twhere sna.dr_cr = 'CR' and sna.upload_date <= #{date} and sna.id = sm.sos__ats_id and sm.match_id = sac.match_id and sac.upload_date > #{date}  \r\n" + //
	// 			"\t\tand sna.status = 1 and sna.availability = 1 and sm.status = 1 and sm.availability = 1  and sac.status = 1 and sac.availability = 1) \r\n" + //
	// 			"\t\t\t \r\n" + //
	// 			"\t\t+ (select ISNULL(sum(distinct bna.amount), 0) from b2b__ats bna, b2b__ats bna2, b2b_matched bm, b2b_matched bm2\r\n" + //
	// 			"\t\twhere bna.dr_cr = 'CR' and bna.upload_date <= #{date} and \r\n" + //
	// 			"\t\t(bna.id = bm.b2b__ats_id and bna2.id = bm2.b2b__ats_id and bm.match_id = bm2.match_id and bna2.value_date_type like '%202' and bna2.upload_date > #{date} and bna2.dr_cr = 'dr' and bna2.amount = (bna.amount - 50))\r\n" + //
	// 			"\t\tand bna.status = 1 and bna.availability = 1 and bm.status = 1 and bm.availability = 1 ) \r\n" + //
	// 			"\t)")
	@Select("WITH CombinedResult AS (\r\n" + //
				"    SELECT bna.* \r\n" + //
				"    FROM b2b__ats bna, b2b_matched bm, b2b__ bac \r\n" + //
				"    WHERE bna.dr_cr = 'CR' \r\n" + //
				"        AND bna.upload_date <= #{date} \r\n" + //
				"        AND (bna.id = bm.b2b__ats_id AND bm.match_id = bac.match_id AND bac.upload_date > #{date})\r\n" + //
				"        AND bna.status = 1 \r\n" + //
				"        AND bna.availability = 1 \r\n" + //
				"        AND bm.status = 1 \r\n" + //
				"        AND bm.availability = 1  \r\n" + //
				"        AND bac.status = 1 \r\n" + //
				"        AND bac.availability = 1\r\n" + //
				"\r\n" + //
				"    UNION \r\n" + //
				"\r\n" + //
				"    SELECT bna.* \r\n" + //
				"    FROM b2b__ats bna, b2b__ats bna2, b2b_matched bm, b2b_matched bm2\r\n" + //
				"    WHERE bna.dr_cr = 'CR' \r\n" + //
				"        AND bna.upload_date <= #{date} \r\n" + //
				"        AND (bna.id = bm.b2b__ats_id \r\n" + //
				"            AND bna2.id = bm2.b2b__ats_id \r\n" + //
				"            AND bm.match_id = bm2.match_id \r\n" + //
				"            AND bna2.value_date_type LIKE '%202' \r\n" + //
				"            AND bna2.upload_date > #{date} \r\n" + //
				"            AND bna2.dr_cr = 'dr' \r\n" + //
				"            AND bna2.amount = (bna.amount - 50))\r\n" + //
				"        AND bna.status = 1 \r\n" + //
				"        AND bna.availability = 1 \r\n" + //
				"        AND bm.status = 1 \r\n" + //
				"        AND bm.availability = 1\r\n" + //
				")\r\n" + //
				"\r\n" + //
				"select ((select ISNULL(sum(dna.amount), 0) from data__ats dna where upload_date <= #{date} and dr_cr = 'CR' and status = 1 and availability = 1)  +   \r\n" + //
				"\t\t\t \r\n" + //
				"\t\t(select ISNULL(sum(dna.amount), 0) from ats_partially_matched dna where upload_date <= #{date} and dr_cr = 'CR' and status = 1 and availability = 1)  \r\n" + //
				"\t\t\t \r\n" + //
				"\t\t+ (   select ISNULL(sum(rna.amount), 0) from rtgs__ats rna, rtgs_matched rm, rtgs__ rac \r\n" + //
				"\t\twhere rna.dr_cr = 'CR' and rna.upload_date <= #{date} and rna.id = rm.rtgs__ats_id and rm.match_id = rac.match_id and rac.upload_date > #{date}  \r\n" + //
				"\t\tand rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1) \r\n" + //
				"\t\t\t \r\n" + //
				"\t\t+ (   select ISNULL(sum(ena.amount), 0) from erca__ats ena, erca_matched em, erca__ eac \r\n" + //
				"\t\twhere ena.dr_cr = 'CR' and ena.upload_date <= #{date} and ena.id = em.erca__ats_id and em.match_id = eac.match_id and eac.upload_date > #{date}  \r\n" + //
				"\t\tand ena.status = 1 and ena.availability = 1 and em.status = 1 and em.availability = 1  and eac.status = 1 and eac.availability = 1)  \r\n" + //
				"\t\t\t \r\n" + //
				"\t\t+ (   select ISNULL(sum(distinct sna.amount), 0) from sos__ats sna, sos_matched sm, sos__ sac \r\n" + //
				"\t\twhere sna.dr_cr = 'CR' and sna.upload_date <= #{date} and sna.id = sm.sos__ats_id and sm.match_id = sac.match_id and sac.upload_date > #{date}  \r\n" + //
				"\t\tand sna.status = 1 and sna.availability = 1 and sm.status = 1 and sm.availability = 1  and sac.status = 1 and sac.availability = 1) \r\n" + //
				"\t\t\t \r\n" + //
				"\t\t+ (SELECT  ISNULL(SUM(amount), 0) FROM CombinedResult) \r\n" + //
				"\t)")
	Double getAtsTotalCredit(@Param("date") String string);
    // @Select("SELECT SUM(aka.amount)\r\n" +
	// 			"FROM (\r\n" +
	// 			"  SELECT bna.amount\r\n" +
	// 			"  FROM b2b__ats bna\r\n" +
	// 			"  JOIN b2b_matched bm ON bna.id = bm.b2b__ats_id\r\n" +
	// 			"  JOIN b2b__ bac ON bm.match_id = bac.match_id\r\n" +
	// 			"  WHERE bna.dr_cr = 'CR'\r\n" +
	// 			"    AND bna.upload_date <= #{date}\r\n" +
	// 			"    AND bna.status = 1\r\n" +
	// 			"    AND bna.availability = 1\r\n" +
	// 			"    AND bm.status = 1\r\n" +
	// 			"    AND bm.availability = 1\r\n" +
	// 			"    AND bac.status = 1\r\n" +
	// 			"    AND bac.availability = 1\r\n" +
	// 			"    AND bac.upload_date > #{date}\r\n" +
	// 			"  UNION\r\n" +
	// 			"  SELECT bna.amount\r\n" +
	// 			"  FROM b2b__ats bna\r\n" +
	// 			"  JOIN b2b_matched bm ON bna.id = bm.b2b__ats_id\r\n" +
	// 			"  JOIN b2b_matched bm2 ON bm.match_id = bm2.match_id\r\n" +
	// 			"  JOIN b2b__ats bna2 ON bm2.b2b__ats_id = bna2.id\r\n" +
	// 			"  WHERE bna.dr_cr = 'CR'\r\n" +
	// 			"    AND bna.upload_date <= #{date}\r\n" +
	// 			"    AND bna.status = 1\r\n" +
	// 			"    AND bna.availability = 1\r\n" +
	// 			"    AND bm.status = 1\r\n" +
	// 			"    AND bm.availability = 1\r\n" +
	// 			"    AND bna2.upload_date > #{date}\r\n" +
	// 			"    AND bna2.dr_cr = 'dr'\r\n" +
	// 			"    AND bna2.amount = (bna.amount - 50)\r\n" +
	// 			"    AND bna2.value_date_type LIKE '%202%'\r\n" +
	// 			") AS aka;")
	// Double getAtsTotalCredit(@Param("date") String date);

	@Select("select ((select ISNULL(sum(dna.amount), 0) from data__ats dna where upload_date <= #{date} and dr_cr = 'DR' and status = 1 and availability = 1)  + "
			+ " (select ISNULL(sum(dna.amount), 0) from ats_partially_matched dna where upload_date <= #{date} and dr_cr = 'DR' and status = 1 and availability = 1)  "
			+ " + ( " + " select ISNULL(sum(rna.amount), 0) from rtgs__ats rna, rtgs_matched rm, rtgs__ rac "
			+ " where rna.dr_cr = 'DR' and rna.upload_date <= #{date} and rna.id = rm.rtgs__ats_id and rm.match_id = rac.match_id and rac.upload_date > #{date}  "
			+ " and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1) "
			+ " + ( " + " select ISNULL(sum(ena.amount), 0) from erca__ats ena, erca_matched em, erca__ eac "
			+ " where ena.dr_cr = 'DR' and ena.upload_date <= #{date} and ena.id = em.erca__ats_id and em.match_id = eac.match_id and eac.upload_date > #{date}  "
			+ " and ena.status = 1 and ena.availability = 1 and em.status = 1 and em.availability = 1  and eac.status = 1 and eac.availability = 1)  "
			+ " + ( " + " select ISNULL(sum(distinct sna.amount), 0) from sos__ats sna, sos_matched sm, sos__ sac "
			+ " where sna.dr_cr = 'DR' and sna.upload_date <= #{date} and sna.id = sm.sos__ats_id and sm.match_id = sac.match_id and sac.upload_date > #{date}  "
			+ " and sna.status = 1 and sna.availability = 1 and sm.status = 1 and sm.availability = 1  and sac.status = 1 and sac.availability = 1) "
			+ " + ( " + " select ISNULL(sum(bna.amount), 0) from b2b__ats bna, b2b_matched bm, b2b__ bac "
			+ " where bna.dr_cr = 'DR' and bna.upload_date <= #{date} and bna.id = bm.b2b__ats_id and bm.match_id = bac.match_id and bac.upload_date > #{date}  "
			+ " and bna.status = 1 and bna.availability = 1 and bm.status = 1 and bm.availability = 1  and bac.status = 1 and bac.availability = 1) "
			+ " )")
	Double getAtsTotalDebit(@Param("date") String string);

	@Select("IF EXISTS (SELECT * FROM files WHERE upload_date = #{date} and type = '' ) " + "    SELECT 'True' "
			+ "ELSE " + "    SELECT 'False'")
	// @Select("SELECT EXISTS(SELECT * FROM files WHERE upload_date = #{date} and
	// type = '')")
	boolean existsFile(@Param("date") String string);

	@Select("IF EXISTS (SELECT * FROM files WHERE upload_date = #{date} and type = 'Payable' ) " + "    SELECT 'True' "
			+ "ELSE " + "    SELECT 'False'")
	// @Select("SELECT EXISTS(SELECT * FROM files WHERE upload_date = #{date} and
	// type = '')")
	boolean existsFilePayable(@Param("date") String string);

	@Select("IF EXISTS (SELECT * FROM files WHERE upload_date = #{date} and type = 'Receivable' ) "
			+ "    SELECT 'True' " + "ELSE " + "    SELECT 'False'")
	// @Select("SELECT EXISTS(SELECT * FROM files WHERE upload_date = #{date} and
	// type = '')")
	boolean existsFileReceivable(@Param("date") String string);

	@Select("IF EXISTS (SELECT * FROM files WHERE upload_date = #{date} and type = 'ISSUE_' ) "
			+ "    SELECT 'True' " + "ELSE " + "    SELECT 'False'")
	// @Select("SELECT EXISTS(SELECT * FROM files WHERE upload_date = #{date} and
	// type = '')")
	boolean existsFileIssue(@Param("date") String string);

	@Select("	 SELECT CASE \r\n"
			+ "  WHEN EXISTS(SELECT 1 FROM  data_fixed_mms dfm WHERE cast(dfm.created_date as date) <= cast(#{date} as date)) THEN 'True'\r\n"
			+ "	 WHEN EXISTS(SELECT 1 FROM  data_fixed_ dfc WHERE dfc.transaction_date <= cast(#{date} as date)) THEN 'True'\r\n"
			+ "	 WHEN EXISTS(SELECT 1 FROM  fixed_mms fm WHERE cast(fm.created_date as date)<= cast(#{date} as date)) THEN 'True'\r\n"
			+ "	 WHEN EXISTS(SELECT 1 FROM  fixed_ fc WHERE fc.transaction_date<= cast(#{date} as date)) THEN 'True'\r\n"
			+ "	 ELSE 'False'\r\n"
			+ "	 END AS record_exists")
	boolean existsFileFixed(@Param("date") String date);



	/*
	 * c1.setCellValue(data_ats.getValue_date_type());
	 * c2.setCellValue(data_ats.getReference());
	 * c3.setCellValue(data_ats.getSender() + " " + data_ats.getReceiver());
	 * c4.setCellValue(data_ats.getAmount());
	 *
	 * private Long id; private Long _id; private String value_date_type;
	 * private String reference; private String sender; private String receiver;
	 * private String additional_information; private Double amount; private String
	 * dr_cr; private Long upload_date; private String match_status; private String
	 * status; private String availability; private Long file_id; private String
	 * match_id; private String reason;
	 *
	 */

	// @Select("insert into b2b_matched (b2b__ats_id, match_id, match_date,
	// reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
	// + "values(#{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many},
	// #{status}, #{availability})")
	// Object addB2bMatchedFromPartial(@Param("id") Long id, @Param("match_id")
	// String match_id, @Param("date") Long date_now,
	// @Param("recon_type") String recon_type, @Param("many_to_many") String
	// many_to_many,
	// @Param("status") String status, @Param("availability") String availability);

	@Insert("INSERT INTO _ats_edit_history (value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date,"
			+ "	match_status, status, availability, REFERENCE, file_id, edit_reason_id, new_old)(SELECT value_date_type, sender, receiver,"
			+ " additional_information, amount, dr_cr, upload_date,'1', status, availability, REFERENCE, file_id, #{edit_reason_id}, 'old' FROM data__ats where id = #{id})")
	void moveOldAtsData(@Param("id") Long id, @Param("edit_reason_id") Long edit_reason_id);

	@Insert("INSERT INTO _ats_edit_history (value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date,"
			+ "	match_status, status, availability, REFERENCE, file_id, edit_reason_id, new_old)(SELECT value_date_type, sender, receiver,"
			+ " additional_information, amount, dr_cr, upload_date,'1', status, availability, REFERENCE, file_id, #{reason_id}, 'deleted' FROM data__ats where id = #{id})")
	void moveDeletedAtsData(@Param("id") Long id, @Param("reason_id") Long reason_id);

	@Insert("INSERT INTO _ats_edit_history (value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date,"
			+ "	match_status, status, availability, REFERENCE, file_id, edit_reason_id, new_old)(SELECT value_date_type, sender, receiver,"
			+ " additional_information, amount, dr_cr, upload_date,'1', status, availability, REFERENCE, file_id, #{edit_reason_id}, 'new' FROM data__ats where id = #{id})")
	boolean moveEditedAtsData(@Param("id") Long id, @Param("edit_reason_id") Long edit_reason_id);

	@Select("INSERT INTO __edit_history(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{edit_reason_id},'old' FROM data__ where id = #{id}")
	void moveOldData(@Param("id") Long id, @Param("edit_reason_id") Long edit_reason_id);

	// ======================================================================edit
	// payable =================================
	@Select("INSERT INTO payable_edit_history(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{edit_reason_id},'old' FROM data_payable where id = #{id}")
	void moveOldPayableData(@Param("id") Long id, @Param("edit_reason_id") Long edit_reason_id);

	@Update("update data_payable set amount =#{amount}, value_date = #{value_date_type}, "
			+ "dr_cr = #{dr_cr}, upload_date = #{upload_date}, additional_information = #{additional_information} where id = #{id}")
	Boolean updateTransactionpayable(File_rtgs__ats edit_data);

	@Select("INSERT INTO payable_edit_history(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{edit_reason_id},'new' FROM data_payable where id = #{id}")
	void moveEditedPayableData(@Param("id") Long id, @Param("edit_reason_id") Long edit_reason_id);

	@Select("INSERT INTO payable_edit_history(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{reason_id},'delete' FROM data_payable where id = #{id}")
	void moveDeletedPayableData(@Param("id") Long id, @Param("reason_id") Long reason_id);

	@Update("update data_payable set availability = '0' where id = #{id}")
	void deleteTransactionpayable(@Param("id") Long long1);
	// =================================================================== ending
	// ========================

	@Select("INSERT INTO __edit_history(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{reason_id},'delete' FROM data__ where id = #{id}")
	void moveDeletedData(@Param("id") Long id, @Param("reason_id") Long reason_id);

	@Select("INSERT INTO __edit_history(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{edit_reason_id},'new' FROM data__ where id = #{id}")
	void moveEditedData(@Param("id") Long id, @Param("edit_reason_id") Long edit_reason_id);

	@Select("select distinct dna.id,dna.additional_information,dna.amount,dna.dr_cr,dna.match_status,dna.REFERENCE,dna.sender,dna.receiver,dna.upload_date,dna.value_date_type,u.firstname,u.lastname  from data__ats dna,users u,file_user_date fud where  u.id =fud.user_id and dna.file_id=fud.file_id and dna.amount  between #{min_amount} and   #{max_amount}  and dna.REFERENCE like '%'+ #{reference} + '%'"
			+ " and dna.value_date_type like '%'+ #{value_date} + '%' and dna.status = 1 and dna.availability = 1 union "
			+ " select distinct rna.id,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname from rtgs__ats rna,users u,file_user_date fud, rtgs_matched rm where  u.id =fud.user_id and rna.file_id=fud.file_id  and rna.amount between   #{min_amount}  and  #{max_amount}"
			+ "  and  rna.REFERENCE like '%'+ #{reference} + '%' and rna.value_date_type like '%'+ #{value_date}+ '%' and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union "
			+ " select distinct bna.id, bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,users u,file_user_date fud, b2b_matched bm where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.amount  between  #{min_amount}   and  #{max_amount}  and bna.REFERENCE like '%'+ #{reference} + '%'"
			+ " and bna.value_date_type like '%'+ #{value_date}+ '%' and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1  union "
			+ " select distinct ena.id, ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.amount  between   #{min_amount}  and  #{max_amount}  "
			+ " and ena.REFERENCE like '%'+ #{reference} + '%' and ena.value_date_type like '%'+ #{value_date}+ '%' and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union "
			+ " select distinct sna.id, sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,users u,file_user_date fud, sos_matched sm where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.amount  between   #{min_amount}  and  #{max_amount} "
			+ " and sna.REFERENCE like '%'+ #{reference} + '%' and sna.value_date_type like '%'+ #{value_date}+ '%' and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1  union "
			+ " select distinct apm.id,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.amount  between   #{min_amount}  and  #{max_amount} "
			+ " and apm.REFERENCE like '%'+ #{reference} + '%' and apm.value_date_type like '%'+ #{value_date}+ '%' and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithAmount(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("reference") String reference,
			@Param("value_date") String value_date);

	// min
	@Select("select distinct dna.id,dna.additional_information,dna.amount,dna.dr_cr,dna.match_status,dna.REFERENCE,dna.sender,dna.receiver,dna.upload_date,dna.value_date_type,u.firstname,u.lastname  from data__ats dna,users u,file_user_date fud where  u.id =fud.user_id and dna.file_id=fud.file_id and dna.amount  >= #{min_amount}   and dna.REFERENCE like '%'+ #{reference} + '%'"
			+ " and dna.value_date_type like '%'+ #{value_date}+ '%'  and  dna.status = 1 and dna.availability = 1 union "
			+ " select distinct rna.id,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname from rtgs__ats rna,users u,file_user_date fud, rtgs_matched rm where  u.id =fud.user_id and rna.file_id=fud.file_id  and rna.amount >=   #{min_amount}  "
			+ "  and  rna.REFERENCE like '%'+ #{reference} + '%' and rna.value_date_type like '%'+ #{value_date}+ '%' and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union "
			+ " select distinct bna.id, bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,users u,file_user_date fud, b2b_matched bm where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.amount  >=  #{min_amount}   and bna.REFERENCE like '%'+ #{reference} + '%'"
			+ " and bna.value_date_type like '%'+ #{value_date}+ '%' and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1  union "
			+ " select distinct ena.id, ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.amount  >=   #{min_amount}   "
			+ " and ena.REFERENCE like '%'+ #{reference} + '%' and ena.value_date_type like '%'+ #{value_date}+ '%' and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union "
			+ " select distinct sna.id, sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,users u,file_user_date fud, sos_matched sm where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.amount  >=   #{min_amount}  "
			+ " and sna.REFERENCE like '%'+ #{reference} + '%' and sna.value_date_type like '%'+ #{value_date}+ '%' and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1  union "
			+ " select distinct apm.id,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.amount  >=  #{min_amount}  "
			+ " and apm.REFERENCE like '%'+ #{reference} + '%' and apm.value_date_type like '%'+ #{value_date}+ '%' and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithMinAmount(@Param("min_amount") float min_amount,
			@Param("reference") String reference, @Param("value_date") String value_date);

	// min
	// max
	@Select("select distinct dna.id,dna.additional_information,dna.amount,dna.dr_cr,dna.match_status,dna.REFERENCE,dna.sender,dna.receiver,dna.upload_date,dna.value_date_type,u.firstname,u.lastname  from data__ats dna,users u,file_user_date fud where  u.id =fud.user_id and dna.file_id=fud.file_id and dna.amount  <= #{max_amount}   and dna.REFERENCE like '%'+ #{reference} + '%'"
			+ " and dna.value_date_type like '%'+ #{value_date}+ '%' and  dna.status = 1 and dna.availability = 1  union "
			+ " select distinct rna.id,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname from rtgs__ats rna,users u,file_user_date fud, rtgs_matched rm where  u.id =fud.user_id and rna.file_id=fud.file_id  and rna.amount <=   #{max_amount}  "
			+ "  and  rna.REFERENCE like '%'+ #{reference} + '%' and rna.value_date_type like '%'+ #{value_date}+ '%' and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union "
			+ " select distinct bna.id, bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,users u,file_user_date fud, b2b_matched bm where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.amount  <=  #{max_amount}   and bna.REFERENCE like '%'+ #{reference} + '%'"
			+ " and bna.value_date_type like '%'+ #{value_date}+ '%' and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1  union "
			+ " select distinct ena.id, ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.amount  <=   #{max_amount}   "
			+ " and ena.REFERENCE like '%'+ #{reference} + '%' and ena.value_date_type like '%'+ #{value_date}+ '%' and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union "
			+ " select distinct sna.id, sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,users u,file_user_date fud, sos_matched sm where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.amount  <=   #{max_amount}  "
			+ " and sna.REFERENCE like '%'+ #{reference} + '%' and sna.value_date_type like '%'+ #{value_date}+ '%' and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1  union "
			+ " select distinct apm.id,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.amount  <=  #{max_amount}  "
			+ " and apm.REFERENCE like '%'+ #{reference} + '%' and apm.value_date_type like '%'+ #{value_date}+ '%' and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithMaxAmount(@Param("max_amount") float max_amount,
			@Param("reference") String reference, @Param("value_date") String value_date);

	// max

	@Select("select dna.id,dna.additional_information,dna.amount,dna.dr_cr,dna.match_status,dna.REFERENCE,dna.sender,dna.receiver,dna.upload_date,dna.value_date_type,u.firstname,u.lastname  from data__ats dna,users u,file_user_date fud  where  u.id =fud.user_id and dna.file_id=fud.file_id  and dna.upload_date between #{min_upload_date} and #{max_upload_date} and dna.REFERENCE like '%'+ #{reference}+ '%' and dna.value_date_type like '%'+ #{value_date}+'%'  and  dna.status = 1 and dna.availability = 1  union"
			+ " select rna.id,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna,users u,file_user_date fud, rtgs_matched rm  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.upload_date between #{min_upload_date} and #{max_upload_date} and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+'%' and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union"
			+ " select bna.id,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.upload_date between #{min_upload_date} and #{max_upload_date} and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+'%' and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1 union"
			+ " select ena.id, ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.upload_date between #{min_upload_date} and #{max_upload_date} and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+'%' and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1union"
			+ " select sna.id, sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.upload_date between #{min_upload_date} and #{max_upload_date} and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+'%' and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1 union"
			+ " select apm.id, apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,users u,file_user_date fud, partially_matched pm where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.upload_date between #{min_upload_date} and #{max_upload_date} and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+'%' and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithUploadDate(@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
			@Param("value_date") String value_date);

	@Select("select rna.id, rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rna.file_id=fud.file_id and  rm.match_date between #{min_match_date} and #{max_match_date} and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+'%'  and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union"
			+ " select bna.id,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bna.file_id=fud.file_id and  bm.match_date between #{min_match_date} and #{max_match_date}  and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+'%'  and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1 union"
			+ " select ena.id,ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and ena.file_id=fud.file_id and  em.match_date between #{min_match_date} and #{max_match_date} and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+'%'  and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1union"
			+ " select sna.id,sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sna.file_id=fud.file_id and  sm.match_date between #{min_match_date} and #{max_match_date} and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+'%'  and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1 union"
			+ " select apm.id,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and apm.file_id=fud.file_id and  pm.match_date between #{min_match_date} and #{max_match_date} and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+'%'  and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithMatchDate(@Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date);

	@Select("select distinct dna.id, dna.additional_information,dna.amount,dna.dr_cr,dna.match_status,dna.REFERENCE,dna.sender,dna.receiver,dna.upload_date,dna.value_date_type,u.firstname,u.lastname  from data__ats dna, users u,file_user_date fud  where  u.id =fud.user_id and dna.file_id=fud.file_id and dna.amount  between #{min_amount} and #{max_amount}  and dna.upload_date between #{min_upload_date} and #{max_upload_date} and dna.REFERENCE like '%'+ #{reference}+ '%' and dna.value_date_type like '%'+ #{value_date}+'%' and  dna.status = 1 and dna.availability = 1  union"
			+ " select distinct rna.id, rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna, users u,file_user_date fud, rtgs_matched rm  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.amount  between #{min_amount} and #{max_amount}  and rna.upload_date between #{min_upload_date} and #{max_upload_date} and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+'%'  and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union"
			+ " select distinct bna.id,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna, users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.amount  between #{min_amount} and #{max_amount}  and bna.upload_date between #{min_upload_date} and #{max_upload_date} and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+'%'  and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1 union"
			+ " select distinct ena.id,ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena, users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.amount  between #{min_amount} and #{max_amount}  and ena.upload_date between #{min_upload_date} and #{max_upload_date} and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+'%'  and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union"
			+ " select distinct sna.id,sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna, users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.amount  between #{min_amount} and #{max_amount}  and sna.upload_date between #{min_upload_date} and #{max_upload_date} and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+'%'  and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1 union"
			+ " select distinct apm.id ,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm, users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.amount  between #{min_amount} and #{max_amount}  and apm.upload_date between #{min_upload_date} and #{max_upload_date} and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+'%'  and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithAmountAndUploadDate(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
			@Param("value_date") String value_date);

	@Select("select distinct dna.id, dna.additional_information,dna.amount,dna.dr_cr,dna.match_status,dna.REFERENCE,dna.sender,dna.receiver,dna.upload_date,dna.value_date_type,u.firstname,u.lastname  from data__ats dna, users u,file_user_date fud  where  u.id =fud.user_id and dna.file_id=fud.file_id and dna.amount  >= #{min_amount} and dna.upload_date between #{min_upload_date} and #{max_upload_date} and dna.REFERENCE like '%'+ #{reference}+ '%' and dna.value_date_type like '%'+ #{value_date}+'%' and  dna.status = 1 and dna.availability = 1  union"
			+ " select distinct rna.id, rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna, users u,file_user_date fud, rtgs_matched rm  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.amount  >= #{min_amount}  and rna.upload_date between #{min_upload_date} and #{max_upload_date} and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+'%'  and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union"
			+ " select distinct bna.id,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna, users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.amount  >= #{min_amount} and bna.upload_date between #{min_upload_date} and #{max_upload_date} and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+'%'  and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1 union"
			+ " select distinct ena.id,ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena, users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.amount  >= #{min_amount}  and ena.upload_date between #{min_upload_date} and #{max_upload_date} and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+'%'  and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union"
			+ " select distinct sna.id,sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna, users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.amount  >= #{min_amount}  and sna.upload_date between #{min_upload_date} and #{max_upload_date} and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+'%'  and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1 union"
			+ " select distinct apm.id ,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm, users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.amount  >= #{min_amount} and apm.upload_date between #{min_upload_date} and #{max_upload_date} and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+'%'  and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithMinAmountAndUploadDate(@Param("min_amount") float min_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("reference") String reference, @Param("value_date") String value_date);

	@Select("select distinct dna.id, dna.additional_information,dna.amount,dna.dr_cr,dna.match_status,dna.REFERENCE,dna.sender,dna.receiver,dna.upload_date,dna.value_date_type,u.firstname,u.lastname  from data__ats dna, users u,file_user_date fud  where  u.id =fud.user_id and dna.file_id=fud.file_id and dna.amount  <= #{max_amount} and dna.upload_date between #{min_upload_date} and #{max_upload_date} and dna.REFERENCE like '%'+ #{reference}+ '%' and dna.value_date_type like '%'+ #{value_date}+'%' and  dna.status = 1 and dna.availability = 1  union"
			+ " select distinct rna.id, rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna, users u,file_user_date fud, rtgs_matched rm  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.amount  <= #{max_amount}  and rna.upload_date between #{min_upload_date} and #{max_upload_date} and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+'%'  and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union"
			+ " select distinct bna.id,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna, users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.amount  <= #{max_amount} and bna.upload_date between #{min_upload_date} and #{max_upload_date} and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+'%'  and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1 union"
			+ " select distinct ena.id,ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena, users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.amount  <= #{max_amount}  and ena.upload_date between #{min_upload_date} and #{max_upload_date} and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+'%'  and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union"
			+ " select distinct sna.id,sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna, users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.amount <= #{max_amount} and sna.upload_date between #{min_upload_date} and #{max_upload_date} and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+'%'  and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1 union"
			+ " select distinct apm.id ,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm, users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.amount <= #{max_amount} and apm.upload_date between #{min_upload_date} and #{max_upload_date} and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+'%'  and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithMaxAmountAndUploadDate(@Param("max_amount") float max_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("reference") String reference, @Param("value_date") String value_date);

	@Select("select distinct rna.id,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.amount between  #{min_amount} and  #{max_amount}  and rna.upload_date between #{min_upload_date} and #{min_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+'%' and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union"
			+ " select distinct bna.id,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.amount between  #{min_amount} and #{max_amount}  and bna.upload_date between #{min_upload_date} and #{max_upload_date} and bm.match_date between #{min_match_date} and #{max_match_date} and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+'%'  and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1 union"
			+ " select distinct ena.id ,ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.amount between  #{min_amount} and #{max_amount}  and ena.upload_date between #{min_upload_date} and #{max_upload_date} and em.match_date between #{min_match_date} and #{max_match_date} and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+'%'  and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union"
			+ " select distinct sna.id ,sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.amount between  #{min_amount} and #{max_amount}  and sna.upload_date between #{min_upload_date} and #{max_upload_date} and sm.match_date between #{min_match_date} and #{max_match_date} and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+'%'  and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1 union"
			+ " select distinct apm.id ,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.amount between  #{min_amount} and #{max_amount}  and apm.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+'%'  and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithAmountUploadDateAndMatchDate(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date);

	@Select("select distinct rna.id,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.amount >=  #{min_amount}  and rna.upload_date between #{min_upload_date} and #{min_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+'%' and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union"
			+ " select distinct bna.id,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.amount >=  #{min_amount}  and bna.upload_date between #{min_upload_date} and #{max_upload_date} and bm.match_date between #{min_match_date} and #{max_match_date} and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+'%'  and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1 union"
			+ " select distinct ena.id ,ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.amount >=  #{min_amount}  and ena.upload_date between #{min_upload_date} and #{max_upload_date} and em.match_date between #{min_match_date} and #{max_match_date} and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+'%'  and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union"
			+ " select distinct sna.id ,sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.amount >=  #{min_amount} and sna.upload_date between #{min_upload_date} and #{max_upload_date} and sm.match_date between #{min_match_date} and #{max_match_date} and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+'%'  and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1 union"
			+ " select distinct apm.id ,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.amount >=  #{min_amount} and apm.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+'%'  and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithMinAmountUploadDateAndMatchDate(@Param("min_amount") float min_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date);

	@Select("select distinct rna.id,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.amount <=  #{max_amount}  and rna.upload_date between #{min_upload_date} and #{min_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+'%' and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union"
			+ " select distinct bna.id,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.amount <=  #{max_amount}  and bna.upload_date between #{min_upload_date} and #{max_upload_date} and bm.match_date between #{min_match_date} and #{max_match_date} and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+'%'  and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1 union"
			+ " select distinct ena.id ,ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.amount <=  #{max_amount}  and ena.upload_date between #{min_upload_date} and #{max_upload_date} and em.match_date between #{min_match_date} and #{max_match_date} and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+'%'  and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union"
			+ " select distinct sna.id ,sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.amount <=  #{max_amount} and sna.upload_date between #{min_upload_date} and #{max_upload_date} and sm.match_date between #{min_match_date} and #{max_match_date} and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+'%'  and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1 union"
			+ " select distinct apm.id ,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.amount <=  #{max_amount} and apm.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+'%'  and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithMaxAmountUploadDateAndMatchDate(@Param("max_amount") float max_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date);

	@Select("select distinct rna.id ,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rna.file_id=fud.file_id and  rna.upload_date between #{min_upload_date} and #{max_upload_date}  and rm.match_date between #{min_match_date} and #{max_match_date} and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+'%'  and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union"
			+ " select distinct bna.id ,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bna.file_id=fud.file_id and  bna.upload_date between #{min_upload_date}  and #{max_upload_date}  and bm.match_date between #{min_match_date} and #{max_match_date} and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+'%'  and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1 union"
			+ " select distinct ena.id ,ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and ena.file_id=fud.file_id and  ena.upload_date between #{min_upload_date} and #{max_upload_date} and em.match_date between #{min_match_date} and #{max_match_date} and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+'%'  and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union"
			+ " select distinct sna.id ,sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sna.file_id=fud.file_id and  sna.upload_date between #{min_upload_date} and #{max_upload_date} and sm.match_date between #{min_match_date} and #{max_match_date} and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+'%'  and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1 union"
			+ " select distinct apm.id ,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and apm.file_id=fud.file_id and  apm.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+'%'  and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithUploadDateAndMatchDate(@Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
			@Param("value_date") String value_date);

	@Select("select distinct rna.id ,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.amount between #{min_amount} and #{max_amount} and rm.match_date between #{min_match_date} and #{max_match_date} and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+'%'  and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union"
			+ " select distinct bna.id ,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.amount between #{min_amount} and #{max_amount} and bm.match_date between #{min_match_date} and #{max_match_date} and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+'%'  and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1union"
			+ " select distinct ena.id,ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.amount between #{min_amount} and #{max_amount} and em.match_date between #{min_match_date} and #{max_match_date} and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+'%'  and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union"
			+ " select distinct sna.id,sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.amount between #{min_amount} and #{max_amount} and sm.match_date between #{min_match_date} and #{max_match_date} and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+'%'  and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1 union"
			+ " select distinct apm.id,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.amount between #{min_amount} and #{max_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+'%'  and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithAmountAndMatchDate(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date);

	@Select("select distinct rna.id ,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.amount >= #{min_amount} and rm.match_date between #{min_match_date} and #{max_match_date} and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+'%'  and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union"
			+ " select distinct bna.id ,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.amount >= #{min_amount} and bm.match_date between #{min_match_date} and #{max_match_date} and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+'%'  and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1union"
			+ " select distinct ena.id,ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.amount >= #{min_amount} and em.match_date between #{min_match_date} and #{max_match_date} and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+'%'  and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union"
			+ " select distinct sna.id,sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.amount >= #{min_amount} and sm.match_date between #{min_match_date} and #{max_match_date} and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+'%'  and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1 union"
			+ " select distinct apm.id,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.amount >= #{min_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+'%'  and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithMinAmountAndMatchDate(@Param("min_amount") float min_amount,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date);

	@Select("select distinct rna.id ,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.amount <= #{max_amount} and rm.match_date between #{min_match_date} and #{max_match_date} and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+'%'  and rm.rtgs__ats_id = rna.id and rna.availability = 1 and rna.status=1 union"
			+ " select distinct bna.id ,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.amount <= #{max_amount} and bm.match_date between #{min_match_date} and #{max_match_date} and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+'%'  and bm.b2b__ats_id = bna.id and bna.availability = 1 and bna.status=1union"
			+ " select distinct ena.id,ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.amount <= #{max_amount} and em.match_date between #{min_match_date} and #{max_match_date} and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+'%'  and em.erca__ats_id = ena.id and ena.availability = 1 and ena.status=1 union"
			+ " select distinct sna.id,sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sna.file_id=fud.file_id and sna.amount <= #{max_amount} and sm.match_date between #{min_match_date} and #{max_match_date} and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+'%'  and sm.sos__ats_id = sna.id and sna.availability = 1 and sna.status=1 union"
			+ " select distinct apm.id,apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.amount <= #{max_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+'%'  and pm.ats_id = apm.id and apm.availability = 1 and apm.status=1")
	public List<Transactionhistory> searchWithMaxAmountAndMatchDate(@Param("max_amount") float max_amount,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date);

	@Select("select distinct dac.id,dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac,users u,file_user_date fud where  u.id =fud.user_id and dac.file_id=fud.file_id and dac.amount  between #{min_amount} and #{max_amount}  and dac.additional_information like '%'+ #{reference} + '%' and dac.value_date like '%'+#{value_date}+ '%' and dac.branch_code like '%'+#{branch_code}+'%'  and dac.status = 1 and dac.availability = 1 union"
			+ " select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname from rtgs__ rac,users u,file_user_date fud, rtgs_matched rm where  u.id =fud.user_id and rac.file_id=fud.file_id  and rac.amount between  #{min_amount} and #{max_amount} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%' and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1 union"
			+ " select distinct bac.id, bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.amount  between #{min_amount} and #{max_amount} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%'  and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id, eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.amount  between #{min_amount} and #{max_amount} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%' and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id, sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.amount  between #{min_amount} and #{max_amount} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id, cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr,users u,file_user_date fud  where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.amount  between #{min_amount} and #{max_amount} and cr.additional_information like '%'+ #{reference} + '%' and cr.posting_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%' and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.amount  between #{min_amount} and #{max_amount} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithAmount(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	// min 
	@Select("select distinct dac.id,dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac,users u,file_user_date fud where  u.id =fud.user_id and dac.file_id=fud.file_id and dac.amount  >= #{min_amount} and dac.additional_information like '%'+ #{reference} + '%' and dac.value_date like '%'+#{value_date}+ '%' and dac.branch_code like '%'+#{branch_code}+'%'  and dac.status = 1 and dac.availability = 1  union"
			+ " select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname from rtgs__ rac,users u,file_user_date fud, rtgs_matched rm where  u.id =fud.user_id and rac.file_id=fud.file_id  and rac.amount >=  #{min_amount}  and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%' and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1 union"
			+ " select distinct bac.id, bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.amount  >= #{min_amount} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%'  and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id, eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.amount  >= #{min_amount} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%' and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id, sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.amount  >= #{min_amount} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id, cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr,users u,file_user_date fud where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.amount  >= #{min_amount} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%'  and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.amount  >= #{min_amount} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithMinAmount(@Param("min_amount") float min_amount,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);
	// min 

	// max 
	@Select("select distinct dac.id,dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac,users u,file_user_date fud where  u.id =fud.user_id and dac.file_id=fud.file_id and dac.amount  <= #{max_amount} and dac.additional_information like '%'+ #{reference} + '%' and dac.value_date like '%'+#{value_date}+ '%' and dac.branch_code like '%'+#{branch_code}+'%' and  dac.status = 1 and dac.availability = 1  union"
			+ " select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname from rtgs__ rac,users u,file_user_date fud, rtgs_matched rm where  u.id =fud.user_id and rac.file_id=fud.file_id  and rac.amount <=  #{max_amount}  and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%' and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1 union"
			+ " select distinct bac.id, bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.amount  <= #{max_amount} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%'  and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id, eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.amount  <= #{max_amount} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%' and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id, sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.amount  <= #{max_amount} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id, cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr,users u,file_user_date fud where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.amount  <= #{max_amount} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%'  and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.amount  <= #{max_amount} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithMaxAmount(@Param("max_amount") float max_amount,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	// max 
	@Select("select dac.id,dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac,users u,file_user_date fud  where  u.id =fud.user_id and dac.file_id=fud.file_id  and dac.upload_date between #{min_upload_date} and #{max_upload_date} and dac.additional_information like '%'+ #{reference} + '%' and dac.value_date like '%'+#{value_date}+ '%' and dac.branch_code like '%'+#{branch_code}+'%' and  dac.status = 1 and dac.availability = 1  union"
			+ " select rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname  from rtgs__ rac,users u,file_user_date fud, rtgs_matched rm  where  u.id =fud.user_id and rac.file_id=fud.file_id and rac.upload_date between #{min_upload_date} and #{max_upload_date} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%' and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1 union"
			+ " select bac.id,bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.upload_date between #{min_upload_date} and #{max_upload_date} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%'  and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select eac.id, eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.upload_date between #{min_upload_date} and #{max_upload_date} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%'  and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select sac.id, sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,users u,file_user_date fud, sos_matched sm where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.upload_date between #{min_upload_date} and #{max_upload_date} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select cr.id, cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr,users u,file_user_date fud where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.upload_date between #{min_upload_date} and #{max_upload_date} and cr.additional_information like '%'+ #{reference} + '%' and cr.posting_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%' and cr.availability = 1 and cr.status=1 union"
			+ " select cpm.id, cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.upload_date between #{min_upload_date} and #{max_upload_date} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithUploadDate(@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select rac.id, rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname  from rtgs__ rac,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rac.file_id=fud.file_id and  rm.match_date between #{min_match_date} and #{max_match_date} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%' and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1 union"
			+ " select bac.id,bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bac.file_id=fud.file_id and  bm.match_date between #{min_match_date} and #{max_match_date} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%'  and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select eac.id,eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and eac.file_id=fud.file_id and  em.match_date between #{min_match_date} and #{max_match_date} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%'  and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select sac.id,sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sac.file_id=fud.file_id and  sm.match_date between #{min_match_date} and #{max_match_date} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select cr.id,cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr, users u,file_user_date fud  where  u.id =fud.user_id and cr.file_id=fud.file_id and  cr.match_date between #{min_match_date} and #{max_match_date} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%'  and cr.availability = 1 and cr.status=1 union"
			+ " select cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and cpm.file_id=fud.file_id and  pm.match_date between #{min_match_date} and #{max_match_date} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithMatchDate(@Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dac.id, dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac, users u,file_user_date fud  where  u.id =fud.user_id and dac.file_id=fud.file_id and dac.amount  between #{min_amount} and  #{max_amount}  and dac.upload_date between #{min_upload_date} and #{max_upload_date} and dac.additional_information like '%'+ #{reference} + '%' and dac.value_date like '%'+#{value_date}+ '%' and dac.branch_code like '%'+#{branch_code}+'%'  and  dac.status = 1 and dac.availability = 1  union"
			+ " select distinct rac.id, rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname  from rtgs__ rac, users u,file_user_date fud, rtgs_matched rm  where  u.id =fud.user_id and rac.file_id=fud.file_id and rac.amount  between #{min_amount} and #{max_amount}  and rac.upload_date between #{min_upload_date} and #{max_upload_date} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%' and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1union"
			+ " select distinct bac.id,bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac, users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.amount  between #{min_amount} and #{max_amount}  and bac.upload_date between #{min_upload_date} and #{max_upload_date} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%'  and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id,eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac, users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.amount  between #{min_amount} and #{max_amount}  and eac.upload_date between #{min_upload_date} and #{max_upload_date} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%'  and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id,sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.value_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac, users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.amount  between #{min_amount} and #{max_amount}  and sac.upload_date between #{min_upload_date} and #{max_upload_date} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id,cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.value_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr, users u,file_user_date fud  where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.amount  between #{min_amount} and #{max_amount}  and cr.upload_date between #{min_upload_date} and #{max_upload_date} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%'  and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id ,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm, users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.amount  between #{min_amount} and #{max_amount}  and cpm.upload_date between #{min_upload_date} and #{max_upload_date} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithAmountAndUploadDate(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dac.id, dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac, users u,file_user_date fud  where  u.id =fud.user_id and dac.file_id=fud.file_id and dac.amount  >= #{min_amount} and dac.upload_date between #{min_upload_date} and #{max_upload_date} and dac.additional_information like '%'+ #{reference} + '%' and dac.value_date like '%'+#{value_date}+ '%' and dac.branch_code like '%'+#{branch_code}+'%'  and  dac.status = 1 and dac.availability = 1  union"
			+ " select distinct rac.id, rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname  from rtgs__ rac, users u,file_user_date fud, rtgs_matched rm  where  u.id =fud.user_id and rac.file_id=fud.file_id and rac.amount   >= #{min_amount}  and rac.upload_date between #{min_upload_date} and #{max_upload_date} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%' and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1union"
			+ " select distinct bac.id,bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac, users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.amount  >= #{min_amount}  and bac.upload_date between #{min_upload_date} and #{max_upload_date} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%'  and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id,eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac, users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.amount  >= #{min_amount} and eac.upload_date between #{min_upload_date} and #{max_upload_date} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%'  and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id,sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.value_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac, users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.amount  >= #{min_amount}  and sac.upload_date between #{min_upload_date} and #{max_upload_date} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id,cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.value_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr, users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.amount  >= #{min_amount}  and cr.upload_date between #{min_upload_date} and #{max_upload_date} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%'  and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id ,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm, users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.amount   >= #{min_amount}  and cpm.upload_date between #{min_upload_date} and #{max_upload_date} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithMinAmountAndUploadDate(@Param("min_amount") float min_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dac.id, dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac, users u,file_user_date fud  where  u.id =fud.user_id and dac.file_id=fud.file_id and dac.amount  <= #{max_amount} and dac.upload_date between #{min_upload_date} and #{max_upload_date} and dac.additional_information like '%'+ #{reference} + '%' and dac.value_date like '%'+#{value_date}+ '%' and dac.branch_code like '%'+#{branch_code}+'%'  and  dac.status = 1 and dac.availability = 1  union"
			+ " select distinct rac.id, rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname  from rtgs__ rac, users u,file_user_date fud, rtgs_matched rm  where  u.id =fud.user_id and rac.file_id=fud.file_id and rac.amount   <= #{max_amount}  and rac.upload_date between #{min_upload_date} and #{max_upload_date} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%' and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1union"
			+ " select distinct bac.id,bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac, users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.amount  <= #{max_amount}  and bac.upload_date between #{min_upload_date} and #{max_upload_date} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%'  and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id,eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac, users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.amount <= #{max_amount} and eac.upload_date between #{min_upload_date} and #{max_upload_date} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%'  and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id,sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.value_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac, users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.amount  <= #{max_amount}  and sac.upload_date between #{min_upload_date} and #{max_upload_date} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id,cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.value_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr, users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.amount  <= #{max_amount}  and cr.upload_date between #{min_upload_date} and #{max_upload_date} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%'  and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id ,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm, users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.amount   <= #{max_amount}  and cpm.upload_date between #{min_upload_date} and #{max_upload_date} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithMaxAmountAndUploadDate(@Param("max_amount") float max_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname  from rtgs__ rac,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rac.file_id=fud.file_id and rac.amount between  #{min_amount} and #{max_amount}  and rac.upload_date between #{min_upload_date} and #{max_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1 union"
			+ " select distinct bac.id,bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.amount between  #{min_amount} and #{max_amount}  and bac.upload_date between #{min_upload_date} and #{max_upload_date} and bm.match_date between #{min_match_date} and #{max_match_date} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%'  and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id ,eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.amount between  #{min_amount} and #{max_amount}  and eac.upload_date between #{min_upload_date} and #{max_upload_date} and em.match_date between #{min_match_date} and #{max_match_date} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%'  and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id ,sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.amount between  #{min_amount} and #{max_amount}  and sac.upload_date between #{min_upload_date} and #{max_upload_date} and sm.match_date between #{min_match_date} and #{max_match_date} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id ,cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr, users u,file_user_date fud  where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.amount between  #{min_amount} and #{max_amount}  and cr.upload_date between #{min_upload_date} and #{max_upload_date} and cr.match_date between #{min_match_date} and #{max_match_date} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%'  and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id ,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.amount between  #{min_amount} and #{max_amount}  and cpm.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithAmountUploadDateAndMatchDate(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname  from rtgs__ rac,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rac.file_id=fud.file_id and rac.amount >=  #{min_amount} and rac.upload_date between #{min_upload_date} and #{max_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1 union"
			+ " select distinct bac.id,bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.amount >=  #{min_amount}  and bac.upload_date between #{min_upload_date} and #{max_upload_date} and bm.match_date between #{min_match_date} and #{max_match_date} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%'  and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id ,eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.amount >=  #{min_amount} and eac.upload_date between #{min_upload_date} and #{max_upload_date} and em.match_date between #{min_match_date} and #{max_match_date} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%'  and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id ,sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.amount >=  #{min_amount}  and sac.upload_date between #{min_upload_date} and #{max_upload_date} and sm.match_date between #{min_match_date} and #{max_match_date} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id ,cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr, users u,file_user_date fud  where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.amount >=  #{min_amount}  and cr.upload_date between #{min_upload_date} and #{max_upload_date} and cr.match_date between #{min_match_date} and #{max_match_date} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%' and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id ,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.amount >=  #{min_amount}  and cpm.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithMinAmountUploadDateAndMatchDate(@Param("min_amount") float min_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname  from rtgs__ rac,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rac.file_id=fud.file_id and rac.amount <=  #{max_amount} and rac.upload_date between #{min_upload_date} and #{max_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1 union"
			+ " select distinct bac.id,bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.amount <=  #{max_amount}  and bac.upload_date between #{min_upload_date} and #{max_upload_date} and bm.match_date between #{min_match_date} and #{max_match_date} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%'  and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id ,eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.amount <=  #{max_amount} and eac.upload_date between #{min_upload_date} and #{max_upload_date} and em.match_date between #{min_match_date} and #{max_match_date} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%'  and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id ,sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.amount <=  #{max_amount}  and sac.upload_date between #{min_upload_date} and #{max_upload_date} and sm.match_date between #{min_match_date} and #{max_match_date} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id ,cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr, users u,file_user_date fud  where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.amount <=  #{max_amount}  and cr.upload_date between #{min_upload_date} and #{max_upload_date} and cr.match_date between #{min_match_date} and #{max_match_date} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%' and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id ,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.amount <=  #{max_amount}  and cpm.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithMaxAmountUploadDateAndMatchDate(@Param("max_amount") float max_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct rac.id ,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname  from rtgs__ rac,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rac.file_id=fud.file_id and  rac.upload_date between #{min_upload_date} and #{max_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1 union"
			+ " select distinct bac.id ,bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bac.file_id=fud.file_id and  bac.upload_date between #{min_upload_date} and #{max_upload_date} and bm.match_date between #{min_match_date} and #{max_match_date} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%'  and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id ,eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and eac.file_id=fud.file_id and  eac.upload_date between #{min_upload_date} and #{max_upload_date} and em.match_date between #{min_match_date} and #{max_match_date} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%'  and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id ,sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sac.file_id=fud.file_id and  sac.upload_date between #{min_upload_date} and #{max_upload_date} and sm.match_date between #{min_match_date} and #{max_match_date} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id ,cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr, users u,file_user_date fud  where  u.id =fud.user_id and cr.file_id=fud.file_id and  cr.upload_date between #{min_upload_date} and #{max_upload_date} and cr.match_date between #{min_match_date} and #{max_match_date} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%' and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id ,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and cpm.file_id=fud.file_id and  cpm.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithUploadDateAndMatchDate(@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct rac.id ,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname  from rtgs__ rac,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rac.file_id=fud.file_id and rac.amount between #{min_amount} and #{max_amount} and rm.match_date between #{min_match_date} and #{max_match_date} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1 union"
			+ " select distinct bac.id ,bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.amount between #{min_amount} and #{max_amount} and bm.match_date between #{min_match_date} and #{max_match_date} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%' and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id,eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.amount between #{min_amount} and #{max_amount} and em.match_date between #{min_match_date} and #{max_match_date} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%'  and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id,sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.amount between #{min_amount} and #{max_amount} and sm.match_date between #{min_match_date} and #{max_match_date} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id,cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr, users u,file_user_date fud  where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.amount between #{min_amount} and #{max_amount} and cr.match_date between #{min_match_date} and #{max_match_date} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%' and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.amount between #{min_amount} and #{max_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithAmountAndMatchDate(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct rac.id ,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname  from rtgs__ rac,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rac.file_id=fud.file_id and rac.amount >= #{min_amount} and rm.match_date between #{min_match_date} and #{max_match_date} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1 union"
			+ " select distinct bac.id ,bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.amount >= #{min_amount} and bm.match_date between #{min_match_date} and #{max_match_date} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%' and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id,eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.amount >= #{min_amount} and em.match_date between #{min_match_date} and #{max_match_date} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%'  and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id,sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.amount >= #{min_amount} and sm.match_date between #{min_match_date} and #{max_match_date} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id,cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr, users u,file_user_date fud  where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.amount >= #{min_amount} and cr.match_date between #{min_match_date} and #{max_match_date} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%' and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.amount >= #{min_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithMinAmountAndMatchDate(@Param("min_amount") float min_amount,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct rac.id ,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname  from rtgs__ rac,rtgs_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rac.file_id=fud.file_id and rac.amount <= #{max_amount} and rm.match_date between #{min_match_date} and #{max_match_date} and rac.additional_information like '%'+ #{reference} + '%' and rac.value_date like '%'+#{value_date}+ '%' and rac.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rac.match_id and rac.availability = 1 and rac.status=1 union"
			+ " select distinct bac.id ,bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,b2b_matched bm, users u,file_user_date fud  where  u.id =fud.user_id and bac.file_id=fud.file_id and bac.amount <= #{max_amount} and bm.match_date between #{min_match_date} and #{max_match_date} and bac.additional_information like '%'+ #{reference} + '%' and bac.value_date like '%'+#{value_date}+ '%' and bac.branch_code like '%'+#{branch_code}+'%' and bm.match_id = bac.match_id and bac.availability = 1 and bac.status=1 union"
			+ " select distinct eac.id,eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,erca_matched em, users u,file_user_date fud  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.amount <= #{max_amount} and em.match_date between #{min_match_date} and #{max_match_date} and eac.additional_information like '%'+ #{reference} + '%' and eac.value_date like '%'+#{value_date}+ '%' and eac.branch_code like '%'+#{branch_code}+'%'  and em.match_id = eac.match_id and eac.availability = 1 and eac.status=1 union"
			+ " select distinct sac.id,sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,sos_matched sm, users u,file_user_date fud  where  u.id =fud.user_id and sac.file_id=fud.file_id and sac.amount <= #{max_amount} and sm.match_date between #{min_match_date} and #{max_match_date} and sac.additional_information like '%'+ #{reference} + '%' and sac.value_date like '%'+#{value_date}+ '%' and sac.branch_code like '%'+#{branch_code}+'%'  and sm.match_id = sac.match_id and sac.availability = 1 and sac.status=1 union"
			+ " select distinct cr.id,cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr, users u,file_user_date fud  where  u.id =fud.user_id and cr.file_id=fud.file_id and cr.amount <= #{max_amount} and cr.match_date between #{min_match_date} and #{max_match_date} and cr.additional_information like '%'+ #{reference} + '%' and cr.value_date like '%'+#{value_date}+ '%' and cr.branch_code like '%'+#{branch_code}+'%' and cr.availability = 1 and cr.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,partially_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.amount <= #{max_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+#{value_date}+ '%' and cpm.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = cpm.match_id and cpm.availability = 1 and cpm.status=1")
	public List<Transactionhistory> searchWithMaxAmountAndMatchDate(@Param("max_amount") float max_amount,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select dna.id,dna.additional_information,dna.amount,dna.dr_cr,dna.match_status,dna.REFERENCE,dna.sender,dna.receiver,dna.upload_date,dna.value_date_type,u.firstname,u.lastname  from data__ats dna,users u,file_user_date fud  where  u.id =fud.user_id and dna.file_id=fud.file_id  and dna.REFERENCE like '%'+ #{reference}+ '%'  and dna.status = 1 and dna.availability = 1  union"
			+ " select rna.id,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna,users u,file_user_date fud, rtgs_matched rm  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.REFERENCE like '%'+ #{reference}+ '%' and rm.rtgs__ats_id = rna.id and rna.availability =1 and rna.status =1  union"
			+ " select bna.id,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.REFERENCE like '%'+ #{reference}+ '%' and bm.b2b__ats_id = bna.id and bna.availability =1 and bna.status =1  union"
			+ " select ena.id, ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.REFERENCE like '%'+ #{reference}+ '%' and em.erca__ats_id = ena.id and ena.availability =1 and ena.status =1 union"
			+ " select sna.id, sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sna.file_id=fud.file_id  and sna.REFERENCE like '%'+ #{reference}+ '%' and sm.sos__ats_id = sna.id and sna.availability =1 and sna.status =1 union"
			+ " select apm.id, apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.REFERENCE like '%'+ #{reference}+ '%' and pm.ats_id = apm.id and apm.availability =1 and apm.status =1 ")
	public List<Transactionhistory> searchWithReference(String reference);

	@Select("select dna.id,dna.additional_information,dna.amount,dna.dr_cr,dna.match_status,dna.REFERENCE,dna.sender,dna.receiver,dna.upload_date,dna.value_date_type, u.firstname,u.lastname  from data__ats dna,users u,file_user_date fud  where  u.id =fud.user_id and dna.file_id=fud.file_id  and dna.value_date_type like '%'+ #{value_date}+ '%' and  dna.status = 1 and dna.availability = 1  union"
			+ " select rna.id,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type, u.firstname,u.lastname  from rtgs__ats rna,users u,file_user_date fud, rtgs_matched rm  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.value_date_type like '%'+ #{value_date}+ '%' and rm.rtgs__ats_id = rna.id and rna.availability =1 and rna.status =1  union"
			+ " select bna.id,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type, u.firstname,u.lastname  from b2b__ats bna,users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.value_date_type like '%'+ #{value_date}+ '%' and bm.b2b__ats_id = bna.id and bna.availability =1 and bna.status =1  union"
			+ " select ena.id, ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type, u.firstname,u.lastname  from erca__ats ena,users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.value_date_type like '%'+ #{value_date}+ '%' and em.erca__ats_id = ena.id and ena.availability =1 and ena.status =1 union"
			+ " select sna.id, sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type, u.firstname,u.lastname  from sos__ats sna,users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sna.file_id=fud.file_id  and sna.value_date_type like '%'+ #{value_date}+ '%' and sm.sos__ats_id = sna.id and sna.availability =1 and sna.status =1 union"
			+ " select apm.id, apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type, u.firstname,u.lastname  from ats_partially_matched apm,users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.value_date_type like '%'+ #{value_date}+ '%' and pm.ats_id = apm.id and apm.availability =1 and apm.status =1 ")
	public List<Transactionhistory> searchWithValueDate(String value_date);

	@Select("select dna.id,dna.additional_information,dna.amount,dna.dr_cr,dna.match_status,dna.REFERENCE,dna.sender,dna.receiver,dna.upload_date,dna.value_date_type,u.firstname,u.lastname  from data__ats dna,users u,file_user_date fud  where  u.id =fud.user_id and dna.file_id=fud.file_id  and dna.REFERENCE like '%'+ #{reference}+ '%' and dna.value_date_type like '%'+ #{value_date}+ '%' and  dna.status = 1 and dna.availability = 1  union"
			+ " select rna.id,rna.additional_information,rna.amount,rna.dr_cr,rna.match_status,rna.REFERENCE,rna.sender,rna.receiver,rna.upload_date,rna.value_date_type,u.firstname,u.lastname  from rtgs__ats rna,users u,file_user_date fud, rtgs_matched rm  where  u.id =fud.user_id and rna.file_id=fud.file_id and rna.REFERENCE like '%'+ #{reference}+ '%' and rna.value_date_type like '%'+ #{value_date}+ '%' and rm.rtgs__ats_id = rna.id and rna.availability =1 and rna.status =1  union"
			+ " select bna.id,bna.additional_information,bna.amount,bna.dr_cr,bna.match_status,bna.REFERENCE,bna.sender,bna.receiver,bna.upload_date,bna.value_date_type,u.firstname,u.lastname  from b2b__ats bna,users u,file_user_date fud, b2b_matched bm  where  u.id =fud.user_id and bna.file_id=fud.file_id and bna.REFERENCE like '%'+ #{reference}+ '%' and bna.value_date_type like '%'+ #{value_date}+ '%' and bm.b2b__ats_id = bna.id and bna.availability =1 and bna.status =1  union"
			+ " select ena.id, ena.additional_information,ena.amount,ena.dr_cr,ena.match_status,ena.REFERENCE,ena.sender,ena.receiver,ena.upload_date,ena.value_date_type,u.firstname,u.lastname  from erca__ats ena,users u,file_user_date fud, erca_matched em  where  u.id =fud.user_id and ena.file_id=fud.file_id and ena.REFERENCE like '%'+ #{reference}+ '%' and ena.value_date_type like '%'+ #{value_date}+ '%' and em.erca__ats_id = ena.id and ena.availability =1 and ena.status =1 union"
			+ " select sna.id, sna.additional_information,sna.amount,sna.dr_cr,sna.match_status,sna.REFERENCE,sna.sender,sna.receiver,sna.upload_date,sna.value_date_type,u.firstname,u.lastname  from sos__ats sna,users u,file_user_date fud, sos_matched sm  where  u.id =fud.user_id and sna.file_id=fud.file_id  and sna.REFERENCE like '%'+ #{reference}+ '%' and sna.value_date_type like '%'+ #{value_date}+ '%' and sm.sos__ats_id = sna.id and sna.availability =1 and sna.status =1 union"
			+ " select apm.id, apm.additional_information,apm.amount,apm.dr_cr,apm.match_status,apm.REFERENCE,apm.sender,apm.receiver,apm.upload_date,apm.value_date_type,u.firstname,u.lastname  from ats_partially_matched apm,users u,file_user_date fud, partially_matched pm  where  u.id =fud.user_id and apm.file_id=fud.file_id and apm.REFERENCE like '%'+ #{reference}+ '%' and apm.value_date_type like '%'+ #{value_date}+ '%' and pm.ats_id = apm.id and apm.availability =1 and apm.status =1 ")
	public List<Transactionhistory> searchWithValueDateAndReference(@Param("value_date") String value_date,
			@Param("reference") String reference);

	@Select("select distinct dac.id,dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac,users u,file_user_date fud  where  u.id =fud.user_id and dac.file_id=fud.file_id and dac.additional_information like '%'+ #{reference} + '%' and  dac.status = 1 and dac.availability = 1 union"
			+ " select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname from rtgs__ rac,users u,file_user_date fud,rtgs_matched rm where  u.id =fud.user_id and rac.file_id=fud.file_id  and rac.additional_information like '%'+ #{reference} + '%'  and rm.match_id = rac.match_id and rac.availability=1 and rac.status=1 union"
			+ " select distinct bac.id, bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,users u,file_user_date fud,b2b_matched bm  where  u.id =fud.user_id and bac.file_id=fud.file_id  and bac.additional_information like '%'+ #{reference} + '%'  and bm.match_id = bac.match_id and  bac.availability=1 and bac.status=1 union"
			+ " select distinct eac.id, eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,users u,file_user_date fud,erca_matched em   where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.additional_information like '%'+ #{reference} + '%' and em.match_id = eac.match_id  and  eac.availability=1 and eac.status=1 union"
			+ " select distinct sac.id, sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,users u,file_user_date fud,sos_matched sm  where  u.id =fud.user_id and sac.file_id=fud.file_id  and sac.additional_information like '%'+ #{reference} + '%'  and sm.match_id = sac.match_id and  sac.availability=1 and sac.status=1 union"
			+ " select distinct cr.id, cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr,users u,file_user_date fud  where  u.id =fud.user_id and cr.file_id=fud.file_id  and cr.additional_information like '%'+ #{reference} + '%' and  cr.availability=1 and cr.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,users u,file_user_date fud,partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.additional_information like '%'+ #{reference} + '%' and pm.match_id = cpm.match_id and  cpm.availability=1 and cpm.status=1")
	List<Transactionhistory> searchWithReference(String reference);

	@Select("select distinct dac.id,dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac,users u,file_user_date fud  where  u.id =fud.user_id and dac.file_id=fud.file_id and dac.value_date like '%'+ #{value_date} + '%' and  dac.status = 1 and dac.availability = 1 union"
			+ " select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname from rtgs__ rac,users u,file_user_date fud ,rtgs_matched rm where  u.id =fud.user_id and rac.file_id=fud.file_id  and rac.value_date like '%'+ #{value_date} + '%' and rm.match_id = rac.match_id and rac.availability=1 and rac.status=1 union"
			+ " select distinct bac.id, bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,users u,file_user_date fud ,b2b_matched bm where  u.id =fud.user_id and bac.file_id=fud.file_id  and bac.value_date like '%'+ #{value_date} + '%' and bm.match_id = bac.match_id and  bac.availability=1 and bac.status=1 union"
			+ " select distinct eac.id, eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,users u,file_user_date fud,erca_matched em  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.value_date like '%'+ #{value_date} + '%' and em.match_id = eac.match_id and  eac.availability=1 and eac.status=1 union"
			+ " select distinct sac.id, sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,users u,file_user_date fud,sos_matched sm  where  u.id =fud.user_id and sac.file_id=fud.file_id  and sac.value_date like '%'+ #{value_date} + '%' and sm.match_id = sac.match_id and  sac.availability=1 and sac.status=1 union"
			+ " select distinct cr.id, cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr,users u,file_user_date fud where  u.id =fud.user_id and cr.file_id=fud.file_id  and cr.posting_date like '%'+ #{value_date} + '%' and  cr.availability=1 and cr.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,users u,file_user_date fud,partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.value_date like '%'+ #{value_date} + '%' and pm.match_id = cpm.match_id and  cpm.availability=1 and cpm.status=1")
	List<Transactionhistory> searchWithValueDate(String value_date);

	@Select("select distinct dac.id,dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac,users u,file_user_date fud  where  u.id =fud.user_id and dac.file_id=fud.file_id and dac.branch_code like '%'+ #{branch_code} + '%'  and dac.status = 1 and dac.availability = 1 union"
			+ " select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname from rtgs__ rac,users u,file_user_date fud,rtgs_matched rm where  u.id =fud.user_id and rac.file_id=fud.file_id  and rac.branch_code like '%'+ #{branch_code} + '%' and rm.match_id = rac.match_id and rac.availability=1 and rac.status=1 union"
			+ " select distinct bac.id, bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,users u,file_user_date fud,b2b_matched bm  where  u.id =fud.user_id and bac.file_id=fud.file_id  and bac.branch_code like '%'+ #{branch_code} + '%' and bm.match_id = bac.match_id and  bac.availability=1 and bac.status=1 union"
			+ " select distinct eac.id, eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,users u,file_user_date fud,erca_matched em  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.branch_code like '%'+ #{branch_code} + '%' and em.match_id = eac.match_id and  eac.availability=1 and eac.status=1 union"
			+ " select distinct sac.id, sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,users u,file_user_date fud,sos_matched sm  where  u.id =fud.user_id and sac.file_id=fud.file_id  and sac.branch_code like '%'+ #{branch_code} + '%' and sm.match_id = sac.match_id and  sac.availability=1 and sac.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,users u,file_user_date fud,partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.branch_code like '%'+ #{branch_code} + '%' and pm.match_id = cpm.match_id and  cpm.availability=1 and cpm.status=1")
	List<Transactionhistory> searchWithBranchCode(String branch_code);

	@Select("select distinct dac.id,dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac,users u,file_user_date fud  where  u.id =fud.user_id and dac.file_id=fud.file_id and dac.branch_code like '%'+ #{branch_code} + '%'  and dac.additional_information like '%'+ #{reference} + '%'  and dac.value_date like '%'+ #{value_date} + '%' and  dac.status = 1 and dac.availability = 1 union"
			+ " select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname from rtgs__ rac,users u,file_user_date fud ,rtgs_matched rm where  u.id =fud.user_id and rac.file_id=fud.file_id  and rac.branch_code like '%'+ #{branch_code} + '%' and rac.additional_information like '%'+ #{reference} + '%'  and rac.value_date like '%'+ #{value_date} + '%' and rm.match_id = rac.match_id and rac.availability=1 and rac.status=1 union"
			+ " select distinct bac.id, bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,users u,file_user_date fud ,b2b_matched bm where  u.id =fud.user_id and bac.file_id=fud.file_id  and bac.branch_code like '%'+ #{branch_code} + '%' and bac.additional_information like '%'+ #{reference} + '%'  and bac.value_date like '%'+ #{value_date} + '%'  and bm.match_id = bac.match_id and  bac.availability=1 and bac.status=1 union"
			+ " select distinct eac.id, eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,users u,file_user_date fud ,erca_matched em where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.branch_code like '%'+ #{branch_code} + '%' and eac.additional_information like '%'+ #{reference} + '%'  and eac.value_date like '%'+ #{value_date} + '%' and em.match_id = eac.match_id and  eac.availability=1 and eac.status=1 union"
			+ " select distinct sac.id, sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,users u,file_user_date fud,sos_matched sm  where  u.id =fud.user_id and sac.file_id=fud.file_id  and sac.branch_code like '%'+ #{branch_code} + '%' and sac.additional_information like '%'+ #{reference} + '%'  and sac.value_date like '%'+ #{value_date} + '%'  and sm.match_id = sac.match_id and  sac.availability=1 and sac.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,users u,file_user_date fud,partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.branch_code like '%'+ #{branch_code} + '%' and cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+ #{value_date} + '%' and pm.match_id = cpm.match_id and  cpm.availability=1 and cpm.status=1")
	List<Transactionhistory> searchWithRefValueDateBranchCode(@Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dac.id,dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac,users u,file_user_date fud  where  u.id =fud.user_id and dac.file_id=fud.file_id  and dac.additional_information like '%'+ #{reference} + '%'  and dac.value_date like '%'+ #{value_date} + '%' and  dac.status = 1 and dac.availability = 1 union"
			+ " select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname from rtgs__ rac,users u,file_user_date fud,rtgs_matched rm where  u.id =fud.user_id and rac.file_id=fud.file_id  and rac.additional_information like '%'+ #{reference} + '%'  and rac.value_date like '%'+ #{value_date} + '%' and rm.match_id = rac.match_id and rac.availability=1 and rac.status=1 union"
			+ " select distinct bac.id, bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,users u,file_user_date fud,b2b_matched bm  where  u.id =fud.user_id and bac.file_id=fud.file_id  and bac.additional_information like '%'+ #{reference} + '%'  and bac.value_date like '%'+ #{value_date} + '%' and bm.match_id = bac.match_id and  bac.availability=1 and bac.status=1 union"
			+ " select distinct eac.id, eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,users u,file_user_date fud,erca_matched em  where  u.id =fud.user_id and eac.file_id=fud.file_id and  eac.additional_information like '%'+ #{reference} + '%'  and eac.value_date like '%'+ #{value_date} + '%' and em.match_id = eac.match_id and  eac.availability=1 and eac.status=1 union"
			+ " select distinct sac.id, sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,users u,file_user_date fud,sos_matched sm  where  u.id =fud.user_id and sac.file_id=fud.file_id   and sac.additional_information like '%'+ #{reference} + '%'  and sac.value_date like '%'+ #{value_date} + '%' and sm.match_id = sac.match_id and  sac.availability=1 and sac.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,users u,file_user_date fud,partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and  cpm.additional_information like '%'+ #{reference} + '%' and cpm.value_date like '%'+ #{value_date} + '%' and pm.match_id = cpm.match_id and  cpm.availability=1 and cpm.status=1")
	List<Transactionhistory> searchWithValueDateAndReference(@Param("value_date") String value_date,
			@Param("reference") String reference);

	@Select("select distinct dac.id,dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac,users u,file_user_date fud  where  u.id =fud.user_id and dac.file_id=fud.file_id and dac.branch_code like '%'+ #{branch_code} + '%'  and dac.value_date like '%'+ #{value_date} + '%' and  dac.status = 1 and dac.availability = 1 union"
			+ " select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname from rtgs__ rac,users u,file_user_date fud,rtgs_matched rm where  u.id =fud.user_id and rac.file_id=fud.file_id  and rac.branch_code like '%'+ #{branch_code} + '%'  and rac.value_date like '%'+ #{value_date} + '%' and rm.match_id = rac.match_id and rac.availability=1 and rac.status=1 union"
			+ " select distinct bac.id, bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,users u,file_user_date fud,b2b_matched bm  where  u.id =fud.user_id and bac.file_id=fud.file_id  and bac.branch_code like '%'+ #{branch_code} + '%'  and bac.value_date like '%'+ #{value_date} + '%' and bm.match_id = bac.match_id and  bac.availability=1 and bac.status=1 union"
			+ " select distinct eac.id, eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,users u,file_user_date fud ,erca_matched em where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.branch_code like '%'+ #{branch_code} + '%' and eac.value_date like '%'+ #{value_date} + '%' and em.match_id = eac.match_id and  eac.availability=1 and eac.status=1 union"
			+ " select distinct sac.id, sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,users u,file_user_date fud,sos_matched sm  where  u.id =fud.user_id and sac.file_id=fud.file_id  and sac.branch_code like '%'+ #{branch_code} + '%'  and sac.value_date like '%'+ #{value_date} + '%' and sm.match_id = sac.match_id and  sac.availability=1 and sac.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,users u,file_user_date fud,partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.branch_code like '%'+ #{branch_code} + '%' and cpm.value_date like '%'+ #{value_date} + '%' and pm.match_id = cpm.match_id and  cpm.availability=1 and cpm.status=1")
	List<Transactionhistory> searchWithValueDateAndBranchCode(@Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dac.id,dac.additional_information,dac.amount,dac.dr_cr,dac.match_status,dac.transaction_reference,dac.branch_code,dac.value_date,dac.upload_date,dac.posting_date,u.firstname,u.lastname  from data__ dac,users u,file_user_date fud  where  u.id =fud.user_id and dac.file_id=fud.file_id and dac.branch_code like '%'+ #{branch_code} + '%'  and dac.additional_information like '%'+ #{reference} + '%' and dac.status = 1 and dac.availability = 1  union"
			+ " select distinct rac.id,rac.additional_information,rac.amount,rac.dr_cr,rac.match_status,rac.transaction_reference,rac.branch_code,rac.value_date,rac.upload_date,rac.posting_date,u.firstname,u.lastname from rtgs__ rac,users u,file_user_date fud,rtgs_matched rm where  u.id =fud.user_id and rac.file_id=fud.file_id  and rac.branch_code like '%'+ #{branch_code} + '%' and rac.additional_information like '%'+ #{reference} + '%' and rm.match_id = rac.match_id and rac.availability=1 and rac.status=1 union"
			+ " select distinct bac.id, bac.additional_information,bac.amount,bac.dr_cr,bac.match_status,bac.transaction_reference,bac.branch_code,bac.value_date,bac.upload_date,bac.posting_date,u.firstname,u.lastname  from b2b__ bac,users u,file_user_date fud,b2b_matched bm  where  u.id =fud.user_id and bac.file_id=fud.file_id  and bac.branch_code like '%'+ #{branch_code} + '%' and bac.additional_information like '%'+ #{reference} + '%' and bm.match_id = bac.match_id  and  bac.availability=1 and bac.status=1 union"
			+ " select distinct eac.id, eac.additional_information,eac.amount,eac.dr_cr,eac.match_status,eac.transaction_reference,eac.branch_code,eac.value_date,eac.upload_date,eac.posting_date,u.firstname,u.lastname  from erca__ eac,users u,file_user_date fud,erca_matched em  where  u.id =fud.user_id and eac.file_id=fud.file_id and eac.branch_code like '%'+ #{branch_code} + '%' and eac.additional_information like '%'+ #{reference} + '%' and em.match_id = eac.match_id  and  eac.availability=1 and eac.status=1 union"
			+ " select distinct sac.id, sac.additional_information,sac.amount,sac.dr_cr,sac.match_status,sac.transaction_reference,sac.branch_code,sac.value_date,sac.upload_date,sac.posting_date,u.firstname,u.lastname  from sos__ sac,users u,file_user_date fud ,sos_matched sm where  u.id =fud.user_id and sac.file_id=fud.file_id  and sac.branch_code like '%'+ #{branch_code} + '%' and sac.additional_information like '%'+ #{reference} + '%' and sm.match_id = sac.match_id   and  sac.availability=1 and sac.status=1 union"
			+ " select distinct cr.id, cr.additional_information,cr.amount,cr.dr_cr,cr.match_status,cr.transaction_reference,cr.branch_code,cr.value_date,cr.upload_date,cr.posting_date,u.firstname,u.lastname  from pas___reversal cr,users u,file_user_date fud where  u.id =fud.user_id and cr.file_id=fud.file_id  and cr.branch_code like '%'+ #{branch_code} + '%' and cr.additional_information like '%'+ #{reference} + '%'  and  cr.availability=1 and cr.status=1 union"
			+ " select distinct cpm.id,cpm.additional_information,cpm.amount,cpm.dr_cr,cpm.match_status,cpm.transaction_reference,cpm.branch_code,cpm.value_date,cpm.upload_date,cpm.posting_date,u.firstname,u.lastname  from _partially_matched cpm,users u,file_user_date fud,partially_matched pm  where  u.id =fud.user_id and cpm.file_id=fud.file_id and cpm.branch_code like '%'+ #{branch_code} + '%' and cpm.additional_information like '%'+ #{reference} + '%' and pm.match_id = cpm.match_id and  cpm.availability=1 and cpm.status=1")
	List<Transactionhistory> searchWithReferenceAndBranchCode(@Param("reference") String reference,
			@Param("branch_code") String branch_code);
	// ====================================== receivable
	// ===================================

	@Select("select distinct dr.id,dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr,users u,file_user_date fud where  u.id =fud.user_id and dr.file_id=fud.file_id and dr.amount  between #{min_amount} and #{max_amount}  and dr.additional_information like '%'+ #{reference} + '%' and dr.value_date like '%'+#{value_date}+ '%' and dr.branch_code like '%'+#{branch_code}+'%' and dr.availability=1 union "
			+ "select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname from receivable_credit rc,users u,file_user_date fud, receivable_matched rm where  u.id =fud.user_id and rc.file_id=fud.file_id  and rc.amount between  #{min_amount} and #{max_amount} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%' and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1 union "
			+ "select distinct rd.id, rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,users u,file_user_date fud, receivable_matched rm  where  u.id =fud.user_id and rd.file_id=fud.file_id and rd.amount  between #{min_amount} and #{max_amount} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")

	public List<Transactionhistory> searchWithAmountReceivable(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dc.id,dc.additional_information,dc.amount,dc.dr_cr,dc.match_status,dc.transaction_reference,dc.branch_code,dc.value_date,dc.upload_date,dc.posting_date,u.firstname,u.lastname  from data_recivable dc,users u,file_user_date fud where  u.id =fud.user_id and dc.file_id=fud.file_id and dc.amount  >= #{min_amount} and dc.additional_information like '%'+ #{reference} + '%' and dc.value_date like '%'+#{value_date}+ '%' and dc.branch_code like '%'+#{branch_code}+'%' and dc.availability=1 union "
			+ "select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname from receivable_credit rc,users u,file_user_date fud, receivable_matched rm where  u.id =fud.user_id and rc.file_id=fud.file_id  and rc.amount >=  #{min_amount}  and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%' and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1 union "
			+ "select distinct rd.id, rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,users u,file_user_date fud, receivable_matched rm  where  u.id =fud.user_id and rd.file_id=fud.file_id and rd.amount  >= #{min_amount} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountReceivable(@Param("min_amount") float min_amount,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dr.id,dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr,users u,file_user_date fud where  u.id =fud.user_id and dr.file_id=fud.file_id and dr.amount  <= #{max_amount} and dr.additional_information like '%'+ #{reference} + '%' and dr.value_date like '%'+#{value_date}+ '%' and dr.branch_code like '%'+#{branch_code}+'%' and dr.availability=1 union "
			+ "select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname from receivable_credit rc,users u,file_user_date fud, receivable_matched rm where  u.id =fud.user_id and rc.file_id=fud.file_id  and rc.amount <=  #{max_amount}  and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%' and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1 union "
			+ "select distinct rd.id, rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,users u,file_user_date fud, receivable_matched rm  where  u.id =fud.user_id and rd.file_id=fud.file_id and rd.amount  <= #{max_amount} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")
	public List<Transactionhistory> searchWithMaxAmountReceivable(@Param("max_amount") float max_amount,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dr.id,dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr,users u,file_user_date fud  where  u.id =fud.user_id and dr.file_id=fud.file_id and dr.additional_information like '%'+ #{reference} + '%' and dr.availability=1 union "
			+ "select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname from receivable_credit rc,users u,file_user_date fud,receivable_matched rm where  u.id =fud.user_id and rc.file_id=fud.file_id  and rc.additional_information like '%'+ #{reference} + '%'  and rm.credit_id = rc.id and rc.availability=1 and rc.status=1 union "
			+ "select distinct rd.id, rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,users u,file_user_date fud,receivable_matched rm  where  u.id =fud.user_id and rd.file_id=fud.file_id  and rd.additional_information like '%'+ #{reference} + '%'  and rm.match_id = rd.match_id and  rd.availability=1 and rd.status=1 ")
	List<Transactionhistory> searchWithReferenceReceivable(String reference);

	@Select("select distinct dr.id,dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr,users u,file_user_date fud  where  u.id =fud.user_id and dr.file_id=fud.file_id and dr.value_date like '%'+ #{value_date} + '%'  and dr.availability=1 union "
			+ "select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname from receivable_credit rc,users u,file_user_date fud ,receivable_matched rm where  u.id =fud.user_id and rc.file_id=fud.file_id  and rc.value_date like '%'+ #{value_date} + '%' and rm.credit_id = rc.id and rc.availability=1 and rc.status=1 union "
			+ "select distinct rd.id, rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,users u,file_user_date fud ,receivable_matched rm where  u.id =fud.user_id and rd.file_id=fud.file_id  and rd.value_date like '%'+ #{value_date} + '%' and rm.match_id = rd.match_id and  rd.availability=1 and rd.status=1 ")
	List<Transactionhistory> searchWithValueDateReceivable(String value_date);

	@Select("select distinct dr.id,dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr,users u,file_user_date fud  where  u.id =fud.user_id and dr.file_id=fud.file_id and dr.branch_code like '%'+ #{branch_code} + '%' and dr.availability=1 union "
			+ "select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname from receivable_credit rc,users u,file_user_date fud,receivable_matched rm where  u.id =fud.user_id and rc.file_id=fud.file_id  and rc.branch_code like '%'+ #{branch_code} + '%' and rm.credit_id = rc.id and rc.availability=1 and rc.status=1 union "
			+ "select distinct rd.id, rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,users u,file_user_date fud,receivable_matched rm  where  u.id =fud.user_id and rd.file_id=fud.file_id  and rd.branch_code like '%'+ #{branch_code} + '%' and rm.match_id = rd.match_id and  rd.availability=1 and rd.status=1 ")
	List<Transactionhistory> searchWithBranchCodeReceivable(String branch_code);

	@Select("select distinct dr.id,dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr,users u,file_user_date fud  where  u.id =fud.user_id and dr.file_id=fud.file_id  and dr.additional_information like '%'+ #{reference} + '%'  and dr.value_date like '%'+ #{value_date} + '%' and dr.availability=1  union "
			+ "select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname from receivable_credit rc,users u,file_user_date fud,receivable_matched rm where  u.id =fud.user_id and rc.file_id=fud.file_id  and rc.additional_information like '%'+ #{reference} + '%'  and rc.value_date like '%'+ #{value_date} + '%' and rm.credit_id = rc.id and rc.availability=1 and rc.status=1 union "
			+ "select distinct rd.id, rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,users u,file_user_date fud,receivable_matched rm  where  u.id =fud.user_id and rd.file_id=fud.file_id  and rd.additional_information like '%'+ #{reference} + '%'  and rd.value_date like '%'+ #{value_date} + '%' and rm.match_id = rd.match_id and  rd.availability=1 and rd.status=1 ")
	List<Transactionhistory> searchWithValueDateAndReferenceReceivable(@Param("value_date") String value_date,
			@Param("reference") String reference);

	@Select("select distinct dr.id,dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr,users u,file_user_date fud  where  u.id =fud.user_id and dr.file_id=fud.file_id and dr.branch_code like '%'+ #{branch_code} + '%'  and dr.value_date like '%'+ #{value_date} + '%' and dr.availability=1  union "
			+ "select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname from receivable_credit rc,users u,file_user_date fud,receivable_matched rm where  u.id =fud.user_id and rc.file_id=fud.file_id  and rc.branch_code like '%'+ #{branch_code} + '%'  and rc.value_date like '%'+ #{value_date} + '%' and rm.credit_id = rc.id and rc.availability=1 and rc.status=1 union "
			+ "select distinct rd.id, rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,users u,file_user_date fud,receivable_matched rm  where  u.id =fud.user_id and rd.file_id=fud.file_id  and rd.branch_code like '%'+ #{branch_code} + '%'  and rd.value_date like '%'+ #{value_date} + '%' and rm.match_id = rd.match_id and  rd.availability=1 and rd.status=1 ")
	List<Transactionhistory> searchWithValueDateAndBranchCodeReceivable(@Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dr.id,dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr,users u,file_user_date fud  where  u.id =fud.user_id and dr.file_id=fud.file_id and dr.branch_code like '%'+ #{branch_code} + '%'  and dr.additional_information like '%'+ #{reference} + '%'  and dr.value_date like '%'+ #{value_date} + '%'  and dr.availability=1 union "
			+ "select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname from receivable_credit rc,users u,file_user_date fud ,receivable_matched rm where  u.id =fud.user_id and rc.file_id=fud.file_id  and rc.branch_code like '%'+ #{branch_code} + '%' and rc.additional_information like '%'+ #{reference} + '%'  and rc.value_date like '%'+ #{value_date} + '%' and rm.credit_id = rc.id and rc.availability=1 and rc.status=1 union "
			+ "select distinct rd.id, rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,users u,file_user_date fud ,receivable_matched rm where  u.id =fud.user_id and rd.file_id=fud.file_id  and rd.branch_code like '%'+ #{branch_code} + '%' and rd.additional_information like '%'+ #{reference} + '%'  and rd.value_date like '%'+ #{value_date} + '%'  and rm.match_id = rd.match_id and  rd.availability=1 and rd.status=1 ")
	List<Transactionhistory> searchWithRefValueDateBranchCodeReceivable(@Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dr.id,dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr,users u,file_user_date fud  where  u.id =fud.user_id and dr.file_id=fud.file_id and dr.branch_code like '%'+ #{branch_code} + '%'  and dr.additional_information like '%'+ #{reference} + '%' and dr.availability=1  union "
			+ "select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname from receivable_credit rc,users u,file_user_date fud,receivable_matched rm where  u.id =fud.user_id and rc.file_id=fud.file_id  and rc.branch_code like '%'+ #{branch_code} + '%' and rc.additional_information like '%'+ #{reference} + '%' and rm.credit_id = rc.id and rc.availability=1 and rc.status=1 union "
			+ "select distinct rd.id, rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,users u,file_user_date fud,receivable_matched rm  where  u.id =fud.user_id and rd.file_id=fud.file_id  and rd.branch_code like '%'+ #{branch_code} + '%' and rd.additional_information like '%'+ #{reference} + '%' and rm.match_id = rd.match_id  and  rd.availability=1 and rd.status=1 ")
	List<Transactionhistory> searchWithReferenceAndBranchCodeReceivable(@Param("reference") String reference,
			@Param("branch_code") String branch_code);

	@Select("select dr.id,dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr,users u,file_user_date fud  where  u.id =fud.user_id and dr.file_id=fud.file_id  and dr.upload_date between #{min_upload_date} and #{max_upload_date} and dr.additional_information like '%'+ #{reference} + '%' and dr.value_date like '%'+#{value_date}+ '%' and dr.branch_code like '%'+#{branch_code}+'%' and dr.availability=1 union "
			+ "select rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname  from receivable_credit rc,users u,file_user_date fud, receivable_matched rm  where  u.id =fud.user_id and rc.file_id=fud.file_id and rc.upload_date between #{min_upload_date} and #{max_upload_date} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%' and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1 union "
			+ "select rd.id,rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,users u,file_user_date fud, receivable_matched rm  where  u.id =fud.user_id and rd.file_id=fud.file_id and rd.upload_date between #{min_upload_date} and #{max_upload_date} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")
	public List<Transactionhistory> searchWithUploadDateReceivable(@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select rc.id, rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname  from receivable_credit rc,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rc.file_id=fud.file_id and  rm.match_date between #{min_match_date} and #{max_match_date} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%' and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1 union "
			+ "select rd.id,rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rd.file_id=fud.file_id and  rm.match_date between #{min_match_date} and #{max_match_date} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")
	public List<Transactionhistory> searchWithMatchDateReceivable(@Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dr.id, dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr, users u,file_user_date fud  where  u.id =fud.user_id and dr.file_id=fud.file_id and dr.amount  between #{min_amount} and  #{max_amount}  and dr.upload_date between #{min_upload_date} and #{max_upload_date} and dr.additional_information like '%'+ #{reference} + '%' and dr.value_date like '%'+#{value_date}+ '%' and dr.branch_code like '%'+#{branch_code}+'%' and dr.availability=1  union "
			+ "select distinct rc.id, rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname  from receivable_credit rc, users u,file_user_date fud, receivable_matched rm  where  u.id =fud.user_id and rc.file_id=fud.file_id and rc.amount  between #{min_amount} and #{max_amount}  and rc.upload_date between #{min_upload_date} and #{max_upload_date} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%' and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1union "
			+ "select distinct rd.id,rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd, users u,file_user_date fud, receivable_matched rm  where  u.id =fud.user_id and rd.file_id=fud.file_id and rd.amount  between #{min_amount} and #{max_amount}  and rd.upload_date between #{min_upload_date} and #{max_upload_date} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")
	public List<Transactionhistory> searchWithAmountAndUploadDateReceivable(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dr.id, dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr, users u,file_user_date fud  where  u.id =fud.user_id and dr.file_id=fud.file_id and dr.amount  >= #{min_amount} and dr.upload_date between #{min_upload_date} and #{max_upload_date} and dr.additional_information like '%'+ #{reference} + '%' and dr.value_date like '%'+#{value_date}+ '%' and dr.branch_code like '%'+#{branch_code}+'%' and dr.availability=1 union "
			+ "select distinct rc.id, rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname  from receivable_credit rc, users u,file_user_date fud, receivable_matched rm  where  u.id =fud.user_id and rc.file_id=fud.file_id and rc.amount   >= #{min_amount}  and rc.upload_date between #{min_upload_date} and #{max_upload_date} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%' and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1union "
			+ "select distinct rd.id,rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd, users u,file_user_date fud, receivable_matched rm  where  u.id =fud.user_id and rd.file_id=fud.file_id and rd.amount  >= #{min_amount}  and rd.upload_date between #{min_upload_date} and #{max_upload_date} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountAndUploadDateReceivable(@Param("min_amount") float min_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dr.id, dr.additional_information,dr.amount,dr.dr_cr,dr.match_status,dr.transaction_reference,dr.branch_code,dr.value_date,dr.upload_date,dr.posting_date,u.firstname,u.lastname  from data_recivable dr, users u,file_user_date fud  where  u.id =fud.user_id and dr.file_id=fud.file_id and dr.amount  <= #{max_amount} and dr.upload_date between #{min_upload_date} and #{max_upload_date} and dr.additional_information like '%'+ #{reference} + '%' and dr.value_date like '%'+#{value_date}+ '%' and dr.branch_code like '%'+#{branch_code}+'%' and dr.availability=1  union "
			+ "select distinct rc.id, rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname  from receivable_credit rc, users u,file_user_date fud, receivable_matched rm  where  u.id =fud.user_id and rc.file_id=fud.file_id and rc.amount   <= #{max_amount}  and rc.upload_date between #{min_upload_date} and #{max_upload_date} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%' and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1union "
			+ "select distinct rd.id,rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd, users u,file_user_date fud, receivable_matched rm  where  u.id =fud.user_id and rd.file_id=fud.file_id and rd.amount  <= #{max_amount}  and rd.upload_date between #{min_upload_date} and #{max_upload_date} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")
	public List<Transactionhistory> searchWithMaxAmountAndUploadDateReceivable(@Param("max_amount") float max_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname  from receivable_credit rc,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rc.file_id=fud.file_id and rc.amount between  #{min_amount} and #{max_amount}  and rc.upload_date between #{min_upload_date} and #{max_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%'  and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1 union "
			+ "select distinct rd.id,rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rd.file_id=fud.file_id and rd.amount between  #{min_amount} and #{max_amount}  and rd.upload_date between #{min_upload_date} and #{max_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")
	public List<Transactionhistory> searchWithAmountUploadDateAndMatchDateReceivable(
			@Param("min_amount") float min_amount, @Param("max_amount") float max_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname  from receivable_credit rc,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rc.file_id=fud.file_id and rc.amount >=  #{min_amount} and rc.upload_date between #{min_upload_date} and #{max_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%'  and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1 union "
			+ "select distinct rd.id,rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rd.file_id=fud.file_id and rd.amount >=  #{min_amount}  and rd.upload_date between #{min_upload_date} and #{max_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountUploadDateAndMatchDateReceivable(
			@Param("min_amount") float min_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct rc.id,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname  from receivable_credit rc,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rc.file_id=fud.file_id and rc.amount <=  #{max_amount} and rc.upload_date between #{min_upload_date} and #{max_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%'  and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1 union "
			+ "select distinct rd.id,rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rd.file_id=fud.file_id and rd.amount <=  #{max_amount}  and rd.upload_date between #{min_upload_date} and #{max_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1")
	public List<Transactionhistory> searchWithMaxAmountUploadDateAndMatchDateReceivable(
			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct rc.id ,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname  from receivable_credit rc,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rc.file_id=fud.file_id and  rc.upload_date between #{min_upload_date} and #{max_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%'  and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1 union "
			+ "select distinct rd.id ,rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rd.file_id=fud.file_id and  rd.upload_date between #{min_upload_date} and #{max_upload_date} and rm.match_date between #{min_match_date} and #{max_match_date} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%'  and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")
	public List<Transactionhistory> searchWithUploadDateAndMatchDateReceivable(
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct rc.id ,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname  from receivable_credit rc,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rc.file_id=fud.file_id and rc.amount between #{min_amount} and #{max_amount} and rm.match_date between #{min_match_date} and #{max_match_date} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%'  and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1 union "
			+ "select distinct rd.id ,rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rd.file_id=fud.file_id and rd.amount between #{min_amount} and #{max_amount} and rm.match_date between #{min_match_date} and #{max_match_date} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%' and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")
	public List<Transactionhistory> searchWithAmountAndMatchDateReceivable(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct rc.id ,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname  from receivable_credit rc,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rc.file_id=fud.file_id and rc.amount >= #{min_amount} and rm.match_date between #{min_match_date} and #{max_match_date} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%'  and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1 union "
			+ "select distinct dc.id ,dc.additional_information,dc.amount,dc.dr_cr,dc.match_status,dc.transaction_reference,dc.branch_code,dc.value_date,dc.upload_date,dc.posting_date,u.firstname,u.lastname  from receivable_debit dc,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and dc.file_id=fud.file_id and dc.amount >= #{min_amount} and rm.match_date between #{min_match_date} and #{max_match_date} and dc.additional_information like '%'+ #{reference} + '%' and dc.value_date like '%'+#{value_date}+ '%' and dc.branch_code like '%'+#{branch_code}+'%' and rm.match_id = dc.match_id and dc.availability = 1 and dc.status=1 ")
	public List<Transactionhistory> searchWithMinAmountAndMatchDateReceivable(@Param("min_amount") float min_amount,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct rc.id ,rc.additional_information,rc.amount,rc.dr_cr,rc.match_status,rc.transaction_reference,rc.branch_code,rc.value_date,rc.upload_date,rc.posting_date,u.firstname,u.lastname  from receivable_credit rc,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rc.file_id=fud.file_id and rc.amount <= #{max_amount} and rm.match_date between #{min_match_date} and #{max_match_date} and rc.additional_information like '%'+ #{reference} + '%' and rc.value_date like '%'+#{value_date}+ '%' and rc.branch_code like '%'+#{branch_code}+'%'  and rm.credit_id = rc.id and rc.availability = 1 and rc.status=1 union "
			+ "select distinct rd.id ,rd.additional_information,rd.amount,rd.dr_cr,rd.match_status,rd.transaction_reference,rd.branch_code,rd.value_date,rd.upload_date,rd.posting_date,u.firstname,u.lastname  from receivable_debit rd,receivable_matched rm, users u,file_user_date fud  where  u.id =fud.user_id and rd.file_id=fud.file_id and rd.amount <= #{max_amount} and rm.match_date between #{min_match_date} and #{max_match_date} and rd.additional_information like '%'+ #{reference} + '%' and rd.value_date like '%'+#{value_date}+ '%' and rd.branch_code like '%'+#{branch_code}+'%' and rm.match_id = rd.match_id and rd.availability = 1 and rd.status=1 ")
	public List<Transactionhistory> searchWithMaxAmountAndMatchDateReceivable(@Param("max_amount") float max_amount,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);
	// ============================ ending
	// ===================================================
	// ===================================== issue
	// =============================================

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp,users u,file_user_date fud where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  between #{min_amount} and #{max_amount}  and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  between #{min_amount} and #{max_amount} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithAmountQBS(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp,users u,file_user_date fud where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  >= #{min_amount} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  >= #{min_amount} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountQBS(@Param("min_amount") float min_amount,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp,users u,file_user_date fud where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  <= #{max_amount} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  <= #{max_amount} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMaxAmountQBS(@Param("max_amount") float max_amount,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.additional_information like '%'+ #{reference} + '%' and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,users u,file_user_date fud,issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.additional_information like '%'+ #{reference} + '%'  and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithReferenceQBS(String reference);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.value_date like '%'+ #{value_date} + '%'  and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_credit pd,users u,file_user_date fud ,issue_matched pm where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.value_date like '%'+ #{value_date} + '%' and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithValueDateQBS(String value_date);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.branch like '%'+ #{branch_code} + '%' and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,users u,file_user_date fud,issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.branch like '%'+ #{branch_code} + '%' and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithBranchCodeQBS(String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id  and dp.additional_information like '%'+ #{reference} + '%'  and dp.value_date like '%'+ #{value_date} + '%' and dp.availability=1  union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,users u,file_user_date fud,issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.additional_information like '%'+ #{reference} + '%'  and pd.value_date like '%'+ #{value_date} + '%' and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithValueDateAndReferenceQBS(@Param("value_date") String value_date,
			@Param("reference") String reference);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.branch like '%'+ #{branch_code} + '%'  and dp.value_date like '%'+ #{value_date} + '%' and dp.availability=1  union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,users u,file_user_date fud,issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.branch like '%'+ #{branch_code} + '%'  and pd.value_date like '%'+ #{value_date} + '%' and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithValueDateAndBranchCodeQBS(@Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.branch like '%'+ #{branch_code} + '%'  and dp.additional_information like '%'+ #{reference} + '%'  and dp.value_date like '%'+ #{value_date} + '%'  and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,users u,file_user_date fud ,issue_matched pm where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.branch like '%'+ #{branch_code} + '%' and pd.additional_information like '%'+ #{reference} + '%'  and pd.value_date like '%'+ #{value_date} + '%'  and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithRefValueDateBranchCodeQBS(@Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.branch like '%'+ #{branch_code} + '%'  and dp.additional_information like '%'+ #{reference} + '%' and dp.availability=1  union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,users u,file_user_date fud,issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.branch like '%'+ #{branch_code} + '%' and pd.additional_information like '%'+ #{reference} + '%' and pm.match_id = pd.match_id  and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithReferenceAndBranchCodeQBS(@Param("reference") String reference,
			@Param("branch_code") String branch_code);

	@Select("select dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id  and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithUploadDateQBS(@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and  pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMatchDateQBS(@Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp, users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  between #{min_amount} and  #{max_amount}  and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1  union "
			+ "select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd, users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  between #{min_amount} and #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithAmountAndUploadDateQBS(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp, users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  >= #{min_amount} and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd, users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  >= #{min_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountAndUploadDateQBS(@Param("min_amount") float min_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_qbs dp, users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  <= #{max_amount} and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1  union "
			+ "select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd, users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  <= #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMaxAmountAndUploadDateQBS(@Param("max_amount") float max_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount between  #{min_amount} and #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithAmountUploadDateAndMatchDateQBS(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount >=  #{min_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountUploadDateAndMatchDateQBS(@Param("min_amount") float min_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount <=  #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1")
	public List<Transactionhistory> searchWithMaxAmountUploadDateAndMatchDateQBS(@Param("max_amount") float max_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct pd.id ,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and  pd.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithUploadDateAndMatchDateQBS(@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct pd.id ,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount between #{min_amount} and #{max_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%' and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithAmountAndMatchDateQBS(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct pd.id ,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount >= #{min_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%' and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountAndMatchDateQBS(@Param("min_amount") float min_amount,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct pd.id ,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_qbs pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount <= #{max_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%' and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMaxAmountAndMatchDateQBS(@Param("max_amount") float max_amount,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	///// ======================

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp,users u,file_user_date fud where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  between #{min_amount} and #{max_amount}  and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  between #{min_amount} and #{max_amount} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithAmountIssue(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp,users u,file_user_date fud where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  >= #{min_amount} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  >= #{min_amount} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountIssue(@Param("min_amount") float min_amount,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp,users u,file_user_date fud where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  <= #{max_amount} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  <= #{max_amount} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMaxAmountIssue(@Param("max_amount") float max_amount,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.additional_information like '%'+ #{reference} + '%' and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,users u,file_user_date fud,issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.additional_information like '%'+ #{reference} + '%'  and pm.issue__id = pd.id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithReferenceIssue(String reference);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.value_date like '%'+ #{value_date} + '%'  and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,users u,file_user_date fud ,issue_matched pm where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.value_date like '%'+ #{value_date} + '%' and pm.issue__id = pd.id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithValueDateIssue(String value_date);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.branch like '%'+ #{branch_code} + '%' and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,users u,file_user_date fud,issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.branch like '%'+ #{branch_code} + '%' and pm.issue__id = pd.id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithBranchCodeIssue(String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id  and dp.additional_information like '%'+ #{reference} + '%'  and dp.value_date like '%'+ #{value_date} + '%' and dp.availability=1  union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,users u,file_user_date fud,issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.additional_information like '%'+ #{reference} + '%'  and pd.value_date like '%'+ #{value_date} + '%' and pm.issue__id = pd.id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithValueDateAndReferenceIssue(@Param("value_date") String value_date,
			@Param("reference") String reference);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.branch like '%'+ #{branch_code} + '%'  and dp.value_date like '%'+ #{value_date} + '%' and dp.availability=1  union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,users u,file_user_date fud,issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.branch like '%'+ #{branch_code} + '%'  and pd.value_date like '%'+ #{value_date} + '%' and pm.issue__id = pd.id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithValueDateAndBranchCodeIssue(@Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.branch like '%'+ #{branch_code} + '%'  and dp.additional_information like '%'+ #{reference} + '%'  and dp.value_date like '%'+ #{value_date} + '%'  and dp.availability=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,users u,file_user_date fud ,issue_matched pm where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.branch like '%'+ #{branch_code} + '%' and pd.additional_information like '%'+ #{reference} + '%'  and pd.value_date like '%'+ #{value_date} + '%'  and pm.issue__id = pd.id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithRefValueDateBranchCodeIssue(@Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.branch like '%'+ #{branch_code} + '%'  and dp.additional_information like '%'+ #{reference} + '%' and dp.availability=1  union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,users u,file_user_date fud,issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.branch like '%'+ #{branch_code} + '%' and pd.additional_information like '%'+ #{reference} + '%' and pm.issue__id = pd.id  and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithReferenceAndBranchCodeIssue(@Param("reference") String reference,
			@Param("branch_code") String branch_code);

	@Select("select dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id  and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithUploadDateIssue(@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and  pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMatchDateIssue(@Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp, users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  between #{min_amount} and  #{max_amount}  and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1  union "
			+ "select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd, users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  between #{min_amount} and #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithAmountAndUploadDateIssue(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp, users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  >= #{min_amount} and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd, users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  >= #{min_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountAndUploadDateIssue(@Param("min_amount") float min_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.branch as branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_issue_ dp, users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  <= #{max_amount} and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch like '%'+#{branch_code}+'%' and dp.availability=1  union "
			+ "select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd, users u,file_user_date fud, issue_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  <= #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMaxAmountAndUploadDateIssue(@Param("max_amount") float max_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount between  #{min_amount} and #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithAmountUploadDateAndMatchDateIssue(
			@Param("min_amount") float min_amount, @Param("max_amount") float max_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount >=  #{min_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountUploadDateAndMatchDateIssue(
			@Param("min_amount") float min_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount <=  #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1")
	public List<Transactionhistory> searchWithMaxAmountUploadDateAndMatchDateIssue(
			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct pd.id ,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and  pd.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%'  and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithUploadDateAndMatchDateIssue(
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct pd.id ,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount between #{min_amount} and #{max_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%' and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithAmountAndMatchDateIssue(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct pd.id ,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount >= #{min_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%' and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountAndMatchDateIssue(@Param("min_amount") float min_amount,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct pd.id ,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.branch as branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from issue_ pd,issue_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount <= #{max_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch like '%'+#{branch_code}+'%' and pm.issue__id = pd.id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMaxAmountAndMatchDateIssue(@Param("max_amount") float max_amount,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	// ======================================ending
	// =============================================
	// =================================== payable ===========================
//	@Select("select distinct dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp,users u, file_user_date fud where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  between #{min_amount} and #{max_amount}  and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch_code like '%'+#{branch_code}+'%' and dp.availability=1 union "
//			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname from payable_credit pc,users u,file_user_date fud, payable_matched pm where  u.id =fud.user_id and pc.file_id=fud.file_id  and pc.amount between  #{min_amount} and #{max_amount} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
//			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,users u,file_user_date fud, payable_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  between #{min_amount} and #{max_amount} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
//	public List<Transactionhistory> searchWithAmountPayable(@Param("min_amount") float min_amount,
//			@Param("max_amount") float max_amount, @Param("reference") String reference,
//			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date from data_payable dp where dp.amount  between #{min_amount} and #{max_amount}  and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch_code like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc,  payable_matched pm where pc.amount between  #{min_amount} and #{max_amount} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date from payable_debit pd, payable_matched pm  where pd.amount  between #{min_amount} and #{max_amount} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithAmountPayable(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);


//	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp,users u,file_user_date fud where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  >= #{min_amount} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch_code like '%'+#{branch_code}+'%' and dp.availability=1 union "
//			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname from payable_credit pc,users u,file_user_date fud, payable_matched pm where  u.id =fud.user_id and pc.file_id=fud.file_id  and pc.amount >=  #{min_amount}  and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
//			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,users u,file_user_date fud, payable_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  >= #{min_amount} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
//	public List<Transactionhistory> searchWithMinAmountPayable(@Param("min_amount") float min_amount,
//			@Param("reference") String reference, @Param("value_date") String value_date,
//			@Param("branch_code") String branch_code);


	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date from data_payable dp where dp.amount  >= #{min_amount} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch_code like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc, payable_matched pm where pc.amount >=  #{min_amount}  and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date  from payable_debit pd, payable_matched pm  where pd.amount  >= #{min_amount} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountPayable(@Param("min_amount") float min_amount,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date from data_payable dp where dp.amount  <= #{max_amount} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch_code like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc, payable_matched pm where pc.amount <=  #{max_amount}  and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date from payable_debit pd, payable_matched pm  where  pd.amount  <= #{max_amount} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMaxAmountPayable(@Param("max_amount") float max_amount,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

//	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.additional_information like '%'+ #{reference} + '%' and dp.availability=1 union "
//			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname from payable_credit pc,users u,file_user_date fud,payable_matched pm where  u.id =fud.user_id and pc.file_id=fud.file_id  and pc.additional_information like '%'+ #{reference} + '%'  and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
//			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,users u,file_user_date fud,payable_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.additional_information like '%'+ #{reference} + '%'  and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
//	List<Transactionhistory> searchWithReferencePayable(String reference);


	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date from data_payable dp where dp.additional_information like '%'+ #{reference} + '%' and dp.availability=1 union "
			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc, payable_matched pm where pc.additional_information like '%'+ #{reference} + '%'  and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date from payable_debit pd, payable_matched pm  where pd.additional_information like '%'+ #{reference} + '%'  and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithReferencePayable( @Param("reference") String reference);

//	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.value_date like '%'+ #{value_date} + '%'  and dp.availability=1 union "
//			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname from payable_credit pc,users u,file_user_date fud ,payable_matched pm where  u.id =fud.user_id and pc.file_id=fud.file_id  and pc.value_date like '%'+ #{value_date} + '%' and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
//			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_credit pd,users u,file_user_date fud ,payable_matched pm where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.value_date like '%'+ #{value_date} + '%' and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
//	List<Transactionhistory> searchWithValueDatePayable(String value_date);


	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date from data_payable dp  where dp.value_date like '%'+ #{value_date} + '%'  and dp.availability=1 union "
			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc, payable_matched pm where pc.value_date like '%'+ #{value_date} + '%' and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date from payable_credit pd, payable_matched pm where  pd.value_date like '%'+ #{value_date} + '%' and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithValueDatePayable(@Param("value_date") String value_date);

//	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.branch_code like '%'+ #{branch_code} + '%' and dp.availability=1 union "
//			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname from payable_credit pc,users u,file_user_date fud,payable_matched pm where  u.id =fud.user_id and pc.file_id=fud.file_id  and pc.branch_code like '%'+ #{branch_code} + '%' and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
//			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,users u,file_user_date fud,payable_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.branch_code like '%'+ #{branch_code} + '%' and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
//	List<Transactionhistory> searchWithBranchCodePayable(String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code, dp.value_date,dp.upload_date,dp.posting_date from data_payable dp  where dp.branch_code like '%'+ #{branch_code} + '%' and dp.availability=1 and dp.status =1 union "
			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code, pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc, payable_matched pm where pc.branch_code like '%'+ #{branch_code} + '%' and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code, pd.value_date,pd.upload_date,pd.posting_date from payable_debit pd, payable_matched pm  where pd.branch_code like '%'+ #{branch_code} + '%' and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithBranchCodePayable(@Param("branch_code") String branch_code);


//	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id  and dp.additional_information like '%'+ #{reference} + '%'  and dp.value_date like '%'+ #{value_date} + '%' and dp.availability=1  union "
//			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname from payable_credit pc,users u,file_user_date fud,payable_matched pm where  u.id =fud.user_id and pc.file_id=fud.file_id  and pc.additional_information like '%'+ #{reference} + '%'  and pc.value_date like '%'+ #{value_date} + '%' and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
//			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,users u,file_user_date fud,payable_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.additional_information like '%'+ #{reference} + '%'  and pd.value_date like '%'+ #{value_date} + '%' and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
//	List<Transactionhistory> searchWithValueDateAndReferencePayable(@Param("value_date") String value_date,
//			@Param("reference") String reference);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date from data_payable dp where dp.additional_information like '%'+ #{reference} + '%'  and dp.value_date like '%'+ #{value_date} + '%' and dp.availability=1  union "
			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc, payable_matched pm where  pc.additional_information like '%'+ #{reference} + '%'  and pc.value_date like '%'+ #{value_date} + '%' and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date from payable_debit pd, payable_matched pm  where pd.additional_information like '%'+ #{reference} + '%'  and pd.value_date like '%'+ #{value_date} + '%' and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithValueDateAndReferencePayable(@Param("value_date") String value_date,
			@Param("reference") String reference);
//
//	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.branch_code like '%'+ #{branch_code} + '%'  and dp.value_date like '%'+ #{value_date} + '%' and dp.availability=1  union "
//			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname from payable_credit pc,users u,file_user_date fud,payable_matched pm where  u.id =fud.user_id and pc.file_id=fud.file_id  and pc.branch_code like '%'+ #{branch_code} + '%'  and pc.value_date like '%'+ #{value_date} + '%' and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
//			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,users u,file_user_date fud,payable_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.branch_code like '%'+ #{branch_code} + '%'  and pd.value_date like '%'+ #{value_date} + '%' and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
//	List<Transactionhistory> searchWithValueDateAndBranchCodePayable(@Param("value_date") String value_date,
//			@Param("branch_code") String branch_code);


	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date from data_payable dp where dp.branch_code like '%'+ #{branch_code} + '%'  and dp.value_date like '%'+ #{value_date} + '%' and dp.availability=1  union "
			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc, payable_matched pm where pc.branch_code like '%'+ #{branch_code} + '%'  and pc.value_date like '%'+ #{value_date} + '%' and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date from payable_debit pd, payable_matched pm  where  pd.branch_code like '%'+ #{branch_code} + '%'  and pd.value_date like '%'+ #{value_date} + '%' and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithValueDateAndBranchCodePayable(@Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

//	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.branch_code like '%'+ #{branch_code} + '%'  and dp.additional_information like '%'+ #{reference} + '%'  and dp.value_date like '%'+ #{value_date} + '%'  and dp.availability=1 union "
//			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname from payable_credit pc,users u,file_user_date fud ,payable_matched pm where  u.id =fud.user_id and pc.file_id=fud.file_id  and pc.branch_code like '%'+ #{branch_code} + '%' and pc.additional_information like '%'+ #{reference} + '%'  and pc.value_date like '%'+ #{value_date} + '%' and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
//			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,users u,file_user_date fud ,payable_matched pm where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.branch_code like '%'+ #{branch_code} + '%' and pd.additional_information like '%'+ #{reference} + '%'  and pd.value_date like '%'+ #{value_date} + '%'  and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
//	List<Transactionhistory> searchWithRefValueDateBranchCodePayable(@Param("reference") String reference,
//			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date  from data_payable dp where dp.branch_code like '%'+ #{branch_code} + '%'  and dp.additional_information like '%'+ #{reference} + '%'  and dp.value_date like '%'+ #{value_date} + '%'  and dp.availability=1 union "
			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc, payable_matched pm where pc.branch_code like '%'+ #{branch_code} + '%' and pc.additional_information like '%'+ #{reference} + '%'  and pc.value_date like '%'+ #{value_date} + '%' and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date from payable_debit pd, payable_matched pm where pd.branch_code like '%'+ #{branch_code} + '%' and pd.additional_information like '%'+ #{reference} + '%'  and pd.value_date like '%'+ #{value_date} + '%'  and pm.match_id = pd.match_id and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithRefValueDateBranchCodePayable(@Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

//	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.branch_code like '%'+ #{branch_code} + '%'  and dp.additional_information like '%'+ #{reference} + '%' and dp.availability=1  union "
//			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname from payable_credit pc,users u,file_user_date fud,payable_matched pm where  u.id =fud.user_id and pc.file_id=fud.file_id  and pc.branch_code like '%'+ #{branch_code} + '%' and pc.additional_information like '%'+ #{reference} + '%' and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
//			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,users u,file_user_date fud,payable_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id  and pd.branch_code like '%'+ #{branch_code} + '%' and pd.additional_information like '%'+ #{reference} + '%' and pm.match_id = pd.match_id  and  pd.availability=1 and pd.status=1 ")
//	List<Transactionhistory> searchWithReferenceAndBranchCodePayable(@Param("reference") String reference,
//			@Param("branch_code") String branch_code);

	@Select("select distinct dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date from data_payable dp where dp.branch_code like '%'+ #{branch_code} + '%'  and dp.additional_information like '%'+ #{reference} + '%' and dp.availability=1  union "
			+ "select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc, payable_matched pm where pc.branch_code like '%'+ #{branch_code} + '%' and pc.additional_information like '%'+ #{reference} + '%' and pm.payable_credit_id = pc.id and pc.availability=1 and pc.status=1 union "
			+ "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date from payable_debit pd,payable_matched pm  where pd.branch_code like '%'+ #{branch_code} + '%' and pd.additional_information like '%'+ #{reference} + '%' and pm.match_id = pd.match_id  and  pd.availability=1 and pd.status=1 ")
	List<Transactionhistory> searchWithReferenceAndBranchCodePayable(@Param("reference") String reference,
			@Param("branch_code") String branch_code);

//	@Select("select dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp,users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id  and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch_code like '%'+#{branch_code}+'%' and dp.availability=1 union "
//			+ "select pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname  from payable_credit pc,users u,file_user_date fud, payable_matched pm  where  u.id =fud.user_id and pc.file_id=fud.file_id and pc.upload_date between #{min_upload_date} and #{max_upload_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
//			+ "select pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,users u,file_user_date fud, payable_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
//	public List<Transactionhistory> searchWithUploadDatePayable(@Param("min_upload_date") int min_upload_date,
//			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
//			@Param("value_date") String value_date, @Param("branch_code") String branch_code);


	@Select("select dp.id,dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date from data_payable dp where dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch_code like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc, payable_matched pm  where pc.upload_date between #{min_upload_date} and #{max_upload_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			+ "select pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date from payable_debit pd, payable_matched pm  where pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithUploadDatePayable(@Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

//	@Select("select pc.id, pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname  from payable_credit pc,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pc.file_id=fud.file_id and  pm.match_date between #{min_match_date} and #{max_match_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
//			+ "select pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and  pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
//	public List<Transactionhistory> searchWithMatchDatePayable(@Param("min_match_date") int min_match_date,
//			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
//			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select pc.id, pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc,payable_matched pm where pm.match_date between #{min_match_date} and #{max_match_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			+ "select pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date from payable_debit pd,payable_matched pm  where pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMatchDatePayable(@Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

//	@Select("select distinct dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp, users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  between #{min_amount} and  #{max_amount}  and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch_code like '%'+#{branch_code}+'%' and dp.availability=1  union "
//		  + "select distinct pc.id, pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname  from payable_credit pc, users u,file_user_date fud, payable_matched pm  where  u.id =fud.user_id and pc.file_id=fud.file_id and pc.amount  between #{min_amount} and #{max_amount}  and pc.upload_date between #{min_upload_date} and #{max_upload_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
//		  + "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd, users u,file_user_date fud, payable_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  between #{min_amount} and #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
//	public List<Transactionhistory> searchWithAmountAndUploadDatePayable(@Param("min_amount") float min_amount,
//			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
//			@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
//			@Param("value_date") String value_date, @Param("branch_code") String branch_code);


	@Select("select distinct dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date from data_payable dp where dp.amount  between #{min_amount} and  #{max_amount}  and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch_code like '%'+#{branch_code}+'%' and dp.availability=1  union "
			  + "select distinct pc.id, pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date from payable_credit pc, payable_matched pm  where  pc.amount  between #{min_amount} and #{max_amount}  and pc.upload_date between #{min_upload_date} and #{max_upload_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			  + "select distinct pd.id, pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date from payable_debit pd, payable_matched pm  where  pd.amount  between #{min_amount} and #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
		public List<Transactionhistory> searchWithAmountAndUploadDatePayable(@Param("min_amount") float min_amount,
				@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
				@Param("max_upload_date") int max_upload_date, @Param("reference") String reference,
				@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp, users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  >= #{min_amount} and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch_code like '%'+#{branch_code}+'%' and dp.availability=1 union "
			+ "select distinct pc.id, pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname  from payable_credit pc, users u,file_user_date fud, payable_matched pm  where  u.id =fud.user_id and pc.file_id=fud.file_id and pc.amount   >= #{min_amount}  and pc.upload_date between #{min_upload_date} and #{max_upload_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1union "
			+ "select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd, users u,file_user_date fud, payable_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  >= #{min_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountAndUploadDatePayable(@Param("min_amount") float min_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct dp.id, dp.additional_information,dp.amount,dp.dr_cr,dp.match_status,dp.transaction_reference,dp.branch_code,dp.value_date,dp.upload_date,dp.posting_date,u.firstname,u.lastname  from data_payable dp, users u,file_user_date fud  where  u.id =fud.user_id and dp.file_id=fud.file_id and dp.amount  <= #{max_amount} and dp.upload_date between #{min_upload_date} and #{max_upload_date} and dp.additional_information like '%'+ #{reference} + '%' and dp.value_date like '%'+#{value_date}+ '%' and dp.branch_code like '%'+#{branch_code}+'%' and dp.availability=1  union "
			+ "select distinct pc.id, pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname  from payable_credit pc, users u,file_user_date fud, payable_matched pm  where  u.id =fud.user_id and pc.file_id=fud.file_id and pc.amount   <= #{max_amount}  and pc.upload_date between #{min_upload_date} and #{max_upload_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%' and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1union "
			+ "select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd, users u,file_user_date fud, payable_matched pm  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount  <= #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMaxAmountAndUploadDatePayable(@Param("max_amount") float max_amount,
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname  from payable_credit pc,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pc.file_id=fud.file_id and pc.amount between  #{min_amount} and #{max_amount}  and pc.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%'  and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			+ "select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount between  #{min_amount} and #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithAmountUploadDateAndMatchDatePayable(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname  from payable_credit pc,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pc.file_id=fud.file_id and pc.amount >=  #{min_amount} and pc.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%'  and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			+ "select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount >=  #{min_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountUploadDateAndMatchDatePayable(
			@Param("min_amount") float min_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct pc.id,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname  from payable_credit pc,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pc.file_id=fud.file_id and pc.amount <=  #{max_amount} and pc.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%'  and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			+ "select distinct pd.id,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount <=  #{max_amount}  and pd.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1")
	public List<Transactionhistory> searchWithMaxAmountUploadDateAndMatchDatePayable(
			@Param("max_amount") float max_amount, @Param("min_upload_date") int min_upload_date,
			@Param("max_upload_date") int max_upload_date, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct pc.id ,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname  from payable_credit pc,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pc.file_id=fud.file_id and  pc.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%'  and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			+ "select distinct pd.id ,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and  pd.upload_date between #{min_upload_date} and #{max_upload_date} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%'  and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithUploadDateAndMatchDatePayable(
			@Param("min_upload_date") int min_upload_date, @Param("max_upload_date") int max_upload_date,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct pc.id ,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname  from payable_credit pc,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pc.file_id=fud.file_id and pc.amount between #{min_amount} and #{max_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%'  and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			+ "select distinct pd.id ,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_credit pd,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount between #{min_amount} and #{max_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%' and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithAmountAndMatchDatePayable(@Param("min_amount") float min_amount,
			@Param("max_amount") float max_amount, @Param("min_match_date") int min_match_date,
			@Param("max_match_date") int max_match_date, @Param("reference") String reference,
			@Param("value_date") String value_date, @Param("branch_code") String branch_code);

	@Select("select distinct pc.id ,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname  from payable_credit pc,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pc.file_id=fud.file_id and pc.amount >= #{min_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%'  and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			+ "select distinct pd.id ,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount >= #{min_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%' and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMinAmountAndMatchDatePayable(@Param("min_amount") float min_amount,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select distinct pc.id ,pc.additional_information,pc.amount,pc.dr_cr,pc.match_status,pc.transaction_reference,pc.branch_code,pc.value_date,pc.upload_date,pc.posting_date,u.firstname,u.lastname  from payable_credit pc,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pc.file_id=fud.file_id and pc.amount <= #{max_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and pc.additional_information like '%'+ #{reference} + '%' and pc.value_date like '%'+#{value_date}+ '%' and pc.branch_code like '%'+#{branch_code}+'%'  and pm.payable_credit_id = pc.id and pc.availability = 1 and pc.status=1 union "
			+ "select distinct pd.id ,pd.additional_information,pd.amount,pd.dr_cr,pd.match_status,pd.transaction_reference,pd.branch_code,pd.value_date,pd.upload_date,pd.posting_date,u.firstname,u.lastname  from payable_debit pd,payable_matched pm, users u,file_user_date fud  where  u.id =fud.user_id and pd.file_id=fud.file_id and pd.amount <= #{max_amount} and pm.match_date between #{min_match_date} and #{max_match_date} and pd.additional_information like '%'+ #{reference} + '%' and pd.value_date like '%'+#{value_date}+ '%' and pd.branch_code like '%'+#{branch_code}+'%' and pm.match_id = pd.match_id and pd.availability = 1 and pd.status=1 ")
	public List<Transactionhistory> searchWithMaxAmountAndMatchDatePayable(@Param("max_amount") float max_amount,
			@Param("min_match_date") int min_match_date, @Param("max_match_date") int max_match_date,
			@Param("reference") String reference, @Param("value_date") String value_date,
			@Param("branch_code") String branch_code);

	@Select("select  rna.id,rm.match_date,rm.reconciliation_type,u.firstname,u.lastname,rac.amount,rac.additional_information,rac.branch_code  from rtgs__ats rna,rtgs_matched rm,users u,user_rgts_matched urm,rtgs__ rac where u.id=urm.user_id and rm.id=urm.rtgs_matched_partial_id and rna.id=rm.rtgs__ats_id and rm.match_id=rac.match_id and  rna.id=#{id} and rna.value_date_type=#{value_date}  union "
			+ "select  bna.id,bm.match_date,bm.reconciliation_type,u.firstname,u.lastname,bac.amount,bac.additional_information,bac.branch_code  from b2b__ats bna,b2b_matched bm,users u,user_b2b_matched ubm,b2b__ bac where u.id=ubm.user_id and bm.id=ubm.b2b_matched_partial_id and bna.id=bm.b2b__ats_id and bm.match_id=bac.match_id and  bna.id=#{id} and bna.value_date_type=#{value_date} union "
			+ "select  ena.id,em.match_date,em.reconciliation_type,u.firstname,u.lastname,eac.amount,eac.additional_information,eac.branch_code  from erca__ats ena,erca_matched em,users u,user_erca_matched uem,erca__ eac where u.id=uem.user_id and em.id=uem.erca_matched_id and ena.id=em.erca__ats_id and em.match_id=eac.match_id and  ena.id=#{id} and ena.value_date_type=#{value_date} union "
			+ "select  sna.id,sm.match_date,sm.reconciliation_type,u.firstname,u.lastname,sac.amount,sac.additional_information,sac.branch_code  from sos__ats sna,sos_matched sm,users u,user_sos_matched usm,sos__ sac where u.id=usm.user_id and sm.id=usm.sos_matched_id and sna.id=sm.sos__ats_id and sm.match_id=sac.match_id and  sna.id=#{id} and sna.value_date_type=#{value_date} union "
			+ "select  apm.id,pm.match_date,pm.reconciliation_type,u.firstname,u.lastname,cpm.amount,cpm.additional_information,cpm.branch_code  from ats_partially_matched apm,partially_matched pm,users u,user_parial_match upm,_partially_matched cpm where u.id=upm.user_id and pm.id=upm.partial_matched_id and apm.id=pm.ats_id and pm.match_id=cpm.match_id and  apm.id=#{id} and apm.value_date_type=#{value_date} ")
	public MatchDetailAts matchDetailAts(@Param("id") Long id, @Param("value_date") String value_date);

	@Update("update edit_reason set current_id=#{current_id},match_data_id=#{match_data_id},type=#{type} where current_id=#{id}")
	boolean updateEditReason(@Param("current_id") Long current_id, @Param("match_data_id") Long matched_data_id,
			@Param("type") String type, @Param("id") Long long1);

	@Select("select distinct dna.id, dna.reference, dna.amount, dna.value_date_type, dna.dr_cr, dna.upload_date, dna.additional_information, er.edit_delete from data__ats dna, edit_reason er where dna.id=er.current_id and er.type='data__ats'"
			+ "union select distinct rna.id, rna.reference, rna.amount, rna.value_date_type, rna.dr_cr, rna.upload_date, rna.additional_information,er.edit_delete from rtgs__ats rna, edit_reason er where rna.id=er.current_id and er.type='rtgs__ats' and er.edit_delete='1'"
			+ "union select distinct bna.id, bna.reference, bna.amount, bna.value_date_type, bna.dr_cr, bna.upload_date, bna.additional_information,er.edit_delete from b2b__ats bna, edit_reason er where bna.id=er.current_id and er.type='b2b__ats' and er.edit_delete='1' "
			+ "union select distinct ena.id, ena.reference, ena.amount, ena.value_date_type, ena.dr_cr, ena.upload_date, ena.additional_information,er.edit_delete from erca__ats ena, edit_reason er where ena.id=er.current_id and er.type='erca__ats' and er.edit_delete='1'"
			+ "union select distinct sna.id, sna.reference, sna.amount, sna.value_date_type, sna.dr_cr, sna.upload_date, sna.additional_information,er.edit_delete from sos__ats sna, edit_reason er where sna.id=er.current_id and er.type='sos__ats' and er.edit_delete='1'")
	List<File_rtgs__ats> get_edited_ats();

	@Select("select distinct dna.id, dna.amount, dna.value_date, dna.dr_cr, dna.upload_date, dna.additional_information , er.edit_delete, er.type from data__ dna, edit_reason er where dna.id=er.current_id and er.type='data__'"
			+ "union select distinct dr.id, dr.amount, dr.value_date, dr.dr_cr, dr.upload_date, dr.additional_information , er.edit_delete, er.type from data_recivable dr, edit_reason er where dr.id=er.current_id and er.type='data_receivable'"
			+ "union select distinct rna.id, rna.amount, rna.value_date, rna.dr_cr, rna.upload_date, rna.additional_information, er.edit_delete, er.type from rtgs__ rna, edit_reason er where rna.id=er.current_id and er.type='rtgs__' and er.edit_delete='1'"
			+ "union select distinct bna.id, bna.amount, bna.value_date, bna.dr_cr, bna.upload_date, bna.additional_information, er.edit_delete, er.type from b2b__ bna, edit_reason er where bna.id=er.current_id and er.type='b2b__' and er.edit_delete='1' "
			+ "union select distinct ena.id, ena.amount, ena.value_date, ena.dr_cr, ena.upload_date, ena.additional_information, er.edit_delete, er.type from erca__ ena, edit_reason er where ena.id=er.current_id and er.type='erca__' and er.edit_delete='1'"
			+ "union select distinct sna.id, sna.amount, sna.value_date, sna.dr_cr, sna.upload_date, sna.additional_information, er.edit_delete, er.type from sos__ sna, edit_reason er where sna.id=er.current_id and er.type='sos__' and er.edit_delete='1'"
			+ "union select distinct dna.id, dna.amount, dna.value_date, dna.dr_cr, dna.upload_date, dna.additional_information , er.edit_delete ,er.type from data_payable dna, edit_reason er where dna.id=er.current_id and er.type='data_payable'"
			+ "union select distinct rna.id, rna.amount, rna.value_date, rna.dr_cr, rna.upload_date, rna.additional_information, er.edit_delete ,er.type from payable_credit rna, edit_reason er where rna.id=er.current_id and er.type='payable_credit' and er.edit_delete='1' "
			+ "      select distinct bna.id, bna.amount, bna.value_date, bna.dr_cr, bna.upload_date, bna.additional_information, er.edit_delete ,er.type from payable_debit bna, edit_reason er where bna.id=er.current_id and er.type='payable_debit' and er.edit_delete='1'")
	List<File_rtgs__> get_edited_();

	// ==================================================== get payable edited
	// transaction==============================================
	@Select("select distinct dna.id, dna.amount, dna.value_date, dna.dr_cr, dna.upload_date, dna.additional_information ,er.edit_delete from data_payable dna, edit_reason er where dna.id=er.current_id and er.type='data_payable' "
			+ "union select distinct rna.id, rna.amount, rna.value_date, rna.dr_cr, rna.upload_date, rna.additional_information, er.edit_delete from payable_credit rna, edit_reason er where rna.id=er.current_id and er.type='payable_credit' and er.edit_delete='1' "
			+ "union select distinct bna.id, bna.amount, bna.value_date, bna.dr_cr, bna.upload_date, bna.additional_information, er.edit_delete from payable_debit bna, edit_reason er where bna.id=er.current_id and er.type='payable_debit' and er.edit_delete='1'  "
			+ "")
	List<File_rtgs__> get_edited_payable();

//	@Select("select distinct dna.id, dna.amount, dna.value_date, dna.dr_cr, dna.upload_date, dna.additional_information ,er.edit_delete from data_payable dna, edit_reason er where er.type='data_payable' "
//			+ "union select distinct rna.id, rna.amount, rna.value_date, rna.dr_cr, rna.upload_date, rna.additional_information, er.edit_delete from payable_credit rna, edit_reason er where er.type='payable_credit' and er.edit_delete='1' "
//			+ "union select distinct bna.id, bna.amount, bna.value_date, bna.dr_cr, bna.upload_date, bna.additional_information, er.edit_delete from payable_debit bna, edit_reason er where er.type='payable_debit' and er.edit_delete='1'  "
//			+ "")
	//List<File_rtgs__> get_edited_payable();

	@Select("select distinct dna.id, dna.new_old, dna.amount, dna.value_date, dna.dr_cr, dna.upload_date, dna.additional_information, er.reason, u.firstname, u.lastname, er.DATE, er.edit_delete , dna.edit_reason_id from payable_edit_history dna, edit_reason er, users u where er.current_id=#{id} and er.id=dna.edit_reason_id and u.id=er.user_id")
	List<File_rtgs__> get_edited_detail_payable(Long id);
	// ======================================================ending

	@Select("select distinct dna.id, dna.new_old, dna.reference, dna.amount, dna.value_date_type, dna.dr_cr, dna.upload_date, dna.additional_information,er.reason, u.firstname, u.lastname, er.DATE, er.edit_delete , dna.edit_reason_id from _ats_edit_history dna, edit_reason er,users u where er.current_id=#{id} and er.id=dna.edit_reason_id and u.id=er.user_id")
	List<File_rtgs__ats> get_edited_detail_ats(Long id);

	@Select("select distinct dna.id, dna.new_old, dna.amount, dna.value_date, dna.dr_cr, dna.upload_date, dna.additional_information, er.reason, u.firstname, u.lastname, er.DATE, er.edit_delete , dna.edit_reason_id from __edit_history dna, edit_reason er, users u where er.current_id=#{id} and er.id=dna.edit_reason_id and u.id=er.user_id"
			+ " union select distinct dna.id, dna.new_old, dna.amount, dna.value_date, dna.dr_cr, dna.upload_date, dna.additional_information, er.reason, u.firstname, u.lastname, er.DATE, er.edit_delete , dna.edit_reason_id from payable_edit_history dna, edit_reason er, users u where er.current_id=#{id} and er.id=dna.edit_reason_id and u.id=er.user_id")
	List<File_rtgs__> get_edited_detail_(Long id);

	@Select("select  rac.id,rm.match_date,rm.reconciliation_type,u.firstname,u.lastname,rna.amount,rna.reference,rna.value_date_type  from rtgs__ rac,rtgs_matched rm,users u,user_rgts_matched urm,rtgs__ats rna where u.id=urm.user_id and rm.id=urm.rtgs_matched_partial_id and rac.match_id=rm.match_id and rm.rtgs__ats_id=rna.id and  rac.id=#{id} and rac.additional_information=#{reference} union "
			+ "select top 1 bac.id,bm.match_date,bm.reconciliation_type,u.firstname,u.lastname,bna.amount,bna.reference,bna.value_date_type   from b2b__ bac,b2b_matched bm,users u,user_b2b_matched ubm,b2b__ats bna where u.id=ubm.user_id and bm.id=ubm.b2b_matched_partial_id and bac.match_id=bm.match_id and bm.b2b__ats_id=bna.id and  bac.id=#{id} and bac.additional_information=#{reference} union "
			+ "select top 1 eac.id,em.match_date,em.reconciliation_type,u.firstname,u.lastname,ena.amount,ena.reference,ena.value_date_type   from erca__ eac,erca_matched em,users u,user_erca_matched uem,erca__ats ena where u.id=uem.user_id and em.id=uem.erca_matched_id and eac.match_id=em.match_id and em.erca__ats_id=ena.id and  eac.id=#{id} and eac.additional_information=#{reference}  union "
			+ "select sac.id,sm.match_date,sm.reconciliation_type,u.firstname,u.lastname,sna.amount,sna.reference,sna.value_date_type   from sos__ sac,sos_matched sm,users u,user_sos_matched usm,sos__ats sna where u.id=usm.user_id and sm.id=usm.sos_matched_id and sac.match_id=sm.match_id and sm.sos__ats_id=sna.id and  sac.id=#{id}  and sac.additional_information=#{reference} union  "
			+ "select  cpm.id,pm.match_date,pm.reconciliation_type,u.firstname,u.lastname,apm.amount,apm.reference,apm.value_date_type  from _partially_matched cpm,partially_matched pm,users u,user_parial_match upm,ats_partially_matched apm where u.id=upm.user_id and pm.id=upm.partial_matched_id and apm.id=pm.ats_id and pm.match_id=cpm.match_id and cpm.id=#{id} and cpm.additional_information=#{reference}  ")
	public MatchDetail matchDetail(@Param("id") Long id, @Param("reference") String reference);

	@Select("select top 1  pc.id,pm.match_date,pm.reconciliation_type,u.firstname,u.lastname,pd.amount,pd.additional_information,pd.value_date  from payable_credit pc,payable_matched pm,users u,user_payable_matched upm,payable_debit pd where u.id=upm.user_id and pm.id=upm.rtgs_matched_partial_id and pc.id=pm.payable_credit_id and pm.match_id=pd.match_id and  pc.id=#{id}")
	public MatchDetail matchDetailPayableCredit(Long id);

	@Select("select top 1  rc.id,rm.match_date,rm.reconciliation_type,u.firstname,u.lastname,rd.amount,rd.additional_information,rd.value_date  from receivable_credit rc,receivable_matched rm,users u,user_receivable_matched urm,receivable_debit rd where u.id=urm.user_id and rm.id=urm.receivable_matched_id and rc.id=rm.credit_id and rm.match_id=rd.match_id and  rc.id=#{id}")
	public MatchDetail matchDetailReceivableCredit(Long id);

	@Select("select top 1  rc.id,rm.match_date,rm.reconciliation_type,u.firstname,u.lastname,rd.amount,rd.additional_information,rd.value_date  from issue_ rc,issue_matched rm,users u,user_issue_matched urm,issue_qbs rd where u.id=urm.user_id and rm.id=urm.issue_matched_id and rc.id=rm.issue__id and rm.match_id=rd.match_id and  rc.id=#{id}")
	public MatchDetail matchDetailIssue(Long id);

	@Select("select top 1  rc.id,rm.match_date,rm.reconciliation_type,u.firstname,u.lastname,rd.amount,rd.additional_information,rd.value_date  from issue_qbs rc,issue_matched rm,users u,user_issue_matched urm,issue_ rd where u.id=urm.user_id and rm.id=urm.issue_matched_id and rc.match_id=rm.match_id and rm.issue__id=rd.id and  rc.id=#{id}")
	public MatchDetail matchDetailIssueQBS(Long id);

	@Select("select top 1  rd.id,rm.match_date,rm.reconciliation_type,u.firstname,u.lastname,rc.amount,rc.additional_information,rc.value_date  from receivable_credit rc,receivable_matched rm,users u,user_receivable_matched urm,receivable_debit rd where u.id=urm.user_id and rm.id=urm.receivable_matched_id and rc.id=rm.credit_id and rm.match_id=rd.match_id and  rd.id=#{id}")
	public MatchDetail matchDetailReceivableDebit(Long id);

	@Select("select top 1 pd.id,pm.match_date,pm.reconciliation_type,u.firstname,u.lastname,pc.amount,pc.additional_information,pc.value_date  from payable_debit pd,payable_matched pm,users u,user_payable_matched upm,payable_credit pc where u.id=upm.user_id and pm.id=upm.rtgs_matched_partial_id and pd.match_id=pm.match_id and pm.payable_credit_id=pc.id and  pd.id=#{id} ")
	public MatchDetail matchDetailPayableDebit(Long id);
	////////////////////////////////////////////////////////////////////////////

	@Select("               select distinct rna.id, rna.value_date_type, rna.sender, rna.receiver, u.firstname,u.lastname,rm.match_date, "
			+ "				                 rna.additional_information, rna.amount, rna.dr_cr, rna.upload_date, rna.match_status, rna.status,  "
			+ "						         rna.availability, rna.reference,rna.file_id,rm.match_id,mr.reason from users u, match_reason mr, user_rgts_matched urm, rtgs__ats rna, file_account fa, rtgs_matched rm where rna.status = 1 and rna.availability = 1 and rna.match_status=1  "
			+ "			 	                 and rna.file_id = fa.file_id and fa.account_id =#{account_id} and  rm.match_date = #{date} and rm.rtgs__ats_id = rna.id "
			+ "				 	            and rm.availability=1 and rm.status=1 and u.id=urm.user_id and urm.rtgs_matched_partial_id=rm.id and rm.match_id=mr.match_id and urm.availability=1 and urm.status=1 "
			+ "					                                     union  				  "
			+ "				select distinct sna.id, sna.value_date_type, sna.sender, sna.receiver,  u.firstname,u.lastname,sm.match_date, "
			+ "			                   sna.additional_information, sna.amount, sna.dr_cr, sna.upload_date, sna.match_status, sna.status,  "
			+ "					             sna.availability, sna.reference,sna.file_id, sm.match_id, mr.reason from  users u, match_reason mr, user_sos_matched usm, sos__ats sna, sos_matched sm, file_account fa where sna.status = 1 and sna.availability = 1 and sna.match_status = 1 "
			+ "						 		and sna.file_id = fa.file_id and fa.account_id = #{account_id} and sm.match_date =#{date} and sm.sos__ats_id = sna.id  "
			+ "								and sm.availability=1 and sm.status =1 and u.id=usm.user_id and usm.sos_matched_id=sm.id and sm.match_id=mr.match_id and usm.availability=1 and usm.status=1 "
			+ "								                         union "
			+ "				select  distinct bna.id, bna.value_date_type, bna.sender, bna.receiver,  u.firstname,u.lastname,bm.match_date, "
			+ "			                 bna.additional_information, bna.amount, bna.dr_cr, bna.upload_date, bna.match_status, bna.status,  "
			+ "						        bna.availability, bna.reference,bna.file_id, bm.match_id, mr.reason from users u, match_reason mr, user_b2b_matched ubm, b2b__ats bna, b2b_matched bm, file_account fa where bna.status = 1 and bna.availability = 1 and bna.match_status = 1  "
			+ "						 		and bna.file_id = fa.file_id and fa.account_id= #{account_id} and bm.match_date= #{date} and bm.b2b__ats_id = bna.id and bm.match_id=mr.match_id and bm.status=1 and  "
			+ "				 		      bm.availability=1 and u.id=ubm.user_id and ubm.b2b_matched_partial_id=bm.id and ubm.availability=1 and ubm.status=1 "
			+ "						                                 union "
			+ "				select  distinct ena.id, ena.value_date_type, ena.sender, ena.receiver, u.firstname,u.lastname,em.match_date, "
			+ "					                 ena.additional_information, ena.amount, ena.dr_cr, ena.upload_date, ena.match_status, ena.status,  "
			+ "				               ena.availability, ena.reference, ena.file_id, em.match_id, mr.reason  from  users u, match_reason mr ,user_erca_matched uem, erca__ats ena, file_account fa, erca_matched em where ena.status = 1 and ena.availability = 1 and ena.match_status = 1 "
			+ "					          and ena.file_id = fa.file_id and fa.account_id = #{account_id} and em.match_date= #{date} and em.erca__ats_id=ena.id and em.match_id=mr.match_id and em.status=1 "
			+ "					        and em.availability =1  and u.id=uem.user_id and uem.erca_matched_id=em.id and uem.availability=1 and uem.status=1")
	List<File_rtgs__ats> get_all_ats_matched_with_reason(@Param("date") String recon_date,
			@Param("account_id") Long account_id);


	@Select("select distinct eac.id, eac.posting_date, eac.transaction_reference, eac.branch_code, "
			+ "		 		eac.amount, eac.dr_cr, eac.upload_date, eac.match_status, eac.status, eac.availability,u.firstname,u.lastname,em.match_date, "
			+ "			 			eac.value_date, eac.additional_information, eac.file_id, eac.match_id, mr.reason from erca__ eac,  match_reason mr, file_account fa,users u,user_erca_matched uem, erca_matched em where eac.status = 1 and eac.availability = 1 and eac.match_status = 1 "
			+ "				  and eac.file_id = fa.file_id and fa.account_id = #{account_id} and em.match_id = eac.match_id and em.status =1 and "
			+ "					em.availability=1 and em.match_date= #{date} and u.id= uem.user_id and uem.erca_matched_id=em.id and em.match_id=mr.match_id and uem.availability=1 and uem.status=1 union "
			+ "		 select distinct bac.id, bac.posting_date, bac.transaction_reference, bac.branch_code, "
			+ "					bac.amount, bac.dr_cr, bac.upload_date, bac.match_status, bac.status, bac.availability ,u.firstname,u.lastname,bm.match_date, "
			+ "					bac.value_date, bac.additional_information, bac.file_id, bac.match_id, mr.reason from b2b__ bac, match_reason mr, b2b_matched bm, file_account fa, "
			+ "					users u, user_b2b_matched ubm where bac.status = 1 and bac.availability = 1 and bac.match_status = 1 "
			+ "						 		and bac.file_id = fa.file_id and fa.account_id = #{account_id} and bm.match_date = #{date}   and bm.match_id=bac.match_id and "
			+ "						 	bm.availability =1 and bm.status =1 and  u.id=ubm.user_id and ubm.b2b_matched_partial_id=bm.id and ubm.availability=1 and bm.match_id=mr.match_id and ubm.status=1 union "
			+ "			select distinct  sac.id, sac.posting_date, sac.transaction_reference, sac.branch_code, "
			+ "					sac.amount, sac.dr_cr, sac.upload_date, sac.match_status, sac.status, sac.availability,u.firstname,u.lastname,sm.match_date, "
			+ "					sac.value_date, sac.additional_information, sac.file_id, sac.match_id, mr.reason from sos__ sac, match_reason mr, sos_matched sm, file_account fa, "
			+ "					users u,user_sos_matched usm  where sac.status = 1 and sac.availability = 1 and sac.match_status = 1 "
			+ "					 			and sac.file_id = fa.file_id and fa.account_id =  #{account_id} and sm.match_date =  #{date}   and sm.match_id = sac.match_id  "
			+ "					 			and sm.availability =1 and sm.status =1 and usm.user_id=u.id and usm.sos_matched_id=sm.id and sm.match_id=mr.match_id and usm.availability=1 and usm.status=1			union "
			+ "			select  distinct rac.id, rac.posting_date, rac.transaction_reference, rac.branch_code, "
			+ "				rac.amount, rac.dr_cr, rac.upload_date, rac.match_status, rac.status, rac.availability,u.firstname,u.lastname,rm.match_date, "
			+ "					rac.value_date, rac.additional_information, rac.file_id, rac.match_id, mr.reason from rtgs__ rac, match_reason mr, file_account fa,  "
			+ "					users u,user_rgts_matched urm,rtgs_matched rm where rac.status = 1 and rac.availability = 1 and rac.match_status = 1 "
			+ "					 and rac.file_id = fa.file_id and fa.account_id = #{account_id} and rm.match_date = #{date}  and rm.match_id=rac.match_id "
			+ "					 and urm.user_id=u.id and urm.rtgs_matched_partial_id=rm.id and urm.availability=1 and rm.match_id=mr.match_id and urm.status=1")
	List<File_rtgs__> get_all__matched_with_reason(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Select("		select  distinct rac.id, rac.posting_date, rac.transaction_reference, rac.branch_code, "
			+ "				rac.amount, rac.dr_cr, rac.upload_date, rac.match_status, rac.status, rac.availability,u.firstname,u.lastname,rm.match_date, "
			+ "					rac.value_date, rac.additional_information, rac.file_id, rm.match_id, mr.reason from payable_credit rac, match_reason mr, "
			+ "					users u,user_payable_matched urm,payable_matched rm where rac.status = 1 and rac.availability = 1 "
			+ "					 and rm.payable_credit_id=rac.id "
			+ "					 and urm.user_id=u.id and urm.rtgs_matched_partial_id=rm.id and urm.availability=1 and rm.match_id=mr.match_id and urm.status=1")
	List<File_rtgs__> get_payable_creditmatched_with_reason();

	@Select("		select  distinct rac.id, rac.posting_date, rac.transaction_reference, rac.branch_code, "
			+ "				rac.amount, rac.dr_cr, rac.upload_date, rac.match_status, rac.status, rac.availability,u.firstname,u.lastname,rm.match_date, "
			+ "					rac.value_date, rac.additional_information, rac.file_id, rm.match_id, mr.reason from payable_debit rac, match_reason mr,   "
			+ "					users u,user_payable_matched urm,payable_matched rm where rac.status = 1 and rac.availability = 1  "
			+ "					 and rm.match_id=rac.match_id "
			+ "					 and urm.user_id=u.id and urm.rtgs_matched_partial_id=rm.id and urm.availability=1 and rm.match_id=mr.match_id and urm.status=1")
	List<File_rtgs__> get_payable_debitmatched_with_reason();

	@Select("select amount from data__ats where id=#{long1}")
	Double getAtsAmount(Long long1);

	@Select("select amount from data__ where id=#{long1}")
	Double getAmount(String long1);

	@Select("select REFERENCE from data__ats where id =#{long1}")
	String getReference(Long long1);

	@Select("select count(*) from files  where type='Payable' and availability=1 ")
	int check_payable_for_firstly();

//	@Select("select *" + " from data_payable dp, file_account fa where "
//			+ " dp.dr_cr = 'cr' and dp.status = 1 and dp.availability = 1 and dp.match_status = 0 and dp.file_id = fa.file_id and fa.account_id = #{account_id} ")
//	List<File_rtgs__> get_credit_for_reconcile(@Param("account_id") Long account_id);

	@Select("select *" + " from data_payable dp where "
			+ " dp.dr_cr = 'cr' and dp.status = 1 and dp.availability = 1")
	List<File_rtgs__> get_credit_for_reconcile();
//
//	@Select("select *" + " from data_payable dp, file_account fa where "
//			+ " dp.dr_cr = 'dr' and dp.status = 1 and dp.availability = 1 and dp.match_status = 0 and dp.file_id = fa.file_id and fa.account_id = #{account_id} ")
//	List<File_rtgs__> get_debit_for_reconcile(@Param("account_id") Long account_id);

	@Select("select *" + " from data_payable dp where "
			+ " dp.dr_cr = 'dr' and dp.status = 1 and dp.availability = 1")
	List<File_rtgs__> get_debit_for_reconcile();

	@Select("select beginning_balance_ifb from files where upload_date =#{date} and status= 1 and availability =1")
	Double beggingBalance(@Param("date") String date);

	@Select("select dp.value_date, dp.dr_cr,dp.amount, dp.posting_date, dp.transaction_reference, dp.branch_code, dp.additional_information, dp.ctr, dp.balance from "
			+ "data_payable dp where dp.status=1 and dp.availability=1 and upload_date =  #{date} ")
	List<File_rtgs__> PayableRawData_unmatched(String replace);

	@Select("select pc.value_date, pc.dr_cr, pc.amount, pc.posting_date, pc.transaction_reference, pc.branch_code, pc.additional_information, pc.ctr, pc.balance from "
			+ "payable_credit pc where pc.status=1 and pc.availability=1 and upload_date =  #{date} ")
	List<File_rtgs__> PayableRawData_matched_cr(String replace);

	@Select("select pd.value_date, pd.dr_cr, pd.amount, pd.posting_date, pd.transaction_reference, pd.branch_code, pd.additional_information, pd.ctr, pd.balance from "
			+ "payable_debit pd where pd.status=1 and pd.availability=1 and upload_date =  #{date} ")
	List<File_rtgs__> PayableRawData_matched_dr(String replace);



//	@Select("select dr.value_date, dr.dr_cr,dr.amount, dr.posting_date, dr.transaction_reference, dr.branch_code, dr.additional_information"
//			+ ", dr.balance, dr.ctr from "
//			+ "data_recivable dr where dr.status=1 and dr.availability=1 and upload_date = #{date} ")
//	List<File_rtgs__> RecievableRawData_unmatched(String replace);
//
//	@Select("select rc.value_date, rc.dr_cr, rc.amount, rc.posting_date, rc.transaction_reference, rc.branch_code, rc.additional_information"
//			+ ", rc.balance, rc.ctr from "
//			+ "receivable_credit rc where rc.status=1 and rc.availability=1 and upload_date = #{date} ")
//	List<File_rtgs__> RecievableRawData_matched_cr(String replace);
//
//	@Select("select rd.value_date, rd.dr_cr, rd.amount, rd.posting_date, rd.transaction_reference, rd.branch_code, rd.additional_information"
//			+ ", rd.balance, rd.ctr from "

	@Select("select dr.value_date, dr.dr_cr,dr.amount, dr.posting_date, dr.transaction_reference, dr.branch_code, dr.additional_information, dr.ctr, dr.balance from "
			+ "data_recivable dr where dr.status=1 and dr.availability=1 and upload_date = #{date} ")
	List<File_rtgs__> RecievableRawData_unmatched(String replace);

	@Select("select rc.value_date, rc.dr_cr, rc.amount, rc.posting_date, rc.transaction_reference, rc.branch_code, rc.additional_information, rc.ctr, rc.balance from "
			+ "receivable_credit rc where rc.status=1 and rc.availability=1 and upload_date = #{date} ")
	List<File_rtgs__> RecievableRawData_matched_cr(String replace);

	@Select("select rd.value_date, rd.dr_cr, rd.amount, rd.posting_date, rd.transaction_reference, rd.branch_code, rd.additional_information, rd.ctr, rd.balance from "

			+ "receivable_debit rd where rd.status=1 and rd.availability=1 and upload_date = #{date} ")
	List<File_rtgs__> RecievableRawData_matched_dr(String replace);

	@Select("select beginning_balance_ifb from files where upload_date = #{date} and status= 1 and availability =1 and type = 'Payable'")
	Double beggingBalancePayableFromFile(String date);

	@Select("select beginning_balance_ifb from files where upload_date = #{date} and status= 1 and availability =1 and type = 'Receivable'")
	Double beggingBalanceReceivableFromFile(String date);


	@Select(" SELECT CASE "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_mms dsm  WHERE cast(dsm.date as date) <= #{date} and dsm.account_segment like '%121') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_ dsc  WHERE cast(dsc.posting_date as date)<= #{date} and dsc.account like '%12100') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_mms sm  WHERE cast(sm.date as date)<= #{date} and sm.account_segment like '%121') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_ sc  WHERE cast(sc.posting_date as date) <= #{date} and sc.account like '%12100') THEN 'True' "
			+ "ELSE 'False' "
			+ "END AS record_exists; ")
	boolean existsFile121_stationary(@Param("date") String date);

	@Select(" SELECT CASE "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_mms dsm  WHERE cast(dsm.date as date) <= #{date} and dsm.account_segment like '%111') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_ dsc  WHERE cast(dsc.posting_date as date)<= #{date} and dsc.account like '%11100') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_mms sm  WHERE cast(sm.date as date)<= #{date} and sm.account_segment like '%111') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_ sc  WHERE cast(sc.posting_date as date) <= #{date} and sc.account like '%11100') THEN 'True' "
			+ "ELSE 'False' "
			+ "END AS record_exists; ")
	boolean existsFile111_tools(@Param("date") String string);

	@Select(" SELECT CASE "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_mms dsm  WHERE cast(dsm.date as date) <= #{date} and dsm.account_segment like '%113') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_ dsc  WHERE cast(dsc.posting_date as date)<= #{date} and dsc.account like '%11300') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_mms sm  WHERE cast(sm.date as date)<= #{date} and sm.account_segment like '%113') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_ sc  WHERE cast(sc.posting_date as date) <= #{date} and sc.account like '%11300') THEN 'True' "
			+ "ELSE 'False' "
			+ "END AS record_exists; ")
	boolean existsFile113_spares(@Param("date") String string);

	@Select(" SELECT CASE "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_mms dsm  WHERE cast(dsm.date as date) <= #{date} and dsm.account_segment like '%105') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_ dsc  WHERE cast(dsc.posting_date as date)<= #{date} and dsc.account like '%10500') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_mms sm  WHERE cast(sm.date as date)<= #{date} and sm.account_segment like '%105') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_ sc  WHERE cast(sc.posting_date as date) <= #{date} and sc.account like '%10500') THEN 'True' "
			+ "ELSE 'False' "
			+ "END AS record_exists; ")
	boolean existsFile105_uniform(@Param("date") String string);

	@Select(" SELECT CASE "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_mms dsm  WHERE cast(dsm.date as date) <= #{date} and dsm.account_segment like '%119') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_ dsc  WHERE cast(dsc.posting_date as date)<= #{date} and dsc.account like '%11900') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_mms sm  WHERE cast(sm.date as date)<= #{date} and sm.account_segment like '%119') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_ sc  WHERE cast(sc.posting_date as date) <= #{date} and sc.account like '%11900') THEN 'True' "
			+ "ELSE 'False' "
			+ "END AS record_exists; ")
	boolean existsFile119_accessory(@Param("date") String string);

	@Select(" SELECT CASE "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_mms dsm  WHERE cast(dsm.date as date) <= #{date} and dsm.account_segment like '%120') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_ dsc  WHERE cast(dsc.posting_date as date)<= #{date} and dsc.account like '%12000') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_mms sm  WHERE cast(sm.date as date)<= #{date} and sm.account_segment like '%120') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_ sc  WHERE cast(sc.posting_date as date) <= #{date} and sc.account like '%12000') THEN 'True' "
			+ "ELSE 'False' "
			+ "END AS record_exists; ")
	boolean existsFile120_check(@Param("date") String string);

	@Select(" SELECT CASE "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_mms dsm  WHERE cast(dsm.date as date) <= #{date} and dsm.account_segment like '%112') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_ dsc  WHERE cast(dsc.posting_date as date)<= #{date} and dsc.account like '%11200') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_mms sm  WHERE cast(sm.date as date)<= #{date} and sm.account_segment like '%112') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_ sc  WHERE cast(sc.posting_date as date) <= #{date} and sc.account like '%11200') THEN 'True' "
			+ "ELSE 'False' "
			+ "END AS record_exists; ")
	boolean existsFile112_sanitory(@Param("date") String string);

	@Select(" SELECT CASE "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_mms dsm  WHERE cast(dsm.date as date) <= #{date} and dsm.account_segment like '%106') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_ dsc  WHERE cast(dsc.posting_date as date)<= #{date} and dsc.account like '%10600') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_mms sm  WHERE cast(sm.date as date)<= #{date} and sm.account_segment like '%106') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_ sc  WHERE cast(sc.posting_date as date) <= #{date} and sc.account like '%10600') THEN 'True' "
			+ "ELSE 'False' "
			+ "END AS record_exists; ")
	boolean existsFile106_computer(@Param("date") String string);


	@Select(" SELECT CASE "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_mms dsm  WHERE cast(dsm.date as date) <= #{date} and dsm.account_segment like '%107') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_ dsc  WHERE cast(dsc.posting_date as date)<= #{date} and dsc.account like '%10700') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_mms sm  WHERE cast(sm.date as date)<= #{date} and sm.account_segment like '%107') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_ sc  WHERE cast(sc.posting_date as date) <= #{date} and sc.account like '%10700') THEN 'True' "
			+ "ELSE 'False' "
			+ "END AS record_exists; ")
	boolean existsFile107_furniture(@Param("date") String string);

	@Select(" SELECT CASE "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_mms dsm  WHERE cast(dsm.date as date) <= #{date} and dsm.account_segment like '%104') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM data_stock_ dsc  WHERE cast(dsc.posting_date as date)<= #{date} and dsc.account like '%10400') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_mms sm  WHERE cast(sm.date as date)<= #{date} and sm.account_segment like '%104') THEN 'True' "
			+ "WHEN EXISTS (SELECT 1 FROM stock_ sc  WHERE cast(sc.posting_date as date) <= #{date} and sc.account like '%10400') THEN 'True' "
			+ "ELSE 'False' "
			+ "END AS record_exists; ")
	boolean existsFile104_office_equipment(@Param("date") String string);
	
	@Select("IF EXISTS (SELECT * FROM data__ WHERE id = #{id}) SELECT 'True' ELSE SELECT 'False'")
	boolean IdExist(Long id);

//	@Select("select beginning_balance_ifb from files where upload_date =#{date} and status= 1 and availability =1 ")
//	double beginningBalanceRecievable(String date);



















}
