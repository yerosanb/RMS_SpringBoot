 package com.example.demo.user.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.CustomAllException;
import com.example.demo.Exception.ExceptionsList;
import com.example.demo.model.Comment;
import com.example.demo.user.mapper.FixedAssetMapper;
import com.example.demo.user.mapper.FixedAssetMMSMapper;
import com.example.demo.user.mapper.MapperAccount;
import com.example.demo.user.mapper.MapperRTGS;
import com.example.demo.user.mapper.SearchMapper;
import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;
import com.example.demo.user.model.GeneralTransactionHistory;
import com.example.demo.user.model.MatchDetailAts;
import com.example.demo.user.model.MatchDetail;
import com.example.demo.user.model.Transactionhistory;
import com.example.demo.utils.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class RTGSService {

	@Autowired
	private Utils util;
	
	@Autowired
	private FixedAssetMapper fixedAssetMapper;
	
	@Autowired
	private FixedAssetMMSMapper fixedAssetMapper;

	@Autowired
	private MapperRTGS rtgsMapper;

	@Autowired
	private SearchMapper searchMapper;

	@Autowired
	private MapperAccount accountMapper;

	// This functionality is
	// get_all_rgts_ats_for_recon_auto=========================
	public List<File_rtgs__ats> get_ats_for_recon_auto(HttpServletRequest request, String recon_date) {
		try {
			if (util.isPermitted(request, "User", "get_all_ats_transaction_for_recon_auto")) {

				util.registerActivity(request, "Get all Ats transactions",
						"Get all ATS transactions to match automatically");
				System.out.println("the date: " + recon_date.replace("-", ""));

				List<File_rtgs__ats> cc = rtgsMapper.get_ats_for_recon_auto(
						Integer.parseInt(recon_date.replace("-", "")),
						accountMapper.getUserAccountId(util.get_user_id(request)));
				for (File_rtgs__ats c : cc) {
					System.out.println("amount:----------> " + c.getAmount());
				}
				// exportExcelFile();
				// exportPdfFile();
				return cc;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}

		} catch (Exception e) {
			throw new ExceptionsList(e);
		}

	}

	public List<File_rtgs__ats> get_ats_for_recon_partial_auto(HttpServletRequest request, String recon_date) {
		try {
			if (util.isPermitted(request, "User", "get_all_partial_ats_for_recon_auto")) {

				util.registerActivity(request, "Get all Ats automatic partial transactions",
						"Get all ATS transactions to match automatically-partial");
				System.out.println("the date: " + recon_date.replace("-", ""));

				List<File_rtgs__ats> cc = rtgsMapper.get_ats_for_recon_partial_auto(
						Integer.parseInt(recon_date.replace("-", "")),
						accountMapper.getUserAccountId(util.get_user_id(request)));
				for (File_rtgs__ats c : cc) {
					System.out.println("amount:----------> " + c.getAmount());
				}
				System.out.println("sizeeeeeeeeeeeeeee: " + cc.size());
				return cc;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}

		} catch (Exception e) {
			throw new ExceptionsList(e);
		}

	}

	// This functionality is
	// get_all_rgts_ats_for_recon=========================
	public List<File_rtgs__ats> get_ats_for_recon(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_all_ats_transaction_for_recon_manual")) {

				util.registerActivity(request, "Get all Ats tranzactions", "Get all ATS transactions to match manualy");
				System.out.println("the date: " + recon_date.replace("-", ""));

				List<File_rtgs__ats> atsList = rtgsMapper.get_ats_for_recon(
						Integer.parseInt(recon_date.replace("-", "")),
						accountMapper.getUserAccountId(util.get_user_id(request)));

				for (File_rtgs__ats aa : atsList) {
					if (aa.getReference().equalsIgnoreCase("0011677063")) {
						System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
						System.out.println("reference:::::::::::::::::::::: " + aa.getReference());
					}
				}
				return atsList;
			} else {
				System.out.println("No user does not have permission in get_ats_for_recon");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__ats> get_rtgs_ats_for_view(HttpServletRequest request, String recon_date) {
		try {
			if (util.isPermitted(request, "User", "get_rtg_ats_matched")) {
				util.registerActivity(request, "Get rtgs ats matched", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
				return rtgsMapper.get_ats_for_view(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	// This functionality is
	// get_all_rtgs__for_recon=========================
	public List<File_rtgs__> get_rtgs__for_view(HttpServletRequest request, String matched_date) {
		try {
			if (util.isPermitted(request, "User", "get_rtgs__matched")) {
				util.registerActivity(request, "Get rtgs  matched trnsaction", "-");
				return rtgsMapper.get_rtgs__for_view(matched_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_Reversal_for_view(HttpServletRequest request, String matched_date) {
		try {
			if (util.isPermitted(request, "User", "get_rtgs__matched")) {
				util.registerActivity(request, "Get rtgs  matched trnsaction", "-");
				return rtgsMapper.get_Reversal_for_view(matched_date.replace("-", ""));
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	public List<File_rtgs__> get__for_recon(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all__transaction_for_recon_manual")) {
				util.registerActivity(request, "Get all  transactions for manual transaction",
						"Get all  transactions for matching manually");
				return rtgsMapper.get__for_recon(accountMapper.getUserAccountId(util.get_user_id(request)));
			} else {
				System.out.println("No user does not have permission. in: get__for_recon");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	// This functionality is
	// get_all_rgts__for_recon_auto=========================
	public List<File_rtgs__> get__for_recon_auto(HttpServletRequest request, String recon_date) {
		try {
			if (util.isPermitted(request, "User", "get_all__transaction_for_recon_auto")) {
				util.registerActivity(request, "Get all  transactions",
						"Get  transactions to match automatically");
				return rtgsMapper.get__for_recon_auto(accountMapper.getUserAccountId(util.get_user_id(request)),
						Integer.parseInt(recon_date.replace("-", "")));
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get__for_recon_partial_auto(HttpServletRequest request, String recon_date) {
		try {
			if (util.isPermitted(request, "User", "get_all_partial__for_recon_auto")) {
				util.registerActivity(request, "Get all  partial automatic transactions",
						"Get  transactions to match automatically-partial");
				return rtgsMapper.get__for_recon_partial_auto(

						accountMapper.getUserAccountId(util.get_user_id(request)),
						Integer.parseInt(recon_date.replace("-", "")));
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	// This functionality is
	// match_all_transactions_rgts_auto=========================
public Boolean match_all_transactions(HttpServletRequest request, String data_ids, String recon_date) {
		try {
			if (util.isPermitted(request, "User", "match_all_transactions_auto")) {
				util.registerActivity(request, "match all transactions", "match all transactions automatically");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] type = id_data_object.get("type").getAsString().split(",");
				String[] _id = id_data_object.get("_id").getAsString().split(",");
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");
//				Long[] id_1 = new Long[id_1_string.length];
			      List<Long> id_1List = new ArrayList<>();
			        List<String> IdList = new ArrayList<>();
			        HashSet<Long> idSet = new HashSet<>();
				for (int i = 0; i < id_1_string.length; i++) {
					Long currentId = Long.parseLong(id_1_string[i]);

		            // Check for duplicates
		            if (!idSet.add(currentId)) {
		                // Duplicate found, remove the corresponding elements
		                System.out.println("Duplicate ID found: " + currentId);

		                // Remove the duplicate id_1 and corresponding _id
//		                id_1List.remove(i);
//		                IdList.remove(i);

		                // If you want to continue processing the next iteration
//		                continue;
		            }
		            else {
			            // If no duplicate, add to the lists
			            id_1List.add(currentId);
			            IdList.add(_id[i]);
		            }
				}
				
		        // Convert the ArrayLists back to arrays if needed
		        Long[] id_1 = id_1List.toArray(new Long[0]);
		        String[] _idResult = IdList.toArray(new String[0]);

				Long[] id_2 = new Long[id_2_string.length];
				for (int i = 0; i < id_2_string.length; i++) {
					id_2[i] = Long.parseLong(id_2_string[i]);
				}
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String typee;
				for (int i = 0; i < id_1.length; i++) {
					String match_id = generateUniqueMatchId();

					if (type[i].endsWith("103")) {
						if(rtgsMapper.IdExist(Long.parseLong(_idResult[i]))) {
						
						current_id = rtgsMapper.moveRtgsAtsData(id_1[i]);
						typee = "rtgs__ats";
						matched_data_id = rtgsMapper.addRtgsMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						rtgsMapper.addUserRtgsMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_1[i]);

						current_id = rtgsMapper.moveRtgsData(Long.parseLong(_idResult[i]), match_id);
						typee = "rtgs__";
						rtgsMapper.updateEditReason(current_id, matched_data_id, typee, Long.parseLong(_idResult[i]));

						//// Long id_2_2 = rtgsMapper.getMatchedIdsFromByAtsId(id_1[i],
						//// recon_date.replace("-", ""));
						}
						else
							continue;
					}

					else if (type[i].endsWith("202")) {
						if(rtgsMapper.IdExist(Long.parseLong(_idResult[i]))) {
						current_id = rtgsMapper.moveB2bAtsData(id_1[i]);
						typee = "b2b__ats";
						matched_data_id = rtgsMapper.addB2bMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						rtgsMapper.addUserB2bMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_1[i]);

						current_id = rtgsMapper.moveB2bData(Long.parseLong(_idResult[i]), match_id);
						typee = "b2b__";
						rtgsMapper.updateEditReason(current_id, matched_data_id, typee, Long.parseLong(_idResult[i]));
						
						}
						else
							continue;
					}

					else if (type[i].endsWith("204")) {
						if(rtgsMapper.IdExist(Long.parseLong(_idResult[i]))) {
						rtgsMapper.addUserErcaMatched(util.get_user_id(request),
								rtgsMapper.addErcaMatched(rtgsMapper.moveErcaAtsData(id_1[i]), match_id, date_now, "1",
										id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1"),
								date_now, "1", "1", "1");
						rtgsMapper.moveErcaData(Long.parseLong(_idResult[i]), match_id);
						
						}
						else
							continue;

					}

					else if (type[i].endsWith("298smt201") || type[i].endsWith("205")) {
						if(rtgsMapper.IdExist(Long.parseLong(_idResult[i]))) {
						rtgsMapper.addUserSosMatched(util.get_user_id(request),
								rtgsMapper.addSosMatched(rtgsMapper.moveSosAtsData(id_1[i]), match_id, date_now, "1",
										id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1"),
								date_now, "1", "1", "1");
						rtgsMapper.moveSosData(Long.parseLong(_idResult[i]), match_id);
					}
					else
						continue;
					}

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

	public Boolean match_all_transactions_partialy(HttpServletRequest request, String data_ids, String recon_date) {
		try {
			if (util.isPermitted(request, "User", "match_all_transactions_partial_auto")) {
				util.registerActivity(request, "match all  partially transactions",
						"match all transactions automatically to partial");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				// String[] type = id_data_object.get("type").getAsString().split(",");
				String[] _id = id_data_object.get("_id").getAsString().split(",");
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
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));

				for (int i = 0; i < id_1.length; i++) {
					Double ats_amount = rtgsMapper.getAtsAmount(id_1[i]);
					Double _amount = rtgsMapper.getAmount(_id[i]);
					String reference = rtgsMapper.getReference(id_1[i]);
					Double diff = (ats_amount - _amount);

					Comment comment = new Comment();
					comment.setTitle("partial match");
					comment.setAmount_difference(diff);
					comment.setDescription("Transaction is matched partially because total ammount at ATS is "
							+ ats_amount + " and total amount at  is " + _amount + " with the same reference "
							+ reference);
					Long comment_id = rtgsMapper.addReason(comment);
					String match_id = generateUniqueMatchId();
					rtgsMapper.addPartialComment(comment_id, match_id);

					Long current_id = rtgsMapper.movAtsPartiallyMatched(id_1[i]);
					String type = "ats_partialy_matched";
					Long matched_data_id = rtgsMapper.addPartialMatched(current_id, match_id, date_now, "1",
							id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
					rtgsMapper.addUserPartialMatched(util.get_user_id(request), matched_data_id, "1", "1");
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);

					current_id = rtgsMapper.movePartiallyMatched(Long.parseLong(_id[i]), match_id);
					type = "_partialy_matched";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, Long.parseLong(_id[i]));

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

	//////////////////////////////////////////////////////////////////////

	public Boolean match_partial_transactions(HttpServletRequest request, List<Long> id_1, List<Long> id_2,
			Double amount_difference, String description) {
		try {

			if (util.isPermitted(request, "User", "match_transactions_partially")) {
				util.registerActivity(request, "match transactions  manual to partial", "-");

				Comment comment = new Comment();
				comment.setTitle("partial match");
				comment.setAmount_difference(amount_difference);
				comment.setDescription(description);
				Long comment_id = rtgsMapper.addReason(comment);

				System.out.println("jjjjjjjjjjjjjjjjjjjjjjj" + amount_difference);

				String match_id = generateUniqueMatchId();
				rtgsMapper.addPartialComment(comment_id, match_id);

				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String type;
				for (int i = 0; i < id_1.size(); i++) {
					current_id = rtgsMapper.movAtsPartiallyMatched(id_1.get(i));
					type = "ats_partialy_matched";
					matched_data_id = rtgsMapper.addPartialMatched(current_id, match_id, date_now, "1",
							id_1.size() > 1 && id_2.size() > 1 ? "1".toString() : "0".toString(), "1", "1");
					rtgsMapper.addUserPartialMatched(util.get_user_id(request), matched_data_id, "1", "1");
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1.get(i));
				}
				for (int i = 0; i < id_2.size(); i++) {
					current_id = rtgsMapper.movePartiallyMatched(id_2.get(i), match_id);
					type = "_partialy_matched";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2.get(i));
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

	//////////////////////////////////////////////////////////////

	// This functionality is
	// match_transactions_rgts_manual=========================
	public Boolean match_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "match_transactions_manual")) {
				util.registerActivity(request, "match transactions manually", "-");
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

				if (id_1[0] != null && 1 == rtgsMapper.isRtgs(id_1[0])) {

					for (int i = 0; i < id_1.length; i++) {
						current_id = rtgsMapper.moveRtgsAtsData(id_1[i]);
						type = "rtgs__ats";
						matched_data_id = rtgsMapper.addRtgsMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						rtgsMapper.addUserRtgsMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						type = "rtgs__";
						current_id = rtgsMapper.moveRtgsData(id_2[i], match_id);
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					}
				} else if (id_1[0] != null && 1 == rtgsMapper.isErca(id_1[0])) {
					for (int i = 0; i < id_1.length; i++) {
						current_id = rtgsMapper.moveErcaAtsData(id_1[i]);
						type = "erca__ats";
						matched_data_id = rtgsMapper.addErcaMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						rtgsMapper.addUserErcaMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						type = "erca__";
						current_id = rtgsMapper.moveErcaData(id_2[i], match_id);
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					}

				} else if (id_1[0] != null && 1 == rtgsMapper.isSos(id_1[0])) {
					System.out.println("it is sos");
					for (int i = 0; i < id_1.length; i++) {
						current_id = rtgsMapper.moveSosAtsData(id_1[i]);
						type = "sos__ats";
						matched_data_id = rtgsMapper.addSosMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						rtgsMapper.addUserSosMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						type = "sos__";
						current_id = rtgsMapper.moveSosData(id_2[i], match_id);
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					}

				} else if (id_1[0] != null && 1 == rtgsMapper.isB2b(id_1[0])) {
					System.out.println("it is b2b");
					System.out.println(id_1[0]);
					System.out.println(id_2[0]);
					for (int i = 0; i < id_1.length; i++) {
						current_id = rtgsMapper.moveB2bAtsData(id_1[i]);
						type = "b2b__ats";
						matched_data_id = rtgsMapper.addB2bMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						rtgsMapper.addUserB2bMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						type = "b2b__";
						current_id = rtgsMapper.moveB2bData(id_2[i], match_id);
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					}
				} else {
					System.out.println("it is not rtgs");
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
	
	public Boolean match_transactionsReversal(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "match_transactions_manual")) {
				util.registerActivity(request, "match transactions manually", "-");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");

				Long[] id_2 = new Long[id_2_string.length];
				for (int i = 0; i < id_2_string.length; i++) {
					id_2[i] = Long.parseLong(id_2_string[i]);
				}

				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String type;
             for(int i=0;i<id_2.length;i++)
             {
            System.out.println("this is matched by "+rtgsMapper.fullName(util.getUserId(request)));
            	 rtgsMapper.moveReversalData(id_2[i], match_id,date_now,rtgsMapper.fullName(util.getUserId(request))); 
            		type = "__reversal";
//					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
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

	// This functionality is
	// match_transactions_with_reason=========================
	public Boolean match_transactions_with_reason(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "match_transactions_with_reason")) {
				util.registerActivity(request, "match transactions with reason", "-");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");
				String reason = id_data_object.get("reason").getAsString();
				String type = id_data_object.get("type").getAsString();
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
				String typee;
				if (id_1[0] != null && 1 == rtgsMapper.isRtgs(id_1[0])) {
					for (int i = 0; i < id_1.length; i++) {
						current_id = rtgsMapper.moveRtgsAtsData(id_1[i]);
						typee = "rtgs__ats";
						matched_data_id = rtgsMapper.addRtgsMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						rtgsMapper.addUserRtgsMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						typee = "rtgs__";
						current_id = rtgsMapper.moveRtgsData(id_2[i], match_id);
						rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_2[i]);
					}
					if (type.equalsIgnoreCase("reference")) {
						System.out.println("reference-rtgs");
						rtgsMapper.addMatchReason(match_id, reason, "rtgs", "1", "1", "1");
					} else {
						System.out.println("amount-rtgs");
						rtgsMapper.addMatchReason(match_id, reason, "rtgs", "2", "1", "1");
					}

				} else if (id_1[0] != null && 1 == rtgsMapper.isErca(id_1[0])) {
					for (int i = 0; i < id_1.length; i++) {
						current_id = rtgsMapper.moveErcaAtsData(id_1[i]);
						typee = "erca__ats";
						matched_data_id = rtgsMapper.addErcaMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						rtgsMapper.addUserErcaMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						typee = "erca__";
						current_id = rtgsMapper.moveErcaData(id_2[i], match_id);
						rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_2[i]);
					}

					if (type.equalsIgnoreCase("reference")) {
						System.out.println("reference-erca");
						rtgsMapper.addMatchReason(match_id, reason, "erca", "1", "1", "1");
					} else {
						System.out.println("amount-erca");
						rtgsMapper.addMatchReason(match_id, reason, "erca", "2", "1", "1");
					}

				} else if (id_1[0] != null && 1 == rtgsMapper.isSos(id_1[0])) {
					System.out.println("it is sos");
					for (int i = 0; i < id_1.length; i++) {
						current_id = rtgsMapper.moveSosAtsData(id_1[i]);
						typee = "sos__ats";
						matched_data_id = rtgsMapper.addSosMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						rtgsMapper.addUserSosMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						typee = "sos__";
						current_id = rtgsMapper.moveSosData(id_2[i], match_id);
						rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_2[i]);
					}

					if (type.equalsIgnoreCase("reference")) {
						System.out.println("reference-sos");
						rtgsMapper.addMatchReason(match_id, reason, "sos", "1", "1", "1");
					} else {
						System.out.println("amount-sos");
						rtgsMapper.addMatchReason(match_id, reason, "sos", "2", "1", "1");
					}

				} else if (id_1[0] != null && 1 == rtgsMapper.isB2b(id_1[0])) {

					for (int i = 0; i < id_1.length; i++) {
						current_id = rtgsMapper.moveB2bAtsData(id_1[i]);
						typee = "b2b__ats";
						matched_data_id = rtgsMapper.addB2bMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						rtgsMapper.addUserB2bMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						typee = "b2b__";
						current_id = rtgsMapper.moveB2bData(id_2[i], match_id);
						rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_2[i]);
					}
					if (type.equalsIgnoreCase("reference")) {
						System.out.println("reference-b2b");
						rtgsMapper.addMatchReason(match_id, reason, "b2b", "1", "1", "1");
					} else {
						System.out.println("amount-b2b");
						rtgsMapper.addMatchReason(match_id, reason, "b2b", "2", "1", "1");
					}
				} else {
					System.out.println("it is not rtgs");
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

	public Boolean unmatch_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "unmatch_transactions_rgts")) {
				util.registerActivity(request, "unmatch transactions rgts", "-");
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
					System.out.println(" id=" + id_2[i]);
					current_id = rtgsMapper.moveRtgsMatched(id_2[i]);
					type = "data__";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					System.out.println("edite reason updated after unmatche id----->" + id_2[i]);

					// matched_id = rtgsMapper.findMatchedId(id_2[i]);
					// rtgsMapper.deleteMatchTransaction(matched_id);
					// rtgsMapper.updateUnMatchStatus_(id_2[i]);
				}

				for (int i = 0; i < id_1.length; i++) {
					System.out.println("Ats id=" + id_1[i]);
					rtgsMapper.deleteRtgsMatched(id_1[i]);

					current_id = rtgsMapper.moveRtgsAtsMatchedToData(id_1[i]);
					type = "data__ats";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					System.out.println("edite reason updated after unmatche id----->" + id_1[i]);
					// rtgsMapper.deleteUserRtgsMatched(id_1[i]);
					rtgsMapper.deleteUserRtgsMatched(rtgsMapper.getrtgsMatchedId(id_1[i]));

					// rtgsMapper.updateUnMatchStatus_ats(id_1[i]);
					// Long ids =
					// rtgsMapper.getRtgsMatchedPartialIdFromRtgsMatchedByMatchId(matched_id);
					// rtgsMapper.deleteUserRtgsMatched(ids);
					// rtgsMapper.addUserRtgsMatched(util.get_user_id(request),
					// rtgsMapper.addRtgsMatched(id_1[i], match_id, date_now, "1",
					// id_1.length >1 && id_2.length>1? "1".toString(): "0".toString(), "1", "1"),
					// date_now, "1", "1");
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

	public Boolean unmatch__reversal_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "unmatch_transactions_rgts")) {
				util.registerActivity(request, "unmatch transactions rgts", "-");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");
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
				 rtgsMapper.moveReversalMatched(id_2[i]);
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

	public List<File_rtgs__ats> get_ats_for_recon_partial(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_ats_for_recon_partial")) {

				util.registerActivity(request, "Get all Rtgs Ats For Recon - partial", "-");
				// System.out.println("the date: " + recon_date.replace("-", ""));
				return rtgsMapper.get_ats_for_recon_partial(accountMapper.getUserAccountId(util.get_user_id(request)));
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get__for_recon_partial(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get__for_recon_partial")) {
				util.registerActivity(request, "Get all Rtgs  For Recon - partial", "-");
				return rtgsMapper.get__for_recon_partial(accountMapper.getUserAccountId(util.get_user_id(request)));
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Boolean update_transaction(HttpServletRequest request, File_rtgs__ats edit_data) {
		try {
			if (util.isPermitted(request, "User", "update_transaction")) {
				util.registerActivity(request, "Update transaction", "-");
				Long user_id = util.get_user_id(request);
				System.out.println("type:-------------------> " + edit_data.getType());
				System.out.println("reference:-------------------> " + edit_data.getReference());
				String type = "";
				if (edit_data.getType().equalsIgnoreCase("")) {
					type = "data__";
					Long edit_reason_id = rtgsMapper.addReasonForEdit(edit_data.getId(), user_id, type,
							edit_data.getReason(), new Date().toString(), "1", "1", "1");
					rtgsMapper.moveOldData(edit_data.getId(), edit_reason_id);

					rtgsMapper.updateTransaction(edit_data);
					rtgsMapper.moveEditedData(edit_data.getId(), edit_reason_id);
				} else if (edit_data.getType().equalsIgnoreCase("payable")) {
					type = "data_payable";
					Long edit_reason_id = rtgsMapper.addReasonForEdit(edit_data.getId(), user_id, type,
							edit_data.getReason(), new Date().toString(), "1", "1", "1");
					rtgsMapper.moveOldPayableData(edit_data.getId(), edit_reason_id);

					rtgsMapper.updateTransactionpayable(edit_data);
					rtgsMapper.moveEditedPayableData(edit_data.getId(), edit_reason_id);

				} else if (edit_data.getType().equalsIgnoreCase("ATS")) {

					type = "data__ats";

					Long edit_reason_id = rtgsMapper.addReasonForEdit(edit_data.getId(), user_id, type,
							edit_data.getReason(), new Date().toString(), "1", "1", "1");
					rtgsMapper.moveOldAtsData(edit_data.getId(), edit_reason_id);
					rtgsMapper.updateTransaction(edit_data);
					return rtgsMapper.moveEditedAtsData(edit_data.getId(), edit_reason_id);
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

	// delete_transactions
	public Boolean delete_transactions(HttpServletRequest request, String datas) {
		try {
			if (util.isPermitted(request, "User", "delete_transactions")) {
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
					System.out.println("hello world");
					if (type != null && type.equalsIgnoreCase("ats")) {
						type1 = "data__ats";
						Long reason_id = rtgsMapper.addReasonForEdit(ids[i], user_id, type1, reason,
								new Date().toString(), "1", "1", "2");
						rtgsMapper.moveDeletedAtsData(ids[i], reason_id);
						rtgsMapper.deleteTransaction(ids[i]);
					} else if (type != null && type.equalsIgnoreCase("")) {
						type1 = "data__";
						System.out.println("it is  and the id is: " + ids[i]);
						Long reason_id = rtgsMapper.addReasonForEdit(ids[i], user_id, type1, reason,
								new Date().toString(), "1", "1", "2");
						rtgsMapper.moveDeletedData(ids[i], reason_id);
						rtgsMapper.deleteTransaction(ids[i]);
					} else if (type != null && type.equalsIgnoreCase("payable")) {
						type1 = "data_payable";
						System.out.println("it is  and the id is: " + ids[i]);
						Long reason_id = rtgsMapper.addReasonForEdit(ids[i], user_id, type1, reason,
								new Date().toString(), "1", "1", "2");
						rtgsMapper.moveDeletedPayableData(ids[i], reason_id);
						rtgsMapper.deleteTransactionpayable(ids[i]);
					}
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

	public Boolean match_partial_to_full_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "match_partial_transaction_to_full")) {
				util.registerActivity(request, "Match partial transactions to full transactions", "-");

				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] b2b_new_ids_ats_string = id_data_object.get("b2b_new_ids_ats").getAsString().split(",");
				String[] b2b_old_ids_ats_string = id_data_object.get("b2b_old_ids_ats").getAsString().split(",");
				String[] b2b_new_ids__string = id_data_object.get("b2b_new_ids_").getAsString().split(",");
				String[] b2b_old_ids__string = id_data_object.get("b2b_old_ids_").getAsString().split(",");

				System.out.println("d: " + b2b_new_ids_ats_string[0]);
				System.out.println("d: " + b2b_old_ids_ats_string[0]);
				System.out.println("d: " + b2b_new_ids__string[0]);
				System.out.println("d: " + b2b_old_ids__string[0]);

				Long[] b2b_new_ids_ats = new Long[b2b_new_ids_ats_string.length];
				if (!b2b_new_ids_ats_string[0].equals(""))
					for (int i = 0; i < b2b_new_ids_ats_string.length; i++) {
						b2b_new_ids_ats[i] = Long.parseLong(b2b_new_ids_ats_string[i]);
					}
				Long[] b2b_old_ids_ats = new Long[b2b_old_ids_ats_string.length];
				if (!b2b_old_ids_ats_string[0].equals(""))
					for (int i = 0; i < b2b_old_ids_ats_string.length; i++) {
						b2b_old_ids_ats[i] = Long.parseLong(b2b_old_ids_ats_string[i]);
					}
				Long[] b2b_new_ids_ = new Long[b2b_new_ids__string.length];
				if (!b2b_new_ids__string[0].equals(""))
					for (int i = 0; i < b2b_new_ids__string.length; i++) {
						b2b_new_ids_[i] = Long.parseLong(b2b_new_ids__string[i]);
					}
				Long[] b2b_old_ids_ = new Long[b2b_old_ids__string.length];
				if (!b2b_old_ids__string[0].equals(""))
					for (int i = 0; i < b2b_old_ids__string.length; i++) {
						b2b_old_ids_[i] = Long.parseLong(b2b_old_ids__string[i]);
					}
				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String type;
				if (b2b_new_ids_ats[0] != null) {

					for (int i = 0; i < b2b_new_ids_ats.length; i++) {
						current_id = rtgsMapper.moveB2bAtsData(b2b_new_ids_ats[i]);
						type = "b2b__ats";
						matched_data_id = rtgsMapper.addB2bMatched(current_id, match_id, date_now, "1", "0", "1", "1");
						rtgsMapper.addUserB2bMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, b2b_new_ids_ats[i]);
					}

				}

				if (b2b_new_ids_[0] != null) {
					for (int i = 0; i < b2b_new_ids_.length; i++) {
						current_id = rtgsMapper.moveB2bData(b2b_new_ids_[i], match_id);
						type = "b2b__";
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, b2b_new_ids_[i]);

					}
				}

				if (b2b_old_ids_ats[0] != null) {

					for (int i = 0; i < b2b_old_ids_ats.length; i++) {
						current_id = rtgsMapper.moveB2bAtsDataFromPartial(b2b_old_ids_ats[i]);
						type = "b2b__ats";
						matched_data_id = rtgsMapper.addB2bMatched(current_id, match_id, date_now, "1", "0", "1", "1");
						rtgsMapper.addUserB2bMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, b2b_old_ids_ats[i]);
					}

				}

				if (b2b_old_ids_[0] != null) {
					for (int i = 0; i < b2b_old_ids_.length; i++) {
						current_id = rtgsMapper.moveB2bDataFromPartial(b2b_old_ids_[i], match_id);
						type = "b2b__";
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, b2b_old_ids_[i]);
					}
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

	public List<File_rtgs__ats> get_edited_ats(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_edited_ats_transaction")) {
				util.registerActivity(request, "Get all edited ats transaction", "-");
				return rtgsMapper.get_edited_ats();
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_edited_(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_edited__transaction")) {
				util.registerActivity(request, "Get all edited  transaction", "-");
				return rtgsMapper.get_edited_();
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__ats> get_edited_detail_ats(HttpServletRequest request, Long id) {
		try {
			if (util.isPermitted(request, "User", "get_detail_edited_ats_transaction")) {
				util.registerActivity(request, "Get detail edited ats transaction", "-");
				System.out.println("id-------->" + id);
				return rtgsMapper.get_edited_detail_ats(id);
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_edited_detail_(HttpServletRequest request, Long id) {
		try {
			if (util.isPermitted(request, "User", "get_detail_edited__transaction")) {
				util.registerActivity(request, "Get detail edited  transaction", "-");
				System.out.println("id-------->" + rtgsMapper.get_edited_detail_(id));
				return rtgsMapper.get_edited_detail_(id);
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<Transactionhistory> General_search(HttpServletRequest request, GeneralTransactionHistory search) {

		try {
			System.out.println("-------------------typeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee=" + search.getType());
			System.out.println("-------------------type=" + search.getType());
			System.out.println("-------------------match_date=" + search.getMatch_date());
			System.out.println("-------------------min am=" + search.getMin_amount());
			System.out.println("-------------------min am=" + search.getMin_amount());

			System.out.println("-------------------max am=" + search.getMax_amount());
			System.out.println("-------------------min uploa=" + search.getMin_upload_date());
			System.out.println("-------------------max am match date =" + search.getMax_match_date());
			System.out.println("-------------------min match date" + search.getMin_match_date());

			System.out.println("-------------------value date=" + search.getValue_date());
			System.out.println("------------------- credit =" + search.getCredit());

			System.out.println("------------------- debit =" + search.getDebit());
			System.out.println("------------------- transaction date =" + search.getTransaction_date());
			System.out.println("-------------------reference=" + search.getReference());
			System.out.println("-------------------max upl=" + search.getMax_upload_date());

			System.out.println("-------------------Tag nuber=" + search.getTag_number());
			System.out.println("-------------------categoryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy=" + search.getCategory());
			
			System.out.println("-------------------categoryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy=" + search.getCategoryStock());
			System.out.println("-------------------categoryyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy=" + search.getCategoryStockMMs());


			System.out.println("-------------------source brnachhhhhhhhhhh=" + search.getSource_branch());
			System.out.println("-------------------branch codeeeeeeeeee=" + search.getBranch_code());
			System.out.println("-------------------store nameeeeeeeeeeeeeee=" + search.getStore_name());

			System.out.println("------------------Accounttttttttttttttttttt=" + search.getAccount());

			
			System.out.println("-------------------branch name=" + search.getBranch_code());
			// LocalDate date = LocalDate.parse("2023-05-24 08:47:48.543",
			// DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

			if (search.getCategory().equalsIgnoreCase("AB")) {
				search.setCom("01197");
				search.setIfb("01A97");
			} else if (search.getCategory().equalsIgnoreCase("BF")) {
				search.setCom("01195");
				search.setIfb("01A95");
			} else if (search.getCategory().equalsIgnoreCase("Cp")) {
				search.setCom("01198");
				search.setIfb("01198");
			} else if (search.getCategory().equalsIgnoreCase("GX")) {
				search.setCom("01196");
				search.setIfb("01A96");
			}
			// ..........................
			if (search.getCategory().equalsIgnoreCase("")) {
				search.setCom("");
				search.setIfb("");
			}
			System.out.println("-------------------con=" + search.getCom());
			System.out.println("-------------------ifb=" + search.getIfb());
			/// swapping min and max vale credit and debit
			double credit2, debit2;
			if (search.getDebit() >= search.getCredit() && search.getCredit() != 0.0) {

				debit2 = search.getDebit();
				credit2 = search.getCredit();
				search.setCredit(debit2);
				search.setDebit(credit2);
				// search.setTag_number(null);
				System.out.println(
						" _________________ credit = " + search.getCredit() + "   debit =" + search.getDebit());

			}
			if (search.getMatch_date() != "") {
				search.setMatch_date2(Integer.parseInt(search.getMatch_date().replace("-", "")));
				search.setMax_match_date2(Integer.parseInt(search.getMax_match_date().replace("-", "")));
				System.out.println("max match date2=" + search.getMax_match_date2());

			} else {
				search.setMatch_date2(0);
				search.setMax_match_date2(0);

			}

			if (search.getMin_amount() == null) {
				search.setMin_amount("");
			}
			if (search.getMax_amount() == null) {
				search.setMax_amount("");
			}
			int min_upload_date = 0;
			int max_upload_date = 0;
			int min_match_date = 0;
			int max_match_date = 0;

			if (search.getMin_upload_date() == null) {
				min_upload_date = 0;
			} else {
				min_upload_date = Integer.parseInt(search.getMin_upload_date().replace("-", ""));
			}
			if (search.getMax_upload_date() == null) {
				max_upload_date = 0;
			} else {
				max_upload_date = Integer.parseInt(search.getMax_upload_date().replace("-", ""));
			}
			if (search.getMin_match_date() == null) {
				min_match_date = 0;
			} else {
				min_match_date = Integer.parseInt(search.getMin_match_date().replace("-", ""));
			}
			if (search.getMax_match_date() == null) {
				max_match_date = 0;
			} else {
				max_match_date = Integer.parseInt(search.getMax_match_date().replace("-", ""));
			}
			String value_date;
			if (search.getValue_date() != null) {
				value_date = search.getValue_date().replace(" ", "\n");
			} else {
				value_date = "";
			}
			String reference = search.getReference();
			String branch_code = search.getBranch_code();
			String account_name = search.getAccount_name();

			if (reference == null) {
				reference = "";
			}
			if (branch_code == null) {
				branch_code = "";
			}
			if (account_name == null) {
				account_name = "";
			}

			List<Transactionhistory> general = null;
			if (util.isPermitted(request, "User", "Search_transaction_history")||util.isPermitted(request, "FixedAsset", "Search_transaction_history")||util.isPermitted(request, "IssueAccount", "Search_transaction_history")) {
				util.registerActivity(request, "View transaction history", "-");

				if (search.getType().equalsIgnoreCase("ATS")) {
					System.out.println("------------");

					if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() != ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithAmount(Float.parseFloat(search.getMin_amount()),
								Float.parseFloat(search.getMax_amount()), reference, value_date);

					}
				
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() != ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithMinAmount(Float.parseFloat(search.getMin_amount()), reference,
								value_date);

					}
					//// min

					///// max
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
						general = rtgsMapper.searchWithMaxAmount(Float.parseFloat(search.getMax_amount()), reference,
								value_date);

					}
					//// max
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& reference != "" && value_date == "") {

						general = rtgsMapper.searchWithReference(reference);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && reference == "") {

						general = rtgsMapper.searchWithValueDate(value_date);

					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && reference != "") {

						general = rtgsMapper.searchWithValueDateAndReference(value_date, reference);

					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
							&& search.getMin_amount() == "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithUploadDate(min_upload_date, max_upload_date, reference,
								value_date);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() == ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMatchDate(min_match_date, max_match_date, reference, value_date);

					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() != ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithAmountAndUploadDate(Float.parseFloat(search.getMin_amount()),
								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date, reference,
								value_date);

					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() != ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0) {
						general = rtgsMapper.searchWithMinAmountAndUploadDate(Float.parseFloat(search.getMin_amount()),
								min_upload_date, max_upload_date, reference, value_date);

					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() == ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
						general = rtgsMapper.searchWithMaxAmountAndUploadDate(Float.parseFloat(search.getMax_amount()),
								min_upload_date, max_upload_date, reference, value_date);

					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() != ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithAmountUploadDateAndMatchDate(
								Float.parseFloat(search.getMin_amount()), Float.parseFloat(search.getMax_amount()),
								min_upload_date, max_upload_date, min_match_date, max_match_date, reference,
								value_date);
					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {
						general = rtgsMapper.searchWithMinAmountUploadDateAndMatchDate(
								Float.parseFloat(search.getMin_amount()), min_upload_date, max_upload_date,
								min_match_date, max_match_date, reference, value_date);
					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() != ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {
						general = rtgsMapper.searchWithMinAmountUploadDateAndMatchDate(
								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date,
								min_match_date, max_match_date, reference, value_date);
					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithUploadDateAndMatchDate(min_match_date, max_match_date,
								min_upload_date, max_upload_date, reference, value_date);
					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() != ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithAmountAndMatchDate(Float.parseFloat(search.getMin_amount()),
								Float.parseFloat(search.getMax_amount()), min_match_date, max_match_date, reference,
								value_date);
					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() == ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMinAmountAndMatchDate(Float.parseFloat(search.getMin_amount()),
								min_match_date, max_match_date, reference, value_date);
					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() != ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMaxAmountAndMatchDate(Float.parseFloat(search.getMax_amount()),
								min_match_date, max_match_date, reference, value_date);
					}

				} else if (search.getType().equalsIgnoreCase("")) {

					if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() != ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithAmount(Float.parseFloat(search.getMin_amount()),
								Float.parseFloat(search.getMax_amount()), reference, value_date, branch_code);

					}
					// min
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() != ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithMinAmount(Float.parseFloat(search.getMin_amount()),
								reference, value_date, branch_code);

					}
					// min
					// max
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithMaxAmount(Float.parseFloat(search.getMax_amount()),
								reference, value_date, branch_code);

					}
					// max
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& reference != "" && value_date == "" && branch_code == "") {

						general = rtgsMapper.searchWithReference(reference);

					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && reference == "" && branch_code == "") {
System.out.println("111111111111111111111111111111111");
						general = rtgsMapper.searchWithValueDate(value_date);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& branch_code != "" && reference == "" && value_date == "") {
						System.out.println("2222222222222222222222222222222222");
						general = rtgsMapper.searchWithBranchCode(branch_code);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && reference != "" && branch_code == "") {
						System.out.println("33333333333333333333333333");
						general = rtgsMapper.searchWithValueDateAndReference(value_date, reference);

					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && branch_code != "" && reference == "") {
						System.out.println("4444444444444444444444444444444444444444");
						general = rtgsMapper.searchWithValueDateAndBranchCode(value_date, branch_code);

					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && branch_code != "" && reference != "") {
						System.out.println("555555555555555555555555555555555555");
						general = rtgsMapper.searchWithRefValueDateBranchCode(reference, value_date, branch_code);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& reference != "" && branch_code != "" && value_date == "") {
						System.out.println("666666666666666666666666666666666666666666");
						general = rtgsMapper.searchWithReferenceAndBranchCode(reference, branch_code);

					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
							&& search.getMin_amount() == "" && min_match_date == 0 && max_match_date == 0) {
						System.out.println("7777777777777777777777777777777");
						general = rtgsMapper.searchWithUploadDate(min_upload_date, max_upload_date, reference,
								value_date, branch_code);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() == ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {
						System.out.println("8888888888888888888888888888888888888");
						general = rtgsMapper.searchWithMatchDate(min_match_date, max_match_date, reference,
								value_date, branch_code);

					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() != ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithAmountAndUploadDate(Float.parseFloat(search.getMin_amount()),
								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date, reference,
								value_date, branch_code);

					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() != ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithMinAmountAndUploadDate(
								Float.parseFloat(search.getMin_amount()), min_upload_date, max_upload_date, reference,
								value_date, branch_code);

					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() == ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
						general = rtgsMapper.searchWithMaxAmountAndUploadDate(
								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date, reference,
								value_date, branch_code);

					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() != ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithAmountUploadDateAndMatchDate(
								Float.parseFloat(search.getMin_amount()), Float.parseFloat(search.getMax_amount()),
								min_upload_date, max_upload_date, min_match_date, max_match_date, reference, value_date,
								branch_code);
					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMinAmountUploadDateAndMatchDate(
								Float.parseFloat(search.getMin_amount()), min_upload_date, max_upload_date,
								min_match_date, max_match_date, reference, value_date, branch_code);
					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() != ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMaxAmountUploadDateAndMatchDate(
								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date,
								min_match_date, max_match_date, reference, value_date, branch_code);
					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {
                                System.out.println("99999999999999999999999999999999999999");
						general = rtgsMapper.searchWithUploadDateAndMatchDate(min_match_date, max_match_date,
								min_upload_date, max_upload_date, reference, value_date, branch_code);
					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() != ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithAmountAndMatchDate(Float.parseFloat(search.getMin_amount()),
								Float.parseFloat(search.getMax_amount()), min_match_date, max_match_date, reference,
								value_date, branch_code);
					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() == ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMinAmountAndMatchDate(
								Float.parseFloat(search.getMin_amount()), min_match_date, max_match_date, reference,
								value_date, branch_code);
					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() != ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMaxAmountAndMatchDate(
								Float.parseFloat(search.getMax_amount()), min_match_date, max_match_date, reference,
								value_date, branch_code);
					}
				} else if (search.getType().equalsIgnoreCase("Payable")) {
					System.out.println("min_upload_date: " + min_upload_date);
					System.out.println("max_upload_date: " + max_upload_date);
					System.out.println("search.getMin_amount(): " + search.getMin_amount());
					System.out.println("search.getMax_amount(): " + search.getMax_amount());
					System.out.println("min_match_date: " + min_match_date);
					System.out.println("max_match_date: " + max_match_date);
					System.out.println("reference: " + reference);
					System.out.println("value_date: " + value_date);
					System.out.println("branch_code: " + branch_code);
					
					general = searchMapper.getPayableSearch(
							search.getMin_amount(), 
							search.getMax_amount(), 
							reference, 
							value_date, 
							branch_code, 
							min_upload_date, 
							max_upload_date, 
							min_match_date, 
							max_match_date);
					System.out.println("here is the min_upload_dateeeeee"+ min_upload_date);

//					if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() != ""
//							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
//
//						general = rtgsMapper.searchWithAmountPayable(Float.parseFloat(search.getMin_amount()),
//								Float.parseFloat(search.getMax_amount()), reference, value_date, branch_code);
//
//						// System.out.println(general);
//					}
//					// min
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() != ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0) {
//
//						general = rtgsMapper.searchWithMinAmountPayable(Float.parseFloat(search.getMin_amount()),
//								reference, value_date, branch_code);
//
//						System.out.println("-----------" + general);
//					}
//					// min
//					// max
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
//
//						general = rtgsMapper.searchWithMaxAmountPayable(Float.parseFloat(search.getMax_amount()),
//								reference, value_date, branch_code);
//
//					}
//					// max
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& reference != "" && value_date == "" && branch_code == "") {
//
//						general = rtgsMapper.searchWithReferencePayable(reference);
//
//					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& value_date != "" && reference == "" && branch_code == "") {
//
//						general = rtgsMapper.searchWithValueDatePayable(value_date);
//
//					}
//
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& branch_code != "" && reference == "" && value_date == "") {
//
//						general = rtgsMapper.searchWithBranchCodePayable(branch_code);
//
//					}
//
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& value_date != "" && reference != "" && branch_code == "") {
//
//						general = rtgsMapper.searchWithValueDateAndReferencePayable(value_date, reference);
//
//					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& value_date != "" && branch_code != "" && reference == "") {
//
//						general = rtgsMapper.searchWithValueDateAndBranchCodePayable(value_date, branch_code);
//
//					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& value_date != "" && branch_code != "" && reference != "") {
//
//						general = rtgsMapper.searchWithRefValueDateBranchCodePayable(reference, value_date,
//								branch_code);
//
//					}
//
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& reference != "" && branch_code != "" && value_date == "") {
//
//						general = rtgsMapper.searchWithReferenceAndBranchCodePayable(reference, branch_code);
//
//					}
//
//					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
//							&& search.getMin_amount() == "" && min_match_date == 0 && max_match_date == 0) {
//
//						general = rtgsMapper.searchWithUploadDatePayable(min_upload_date, max_upload_date, reference,
//								value_date, branch_code);
//
//					}
//
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() == ""
//							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithMatchDatePayable(min_match_date, max_match_date, reference,
//								value_date, branch_code);
//
//					}
//
//					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() != ""
//							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
//
//						general = rtgsMapper.searchWithAmountAndUploadDatePayable(
//								Float.parseFloat(search.getMin_amount()), Float.parseFloat(search.getMax_amount()),
//								min_upload_date, max_upload_date, reference, value_date, branch_code);
//
//					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() != ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0) {
//
//						general = rtgsMapper.searchWithMinAmountAndUploadDatePayable(
//								Float.parseFloat(search.getMin_amount()), min_upload_date, max_upload_date, reference,
//								value_date, branch_code);
//
//					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
//						general = rtgsMapper.searchWithMaxAmountAndUploadDatePayable(
//								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date, reference,
//								value_date, branch_code);
//
//					}
//
//					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() != ""
//							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithAmountUploadDateAndMatchDatePayable(
//								Float.parseFloat(search.getMin_amount()), Float.parseFloat(search.getMax_amount()),
//								min_upload_date, max_upload_date, min_match_date, max_match_date, reference, value_date,
//								branch_code);
//					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
//							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithMinAmountUploadDateAndMatchDatePayable(
//								Float.parseFloat(search.getMin_amount()), min_upload_date, max_upload_date,
//								min_match_date, max_match_date, reference, value_date, branch_code);
//					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() != ""
//							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithMaxAmountUploadDateAndMatchDatePayable(
//								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date,
//								min_match_date, max_match_date, reference, value_date, branch_code);
//					}
//
//					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
//							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithUploadDateAndMatchDatePayable(min_upload_date, max_upload_date,
//								min_match_date, max_match_date, reference, value_date, branch_code);
//						System.out.println(general);
//						System.out.println("----------------------------");
//					}
//
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() != ""
//							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithAmountAndMatchDatePayable(
//								Float.parseFloat(search.getMin_amount()), Float.parseFloat(search.getMax_amount()),
//								min_match_date, max_match_date, reference, value_date, branch_code);
//					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() == ""
//							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithMinAmountAndMatchDatePayable(
//								Float.parseFloat(search.getMin_amount()), min_match_date, max_match_date, reference,
//								value_date, branch_code);
//					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() != ""
//							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithMaxAmountAndMatchDatePayable(
//								Float.parseFloat(search.getMax_amount()), min_match_date, max_match_date, reference,
//								value_date, branch_code);
//					}
					
				} else if (search.getType().equalsIgnoreCase("Receivable")) {
					general = searchMapper.getRecievableSearch(
							search.getMin_amount(), 
							search.getMax_amount(), 
							reference, 
							value_date, 
							branch_code, 
							min_upload_date, 
							max_upload_date, 
							min_match_date, 
							max_match_date);

//					if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() != ""
//							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
//
//						general = rtgsMapper.searchWithAmountReceivable(Float.parseFloat(search.getMin_amount()),
//								Float.parseFloat(search.getMax_amount()), reference, value_date, branch_code);
//
//						// System.out.println(general);
//					}
//					// min
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() != ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0) {
//						general = rtgsMapper.searchWithMinAmountReceivable(Float.parseFloat(search.getMin_amount()),
//								reference, value_date, branch_code);
//
//					}
//					// min
//					// max
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
//
//						general = rtgsMapper.searchWithMaxAmountReceivable(Float.parseFloat(search.getMax_amount()),
//								reference, value_date, branch_code);
//
//					}
//					// max
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& reference != "" && value_date == "" && branch_code == "") {
//
//						general = rtgsMapper.searchWithReferenceReceivable(reference);
//
//					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& value_date != "" && reference == "" && branch_code == "") {
//
//						general = rtgsMapper.searchWithValueDateReceivable(value_date);
//
//					}
//
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& branch_code != "" && reference == "" && value_date == "") {
//
//						general = rtgsMapper.searchWithBranchCodeReceivable(branch_code);
//
//					}
//
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& value_date != "" && reference != "" && branch_code == "") {
//
//						general = rtgsMapper.searchWithValueDateAndReferenceReceivable(value_date, reference);
//
//					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& value_date != "" && branch_code != "" && reference == "") {
//
//						general = rtgsMapper.searchWithValueDateAndBranchCodeReceivable(value_date, branch_code);
//
//					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& value_date != "" && branch_code != "" && reference != "") {
//
//						general = rtgsMapper.searchWithRefValueDateBranchCodeReceivable(reference, value_date,
//								branch_code);
//
//					}
//
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
//							&& reference != "" && branch_code != "" && value_date == "") {
//
//						general = rtgsMapper.searchWithReferenceAndBranchCodeReceivable(reference, branch_code);
//
//					}
//
//					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
//							&& search.getMin_amount() == "" && min_match_date == 0 && max_match_date == 0) {
//
//						general = rtgsMapper.searchWithUploadDateReceivable(min_upload_date, max_upload_date, reference,
//								value_date, branch_code);
//
//					}
//
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() == ""
//							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithMatchDateReceivable(min_match_date, max_match_date, reference,
//								value_date, branch_code);
//
//					}
//
//					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() != ""
//							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
//
//						general = rtgsMapper.searchWithAmountAndUploadDateReceivable(
//								Float.parseFloat(search.getMin_amount()), Float.parseFloat(search.getMax_amount()),
//								min_upload_date, max_upload_date, reference, value_date, branch_code);
//
//					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() != ""
//							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0) {
//
//						general = rtgsMapper.searchWithMinAmountAndUploadDateReceivable(
//								Float.parseFloat(search.getMin_amount()), min_upload_date, max_upload_date, reference,
//								value_date, branch_code);
//
//					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() == ""
//							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
//						general = rtgsMapper.searchWithMaxAmountAndUploadDateReceivable(
//								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date, reference,
//								value_date, branch_code);
//
//					}
//
//					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() != ""
//							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithAmountUploadDateAndMatchDateReceivable(
//								Float.parseFloat(search.getMin_amount()), Float.parseFloat(search.getMax_amount()),
//								min_upload_date, max_upload_date, min_match_date, max_match_date, reference, value_date,
//								branch_code);
//					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
//							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithMinAmountUploadDateAndMatchDateReceivable(
//								Float.parseFloat(search.getMin_amount()), min_upload_date, max_upload_date,
//								min_match_date, max_match_date, reference, value_date, branch_code);
//					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() != ""
//							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithMaxAmountUploadDateAndMatchDateReceivable(
//								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date,
//								min_match_date, max_match_date, reference, value_date, branch_code);
//					}
//
//					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
//							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithUploadDateAndMatchDateReceivable(min_upload_date,
//								max_upload_date, min_match_date, max_match_date, reference, value_date, branch_code);
//						System.out.println(general);
//					}
//
//					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() != ""
//							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithAmountAndMatchDateReceivable(
//								Float.parseFloat(search.getMin_amount()), Float.parseFloat(search.getMax_amount()),
//								min_match_date, max_match_date, reference, value_date, branch_code);
//					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() == ""
//							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithMinAmountAndMatchDateReceivable(
//								Float.parseFloat(search.getMin_amount()), min_match_date, max_match_date, reference,
//								value_date, branch_code);
//					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() != ""
//							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {
//
//						general = rtgsMapper.searchWithMaxAmountAndMatchDateReceivable(
//								Float.parseFloat(search.getMax_amount()), min_match_date, max_match_date, reference,
//								value_date, branch_code);
//					}
				} 
				
				
				else if (search.getType().equalsIgnoreCase("stock")) {
					System.out.println("min_upload_date: " + min_upload_date);
					System.out.println("max_upload_date: " + max_upload_date);
					System.out.println("search.getMin_amount(): " + search.getMin_amount());
					System.out.println("search.getMax_amount(): " + search.getMax_amount());
					System.out.println("min_match_date: " + min_match_date);
					System.out.println("max_match_date: " + max_match_date);
					System.out.println("reference: " + reference);
					System.out.println("value_date: " + value_date);
					System.out.println("branch_code: " + branch_code);
					
					general = searchMapper.getStockSearch(
							search.getMin_amount(), 
							search.getMax_amount(), 
							reference,
							search.getCategoryStock(),
							search.getSource_branch(),
 							branch_code, 
							min_upload_date, 
							max_upload_date, 
							min_match_date, 
							max_match_date);
					System.out.println("here is the min_upload_dateeeeee"+ min_upload_date);
				}
				
				else if (search.getType().equalsIgnoreCase("stockMMS")) {
					System.out.println("min_upload_date: " + min_upload_date);
					System.out.println("max_upload_date: " + max_upload_date);
					System.out.println("search.getMin_amount(): " + search.getMin_amount());
					System.out.println("search.getMax_amount(): " + search.getMax_amount());
					System.out.println("min_match_date: " + min_match_date);
					System.out.println("max_match_date: " + max_match_date);
					System.out.println("reference: " + reference);
					System.out.println("value_date: " + value_date);
					System.out.println("branch_code: " + branch_code);
					
					general = searchMapper.getStockMMSSearch(
							search.getMin_amount(), 
							search.getMax_amount(), 
							reference,
							search.getCategoryStockMMs(),
 							branch_code, 
							search.getStore_name(),
 							min_upload_date, 
							max_upload_date, 
							min_match_date, 
							max_match_date);
					System.out.println("here is the min_upload_dateeeeee"+ min_upload_date);
				}
				
				else if (search.getType().equalsIgnoreCase("QBS")) {

					if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() != ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithAmountQBS(Float.parseFloat(search.getMin_amount()),
								Float.parseFloat(search.getMax_amount()), reference, value_date, branch_code);

						// System.out.println(general);
					}
					// min
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() != ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithMinAmountQBS(Float.parseFloat(search.getMin_amount()), reference,
								value_date, branch_code);
					}
					// min
					// max
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithMaxAmountQBS(Float.parseFloat(search.getMax_amount()), reference,
								value_date, branch_code);

					}
					// max
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& reference != "" && value_date == "" && branch_code == "") {

						general = rtgsMapper.searchWithReferenceQBS(reference);

					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && reference == "" && branch_code == "") {

						general = rtgsMapper.searchWithValueDateQBS(value_date);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& branch_code != "" && reference == "" && value_date == "") {

						general = rtgsMapper.searchWithBranchCodeQBS(branch_code);
                     
					}
                    
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && reference != "" && branch_code == "") {

						general = rtgsMapper.searchWithValueDateAndReferenceQBS(value_date, reference);

					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && branch_code != "" && reference == "") {

						general = rtgsMapper.searchWithValueDateAndBranchCodeQBS(value_date, branch_code);

					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && branch_code != "" && reference != "") {

						general = rtgsMapper.searchWithRefValueDateBranchCodeQBS(reference, value_date, branch_code);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& reference != "" && branch_code != "" && value_date == "") {

						general = rtgsMapper.searchWithReferenceAndBranchCodeQBS(reference, branch_code);

					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
							&& search.getMin_amount() == "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithUploadDateQBS(min_upload_date, max_upload_date, reference,
								value_date, branch_code);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() == ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMatchDateQBS(min_match_date, max_match_date, reference,
								value_date, branch_code);

					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() != ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithAmountAndUploadDateQBS(Float.parseFloat(search.getMin_amount()),
								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date, reference,
								value_date, branch_code);

					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() != ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithMinAmountAndUploadDateQBS(
								Float.parseFloat(search.getMin_amount()), min_upload_date, max_upload_date, reference,
								value_date, branch_code);

					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() == ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
						general = rtgsMapper.searchWithMaxAmountAndUploadDateQBS(
								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date, reference,
								value_date, branch_code);
					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() != ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithAmountUploadDateAndMatchDateQBS(
								Float.parseFloat(search.getMin_amount()), Float.parseFloat(search.getMax_amount()),
								min_upload_date, max_upload_date, min_match_date, max_match_date, reference, value_date,
								branch_code);
					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMinAmountUploadDateAndMatchDateQBS(
								Float.parseFloat(search.getMin_amount()), min_upload_date, max_upload_date,
								min_match_date, max_match_date, reference, value_date, branch_code);
					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() != ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMaxAmountUploadDateAndMatchDateQBS(
								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date,
								min_match_date, max_match_date, reference, value_date, branch_code);
					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithUploadDateAndMatchDateQBS(min_upload_date, max_upload_date,
								min_match_date, max_match_date, reference, value_date, branch_code);
						System.out.println(general);
						System.out.println("----------------------------");
					}
					
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() != ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithAmountAndMatchDateQBS(Float.parseFloat(search.getMin_amount()),
								Float.parseFloat(search.getMax_amount()), min_match_date, max_match_date, reference,
								value_date, branch_code);
					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() == ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMinAmountAndMatchDateQBS(
								Float.parseFloat(search.getMin_amount()), min_match_date, max_match_date, reference,
								value_date, branch_code);
					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() != ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMaxAmountAndMatchDateQBS(
								Float.parseFloat(search.getMax_amount()), min_match_date, max_match_date, reference,
								value_date, branch_code);
					}
				} else if (search.getType().equalsIgnoreCase("Issue")) {

					if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() != ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithAmountIssue(Float.parseFloat(search.getMin_amount()),
								Float.parseFloat(search.getMax_amount()), reference, value_date, branch_code);
					}
					// min
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() != ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithMinAmountIssue(Float.parseFloat(search.getMin_amount()),
								reference, value_date, branch_code);

						System.out.println("-----------" + general);
					}
					// min
					// max
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithMaxAmountIssue(Float.parseFloat(search.getMax_amount()),
								reference, value_date, branch_code);

					}
					// max
					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& reference != "" && value_date == "" && branch_code == "") {

						general = rtgsMapper.searchWithReferenceIssue(reference);

					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && reference == "" && branch_code == "") {

						general = rtgsMapper.searchWithValueDateIssue(value_date);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& branch_code != "" && reference == "" && value_date == "") {

						general = rtgsMapper.searchWithBranchCodeIssue(branch_code);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && reference != "" && branch_code == "") {

						general = rtgsMapper.searchWithValueDateAndReferenceIssue(value_date, reference);

					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && branch_code != "" && reference == "") {

						general = rtgsMapper.searchWithValueDateAndBranchCodeIssue(value_date, branch_code);

					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& value_date != "" && branch_code != "" && reference != "") {

						general = rtgsMapper.searchWithRefValueDateBranchCodeIssue(reference, value_date,
								branch_code);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMin_amount() == ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0
							&& reference != "" && branch_code != "" && value_date == "") {

						general = rtgsMapper.searchWithReferenceAndBranchCodeIssue(reference, branch_code);

					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
							&& search.getMin_amount() == "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithUploadDateIssue(min_upload_date, max_upload_date, reference,
								value_date, branch_code);

					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() == ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMatchDateIssue(min_match_date, max_match_date, reference,
								value_date, branch_code);

					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() != ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithAmountAndUploadDateIssue(
								Float.parseFloat(search.getMin_amount()), Float.parseFloat(search.getMax_amount()),
								min_upload_date, max_upload_date, reference, value_date, branch_code);

					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() != ""
							&& search.getMax_amount() == "" && min_match_date == 0 && max_match_date == 0) {

						general = rtgsMapper.searchWithMinAmountAndUploadDateIssue(
								Float.parseFloat(search.getMin_amount()), min_upload_date, max_upload_date, reference,
								value_date, branch_code);

					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMin_amount() == ""
							&& search.getMax_amount() != "" && min_match_date == 0 && max_match_date == 0) {
						general = rtgsMapper.searchWithMaxAmountAndUploadDateIssue(
								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date, reference,
								value_date, branch_code);

					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() != ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithAmountUploadDateAndMatchDateIssue(
								Float.parseFloat(search.getMin_amount()), Float.parseFloat(search.getMax_amount()),
								min_upload_date, max_upload_date, min_match_date, max_match_date, reference, value_date,
								branch_code);
					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMinAmountUploadDateAndMatchDateIssue(
								Float.parseFloat(search.getMin_amount()), min_upload_date, max_upload_date,
								min_match_date, max_match_date, reference, value_date, branch_code);
					} else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() != ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMaxAmountUploadDateAndMatchDateIssue(
								Float.parseFloat(search.getMax_amount()), min_upload_date, max_upload_date,
								min_match_date, max_match_date, reference, value_date, branch_code);
					}

					else if (min_upload_date != 0 && max_upload_date != 0 && search.getMax_amount() == ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithUploadDateAndMatchDateIssue(min_upload_date, max_upload_date,
								min_match_date, max_match_date, reference, value_date, branch_code);
						System.out.println(general);
						System.out.println("----------------------------");
					}

					else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() != ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithAmountAndMatchDateIssue(
								Float.parseFloat(search.getMin_amount()), Float.parseFloat(search.getMax_amount()),
								min_match_date, max_match_date, reference, value_date, branch_code);
					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() == ""
							&& search.getMin_amount() != "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMinAmountAndMatchDateIssue(
								Float.parseFloat(search.getMin_amount()), min_match_date, max_match_date, reference,
								value_date, branch_code);
					} else if (min_upload_date == 0 && max_upload_date == 0 && search.getMax_amount() != ""
							&& search.getMin_amount() == "" && min_match_date != 0 && max_match_date != 0) {

						general = rtgsMapper.searchWithMaxAmountAndMatchDateIssue(
								Float.parseFloat(search.getMax_amount()), min_match_date, max_match_date, reference,
								value_date, branch_code);
					}
				}

				else if (search.getType().equalsIgnoreCase("FixedAssetMMS")) {
					System.out.println("this is the fixed AssetMMsssssssssssssssssssssssss");

					if (search.getReference() == "" && search.getTransaction_date() == "" && search.getDebit() != 0.0
							&& search.getCredit() != 0.0 && search.getMatch_date() == "") {
                      
						general = fixedAssetMapper.searchWithDebitCreditMMS(search);

					}
					// min
					else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithDebitMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() == "" && search.getBranch_code() == ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchWithCategoryMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() == "" && search.getBranch_code() != ""
							&& search.getCategory() == "") {
						general = fixedAssetMapper.searchWithBranchMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() == ""
							&& search.getCategory() == "") {
						general = fixedAssetMapper.searchWithTaghMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() != ""
							&& search.getCategory() == "") {
						general = fixedAssetMapper.searchWithTagbranchMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() == ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchCatagoryWithTagMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() == ""
							&& search.getCategory() == "") {
						general = fixedAssetMapper.searchWithReferenceTagMMS(search);

					}

					else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() == "" && search.getBranch_code() != ""
							&& search.getCategory() == "") {

						general = fixedAssetMapper.searchWithReferenceBranchMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() == "" && search.getBranch_code() == ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchWithReferenceCategoryMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() == "" && search.getBranch_code() != ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchWithBranchCategoryMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() != ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchWithBranchCategoryReferenceTagMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() == ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchWithCategoryReferenceTagMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() != ""
							&& search.getCategory() == "") {
						general = fixedAssetMapper.searchWithBranchReferenceTagMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() == "" && search.getBranch_code() != ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchWithBranchReferenceCategoryMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() != ""
							&& search.getCategory() != "") {
						System.out.println("--------------------------++");

						general = fixedAssetMapper.searchWithBranchTagCategoryMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithCreditMMS(search);

					}
					// max
					else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithReferenceMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithTransactionDateMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithMatchDateMMS(search);

					}

					else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithReference_TransactionDate_MatchDateMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithReferenceAndTransactionDate(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithReferenceAndMatchDate(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithReferenceAndDebit(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithReferenceAndCredit(search);

					}

					else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithTransactionDateAndCredit(search);

					} else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithTransactionDateAndDebit(search);

					} else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithTransactionDateAndMatchDateAndDebit(search);

					} else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithTransactionDateAndMatchDateAndCredit(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithDebitAndMatchDateAndReference(search);

					}

					else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithDebitAndCreditAndMatchDateAndReference(search);

					}

					else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithCreditAndMatchDateAndReference(search);

					} else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithDebitAndTransactionDateAndReference(search);

					} else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithCreditAndTransactionDateAndReference(search);

					}

					else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithMatchDateAndCredit(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithMatchDateAndDebit(search);

					} else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithAllCriteriaMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {
						System.out.println("________________________________");

						general = fixedAssetMapper.searchWithTransactionDateMatchDateMMS(search);

					}

					else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {
						System.out.println("___________________________ffff");

						general = fixedAssetMapper.searchWithDebitMatchDateCredit(search);

					}

					else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {
						general = fixedAssetMapper.searchWithReferenceAndTransactionDateMatchDateDebit(search);
					} else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithReferenceAndTransactionDateMatchDateCredit(search);

					}

					else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithDebitCreditAndTransactionDateMatchDate(search);

					}

					else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithDebitCreditAndTransactionDate(search);

					}

					else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithDebitCreditAndReference(search);

					}

				}

				else if (search.getType().equalsIgnoreCase("FixedAsset")) {

					if (search.getReference() == "" && search.getTransaction_date() == "" && search.getDebit() != 0.0
							&& search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithDebitCreditMMS(search);

					}
					// min
					else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithDebitMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() == "" && search.getBranch_code() == ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchWithCategoryMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() == "" && search.getBranch_code() != ""
							&& search.getCategory() == "") {
						general = fixedAssetMapper.searchWithBranchMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() == ""
							&& search.getCategory() == "") {
						general = fixedAssetMapper.searchWithTaghMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() != ""
							&& search.getCategory() == "") {
						general = fixedAssetMapper.searchWithTagbranchMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() == ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchCatagoryWithTagMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() == ""
							&& search.getCategory() == "") {
						general = fixedAssetMapper.searchWithReferenceTagMMS(search);

					}

					else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() == "" && search.getBranch_code() != ""
							&& search.getCategory() == "") {

						general = fixedAssetMapper.searchWithReferenceBranchMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() == "" && search.getBranch_code() == ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchWithReferenceCategoryMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() == "" && search.getBranch_code() != ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchWithBranchCategoryMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() != ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchWithBranchCategoryReferenceTagMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() == ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchWithCategoryReferenceTagMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() != ""
							&& search.getCategory() == "") {
						general = fixedAssetMapper.searchWithBranchReferenceTagMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() == "" && search.getBranch_code() != ""
							&& search.getCategory() != "") {
						general = fixedAssetMapper.searchWithBranchReferenceCategoryMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == ""
							&& search.getTag_number() != "" && search.getBranch_code() != ""
							&& search.getCategory() != "") {
						System.out.println("--------------------------++");

						general = fixedAssetMapper.searchWithBranchTagCategoryMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithCreditMMS(search);

					}
					// max
					else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithReferenceMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithTransactionDateMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithMatchDateMMS(search);

					}

					else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithReference_TransactionDate_MatchDateMMS(search);

					} else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithReferenceAndTransactionDate(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithReferenceAndMatchDate(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithReferenceAndDebit(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithReferenceAndCredit(search);

					}

					else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithTransactionDateAndCredit(search);

					} else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithTransactionDateAndDebit(search);

					} else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithTransactionDateAndMatchDateAndDebit(search);

					} else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithTransactionDateAndMatchDateAndCredit(search);

					} else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithDebitAndMatchDateAndReference(search);

					}

					else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithDebitAndCreditAndMatchDateAndReference(search);

					}

					else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithCreditAndMatchDateAndReference(search);

					} else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithDebitAndTransactionDateAndReference(search);

					} else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithCreditAndTransactionDateAndReference(search);

					}

					else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithMatchDateAndCredit(search);

					} else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithMatchDateAndDebit(search);

					} else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithAllCriteriaMMS(search);

					} else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {
						System.out.println("________________________________");

						general = fixedAssetMapper.searchWithTransactionDateMatchDateMMS(search);

					}

					else if (search.getReference() == "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {
						System.out.println("___________________________ffff");

						general = fixedAssetMapper.searchWithDebitMatchDateCredit(search);

					}

					else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() == 0.0 && search.getMatch_date() != "") {
						general = fixedAssetMapper.searchWithReferenceAndTransactionDateMatchDateDebit(search);
					} else if (search.getReference() != "" && search.getTransaction_date() != ""
							&& search.getDebit() == 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithReferenceAndTransactionDateMatchDateCredit(search);

					}

					else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() != 0.0 && search.getMatch_date() != "") {

						general = fixedAssetMapper.searchWithDebitCreditAndTransactionDateMatchDate(search);

					}

					else if (search.getReference() == "" && search.getTransaction_date() != ""
							&& search.getDebit() != 0.0 && search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithDebitCreditAndTransactionDate(search);

					}

					else if (search.getReference() != "" && search.getTransaction_date() == ""
							&& search.getDebit() != 0.0 && search.getCredit() != 0.0 && search.getMatch_date() == "") {

						general = fixedAssetMapper.searchWithDebitCreditAndReference(search);

					}

				}
			}

			if (general.size() < 50000) {

				return general;
			}

			else
				throw new CustomAllException(

						"extra-data");

		} catch (Exception e) {
			e.printStackTrace();
			
			if (Float.parseFloat(search.getMin_amount()) > Float.parseFloat(search.getMax_amount()))

				throw new CustomAllException(

						"incorrect_max_amount");
			else
				throw new CustomAllException(
						"add-other-searching-crateria");
			
		}
		// return null;

	}

	public MatchDetailAts matchDetailAts(HttpServletRequest request, String ids) {
		try {
			// if (util.isPermitted(request, "User", "match_partial_transaction_to_full")) {
			// util.registerActivity(request, "Update transaction", "-");
			JsonObject date_data_object = JsonParser.parseString(ids).getAsJsonObject();
			String[] id = date_data_object.get("id").getAsString().split(",");
			String[] value_date = date_data_object.get("value_date").getAsString().split(",");
			Long[] id_1 = new Long[id.length];
			id_1[0]=Long.parseLong(id[0]);
			
			//System.out.println(rtgsMapper.matchDetailAts(id_1[0]));
			System.out.println("---------------------"+id_1[0]+"======"+value_date[0].replace(" ", "\n"));
			return rtgsMapper.matchDetailAts(id_1[0],value_date[0].replace(" ", "\n"));
			// }
		} catch (Exception e) {
			throw new ExceptionsList(e);

		}
		// return null;
	}

	// public MatchDetail MatchDetail(HttpServletRequest request, Long id) {
	// // TODO Auto-generated method stub
	// return null;

	public MatchDetail MatchDetail(HttpServletRequest request, Long id, String type, String dr_cr,String reference) {
		try {

			MatchDetail result = null;
			if (type.equalsIgnoreCase("Payable")) {
				if (dr_cr.equalsIgnoreCase("CR"))

				{

					result = rtgsMapper.matchDetailPayableCredit(id);

				} else if (dr_cr.equalsIgnoreCase("DR")) {
					result = rtgsMapper.matchDetailPayableDebit(id);
				}
			} else if (type.equalsIgnoreCase("Receivable")) {
				if (dr_cr.equalsIgnoreCase("CR"))

				{

					result = rtgsMapper.matchDetailReceivableCredit(id);

				} else if (dr_cr.equalsIgnoreCase("DR")) {
					result = rtgsMapper.matchDetailReceivableDebit(id);
				}
			} else if (type.equalsIgnoreCase("QBS")) {

				result = rtgsMapper.matchDetailIssueQBS(id);

			} else if (type.equalsIgnoreCase("Issue")) {

				result = rtgsMapper.matchDetailIssue(id);
			}

			else {
				result = rtgsMapper.matchDetail(id,reference);
			}

			return result;
			// }
		} catch (Exception e) {
			throw new ExceptionsList(e);

		}
		// return null;
	}

	public List<File_rtgs__ats> get_all_ats_matched_with_reason(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_all_ats_transaction_matched_with_reason")) {
				util.registerActivity(request, "Get all  Ats transactions matched  with reason ", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
				// List<File_rtgs__ats> atsList =
				// rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
				// accountMapper.getUserAccountId(util.get_user_id(request)));
				// for(File_rtgs__ats aa : atsList) {
				// if(aa.getId() == 98473l){
				// System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				// }
				// }
				return rtgsMapper.get_all_ats_matched_with_reason(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_all__matched_with_reason(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "User", "get_all__transaction_matched_with_reason")) {
				util.registerActivity(request, "Get all  matched with reason  transactions", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
				// List<File_rtgs__ats> atsList =
				// rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
				// accountMapper.getUserAccountId(util.get_user_id(request)));
				// for(File_rtgs__ats aa : atsList) {
				// if(aa.getId() == 98473l){
				// System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				// }
				// }
				return rtgsMapper.get_all__matched_with_reason(recon_date.replace("-", ""),
						accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_payable_credit_for_reconcilation(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_payable_credit_for_reconciliation")) {
				util.registerActivity(request, "Get all credit transactions", "Get credit transactions to reconcile");
				System.out.println("here is the size: " + rtgsMapper.get_credit_for_reconcile());
//				return rtgsMapper.get_credit_for_reconcile(accountMapper.getUserAccountId(util.get_user_id(request)));
				return rtgsMapper.get_credit_for_reconcile();
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_payable_debit_for_reconcilation(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_payable_debit_for_reconciliation")) {
				util.registerActivity(request, "Get all debit transactions", "Get debit transactions to reconcile");
//				return rtgsMapper.get_debit_for_reconcile(accountMapper.getUserAccountId(util.get_user_id(request)));
				return rtgsMapper.get_debit_for_reconcile();
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Boolean match_payable_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "match_payable_transactions_manual")) {
				util.registerActivity(request, "match payable transactions manually", "-");
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

				String[] match_id = new String[id_1.length];
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String type;

				for (int i = 0; i < id_1.length; i++) {
					current_id = rtgsMapper.movePayableCreditData(id_1[i]);
					type = "payable_credit";
					match_id[i] = generateUniqueMatchId();

					System.out.println("----------" + match_id[i]);

					matched_data_id = rtgsMapper.addPayableMatched(current_id, match_id[i], date_now, "1",
							id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
					rtgsMapper.addUserPayableMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
							"1");
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
				}
				for (int i = 0; i < id_2.length; i++) {
					type = "payable_debit";
					current_id = rtgsMapper.movePayableDebitData(id_2[i], match_id[i]);
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
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

	public List<File_rtgs__> get_payable_credit_for_recon_auto(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all_payable_credit_transaction_for_recon_auto")) {
				util.registerActivity(request, "Get all payable credit transactions",
						"Get  payable credit transactions to match automatically");
//				return rtgsMapper
//						.get_payable_credit_for_recon_auto(accountMapper.getUserAccountId(util.get_user_id(request)));
				
				return rtgsMapper
						.get_payable_credit_for_recon_auto();
				
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	
	public List<File_rtgs__> get_payable_debit_for_recon_auto(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all_payable_debit_transaction_for_recon_auto")) {
				util.registerActivity(request, "Get all payable debit transactions",
						"Get payable debit transactions to match automatically");
//				return rtgsMapper
//						.get_payable_debit_for_recon_auto(accountMapper.getUserAccountId(util.get_user_id(request)));
				
				return rtgsMapper.get_payable_debit_for_recon_auto();
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_payable_credit_for_view(HttpServletRequest request, String matched_date) {
		try {
			if (util.isPermitted(request, "User", "get_payable_credit_matched")) {
				util.registerActivity(request, "Get payable credit matched trnsaction", "-");
//				return rtgsMapper.get_payable_credit_for_view(accountMapper.getUserAccountId(util.get_user_id(request)),
//						matched_date.replace("-", ""));
				System.out.println("payable gett============================="+ rtgsMapper.get_payable_credit_for_view(matched_date.replace("-", "")));
				return rtgsMapper.get_payable_credit_for_view( 
						matched_date.replace("-", ""));
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_payable_debit_for_view(HttpServletRequest request, String matched_date) {
		try {
			if (util.isPermitted(request, "User", "get_payable_debit_matched")) {
				util.registerActivity(request, "Get payable debit matched trnsaction", "-");
//				return rtgsMapper.get_payable_debit_for_view(accountMapper.getUserAccountId(util.get_user_id(request)),
//						matched_date.replace("-", ""));
				System.out.println("debit transactionnnnnnnnnnnnnn"+ rtgsMapper.get_payable_debit_for_view(
						matched_date.replace("-", "")) );
				return rtgsMapper.get_payable_debit_for_view(
						matched_date.replace("-", ""));
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public boolean unmatch_Payabletransactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "unmatch_payable_transactions")) {
				util.registerActivity(request, "unmatch transactions payable", "-");
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
					System.out.println(" id=" + id_2[i]);
					current_id = rtgsMapper.movepayableDebitMatched(id_2[i]);
					type = "data_payable_debit";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					System.out.println("edite reason updated after unmatche id----->" + id_2[i]);

					// matched_id = rtgsMapper.findMatchedId(id_2[i]);
					// rtgsMapper.deleteMatchTransaction(matched_id);
					// rtgsMapper.updateUnMatchStatus_(id_2[i]);
				}

				for (int i = 0; i < id_1.length; i++) {
					System.out.println("Ats id=" + id_1[i]);
					rtgsMapper.deletePayableMatched(id_1[i]);

					current_id = rtgsMapper.movepayableCreditMatched(id_1[i]);
					type = "data_payable_debit";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					System.out.println("edite reason updated after unmatche id----->" + id_1[i]);
					// rtgsMapper.deleteUserRtgsMatched(id_1[i]);
					rtgsMapper.deleteUserPayableMatched(rtgsMapper.getPayableMatchedId(id_1[i]));

					// rtgsMapper.updateUnMatchStatus_ats(id_1[i]);
					// Long ids =
					// rtgsMapper.getRtgsMatchedPartialIdFromRtgsMatchedByMatchId(matched_id);
					// rtgsMapper.deleteUserRtgsMatched(ids);
					// rtgsMapper.addUserRtgsMatched(util.get_user_id(request),
					// rtgsMapper.addRtgsMatched(id_1[i], match_id, date_now, "1",
					// id_1.length >1 && id_2.length>1? "1".toString(): "0".toString(), "1", "1"),
					// date_now, "1", "1");
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

	public boolean match_all_payable_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "match_all_payable_transactions_auto")) {
				util.registerActivity(request, "match all  payable transactions",
						"match all  payable transactions automatically");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] _id = id_data_object.get("_id").getAsString().split(",");
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
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String typee;
				for (int i = 0; i < id_1.length; i++) {
					String match_id = generateUniqueMatchId();
					current_id = rtgsMapper.movePayableCreditData(id_1[i]);
					typee = "payable_credit";
					matched_data_id = rtgsMapper.addPayableMatched(current_id, match_id, date_now, "1",
							id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
					rtgsMapper.addUserPayableMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
							"1");
					rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_1[i]);

					current_id = rtgsMapper.movePayableDebitData(id_2[i], match_id);
					typee = "payable_debit";
					rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_2[i]);

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

	public boolean match_payable_transactions_with_reason(HttpServletRequest request, String data_ids) {
		// TODO Auto-generated method stub
		try {
			if (util.isPermitted(request, "User", "match_Payable_transactions_with_reason")) {
				util.registerActivity(request, "match payable transactions with reason", "-");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");
				String reason = id_data_object.get("reason").getAsString();
				// String type = id_data_object.get("type").getAsString();
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
				String typee;
				for (int i = 0; i < id_1.length; i++) {
					current_id = rtgsMapper.movePayableCreditData(id_1[i]);
					typee = "payable_credit";
					matched_data_id = rtgsMapper.addPayableMatched(current_id, match_id, date_now, "1",
							id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
					rtgsMapper.addUserPayableMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
							"1");
					rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_1[i]);
				}
				for (int i = 0; i < id_2.length; i++) {
					typee = "payable_debit";
					current_id = rtgsMapper.movePayableDebitData(id_2[i], match_id);
					rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_2[i]);
				}

				rtgsMapper.addMatchReason(match_id, reason, "payable", "2", "1", "1");

				return true;
			} else {
				System.out.println("No user does not have permission.");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_edited_payable(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_edited_payable_transaction")) {
				util.registerActivity(request, "Get all edited payable transaction", "-");
				System.out.println("==========================" + rtgsMapper.get_edited_payable());
				return rtgsMapper.get_edited_payable();
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_edited_detail_payable(HttpServletRequest request, Long id) {
		try {
			if (util.isPermitted(request, "User", "get_detail_edited_payable_transaction")) {
				util.registerActivity(request, "Get detail edited payable transaction", "-");
				System.out.println("id-------->" + rtgsMapper.get_edited_detail_(id));
				return rtgsMapper.get_edited_detail_payable(id);
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}

	}

	public List<File_rtgs__> get_payable_credit_matched_with_reason(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all__transaction_matched_with_reason")) {
				util.registerActivity(request, "Get all  matched with reason  transactions", "-");
				return rtgsMapper.get_payable_creditmatched_with_reason();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_payable_debit_matched_with_reason(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all__transaction_matched_with_reason")) {
				util.registerActivity(request, "Get all  matched with reason  transactions", "-");

				// List<File_rtgs__ats> atsList =
				// rtgsMapper.get_ats_for_recon(recon_date.replace("-", ""),
				// accountMapper.getUserAccountId(util.get_user_id(request)));
				// for(File_rtgs__ats aa : atsList) {
				// if(aa.getId() == 98473l){
				// System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				// }
				// }
				return rtgsMapper.get_payable_debitmatched_with_reason();

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Transactionhistory matchDetailFixed(HttpServletRequest request, Long id) {
		try {

			Transactionhistory result = null;

			result = fixedAssetMapper.matchDetailFixed(id);

			return result;
			// }
		} catch (Exception e) {
			throw new ExceptionsList(e);

		}
	}
	
	public Transactionhistory matchDetailStock(HttpServletRequest request, Long id) {
		try {

			Transactionhistory result = null;

			result = fixedAssetMapper.matchDetailStock(id);

			return result;
			// }
		} catch (Exception e) {
			throw new ExceptionsList(e);

		}
	}
	
	public Transactionhistory matchDetailStockMms(HttpServletRequest request, Long id) {
		System.out.println("stock serviceeeeeeeeeeeeeeeeeeeeeee");

		try {

			Transactionhistory result = null;

			result = fixedAssetMapper.matchDetailStockMms(id);

			return result;
			// }
		} catch (Exception e) {
			throw new ExceptionsList(e);

		}
	}


	public Transactionhistory matchDetailFixedMms(HttpServletRequest request, Long id) {
		try {

			Transactionhistory result = null;

			result = fixedAssetMapper.matchDetailFixedMms(id);

			return result;
			
			// }
		} catch (Exception e) {
			throw new ExceptionsList(e);

		}
	}

}
// }
