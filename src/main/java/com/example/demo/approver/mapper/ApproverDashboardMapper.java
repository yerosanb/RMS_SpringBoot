package com.example.demo.approver.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.approver.model.Remark;
import com.example.demo.user.model.AtsBeginningEnding;
import com.example.demo.user.model.CoreBeginningEnding;

@Mapper
public interface ApproverDashboardMapper {

	@Select("select count(*) from account where status = 1 and availability = 1")
	int getTotalNumberOfAccounts();

	@Select("select count(*) from currency where status = 1 and availability = 1")
	int getTotalNumberOfCurrencies();

	@Select("select count(*) from currency where status = 1 and availability = 1")
	int getNumberOfApprovedCurrency();

	@Select("select count(*) from currency_request where status = 1 and availability = 1  and request_status=0")
	int getNumberOfPendingCurrency();

	@Select("select count(*) from currency_request where status = 1 and availability = 1 and request_status=2")
	int getNumberOfRejectedCurrency();

	@Select("select count(*) from account where status = 1 and availability = 1")
	int getNumberOfApprovedAccount();

	@Select("select count(*) from account_request where status = 1 and availability = 1 and request_status=0")
	int getNumberOfPendingAccount();

	@Select("select count(*) from account_request where status = 1 and availability = 1 and  request_status=2")
	int getNumberOfRejectedAccount();

	@Select("select count(*)  from remark r , users_remark ur, currency_request_remark crr, users u where ur.receiver_id=#{reciver_id} and (ur.receiver_id=u.id or ur.sender_id=u.id) and ur.remark_id=r.id and crr.remark_id=r.id and ur.sender_id=u.id")
	int getNumberOfCurrencyRemarks(Long reciver_id);

	@Select("select count(*) from remark r , users_remark ur, account_request_remark arr, users u where ur.receiver_id=#{receiver_id} and (ur.receiver_id=u.id or ur.sender_id=u.id) and ur.remark_id=r.id and arr.remark_id=r.id and ur.sender_id=u.id")
	int getNumberOfAccountRemarks(long receiver_id);




}
