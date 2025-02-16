package com.example.demo.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ExceptionsList;
import com.example.demo.user.mapper.ErcaMapper;
import com.example.demo.user.model.File_erca_;
import com.example.demo.user.model.File_erca_;
import com.example.demo.utils.Utils;

@Service
public class AutomaticErcaService {
	@Autowired
	private Utils util;
	@Autowired
	private  ErcaMapper autoErcaMapper;
	String value_date_type="";
	float amount=0;
	private List<Float> amounts;
	private List<String> value_date_types;
	
	
	public List<File_erca_> get_erca_for_AutoMatch(HttpServletRequest request) {
		
		try {
			if (util.isPermitted(request, "User", "get_all_erca_for_auto_match")) {
				util.registerActivity(request, "Automatic match Erca  transaction", "-");
				return autoErcaMapper.get_erca__for_recon(value_date_type);
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public List<File_erca_> get_erca__for_AutoMatch(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all_erca__for_auto_match")) {
				util.registerActivity(request, "Automatic match transaction", "-");
				return autoErcaMapper.get_erca__for_recon(amount);
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

}
