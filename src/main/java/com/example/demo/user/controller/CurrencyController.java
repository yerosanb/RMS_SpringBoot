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
import com.example.demo.user.model.Currency;
import com.example.demo.user.service.CurrencyService;
@RestController
@RequestMapping("/currency")
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;
 // 	
//	
//	@RequestMapping(value = "/send-request", method = RequestMethod.POST)
//	public ResponseEntity <String> savedatas(@RequestBody Currency currency) throws ParseException {
// 			System.out.println("hereeeeeeeeeeeeeeeeeeeeee");
// 		try {
//			if (currency.getId() == null) {
//				currencyService.currencyRequest(currency);
//			}
//	     else {
//	    	 
////				currencyService.updateRequest(currency);
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
//	
	
	@RequestMapping(value = "/send-request", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> register_account(HttpServletRequest request,
			@Validated @RequestBody Currency currency) {

		return APIResponse.response(currencyService.register_currency(currency, request));
	}
		 
	@RequestMapping(value = "/get_currency", method = RequestMethod.GET)
		public ResponseEntity<Object> getAllCurrency() {
		System.out.println("hereeeeeee");
			return APIResponse.response(currencyService.getAll());	
			}
			
	@RequestMapping(value = "/deleteCurrency/{id}", method = RequestMethod.DELETE)
		public void deleteCurrency(@PathVariable Long id) throws ParseException {
			System.out.println("controllerrrrrrrrrrrrrrrr...");
			currencyService.deleteCurrency(id);
		}
	@RequestMapping(value = "/deleteCurrencyRequest/{id}", method = RequestMethod.DELETE)
	public void deleteCurrencyRequest(@PathVariable Long id) throws ParseException {
		System.out.println("controllerrrrrrrrrrrrrrrr...");
		currencyService.deleteCurrencyRequest(id);
	}
	@RequestMapping(value = "/updateCurrency/{id}", method = RequestMethod.PUT)
	public Boolean deleteUser (@PathVariable Long id, @RequestBody Currency currency) {
		System.out.println("controllerrrrrrrrrrrrrrrrooooooooo...: " + id);
		return currencyService.updateRequest(currency, id);
	}
	@RequestMapping(value = "/update-approved-request/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Boolean> updateApprovedRequest(HttpServletRequest request,@PathVariable Long id,
			@Validated @RequestBody Currency currency) {

		return APIResponse.response(currencyService.updateApprovedRequest(currency, request, id));
	}
//	@RequestMapping(value = "/update-approved-currency/{id}", method = RequestMethod.PUT)
//	public Boolean updateCurrency (@PathVariable Long id, @RequestBody Currency currency) {
//		System.out.println("controllerrrrrrrrrrrrrrrr...: " + id);
//		return currencyService.updateApprovedRequest(currency, id);
//	}
	
//	@RequestMapping(value = "/updateCurrency/{id}", method = RequestMethod.PUT)
//	public Boolean you (@PathVariable("id") Long id, @RequestBody Currency currency) {
//		
//		System.out.println("controllerrrrrrrrrrrrrrrr...: " + currency.getName());
//		return currencyService.updateRequest(currency, id);
//	}
//	
	
	@RequestMapping(value = "/get_all_currencies", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllCurrency(HttpServletRequest request) {
		return APIResponse.response(currencyService.getAllCurrencies(request));
	}
	
	@RequestMapping(value = "/get_approved_currencies", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity <Object> getApprovedCurrency(HttpServletRequest request) {
		return APIResponse.response(currencyService.getApprovedCurrencies(request));
	}
	
	@RequestMapping(value = "/get_pending_currencies", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity <Object> getPendingCurrency(HttpServletRequest request) {
		return APIResponse.response(currencyService.getPendingCurrencies(request));
	}
	
	@RequestMapping(value = "/get_rejected_currencies", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity <Object> getRejectedCurrency(HttpServletRequest request) {
		return APIResponse.response(currencyService.getRejectedCurrencies(request));
	}
	
//	@RequestMapping(value ="/updateCurrency/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @RequestBody Currency currency) {
//    	currencyService.updateRequest(currency);
//		System.out.println("controllerrrrrrrrrrrrrrrr...");
//        return ResponseEntity.ok("change saved");
//        
//     }
	

//	 @GetMapping("/findById/{id}")
//	 public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id) {
//			return APIResponse.response(currencyService.findById(id));	
//			
//			}
//	
	@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
	public ResponseEntity<Account> findById(@PathVariable Long id) {
		System.out.println("arrived here");
		Account account = currencyService.findById(id);
		return ResponseEntity.ok(account);
	}
//	
	@RequestMapping(value = "/findById_currency/{id}", method = RequestMethod.GET)
	public ResponseEntity<Currency> findByIdAccount(@PathVariable Long id) {
		System.out.println("arrived here");
		Currency currency = currencyService.findByIdCurrency(id);
		return ResponseEntity.ok(currency);
	}
//	    @GetMapping("/findById/{id}")
//	    public Currency findById(@PathVariable Long id){
//	    	System.out.println(id);
//	        return currencyService.findById(id);
// 
//	    }
//	 @GetMapping("/departs/{id}")
//		public model fetchById(@PathVariable ("id") int id) {
//			return serviceimpl.fetchById(id);
//			
//		}
	     
	
	 
}

 