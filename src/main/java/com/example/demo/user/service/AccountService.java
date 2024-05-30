package com.example.demo.user.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.CustomAllException;
import com.example.demo.Exception.ExceptionsList;
import com.example.demo.jwt.JwtHelper;
import com.example.demo.mapper.MapperAuth;
import com.example.demo.user.mapper.MapperAccount;
import com.example.demo.user.model.Account;
import com.example.demo.user.model.Notification;
import com.example.demo.utils.Utils;

@Service
public class AccountService {

	@Autowired
	private MapperAccount mapper;

	@Autowired
	private MapperAuth authMapper;
	
	@Autowired
	private JwtHelper jwtHelper;

	@Value("${jwt.access.token.cookie.name}")
	private String cookieName;

	@Autowired
	private Utils util;

	public boolean register_account(Account account, HttpServletRequest request) {

		try {
			if (util.isPermitted(request, "User", "add_account_request")) {
				System.out.println("account: " + account.getCode());	
				if (util.check_AccountCodeExist(account.getCode())) {
					if(util.check_AcountNameNotExist(account.getName()) ||
						(util.check_AcountNameExist(account.getName()) && util.check_AcountCurrency(account.getCurrency(), account.getName()))) {
//                  System.out.println(" get currency result "+util.check_AcountCurrency(account.getCurrency()) );
					Notification notification = new Notification();
					notification.setTitle("Account Request");
					notification.setDescription("This is account request notification");
					notification.setDate(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
					notification.setViewStatus("0");
					notification.setStatus("1");
					notification.setAvailability("1");
//				    Long id=notification.getId();		
					account.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
					Long User_id = Long.parseLong(account.getUserId());
                 	Long cur_id = Long.parseLong(account.getCurrency());
					Long AccountRequestId=mapper.accountRequest(account);
//				    account.setCurrency(mapper.getCurrencyName(cur_id));
					System.out.println("here the id "+AccountRequestId + " "+ User_id);
					mapper.addAccountRequestCurrency(AccountRequestId, cur_id);
 					mapper.addUserAccount(AccountRequestId, User_id);
					mapper.addUserNotification(User_id, mapper.addAccountNotification(notification));
					util.registerActivity(request, "send account request", "send create account request with account name "+account.getName()+" and with code "+account.getCode());

					return true;
					
				}else {
						
					throw new CustomAllException("Account Name is already registered");

					}
				}else {
					
					 throw new CustomAllException("Account is already registered");
				}
				
				}else {
					return false;
					
				}

		} catch (Exception e) {
			throw new ExceptionsList(e);
		}

	}
	public boolean updateApprovedRequest(Account account, HttpServletRequest request, Long id) {

		try {
			if (util.isPermitted(request, "User", "update_approved_request")) {
				System.out.println("arrived hereeeeeeeeeeee");
				account.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
				Long cur_id = Long.parseLong(account.getCurrency());
				Long user_id = Long.parseLong(account.getUserId());
////			account.setCurrency(mapper.getCurrencyName(cur_id));
//				Long id = account.getId();
				account.setId(id);
//				Long returnedId=mapper.getEdittedAccountsId(id);
				

//				System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyuuuuuuuuuuuu"+returnedId);
 
				System.out.println("hee is the id " + id);
//    			accountmapper.updateApprovedRequest(account);
			    mapper.updateApprovedRequest(account);
				System.out.println("dhfjdsfhjkdfhkd"+ mapper.updateApprovedRequest(account));

				
//				mapper.addAccountRequestCurrency(updateRequestId, cur_id);
//				System.out.println("hee is the id wich is said null " + id +" "+updateRequestId);

//				mapper.addAccountCurrency(updateRequestId, cur_id);
//				mapper.addAccountAccountRequest(id, updateRequestId);
//				mapper.addUserAccount(returnedId, user_id);
				util.registerActivity(request, "update account", "send request to update account to name "+account.getName());
				
				return true;

			} else
				return false;

		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
 

	public List<Account> getAllAccounts(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all_accounts")) {
				util.registerActivity(request, "View all accounts", "view all accounts");
				return mapper.getAllAccounts();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<Account> getApprovedAccounts(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_approved_accounts")) {
				util.registerActivity(request, "View accounts", "view all approved requests");
				return mapper.getApprovedAccounts();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<Account> getPendingAccounts(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_pending_accounts")) {
				util.registerActivity(request, "View accounts", "View all pending accounts");
				return mapper.getPendingAccounts();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<Account> getRejectedAccounts(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_rejected_accounts")) {
				util.registerActivity(request, "view accounts", "view all rejected accounts");
				return mapper.getRejectedAccounts();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<Account> getAll() {
		return mapper.getAll();

	}

	public Boolean updateRequest(Account account, Long id) {
		try {
			System.out.println("currrency update service: " + account.getCode());
			account.setId(id);
			account.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));

			Boolean a = mapper.updateRequest(account);
//			Boolean a = mapper.updateRequest_001(currency.getName(), id);
			System.out.println("returned value: " + a);
 
			return a;
		} catch (Exception e) {
			System.out.println("Exception occured.");
			throw new ExceptionsList(e);
		}

	}

	public void deleteAccount(Long id) {
		mapper.deleteAccount(id);

	}

	public void deleteAccountRequest(Long id) {
		mapper.deleteAccountRequest(id);

	}

	public Account findById(Long id) {
		return mapper.findById(id);
	}

	public Account findByIdAccount(Long id) {
		return mapper.findByIdAccount(id);
	}

	// this functionality is get_all_accounts_to_set
	public List<Account> getAllAccountsToSet(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all_accounts_to_set")||util.isPermitted(request, "IssueAccount", "get_all_accounts_to_set")) {
				util.registerActivity(request, "Get all accounts to set account", "-");
				System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa.");
				return mapper.getAllAccountsToSet();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	// this functionality is get_selected_account
	public Long getSelectedAccount(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_selected_account")||util.isPermitted(request, "IssueAccount", "get_selected_account")) {
				//util.registerActivity(request, "Get selected account", "-");
				
				return mapper.getSelectedAccount(util.get_user_id(request));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	// this functionality is change_user_wrokspace_account
	public Boolean changeUserWorkspaceAccount(HttpServletRequest request, String account_id) {
		try {
			if (util.isPermitted(request, "User", "change_user_wrokspace_account")||util.isPermitted(request, "IssueAccount", "change_user_wrokspace_account")) {
				//util.registerActivity(request, "Get all accounts to set account", "-");
				System.out.println("account_id: " + account_id);
				System.out.println("user account: " + mapper.getUserAccountId(util.get_user_id(request)));
				if (mapper.getUserAccountId(util.get_user_id(request)) != null)
					return mapper.changeUserWorkspaceAccount(util.get_user_id(request), account_id);
				else
					return mapper.setUserWorkspaceAccount(util.get_user_id(request), account_id, "1", "1");

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	// this functionality is set_workspace
	public Boolean setWorkspace(HttpServletRequest request, String reconciliation_type) {
		try {
			if (util.isPermitted(request, "User", "set_workspace")) {
				//util.registerActivity(request, "Set Workspace", "-");
				if (util.isWorkspaceFree(reconciliation_type))
					if (util.removeUserFromWorkspace(request))
						return mapper.setWorkspace(util.get_user_id(request),
								authMapper.getWorkspaceIdFromReconciliationType(reconciliation_type), "1", "1");
					else
						throw new CustomAllException("can-not-unbind-user-from-previous-workspace");
				else
					throw new CustomAllException("workspace-is-not-free");

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	// this functionality is get_current_user_wrokspace
	public Map<String, Object> getCurrentWorkspace(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_current_user_wrokspace")) {
				//util.registerActivity(request, "Get current user workspace", "-");
				String ret = mapper.getCurrentWorkspace(util.get_user_id(request));
				System.out.println("current workspace: " + ret);
				Map<String, Object> map = new HashMap<>();
				map.put("reconciliation_type", ret);
				return map;

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
}

//accountIns.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
