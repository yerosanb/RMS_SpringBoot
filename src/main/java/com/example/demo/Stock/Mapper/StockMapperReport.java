package com.example.demo.Stock.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.Stock.Model.StockReport;
import com.example.demo.abebayehu.entity.Fixed_core_report;
import com.example.demo.garama.models.RawFixedCoreModel;
import com.example.demo.garama.models.RawFixedMMSModel;
@Mapper
public interface StockMapperReport {
	@Select("select DISTINCT fmms.*, fc.id as core_id  from data_fixed_mms fmms, data_fixed_core fc where "
			+ "	       Patindex ('%'+fmms.giv_number+'%', fc.naration) != 0 and (ROUND(fmms.original_cost,2) = fc.credit or ROUND(fmms.original_cost, 2)=fc.debit) "
			+ "		   and fmms.status = 1 and fmms.availability = 1 and fc.status=1 and fc.availability=1 and  fmms.main_pg='AB' and cast(fmms.created_date as date)<= cast(#{date} as date) "
			+ "		   and fc.transaction_date <= #{date}")
	List<RawFixedMMSModel> get_stock_mms_for_recon_auto_Accesssory(@Param("date") String recon_date);
	
	@Select("select DISTINCT fc.*  from data_fixed_mms fmms, data_fixed_core fc where"
			+ " Patindex('%'+fmms.giv_number+'%', fc.naration) !=0 and (ROUND(fmms.original_cost, 2) = fc.credit or ROUND(fmms.original_cost, 2)=fc.debit)and fc.status = 1 and fc.availability = 1 and (fc.account_number like '01197%' or fc.account_number like '01A97%') and fmms.status = 1 and fmms.availability = 1 and fc.transaction_date<=#{date} and fmms.created_date<=#{date}")
	List<RawFixedCoreModel> get_stock_core_for_recon_auto_accessory(@Param("account_id") Long account_id,
			@Param("date") String recon_date);
	
	@Select(" select distinct dsc.id, dsc.posting_date, dsc.description as reference, dsc.amount, dsc.dr_cr, dsc.balance "
			+ " from data_stock_core dsc WHERE cast(dsc.posting_date as date) <= #{date} and dsc.account like '%12100' "
			+ " and dsc.status = 1 and dsc.availability = 1  "
			+ " union  "
			+ " select distinct sc.id, sc.posting_date, sc.description as reference, sc.amount, sc.dr_cr, sc.balance from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where sc.account like '%12100' and cast(sc.posting_date as date) "
			+ " <= #{date} and (sc.match_id = sma.match_id and sm.id = sma.stock_mms_id and cast(sm.date as date) > #{date}) "
			+ " and sm.status = 1 and sm.availability = 1 and sma.status = 1 and sma.availability = 1  and sc.status = 1 and sc.availability = 1")
	List<StockReport> reportCoreStationary(@Param("date")String date);

	@Select(" select distinct dsm.id, dsm.date, dsm.description, dsm.reference, dsm.amount, dsm.dr_cr from  "
			+ " data_stock_mms dsm where cast(dsm.date as date) <= #{date} and dsm.account_segment like '%121'  "
			+ " and dsm.status = 1 and dsm.availability = 1  "
			+ " union "
			+ " select distinct sm.id, sm.date, sm.description, sm.reference, sm.amount, sm.dr_cr from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where cast(sm.date as date) <= #{date}  "
			+ " and sm.account_segment like '%121' and sc.match_id = sma.match_id and sma.stock_mms_id = sm.id  "
			+ " and cast(sc.posting_date as date) > #{date} and sm.status = 1 and sm.availability = 1 and sma.status = 1  "
			+ " and sma.availability = 1  and sc.status = 1 and sc.availability = 1 ")
	List<StockReport> reportMmsStationary(@Param("date") String date);
	
	@Select(" select distinct dsc.id, dsc.posting_date, dsc.description as reference, dsc.amount, dsc.dr_cr, dsc.balance "
			+ " from data_stock_core dsc WHERE cast(dsc.posting_date as date) <= #{date} and dsc.account like '%11100' "
			+ " and dsc.status = 1 and dsc.availability = 1  "
			+ " union  "
			+ " select distinct sc.id, sc.posting_date, sc.description as reference, sc.amount, sc.dr_cr, sc.balance from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where sc.account like '%11100' and cast(sc.posting_date as date) "
			+ " <= #{date} and (sc.match_id = sma.match_id and sm.id = sma.stock_mms_id and cast(sm.date as date) > #{date}) "
			+ " and sm.status = 1 and sm.availability = 1 and sma.status = 1 and sma.availability = 1  and sc.status = 1 and sc.availability = 1")
	List<StockReport> reportCoreTools(@Param("date")String date);

	@Select(" select distinct dsm.id, dsm.date, dsm.description, dsm.reference, dsm.amount, dsm.dr_cr from  "
			+ " data_stock_mms dsm where cast(dsm.date as date) <= #{date} and dsm.account_segment like '%111'  "
			+ " and dsm.status = 1 and dsm.availability = 1  "
			+ " union "
			+ " select distinct sm.id, sm.date, sm.description, sm.reference, sm.amount, sm.dr_cr from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where cast(sm.date as date) <= #{date}  "
			+ " and sm.account_segment like '%111' and sc.match_id = sma.match_id and sma.stock_mms_id = sm.id  "
			+ " and cast(sc.posting_date as date) > #{date} and sm.status = 1 and sm.availability = 1 and sma.status = 1  "
			+ " and sma.availability = 1  and sc.status = 1 and sc.availability = 1 ")
	List<StockReport> reportMmsTools(@Param("date") String date);
	
	@Select(" select distinct dsc.id, dsc.posting_date, dsc.description as reference, dsc.amount, dsc.dr_cr, dsc.balance "
			+ " from data_stock_core dsc WHERE cast(dsc.posting_date as date) <= #{date} and dsc.account like '%11300' "
			+ " and dsc.status = 1 and dsc.availability = 1  "
			+ " union  "
			+ " select distinct sc.id, sc.posting_date, sc.description as reference, sc.amount, sc.dr_cr, sc.balance from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where sc.account like '%11300' and cast(sc.posting_date as date) "
			+ " <= #{date} and (sc.match_id = sma.match_id and sm.id = sma.stock_mms_id and cast(sm.date as date) > #{date}) "
			+ " and sm.status = 1 and sm.availability = 1 and sma.status = 1 and sma.availability = 1  and sc.status = 1 and sc.availability = 1")
	List<StockReport> reportCoreSpares(@Param("date")String date);

	@Select(" select distinct dsm.id, dsm.date, dsm.description, dsm.reference, dsm.amount, dsm.dr_cr from  "
			+ " data_stock_mms dsm where cast(dsm.date as date) <= #{date} and dsm.account_segment like '%113'  "
			+ " and dsm.status = 1 and dsm.availability = 1  "
			+ " union "
			+ " select distinct sm.id, sm.date, sm.description, sm.reference, sm.amount, sm.dr_cr from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where cast(sm.date as date) <= #{date}  "
			+ " and sm.account_segment like '%113' and sc.match_id = sma.match_id and sma.stock_mms_id = sm.id  "
			+ " and cast(sc.posting_date as date) > #{date} and sm.status = 1 and sm.availability = 1 and sma.status = 1  "
			+ " and sma.availability = 1  and sc.status = 1 and sc.availability = 1 ")
	List<StockReport> reportMmsSpares(@Param("date") String date);
	
	@Select(" select distinct dsc.id, dsc.posting_date, dsc.description as reference, dsc.amount, dsc.dr_cr, dsc.balance "
			+ " from data_stock_core dsc WHERE cast(dsc.posting_date as date) <= #{date} and dsc.account like '%10500' "
			+ " and dsc.status = 1 and dsc.availability = 1  "
			+ " union  "
			+ " select distinct sc.id, sc.posting_date, sc.description as reference, sc.amount, sc.dr_cr, sc.balance from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where sc.account like '%10500' and cast(sc.posting_date as date) "
			+ " <= #{date} and (sc.match_id = sma.match_id and sm.id = sma.stock_mms_id and cast(sm.date as date) > #{date}) "
			+ " and sm.status = 1 and sm.availability = 1 and sma.status = 1 and sma.availability = 1  and sc.status = 1 and sc.availability = 1")
	List<StockReport> reportCoreUniform(@Param("date")String date);

	@Select(" select distinct dsm.id, dsm.date, dsm.description, dsm.reference, dsm.amount, dsm.dr_cr from  "
			+ " data_stock_mms dsm where cast(dsm.date as date) <= #{date} and dsm.account_segment like '%105'  "
			+ " and dsm.status = 1 and dsm.availability = 1  "
			+ " union "
			+ " select distinct sm.id, sm.date, sm.description, sm.reference, sm.amount, sm.dr_cr from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where cast(sm.date as date) <= #{date}  "
			+ " and sm.account_segment like '%105' and sc.match_id = sma.match_id and sma.stock_mms_id = sm.id  "
			+ " and cast(sc.posting_date as date) > #{date} and sm.status = 1 and sm.availability = 1 and sma.status = 1  "
			+ " and sma.availability = 1  and sc.status = 1 and sc.availability = 1 ")
	List<StockReport> reportMmsUniform(@Param("date") String date);
	
	
	@Select(" select distinct dsc.id, dsc.posting_date, dsc.description as reference, dsc.amount, dsc.dr_cr, dsc.balance "
			+ " from data_stock_core dsc WHERE cast(dsc.posting_date as date) <= #{date} and dsc.account like '%11900' "
			+ " and dsc.status = 1 and dsc.availability = 1  "
			+ " union  "
			+ " select distinct sc.id, sc.posting_date, sc.description as reference, sc.amount, sc.dr_cr, sc.balance from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where sc.account like '%11900' and cast(sc.posting_date as date) "
			+ " <= #{date} and (sc.match_id = sma.match_id and sm.id = sma.stock_mms_id and cast(sm.date as date) > #{date}) "
			+ " and sm.status = 1 and sm.availability = 1 and sma.status = 1 and sma.availability = 1  and sc.status = 1 and sc.availability = 1")
	List<StockReport> reportCoreAccessory(@Param("date")String date);

	@Select(" select distinct dsm.id, dsm.date, dsm.description, dsm.reference, dsm.amount, dsm.dr_cr from  "
			+ " data_stock_mms dsm where cast(dsm.date as date) <= #{date} and dsm.account_segment like '%119'  "
			+ " and dsm.status = 1 and dsm.availability = 1  "
			+ " union "
			+ " select distinct sm.id, sm.date, sm.description, sm.reference, sm.amount, sm.dr_cr from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where cast(sm.date as date) <= #{date}  "
			+ " and sm.account_segment like '%119' and sc.match_id = sma.match_id and sma.stock_mms_id = sm.id  "
			+ " and cast(sc.posting_date as date) > #{date} and sm.status = 1 and sm.availability = 1 and sma.status = 1  "
			+ " and sma.availability = 1  and sc.status = 1 and sc.availability = 1 ")
	List<StockReport> reportMmsAccessory(@Param("date") String date);
	
	@Select(" select distinct dsc.id, dsc.posting_date, dsc.description as reference, dsc.amount, dsc.dr_cr, dsc.balance "
			+ " from data_stock_core dsc WHERE cast(dsc.posting_date as date) <= #{date} and dsc.account like '%12000' "
			+ " and dsc.status = 1 and dsc.availability = 1  "
			+ " union  "
			+ " select distinct sc.id, sc.posting_date, sc.description as reference, sc.amount, sc.dr_cr, sc.balance from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where sc.account like '%12000' and cast(sc.posting_date as date) "
			+ " <= #{date} and (sc.match_id = sma.match_id and sm.id = sma.stock_mms_id and cast(sm.date as date) > #{date}) "
			+ " and sm.status = 1 and sm.availability = 1 and sma.status = 1 and sma.availability = 1  and sc.status = 1 and sc.availability = 1")
	List<StockReport> reportCoreCheck(@Param("date")String date);

	@Select(" select distinct dsm.id, dsm.date, dsm.description, dsm.reference, dsm.amount, dsm.dr_cr from  "
			+ " data_stock_mms dsm where cast(dsm.date as date) <= #{date} and dsm.account_segment like '%120'  "
			+ " and dsm.status = 1 and dsm.availability = 1  "
			+ " union "
			+ " select distinct sm.id, sm.date, sm.description, sm.reference, sm.amount, sm.dr_cr from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where cast(sm.date as date) <= #{date}  "
			+ " and sm.account_segment like '%120' and sc.match_id = sma.match_id and sma.stock_mms_id = sm.id  "
			+ " and cast(sc.posting_date as date) > #{date} and sm.status = 1 and sm.availability = 1 and sma.status = 1  "
			+ " and sma.availability = 1  and sc.status = 1 and sc.availability = 1 ")
	List<StockReport> reportMmsCheck(@Param("date") String date);
	
	@Select(" select distinct dsc.id, dsc.posting_date, dsc.description as reference, dsc.amount, dsc.dr_cr, dsc.balance "
			+ " from data_stock_core dsc WHERE cast(dsc.posting_date as date) <= #{date} and dsc.account like '%11200' "
			+ " and dsc.status = 1 and dsc.availability = 1  "
			+ " union  "
			+ " select distinct sc.id, sc.posting_date, sc.description as reference, sc.amount, sc.dr_cr, sc.balance from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where sc.account like '%11200' and cast(sc.posting_date as date) "
			+ " <= #{date} and (sc.match_id = sma.match_id and sm.id = sma.stock_mms_id and cast(sm.date as date) > #{date}) "
			+ " and sm.status = 1 and sm.availability = 1 and sma.status = 1 and sma.availability = 1  and sc.status = 1 and sc.availability = 1")
	List<StockReport> reportCoreSanitory(@Param("date")String date);

	@Select(" select distinct dsm.id, dsm.date, dsm.description, dsm.reference, dsm.amount, dsm.dr_cr from  "
			+ " data_stock_mms dsm where cast(dsm.date as date) <= #{date} and dsm.account_segment like '%112'  "
			+ " and dsm.status = 1 and dsm.availability = 1  "
			+ " union "
			+ " select distinct sm.id, sm.date, sm.description, sm.reference, sm.amount, sm.dr_cr from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where cast(sm.date as date) <= #{date}  "
			+ " and sm.account_segment like '%112' and sc.match_id = sma.match_id and sma.stock_mms_id = sm.id  "
			+ " and cast(sc.posting_date as date) > #{date} and sm.status = 1 and sm.availability = 1 and sma.status = 1  "
			+ " and sma.availability = 1  and sc.status = 1 and sc.availability = 1 ")
	List<StockReport> reportMmsSanitory(@Param("date") String date);
	
	@Select(" select distinct dsc.id, dsc.posting_date, dsc.description as reference, dsc.amount, dsc.dr_cr, dsc.balance "
			+ " from data_stock_core dsc WHERE cast(dsc.posting_date as date) <= #{date} and dsc.account like '%10600' "
			+ " and dsc.status = 1 and dsc.availability = 1  "
			+ " union  "
			+ " select distinct sc.id, sc.posting_date, sc.description as reference, sc.amount, sc.dr_cr, sc.balance from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where sc.account like '%10600' and cast(sc.posting_date as date) "
			+ " <= #{date} and (sc.match_id = sma.match_id and sm.id = sma.stock_mms_id and cast(sm.date as date) > #{date}) "
			+ " and sm.status = 1 and sm.availability = 1 and sma.status = 1 and sma.availability = 1  and sc.status = 1 and sc.availability = 1")
	List<StockReport> reportCoreComputer(@Param("date")String date);

	@Select(" select distinct dsm.id, dsm.date, dsm.description, dsm.reference, dsm.amount, dsm.dr_cr from  "
			+ " data_stock_mms dsm where cast(dsm.date as date) <= #{date} and dsm.account_segment like '%106'  "
			+ " and dsm.status = 1 and dsm.availability = 1  "
			+ " union "
			+ " select distinct sm.id, sm.date, sm.description, sm.reference, sm.amount, sm.dr_cr from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where cast(sm.date as date) <= #{date}  "
			+ " and sm.account_segment like '%106' and sc.match_id = sma.match_id and sma.stock_mms_id = sm.id  "
			+ " and cast(sc.posting_date as date) > #{date} and sm.status = 1 and sm.availability = 1 and sma.status = 1  "
			+ " and sma.availability = 1  and sc.status = 1 and sc.availability = 1 ")
	List<StockReport> reportMmsComputer(@Param("date") String date);
	
	@Select(" select distinct dsc.id, dsc.posting_date, dsc.description as reference, dsc.amount, dsc.dr_cr, dsc.balance "
			+ " from data_stock_core dsc WHERE cast(dsc.posting_date as date) <= #{date} and dsc.account like '%10700' "
			+ " and dsc.status = 1 and dsc.availability = 1  "
			+ " union  "
			+ " select distinct sc.id, sc.posting_date, sc.description as reference, sc.amount, sc.dr_cr, sc.balance from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where sc.account like '%10700' and cast(sc.posting_date as date) "
			+ " <= #{date} and (sc.match_id = sma.match_id and sm.id = sma.stock_mms_id and cast(sm.date as date) > #{date}) "
			+ " and sm.status = 1 and sm.availability = 1 and sma.status = 1 and sma.availability = 1  and sc.status = 1 and sc.availability = 1")
	List<StockReport> reportCoreFurniture(@Param("date")String date);

	@Select(" select distinct dsm.id, dsm.date, dsm.description, dsm.reference, dsm.amount, dsm.dr_cr from  "
			+ " data_stock_mms dsm where cast(dsm.date as date) <= #{date} and dsm.account_segment like '%107'  "
			+ " and dsm.status = 1 and dsm.availability = 1  "
			+ " union "
			+ " select distinct sm.id, sm.date, sm.description, sm.reference, sm.amount, sm.dr_cr from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where cast(sm.date as date) <= #{date}  "
			+ " and sm.account_segment like '%107' and sc.match_id = sma.match_id and sma.stock_mms_id = sm.id  "
			+ " and cast(sc.posting_date as date) > #{date} and sm.status = 1 and sm.availability = 1 and sma.status = 1  "
			+ " and sma.availability = 1  and sc.status = 1 and sc.availability = 1 ")
	List<StockReport> reportMmsFurniture(@Param("date") String date);
	
	@Select(" select distinct dsc.id, dsc.posting_date, dsc.description as reference, dsc.amount, dsc.dr_cr, dsc.balance "
			+ " from data_stock_core dsc WHERE cast(dsc.posting_date as date) <= #{date} and dsc.account like '%10400' "
			+ " and dsc.status = 1 and dsc.availability = 1  "
			+ " union  "
			+ " select distinct sc.id, sc.posting_date, sc.description as reference, sc.amount, sc.dr_cr, sc.balance from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where sc.account like '%10400' and cast(sc.posting_date as date) "
			+ " <= #{date} and (sc.match_id = sma.match_id and sm.id = sma.stock_mms_id and cast(sm.date as date) > #{date}) "
			+ " and sm.status = 1 and sm.availability = 1 and sma.status = 1 and sma.availability = 1  and sc.status = 1 and sc.availability = 1")
	List<StockReport> reportCoreOfficeEquipment(@Param("date")String date);

	@Select(" select distinct dsm.id, dsm.date, dsm.description, dsm.reference, dsm.amount, dsm.dr_cr from  "
			+ " data_stock_mms dsm where cast(dsm.date as date) <= #{date} and dsm.account_segment like '%104'  "
			+ " and dsm.status = 1 and dsm.availability = 1  "
			+ " union "
			+ " select distinct sm.id, sm.date, sm.description, sm.reference, sm.amount, sm.dr_cr from  "
			+ " stock_mms sm, stock_matched sma, stock_core sc where cast(sm.date as date) <= #{date}  "
			+ " and sm.account_segment like '%104' and sc.match_id = sma.match_id and sma.stock_mms_id = sm.id  "
			+ " and cast(sc.posting_date as date) > #{date} and sm.status = 1 and sm.availability = 1 and sma.status = 1  "
			+ " and sma.availability = 1  and sc.status = 1 and sc.availability = 1 ")
	List<StockReport> reportMmsOfficeEquipment(@Param("date") String date);
	
	@Select("select ISNULL(sum(stock_stationary_core), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceCore121_stationary(String date);
	@Select("select ISNULL(sum(stock_stationary), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceMms121_stationary(String date);
	
	@Select("select ISNULL(sum(stock_tools_core), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceCore111_tools(String date);
	@Select("select ISNULL(sum(stock_tools), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceMms111_tools(String date);
	
	@Select("select ISNULL(sum(stock_spares_core), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceCore113_spares(String date);
	@Select("select ISNULL(sum(stock_spares), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceMms113_spares(String date);
	
	@Select("select ISNULL(sum(stock_uniform_core), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceCore105_uniform(String date);
	@Select("select ISNULL(sum(stock_uniform), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceMms105_uniform(String date);
	
	@Select("select ISNULL(sum(stock_accessory_core), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceCore119_accessory(String date);
	@Select("select ISNULL(sum(stock_accessory), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceMms119_accessory(String date);
	
	@Select("select ISNULL(sum(stock_check_core), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceCore120_check(String date);
	@Select("select ISNULL(sum(stock_check), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceMms120_check(String date);
	
	@Select("select ISNULL(sum(stock_sanitory_core), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceCore112_sanitory(String date);
	@Select("select ISNULL(sum(stock_sanitory), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceMms112_sanitory(String date);
	
	@Select("select ISNULL(sum(stock_computer_core), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceCore106_computer(String date);
	@Select("select ISNULL(sum(stock_computer), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceMms106_computer(String date);
	
	@Select("select ISNULL(sum(stock_furniture_core), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceCore107_furniture(String date);
	@Select("select ISNULL(sum(stock_furniture), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceMms107_furniture(String date);
	
	@Select("select ISNULL(sum(stock_office_equipment_core), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceCore104_office_equipment(String date);
	@Select("select ISNULL(sum(stock_office_equipment), 0) from data_trial_balance where date=#{date}")
	Double getEndingBalanceMms104_office_equipment(String date);

}

