package com.example.demo.user.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.APIResponse;
import com.example.demo.user.service.ERCAService;

@RestController
@RequestMapping("api/user/recon/erca/")
public class ERCAController {
	@Autowired
	private ERCAService ercaService;
	
//	@Autowired
//	private RTGSService rtgsService;

	@RequestMapping(value = "delete_erca/{id}", method = RequestMethod.DELETE)
	public void deleteAccountRequest( @PathVariable("id") List<Long> id) throws ParseException {
		System.out.println("helloooooooooo");
		ercaService.deleteReconItems(id);
	}

	@RequestMapping(value = "get_erca_ats_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity <Object> get_ats_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(ercaService.get_erca_ats_for_view(request, recon_date));
		
	}
	
	@RequestMapping(value = "get_erca__for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get__for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(ercaService.get_erca__for_view(request, recon_date));
	}
	
//	 to  
	
	@RequestMapping(value = "get_btb_ats_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity <Object> get_btb_ats_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(ercaService.get_btb_ats_for_view(request, recon_date));
		
	}
	
	@RequestMapping(value = "get_btb__for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity <Object> get_btb__for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(ercaService.get_btb__for_view(request, recon_date));	
	}	
	

///////settlement of surrender
	
	
	@RequestMapping(value = "get_sos_ats_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity <Object> get_sos_ats_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(ercaService.get_sos_ats_for_view(request, recon_date));
		
	}
	
	@RequestMapping(value = "get_sos__for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity <Object> get_sos__for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(ercaService.get_sos__for_view(request, recon_date));
		
	}
	
	
	@RequestMapping(value = "get_all_ats_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity <Object> get_all_ats_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(ercaService.get_all_ats_for_view(request, recon_date));
		
	}
	@RequestMapping(value = "get_all__for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity <Object> get_all__for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(ercaService.get_all__for_view(request, recon_date));
		
	}
	@RequestMapping(value = "get_all_ats_unmatched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_all_ats_unmatched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(ercaService.get_all_ats_unmatched_for_view(request, recon_date));
	}
	@RequestMapping(value = "get_all__unmatched_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_all_s_unmatched_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(ercaService.get_all__unmatched_for_view(request, recon_date));
	}
	
	//===================================== get all unmatch payable transaction  start ==============================
	@RequestMapping(value = "get_payable_credit_unmatched_for_view", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_payable_credit_unmatched_for_view(HttpServletRequest request) {
		return APIResponse.response(ercaService.get_payable_credit_unmatched_for_view(request));
	}
	@RequestMapping(value = "get_payable_debit_unmatched_for_view", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_payable_debit_unmatched_for_view(HttpServletRequest request) {
		return APIResponse.response(ercaService.get_payable_debit_unmatched_for_view(request));
	}
	//===================================== ending  get all unmatch  payyable transaction ===============================
	@RequestMapping(value = "get_all_ats_partial_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_all_ats_partial_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(ercaService.get_all_ats_partial_for_view(request, recon_date));
	}
	@RequestMapping(value = "get_all__partial_for_view/{recon_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_all__partial_for_view(HttpServletRequest request,
			@PathVariable("recon_date") String recon_date) {
		return APIResponse.response(ercaService.get_all__partial_for_view(request, recon_date));
	}
	
	@RequestMapping(value = "unmatch_erca_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(ercaService.unmatch_erca_transactions(request, data_ids));
	}
	 
	@RequestMapping(value = "unmatch_btb_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_Btb_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(ercaService.unmatch_btb_transactions(request, data_ids));
	}
	 
	 
	@RequestMapping(value = "unmatch_sos_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_sos_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(ercaService.unmatch_sos_transactions(request, data_ids));
	}
	
	 
	@RequestMapping(value = "unmatch_all_partial_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_all_partial_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(ercaService.unmatch_all_partial_transactions(request, data_ids));
	}
		 
		
	


	
////////////////////////////////////////////////////////////////////////

	

}
