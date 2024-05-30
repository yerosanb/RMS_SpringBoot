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
import com.example.demo.user.model.File_rtgs_nbe_ats;
import com.example.demo.user.service.IssueAccountService;

@RestController
@RequestMapping("api/issueaccount/")
public class IssueAccountController {

	@Autowired
	private IssueAccountService iaservice;
	
	@RequestMapping(value = "get_core_issue_for_recon", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_core_issue_for_recon(HttpServletRequest request) {
		return APIResponse.response(iaservice.get_core_issue_for_recon(request));
	}
	
	@RequestMapping(value = "get_core_issue_for_view_matched", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_core_issue_for_view_matched(HttpServletRequest request,@RequestBody() String match_dates) {
		return APIResponse.response(iaservice.get_core_issue_for_view_matched(request,match_dates));
	}
	@RequestMapping(value = "get_core_issue_for_view_matched_reversal", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_core_issue_for_view_matchedreversal(HttpServletRequest request,@RequestBody() String match_dates) {
		return APIResponse.response(iaservice.get_core_issue_for_view_matchedreversal(request,match_dates));
	}
	@RequestMapping(value = "get_QBS_issue_for_view_matched_reversal", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_qbs_issue_for_view_matchedreversal(HttpServletRequest request,@RequestBody() String match_dates) {
		return APIResponse.response(iaservice.get_qbs_issue_for_view_matchedreversal(request,match_dates));
	}
	@RequestMapping(value = "get_qbs_issue_for_recon", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_qbs_issue_for_recon(HttpServletRequest request) {
		return APIResponse.response(iaservice.get_qbs_issue_for_recon(request));
	}
	@RequestMapping(value = "get_qbs_issue_for_view_matched", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_qbs_issue_for_view_matched(HttpServletRequest request,@RequestBody() String match_dates) {
		return APIResponse.response(iaservice.get_qbs_issue_for_view_matched(request,match_dates));
	}
	
	@RequestMapping(value = "match_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(iaservice.match_transactions(request, data_ids));
	}
	
	@RequestMapping(value = "match_Issue_core_reversal_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_issue_core_reversal_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(iaservice.match_issue_core_reversal_transactions(request, data_ids));
	}
	@RequestMapping(value = "match_Issue_qbs_reversal_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_issue_qbs_reversal_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(iaservice.match_issue_qbs_reversal_transactions(request, data_ids));
	}
	@RequestMapping(value = "match_transactions_with_reason", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactions_with_reason(HttpServletRequest request,
			@RequestBody() String data_ids) {
		return APIResponse.response(iaservice.match_transactions_with_reason(request, data_ids));
  }
	@RequestMapping(value = "unmatch_issue_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_issue_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(iaservice.unmatch_issuetransactions(request, data_ids));
	}
	
	@RequestMapping(value = "unmatch_issue_core_reversal_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_issue_transactions_core_reversal(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(iaservice.unmatch_issue_transactions_core_reversal(request, data_ids));
	}
	@RequestMapping(value = "unmatch_issue_qbs_reversal_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_issue_transactions_qbs_reversal(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(iaservice.unmatch_issue_transactions_qbs_reversal(request, data_ids));
	}
	@RequestMapping(value = "match_all_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_all_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(iaservice.match_all_transactions(request, data_ids));
	}
	
	@RequestMapping(value = "get_core_issue_for_view_unmatched", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_core_issue_for_view_unmatched(HttpServletRequest request) {
		return APIResponse.response(iaservice.get_core_issue_for_view_unmatched(request));
	}
	@RequestMapping(value = "get_qbs_issue_for_view_unmatched", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_qbs_issue_for_view_unmatched(HttpServletRequest request) {
		return APIResponse.response(iaservice.get_qbs_issue_for_view_unmatched(request));
	}

	@RequestMapping(value = "get_issue_core_matched_with_reason", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_issue_core_matched_with_reason(HttpServletRequest request) {
		return APIResponse.response(iaservice.get_issue_core_matched_with_reason(request));
	}
	@RequestMapping(value = "get_issue_qbs_matched_with_reason", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_issue_qbs_matched_with_reason(HttpServletRequest request) {
		return APIResponse.response(iaservice.get_issue_qbs_matched_with_reason(request));

	}
	@RequestMapping(value = "get-edited-issue-core", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_issue_core(HttpServletRequest request) {
		return APIResponse.response(iaservice.get_edited_issue_core(request));
	}
	@RequestMapping(value = "get-edited-issue-qbs", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_issue_qbs(HttpServletRequest request) {
		return APIResponse.response(iaservice.get_edited_issue_qbs(request));
	}
	@RequestMapping(value = "get-edited-detail-issue-core/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_detail_issue_core(HttpServletRequest request, @PathVariable("id") Long id) {
		return APIResponse.response(iaservice.get_edited_detail_issue_core(request, id));
	}
	@RequestMapping(value = "get-edited-detail-issue-qbs/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_detail_issue_qbs(HttpServletRequest request, @PathVariable("id") Long id) {
		return APIResponse.response(iaservice.get_edited_detail_issue_qbs(request, id));
	}
	@RequestMapping(value = "update_transaction_issue", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> update_transaction_issue(HttpServletRequest request,
			@RequestBody() File_rtgs_nbe_ats edit_data) {
		return APIResponse.response(iaservice.update_transaction_issue(request, edit_data));
	}
	@RequestMapping(value = "delete_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> delete_transactions(HttpServletRequest request, @RequestBody() String datas) {
		return APIResponse.response(iaservice.delete_transactions(request, datas));
	}

	@RequestMapping(value = "get_core_issue_for_recon_auto", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_core_issue_for_recon_auto(HttpServletRequest request) {
		return APIResponse.response(iaservice.get_core_issue_for_recon_auto(request));
	}
	
	@RequestMapping(value = "get_qbs_issue_for_recon_auto", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_qbs_issue_for_recon_auto(HttpServletRequest request) {
		return APIResponse.response(iaservice.get_qbs_issue_for_recon_auto(request));

	}
}