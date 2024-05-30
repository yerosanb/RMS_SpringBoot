package com.example.demo.abebayehu.entity;

public class view_fixed_core_deleted {
	private Long id;
//    private String account_number;
//    private String transaction_date;
//    private String posting_date;
    private String value_date;
    private double credit;
    private double debit;
	private String branch_code;
//	private String reference;
	private String naration;
//	private String account_description;
	private String account_name;
    private String  edit_delete;
//    private String  availability;
//    private String  match_status;
view_fixed_core_deleted(Long id, String value_date, double credit, double debit, String branch_code, String naration,
		String account_name, String edit_delete) {
	super();
	this.id = id;
	this.value_date = value_date;
	this.credit = credit;
	this.debit = debit;
	this.branch_code = branch_code;
	this.naration = naration;
	this.account_name = account_name;
	this.edit_delete = edit_delete;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
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
public String getNaration() {
	return naration;
}
public void setNaration(String naration) {
	this.naration = naration;
}
public String getAccount_name() {
	return account_name;
}
public void setAccount_name(String account_name) {
	this.account_name = account_name;
}
	
public String getEdit_delete() {
	return edit_delete;
}
public void setEdit_delete(String edit_delete) {
	this.edit_delete = edit_delete;
}
}
