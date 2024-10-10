package com.example.demo.abebayehu.entity;

public class Fixed__report {
	private Long id;
    private String transaction_date;
	private String account_description;
	private String naration;
	private String account_name;
	private String amount;
	Fixed__report(Long id, String transaction_date, String account_description, String naration,
			String account_name, String amount) {
		super();
		this.id = id;
		this.transaction_date = transaction_date;
		this.account_description = account_description;
		this.naration = naration;
		this.account_name = account_name;
		this.amount = amount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getAccount_description() {
		return account_description;
	}
	public void setAccount_description(String account_description) {
		this.account_description = account_description;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	
}
