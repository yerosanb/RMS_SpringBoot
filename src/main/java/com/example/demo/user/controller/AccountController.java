package com.example.demo.user.controller;


import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.APIResponse;
import com.example.demo.user.model.Account;
import com.example.demo.user.service.AccountService;
import com.example.demo.utils.Utils;
 
@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@Autowired
	private Utils util;

//	
//	@RequestMapping(value = "/send-request", method = RequestMethod.POST)
//	public ResponseEntity <String> savedatas(@RequestBody Account account) throws ParseException {
// 			System.out.println("hereeeeeeeeeeeeeeeeeeeeee");
// 		try {
//			if (account.getId() == null) {
//				accountService.accountRequest(account);
//			}
//	     else {	
//	    	 
//	    	 accountService.updateRequest(account);
//
// 			}
//  			
//		} catch (DuplicateKeyException ex) {
// 		    return new ResponseEntity<>("already exist", HttpStatus.OK);
//		       
//		}
//
//	    return new ResponseEntity<>("request completed", HttpStatus.OK);	
//	    }
	@RequestMapping(value = "/send-request", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> register_account(HttpServletRequest request,
			@Validated @RequestBody Account account) {
//       if (account.getCode() == null) {

         return APIResponse.response(accountService.register_account(account, request));
    }
//       else {
//    	
//	   throw new CustomAllException("Account is already registered");
//
//	 
// }
	
	@RequestMapping(value = "/update-approved-request/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Boolean> updateApprovedRequest(HttpServletRequest request, @PathVariable Long id,
			@Validated @RequestBody Account account) {

		return APIResponse.response(accountService.updateApprovedRequest(account, request, id));
	}
//	@RequestMapping(value = "/update-approved-request/{id}", method = RequestMethod.PUT)
//	public Boolean updateAccount (@PathVariable Long id, @RequestBody Account account) {
//		System.out.println("controllerrrrrrrrrrrrrrrr...: " + id);
//		return accountService.updateApprovedRequest(account,id);
//	}

//	@RequestMapping(value = "/get-account", method = RequestMethod.GET)
//		public ResponseEntity<Object> getAllCurrency() {
//			return APIResponse.response(accountService.getAll());
//			
//			}

	@RequestMapping(value = "/get_all_accounts", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllAccounts(HttpServletRequest request) {
		return APIResponse.response(accountService.getAllAccounts(request));
	}

	@RequestMapping(value = "/get_approved_accounts", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getApprovedAccounts(HttpServletRequest request) {
		return APIResponse.response(accountService.getApprovedAccounts(request));
	}

	@RequestMapping(value = "/get_pending_accounts", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getPendingAccounts(HttpServletRequest request) {
		return APIResponse.response(accountService.getPendingAccounts(request));
	}

	@RequestMapping(value = "/get_rejected_accounts", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getRejectedAccounts(HttpServletRequest request) {
		return APIResponse.response(accountService.getRejectedAccounts(request));
	}

//	@RequestMapping(value = "/delete-account/{id}", method = RequestMethod.DELETE)
//		public void deleteUser(@PathVariable Long id) throws ParseException {
//			System.out.println("controllerrrrrrrrrrrrrrrr...");
//			accountService.deleteAccount(id);
//		}

	@RequestMapping(value = "/delete-account/{id}", method = RequestMethod.DELETE)
	public void deleteAccount(@PathVariable Long id) throws ParseException {
		System.out.println("controllerrrrrrrrrrrrrrrrnewwwww...");
		accountService.deleteAccount(id);
	}

	@RequestMapping(value = "/delete-account-request/{id}", method = RequestMethod.DELETE)
	public void deleteAccountRequest(@PathVariable Long id) throws ParseException {
		System.out.println("controllerrrrrrrrrrrrrrrrnewwwww...");
		accountService.deleteAccountRequest(id);
	}

	@RequestMapping(value = "/update-account/{id}", method = RequestMethod.PUT)
	public Boolean deleteUser (@PathVariable Long id, @RequestBody Account account) {
		System.out.println("controllerrrrrrrrrrrrrrrr...: " + id);
		return accountService.updateRequest(account, id);
	}
	@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Account> findById(@PathVariable Long id) {
		System.out.println("arrived here");
		Account account = accountService.findById(id);
		return ResponseEntity.ok(account);
	}

	@RequestMapping(value = "/findById_account/{id}", method = RequestMethod.GET)
	public ResponseEntity<Account> findByIdAccount(@PathVariable Long id) {
		System.out.println("arrived here");
		Account account = accountService.findByIdAccount(id);
		return ResponseEntity.ok(account);
	}

//	@RequestMapping(value = "update_functionality_status/{role_id}", method = RequestMethod.PUT, produces = "application/json")
//	public ResponseEntity<APIResponse> update_functionality_status(HttpServletRequest request,
//			@PathVariable("id") Long id, @RequestBody Account account) {
//		    try {
//				return APIResponse.response(accountService.updateRequest(account));
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	
	@RequestMapping(value = "/get_selected_account", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getSelectedAccount(HttpServletRequest request) {
		return APIResponse.response(accountService.getSelectedAccount(request));
	}
	
	@RequestMapping(value = "/get_all_accounts_to_set", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllAccountsToSet(HttpServletRequest request) {
		return APIResponse.response(accountService.getAllAccountsToSet(request));
	}
	
	@RequestMapping(value = "/change_user_workspace_account/{account_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> changeUserWorkspaceAccount(HttpServletRequest request, @PathVariable("account_id")String account_id) {
		return APIResponse.response(accountService.changeUserWorkspaceAccount(request, account_id));
	}
	@RequestMapping(value = "/set_workspace/{reconciliation_type}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> setWorkspace(HttpServletRequest request, @PathVariable("reconciliation_type")String reconciliation_type) {
		return APIResponse.response(accountService.setWorkspace(request, reconciliation_type));
	}
	
	@RequestMapping(value = "/get_current_workspace", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getCurrentWorkspace(HttpServletRequest request) {
		return APIResponse.response(accountService.getCurrentWorkspace(request));
	}
}
