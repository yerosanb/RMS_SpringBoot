package com.example.demo.user.model;

public class MatchDetailAts {
	private Long id;
	private String match_date;
	private String reconciliation_type;
	private String firstname;
	private String lastname;
	private double amount;
	private String additional_information;
	private String branch_code;
	
	public MatchDetailAts() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MatchDetailAts(Long id, String match_date, String reconciliation_type, String firstname, String lastname,
			double amount, String additional_information, String branch_code) {
		super();
		this.id = id;
		this.match_date = match_date;
		this.reconciliation_type = reconciliation_type;
		this.firstname = firstname;
		this.lastname = lastname;
		this.amount = amount;
		this.additional_information = additional_information;
		this.branch_code = branch_code;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatch_date() {
		return match_date;
	}
	public void setMatch_date(String match_date) {
		this.match_date = match_date;
	}
	public String getReconciliation_type() {
		return reconciliation_type;
	}
	public void setReconciliation_type(String reconciliation_type) {
		this.reconciliation_type = reconciliation_type;
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
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getAdditional_information() {
		return additional_information;
	}
	public void setAdditional_information(String additional_information) {
		this.additional_information = additional_information;
	}
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	@Override
	public String toString() {
		return "MatchDetailAts [id=" + id + ", match_date=" + match_date + ", reconciliation_type="
				+ reconciliation_type + ", firstname=" + firstname + ", lastname=" + lastname + ", amount=" + amount
				+ ", additional_information=" + additional_information + ", branch_code=" + branch_code + "]";
	}
	
	

}
