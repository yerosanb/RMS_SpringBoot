package com.example.demo.user.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ExceptionsList;
import com.example.demo.user.mapper.MapperIssueAccount;
import com.example.demo.user.mapper.MapperRTGS;
import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;
import com.example.demo.utils.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class IssueAccountService {
	@Autowired
	private MapperIssueAccount iaMapper;
	@Autowired
	private MapperRTGS rtgsMapper;

	@Autowired
	private Utils util;

	public List<File_rtgs__> get__issue_for_recon(HttpServletRequest request) {

		try {
			if (util.isPermitted(request, "IssueAccount", "get_issue_account__transactions_for_recon_manual")) {
				util.registerActivity(request, "Get all  transactions for issue account",
						"Get issue account  transactions for recon manual");
				List<File_rtgs__> List = iaMapper.get__issue_for_recon();
				for (File_rtgs__ aa : List) {
					System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				}
				return List;
			} else {
				System.out.println(
						"No user does not have permission in get_issue_account__transactions_for_recon_manual");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_qbs_issue_for_recon(HttpServletRequest request) {
		try {

			if (util.isPermitted(request, "IssueAccount", "get_issue_account_qbs_transactions_for_recon_manual")) {
				util.registerActivity(request, "Get all  transactions for issue account",
						"Get issue account qbs transactions for recon manual");

				List<File_rtgs__> qbsList = iaMapper.get_qbs_issue_for_recon();
				for (File_rtgs__ aa : qbsList) {
					System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				}
				return qbsList;
			} else {
				System.out.println(
						"No user does not have permission in get_issue_account_qbs_transactions_for_recon_manual");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Boolean match_transactions(HttpServletRequest request, String data_ids) {
		try {
			System.out.println("here it comes: " + data_ids);
			if (util.isPermitted(request, "IssueAccount", "match_issue_account_transactions_manual")) {

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

				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String type;

				if (id_1[0] != null) {
					for (int i = 0; i < id_1.length; i++) {
						current_id = iaMapper.moveIssueData(id_1[i]);
						type = "issue_";
						matched_data_id = iaMapper.addIssueMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						iaMapper.addUserIssueMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						iaMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						type = "issue_qbs";
						current_id = iaMapper.moveIssueQbsData(id_2[i], match_id);
						iaMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					}
				} else {
					System.out.println("it is not rtgs");
				}
				util.registerActivity(request, "Issue account match",
						util.getUserName(request) + ": Manual Match issue account transactions.");
				return true;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Boolean match_all_transactions(HttpServletRequest request, String data_ids) {
		try {
			System.out.println("here it comes: " + data_ids);
			if (util.isPermitted(request, "IssueAccount", "match_issue_account_transactions_automatic")) {

				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");
				String[] branch_1 = id_data_object.get("data_1_branch").getAsString().split(",");
				String[] branch_2 = id_data_object.get("data_2_branch").getAsString().split(",");
				String[] amount_1 = id_data_object.get("data_1_amount").getAsString().split(",");
				String[] amount_2 = id_data_object.get("data_2_amount").getAsString().split(",");
				Long[] id_1 = new Long[id_1_string.length];
				for (int i = 0; i < id_1_string.length; i++) {
					id_1[i] = Long.parseLong(id_1_string[i]);
				}

				Long[] id_2 = new Long[id_2_string.length];
				for (int i = 0; i < id_2_string.length; i++) {
					id_2[i] = Long.parseLong(id_2_string[i]);
				}

				double[] amount_ = new double[amount_1.length];
				for (int i = 0; i < amount_1.length; i++) {
					amount_[i] = Double.parseDouble(amount_1[i]);
				}

				double[] amount_qbs = new double[amount_2.length];
				for (int i = 0; i < amount_2.length; i++) {
					amount_qbs[i] = Double.parseDouble(amount_2[i]);
				}

				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String type;
				for (int i = 0; i < id_1.length; i++) {

					for (int j = 0; j < id_2.length; j++) {

						if (amount_[i] == amount_qbs[j] && iaMapper.existsbranchInAdditionalInformation(branch_1[i],iaMapper.additional_information(id_2[j]))==true) {
							// System.out.println("qbs_id-------------->" + qbs_id[i]);
							String match_id = generateUniqueMatchId();
							current_id = iaMapper.moveIssueData(id_1[i]);
							type = "issue_";
							matched_data_id = iaMapper.addIssueMatched(current_id, match_id, date_now, "1",
									id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
							iaMapper.addUserIssueMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
									"1");
							iaMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);

							type = "issue_qbs";
							current_id = iaMapper.moveIssueQbsData(id_2[j], match_id);
							iaMapper.updateEditReason(current_id, matched_data_id, type, id_2[j]);
							amount_qbs[j] = 0.0;
							branch_1[i] = "grtrrrrrrrrrrrrrrrtttttttttttttuooooooooooooo";
  						   break;
							
						} else {
							continue;
						}
					}
				}
				util.registerActivity(request, "Issue account match",
						util.getUserName(request) + ": Manual Match issue account transactions.");
				return true;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Boolean match_transactions_with_reason(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "IssueAccount", "match_issue_account_transactions_with_reason")) {
				util.registerActivity(request, "Issue account match transactions with reason",
						util.getUserName(request) + ": Matched issue account transactions with reason.");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");
				String reason = id_data_object.get("reason").getAsString();

				Long[] id_1 = new Long[id_1_string.length];
				for (int i = 0; i < id_1_string.length; i++) {
					id_1[i] = Long.parseLong(id_1_string[i]);
				}

				Long[] id_2 = new Long[id_2_string.length];
				for (int i = 0; i < id_2_string.length; i++) {
					id_2[i] = Long.parseLong(id_2_string[i]);
				}

				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String type;

				if (id_1[0] != null) {
					for (int i = 0; i < id_1.length; i++) {
						current_id = iaMapper.moveIssueData(id_1[i]);
						type = "issue_";
						matched_data_id = iaMapper.addIssueMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						iaMapper.addUserIssueMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						iaMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						type = "issue_qbs";
						current_id = iaMapper.moveIssueQbsData(id_2[i], match_id);
						iaMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					}

					iaMapper.addMatchReason(match_id, reason, "issue_account", "-", "1", "1");

				} else {
					System.out.println("it is not rtgs");
				}

				return true;
			} else {
				System.out.println("No user does not have permission. match_issue_account_transactions_with_reason");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	String generateUniqueMatchId() {
		String match_id = "";
		long millis = System.currentTimeMillis();
		String datetime = new Date().toGMTString();
		datetime = datetime.replace(" ", "");
		datetime = datetime.replace(":", "");
		String rndchars = RandomStringUtils.randomAlphanumeric(16);
		match_id = millis + rndchars + datetime;
		return match_id;

	}

	public List<File_rtgs__> get__issue_for_view_matched(HttpServletRequest request, String match_dates) {
		try {
			if (util.isPermitted(request, "IssueAccount", "get_issue_account__transactions_for_view_matched")) {
				util.registerActivity(request, "Get all  matched  transactions for issue account",
						"Get issue account   transactions for view matched ");
				JsonObject date_data_object = JsonParser.parseString(match_dates).getAsJsonObject();
				String[] minDate = date_data_object.get("minDate").getAsString().split(",");
				String[] maxDate = date_data_object.get("maxDate").getAsString().split(",");
				List<File_rtgs__> List = iaMapper.get__issue_for_view_matched(
						Integer.parseInt(minDate[0].replace("-", "")), Integer.parseInt(maxDate[0].replace("-", "")));
				for (File_rtgs__ aa : List) {
					System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				}
				return List;
			} else {
				System.out.println(
						"No user does not have permission in get_issue_account__transactions_for_view_matched");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_qbs_issue_for_view_matched(HttpServletRequest request, String match_dates) {
		try {

			if (util.isPermitted(request, "IssueAccount", "get_issue_account_qbs_transactions_for_view_matched")) {
				util.registerActivity(request, "Get all  matchedd transactions for issue account",
						"Get issue account qbs transactions for view matched");
				JsonObject date_data_object = JsonParser.parseString(match_dates).getAsJsonObject();
				String[] minDate = date_data_object.get("minDate").getAsString().split(",");
				String[] maxDate = date_data_object.get("maxDate").getAsString().split(",");

				List<File_rtgs__> qbsList = iaMapper.get_qbs_issue_for_view_matched(
						Integer.parseInt(minDate[0].replace("-", "")), Integer.parseInt(maxDate[0].replace("-", "")));
				for (File_rtgs__ aa : qbsList) {
					System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				}
				return qbsList;
			} else {
				System.out.println(
						"No user does not have permission in get_issue_account_qbs_transactions_for_view_matched");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public boolean unmatch_issuetransactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "IssueAccount", "unmatch_issue_account_transactions")) {
				util.registerActivity(request, "unmatch transactions issue", "-");
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

				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));

				String matched_id = null;
				Long current_id = null;
				String type = "";
				Long matched_data_id = null;
				for (int i = 0; i < id_2.length; i++) {
					System.out.println("qbs id=" + id_2[i]);
					current_id = iaMapper.moveIssueQbsMatched(id_2[i]);
					type = "data_issue_qbs";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					System.out.println("edite reason updated after unmatche id----->" + id_2[i]);

//					matched_id = rtgsMapper.findMatchedId(id_2[i]);
//					rtgsMapper.deleteMatchTransaction(matched_id);
//					rtgsMapper.updateUnMatchStatus_(id_2[i]);
				}

				for (int i = 0; i < id_1.length; i++) {
					System.out.println(" id=" + id_1[i]);
					iaMapper.deleteIssueMatched(id_1[i]);

					current_id = iaMapper.moveIssueMatched(id_1[i]);
					type = "data_issue_";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					System.out.println("edite reason updated after unmatche id----->" + id_1[i]);
					// rtgsMapper.deleteUserRtgsMatched(id_1[i]);
					iaMapper.deleteUserIssueMatched(iaMapper.getIssueMatchedId(id_1[i]));

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
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get__issue_for_view_unmatched(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "IssueAccount", "get_issue_account__transactions_for_view_unmatched")) {
				util.registerActivity(request, "Get all  unmatched  transactions for issue account",
						"Get issue account   transactions for view unmatched ");
				List<File_rtgs__> List = iaMapper.get__issue_for_view_unmatched();
				for (File_rtgs__ aa : List) {
					System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				}
				return List;
			} else {
				System.out.println(
						"No user does not have permission in get_issue_account__transactions_for_view_unmatched");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_qbs_issue_for_view_unmatched(HttpServletRequest request) {
		try {

			if (util.isPermitted(request, "IssueAccount", "get_issue_account_qbs_transactions_for_view_unmatched")) {
				util.registerActivity(request, "Get all  unmatchedd transactions for issue account",
						"Get issue account qbs transactions for view unmatched");

				List<File_rtgs__> qbsList = iaMapper.get_qbs_issue_for_view_unmatched();
				for (File_rtgs__ aa : qbsList) {
					System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				}
				return qbsList;
			} else {
				System.out.println(
						"No user does not have permission in get_issue_account_qbs_transactions_for_view_unmatched");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_issue__matched_with_reason(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "IssueAccount", "get_all_issue__transaction_matched_with_reason")) {
				util.registerActivity(request, "Get all issue  matched with reason  transactions", "-");
				return iaMapper.get_issue__matched_with_reason();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_issue_qbs_matched_with_reason(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "IssueAccount", "get_all_issue_qbs_transaction_matched_with_reason")) {
				util.registerActivity(request, "Get all issue QBS matched with reason  transactions", "-");
				return iaMapper.get_issue_qbs_matched_with_reason();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_edited_issue_(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "IssueAccount", "get_edited_issue__transaction")) {
				util.registerActivity(request, "Get all edited issue  transaction", "-");
				return iaMapper.get_edited_issue_();
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_edited_issue_qbs(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "IssueAccount", "get_edited_issue_qbs_transaction")) {
				util.registerActivity(request, "Get all edited issue QBS transaction", "-");
				return iaMapper.get_edited_issue_qbs();
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_edited_detail_issue_(HttpServletRequest request, Long id) {
		try {
			if (util.isPermitted(request, "IssueAccount", "get_detail_edited_issue__transaction")) {
				util.registerActivity(request, "Get detail edited issue  transaction", "-");
				return iaMapper.get_edited_detail_issue_(id);
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_edited_detail_issue_qbs(HttpServletRequest request, Long id) {
		try {
			if (util.isPermitted(request, "IssueAccount", "get_detail_edited_issue_qbs_transaction")) {
				util.registerActivity(request, "Get detail edited issue QBS transaction", "-");
				return iaMapper.get_edited_detail_issue_QBS(id);
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get__issue_for_recon_auto(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "IssueAccount", "get__issue_for_recon_auto")) {
				util.registerActivity(request, "Get all  transactions for issue account",
						"Get issue account   transactions for reconcile automaticaly ");
				List<File_rtgs__> List = iaMapper.get__issue_for_recon_auto();
				for (File_rtgs__ aa : List) {
					System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				}
				return List;
			} else {
				System.out.println("No user does not have permission in. get__issue_for_recon_auto");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public boolean update_transaction_issue(HttpServletRequest request, File_rtgs__ats edit_data) {
		try {
			if (util.isPermitted(request, "IssueAccount", "update_transaction_issuee")) {
				util.registerActivity(request, "Update transaction", "-");
				Long user_id = util.get_user_id(request);
				String type = "";
				if (edit_data.getType().equalsIgnoreCase("Issue_QBS")) {
					type = "data_issue_QBS";
					Long edit_reason_id = rtgsMapper.addReasonForEdit(edit_data.getId(), user_id, type,
							edit_data.getReason(), new Date().toString(), "1", "1", "1");
					iaMapper.moveOldIssueQBSData(edit_data.getId(), edit_reason_id);

					iaMapper.updateTransactionQBS(edit_data);
					iaMapper.moveEditedIssueQBSeData(edit_data.getId(), edit_reason_id);
				} else if (edit_data.getType().equalsIgnoreCase("Issue_")) {
					type = "data_issue_";
					Long edit_reason_id = rtgsMapper.addReasonForEdit(edit_data.getId(), user_id, type,
							edit_data.getReason(), new Date().toString(), "1", "1", "1");
					iaMapper.moveOldIssueData(edit_data.getId(), edit_reason_id);

					iaMapper.updateTransaction(edit_data);
					iaMapper.moveEditedIssueeData(edit_data.getId(), edit_reason_id);

				}
				return true;
			} else {
				System.out.println("No user does not have permission.");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public boolean delete_transactions(HttpServletRequest request, String datas) {
		try {
			if (util.isPermitted(request, "IssueAccount", "delete_transactions_issue")) {
				util.registerActivity(request, "Delete transactions", "-");
				System.out.println("datas: " + datas);
				JsonObject id_data_object = JsonParser.parseString(datas).getAsJsonObject();
				String reason = id_data_object.get("reason").getAsString();
				String type = id_data_object.get("type").getAsString();
				String[] ids_string = id_data_object.get("ids").getAsString().split(",");
				Long user_id = util.get_user_id(request);
				Long[] ids = new Long[ids_string.length];
				for (int i = 0; i < ids_string.length; i++) {
					ids[i] = Long.parseLong(ids_string[i]);
				}
				String type1 = "";
				for (int i = 0; i < ids.length; i++) {
					if (type != null && type.equalsIgnoreCase("Issue_QBS")) {
						type1 = "data_issue_QBS";
						Long reason_id = rtgsMapper.addReasonForEdit(ids[i], user_id, type1, reason,
								new Date().toString(), "1", "1", "2");
						iaMapper.moveDeletedIssueQBSData(ids[i], reason_id);
						iaMapper.deleteTransactionissueQBS(ids[i]);
					} else if (type != null && type.equalsIgnoreCase("Issue_")) {
						type1 = "data_issue_";
						Long reason_id = rtgsMapper.addReasonForEdit(ids[i], user_id, type1, reason,
								new Date().toString(), "1", "1", "2");
						iaMapper.moveDeletedIssueData(ids[i], reason_id);
						iaMapper.deleteTransactionissueore(ids[i]);
					}
				}
				return true;
			} else {
				System.out.println("No user does not have permission.");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_qbs_issue_for_recon_auto(HttpServletRequest request) {
		try {

			if (util.isPermitted(request, "IssueAccount", "get_qbs_issue_for_recon_auto")) {
				util.registerActivity(request, "Get all qbs transactions of issue account",
						"Get issue account qbs transactions for reconcile automaticaly");

				List<File_rtgs__> qbsList = iaMapper.get_qbs_issue_for_recon_auto();
				for (File_rtgs__ aa : qbsList) {
					System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				}
				return qbsList;
			} else {
				System.out.println("No user does not have permission in. get_qbs_issue_for_recon_auto");
				return null;

			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public boolean match_issue__reversal_transactions(HttpServletRequest request, String data_ids) {
		try {
			System.out.println("here it comes: " + data_ids);
			if (util.isPermitted(request, "IssueAccount", "match_issue_account_transactions__reversal")) {

				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");

				Long[] id_1 = new Long[id_1_string.length];
				for (int i = 0; i < id_1_string.length; i++) {
					id_1[i] = Long.parseLong(id_1_string[i]);
				}

				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String type;

				if (id_1[0] != null) {
					for (int i = 0; i < id_1.length; i++) {
						current_id = iaMapper.moveIssueReversalData(id_1[i], match_id, date_now);
//						type = "issue__reversal";
//						matched_data_id = iaMapper.addIssueMatched(current_id, match_id, date_now, "1",
//								id_1.length > 1 ? "1".toString() : "0".toString(), "1", "1");
//						iaMapper.addUserIssueMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
//								"1");
//						iaMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					}
				} else {
					System.out.println("it is not issue");
				}
				util.registerActivity(request, "Issue account match",
						util.getUserName(request) + ": match_issue_account_transactions__reversal.");
				return true;
			} else {
				System.out.println("No user does not have permission.");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public boolean match_issue_qbs_reversal_transactions(HttpServletRequest request, String data_ids) {
		try {
			System.out.println("here it comes: " + data_ids);
			if (util.isPermitted(request, "IssueAccount", "match_issue_account_transactions_qbs_reversal")) {

				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");

				Long[] id_1 = new Long[id_1_string.length];
				for (int i = 0; i < id_1_string.length; i++) {
					id_1[i] = Long.parseLong(id_1_string[i]);
				}

				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String type;

				if (id_1[0] != null) {
					for (int i = 0; i < id_1.length; i++) {
						current_id = iaMapper.moveIssueQBSeReversalData(id_1[i], match_id, date_now);
//						type = "issue__reversal";
//						matched_data_id = iaMapper.addIssueMatched(current_id, match_id, date_now, "1",
//								id_1.length > 1 ? "1".toString() : "0".toString(), "1", "1");
//						iaMapper.addUserIssueMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
//								"1");
//						iaMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					}
				} else {
					System.out.println("it is not issue");
				}
				util.registerActivity(request, "Issue account match",
						util.getUserName(request) + ": match_issue_account_transactions_qbs_reversal.");
				return true;
			} else {
				System.out.println("No user does not have permission.");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get__issue_for_view_matchedreversal(HttpServletRequest request,
			String match_dates) {
		try {
			if (util.isPermitted(request, "IssueAccount", "get_issue_account__transactions_for_view_matched")) {
				util.registerActivity(request, "Get all  reversal matched  transactions for issue account",
						"Get issue account   transactions for view matched ");
				JsonObject date_data_object = JsonParser.parseString(match_dates).getAsJsonObject();
				String[] minDate = date_data_object.get("minDate").getAsString().split(",");
				String[] maxDate = date_data_object.get("maxDate").getAsString().split(",");
				List<File_rtgs__> List = iaMapper.get__issue_reversal_matched(
						Integer.parseInt(minDate[0].replace("-", "")), Integer.parseInt(maxDate[0].replace("-", "")));
				for (File_rtgs__ aa : List) {
					System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				}
				return List;
			} else {
				System.out.println(
						"No user does not have permission in get_issue_account__transactions_for_view_matched");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_qbs_issue_for_view_matchedreversal(HttpServletRequest request,
			String match_dates) {
		try {
			if (util.isPermitted(request, "IssueAccount", "get_issue_account__transactions_for_view_matched")) {
				util.registerActivity(request, "Get all ABS reversal matched  transactions for issue account",
						"Get issue account   transactions for view matched ");
				JsonObject date_data_object = JsonParser.parseString(match_dates).getAsJsonObject();
				String[] minDate = date_data_object.get("minDate").getAsString().split(",");
				String[] maxDate = date_data_object.get("maxDate").getAsString().split(",");
				List<File_rtgs__> List = iaMapper.get_qbs_issue_reversal_matched(
						Integer.parseInt(minDate[0].replace("-", "")), Integer.parseInt(maxDate[0].replace("-", "")));
				for (File_rtgs__ aa : List) {
					System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				}
				return List;
			} else {
				System.out.println(
						"No user does not have permission in get_issue_account__transactions_for_view_matched");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public boolean unmatch_issue_transactions__reversal(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "IssueAccount", "unmatch_issue_account_transactions")) {
				util.registerActivity(request, "unmatch transactions issue  reversal ", "-");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				Long[] id_1 = new Long[id_1_string.length];
				for (int i = 0; i < id_1_string.length; i++) {
					id_1[i] = Long.parseLong(id_1_string[i]);
				}

				for (int i = 0; i < id_1.length; i++) {
					System.out.println("id=" + id_1[i]);
					iaMapper.moveIssueMatchedReveersal(id_1[i]);
				}

				return true;
			} else {
				System.out.println("No user does not have permission.");
				return false;
			}

		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public boolean unmatch_issue_transactions_qbs_reversal(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "IssueAccount", "unmatch_issue_account_transactions")) {
				util.registerActivity(request, "unmatch transactions issue qbs reversal", "-");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				Long[] id_1 = new Long[id_1_string.length];
				for (int i = 0; i < id_1_string.length; i++) {
					id_1[i] = Long.parseLong(id_1_string[i]);
				}

				for (int i = 0; i < id_1.length; i++) {
					System.out.println("id=" + id_1[i]);
					iaMapper.moveIssueMatchedQBsReveersal(id_1[i]);
				}

				return true;
			} else {
				System.out.println("No user does not have permission.");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
}
