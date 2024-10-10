package com.example.demo.user.service;

import com.example.demo.Exception.CustomAllException;
import com.example.demo.Exception.ExceptionsList;
import com.example.demo.Stock.Mapper.StockMapperReport;
import com.example.demo.Stock.Model.StockReport;
import com.example.demo.abebayehu.entity.Fixed__report;
import com.example.demo.abebayehu.entity.Fixed_mms_report;
import com.example.demo.abebayehu.mapper.MapperFixedAsset;
import com.example.demo.model.Files_;
import com.example.demo.user.mapper.MapperAccount;
import com.example.demo.user.mapper.MapperFixedReport;
import com.example.demo.user.mapper.MapperIssueAccount;
import com.example.demo.user.mapper.MapperRTGS;
import com.example.demo.user.mapper.MapperReceivable;
import com.example.demo.user.mapper.RolebackMapper;
import com.example.demo.user.model.FileUpload;
import com.example.demo.user.model.File_rtgs__;
import com.example.demo.user.model.File_rtgs__ats;
import com.example.demo.utils.ExcelHelper;
import com.example.demo.utils.ExcelHelperFixed;
import com.example.demo.utils.ExcelHelperIssue;
import com.example.demo.utils.ExcelHelperPayable;
import com.example.demo.utils.ExcelHelperPayableRawData;
import com.example.demo.utils.ExcelHelperReceivable;
import com.example.demo.utils.ExcelHelperRecievableRawData2;
import com.example.demo.utils.ExcelHelperSTOCK;
import com.example.demo.utils.PDFGenerator;
import com.example.demo.utils.PDFGeneratorFixed;
import com.example.demo.utils.PDFGeneratorIssue;
import com.example.demo.utils.PDFGeneratorPayable;
import com.example.demo.utils.PDFGeneratorReceivable;
import com.example.demo.utils.PDFGeneratorSTOCK;
import com.example.demo.utils.Utils;
import com.google.zxing.WriterException;
import com.lowagie.text.DocumentException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework..io.InputStreamResource;
import org.springframework..io.Resource;
import org.springframework..io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  @Autowired
  private RolebackMapper roleMapper;

  @Autowired
  private MapperRTGS atsMaper;

  @Autowired
  private ExcelHelperPayableRawData ExcelHelperPayableRaw;

  @Autowired
  private ExcelHelperPayableRawData cc;

  @Autowired
  private MapperReceivable mapperReceivable;

  @Autowired
  private MapperAccount mapper;

  @Autowired
  private ExcelHelperRecievableRawData2 ExcelHelperRecievableRaw;

  @Autowired
  private MapperIssueAccount mapperIssue;

  @Autowired
  private MapperFixedReport fixedMapper;

  @Autowired
  private StockMapperReport stockMapperReport;

  @Value("${jwt.access.token.cookie.name}")
  private String cookieName;

  @Autowired
  private Utils util;

  @Autowired
  private MapperFixedAsset mapperFixedAsset;

  double last_uploded_closing_balance = 0d;
  double last_uploded_closing_balance_con = 0d;
  double last_uploded_closing_balance_ifb = 0d;
  double last_uploded_closing_balance_issue_ = 0d;
  double last_uploded_closing_balance_issue_qbs = 0d;
  double last_uploded_closing_balance_receivable = 0d;
  double last_uploded_closing_balance_payable = 0d;
  double upload_opening_balance = 0d;
  String user_account;
  Files_ newfile = new Files_();
  String disposed_date;
  String removed_date;
  String waiting_date;

  public Boolean uploadAndExtractFile(
    MultipartFile file,
    String transaction_date,
    String recon_left_right,
    HttpServletRequest request
  ) {
    try {
      // role authentication goes here
      if (true) {
        recon_left_right = "" + recon_left_right.replace("\"", "");
        Long user_account_id = mapper.getUserAccountId(
          util.get_user_id(request)
        );
        System.out.println(
          "**********************************" + util.get_user_id(request)
        );
        String user_account = util.get_user_account(request);
        if (
          util.check_IfFileIsUploaded(
            transaction_date,
            user_account_id,
            recon_left_right
          )
        ) throw new CustomAllException("file-already-uploaded");

        final String DIRECTORY =
          System.getProperty("user.dir") +
          "/src/main/resources/static/files/" +
          user_account +
          "/" +
          transaction_date.substring(0, 7) +
          "/" +
          transaction_date.substring(8, 10) +
          "/";

        File file_path = new File(StringUtils.join(DIRECTORY));

        if (!file_path.exists()) {
          file_path.mkdirs();
        }

        // List<String> filenames = new ArrayList<>();
        String extension = FilenameUtils.getExtension(
          org.springframework.util.StringUtils.cleanPath(
            file.getOriginalFilename()
          )
        );
        String filename = org.springframework.util.StringUtils.cleanPath(
          file.getOriginalFilename()
        );
        String file_location_with_name =
          file_path.getAbsolutePath() +
          "/" +
          generateUniqueFileName(recon_left_right) +
          "." +
          extension;
        newfile.setName(file.getOriginalFilename());
        newfile.setPath(file_location_with_name);
        newfile.setOriginal_file_name(filename);
        newfile.setFile_type(file.getContentType());
        newfile.setUpload_date(transaction_date);
        newfile.setUsage_type("null");
        newfile.setStatus("1");
        newfile.setAvailability("1");
        System.out.println("just before upload: " + recon_left_right);
        if (recon_left_right.equalsIgnoreCase("1")) {
          newfile.setType("ATS");

          extractExcellData__ats(
            file,
            transaction_date,
            recon_left_right,
            request,
            user_account_id
          );
          // return true;
        } else if (recon_left_right.equalsIgnoreCase("2")) {
          newfile.setType("");
          extractExcellData__(
            file,
            transaction_date,
            recon_left_right,
            request,
            user_account_id
          );
        }
        ////////////////////////////////// Issue account begin ///////////
        else if (recon_left_right.equalsIgnoreCase("125")) {
          newfile.setType("ISSUE_QBS");
          extractExcellData_issue_qbs(
            file,
            transaction_date,
            recon_left_right,
            request,
            user_account_id
          );
        } else if (recon_left_right.equalsIgnoreCase("126")) {
          newfile.setType("ISSUE_");
          extractExcellData_issue_(
            file,
            transaction_date,
            recon_left_right,
            request,
            user_account_id
          );
        }
        ////////////////////////////////// Issue account end /////////////

        else if (recon_left_right.equalsIgnoreCase("3")) {
          newfile.setType("Payable");
          extractExcellDataPayable(
            file,
            transaction_date,
            recon_left_right,
            request,
            user_account_id
          );
          System.out.println("----------------" + recon_left_right);
        } else if (recon_left_right.equalsIgnoreCase("4")) {
          newfile.setType("Receivable");
          extractExcellData_receivable(
            file,
            transaction_date,
            recon_left_right,
            request,
            user_account_id
          );
        }

        file.transferTo(new File(file_location_with_name));
        return true;
      }
      return null;
    } catch (Exception e) {
      System.out.println(
        "error in the file upload service..." + e.getLocalizedMessage()
      );
      throw new ExceptionsList(e);
      // return false;
    }
  }

  private boolean extractExcellData__ats(
    MultipartFile file,
    String date,
    String recon_left_right,
    HttpServletRequest request,
    long user_account_id
  ) {
    try {
      try {
        last_uploded_closing_balance =
          Double.parseDouble(
            atsMaper.check_openig_with_closing_balance_ats(
              findPrevDay(LocalDate.parse(date), 1).toString()
            )
          );

      } catch (Exception ex) {
        try {
          last_uploded_closing_balance =
            Double.parseDouble(
              atsMaper.check_openig_with_closing_balance_ats(
                findPrevDay(LocalDate.parse(date), 2).toString()
              )
            );
        } catch (Exception exx) {
          try {
            last_uploded_closing_balance = 0;
            if (
              last_uploded_closing_balance == 0
            ) last_uploded_closing_balance =
              Double.parseDouble(
                atsMaper.check_openig_with_closing_balance_ats(
                  findPrevDay(LocalDate.parse(date), 3).toString()
                )
              );
          } catch (Exception exxx) {
            try {
              last_uploded_closing_balance = 0;
              if (
                last_uploded_closing_balance == 0
              ) last_uploded_closing_balance =
                Double.parseDouble(
                  atsMaper.check_openig_with_closing_balance_ats(
                    findPrevDay(LocalDate.parse(date), 4).toString()
                  )
                );
            } catch (Exception exxxx) {
              try {
                last_uploded_closing_balance = 0;
                if (
                  last_uploded_closing_balance == 0
                ) last_uploded_closing_balance =
                  Double.parseDouble(
                    atsMaper.check_openig_with_closing_balance_ats(
                      findPrevDay(LocalDate.parse(date), 5).toString()
                    )
                  );
              } catch (Exception exxxxx) {
                last_uploded_closing_balance = 0;
              }
            }
          }
        }
      }
      Map<String, Object> resp = UploadExcelService_rtgs__ats.getAtsDataFromExcel(
        file.getInputStream(),
        Long.parseLong(date.replace("-", ""))
      );
      upload_opening_balance = (Double) resp.get("beginningBallance");
      System.out.println(
        "Beg balance: " + last_uploded_closing_balance + " : " + findPrevDay(LocalDate.parse(date), 1).toString()
      );
      System.out.println(
        "Beg balance: " + (Double) resp.get("beginningBallance")
      );
      if (
        last_uploded_closing_balance ==
        (Double) resp.get("beginningBallance") ||
        atsMaper.check_ats_for_firstly() == 0
      ) {
        Long file_id = mapper.addFile(newfile);
        mapper.addAccountFile(user_account_id, file_id, "1", "1");
        mapper.addFileUserDate(
          date,
          file_id,
          "1",
          "1",
          util.get_user_id(request),
          recon_left_right
        );
        mapper.updateAdditionalFileInfoAts(
          (Double) resp.get("beginningBallance"),
          (Double) resp.get("endingBallance"),
          (int) resp.get("totalCredit"),
          (int) resp.get("totalDebit"),
          (Double) resp.get("totalCreditAmount"),
          (Double) resp.get("totalDebitAmount"),
          (String) resp.get("businessDate"),
          (String) resp.get("accountNumber"),
          file_id
        );
        // System.out.println("============================"+last_uploded_closing_balance);
        for (
          int i = 0;
          i < ((List<File_rtgs__ats>) resp.get("data")).size();
          i++
        ) {
          ((List<File_rtgs__ats>) resp.get("data")).get(i)
            .setFile_id(file_id);
          mapper.uploadData__ats(
            ((List<File_rtgs__ats>) resp.get("data")).get(i)
          );
        }
        util.registerActivity(
          request,
          "upload Ats data",
          "upload  ATS transaction of date " + date
        );
        // System.out.println("------------------------**********: " + user_account);
        return true;
      } else {
        throw new CustomAllException(
          "last day uploaded file closing balance different from opening balance of this? please check it"
        );
      }
    } catch (IOException ex) {
      throw new CustomAllException("InvalidFormat");
    }
  }

  ///////////////////////// issue account  begin ////////////

  @SuppressWarnings("unchecked")
  private boolean extractExcellData_issue_qbs(
    MultipartFile file,
    String date,
    String recon_left_right,
    HttpServletRequest request,
    long user_account_id
  ) {
    try {
      try {
        last_uploded_closing_balance_issue_qbs =
          Double.parseDouble(
            atsMaper.check_openig_with_closing_balance_issue_qbs(
              findPrevDay(LocalDate.parse(date), 1).toString()
            )
          );
      } catch (Exception ex) {
        try {
          last_uploded_closing_balance_issue_qbs = 0;
          last_uploded_closing_balance_issue_qbs =
            Double.parseDouble(
              atsMaper.check_openig_with_closing_balance_issue_qbs(
                findPrevDay(LocalDate.parse(date), 2).toString()
              )
            );
        } catch (Exception exx) {
          try {
            last_uploded_closing_balance_issue_qbs = 0;
            last_uploded_closing_balance_issue_qbs =
              Double.parseDouble(
                atsMaper.check_openig_with_closing_balance_issue_qbs(
                  findPrevDay(LocalDate.parse(date), 3).toString()
                )
              );
          } catch (Exception exxx) {
            try {
              last_uploded_closing_balance_issue_qbs = 0;
              last_uploded_closing_balance_issue_qbs =
                Double.parseDouble(
                  atsMaper.check_openig_with_closing_balance_issue_qbs(
                    findPrevDay(LocalDate.parse(date), 4).toString()
                  )
                );
            } catch (Exception exxxx) {
              try {
                last_uploded_closing_balance_issue_qbs = 0;
                last_uploded_closing_balance_issue_qbs =
                  Double.parseDouble(
                    atsMaper.check_openig_with_closing_balance_issue_qbs(
                      findPrevDay(LocalDate.parse(date), 5).toString()
                    )
                  );
              } catch (Exception exxxxx) {
                last_uploded_closing_balance_issue_qbs = 0;
              }
            }
          }
        }
      }
      Map<String, Object> resp = UploadExcelService_issue_qbs.getAtsDataFromExcel(
        file.getInputStream(),
        Long.parseLong(date.replace("-", ""))
      );
      System.out.println("size: " + resp.size());
      System.out.println(
        "for update: " +
        resp.get("beginningBallance_con") +
        " \n: " +
        resp.get("beginningBallance_ifb") +
        " \n: " +
        resp.get("endingBallance_con") +
        " \n: " +
        resp.get("endingBallance_ifb") +
        " \n: " +
        resp.get("totalCredit_con") +
        " \n: " +
        resp.get("totalCredit_ifb") +
        " \n: " +
        resp.get("totalDebit_con") +
        " \n: " +
        resp.get("totalDebit_ifb") +
        " \n: " +
        resp.get("totalCreditAmount_con") +
        " \n: " +
        resp.get("totalCreditAmount_ifb") +
        " \n: " +
        resp.get("totalDebitAmount_con") +
        " \n: " +
        "this is the new: " +
        resp.get("the_new_b_b_ifb") +
        " \n: " +
        resp.get("totalDebitAmount_ifb")
      );
      if (
        last_uploded_closing_balance_issue_qbs ==
        ((Double) resp.get("the_new_b_b_ifb")).doubleValue() ||
        atsMaper.check_issue_qbs_for_firstly() == 0
      ) {
        Long file_id = mapper.addFile(newfile);
        //				mapper.addAccountFile(user_account_id, file_id, "1", "1");
        mapper.addFileUserDate(
          date,
          file_id,
          "1",
          "1",
          util.get_user_id(request),
          recon_left_right
        );
        mapper.updateAdditionalFileInfoIssue(
          (Double) resp.get("the_new_b_b_ifb"),
          (Double) resp.get("endingBallance_ifb"),
          (int) resp.get("totalCredit_ifb"),
          (int) resp.get("totalDebit_ifb"),
          (Double) resp.get("totalCreditAmount_ifb"),
          (Double) resp.get("totalDebitAmount_ifb"),
          file_id
        );

        //				System.out.println("toLong custom1: " + resp.get("beginningBallance_con"));

        for (
          int i = 0;
          i < ((List<File_rtgs__>) resp.get("data")).size();
          i++
        ) {
          System.out.println(
            "branches: " +
            ((List<File_rtgs__>) resp.get("data")).get(i).getBranch()
          );
          ((List<File_rtgs__>) resp.get("data")).get(i)
            .setFile_id(file_id);
          mapper.uploadData_issue_qbs(
            ((List<File_rtgs__>) resp.get("data")).get(i)
          );
        }

        util.registerActivity(
          request,
          "upload Issue  data",
          "upload ISSUE  transaction of date " + date
        );

        return true;
      } else {
        throw new CustomAllException(
          "last day uploaded file closing balance different from opening balance of this? please check it"
        );
      }
    } catch (IOException ex) {
      throw new CustomAllException("InvalidFormat");
    }
  }

  //	@SuppressWarnings("unchecked")
  //	private boolean extractExcellData_issue_(MultipartFile file, String date, String recon_left_right,
  //			HttpServletRequest request, long user_account_id) {
  //		try {
  //			try {
  //
  //				last_uploded_closing_balance_issue_ = Double
  //						.parseDouble(atsMaper.check_openig_with_closing_balance_issue_(
  //								findPrevDay(LocalDate.parse(date), 1).toString()));
  //			} catch (Exception ex) {
  //				try {
  //					last_uploded_closing_balance_issue_ = 0;
  //					last_uploded_closing_balance_issue_ = Double
  //							.parseDouble(atsMaper.check_openig_with_closing_balance_issue_(
  //									findPrevDay(LocalDate.parse(date), 2).toString()));
  //				} catch (Exception exx) {
  //					try {
  //						last_uploded_closing_balance_issue_ = 0;
  //						last_uploded_closing_balance_issue_ = Double
  //								.parseDouble(atsMaper.check_openig_with_closing_balance_issue_(
  //										findPrevDay(LocalDate.parse(date), 3).toString()));
  //					} catch (Exception exxx) {
  //						try {
  //							last_uploded_closing_balance_issue_ = 0;
  //							last_uploded_closing_balance_issue_ = Double
  //									.parseDouble(atsMaper.check_openig_with_closing_balance_issue_(
  //											findPrevDay(LocalDate.parse(date), 4).toString()));
  //						} catch (Exception exxxx) {
  //							try {
  //								last_uploded_closing_balance_issue_ = 0;
  //								last_uploded_closing_balance_issue_ = Double
  //										.parseDouble(atsMaper.check_openig_with_closing_balance_issue_(
  //												findPrevDay(LocalDate.parse(date), 5).toString()));
  //							} catch (Exception exxxxx) {
  //								last_uploded_closing_balance_issue_ = 0;
  //							}
  //						}
  //					}
  //				}
  //			}
  //			Map<String, Object> resp = UploadExcelService_issue_.getAtsDataFromExcel(file.getInputStream(),
  //					Long.parseLong(date.replace("-", "")));
  //			System.out.println("size: " + resp.size());
  //			System.out.println("for update: " + resp.get("beginningBallance_con") + " \n: "
  //					+ resp.get("beginningBallance_ifb") + " \n: " + resp.get("endingBallance_con") + " \n: "
  //					+ resp.get("endingBallance_ifb") + " \n: " + resp.get("totalCredit_con") + " \n: "
  //					+ resp.get("totalCredit_ifb") + " \n: " + resp.get("totalDebit_con") + " \n: "
  //					+ resp.get("totalDebit_ifb") + " \n: " + resp.get("totalCreditAmount_con") + " \n: "
  //					+ resp.get("totalCreditAmount_ifb") + " \n: " + resp.get("totalDebitAmount_con") + " \n: "
  //					+ "this is the new: " + resp.get("the_new_b_b_ifb") + " \n: " + resp.get("totalDebitAmount_ifb"));
  //			if (last_uploded_closing_balance_issue_ == ((Double) resp.get("the_new_b_b_ifb")).doubleValue()
  //					|| atsMaper.check_issue__for_firstly() == 0) {
  //
  ////				Long file_id = 0l;
  //				Long file_id = mapper.addFile(newfile);
  ////				mapper.addAccountFile(user_account_id, file_id, "1", "1");
  //				mapper.addFileUserDate(date, file_id, "1", "1", util.get_user_id(request), recon_left_right);
  //				mapper.updateAdditionalFileInfoIssue((Double) resp.get("the_new_b_b_ifb"),
  //						(Double) resp.get("endingBallance_ifb"), (int) resp.get("totalCredit_ifb"),
  //						(int) resp.get("totalDebit_ifb"), (Double) resp.get("totalCreditAmount_ifb"),
  //						(Double) resp.get("totalDebitAmount_ifb"), file_id);
  //
  ////				System.out.println("toLong custom1: " + resp.get("beginningBallance_con"));
  //
  //				for (int i = 0; i < ((List<File_rtgs__>) resp.get("data")).size(); i++) {
  //					System.out.println("branches: " + ((List<File_rtgs__>) resp.get("data")).get(i).getBranch());
  //					((List<File_rtgs__>) resp.get("data")).get(i).setFile_id(file_id);
  //					mapper.uploadData_issue_(((List<File_rtgs__>) resp.get("data")).get(i));
  //				}
  //
  //				util.registerActivity(request, "upload Issue  data",
  //						"upload ISSUE  transaction of date " + date);
  //
  //				return true;
  //			} else {
  //				throw new CustomAllException(
  //						"last day uploaded file closing balance different from opening balance of this? please check it");
  //			}
  //		} catch (IOException ex) {
  //			throw new CustomAllException("InvalidFormat");
  // 		}
  //
  //	}

  @SuppressWarnings("unchecked")
  private boolean extractExcellData_issue_(
    MultipartFile file,
    String date,
    String recon_left_right,
    HttpServletRequest request,
    long user_account_id
  ) {
    try {
      try {
        last_uploded_closing_balance_issue_ =
          Double.parseDouble(
            atsMaper.check_openig_with_closing_balance_issue_(
              findPrevDay(LocalDate.parse(date), 1).toString()
            )
          );
      } catch (Exception ex) {
        try {
          last_uploded_closing_balance_issue_ = 0;
          last_uploded_closing_balance_issue_ =
            Double.parseDouble(
              atsMaper.check_openig_with_closing_balance_issue_(
                findPrevDay(LocalDate.parse(date), 2).toString()
              )
            );
        } catch (Exception exx) {
          try {
            last_uploded_closing_balance_issue_ = 0;
            last_uploded_closing_balance_issue_ =
              Double.parseDouble(
                atsMaper.check_openig_with_closing_balance_issue_(
                  findPrevDay(LocalDate.parse(date), 3).toString()
                )
              );
          } catch (Exception exxx) {
            try {
              last_uploded_closing_balance_issue_ = 0;
              last_uploded_closing_balance_issue_ =
                Double.parseDouble(
                  atsMaper.check_openig_with_closing_balance_issue_(
                    findPrevDay(LocalDate.parse(date), 4).toString()
                  )
                );
            } catch (Exception exxxx) {
              try {
                last_uploded_closing_balance_issue_ = 0;
                last_uploded_closing_balance_issue_ =
                  Double.parseDouble(
                    atsMaper.check_openig_with_closing_balance_issue_(
                      findPrevDay(LocalDate.parse(date), 5).toString()
                    )
                  );
              } catch (Exception exxxxx) {
                last_uploded_closing_balance_issue_ = 0;
              }
            }
          }
        }
      }
      Map<String, Object> resp = UploadExcelService_issue_.getAtsDataFromExcel(
        file.getInputStream(),
        Long.parseLong(date.replace("-", ""))
      );
      System.out.println("size: " + resp.size());
      System.out.println(
        "for update: " +
        resp.get("beginningBallance_con") +
        " \n: " +
        resp.get("beginningBallance_ifb") +
        " \n: " +
        resp.get("endingBallance_con") +
        " \n: " +
        resp.get("endingBallance_ifb") +
        " \n: " +
        resp.get("totalCredit_con") +
        " \n: " +
        resp.get("totalCredit_ifb") +
        " \n: " +
        resp.get("totalDebit_con") +
        " \n: " +
        resp.get("totalDebit_ifb") +
        " \n: " +
        resp.get("totalCreditAmount_con") +
        " \n: " +
        resp.get("totalCreditAmount_ifb") +
        " \n: " +
        resp.get("totalDebitAmount_con") +
        " \n: " +
        "this is the new: " +
        resp.get("the_new_b_b_ifb") +
        " \n: " +
        resp.get("totalDebitAmount_ifb")
      );
      if (
        last_uploded_closing_balance_issue_ ==
        ((Double) resp.get("the_new_b_b_ifb")).doubleValue() ||
        atsMaper.check_issue__for_firstly() == 0
      ) {
        //				Long file_id = 0l;
        Long file_id = mapper.addFile(newfile);
        //				mapper.addAccountFile(user_account_id, file_id, "1", "1");
        mapper.addFileUserDate(
          date,
          file_id,
          "1",
          "1",
          util.get_user_id(request),
          recon_left_right
        );
        mapper.updateAdditionalFileInfoIssue(
          (Double) resp.get("the_new_b_b_ifb"),
          (Double) resp.get("endingBallance_ifb"),
          (int) resp.get("totalCredit_ifb"),
          (int) resp.get("totalDebit_ifb"),
          (Double) resp.get("totalCreditAmount_ifb"),
          (Double) resp.get("totalDebitAmount_ifb"),
          file_id
        );

        //				System.out.println("toLong custom1: " + resp.get("beginningBallance_con"));

        for (
          int i = 0;
          i < ((List<File_rtgs__>) resp.get("data")).size();
          i++
        ) {
          System.out.println(
            "branches: " +
            ((List<File_rtgs__>) resp.get("data")).get(i).getBranch()
          );
          ((List<File_rtgs__>) resp.get("data")).get(i)
            .setFile_id(file_id);
          mapper.uploadData_issue_(
            ((List<File_rtgs__>) resp.get("data")).get(i)
          );
        }

        util.registerActivity(
          request,
          "upload Issue  data",
          "upload ISSUE  transaction of date " + date
        );

        return true;
      } else {
        throw new CustomAllException(
          "last day uploaded file closing balance different from opening balance of this? please check it"
        );
      }
    } catch (IOException ex) {
      throw new CustomAllException("InvalidFormat");
    }
  }

  private boolean extractExcellData__(
    MultipartFile file,
    String date,
    String recon_left_right,
    HttpServletRequest request,
    long user_account_id
  ) {
    try {
      try {
        last_uploded_closing_balance_ifb =
          Double.parseDouble(
            atsMaper.check_openig_with_closing_balance__ifb(
              findPrevDay(LocalDate.parse(date), 1).toString()
            )
          );
        last_uploded_closing_balance_con =
          Double.parseDouble(
            atsMaper.check_openig_with_closing_balance__con(
              findPrevDay(LocalDate.parse(date), 1).toString()
            )
          );
      } catch (Exception ex) {
        try {
          // throw new ExceptionsList(ex);
          last_uploded_closing_balance_ifb = 0;
          last_uploded_closing_balance_con = 0;
          if (
            last_uploded_closing_balance_ifb == 0 &&
            last_uploded_closing_balance_con == 0
          ) {
            last_uploded_closing_balance_ifb =
              Double.parseDouble(
                atsMaper.check_openig_with_closing_balance__ifb(
                  findPrevDay(LocalDate.parse(date), 2).toString()
                )
              );
            last_uploded_closing_balance_con =
              Double.parseDouble(
                atsMaper.check_openig_with_closing_balance__con(
                  findPrevDay(LocalDate.parse(date), 2).toString()
                )
              );
          }
        } catch (Exception exx) {
          try {
            last_uploded_closing_balance_ifb = 0;
            last_uploded_closing_balance_con = 0;
            if (
              last_uploded_closing_balance_ifb == 0 &&
              last_uploded_closing_balance_con == 0
            ) {
              last_uploded_closing_balance_ifb =
                Double.parseDouble(
                  atsMaper.check_openig_with_closing_balance__ifb(
                    findPrevDay(LocalDate.parse(date), 3).toString()
                  )
                );
              last_uploded_closing_balance_con =
                Double.parseDouble(
                  atsMaper.check_openig_with_closing_balance__con(
                    findPrevDay(LocalDate.parse(date), 3).toString()
                  )
                );
            }
          } catch (Exception exxx) {
            try {
              last_uploded_closing_balance_ifb = 0;
              last_uploded_closing_balance_con = 0;
              if (
                last_uploded_closing_balance_ifb == 0 &&
                last_uploded_closing_balance_con == 0
              ) {
                last_uploded_closing_balance_ifb =
                  Double.parseDouble(
                    atsMaper.check_openig_with_closing_balance__ifb(
                      findPrevDay(LocalDate.parse(date), 4).toString()
                    )
                  );
                last_uploded_closing_balance_con =
                  Double.parseDouble(
                    atsMaper.check_openig_with_closing_balance__con(
                      findPrevDay(LocalDate.parse(date), 4).toString()
                    )
                  );
              }
            } catch (Exception exxxx) {
              try {
                last_uploded_closing_balance_ifb = 0;
                last_uploded_closing_balance_con = 0;
                if (
                  last_uploded_closing_balance_ifb == 0 &&
                  last_uploded_closing_balance_con == 0
                ) {
                  last_uploded_closing_balance_ifb =
                    Double.parseDouble(
                      atsMaper.check_openig_with_closing_balance__ifb(
                        findPrevDay(LocalDate.parse(date), 5).toString()
                      )
                    );
                  last_uploded_closing_balance_con =
                    Double.parseDouble(
                      atsMaper.check_openig_with_closing_balance__con(
                        findPrevDay(LocalDate.parse(date), 5).toString()
                      )
                    );
                }
              } catch (Exception exxxxx) {
                last_uploded_closing_balance_ifb = 0;
                last_uploded_closing_balance_con = 0;
              }
            }
          }
        }
      }
      Map<String, Object> resp = UploadExcelService_rtgs__.getAtsDataFromExcel(
        file.getInputStream(),
        Long.parseLong(date.replace("-", ""))
      );

      System.out.println(
        "for update: " +
        resp.get("beginningBallance_con") +
        " \n: " +
        resp.get("beginningBallance_ifb") +
        " \n: " +
        resp.get("endingBallance_con") +
        " \n: " +
        resp.get("endingBallance_ifb") +
        " \n: " +
        resp.get("totalCredit_con") +
        " \n: " +
        resp.get("totalCredit_ifb") +
        " \n: " +
        resp.get("totalDebit_con") +
        " \n: " +
        resp.get("totalDebit_ifb") +
        " \n: " +
        resp.get("totalCreditAmount_con") +
        " \n: " +
        resp.get("totalCreditAmount_ifb") +
        " \n: " +
        resp.get("totalDebitAmount_con") +
        " \n: " +
        "this is the new: " +
        resp.get("the_new_b_b_ifb") +
        " \n: " +
        resp.get("totalDebitAmount_ifb")
      );
      System.out.println(
        last_uploded_closing_balance_con +
        " : " +
        Math.abs(((Double) resp.get("beginningBallance_con")).doubleValue())
      );
      System.out.println(
        last_uploded_closing_balance_ifb +
        " : " +
        Math.abs(((Double) resp.get("beginningBallance_con")).doubleValue())
      );
      if (
        last_uploded_closing_balance_con ==
        Math.abs(((Double) resp.get("beginningBallance_con")).doubleValue()) &&
        last_uploded_closing_balance_ifb ==
        Math.abs(((Double) resp.get("the_new_b_b_ifb")).doubleValue()) ||
        atsMaper.check__for_firstly() == 0
      ) {
        Long file_id = mapper.addFile(newfile);
        mapper.addAccountFile(user_account_id, file_id, "1", "1");
        mapper.addFileUserDate(
          date,
          file_id,
          "1",
          "1",
          util.get_user_id(request),
          recon_left_right
        );
        mapper.updateAdditionalFileInfo(
          (Double) resp.get("beginningBallance_con"),
          (Double) resp.get("the_new_b_b_ifb"),
          (Double) resp.get("endingBallance_con"),
          (Double) resp.get("endingBallance_ifb"),
          (int) resp.get("totalCredit_con"),
          (int) resp.get("totalCredit_ifb"),
          (int) resp.get("totalDebit_con"),
          (int) resp.get("totalDebit_ifb"),
          (Double) resp.get("totalCreditAmount_con"),
          (Double) resp.get("totalCreditAmount_ifb"),
          (Double) resp.get("totalDebitAmount_con"),
          (Double) resp.get("totalDebitAmount_ifb"),
          file_id
        );

        for (
          int i = 0;
          i < ((List<File_rtgs__>) resp.get("data")).size();
          i++
        ) {
          ((List<File_rtgs__>) resp.get("data")).get(i)
            .setFile_id(file_id);
          mapper.uploadData__(
            ((List<File_rtgs__>) resp.get("data")).get(i)
          );
        }
        util.registerActivity(
          request,
          "upload  data",
          "upload   transaction of date " + date
        );

        return true;
      } else {
        System.out.println("it is all aka");
        throw new CustomAllException(
          "last day uploaded file closing balance different from opening balance of this? please check it"
        );
      }
    } catch (IOException ex) {
      throw new CustomAllException("InvalidFormat");
    }
  }

  private boolean extractExcellDataPayable(
    MultipartFile file,
    String date,
    String recon_left_right,
    HttpServletRequest request,
    long user_account_id
  ) {
    try {
      try {
        last_uploded_closing_balance_payable =
          Double.parseDouble(
            atsMaper.check_openig_with_closing_balancepayable(
              findPrevDay(LocalDate.parse(date), 1).toString()
            )
          );
      } catch (Exception ex) {
        try {
          // throw new ExceptionsList(ex);
          last_uploded_closing_balance_payable = 0;
          if (last_uploded_closing_balance_payable == 0) {
            last_uploded_closing_balance_payable =
              Double.parseDouble(
                atsMaper.check_openig_with_closing_balancepayable(
                  findPrevDay(LocalDate.parse(date), 2).toString()
                )
              );
          }
        } catch (Exception exx) {
          try {
            last_uploded_closing_balance_payable = 0;
            if (last_uploded_closing_balance_payable == 0) {
              last_uploded_closing_balance_payable =
                Double.parseDouble(
                  atsMaper.check_openig_with_closing_balancepayable(
                    findPrevDay(LocalDate.parse(date), 3).toString()
                  )
                );
            }
          } catch (Exception exxx) {
            try {
              last_uploded_closing_balance_payable = 0;
              if (last_uploded_closing_balance_payable == 0) {
                last_uploded_closing_balance_payable =
                  Double.parseDouble(
                    atsMaper.check_openig_with_closing_balancepayable(
                      findPrevDay(LocalDate.parse(date), 4).toString()
                    )
                  );
              }
            } catch (Exception exxxx) {
              try {
                last_uploded_closing_balance_payable = 0;
                if (last_uploded_closing_balance_payable == 0) {
                  last_uploded_closing_balance_payable =
                    Double.parseDouble(
                      atsMaper.check_openig_with_closing_balancepayable(
                        findPrevDay(LocalDate.parse(date), 5).toString()
                      )
                    );
                }
              } catch (Exception exxxxx) {
                last_uploded_closing_balance_payable = 0;
              }
            }
          }
        }
      }
      Map<String, Object> resp = UploadExcelService_payable.getPayableDataFromExcel(
        file.getInputStream(),
        Long.parseLong(date.replace("-", ""))
      );
      System.out.println("size: " + resp.size());
      //			System.out.println("for update: " + resp.get("beginningBallance") + " \n: "
      //					+ resp.get("beginningBallance_ifb") + " \n: " + resp.get("endingBallance_con") + " \n: "
      //					+ resp.get("endingBallance_ifb") + " \n: " + resp.get("totalCredit_con") + " \n: "
      //					+ resp.get("totalCredit_ifb") + " \n: " + resp.get("totalDebit_con") + " \n: "
      //					+ resp.get("totalDebit_ifb") + " \n: " + resp.get("totalCreditAmount_con") + " \n: "
      //					+ resp.get("totalCreditAmount_ifb") + " \n: " + resp.get("totalDebitAmount_con") + " \n: "
      //					+ "this is the new: " + resp.get("the_new_b_b_ifb") + " \n: " + resp.get("totalDebitAmount_ifb"));
      if (
        last_uploded_closing_balance_payable ==
        ((Double) resp.get("beginningBallance")).doubleValue() ||
        atsMaper.check_payable_for_firstly() == 0
      ) {
        Long file_id = mapper.addFile(newfile);
        mapper.addAccountFile(user_account_id, file_id, "1", "1");
        mapper.addFileUserDate(
          date,
          file_id,
          "1",
          "1",
          util.get_user_id(request),
          recon_left_right
        );
        mapper.updateAdditionalFileInfoPayable(
          (Double) resp.get("beginningBallance"),
          (Double) resp.get("endingBallance"),
          (int) resp.get("totalCredit"),
          (int) resp.get("totalDebit"),
          (Double) resp.get("totalCreditAmount"),
          (Double) resp.get("totalDebitAmount"),
          file_id
        );

        System.out.println("toLong custom1: " + resp.get("beginningBallance"));
        // System.out.println("toLong custom2: " +
        // toLong(resp.get("beginningBallance_con")));

        for (
          int i = 0;
          i < ((List<File_rtgs__>) resp.get("data")).size();
          i++
        ) {
          ((List<File_rtgs__>) resp.get("data")).get(i)
            .setFile_id(file_id);
          mapper.uploadDataPayable(
            ((List<File_rtgs__>) resp.get("data")).get(i)
          );
        }
        util.registerActivity(
          request,
          "upload  data",
          "upload   transaction of date " + date
        );

        System.out.println(
          "----------------------" + last_uploded_closing_balance_ifb
        );
        return true;
      } else {
        throw new CustomAllException(
          "last day uploaded file closing balance different from opening balance of this? please check it"
        );
      }
    } catch (IOException ex) {
      throw new CustomAllException("InvalidFormat");
    }
  }

  private boolean extractExcellData_receivable(
    MultipartFile file,
    String date,
    String recon_left_right,
    HttpServletRequest request,
    long user_account_id
  ) {
    try {
      try {
        last_uploded_closing_balance_receivable =
          Double.parseDouble(
            atsMaper.check_openig_with_closing_balance_receivable(
              findPrevDay(LocalDate.parse(date), 1).toString()
            )
          );
        System.out.println(
          "bbbbbbbbb" + last_uploded_closing_balance_receivable
        );
      } catch (Exception ex) {
        try {
          // throw new ExceptionsList(ex);
          last_uploded_closing_balance_receivable = 0;
          if (last_uploded_closing_balance_receivable == 0) {
            last_uploded_closing_balance_receivable =
              Double.parseDouble(
                atsMaper.check_openig_with_closing_balance_receivable(
                  findPrevDay(LocalDate.parse(date), 2).toString()
                )
              );
          }
        } catch (Exception exx) {
          try {
            last_uploded_closing_balance_receivable = 0;

            if (last_uploded_closing_balance_receivable == 0) {
              last_uploded_closing_balance_receivable =
                Double.parseDouble(
                  atsMaper.check_openig_with_closing_balance_receivable(
                    findPrevDay(LocalDate.parse(date), 3).toString()
                  )
                );
            }
          } catch (Exception exxx) {
            try {
              last_uploded_closing_balance_receivable = 0;
              if (last_uploded_closing_balance_receivable == 0) {
                last_uploded_closing_balance_receivable =
                  Double.parseDouble(
                    atsMaper.check_openig_with_closing_balance_receivable(
                      findPrevDay(LocalDate.parse(date), 4).toString()
                    )
                  );
              }
            } catch (Exception exxxx) {
              try {
                last_uploded_closing_balance_receivable = 0;
                if (last_uploded_closing_balance_receivable == 0) {
                  last_uploded_closing_balance_receivable =
                    Double.parseDouble(
                      atsMaper.check_openig_with_closing_balance_receivable(
                        findPrevDay(LocalDate.parse(date), 5).toString()
                      )
                    );
                }
              } catch (Exception exxxxx) {
                last_uploded_closing_balance_receivable = 0;
              }
            }
          }
        }
      }
      Map<String, Object> resp = UploadExcelService_receivable.getAtsDataFromExcel(
        file.getInputStream(),
        Long.parseLong(date.replace("-", ""))
      );
      System.out.println("size: " + resp.size());
      System.out.println(
        "for update: " +
        resp.get("beginningBallance") +
        " \n: " +
        resp.get("endingBallance") +
        " \n: " +
        resp.get("totalCredit") +
        " \n: " +
        resp.get("totalDebit") +
        " \n: " +
        resp.get("totalCreditAmount") +
        " \n: " +
        "this is the new: " +
        resp.get("the_new_b_b") +
        " \n: " +
        resp.get("totalDebitAmount")
      );
      if (
        last_uploded_closing_balance_receivable ==
        ((Double) resp.get("beginningBallance")).doubleValue() ||
        atsMaper.check_recievable_for_firstly() == 0
      ) {
        Long file_id = mapper.addFile(newfile);
        mapper.addAccountFile(user_account_id, file_id, "1", "1");
        mapper.addFileUserDate(
          date,
          file_id,
          "1",
          "1",
          util.get_user_id(request),
          recon_left_right
        );
        mapper.updateAdditionalFileInfoReceivable(
          (Double) resp.get("the_new_b_b"),
          (Double) resp.get("endingBallance"),
          (int) resp.get("totalCredit"),
          (int) resp.get("totalDebit"),
          (Double) resp.get("totalCreditAmount"),
          (Double) resp.get("totalDebitAmount"),
          file_id
        );

        System.out.println("toLong custom1: " + resp.get("the_new_b_b"));
        // System.out.println("toLong custom2: " +
        // toLong(resp.get("beginningBallance_con")));

        for (
          int i = 0;
          i < ((List<File_rtgs__>) resp.get("data")).size();
          i++
        ) {
          ((List<File_rtgs__>) resp.get("data")).get(i)
            .setFile_id(file_id);
          mapper.uploadData_receivable(
            ((List<File_rtgs__>) resp.get("data")).get(i)
          );
        }
        util.registerActivity(
          request,
          "upload  data",
          "upload  receivable transaction of date " + date
        );

        System.out.println(
          "----------------------" + last_uploded_closing_balance_receivable
        );
        return true;
      } else {
        throw new CustomAllException(
          "last day uploaded file closing balance different from opening balance of this? please check it"
        );
      }
    } catch (IOException ex) {
      throw new CustomAllException("InvalidFormat");
    }
  }

  private static LocalDate findPrevDay(LocalDate localdate, int i) {
    return localdate.minusDays(i);
  }

  String generateUniqueFileName(String recon_left_right) {
    String filename = "";
    long millis = System.currentTimeMillis();
    String datetime = new Date().toGMTString();
    datetime = datetime.replace(" ", "");
    datetime = datetime.replace(":", "");
    String rndchars = RandomStringUtils.randomAlphanumeric(16);
    filename = recon_left_right + "_" + datetime + "_" + millis;
    return filename;
  }

  public List<FileUpload> getAllUploadedFiles(HttpServletRequest request) {
    try {
      if (
        util.isPermitted(request, "User", "get_all_uploaded_files") ||
        util.isPermitted(request, "IssueAccount", "get_all_uploaded_files")
      ) {
        util.registerActivity(request, "get all uploaded files", "-");
        List<FileUpload> files_for_user = new ArrayList<>();
        List<FileUpload> files_for_payable = new ArrayList<>();
        List<FileUpload> files_for_receivable = new ArrayList<>();
        List<FileUpload> files_for_issue = new ArrayList<>();
        List<FileUpload> all_upload = new ArrayList<>();

        if (util.userHasRole(request, "User")) {
          files_for_user = mapper.getAllUploadedFiles_for_user();
          //					files_for_payable = mapper.getAllPayableFiles();
          //					files_for_receivable = mapper.getAllReceivableFiles();
        }

        if (util.userHasRole(request, "IssueAccount")) {
          files_for_issue = mapper.getAllUploadedFiles_for_issue();
        }

        for (FileUpload file : files_for_user) {
          all_upload.add(file);
        }
        for (FileUpload file : files_for_issue) {
          all_upload.add(file);
        }
        //				for (FileUpload file : files_for_receivable) {
        //					all_upload.add(file);
        //				}
        //				System.out.println("payable size : " + files_for_payable.size());
        //				System.out.println("payable type : " + files_for_payable.get(0).getType());
        //				for (FileUpload file : files_for_issue) {
        //					all_upload.add(file);
        //				}
        return all_upload;
      } else {
        System.out.println(
          "No user does not have permission get uploaded files."
        );
        return null;
      }
    } catch (Exception e) {
      throw new ExceptionsList(e);
    }
  }

  public FileUpload getfilebyid(HttpServletRequest request, long id) {
    try {
      return mapper.getfilebyid(id);
    } catch (Exception e) {
      throw new ExceptionsList(e);
    }
  }

  public boolean FileUpload(long id, FileUpload updateFileUpload) {
    try {
      FileUpload file = new FileUpload();
      file.setFile_name(updateFileUpload.getFile_name());
      file.setFile_type(updateFileUpload.getFile_type());
      file.setUpload_date(updateFileUpload.getUpload_date());
      mapper.updateFile(
        id,
        file.getFile_name(),
        file.getFile_type(),
        file.getUpload_date()
      );
      return true;
    } catch (Exception e) {
      throw new ExceptionsList(e);
    }
  }

  //	@RequestMapping(value = "download/{filename}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  //	public ResponseEntity<Resource> downloadFiles(@PathVariable("filename") String filename) throws IOException {
  //		Path filePath = Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve(filename);
  //		if (!Files.exists(filePath)) {
  //			throw new FileNotFoundException(filename + " was not found on the server.");
  //		}
  //		Resource resource = new UrlResource(filePath.toUri());
  //		HttpHeaders httpHeaders = new HttpHeaders();
  //		httpHeaders.add("File-Name", filename);
  //		httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());
  //		return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
  //				.headers(httpHeaders).body(resource);
  //	}

  //	if(true) {
  //		//path = tutorialsToExcelPayable();
  //		Path filePath = Paths.get(path).toAbsolutePath().normalize().resolve(path);
  //		if (!Files.exists(filePath)) {
  //			throw new FileNotFoundException("The requested file could not be found on the server.");
  //		}
  //		Resource resource = new UrlResource(filePath.toUri());
  //		HttpHeaders httpHeaders = new HttpHeaders();
  //		httpHeaders.add("File-Name", path);
  ////		if (date_type.split("-_-")[1].equalsIgnoreCase("pdf")
  ////				|| date_type.split("-_-")[1].equalsIgnoreCase("payable_pdf")
  ////				|| date_type.split("-_-")[1].equalsIgnoreCase("receivable_pdf")
  ////				|| date_type.split("-_-")[1].equalsIgnoreCase("issue_pdf")
  ////				|| date_type.split("-_-")[1].equalsIgnoreCase("fur_fixed_pdf")
  ////				|| date_type.split("-_-")[1].equalsIgnoreCase("comp_fixed_pdf")
  ////				|| date_type.split("-_-")[1].equalsIgnoreCase("vehi_fixed_pdf")
  ////				|| date_type.split("-_-")[1].equalsIgnoreCase("equp_fixed_pdf")) {
  ////			httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
  ////		} else {
  //			httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
  ////		}
  //		return ResponseEntity.ok().headers(httpHeaders).body(resource);
  //	}

  public ResponseEntity<Resource> downloadFiles(
    HttpServletRequest request,
    Long id
  ) {
    try {
      if (util.isPermitted(request, "User", "download_files")) {
        util.registerActivity(request, "download files", "-");
        String file_type = mapperReceivable.getFileType(id);
        System.out.println("this is the file type" + file_type);
        String path = "";
        String date = mapperReceivable.getUploadDate(id);
        System.out.println("the date is hereee" + date);
        //				List<File_rtgs__> xx = mapperReceivable.getPayableRawData(id);
        if (file_type.equalsIgnoreCase("Payable")) {
          path = exportExcelFilePayableRaw(date);
          // path = cc.tutorialsToExcelPayable(date, xx);
          Path filePath = Paths
            .get(path)
            .toAbsolutePath()
            .normalize()
            .resolve(path);
          if (!Files.exists(filePath)) {
            throw new FileNotFoundException(
              "The requested file could not be found on the server."
            );
          }
          Resource resource = new UrlResource(filePath.toUri());
          HttpHeaders httpHeaders = new HttpHeaders();
          httpHeaders.add("File-Name", path);
          httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
          return ResponseEntity.ok().headers(httpHeaders).body(resource);
        } else if (file_type.equalsIgnoreCase("Receivable")) {
          path = exportExcelFileRecievableRaw(date);
          // path = cc.tutorialsToExcelPayable(date, xx);
          Path filePath = Paths
            .get(path)
            .toAbsolutePath()
            .normalize()
            .resolve(path);
          if (!Files.exists(filePath)) {
            throw new FileNotFoundException(
              "The requested file could not be found on the server."
            );
          }
          Resource resource = new UrlResource(filePath.toUri());
          HttpHeaders httpHeaders = new HttpHeaders();
          httpHeaders.add("File-Name", path);
          httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
          return ResponseEntity.ok().headers(httpHeaders).body(resource);
        } else {
          String path1 = mapper.downloadFiles(id);
          Path filePath = Paths
            .get(path1)
            .toAbsolutePath()
            .normalize()
            .resolve(path1);
          if (!Files.exists(filePath)) {
            //					System.out.println("it does not exist.");
            throw new FileNotFoundException(
              "The requested file could not be found on the server."
            );
          }
          Resource resource = new UrlResource(filePath.toUri());
          HttpHeaders httpHeaders = new HttpHeaders();
          httpHeaders.add("File-Name", path1);
          httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
          httpHeaders.add(
            HttpHeaders.CONTENT_DISPOSITION,
            "attachment;File-Name=" + resource.getFilename()
          );
          // System.out.println("content type: " +
          // MediaType.parseMediaType(Files.probeContentType(filePath)));
          return ResponseEntity.ok().headers(httpHeaders).body(resource);
          //				return ResponseEntity.ok().contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
          //						.headers(httpHeaders).body(resource);
          //				return null;
        }
      } else {
        System.out.println("No user does not have permission.");
        return null;
      }
    } catch (Exception e) {
      throw new ExceptionsList(e);
    }
  }

  public boolean deleteFile(HttpServletRequest request, long id) {
    try {
      if (util.isPermitted(request, "User", "delete_file")) {
        util.registerActivity(request, "delete file", "-");

        return mapper.deleteFile(id);
      } else {
        System.out.println("No user does not have permission.");
        return false;
      }
    } catch (Exception e) {
      throw new ExceptionsList(e);
    }
  }

  public boolean rolebackFile(
    HttpServletRequest request,
    List<Long> id,
    List<String> type
  ) {
    try {
      if (util.isPermitted(request, "User", "roleback_file")) {
        util.registerActivity(request, "roleback file", "-");
        for (int i = 0; i < id.size(); i++) {
          if (
            roleMapper.payableCredit(id.get(i)) == 0 &&
            roleMapper.payableDebit(id.get(i)) == 0 &&
            roleMapper.receivableCredit(id.get(i)) == 0 &&
            roleMapper.receivableDebit(id.get(i)) == 0 &&
            roleMapper.issue_(id.get(i)) == 0 &&
            roleMapper.issue_qbs(id.get(i)) == 0 &&
            roleMapper.rtgsam(id.get(i)) == 0 &&
            roleMapper.rtgcm(id.get(i)) == 0 &&
            roleMapper.ercam(id.get(i)) == 0 &&
            roleMapper.erccm(id.get(i)) == 0 &&
            roleMapper.btbam(id.get(i)) == 0 &&
            roleMapper.btbcm(id.get(i)) == 0 &&
            roleMapper.sosam(id.get(i)) == 0 &&
            roleMapper.soscm(id.get(i)) == 0 &&
            roleMapper.apm(id.get(i)) == 0 &&
            roleMapper.cpm(id.get(i)) == 0
          ) {
            if (type.get(i).equalsIgnoreCase("1")) {
              roleMapper.roleback_file(id.get(i));
              roleMapper.roleback_ats_file(id.get(i));
              return true;
            } else if (type.get(i).equalsIgnoreCase("2")) {
              roleMapper.roleback_file(id.get(i));
              roleMapper.roleback__file(id.get(i));
              return true;
            } else if (type.get(i).equalsIgnoreCase("3")) {
              roleMapper.roleback_file(id.get(i));
              roleMapper.roleback_payable_file(id.get(i));
              return true;
            } else if (type.get(i).equalsIgnoreCase("4")) {
              roleMapper.roleback_file(id.get(i));
              roleMapper.roleback_receivable_file(id.get(i));
              return true;
            } else if (type.get(i).equalsIgnoreCase("125")) {
              roleMapper.roleback_file(id.get(i));
              roleMapper.roleback_issue_qbs_file(id.get(i));
              return true;
            } else if (type.get(i).equalsIgnoreCase("126")) {
              roleMapper.roleback_file(id.get(i));
              roleMapper.roleback_issue__file(id.get(i));
              return true;
            }
          }
          // return false;
        }

        return false;
      } else {
        System.out.println("No user does not have permission.");
        return false;
      }
    } catch (Exception e) {
      throw new ExceptionsList(e);
    }
  }

  public ResponseEntity<Resource> downloadReportFiles(
    HttpServletRequest request,
    String date_type
  ) {
    try {
      if (
        util.isPermitted(request, "User", "download_report_files") ||
        util.isPermitted(request, "FixedAsset", "download_report_files") ||
        util.isPermitted(request, "IssueAccount", "download_report_files")
      ) {
        util.registerActivity(request, "download  report files", "-");
        //				generate
        System.out.println("this is the download file serviceeeeeeeeeeeeee");
        disposed_date = mapperFixedAsset.getDisposedDate();
        removed_date = mapperFixedAsset.getRemovedDate();
        waiting_date = mapperFixedAsset.getWaitingDate();

        String path = "";
        if (date_type.split("-_-")[1].equalsIgnoreCase("pdf")) {
          //					if (true) {
          if (atsMaper.existsFile(date_type.split("-_-")[0])) {
            path =
              exportPdfFile(
                date_type.split("-_-")[0],
                date_type.split("-_-")[2],
                date_type.split("-_-")[3]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (date_type.split("-_-")[1].equalsIgnoreCase("excel")) {
          if (atsMaper.existsFile(date_type.split("-_-")[0])) {
            path =
              exportExcelFile(
                date_type.split("-_-")[0],
                date_type.split("-_-")[2],
                date_type.split("-_-")[3]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (date_type.split("-_-")[1].equalsIgnoreCase("payable_pdf")) {
          if (atsMaper.existsFilePayable(date_type.split("-_-")[0])) {
            System.out.println("filter works...");
            path = exportPdfFilePayable(date_type.split("-_-")[0]);
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("payable_excel")
        ) {
          if (atsMaper.existsFilePayable(date_type.split("-_-")[0])) {
            path = exportExcelFilePayable(date_type.split("-_-")[0]);
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("receivable_pdf")
        ) {
          if (atsMaper.existsFileReceivable(date_type.split("-_-")[0])) {
            path = exportPdfFileReceivable(date_type.split("-_-")[0]);
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("receivable_excel")
        ) {
          if (atsMaper.existsFileReceivable(date_type.split("-_-")[0])) {
            path = exportExcelFileReceivable(date_type.split("-_-")[0]);
          } else throw new ExceptionsList(new CustomAllException(""));
          //				} else if (date_type.split("-_-")[1].equalsIgnoreCase("payable_pdf")) {
          //					if (util.existsFilePayable(date_type.split("-_-")[0])) {
          //						System.out.println("filter works...");
          //						path = exportPdfFilePayable(date_type.split("-_-")[0]);
          //					} else
          //						throw new ExceptionsList(new CustomAllException(""));
          //				} else if (date_type.split("-_-")[1].equalsIgnoreCase("payable_excel")) {
          //					if (util.existsFilePayable(date_type.split("-_-")[0])) {
          //						path = exportExcelFilePayable(date_type.split("-_-")[0]);
          //					} else
          //						throw new ExceptionsList(new CustomAllException(""));
          //				} else if (date_type.split("-_-")[1].equalsIgnoreCase("receivable_pdf")) {
          //					if (util.existsFileReceivable(date_type.split("-_-")[0])) {
          //						path = exportPdfFileReceivable(date_type.split("-_-")[0]);
          //					} else
          //						throw new ExceptionsList(new CustomAllException(""));
          //				} else if (date_type.split("-_-")[1].equalsIgnoreCase("receivable_excel")) {
          //					if (util.existsFileReceivable(date_type.split("-_-")[0])) {
          //						path = exportExcelFileReceivable(date_type.split("-_-")[0]);
          //					} else
          //						throw new ExceptionsList(new CustomAllException(""));

        } else if (date_type.split("-_-")[1].equalsIgnoreCase("issue_pdf")) {
          if (atsMaper.existsFileIssue(date_type.split("-_-")[0])) {
            path = exportPdfFileIssue(date_type.split("-_-")[0]);
            System.out.println("pdf fileeeeeeeeeeeeeeeeeeeee issue    ");
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (date_type.split("-_-")[1].equalsIgnoreCase("issue_excel")) {
          if (atsMaper.existsFileIssue(date_type.split("-_-")[0])) {
            path = exportExcelFileIssue(date_type.split("-_-")[0]);
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("fur_fixed_pdf")
        ) {
          if (atsMaper.existsFileFixed(date_type.split("-_-")[0])) {
            System.out.println("furniture pdf====" + date_type.split("-_-")[0]);
            path =
              exportPdfFileFixedFurniture(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
            System.out.println("furniture pdf=" + date_type.split("-_-")[0]);
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("fur_fixed_excel")
        ) {
          if (atsMaper.existsFileFixed(date_type.split("-_-")[0])) {
            path =
              exportExcelFileFixedFurniture(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else {
            System.out.println("furniture Excel");
            throw new ExceptionsList(new CustomAllException(""));
          }
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("comp_fixed_pdf")
        ) {
          System.out.println("this is the computer pdffff");
          if (atsMaper.existsFileFixed(date_type.split("-_-")[0])) {
            path =
              exportPdfFileFixedComputer(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
            System.out.println("computer pdffffffffff");
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("comp_fixed_excel")
        ) {
          if (atsMaper.existsFileFixed(date_type.split("-_-")[0])) {
            path =
              exportExcelFileFixedComputer(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("vehi_fixed_pdf")
        ) {
          if (atsMaper.existsFileFixed(date_type.split("-_-")[0])) {
            path =
              exportPdfFileFixedMotorVehicle(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
            System.out.println("Motor Vehicle pdf");
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("vehi_fixed_excel")
        ) {
          if (atsMaper.existsFileFixed(date_type.split("-_-")[0])) {
            path =
              exportExcelFileFixedMotorVehicle(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
            System.out.println("motor vehicle excel");
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("equp_fixed_pdf")
        ) {
          if (atsMaper.existsFileFixed(date_type.split("-_-")[0])) {
            path =
              exportPdfFileFixedOfficeEquipment(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
            System.out.println("Equpment pdf");
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("equp_fixed_excel")
        ) {
          if (atsMaper.existsFileFixed(date_type.split("-_-")[0])) {
            path =
              exportExcelFileFixedOfficeEquipment(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
            System.out.println("Equpment excel");
          } else throw new ExceptionsList(new CustomAllException(""));
        }
        ///////////////////////////////////////////////////////////////////////////////
        // STOCK START
        else if (
          date_type.split("-_-")[1].equalsIgnoreCase("121_stationary_pdf")
        ) {
          if (atsMaper.existsFile121_stationary(date_type.split("-_-")[0])) {
            path =
              exportPdfSTOCK(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("121_stationary_excel")
        ) {
          if (atsMaper.existsFile121_stationary(date_type.split("-_-")[0])) {
            path =
              exportExcel121_stationary(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("111_tools_pdf")
        ) {
          if (atsMaper.existsFile111_tools(date_type.split("-_-")[0])) {
            path =
              exportPdfSTOCK(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("111_tools_excel")
        ) {
          if (atsMaper.existsFile111_tools(date_type.split("-_-")[0])) {
            path =
              exportExcel121_stationary(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("113_spares_pdf")
        ) {
          if (atsMaper.existsFile113_spares(date_type.split("-_-")[0])) {
            path =
              exportPdfSTOCK(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("113_spares_excel")
        ) {
          if (atsMaper.existsFile113_spares(date_type.split("-_-")[0])) {
            path =
              exportExcel121_stationary(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("105_uniform_pdf")
        ) {
          if (atsMaper.existsFile105_uniform(date_type.split("-_-")[0])) {
            path =
              exportPdfSTOCK(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("105_uniform_excel")
        ) {
          if (atsMaper.existsFile105_uniform(date_type.split("-_-")[0])) {
            path =
              exportExcel121_stationary(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("119_accessory_pdf")
        ) {
          if (atsMaper.existsFile119_accessory(date_type.split("-_-")[0])) {
            path =
              exportPdfSTOCK(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("119_accessory_excel")
        ) {
          if (atsMaper.existsFile119_accessory(date_type.split("-_-")[0])) {
            path =
              exportExcel121_stationary(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("120_check_pdf")
        ) {
          if (atsMaper.existsFile120_check(date_type.split("-_-")[0])) {
            path =
              exportPdfSTOCK(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("120_check_excel")
        ) {
          if (atsMaper.existsFile120_check(date_type.split("-_-")[0])) {
            path =
              exportExcel121_stationary(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("112_sanitory_pdf")
        ) {
          if (atsMaper.existsFile112_sanitory(date_type.split("-_-")[0])) {
            path =
              exportPdfSTOCK(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("112_sanitory_excel")
        ) {
          if (atsMaper.existsFile112_sanitory(date_type.split("-_-")[0])) {
            path =
              exportExcel121_stationary(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("106_computer_pdf")
        ) {
          if (atsMaper.existsFile106_computer(date_type.split("-_-")[0])) {
            path =
              exportPdfSTOCK(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("106_computer_excel")
        ) {
          if (atsMaper.existsFile106_computer(date_type.split("-_-")[0])) {
            path =
              exportExcel121_stationary(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("107_furniture_pdf")
        ) {
          if (atsMaper.existsFile107_furniture(date_type.split("-_-")[0])) {
            path =
              exportPdfSTOCK(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("107_furniture_excel")
        ) {
          if (atsMaper.existsFile107_furniture(date_type.split("-_-")[0])) {
            path =
              exportExcel121_stationary(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type.split("-_-")[1].equalsIgnoreCase("104_office_equipment_pdf")
        ) {
          if (
            atsMaper.existsFile104_office_equipment(date_type.split("-_-")[0])
          ) {
            path =
              exportPdfSTOCK(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        } else if (
          date_type
            .split("-_-")[1].equalsIgnoreCase("104_office_equipment_excel")
        ) {
          if (
            atsMaper.existsFile104_office_equipment(date_type.split("-_-")[0])
          ) {
            path =
              exportExcel121_stationary(
                date_type.split("-_-")[0],
                date_type.split("-_-")[1]
              );
          } else throw new ExceptionsList(new CustomAllException(""));
        }
        // STOCK END
        ///////////////////////////////////////////////////////////////////////////////
        Path filePath = Paths
          .get(path)
          .toAbsolutePath()
          .normalize()
          .resolve(path);
        if (!Files.exists(filePath)) {
          throw new FileNotFoundException(
            "The requested file could not be found on the server."
          );
        }
        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", path);
        if (
          date_type.split("-_-")[1].equalsIgnoreCase("pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("payable_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("receivable_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("issue_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("fur_fixed_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("comp_fixed_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("vehi_fixed_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("equp_fixed_pdf") ||
          // STOCK
          date_type.split("-_-")[1].equalsIgnoreCase("121_stationary_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("111_tools_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("113_spares_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("105_uniform_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("119_accessory_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("120_check_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("112_sanitory_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("106_computer_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("107_furniture_pdf") ||
          date_type.split("-_-")[1].equalsIgnoreCase("104_office_equipment_pdf")
          // STOCK
        ) {
          httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
        } else {
          httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
        }
        return ResponseEntity.ok().headers(httpHeaders).body(resource);
        //				return null;
      } else {
        System.out.println("No user does not have permission.");
        return null;
      }
    } catch (Exception e) {
      throw new ExceptionsList(e);
    }
  }

  public String exportExcel121_stationary(String date, String type)
    throws IOException, ParseException, WriterException {
    List<StockReport> data_stock_ = null;
    List<StockReport> data_stock_mms = null;
    Double stock__total = 0d;
    Double stock_mms_total = 0d;

    if (type.equalsIgnoreCase("121_stationary_excel")) {
      data_stock_ = stockMapperReport.reportStationary(date);
      data_stock_mms = stockMapperReport.reportMmsStationary(date);
      stock__total =
        stockMapperReport.getEndingBalance121_stationary(date);
      stock_mms_total =
        stockMapperReport.getEndingBalanceMms121_stationary(date);
    } else if (type.equalsIgnoreCase("111_tools_excel")) {
      data_stock_ = stockMapperReport.reportTools(date);
      data_stock_mms = stockMapperReport.reportMmsTools(date);
      stock__total = stockMapperReport.getEndingBalance111_tools(date);
      stock_mms_total = stockMapperReport.getEndingBalanceMms111_tools(date);
    } else if (type.equalsIgnoreCase("113_spares_excel")) {
      data_stock_ = stockMapperReport.reportSpares(date);
      data_stock_mms = stockMapperReport.reportMmsSpares(date);
      stock__total = stockMapperReport.getEndingBalance113_spares(date);
      stock_mms_total = stockMapperReport.getEndingBalanceMms113_spares(date);
    } else if (type.equalsIgnoreCase("105_uniform_excel")) {
      data_stock_ = stockMapperReport.reportUniform(date);
      data_stock_mms = stockMapperReport.reportMmsUniform(date);
      stock__total =
        stockMapperReport.getEndingBalance105_uniform(date);
      stock_mms_total = stockMapperReport.getEndingBalanceMms105_uniform(date);
    } else if (type.equalsIgnoreCase("119_accessory_excel")) {
      data_stock_ = stockMapperReport.reportAccessory(date);
      data_stock_mms = stockMapperReport.reportMmsAccessory(date);
      stock__total =
        stockMapperReport.getEndingBalance119_accessory(date);
      stock_mms_total =
        stockMapperReport.getEndingBalanceMms119_accessory(date);
    } else if (type.equalsIgnoreCase("120_check_excel")) {
      data_stock_ = stockMapperReport.reportCheck(date);
      data_stock_mms = stockMapperReport.reportMmsCheck(date);
      stock__total = stockMapperReport.getEndingBalance120_check(date);
      stock_mms_total = stockMapperReport.getEndingBalanceMms120_check(date);
    } else if (type.equalsIgnoreCase("112_sanitory_excel")) {
      data_stock_ = stockMapperReport.reportSanitory(date);
      data_stock_mms = stockMapperReport.reportMmsSanitory(date);
      stock__total =
        stockMapperReport.getEndingBalance112_sanitory(date);
      stock_mms_total = stockMapperReport.getEndingBalanceMms112_sanitory(date);
    } else if (type.equalsIgnoreCase("106_computer_excel")) {
      data_stock_ = stockMapperReport.reportComputer(date);
      data_stock_mms = stockMapperReport.reportMmsComputer(date);
      stock__total =
        stockMapperReport.getEndingBalance106_computer(date);
      stock_mms_total = stockMapperReport.getEndingBalanceMms106_computer(date);
    } else if (type.equalsIgnoreCase("107_furniture_excel")) {
      data_stock_ = stockMapperReport.reportFurniture(date);
      data_stock_mms = stockMapperReport.reportMmsFurniture(date);
      stock__total =
        stockMapperReport.getEndingBalance107_furniture(date);
      stock_mms_total =
        stockMapperReport.getEndingBalanceMms107_furniture(date);
    } else if (type.equalsIgnoreCase("104_office_equipment_excel")) {
      data_stock_ = stockMapperReport.reportOfficeEquipment(date);
      data_stock_mms = stockMapperReport.reportMmsOfficeEquipment(date);
      stock__total =
        stockMapperReport.getEndingBalance104_office_equipment(date);
      stock_mms_total =
        stockMapperReport.getEndingBalanceMms104_office_equipment(date);
    }

    //		PDFGeneratorSTOCK generator = new PDFGeneratorSTOCK();

    ByteArrayInputStream in = ExcelHelperSTOCK.GenerateExcelStock(
      date,
      type,
      data_stock_,
      data_stock_mms,
      stock__total,
      stock_mms_total
    );

    //		InputStreamResource file = new InputStreamResource(in);
    InputStream targetStream = in;

    SimpleDateFormat formatter = new SimpleDateFormat(
      "yyyy-MM-dd",
      Locale.ENGLISH
    );
    Date date2 = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date2);
    String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

    final String DIRECTORY2 =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/";

    final String DIRECTORY =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/" +
      "REPORT As of " +
      monthName +
      " " +
      date2.getDate() +
      ", " +
      date2.toString().split(" ")[5] +
      ".xls";
    File file_path = new File(StringUtils.join(DIRECTORY2));

    if (!file_path.exists()) {
      file_path.mkdirs();
    }

    File filee = new File(DIRECTORY);
    // commons-io
    FileUtils.copyInputStreamToFile(targetStream, filee);
    return DIRECTORY;
  }

  public String exportPdfSTOCK(String date, String type)
    throws IOException, DocumentException, ParseException, WriterException {
    List<StockReport> data_stock_ = null;
    List<StockReport> data_stock_mms = null;
    Double stock__total = 0d;
    Double stock_mms_total = 0d;

    if (type.equalsIgnoreCase("121_stationary_pdf")) {
      data_stock_ = stockMapperReport.reportStationary(date);
      data_stock_mms = stockMapperReport.reportMmsStationary(date);
      stock__total =
        stockMapperReport.getEndingBalance121_stationary(date);
      stock_mms_total =
        stockMapperReport.getEndingBalanceMms121_stationary(date);
    } else if (type.equalsIgnoreCase("111_tools_pdf")) {
      data_stock_ = stockMapperReport.reportTools(date);
      data_stock_mms = stockMapperReport.reportMmsTools(date);
      stock__total = stockMapperReport.getEndingBalance111_tools(date);
      stock_mms_total = stockMapperReport.getEndingBalanceMms111_tools(date);
    } else if (type.equalsIgnoreCase("113_spares_pdf")) {
      data_stock_ = stockMapperReport.reportSpares(date);
      data_stock_mms = stockMapperReport.reportMmsSpares(date);
      stock__total = stockMapperReport.getEndingBalance113_spares(date);
      stock_mms_total = stockMapperReport.getEndingBalanceMms113_spares(date);
    } else if (type.equalsIgnoreCase("105_uniform_pdf")) {
      data_stock_ = stockMapperReport.reportUniform(date);
      data_stock_mms = stockMapperReport.reportMmsUniform(date);
      stock__total =
        stockMapperReport.getEndingBalance105_uniform(date);
      stock_mms_total = stockMapperReport.getEndingBalanceMms105_uniform(date);
    } else if (type.equalsIgnoreCase("119_accessory_pdf")) {
      data_stock_ = stockMapperReport.reportAccessory(date);
      data_stock_mms = stockMapperReport.reportMmsAccessory(date);
      stock__total =
        stockMapperReport.getEndingBalance119_accessory(date);
      stock_mms_total =
        stockMapperReport.getEndingBalanceMms119_accessory(date);
    } else if (type.equalsIgnoreCase("120_check_pdf")) {
      data_stock_ = stockMapperReport.reportCheck(date);
      data_stock_mms = stockMapperReport.reportMmsCheck(date);
      stock__total = stockMapperReport.getEndingBalance120_check(date);
      stock_mms_total = stockMapperReport.getEndingBalanceMms120_check(date);
    } else if (type.equalsIgnoreCase("112_sanitory_pdf")) {
      data_stock_ = stockMapperReport.reportSanitory(date);
      data_stock_mms = stockMapperReport.reportMmsSanitory(date);
      stock__total =
        stockMapperReport.getEndingBalance112_sanitory(date);
      stock_mms_total = stockMapperReport.getEndingBalanceMms112_sanitory(date);
    } else if (type.equalsIgnoreCase("106_computer_pdf")) {
      data_stock_ = stockMapperReport.reportComputer(date);
      data_stock_mms = stockMapperReport.reportMmsComputer(date);
      stock__total =
        stockMapperReport.getEndingBalance106_computer(date);
      stock_mms_total = stockMapperReport.getEndingBalanceMms106_computer(date);
    } else if (type.equalsIgnoreCase("107_furniture_pdf")) {
      data_stock_ = stockMapperReport.reportFurniture(date);
      data_stock_mms = stockMapperReport.reportMmsFurniture(date);
      stock__total =
        stockMapperReport.getEndingBalance107_furniture(date);
      stock_mms_total =
        stockMapperReport.getEndingBalanceMms107_furniture(date);
    } else if (type.equalsIgnoreCase("104_office_equipment_pdf")) {
      data_stock_ = stockMapperReport.reportOfficeEquipment(date);
      data_stock_mms = stockMapperReport.reportMmsOfficeEquipment(date);
      stock__total =
        stockMapperReport.getEndingBalance104_office_equipment(date);
      stock_mms_total =
        stockMapperReport.getEndingBalanceMms104_office_equipment(date);
    }

    PDFGeneratorSTOCK generator = new PDFGeneratorSTOCK();

    return generator.generate(
      date,
      type,
      data_stock_,
      data_stock_mms,
      stock__total,
      stock_mms_total
    );
  }

  //	public ResponseEntity<Resource> downloadReportFiles(HttpServletRequest request, String date_type) {
  //		try {
  //			if (util.isPermitted(request, "User", "download_report_files")) {
  //				util.registerActivity(request, "download  report files", "-");
  ////				generate
  //				String path = "";
  //				if (date_type.split("-_-")[1].equalsIgnoreCase("pdf")) {
  //					if (atsMaper.existsFile(date_type.split("-_-")[0])) {
  //						path = exportPdfFile(date_type.split("-_-")[0]);
  //					} else
  //						throw new ExceptionsList(new CustomAllException(""));
  //				} else if (date_type.split("-_-")[1].equalsIgnoreCase("excel")) {
  //					if (atsMaper.existsFile(date_type.split("-_-")[0])) {
  //						path = exportExcelFile(date_type.split("-_-")[0]);
  //					} else
  //						throw new ExceptionsList(new CustomAllException(""));
  ////				} else if (date_type.split("-_-")[1].equalsIgnoreCase("payable_pdf")) {
  ////					if (atsMaper.existsFilePayable(date_type.split("-_-")[0])) {
  //////						path = exportExcelFile(date_type.split("-_-")[0]);
  ////					} else
  ////						throw new ExceptionsList(new CustomAllException(""));
  ////				} else if (date_type.split("-_-")[1].equalsIgnoreCase("payable_excel")) {
  ////					if (atsMaper.existsFilePayable(date_type.split("-_-")[0])) {
  //////						path = exportExcelFile(date_type.split("-_-")[0]);
  ////					} else
  ////						throw new ExceptionsList(new CustomAllException(""));
  ////				} else if (date_type.split("-_-")[1].equalsIgnoreCase("receivable_pdf")) {
  ////					if (atsMaper.existsFileReceivable(date_type.split("-_-")[0])) {
  //////						path = exportExcelFile(date_type.split("-_-")[0]);
  ////					} else
  ////						throw new ExceptionsList(new CustomAllException(""));
  ////				} else if (date_type.split("-_-")[1].equalsIgnoreCase("receivable_excel")) {
  ////					if (atsMaper.existsFileReceivable(date_type.split("-_-")[0])) {
  //////						path = exportExcelFile(date_type.split("-_-")[0]);
  ////					} else
  //						throw new ExceptionsList(new CustomAllException(""));
  //				} else if (date_type.split("-_-")[1].equalsIgnoreCase("issue_pdf")) {
  //					if (atsMaper.existsFileIssue(date_type.split("-_-")[0])) {
  //						path = exportPdfFileIssue(date_type.split("-_-")[0]);
  //					} else
  //						throw new ExceptionsList(new CustomAllException(""));
  //				} else if (date_type.split("-_-")[1].equalsIgnoreCase("issue_excel")) {
  //					if (atsMaper.existsFileIssue(date_type.split("-_-")[0])) {
  //						path = exportPdfFileIssue(date_type.split("-_-")[0]);
  //					} else
  //						throw new ExceptionsList(new CustomAllException(""));
  ////				} else if (date_type.split("-_-")[1].equalsIgnoreCase("fixed_pdf")) {
  ////					if (atsMaper.existsFileFixed(date_type.split("-_-")[0])) {
  //////						path = exportExcelFile(date_type.split("-_-")[0]);
  ////					} else
  ////						throw new ExceptionsList(new CustomAllException(""));
  ////				} else if (date_type.split("-_-")[1].equalsIgnoreCase("fixed_excel")) {
  ////					if (atsMaper.existsFileFixed(date_type.split("-_-")[0])) {
  //////						path = exportExcelFile(date_type.split("-_-")[0]);
  ////					} else
  ////						throw new ExceptionsList(new CustomAllException(""));
  //				}
  //
  //				Path filePath = Paths.get(path).toAbsolutePath().normalize().resolve(path);
  //				if (!Files.exists(filePath)) {
  //					throw new FileNotFoundException("The requested file could not be found on the server.");
  //				}
  //				Resource resource = new UrlResource(filePath.toUri());
  //				HttpHeaders httpHeaders = new HttpHeaders();
  //				httpHeaders.add("File-Name", path);
  //				if (date_type.split("-_-")[1].equalsIgnoreCase("pdf")) {
  //					httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
  //				} else {
  //					httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
  //				}
  //				return ResponseEntity.ok().headers(httpHeaders).body(resource);
  ////				return null;
  //			}
  //
  //			else {
  //				System.out.println("No user does not have permission.");
  //				return null;
  //			}
  //		} catch (Exception e) {
  //			throw new ExceptionsList(e);
  //		}
  //
  //	}

  public boolean doesNotExpridedToken(HttpServletRequest request) {
    try {
      if (util.isPermitted(request, "User", "get_all_uploaded_files")) {
        return true;
      } else {
        System.out.println("No user does not have permission.");
        return false;
      }
    } catch (Exception e) {
      throw new ExceptionsList(e);
    }
  }

  public String exportPdfFile(String date, String con, String ifb)
    throws IOException, DocumentException, ParseException, WriterException {
    double con_ending;
    double ifb_ending;
    System.out.println("extracting: reportDebit");
    List<File_rtgs__> data__debit = atsMaper.reportDebit(
      date.replace("-", "")
    );
    System.out.println("extracting: reportCredit");
    List<File_rtgs__> data__credit = atsMaper.reportCredit(
      date.replace("-", "")
    );
    System.out.println("extracting: reportAtsCredit");
    List<File_rtgs__ats> ats_credit_data = atsMaper.reportAtsCredit(
      date.replace("-", "")
    );
    System.out.println("extracting: reportAtsDebit");
    List<File_rtgs__ats> ats_debit_data = atsMaper.reportAtsDebit(
      date.replace("-", "")
    );
    System.out.println("generating: pdf");

    Map<String, Double> totalAmountsByDateOs = new HashMap<>();

    // iterate over the transactions in the list
    Iterator<File_rtgs__> iterator = data__debit.iterator();
    while (iterator.hasNext()) {
      File_rtgs__ t = iterator.next();
      // check if the name starts with 'is'
      if (t.getAdditional_information().toLowerCase().startsWith("os:")) {
        // if the name starts with 'is', add its amount to the total for the date
        Double totalAmountForDate = totalAmountsByDateOs.get(t.getValue_date());
        if (totalAmountForDate == null) {
          totalAmountForDate = 0.0;
        }
        totalAmountForDate += t.getAmount();
        totalAmountsByDateOs.put(t.getValue_date(), totalAmountForDate);
        // remove the transaction from the list
        iterator.remove();
      }
    }
    // add the total amounts to the existing transactions as new data
    for (Map.Entry<String, Double> entry : totalAmountsByDateOs.entrySet()) {
      File_rtgs__ newTransaction = new File_rtgs__();
      newTransaction.setAdditional_information(
        "Total OS as of " + entry.getKey()
      );
      newTransaction.setAmount(entry.getValue());
      newTransaction.setValue_date(entry.getKey());
      //	        	newTransaction.setBranch(entry.getKey());
      data__debit.add(newTransaction);
    }

    Map<String, Double> totalAmountsByDateIs = new HashMap<>();

    // iterate over the transactions in the list
    Iterator<File_rtgs__> iteratoris = data__credit.iterator();
    while (iteratoris.hasNext()) {
      File_rtgs__ t = iteratoris.next();
      // check if the name starts with 'is'
      if (t.getAdditional_information().toLowerCase().startsWith("is:")) {
        // if the name starts with 'is', add its amount to the total for the date
        Double totalAmountForDate = totalAmountsByDateIs.get(t.getValue_date());
        if (totalAmountForDate == null) {
          totalAmountForDate = 0.0;
        }
        totalAmountForDate += t.getAmount();
        totalAmountsByDateIs.put(t.getValue_date(), totalAmountForDate);
        // remove the transaction from the list
        iteratoris.remove();
      }
    }
    // add the total amounts to the existing transactions as new data
    for (Map.Entry<String, Double> entry : totalAmountsByDateIs.entrySet()) {
      File_rtgs__ newTransaction = new File_rtgs__();
      newTransaction.setAdditional_information(
        "Total IS as of " + entry.getKey()
      );
      newTransaction.setAmount(entry.getValue());
      newTransaction.setValue_date(entry.getKey());
      //		        	newTransaction.setBranch(entry.getKey());
      data__credit.add(newTransaction);
    }
    if (con.equalsIgnoreCase("1")) {
      con_ending = atsMaper.getConventionalEndingBalance(date);
    } else {
      con_ending = 0 - (atsMaper.getConventionalEndingBalance(date));
    }
    if (ifb.equalsIgnoreCase("1")) {
      ifb_ending = atsMaper.getIfbEndingBalance(date);
    } else {
      ifb_ending = 0 - (atsMaper.getIfbEndingBalance(date));
    }
    PDFGenerator generator = new PDFGenerator();
    return generator.generate(
      date,
      data__debit,
      atsMaper.getAtsEndingbalance(date),
      atsMaper.getTotalDebit(date.replace("-", "")),
      data__credit,
      atsMaper.getTotalCredit(date.replace("-", "")),
      con_ending,
      ifb_ending,
      ats_credit_data,
      atsMaper.getAtsTotalCredit(date.replace("-", "")),
      ats_debit_data,
      atsMaper.getAtsTotalDebit(date.replace("-", ""))
    );
    //        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(data__debit, rtgsMapper.getAtsEndingbalance(date),
    //        		rtgsMapper.getTotalDebit(date.replace("-", "")), data__credit, rtgsMapper.getTotalCredit(date.replace("-", "")),
    //        		rtgsMapper.getConventionalEndingBalance(date), rtgsMapper.getIfbEndingBalance(date),
    //        		ats_credit_data, rtgsMapper.getAtsTotalCredit(date.replace("-", "")), ats_debit_data, rtgsMapper.getAtsTotalDebit(date.replace("-", "")));
    //        InputStreamResource file = new InputStreamResource(in);
    //        InputStream targetStream = in;
    //        File filee = new File("D:\\"+ new Date().getMinutes() + new Date().getSeconds()+ "google2.xlsx");
    //        FileUtils.copyInputStreamToFile(targetStream, filee);
  }

  public String exportPdfFilePayable(String date)
    throws IOException, DocumentException, ParseException, WriterException {
    System.out.println("extracting: reportPayableDebit");
    List<File_rtgs__> data_payable_debit = atsMaper.reportPayableDebit(
      date.replace("-", "")
    );
    System.out.println("extracting: reportPayableCredit");
    List<File_rtgs__> data_payable_credit = atsMaper.reportPayableCredit(
      date.replace("-", "")
    );
    System.out.println("finished: reportPayableCredit" + data_payable_credit);
    //		List<File_rtgs__ats> ats_credit_data = atsMaper.reportAtsCredit(date.replace("-", ""));
    //		System.out.println("extracting: reportAtsDebit" );
    //		List<File_rtgs__ats> ats_debit_data = atsMaper.reportAtsDebit(date.replace("-", ""));
    System.out.println("generating: pdf");
    PDFGeneratorPayable generator = new PDFGeneratorPayable();

    return generator.generate(
      date,
      data_payable_debit,
      atsMaper.getPayableEndingbalance(date),
      atsMaper.getTotalPayableDebit(date.replace("-", "")),
      data_payable_credit,
      atsMaper.getTotalPayableCredit(date.replace("-", ""))
    );
    //				atsMaper.getIfbEndingBalance(date), ats_credit_data, atsMaper.getAtsTotalCredit(date.replace("-", "")),
    //				ats_debit_data, atsMaper.getAtsTotalDebit(date.replace("-", "")));
    //        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(data__debit, rtgsMapper.getAtsEndingbalance(date),
    //        		rtgsMapper.getTotalDebit(date.replace("-", "")), data__credit, rtgsMapper.getTotalCredit(date.replace("-", "")),
    //        		rtgsMapper.getConventionalEndingBalance(date), rtgsMapper.getIfbEndingBalance(date),
    //        		ats_credit_data, rtgsMapper.getAtsTotalCredit(date.replace("-", "")), ats_debit_data, rtgsMapper.getAtsTotalDebit(date.replace("-", "")));
    //        InputStreamResource file = new InputStreamResource(in);
    //        InputStream targetStream = in;
    //        File filee = new File("D:\\"+ new Date().getMinutes() + new Date().getSeconds()+ "google2.xlsx");
    //        FileUtils.copyInputStreamToFile(targetStream, filee);
  }

  public String exportPdfFileReceivable(String date)
    throws IOException, DocumentException, ParseException, WriterException {
    System.out.println("extracting: reportReceivableDebit");
    List<File_rtgs__> data_receivable_debit = mapperReceivable.reportReceivableDebit(
      date.replace("-", "")
    );
    System.out.println("extracting: reportReceivableCredit");
    List<File_rtgs__> data_receivable_credit = mapperReceivable.reportReceivableCredit(
      date.replace("-", "")
    );
    System.out.println(
      "finished: reportReceivableCredit" + data_receivable_credit
    );
    //		List<File_rtgs__ats> ats_credit_data = atsMaper.reportAtsCredit(date.replace("-", ""));
    //		System.out.println("extracting: reportAtsDebit" );
    //		List<File_rtgs__ats> ats_debit_data = atsMaper.reportAtsDebit(date.replace("-", ""));
    System.out.println("generating: pdf");
    PDFGeneratorReceivable generator = new PDFGeneratorReceivable();

    return generator.generate(
      date,
      data_receivable_debit,
      mapperReceivable.getReceivableEndingbalance(date),
      mapperReceivable.getTotalReceivableDebit(date.replace("-", "")),
      data_receivable_credit,
      mapperReceivable.getTotalReceivableCredit(date.replace("-", ""))
    );
    //				atsMaper.getIfbEndingBalance(date), ats_credit_data, atsMaper.getAtsTotalCredit(date.replace("-", "")),
    //				ats_debit_data, atsMaper.getAtsTotalDebit(date.replace("-", "")));
    //        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(data__debit, rtgsMapper.getAtsEndingbalance(date),
    //        		rtgsMapper.getTotalDebit(date.replace("-", "")), data__credit, rtgsMapper.getTotalCredit(date.replace("-", "")),
    //        		rtgsMapper.getConventionalEndingBalance(date), rtgsMapper.getIfbEndingBalance(date),
    //        		ats_credit_data, rtgsMapper.getAtsTotalCredit(date.replace("-", "")), ats_debit_data, rtgsMapper.getAtsTotalDebit(date.replace("-", "")));
    //        InputStreamResource file = new InputStreamResource(in);
    //        InputStream targetStream = in;
    //        File filee = new File("D:\\"+ new Date().getMinutes() + new Date().getSeconds()+ "google2.xlsx");
    //        FileUtils.copyInputStreamToFile(targetStream, filee);
  }

  public String exportExcelFile(String date, String con, String ifb)
    throws IOException, ParseException, WriterException {
    double con_ending;
    double ifb_ending;
    System.out.println("The almighty DATE: " + date.replace("-", ""));
    List<File_rtgs__> data__debit = atsMaper.reportDebit(
      date.replace("-", "")
    );
    System.out.println("The almighty DATE: " + date.replace("-", ""));
    List<File_rtgs__> data__credit = atsMaper.reportCredit(
      date.replace("-", "")
    );
    System.out.println("The almighty DATE: " + date.replace("-", ""));
    List<File_rtgs__ats> ats_credit_data = atsMaper.reportAtsCredit(
      date.replace("-", "")
    );
    System.out.println("The almighty DATE: " + date.replace("-", ""));
    List<File_rtgs__ats> ats_debit_data = atsMaper.reportAtsDebit(
      date.replace("-", "")
    );
    System.out.println("The almighty DATE: " + date.replace("-", ""));

    Map<String, Double> totalAmountsByDateOs = new HashMap<>();

    // iterate over the transactions in the list
    Iterator<File_rtgs__> iterator = data__debit.iterator();
    while (iterator.hasNext()) {
      File_rtgs__ t = iterator.next();
      // check if the name starts with 'is'
      if (t.getAdditional_information().toLowerCase().startsWith("os:")) {
        // if the name starts with 'is', add its amount to the total for the date
        Double totalAmountForDate = totalAmountsByDateOs.get(t.getValue_date());
        if (totalAmountForDate == null) {
          totalAmountForDate = 0.0;
        }
        totalAmountForDate += t.getAmount();
        totalAmountsByDateOs.put(t.getValue_date(), totalAmountForDate);
        // remove the transaction from the list
        iterator.remove();
      }
    }
    // add the total amounts to the existing transactions as new data
    for (Map.Entry<String, Double> entry : totalAmountsByDateOs.entrySet()) {
      File_rtgs__ newTransaction = new File_rtgs__();
      newTransaction.setAdditional_information(
        "Total OS as of " + entry.getKey()
      );
      newTransaction.setAmount(entry.getValue());
      newTransaction.setValue_date(entry.getKey());
      //	        	newTransaction.setBranch(entry.getKey());
      data__debit.add(newTransaction);
    }

    Map<String, Double> totalAmountsByDateIs = new HashMap<>();

    // iterate over the transactions in the list
    Iterator<File_rtgs__> iteratoris = data__credit.iterator();
    while (iteratoris.hasNext()) {
      File_rtgs__ t = iteratoris.next();
      // check if the name starts with 'is'
      if (t.getAdditional_information().toLowerCase().startsWith("is:")) {
        // if the name starts with 'is', add its amount to the total for the date
        Double totalAmountForDate = totalAmountsByDateIs.get(t.getValue_date());
        if (totalAmountForDate == null) {
          totalAmountForDate = 0.0;
        }
        totalAmountForDate += t.getAmount();
        totalAmountsByDateIs.put(t.getValue_date(), totalAmountForDate);
        // remove the transaction from the list
        iteratoris.remove();
      }
    }

    // add the total amounts to the existing transactions as new data
    for (Map.Entry<String, Double> entry : totalAmountsByDateIs.entrySet()) {
      File_rtgs__ newTransaction = new File_rtgs__();
      newTransaction.setAdditional_information(
        "Total IS as of " + entry.getKey()
      );
      newTransaction.setAmount(entry.getValue());
      newTransaction.setValue_date(entry.getKey());
      //		        	newTransaction.setBranch(entry.getKey());
      data__credit.add(newTransaction);
    }
    if (con.equalsIgnoreCase("1")) {
      con_ending = atsMaper.getConventionalEndingBalance(date);
    } else {
      con_ending = 0 - (atsMaper.getConventionalEndingBalance(date));
    }
    if (ifb.equalsIgnoreCase("1")) {
      ifb_ending = atsMaper.getIfbEndingBalance(date);
    } else {
      ifb_ending = 0 - (atsMaper.getIfbEndingBalance(date));
    }

    ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(
      date,
      data__debit,
      atsMaper.getAtsEndingbalance(date),
      atsMaper.getTotalDebit(date.replace("-", "")),
      data__credit,
      atsMaper.getTotalCredit(date.replace("-", "")),
      con_ending,
      ifb_ending,
      ats_credit_data,
      atsMaper.getAtsTotalCredit(date.replace("-", "")),
      ats_debit_data,
      atsMaper.getAtsTotalDebit(date.replace("-", ""))
    );
    // InputStreamResource file = new InputStreamResource(in);
    InputStream targetStream = in;

    SimpleDateFormat formatter = new SimpleDateFormat(
      "yyyy-MM-dd",
      Locale.ENGLISH
    );
    Date date2 = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date2);
    String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

    final String DIRECTORY2 =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/";

    final String DIRECTORY =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/" +
      "REPORT As of " +
      monthName +
      " " +
      date2.getDate() +
      ", " +
      date2.toString().split(" ")[5] +
      ".xls";
    File file_path = new File(StringUtils.join(DIRECTORY2));

    if (!file_path.exists()) {
      file_path.mkdirs();
    }

    File filee = new File(DIRECTORY);
    // commons-io
    FileUtils.copyInputStreamToFile(targetStream, filee);
    return DIRECTORY;
  }

  // public ResponseEntity<Resource> downloadReportFilesIssue(HttpServletRequest
  // request, String date_type) {
  //		try {
  //			if (util.isPermitted(request, "User", "download_report_files")) {
  //				util.registerActivity(request, "download  report files", "-");
  ////				generate
  //				String path = "";
  //				if (atsMaper.existsFile(date_type.split("-_-")[0]))
  ////					System.out.println("it exists");
  //					if (date_type.split("-_-")[1].equalsIgnoreCase("pdf")) {
  //						path = exportPdfFileIssue(date_type.split("-_-")[0]);
  //					} else {
  //						path = exportExcelFileIssue(date_type.split("-_-")[0]);
  //					}
  //				else
  //					throw new ExceptionsList(new CustomAllException(""));
  //
  //				Path filePath = Paths.get(path).toAbsolutePath().normalize().resolve(path);
  //				if (!Files.exists(filePath)) {
  //					throw new FileNotFoundException("The requested file could not be found on the server.");
  //				}
  //				Resource resource = new UrlResource(filePath.toUri());
  //				HttpHeaders httpHeaders = new HttpHeaders();
  //				httpHeaders.add("File-Name", path);
  //				if (date_type.split("-_-")[1].equalsIgnoreCase("pdf")) {
  //					httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
  //				} else {
  //					httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/vnd.ms-excel");
  //				}
  //				return ResponseEntity.ok().headers(httpHeaders).body(resource);
  ////				return null;
  //			}
  //
  //			else {
  //				System.out.println("No user does not have permission.");
  //				return null;
  //			}
  //		} catch (Exception e) {
  //			throw new ExceptionsList(e);
  //		}
  //
  //	}

  public String exportPdfFileIssue(String date)
    throws IOException, DocumentException, ParseException, WriterException {
    System.out.println("extracting: reportDebit");
    List<File_rtgs__> data__debit = mapperIssue.reportDebit(
      date.replace("-", "")
    );
    System.out.println("extracting: reportCreditSetteled");
    List<File_rtgs__> data__debit_setteled = mapperIssue.reportDebitSetteled(
      date.replace("-", "")
    );
    System.out.println("extracting: reportCredit");
    List<File_rtgs__> data__credit = mapperIssue.reportCredit(
      date.replace("-", "")
    );
    System.out.println("extracting: reportCreditSetteled");
    List<File_rtgs__> data__credit_setteled = mapperIssue.reportCreditSetteled(
      date.replace("-", "")
    );
    System.out.println("extracting: reportAtsCredit");
    List<File_rtgs__ats> ats_credit_data = mapperIssue.reportQbsCredit(
      date.replace("-", "")
    );
    System.out.println("extracting: reportQbsCreditSetteled");
    List<File_rtgs__ats> ats_credit_data_setteled = mapperIssue.reportQbsCreditSetteled(
      date.replace("-", "")
    );
    System.out.println("extracting: reportAtsDebit");
    List<File_rtgs__ats> ats_debit_data = mapperIssue.reportQbsDebit(
      date.replace("-", "")
    );
    System.out.println("extracting: reportQbsDebitSetteled");
    List<File_rtgs__ats> ats_debit_data_setteled = mapperIssue.reportQbsDebitSetteled(
      date.replace("-", "")
    );
    System.out.println("generating: pdf");
    PDFGeneratorIssue generator = new PDFGeneratorIssue();

    return generator.generate(
      date,
      data__debit,
      data__debit_setteled,
      mapperIssue.getQbsEndingbalance(date),
      mapperIssue.getTotalDebit(date.replace("-", "")),
      mapperIssue.getTotalDebitSetteled(date.replace("-", "")),
      data__credit,
      data__credit_setteled,
      mapperIssue.getTotalCredit(date.replace("-", "")),
      mapperIssue.getTotalCreditSetteled(date.replace("-", "")),
      // mapperIssue.getConventionalEndingBalance(date),
      mapperIssue.getIfbEndingBalance(date),
      ats_credit_data,
      ats_credit_data_setteled,
      mapperIssue.getQbsTotalCredit(date.replace("-", "")),
      mapperIssue.getQbsTotalCreditSetteled(date.replace("-", "")),
      ats_debit_data,
      ats_debit_data_setteled,
      mapperIssue.getQbsTotalDebit(date.replace("-", "")),
      mapperIssue.getQbsTotalDebitSetteled(date.replace("-", ""))
    );
    //        ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(data__debit, rtgsMapper.getAtsEndingbalance(date),
    //        		rtgsMapper.getTotalDebit(date.replace("-", "")), data__credit, rtgsMapper.getTotalCredit(date.replace("-", "")),
    //        		rtgsMapper.getConventionalEndingBalance(date), rtgsMapper.getIfbEndingBalance(date),
    //        		ats_credit_data, rtgsMapper.getAtsTotalCredit(date.replace("-", "")), ats_debit_data, rtgsMapper.getAtsTotalDebit(date.replace("-", "")));
    //        InputStreamResource file = new InputStreamResource(in);
    //        InputStream targetStream = in;
    //        File filee = new File("D:\\"+ new Date().getMinutes() + new Date().getSeconds()+ "google2.xlsx");
    //        FileUtils.copyInputStreamToFile(targetStream, filee);
  }

  public String exportPdfFileFixedFurniture(String date, String type)
    throws IOException, DocumentException, ParseException, WriterException {
    System.out.println("extracting: report");
    List<Fixed__report> data__debit = fixedMapper.reportDebitFurn(
      date
    );
    System.out.println("extracting: report");
    List<Fixed__report> data__credit = fixedMapper.reportCreditFurn(
      date
    );
    System.out.println("extracting: reportAtsCredit");
    List<Fixed_mms_report> mms_data = fixedMapper.reportMMSFurn(date);
    System.out.println("extracting: reportAtsDebit");
    //		List<Fixed_mms_report> mms_debit_data = fixedMapper.reportMMSDebitFurn(date);

    System.out.println("generating: pdf");
    PDFGeneratorFixed generator = new PDFGeneratorFixed();
    System.out.println(
      "this is the bfub balanceeeeeeeeeeeeeeeeeeeeeeeeeeeeee" +
      fixedMapper.getConvFurniture(date)
    );

    return generator.generateFixedPDF(
      date,
      type,
      data__debit,
      data__credit,
      fixedMapper.getTotalFurn(date),
      mms_data,
      fixedMapper.getMMSTotalFurn(date),
      fixedMapper.getConvFurniture(date),
      fixedMapper.getIfbFurniture(date),
      fixedMapper.getWaitingFurniture(waiting_date),
      fixedMapper.getDisposedFurniture(disposed_date),
      fixedMapper.getRemovedFurniture(removed_date),
      fixedMapper.getMmsFurniture(date)
    );
  }

  public String exportPdfFileFixedComputer(String date, String type)
    throws IOException, DocumentException, ParseException, WriterException {
    System.out.println("extracting: report");
    List<Fixed__report> data__debit = fixedMapper.reportDebitComp(
      date
    );
    System.out.println("extracting: report");
    List<Fixed__report> data__credit = fixedMapper.reportCreditComp(
      date
    );
    System.out.println("extracting: reportAtsCredit");
    List<Fixed_mms_report> mms_data = fixedMapper.reportMMSComp(date);
    System.out.println("extracting: reportAtsDebit");
    //		List<Fixed_mms_report> mms_debit_data = fixedMapper.reportMMSDebitComp(date);
    System.out.println("generating: pdf");
    PDFGeneratorFixed generator = new PDFGeneratorFixed();

    return generator.generateFixedPDF(
      date,
      type,
      data__debit,
      data__credit,
      fixedMapper.getTotalComp(date),
      mms_data,
      fixedMapper.getMMSTotalComp(date),
      fixedMapper.getConvComputer(date),
      fixedMapper.getIfbComputer(date),
      fixedMapper.getWaitingComputer(waiting_date),
      fixedMapper.getDisposedComputer(disposed_date),
      fixedMapper.getRemovedComputer(removed_date),
      fixedMapper.getMmsComputer(date)
    );
  }

  public String exportPdfFileFixedMotorVehicle(String date, String type)
    throws IOException, DocumentException, ParseException, WriterException {
    System.out.println("extracting: report");
    List<Fixed__report> data__debit = fixedMapper.reportDebitMotor(
      date
    );
    System.out.println("extracting: report");
    List<Fixed__report> data__credit = fixedMapper.reportCreditMotor(
      date
    );
    System.out.println("extracting: reportAtsCredit");
    List<Fixed_mms_report> mms_data = fixedMapper.reportMMSMotor(date);
    System.out.println("extracting: reportAtsDebit");
    //		List<Fixed_mms_report> mms_debit_data = fixedMapper.reportMMSDebitMotor(date);
    System.out.println("generating: pdf");
    PDFGeneratorFixed generator = new PDFGeneratorFixed();

    return generator.generateFixedPDF(
      date,
      type,
      data__debit,
      data__credit,
      fixedMapper.getTotalMotor(date),
      mms_data,
      fixedMapper.getMMSTotalMotor(date),
      fixedMapper.getConvVehicle(date),
      fixedMapper.getIfbVehicle(date),
      fixedMapper.getWaitingVehicle(waiting_date),
      fixedMapper.getDisposedVehicle(disposed_date),
      fixedMapper.getRemovedVehicle(removed_date),
      fixedMapper.getMmsVehicle(date)
    );
  }

  public String exportPdfFileFixedOfficeEquipment(String date, String type)
    throws IOException, DocumentException, ParseException, WriterException {
    System.out.println("extracting: report");
    List<Fixed__report> data__debit = fixedMapper.reportDebitOfficeEqipment(
      date
    );
    System.out.println("extracting: report");
    List<Fixed__report> data__credit = fixedMapper.reportCreditOfficeEqipment(
      date
    );
    System.out.println("extracting: reportAtsCredit");
    List<Fixed_mms_report> mms_data = fixedMapper.reportMMSCreditOfficeEqipment(
      date
    );
    System.out.println("extracting: reportAtsDebit");
    //		List<Fixed_mms_report> mms_debit_data = fixedMapper.reportMMSDebitOfficeEqipment(date);
    System.out.println("generating: pdf");
    PDFGeneratorFixed generator = new PDFGeneratorFixed();

    return generator.generateFixedPDF(
      date,
      type,
      data__debit,
      data__credit,
      fixedMapper.getTotalOfficeEqipment(date),
      mms_data,
      fixedMapper.getMMSTotalOfficeEqipment(date),
      fixedMapper.getConvEquipment(date),
      fixedMapper.getIfbEquipment(date),
      fixedMapper.getWaitingEquipment(waiting_date),
      fixedMapper.getDisposedEquipment(disposed_date),
      fixedMapper.getRemovedEquipment(removed_date),
      fixedMapper.getMmsEquipment(date)
    );
  }

  public String exportExcelFileFixedFurniture(String date, String type)
    throws IOException, ParseException, WriterException {
    List<Fixed__report> data__debit = fixedMapper.reportDebitFurn(
      date
    );
    System.out.println("extracting: report");
    List<Fixed__report> data__credit = fixedMapper.reportCreditFurn(
      date
    );
    System.out.println("extracting: reportAtsCredit");
    List<Fixed_mms_report> mms_data = fixedMapper.reportMMSFurn(date);
    System.out.println("extracting: reportAtsDebit");
    //		List<Fixed_mms_report> mms_debit_data = fixedMapper.reportMMSDebitFurn(date);
    ByteArrayInputStream in = ExcelHelperFixed.GenerateExcelFixed(
      date,
      type,
      data__debit,
      data__credit,
      fixedMapper.getTotalFurn(date),
      mms_data,
      fixedMapper.getMMSTotalFurn(date),
      fixedMapper.getConvFurniture(date),
      fixedMapper.getIfbFurniture(date),
      fixedMapper.getWaitingFurniture(waiting_date),
      fixedMapper.getDisposedFurniture(disposed_date),
      fixedMapper.getRemovedFurniture(removed_date),
      fixedMapper.getMmsFurniture(date)
    );
    InputStreamResource file = new InputStreamResource(in);
    InputStream targetStream = in;

    SimpleDateFormat formatter = new SimpleDateFormat(
      "yyyy-MM-dd",
      Locale.ENGLISH
    );
    Date date2 = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date2);
    String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

    final String DIRECTORY2 =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/";

    final String DIRECTORY =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/" +
      "REPORT As of " +
      monthName +
      " " +
      date2.getDate() +
      ", " +
      date2.toString().split(" ")[5] +
      ".xls";
    File file_path = new File(StringUtils.join(DIRECTORY2));

    if (!file_path.exists()) {
      file_path.mkdirs();
    }

    File filee = new File(DIRECTORY);
    // commons-io
    FileUtils.copyInputStreamToFile(targetStream, filee);
    return DIRECTORY;
  }

  public String exportExcelFileFixedComputer(String date, String type)
    throws IOException, ParseException, WriterException {
    List<Fixed__report> data__debit = fixedMapper.reportDebitComp(
      date
    );
    System.out.println("extracting: report");
    List<Fixed__report> data__credit = fixedMapper.reportCreditComp(
      date
    );
    System.out.println("extracting: reportAtsCredit");
    List<Fixed_mms_report> mms_data = fixedMapper.reportMMSComp(date);
    System.out.println("extracting: reportAtsDebit");
    //		List<Fixed_mms_report> mms_debit_data = fixedMapper.reportMMSDebitComp(date);
    ByteArrayInputStream in = ExcelHelperFixed.GenerateExcelFixed(
      date,
      type,
      data__debit,
      data__credit,
      fixedMapper.getTotalComp(date),
      mms_data,
      fixedMapper.getMMSTotalComp(date),
      fixedMapper.getConvComputer(date),
      fixedMapper.getIfbComputer(date),
      fixedMapper.getWaitingComputer(waiting_date),
      fixedMapper.getDisposedComputer(disposed_date),
      fixedMapper.getRemovedComputer(removed_date),
      fixedMapper.getMmsComputer(date)
    );
    InputStreamResource file = new InputStreamResource(in);
    InputStream targetStream = in;

    SimpleDateFormat formatter = new SimpleDateFormat(
      "yyyy-MM-dd",
      Locale.ENGLISH
    );
    Date date2 = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date2);
    String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

    final String DIRECTORY2 =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/";

    final String DIRECTORY =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/" +
      "REPORT As of " +
      monthName +
      " " +
      date2.getDate() +
      ", " +
      date2.toString().split(" ")[5] +
      ".xls";
    File file_path = new File(StringUtils.join(DIRECTORY2));

    if (!file_path.exists()) {
      file_path.mkdirs();
    }

    File filee = new File(DIRECTORY);
    // commons-io
    FileUtils.copyInputStreamToFile(targetStream, filee);
    return DIRECTORY;
  }

  public String exportExcelFileFixedMotorVehicle(String date, String type)
    throws IOException, ParseException, WriterException {
    List<Fixed__report> data__debit = fixedMapper.reportDebitMotor(
      date
    );
    System.out.println("extracting: report");
    List<Fixed__report> data__credit = fixedMapper.reportCreditMotor(
      date
    );
    System.out.println("extracting: reportAtsCredit");
    List<Fixed_mms_report> mms_data = fixedMapper.reportMMSMotor(date);
    System.out.println("extracting: reportAtsDebit");
    //		List<Fixed_mms_report> mms_debit_data = fixedMapper.reportMMSDebitMotor(date);
    ByteArrayInputStream in = ExcelHelperFixed.GenerateExcelFixed(
      date,
      type,
      data__debit,
      data__credit,
      fixedMapper.getTotalMotor(date),
      mms_data,
      fixedMapper.getMMSTotalMotor(date),
      fixedMapper.getConvVehicle(date),
      fixedMapper.getIfbVehicle(date),
      fixedMapper.getWaitingVehicle(waiting_date),
      fixedMapper.getDisposedVehicle(disposed_date),
      fixedMapper.getRemovedVehicle(removed_date),
      fixedMapper.getMmsVehicle(date)
    );
    InputStreamResource file = new InputStreamResource(in);
    InputStream targetStream = in;

    SimpleDateFormat formatter = new SimpleDateFormat(
      "yyyy-MM-dd",
      Locale.ENGLISH
    );
    Date date2 = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date2);
    String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

    final String DIRECTORY2 =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/";

    final String DIRECTORY =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/" +
      "REPORT As of " +
      monthName +
      " " +
      date2.getDate() +
      ", " +
      date2.toString().split(" ")[5] +
      ".xls";
    File file_path = new File(StringUtils.join(DIRECTORY2));

    if (!file_path.exists()) {
      file_path.mkdirs();
    }

    File filee = new File(DIRECTORY);
    // commons-io
    FileUtils.copyInputStreamToFile(targetStream, filee);
    return DIRECTORY;
  }

  public String exportExcelFileFixedOfficeEquipment(String date, String type)
    throws IOException, ParseException, WriterException {
    List<Fixed__report> data__debit = fixedMapper.reportDebitOfficeEqipment(
      date
    );
    System.out.println("extracting: report");
    List<Fixed__report> data__credit = fixedMapper.reportCreditOfficeEqipment(
      date
    );
    System.out.println("extracting: reportAtsCredit");
    List<Fixed_mms_report> mms_data = fixedMapper.reportMMSCreditOfficeEqipment(
      date
    );
    System.out.println("extracting: reportAtsDebit");
    //		List<Fixed_mms_report> mms_debit_data = fixedMapper.reportMMSDebitOfficeEqipment(date);
    ByteArrayInputStream in = ExcelHelperFixed.GenerateExcelFixed(
      date,
      type,
      data__debit,
      data__credit,
      fixedMapper.getTotalOfficeEqipment(date),
      mms_data,
      fixedMapper.getMMSTotalOfficeEqipment(date),
      fixedMapper.getConvEquipment(date),
      fixedMapper.getIfbEquipment(date),
      fixedMapper.getWaitingEquipment(waiting_date),
      fixedMapper.getDisposedEquipment(disposed_date),
      fixedMapper.getRemovedEquipment(removed_date),
      fixedMapper.getMmsEquipment(date)
    );

    InputStreamResource file = new InputStreamResource(in);
    InputStream targetStream = in;

    SimpleDateFormat formatter = new SimpleDateFormat(
      "yyyy-MM-dd",
      Locale.ENGLISH
    );
    Date date2 = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date2);
    String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

    final String DIRECTORY2 =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/";

    final String DIRECTORY =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/" +
      "REPORT As of " +
      monthName +
      " " +
      date2.getDate() +
      ", " +
      date2.toString().split(" ")[5] +
      ".xls";
    File file_path = new File(StringUtils.join(DIRECTORY2));

    if (!file_path.exists()) {
      file_path.mkdirs();
    }

    File filee = new File(DIRECTORY);
    // commons-io
    FileUtils.copyInputStreamToFile(targetStream, filee);
    return DIRECTORY;
  }

  public String exportExcelFileIssue(String date)
    throws IOException, ParseException, WriterException {
    List<File_rtgs__> data__debit = mapperIssue.reportDebit(
      date.replace("-", "")
    );
    List<File_rtgs__> data__debit_setteled = mapperIssue.reportDebitSetteled(
      date.replace("-", "")
    );
    List<File_rtgs__> data__credit = mapperIssue.reportCredit(
      date.replace("-", "")
    );
    List<File_rtgs__> data__credit_setteled = mapperIssue.reportCreditSetteled(
      date.replace("-", "")
    );
    List<File_rtgs__ats> ats_credit_data = mapperIssue.reportQbsCredit(
      date.replace("-", "")
    );
    List<File_rtgs__ats> ats_credit_data_setteled = mapperIssue.reportQbsCreditSetteled(
      date.replace("-", "")
    );
    List<File_rtgs__ats> ats_debit_data = mapperIssue.reportQbsDebit(
      date.replace("-", "")
    );
    List<File_rtgs__ats> ats_debit_data_setteled = mapperIssue.reportQbsDebitSetteled(
      date.replace("-", "")
    );
    ByteArrayInputStream in = ExcelHelperIssue.tutorialsToExcel(
      date,
      data__debit,
      data__debit_setteled,
      mapperIssue.getQbsEndingbalance(date),
      mapperIssue.getTotalDebit(date.replace("-", "")),
      mapperIssue.getTotalDebitSetteled(date.replace("-", "")),
      data__credit,
      data__credit_setteled,
      mapperIssue.getTotalCredit(date.replace("-", "")),
      mapperIssue.getTotalCreditSetteled(date.replace("-", "")),
      // mapperIssue.getConventionalEndingBalance(date),
      mapperIssue.getIfbEndingBalance(date),
      ats_credit_data,
      ats_credit_data_setteled,
      mapperIssue.getQbsTotalCredit(date.replace("-", "")),
      mapperIssue.getQbsTotalCreditSetteled(date.replace("-", "")),
      ats_debit_data,
      ats_debit_data_setteled,
      mapperIssue.getQbsTotalDebit(date.replace("-", "")),
      mapperIssue.getQbsTotalDebitSetteled(date.replace("-", ""))
    );

    InputStreamResource file = new InputStreamResource(in);
    InputStream targetStream = in;

    SimpleDateFormat formatter = new SimpleDateFormat(
      "yyyy-MM-dd",
      Locale.ENGLISH
    );
    Date date2 = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date2);
    String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

    final String DIRECTORY2 =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/";

    final String DIRECTORY =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/" +
      "REPORT As of " +
      monthName +
      " " +
      date2.getDate() +
      ", " +
      date2.toString().split(" ")[5] +
      ".xls";
    File file_path = new File(StringUtils.join(DIRECTORY2));

    if (!file_path.exists()) {
      file_path.mkdirs();
    }

    File filee = new File(DIRECTORY);
    // commons-io
    FileUtils.copyInputStreamToFile(targetStream, filee);
    return DIRECTORY;
  }

  public String exportExcelFilePayable(String date)
    throws IOException, ParseException, WriterException {
    System.out.println("extracting: reportPayableDebit");
    List<File_rtgs__> data_payable_debit = atsMaper.reportPayableDebit(
      date.replace("-", "")
    );
    System.out.println("extracting: reportPayableCredit");
    List<File_rtgs__> data_payable_credit = atsMaper.reportPayableCredit(
      date.replace("-", "")
    );
    System.out.println("finished: reportPayableCredit" + data_payable_credit);
    ByteArrayInputStream in = ExcelHelperPayable.tutorialsToExcelPayable(
      date,
      data_payable_debit,
      atsMaper.getPayableEndingbalance(date),
      atsMaper.getTotalPayableDebit(date.replace("-", "")),
      data_payable_credit,
      atsMaper.getTotalPayableCredit(date.replace("-", ""))
    );
    InputStreamResource file = new InputStreamResource(in);
    InputStream targetStream = in;

    SimpleDateFormat formatter = new SimpleDateFormat(
      "yyyy-MM-dd",
      Locale.ENGLISH
    );
    Date date2 = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date2);
    String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

    final String DIRECTORY2 =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/";

    final String DIRECTORY =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/" +
      "REPORT As of " +
      monthName +
      " " +
      date2.getDate() +
      ", " +
      date2.toString().split(" ")[5] +
      ".xls";
    File file_path = new File(StringUtils.join(DIRECTORY2));

    if (!file_path.exists()) {
      file_path.mkdirs();
    }

    File filee = new File(DIRECTORY);
    // commons-io
    FileUtils.copyInputStreamToFile(targetStream, filee);
    return DIRECTORY;
  }

  public String exportExcelFilePayableRaw(String date)
    throws IOException, ParseException, WriterException {
    //		System.out.println("extracting: reportPayableDebit");
    //		List<File_rtgs__> data_payable_debit = atsMaper.reportPayableDebit(date.replace("-", ""));
    //		System.out.println("extracting: reportPayableCredit");
    // List<File_rtgs__> Payable_raw_data_all = null;
    ArrayList<File_rtgs__> Payable_raw_data_all = new ArrayList<File_rtgs__>();
    // <File_rtgs__> Payable_raw_data_unmatched =
    // atsMaper.PayableRawData(date.replace("-", ""));
    List<File_rtgs__> Payable_raw_data_unmatched = atsMaper.PayableRawData_unmatched(
      date.replace("-", "")
    );
    List<File_rtgs__> Payable_raw_data_matched_cr = atsMaper.PayableRawData_matched_cr(
      date.replace("-", "")
    );
    List<File_rtgs__> Payable_raw_data_matched_dr = atsMaper.PayableRawData_matched_dr(
      date.replace("-", "")
    );

    for (File_rtgs__ data_payable : Payable_raw_data_unmatched) {
      System.out.println("ctrrrr sizeeeeeeeee" + data_payable.getCtr());
    }
    for (File_rtgs__ data_payable : Payable_raw_data_matched_cr) {
      System.out.println("ctrrrr sizeeeeeeeeeyyy" + data_payable.getCtr());
    }
    for (File_rtgs__ data_payable : Payable_raw_data_matched_dr) {
      System.out.println("ctrrrr sizeeeeeeeeezz" + data_payable.getCtr());
    }

    for (File_rtgs__ data_payable : Payable_raw_data_unmatched) {
      Payable_raw_data_all.add(data_payable);
      System.out.println(
        "Payable_raw_data_all sizeeeeeeeee" + Payable_raw_data_all.size()
      );
    }

    for (File_rtgs__ data_payable : Payable_raw_data_matched_cr) {
      File_rtgs__ aa = new File_rtgs__();
      //			aa.setAdditional_information(data_payable.getAdditional_information());
      Payable_raw_data_all.add(data_payable);
    }

    for (File_rtgs__ data_payable : Payable_raw_data_matched_dr) {
      File_rtgs__ aa = new File_rtgs__();
      //			aa.setAdditional_information(data_payable.getAdditional_information());
      Payable_raw_data_all.add(data_payable);
    }

    Collections.sort(
      Payable_raw_data_all,
      new Comparator<File_rtgs__>() {
        @Override
        public int compare(File_rtgs__ file1, File_rtgs__ file2) {
          int ctr1 = Integer.parseInt(file1.getCtr());
          int ctr2 = Integer.parseInt(file2.getCtr());

          return Integer.compare(ctr1, ctr2);
        }
      }
    );

    //         System.out.println("data payable listttttttttttt: "+ Payable_raw_data_unmatched.get(0).getCtr().getClass().getName());
    // Define a custom comparator that sorts by the ctr field

    //         Comparator<File_rtgs__> byCtr = Comparator.comparingLong(File_rtgs__ -> Long.parseLong(Payable_raw_data_unmatched.get(0).getCtr()));
    //         System.out.println("ctr valuesssssssssss: "+ byCtr);
    //
    //		//Comparator<File_rtgs__> byCtr = Comparator.comparingDouble(Long.parseLong(Payable_raw_data_unmatched.get(0).getCtr()));
    //
    //		// Sort the list using the custom comparator
    //		Collections.sort(Payable_raw_data_all, byCtr);

    Double beginning_balance = atsMaper.beggingBalancePayableFromFile(date);
    //		System.out.println("posting dazsdasdasdasdasdasdasdqsdata: " + Payable_raw_data.get(0).getPosting_date());
    // System.out.println("finished: reportPayableCredit" + data_payable_credit);
    System.out.println("dateeeeeeeeeeeeeeeeeee:" + date);
    ByteArrayInputStream in = ExcelHelperPayableRawData.tutorialsToExcelPayable(
      date,
      Payable_raw_data_all,
      beginning_balance
    );
    InputStreamResource file = new InputStreamResource(in);
    InputStream targetStream = in;
    SimpleDateFormat formatter = new SimpleDateFormat(
      "yyyy-MM-dd",
      Locale.ENGLISH
    );
    Date date2 = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date2);
    String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

    final String DIRECTORY2 =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/";

    final String DIRECTORY =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/" +
      "REPORT As of " +
      monthName +
      " " +
      date2.getDate() +
      ", " +
      date2.toString().split(" ")[5] +
      ".xls";
    File file_path = new File(StringUtils.join(DIRECTORY2));

    if (!file_path.exists()) {
      file_path.mkdirs();
    }

    File filee = new File(DIRECTORY);
    // commons-io
    FileUtils.copyInputStreamToFile(targetStream, filee);
    return DIRECTORY;
  }

  public String exportExcelFileRecievableRaw(String date)
    throws IOException, ParseException, WriterException {
    //		System.out.println("extracting: reportPayableDebit");
    //		List<File_rtgs__> data_payable_debit = atsMaper.reportPayableDebit(date.replace("-", ""));
    //		System.out.println("extracting: reportPayableCredit");
    // List<File_rtgs__> Payable_raw_data_all = null;
    ArrayList<File_rtgs__> Recievable_raw_data_all = new ArrayList<File_rtgs__>();
    // <File_rtgs__> Payable_raw_data_unmatched =
    // atsMaper.PayableRawData(date.replace("-", ""));
    List<File_rtgs__> Recievable_raw_data_unmatched = atsMaper.RecievableRawData_unmatched(
      date.replace("-", "")
    );
    List<File_rtgs__> Reciavable_raw_data_matched_cr = atsMaper.RecievableRawData_matched_cr(
      date.replace("-", "")
    );
    List<File_rtgs__> Reciavable_raw_data_matched_dr = atsMaper.RecievableRawData_matched_dr(
      date.replace("-", "")
    );

    for (File_rtgs__ data_payable : Recievable_raw_data_unmatched) {
      Recievable_raw_data_all.add(data_payable);
    }
    for (File_rtgs__ data_payable : Reciavable_raw_data_matched_cr) {
      File_rtgs__ aa = new File_rtgs__();
      Recievable_raw_data_all.add(data_payable);
    }

    for (File_rtgs__ data_payable : Reciavable_raw_data_matched_dr) {
      File_rtgs__ aa = new File_rtgs__();
      Recievable_raw_data_all.add(data_payable);
    }

    Collections.sort(
      Recievable_raw_data_all,
      new Comparator<File_rtgs__>() {
        @Override
        public int compare(File_rtgs__ file1, File_rtgs__ file2) {
          int ctr1 = Integer.parseInt(file1.getCtr());
          int ctr2 = Integer.parseInt(file2.getCtr());

          return Integer.compare(ctr1, ctr2);
        }
      }
    );

    DecimalFormat df = new DecimalFormat("#.##");
    System.out.println(
      "fffffffffffffffff: " +
      df.format(atsMaper.beggingBalanceReceivableFromFile(date))
    );
    System.out.println("fffffffffffffffff: ");

    double beginning_balance = atsMaper.beggingBalanceReceivableFromFile(date);
    System.out.println("fffffffffffffffff: " + beginning_balance);
    // double beginning_balance_recievable =
    // atsMaper.beginningBalanceRecievable(date);

    //		System.out.println("posting dazsdasdasdasdasdasdasdqsdata: " + Payable_raw_data.get(0).getPosting_date());
    // System.out.println("finished: reportPayableCredit" + data_payable_credit);

    ByteArrayInputStream in = ExcelHelperRecievableRaw.tutorialsToExcelRecievable(
      date,
      Recievable_raw_data_all,
      beginning_balance
    );
    InputStreamResource file = new InputStreamResource(in);
    InputStream targetStream = in;
    SimpleDateFormat formatter = new SimpleDateFormat(
      "yyyy-MM-dd",
      Locale.ENGLISH
    );
    Date date2 = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date2);
    String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

    final String DIRECTORY2 =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/";

    final String DIRECTORY =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/" +
      "REPORT As of " +
      monthName +
      " " +
      date2.getDate() +
      ", " +
      date2.toString().split(" ")[5] +
      ".xls";
    File file_path = new File(StringUtils.join(DIRECTORY2));

    if (!file_path.exists()) {
      file_path.mkdirs();
    }

    File filee = new File(DIRECTORY);
    // commons-io
    FileUtils.copyInputStreamToFile(targetStream, filee);
    return DIRECTORY;
  }

  public String exportExcelFileReceivable(String date)
    throws IOException, ParseException, WriterException {
    System.out.println("extracting: reportReceivableDebit");
    List<File_rtgs__> data_receivable_debit = mapperReceivable.reportReceivableDebit(
      date.replace("-", "")
    );
    System.out.println("extracting: reportReceivableCredit");
    List<File_rtgs__> data_receivable_credit = mapperReceivable.reportReceivableCredit(
      date.replace("-", "")
    );
    System.out.println(
      "finished: reportReceivableCredit" + data_receivable_credit
    );
    ByteArrayInputStream in = ExcelHelperReceivable.tutorialsToExcelReceivable(
      date,
      data_receivable_debit,
      mapperReceivable.getReceivableEndingbalance(date),
      mapperReceivable.getTotalReceivableDebit(date.replace("-", "")),
      data_receivable_credit,
      mapperReceivable.getTotalReceivableCredit(date.replace("-", ""))
    );
    InputStreamResource file = new InputStreamResource(in);
    InputStream targetStream = in;

    SimpleDateFormat formatter = new SimpleDateFormat(
      "yyyy-MM-dd",
      Locale.ENGLISH
    );
    Date date2 = formatter.parse(date);
    Calendar cal = Calendar.getInstance();
    cal.setTime(date2);
    String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());

    final String DIRECTORY2 =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/";

    final String DIRECTORY =
      System.getProperty("user.dir") +
      "/src/main/resources/static/generated_reports/" +
      date.substring(0, 7) +
      "/" +
      "REPORT As of " +
      monthName +
      " " +
      date2.getDate() +
      ", " +
      date2.toString().split(" ")[5] +
      ".xls";
    File file_path = new File(StringUtils.join(DIRECTORY2));

    if (!file_path.exists()) {
      file_path.mkdirs();
    }

    File filee = new File(DIRECTORY);
    // commons-io
    FileUtils.copyInputStreamToFile(targetStream, filee);
    return DIRECTORY;
  }
}
