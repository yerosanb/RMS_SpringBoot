package com.example.demo.user.remark;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

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

@RestController
@RequestMapping("/api/user/")
public class AccountRemarkController {
	@Autowired
	private remarkService remarkService;

	@RequestMapping(value = "view_account_remark", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getAllAccountRemarks(HttpServletRequest request) {
		return APIResponse.response(remarkService.getAccountRemarks(request));

	}

	@RequestMapping(value = "replay_account_remark", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> ReplayCurrencyRemark(HttpServletRequest request,
			@Validated @RequestBody remarkModel remark) {
		return APIResponse.response(remarkService.replayAccountRemark(remark, request));
	}

	@RequestMapping(value = "delete_account_remark/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Long id) throws ParseException {
		remarkService.deleteaccountRemark(id);
	}

	@RequestMapping(value = "email", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getEmail(HttpServletRequest request) {
		String email = remarkService.getEmail(request);
		Map res = new HashMap<>();
		res.put("email", email);
		System.out.println("email=" + res);
		return APIResponse.response(res);

	}

	@RequestMapping(value = "GetTotalCurrencyrmark", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> GetTotalCurrencyrmark(HttpServletRequest request) {
		long total = remarkService.totalcurrencyRemark(request);
		Map res = new HashMap<>();
		res.put("total", total);
		System.out.println("total=" + res);
		return APIResponse.response(res);

	}

	@RequestMapping(value = "get_remark_byId/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Object> getRemarkById(@PathVariable Long id) {
		System.out.print("Get Remark byId");
		return APIResponse.response(remarkService.getRemarkyId(id));

	}

	@RequestMapping(value = "update_remark/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> updatRemark(@PathVariable Long id, @RequestBody remarkModel remarkModel) {
		return APIResponse.response(remarkService.updateremarkById(id, remarkModel));

	}

}
