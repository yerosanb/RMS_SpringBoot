package com.example.demo.user.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.Files_;
import com.example.demo.user.model.Account;
import com.example.demo.user.model.FileUpload;
import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;
import com.example.demo.user.model.Notification;

@Mapper
public interface MapperAccount {

	@Select("insert into account_request(name, code, description, created_date, type, status, availability, request_status)"
			+ "OUTPUT Inserted.id " + " values (#{name}, #{code}, #{description}, #{created_date}, 'new', 1, 1, 0)")
	public Long accountRequest(Account account);

	@Insert("insert into user_account_request (account_request_id, user_id, status, availability) values ( #{account_request_id},"
			+ " #{user_id}, 1, 1)")
	public void addUserAccount(@Param("account_request_id") Long account_id, @Param("user_id") Long user_id);

	@Select("insert into notification(title, description, date, view_status, status, availability)"
			+ "OUTPUT Inserted.id " + " values (#{title}, #{description}, #{date}, #{viewStatus}, 1, 1)")
	public Long addAccountNotification(Notification notification);

	// @Select("select account_request_id from account_account_request where
	// id=#{id}")
	// public Long getAccountRequestId(Long id);

	@Insert("insert into user_notification (user_id, notification_id, status, availabilty) values ( #{user_id},"
			+ " #{notification_id}, 1, 1)")
	public void addUserNotification(@Param("user_id") Long user_id, @Param("notification_id") Long notification_id);

	// @Select ("SELECT ar.name, ar.code, ar.created_date, ar.description, a.name,
	// a.code, a.created_date, a.description "
	// + "FROM account_request ar INNER JOIN account a ON ar.code != a.code")
	// @Select("insert into account_request(name, code, description, created_date,
	// currency, request_type, status, availability, request_status)"
	// + "OUTPUT Inserted.id "
	// + " values (#{name}, #{code}, #{description}, #{created_date}, #{currency},
	// 'new', 1, 1, 0)" )
	//
	// @Select ("SELECT ar.name, ar.code, ar.created_date, ar.description,c.name
	// FROM account_request ar INNER JOIN currency c where ar.id =
	// account_request_currency.currency_id and ar.status IN (0,1,2)")
	// @Select("SELECT * FROM account_request WHERE request_status IN (0, 1,2)")
	@Select("Select a.id, a.request_status, a.name, a.code, a.description, a.created_date, c.name as currency from account_request a, currency c, account_request_currency ac "
			+ "where a.id = ac.account_request_id and ac.currency_id = c.id and a.request_status IN (0,1,2) and a.availability = 1")
	public List<Account> getAllAccounts();

	// @Select("select * from account")
	@Select("Select a.id, a.name, a.code, a.description, a.created_date, c.name as currency from account a, currency c, account_currency ac \r\n"
			+ "	where a.id = ac.account_id and ac.currency_id = c.id and a.status = 1 and a.availability = 1 and c.availability = 1")
	public List<Account> getApprovedAccounts();

	// @Select("select * from account_request where request_status='0'")
	@Select("Select a.id, a.name, a.code, a.description, a.created_date, c.name as currency from account_request a, currency c, account_request_currency ac "
			+ "where a.id = ac.account_request_id and ac.currency_id = c.id and a.request_status = 0 and a.availability = 1 and a.availability = 1")
	public List<Account> getPendingAccounts();

	// @Select("select * from account_request where request_status='2'")
	@Select("Select a.id, a.name, a.code, a.description, a.created_date, c.name as currency from account_request a, currency c, account_request_currency ac "
			+ "where a.id = ac.account_request_id and ac.currency_id = c.id and a.request_status = 2 and a.availability = 1 and c.availability = 1")
	public List<Account> getRejectedAccounts();

	@Insert("insert into account_request_currency (account_request_id, currency_id, status, availability) values ( #{account_request_id},"
			+ " #{currency_id}, 1, 1)")
	public void addAccountRequestCurrency(@Param("account_request_id") Long account_request_id,
			@Param("currency_id") Long currency_id);

	@Insert("insert into account_currency (account_id, currency_id, status, availability) values ( #{account_id},"
			+ " #{currency_id}, 1, 1)")
	public void addAccountCurrency(@Param("account_id") Long account_id, @Param("currency_id") Long currency_id);

	@Insert("insert into account_account_request (account_id, account_request_id, status, availability) values ( #{account_id},"
			+ " #{account_request_id}, 1, 1)")
	public void addAccountAccountRequest(@Param("account_id") Long account_id,
			@Param("account_request_id") Long account_request_id);

	@Select("select * from account  where availability = 1")
	public List<Account> getAll();

	@Update("update account_request set name = #{name}, code = #{code}, created_date = #{created_date}, description = #{description}, type = 'update', status = '1', availability = '1' where id = #{id}")
	public Boolean updateRequest(Account account);

	@Update("update cr set cr.name = #{name}, cr.code = #{code},\r\n"
			+ "cr.created_date = #{created_date}, cr.description = #{description}, cr.type = 'update', cr.status = '1', cr.request_status='0',\r\n"
			+ "cr.availability = '1' from account_request cr , account_account_request ccr,\r\n"
			+ " account c  where cr.id = ccr.account_request_id and c.id= ccr.account_id and cr.id=(select account_request_id from account_account_request ccr  where\r\n"
			+ " ccr.account_id=#{id})")
	public Boolean updateApprovedRequest(Account account);

	@Select("select account_request_id from account_account_request ccr  where\r\n" + " ccr.account_id=#{id}")
	public Long getEdittedAccountsId(Long id);

	// @Update("UPDATE account_request SET name = #{name}, code = #{code},
	// created_date = #{created_date}, description = #{description}, type =
	// 'update', request_status='0', status = '1', availability = '1' where id =
	// #{req_id}")
	// public Boolean updateApprovedRequest(Account account, @Param("req_id") Long
	// req_id);

	@Select("select * from account_request where id=#{id} and availability = 1" )
	public Account findById(Long id);
	//
	// DELETE messages , usersmessages FROM messages INNER JOIN usersmessages
	// WHERE messages.messageid= usersmessages.messageid and messages.messageid =
	// '1'

	// @Delete("delete from account_currency where id=#{id}")
	// public void deleteFromAccountCurrency(Long id);
	//
	// @Delete("delete from user_account_request where id=#{id}")
	// public void deleteFromUserAccount(Long id);
	//
	// @Delete("delete from account_account_request where id=#{id}")
	// public void deleteFromAccountAccountRequest(Long id);

	// @Delete("delete from account where id=#{id}")
	@Update("UPDATE account SET request_status = '3' WHERE id=#{id}")
	public void deleteAccount(Long id);

	// @Delete("delete from account_request where id=#{id}")
	@Update("UPDATE account_request SET request_status = '3' WHERE id=#{id}")
	public void deleteAccountRequest(Long id);

	// @Select("select * from account_request where id=#{id}")
	// @Select("Select a.id, a.name, a.code, a.description, c.name as currency from
	// account_request a, currency c, account_request_currency ac"
	// + "where id=#{id} and a.id = ac.account_request_id and ac.currency_id = c.id
	// and a.status = 1 and a.availability = 1")
	@Select("	select a.id,a.name, a.code, a.description, c.name as currency from account a \r\n"
			+ "	,account_currency ac, currency c where a.id =ac.account_id  and ac.currency_id = c.id and a.id=#{id} and a.status=1 and a.availability=1\r\n"
			+ "	and ac.status=1 and ac.availability=1 and c.status=1 and c.availability=1;")
	public Account findByIdAccount(Long id);

	// @Select("select name from user where name = Approver")
	// public String getApproveName();

	@Select("Select a.id, a.name, a.code, a.description, c.name as currency from account a, currency c, account_currency ac "
			+ "where a.id = ac.account_id and ac.currency_id = c.id and a.status = 1 and a.availability = 1")
	public List<Account> getAllAccountsToSet();

	@Select("select account_id from user_selected_account a"
			+ "	where user_id = #{user_id} and status = 1 and availability = 1")
	public Long getSelectedAccount(@Param("user_id") Long get_user_id);

	@Update("update user_selected_account set account_id = #{account_id} where user_id = #{user_id}")
	public Boolean changeUserWorkspaceAccount(@Param("user_id") Long user_id, @Param("account_id") String account_id);

	@Insert("insert into user_selected_account (user_id, account_id, status, availability) values (#{user_id}, #{account_id}, #{status}, #{availability})")
	public Boolean setUserWorkspaceAccount(@Param("user_id") Long user_id, @Param("account_id") String account_id,
			@Param("status") String status, @Param("availability") String availability);

	@Insert("insert into user_workspace (user_id, workspace_id, status, availability) values "
			+ "(#{user_id}, #{reconciliation_id}, #{status}, #{availability})")
	public Boolean setWorkspace(@Param("user_id") Long user_id, @Param("reconciliation_id") Long reconciliation_id,
			@Param("status") String status, @Param("availability") String availability);

	@Select("select w.reconciliation_type from workspace w where id = (select uw.workspace_id from "
			+ "user_workspace uw where user_id = #{user_id})")
	public String getCurrentWorkspace(@Param("user_id") Long user_id);

	@Select("Select CONCAT(a.code, '_', a.name, '_',c.name) from account a, currency c, account_currency ac, user_selected_account usa, users u "
			+ "where a.id = ac.account_id and ac.currency_id = c.id and a.status = 1 and a.availability = 1 and a.id = usa.account_id and usa.user_id = u.id "
			+ "and u.id = #{user_id} and usa.status = 1 and usa.availability= 1")
	public Optional<String> getUserAccountAndCurrency(@Param("user_id") Long user_id);

	// @Insert("insert into files (name, path, file_type, usage_type, upload_date,
	// status, availability) OUTPUT Inserted.id values "
	// + " ('name', 'name', 'name', 'name', 'name', '1','1')")

	@Select("insert into files (name, path, file_type, usage_type, upload_date, status, availability, original_file_name,type) OUTPUT Inserted.id values "
			+ "(#{name}, #{path}, #{file_type}, #{usage_type}, #{upload_date}, #{status}, #{availability}, #{original_file_name},#{type});")
	public Long addFile(Files_ newfile);

	@Insert("insert into file_account (account_id, file_id, status, availability) values "
			+ "(#{user_account_id}, #{file_id}, #{status}, #{availability});")
	public void addAccountFile(@Param("user_account_id") Long user_account_id, @Param("file_id") Long file_id,
			@Param("status") String status, @Param("availability") String availability);

	@Insert("insert into file_user_date (date, file_id, status, availability, user_id, left_right) values "
			+ "(#{date}, #{file_id}, #{status}, #{availability}, #{user_id}, #{left_right});")
	public void addFileUserDate(@Param("date") String transaction_date, @Param("file_id") Long file_id,
			@Param("status") String string, @Param("availability") String string2, @Param("user_id") Long user_id,
			@Param("left_right") String left_right);

	@Select("select account_id from user_selected_account where user_id = #{user_id}")
	public Long getUserAccountId(@Param("user_id") Long user_id);

	// @Select("select workspace_id from user_workspace where user_id = #{user_id}")
	// public Long getUserWorkspaceId(@Param("user_id") Long user_id);

	@Insert("insert into data__ats (value_date_type, reference, sender, receiver, additional_information, amount, "
			+ "dr_cr, upload_date, match_status, status, availability, file_id) values (#{value_date_type}, #{reference}, #{sender}, #{receiver}, "
			+ "#{additional_information}, #{amount}, #{dr_cr}, #{upload_date}, #{match_status}, #{status}, #{availability}, #{file_id});")
	public void uploadData__ats(File_rtgs__ats file_Rtgs);

	// @Select("select top 1 '1' from files f, file_workspace_date fwd, file_account
	// fc"
	// + " where f.upload_date = #{upload_date} and f.id = fwd.file_id and
	// fwd.workspace_id = #{workspace_id} and "
	// + "f.id = fc.file_id and fc.account_id = #{account_id} and fwd.left_right =
	// #{left_right} and f.status = 1 and f.availability = 1 "
	// + "and fc.status = 1 and fc.availability = 1 and fwd.status = 1 and
	// fwd.availability = 1")
	@Select("select top 1 '1' from files f, file_user_date fud, file_account fc "
			+ "			where f.upload_date = #{upload_date} and f.id = fud.file_id and "
			+ "			f.id = fc.file_id and fc.account_id = #{account_id} and fud.left_right = #{left_right} and f.status = 1 and f.availability = 1 "
			+ "			and fc.status = 1 and fc.availability = 1 and fud.status = 1 and fud.availability = 1")
	public Optional<String> getWorkspaceIdFromReconciliationType(@Param("upload_date") String transaction_date,
			@Param("account_id") Long user_account_id, @Param("left_right") String recon_left_right);

	@Insert("insert into data__ (posting_date, transaction_reference, branch_code, additional_information, amount, "
			+ "dr_cr, upload_date, match_status, status, availability, value_date, file_id) values (#{posting_date}, #{transaction_reference}, #{branch_code},"
			+ " #{additional_information}, #{amount}, #{dr_cr}, #{upload_date}, #{match_status}, #{status}, #{availability}, #{value_date}, #{file_id});")
	public void uploadData__(File_rtgs__ file_rtgs__);
	
	@Insert("insert into data_recivable (posting_date, transaction_reference, branch_code, additional_information, amount, "
			+ "dr_cr, upload_date, match_status, status, availability, value_date, file_id) values (#{posting_date}, #{transaction_reference}, #{branch_code},"
			+ " #{additional_information}, #{amount}, #{dr_cr}, #{upload_date}, #{match_status}, #{status}, #{availability}, #{value_date}, #{file_id});")
	public void uploadData_receivable(File_rtgs__ file_rtgs__);

	/////////////////////////////////// Payable started //////////////////////////////
	@Insert("insert into data_payable (posting_date, transaction_reference, branch_code, additional_information, amount, "
			+ "dr_cr, upload_date, match_status, status, availability, value_date, file_id) values (#{posting_date}, #{transaction_reference}, #{branch_code},"
			+ " #{additional_information}, #{amount}, #{dr_cr}, #{upload_date}, #{match_status}, #{status}, #{availability}, #{value_date}, #{file_id});")
	public void uploadDataPayable(File_rtgs__ file_rtgs__);
	
	@Insert("insert into data_issue_ (posting_date, branch, additional_information, amount, "
			+ "dr_cr, upload_date, match_status, status, availability, value_date, file_id, balance) values (#{posting_date}, #{branch},"
			+ " #{additional_information}, #{amount}, #{dr_cr}, #{upload_date}, #{match_status}, #{status}, #{availability}, #{value_date}, #{file_id}, #{balance});")
	public void uploadData_issue_(File_rtgs__ file_rtgs__);	
	
	@Insert("insert into data_issue_qbs (posting_date, branch, additional_information, amount, "
			+ "dr_cr, upload_date, match_status, status, availability, value_date, file_id, balance) values (#{posting_date}, #{branch},"
			+ " #{additional_information}, #{amount}, #{dr_cr}, #{upload_date}, #{match_status}, #{status}, #{availability}, #{value_date}, #{file_id}, #{balance});")
	public void uploadData_issue_qbs(File_rtgs__ file_rtgs__);


	@Select("select top 1 '1' from account_request where code = #{code}")
	public Optional<String> check_AccountCodeExist(@Param("code") String acc);

	@Select("select top 1 '1' from account_request where name = #{name}")
	public Optional<String> check_AccountNameExist(@Param("name") String acc);

	@Select("select  top 1 '1' FROM currency c, account_request a, account_request_currency ac  where  c.id= #{currency} and  \r\n"
			+ "a.name= #{account_name} and  ac.account_request_id = a.id and ac.currency_id = c.id")

	public Optional<String> check_AccountCurrencyExist(@Param("currency") String currency,
			@Param("account_name") String account_currency);

	@Update("update files set beginning_balance_ats = #{beginning_balance_ats}, "
			+ "ending_balance_ats = #{ending_balance_ats}, "
			+ "total_number_of_credit_ats = #{total_number_of_credit_ats}, "
			+ "total_number_of_debit_ats = #{total_number_of_debit_ats}, " + "total_credit_ats = #{total_credit_ats}, "
			+ "total_debit_ats = #{total_debit_ats}, " + "business_date = #{business_date}, "
			+ "account_number = #{account_number} where id = #{file_id} ")
	public void updateAdditionalFileInfoAts(@Param("beginning_balance_ats") Double double1,
			@Param("ending_balance_ats") Double double2, @Param("total_number_of_credit_ats") int totalCredit,
			@Param("total_number_of_debit_ats") int totalDebit, @Param("total_credit_ats") Double double3,
			@Param("total_debit_ats") Double double4, @Param("business_date") String businessDate,
			@Param("account_number") String accountNumber, @Param("file_id") Long file_id);

	@Update("update files set beginning_balance_con = #{beginning_balance_con}, "
			+ "beginning_balance_ifb = #{beginning_balance_ifb}, " + "ending_balance_con = #{ending_balance_con}, "
			+ "ending_balance_ifb = #{ending_balance_ifb}, "
			+ "total_number_of_credit_con = #{total_number_of_credit_con}, "
			+ "total_number_of_credit_ifb = #{total_number_of_credit_ifb}, "
			+ "total_number_of_debit_con = #{total_number_of_debit_con}, "
			+ "total_number_of_debit_ifb = #{total_number_of_debit_ifb}, " + "total_credit_con = #{total_credit_con}, "
			+ "total_credit_ifb = #{total_credit_ifb}, " + "total_debit_con = #{total_debit_con}, "
			+ "total_debit_ifb = #{total_debit_ifb} " + " where id = #{file_id} ")
	public void updateAdditionalFileInfo(@Param("beginning_balance_con") Double double1,
			@Param("beginning_balance_ifb") Double double2, @Param("ending_balance_con") Double double3,
			@Param("ending_balance_ifb") Double double4, @Param("total_number_of_credit_con") int totalCredit_con,
			@Param("total_number_of_credit_ifb") int totalCredit_ifb,
			@Param("total_number_of_debit_con") int totalDebit_con,
      @Param("total_number_of_debit_ifb") int totalDebit_ifb,
			@Param("total_credit_con") Double double5,
			@Param("total_credit_ifb") Double double6,
			@Param("total_debit_con") Double double7,
			@Param("total_debit_ifb") Double double8,
			@Param("file_id") Long file_id);
  
	@Update("update files set  " + "beginning_balance_ifb = #{beginning_balance_ifb}, "
			+ "ending_balance_ifb = #{ending_balance_ifb}, "
			+ "total_number_of_credit_ifb = #{total_number_of_credit_ifb}, "
			+ "total_number_of_debit_ifb = #{total_number_of_debit_ifb}, " + "total_credit_ifb = #{total_credit_ifb}, "
			+ "total_debit_ifb = #{total_debit_ifb} " + " where id = #{file_id} ")
	public void updateAdditionalFileInfoIssue(@Param("beginning_balance_ifb") Double double2,
			@Param("ending_balance_ifb") Double double4, @Param("total_number_of_credit_ifb") int totalCredit_ifb,
			@Param("total_number_of_debit_ifb") int totalDebit_ifb, @Param("total_credit_ifb") Double double6,
			@Param("total_debit_ifb") Double double8, @Param("file_id") Long file_id);
	
	@Update("update files set beginning_balance_ifb = #{beginning_balance_ifb}, " +
			"ending_balance_ifb = #{ending_balance_ifb}, " +
			"total_number_of_credit_ifb = #{total_number_of_credit_ifb}, " +
			"total_number_of_debit_ifb = #{total_number_of_debit_ifb}, " +
			"total_credit_ifb = #{total_credit_ifb}, " +
			"total_debit_ifb = #{total_debit_ifb} " +
			" where id = #{file_id} ")
	public void updateAdditionalFileInfoReceivable(
			@Param("beginning_balance_ifb") Double double2,
			@Param("ending_balance_ifb") Double double4,
			@Param("total_number_of_credit_ifb") int totalCredit_ifb,
			@Param("total_number_of_debit_ifb") int totalDebit_ifb,
			@Param("total_credit_ifb") Double double6,
			@Param("total_debit_ifb") Double double8,
			@Param("file_id") Long file_id);

	@Select("select amount from data__ats where reference = '0011677063'")
	public Double getAmountMM();

	@Select("select f.id, f.original_file_name as file_name, f.file_type, f.upload_date, a.name as account_name, c.name as currency_name,\r\n"
			+ "		 u.firstname as first_name,u.email, u.middlename as middle_name, u.lastname \r\n"
			+ "			as last_name, fud.left_right as type from files f, account a, currency c, account_currency ac, file_account fa, \r\n"
			+ "			file_user_date fud, users u \r\n"
			+ "			where fud.file_id = f.id and fud.user_id = u.id and fa.account_id = a.id and f.id = fa.file_id and ac.account_id = a.id \r\n"
			+ "			and ac.currency_id = c.id and f.availability = '1'  and f.status='1' and a.availability='1' and c.availability='1' and \r\n"
			+ "			ac.availability='1' and fud.availability='1'  and u.availability='1'")
	public List<FileUpload> getAllUploadedFiles_for_user();

	@Select("select DISTINCT  f.original_file_name as file_name, f.file_type, f.upload_date from files f \r\n"
			+ "				where f.id=#{id}  and f.availability = '1' ")
	public FileUpload getfilebyid(long id);

	@Update("update files set original_file_name =#{file_name}, file_type=#{file_type},upload_date=#{upload_date} where id=#{id}")
	public boolean updateFile(@Param("id") Long id, @Param("file_name") String file_name,
			@Param("file_type") String file_type, @Param("upload_date") String upload_date);

	@Select("select path from files where id=#{id}")
	public String downloadFiles(Long id);

	@Delete("update files set availability=0 where id=#{id}")
	public boolean deleteFile(long id);
	// @Update("update files set beginning_balance_ats = #{beginning_balance_ats}, "
	// +
	// "ending_balance_ats = #{ending_balance_ats}, " +
	// "total_number_of_credit_ats = #{total_number_of_credit_ats}, " +
	// "total_number_of_debit_ats = #{total_number_of_debit_ats}, " +
	// "total_credit_ats = #{total_credit_ats}, " +
	// "total_debit_ats = #{total_debit_ats}, " +
	// "business_date = #{business_date}, " +
	// "account_number = #{account_number} where id = #{file_id} ")
	// public void updateAdditionalFileInfo(
	// @Param("beginning_balance_ats") Float beginningBallance,
	// @Param("ending_balance_ats") Float endingBallance,
	// @Param("total_number_of_credit_ats") int totalCredit,
	// @Param("total_number_of_debit_ats") int totalDebit,
	// @Param("total_credit_ats") Float totalCreditAmount,
	// @Param("total_debit_ats") Float totalDebitAmount,
	// @Param("business_date") String businessDate,
	// @Param("account_number") String accountNumber,
	// @Param("file_id") Long file_id
	// );

	@Update("update files set beginning_balance_ifb = #{beginning_balance}, " +
			
			
			"ending_balance_ifb = #{ending_balance}, " +
			
			"total_number_of_credit_ifb = #{total_number_of_credit}, " +
			
			"total_number_of_debit_ifb = #{total_number_of_debit}, " +
			
			"total_credit_ifb = #{total_credit}, " +
			
			"total_debit_ifb = #{total_debit} " +
			" where id = #{file_id} ")
	public void updateAdditionalFileInfoPayable(
			@Param("beginning_balance") Double double1,
			
			@Param("ending_balance") Double double3,
			
			@Param("total_number_of_credit") int totalCredit,
			
			@Param("total_number_of_debit") int totalDebit,
			
			@Param("total_credit") Double double5,
		
			@Param("total_debit") Double double7,
		
			@Param("file_id") Long file_id);
	
	@Select("select distinct f.id, f.original_file_name as file_name, f.file_type, f.upload_date, \r\n"
			+ " u.firstname as first_name,u.email, u.middlename as middle_name, u.lastname as last_name, fud.left_right as type\r\n"
			+ "from files f, users u, file_user_date fud where (f.type = 'ISSUE_QBS' or f.type = 'ISSUE_') and  fud.file_id = f.id and fud.user_id = u.id  \r\n"
			+ "and f.availability = '1'  and f.status='1'  \r\n"
			+ "and fud.availability='1'  and u.availability='1'")
	public List<FileUpload> getAllUploadedFiles_for_issue();

	@Select("select * from files where status = 1 and availability = 1 and type = 'Payable'")
	public List<FileUpload> getAllPayableFiles();
	
	@Select("select * from files where status = 1 and availability = 1 and type = 'Receivable'")
	public List<FileUpload> getAllReceivableFiles();

	// @Select("select top 1 '1' from user_workspace where workspace_id =
	// #{workspace_id} and status = 1 and availability = 1")
	// public Optional<String> check_IfWorkspaceIsFree(@Param("workspace_id") Long
	// workspace_id);

}
