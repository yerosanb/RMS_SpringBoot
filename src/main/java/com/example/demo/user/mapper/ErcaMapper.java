package com.example.demo.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.user.model.File_erca_;
import com.example.demo.user.model.File_erca_;
import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;

@Mapper

public interface ErcaMapper {

	@Insert("insert into erca__ats (value_date_type, reference, sender, receiver, additional_information, amount, "
			+ "dr_cr, upload_date, match_status, status, availability) values (#{value_date_type}, #{reference}, #{sender}, #{receiver}, "
			+ "#{additional_information}, #{amount}, #{dr_cr}, #{upload_date}, #{match_status}, #{status}, #{availability})")
	public abstract void uploadData_erca_(File_erca_ erca_);
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Select("select * from erca__ats ena, file_account fa,users u,user_erca_matched uem, erca_matched em where ena.status = 1 and ena.availability = 1 and ena.match_status = 1 \r\n"
			+ "			and ena.file_id = fa.file_id and fa.account_id = #{account_id} and em.match_date=#{date} and em.erca__ats_id=ena.id and em.status=1\r\n"
			+ "			and em.availability =1 and u.id=uem.user_id and em.id=uem.erca_matched_id and uem.availability=1 and uem.status=1;")
	List<File_rtgs__ats> get_erca_ats_for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Select("select distinct eac.id, eac.additional_information, eac.amount, eac.value_date, eac.branch_code,\r\n"
			+ "eac.dr_cr, eac.file_id, eac.upload_date, eac.transaction_reference,  eac.posting_date, eac.value_date,u.firstname,u.lastname,em.match_date,\r\n"
			+ "em.match_id from erca__ eac, file_account fa,users u,user_erca_matched uem, erca_matched em where eac.status = 1 and eac.availability = 1 and eac.match_status = 1\r\n"
			+ "	 and eac.file_id = fa.file_id and fa.account_id = #{account_id} and em.match_id = eac.match_id and em.status =1 and em.availability=1 and em.match_date=#{date} and u.id=uem.user_id and em.id=uem.erca_matched_id and uem.availability=1 and uem.status=1")
	List<File_rtgs__> get_erca__for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Select("select  distinct * from b2b__ats bna, b2b_matched bm, file_account fa,users u,user_b2b_matched ubm where bna.status = 1 and bna.availability = 1 and bna.match_status = 1 \r\n"
			+ "			and bna.file_id = fa.file_id and fa.account_id = #{account_id} and bm.match_date= #{date} and bm.b2b__ats_id = bna.id and bm.status=1 and \r\n"
			+ "			bm.availability=1 and u.id=ubm.user_id and ubm.b2b_matched_partial_id=bm.id and  ubm.availability=1 and ubm.status=1")
	List<File_rtgs__ats> get_btb_ats_for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);


@Select("select  distinct bac.id ,bac.additional_information, bac.amount, bac.value_date, bac.branch_code,\r\n"
		+ "			bac.dr_cr, bac.file_id, bac.upload_date, bac.transaction_reference,  bac.posting_date, bac.value_date,u.firstname,u.lastname,bm.match_date,bm.match_id\r\n"
		+ "  from b2b__ bac, b2b_matched bm, file_account fa,users u,user_b2b_matched ubm  where bac.status = 1 and bac.availability = 1 and bac.match_status = 1\r\n"
		+ "				and bac.file_id = fa.file_id and fa.account_id = #{account_id} and bm.match_date = #{date}  and bm.match_id=bac.match_id and\r\n"
		+ "				bm.availability =1 and bm.status =1 and u.id=ubm.user_id and ubm.b2b_matched_partial_id=bm.id and  ubm.availability=1 and ubm.status=1")

	List<File_rtgs__> get_btb__for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Select("select * from rtgs__ats rna, file_account fa where rna.status = 1 and rna.availability = 1 and rna.match_status = 0 "
			+ "and rna.file_id = fa.file_id and fa.account_id = #{account_id} and rna.upload_date = #{date};")
	List<File_erca_> get_btb_ats_unmatched_for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Select("select * from rtgs__ats rna, file_account fa where rna.status = 1 and rna.availability = 1 and rna.match_status = 0 "
			+ "and rna.file_id = fa.file_id and fa.account_id = #{account_id} and rna.upload_date = #{date};")
	List<File_erca_> get_btb__unmatched_for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);
	@Select("select * from sos__ats sna, sos_matched sm, file_account fa,users u,user_sos_matched usm  where sna.status = 1 and sna.availability = 1 and sna.match_status = 1\r\n"
			+ "			 		and sna.file_id = fa.file_id and fa.account_id = #{account_id} and sm.match_date = #{date} and sm.sos__ats_id = sna.id \r\n"
			+ "					and sm.availability=1 and sm.status =1 and u.id= usm.user_id and sm.id=usm.sos_matched_id and usm.availability=1 and usm.status=1")
	List<File_rtgs__ats> get_sos_ats_for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Select("select * from sos__ sac, sos_matched sm, file_account fa,users u,user_sos_matched usm where sac.status = 1 and sac.availability = 1 and sac.match_status = 1\r\n"
			+ "			and sac.file_id = fa.file_id and fa.account_id = #{account_id} and sm.match_date = #{date} and sm.match_id = sac.match_id \r\n"
			+ "			and sm.availability =1 and sm.status =1 and u.id= usm.user_id and sm.id=usm.sos_matched_id and usm.availability=1 and usm.status=1;")
	List<File_rtgs__> get_sos__for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Select("               select distinct rna.id, rna.value_date_type, rna.sender, rna.receiver, u.firstname,u.lastname,rm.match_date,\r\n"
			+ "				                 rna.additional_information, rna.amount, rna.dr_cr, rna.upload_date, rna.match_status, rna.status, \r\n"
			+ "						         rna.availability, rna.reference,rna.file_id, rm.match_id from users u,user_rgts_matched urm, rtgs__ats rna, file_account fa, rtgs_matched rm where rna.status = 1 and rna.availability = 1 and rna.match_status=1 \r\n"
			+ "			 	                 and rna.file_id = fa.file_id and fa.account_id =#{account_id} and  rm.match_date = #{date} and rm.rtgs__ats_id = rna.id\r\n"
			+ "				 	            and rm.availability=1 and rm.status=1 and u.id=urm.user_id and urm.rtgs_matched_partial_id=rm.id and urm.availability=1 and urm.status=1\r\n"
			+ "					                                     union  				 \r\n"
			+ "				select distinct sna.id, sna.value_date_type, sna.sender, sna.receiver,  u.firstname,u.lastname,sm.match_date,\r\n"
			+ "			                   sna.additional_information, sna.amount, sna.dr_cr, sna.upload_date, sna.match_status, sna.status, \r\n"
			+ "					             sna.availability, sna.reference,sna.file_id, sm.match_id  from  users u,user_sos_matched usm, sos__ats sna, sos_matched sm, file_account fa where sna.status = 1 and sna.availability = 1 and sna.match_status = 1\r\n"
			+ "						 		and sna.file_id = fa.file_id and fa.account_id = #{account_id} and sm.match_date =#{date} and sm.sos__ats_id = sna.id \r\n"
			+ "								and sm.availability=1 and sm.status =1 and u.id=usm.user_id and usm.sos_matched_id=sm.id and usm.availability=1 and usm.status=1\r\n"
			+ "								                         union\r\n"
			+ "				select  distinct bna.id, bna.value_date_type, bna.sender, bna.receiver,  u.firstname,u.lastname,bm.match_date,\r\n"
			+ "			                 bna.additional_information, bna.amount, bna.dr_cr, bna.upload_date, bna.match_status, bna.status, \r\n"
			+ "						        bna.availability, bna.reference,bna.file_id, bm.match_id  from users u,user_b2b_matched ubm, b2b__ats bna, b2b_matched bm, file_account fa where bna.status = 1 and bna.availability = 1 and bna.match_status = 1 \r\n"
			+ "						 		and bna.file_id = fa.file_id and fa.account_id= #{account_id} and bm.match_date= #{date} and bm.b2b__ats_id = bna.id and bm.status=1 and \r\n"
			+ "				 		      bm.availability=1 and u.id=ubm.user_id and ubm.b2b_matched_partial_id=bm.id and ubm.availability=1 and ubm.status=1\r\n"
			+ "						                                 union\r\n"
			+ "				select  distinct ena.id, ena.value_date_type, ena.sender, ena.receiver, u.firstname,u.lastname,em.match_date,\r\n"
			+ "					                 ena.additional_information, ena.amount, ena.dr_cr, ena.upload_date, ena.match_status, ena.status, \r\n"
			+ "				               ena.availability, ena.reference, ena.file_id, em.match_id  from  users u,user_erca_matched uem, erca__ats ena, file_account fa, erca_matched em where ena.status = 1 and ena.availability = 1 and ena.match_status = 1\r\n"
			+ "					          and ena.file_id = fa.file_id and fa.account_id = #{account_id} and em.match_date= #{date} and em.erca__ats_id=ena.id and em.status=1\r\n"
			+ "					        and em.availability =1  and u.id=uem.user_id and uem.erca_matched_id=em.id and uem.availability=1 and uem.status=1")
	List<File_rtgs__ats> get_all_ats_for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Select("select distinct eac.id, eac.posting_date, eac.transaction_reference, eac.branch_code,\r\n"
			+ "		 		eac.amount, eac.dr_cr, eac.upload_date, eac.match_status, eac.status, eac.availability,u.firstname,u.lastname,em.match_date,\r\n"
			+ "			 			eac.value_date, eac.additional_information, eac.file_id, eac.match_id from erca__ eac, file_account fa,users u,user_erca_matched uem, erca_matched em where eac.status = 1 and eac.availability = 1 and eac.match_status = 1\r\n"
			+ "				  and eac.file_id = fa.file_id and fa.account_id = #{account_id} and em.match_id = eac.match_id and em.status =1 and\r\n"
			+ "					em.availability=1 and em.match_date= #{date} and u.id= uem.user_id and uem.erca_matched_id=em.id and uem.availability=1 and uem.status=1 union\r\n"
			+ "		 select distinct bac.id, bac.posting_date, bac.transaction_reference, bac.branch_code,\r\n"
			+ "					bac.amount, bac.dr_cr, bac.upload_date, bac.match_status, bac.status, bac.availability ,u.firstname,u.lastname,bm.match_date,\r\n"
			+ "					bac.value_date, bac.additional_information, bac.file_id, bac.match_id from b2b__ bac, b2b_matched bm, file_account fa,\r\n"
			+ "					users u, user_b2b_matched ubm where bac.status = 1 and bac.availability = 1 and bac.match_status = 1\r\n"
			+ "						 		and bac.file_id = fa.file_id and fa.account_id = #{account_id} and bm.match_date = #{date}   and bm.match_id=bac.match_id and\r\n"
			+ "						 	bm.availability =1 and bm.status =1 and  u.id=ubm.user_id and ubm.b2b_matched_partial_id=bm.id and ubm.availability=1 and ubm.status=1 union\r\n"
			+ "			select distinct  sac.id, sac.posting_date, sac.transaction_reference, sac.branch_code,\r\n"
			+ "					sac.amount, sac.dr_cr, sac.upload_date, sac.match_status, sac.status, sac.availability,u.firstname,u.lastname,sm.match_date,\r\n"
			+ "					sac.value_date, sac.additional_information, sac.file_id, sac.match_id from sos__ sac, sos_matched sm, file_account fa,\r\n"
			+ "					users u,user_sos_matched usm  where sac.status = 1 and sac.availability = 1 and sac.match_status = 1\r\n"
			+ "					 			and sac.file_id = fa.file_id and fa.account_id =  #{account_id} and sm.match_date =  #{date}   and sm.match_id = sac.match_id \r\n"
			+ "					 			and sm.availability =1 and sm.status =1 and usm.user_id=u.id and usm.sos_matched_id=sm.id and usm.availability=1 and usm.status=1			union\r\n"
			+ "			select  distinct rac.id, rac.posting_date, rac.transaction_reference, rac.branch_code,\r\n"
			+ "				rac.amount, rac.dr_cr, rac.upload_date, rac.match_status, rac.status, rac.availability,u.firstname,u.lastname,rm.match_date,\r\n"
			+ "					rac.value_date, rac.additional_information, rac.file_id, rac.match_id from rtgs__ rac, file_account fa, \r\n"
			+ "					users u,user_rgts_matched urm,rtgs_matched rm where rac.status = 1 and rac.availability = 1 and rac.match_status = 1\r\n"
			+ "					 and rac.file_id = fa.file_id and fa.account_id = #{account_id} and rm.match_date = #{date}  and rm.match_id=rac.match_id\r\n"
			+ "					 and urm.user_id=u.id and urm.rtgs_matched_partial_id=rm.id and urm.availability=1 and urm.status=1")
	List<File_rtgs__> get_all__for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Select("select * from data__ats rna, file_account fa where rna.status = 1 and rna.availability = 1 and rna.match_status = 0 "
			+ "and rna.file_id = fa.file_id and fa.account_id = #{account_id} and rna.upload_date = #{date};")
	List<File_rtgs__ats> get_all_ats_unmatched_for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Select("select * from data__ dac, file_account fa where dac.status = 1 and dac.availability = 1 and dac.match_status = 0 "
			+ "and dac.file_id = fa.file_id and fa.account_id = #{account_id} and dac.upload_date = #{date};")
	List<File_rtgs__> get_all__unmatched_for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);
	//============================================== start get unmatch payable transaction ========================================
	@Select("select * from data_payable dpc where dpc.status = 1 and dpc.availability = 1  "
			+ "and dpc.dr_cr='CR';")
	List<File_rtgs__> get_payable_credit_unmatched_for_view();
	@Select("select * from data_payable dpd  where dpd.status = 1 and dpd.availability = 1"
			+ "and dpd.dr_cr='DR';")
	List<File_rtgs__> get_payable_debit_unmatched_for_view();
	//=============================================== ending get payable unmatch transaction ======================================

	@Select("select * from rtgs__ats rna, file_account fa where rna.status = 1 and rna.availability = 1 and rna.match_status = 0 "
			+ "and rna.file_id = fa.file_id and fa.account_id = #{account_id} and rna.upload_date = #{date};")
	List<File_erca_> get_all_ats_partial_for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Select("select * from rtgs__ats rna, file_account fa where rna.status = 1 and rna.availability = 1 and rna.match_status = 0 "
			+ "and rna.file_id = fa.file_id and fa.account_id = #{account_id} and rna.upload_date = #{date};")
	List<File_erca_> get_all__partial_for_view(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Update("UPDATE rtgs__ats SET availability = '0' WHERE id=#{id}")
	public void deleteReconItems(Long i);

////	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


	@Insert("insert into erca__ (posting_date, transaction_reference, branch_code,amount, "
			+ "dr_cr, upload_date, match_status, status, availability) values (#{posting_date}, #{transaction_reference}, #{branch_code},"
			+ " #{amount}, #{dr_cr}, #{upload_date}, #{match_status}, #{status}, #{availability})")
	public void uploadData_erca_(File_erca_ erca_);

	@Select("select value_date_type, sum(amount) as amount from erca__ats\r\n"
			+ " where additional_information like'%\r\n" + ".\r\n"
			+ "value of erca%' group by value_date_type having count(id)>0")
	public void totalAmmountOfTheSameValueDateTaypeWithValueOfErca();

	@Update("update erca__ats set match_status='1' where value_date_type=#{value_date_type}\r\n"
			+ "and additional_information like'%\r\n" + ".\r\n" + "value of erca%'")
	public void matchErca(String value_date_type);

	@Update("update erca__ set match_status='1' where amount=#{amount}")
	public void matchErca(float amount);

	@Select("select * from erca__ats where status = 1 and availability = 1 and match_status = 0 and value_date_type=#{value_date_type} and \r\n "
			+ "and additional_information like'%\r\n" + ".\r\n" + "value of erca%'")
	List<File_erca_> get_erca__for_recon(String value_date_type);

	@Select("select * from erca__ where status = 1 and availability = 1 and match_status = 0 and amount=#{amount} ;")
	List<File_erca_> get_erca__for_recon(float amount);

	@Select("INSERT INTO data__(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id ) OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id "
			+ "FROM erca__ where id = #{id}; update erca__ set availability = 0 where id = #{id};")
	Long moveErcaMatched(@Param("id") Long long1);

	@Select("INSERT INTO data__ats(value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, REFERENCE, file_id) OUTPUT Inserted.id SELECT value_date_type, sender, receiver, "
			+ "additional_information, amount, dr_cr, upload_date, '0', status, availability, REFERENCE, file_id FROM erca__ats "
			+ "where id = #{id}; update erca__ats set availability= 0 where id = #{id};")
	Long moveErcaAtsMatchedToData(@Param("id") Long long1);

	@Update("update erca_matched set availability=0 where erca__ats_id=#{id}")
	public void deleteErcaMatched(@Param("id") Long long1);

	@Select("INSERT INTO data__(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id ) OUTPUT Inserted.id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id "
			+ "FROM b2b__ where id = #{id}; update b2b__ set availability = 0 where id = #{id};")
	Long moveBtbMatched(@Param("id") Long long1);

	@Select("INSERT INTO data__ats(value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, REFERENCE, file_id)OUTPUT Inserted.id SELECT value_date_type, sender, receiver, "
			+ "additional_information, amount, dr_cr, upload_date, '0', status, availability, REFERENCE, file_id FROM b2b__ats "
			+ "where id = #{id}; update b2b__ats set availability= 0 where id = #{id};")
	Long moveBtbAtsMatchedToData(@Param("id") Long long1);

	@Update("update b2b_matched set availability = 0 where b2b__ats_id=#{id}")
	public void deleteBtbMatched(@Param("id") Long long1);

	@Select("INSERT INTO data__(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id ) OUTPUT Inserted.Id "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id "
			+ "FROM sos__ where id = #{id}; update sos__ set availability = 0 where id = #{id};")
	Long moveSosMatched(@Param("id") Long long1);

	@Select(" select distinct * from ats_partially_matched apm, partially_matched pm ,comment c,partial_comment pc, file_account fa ,users u,user_parial_match upm where pm.ats_id= apm.id and fa.account_id=#{account_id} \r\n"
			+ "	 and apm.file_id=fa.file_id  and pm.availability=1 and pm.status =1 and apm.availability =1 and apm.status =1 \r\n"
			+ "	 and u.id=upm.user_id and upm.partial_matched_id=pm.id and upm.availability=1 and upm.status=1 and c.id=pc.comment_id and \r\n"
			+ "	 pm.match_id=pc.match_id and c.availability=1 and c.status=1 and pc.availability=1 and pc.status=1")

	List<File_rtgs__ats> get_ats_for_view_partial(@Param("account_id") Long account_id);

	@Select("select distinct * from _partially_matched cpm, partially_matched pm ,comment c,partial_comment pc , file_account fa  ,users u,user_parial_match upm where pm.ats_id= cpm.id and fa.account_id=#{account_id} "
			+ " and cpm.file_id=fa.file_id  and pm.availability=1 and pm.status =1 and cpm.availability =1 and cpm.status =1 "
			+ "and u.id=upm.user_id and upm.partial_matched_id=pm.id and upm.availability=1 and upm.status=1"
			+ " and c.id=pc.comment_id and \r\n"
			+ "	pm.match_id=pc.match_id and c.availability=1 and c.status=1 and pc.availability=1 and pc.status=1")
	List<File_rtgs__> get__for_view_partial(@Param("account_id") Long account_id);

	@Select("INSERT INTO data__ats(value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, REFERENCE, file_id) OUTPUT Inserted.Id SELECT value_date_type, sender, receiver, "
			+ "additional_information, amount, dr_cr, upload_date, '0', status, availability, REFERENCE, file_id FROM sos__ats "
			+ "where id = #{id}; update sos__ats set availability= 0 where id = #{id};")
	Long moveSosAtsMatchedToData(@Param("id") Long long1);

	@Update("update sos_matched set availability = 0 where sos__ats_id=#{id}")
	public void deleteSosMatched(@Param("id") Long long1);

	@Select("INSERT INTO data__(posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, value_date, additional_information, file_id ) "
			+ "SELECT posting_date, transaction_reference, branch_code, amount, dr_cr, upload_date, '0', status, "
			+ "availability, value_date, additional_information, file_id "
			+ "FROM _partially_matched where id = #{id}; update _partially_matched set availability = 0 where id = #{id};")
	void moveAllPartialMatched(@Param("id") Long long1);

	@Select("INSERT INTO data__ats(value_date_type, sender, receiver, additional_information, amount, dr_cr, upload_date, "
			+ "match_status, status, availability, REFERENCE, file_id) SELECT value_date_type, sender, receiver, "
			+ "additional_information, amount, dr_cr, upload_date, '0', status, availability, REFERENCE, file_id FROM ats_partially_matched "
			+ "where id = #{id}; update ats_partially_matched set availability= 0 where id = #{id};")
	public void moveAllPartialAtsMatchedToData(@Param("id") Long long1);

	@Update("update partially_matched set availability = 0 where ats_id=#{id}")
	public void deleteAllPartialMatched(@Param("id") Long long1);

}
