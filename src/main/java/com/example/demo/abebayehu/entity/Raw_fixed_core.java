package com.example.demo.abebayehu.entity;

public class Raw_fixed_ {  
	private Long id;
    private String account_number;
    private String transaction_date;
    private String posting_date;
    private String value_date;
    private double credit;
    private double debit;
	private String branch_code;
	private String reference;
	private String naration;
	private String account_description;
	private String account_name;
    private String  status;
    private String  availability;
    private String  match_status;

	Raw_fixed_(Long id, String account_number, String transaction_date, String posting_date, String value_date,
			double credit, double debit, String branch_code, String reference, String naration,

			String account_description, String account_name, String status, String availability, String match_status) {
		super();
		this.id = id;
		this.account_number = account_number;
		this.transaction_date = transaction_date;
		this.posting_date = posting_date;
		this.value_date = value_date;
		this.credit = credit;
		this.debit = debit;
		this.branch_code = branch_code;
		this.reference = reference;
		this.naration = naration;
		this.account_description = account_description;
		this.account_name = account_name;
		this.status = status;
		this.availability = availability;
		this.match_status = match_status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
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
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getNaration() {
		return naration;
	}
	public void setNaration(String naration) {
		this.naration = naration;
	}
	public String getAccount_description() {
		return account_description;
	}
	public void setAccount_description(String account_description) {
		this.account_description = account_description;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
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
    
    
}

