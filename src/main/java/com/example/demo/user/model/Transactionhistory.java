package com.example.demo.user.model;

public class Transactionhistory {

	private Long id;
	private Long _id;
	private String value_date_type;
	private String reference;
	private String sender;
	private String receiver;
	private String additional_information;
	private Double amount;
	private String dr_cr;
	private Long upload_date;
	private String match_status;
	private String status;
	private String availability;
	private Long file_id;
	private String match_id;
	private Long match_date;
	private String firstname;
	private String middlename;
	private String lastname;
	private String posting_date;
	private String value_date;
	private String transaction_reference;
	private String branch_code;
	private String account_name;
	private String store_code;
	private String transaction_date;
	private Long period;
	private float debit;
	private float credit;
	private String store_name;
	private String category_description;
	private String tran_code;
	private String stock_account_segment;
	private String naration;
	private String account_number;
	private String type;
	private String reconciliation_type;
	private String description;
	private String source_branch;
    private String stock_date;

	private String account_description;
	private String reason;
	private String bbf;
	private String main_pg;

	public Transactionhistory() {

	}

	public Transactionhistory(Long id, Long _id, String value_date_type, String reference, String sender,
			String receiver, String additional_information, Double amount, String dr_cr, Long upload_date,
			String match_status, String status, String availability, Long file_id, String match_id, Long match_date,
			String firstname, String middlename, String lastname, String posting_date, String value_date,
			String transaction_reference, String branch_code, String account_name, String store_code,
			String transaction_date, Long period, float debit, float credit, String store_name,
			String category_description, String tran_code, String stock_account_segment, String naration,
			String account_number, String type, String reconciliation_type, String description, String source_branch,
			String stock_date, String account_description, String reason, String bbf, String main_pg) {
		super();
		this.id = id;
		this._id = _id;
		this.value_date_type = value_date_type;
		this.reference = reference;
		this.sender = sender;
		this.receiver = receiver;
		this.additional_information = additional_information;
		this.amount = amount;
		this.dr_cr = dr_cr;
		this.upload_date = upload_date;
		this.match_status = match_status;
		this.status = status;
		this.availability = availability;
		this.file_id = file_id;
		this.match_id = match_id;
		this.match_date = match_date;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.posting_date = posting_date;
		this.value_date = value_date;
		this.transaction_reference = transaction_reference;
		this.branch_code = branch_code;
		this.account_name = account_name;
		this.store_code = store_code;
		this.transaction_date = transaction_date;
		this.period = period;
		this.debit = debit;
		this.credit = credit;
		this.store_name = store_name;
		this.category_description = category_description;
		this.tran_code = tran_code;
		this.stock_account_segment = stock_account_segment;
		this.naration = naration;
		this.account_number = account_number;
		this.type = type;
		this.reconciliation_type = reconciliation_type;
		this.description = description;
		this.source_branch = source_branch;
		this.stock_date = stock_date;
		this.account_description = account_description;
		this.reason = reason;
		this.bbf = bbf;
		this.main_pg = main_pg;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public String getValue_date_type() {
		return value_date_type;
	}

	public void setValue_date_type(String value_date_type) {
		this.value_date_type = value_date_type;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getAdditional_information() {
		return additional_information;
	}

	public void setAdditional_information(String additional_information) {
		this.additional_information = additional_information;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDr_cr() {
		return dr_cr;
	}

	public void setDr_cr(String dr_cr) {
		this.dr_cr = dr_cr;
	}

	public Long getUpload_date() {
		return upload_date;
	}

	public void setUpload_date(Long upload_date) {
		this.upload_date = upload_date;
	}

	public String getMatch_status() {
		return match_status;
	}

	public void setMatch_status(String match_status) {
		this.match_status = match_status;
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

	public Long getMatch_date() {
		return match_date;
	}

	public void setMatch_date(Long match_date) {
		this.match_date = match_date;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getTransaction_reference() {
		return transaction_reference;
	}

	public void setTransaction_reference(String transaction_reference) {
		this.transaction_reference = transaction_reference;
	}

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getStore_code() {
		return store_code;
	}

	public void setStore_code(String store_code) {
		this.store_code = store_code;
	}

	public String getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}

	public Long getPeriod() {
		return period;
	}

	public void setPeriod(Long period) {
		this.period = period;
	}

	public float getDebit() {
		return debit;
	}

	public void setDebit(float debit) {
		this.debit = debit;
	}

	public float getCredit() {
		return credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getCategory_description() {
		return category_description;
	}

	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}

	public String getTran_code() {
		return tran_code;
	}

	public void setTran_code(String tran_code) {
		this.tran_code = tran_code;
	}

	public String getStock_account_segment() {
		return stock_account_segment;
	}

	public void setStock_account_segment(String stock_account_segment) {
		this.stock_account_segment = stock_account_segment;
	}

	public String getNaration() {
		return naration;
	}

	public void setNaration(String naration) {
		this.naration = naration;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReconciliation_type() {
		return reconciliation_type;
	}

	public void setReconciliation_type(String reconciliation_type) {
		this.reconciliation_type = reconciliation_type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSource_branch() {
		return source_branch;
	}

	public void setSource_branch(String source_branch) {
		this.source_branch = source_branch;
	}

	public String getStock_date() {
		return stock_date;
	}

	public void setStock_date(String stock_date) {
		this.stock_date = stock_date;
	}

	public String getAccount_description() {
		return account_description;
	}

	public void setAccount_description(String account_description) {
		this.account_description = account_description;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBbf() {
		return bbf;
	}

	public void setBbf(String bbf) {
		this.bbf = bbf;
	}

	public String getMain_pg() {
		return main_pg;
	}

	public void setMain_pg(String main_pg) {
		this.main_pg = main_pg;
	}
	
}
 