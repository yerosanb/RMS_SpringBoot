package com.example.demo.user.remark;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.APIResponse;

@RestController
@RequestMapping("/api/user/")
public class ReplayRemarkController {
	@Autowired
	private remarkService remarkService;

	@RequestMapping(value = "replay_currency_remark", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> ReplayCurrencyRemark(HttpServletRequest request,
			@Validated @RequestBody remarkModel remark) {
		System.out.println("*******************"+remark.getTitle());
		return APIResponse.response(remarkService.replaycurrencyRemark(remark, request));
	}

}
