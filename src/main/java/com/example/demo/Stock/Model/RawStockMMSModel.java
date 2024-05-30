package com.example.demo.Stock.Model;

public class RawStockMMSModel {
	private Long id;
	private String date;
	private String store_code;
	private String description;
	private String category_description;
	private String branch_name;
	private String account_segment;
	private String store_name;
	private Long period;
	private String transaction_code;
	private String reference;
	private double bbf;
	private double amount;
	private String main_pg;
	private String dr_cr;
	private String status;
	private String availability;
	private String match_status;
	private String match_id;
	private Long core_id;
	private String match_date;
	private String firstname;
	private String lastname;
	private String reason;
	private String edit_delete;
	private String edit_reason_id;
	private String new_old;
	
	public RawStockMMSModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RawStockMMSModel(Long id, String date, String store_code, String description, String category_description,
			String branch_name, String account_segment, String store_name, Long period, String transaction_code,
			String reference, double bbf, double amount, String main_pg, String dr_cr, String status,
			String availability, String match_status, String match_id, Long core_id, String match_date,
			String firstname, String lastname, String reason,String edit_reason_id, String new_old) {
		super();
		this.id = id;
		this.date = date;
		this.store_code = store_code;
		this.description = description;
		this.category_description = category_description;
		this.branch_name = branch_name;
		this.account_segment = account_segment;
		this.store_name = store_name;
		this.period = period;
		this.transaction_code = transaction_code;
		this.reference = reference;
		this.bbf = bbf;
		this.amount = amount;
		this.main_pg = main_pg;
		this.dr_cr = dr_cr;
		this.status = status;
		this.availability = availability;
		this.match_status = match_status;
		this.match_id = match_id;
		this.core_id = core_id;
		this.match_date = match_date;
		this.firstname = firstname;
		this.lastname = lastname;
		this.reason = reason;
		this.edit_reason_id = edit_reason_id;
		this.new_old = new_old;
	}
	
	public String getEdit_delete() {
		return edit_delete;
	}
	public void setEdit_delete(String edit_delete) {
		this.edit_delete = edit_delete;
	}
	public RawStockMMSModel(String edit_delete) {
		super();
		this.edit_delete = edit_delete;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStore_code() {
		return store_code;
	}
	public void setStore_code(String store_code) {
		this.store_code = store_code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory_description() {
		return category_description;
	}
	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getAccount_segment() {
		return account_segment;
	}
	public void setAccount_segment(String account_segment) {
		this.account_segment = account_segment;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public Long getPeriod() {
		return period;
	}
	public void setPeriod(Long period) {
		this.period = period;
	}
	public String getTransaction_code() {
		return transaction_code;
	}
	public void setTransaction_code(String transaction_code) {
		this.transaction_code = transaction_code;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public double getBbf() {
		return bbf;
	}
	public void setBbf(double bbf) {
		this.bbf = bbf;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getMain_pg() {
		return main_pg;
	}
	public void setMain_pg(String main_pg) {
		this.main_pg = main_pg;
	}
	public String getDr_cr() {
		return dr_cr;
	}
	public void setDr_cr(String dr_cr) {
		this.dr_cr = dr_cr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getMatch_status() {
		return match_status;
	}
	public void setMatch_status(String match_status) {
		this.match_status = match_status;
	}
	public String getMatch_id() {
		return match_id;
	}
	public void setMatch_id(String match_id) {
		this.match_id = match_id;
	}
	public Long getCore_id() {
		return core_id;
	}
	public void setCore_id(Long core_id) {
		this.core_id = core_id;
	}
	public String getMatch_date() {
		return match_date;
	}
	public void setMatch_date(String match_date) {
		this.match_date = match_date;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getEdit_reason_id() {
		return edit_reason_id;
	}
	public void setEdit_reason_id(String edit_reason_id) {
		this.edit_reason_id = edit_reason_id;
	}
	public String getNew_old() {
		return new_old;
	}
	public void setNew_old(String new_old) {
		this.new_old = new_old;
	}




}
