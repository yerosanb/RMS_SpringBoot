package com.example.demo.abebayehu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.abebayehu.entity.Mms_trial_balance;
import com.example.demo.abebayehu.service.FixedAssetService;
import com.example.demo.response.APIResponse;

@RestController
@RequestMapping("api/fixedAsset/recon/")
public class FixedAssetController {

	@Autowired

	private FixedAssetService fixedAssetService;

	@RequestMapping(value = "get_raw_fixed_core_for_recon_computer", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_raw_fixed_core_for_recon(HttpServletRequest request) {
		return APIResponse.response(fixedAssetService.get_raw_fixed_core_for_recon_computer(request));
	}
	
	@RequestMapping(value = "get_raw_fixed_core_for_recon_equipment", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_raw_fixed_core_for_recon_equipment(HttpServletRequest request) {
		return APIResponse.response(fixedAssetService.get_raw_fixed_core_for_recon_equipment(request));
	}
    
	@RequestMapping(value = "get_raw_fixed_core_for_recon_vehicle", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_raw_fixed_core_for_recon_vehicle(HttpServletRequest request) {
		return APIResponse.response(fixedAssetService.get_raw_fixed_core_for_recon_vehicle(request));
	}
    	
	@RequestMapping(value = "get_raw_fixed_core_for_recon_furniture", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_raw_fixed_core_for_recon_furniture(HttpServletRequest request) {
		return APIResponse.response(fixedAssetService.get_raw_fixed_core_for_recon_furniture(request));
	}
    
	@RequestMapping(value = "get_raw_fixed_mms_for_recon_computer/{transaction_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_raw_fixed_mms_for_recon(HttpServletRequest request,
			@PathVariable("transaction_date") String transaction_date) {
		return APIResponse.response(fixedAssetService.get_raw_fixed_mms_for_recon_computer(request, transaction_date));
	}
	
	@RequestMapping(value = "get_raw_fixed_mms_for_recon_equipment/{transaction_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_raw_fixed_mms_for_recon_equipment(HttpServletRequest request,
			@PathVariable("transaction_date") String transaction_date) {
		return APIResponse.response(fixedAssetService.get_raw_fixed_mms_for_recon_equipment(request, transaction_date));
	}
	
	@RequestMapping(value = "get_raw_fixed_mms_for_recon_furniture/{transaction_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_raw_fixed_mms_for_recon_furniture(HttpServletRequest request,
			@PathVariable("transaction_date") String transaction_date) {
		return APIResponse.response(fixedAssetService.get_raw_fixed_mms_for_recon_furniture(request, transaction_date));
	}
	
	@RequestMapping(value = "get_raw_fixed_mms_for_recon_vehicle/{transaction_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_raw_fixed_mms_for_recon_vehicle(HttpServletRequest request,
			@PathVariable("transaction_date") String transaction_date) {
		return APIResponse.response(fixedAssetService.get_raw_fixed_mms_for_recon_vehicle(request, transaction_date));
	}

	@RequestMapping(value = "match_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactions(HttpServletRequest request,
			@RequestBody List<List<Long>> matched_fixed_asset_ids) {
		return APIResponse.response(fixedAssetService.match_transactions(request, matched_fixed_asset_ids));
	}
	@RequestMapping(value = "match_reversal_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_reversal_transactions(HttpServletRequest request,
			@RequestBody List<List<Long>> matched_fixed_asset_ids) {
		return APIResponse.response(fixedAssetService.match_transactions_reversal(request, matched_fixed_asset_ids));
	}

	@RequestMapping(value = "match_transactions_with_reason", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactions_with_reason(HttpServletRequest request,
			@RequestBody() List<ArrayList> matched_fixed_asset_ids) {
		return APIResponse.response(fixedAssetService.match_transactions_with_reason(request, matched_fixed_asset_ids));
	}
	
	@RequestMapping(value = "delete_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> delete_transactions(HttpServletRequest request, @RequestBody() String datas) {
		return APIResponse.response(fixedAssetService.delete_transactions(request, datas));
	}
	
	@RequestMapping(value = "get-deleted-fixed-core", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_fixed_core(HttpServletRequest request) {
		return APIResponse.response(fixedAssetService.get_edited_fixed_core(request));
	}
	
	@RequestMapping(value = "get-edited-detail-fixed-core/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_detail_fixed_core(HttpServletRequest request, @PathVariable("id") Long id) {
		return APIResponse.response(fixedAssetService.get_edited_detail_fixed_core(request, id));
	}
	
	@RequestMapping(value = "get-deleted-fixed-mms", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_fixed_mms(HttpServletRequest request) {
		return APIResponse.response(fixedAssetService.get_edited_fixed_mms(request));
	}
	
	@RequestMapping(value = "get-edited-detail-fixed-mms/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_detail_fixed_mms(HttpServletRequest request, @PathVariable("id") Long id) {
		return APIResponse.response(fixedAssetService.get_edited_detail_fixed_mms(request, id));
	}
	
	@RequestMapping(value = "getInitialFixedAssetEndingBalances", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getInitialFixedAssetEndingBalances(HttpServletRequest request) {
		return APIResponse.response(fixedAssetService.getInitialFixedAssetEndingBalances(request));
	}
	@RequestMapping(value = "getInitialStockEndingBalances", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getInitialStockEndingBalances(HttpServletRequest request) {
		return APIResponse.response(fixedAssetService.getInitialStockEndingBalances(request));
	}
	
	@RequestMapping(value = "updateFixedAssetBalance", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> updateFixedAssetBalance(@RequestBody Mms_trial_balance req_info, HttpServletRequest request) {
		return APIResponse.response(fixedAssetService.updateFixedAssetBalance(request, req_info));
	}
	
	@RequestMapping(value = "changeDate", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> change_date(
			@RequestParam("disposed_date") String disposed_date, @RequestParam("waiting_date") String waiting_date,
			@RequestParam("removed_date") String removed_date, HttpServletRequest request) throws IOException {
		return APIResponse.response(fixedAssetService.change_date(disposed_date, waiting_date, removed_date, request));
	}
	
}