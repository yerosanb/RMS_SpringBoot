package com.example.demo.garama.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.abebayehu.entity.Raw_fixed_mms;
import com.example.demo.garama.models.FixedMmsDisposed;
import com.example.demo.garama.models.FixedMmsRemoved;
import com.example.demo.garama.models.FixedMmsWaiting;
import com.example.demo.garama.models.RawFixedModel;
import com.example.demo.garama.models.RawFixedMMSModel;
import com.example.demo.garama.models.UnMatchFixed;
import com.example.demo.garama.models.UnMatchFixedModel;
import com.example.demo.garama.models.fixedReversal;
import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.Transactionhistory;

@Mapper
public interface FixedAutReconMapper {
	@Select("select DISTINCT fmms.*, fc.id as _id  from data_fixed_mms fmms, data_fixed_ fc where\r\n"
			+ "	       Patindex ('%'+fmms.giv_number+'%', fc.naration) != 0 and (ROUND(fmms.original_cost,2) = fc.credit or ROUND(fmms.original_cost, 2)=fc.debit)\r\n"
			+ "		   and fmms.status = 1 and fmms.availability = 1 and fc.status=1 and fc.availability=1 and  fmms.main_pg='AB' and cast(fmms.created_date as date)<= cast(#{date} as date)\r\n"
			+ "		   and fc.transaction_date <= #{date}")
	List<RawFixedMMSModel> get_fixed_mms_for_recon_auto_computer(@Param("date") String recon_date);
	
	@Select("select DISTINCT fmms.*, fc.id as _id  from data_fixed_mms fmms, data_fixed_ fc where"
			+ " Patindex('%'+fmms.giv_number+'%', fc.naration) !=0 and (ROUND(fmms.original_cost,2) = fc.credit or ROUND(fmms.original_cost, 2)=fc.debit) and fmms.status = 1 and fmms.availability = 1 and fc.status=1 and fc.availability=1 and  fmms.main_pg='BF' and fmms.created_date<=#{date} and fc.transaction_date <= #{date}")
	List<RawFixedMMSModel> get_fixed_mms_for_recon_auto_furniture(@Param("date") String recon_date,
			@Param("account_id") Long account_id);
	
	@Select("select DISTINCT fmms.*, fc.id as _id  from data_fixed_mms fmms, data_fixed_ fc where"
			+ " Patindex('%'+fmms.giv_number+'%', fc.naration) !=0 and (ROUND(fmms.original_cost,2) = fc.credit or ROUND(fmms.original_cost, 2)=fc.debit)and fmms.status = 1 and fmms.availability = 1 and fc.status=1 and fc.availability=1 and  fmms.main_pg='GX' and fmms.created_date<=#{date} and fc.transaction_date <= #{date}")
	List<RawFixedMMSModel> get_fixed_mms_for_recon_auto_equipment(@Param("date") String recon_date,
			@Param("account_id") Long account_id);
	
	@Select("select DISTINCT fmms.*, fc.id as _id  from data_fixed_mms fmms, data_fixed_ fc where"
			+ " Patindex('%'+fmms.giv_number+'%', fc.naration) !=0 and (ROUND(fmms.original_cost,2) = fc.credit or ROUND(fmms.original_cost, 2)=fc.debit)and fmms.status = 1 and fmms.availability = 1 and fc.status=1 and fc.availability=1 and  fmms.main_pg='CP' and fmms.created_date<=#{date} and fc.transaction_date <= #{date}")
	List<RawFixedMMSModel> get_fixed_mms_for_recon_auto_vehicle(@Param("date") String recon_date,
			@Param("account_id") Long account_id);

	@Select("select DISTINCT fc.*  from data_fixed_mms fmms, data_fixed_ fc where"
			+ " Patindex('%'+fmms.giv_number+'%', fc.naration) !=0 and (ROUND(fmms.original_cost, 2) = fc.credit or ROUND(fmms.original_cost, 2)=fc.debit)and fc.status = 1 and fc.availability = 1 and (fc.account_number like '01197%' or fc.account_number like '01A97%') and fmms.status = 1 and fmms.availability = 1 and fc.transaction_date<=#{date} and fmms.created_date<=#{date}")
	List<RawFixedModel> get_fixed__for_recon_auto_computer(@Param("account_id") Long account_id,
			@Param("date") String recon_date);
	
	@Select("select  DISTINCT fc.*  from data_fixed_mms fmms, data_fixed_ fc where"
			+ " Patindex('%'+fmms.giv_number+'%', fc.naration) !=0 and (ROUND(fmms.original_cost, 2) = fc.credit or ROUND(fmms.original_cost, 2)=fc.debit)and fc.status = 1 and fc.availability = 1 and  (fc.account_number like '01196%' or fc.account_number like '01A96%') and fmms.status = 1 and fmms.availability = 1 and fc.transaction_date<=#{date} and fmms.created_date<=#{date}")
	List<RawFixedModel> get_fixed__for_recon_auto_equipment(@Param("account_id") Long account_id,
			@Param("date") String recon_date);
	
	@Select("select DISTINCT  fc.*  from data_fixed_mms fmms, data_fixed_ fc where"
			+ " Patindex('%'+fmms.giv_number+'%', fc.naration) !=0 and (ROUND(fmms.original_cost, 2) = fc.credit or ROUND(fmms.original_cost, 2)=fc.debit)and fc.status = 1 and fc.availability = 1 and (fc.account_number like '01198%' or fc.account_number like '01A98%') and fmms.status = 1 and fmms.availability = 1 and fc.transaction_date<=#{date} and fmms.created_date<=#{date}")
	List<RawFixedModel> get_fixed__for_recon_auto_vehicle(@Param("account_id") Long account_id,
			@Param("date") String recon_date);
	
	@Select("select DISTINCT fc.*  from data_fixed_mms fmms, data_fixed_ fc where"
			+ " Patindex('%'+fmms.giv_number+'%', fc.naration) !=0 and (ROUND(fmms.original_cost, 2) = fc.credit or ROUND(fmms.original_cost, 2)=fc.debit)and fc.status = 1 and fc.availability = 1 and (fc.account_number like '01195%' or fc.account_number like '01A95%') and fmms.status = 1 and fmms.availability = 1 and fc.transaction_date<=#{date} and fmms.created_date<=#{date}")
	List<RawFixedModel> get_fixed__for_recon_auto_furniture(@Param("account_id") Long account_id,
			@Param("date") String recon_date);

	//////////////////////////////////// insert into fixed matched
	//////////////////////////////////// ending/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Select("insert into fixed_matched (fixed_mms_id, match_id, match_date, reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
			+ "values(#{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many}, #{status}, #{availability})")
	Long addFixedMatched(@Param("id") Long id, @Param("match_id") String match_id, @Param("date") Long date_now,
			@Param("recon_type") String recon_type, @Param("many_to_many") String many_to_many,
			@Param("status") String status, @Param("availability") String availability);

	////////// move fixed mms data when data matched//

	@Select("INSERT INTO fixed_mms(created_date, asset_id, asset_description, tag_number, branch_name, quantity, grv_number, giv_number, original_cost, book_value, main_pg, old_main_pg, "
			+ "match_status, status, availability) OUTPUT Inserted.id SELECT created_date, asset_id, asset_description, tag_number, branch_name, quantity, grv_number, giv_number, original_cost, book_value, main_pg, old_main_pg, '1', status, availability FROM data_fixed_mms "
			+ "where id = #{id}; delete from data_fixed_mms where id = #{id};")
	Long moveMMSFixedData(@Param("id") Long long1);
	////// Move fixed  data when data matched////////////

	@Select("INSERT INTO fixed_(account_number, transaction_date, posting_date, value_date, credit, debit, "
			+ "branch_code, REFERENCE, naration, account_description, account_name, status, availability,match_id )OUTPUT Inserted.id "
			+ "SELECT account_number, transaction_date, posting_date, value_date, credit, debit,branch_code, REFERENCE,naration,account_description,"
			+ " account_name, status, availability,#{match_id}"
			+ " FROM data_fixed_ where id = #{id};delete from data_fixed_ where id = #{id};")
	Long moveFixedData(@Param("id") Long long1, @Param("match_id") String match_id);

	// ============================================= insert user fixed matched
	// ending =================
	
	@Insert("insert into user_fixed_matched (user_id, fixed_matched_id, date, status, availability, type) values "

			+ "(#{user_id}, #{fixed_matched_id}, #{date},  #{status}, #{availability}, #{type})")
	void addUserFixedMatched(@Param("user_id") Long user_id, @Param("fixed_matched_id") Long fixed_matched_id,
			@Param("date") Long date, @Param("status") String status, @Param("availability") String availability,
			@Param("type") String type);

	@Select("select fm.fixed_mms_id as id, fmms.REFERENCE as reference,fmms.store_code, fmms.stock_account_segment,fmms.transaction_date, fmms.additional_information, fmms.period, fmms.debit,fmms.credit,  fmms.bbf, fmms.tran_code, fmms.main_pg, fmms.store_name,  fmms.category_description, "
			+ "  fmms.status, fmms.availability, "
			+ "  fm.match_id, fm.match_date,u.firstname, u.middlename, u.lastname"
			+ " from fixed_mms fmms, users u, user_fixed_matched ufm, fixed_matched  fm where fmms.status = 1 and fmms.availability = 1 and fmms.status=1 and fm.match_date <= #{date} and fm.fixed_mms_id = fmms.id"
			+ " and fm.availability=1 and fm.status=1 and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1")
	List<UnMatchFixed> get_fixed_mms_for_view(String date);

	@Select("select fc.id, fc.account_number, fc.transaction_date, fc.posting_date, fc.value_date, fc.credit, fc.debit, fc.branch_code, fc.reference,"
			+ "fc.naration, fc.account_description, fc.account_name, fc.status, fc.availability, fc.match_id, fm.match_date, u.firstname, u.middlename,u.lastname"
			+ " from  fixed_ fc,users u,user_fixed_matched ufm, fixed_matched fm where fc.status = 1 and fc.availability = 1 and fc.status = 1"
			+ " and fm.match_id= fc.match_id and fm.match_date<=#{date} and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1")
//        @Select("select fc.id, fc.account_number, fc.transaction_date, fc.posting_date, fc.value_date, fc.credit, fc.debit, fc.branch_code, fc.reference,"
//        		+ "fc.naration, fc.account_description, fc.account_name, fc.status, fc.availability, fc.match_id, u.firstname, u.middlename,u.lastname"
//        		+ " from  fixed_ fc,users u,user_fixed_matched ufm, fixed_matched fm where fc.status = 1 and fc.availability = 1 and fc.status = 1"
//        		+ " and fm.match_id= fc.match_id and fm.match_date=#{date, jdbcType=INTEGER} and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1")
	List<UnMatchFixedModel> get_fixed__for_view(@Param("date") String date);

	@Select(" select distinct fm.fixed_mms_id as id, fmms.tag_number ,fmms.asset_description, fmms.asset_id,\r\n"
			+ "	fmms.created_date, fmms.branch_name, fmms.original_cost, fmms.book_value,fmms.quantity, \r\n"
			+ "	fmms.grv_number, fmms.giv_number, fmms.main_pg, fmms.old_main_pg,  fmms.match_status, fmms.status, fmms.availability, fm.match_id, fm.match_date,u.firstname, u.middlename,"
			+ " u.lastname , mr.reason from fixed_mms fmms ,  users u , user_fixed_matched ufm , fixed_matched  fm ,"
			+ " match_reason mr where fmms.status = 1 and fmms.availability = 1  and  fm.match_date = #{date}  and fm.fixed_mms_id = fmms.id and fm.match_id = mr.match_id and fm.availability=1 and fm.status=1 and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1")
	List<Raw_fixed_mms> get_fixed_mms_for_view_reason(int date);

	@Select("select fc.id, fc.account_number, fc.transaction_date, fc.posting_date, fc.value_date, fc.credit, fc.debit, fc.branch_code, fc.reference,"
			+ "fc.naration, fc.account_description, fc.account_name, fc.status, fc.availability, fc.match_id, fm.match_date, u.firstname, u.middlename,u.lastname , mr.reason"
			+ " from  fixed_ fc,users u,user_fixed_matched ufm, fixed_matched fm, match_reason mr where fc.status = 1 and fc.availability = 1 and fc.status = 1 and mr.match_id= fm.match_id"
			+ " and fm.match_id= fc.match_id and fm.match_date=#{date} and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1")
	List<Transactionhistory> get_fixed__for_view_reason(int date);

	@Select("INSERT INTO data_fixed_(account_number, transaction_date, posting_date, value_date, credit, debit, "
			+ "branch_code, REFERENCE, naration, account_description, account_name, status,availability,match_status ) OUTPUT Inserted.id "
			+ "SELECT account_number, transaction_date, posting_date, value_date, credit, debit, branch_code, REFERENCE,naration,account_description, "
			+ "account_name, status,availability , '0' "
			+ "FROM fixed_ where id = #{id}; update fixed_ set availability = 0 where id = #{id};")
	Long moveFixedMatched(Long id);

	@Update("update fixed_matched set availability=0 where fixed_mms_id=#{id}")
	public void deleteFixedMatched(Long id);

	@Select("INSERT INTO data_fixed_mms(created_date  ,asset_id ,asset_description ,tag_number,branch_name,quantity,grv_number,giv_number,original_cost,book_value,main_pg,old_main_pg,status ,availability ,match_status)OUTPUT Inserted.id SELECT created_date  ,asset_id ,asset_description ,tag_number,branch_name,quantity,grv_number,giv_number,original_cost,book_value,main_pg,old_main_pg,status ,availability , '0' FROM fixed_mms "
			+ "where id = #{id}; update fixed_mms set availability= 0 where id = #{id};")
	public Long moveFixedMMSMatchedToData(Long id);

	@Update("update user_fixed_matched set availability=0 where fixed_matched_id = #{id}")
	void deleteUserFixedMatched(@Param("id") Long id);

	@Select("select id from fixed_matched where fixed_mms_id = #{id}")
	Long getFixedMatchedId(@Param("id") long id);

	@Select("select * from data_fixed_mms where status = 1 and availability = 1 " + " and transaction_date <= #{date}")
	List<RawFixedMMSModel> get_unmatched_fixed_mms_for_view(String date);

	@Select("select * from data_fixed_ where status = 1 and availability = 1")
	List<RawFixedModel> get_unmatched_fixed__for_view();
	// =================================

	@Select("select * from data_fixed_mms dfm where dfm.status = 1 and dfm.availability = 1 "
			+ "and dfm.main_pg ='AB' and cast(dfm.created_date as date) <= #{date}")
	List<Raw_fixed_mms> get_computer_mms_unmatched_for_view(@Param("date") String recon_date);

	@Select("select * from data_fixed_ dfc where dfc.status = 1 and dfc.availability = 1 "
			+ "and  dfc.account_number like '01197%' and cast(dfc.transaction_date as date) <= #{date}")
	List<RawFixedModel> get_computer__unmatched_for_view(@Param("date") String recon_date);

	@Select("select * from data_fixed_mms dfm where dfm.status = 1 and dfm.availability = 1 "
			+ "and  dfm.main_pg ='BF' and cast(dfm.created_date as date) <= #{date}")
	List<Raw_fixed_mms> get_furniture_mms_unmatched_for_view(@Param("date") String recon_date);

	@Select("select * from data_fixed_ dfc where dfc.status = 1 and dfc.availability = 1 "
			+ "and cast(dfc.transaction_date as date) <= #{date} and (dfc.account_number like '01195%' or dfc.account_number like '01A95%')  ")
	List<RawFixedModel> get_furniture__unmatched_for_view(@Param("date") String recon_date);

	@Select("select * from data_fixed_mms dfm where dfm.status = 1 and dfm.availability = 1 "
			+ "and  dfm.main_pg ='GX' and cast(dfm.created_date as date) <= #{date}")
	List<Raw_fixed_mms> get_equipment_mms_unmatched_for_view(@Param("date") String recon_date);

	@Select("select * from data_fixed_ dfc where dfc.status = 1 and dfc.availability = 1 "
			+ "and cast(dfc.transaction_date as date) <= #{date} and (dfc.account_number like '01196%' or dfc.account_number like '01A96%') ")
	List<RawFixedModel> get_equipment__unmatched_for_view(@Param("date") String recon_date);

	@Select("select * from data_fixed_mms dfm where dfm.status = 1 and dfm.availability = 1 "
			+ "and  dfm.main_pg ='CP' and cast(dfm.created_date as date) <= #{date}")
	List<Raw_fixed_mms> get_vehicle_mms_unmatched_for_view(@Param("date") String recon_date);

	@Select("select * from data_fixed_ dfc where dfc.status = 1 and dfc.availability = 1 "
			+ "and cast(dfc.transaction_date as date) <= #{date} and (dfc.account_number like '01198%') ")
	List<RawFixedModel> get_vehicle__unmatched_for_view(@Param("date") String recon_date);

	// ======================== view matched fixed asset by catagoty
	@Select("select distinct fm.fixed_mms_id as id, fmms.tag_number ,fmms.asset_description, fmms.asset_id,\r\n"
			+ "fmms.created_date, fmms.branch_name, fmms.original_cost, fmms.book_value,fmms.quantity, \r\n"
			+ "fmms.grv_number, fmms.giv_number, fmms.main_pg, fmms.old_main_pg,  fmms.match_status, fmms.status, fmms.availability, fm.match_id, fm.match_date,u.firstname, u.middlename, u.lastname\r\n"
			+ " from fixed_mms fmms, users u, user_fixed_matched ufm, fixed_matched  fm \r\n"
			+ "where fmms.status = 1 and fmms.availability = 1 and fmms.status=1 and fm.match_date = #{date} and fm.fixed_mms_id = fmms.id and fm.availability=1 and fm.status=1 and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1 and fmms.main_pg='AB'")
	List<Raw_fixed_mms> get_computer_mms_matched_for_view(@Param("date") int recon_date);

	@Select("select  distinct fc.id, fc.account_number, fc.transaction_date, fc.posting_date, fc.value_date, fc.credit, fc.debit, fc.branch_code, fc.reference,\r\n"
			+ "  fc.naration, fc.account_description, fc.account_name, fc.status, fc.availability, fc.match_id, fm.match_date, u.firstname, u.middlename,u.lastname\r\n"
			+ "    from  fixed_ fc,users u,user_fixed_matched ufm, fixed_matched fm where fc.status = 1 and fc.availability = 1 and fc.status = 1\r\n"
			+ "     and fm.match_id= fc.match_id and fm.match_date = #{date} and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1 and ( fc.account_number like '01197%' or fc.account_number like '01A97%' )")
	List<UnMatchFixedModel> get_computer__matched_for_view(@Param("date") int recon_date);

	@Select("select distinct fm.fixed_mms_id as id, fmms.tag_number ,fmms.asset_description, fmms.asset_id,\r\n"
			+ "fmms.created_date, fmms.branch_name, fmms.original_cost, fmms.book_value,fmms.quantity, \r\n"
			+ "fmms.grv_number, fmms.giv_number, fmms.main_pg, fmms.old_main_pg,  fmms.match_status, fmms.status, fmms.availability, fm.match_id, fm.match_date,u.firstname, u.middlename, u.lastname\r\n"
			+ " from fixed_mms fmms, users u, user_fixed_matched ufm, fixed_matched  fm \r\n"
			+ "where fmms.status = 1 and fmms.availability = 1 and fmms.status=1 and fm.match_date = #{date} and fm.fixed_mms_id = fmms.id and fm.availability=1 and fm.status=1 and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1 and fmms.main_pg='BF'")
	List<Raw_fixed_mms> get_furniture_mms_matched_for_view(@Param("date") int recon_date);

	@Select("select distinct fc.id, fc.account_number, fc.transaction_date, fc.posting_date, fc.value_date, fc.credit, fc.debit, fc.branch_code, fc.reference,\r\n"
			+ "  fc.naration, fc.account_description, fc.account_name, fc.status, fc.availability, fc.match_id, fm.match_date, u.firstname, u.middlename,u.lastname\r\n"
			+ "    from  fixed_ fc,users u,user_fixed_matched ufm, fixed_matched fm where fc.status = 1 and fc.availability = 1 and fc.status = 1\r\n"
			+ "     and fm.match_id= fc.match_id and fm.match_date = #{date} and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1 and ( fc.account_number like '01195%' or fc.account_number like '01A95%' ) ")
	List<UnMatchFixedModel> get_furniture__matched_for_view(@Param("date") int recon_date);

	@Select("select distinct fm.fixed_mms_id as id, fmms.tag_number ,fmms.asset_description, fmms.asset_id,\r\n"
			+ "fmms.created_date, fmms.branch_name, fmms.original_cost, fmms.book_value,fmms.quantity, \r\n"
			+ "fmms.grv_number, fmms.giv_number, fmms.main_pg, fmms.old_main_pg,  fmms.match_status, fmms.status, fmms.availability, fm.match_id, fm.match_date,u.firstname, u.middlename, u.lastname\r\n"
			+ " from fixed_mms fmms, users u, user_fixed_matched ufm, fixed_matched  fm \r\n"
			+ "where fmms.status = 1 and fmms.availability = 1 and fmms.status=1 and fm.match_date = #{date} and fm.fixed_mms_id = fmms.id and fm.availability=1 and fm.status=1 and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1 and fmms.main_pg='GX'")
	List<Raw_fixed_mms> get_equipment_mms_matched_for_view(@Param("date") int recon_date);

	@Select("select distinct fc.id, fc.account_number, fc.transaction_date, fc.posting_date, fc.value_date, fc.credit, fc.debit, fc.branch_code, fc.reference,\r\n"
			+ "  fc.naration, fc.account_description, fc.account_name, fc.status, fc.availability, fc.match_id, fm.match_date, u.firstname, u.middlename,u.lastname\r\n"
			+ "    from  fixed_ fc,users u,user_fixed_matched ufm, fixed_matched fm where fc.status = 1 and fc.availability = 1 and fc.status = 1\r\n"
			+ "     and fm.match_id= fc.match_id and fm.match_date = #{date} and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1 and ( fc.account_number like '01196%' or fc.account_number like '01A96%' ) ")
	List<UnMatchFixedModel> get_equipment__matched_for_view(@Param("date") int recon_date);

	@Select("select distinct fm.fixed_mms_id as id, fmms.tag_number ,fmms.asset_description, fmms.asset_id,\r\n"
			+ "fmms.created_date, fmms.branch_name, fmms.original_cost, fmms.book_value,fmms.quantity, \r\n"
			+ "fmms.grv_number, fmms.giv_number, fmms.main_pg, fmms.old_main_pg,  fmms.match_status, fmms.status, fmms.availability, fm.match_id, fm.match_date,u.firstname, u.middlename, u.lastname\r\n"
			+ " from fixed_mms fmms, users u, user_fixed_matched ufm, fixed_matched  fm \r\n"
			+ "where fmms.status = 1 and fmms.availability = 1 and fmms.status=1 and fm.match_date = #{date} and fm.fixed_mms_id = fmms.id and fm.availability=1 and fm.status=1 and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1 and fmms.main_pg='CP'")
	List<Raw_fixed_mms> get_vehicle_mms_matched_for_view(@Param("date") int recon_date);

	@Select("select distinct fc.id, fc.account_number, fc.transaction_date, fc.posting_date, fc.value_date, fc.credit, fc.debit, fc.branch_code, fc.reference,\r\n"
			+ "  fc.naration, fc.account_description, fc.account_name, fc.status, fc.availability, fc.match_id, fm.match_date, u.firstname, u.middlename,u.lastname\r\n"
			+ "    from  fixed_ fc,users u,user_fixed_matched ufm, fixed_matched fm where fc.status = 1 and fc.availability = 1 and fc.status = 1\r\n"
			+ "     and fm.match_id= fc.match_id and fm.match_date = #{date} and u.id=ufm.user_id and ufm.fixed_matched_id=fm.id and ufm.availability=1 and ufm.status=1 and ( fc.account_number like '01198%')")
	List<UnMatchFixedModel> get_vehicle__matched_for_view(@Param("date") int recon_date);

	@Select("Select * from data_fixed_mms_waiting where cast(created_date as date) between #{minDate} and #{maxDate} and availability=1")
	List<FixedMmsWaiting> get_fixed_mms_waiting(@Param("minDate") String minDate,@Param("maxDate") String maxDate);
	@Select("Select * from data_fixed_mms_disposed where cast(disposed_date as date) between #{minDate} and #{maxDate} ")
	List<FixedMmsDisposed> get_fixed_mms_disposed(@Param("minDate") String minDate,@Param("maxDate") String maxDate);
	@Select("Select * from data_fixed_mms_removed where cast(removed_date as date) between #{minDate} and #{maxDate} ")
	List<FixedMmsRemoved> get_fixed_mms_removed(@Param("minDate") String minDate,@Param("maxDate") String maxDate);
	
	@Select("select id, account_number, transaction_date, posting_date, value_date, credit, debit, branch_code, reference, naration, account_description, account_name, match_id, match_date, firstname from reversal_fixed_  where  match_date=#{date} and  availability=1 and status=1;")
	List<fixedReversal> get_Reversal_for_view(@Param("date") String recon_date);
	
	@Select("INSERT INTO data_fixed_(account_number, transaction_date, posting_date, value_date, credit, debit, "
			+ "branch_code, REFERENCE, naration, account_description, account_name, status,availability,match_status ) OUTPUT Inserted.id "
			+ "SELECT account_number, transaction_date, posting_date, value_date, credit, debit, branch_code, REFERENCE,naration,account_description, "
			+ "account_name, '1', '1', '0' "
			+ "FROM reversal_fixed_ where id = #{id}; update reversal_fixed_ set availability = 0 where id = #{id};")
	Long moveReversalMatched(@Param("id") Long long1);
}
