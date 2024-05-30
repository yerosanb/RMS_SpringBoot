package com.example.demo.garama.models;

public class fixedCoreReversal {
	private Long id;
    private String account_number;
    private String transaction_date;
    private String posting_date;
    private String value_date;
    private float credit;
    private float debit;
    private String branch_code;
    private String reference;
    private String naration;
    private String account_description;
    private String account_name;
    private String match_id;
    private String match_date;
    private String firstname;
	public fixedCoreReversal(Long id, String account_number, String transaction_date, String posting_date,
			String value_date, float credit, float debit, String branch_code, String reference, String naration,
			String account_description, String account_name, String match_id, String match_date, String firstname) {
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
		this.match_id = match_id;
		this.match_date = match_date;
		this.firstname = firstname;
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
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public float getDebit() {
		return debit;
	}
	public void setDebit(float debit) {
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
    
}
