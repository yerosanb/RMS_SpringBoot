package com.example.demo.garama.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.ExceptionsList;
import com.example.demo.abebayehu.entity.Raw_fixed_mms;
import com.example.demo.garama.mapper.FixedAutReconMapper;
import com.example.demo.garama.models.FixedMmsDisposed;
import com.example.demo.garama.models.FixedMmsRemoved;
import com.example.demo.garama.models.FixedMmsWaiting;
import com.example.demo.garama.models.RawFixedCoreModel;
import com.example.demo.garama.models.RawFixedMMSModel;
import com.example.demo.garama.models.UnMatchFixed;
import com.example.demo.garama.models.UnMatchFixedCoreModel;
import com.example.demo.garama.models.fixedCoreReversal;
import com.example.demo.user.mapper.MapperAccount;
import com.example.demo.user.mapper.MapperRTGS;
import com.example.demo.user.model.File_rtgs_awb_core;
import com.example.demo.user.model.File_rtgs_nbe_ats;
import com.example.demo.user.model.Transactionhistory;
import com.example.demo.utils.Utils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class FixedAutReconService {
    @Autowired
    private Utils util;
    @Autowired
    private FixedAutReconMapper fixedMapper;
    @Autowired
    private MapperAccount accountMapper;
    @Autowired
    private MapperRTGS rtgsMapper;

    public List<RawFixedMMSModel> get_fixed_mms_for_recon_auto_computer(HttpServletRequest request, String recon_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_mms_for_recon_auto")) {

                util.registerActivity(request, "Get all Ats transactions",
                        "Get all ATS transactions to match automatically");
                System.out.println("the date: " + recon_date.replace("-", ""));
                List<RawFixedMMSModel> cc = fixedMapper.get_fixed_mms_for_recon_auto_computer(recon_date);
                for (RawFixedMMSModel c : cc) {
                    // System.out.println("reference:----------> " + c.getReference());
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
    public List<RawFixedMMSModel> get_fixed_mms_for_recon_auto_furniture(HttpServletRequest request, String recon_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_mms_for_recon_auto")) {

                util.registerActivity(request, "Get all Ats transactions",
                        "Get all ATS transactions to match automatically");
                System.out.println("the date: " + recon_date.replace("-", ""));
                List<RawFixedMMSModel> cc = fixedMapper.get_fixed_mms_for_recon_auto_furniture(recon_date,
                        accountMapper.getUserAccountId(util.get_user_id(request)));
                for (RawFixedMMSModel c : cc) {
                    // System.out.println("reference:----------> " + c.getReference());
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
    
    public List<RawFixedMMSModel> get_fixed_mms_for_recon_auto_equipment(HttpServletRequest request, String recon_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_mms_for_recon_auto")) {

                util.registerActivity(request, "Get all Ats transactions",
                        "Get all ATS transactions to match automatically");
                System.out.println("the date: " + recon_date.replace("-", ""));
                List<RawFixedMMSModel> cc = fixedMapper.get_fixed_mms_for_recon_auto_equipment(recon_date,
                        accountMapper.getUserAccountId(util.get_user_id(request)));
                for (RawFixedMMSModel c : cc) {
                    // System.out.println("reference:----------> " + c.getReference());
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
    
    
    public List<RawFixedMMSModel> get_fixed_mms_for_recon_auto_vehicle(HttpServletRequest request, String recon_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_mms_for_recon_auto")) {

                util.registerActivity(request, "Get all Ats transactions",
                        "Get all ATS transactions to match automatically");
                System.out.println("the date: " + recon_date.replace("-", ""));
                List<RawFixedMMSModel> cc = fixedMapper.get_fixed_mms_for_recon_auto_vehicle(recon_date,
                        accountMapper.getUserAccountId(util.get_user_id(request)));
                for (RawFixedMMSModel c : cc) {
                    // System.out.println("reference:----------> " + c.getReference());
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

    public List<RawFixedCoreModel> get_fixed_core_for_recon_auto_computer(HttpServletRequest request, String recon_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_core_for_recon_auto")) {
                util.registerActivity(request, "Get all Core transactions",
                        "Get Core transactions to match automatically");
                System.out.println("hello world: " + recon_date);
                List<RawFixedCoreModel> a = fixedMapper.get_fixed_core_for_recon_auto_computer(
                        accountMapper.getUserAccountId(util.get_user_id(request)),
                        recon_date);
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
    public List<RawFixedCoreModel> get_fixed_core_for_recon_auto_equipment(HttpServletRequest request, String recon_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_core_for_recon_auto")) {
                util.registerActivity(request, "Get all Core transactions",
                        "Get Core transactions to match automatically");
                System.out.println("hello world: " + recon_date);
                List<RawFixedCoreModel> a = fixedMapper.get_fixed_core_for_recon_auto_equipment(
                        accountMapper.getUserAccountId(util.get_user_id(request)),
                        recon_date);
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
    
    public List<RawFixedCoreModel> get_fixed_core_for_recon_auto_vehicle(HttpServletRequest request, String recon_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_core_for_recon_auto")) {
                util.registerActivity(request, "Get all Core transactions",
                        "Get Core transactions to match automatically");
                System.out.println("hello world: " + recon_date);
                List<RawFixedCoreModel> a = fixedMapper.get_fixed_core_for_recon_auto_vehicle(
                        accountMapper.getUserAccountId(util.get_user_id(request)),
                        recon_date);
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
    
    public List<RawFixedCoreModel> get_fixed_core_for_recon_auto_furniture(HttpServletRequest request, String recon_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_core_for_recon_auto")) {
                util.registerActivity(request, "Get all Core transactions",
                        "Get Core transactions to match automatically");
                System.out.println("hello world: " + recon_date);
                List<RawFixedCoreModel> a = fixedMapper.get_fixed_core_for_recon_auto_furniture(
                        accountMapper.getUserAccountId(util.get_user_id(request)),
                        recon_date);
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
            if (util.isPermitted(request, "FixedAsset", "match_fixed_transactions_auto_single")) {
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
                        current_id = fixedMapper.moveMMSFixedData(id_1[i]);
                        type = "fixed_mms";
                        matched_data_id = fixedMapper.addFixedMatched(current_id, match_id, date_now, "1",
                                id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
 
                        System.out.println(util.get_user_id(request));
                        fixedMapper.addUserFixedMatched(util.get_user_id(request), matched_data_id, date_now,
                                "1", "1", "1");
                         rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
                    }
                    for (int i = 0; i < id_2.length; i++) {
                        type = "fixed_core";
                        System.out.println(id_2[i]);
                        current_id = fixedMapper.moveCoreFixedData(id_2[i], match_id);
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
            if (util.isPermitted(request, "FixedAsset", "match_all_transactions_fixed_auto")) {
                util.registerActivity(request, "match all transactions", "match all transactions automatically");
                JsonObject id_data_object = JsonParser.parseString(data_ids).getAsJsonObject();
                String[] type = id_data_object.get("type").getAsString().split(",");
                String[] core_id = id_data_object.get("core_id").getAsString().split(",");
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

                    current_id = fixedMapper.moveMMSFixedData(id_1[i]);
                    typee = "fixed_mms";
                    matched_data_id = fixedMapper.addFixedMatched(current_id, match_id, date_now, "1",
                            id_1.length > 1 && id_2.length > 1 ? "1".toString() : "0".toString(), "1", "1");
 
                    fixedMapper.addUserFixedMatched(util.get_user_id(request), matched_data_id, date_now,
                            "1", "1", "1");
 
                    rtgsMapper.updateEditReason(current_id, matched_data_id, typee, id_1[i]);

                    current_id = fixedMapper.moveCoreFixedData(Long.parseLong(core_id[i]), match_id);
                    typee = "fixed_core";
                    rtgsMapper.updateEditReason(current_id, matched_data_id, typee, Long.parseLong(core_id[i]));

                    //// Long id_2_2 = rtgsMapper.getMatchedIdsFromCoreByAtsId(id_1[i],
                    //// recon_date.replace("-", ""));

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

    public List<UnMatchFixed> get_fixed_mms_for_view(HttpServletRequest request, String matched_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_mms_matched")) {
                util.registerActivity(request, "Get fixed mms matched trnsaction", "-");
 
                // ystem.out.println();
                 return fixedMapper.get_fixed_mms_for_view(matched_date.replace("-", ""));
            } else {
                System.out.println("No user does not have permission.");
                return null;
            }
        } catch (Exception e) {
            throw new ExceptionsList(e);
        }
    }

    public List<UnMatchFixedCoreModel> get_fixed_core_for_view(HttpServletRequest request, String matched_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_core_matched")) {
                util.registerActivity(request, "Get fixed core matched trnsaction", "-");
                return fixedMapper.get_fixed_core_for_view(matched_date.replace("-", ""));
            } else {
                System.out.println("No user does not have permission.");
                return null;
            }
        } catch (Exception e) {
            throw new ExceptionsList(e);
        }
    }

    public List<Raw_fixed_mms> get_fixed_mms_for_view_reason(HttpServletRequest request, String matched_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_mms_for_view_reason")) {
                util.registerActivity(request, "Get fixed mms matched trnsaction", "-");
                if(matched_date !=""){

                System.out.println(matched_date);
                int date=Integer.parseInt(matched_date.replace("-", ""));
          //  System.out.println("x="+x);
                    return fixedMapper.get_fixed_mms_for_view_reason(date);
                    
                }
                else{
                    
               return fixedMapper.get_fixed_mms_for_view_reason(0);

                }
            }
            else {
                System.out.println("user do not have permission..");
                return null;
            }
        } catch (Exception e) {
            throw new ExceptionsList(e);
        }
    }

    public List<Transactionhistory> get_fixed_core_for_view_reason(HttpServletRequest request, String matched_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_core_for_view_reason")) {
                util.registerActivity(request, "Get fixed mms matched trnsaction", "-");


                if ( matched_date != "") {
                    return fixedMapper.get_fixed_core_for_view_reason(Integer.parseInt(matched_date.replace("-", "")));

                } else {
                

                    return fixedMapper.get_fixed_core_for_view_reason(0);

                }
            }else{
                System.out.println(" user  have not permission.");

                

            }
            } catch (Exception e) {
                    throw new ExceptionsList(e);
                }
                return null ;

            }


    public Boolean unmatch_transactions(HttpServletRequest request, String data_ids) {
        try {
            if (util.isPermitted(request, "FixedAsset", "unmatch_transactions_fixed")) {
                util.registerActivity(request, "unmatch transactions fixed", "-");
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
                    current_id = fixedMapper.moveFixedCoreMatched(id_2[i]);
                    type = "data_fixed_core";
                    rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_2[i]);
                    System.out.println("edite reason updated after unmatche id----->" + id_2[i]);
                }

                for (int i = 0; i < id_1.length; i++) {
                    System.out.println("mms id=" + id_1[i]);
                    fixedMapper.deleteFixedMatched(id_1[i]);

                    current_id = fixedMapper.moveFixedMMSMatchedToData(id_1[i]);
                    type = "data_mms_fixed";
                    rtgsMapper.updateEditReason(current_id, matched_data_id, type, id_1[i]);
                    System.out.println("edite reason updated after unmatche id----->" + id_1[i]);
                    fixedMapper.deleteUserFixedMatched(fixedMapper.getFixedMatchedId(id_1[i]));
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

 
    public List<RawFixedMMSModel> get_unmatched_fixed_mms_for_view(HttpServletRequest request, String tran_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_mms_matched")) {
                util.registerActivity(request, "Get fixed mms matched trnsaction", "-");
                // return fixedMapper.get_unmatched_fixed_mms_for_view(matched_date.replace("-",
                // ""));
                List<RawFixedMMSModel> mms_unmatched = fixedMapper.get_unmatched_fixed_mms_for_view(tran_date);
                return mms_unmatched;
            } else {
                System.out.println("No user does not have permission.");
                return null;
            }
        } catch (Exception e) {
            throw new ExceptionsList(e);
        }
    }

    public List<RawFixedCoreModel> get_unmatched_fixed_core_for_view(HttpServletRequest request,
            String matched_date) {
        try {
            if (util.isPermitted(request, "FixedAsset", "get_fixed_core_matched")) {
                util.registerActivity(request, "Get fixed core matched trnsaction", "-");
                // return
                // fixedMapper.get_unmatched_fixed_core_for_view(matched_date.replace("-", ""));
                List<RawFixedCoreModel> fixedCoreList = fixedMapper.get_unmatched_fixed_core_for_view();

                return fixedCoreList;
            } else {
                System.out.println("No user does not have permission in get_raw_fixed_core_for_recon");
                return null;
            }
        } catch (Exception e) {
            throw new ExceptionsList(e);
        }
    }
   
  public List<Raw_fixed_mms> get_computer_mms_unmatched_for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "FixedAsset", "get_computer_mms_fixed_unmatch")) {
				util.registerActivity(request, "Get  unmatch computer mms fixed", "-");
				System.out.println("the date: " + recon_date.replace("-", ""));
				return fixedMapper.get_computer_mms_unmatched_for_view(recon_date);

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

    
  public List<RawFixedCoreModel> get_computer_core_unmatched_for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "FixedAsset", "get_computer_core_fixed_unmatch")) {
				util.registerActivity(request, "Get computer core fixed unmatch transaction ", "-");
				return fixedMapper.get_computer_core_unmatched_for_view(recon_date);

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}
  
  public List<Raw_fixed_mms> get_furniture_mms_unmatched_for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "FixedAsset", "get_Furniture_mms_fixed_unmatch")) {
				util.registerActivity(request, "Get Fuyrniture fixed unmatch ", "-");
				System.out.println("date="+recon_date);	
				return fixedMapper.get_furniture_mms_unmatched_for_view(recon_date);

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}

  
public List<RawFixedCoreModel> get_furniture_core_unmatched_for_view(HttpServletRequest request, String recon_date) {

		try {
			if (util.isPermitted(request, "FixedAsset", "get_Furniture_core_fixed_unmatch")) {
				util.registerActivity(request, "Get furniture core fixed unmatch transaction ", "-");
				System.out.println("the date----->: " + recon_date.replace("-", ""));
				return fixedMapper.get_furniture_core_unmatched_for_view(recon_date);

			} else {
				System.out.println("No user does not have permission.");
				return null;
			}
		} catch (Exception e) {
			throw new ExceptionsList(e);
		}
	}


public List<Raw_fixed_mms> get_equipment_mms_unmatched_for_view(HttpServletRequest request, String recon_date) {

	try {
		if (util.isPermitted(request, "FixedAsset", "get_equipment_mms_fixed_unmatch")) {
			util.registerActivity(request, "Get equipment fixed unmatch ", "-");
			System.out.println("the date: " + recon_date.replace("-", ""));
			return fixedMapper.get_equipment_mms_unmatched_for_view(recon_date);

		} else {
			System.out.println("No user does not have permission.");
			return null;
		}
	} catch (Exception e) {
		throw new ExceptionsList(e);
	}
}


public List<RawFixedCoreModel> get_equipment_core_unmatched_for_view(HttpServletRequest request, String recon_date) {

	try {
		if (util.isPermitted(request, "FixedAsset", "get_equipment_core_fixed_unmatch")) {
			util.registerActivity(request, "Get equipment core fixed unmatch transaction ", "-");
			System.out.println("the date: " + recon_date.replace("-", ""));
			return fixedMapper.get_equipment_core_unmatched_for_view(recon_date);

		} else {
			System.out.println("No user does not have permission.");
			return null;
		}
	} catch (Exception e) {
		throw new ExceptionsList(e);
	}
}

public List<Raw_fixed_mms> get_vehicle_mms_unmatched_for_view(HttpServletRequest request, String recon_date) {

	try {
		if (util.isPermitted(request, "FixedAsset", "get_vehicle_mms_fixed_unmatch")) {
			util.registerActivity(request, "Get motor vehicle fixed unmatch ", "-");
			System.out.println("the date: " + recon_date.replace("-", ""));
			return fixedMapper.get_vehicle_mms_unmatched_for_view(recon_date);

		} else {
			System.out.println("No user does not have permission.");
			return null;
		}
	} catch (Exception e) {
		throw new ExceptionsList(e);
	}
}

public List<RawFixedCoreModel> get_vehicle_core_unmatched_for_view(HttpServletRequest request, String recon_date) {

	try {
		if (util.isPermitted(request, "FixedAsset", "get_vehicle_core_fixed_unmatch")) {
			util.registerActivity(request, "Get motor vehicle core unmatch transaction ", "-");
			System.out.println("the date: " + recon_date.replace("-", ""));
			return fixedMapper.get_vehicle_core_unmatched_for_view(recon_date);

		} else {
			System.out.println("No user does not have permission.");
			return null;
		}
	} catch (Exception e) {
		throw new ExceptionsList(e);
	}
}
public List<Raw_fixed_mms> get_computer_mms_matched_for_view(HttpServletRequest request, String recon_date) {

	try {
		if (util.isPermitted(request, "FixedAsset", "get_computer_mms_fixed_matched")) {
			util.registerActivity(request, "Get  match computer mms fixed", "-");
			System.out.println("the date: " + recon_date.replace("-", ""));
			return fixedMapper.get_computer_mms_matched_for_view(Integer.parseInt(recon_date.replace("-", "")));

		} else {
			System.out.println("No user does not have permission.");
			return null;
		}
	} catch (Exception e) {
		throw new ExceptionsList(e);
	}
}


public List<UnMatchFixedCoreModel> get_computer_core_matched_for_view(HttpServletRequest request, String recon_date) {

	try {
		if (util.isPermitted(request, "FixedAsset", "get_computer_core_fixed_matched")) {
			util.registerActivity(request, "Get computer core fixed match transaction ", "-");
			return fixedMapper.get_computer_core_matched_for_view(Integer.parseInt(recon_date.replace("-", "")));

		} else {
			System.out.println("No user does not have permission.");
			return null;
		}
	} catch (Exception e) {
		throw new ExceptionsList(e);
	}
}

public List<Raw_fixed_mms> get_furniture_mms_matched_for_view(HttpServletRequest request, String recon_date) {

	try {
		if (util.isPermitted(request, "FixedAsset", "get_Furniture_mms_fixed_matched")) {
			util.registerActivity(request, "Get Fuyrniture fixed match ", "-");
			System.out.println("date="+recon_date);	
			return fixedMapper.get_furniture_mms_matched_for_view(Integer.parseInt(recon_date.replace("-", "")));

		} else {
			System.out.println("No user does not have permission.");
			return null;
		}
	} catch (Exception e) {
		throw new ExceptionsList(e);
	}
}


public List<UnMatchFixedCoreModel> get_furniture_core_matched_for_view(HttpServletRequest request, String recon_date) {

	try {
		if (util.isPermitted(request, "FixedAsset", "get_Furniture_core_fixed_matched")) {
			util.registerActivity(request, "Get furniture core fixed match transaction ", "-");
			System.out.println("the date: " + recon_date.replace("-", ""));
			return fixedMapper.get_furniture_core_matched_for_view(Integer.parseInt(recon_date.replace("-", "")));

		} else {
			System.out.println("No user does not have permission.");
			return null;
		}
	} catch (Exception e) {
		throw new ExceptionsList(e);
	}
}


public List<Raw_fixed_mms> get_equipment_mms_matched_for_view(HttpServletRequest request, String recon_date) {

try {
	if (util.isPermitted(request, "FixedAsset", "get_equipment_mms_fixed_matched")) {
		util.registerActivity(request, "Get equipment fixed match ", "-");
		System.out.println("the date: " + recon_date.replace("-", ""));
		return fixedMapper.get_equipment_mms_matched_for_view(Integer.parseInt(recon_date.replace("-", "")));

	} else {
		System.out.println("No user does not have permission.");
		return null;
	}
} catch (Exception e) {
	throw new ExceptionsList(e);
}
}


public List<UnMatchFixedCoreModel> get_equipment_core_matched_for_view(HttpServletRequest request, String recon_date) {

try {
	if (util.isPermitted(request, "FixedAsset", "get_equipment_core_fixed_matched")) {
		util.registerActivity(request, "Get equipment core fixed match transaction ", "-");
		System.out.println("the date: " + recon_date.replace("-", ""));
		return fixedMapper.get_equipment_core_matched_for_view(Integer.parseInt(recon_date.replace("-", "")));

	} else {
		System.out.println("No user does not have permission.");
		return null;
	}
} catch (Exception e) {
	throw new ExceptionsList(e);
}
}

public List<Raw_fixed_mms> get_vehicle_mms_matched_for_view(HttpServletRequest request, String recon_date) {

try {
	if (util.isPermitted(request, "FixedAsset", "get_vehicle_mms_fixed_matched")) {
		util.registerActivity(request, "Get motor vehicle fixed match ", "-");
		System.out.println("the date: " + recon_date.replace("-", ""));
		return fixedMapper.get_vehicle_mms_matched_for_view(Integer.parseInt(recon_date.replace("-", "")));

	} else {
		System.out.println("No user does not have permission.");
		return null;
	}
} catch (Exception e) {
	throw new ExceptionsList(e);
}
}

public List<UnMatchFixedCoreModel> get_vehicle_core_matched_for_view(HttpServletRequest request, String recon_date) {

try {
	if (util.isPermitted(request, "FixedAsset", "get_vehicle_core_fixed_matched")) {
		util.registerActivity(request, "Get motor vehicle core match transaction ", "-");
		System.out.println("the date: " + recon_date.replace("-", ""));
		return fixedMapper.get_vehicle_core_matched_for_view(Integer.parseInt(recon_date.replace("-", "")));

	} else {
		System.out.println("No user does not have permission.");
		return null;
	}
} catch (Exception e) {
	throw new ExceptionsList(e);
}
}

public List<FixedMmsWaiting> View_mms_wating(HttpServletRequest request, String dates) {
	try {
		if (util.isPermitted(request, "FixedAsset", "get_fixed_mms_waiting")) {
			System.out.println("this is mms removedddddddddddddddd");
			util.registerActivity(request, "Get fixed mms waiting transactions", "-");
			JsonObject date_data_object = JsonParser.parseString(dates).getAsJsonObject();
			String[] minDate = date_data_object.get("minDate").getAsString().split(",");
			String[] maxDate = date_data_object.get("maxDate").getAsString().split(",");
			System.out.println("minDate="+minDate[0]+"maxDate="+maxDate[0]);
			List <FixedMmsWaiting> waiting=fixedMapper.get_fixed_mms_waiting(minDate[0],maxDate[0]);
			  return waiting;
		} else {
			System.out.println("No user does not have permission.");
			return null;
		}
	} catch (Exception e) {
		throw new ExceptionsList(e);
	}
}
public List<FixedMmsDisposed> View_mms_disposed(HttpServletRequest request, String dates) {
	try {
		if (util.isPermitted(request, "FixedAsset", "get_fixed_mms_disposed")) {
			System.out.println("this is mms removedddddddddddddddd");
			util.registerActivity(request, "Get fixed mms Disposed transactions", "-");
			JsonObject date_data_object = JsonParser.parseString(dates).getAsJsonObject();
			String[] minDate = date_data_object.get("minDate").getAsString().split(",");
			String[] maxDate = date_data_object.get("maxDate").getAsString().split(",");
			System.out.println("minDate="+minDate[0]+"maxDate="+maxDate[0]);
			List <FixedMmsDisposed> waiting=fixedMapper.get_fixed_mms_disposed(minDate[0],maxDate[0]);
			  return waiting;
		} else {
			System.out.println("No user does not have permission.");
			return null;
		}
	} catch (Exception e) {
		throw new ExceptionsList(e);
	}
}

public List<FixedMmsRemoved> View_mms_removed(HttpServletRequest request, String dates) {
	try {
		if (util.isPermitted(request, "FixedAsset", "get_fixed_mms_removed")) {
			System.out.println("this is mms removedddddddddddddddd");
			util.registerActivity(request, "Get fixed mms removed transactions", "-");
			JsonObject date_data_object = JsonParser.parseString(dates).getAsJsonObject();
			String[] minDate = date_data_object.get("minDate").getAsString().split(",");
			String[] maxDate = date_data_object.get("maxDate").getAsString().split(",");
			System.out.println("minDate="+minDate[0]+"maxDate="+maxDate[0]);
			List <FixedMmsRemoved> waiting=fixedMapper.get_fixed_mms_removed(minDate[0],maxDate[0]);
			  return waiting;
		} else {
			System.out.println("No user does not have permission.");
			return null;
		}
	} catch (Exception e) {
		throw new ExceptionsList(e);
	}
}

public List<fixedCoreReversal> get_coreReversal_for_view(HttpServletRequest request, String matched_date) {
	try {
		if (util.isPermitted(request, "FixedAsset", "get_vehicle_core_fixed_matched")) {
			util.registerActivity(request, "view matched transactions reversaly  ", "-");
			return fixedMapper.get_coreReversal_for_view(matched_date.replace("-", ""));
		} else {
			System.out.println("No user does not have permission.");
			return null;
		}
	} catch (Exception e) {
		throw new ExceptionsList(e);
	}
}

public Boolean unmatch_core_reversal_transactions(HttpServletRequest request, String data_ids) {
	try {
		if (util.isPermitted(request, "FixedAsset", "unmatch_transactions_fixed")) {
			util.registerActivity(request, "unmatch transactions reversaly matched on core", "-");
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
				fixedMapper.moveCoreReversalMatched(id_2[i]);
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

 }
