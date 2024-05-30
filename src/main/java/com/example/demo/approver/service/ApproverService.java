package com.example.demo.approver.service;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import com.example.demo.Exception.ExceptionsList;
import com.example.demo.approver.mapper.ApproverMapper;
import com.example.demo.approver.model.AccountRequest;
import com.example.demo.approver.model.CurrencyRequest;
import com.example.demo.approver.model.Remark;
import com.example.demo.jwt.JwtHelper;
import com.example.demo.utils.Utils;
@Service
public class ApproverService {
	
@Autowired
private JwtHelper jwtHelper;

@Autowired
private Utils util;

@Autowired
private ApproverMapper approverMapper;

@Value("${jwt.access.token.cookie.name}")
private String cookieName;

	public List<CurrencyRequest> getAllCurrencyRequests(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "Approver", "view_currency_requests")) {
				
				List<CurrencyRequest> currencyRequest = approverMapper.getAllCurrencyRequests();
				util.registerActivity(request, "View currency Request", "Views all pending currency requests");
				return currencyRequest;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public List<CurrencyRequest> getAllApprovedCurrencyRequests(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "Approver", "view_approved_currency_requests")) {
				
				List<CurrencyRequest> currencyRequest = approverMapper.getAllApprovedCurrencyRequests();
				util.registerActivity(request, "View approved currency Request", "views all approved currency requests");
				return currencyRequest;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public List<AccountRequest> getAllApprovedAccountRequests(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "Approver", "view_approved_account_requests")) {
				
				List<AccountRequest> accountRequest = approverMapper.getAllApprovedAccountRequests();
				util.registerActivity(request, "View approved account Request", "views all approved account requests");
				return accountRequest;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public List<CurrencyRequest> getAllRejectedCurrencyRequests(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "Approver", "view_rejected_currency_requests")) {
				
				List<CurrencyRequest> currencyRequest = approverMapper.getAllRejectedCurrencyRequests();
				util.registerActivity(request, "View rejected currency Request", "views all rejected currency requests");
				return currencyRequest;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public List<AccountRequest> getAllRejectedAccountRequests(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "Approver", "view_rejected_account_requests")) {
				
				List<AccountRequest> accountRequest = approverMapper.getAllRejectedAccountRequests();
				util.registerActivity(request, "View rejected account Request", "views all rejected account requests");
				return accountRequest;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public List<AccountRequest> getAllAccountRequests(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "Approver", "view_account_requests")) {
				
				List<AccountRequest> accountRequest = approverMapper.getAllAccountRequests();
				util.registerActivity(request, "View currency Request", "views all pending account requests");
				return accountRequest;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public Boolean approve_currency_request(String request_id, HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "Approver", "approve_currency_request")) {
				System.out.println("Yes user has permission.");
				String currencyName=approverMapper.getCurrencyName(Long.parseLong(request_id));
				Long currency_id=approverMapper.getCurrencyId(request_id);
				   if (currency_id != null) {
					   approverMapper.updateCurrency(request_id, currency_id);
					   System.out.println("Currency id "+currency_id+" successfully updated");
				   }
				   
				else {
				Long cur_id = approverMapper.insertCurrency(request_id);
				System.out.println("Currency id:>>>>>>>> " + cur_id);
				approverMapper.insertCurrencyCurrencyRequest(request_id,cur_id);
			    }
				approverMapper.approve_currency_request(request_id);
				util.registerActivity(request, "Approve currency request", "approve currency request of "+approverMapper.getUserNameCurrencyRequest(Long.parseLong(request_id))+" with currency name "+currencyName);
				return true;
			} else {
				System.out.println("No user does not have permission.");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public Boolean approve_account_request(String request_id, HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "Approver", "approve_account_request")) {
				System.out.println("Yes user has permission.");
				Long account_id=approverMapper.getAccountId(request_id);
				Long currency_id=approverMapper.getCurrencyIdByRequestId(request_id);
				String accountName=approverMapper.getAccountName(Long.parseLong(request_id));
			   if (account_id != null) {
				   approverMapper.updateAccount(request_id, account_id);
				   System.out.println("Account id "+account_id+" successfully updated");
				   approverMapper.updateCurrencyId(account_id,currency_id);
			   }
			   else {
				Long acc_id = approverMapper.insertAccount(request_id);
				System.out.println("Account id: " + acc_id);
				approverMapper.insertAccountAccountRequest(request_id,acc_id);
				approverMapper.insertAccountCurrency(acc_id,currency_id);
			   }
			   approverMapper.approve_account_request(request_id);
				util.registerActivity(request, "Approve account request", "approve account request of "+approverMapper.getUserNameAccountRequest(Long.parseLong(request_id))+" with account name "+accountName);
				return true;
			} else {
				System.out.println("No user does not have permission.");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	public Boolean reject_currency_request(String request_id, HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "Approver", "reject_currency_request")) {
				System.out.println("Yes user has permission.");
				String currencyName=approverMapper.getCurrencyName(Long.parseLong(request_id));
				approverMapper.reject_currency_request(request_id);
				util.registerActivity(request, "reject currency request", "reject currency request of "+approverMapper.getUserNameCurrencyRequest(Long.parseLong(request_id))+" with currency name "+currencyName);
				return true;
			} else {
				System.out.println("No user does not have permission.");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	
	public Boolean reject_account_request(String request_id, HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "Approver", "reject_account_request")) {
				System.out.println("Yes user has permission.");
				String accountName=approverMapper.getAccountName(Long.parseLong(request_id));
				approverMapper.reject_account_request(request_id);
				util.registerActivity(request, "reject account request",  "reject account request of "+approverMapper.getUserNameAccountRequest(Long.parseLong(request_id))+" with account name "+accountName);
				return true;
			} else {
				System.out.println("No user does not have permission.");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	public boolean currency_remark( HttpServletRequest request,long request_id, Remark remark) {
		String accessToken = getToken(request);
		Long sender_id = approverMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(accessToken));
		Long reciver_id=approverMapper.getReciverId(request_id);
		

		try {
			try {
				if (util.isPermitted(request, "Approver", "currency_remark")) {
					Remark remarkk = new Remark();
					remarkk.setTitle(remark.getTitle());
					remarkk.setDescription(remark.getDescription());
					remarkk.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
					
					System.out.println("user id........>"+sender_id);
                    System.out.println("request id........>"+request_id);       
					long remark_id=approverMapper.send_remark(remarkk);
					approverMapper.addcurrencyRequestRemark(request_id,remark_id);
					approverMapper.addUserRemark(remark_id,sender_id,reciver_id);
					util.registerActivity(request, "send currency remark ", "send a currency remark");
					return true;
				} else {
					System.out.println("No user does not have permission.");
					return false;
				}
			} catch (Exception e) {
				throw new ExceptionsList(e);
			}
		} catch (Exception ex) {
			throw new ExceptionsList(ex);
		}
	}
	
	public boolean account_remark( HttpServletRequest request,long request_id, Remark remark) {
		String accessToken = getToken(request);
		Long sender_id = approverMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(accessToken));
		Long reciver_id=approverMapper.getReciversId(request_id);
		
		try {
			try {
				if (util.isPermitted(request, "Approver", "account_remark")) {
					Remark remarkk = new Remark();
					remarkk.setTitle(remark.getTitle());
					remarkk.setDescription(remark.getDescription());
					remarkk.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
					
					System.out.println("user id........>"+sender_id);
                    System.out.println("request id........>"+request_id);
					long remark_id=approverMapper.send_remark(remarkk);
					approverMapper.addaccountRequestRemark(request_id,remark_id);
					approverMapper.addUserRemark(remark_id, sender_id, reciver_id);
					util.registerActivity(request, "send account remark", "send remark for account request");
					return true;
				} else {
					System.out.println("No user does not have permission.");
					return false;
				}
			} catch (Exception e) {
				throw new ExceptionsList(e);
			}
		} catch (Exception ex) {
			throw new ExceptionsList(ex);
		}
	}
	
	public boolean replay_remark_account( HttpServletRequest request,long remark_id, Remark remark) {
		String accessToken = getToken(request);
		Long sender_id = approverMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(accessToken));
		Long reciver_id=approverMapper.getReciverssId(remark_id);
		Long request_id=approverMapper.getAccountRequestId(remark_id);
		try {
			try {
				if (util.isPermitted(request, "Approver", "replay_remark_account")) {
					Remark remarkk = new Remark();
					remarkk.setTitle(remark.getTitle());
					remarkk.setDescription(remark.getDescription());
					remarkk.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
					
					System.out.println("sender id........>"+sender_id);
                    System.out.println("reciver id........>"+reciver_id);
                    System.out.println("remark id........>"+remark_id);
					long remarkk_id=approverMapper.send_remark(remarkk);
					approverMapper.addUserRemark(remarkk_id, sender_id, reciver_id);
					approverMapper.insertAccountRequestRemark(request_id,remarkk_id);
					util.registerActivity(request, "replay remark", "replay for account remark");
					return true;
				} else {
					System.out.println("No user does not have permission.");
					return false;
				}
			} catch (Exception e) {
				throw new ExceptionsList(e);
			}
		} catch (Exception ex) {
			throw new ExceptionsList(ex);
		}
	}
	
	public boolean replay_remark_currency( HttpServletRequest request,long remark_id, Remark remark) {
		String accessToken = getToken(request);
		Long sender_id = approverMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(accessToken));
		Long reciver_id=approverMapper.getReciverssId(remark_id);
		Long request_id=approverMapper.getCurrencyRequestId(remark_id);
		try {
			try {
				if (util.isPermitted(request, "Approver", "replay_remark_currency")) {
					Remark remarkk = new Remark();
					remarkk.setTitle(remark.getTitle());
					remarkk.setDescription(remark.getDescription());
					remarkk.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
					
					System.out.println("sender id........>"+sender_id);
                    System.out.println("reciver id........>"+reciver_id);
                    System.out.println("remark id........>"+remark_id);
					long remarkk_id=approverMapper.send_remark(remarkk);
					approverMapper.addUserRemark(remarkk_id, sender_id, reciver_id);
					approverMapper.insertCurrencyRequestRemark(request_id,remarkk_id);
					util.registerActivity(request, "replay remark", "replay remark for currency request");
					return true;
				} else {
					System.out.println("No user does not have permission.");
					return false;
				}
			} catch (Exception e) {
				throw new ExceptionsList(e);
			}
		} catch (Exception ex) {
			throw new ExceptionsList(ex);
		}
	}
	
	public List<Remark> getAccountRemarks(HttpServletRequest request) {
		
		String accessToken = getToken(request);
		Long reciver_id = approverMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(accessToken));
		
		try {
			if (util.isPermitted(request, "Approver", "view_account_remarks")) {
				
				List<Remark> accountRemark = approverMapper.getAccountRemarks(reciver_id);
				util.registerActivity(request, "View account Remarks", "view all account remarks");
				return accountRemark;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	public List<Remark> getCurrencyRemarks(HttpServletRequest request) {
		
		String accessToken = getToken(request);
		Long reciver_id = approverMapper.getUserIdByEmail(jwtHelper.getEmailFromAccessToken(accessToken));
		
		try {
			if (util.isPermitted(request, "Approver", "view_currency_remarks")) {
				
				List<Remark> currencyRemark = approverMapper.getCurrencyRemarks(reciver_id);
				util.registerActivity(request, "View currency Remarks", "view all currency requests");
				return currencyRemark;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	private String getToken(HttpServletRequest request) {
		Cookie cookie = WebUtils.getCookie(request, cookieName);
		return cookie != null ? cookie.getValue() : null;
	}
}
