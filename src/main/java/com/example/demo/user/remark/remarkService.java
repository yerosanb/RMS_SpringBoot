package com.example.demo.user.remark;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ExceptionsList;
import com.example.demo.user.notification.notificationModel;
import com.example.demo.user.notification.notificationService;
import com.example.demo.utils.Utils;

@Service
public class remarkService {
	@Autowired
	notificationService notificationService;
	@Autowired
	private Utils util;
	@Autowired
	private remarkMapper remarkMapper;
	notificationModel notificationModel = new notificationModel();

	public List<remarkModel> getUserRemarks(HttpServletRequest request) {

		try {
			if (util.isPermitted(request, "User", "view_currency_remark")) {
//				String email = remarkMapper.username(util.getUserId(request));
				// String email = util.getUserEmail(request);
				List<remarkModel> remarkModel = remarkMapper.getAllRmarkeSentFromApprover(util.getUserId(request));
				util.registerActivity(request, "View Remarks", "-");
				System.out.println(remarkModel);
				return remarkModel;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<remarkModel> getAccountRemarks(HttpServletRequest request) {

		try {
			if (util.isPermitted(request, "User", "view_account_remark")) {
				List<remarkModel> remarkModel = remarkMapper.getAllAccountRemarks(util.getUserId(request));
				util.registerActivity(request, "View Remarks", "-");
				System.out.println(remarkModel);
				return remarkModel;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public boolean replayAccountRemark(remarkModel remarkModel, HttpServletRequest request) {

		try {
			if (util.isPermitted(request, "User", "replay_account_remark")) {
				remarkModel.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
				long remarkId = remarkMapper.ReplayCurrencyRemark(remarkModel);
				long receiverId = remarkMapper.getRemarkSenderId(remarkModel.getRemarkId());
				long notificationId = notificationService.notification(notificationModel);
				notificationService.userNotification(util.getUserId(request), notificationId);
				notificationService.notificationOwner(notificationId, remarkId);
				notificationService.notificationReceiver(notificationId, receiverId);
				util.registerActivity(request, "Replay account Remark", "-");
				remarkMapper.Users_remark(util.getUserId(request), receiverId, remarkId);
				long accountRequestId = remarkMapper.getrequestIdByREmarkId(remarkModel.getRemarkId());
				remarkMapper.insertintoAccountRequestRemark(accountRequestId, remarkId);

				return true;
			} else
				return false;

		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public long totalcurrencyRemark(HttpServletRequest request) {
		return remarkMapper.numberofCurrencyRemarks(util.getUserId(request));
	}

	public long totalaccountRemark(HttpServletRequest request) {
		return remarkMapper.numberofaccountRemarks(util.getUserId(request));
	}

	public boolean replaycurrencyRemark(remarkModel remarkModel, HttpServletRequest request) {

		try {
			if (util.isPermitted(request, "User", "replay_currency_remark")) {
				remarkModel.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));

				long remarkId = remarkMapper.ReplayCurrencyRemark(remarkModel);
				System.out.println("idddddddddddddd"+ remarkModel.getRemarkId());
				util.registerActivity(request, "Replay Currency Remark", "-");
				long receiverId = remarkMapper.getRemarkSenderId(remarkModel.getRemarkId());
//				remarkMapper.remarkDetail(remarkModel.getRemarkId(),remarkId);

				long notificationId = notificationService.notification(notificationModel);
				notificationService.userNotification(util.getUserId(request), notificationId);
				notificationService.notificationOwner(notificationId, remarkId);
				notificationService.notificationReceiver(notificationId, receiverId);
				remarkMapper.Users_remark(util.getUserId(request), receiverId, remarkId);
				long currencyRequestId = remarkMapper.getuserIdByREmarkId(remarkModel.getRemarkId());
				remarkMapper.insertintoCurencyRequestRemark(currencyRequestId, remarkId);

				return true;
			} else
				return false;

		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public void deleteCurrencyRemark(Long id) {
		remarkMapper.deleteCurrencyRemark(id);

	}

	public void deleteaccountRemark(Long id) {
		remarkMapper.deleteaccountyRemark(id);

	}

	public String getEmail(HttpServletRequest request) {
		return util.getUserEmail(request);
	}

	public remarkModel getRemarkyId(long id) {

		remarkModel remarkModel = remarkMapper.getRemarkById(id);
		System.out.println(remarkModel);
		return remarkModel;

	}
	public boolean updateremarkById(long id, remarkModel remarkModel) {
		remarkModel.setId(id);
		remarkModel.setTitle(remarkModel.getTitle());
		remarkModel.setDescription(remarkModel.getDescription());
		remarkModel.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		remarkMapper.updateremarkbyId(remarkModel);
return true;
	}

}