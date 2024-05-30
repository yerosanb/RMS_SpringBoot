package com.example.demo.abebayehu.entity;

public class Fixed_mms_report {
	
	private Long id;
    private String created_date;
    private String asset_description;
	private String tag_number;
    private String branch_name;
    private double amount;
	Fixed_mms_report(Long id, String created_date, String asset_description, String tag_number, String branch_name,
			double amount) {
		super();
		this.id = id;
		this.created_date = created_date;
		this.asset_description = asset_description;
		this.tag_number = tag_number;
		this.branch_name = branch_name;
		this.amount = amount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getAsset_description() {
		return asset_description;
	}
	public void setAsset_description(String asset_description) {
		this.asset_description = asset_description;
	}
	public String getTag_number() {
		return tag_number;
	}
	public void setTag_number(String tag_number) {
		this.tag_number = tag_number;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	

}
