package com.example.demo.user.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.CustomAllException;
import com.example.demo.Exception.ExceptionsList;
import com.example.demo.jwt.JwtHelper;
import com.example.demo.user.mapper.MapperCurrency;
import com.example.demo.user.model.Account;
import com.example.demo.user.model.Currency;
import com.example.demo.user.model.Notification;
import com.example.demo.utils.Utils;

@Service
public class CurrencyService {

	@Autowired
	private MapperCurrency mapper;
	@Autowired
	private JwtHelper jwtHelper;

	@Value("${jwt.access.token.cookie.name}")
	private String cookieName;

	@Autowired
	private Utils util;

//	public void currencyRequest(Currency currency) {
//		currency.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
//		mapper.currencyRequest(currency);
//	}
	
	public boolean register_currency(Currency currency, HttpServletRequest request) {

		try {
			if (util.isPermitted(request, "User", "add_currency_request")) {
				if(util.check_CurrencyCodeExist(currency.getCode())) {
				Notification notification = new Notification();
				notification.setTitle("Currency Request");
				notification.setDescription("This is Currency request notification");
				notification.setDate(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
				notification.setViewStatus("0");
 				notification.setStatus("1");
				notification.setAvailability("1");
				
 	     		currency.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
 				Long cur_id = Long.parseLong(currency.getUserId());
//				account.setCurrency(mapper.getCurrencyName(cur_id));
				mapper.addUserCurrency(mapper.currencyRequest(currency), cur_id);
				util.registerActivity(request, "Register User", "-");
//   			mapper.currencyRequest(currency);
   				mapper.addUserNotification(cur_id, mapper.addCurrencyNotification(notification));
				util.registerActivity(request, "Send request", "Send create currency reques with currency name "+currency.getName());
				
				return true;
			} else 
			{
				throw new CustomAllException("currency already registered");
			}
			}
				else
				return false;

		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
//	public boolean updateApprovedRequest(Currency currency, HttpServletRequest request) {
//
//		try {
//			if (util.isPermitted(request, "User", "update_approved_request")) {
//				Notification notification = new Notification();
//				notification.setTitle("Currency Request");
//				notification.setDescription("This is Currency request notification");
//				notification.setDate(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
//				notification.setViewStatus("0");
// 				notification.setStatus("1");
//				notification.setAvailability("1");
// 	     		currency.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
// 				Long cur_id = Long.parseLong(currency.getUserId());
////				account.setCurrency(mapper.getCurrencyName(cur_id));
//				mapper.addUserCurrency(mapper.updateApprovedRequest(currency), cur_id);
//				util.registerActivity(request, "Register User", "-");
////   				mapper.currencyRequest(currency);
//   				mapper.addUserNotification(cur_id, mapper.addCurrencyNotification(notification));
//				util.registerActivity(request, "Register User", "-");
//				
//				return true;
//			} else
//				return false;
//
//		} catch (Exception e) {
//			throw new ExceptionsList(e);
//		}
//	}
	public boolean updateApprovedRequest(Currency currency, HttpServletRequest request, Long id) {
		 
 		try {
 			if (util.isPermitted(request, "User", "update_approved_request")) {
 			    System.out.println("arrived hereeeeeeeeeeee");
    			currency.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
// 				Long cur_id = Long.parseLong(currency.getCurrency());
////				account.setCurrency(mapper.getCurrencyName(cur_id));
   			    System.out.println("error is hereee");
   			    currency.setId(id);
 				System.out.println("hee is the id wich is said null " +id);
//    			mapper.updateApprovedRequest(account);
 				System.out.println("hee is the request id " + id);

 				Long user_id = Long.parseLong(currency.getUserId());

 				mapper.updateApprovedRequest(currency);

 				mapper.addUserCurrency(id, user_id);
// 				System.out.println("hee is the request id " + updateId);


// 	     		mapper.addCurrencyCurrencyRequest( id, updateId);

// 				mapper.addAccountCurrency(updateId, cur_id);
 
 				util.registerActivity(request, "update currency", "update currency");
 				return true;
 				
 			} else
 				return false;
 
 		} catch (Exception e) {
 			throw new ExceptionsList(e);
 		}
 	}

//	public boolean register_currency(Currency currency, HttpServletRequest request) {
//
//		try {
//			if (util.isPermitted(request, "User", "add_account_request")) {
//				currency.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
//				Long cur_id = Long.parseLong(currency.getUserId());
////				account.setCurrency(mapper.getCurrencyName(cur_id));
//				mapper.addUserCurrency(mapper.currencyRequest(currency), cur_id);
//				util.registerActivity(request, "Register User", "-");
//				return true;
//			} else
//				return false;
//
//		} catch (Exception e) {
//			throw new ExceptionsList(e);
//		}
//	}
// 
//	public boolean updateApprovedRequest(Currency currency, HttpServletRequest request) {
//
//		try {
//			if (util.isPermitted(request, "User", "update_approved_request")) {
//			    System.out.println("arrived hereeeeeeeeeeee");
//
//   			    currency.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
//				Long cur_id = Long.parseLong(currency.getCurrency());
////				account.setCurrency(mapper.getCurrencyName(cur_id));
//   			    System.out.println("error is hereee");
////   			    mapper.updateApprovedRequest(currency);
//				
//				mapper.addAccountCurrency(mapper.accountRequest(currency), cur_id);		
//   			    util.registerActivity(request, "Register User", "-");
//				return true;
//		} else
//				return false;
//
//		} catch (Exception e) {
//			throw new ExceptionsList(e);
//		}
//	}
//	
//	public Boolean updateApprovedRequest(Currency currency, Long id) {
//		try {
//			System.out.println("currrency update service: " + currency.getCode());
////			account.setId(id)
//		    currency.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
//            Long req_id = mapper.getCurrencyRequestId(id);
//            System.out.println("service  " + req_id);
//            Boolean a = mapper.updateApprovedRequest(currency, req_id);
////			Boolean a = mapper.updateRequest(account);
////			Boolean a = mapper.updateRequest_001(currency.getName(), id);
//			System.out.println("returned value: " + a);
//			return a;
//		} catch (Exception e) {
//			System.out.println("Exception occured.");
//			throw new ExceptionsList(e);
//		}
//
//	}
//	

	public List<Currency> getAll() {
		return mapper.getAll();
	}

//	public void createCurrency(Currency currency) {
//		mapper.createCurrency(currency);
//	}

	public List<Currency> getAllCurrencies(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all_currencies")) {
				util.registerActivity(request, "View all currency", "View all currency lists");
				return mapper.getAllCurrencies();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<Currency> getApprovedCurrencies(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_approved_currencies")) {
				util.registerActivity(request, "View  currencies", " view all approved currencies");
				return mapper.getApprovedCurrencies();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<Currency> getPendingCurrencies(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_pending_currencies")) {
				util.registerActivity(request, "View currency", "view all pending currencies");
				return mapper.getPendingCurrencies();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<Currency> getRejectedCurrencies(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_rejected_currencies")) {
				util.registerActivity(request, "View currency", "view all rejected currencies");
				return mapper.getRejectedCurrencies();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Boolean updateRequest(Currency currency, Long id) {
		try {
			System.out.println("currrency update service: " + currency.getCode());
			currency.setId(id);
			System.out.println("ghhhhhhhhhhhhhhhhhhhhhhhhhhh"+ currency.getId());
		    currency.setCreated_date(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
			Boolean a = mapper.updateRequest(currency);
//			Boolean a = mapper.updateRequest_001(currency.getName(), id);
			System.out.println("returned value: " + a);
			return a;
		} catch (Exception e) {
			System.out.println("Exception occured.");
			throw new ExceptionsList(e);
		}

	}

	public void deleteCurrency(Long id) {
		mapper.deleteCurrency(id);

	}
	public void deleteCurrencyRequest(Long id) {
		mapper.deleteCurrencyRequest(id);

	}

	public Account findById(Long id) {
		return mapper.findById(id);
	}

	public Currency findByIdCurrency(Long id) {
		return mapper.findByIdCurrency(id);
	}
	 

}
