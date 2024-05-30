package com.example.demo.Stock.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ExceptionsList;
import com.example.demo.Stock.Mapper.StockMapper;
import com.example.demo.Stock.Model.RawStockCoreModel;
import com.example.demo.Stock.Model.RawStockMMSModel;
import com.example.demo.Stock.Model.StockCoreDeleted;
import com.example.demo.Stock.Model.stockCoreDetail;
import com.example.demo.abebayehu.entity.Raw_fixed_mms;
import com.example.demo.abebayehu.entity.core_detail_deleted;
import com.example.demo.abebayehu.entity.mms_detail_deleted;
import com.example.demo.abebayehu.entity.view_fixed_core_deleted;
import com.example.demo.abebayehu.entity.view_fixed_mms_deleted;
import com.example.demo.garama.mapper.FixedAutReconMapper;
import com.example.demo.garama.models.RawFixedCoreModel;
import com.example.demo.garama.models.RawFixedMMSModel;
import com.example.demo.garama.models.UnMatchFixedCoreModel;
import com.example.demo.user.mapper.MapperAccount;
import com.example.demo.user.mapper.MapperRTGS;
import com.example.demo.utils.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class StockService {
	@Autowired
	private Utils util;
	@Autowired
	private StockMapper stockMapper;
	@Autowired
	private MapperAccount accountMapper;

	@Autowired
	private FixedAutReconMapper fixedMapper;

	@Autowired
	private MapperRTGS rtgsMapper;

	// public List<RawFixedMMSModel> get_stock_mms_for_recon_auto(HttpServletRequest
	// request, String datas) {
	// private MapperRTGS rtgsMapper;

	public List<RawStockMMSModel> get_stock_mms_for_recon_auto(HttpServletRequest request, String datas) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_stock_mms_for_recon_auto")) {

				util.registerActivity(request, "Get MMS Stock transactions",
						"Get MMS stock transactions to match automatically");

				JsonObject id_data_object = JsonParser.parseString(datas).getAsJsonObject();
				String recon_date = id_data_object.get("c_date").getAsString();
				String type = id_data_object.get("type").getAsString();
//					String[] ids_string = id_data_object.get("ids").getAsString().split(",");
				System.out.println("Type=" + type);
				System.out.println("the date: " + recon_date.replace("-", ""));
				List<RawStockMMSModel> cc = null;
				if (type.equalsIgnoreCase("all")) {
					cc = stockMapper.get_stock_mms_for_recon_auto(recon_date, "");
				} else {
					cc = stockMapper.get_stock_mms_for_recon_auto(recon_date, type);
				}
//				for (RawStockMMSModel c : cc) {
//					// System.out.println("reference:----------> " + c.getReference());
//				}
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

	public List<RawStockCoreModel> get_fixed_core_for_recon_auto(HttpServletRequest request, String datas) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_stock_core_for_recon_auto")) {
				util.registerActivity(request, "Get all Core transactions",
						"Get Core transactions to match automatically");
				JsonObject id_data_object = JsonParser.parseString(datas).getAsJsonObject();
				String recon_date = id_data_object.get("c_date").getAsString();
				String type = id_data_object.get("type").getAsString();
//					String[] ids_string = id_data_object.get("ids").getAsString().split(",");
				System.out.println("Type=" + type);
				List<RawStockCoreModel> a = null;
				if (type.equalsIgnoreCase("all")) {
					a = stockMapper.get_stock_core_for_recon_auto("", recon_date);
				} else {
					a = stockMapper.get_stock_core_for_recon_auto(type, recon_date);
				}
				System.out.println("size: " + a.size());
				return a;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	// this is to match single transactions manually
	public Boolean match_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "FixedAsset", "match_stock_transactions_auto_single")) {
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

				if (id_1[0] != null) {

					for (int i = 0; i < id_1.length; i++) {
						System.out.println(id_1[i]);
						current_id = stockMapper.moveMMSStockData(id_1[i]);
						type = "stock_mms";
						matched_data_id = stockMapper.addStockMatched(current_id, match_id, date_now, "1",
								id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");

						System.out.println(util.get_user_id(request));
						stockMapper.addUserStockMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
								"1");
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					}
					for (int i = 0; i < id_2.length; i++) {
						type = "stock_core";
						System.out.println(id_2[i]);
						current_id = stockMapper.moveCoreStockData(id_2[i], match_id);
						rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					}

				} else {
					System.out.println("it is not Fixed");
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

	// This functionality is
	// match_all_transactions_fixed_auto=========================
	public Boolean match_all_transactions(HttpServletRequest request, String data_ids, String recon_date) {
		try {
			if (util.isPermitted(request, "FixedAsset", "match_all_transactions_stock_auto")) {
				util.registerActivity(request, "match all transactions", "match all transactions automatically");
				JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
				String[] id_1_string = id_data_object.get("id_1").getAsString().split(",");
				String[] id_2_string = id_data_object.get("id_2").getAsString().split(",");
				String[] amount_1_string = id_data_object.get("amount_1").getAsString().split(",");
				String[] amount_2_string = id_data_object.get("amount_2").getAsString().split(",");
				String[] dr_cr_1_string = id_data_object.get("dr_cr_1").getAsString().split(",");
				String[] dr_cr_2_string = id_data_object.get("dr_cr_2").getAsString().split(",");
				String[] reference_1_string = id_data_object.get("reference_1").getAsString().split(",");
				String[] reference_2_string = id_data_object.get("reference_2").getAsString().split(",");
				String[] account_1_string = id_data_object.get("account_1").getAsString().split(",");
				String[] account_2_string = id_data_object.get("account_2").getAsString().split(",");
				
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
					System.out.println(": " + reference_1_string[i]);
				}
				
				for (int i = 0; i < id_2.length; i++) {
					System.out.println(":: " + reference_2_string[i]);
				}
				for (int i = 0; i < id_1.length; i++) {
					for (int j = 0; j < id_2.length; j++) {
						
						if ((reference_2_string[j].contains(reference_1_string[i])) || (reference_2_string[j].contains("STOV".replace('O', 'I')))
								&& new BigDecimal(amount_1[i]).setScale(2, RoundingMode.HALF_UP)
								.equals(new BigDecimal(amount_2[j]).setScale(2, RoundingMode.HALF_UP))
						&& dr_cr_1_string[i].equalsIgnoreCase(dr_cr_2_string[j])
						&& stockMapper.existsTransaction(id_2[j])
						&& account_2_string[j].contains(account_1_string[i])
								) {
							
							String match_id = generateUniqueMatchId();

							current_id = stockMapper.moveMMSStockData(id_1[i]);
							typee = "stock_mms";
							matched_data_id = stockMapper.addStockMatched(current_id, match_id, date_now, "1",
									id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");

							stockMapper.addUserStockMatched(util.get_user_id(request), matched_data_id, date_now, "1",
									"1", "1");

							rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_1[i]);

							current_id = stockMapper.moveCoreStockData(id_2[j], match_id);
							typee = "stock_core";
							rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_2[j]);
							break;
						}else {
							System.out.println("===" + reference_2_string[j] + " : " + reference_1_string[i]);
						}
//						break;
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

	public List<RawStockMMSModel> get_stock_mms_matched_for_view(HttpServletRequest request, String datas) {

		try {
			if (util.isPermitted(request, "FixedAsset", "get_mms_stock_matched")) {
				util.registerActivity(request, "Get  stock MMS matched match transaction ", "-");
				JsonObject id_data_object = JsonParser.parseString(datas).getAsJsonObject();
				String recon_date = id_data_object.get("c_date").getAsString();
				String type = id_data_object.get("type").getAsString();
				System.out.println("the date: " + recon_date.replace("-", ""));
				List<RawStockMMSModel> mms = null;
				if (type.equalsIgnoreCase("all")) {
					mms = stockMapper.get_stock_mms_matched_for_viewAll(recon_date.replace("-", ""));
				} else {
					mms = stockMapper.get_stock_mms_matched_for_view(Integer.parseInt(recon_date.replace("-", "")),
							type);
				}
				return mms;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<RawStockCoreModel> get_stock_core_matched_for_view(HttpServletRequest request, String datas) {

		try {
			if (util.isPermitted(request, "FixedAsset", "get_core_stock_matched")) {
				util.registerActivity(request, "Getstock core core match transaction ", "-");
				JsonObject id_data_object = JsonParser.parseString(datas).getAsJsonObject();
				String recon_date = id_data_object.get("c_date").getAsString();
				String type = id_data_object.get("type").getAsString();
				System.out.println("the date: " + recon_date.replace("-", ""));
				List<RawStockCoreModel> core = null;
				if (type.equalsIgnoreCase("all00")) { 
					core = stockMapper
							.get_stock_core_matched_for_viewAll(Integer.parseInt(recon_date.replace("-", "")));
				} else {
					core = stockMapper.get_stock_core_matched_for_view(Integer.parseInt(recon_date.replace("-", "")),
							type);
				}
				return core;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<RawStockCoreModel> get_stock_core_reversal(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "FixedAsset", "get_stock_matched_reversal")) {
				util.registerActivity(request, "Getstock core reversal matched ", "-");
//				JsonObject id_data_object = JsonParser.parseString(datas).getAsJsonObject();
//				String recon_date = id_data_object.get("c_date").getAsString();
//				String type = id_data_object.get("type").getAsString();
				System.out.println("the date: " + recon_date.replace("-", ""));
				List<RawStockCoreModel> core = stockMapper
						.get_stock_reversal(Integer.parseInt(recon_date.replace("-", "")));
				return core;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<RawStockMMSModel> get_stock_mms_unmatched_for_view(HttpServletRequest request, String datas) {

		try {
			if (util.isPermitted(request, "FixedAsset", "get_stock_mms_unmatched_for_view")) {
				util.registerActivity(request, "Get  Unmatched transaction ", "-");
				JsonObject id_data_object = JsonParser.parseString(datas).getAsJsonObject();
				String recon_date = id_data_object.get("c_date").getAsString();
				String type = id_data_object.get("type").getAsString();
				System.out.println("the date: " + recon_date.replace("-", ""));
				List<RawStockMMSModel> mms = null;
				if (type.equalsIgnoreCase("all")) {
					mms = stockMapper.get_stock_mms_unmatched_for_viewAll(recon_date);
				} else {
					mms = stockMapper.get_stock_mms_unmatched_for_view(recon_date, type);
				}
				return mms;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<RawStockCoreModel> get_stock_core_unmatched_for_view(HttpServletRequest request, String datas) {

		try {
			if (util.isPermitted(request, "FixedAsset", "get_stock_core_unmatched_for_view")) {
				util.registerActivity(request, "Getstock core  unmatch transaction ", "-");
				JsonObject id_data_object = JsonParser.parseString(datas).getAsJsonObject();
				String recon_date = id_data_object.get("c_date").getAsString();
				String type = id_data_object.get("type").getAsString();
				System.out.println("the date: " + recon_date.replace("-", ""));
				List<RawStockCoreModel> core = null;
				if (type.equalsIgnoreCase("all00")) {
					core = stockMapper.get_stock_core_unmatched_for_viewAll(recon_date);
				} else {
					core = stockMapper.get_stock_core_unmatched_for_view(recon_date, type);
				}
				return core;
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
			if (util.isPermitted(request, "FixedAsset", "unmatch_transactions_stock")) {
				util.registerActivity(request, "unmatch transactions Stock", "-");
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
					System.out.println("Core id=" + id_2[i]);
					current_id = stockMapper.moveStockCoreMatched(id_2[i]);
					type = "data_fixed_core";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					System.out.println("edite reason updated after unmatche id----->" + id_2[i]);
				}

				for (int i = 0; i < id_1.length; i++) {
					System.out.println("mms id=" + id_1[i]);
					stockMapper.deleteStockMatched(id_1[i]);

					current_id = stockMapper.moveStockMMSMatchedToData(id_1[i]);
					type = "data_mms_fixed";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
					System.out.println("edite reason updated after unmatche id----->" + id_1[i]);
					stockMapper.deleteUserStockMatched(stockMapper.getStockMatchedId(id_1[i]));
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

	public Boolean unmatch_reversal_transactions(HttpServletRequest request, String data_ids) {
		try {
			if (util.isPermitted(request, "FixedAsset", "unmatch_reversal_transactions_stock")) {
				util.registerActivity(request, "unmatch reversal transactions Stock", "-");
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
					System.out.println("Core id=" + id_2[i]);
					current_id = stockMapper.moveStockCoreMatchedReversal(id_2[i]);
					type = "stock__core_reversal";
					rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
					System.out.println("edite reason updated after unmatche id----->" + id_2[i]);
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

	public List<RawStockMMSModel> get_stock_mms_matched_with_reason(HttpServletRequest request, String datas) {

		try {
			if (util.isPermitted(request, "FixedAsset", "get_stock_mms_matched_with_reason")) {
				util.registerActivity(request, "Get  stock MMS matched with reason transaction ", "-");
				JsonObject id_data_object = JsonParser.parseString(datas).getAsJsonObject();
				String recon_date = id_data_object.get("c_date").getAsString();
				String type = id_data_object.get("type").getAsString();
				System.out.println("the date: " + recon_date.replace("-", ""));
				List<RawStockMMSModel> mms = null;
				if (type.equalsIgnoreCase("all")) {
					mms = stockMapper
							.get_stock_mms_matched_with_reasonAll(Integer.parseInt(recon_date.replace("-", "")));
				} else {
					mms = stockMapper.get_stock_mms_matched_with_reason(Integer.parseInt(recon_date.replace("-", "")),
							type);
				}
				return mms;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<RawStockCoreModel> get_stock_core_matched_with_reason(HttpServletRequest request, String datas) {

		try {
			if (util.isPermitted(request, "FixedAsset", "get_stock_core_matched_with_reason")) {
				util.registerActivity(request, "Getstock core  matched with reason transaction ", "-");
				JsonObject id_data_object = JsonParser.parseString(datas).getAsJsonObject();
				String recon_date = id_data_object.get("c_date").getAsString();
				String type = id_data_object.get("type").getAsString();
				System.out.println("the date: " + recon_date.replace("-", ""));
				List<RawStockCoreModel> core = null;
				if (type.equalsIgnoreCase("all00")) {
					core = stockMapper
							.get_stock_core_matched_with_reasonAll(Integer.parseInt(recon_date.replace("-", "")));
				} else {
					core = stockMapper.get_stock_core_matched_with_reason(Integer.parseInt(recon_date.replace("-", "")),
							type);
				}
				return core;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

//	added by demeke
	public List<RawStockMMSModel> get_stock_mms_for_recon_manual(HttpServletRequest request, String datas) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_stock_mms_for_recon_manual")) {

				util.registerActivity(request, "Get MMS Stock transactions",
						"Get MMS stock transactions to match manualy");

				JsonObject id_data_object = JsonParser.parseString(datas).getAsJsonObject();
				String recon_date = id_data_object.get("c_date").getAsString();
				String type = id_data_object.get("type").getAsString();
//					String[] ids_string = id_data_object.get("ids").getAsString().split(",");
				System.out.println("Type=" + type);
				System.out.println("the date: " + recon_date.replace("-", ""));
				List<RawStockMMSModel> cc = null;
				if (type.equalsIgnoreCase("all")) {
					cc = stockMapper.get_all_stock_mms_for_recon_manual(recon_date);
				} else {
					cc = stockMapper.get_stock_mms_for_recon_manual(recon_date, type);
				}
				return cc;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}

		} catch (Exception e) {
			throw new ExceptionsList(e);
		}

	}

	public List<RawStockCoreModel> get_stock_core_for_recon_manual(HttpServletRequest request, String datas) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_stock_core_for_recon_manual")) {
				util.registerActivity(request, "Get all Core transactions", "Get Core transactions to match manualy");
				JsonObject id_data_object = JsonParser.parseString(datas).getAsJsonObject();
				String recon_date = id_data_object.get("c_date").getAsString();
				String type = id_data_object.get("type").getAsString();
//					String[] ids_string = id_data_object.get("ids").getAsString().split(",");
				System.out.println("core Type=" + type);
				List<RawStockCoreModel> a = null;
				if (type.equalsIgnoreCase("all00")) {
					a = stockMapper.get_all_stock_core_for_recon_manual(recon_date);
				} else {
					a = stockMapper.get_stock_core_for_recon_manual(type, recon_date);
				}
				System.out.println("core  size: " + a.size());
				return a;
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public Boolean match_transaction(HttpServletRequest request, List<List<Long>> matched_ids) {
		try {
			if (util.isPermitted(request, "FixedAsset", "match_stock_transactions_manually")) {
				util.registerActivity(request, "match stock transactions manually", "-");
				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				int mms_length = matched_ids.get(0).size();
				int core_length = matched_ids.get(1).size();
				String typee;
				int count = 0;
				for (List<Long> matched_ids_list : matched_ids) {
					if (count == 0)
						for (Long mms_id : matched_ids_list) {
							current_id = stockMapper.moveMMSStockData(mms_id);
							matched_data_id = stockMapper.addStockMatched(current_id, match_id, date_now, "1",
									mms_length > 1 && core_length > 1 ? "1".toString() : "0".toString(), "1", "1");
							stockMapper.addUserStockMatched(util.get_user_id(request), matched_data_id, date_now, "1",
									"1", "1");
							typee = "stock_mms";
							rtgsMapper.updateEditReason(current_id, matched_data_id, typee, mms_id);
						}
					else {
						for (Long core_id : matched_ids_list) {
							typee = "stock_core";
							current_id = stockMapper.moveCoreStockData(core_id, match_id);
							rtgsMapper.updateEditReason(current_id, matched_data_id, typee, core_id);
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

	public Boolean match_transactions_with_reason(HttpServletRequest request, List<ArrayList> matched_ids) {
		try {
			if (util.isPermitted(request, "FixedAsset", "match_stock_transactions_with_reason")) {
				util.registerActivity(request, "match stock transactions with reason", "-");

				List<Integer> core_ids = matched_ids.get(0);
				List<Integer> mms_ids = matched_ids.get(1);

				int mms_length = mms_ids.size();
				int core_length = core_ids.size();

				String reason = matched_ids.get(2).get(0).toString();
				String type = matched_ids.get(2).get(1).toString();

				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));
				Long current_id = null;
				Long matched_data_id = null;
				String typee;

				for (int mms_id : mms_ids) {
					current_id = stockMapper.moveMMSStockData((long) mms_id);
					matched_data_id = stockMapper.addStockMatched(current_id, match_id, date_now, "1",
							mms_length > 1 && core_length > 1 ? "1".toString() : "0".toString(), "1", "1");
					stockMapper.addUserStockMatched(util.get_user_id(request), matched_data_id, date_now, "1", "1",
							"1");
					typee = "stock_mms";
					rtgsMapper.updateEditReason(current_id, matched_data_id, typee, (long) mms_id);
				}

				for (int core_id : core_ids) {
					typee = "stock_core";
					current_id = stockMapper.moveCoreStockData((long) core_id, match_id);
					rtgsMapper.updateEditReason(current_id, matched_data_id, typee, (long) core_id);
				}

				if (type.equalsIgnoreCase("reference")) {
					stockMapper.addMatchReason(match_id, reason, "fixed stock", "1", "1", "1");
				} else {
					stockMapper.addMatchReason(match_id, reason, "fixed stock", "2", "1", "1");
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

	public Boolean delete_transactions(HttpServletRequest request, String datas) {
		try {
			if (util.isPermitted(request, "User", "delete_transactions")
					|| util.isPermitted(request, "FixedAsset", "delete_transactions")) {
				util.registerActivity(request, "Delete transactions", "delete stock transactions");
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
					if (type != null && type.equalsIgnoreCase("mms_stock")) {
						type1 = "data_mms_stock";
						Long reason_id = stockMapper.addReasonForEdit(ids[i], user_id, type1, reason,
								new Date().toString(), "1", "1", "2");
						stockMapper.moveDeletedMmsData(ids[i], reason_id);
						stockMapper.deleteTransaction(ids[i]);

					}

					else if (type != null && type.equalsIgnoreCase("core_stock")) {
						type1 = "data_stock_core";
						Long reason_id = stockMapper.addReasonForEdit(ids[i], user_id, type1, reason,
								new Date().toString(), "1", "1", "2");
						stockMapper.moveDeletedStockCoreData(ids[i], reason_id);
						stockMapper.deleteFixedCoreTransaction(ids[i]);
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

	public Boolean match_transactions_reversal(HttpServletRequest request, List<List<Long>> matched_stock_ids) {
		try {
			if (util.isPermitted(request, "FixedAsset", "match_stock_transactions_reversal")) {
				util.registerActivity(request, "match transactions manually reversal", "it is stock transaction");
				String match_id = generateUniqueMatchId();
				Long date_now = Long.parseLong(DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDateTime.now()));

				for (List<Long> matched_stock_ids_list : matched_stock_ids) {

					for (Long core_id : matched_stock_ids_list) {
						System.out.println("stock-core-id=" + core_id);
						stockMapper.moveStockCoreReversalData(core_id, match_id, date_now,
								stockMapper.fullName(util.get_user_id(request)));
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

	public List<StockCoreDeleted> get_edited_stock_core(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_deleted_stock_core_transaction")) {
				util.registerActivity(request, "Get all deleted stock core transaction", "-");
				System.out.println("nothing special==============>" + stockMapper.get_edited_stock_core());
				return stockMapper.get_edited_stock_core();
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<stockCoreDetail> get_edited_detail_stock_core(HttpServletRequest request, Long id) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_edited_detail_stock_core")) {
				util.registerActivity(request, "Get detail deleted stock core transaction", "-");
				return stockMapper.get_edited_detail_stock_core(id);
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<RawStockMMSModel> get_edited_stock_mms(HttpServletRequest request) {
		try {
			if (util.isPermitted(request, "fixedAsset", "get_deleted_stock_mms_transaction")) {
				util.registerActivity(request, "Get all deleted Stock mms transaction", "-");
				System.out.println("nothing specialllll==============>" + stockMapper.get_deleted_stock_mms());
				return stockMapper.get_deleted_stock_mms();
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

	public List<RawStockMMSModel> get_edited_detail_stock_mms(HttpServletRequest request, Long id) {
		try {
			if (util.isPermitted(request, "FixedAsset", "get_edited_detail_stock_mms")) {
				util.registerActivity(request, "Get detail edited stock core transaction", "-");
				System.out.println("menchachat =,,,,,,,,,,,,,,,,,,,,>" + stockMapper.get_edited_detail_stock_mms(id));

				return stockMapper.get_edited_detail_stock_mms(id);
			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
}
