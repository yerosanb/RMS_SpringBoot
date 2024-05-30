package com.example.demo.Stock.Model;

public class stockCoreDetail {
	private Long id;
	private String transaction_reference;
	private String posting_date;
	private String value_date;
	private float amount;
	private String source_branch;
	private String branch_name;
	private String description;
	private String dr_cr;
	private String account;
	private String  reason;
	private String  firstname;
	private String  lastname;
	private String  DATE;
	private String  edit_delete;
	private String edit_reason_id;
	private String new_old;
	
	public stockCoreDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public stockCoreDetail(Long id, String transaction_reference, String posting_date, String value_date, float amount,
			String source_branch, String branch_name, String description, String dr_cr, String account, String reason,
			String firstname, String lastname, String dATE, String edit_delete, String edit_reason_id,
			String new_old) {
		super();
		this.id = id;
		this.transaction_reference = transaction_reference;
		this.posting_date = posting_date;
		this.value_date = value_date;
		this.amount = amount;
		this.source_branch = source_branch;
		this.branch_name = branch_name;
		this.description = description;
		this.dr_cr = dr_cr;
		this.account = account;
		this.reason = reason;
		this.firstname = firstname;
		this.lastname = lastname;
		DATE = dATE;
		this.edit_delete = edit_delete;
		this.edit_reason_id = edit_reason_id;
		this.new_old = new_old;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTransaction_reference() {
		return transaction_reference;
	}
	public void setTransaction_reference(String transaction_reference) {
		this.transaction_reference = transaction_reference;
	}
	public String getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}
	public String getValue_date() {
		return value_date;
	}
	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getSource_branch() {
		return source_branch;
	}
	public void setSource_branch(String source_branch) {
		this.source_branch = source_branch;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDr_cr() {
		return dr_cr;
	}
	public void setDr_cr(String dr_cr) {
		this.dr_cr = dr_cr;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	public String getDATE() {
		return DATE;
	}
	public void setDATE(String dATE) {
		DATE = dATE;
	}
	public String getEdit_delete() {
		return edit_delete;
	}
	public void setEdit_delete(String edit_delete) {
		this.edit_delete = edit_delete;
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
