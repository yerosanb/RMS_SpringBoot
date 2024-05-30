package com.example.demo.user.remark;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/api/user")
public class TotalRemark {
	@Autowired
	private remarkService remarkService;
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	public long getAllUserRemarks(HttpServletRequest request) {
		return remarkService.totalcurrencyRemark(request);
	}
}
