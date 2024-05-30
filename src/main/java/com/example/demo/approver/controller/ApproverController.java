package com.example.demo.approver.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.approver.model.Remark;
import com.example.demo.approver.service.ApproverService;
import com.example.demo.response.APIResponse;
@RestController
@RequestMapping("/api/approver/")
public class ApproverController {
	@Autowired
	private ApproverService approverService;
	
	@RequestMapping(value = "view_currency_requests", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllCurrencyRequests(HttpServletRequest request) {
		return APIResponse.response(approverService.getAllCurrencyRequests(request));
	}
	
	@RequestMapping(value = "view_rejected_currency_requests", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllRejectedCurrencyRequests(HttpServletRequest request) {
		return APIResponse.response(approverService.getAllRejectedCurrencyRequests(request));
	}
	
	@RequestMapping(value = "view_rejected_account_requests", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllRejectedAccountRequests(HttpServletRequest request) {
		return APIResponse.response(approverService.getAllRejectedAccountRequests(request));
	}
	
	@RequestMapping(value = "view_approved_currency_requests", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllApprovedCurrencyRequests(HttpServletRequest request) {
		return APIResponse.response(approverService.getAllApprovedCurrencyRequests(request));
	}
	
	@RequestMapping(value = "view_approved_account_requests", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllApprovedAccountRequests(HttpServletRequest request) {
		return APIResponse.response(approverService.getAllApprovedAccountRequests(request));
	}
	
	@RequestMapping(value = "view_account_requests", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllAccountRequests(HttpServletRequest request) {
		return APIResponse.response(approverService.getAllAccountRequests(request));
	}
	
	@RequestMapping(value = "view_account_remarks", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAccountRemarks(HttpServletRequest request) {
		return APIResponse.response(approverService.getAccountRemarks(request));
	}
	
	@RequestMapping(value = "view_currency_remarks", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getCurrencyRemarks(HttpServletRequest request) {
		return APIResponse.response(approverService.getCurrencyRemarks(request));
	}
	

	@RequestMapping(value = "approve_currency_request/{request_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> approve_currency_request(@PathVariable("request_id") String request_id, HttpServletRequest request) {
		return APIResponse.response(approverService.approve_currency_request(request_id, request));
	}
	
	@RequestMapping(value = "approve_account_request/{request_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> approve_account_request(@PathVariable("request_id") String request_id, HttpServletRequest request) {
		return APIResponse.response(approverService.approve_account_request(request_id, request));
	}
	
	
	@RequestMapping(value = "reject_currency_request/{request_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> reject_currency_request(@PathVariable("request_id") String request_id, HttpServletRequest request) {
		return APIResponse.response(approverService.reject_currency_request(request_id, request));
	}
	
	@RequestMapping(value = "reject_account_request/{request_id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Boolean> reject_account_request(@PathVariable("request_id") String request_id, HttpServletRequest request) {
		return APIResponse.response(approverService.reject_account_request(request_id, request));
	}
	
	@RequestMapping(value = "currency_remark/{request_id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> currency_remark(HttpServletRequest request,
			@Validated @RequestBody Remark remark,@PathVariable("request_id") long request_id) {
		return APIResponse.response(approverService.currency_remark(request,request_id,remark));
		
	}
		
	@RequestMapping(value = "account_remark/{request_id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> account_remark(HttpServletRequest request,
			@Validated @RequestBody Remark remark,@PathVariable("request_id") long request_id) {
		return APIResponse.response(approverService.account_remark(request,request_id,remark));
		
	}
	
	@RequestMapping(value = "replay_remark_account/{remark_id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> replay_remark_account(HttpServletRequest request,
			@Validated @RequestBody Remark remark,@PathVariable("remark_id") long remark_id) {
		return APIResponse.response(approverService.replay_remark_account(request,remark_id,remark));
		
	}
	
	@RequestMapping(value = "replay_remark_currency/{remark_id}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> replay_remark_currency(HttpServletRequest request,
			@Validated @RequestBody Remark remark,@PathVariable("remark_id") long remark_id) {
		return APIResponse.response(approverService.replay_remark_currency(request,remark_id,remark));
		
	}
}
