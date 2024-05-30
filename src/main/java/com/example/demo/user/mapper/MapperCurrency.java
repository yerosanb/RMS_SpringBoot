package com.example.demo.user.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.user.model.Account;
import com.example.demo.user.model.Currency;
import com.example.demo.user.model.Notification;
@Mapper
public interface MapperCurrency {
	@Select("insert into currency_request(name, code, description, created_date, type, status, availability, request_status)"
			+ "OUTPUT Inserted.id " 
			+ " values (#{name}, #{code}, #{description}, #{created_date}, 'new', 1, 1, 0)" ) 
	public Long currencyRequest(Currency currency);

	@Select("select * from currency where availability=1")
	public List<Currency> getAll();
 	
//	@Select("select currency_request_id from currency_currency_request where id=#{id}")
//	public Long getCurrencyRequestId(Long id); 
//	
//	@Update("UPDATE currency_request SET name = #{name}, code = #{code}, created_date = #{created_date}, description = #{description}, type = 'update', request_status='0', status = '1', availability = '1' where id = #{req_id}")
//	public Boolean updateApprovedRequest(Currency currency, @Param("req_id") Long req_id);

	@Insert("insert into user_currency_request (currency_request_id, user_id, status, availability) values (#{currency_request_id},"
			+ " #{user_id}, 1, 1)")
	public void addUserCurrency(@Param("currency_request_id") Long currency_request_id, @Param("user_id") Long user_id);
	

	@Select("insert into notification(title, description, date, view_status, status, availability)"
			+ "OUTPUT Inserted.id " 
			+ " values (#{title}, #{description}, #{date}, #{viewStatus}, 1, 1)" ) 
	public Long addCurrencyNotification(Notification notification);

	
	@Insert("insert into user_notification (user_id, notification_id, status, availabilty) values ( #{user_id},"
			+ " #{notification_id}, 1, 1)")
	public void addUserNotification(@Param("user_id") Long user_id, @Param("notification_id") Long notification_id);
	
	@Insert("insert into currency_currency_request (currency_id, currency_request_id, status, availability) values ( #{currency_id},"
			+ " #{currency_request_id}, 1, 1)")
	public void addCurrencyCurrencyRequest(@Param("currency_id") Long currency_id, @Param("currency_request_id") Long currency_request_id);
	
	
	@Update("update cr set cr.name = #{name}, cr.code = #{code},\r\n"
			+ "cr.created_date = #{created_date}, cr.description = #{description}, cr.type = 'update', cr.status = '1', cr.request_status='0',\r\n"
			+ "cr.availability = '1' from currency_request cr , currency_currency_request ccr,\r\n"
			+ " currency c  where cr.id = ccr.currency_request_id and c.id= ccr.currency_id and cr.id=(select currency_request_id from currency_currency_request ccr  where\r\n"
			+ " ccr.currency_id=#{id})")
    public Boolean updateApprovedRequest(Currency currency);
	
//   @Insert("insert into currency c (name, code, description, created_date, status, availibility) "
//	   		+ " values (#{name}, #{code}, #{description}, #{created_date}, #{status}, #{availibility}) where currency_request r.status= 1"
//	   		+ " && c.id = r.id")
//   public void createCurrency(Currency currency);
//   
//   @Update("update currency_request set name = #{name}, code = #{code}, created_date = #{created_date}, description = #{description}"
//   		+ " type = update, status = 1, "
//   		+ "availability = 1 where id = #{id}")
//   public void updateRequest(Currency currency);
//   

	@Delete("delete currency where id=#{id}")
	public void deleteCurrency(Long id);
	
	@Update("UPDATE currency SET request_status = '3' WHERE id=#{id}")
	public void deleteFromAccountCurrency(Long id);
	
	@Update("UPDATE currency_request SET request_status = '3' WHERE id=#{id}")
	public void deleteCurrencyRequest(Long id);

	@Select("select * from currency where id=#{id} and availability=1")
	public Account findById(Long id);

	@Select("SELECT * FROM currency_request WHERE request_status IN (0, 1,2) and availability=1")	
	public List<Currency> getAllCurrencies();

	@Select("select * from currency where availability=1")
	public List<Currency> getApprovedCurrencies();

	@Select("select * from currency_request where request_status='0' and availability=1")
	public List<Currency> getPendingCurrencies();

	@Select("select * from currency_request where request_status='2' and availability=1")
	public List<Currency> getRejectedCurrencies();

	@Update("update currency_request set name = #{name}, code = #{code}, created_date = #{created_date}, description = #{description}, type = 'update', status = '0', availability = '1' where id = #{id}")
	public Boolean updateRequest(Currency currency);

//	@Update("update currency_request set name = #{name} where id = #{id}")
//	@Update("UPDATE currency_request "
//			+ "SET name = 'Alfred Schmidt' "
//			+ "WHERE id = 107;")
	@Update("UPDATE currency_request "
			+ "SET name = 'Alfred Schmidt' "
			+ "WHERE id = #{id};")
	public Boolean updateRequest_001(@Param("name") String name, @Param("id") Long id);

	@Select("select * from currency_request where id=#{id} and availability=1")
	public Currency findByIdCurrency(Long id);
	
	@Select("select top 1 '1' from currency_request where code = #{code} and availability=1" )
	public Optional<String> check_CurrencyCodeExist(@Param("code")String curr);
	@Select("select top 1 '1' from roles where name = #{name} and availability=1")
	public Optional<String> check_roleExist(@Param("name")String name);



}
