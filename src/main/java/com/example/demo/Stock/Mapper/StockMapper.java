package com.example.demo.Stock.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.Stock.Model.RawStockCoreModel;
import com.example.demo.Stock.Model.RawStockMMSModel;
import com.example.demo.Stock.Model.StockCoreDeleted;
import com.example.demo.Stock.Model.stockCoreDetail;
import com.example.demo.abebayehu.entity.Raw_fixed_mms;
import com.example.demo.abebayehu.entity.core_detail_deleted;
import com.example.demo.abebayehu.entity.mms_detail_deleted;
import com.example.demo.abebayehu.entity.view_fixed_core_deleted;
import com.example.demo.abebayehu.entity.view_fixed_mms_deleted;
import com.example.demo.garama.models.UnMatchFixedCoreModel;

@Mapper
public interface StockMapper {
	@Select("IF EXISTS (SELECT distinct * FROM data_stock_core\r\n" + "	WHERE id=#{id}) \r\n"
			+ "	SELECT 'True' ELSE  SELECT 'False'")
	boolean existsTransaction(@Param("id") Long id);

	@Select("select DISTINCT fmms.* from data_stock_mms fmms, data_stock_core fc where\r\n"
			+ "	 Patindex ('%'+fmms.reference+'%', fc.description) != 0 and ((ROUND(fmms.amount,2) = fc.amount) or (ROUND(fc.amount, 2)=fmms.amount)) and fmms.dr_cr=fc.dr_cr \r\n"
			+ "	and fmms.status = 1 and fmms.availability = 1 and fc.status=1 and fc.availability=1 and  fmms.account_segment like '%'+#{type}+ '%' and   fc.account like '%'+#{type}+ '%' and cast(fmms.date as date)<= cast(#{date} as date)\r\n"
			+ "	 and cast(fc.value_date as date) <= #{date}  union \r\n"
			+ "	SELECT DISTINCT fmms.* FROM data_stock_mms fmms inner JOIN data_stock_core fc ON ((ROUND(fmms.amount, 2) = fc.amount) OR (ROUND(fc.amount, 2) = fmms.amount)) and fmms.account_segment like '%'+#{type}+ '%' and   fc.account like '%'+#{type}+ '%'\r\n"
			+ "	WHERE fmms.reference like'STOV%' and fc.description like'STIV%' and fmms.dr_cr=fc.dr_cr AND PATINDEX('%'+fmms.account_segment + '%', fc.account) != 0 AND fmms.status = 1 AND fmms.availability = 1 AND fc.status = 1 AND fc.availability = 1 AND CAST(fmms.date AS DATE) <= cast(#{date} as date) AND CAST(fc.value_date AS DATE) <= cast(#{date} as date)")
	List<RawStockMMSModel> get_stock_mms_for_recon_auto(@Param("date") String recon_date, @Param("type") String type);

	@Select(" select DISTINCT fc.*  from data_stock_mms fmms, data_stock_core fc where\r\n"
			+ "	 Patindex('%'+fmms.reference+'%', fc.description) !=0 and ((ROUND(fmms.amount, 2) = fc.amount) or(ROUND(fc.amount, 2) = fmms.amount)) and fmms.dr_cr=fc.dr_cr and fc.status = 1 and fc.availability = 1 and (fc.account like '%'+#{account}+ '%' and fmms.account_segment like '%'+#{account}+ '%') and cast(fmms.date as date)<= cast(#{date} as date)\r\n"
			+ "	and cast(fc.value_date as date) <= #{date} and fmms.status = 1 union\r\n"
			+ "	select DISTINCT fc.*  from data_stock_mms fmms, data_stock_core fc where\r\n"
			+ "	fmms.reference like'STOV%' and fc.description like'STIV%'  and fmms.dr_cr=fc.dr_cr and ((ROUND(fmms.amount, 2) = fc.amount) or(ROUND(fc.amount, 2) = fmms.amount)) and cast(fmms.date as date)<= cast(#{date} as date)\r\n"
			+ "	and cast(fc.value_date as date) <= cast(#{date} as date) and fc.status = 1 and fc.availability = 1 and fmms.status = 1 and (fc.account like '%'+#{account}+ '%' and fmms.account_segment like '%'+#{account}+ '%')")
	List<RawStockCoreModel> get_stock_core_for_recon_auto(@Param("account") String account,
			@Param("date") String recon_date);

	@Select("INSERT INTO stock_mms(store_code  ,reference ,account_segment ,date ,description ,period ,amount,dr_cr,bbf ,transaction_code ,main_pg ,store_name,category_description,status,availability,match_status) OUTPUT Inserted.id SELECT store_code  ,reference ,account_segment ,date ,description ,period ,amount,dr_cr,bbf ,transaction_code ,main_pg ,store_name,category_description,status,availability,'1' FROM data_stock_mms "
			+ "where id = #{id}; delete from data_stock_mms where id = #{id};")
	Long moveMMSStockData(@Param("id") Long long1);

	@Select("insert into stock_matched (stock_mms_id, match_id, match_date, reconciliation_type, many_to_many, status, availability) OUTPUT Inserted.Id "
			+ "values(#{id}, #{match_id}, #{date}, #{recon_type}, #{many_to_many}, #{status}, #{availability})")
	Long addStockMatched(@Param("id") Long id, @Param("match_id") String match_id, @Param("date") Long date_now,
			@Param("recon_type") String recon_type, @Param("many_to_many") String many_to_many,
			@Param("status") String status, @Param("availability") String availability);

	@Insert("insert into user_stock_matched (user_id, stock_matched_id, date, status, availability, type) values "
			+ "(#{user_id}, #{stock_matched_id}, #{date},  #{status}, #{availability}, #{type})")
	void addUserStockMatched(@Param("user_id") Long user_id, @Param("stock_matched_id") Long stock_matched_id,
			@Param("date") Long date, @Param("status") String status, @Param("availability") String availability,
			@Param("type") String type);

	@Select("INSERT INTO stock_core(posting_date,value_date,account,description,transaction_reference,source_branch,branch_name,amount,dr_cr,balance,match_status,status,availability,match_id)OUTPUT Inserted.id "
			+ "SELECT posting_date,value_date,account,description,transaction_reference,source_branch,branch_name,amount,dr_cr,balance,'1',status,availability,#{match_id}"
			+ " FROM data_stock_core where id = #{id};delete from data_stock_core where id = #{id};")
	Long moveCoreStockData(@Param("id") Long long1, @Param("match_id") String match_id);

	@Select("select distinct fc.*, fm.match_date, u.firstname, u.middlename as lastname\r\n"
			+ "    from  stock_core fc,users u,user_stock_matched ufm, stock_matched fm where fc.status = 1 and fc.availability = 1 and fc.status = 1\r\n"
			+ "     and fm.match_id= fc.match_id and fm.match_date = #{date} and u.id=ufm.user_id and ufm.stock_matched_id=fm.id and ufm.availability=1 and ufm.status=1 and ( fc.account like '%'+ #{account}+'%')")
	List<RawStockCoreModel> get_stock_core_matched_for_view(@Param("date") int recon_date,
			@Param("account") String account);

	@Select("select distinct fc.*,mr.reason, fm.match_date, u.firstname, u.middlename as lastname\r\n"
			+ "    from  stock_core fc,users u,user_stock_matched ufm, stock_matched fm,match_reason mr where fc.status = 1 and fc.availability = 1 and fc.status = 1\r\n"
			+ "     and fm.match_id= fc.match_id and fm.match_date = #{date} and u.id=ufm.user_id and ufm.stock_matched_id=fm.id and ufm.availability=1 and ufm.status=1 and ( fc.account like '%'+ #{account}+'%') and fm.match_id=mr.match_id")
	List<RawStockCoreModel> get_stock_core_matched_with_reason(@Param("date") int recon_date,
			@Param("account") String account);

	@Select("select distinct fc.*,mr.reason, fm.match_date, u.firstname, u.middlename as lastname\r\n"
			+ "    from  stock_core fc,users u,user_stock_matched ufm, stock_matched fm,match_reason mr where fc.status = 1 and fc.availability = 1 and fc.status = 1\r\n"
			+ "     and fm.match_id= fc.match_id and fm.match_date = #{date} and u.id=ufm.user_id and ufm.stock_matched_id=fm.id and ufm.availability=1 and ufm.status=1  and fm.match_id=mr.match_id")
	List<RawStockCoreModel> get_stock_core_matched_with_reasonAll(@Param("date") int recon_date);

	@Select("select * from reversal_stock_core where match_date = #{date} and availability=1 and status=1 ")
	List<RawStockCoreModel> get_stock_reversal(@Param("date") int recon_date);

	@Select("select distinct fmms.id,fmms.store_code,fmms.reference,fmms.account_segment,fmms.date,fmms.description,fmms.period,fmms.amount,fmms.dr_cr,fmms.bbf,fmms.transaction_code,fmms.main_pg,fmms.store_name,fmms.category_description, fm.match_id, fm.match_date,u.firstname, u.middlename as lastname\r\n"
			+ " from stock_mms fmms, users u, user_stock_matched ufm, stock_matched  fm \r\n"
			+ "where fmms.status = 1 and fmms.availability = 1 and fmms.status=1 and fm.match_date = #{date} and fm.stock_mms_id = fmms.id and fm.availability=1 and fm.status=1 and u.id=ufm.user_id and ufm.stock_matched_id=fm.id and ufm.availability=1 and ufm.status=1 and fmms.account_segment like '%'+ #{account} +'%'")
	List<RawStockMMSModel> get_stock_mms_matched_for_view(@Param("date") int recon_date,
			@Param("account") String account);

	@Select("select distinct fmms.id,mr.reason,fmms.store_code,fmms.reference,fmms.account_segment,fmms.date,fmms.description,fmms.period,fmms.amount,fmms.dr_cr,fmms.bbf,fmms.transaction_code,fmms.main_pg,fmms.store_name,fmms.category_description, fm.match_id, fm.match_date,u.firstname, u.middlename as lastname\r\n"
			+ " from stock_mms fmms, users u, user_stock_matched ufm, stock_matched  fm,match_reason mr \r\n"
			+ "where fmms.status = 1 and fmms.availability = 1 and fmms.status=1 and fm.match_date = #{date} and fm.stock_mms_id = fmms.id and fm.availability=1 and fm.status=1 and u.id=ufm.user_id and ufm.stock_matched_id=fm.id and ufm.availability=1 and ufm.status=1 and fmms.account_segment like '%'+ #{account} +'%' and fm.match_id=mr.match_id")
	List<RawStockMMSModel> get_stock_mms_matched_with_reason(@Param("date") int recon_date,
			@Param("account") String account);

	@Select("select distinct fmms.id,mr.reason,fmms.store_code,fmms.reference,fmms.account_segment,fmms.date,fmms.description,fmms.period,fmms.amount,fmms.dr_cr,fmms.bbf,fmms.transaction_code,fmms.main_pg,fmms.store_name,fmms.category_description, fm.match_id, fm.match_date,u.firstname, u.middlename as lastname\r\n"
			+ " from stock_mms fmms, users u, user_stock_matched ufm, stock_matched  fm,match_reason mr \r\n"
			+ "where fmms.status = 1 and fmms.availability = 1 and fmms.status=1 and fm.match_date = #{date} and fm.stock_mms_id = fmms.id and fm.availability=1 and fm.status=1 and u.id=ufm.user_id and ufm.stock_matched_id=fm.id and ufm.availability=1 and ufm.status=1  and fm.match_id=mr.match_id")
	List<RawStockMMSModel> get_stock_mms_matched_with_reasonAll(@Param("date") int recon_date);

	@Select("select * from data_stock_core where status = 1 and availability = 1 \r\n"
			+ "   and cast(value_date as date) <= #{date} and (account like '%'+ #{account}+'%')")
	List<RawStockCoreModel> get_stock_core_unmatched_for_view(@Param("date") String recon_date,
			@Param("account") String account);

	@Select("select  * from data_stock_mms \r\n"
			+ "where status = 1 and availability = 1 and cast(date as date) < = #{date}   and account_segment like '%'+ #{account} +'%'")
	List<RawStockMMSModel> get_stock_mms_unmatched_for_view(@Param("date") String recon_date,
			@Param("account") String account);

	@Select("select * from data_stock_core where status = 1 and availability = 1 \r\n"
			+ "   and cast(value_date as date) <= #{date} ")
	List<RawStockCoreModel> get_stock_core_unmatched_for_viewAll(@Param("date") String recon_date);

	@Select("select  * from data_stock_mms \r\n"
			+ "where status = 1 and availability = 1 and cast(date as date) < = #{date} ")
	List<RawStockMMSModel> get_stock_mms_unmatched_for_viewAll(@Param("date") String string);

//	added by Demeke
	@Select(" select DISTINCT fc.* from data_stock_core fc where\r\n"
			+ "	fc.status = 1 and fc.availability = 1 and (fc.account like '%'+#{account}+ '%')\r\n"
			+ "	and cast(fc.value_date as date) <= #{date}")
	List<RawStockCoreModel> get_stock_core_for_recon_manual(@Param("account") String account,
			@Param("date") String recon_date);

	@Select(" select DISTINCT fc.* from data_stock_core fc where\r\n"
			+ "	fc.status = 1 and fc.availability = 1 and cast(fc.value_date as date) <= #{date}")
	List<RawStockCoreModel> get_all_stock_core_for_recon_manual(@Param("date") String recon_date);

	@Select("select DISTINCT fmms.* from data_stock_mms fmms where\r\n"
			+ "fmms.status = 1 and fmms.availability = 1 and fmms.account_segment like '%'+#{type}+ '%' and  cast(fmms.date as date)<= cast(#{date} as date)\r\n")
	List<RawStockMMSModel> get_stock_mms_for_recon_manual(@Param("date") String recon_date, @Param("type") String type);

	@Select("select DISTINCT fmms.* from data_stock_mms fmms where\r\n"
			+ "fmms.status = 1 and fmms.availability = 1 and  cast(fmms.date as date)<= cast(#{date} as date)\r\n")
	List<RawStockMMSModel> get_all_stock_mms_for_recon_manual(@Param("date") String recon_date);

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

	@Select("INSERT INTO stock_mms_edit_history(store_code ,reference ,account_segment ,date ,description ,period ,amount ,dr_cr, bbf, "
			+ "transaction_code ,main_pg ,store_name, category_description, match_status, status, availability, edit_reason_id, new_old) OUTPUT Inserted.id SELECT store_code, "
			+ "reference ,account_segment ,date ,description ,period ,amount,dr_cr,bbf ,transaction_code ,main_pg ,store_name,category_description, match_status, status, availability,#{reason_id}, 'deleted' FROM data_stock_mms where id = #{id};")
	Long moveDeletedMmsData(@Param("id") Long id, @Param("reason_id") Long reason_id);

	@Update("update data_stock_mms set availability = '0' where id = #{id}")
	void deleteTransaction(@Param("id") Long id);

	@Select("INSERT INTO stock_core_edit_history(posting_date, value_date, account, description, transaction_reference, source_branch, branch_name, amount, dr_cr, balance, match_status, status, availability,"
			+ "edit_reason_id, new_old)OUTPUT Inserted.id SELECT posting_date, value_date, account, description, transaction_reference, source_branch, branch_name, amount, dr_cr, balance, match_status, status,"
			+ "availability, #{reason_id}, 'deleted' FROM data_stock_core where id = #{id};")
	Long moveDeletedStockCoreData(@Param("id") Long id, @Param("reason_id") Long reason_id);

	@Update("update data_stock_core set availability = '0' where id = #{id}")
	void deleteFixedCoreTransaction(@Param("id") Long id);

	@Select(" select  CONCAT(firstname, ' ', middlename) as fullname from users where id=#{id} ")
	String fullName(Long id);

	@Select("INSERT INTO reversal_stock_core(posting_date, value_date, account, description, transaction_reference, source_branch, branch_name, amount, dr_cr, balance, status, availability,"
			+ "match_id, match_date, firstname)OUTPUT Inserted.id SELECT posting_date, value_date, account, description, transaction_reference, source_branch, branch_name, amount, dr_cr, balance, status,"
			+ "availability, #{match_id}, #{match_date}, #{match_by} FROM data_stock_core where id = #{id}; delete from data_stock_core where id = #{id};")
	Long moveStockCoreReversalData(@Param("id") Long long1, @Param("match_id") String match_id,
			@Param("match_date") Long match_date, @Param("match_by") String match_by);
//	added By demeke

	@Select("select distinct fc.*, fm.match_date, u.firstname, u.middlename as lastname\r\n"
			+ "    from  stock_core fc,users u,user_stock_matched ufm, stock_matched fm where fc.status = 1 and fc.availability = 1 and fc.status = 1\r\n"
			+ "     and fm.match_id= fc.match_id and fm.match_date = #{date} and u.id=ufm.user_id and ufm.stock_matched_id=fm.id and ufm.availability=1 and ufm.status=1")
	List<RawStockCoreModel> get_stock_core_matched_for_viewAll(@Param("date") int recon_date);

	@Select("select distinct fmms.id,fmms.store_code,fmms.reference,fmms.account_segment,fmms.date,fmms.description,fmms.period,fmms.amount,fmms.dr_cr,fmms.bbf,fmms.transaction_code,fmms.main_pg,fmms.store_name,fmms.category_description, fm.match_id, fm.match_date,u.firstname, u.middlename as lastname\r\n"
			+ " from stock_mms fmms, users u, user_stock_matched ufm, stock_matched  fm \r\n"
			+ "where fmms.status = 1 and fmms.availability = 1 and fmms.status=1 and fm.match_date = #{date} and fm.stock_mms_id = fmms.id and fm.availability=1 and fm.status=1 and u.id=ufm.user_id and ufm.stock_matched_id=fm.id and ufm.availability=1 and ufm.status=1 ")
	List<RawStockMMSModel> get_stock_mms_matched_for_viewAll(@Param("date") String string);

	@Select("INSERT INTO data_stock_core(posting_date,value_date,account,description,transaction_reference,source_branch,branch_name,amount,dr_cr,balance,match_status,status,availability ) OUTPUT Inserted.id "
			+ "SELECT posting_date,value_date,account,description,transaction_reference,source_branch,branch_name,amount,dr_cr,balance,'0',status,availability "
			+ "FROM stock_core where id = #{id}; update stock_core set availability = 0 where id = #{id};")
	Long moveStockCoreMatched(Long id);

	@Select("INSERT INTO data_stock_core(posting_date,value_date,account,description,transaction_reference,source_branch,branch_name,amount,dr_cr,balance,match_status,status,availability ) OUTPUT Inserted.id "
			+ "SELECT posting_date,value_date,account,description,transaction_reference,source_branch,branch_name,amount,dr_cr,balance,'0',status,availability "
			+ "FROM reversal_stock_core where id = #{id}; update reversal_stock_core set availability = 0 where id = #{id};")
	Long moveStockCoreMatchedReversal(Long id);

	@Update("update stock_matched set availability=0 where stock_mms_id=#{id}")
	public void deleteStockMatched(Long id);

	@Select("INSERT INTO data_stock_mms(store_code  ,reference ,account_segment ,date ,description ,period ,amount,dr_cr,bbf ,transaction_code ,main_pg ,store_name,category_description,status,availability,match_status)OUTPUT Inserted.id SELECT store_code  ,reference ,account_segment ,date ,description ,period ,amount,dr_cr,bbf ,transaction_code ,main_pg ,store_name,category_description,status,availability , '0' FROM stock_mms "
			+ "where id = #{id}; update stock_mms set availability= 0 where id = #{id};")
	public Long moveStockMMSMatchedToData(Long id);

	@Update("update user_stock_matched set availability=0 where stock_matched_id = #{id}")
	void deleteUserStockMatched(@Param("id") Long id);

	@Select("select id from stock_matched where stock_mms_id = #{id}")
	Long getStockMatchedId(@Param("id") long id);

	@Select("select distinct dna.id, dna.transaction_reference, dna.posting_date, dna.value_date,  dna.amount,  dna.source_branch, dna.branch_name, dna.description, dna.dr_cr,  dna.account, er.edit_delete from data_stock_core dna, edit_reason er where dna.id=er.current_id and er.type='data_stock_core'\r\n"
			+ " union select distinct rna.id, rna.transaction_reference, rna.posting_date, rna.value_date,  rna.amount,  rna.source_branch, rna.branch_name, rna.description, rna.dr_cr,  rna.account, er.edit_delete from stock_core rna, edit_reason er where rna.id=er.current_id and er.type='stock_core' and er.edit_delete='1' ")
	List<StockCoreDeleted> get_edited_stock_core();

	@Select("select distinct dna.id, dna.new_old,  dna.transaction_reference, dna.posting_date, dna.value_date,  dna.amount,  dna.source_branch, dna.branch_name, dna.description, dna.dr_cr,  dna.account, er.reason, u.firstname, u.lastname, er.DATE, er.edit_delete , dna.edit_reason_id from stock_core_edit_history dna, edit_reason er, users u where er.current_id=#{id} and er.id=dna.edit_reason_id and u.id=er.user_id")
	List<stockCoreDetail> get_edited_detail_stock_core(Long id);

	@Select("select distinct dna.id, dna.reference, dna.account_segment, dna.amount, dna.main_pg, dna.dr_cr, dna.date,  dna.store_name, dna.category_description, er.edit_delete from data_stock_mms dna, edit_reason er where dna.id=er.current_id and er.type='data_mms_stock'\r\n"
			+ "union select distinct rna.id, rna.reference, rna.account_segment, rna.amount, rna.main_pg, rna.dr_cr, rna.date,  rna.store_name, rna.category_description, er.edit_delete from stock_mms rna, edit_reason er where rna.id=er.current_id and er.type='stock_mms' and er.edit_delete='1' ")
	List<RawStockMMSModel> get_deleted_stock_mms();

	@Select("select distinct dna.id, dna.new_old,  dna.reference, dna.account_segment, dna.amount, dna.main_pg, dna.dr_cr, dna.date,  dna.store_name, dna.category_description,  er.reason, u.firstname, u.lastname, er.DATE, er.edit_delete , dna.edit_reason_id from stock_mms_edit_history dna, edit_reason er, users u where er.current_id=#{id} and er.id=dna.edit_reason_id and u.id=er.user_id")
	List<RawStockMMSModel> get_edited_detail_stock_mms(Long id);

}
