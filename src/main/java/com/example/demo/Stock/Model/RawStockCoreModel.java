package com.example.demo.Stock.Model;

public class RawStockCoreModel {
	private Long id;
	private String transaction_reference;
	private String transaction_date;
	private String posting_date;
	private String value_date;
	private float amount;
	private float balance;
	private String source_branch;
	private String branch_name;
	private String description;
	private String dr_cr;
	private String account;
	private String status;
	private String availability;
	private Long file_id;
	private String match_id;
	private String match_date;
	private String firstname;
	private String lastname;
	private String reason;
	public RawStockCoreModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public RawStockCoreModel(Long id, String transaction_reference, String transaction_date, String posting_date,
			String value_date, float amount, float balance, String source_branch, String branch_name,
			String description, String dr_cr, String account, String status, String availability, Long file_id,
			String match_id, String match_date, String firstname, String lastname, String reason) {
		super();
		this.id = id;
		this.transaction_reference = transaction_reference;
		this.transaction_date = transaction_date;
		this.posting_date = posting_date;
		this.value_date = value_date;
		this.amount = amount;
		this.balance = balance;
		this.source_branch = source_branch;
		this.branch_name = branch_name;
		this.description = description;
		this.dr_cr = dr_cr;
		this.account = account;
		this.status = status;
		this.availability = availability;
		this.file_id = file_id;
		this.match_id = match_id;
		this.match_date = match_date;
		this.firstname = firstname;
		this.lastname = lastname;
		this.reason = reason;
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
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
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
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
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
	public Long getFile_id() {
		return file_id;
	}
	public void setFile_id(Long file_id) {
		this.file_id = file_id;
	}
	public String getMatch_id() {
		return match_id;
	}
	public void setMatch_id(String match_id) {
		this.match_id = match_id;
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
	

    
}
