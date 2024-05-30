package com.example.demo.user.remark;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface remarkMapper {
	@Select(" select u.email, u.firstname, u.middlename, u.lastname, r.id, r.title , r.description, \r\n"
			+ " r.created_date from users u,remark r,users_remark ur,currency_request_remark crr\r\n"
			+ "	where( ur.receiver_id=#{userId} or ur.sender_id=#{userId}) and ur.remark_id=r.id  \r\n"
			+ "	and crr.remark_id=r.id	and (ur.sender_id=u.id or ur.receiver_id=u.id) and ur.sender_id=u.id;")
	public List<remarkModel> getAllRmarkeSentFromApprover(@Param("userId") long userId);

	@Select("select count( r.id) \r\n" + "	from users u,remark r,users_remark ur,currency_request_remark crr\r\n"
			+ "	where( ur.receiver_id=#{userId} or ur.sender_id=#{userId}) and ur.remark_id=r.id  \r\n"
			+ "	and crr.remark_id=r.id	and (ur.sender_id=u.id or ur.receiver_id=u.id) and ur.sender_id=u.id;")
	public long numberofCurrencyRemarks(long userId);

	@Select(" select u.email, u.firstname, u.middlename, u.lastname, r.id, r.title , r.description, \r\n"
			+ "	r.created_date from users u,remark r,users_remark ur,account_request_remark arr\r\n"
			+ "	where ( ur.receiver_id=#{userId} or ur.sender_id=#{userId}) and ur.remark_id=r.id  \r\n"
			+ "	and arr.remark_id=r.id	and (ur.sender_id=u.id or ur.receiver_id=u.id) and ur.sender_id=u.id ;")
	public List<remarkModel> getAllAccountRemarks(@Param("userId") long userId);

	@Select("insert into remark(title, description, created_date) OUTPUT Inserted.id "
			+ "values(#{title}, #{description}, #{created_date});")
	public Long ReplayCurrencyRemark(remarkModel remarkModel);

	@Insert("insert into users_remark(sender_id, receiver_id,remark_id)"
			+ "values(#{sender_id}, #{receiver_id},#{remark_id});")
	public void Users_remark(@Param("sender_id") long sender_id, @Param("receiver_id") long receiver_id,
			@Param("remark_id") long remark_id);

	@Insert("insert into currency_request_remark(currency_request_id, remark_id)"
			+ "values(#{currency_request_id}, #{remark_id});")
	public void insertintoCurencyRequestRemark(@Param("currency_request_id") long currency_request_id,
			@Param("remark_id") long remark_id);

	@Insert("insert into account_request_remark(account_request_id, remark_id)"
			+ "values(#{account_request_id}, #{remark_id});")
	public void insertintoAccountRequestRemark(@Param("account_request_id") long account_request_id,
			@Param("remark_id") long remark_id);

	@Select("select cr.id from users u inner join user_currency_request ucr on u.id=ucr.user_id   inner join  currency_request cr on cr.id=ucr.currency_request_id\r\n"
			+ ",currency_request_remark crr where crr.currency_request_id=cr.id  and crr.remark_id=#{remarkId};")
	public long getuserIdByREmarkId(long remarkId);

	@Select("select ar.id from users u inner join user_account_request uar on u.id=uar.user_id   inner join  account_request ar on ar.id=uar.account_request_id\r\n"
			+ ",account_request_remark arr where arr.account_request_id=ar.id  and arr.remark_id=#{remarkId};")
	public long getrequestIdByREmarkId(long remarkId);

//	@Select("select u.email from users u where u.id=#{uerId};")
//	public String username(long uerId);

	@Delete("delete currency_request_remark  where  remark_id=#{id}")
	public void deleteCurrencyRemark(Long id);

	@Delete("delete account_request_remark  where  remark_id=#{id}")
	public void deleteaccountyRemark(Long id);

	@Select("select * from remark  where  id=#{remarkId}")
	public remarkModel getRemarkById(Long remarkId);

	@Update("update remark set title = #{title}, description = #{description}, created_date = #{created_date} "
			+ "where id=#{id};")
	public void updateremarkbyId(remarkModel remarkModel);

	@Select("select ur.sender_id from users_remark ur where ur.remark_id=#{remarkId}")
	public long getRemarkSenderId(Long remarkId);

	@Select("select count( r.id)  \r\n" + "from users u,remark r,users_remark ur,account_request_remark arr\r\n"
			+ "	where ( ur.receiver_id=#{userId} or ur.sender_id=#{userId}) and ur.remark_id=r.id  \r\n"
			+ "	and arr.remark_id=r.id	and (ur.sender_id=u.id or ur.receiver_id=u.id) and ur.sender_id=u.id ;")
	public long numberofaccountRemarks(@Param("userId") long userId);
	
	//@Select("select * from remark r where id=#{remarkId} and availability=1 and status=1")
	@Select("insert into remark_replay(remark_id, replay_remark_id, availability,status) OUTPUT Inserted.id "
			+ "values(#{remarkId}, #{replay_remark_id}, 1,1)")
	public long remarkDetail( @Param("remarkId")long remarkId,@Param("replay_remark_id")long replay_remark_id);

}
