package com.example.demo.garama.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.garama.services.FixedAutReconService;
import com.example.demo.response.APIResponse;
import com.example.demo.user.model.GeneralTransactionHistory;
import com.example.demo.user.model.Transactionhistory;

@RestController
@RequestMapping("api/Fixed/recon/")
public class FixedAutReconController {

	@Autowired
	FixedAutReconService fixedService;

	@RequestMapping(value = "get_mms_for_recon_auto_computer/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_fixed_mms_for_recon_auto(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_fixed_mms_for_recon_auto_computer(request, recon_date));
	}
	@RequestMapping(value = "get_mms_for_recon_auto_furniture/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_fixed_mms_for_recon_auto_furniture(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_fixed_mms_for_recon_auto_furniture(request, recon_date));
	}
	
	@RequestMapping(value = "get_mms_for_recon_auto_equipment/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_fixed_mms_for_recon_auto_equipment(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_fixed_mms_for_recon_auto_equipment(request, recon_date));
	}

	@RequestMapping(value = "get_mms_for_recon_auto_vehicle/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_fixed_mms_for_recon_auto_vehicle(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_fixed_mms_for_recon_auto_vehicle(request, recon_date));
	}
	
	@RequestMapping(value = "get__for_recon_auto_computer/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_fixed__for_recon_auto_computer(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_fixed__for_recon_auto_computer(request, recon_date));
	}
	@RequestMapping(value = "get__for_recon_auto_equipment/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_fixed__for_recon_auto_equipment(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_fixed__for_recon_auto_equipment(request, recon_date));
	}
	
	@RequestMapping(value = "get__for_recon_auto_vehicle/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_fixed__for_recon_auto_vehicle(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_fixed__for_recon_auto_vehicle(request, recon_date));
	}
	
	@RequestMapping(value = "get__for_recon_auto_furniture/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_fixed__for_recon_auto_furniture(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_fixed__for_recon_auto_furniture(request, recon_date));
	}

	@RequestMapping(value = "match_single_transaction", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		System.out.println("to match:" + data_ids);
		return APIResponse.response(fixedService.match_transactions(request, data_ids));
	}

	@RequestMapping(value = "match_all_transactions/{recon_date}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_all_transactions(HttpServletRequest request, @RequestBody() String data_ids,
			@PathVariable("recon_date") String recon_date) {
		System.out.println(data_ids);
		return APIResponse.response(fixedService.match_all_transactions(request, data_ids, recon_date));
	}

	@RequestMapping(value = "get_fixed_mms_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_fixed_mms_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_fixed_mms_for_view(request, recon_date));
	}

	@RequestMapping(value = "get_fixed__for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")

	public ResponseEntity<Object> get_fixed__for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_fixed__for_view(request, recon_date));
	}

	@RequestMapping(value = "unmatch_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(fixedService.unmatch_transactions(request, data_ids));
	}

	@RequestMapping(value = "get_unmatched_fixed_mms_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_unmatched_fixed_mms_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_unmatched_fixed_mms_for_view(request, recon_date));
	}

	@RequestMapping(value = "get_unmatched_fixed__for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_unmatched_fixed__for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_unmatched_fixed__for_view(request, recon_date));
	}

//	public ResponseEntity<Object> get_fixed__for_view(HttpServletRequest request,
//			@PathVariable("recon_date") String recon_date) {
//		return APIResponse.response(fixedService.get_fixed__for_view(request, recon_date));
//	}

	@RequestMapping(value = "get_fixed_mms_for_view_reason/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_fixed_mms_for_view_reason(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		System.out.println("__________________recon_datemms" + recon_date);
		return APIResponse.response(fixedService.get_fixed_mms_for_view_reason(request, recon_date));
	}

	@RequestMapping(value = "get_fixed__for_view_reason/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_fixed__for_view_reason(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		System.out.println("__________________=recon_date" + recon_date);
		return APIResponse.response(fixedService.get_fixed__for_view_reason(request, recon_date));
	}

//	@RequestMapping(value = "unmatch_transactions", method = RequestMethod.POST, produces = "application/json")
//	public ResponseEntity<Boolean> unmatch_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
//		return APIResponse.response(fixedService.unmatch_transactions(request, data_ids));
//	}
	
	
	//===========================================================================================

	@RequestMapping(value = "get_computer_mms_unmatched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_computer_mms_unmatched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_computer_mms_unmatched_for_view(request, recon_date));
	}

	@RequestMapping(value = "get_computer__unmatched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_computer__unmatched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_computer__unmatched_for_view(request, recon_date));
	}
	@RequestMapping(value = "get_furniture_mms_unmatched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_furniture_mms_unmatched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_furniture_mms_unmatched_for_view(request, recon_date));
	}

	@RequestMapping(value = "get_furniture__unmatched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_furniture__unmatched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_furniture__unmatched_for_view(request, recon_date));
	}
	@RequestMapping(value = "get_equipment_mms_unmatched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_equipment_mms_unmatched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_equipment_mms_unmatched_for_view(request, recon_date));
	}

	@RequestMapping(value = "get_equipment__unmatched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_equipment__unmatched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_equipment__unmatched_for_view(request, recon_date));
	}
	@RequestMapping(value = "get_vehicle_mms_unmatched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_vehicle_mms_unmatched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_vehicle_mms_unmatched_for_view(request, recon_date));
	}

	@RequestMapping(value = "get_vehicle__unmatched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_vehicle__unmatched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_vehicle__unmatched_for_view(request, recon_date));
	}
	@RequestMapping(value = "get_computer_mms_matched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_computer_mms_matched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_computer_mms_matched_for_view(request, recon_date));
	}

	@RequestMapping(value = "get_computer__matched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_computer__matched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_computer__matched_for_view(request, recon_date));
	}
	@RequestMapping(value = "get_furniture_mms_matched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_furniture_mms_matched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_furniture_mms_matched_for_view(request, recon_date));
	}

	@RequestMapping(value = "get_furniture__matched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_furniture__matched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_furniture__matched_for_view(request, recon_date));
	}
	@RequestMapping(value = "get_equipment_mms_matched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_equipment_mms_matched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_equipment_mms_matched_for_view(request, recon_date));
	}

	@RequestMapping(value = "get_equipment__matched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_equipment__matched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_equipment__matched_for_view(request, recon_date));
	}
	@RequestMapping(value = "get_vehicle_mms_matched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_vehicle_mms_matched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_vehicle_mms_matched_for_view(request, recon_date));
	}

	@RequestMapping(value = "get_vehicle__matched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_vehicle__matched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_vehicle__matched_for_view(request, recon_date));
	}
	@RequestMapping(value = "view_mms_waiting", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> View_mms_wating(HttpServletRequest request, @RequestBody() String dates) {
		System.out.println("this is the mms_remmmmmmmmmmmmmmmmmmmmmmmoved");
		return APIResponse.response(fixedService.View_mms_wating(request, dates));
	}
	
	@RequestMapping(value = "view_mms_disposed", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> View_mms_disposed(HttpServletRequest request, @RequestBody() String dates) {
		System.out.println("this is the mms_remmmmmmmmmmmmmmmmmmmmmmmoved");
		return APIResponse.response(fixedService.View_mms_disposed(request, dates));
	}
	
	@RequestMapping(value = "view_mms_removed", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> View_mms_removed(HttpServletRequest request, @RequestBody() String dates) {
		System.out.println("this is the mms_remmmmmmmmmmmmmmmmmmmmmmmoved");
		return APIResponse.response(fixedService.View_mms_removed(request, dates));
	}
	
	@RequestMapping(value = "get__reversal_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get__reversal_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(fixedService.get_Reversal_for_view(request, recon_date));
	}
	
	@RequestMapping(value = "unmatch__reversaltransactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch__reversal_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(fixedService.unmatch__reversal_transactions(request, data_ids));
	}
	
}

