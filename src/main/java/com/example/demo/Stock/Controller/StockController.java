package com.example.demo.Stock.Controller;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Stock.Service.StockService;
import com.example.demo.response.APIResponse;

@RestController
@RequestMapping("api/Stock/recon/")
public class StockController {
	@Autowired
	StockService stockService;
	@RequestMapping(value = "get_mms_for_recon_auto", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_stock_mms_for_recon_auto_Accessory(HttpServletRequest request,
			@RequestBody() String datas) {
		return APIResponse.response(stockService.get_stock_mms_for_recon_auto(request, datas));
	}

	@RequestMapping(value = "get_core_for_recon_auto", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_fixed_core_for_recon_auto_computer(HttpServletRequest request,
			@RequestBody() String datas) {
		return APIResponse.response(stockService.get_fixed_core_for_recon_auto(request, datas));
	}
	
	@RequestMapping(value = "match_single_transaction", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		System.out.println("to match:" + data_ids);
		return APIResponse.response(stockService.match_transactions(request, data_ids));
	}

	@RequestMapping(value = "match_all_transactions/{recon_date}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_all_transactions(HttpServletRequest request, @RequestBody() String data_ids,
			@PathVariable("recon_date") String recon_date) {
		System.out.println(data_ids);
		return APIResponse.response(stockService.match_all_transactions(request, data_ids, recon_date));
	}
	@RequestMapping(value = "get_stock_mms_matched_for_view", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_stock_mms_matched_for_view(HttpServletRequest request,
			@RequestBody() String datas) {
		return APIResponse.response(stockService.get_stock_mms_matched_for_view(request, datas));
	}

	@RequestMapping(value = "get_stock_core_matched_for_view", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_stock_core_matched_for_view(HttpServletRequest request,
			@RequestBody() String datas) {
		return APIResponse.response(stockService.get_stock_core_matched_for_view(request, datas));
	}
	@RequestMapping(value = "get_stock_mms_unmatched_for_view", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_stock_mms_unmatched_for_view(HttpServletRequest request,
			@RequestBody() String datas) {
		return APIResponse.response(stockService.get_stock_mms_unmatched_for_view(request, datas));
	}

	@RequestMapping(value = "get_stock_core_unmatched_for_view", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_stock_core_unmatched_for_view(HttpServletRequest request,
			@RequestBody() String datas) {
		return APIResponse.response(stockService.get_stock_core_unmatched_for_view(request, datas));
	}
	@RequestMapping(value = "get_stock_core_reversal/{c_date}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_stock_core_reversal(HttpServletRequest request,
			@PathVariable String c_date) {
		return APIResponse.response(stockService.get_stock_core_reversal(request, c_date));
	}
	
//	added by Demeke
	@RequestMapping(value = "get_stock_core_for_recon_manual", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_stock_core_for_recon_manual(HttpServletRequest request,
			@RequestBody() String datas) {
		return APIResponse.response(stockService.get_stock_core_for_recon_manual(request, datas));
	}
	
	@RequestMapping(value = "get_stock_mms_for_recon_manual", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_stock_mms_for_recon_manual(HttpServletRequest request,
			@RequestBody() String datas) {
		return APIResponse.response(stockService.get_stock_mms_for_recon_manual(request, datas));
	}
	
	@RequestMapping(value = "match_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactions(HttpServletRequest request,
			@RequestBody List<List<Long>> matched_ids) {
		return APIResponse.response(stockService.match_transaction(request, matched_ids));
	}
	
	@RequestMapping(value = "match_transactions_with_reason", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_transactions_with_reason(HttpServletRequest request,
			@RequestBody() List<ArrayList> matched_ids) {
		return APIResponse.response(stockService.match_transactions_with_reason(request, matched_ids));
	}
	
	@RequestMapping(value = "delete_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> delete_transactions(HttpServletRequest request, @RequestBody() String datas) {
		return APIResponse.response(stockService.delete_transactions(request, datas));
	}
	
	@RequestMapping(value = "match_reversal_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> match_reversal_transactions(HttpServletRequest request,
			@RequestBody List<List<Long>> matched_stock_ids) {
		return APIResponse.response(stockService.match_transactions_reversal(request, matched_stock_ids));
	}
	
	@RequestMapping(value = "get_stock_mms_matched_with_reason", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_stock_mms_matched_with_reason(HttpServletRequest request,
			@RequestBody() String datas) {
		return APIResponse.response(stockService.get_stock_mms_matched_with_reason(request, datas));
	}

	@RequestMapping(value = "get_stock_core_matched_with_reason", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Object> get_stock_core_matched_with_reason(HttpServletRequest request,
			@RequestBody() String datas) {
		return APIResponse.response(stockService.get_stock_core_matched_with_reason(request, datas));
	}
//	added by Demeke

	@RequestMapping(value = "unmatch_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(stockService.unmatch_transactions(request, data_ids));
	}
	@RequestMapping(value = "unmatch_reversal_transactions", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> unmatch_reversal_transactions(HttpServletRequest request, @RequestBody() String data_ids) {
		return APIResponse.response(stockService.unmatch_reversal_transactions(request, data_ids));
	}
	@RequestMapping(value = "get-deleted-stock-core", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_stock_core(HttpServletRequest request) {
		return APIResponse.response(stockService.get_edited_stock_core(request));
	}
	@RequestMapping(value = "get-edited-detail-stock-core/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_detail_stock_core(HttpServletRequest request, @PathVariable("id") Long id) {
		return APIResponse.response(stockService.get_edited_detail_stock_core(request, id));
	}
	
	@RequestMapping(value = "get-deleted-stock-mms", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_stock_mms(HttpServletRequest request) {
		return APIResponse.response(stockService.get_edited_stock_mms(request));
	}
	@RequestMapping(value = "get-edited-detail-stock-mms/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> get_edited_detail_stock_mms(HttpServletRequest request, @PathVariable("id") Long id) {
		return APIResponse.response(stockService.get_edited_detail_stock_mms(request, id));
	}

}
