package com.example.demo.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.abebayehu.entity.Fixed_core_report;
import com.example.demo.abebayehu.entity.Fixed_mms_report;
//import com.example.demo.abebayehu.entity.Raw_fixed_core;
//import com.example.demo.abebayehu.entity.Raw_fixed_mms;
@Mapper
public interface MapperFixedReport {
	
	//Furniture
	@Select(" select distinct dic.id, dic.transaction_date, dic.account_description, dic.naration, dic.account_name, dic.debit as amount from data_fixed_core dic where dic.transaction_date <=#{date} and dic.credit=0 and dic.status = 1 and dic.availability = 1 and dic.account_number like '01195000054%' union \r\n"
			+ "	select distinct ic.id, ic.transaction_date, ic.account_description, ic.naration, ic.account_name, ic.debit as amount from fixed_core ic, fixed_matched im, fixed_mms iq \r\n"
			+ "	 where ic.credit=0 and ic.transaction_date <= #{date}  and (ic.match_id = im.match_id and iq.id = im.fixed_mms_id and cast(iq.created_date as date) > #{date}) and ic.status = 1 and ic.availability = 1  \r\n"
			+ "	and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1  and ic.account_number like '01195000054%'")
	List<Fixed_core_report> reportCoreDebitFurn(String date);
	
	@Select(" select distinct dic.id, dic.transaction_date, dic.account_description, dic.naration, dic.account_name, dic.credit as amount from data_fixed_core dic where dic.transaction_date <= #{date} and dic.debit=0 and dic.status = 1 and dic.availability = 1 and dic.account_number like '01195000054%' union \r\n"
			+ "	 select distinct ic.id, ic.transaction_date, ic.account_description, ic.naration, ic.account_name, ic.debit as amount from fixed_core ic, fixed_matched im, fixed_mms iq \r\n"
			+ "	 where ic.debit=0 and ic.transaction_date <= #{date} and (ic.match_id = im.match_id and iq.id = im.fixed_mms_id and cast(iq.created_date as date) > #{date}) and ic.status = 1 and ic.availability = 1 \r\n"
			+ "	  and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1 and ic.account_number like '01195000054%'")
	List<Fixed_core_report> reportCoreCreditFurn(String date);
	
    @Select(" select distinct dic.id, cast(dic.created_date as date), dic.asset_description, dic.tag_number,dic.branch_name, dic.original_cost as amount from data_fixed_mms dic where cast(dic.created_date as date) <= #{date}  and status = 1 and availability = 1 and dic.main_pg='BF' union \r\n"
    		+ "	  select distinct iq.id, cast(iq.created_date as date), iq.asset_description, iq.tag_number,iq.branch_name, iq.original_cost as amount from fixed_mms iq, fixed_matched im, fixed_core ic\r\n"
    		+ "	  where iq.main_pg='BF' and cast(iq.created_date as date) <= #{date} and ic.match_id = im.match_id and im.fixed_mms_id = iq.id and ic.transaction_date > #{date} \r\n"
    		+ "	 and iq.status = 1 and iq.availability = 1 and im.status = 1 and im.availability = 1  and ic.status = 1 and ic.availability = 1 ")
	List<Fixed_mms_report> reportMMSFurn(String date);
	

//	@Select("Select distinct dic.id, dic.transaction_date, dic.category_description, dic.reference,dic.additional_information, dic.debit as amount from data_fixed_mms dic where dic.transaction_date <= #{date} and dic.credit = 0  and status = 1 and availability = 1 and dic.main_pg='BF' union \r\n"
//			+ "  select distinct rna.id, rna.transaction_date, rna.category_description, rna.reference,rna.additional_information, rna.debit as amount from fixed_mms rna, fixed_matched rm, fixed_core rac \r\n"
//			+ "	 where rna.credit = 0 and rna.main_pg='BF' and rna.transaction_date <= #{date} and rac.match_id = rm.match_id and rm.fixed_mms_id = rna.id and rac.transaction_date > #{date}\r\n"
//			+ "	 and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1")
//	List<Fixed_mms_report> reportMMSDebitFurn(String date);

	
	@Select(" select ((select ISNULL(sum(dic.debit + dic.credit), 0) from data_fixed_core dic where transaction_date <= #{date} and dic.account_number like '01195000054%' and dic.status = 1 and dic.availability = 1 )\r\n"
			+ "	   + (\r\n"
			+ "	  select ISNULL(sum(ic.credit + ic.debit ), 0) from fixed_core ic, fixed_matched im, fixed_mms iq\r\n"
			+ "	 where ic.transaction_date <= #{date} and (ic.match_id = im.match_id and iq.id = im.fixed_mms_id and cast(iq.created_date as date) > #{date}) and ic.status = 1 and ic.availability = 1  \r\n"
			+ "	 and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1 and ic.account_number like '01195000054%'))")
	Double getCoreTotalFurn(String date);
	
	
	@Select("select ((select ISNULL(sum(dna.original_cost), 0) from data_fixed_mms dna where cast(dna.created_date as date) <= #{date} and dna.main_pg = 'BF' and status = 1 and availability = 1)\r\n"
			+ "	+ (select ISNULL(sum(rna.original_cost), 0) from fixed_mms rna, fixed_matched rm, fixed_core rac \r\n"
			+ "	where rna.main_pg = 'BF' and cast(rna.created_date as date) <= #{date} and rac.match_id = rm.match_id and rm.fixed_mms_id = rna.id and rac.transaction_date >#{date} \r\n"
			+ "	and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1))")
	Double getMMSTotalFurn(String date);
	
	//computer
	@Select(" select distinct dic.id, dic.transaction_date, dic.account_description, dic.naration, dic.account_name, dic.debit as amount from data_fixed_core dic where dic.transaction_date <=#{date} and dic.credit=0 and dic.status = 1 and dic.availability = 1 and dic.account_number like '01197000058%' union \r\n"
			+ "	select distinct ic.id, ic.transaction_date, ic.account_description, ic.naration, ic.account_name, ic.debit as amount from fixed_core ic, fixed_matched im, fixed_mms iq \r\n"
			+ "	 where ic.credit=0 and ic.transaction_date <= #{date}  and (ic.match_id = im.match_id and iq.id = im.fixed_mms_id and cast(iq.created_date as date) > #{date}) and ic.status = 1 and ic.availability = 1  \r\n"
			+ "	and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1  and ic.account_number like '01197000058%'")
	List<Fixed_core_report> reportCoreDebitComp(String date);

	@Select(" select distinct dic.id, dic.transaction_date, dic.account_description, dic.naration, dic.account_name, dic.credit as amount from data_fixed_core dic where dic.transaction_date <= #{date} and dic.debit=0 and dic.status = 1 and dic.availability = 1 and dic.account_number like '01197000058%' union \r\n"
			+ "	 select distinct ic.id, ic.transaction_date, ic.account_description, ic.naration, ic.account_name, ic.debit as amount from fixed_core ic, fixed_matched im, fixed_mms iq \r\n"
			+ "	 where ic.debit=0 and ic.transaction_date <= #{date} and (ic.match_id = im.match_id and iq.id = im.fixed_mms_id and cast(iq.created_date as date) > #{date}) and ic.status = 1 and ic.availability = 1 \r\n"
			+ "	  and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1 and ic.account_number like '01197000058%'")
	List<Fixed_core_report> reportCoreCreditComp(String date);
	
    @Select(" select distinct dic.id, cast(dic.created_date as date), dic.asset_description, dic.tag_number,dic.branch_name, dic.original_cost as amount from data_fixed_mms dic where cast(dic.created_date as date) <= #{date} and status = 1 and availability = 1 and dic.main_pg='AB' union \r\n"
    		+ "	  select iq.id, cast(iq.created_date as date), iq.asset_description, iq.tag_number,iq.branch_name, iq.original_cost as amount from fixed_mms iq, fixed_matched im, fixed_core ic\r\n"
    		+ "	  where iq.main_pg='AB' and cast(iq.created_date as date) <= #{date} and ic.match_id = im.match_id and im.fixed_mms_id = iq.id and ic.transaction_date > #{date} \r\n"
    		+ "	 and iq.status = 1 and iq.availability = 1 and im.status = 1 and im.availability = 1  and ic.status = 1 and ic.availability = 1 ")
	List<Fixed_mms_report> reportMMSComp(String date);

//	@Select("	 Select distinct dic.id, dic.transaction_date, dic.category_description, dic.reference,dic.additional_information, dic.debit as amount from data_fixed_mms dic where dic.transaction_date <= #{date} and dic.credit = 0  and status = 1 and availability = 1 and dic.main_pg='AB' union \r\n"
//			+ "  select distinct rna.id, rna.transaction_date, rna.category_description, rna.reference,rna.additional_information, rna.debit as amount from fixed_mms rna, fixed_matched rm, fixed_core rac \r\n"
//			+ "	 where rna.credit = 0 and rna.main_pg='AB' and rna.transaction_date <= #{date} and rac.match_id = rm.match_id and rm.fixed_mms_id = rna.id and rac.transaction_date > #{date}\r\n"
//			+ "	 and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1")
//	List<Fixed_mms_report> reportMMSDebitComp(String date);
   
	@Select(" select ((select ISNULL(sum(dic.debit + dic.credit), 0) from data_fixed_core dic where transaction_date <= #{date} and dic.account_number like '01197000058%' and dic.status = 1 and dic.availability = 1 )\r\n"
			+ "	   + (\r\n"
			+ "	  select ISNULL(sum(ic.credit + ic.debit ), 0) from fixed_core ic, fixed_matched im, fixed_mms iq\r\n"
			+ "	 where ic.transaction_date <= #{date} and (ic.match_id = im.match_id and iq.id = im.fixed_mms_id and cast(iq.created_date as date) > #{date}) and ic.status = 1 and ic.availability = 1  \r\n"
			+ "	 and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1 and account_number like '01197000058%'))")
	Double getCoreTotalComp(String date);
     
	@Select("select ((select ISNULL(sum(dna.original_cost), 0) from data_fixed_mms dna where cast(dna.created_date as date) <= #{date} and dna.main_pg = 'AB' and status = 1 and availability = 1)\r\n"
			+ "	+ (select ISNULL(sum(rna.original_cost), 0) from fixed_mms rna, fixed_matched rm, fixed_core rac \r\n"
			+ "	where rna.main_pg = 'AB' and cast(rna.created_date as date) <= #{date} and rac.match_id = rm.match_id and rm.fixed_mms_id = rna.id and rac.transaction_date >#{date} \r\n"
			+ "	and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1))")
	Double getMMSTotalComp(String date);
	
	
	//Motor Vehicle
	@Select(" select distinct dic.id, dic.transaction_date, dic.account_description, dic.naration, dic.account_name, dic.debit as amount from data_fixed_core dic where dic.transaction_date <=#{date} and dic.credit=0 and dic.status = 1 and dic.availability = 1 and dic.account_number like '01198000059%' union \r\n"
			+ "	select distinct ic.id, ic.transaction_date, ic.account_description, ic.naration, ic.account_name, ic.debit as amount from fixed_core ic, fixed_matched im, fixed_mms iq \r\n"
			+ "	 where ic.credit=0 and ic.transaction_date <= #{date}  and (ic.match_id = im.match_id and iq.id = im.fixed_mms_id and cast(iq.created_date as date) > #{date}) and ic.status = 1 and ic.availability = 1  \r\n"
			+ "	and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1  and ic.account_number like '01198000059%'")
	List<Fixed_core_report> reportCoreDebitMotor(String date);

	@Select(" select distinct dic.id, dic.transaction_date, dic.account_description, dic.naration, dic.account_name, dic.credit as amount from data_fixed_core dic where dic.transaction_date <= #{date} and dic.debit=0 and dic.status = 1 and dic.availability = 1 and dic.account_number like '01198000059%' union \r\n"
			+ "	 select distinct ic.id, ic.transaction_date, ic.account_description, ic.naration, ic.account_name, ic.debit as amount from fixed_core ic, fixed_matched im, fixed_mms iq \r\n"
			+ "	 where ic.debit=0 and ic.transaction_date <= #{date} and (ic.match_id = im.match_id and iq.id = im.fixed_mms_id and cast(iq.created_date as date) > #{date}) and ic.status = 1 and ic.availability = 1 \r\n"
			+ "	  and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1 and ic.account_number like '01198000059%'")
	List<Fixed_core_report> reportCoreCreditMotor(String date);
	
    @Select(" select distinct dic.id, cast(dic.created_date as date), dic.asset_description, dic.tag_number,dic.branch_name, dic.original_cost as amount from data_fixed_mms dic where cast(dic.created_date as date) <= #{date} and status = 1 and availability = 1 and dic.main_pg='CP' union \r\n"
    		+ "	  select distinct iq.id, cast(iq.created_date as date), iq.asset_description, iq.tag_number,iq.branch_name, iq.original_cost as amount from fixed_mms iq, fixed_matched im, fixed_core ic\r\n"
    		+ "	  where iq.main_pg='CP' and cast(iq.created_date as date) <= #{date} and ic.match_id = im.match_id and im.fixed_mms_id = iq.id and ic.transaction_date > #{date} \r\n"
    		+ "	 and iq.status = 1 and iq.availability = 1 and im.status = 1 and im.availability = 1  and ic.status = 1 and ic.availability = 1 ")
	List<Fixed_mms_report> reportMMSMotor(String date);

//	@Select("	 Select distinct dic.id, dic.transaction_date, dic.category_description, dic.reference,dic.additional_information, dic.debit as amount from data_fixed_mms dic where dic.transaction_date <= #{date} and dic.credit = 0  and status = 1 and availability = 1 and dic.main_pg='GX' union \r\n"
//			+ "  select distinct rna.id, rna.transaction_date, rna.category_description, rna.reference,rna.additional_information, rna.debit as amount from fixed_mms rna, fixed_matched rm, fixed_core rac \r\n"
//			+ "	 where rna.credit = 0 and rna.main_pg='GX' and rna.transaction_date <= #{date} and rac.match_id = rm.match_id and rm.fixed_mms_id = rna.id and rac.transaction_date > #{date}\r\n"
//			+ "	 and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1")
//	List<Fixed_mms_report> reportMMSDebitMotor(String date);

	@Select(" select ((select ISNULL(sum(dic.debit + dic.credit), 0) from data_fixed_core dic where transaction_date <= #{date} and dic.account_number like '01198000059%' and dic.status = 1 and dic.availability = 1 )\r\n"
			+ "	   + (\r\n"
			+ "	  select ISNULL(sum(ic.credit + ic.debit ), 0) from fixed_core ic, fixed_matched im, fixed_mms iq\r\n"
			+ "	 where ic.transaction_date <= #{date} and (ic.match_id = im.match_id and iq.id = im.fixed_mms_id and cast(iq.created_date as date) > #{date}) and ic.status = 1 and ic.availability = 1  \r\n"
			+ "	 and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1 and account_number like '01198000059%'))")
	Double getCoreTotalMotor(String date);

	@Select("select ((select ISNULL(sum(dna.original_cost), 0) from data_fixed_mms dna where cast(dna.created_date as date) <= #{date} and dna.main_pg = 'CP' and status = 1 and availability = 1)\r\n"
			+ "	+ (select ISNULL(sum(rna.original_cost), 0) from fixed_mms rna, fixed_matched rm, fixed_core rac \r\n"
			+ "	where rna.main_pg = 'CP' and cast(rna.created_date as date) <= #{date} and rac.match_id = rm.match_id and rm.fixed_mms_id = rna.id and rac.transaction_date >#{date} \r\n"
			+ "	and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1))")
	Double getMMSTotalMotor(String date);
	
	//Office equipment
	@Select(" select distinct dic.id, dic.transaction_date, dic.account_description, dic.naration, dic.account_name, dic.debit as amount from data_fixed_core dic where dic.transaction_date <=#{date} and dic.credit=0 and dic.status = 1 and dic.availability = 1 and dic.account_number like '01196000056%' union \r\n"
			+ "	select distinct ic.id, ic.transaction_date, ic.account_description, ic.naration, ic.account_name, ic.debit as amount from fixed_core ic, fixed_matched im, fixed_mms iq \r\n"
			+ "	 where ic.credit=0 and ic.transaction_date <= #{date}  and (ic.match_id = im.match_id and iq.id = im.fixed_mms_id and cast(iq.created_date as date) > #{date}) and ic.status = 1 and ic.availability = 1  \r\n"
			+ "	and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1  and ic.account_number like '01196000056%'")
	List<Fixed_core_report> reportCoreDebitOfficeEqipment(String date);
    
	@Select(" select distinct dic.id, dic.transaction_date, dic.account_description, dic.naration, dic.account_name, dic.credit as amount from data_fixed_core dic where dic.transaction_date <= #{date} and dic.debit=0 and dic.status = 1 and dic.availability = 1 and dic.account_number like '01196000056%' union \r\n"
			+ "	 select distinct ic.id, ic.transaction_date, ic.account_description, ic.naration, ic.account_name, ic.debit as amount from fixed_core ic, fixed_matched im, fixed_mms iq \r\n"
			+ "	 where ic.debit=0 and ic.transaction_date <= #{date} and (ic.match_id = im.match_id and iq.id = im.fixed_mms_id and cast(iq.created_date as date) > #{date}) and ic.status = 1 and ic.availability = 1 \r\n"
			+ "	  and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1 and ic.account_number like '01196000056%'")
	List<Fixed_core_report> reportCoreCreditOfficeEqipment(String date);
	
    @Select(" select distinct dic.id, cast(dic.created_date as date), dic.asset_description, dic.tag_number,dic.branch_name, dic.original_cost as amount from data_fixed_mms dic where cast(dic.created_date as date) <= #{date}  and status = 1 and availability = 1 and dic.main_pg='GX' union \r\n"
    		+ "	  select distinct iq.id, cast(iq.created_date as date), iq.asset_description, iq.tag_number,iq.branch_name, iq.original_cost as amount from fixed_mms iq, fixed_matched im, fixed_core ic\r\n"
    		+ "	  where iq.main_pg='GX' and cast(iq.created_date as date) <= #{date} and ic.match_id = im.match_id and im.fixed_mms_id = iq.id and ic.transaction_date > #{date} \r\n"
    		+ "	 and iq.status = 1 and iq.availability = 1 and im.status = 1 and im.availability = 1  and ic.status = 1 and ic.availability = 1 ")
	List<Fixed_mms_report> reportMMSCreditOfficeEqipment(String date);

//	@Select("	 Select distinct dic.id, dic.transaction_date, dic.category_description, dic.reference,dic.additional_information, dic.debit as amount from data_fixed_mms dic where dic.transaction_date <= #{date} and dic.credit = 0  and status = 1 and availability = 1 and dic.main_pg='CP' union \r\n"
//			+ "  select distinct rna.id, rna.transaction_date, rna.category_description, rna.reference,rna.additional_information, rna.debit as amount from fixed_mms rna, fixed_matched rm, fixed_core rac \r\n"
//			+ "	 where rna.credit = 0 and rna.main_pg='CP' and rna.transaction_date <= #{date} and rac.match_id = rm.match_id and rm.fixed_mms_id = rna.id and rac.transaction_date > #{date}\r\n"
//			+ "	 and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1")
//	List<Fixed_mms_report> reportMMSDebitOfficeEqSipment(String date);

	@Select(" select ((select ISNULL(sum(dic.debit + dic.credit), 0) from data_fixed_core dic where transaction_date <= #{date} and dic.account_number like '01196000056%' and dic.status = 1 and dic.availability = 1 )\r\n"
			+ "	   + (\r\n"
			+ "	  select ISNULL(sum(ic.credit + ic.debit ), 0) from fixed_core ic, fixed_matched im, fixed_mms iq\r\n"
			+ "	 where ic.transaction_date <= #{date} and (ic.match_id = im.match_id and iq.id = im.fixed_mms_id and cast(iq.created_date as date) > #{date}) and ic.status = 1 and ic.availability = 1  \r\n"
			+ "	 and im.status = 1 and im.availability = 1  and iq.status = 1 and iq.availability = 1 and account_number like '01196000056%'))")
	Double getCoreTotalOfficeEqipment(String date);

	@Select("select ((select ISNULL(sum(dna.original_cost), 0) from data_fixed_mms dna where cast(dna.created_date as date) <= #{date} and dna.main_pg = 'GX' and status = 1 and availability = 1)\r\n"
			+ "	+ (select ISNULL(sum(rna.original_cost), 0) from fixed_mms rna, fixed_matched rm, fixed_core rac \r\n"
			+ "	where rna.main_pg = 'GX' and cast(rna.created_date as date) <= #{date} and rac.match_id = rm.match_id and rm.fixed_mms_id = rna.id and rac.transaction_date >#{date} \r\n"
			+ "	and rna.status = 1 and rna.availability = 1 and rm.status = 1 and rm.availability = 1  and rac.status = 1 and rac.availability = 1))")
	Double getMMSTotalOfficeEqipment(String date);
	
	@Select("select ISNULL(sum(conv_fur), 0) from data_trial_balance where date=#{date}")
	Double getConvFurniture(String date);
	
	@Select("select ISNULL(sum(ifb_fur), 0) from data_trial_balance where date=#{date}")
	Double getIfbFurniture(String date);
	
	@Select("select ISNULL(sum(conv_comp), 0) from data_trial_balance where date=#{date}")
	Double getConvComputer(String date);
	
	@Select("select ISNULL(sum(ifb_comp), 0) from data_trial_balance where date=#{date}")
	Double getIfbComputer(String date);
	
	@Select("select ISNULL(sum(conv_oe), 0) from data_trial_balance where date=#{date}")
	Double getConvEquipment(String date);
	
	@Select("select ISNULL(sum(ifb_oe), 0) from data_trial_balance where date=#{date}")
	Double getIfbEquipment(String date);
	
	@Select("select ISNULL(sum(conv_mv), 0) from data_trial_balance where date=#{date}")
	Double getConvVehicle(String date);
	
	@Select("select ISNULL(sum(ifb_mv), 0) from data_trial_balance where date=#{date}")
	Double getIfbVehicle(String date);
	
	@Select("select ISNULL(sum(mms_fur), 0) from data_trial_balance where date=#{date}")
	Double getMmsFurniture(String date);
	
	@Select("select ISNULL(sum(mms_comp), 0) from data_trial_balance where date=#{date}")
	Double getMmsComputer(String date);
	
	@Select("select ISNULL(sum(mms_oe), 0) from data_trial_balance where date=#{date}")
	Double getMmsEquipment(String date);
	
	@Select("select ISNULL(sum(mms_mv), 0) from data_trial_balance where date=#{date}")
	Double getMmsVehicle(String date);
	
	@Select("select ISNULL(sum(original_cost), 0) from data_fixed_mms_waiting where main_pg='BF' and cast(created_date as date)>cast(#{date} as date) ")
	Double getWaitingFurniture(String date);
	
	@Select("select ISNULL(sum(original_cost), 0) from data_fixed_mms_waiting where main_pg='AB' and cast(created_date as date)>cast(#{date} as date) ")
	Double getWaitingComputer(String date);
	
	@Select("select ISNULL(sum(original_cost), 0) from data_fixed_mms_waiting where main_pg='GX' and cast(created_date as date)>cast(#{date} as date) ")
	Double getWaitingEquipment(String date);
	
	@Select("select ISNULL(sum(original_cost), 0) from data_fixed_mms_waiting where main_pg='CP' and cast(created_date as date)>cast(#{date} as date) ")
	Double getWaitingVehicle(String date);
	
	@Select("select ISNULL(sum(book_value), 0) from data_fixed_mms_disposed where main_pg='BF' and cast(disposed_date as date)>cast(#{date} as date) ")
	Double getDisposedFurniture(String date);
	
	@Select("select ISNULL(sum(book_value), 0) from data_fixed_mms_disposed where main_pg='AB' and cast(disposed_date as date)>cast(#{date} as date) ")
	Double getDisposedComputer(String date);
	
	@Select("select ISNULL(sum(book_value), 0) from data_fixed_mms_disposed where main_pg='GX' and cast(disposed_date as date)>cast(#{date} as date) ")
	Double getDisposedEquipment(String date);
	
	@Select("select ISNULL(sum(book_value), 0) from data_fixed_mms_disposed where main_pg='CP' and cast(disposed_date as date)>cast(#{date} as date) ")
	Double getDisposedVehicle(String date);
	
	@Select("select ISNULL(sum(book_value), 0) from data_fixed_mms_removed where main_pg='BF' and cast(removed_date as date)>cast(#{date} as date) ")
	Double getRemovedFurniture(String date);
	
	@Select("select ISNULL(sum(book_value), 0) from data_fixed_mms_removed where main_pg='AB' and cast(removed_date as date)>cast(#{date} as date) ")
	Double getRemovedComputer(String date);
	
	@Select("select ISNULL(sum(book_value), 0) from data_fixed_mms_removed where main_pg='GX' and cast(removed_date as date)>cast(#{date} as date) ")
	Double getRemovedEquipment(String date);
	
	@Select("select ISNULL(sum(book_value), 0) from data_fixed_mms_removed where main_pg='CP' and cast(removed_date as date)>cast(#{date} as date) ")
	Double getRemovedVehicle(String date);
	
	
	
}
