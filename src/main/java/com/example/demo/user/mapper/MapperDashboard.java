package com.example.demo.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.user.model.AtsBeginningEnding;
import com.example.demo.user.model.CoreBeginningEnding;

@Mapper
public interface MapperDashboard {

	@Select("select count(*) from account where status = 1 and availability = 1")
	int getTotalNumberOfAccounts();

	@Select("select count(*) from currency where status = 1 and availability = 1")
	int getTotalNumberOfCurrencies();

	@Select("select count(*) from files where status = 1 and availability = 1")
	int getTotalNumberOfFiles();

	@Select("select ((select count(*) from data_nbe_ats where status = 1 and availability = 1) +"
			+ "(select count(*) from ats_partially_matched where status = 1 and availability = 1) +"
			+ "(select count(*) from rtgs_nbe_ats where status = 1 and availability = 1) +"
			+ "(select count(*) from erca_nbe_ats where status = 1 and availability = 1) +"
			+ "(select count(*) from sos_nbe_ats where status = 1 and availability = 1) +"
			+ "(select count(*) from b2b_nbe_ats where status = 1 and availability = 1))")
	int getTotalNumberOfTransactionsAts();

	@Select("select ((select count(*) from data_awb_core where status = 1 and availability = 1 ) + "
			+ "(select count(*) from core_partially_matched where status = 1 and availability = 1) + "
			+ "(select count(*) from rtgs_awb_core where status = 1 and availability = 1) + "
			+ "(select count(*) from erca_awb_core where status = 1 and availability = 1) + "
			+ "(select count(*) from sos_awb_core where status = 1 and availability = 1) + "
			+ "(select count(*) from b2b_awb_core where status = 1 and availability = 1)) ")
	int getTotalNumberOfTransactionsCore();

	@Select("select count(*) from data_awb_core where status = 1 and availability = 1 and dr_cr = 'CR'")
	int getOutstandingTransactionsOnCoreCredit();

	@Select("select count(*) from data_awb_core where status = 1 and availability = 1 and dr_cr = 'DR'")
	int getOutstandingTransactionsOnCoreDebit();

	@Select("select count(*) from data_nbe_ats where status = 1 and availability = 1 and dr_cr = 'CR'")
	int getOutstandingTransactionsOnAtsCredit();

	@Select("select count(*) from data_nbe_ats where status = 1 and availability = 1 and dr_cr = 'DR'")
	int getOutstandingTransactionsOnAtsDebit();

	@Select("SELECT TOP 1 beginning_balance_ats FROM files where type = 'ATS' AND status = 1 and availability = 1 ORDER BY ID DESC")
	Double getBeginningBalanceAts();
	
	@Select("SELECT TOP 1 ending_balance_ats FROM files where type = 'ATS' AND status = 1 and availability = 1 ORDER BY ID DESC")
	Double getEndingBalanceAts();

	@Select("SELECT TOP 1 beginning_balance_ifb FROM files where type = 'CORE' AND status = 1 and availability = 1 ORDER BY ID DESC")
	Double getBeginningBalanceCoreIfb();
	
	@Select("SELECT TOP 1 ending_balance_ifb FROM files where type = 'CORE' AND status = 1 and availability = 1 ORDER BY ID DESC")
	Double getEndingBalanceCoreIfb();

	@Select("SELECT TOP 1 beginning_balance_con FROM files where type = 'CORE' AND status = 1 and availability = 1 ORDER BY ID DESC")
	Double getBeginningBalanceCoreConv();
	
	@Select("SELECT TOP 1 ending_balance_con FROM files where type = 'CORE' AND status = 1 and availability = 1 ORDER BY ID DESC")
	Double getEndingBalanceCoreConv();

	@Select("select ISNULL(sum(amount), 0) from data_awb_core where status = 1 and availability = 1 and dr_cr = 'CR'")
	Double getOutstandingBalanceOnCoreCredit();

	@Select("select ISNULL(sum(amount), 0) from data_awb_core where status = 1 and availability = 1 and dr_cr = 'DR'")
	Double getOutstandingBalanceOnCoreDebit();

	@Select("select ISNULL(sum(amount), 0) from data_nbe_ats where status = 1 and availability = 1 and dr_cr = 'CR'")
	Double getOutstandingBalanceOnAtsCredit();

	@Select("select ISNULL(sum(amount), 0) from data_nbe_ats where status = 1 and availability = 1 and dr_cr = 'DR'")
	Double getOutstandingBalanceOnAtsDebit();

	@Select("select count(*) from edit_reason where status = 1 and availability = 1 and edit_delete = 1")
	int getNumberOfEditedTransactions();

	@Select("select count(*) from edit_reason where status = 1 and availability = 1 and edit_delete = 2")
	int getNumberOfDeletedTransactions();

	@Select("select count(*) from rtgs_nbe_ats where status = 1 and availability = 1")
	int getNumberOfMatchedTransactionsAtsRTGS();

	@Select("select count(*) from sos_nbe_ats where status = 1 and availability = 1")
	int getNumberOfMatchedTransactionsAtsSOS();

	@Select("select count(*) from b2b_nbe_ats where status = 1 and availability = 1")
	int getNumberOfMatchedTransactionsAtsB2B();

	@Select("select count(*) from erca_nbe_ats where status = 1 and availability = 1")
	int getNumberOfMatchedTransactionsAtsERCA();

	@Select("select count(*) from rtgs_awb_core where status = 1 and availability = 1")
	int getNumberOfMatchedTransactionsCoreRTGS();

	@Select("select count(*) from sos_awb_core where status = 1 and availability = 1")
	int getNumberOfMatchedTransactionsCoreSOS();

	@Select("select count(*) from b2b_awb_core where status = 1 and availability = 1")
	int getNumberOfMatchedTransactionsCoreB2B();

	@Select("select count(*) from erca_awb_core where status = 1 and availability = 1")
	int getNumberOfMatchedTransactionsCoreERCA();

	@Select("select top 1 upload_date from files where type = 'ATS' and status = 1 and availability = 1 ORDER BY ID DESC")
	String getAtsFileLastDate();

	@Select("select top 1 upload_date, id from files where type = 'CORE' and status = 1 and availability = 1 ORDER BY ID DESC")
	String getCoreFileLastDate();

	@Select("select ISNULL((ISNULL(total_number_of_credit_con,0) + ISNULL(total_number_of_credit_ifb,0) "
			+ "+ ISNULL(total_number_of_debit_con,0) + ISNULL(total_number_of_debit_ifb,0)),0)  from files "
			+ "where type = 'CORE' and status = 1 and availability = 1 and upload_date = #{date} ORDER BY ID DESC")
	Integer getTotalUploadedTransactionsCore(@Param("date") String string);

	@Select("select ((select ISNULL(count(*),0) from core_partially_matched where status = 1 and availability = 1 and upload_date = #{date})+ "
			+ "(select ISNULL(count(*),0) from rtgs_awb_core where status = 1 and availability = 1 and upload_date = #{date})+ "
			+ "(select ISNULL(count(*),0) from sos_awb_core where status = 1 and availability = 1 and upload_date = #{date})+ "
			+ "(select ISNULL(count(*),0) from b2b_awb_core where status = 1 and availability = 1 and upload_date = #{date})+ "
			+ "(select ISNULL(count(*),0) from erca_awb_core where status = 1 and availability = 1 and upload_date = #{date}))")
	Integer getMatchedTransactionsCore(@Param("date") String string);

	@Select("select ISNULL(count(*),0) from data_awb_core where status = 1 and availability = 1 and upload_date = #{date}")
	Integer getOutstandingTransactionsCore(@Param("date") String string);

	@Select("select ISNULL((ISNULL(total_number_of_credit_ats,0) + ISNULL(total_number_of_debit_ats,0)), 0)  from files "
			+ "where type = 'ATS' and status = 1 and availability = 1 and upload_date = #{date} ORDER BY ID DESC")
	Integer getTotalUploadedTransactionsAts(@Param("date") String string);

	@Select("select ((select ISNULL(count(*),0) from ats_partially_matched where status = 1 and availability = 1 and upload_date = #{date})+"
			+ "(select ISNULL(count(*),0) from rtgs_nbe_ats where status = 1 and availability = 1 and upload_date = #{date})+"
			+ "(select ISNULL(count(*),0) from sos_nbe_ats where status = 1 and availability = 1 and upload_date = #{date})+"
			+ "(select ISNULL(count(*),0) from b2b_nbe_ats where status = 1 and availability = 1 and upload_date = #{date})+"
			+ "(select ISNULL(count(*),0) from erca_nbe_ats where status = 1 and availability = 1 and upload_date = #{date}))")
	Integer getMatchedTransactionsAts(@Param("date") String string);

	@Select("select ISNULL(count(*),0) from data_nbe_ats where status = 1 and availability = 1 and upload_date = #{date}")
	Integer getOutstandingTransactionsAts(@Param("date") String string);

//	@Select("select ISNULL(beginning_balance_ats,0) as beginning_balance_ats, ISNULL(ending_balance_ats,0) as ending_balance_ats from files "
//			+ "where type = 'ATS' and status = 1 and availability = 1 and upload_date = #{date} ORDER BY ID DESC")
//	AtsBeginningEnding getBeginningEndingBalanceAts(@Param("date") String string);
	
	@Select("select beginning_balance_ats as beginning_balance_ats, ISNULL(ending_balance_ats,0) as ending_balance_ats from files "
			+ "where type = 'ATS' and status = 1 and availability = 1 and upload_date = #{date} ORDER BY ID DESC")
	AtsBeginningEnding getBeginningEndingBalanceAts(@Param("date") String string);

	@Select("select ISNULL(beginning_balance_con,0) as beginning_balance_con"
			+ ", ISNULL(beginning_balance_ifb,0) as beginning_balance_ifb"
			+ ", ISNULL(ending_balance_con,0) as ending_balance_con"
			+ ", ISNULL(ending_balance_ifb,0) as ending_balance_ifb from files "
			+ "where type = 'CORE' and status = 1 and availability = 1 and upload_date = #{date} ORDER BY ID DESC")
	CoreBeginningEnding getBeginningEndingBalanceCore(@Param("date") String string);

}
