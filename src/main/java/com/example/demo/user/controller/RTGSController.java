package com.example.demo.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.APIResponse;
import com.example.demo.user.model.File_rtgs_nbe_ats;
import com.example.demo.user.model.GeneralTransactionHistory;
import com.example.demo.user.model.MatchDetailAts;
import com.example.demo.user.model.MatchDetailCore;
import com.example.demo.user.model.Transactionhistory;
import com.example.demo.user.service.RTGSService;

@RestController
@RequestMapping("api/user/recon/")
public class RTGSController {

	@Autowired
	private RTGSService rtgsService;

	@RequestMapping(value = "get_ats_for_recon_auto/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_ats_for_recon_auto(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(rtgsService.get_ats_for_recon_auto(request, recon_date));
	}

	@RequestMapping(value = "get_ats_for_recon_partial_auto/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_ats_for_recon_partial_auto(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(rtgsService.get_ats_for_recon_partial_auto(request, recon_date));
	}

	@RequestMapping(value = "get_ats_for_recon_partial", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_ats_for_recon_partial(HttpServletRequest request) {
		return APIResponse.response(rtgsService.get_ats_for_recon_partial(request));
	}

	@RequestMapping(value = "get_ats_for_recon/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_ats_for_recon(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(rtgsService.get_ats_for_recon(request, recon_date));
	}
	////////////////////////

	@RequestMapping(value = "rtgs/get_rtgs_ats_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_rtgs_ats_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(rtgsService.get_rtgs_ats_for_view(request, recon_date));
	}

	@RequestMapping(value = "rtgs/get_rtgs_core_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_rtgs_core_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(rtgsService.get_rtgs_core_for_view(request, recon_date));
	}

	//======================================================= view payable matched  start ====================================
	
	@RequestMapping(value = "rtgs/get_payable_credit_for_view/{match_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_payable_credit_for_view(HttpServletRequest request,@PathVariable("match_date") String match_date) {
		return APIResponse.response(rtgsService.get_payable_credit_for_view(request,match_date));
	}
	
	
	@RequestMapping(value = "rtgs/get_payable_debit_for_view/{match_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_payable_debit_for_view(HttpServletRequest request,@PathVariable("match_date") String match_date) {
		return APIResponse.response(rtgsService.get_payable_debit_for_view(request,match_date));
	}
	//======================================================= view payable matched  end ====================================

	@RequestMapping(value = "get_core_for_recon", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_core_for_recon(HttpServletRequest request) {
		return APIResponse.response(rtgsService.get_core_for_recon(request));
	}

	@RequestMapping(value = "get_core_for_recon_auto/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_core_for_recon_auto(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(rtgsService.get_core_for_recon_auto(request, recon_date));
	}
	//=======================================================  get payable transaction for auto match starting ==============================
	@RequestMapping(value = "get_payable_credit_for_recon_auto", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_payable_credit_for_recon_auto(HttpServletRequest request) {
		return APIResponse.response(rtgsService.get_payable_credit_for_recon_auto(request));
	}
	@RequestMapping(value = "get_payable_debit_for_recon_auto", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_payble_debit_for_recon_auto(HttpServletRequest request) {
		return APIResponse.response(rtgsService.get_payable_debit_for_recon_auto(request));
	}
	//=======================================================  get payable transaction for auto match ending ==============================
	@RequestMapping(value = "get_payable_credit", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_payable_credit_for_reconcilation(HttpServletRequest request) {
		return APIResponse.response(rtgsService.get_payable_credit_for_reconcilation(request));
	}
	@RequestMapping(value = "get_payable_debit", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_payable_debit_for_reconcilation(HttpServletRequest request) {
		return APIResponse.response(rtgsService.get_payable_debit_for_reconcilation(request));
	}
	@RequestMapping(value = "get_core_for_recon_partial_auto/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_core_for_recon_partial_auto(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(rtgsService.get_core_for_recon_partial_auto(request, recon_date));
	}

	@RequestMapping(value = "get_core_for_recon_partial", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_core_for_recon_partial(HttpServletRequest request) {
		return APIResponse.response(rtgsService.get_core_for_recon_partial(request));
	}

	@RequestMapping(value = "match_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(rtgsService.match_transactions(request, data_ids));
	}
	@RequestMapping(value = "match_transactions_reversal", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactionsReversal(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(rtgsService.match_transactionsReversal(request, data_ids));
	}
	@RequestMapping(value = "rtgs/get_core_reversal_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_core_reversal_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(rtgsService.get_coreReversal_for_view(request, recon_date));
	}
	//////////   match payable transaction begin /////////////////

	@RequestMapping(value = "match_payable_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_payable_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(rtgsService.match_payable_transactions(request, data_ids));
	}
	///////////  match payable transaction end  //////////////
	

	@RequestMapping(value = "match_transactions_with_reason", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactions_with_reason(HttpServletRequest request,
			@RequestBody() String data_ids) {
		return APIResponse.response(rtgsService.match_transactions_with_reason(request, data_ids));
	}
	//==================================================== start match payable transaction with reason =======================
	
	@RequestMapping(value = "match_payable_transactions_with_reason", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_payable_transactions_with_reason(HttpServletRequest request,
			@RequestBody() String data_ids) {
		return APIResponse.response(rtgsService.match_payable_transactions_with_reason(request, data_ids));
	}
	
	//======================================================== ending =========================================================

	@RequestMapping(value = "rtgs/unmatch_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(rtgsService.unmatch_transactions(request, data_ids));
	}
	
	@RequestMapping(value = "rtgs/unmatch_core_reversaltransactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_core_reversal_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(rtgsService.unmatch_core_reversal_transactions(request, data_ids));
	}
	//================================================ unmatch payable transaction starting ======================================
	@RequestMapping(value = "rtgs/unmatch_payable_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_payable_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(rtgsService.unmatch_Payabletransactions(request, data_ids));
	}
	//================================================ unmatch payable transaction  ending =======================================


	@RequestMapping(value = "match_partial_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_partial_transactions(HttpServletRequest request,
			@RequestParam("data_1_id") List<Long> data_1_id, @RequestParam("data_2_id") List<Long> data_2_id,
			@RequestParam("amount_difference") Double amount_difference,
			@RequestParam("description") String description) {
		return APIResponse.response(
				rtgsService.match_partial_transactions(request, data_1_id, data_2_id, amount_difference, description));
	}

	@RequestMapping(value = "match_partial_to_full_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_partial_to_full_transactions(HttpServletRequest request,
			@RequestBody() String data_ids) {
		return APIResponse.response(rtgsService.match_partial_to_full_transactions(request, data_ids));
	}

	@RequestMapping(value = "match_all_transactions/{recon_date}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_all_transactions(HttpServletRequest request, @RequestBody() String data_ids,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(rtgsService.match_all_transactions(request, data_ids, recon_date));
	}
	//========================================== start match automatic payable transaction ===========================
	@RequestMapping(value = "match_all_payable_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_all_payable_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(rtgsService.match_all_payable_transactions(request, data_ids));
	}
	//============================================ end match automatic payable transaction ==========================

	@RequestMapping(value = "match_all_transactions_partialy/{recon_date}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_all_transactions_partialy(HttpServletRequest request,
			@RequestBody() String data_ids, @PathVariable("recon_date") String recon_date) {
		return APIResponse.response(rtgsService.match_all_transactions_partialy(request, data_ids, recon_date));
	}

	@RequestMapping(value = "update_transaction", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> update_transaction(HttpServletRequest request,
			@RequestBody() File_rtgs_nbe_ats edit_data) {
		return APIResponse.response(rtgsService.update_transaction(request, edit_data));
	}

	@RequestMapping(value = "delete_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> delete_transactions(HttpServletRequest request, @RequestBody() String datas) {
		return APIResponse.response(rtgsService.delete_transactions(request, datas));
	}

	@RequestMapping(value = "get-edited-ats", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_ats(HttpServletRequest request) {
		return APIResponse.response(rtgsService.get_edited_ats(request));
	}

	@RequestMapping(value = "get-edited-core", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_core(HttpServletRequest request) {
		return APIResponse.response(rtgsService.get_edited_core(request));
	}
	//======================================= payable edited transaction start =================================
	@RequestMapping(value = "get-edited-payable", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_payable(HttpServletRequest request) {
		return APIResponse.response(rtgsService.get_edited_payable(request));
	}
	@RequestMapping(value = "get-edited-detail-payable/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_detail_payable(HttpServletRequest request, @PathVariable("id") Long id) {
		return APIResponse.response(rtgsService.get_edited_detail_payable(request, id));
	}

	//==================================================== ending ================================================

	@RequestMapping(value = "get-edited-detail-ats/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_detail_ats(HttpServletRequest request, @PathVariable("id") Long id) {
		return APIResponse.response(rtgsService.get_edited_detail_ats(request, id));
	}

	@RequestMapping(value = "get-edited-detail-core/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_detail_core(HttpServletRequest request, @PathVariable("id") Long id) {
		return APIResponse.response(rtgsService.get_edited_detail_core(request, id));
	}

	@RequestMapping(value = "rtgs/search-general", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> search_general(HttpServletRequest request,
			@RequestBody() GeneralTransactionHistory search) {
		 System.out.println("----------------------------------------------------------"+search);
		return APIResponse.response(rtgsService.General_search(request, search));
	}
//	@RequestMapping(value = "get_all_logs", method = RequestMethod.GET, produces = "application/json")
//	public ResponseEntity<Object> getAllLogs(HttpServletRequest request) {
//		return APIResponse.response(adminService.getAllLogs(request));
//	}

	@RequestMapping(value = "/rtgs/matched-detail-ats", method = RequestMethod.POST)
	public ResponseEntity<Object> matchDetailAts(HttpServletRequest request, @RequestBody() String id) {
		System.out.println("arrived here");
		MatchDetailAts detail = rtgsService.matchDetailAts(request, id);
		return ResponseEntity.ok(detail);
	}
	@RequestMapping(value = "/rtgs/matched-detail-core/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> matchDetailCore(HttpServletRequest request, @PathVariable Long id,@RequestParam("type") String type,
			@RequestParam("dr_cr") String dr_cr,@RequestParam("reference") String reference) {
		System.out.println("type="+type);
		System.out.println("dr_cr="+dr_cr);
		System.out.println("addional_information="+reference	);
				MatchDetailCore detail = rtgsService.MatchDetailCore(request, id,type,dr_cr,reference);
		return ResponseEntity.ok(detail);

	}

	@RequestMapping(value = "get_all_ats_matched_with_reason/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_all_ats_matched_with_reason(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(rtgsService.get_all_ats_matched_with_reason(request, recon_date));

	}

	@RequestMapping(value = "get_all_core_matched_with_reason/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_all_core_matched_with_reason(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(rtgsService.get_all_core_matched_with_reason(request, recon_date));

	}
	@RequestMapping(value = "get_payable_credit_matched_with_reason", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_payable_credit_matched_with_reason(HttpServletRequest request) {
		return APIResponse.response(rtgsService.get_payable_credit_matched_with_reason(request));

	}
	@RequestMapping(value = "get_payable_debit_matched_with_reason", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_payable_debit_matched_with_reason(HttpServletRequest request) {
		return APIResponse.response(rtgsService.get_payable_debit_matched_with_reason(request));

	}
	///////////////////////////////////


	@RequestMapping(value = "/rtgs/match_detail_fixed_core/{id}", method = RequestMethod.GET)
	public ResponseEntity<Transactionhistory> matchDetailFixedCore(HttpServletRequest request, @PathVariable Long id) {
		Transactionhistory detail = rtgsService.matchDetailFixedCore(request, id);
		return ResponseEntity.ok(detail);
			}

	@RequestMapping(value = "/rtgs/match_detail_stock_core/{id}", method = RequestMethod.GET)
	public ResponseEntity<Transactionhistory> matchDetailStockCore(HttpServletRequest request, @PathVariable Long id) {
		Transactionhistory detail = rtgsService.matchDetailStockCore(request, id);
		return ResponseEntity.ok(detail);
			}
	
	@RequestMapping(value = "/rtgs/match_detail_fixed_mms/{id}", method = RequestMethod.GET)
	public ResponseEntity<Transactionhistory> matchDetailFixed_mms(HttpServletRequest request, @PathVariable Long id) {
		try{
			Transactionhistory detail = rtgsService.matchDetailFixedMms(request, id);
			return ResponseEntity.ok(detail);

		}catch(Exception ex){
			System.out.println(ex);
			return null;


		}


	}
	
	@RequestMapping(value = "/rtgs/match_detail_stock_mms/{id}", method = RequestMethod.GET)
	public ResponseEntity<Transactionhistory> matchDetailStock_mms(HttpServletRequest request, @PathVariable Long id) {
		try{
			Transactionhistory detail = rtgsService.matchDetailStockMms(request, id);
			return ResponseEntity.ok(detail);

		}catch(Exception ex){
			System.out.println(ex);
			return null;


		}


	}

	}

