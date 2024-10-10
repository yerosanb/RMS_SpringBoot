package com.example.demo.user.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ExceptionsList;
import com.example.demo.user.mapper.MapperAccount;
import com.example.demo.user.mapper.MapperReceivable;
import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;
import com.example.demo.utils.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class ReceivableService {

	@Autowired
	private MapperReceivable receivableMapper;

	@Autowired
	private Utils util;
	
	@Autowired
	private MapperAccount accountMapper;

	public List<File_rtgs__> get_credit_for_recon(HttpServletRequest request) {

		try {
			if (util.isPermitted(request, "User", "get_receivable_credit_transactions_for_recon_manual")) {
				util.registerActivity(request, "Get all receivable credit transactions",
						"Get receivable credit transactions for recon manual");
				List<File_rtgs__> List = receivableMapper.get_credit_for_recon();
				for (File_rtgs__ aa : List) {
					System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				}
				return List;
			} else {
				System.out.println("No user does not have permission. get_receivable_credit_transactions_for_recon_manual");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	public List<File_rtgs__> get_debit_for_recon(HttpServletRequest request) {

		try {
			if (util.isPermitted(request, "User", "get_receivable_debit_transactions_for_recon_manual")) {
				util.registerActivity(request, "Get all receivable debit transactions",
						"Get receivable debit transactions for recon manual");
				List<File_rtgs__> List = receivableMapper.get_debit_for_recon();
				for (File_rtgs__ aa : List) {
					System.out.println("amount:::::::::::::::::::::: " + aa.getAmount());
				}
				return List;
			} else {
				System.out.println("No user does not have permission. get_receivable_debit_transactions_for_recon_manual");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public List<File_rtgs__> get_receivable_credit_for_recon_auto(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all_receivable_credit_transaction_for_recon_auto")) {
				util.registerActivity(request, "Get all receivable credit transactions",
						"Get  receivable credit transactions to match automatically");
				System.out.println("id------>"+util.get_user_id(request));
			return receivableMapper.get_receivable_credit_for_recon_auto(accountMapper.getUserAccountId(util.get_user_id(request)));
			
			} else {
				System.out.println("No user does not have permission. get_all_receivable_credit_transaction_for_recon_auto");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_receivable_debit_for_recon_auto(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all_receivable_debit_transaction_for_recon_auto")) {
				util.registerActivity(request, "Get all receivable debit transactions",
					"Get receivable debit transactions to match automatically");
				return receivableMapper.get_receivable_debit_for_recon_auto(accountMapper.getUserAccountId(util.get_user_id(request)));
			} else {
				System.out.println("No user does not have permission. get_all_receivable_debit_transaction_for_recon_auto");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public List<File_rtgs__> get_receivable_credit_matched(HttpServletRequest request,String matched_date) {
		try {
			if (util.isPermitted(request, "User", "get_receivable_credit_matched")) {
				util.registerActivity(request, "Get receivable credit matched trnsaction", "-");
				return receivableMapper.get_receivable_credit_matched(
						accountMapper.getUserAccountId(util.get_user_id(request)),matched_date.replace("-", ""));
			} else {
				System.out.println("No user does not have permission. get_receivable_credit_matched");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_receivable_debit_matched(HttpServletRequest request,String matched_date) {
		try {
			if (util.isPermitted(request, "User", "get_receivable_debit_matched")) {
				util.registerActivity(request, "Get receivable debit matched trnsaction", "-");
				return receivableMapper.get_receivable_debit_matched(
						accountMapper.getUserAccountId(util.get_user_id(request)),matched_date.replace("-", ""));
			} else {
				System.out.println("No user does not have permission. get_receivable_debit_matched");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public Boolean match_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "match_receivable_transactions_manual")) {
				util.registerActivity(request, "match transactions manually", "match receivable transactions manually");
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

				if (id_1[0] != null && 1 == receivableMapper.isCredit(id_1[0])) {

					for (int i = 0; i < id_1.length; i++) {
						current_id = receivableMapper.moveReceivableCredit(id_1[i],match_id);
						type = "receivable_cr";
						matched_data_id = receivableMapper.addReceivableMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						receivableMapper.addUserReceivableMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						receivableMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						type = "receivable_dr";
						current_id = receivableMapper.moveReceivableDebit(id_2[i], match_id);
						receivableMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					}
				} else {
					System.out.println("it is not credit");
				}

				return true;
			} else {
				System.out.println("No user does not have permission. match_receivable_transactions_manual");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public Boolean match_transactions_with_reason(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "match_receivable_transactions_with_reason")) {
				util.registerActivity(request, "match transactions with reason", "match receivable transactions with reason");
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
				if (id_1[0] != null && 1 == receivableMapper.isCredit(id_1[0])) {
					for (int i = 0; i < id_1.length; i++) {
						current_id = receivableMapper.moveReceivableCredit(id_1[i],match_id);
						typee = "data_receivable";
						matched_data_id = receivableMapper.addReceivableMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						receivableMapper.addUserReceivableMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						receivableMapper.updateEditReason(current_id, matched_data_id, typee, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						typee = "data_receivable";
						current_id = receivableMapper.moveReceivableDebit(id_2[i], match_id);
						receivableMapper.updateEditReason(current_id, matched_data_id, typee, id_2[i]);
					}
					if (type.equalsIgnoreCase("amount")) {
						System.out.println("reference-rtgs");
						receivableMapper.addMatchReason(match_id, reason, "receivable_cr", "1", "1", "1");
					} else {
						System.out.println("not known");
						receivableMapper.addMatchReason(match_id, reason, "receivable_dr", "2", "1", "1");
					}

				} else {
					System.out.println("it is not receivable");
				}

				return true;
			} else {
				System.out.println("No user does not have permission. match_receivable_transactions_with_reason");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
//	delete_transactions
	public Boolean delete_transactions(HttpServletRequest request, String datas) {
		try {
			if (util.isPermitted(request, "User", "delete_receivable_transactions")) {
				util.registerActivity(request, "Delete transactions", "Delete receivable transactions");
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
				String type1 =  "data_receivable";
				for (int i = 0; i < ids.length; i++) {
						System.out.println("it is  and the id is: " + ids[i]);
						Long reason_id = receivableMapper.addReasonForEdit(ids[i], user_id, type1, reason,
								new Date().toString(), "1", "1", "2");
						receivableMapper.moveDeletedData(ids[i], reason_id);
						receivableMapper.deleteTransaction(ids[i]);
					
				}
				return true;
			} else {
				System.out.println("No user does not have permission. delete_receivable_transactions");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public Boolean update_transaction(HttpServletRequest request, File_rtgs__ats edit_data) {
		try {
			if (util.isPermitted(request, "User", "update_receivable_transaction")) {
				util.registerActivity(request, "Update transaction", "Update receivable transaction");
				Long user_id = util.get_user_id(request);
				System.out.println("reference:-------------------> " + edit_data.getReference());
				String type = "data_receivable";
				
					Long edit_reason_id = receivableMapper.addReasonForEdit(edit_data.getId(), user_id, type,
							edit_data.getReason(), new Date().toString(), "1", "1", "1");
					receivableMapper.moveOldData(edit_data.getId(), edit_reason_id);
					receivableMapper.updateTransaction(edit_data);
					receivableMapper.moveEditedData(edit_data.getId(), edit_reason_id);
					return true;
				

			} else {
				System.out.println("No user does not have permission. update_receivable_transaction");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public boolean match_all_receivable_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "match_all_receivable_transactions_auto")) {
				util.registerActivity(request, "match all  receivable transactions", "match all  receivable transactions automatically");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] _id = id_data_object.get("_id").getAsString().split(",");
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");
				String[] amount_1_string = id_data_object.get("amount_1").getAsString().split(",");
				String[] amount_2_string = id_data_object.get("amount_2").getAsString().split(",");
				Long[] id_1 = new Long[id_1_string.length];
				for (int i = 0; i < id_1_string.length; i++) {
					id_1[i] = Long.parseLong(id_1_string[i]);
				}

				Long[] id_2 = new Long[id_2_string.length];
				for (int i = 0; i < id_2_string.length; i++) {
					id_2[i] = Long.parseLong(id_2_string[i]);
				}
				
				double[] amount_1 = new double[amount_1_string.length];
				for (int i = 0; i < amount_1_string.length; i++) {
					amount_1[i] = Double.parseDouble(amount_1_string[i]);
				}
				
				double[] amount_2 = new double[amount_2_string.length];
				for (int i = 0; i < amount_2_string.length; i++) {
					amount_2[i] = Double.parseDouble(amount_2_string[i]);
				}
				
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String typee;
				for (int i = 0; i < id_1.length; i++) {
					for (int j = 0; j < id_2.length; j++) {
						if (amount_1[i] == amount_2[j]) {
					String match_id = generateUniqueMatchId();
						current_id = receivableMapper.moveReceivableCredit(id_1[i], match_id);
						typee = "receivable_credit";
						matched_data_id = receivableMapper.addReceivableMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
						receivableMapper.addUserReceivableMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						receivableMapper.updateEditReason(current_id, matched_data_id, typee, id_1[i]);

						current_id = receivableMapper.moveReceivableDebit((id_2[j]), match_id);
						typee = "receivable_debit";
						receivableMapper.updateEditReason(current_id, matched_data_id, typee, (id_2[j]));
						amount_2[j] = 0.0;
						   break;
							
					} else {
						continue;
					}
				}

				}

				return true;
			} else {
				System.out.println("No user does not have permission. match_all_receivable_transactions_auto ");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public boolean unmatch_receivable_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "User", "unmatch_receivable_transactions")) {
				util.registerActivity(request, "unmatch transactions receivable", "unmatch transactions receivable");
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

				Long current_id = null;
				String type = "";
				Long matched_data_id = null;
				for (int i = 0; i < id_2.length; i++) {
					System.out.println(" id=" + id_2[i]);
					current_id = receivableMapper.moveReceivableDebitMatched(id_2[i]);
					type = "data_receivable_debit";
					receivableMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					System.out.println("edite reason updated after unmatche id----->" + id_2[i]);

				}

				for (int i = 0; i < id_1.length; i++) {
					System.out.println("Ats id=" + id_1[i]);
					receivableMapper.deleteReceivableMatched(id_1[i]);

					current_id = receivableMapper.moveReceivableCreditMatched(id_1[i]);
					type = "data_receivable_credit";
					receivableMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					System.out.println("edite reason updated after unmatche id----->" + id_1[i]);
					// rtgsMapper.deleteUserRtgsMatched(id_1[i]);
					receivableMapper.deleteUserReceivableMatched(receivableMapper.getReceivableMatchedId(id_1[i]));

				}

				return true;
			} else {
				System.out.println("No user does not have permission. unmatch_receivable_transactions");
				return false;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	
	public List<File_rtgs__> get_receivable_credit_matched_with_reason(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all__transaction_matched_with_reason")) {
				util.registerActivity(request, "Get all  matched with reason  transactions", "-");
				return receivableMapper.get_receivable_credit_matched_with_reason(
						accountMapper.getUserAccountId(util.get_user_id(request)));

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<File_rtgs__> get_receivable_debit_matched_with_reason(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "User", "get_all__transaction_matched_with_reason")) {
				util.registerActivity(request, "Get all  matched with reason  transactions", "-");

				return receivableMapper.get_receivable_debit_matched_with_reason(
						accountMapper.getUserAccountId(util.get_user_id(request)));

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
}

