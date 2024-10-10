package com.example.demo.user.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ExceptionsList;
import com.example.demo.user.mapper.ErcaMapper;
import com.example.demo.user.mapper.MapperAccount;
import com.example.demo.user.mapper.MapperRTGS;
import com.example.demo.user.model.File_erca_;
import com.example.demo.user.model.File_erca_;
import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;
import com.example.demo.utils.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class ERCAService {
	@Autowired
	private Utils util;
	@Autowired
	private ErcaMapper ercaMapper;
	@Autowired
	private MapperAccount accountMapper;
	@Autowired
	private MapperRTGS rtgsMapper;

	public void deleteReconItems(List<Long> id) {
		System.out.println("serviceeeeee deleteeee reconnnnnnnnnnnnnn");
		for (int i = 0; i < id.size(); i++) {
			ercaMapper.deleteReconItems(id.get(i));

		}

	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public List<File_rtgs__ats> get_erca_ats_for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_erca_ats_matched")) {
				util.registerActivity(request, "Get erca ats matched transaction", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
//				List<File_rtgs__ats> atsList = rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
//						accountMapper.getUserAccountId(util.get_user_id(request)));
//				for(File_rtgs__ats aa : atsList) {
//					if(aa.getId() == 98473l){
//						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
//					}
//				}
				return ercaMapper.get_erca_ats_for_view(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_erca__for_view(HttpServletRequest request, String recon_date) {
		try {
			if (util.isPermitted(request, "User", "get_erca__matched")) {
				util.registerActivity(request, "Get erca  matched transaction", "-");
				return ercaMapper.get_erca__for_view(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__ats> get_btb_ats_for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_b2b__matched")) {
				util.registerActivity(request, "Get  to  ats matched transaction", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
//				List<File_rtgs__ats> atsList = rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
//						accountMapper.getUserAccountId(util.get_user_id(request)));
//				for(File_rtgs__ats aa : atsList) {
//					if(aa.getId() == 98473l){
//						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
//					}
//				}
				return ercaMapper.get_btb_ats_for_view(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_btb__for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_b2b_ats_matched")) {
				util.registerActivity(request, "Get  to  ats matched transaction", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
//				List<File_rtgs__ats> atsList = rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
//						accountMapper.getUserAccountId(util.get_user_id(request)));
//				for(File_rtgs__ats aa : atsList) {
//					if(aa.getId() == 98473l){
//						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
//					}
//				}
				return ercaMapper.get_btb__for_view(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__ats> get_sos_ats_for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_sos_ats_matched")) {
				util.registerActivity(request, "Get sos ats matched transaction", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
//				List<File_rtgs__ats> atsList = rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
//						accountMapper.getUserAccountId(util.get_user_id(request)));
//				for(File_rtgs__ats aa : atsList) {
//					if(aa.getId() == 98473l){
//						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
//					}
//				}
				return ercaMapper.get_sos_ats_for_view(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_sos__for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_sos__matched")) {
				util.registerActivity(request, "Get sos  matched transaction", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
//				List<File_rtgs__ats> atsList = rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
//						accountMapper.getUserAccountId(util.get_user_id(request)));
//				for(File_rtgs__ats aa : atsList) {
//					if(aa.getId() == 98473l){
//						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
//					}
//				}
				return ercaMapper.get_sos__for_view(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__ats> get_all_ats_for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_all_matched_ats")) {
				util.registerActivity(request, "Get all matched Ats", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
//				List<File_rtgs__ats> atsList = rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
//						accountMapper.getUserAccountId(util.get_user_id(request)));
//				for(File_rtgs__ats aa : atsList) {
//					if(aa.getId() == 98473l){
//						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
//					}
//				}
				return ercaMapper.get_all_ats_for_view(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_all__for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_all_matched_")) {
				util.registerActivity(request, "Get all matched ", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
//				List<File_rtgs__ats> atsList = rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
//						accountMapper.getUserAccountId(util.get_user_id(request)));
//				for(File_rtgs__ats aa : atsList) {
//					if(aa.getId() == 98473l){
//						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
//					}
//				}
				return ercaMapper.get_all__for_view(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__ats> get_all_ats_unmatched_for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_all_unmatch_ats")) {
				util.registerActivity(request, "Get all unmatch Ats", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
//				List<File_rtgs__ats> atsList = rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
//						accountMapper.getUserAccountId(util.get_user_id(request)));
//				for(File_rtgs__ats aa : atsList) {
//					if(aa.getId() == 98473l){
//						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
//					}
//				}
				return ercaMapper.get_all_ats_unmatched_for_view(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_all__unmatched_for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_all_unmatch_")) {
				util.registerActivity(request, "Get all  unmatch transaction ", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
//				List<File_rtgs__ats> atsList = rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
//						accountMapper.getUserAccountId(util.get_user_id(request)));
//				for(File_rtgs__ats aa : atsList) {
//					if(aa.getId() == 98473l){
//						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
//					}
//				}
				return ercaMapper.get_all__unmatched_for_view(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__ats> get_all_ats_partial_for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_all_partial_ats")) {
				util.registerActivity(request, "Get all partial ats", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
//				List<File_rtgs__ats> atsList = rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
//						accountMapper.getUserAccountId(util.get_user_id(request)));
//				for(File_rtgs__ats aa : atsList) {
//					if(aa.getId() == 98473l){
//						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
//					}
//				}
				return ercaMapper.get_ats_for_view_partial(accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_all__partial_for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_all_partial_")) {
				util.registerActivity(request, "Get all partial  ", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
//				List<File_rtgs__ats> atsList = rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
//						accountMapper.getUserAccountId(util.get_user_id(request)));
//				for(File_rtgs__ats aa : atsList) {
//					if(aa.getId() == 98473l){
//						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
//					}
//				}
				return ercaMapper.get__for_view_partial(accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Boolean unmatch_erca_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "unmatch_transactions_erca")) {
				util.registerActivity(request, "unmatch erca transactions ", "-");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");
				Long[] id_1 = new Long[id_1_string.length];
				for (int i = 0; i < id_1_string.length; i++) {
					id_1[i] = Long.parseLong(id_1_string[i]);
				}

				Long[] id_2 = new Long[id_2_string.length];
				for (int i = 0; i < id_2_string.length; i++) {
					id_2[i] = Long.parseLong(id_2_string[i]);
				}

//				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));

				String matched_id = null;
				Long current_id = null;
				String type = "";
				Long matched_data_id = null;
				for (int i = 0; i < id_2.length; i++) {
					System.out.println(" id=" + id_2[i]);
					current_id = ercaMapper.moveErcaMatched(id_2[i]);
					type = "data__";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);

//					matched_id = rtgsMapper.findMatchedId(id_2[i]);
//					rtgsMapper.deleteMatchTransaction(matched_id);
//					rtgsMapper.updateUnMatchStatus_(id_2[i]);
				}

				for (int i = 0; i < id_1.length; i++) {
					System.out.println("Ats id=" + id_1[i]);
					ercaMapper.deleteErcaMatched(id_1[i]);

					current_id = ercaMapper.moveErcaAtsMatchedToData(id_1[i]);
					type = "data__ats";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);

					// rtgsMapper.deleteUserErcaMatched(id_1[i]);

					rtgsMapper.deleteUserErcaMatched(rtgsMapper.getercaMatchedId(id_1[i]));

//					rtgsMapper.updateUnMatchStatus_ats(id_1[i]);
//					Long ids = rtgsMapper.getRtgsMatchedPartialIdFromRtgsMatchedByMatchId(matched_id);
//					rtgsMapper.deleteUserRtgsMatched(ids);
//					rtgsMapper.addUserRtgsMatched(util.get_user_id(request), 
//							rtgsMapper.addRtgsMatched(id_1[i], match_id, date_now, "1", 
//							id_1.length >1 && id_2.length>1? "1".toString(): "0".toString(), "1", "1"), date_now, "1", "1");
				}

				return true;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Boolean unmatch_btb_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "unmatch_transactions_b2b")) {
				util.registerActivity(request, "unmatch transactions  to ", "-");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");
				Long[] id_1 = new Long[id_1_string.length];
				for (int i = 0; i < id_1_string.length; i++) {
					id_1[i] = Long.parseLong(id_1_string[i]);
				}

				Long[] id_2 = new Long[id_2_string.length];
				for (int i = 0; i < id_2_string.length; i++) {
					id_2[i] = Long.parseLong(id_2_string[i]);
				}

//				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));

				String matched_id = null;
				Long current_id = null;
				String type = "";
				Long matched_data_id = null;
				for (int i = 0; i < id_2.length; i++) {
					System.out.println(" id=" + id_2[i]);
					current_id = ercaMapper.moveBtbMatched(id_2[i]);
					type = "data__";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					System.out.println("reason updated after transaction is unmatched------->" + id_2[i]);
//					matched_id = rtgsMapper.findMatchedId(id_2[i]);
//					rtgsMapper.deleteMatchTransaction(matched_id);
//					rtgsMapper.updateUnMatchStatus_(id_2[i]);
				}

				for (int i = 0; i < id_1.length; i++) {
					System.out.println("Ats id=" + id_1[i]);
					ercaMapper.deleteBtbMatched(id_1[i]);

					rtgsMapper.deleteUserBtbMatched(id_1[i]);
					// System.out.println("========================="+id_1[i]);
					current_id = ercaMapper.moveBtbAtsMatchedToData(id_1[i]);
					type = "data__ats";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					System.out.println("reason updated after transaction is unmatched------->" + id_1[i]);

					rtgsMapper.deleteUserBtbMatched(rtgsMapper.getb2bMatchedId(id_1[i]));

//					rtgsMapper.updateUnMatchStatus_ats(id_1[i]);
//					Long ids = rtgsMapper.getRtgsMatchedPartialIdFromRtgsMatchedByMatchId(matched_id);
//					rtgsMapper.deleteUserRtgsMatched(ids);
//					rtgsMapper.addUserRtgsMatched(util.get_user_id(request), 
//							rtgsMapper.addRtgsMatched(id_1[i], match_id, date_now, "1", 
//							id_1.length >1 && id_2.length>1? "1".toString(): "0".toString(), "1", "1"), date_now, "1", "1");
				}

				return true;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Boolean unmatch_sos_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "unmatch_transactions_sos")) {
				util.registerActivity(request, "unmatch  sos transactions ", "-");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");
				Long[] id_1 = new Long[id_1_string.length];
				for (int i = 0; i < id_1_string.length; i++) {
					id_1[i] = Long.parseLong(id_1_string[i]);
				}

				Long[] id_2 = new Long[id_2_string.length];
				for (int i = 0; i < id_2_string.length; i++) {
					id_2[i] = Long.parseLong(id_2_string[i]);
				}

//				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));

				String matched_id = null;
				Long current_id = null;
				String type = "";
				Long matched_data_id = null;
				for (int i = 0; i < id_2.length; i++) {
					System.out.println(" id=" + id_2[i]);
					current_id = ercaMapper.moveSosMatched(id_2[i]);
					type = "data__";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);

//					matched_id = rtgsMapper.findMatchedId(id_2[i]);
//					rtgsMapper.deleteMatchTransaction(matched_id);
//					rtgsMapper.updateUnMatchStatus_(id_2[i]);
				}

				for (int i = 0; i < id_1.length; i++) {
					System.out.println("Ats id=" + id_1[i]);
					ercaMapper.deleteSosMatched(id_1[i]);

					current_id = ercaMapper.moveSosAtsMatchedToData(id_1[i]);
					type = "data__ats";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					rtgsMapper.deleteUserSosMatched(id_1[i]);
					rtgsMapper.deleteUserSosMatched(rtgsMapper.getsosMatchedId(id_1[i]));

//					rtgsMapper.updateUnMatchStatus_ats(id_1[i]);
//					Long ids = rtgsMapper.getRtgsMatchedPartialIdFromRtgsMatchedByMatchId(matched_id);
//					rtgsMapper.deleteUserRtgsMatched(ids);
//					rtgsMapper.addUserRtgsMatched(util.get_user_id(request), 
//							rtgsMapper.addRtgsMatched(id_1[i], match_id, date_now, "1", 
//							id_1.length >1 && id_2.length>1? "1".toString(): "0".toString(), "1", "1"), date_now, "1", "1");
				}

				return true;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Boolean unmatch_all_partial_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "unmatch_transactions_partial")) {
				util.registerActivity(request, "unmatch transactions partially matched", "-");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");
				Long[] id_1 = new Long[id_1_string.length];
				for (int i = 0; i < id_1_string.length; i++) {
					id_1[i] = Long.parseLong(id_1_string[i]);
				}

				Long[] id_2 = new Long[id_2_string.length];
				for (int i = 0; i < id_2_string.length; i++) {
					id_2[i] = Long.parseLong(id_2_string[i]);
				}

//				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));

				String matched_id = null;
				for (int i = 0; i < id_2.length; i++) {
					System.out.println(" id=" + id_2[i]);
					ercaMapper.moveAllPartialMatched(id_2[i]);

//					matched_id = rtgsMapper.findMatchedId(id_2[i]);
//					rtgsMapper.deleteMatchTransaction(matched_id);
//					rtgsMapper.updateUnMatchStatus_(id_2[i]);
				}

				for (int i = 0; i < id_1.length; i++) {
					System.out.println("Ats id=" + id_1[i]);
					ercaMapper.deleteAllPartialMatched(id_1[i]);
					ercaMapper.moveAllPartialAtsMatchedToData(id_1[i]);
					rtgsMapper.deleteUserPartialMatched(rtgsMapper.getpartialMatchedId(id_1[i]));

//					rtgsMapper.updateUnMatchStatus_ats(id_1[i]);
//					Long ids = rtgsMapper.getRtgsMatchedPartialIdFromRtgsMatchedByMatchId(matched_id);
//					rtgsMapper.deleteUserRtgsMatched(ids);
//					rtgsMapper.addUserRtgsMatched(util.get_user_id(request), 
//							rtgsMapper.addRtgsMatched(id_1[i], match_id, date_now, "1", 
//							id_1.length >1 && id_2.length>1? "1".toString(): "0".toString(), "1", "1"), date_now, "1", "1");
				}

				return true;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_payable_credit_unmatched_for_view(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_payable_credit_unmatch")) {
				util.registerActivity(request, "Get payable credit unmatch transaction ", "-");

				return ercaMapper.get_payable_credit_unmatched_for_view();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_payable_debit_unmatched_for_view(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_payable_debit_unmatch")) {
				util.registerActivity(request, "Get payable debit unmatch transaction ", "-");
				return ercaMapper.get_payable_debit_unmatched_for_view();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	///////////////////////////////////////////////////////////////////////////////////////

}
