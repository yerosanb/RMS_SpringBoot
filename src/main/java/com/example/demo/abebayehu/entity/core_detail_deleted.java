package com.example.demo.abebayehu.entity;

public class _detail_deleted {
	private Long id;
	private String new_old;
    private String value_date;
    private double credit;
    private double debit;
	private String branch_code;
	private String naration;
	private String account_name;
    private String  reason;
    private String  firstname;
    private String  lastname;
    private String  DATE;
    private String  edit_delete;
    private String edit_reason_id;
	_detail_deleted(Long id, String new_old, String value_date, double credit, double debit, String branch_code,
			String naration, String account_name, String reason, String firstname, String lastname, String dATE,
			String edit_delete, String edit_reason_id) {
		super();
		this.id = id;
		this.new_old = new_old;
		this.value_date = value_date;
		this.credit = credit;
		this.debit = debit;
		this.branch_code = branch_code;
		this.naration = naration;
		this.account_name = account_name;
		this.reason = reason;
		this.firstname = firstname;
		this.lastname = lastname;
		DATE = dATE;
		this.edit_delete = edit_delete;
		this.edit_reason_id = edit_reason_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNew_old() {
		return new_old;
	}
	public void setNew_old(String new_old) {
		this.new_old = new_old;
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

}
