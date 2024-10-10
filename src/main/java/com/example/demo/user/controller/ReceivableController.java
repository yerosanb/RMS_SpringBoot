package com.example.demo.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.APIResponse;
import com.example.demo.user.model.File_rtgs__ats;
import com.example.demo.user.service.ReceivableService;

@RestController
@RequestMapping("api/receivable/")
public class ReceivableController {
	@Autowired
	private ReceivableService receivableService;
	
	@RequestMapping(value = "get_receivable_credit_for_recon", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_credit_for_recon(HttpServletRequest request) {
		return APIResponse.response(receivableService.get_credit_for_recon(request));
	}
	@RequestMapping(value = "get_receivable_debit_for_recon", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_debit_for_recon(HttpServletRequest request) {
		return APIResponse.response(receivableService.get_debit_for_recon(request));
	}
	@RequestMapping(value = "match_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(receivableService.match_transactions(request, data_ids));
	}
	
	@RequestMapping(value = "match_transactions_with_reason", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactions_with_reason(HttpServletRequest request,
			@RequestBody() String data_ids) {
		return APIResponse.response(receivableService.match_transactions_with_reason(request, data_ids));
	}
	
	@RequestMapping(value = "delete_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> delete_transactions(HttpServletRequest request, @RequestBody() String datas) {
		return APIResponse.response(receivableService.delete_transactions(request, datas));
	}
	
	@RequestMapping(value = "update_transaction", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> update_transaction(HttpServletRequest request,
			@RequestBody() File_rtgs__ats edit_data) {
		return APIResponse.response(receivableService.update_transaction(request, edit_data));
	}
	
	@RequestMapping(value = "get_receivable_credit_for_recon_auto", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_receivable_credit_for_recon_auto(HttpServletRequest request) {
		return APIResponse.response(receivableService.get_receivable_credit_for_recon_auto(request));
	}
	
	@RequestMapping(value = "get_receivable_debit_for_recon_auto", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_receivable_debit_for_recon_auto(HttpServletRequest request) {
		return APIResponse.response(receivableService.get_receivable_debit_for_recon_auto(request));
	}
	
	@RequestMapping(value = "match_all_receivable_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_all_receivable_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(receivableService.match_all_receivable_transactions(request, data_ids));
	}
	
	@RequestMapping(value = "get_receivable_credit_matched/{match_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_receivable_credit_matched(HttpServletRequest request,@PathVariable("match_date") String match_date) {
		return APIResponse.response(receivableService.get_receivable_credit_matched(request,match_date));
	}
	@RequestMapping(value = "get_receivable_debit_matched/{match_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_receivable_debit_matched(HttpServletRequest request,@PathVariable("match_date") String match_date) {
		return APIResponse.response(receivableService.get_receivable_debit_matched(request,match_date));
	}
	
	@RequestMapping(value = "unmatch_receivable_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_receivable_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(receivableService.unmatch_receivable_transactions(request, data_ids));
	}
	
	@RequestMapping(value = "get_receivable_credit_matched_with_reason", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_receivable_credit_matched_with_reason(HttpServletRequest request) {
		return APIResponse.response(receivableService.get_receivable_credit_matched_with_reason(request));

	}
	@RequestMapping(value = "get_receivable_debit_matched_with_reason", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_receivable_debit_matched_with_reason(HttpServletRequest request) {
		return APIResponse.response(receivableService.get_receivable_debit_matched_with_reason(request));

	}

}
