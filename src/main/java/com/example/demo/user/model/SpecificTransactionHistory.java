package com.example.demo.user.model;

public class SpecificTransactionHistory {
	private String type;
	private String reference;
	private String branch_code;
	private String value_date;
	private float amount;
	public SpecificTransactionHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SpecificTransactionHistory(String type, String reference, String branch_code, String value_date,
			float amount) {
		super();
		this.type = type;
		this.reference = reference;
		this.branch_code = branch_code;
		this.value_date = value_date;
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
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
	@Override
	public String toString() {
		return "SpecificTransactionHistory [type=" + type + ", reference=" + reference + ", branch_code=" + branch_code
				+ ", value_date=" + value_date + ", amount=" + amount + "]";
	}
	
	

}
