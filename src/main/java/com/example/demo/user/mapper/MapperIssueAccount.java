package com.example.demo.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.HttpStatus;

import com.example.demo.abebayehu.entity.Raw_fixed_mms;
import com.example.demo.model.Comment;
import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;
import com.example.demo.user.model.MatchDetailAts;
import com.example.demo.user.model.MatchDetail;
import com.example.demo.user.model.Transactionhistory;

@Mapper
public interface MapperIssueAccount {

	@Select("select * from data_issue_ where status = 1 and availability = 1 and match_status = 0")
	List<File_rtgs__> get__issue_for_recon();

	@Select("select ic.posting_date,ic.additional_information,ic.amount,ic.branch,ic.file_id,ic.match_status,ic.value_date,u.firstname,u.middlename,"
			+ "	u.lastname,im.match_date,im.match_id,ic.id,ic.upload_date,ic.dr_cr from issue_ ic,users u,user_issue_matched uim, issue_matched im where ic.status = 1 and ic.availability = 1 and ic.match_status = 1"
			+ "	and im.issue__id= ic.id  and u.id=uim.user_id and uim.issue_matched_id=im.id and uim.availability=1 and uim.status=1 and im.match_date between #{minDate} and #{maxDate} ;")
	List<File_rtgs__> get__issue_for_view_matched(@Param("minDate") int minDate,
			@Param("maxDate") int maxDate);

	@Select("select * from issue__reversal where match_date between #{minDate} and #{maxDate} and availability=1;")
	List<File_rtgs__> get__issue_reversal_matched(@Param("minDate") int minDate,
			@Param("maxDate") int maxDate);

	@Select("select * from issue_qbs_reversal where match_date between #{minDate} and #{maxDate} and availability=1;")
	List<File_rtgs__> get_qbs_issue_reversal_matched(@Param("minDate") int minDate,
			@Param("maxDate") int maxDate);

	@Select("select * from data_issue_ where availability=1 and status=1 and match_status=0;")
	List<File_rtgs__> get__issue_for_view_unmatched();

	@Select("SELECT distinct dic.id, dic.posting_date, dic.value_date, dic.additional_information, dic.branch, dic.amount, dic.dr_cr, dic.balance, \r\n"
			+ "dic.upload_date, dic.match_status, dic.status, dic.availability, dic.file_id\r\n"
			+ "FROM data_issue_ dic JOIN data_issue_qbs diq ON dic.amount = diq.amount \r\n"
			+ "WHERE NOT EXISTS (SELECT 1 FROM STRING_SPLIT(dic.branch, ' ') AS word WHERE diq.additional_information NOT LIKE '%' + word.value + '%') order by dic.amount asc;")
	List<File_rtgs__> get__issue_for_recon_auto();

	@Select("SELECT distinct diq.id, diq.posting_date, diq.value_date, diq.additional_information, diq.branch, diq.amount, diq.dr_cr, diq.balance, \r\n"
			+ "diq.upload_date, diq.match_status, diq.status, diq.availability, diq.file_id \r\n"
			+ "FROM data_issue_qbs diq JOIN data_issue_ dic ON diq.amount = dic.amount \r\n"
			+ "WHERE NOT EXISTS (SELECT 1 FROM STRING_SPLIT(dic.branch, ' ') AS word WHERE diq.additional_information NOT LIKE '%' + word.value + '%') order by diq.amount asc;")
	List<File_rtgs__> get_qbs_issue_for_recon_auto();

	@Select("select * from data_issue_qbs where status = 1 and availability = 1 and match_status = 0")
	List<File_rtgs__> get_qbs_issue_for_recon();

	@Select("select iq.posting_date,iq.additional_information,iq.amount,iq.branch,iq.file_id,iq.match_status,iq.value_date,u.firstname,u.middlename,"
			+ "	u.lastname,im.match_date,im.match_id,iq.id,iq.upload_date,iq.dr_cr from issue_qbs iq,users u,user_issue_matched uim, issue_matched im where iq.status = 1 and iq.availability = 1 and iq.match_status = 1"

			+ "	and im.match_id= iq.match_id  and u.id=uim.user_id and uim.issue_matched_id=im.id and uim.availability=1 and uim.status=1 and im.match_date between #{minDate} and #{maxDate} ;")
	List<File_rtgs__> get_qbs_issue_for_view_matched(@Param("minDate") int minDate,
			@Param("maxDate") int maxDate);

	@Select("select * from data_issue_qbs where availability=1 and status=1 and match_status=0;")
	List<File_rtgs__> get_qbs_issue_for_view_unmatched();

	@Select("INSERT INTO issue_(posting_date, value_date, additional_information, branch, amount, dr_cr, balance, upload_date, match_status, status, availability, file_id) "
			+ "OUTPUT Inserted.id SELECT posting_date, value_date, additional_information, branch, amount, dr_cr, balance, upload_date, '1', status, availability,file_id "
			+ "FROM data_issue_ where id = #{id}; delete from data_issue_ where id = #{id};")
	Long moveIssueData(@Param("id") Long id);

	@Select("INSERT INTO issue__reversal(posting_date, value_date, additional_information, branch, amount, dr_cr, balance, upload_date, match_status,match_id,match_date, status, availability, file_id) "
			+ "OUTPUT Inserted.id SELECT posting_date, value_date, additional_information, branch, amount, dr_cr, balance, upload_date, '1',#{match_id},#{date_now},status, availability,file_id "
			+ "FROM data_issue_ where id = #{id}; delete from data_issue_ where id = #{id};")
	Long moveIssueReversalData(@Param("id") Long id, @Param("match_id") String match_id,
			@Param("date_now") Long date_now);

	@Select("INSERT INTO issue_qbs_reversal(posting_date, value_date, additional_information, branch, amount, dr_cr, balance, upload_date, match_status,match_id,match_date, status, availability, file_id) "
			+ "OUTPUT Inserted.id SELECT posting_date, value_date, additional_information, branch, amount, dr_cr, balance, upload_date, '1',#{match_id},#{date_now}, status, availability,file_id "
			+ "FROM data_issue_qbs where id = #{id}; delete from data_issue_qbs where id = #{id};")
	Long moveIssueQBSeReversalData(@Param("id") Long id, @Param("match_id") String match_id,
			@Param("date_now") Long date_now);

	@Select("INSERT INTO dbo.issue_matched(issue__id, match_id, match_date, reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
			+ "VALUES ( #{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many}, #{status}, #{availability});")
	Long addIssueMatched(@Param("id") Long id, @Param("match_id") String match_id, @Param("date") Long date_now,
			@Param("recon_type") String recon_type, @Param("many_to_many") String many_to_many,
			@Param("status") String status, @Param("availability") String availability);

	@Insert("INSERT INTO dbo.user_issue_matched ( user_id, issue_matched_id, DATE, status, availability,  type ) "
			+ "VALUES (#{user_id}, #{issue_matched_id}, #{date},  #{status}, #{availability}, #{type});")
	void addUserIssueMatched(@Param("user_id") Long user_id, @Param("issue_matched_id") Long RtgsMatchedId,
			@Param("date") Long date, @Param("status") String status, @Param("availability") String availability,
			@Param("type") String type);

	@Update("update edit_reason set current_id=#{current_id},match_data_id=#{match_data_id},type=#{type} where current_id=#{id}")
	void updateEditReason(@Param("current_id") Long current_id, @Param("match_data_id") Long matched_data_id,
			@Param("type") String type, @Param("id") Long long1);

	@Select("INSERT INTO issue_qbs(posting_date, value_date, additional_information, branch, amount, dr_cr, balance, upload_date, match_status, status, availability, file_id, match_id) "
			+ "OUTPUT Inserted.id SELECT posting_date, value_date, additional_information, branch, amount, dr_cr, balance, upload_date, '1', status, availability, file_id,  #{match_id} "
			+ "FROM data_issue_qbs where id = #{id}; delete from data_issue_qbs where id = #{id};")
	Long moveIssueQbsData(@Param("id") Long long1, @Param("match_id") String match_id);

	@Select("INSERT INTO data_issue_qbs(posting_date, balance, branch, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id )OUTPUT Inserted.id "
			+ "SELECT posting_date, balance, branch, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id "
			+ "FROM issue_qbs where id = #{id}; update issue_qbs set availability = 0 where id = #{id};")
	Long moveIssueQbsMatched(@Param("id") Long long1);

	@Update("update issue_matched set availability=0 where issue__id=#{id}")
	public void deleteIssueMatched(@Param("id") Long long1);

	@Select("INSERT INTO data_issue_(posting_date, balance, branch, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id )OUTPUT Inserted.id "
			+ "SELECT posting_date, balance, branch, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id "
			+ "FROM issue_ where id = #{id}; update issue_ set availability = 0 where id = #{id};")
	Long moveIssueMatched(@Param("id") Long long1);

	@Select("INSERT INTO data_issue_(posting_date, balance, branch, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id )OUTPUT Inserted.id "
			+ "SELECT posting_date, balance, branch, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id "
			+ "FROM issue__reversal where id = #{id}; update issue__reversal set availability = 0 where id = #{id};")
	Long moveIssueMatchedReveersal(@Param("id") Long long1);

	@Select("INSERT INTO data_issue_qbs(posting_date, balance, branch, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id )OUTPUT Inserted.id "
			+ "SELECT posting_date, balance, branch, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id "
			+ "FROM issue_qbs_reversal where id = #{id}; update issue_qbs_reversal set availability = 0 where id = #{id};")
	Long moveIssueMatchedQBsReveersal(@Param("id") Long long1);

	@Select("select id from issue_matched where issue__id = #{id}")
	Long getIssueMatchedId(@Param("id") long id);

	@Update("update user_issue_matched set availability=0 where issue_matched_id = #{id}")
	void deleteUserIssueMatched(@Param("id") Long id);

	@Insert("insert into match_reason (match_id, reason, type, reference_amount, status, availability) values "
			+ " (#{match_id}, #{reason}, #{type}, #{reference_amount}, #{status}, #{availability});")
	void addMatchReason(@Param("match_id") String match_id, @Param("reason") String reason, @Param("type") String type,
			@Param("reference_amount") String reference_amount, @Param("status") String status,
			@Param("availability") String availability);

	@Select("select  distinct rac.id, rac.posting_date,rac.branch as branch_code,\r\n"
			+ "	rac.amount, rac.dr_cr, rac.upload_date, rac.match_status, rac.status, rac.availability,u.firstname,u.lastname,rm.match_date,\r\n"
			+ "	rac.value_date, rac.additional_information, rac.file_id, rm.match_id, mr.reason from issue_ rac, match_reason mr, \r\n"
			+ "	users u,user_issue_matched urm,issue_matched rm where rac.status = 1 and rac.availability = 1 and rac.match_status = 1\r\n"
			+ "	 and rm.issue__id=rac.id\r\n"
			+ "	and urm.user_id=u.id and urm.issue_matched_id=rm.id and urm.availability=1 and rm.match_id=mr.match_id and urm.status=1")
	List<File_rtgs__> get_issue__matched_with_reason();

	@Select("select  distinct rac.id, rac.posting_date,rac.branch as branch_code,\r\n"
			+ "	rac.amount, rac.dr_cr, rac.upload_date, rac.match_status, rac.status, rac.availability,u.firstname,u.lastname,rm.match_date,\r\n"
			+ "	rac.value_date, rac.additional_information, rac.file_id, rm.match_id, mr.reason from issue_qbs rac, match_reason mr, \r\n"
			+ "	users u,user_issue_matched urm,issue_matched rm where rac.status = 1 and rac.availability = 1 and rac.match_status = 1\r\n"
			+ "	 and rm.match_id=rac.match_id\r\n"
			+ "	and urm.user_id=u.id and urm.issue_matched_id=rm.id and urm.availability=1 and rm.match_id=mr.match_id and urm.status=1")
	List<File_rtgs__> get_issue_qbs_matched_with_reason();

	@Select("select distinct dna.id, dna.amount, dna.value_date, dna.dr_cr, dna.upload_date, dna.additional_information , er.edit_delete from data_issue_ dna, edit_reason er where dna.id=er.current_id and er.type='data_issue_'\r\n"
			+ "union select distinct rna.id, rna.amount, rna.value_date, rna.dr_cr, rna.upload_date, rna.additional_information, er.edit_delete from issue_ rna, edit_reason er where rna.id=er.current_id and er.type='issue_' and er.edit_delete='1' ")
	List<File_rtgs__> get_edited_issue_();

	@Select("select distinct dna.id, dna.amount, dna.value_date, dna.dr_cr, dna.upload_date, dna.additional_information , er.edit_delete from data_issue_qbs dna, edit_reason er where dna.id=er.current_id and er.type='data_issue_qbs'\r\n"
			+ "union select distinct rna.id, rna.amount, rna.value_date, rna.dr_cr, rna.upload_date, rna.additional_information, er.edit_delete from issue_qbs rna, edit_reason er where rna.id=er.current_id and er.type='issue_qbs' and er.edit_delete='1' ")
	List<File_rtgs__> get_edited_issue_qbs();

	@Select("select distinct dna.id, dna.new_old, dna.amount, dna.value_date, dna.dr_cr, dna.upload_date, dna.additional_information, er.reason, u.firstname, u.lastname, er.DATE, er.edit_delete , dna.edit_reason_id from issue__history dna, edit_reason er, users u where er.current_id=#{id} and er.id=dna.edit_reason_id and u.id=er.user_id")
	List<File_rtgs__> get_edited_detail_issue_(Long id);

	@Select("select distinct dna.id, dna.new_old, dna.amount, dna.value_date, dna.dr_cr, dna.upload_date, dna.additional_information, er.reason, u.firstname, u.lastname, er.DATE, er.edit_delete , dna.edit_reason_id from issue_qbs_history dna, edit_reason er, users u where er.current_id=#{id} and er.id=dna.edit_reason_id and u.id=er.user_id")
	List<File_rtgs__> get_edited_detail_issue_QBS(Long id);

	@Select("INSERT INTO issue_qbs_history(posting_date, branch, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, branch, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{edit_reason_id},'old' FROM data_issue_qbs where id = #{id}")
	void moveOldIssueQBSData(@Param("id") Long id, @Param("edit_reason_id") Long edit_reason_id);

	@Update("update data_issue_qbs set amount =#{amount}, value_date = #{value_date_type}, "
			+ "dr_cr = #{dr_cr}, upload_date = #{upload_date}, additional_information = #{additional_information} where id = #{id}")
	Boolean updateTransactionQBS(File_rtgs__ats edit_data);

	@Select("INSERT INTO issue_qbs_history(posting_date,branch, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, branch, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{edit_reason_id},'new' FROM data_issue_qbs where id = #{id}")
	void moveEditedIssueQBSeData(@Param("id") Long id, @Param("edit_reason_id") Long edit_reason_id);

	@Select("INSERT INTO issue__history(posting_date, branch, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, branch, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{edit_reason_id},'old' FROM data_issue_ where id = #{id}")
	void moveOldIssueData(@Param("id") Long id, @Param("edit_reason_id") Long edit_reason_id);

	@Update("update data_issue_ set amount =#{amount}, value_date = #{value_date_type}, "
			+ "dr_cr = #{dr_cr}, upload_date = #{upload_date}, additional_information = #{additional_information} where id = #{id}")
	Boolean updateTransaction(File_rtgs__ats edit_data);

	@Select("INSERT INTO issue__history(posting_date,branch, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, branch, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{edit_reason_id},'new' FROM data_issue_ where id = #{id}")
	void moveEditedIssueeData(@Param("id") Long id, @Param("edit_reason_id") Long edit_reason_id);

	@Select("INSERT INTO issue_qbs_history(posting_date,  branch, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, branch, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{reason_id},'delete' FROM data_issue_qbs where id = #{id}")
	void moveDeletedIssueQBSData(@Param("id") Long id, @Param("reason_id") Long reason_id);

	@Update("update data_issue_qbs set availability = '0' where id = #{id}")
	void deleteTransactionissueQBS(@Param("id") Long long1);

	@Select("INSERT INTO issue__history(posting_date,  branch, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id,edit_reason_id,new_old) "
			+ "SELECT posting_date, branch, amount, dr_cr, upload_date, '1', status, "
			+ "availability, value_date, additional_information, file_id, #{reason_id},'delete' FROM data_issue_ where id = #{id}")
	void moveDeletedIssueData(@Param("id") Long id, @Param("reason_id") Long reason_id);

	@Update("update data_issue_ set availability = '0' where id = #{id}")
	void deleteTransactionissueore(@Param("id") Long long1);

	@Select("select distinct dic.id, dic.amount, dic.branch as branch_code, dic.additional_information, SUBSTRING(dic.value_date, 1, 8) as value_date from data_issue_ dic where upload_date <= #{date} and dr_cr = 'DR' and dic.status = 1 and dic.availability = 1 union \r\n"
			+ " 			 select distinct ic.id, ic.amount, ic.branch, ic.additional_information, ic.value_date from issue_ ic, issue_matched im, issue_qbs iq \r\n"
			+ "			 where ic.dr_cr = 'DR' and ic.upload_date <= #{date}  and (iq.match_id = im.match_id and ic.id = im.issue__id and iq.upload_date > #{date} ) and ic.status = 1 and ic.availability = 1  \r\n"
			+ "			 and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1")

	List<File_rtgs__> reportDebit(@Param("date") String date);

	@Select("select distinct dic.id, dic.amount, dic.branch as branch_code, dic.additional_information,  SUBSTRING(dic.value_date, 1, 8) as value_date from data_issue_ dic where upload_date <= #{date} and dr_cr = 'CR' and dic.status = 1 and dic.availability = 1 union \r\n"
			+ " 			  select distinct ic.id, ic.amount, ic.branch, ic.additional_information, ic.value_date from issue_ ic, issue_matched im, issue_qbs iq \r\n"
			+ "			  where ic.dr_cr = 'CR' and ic.upload_date <= #{date} and (iq.match_id = im.match_id and ic.id = im.issue__id and iq.upload_date > #{date}) and ic.status = 1 and ic.availability = 1  \r\n"
			+ "			 and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1")
	List<File_rtgs__> reportCredit(@Param("date") String string);

	@Select("select distinct diq.id, diq.value_date as value_date_type, diq.branch as sender, diq.additional_information as reference, diq.amount from data_issue_qbs diq where upload_date <= #{date} and dr_cr = 'CR' and status = 1 and availability = 1 union \r\n"
			+ " 			select distinct iq.id, iq.value_date as value_date_type, iq.branch as sender, iq.additional_information as reference, iq.amount from issue_qbs iq, issue_matched im, issue_ ic\r\n"
			+ "			 where iq.dr_cr = 'CR' and iq.upload_date <= #{date} and iq.match_id = im.match_id and im.issue__id = ic.id and ic.upload_date > #{date} \r\n"
			+ "			 and iq.status = 1 and iq.availability = 1 and im.status = 1 and im.availability = 1  and ic.status = 1 and ic.availability = 1  \r\n"
			+ "			 ")
	List<File_rtgs__ats> reportQbsCredit(@Param("date") String string);

	@Select("Select distinct dna.id, dna.value_date as value_date_type, dna.branch as sender, dna.additional_information as reference, dna.amount from data_issue_qbs dna where upload_date <= #{date} and dr_cr = 'DR' and status = 1 and availability = 1 union \r\n"
			+ " 			 select distinct rna.id, rna.value_date as value_date_type, rna.branch as sender, rna.additional_information as reference, rna.amount from issue_qbs rna, issue_matched rm, issue_ rac \r\n"
			+ "			 where rna.dr_cr = 'DR' and rna.upload_date <= #{date} and rna.match_id = rm.match_id and rm.issue__id = rac.id and rac.upload_date > #{date} \r\n"
			+ "			 and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1")
	List<File_rtgs__ats> reportQbsDebit(@Param("date") String string);

	@Select("\r\n"
			+ "			 select ((select ISNULL(sum(amount), 0) from data_issue_ dic where upload_date <= #{date} and dr_cr = 'DR' and dic.status = 1 and dic.availability = 1 )\r\n"
			+ "			 + (\r\n"
			+ "			 select ISNULL(sum(ic.amount), 0) from issue_ ic, issue_matched im, issue_qbs iq \r\n"
			+ "			 where ic.dr_cr = 'DR' and ic.upload_date <= #{date} and (iq.match_id = im.match_id and ic.id = im.issue__id and iq.upload_date > #{date}) and ic.status = 1 and ic.availability = 1  \r\n"
			+ "			 and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1))\r\n"
			+ "")
	Double getTotalDebit(@Param("date") String string);

	@Select(" select ((select ISNULL(sum(amount), 0) from data_issue_ dic where upload_date <= #{date} and dr_cr = 'CR' and dic.status = 1 and dic.availability = 1 )\r\n"
			+ "			 + (\r\n"
			+ "			 select ISNULL(sum(ic.amount), 0) from issue_ ic, issue_matched im, issue_qbs iq \r\n"
			+ "			 where ic.dr_cr = 'DR' and ic.upload_date <= #{date} and (iq.match_id = im.match_id and ic.id = im.issue__id and iq.upload_date > #{date}) and ic.status = 1 and ic.availability = 1  \r\n"
			+ "			 and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1))")
	Double getTotalCredit(@Param("date") String string);

//	@Select("select ISNULL(sum(ending_balance_con), 0) from files where upload_date = #{date} and type = ''  and status = 1 and availability = 1")
//	Double getConventionalEndingBalance(@Param("date") String string);
//
	@Select("select ISNULL(sum(ending_balance_ifb), 0) from files where upload_date like #{date}+'%' and type = 'ISSUE_' and status = 1 and availability = 1")
	Double getIfbEndingBalance(@Param("date") String string);

	@Select("select ending_balance_ifb FROM files where upload_date = #{date} and type = 'ISSUE_' and status = 1 and availability = 1")
	Double getQbsEndingbalance(@Param("date") String date);

	@Select("select ((select ISNULL(sum(dna.amount), 0) from data_issue_qbs dna where upload_date <= #{date} and dr_cr = 'CR' and status = 1 and availability = 1)  \r\n"
			+ " 		+ (select ISNULL(sum(rna.amount), 0) from issue_qbs rna, issue_matched rm, issue_ rac \r\n"
			+ "			where rna.dr_cr = 'CR' and rna.upload_date <= #{date} and rna.match_id = rm.match_id and rm.issue__id = rac.id and rac.upload_date > #{date} \r\n"
			+ "			and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1))")
	Double getQbsTotalCredit(@Param("date") String string);

	@Select("select ((select ISNULL(sum(dna.amount), 0) from data_issue_qbs dna where upload_date <= #{date} and dr_cr = 'DR' and status = 1 and availability = 1)  \r\n"
			+ " 		+ (select ISNULL(sum(rna.amount), 0) from issue_qbs rna, issue_matched rm, issue_ rac \r\n"
			+ "			where rna.dr_cr = 'DR' and rna.upload_date <= #{date} and rna.match_id = rm.match_id and rm.issue__id = rac.id and rac.upload_date > #{date} \r\n"
			+ "			and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1))")
	Double getQbsTotalDebit(@Param("date") String string);

	@Select("select distinct ic.id, ic.amount, ic.branch, ic.additional_information, SUBSTRING(ic.value_date, 1, 8) as value_date,iq.value_date as setteled_date from issue_ ic, issue_matched im, issue_qbs iq \r\n"
			+ "where ic.dr_cr = 'DR' and ic.upload_date <= #{date} and ic.upload_date   >= STUFF(#{date}, LEN(#{date})-1, 2, '01')  and  (iq.match_id = im.match_id and ic.id = im.issue__id ) and ic.status = 1 and ic.availability = 1 \r\n"
			+ "and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1 and  cast(iq.value_date as date) > cast(SUBSTRING(ic.value_date, 1, 8) as date)")
	List<File_rtgs__> reportDebitSetteled(String date);

	@Select("select ISNULL(sum(ic.amount), 0) from issue_ ic, issue_matched im, issue_qbs iq \r\n"
			+ "where ic.dr_cr = 'DR' and ic.upload_date   <= #{date} and ic.upload_date   >= STUFF(#{date}, LEN(#{date})-1, 2, '01')  and (iq.match_id = im.match_id and ic.id = im.issue__id ) and ic.status = 1 and ic.availability = 1 \r\n"
			+ "and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1 and  cast(iq.value_date as date) > cast(SUBSTRING(ic.value_date, 1, 8) as date)")
	Double getTotalDebitSetteled(String date);

	@Select("select distinct ic.id, ic.amount, ic.branch, ic.additional_information, SUBSTRING(ic.value_date, 1, 8) as value_date,iq.value_date as setteled_date from issue_ ic, issue_matched im, issue_qbs iq \r\n"
			+ "where ic.dr_cr = 'CR' and ic.upload_date <= #{date} and ic.upload_date   >= STUFF(#{date}, LEN(#{date})-1, 2, '01')  and  (iq.match_id = im.match_id and ic.id = im.issue__id ) and ic.status = 1 and ic.availability = 1 \r\n"
			+ "and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1 and  cast(iq.value_date as date) > cast(SUBSTRING(ic.value_date, 1, 8) as date)")
	List<File_rtgs__> reportCreditSetteled(String replace);

	@Select("select ISNULL(sum(ic.amount), 0) from issue_ ic, issue_matched im, issue_qbs iq \r\n"
			+ "where ic.dr_cr = 'CR' and ic.upload_date   <= #{date} and ic.upload_date   >= STUFF(#{date}, LEN(#{date})-1, 2, '01')  and (iq.match_id = im.match_id and ic.id = im.issue__id ) and ic.status = 1 and ic.availability = 1 \r\n"
			+ "and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1 and  cast(iq.value_date as date) > cast(SUBSTRING(ic.value_date, 1, 8) as date)")
	Double getTotalCreditSetteled(String date);

	@Select(" select distinct iq.id, iq.value_date as value_date_type, iq.branch as sender, iq.additional_information as reference, iq.amount, SUBSTRING(ic.value_date, 1, 8) as setteled_date from issue_qbs iq, issue_matched im, issue_ ic\r\n"
			+ " where iq.dr_cr = 'CR' and iq.upload_date <= #{date} and  iq.upload_date   >= STUFF(#{date}, LEN(#{date})-1, 2, '01') and iq.match_id = im.match_id and im.issue__id = ic.id\r\n"
			+ "and iq.status = 1 and iq.availability = 1 and im.status = 1 and im.availability = 1  and ic.status = 1 and ic.availability = 1 and cast(SUBSTRING(ic.value_date, 1, 8) as date)> cast(iq.value_date as date) ")
	List<File_rtgs__ats> reportQbsCreditSetteled(String date);

	@Select("select ISNULL(sum(iq.amount), 0) from issue_qbs iq, issue_matched im, issue_ ic\r\n"
			+ " where iq.dr_cr = 'CR' and iq.upload_date <= #{date} and iq.upload_date   >= STUFF(#{date}, LEN(#{date})-1, 2, '01') and iq.match_id = im.match_id and im.issue__id = ic.id\r\n"
			+ "and iq.status = 1 and iq.availability = 1 and im.status = 1 and im.availability = 1  and ic.status = 1 and ic.availability = 1 and cast(SUBSTRING(ic.value_date, 1, 8) as date)> cast(iq.value_date as date)")
	Double getQbsTotalCreditSetteled(String date);

	@Select(" select distinct iq.id, iq.value_date as value_date_type, iq.branch as sender, iq.additional_information as reference, iq.amount, SUBSTRING(ic.value_date, 1, 8) as setteled_date from issue_qbs iq, issue_matched im, issue_ ic\r\n"
			+ " where iq.dr_cr = 'DR' and iq.upload_date <= #{date} and  iq.upload_date   >= STUFF(#{date}, LEN(#{date})-1, 2, '01') and iq.match_id = im.match_id and im.issue__id = ic.id\r\n"
			+ "and iq.status = 1 and iq.availability = 1 and im.status = 1 and im.availability = 1  and ic.status = 1 and ic.availability = 1 and cast(SUBSTRING(ic.value_date, 1, 8) as date)> cast(iq.value_date as date) ")
	List<File_rtgs__ats> reportQbsDebitSetteled(String date);

	@Select("select ISNULL(sum(iq.amount), 0) from issue_qbs iq, issue_matched im, issue_ ic\r\n"
			+ " where iq.dr_cr = 'DR' and iq.upload_date <= #{date} and iq.upload_date   >= STUFF(#{date}, LEN(#{date})-1, 2, '01') and iq.match_id = im.match_id and im.issue__id = ic.id\r\n"
			+ "and iq.status = 1 and iq.availability = 1 and im.status = 1 and im.availability = 1  and ic.status = 1 and ic.availability = 1 and cast(SUBSTRING(ic.value_date, 1, 8) as date)> cast(iq.value_date as date)")
	Double getQbsTotalDebitSetteled(String date);

	@Select("IF EXISTS (SELECT distinct * FROM data_issue_qbs diq\r\n"
			+ "			WHERE NOT EXISTS (SELECT 1 FROM STRING_SPLIT(#{branch}, ' ') AS word WHERE diq.additional_information NOT LIKE '%' + word.value + '%') and diq.additional_information=#{branch_2}  ) \r\n"
			+ "			  SELECT 'True' ELSE  SELECT 'False'")
	boolean existsbranchInAdditionalInformation(@Param("branch") String branch,@Param("branch_2") String branch_2);
	@Select(" select additional_information  from data_issue_qbs where id=#{id}")
	String additional_information(Long id);
}
