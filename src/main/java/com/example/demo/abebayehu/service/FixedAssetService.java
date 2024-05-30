package com.example.demo.abebayehu.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Exception.ExceptionsList;
import com.example.demo.abebayehu.entity.Mms_trial_balance;
import com.example.demo.abebayehu.entity.Raw_fixed_core;
import com.example.demo.abebayehu.entity.Raw_fixed_mms;
import com.example.demo.abebayehu.entity.core_detail_deleted;
import com.example.demo.abebayehu.entity.mms_detail_deleted;
import com.example.demo.abebayehu.entity.view_fixed_core_deleted;
import com.example.demo.abebayehu.entity.view_fixed_mms_deleted;
import com.example.demo.abebayehu.mapper.MapperFixedAsset;
import com.example.demo.user.model.File_rtgs_awb_core;
import com.example.demo.utils.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class FixedAssetService {
	@Autowired
	private Utils util;

	@Autowired
	private MapperFixedAsset mapperFixedAsset;

	public List<Raw_fixed_core> get_raw_fixed_core_for_recon_computer(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_raw_fixed_core_data_for_manual_recon")) {
				util.registerActivity(request, "Get all Fixed Asset Core transactions",
						"Get all Fixed Asset Core transactions to match manually");
				List<Raw_fixed_core> fixedCoreList = mapperFixedAsset.get_raw_fixed_core_for_recon_computer();

				return fixedCoreList;
			} else {
				System.out.println("No user does not have permission in get_raw_fixed_core_for_recon");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<Raw_fixed_core> get_raw_fixed_core_for_recon_equipment(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_raw_fixed_core_data_for_manual_recon")) {
				util.registerActivity(request, "Get all Fixed Asset Core transactions",
						"Get all Fixed Asset Core transactions to match manually");
				List<Raw_fixed_core> fixedCoreList = mapperFixedAsset.get_raw_fixed_core_for_recon_equipment();

				return fixedCoreList;
			} else {
				System.out.println("No user does not have permission in get_raw_fixed_core_for_recon");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<Raw_fixed_core> get_raw_fixed_core_for_recon_vehicle(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_raw_fixed_core_data_for_manual_recon")) {
				util.registerActivity(request, "Get all Fixed Asset Core transactions",
						"Get all Fixed Asset Core transactions to match manually");
				List<Raw_fixed_core> fixedCoreList = mapperFixedAsset.get_raw_fixed_core_for_recon_vehicle();

				return fixedCoreList;
			} else {
				System.out.println("No user does not have permission in get_raw_fixed_core_for_recon");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<Raw_fixed_core> get_raw_fixed_core_for_recon_furniture(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_raw_fixed_core_data_for_manual_recon")) {
				util.registerActivity(request, "Get all Fixed Asset Core transactions",
						"Get all Fixed Asset Core transactions to match manually");
				List<Raw_fixed_core> fixedCoreList = mapperFixedAsset.get_raw_fixed_core_for_recon_furniture();

				return fixedCoreList;
			} else {
				System.out.println("No user does not have permission in get_raw_fixed_core_for_recon");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<Raw_fixed_mms> get_raw_fixed_mms_for_recon_computer(HttpServletRequest request,
			String transaction_date) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_raw_fixed_mms_data_for_manual_recon")) {
				util.registerActivity(request, "Get all Fixed Asset MMS transactions",
						"Get all Fixed Asset MMS transactions to match manually");
				List<Raw_fixed_mms> fixedMMSList = mapperFixedAsset
						.get_raw_fixed_mms_for_recon_computer(transaction_date);

				return fixedMMSList;
			} else {
				System.out.println("No user does not have permission in get_raw_fixed_core_for_recon");
				return null;
			}
		} catch (Exception e) {
			System.out.print("exxxxxx--------->" + e);
			throw new ExceptionsList(e);
		}
	}

	public List<Raw_fixed_mms> get_raw_fixed_mms_for_recon_equipment(HttpServletRequest request,
			String transaction_date) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_raw_fixed_mms_data_for_manual_recon")) {
				util.registerActivity(request, "Get all Fixed Asset MMS transactions",
						"Get all Fixed Asset MMS transactions to match manually");
				List<Raw_fixed_mms> fixedMMSList = mapperFixedAsset
						.get_raw_fixed_mms_for_recon_equipment(transaction_date);

				return fixedMMSList;
			} else {
				System.out.println("No user does not have permission in get_raw_fixed_core_for_recon");
				return null;
			}
		} catch (Exception e) {
			System.out.print("exxxxxx--------->" + e);
			throw new ExceptionsList(e);
		}
	}

	public List<Raw_fixed_mms> get_raw_fixed_mms_for_recon_furniture(HttpServletRequest request,
			String transaction_date) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_raw_fixed_mms_data_for_manual_recon")) {
				util.registerActivity(request, "Get all Fixed Asset MMS transactions",
						"Get all Fixed Asset MMS transactions to match manually");
				List<Raw_fixed_mms> fixedMMSList = mapperFixedAsset
						.get_raw_fixed_mms_for_recon_furniture(transaction_date);

				return fixedMMSList;
			} else {
				System.out.println("No user does not have permission in get_raw_fixed_core_for_recon");
				return null;
			}
		} catch (Exception e) {
			System.out.print("exxxxxx--------->" + e);
			throw new ExceptionsList(e);
		}
	}

	public List<Raw_fixed_mms> get_raw_fixed_mms_for_recon_vehicle(HttpServletRequest request,
			String transaction_date) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_raw_fixed_mms_data_for_manual_recon")) {
				util.registerActivity(request, "Get all Fixed Asset MMS transactions",
						"Get all Fixed Asset MMS transactions to match manually");
				List<Raw_fixed_mms> fixedMMSList = mapperFixedAsset
						.get_raw_fixed_mms_for_recon_vehicle(transaction_date);

				return fixedMMSList;
			} else {
				System.out.println("No user does not have permission in get_raw_fixed_core_for_recon");
				return null;
			}
		} catch (Exception e) {
			System.out.print("exxxxxx--------->" + e);
			throw new ExceptionsList(e);
		}
	}

	public Boolean match_transactions(HttpServletRequest request, List<List<Long>> matched_fixed_asset_ids) {
		try {
			if (util.isPermitted(request, "FixedAsset", "match_fixed_asset_transactions_manually")) {
				util.registerActivity(request, "match fixed asset transactions manually", "-");
				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				int mms_length = matched_fixed_asset_ids.get(0).size();
				int core_length = matched_fixed_asset_ids.get(1).size();

				int count = 0;
				for (List<Long> matched_fixed_asset_ids_list : matched_fixed_asset_ids) {
					if (count == 0)
						for (Long mms_id : matched_fixed_asset_ids_list) {
							current_id = mapperFixedAsset.moveFixedAssetMmsData(mms_id);
							matched_data_id = mapperFixedAsset.addFixedAssetMatched(current_id, match_id, date_now, "1",
									mms_length > 1 && core_length > 1 ? "1".toString() : "0".toString(), "1", "1");

							mapperFixedAsset.addUserFixedMatched(util.get_user_id(request), matched_data_id, date_now,
									"1", "1", "1");
						}
					else {
						for (Long core_id : matched_fixed_asset_ids_list) {
							current_id = mapperFixedAsset.moveFixedAssetCoreData(core_id, match_id);
						}
					}
					count++;
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

	public Boolean match_transactions_reversal(HttpServletRequest request, List<List<Long>> matched_fixed_asset_ids) {
		try {
			if (util.isPermitted(request, "FixedAsset", "match_fixed_asset_transactions_manually")) {
				util.registerActivity(request, "match fixed asset transactions manually reversal", "-");
				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));

				for (List<Long> matched_fixed_asset_ids_list : matched_fixed_asset_ids) {

					for (Long core_id : matched_fixed_asset_ids_list) {
						System.out.println("core-id=" + core_id);
						mapperFixedAsset.moveFixedAssetCoreReversalData(core_id, match_id, date_now,
								mapperFixedAsset.fullName(util.get_user_id(request)));
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

	public Boolean match_transactions_with_reason(HttpServletRequest request, List<ArrayList> matched_fixed_asset_ids) {
		try {
			if (util.isPermitted(request, "FixedAsset", "match_fixed_asset_transactions_with_reason")) {
				util.registerActivity(request, "match fixed asset transactions with reason", "-");

				List<Integer> core_fixed_ids = matched_fixed_asset_ids.get(0);
				List<Integer> mms_fixed_ids = matched_fixed_asset_ids.get(1);

				int mms_length = mms_fixed_ids.size();
				int core_length = core_fixed_ids.size();

				String reason = matched_fixed_asset_ids.get(2).get(0).toString();
				String type = matched_fixed_asset_ids.get(2).get(1).toString();

				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;

				for (int mms_id : mms_fixed_ids) {
					current_id = mapperFixedAsset.moveFixedAssetMmsData((long) mms_id);
					matched_data_id = mapperFixedAsset.addFixedAssetMatched(current_id, match_id, date_now, "1",
							mms_length > 1 && core_length > 1 ? "1".toString() : "0".toString(), "1", "1");
					mapperFixedAsset.addUserFixedMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
							"1");
				}

				for (int core_id : core_fixed_ids) {
					current_id = mapperFixedAsset.moveFixedAssetCoreData((long) core_id, match_id);
				}

				if (type.equalsIgnoreCase("reference")) {
					mapperFixedAsset.addMatchReason(match_id, reason, "fixed asset", "1", "1", "1");
				} else {
					mapperFixedAsset.addMatchReason(match_id, reason, "fixed asset", "2", "1", "1");
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

//	delete_transactions
	public Boolean delete_transactions(HttpServletRequest request, String datas) {
		try {
			if (util.isPermitted(request, "User", "delete_transactions")
					|| util.isPermitted(request, "FixedAsset", "delete_transactions")) {
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
					if (type != null && type.equalsIgnoreCase("mms")) {
						type1 = "data_fixed_mms";
						Long reason_id = mapperFixedAsset.addReasonForEdit(ids[i], user_id, type1, reason,
								new Date().toString(), "1", "1", "2");
						mapperFixedAsset.moveDeletedMmsData(ids[i], reason_id);
						mapperFixedAsset.deleteTransaction(ids[i]);
					} else if (type != null && type.equalsIgnoreCase("mms_waiting")) {
						type1 = "data_fixed_mms_waiting";
						Long reason_id = mapperFixedAsset.addReasonForEdit(ids[i], user_id, type1, reason,
								new Date().toString(), "1", "1", "2");
						mapperFixedAsset.moveDeletedMmsWaitingData(ids[i], reason_id);
						mapperFixedAsset.deleteWaitingTransaction(ids[i]);
					}

					else if (type != null && type.equalsIgnoreCase("core")) {
						type1 = "data_fixed_core";
						Long reason_id = mapperFixedAsset.addReasonForEdit(ids[i], user_id, type1, reason,
								new Date().toString(), "1", "1", "2");
						mapperFixedAsset.moveDeletedFixedCoreData(ids[i], reason_id);
						mapperFixedAsset.deleteFixedCoreTransaction(ids[i]);
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

	public List<view_fixed_core_deleted> get_edited_fixed_core(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_deleted_fixed_core_transaction")) {
				util.registerActivity(request, "Get all deleted fixed core transaction", "-");
				System.out.println("nothing special==============>" + mapperFixedAsset.get_edited_fixed_core());
				return mapperFixedAsset.get_edited_fixed_core();
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<core_detail_deleted> get_edited_detail_fixed_core(HttpServletRequest request, Long id) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_deleted_fixed_core_transaction")) {
				util.registerActivity(request, "Get detail deleted fixed core transaction", "-");
				return mapperFixedAsset.get_edited_detail_fixed_core(id);
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<view_fixed_mms_deleted> get_edited_fixed_mms(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "fixedAsset", "get_deleted_fixed_mms_transaction")) {
				util.registerActivity(request, "Get all deleted fixed mms transaction", "-");
				System.out.println("nothing specialllll==============>" + mapperFixedAsset.get_deleted_fixed_mms());
				return mapperFixedAsset.get_deleted_fixed_mms();
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<mms_detail_deleted> get_edited_detail_fixed_mms(HttpServletRequest request, Long id) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_deleted_fixed_mms_transaction")) {
				util.registerActivity(request, "Get detail edited issue core transaction", "-");
				System.out.println("menchachat =,,,,,,,,,,,,,,,,,,,,>");

				return mapperFixedAsset.get_edited_detail_fixed_mms(id);
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Mms_trial_balance getInitialFixedAssetEndingBalances(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_fixed_asset_mms_balances")) {
				util.registerActivity(request, "Get fixed asset mms balances", "-");
				Mms_trial_balance aa = mapperFixedAsset.getInitialFixedAssetEndingBalances();
//				System.out.println(": " + aa.getMms_fur());
				return aa;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Mms_trial_balance getInitialStockEndingBalances(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_fixed_asset_mms_balances")) {
				util.registerActivity(request, "Get Stock mms balances", "-");
				Mms_trial_balance aa = mapperFixedAsset.getInitialsStockEndingBalances();
//				System.out.println(": " + aa.getMms_fur());
				return aa;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Boolean updateFixedAssetBalance(HttpServletRequest request, Mms_trial_balance req_info) {
		try {
			if (util.isPermitted(request, "FixedAsset", "update_fixed_assetBalance")) {
				util.registerActivity(request, "Update Fixed asset balance.",
						"Update Fixed asset or stock  balance. type: " + req_info.getType() + " add_sub: "
								+ req_info.getAdd_subtract() + " change amount: " + req_info.getAmount());
				if (req_info.getType().equalsIgnoreCase("comp")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_com_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_com_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("fur")) {
					System.out.println("furnitureeeeeeeeeeeee: " + req_info.getAmount());
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_fur_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_fur_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("oe")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_oe_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_oe_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("mv")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_mv_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_mv_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("Stationary")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_stock_Stationary_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_stock_Stationary_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("Tools")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_stock_tools_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_stock_tools_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("Spares")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_stock_spares_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_stock_spares_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("Uniform")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_stock_uniform_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_stock_uniform_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("Accessory")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_stock_accessory_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_stock_accessory_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("check")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_stock_check_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_stock_check_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("Sanitory")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_stock_sanitory_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_stock_sanitory_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("Computer")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_stock_computer_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_stock_computer_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("Furniture")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset.updateFixedAssetBalance_stock_furniture_add(req_info.getAmount());
					else
						return mapperFixedAsset.updateFixedAssetBalance_stock_furniture_sub(req_info.getAmount());
				} else if (req_info.getType().equalsIgnoreCase("Equipment")) {
					if (req_info.getAdd_subtract().equalsIgnoreCase("add"))
						return mapperFixedAsset
								.updateFixedAssetBalance_stock_office_equipment_add(req_info.getAmount());
					else
						return mapperFixedAsset
								.updateFixedAssetBalance_stock_office_equipment_sub(req_info.getAmount());
				} else {
					System.out.println("it is false: " + req_info.getType());
					return false;
				}

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
	public Boolean change_date( String disposed_date, String waiting_date, String removed_date,
			HttpServletRequest request) {
		try {
			System.out.println("here is disposed date1 "+disposed_date);
			if (util.isPermitted(request, "FixedAsset", "get_raw_fixed_core_data_for_manual_recon")) {
				System.out.println("here is disposed ------date "+disposed_date);
				if ((disposed_date).equalsIgnoreCase("empty")) {
					System.out.println("here is disposed date11111 "+disposed_date);
					disposed_date=mapperFixedAsset.getDisposedDate();
				}
				if((removed_date).equalsIgnoreCase("empty")) {
					removed_date=mapperFixedAsset.getRemovedDate();
				}
				if((waiting_date).equalsIgnoreCase("empty")) {
					waiting_date=mapperFixedAsset.getWaitingDate();
				}
				return mapperFixedAsset.updateDate(disposed_date,removed_date,waiting_date);
				
			}
			else {
				System.out.println("No user does not have permission.");
				return null;
			}
		}catch (Exception e) {
			System.out.println("error in the fixed asset service..." + e.getLocalizedMessage());
			throw new ExceptionsList(e);
			// return false;
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