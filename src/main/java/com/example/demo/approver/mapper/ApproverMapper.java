package com.example.demo.approver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.approver.model.AccountRequest;
import com.example.demo.approver.model.CurrencyRequest;
import com.example.demo.approver.model.Remark;
@Mapper
public interface ApproverMapper {


	
	@Select("select distinct c.id, u.firstname, u.middlename,  c.name, c.code, c.type, c.created_date, c.description, c.request_status from currency_request c, "
			+ "user_currency_request uc, users u where (c.id= uc.currency_request_id and u.id=uc.user_id) and request_status=0")
	public List<CurrencyRequest> getAllCurrencyRequests();
	
	@Select("select u.firstname, u.middlename, c.id, c.name, c.code, c.type, c.created_date, c.description, c.request_status from currency_request c, "
			+ "user_currency_request uc, users u where c.id= uc.currency_request_id and u.id=uc.user_id and request_status=1")
	public List<CurrencyRequest> getAllApprovedCurrencyRequests();
	
	@Select("select u.firstname, u.middlename, ar.id, ar.name, ar.code, ar.type, ar.created_date, ar.description, ar.request_status, c.name as currency from account_request ar, "
			+ "user_account_request ua, users u, currency c, account_request_currency arc where arc.account_request_id=ar.id and arc.currency_id=c.id and ar.id= ua.account_request_id and u.id=ua.user_id and request_status=1 and ar.availability=1 and c.availability=1")
	public List<AccountRequest> getAllApprovedAccountRequests();
	
	@Select("select u.firstname, u.middlename, c.id, c.name, c.code, c.type, c.created_date, c.description, c.request_status from currency_request c, "
			+ "user_currency_request uc, users u where c.id= uc.currency_request_id and u.id=uc.user_id and request_status=2")
	public List<CurrencyRequest> getAllRejectedCurrencyRequests();
	
	@Select("select u.firstname, u.middlename, ar.id, ar.name, ar.code, ar.type, ar.created_date, ar.description, ar.request_status, c.name as currency from account_request ar, "
			+ "user_account_request ua, users u, currency c, account_request_currency arc where arc.account_request_id=ar.id and arc.currency_id=c.id and ar.id= ua.account_request_id and u.id=ua.user_id and request_status=2")
	public List<AccountRequest> getAllRejectedAccountRequests();
	
	@Select("select u.firstname, u.middlename, ar.id, ar.name, ar.code, ar.type, ar.created_date, ar.description, ar.request_status, c.name as currency from account_request ar, "
			+ "user_account_request ua, users u, currency c, account_request_currency arc where arc.account_request_id=ar.id and arc.currency_id=c.id and ar.id= ua.account_request_id and u.id=ua.user_id and request_status=0")
	public List<AccountRequest> getAllAccountRequests();
	
	@Update("update currency_request set request_status = '1' where id = #{request_id} and request_status=0")
	public void approve_currency_request(String request_id);
	
	@Update("update account_request set request_status = '1' where id = #{request_id} and request_status=0")
	public void approve_account_request(String request_id);
	
	@Update("update currency_request set request_status = '2' where id = #{request_id} and request_status=0")
	public void reject_currency_request(String request_id);
	
	@Update("update account_request set request_status = '2' where id = #{request_id} and request_status=0")
	public void reject_account_request(String request_id);
   
	@Select("INSERT INTO currency (name, code, description, created_date) OUTPUT Inserted.id SELECT name, code, description, created_date FROM currency_request WHERE id=#{request_id}")
	public Long insertCurrency(String request_id);
	
	@Select("INSERT INTO account (name, code, description, created_date) OUTPUT Inserted.id SELECT name, code, description, created_date FROM account_request WHERE id=#{request_id}")
	public Long insertAccount(String request_id);
	
	@Select("insert into remark(title, description, created_date) OUTPUT Inserted.id "
			+ "values(#{title}, #{description}, #{created_date});")
	public Long send_remark(Remark remarkk);
	
	@Insert("INSERT INTO currency_request_remark(currency_request_id, remark_id)  values(#{request_id},#{remark_id})")
	public void addcurrencyRequestRemark(@Param("request_id")Long request_id,@Param("remark_id")Long remark_id);
	
	@Insert("INSERT INTO currency_request_remark(currency_request_id, remark_id)  values(#{request_id},#{remarkk_id})")
	public void insertCurrencyRequestRemark(@Param("request_id")Long request_id,@Param("remarkk_id") Long remarkk_id);
	
	@Insert("INSERT INTO account_request_remark(account_request_id, remark_id)  values(#{request_id},#{remark_id})")
	public void addaccountRequestRemark(@Param("request_id")Long request_id,@Param("remark_id")Long remark_id);
	
	@Insert("INSERT INTO account_request_remark(account_request_id, remark_id)  values(#{request_id},#{remarkk_id})")
	public void insertAccountRequestRemark(@Param("request_id")Long request_id,@Param("remarkk_id")Long remarkk_id);
	
	@Insert("INSERT INTO users_remark(remark_id, sender_id,receiver_id)  values(#{remark_id},#{sender_id},#{reciver_id})")
	public void addUserRemark(@Param("remark_id")Long remark_id,@Param("sender_id")Long sender_id,@Param("reciver_id")Long reciver_id);
	
	@Insert("INSERT INTO currency_currency_request(currency_request_id, currency_id)  values(#{request_id},#{cur_id})")
	public void insertCurrencyCurrencyRequest(@Param("request_id")String request_id,@Param("cur_id")Long cur_id);
	
	@Insert("INSERT INTO account_account_request(account_request_id, account_id)  values(#{request_id},#{acc_id})")
	public void insertAccountAccountRequest(@Param("request_id")String request_id,@Param("acc_id")Long acc_id);
	
	@Select("select id from users where email = #{email}")
	public Long getUserIdByEmail(@Param("email") String email);
	
	@Select("update account set account.name=(select ar.name from account_request ar where \r\n"
			+ "ar.id =#{request_id}), account.code = (select ar.code from account_request ar  where \r\n"
			+ "ar.id = #{request_id}), account.created_date = (select ar.created_date from account_request ar  where \r\n"
			+ "ar.id = #{request_id}) , account.description= (select ar.description from account_request ar  where \r\n"
			+ "ar.id = #{request_id}) where account.id=#{account_id}")
	public void updateAccount(@Param("request_id") String request_id,@Param("account_id") Long account_id);

	@Select("select account_id from account_account_request where account_request_id = #{request_id}")
	public Long getAccountId(@Param("request_id") String request_id);
	
	@Select("update currency set currency.name=(select cr.name from currency_request cr where \r\n"
			+ "cr.id =#{request_id}),currency.code = (select cr.code from currency_request cr  where \r\n"
			+ "cr.id = #{request_id}),currency.created_date = (select cr.created_date from currency_request cr  where \r\n"
			+ "cr.id = #{request_id}) ,currency.description= (select cr.description from currency_request cr  where \r\n"
			+ "cr.id = #{request_id}) where currency.id=#{currency_id}")
	public void updateCurrency(@Param("request_id") String request_id,@Param("currency_id") Long currency_id);
	
	@Select("select currency_id from currency_currency_request where currency_request_id = #{request_id}")
	public Long getCurrencyId(@Param("request_id") String request_id);
	
	@Select("select user_id from user_currency_request where currency_request_id=#{request_id}")
	public Long getReciverId(Long request_id);
	@Select("select user_id from user_account_request where account_request_id=#{request_id}")
	public Long getReciversId(Long request_id);
	
	@Select("select sender_id from users_remark where remark_id=#{remark_id}")
	public Long getReciverssId(Long remark_id);
	
	@Select("select currency_request_id from currency_request_remark where remark_id=#{remark_id}")
	public Long getCurrencyRequestId(Long remark_id);
	
	@Select("select account_request_id from account_request_remark where remark_id=#{remark_id}")
	public Long getAccountRequestId(Long remark_id);
	
    @Select("select r.id, r.title, r.description, r.created_date, u.firstname from remark r , users_remark ur, account_request_remark arr, users u where ur.receiver_id=#{id} and (ur.receiver_id=u.id or ur.sender_id=u.id) and ur.remark_id=r.id and arr.remark_id=r.id and ur.sender_id=u.id")
	public List<Remark> getAccountRemarks(Long reciver_id);
    
    @Select("select r.id, r.title, r.description, r.created_date, u.firstname from remark r , users_remark ur, currency_request_remark crr, users u where ur.receiver_id=#{id} and (ur.receiver_id=u.id or ur.sender_id=u.id) and ur.remark_id=r.id and crr.remark_id=r.id and ur.sender_id=u.id")
	public List<Remark> getCurrencyRemarks(Long reciver_id);
    
    @Select("select currency_id from account_request_currency where account_request_id=#{request_id}")
	public Long getCurrencyIdByRequestId(String request_id);
    
    @Update("update account_currency set currency_id=#{currency_id} where account_id=#{account_id}")
	public void updateCurrencyId(@Param("account_id") Long account_id, @Param("currency_id") Long currency_id);
    
    @Insert("insert into account_currency(account_id, currency_id, availability, status) values(#{acc_id},#{currency_id}, 1, 1)")
	public void insertAccountCurrency(@Param("acc_id") Long acc_id,@Param("currency_id") Long currency_id);
    
    @Select("select CONCAT(u.firstname, ' ',u.middlename) as name from users u, user_currency_request ucr where u.id=ucr.user_id and ucr.currency_request_id=#{request_id}")
	public String getUserNameCurrencyRequest(long request_id);
    
    @Select("select name from currency_request where id=#{request_id}")
    public String getCurrencyName(long request_id);

    @Select("select CONCAT(u.firstname, ' ',u.middlename) as name from users u, user_account_request uar where u.id=uar.user_id and uar.account_request_id=#{request_id}")
	public String getUserNameAccountRequest(long request_id);
    
    @Select("select name from account_request where id=#{request_id}")
    public String getAccountName(long request_id);
} 

      
