package com.example.demo.user.remark;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.APIResponse;

@RestController
@RequestMapping("/api/user/")
public class remarkController {
	
	@Autowired
	private remarkService remarkService;

	@RequestMapping(value = "view_user_remark", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllUserRemarks(HttpServletRequest request) {
		return APIResponse.response(remarkService.getUserRemarks(request));
	}
	
	@RequestMapping(value = "delete_currency_remark/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Long id) throws ParseException {
		remarkService.deleteCurrencyRemark(id);
	}
	@RequestMapping(value = "GetTotalAccountrmark", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> GetTotalCurrencyrmark(HttpServletRequest request) {
		long total=remarkService.totalaccountRemark(request);
		Map res = new HashMap<>();
		res.put("total", total);
		System.out.println("total="+res);
		return APIResponse.response(res);   


	}
}