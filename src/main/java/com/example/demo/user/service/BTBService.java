package com.example.demo.user.service;
/*
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ExceptionsList;
import com.example.demo.user.mapper.MapperAccount;
import com.example.demo.user.mapper.MapperBTB;
import com.example.demo.user.mapper.MapperRTGS;
import com.example.demo.user.model.File_rtgs_awb_core;
import com.example.demo.user.model.File_rtgs_nbe_ats;
import com.example.demo.utils.Utils;
@Service
public class BTBService {
	@Autowired
	private Utils util;

	@Autowired
	private MapperBTB btbMapper;
	
	@Autowired
	private MapperAccount accountMapper;
	
	public List<File_rtgs_nbe_ats> get_ats_for_btb_recon(HttpServletRequest request, String recon_date) {


				util.registerActivity(request, "Get all Rtgs Ats For Recon", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
//				List<File_rtgs_nbe_ats> atsList = rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
//						accountMapper.getUserAccountId(util.get_user_id(request)));
//				for(File_rtgs_nbe_ats aa : atsList) {
//					if(aa.getId() == 98473l){
//						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
//					}
//				}
				return btbMapper.get_ats_for_recon(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public List<File_rtgs_awb_core> get_core_for_btb_recon(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all_rtgs_core_for_recon")) {
				util.registerActivity(request, "Get all Rtgs Core Data For Recon", "-");
				return btbMapper.get_core_for_recon(accountMapper.getUserAccountId(util.get_user_id(request)));
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

}
*/