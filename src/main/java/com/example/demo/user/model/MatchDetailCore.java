package com.example.demo.user.model;

public class MatchDetailCore {
  private Long id;
  private String match_date;
  private String reconciliation_type;
  private String firstname;
  private String lastname;
  private double amount;
  private String reference;
  private String value_date_type;
  private String value_date;
  private String additional_information;
  
  
public MatchDetailCore() {
	super();
	// TODO Auto-generated constructor stub
}


public MatchDetailCore(Long id, String match_date, String reconciliation_type, String firstname, String lastname,
		double amount, String reference, String value_date_type, String value_date, String additional_information) {
	super();
	this.id = id;
	this.match_date = match_date;
	this.reconciliation_type = reconciliation_type;
	this.firstname = firstname;
	this.lastname = lastname;
	this.amount = amount;
	this.reference = reference;
	this.value_date_type = value_date_type;
	this.value_date = value_date;
	this.additional_information = additional_information;
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


public String getReference() {
	return reference;
}


public void setReference(String reference) {
	this.reference = reference;
}


public String getValue_date_type() {
	return value_date_type;
}


public void setValue_date_type(String value_date_type) {
	this.value_date_type = value_date_type;
}


public String getValue_date() {
	return value_date;
}


public void setValue_date(String value_date) {
	this.value_date = value_date;
}


public String getAdditional_information() {
	return additional_information;
}


public void setAdditional_information(String additional_information) {
	this.additional_information = additional_information;
}


@Override
public String toString() {
	return "MatchDetailCore [id=" + id + ", match_date=" + match_date + ", reconciliation_type=" + reconciliation_type
			+ ", firstname=" + firstname + ", lastname=" + lastname + ", amount=" + amount + ", reference=" + reference
			+ ", value_date_type=" + value_date_type + ", value_date=" + value_date + ", additional_information="
			+ additional_information + "]";
}

  
}
