package com.example.demo.Stock.Model;

 
public class StockReport {
	
  	private String posting_date;
  	private String date;
  	private String description;
  	private String reference;
  	private double amount;
  	private String dr_cr;  	
  	private double balance;
  	
 	public StockReport() {
 		
 	}

	public StockReport(String posting_date, String date, String description, String reference, double amount,
			String dr_cr, double balance) {
		super();
		this.posting_date = posting_date;
		this.date = date;
		this.description = description;
		this.reference = reference;
		this.amount = amount;
		this.dr_cr = dr_cr;
		this.balance = balance;
	}

	public String getPosting_date() {
		return posting_date;
	}

	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDr_cr() {
		return dr_cr;
	}

	public void setDr_cr(String dr_cr) {
		this.dr_cr = dr_cr;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "StockReport [posting_date=" + posting_date + ", date=" + date + ", description=" + description
				+ ", reference=" + reference + ", amount=" + amount + ", dr_cr=" + dr_cr + ", balance=" + balance + "]";
	}

	
}
