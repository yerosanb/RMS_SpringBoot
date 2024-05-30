package com.example.demo.garama.models;

public class FixedMmsWaiting {
	private Long id;
	private String unit_cost;
	private String purchase_date;
	private String main_pg;
	private String old_main_pg;
	private String created_date;
	private String asset_id;
	private String tag_number;
	private String quantity;
	private String asset_description;
	private String original_cost;
	private String book_value;
	private String branch_name;
	private String grv_number;
	private String giv_number;
	private String siv_date;
	private String status;
	private String availability;
	public FixedMmsWaiting() {
	}

	public FixedMmsWaiting(Long id, String unit_cost, String purchase_date, String main_pg, String old_main_pg,
			String created_date, String asset_id, String tag_number, String quantity, String asset_description,
			String original_cost, String book_value, String branch_name, String grv_number, String giv_number,
			String siv_date, String status, String availability) {
		super();
		this.id = id;
		this.unit_cost = unit_cost;
		this.purchase_date = purchase_date;
		this.main_pg = main_pg;
		this.old_main_pg = old_main_pg;
		this.created_date = created_date;
		this.asset_id = asset_id;
		this.tag_number = tag_number;
		this.quantity = quantity;
		this.asset_description = asset_description;
		this.original_cost = original_cost;
		this.book_value = book_value;
		this.branch_name = branch_name;
		this.grv_number = grv_number;
		this.giv_number = giv_number;
		this.siv_date = siv_date;
		this.status = status;
		this.availability = availability;
	}

	public String getUnit_cost() {
		return unit_cost;
	}
	public void setUnit_cost(String unit_cost) {
		this.unit_cost = unit_cost;
	}
	public String getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}
	public String getMain_pg() {
		return main_pg;
	}
	public void setMain_pg(String main_pg) {
		this.main_pg = main_pg;
	}
	public String getOld_main_pg() {
		return old_main_pg;
	}
	public void setOld_main_pg(String old_main_pg) {
		this.old_main_pg = old_main_pg;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
	public String getTag_number() {
		return tag_number;
	}
	public void setTag_number(String tag_number) {
		this.tag_number = tag_number;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getAsset_description() {
		return asset_description;
	}
	public void setAsset_description(String asset_description) {
		this.asset_description = asset_description;
	}
	public String getOriginal_cost() {
		return original_cost;
	}
	public void setOriginal_cost(String original_cost) {
		this.original_cost = original_cost;
	}
	public String getBook_value() {
		return book_value;
	}
	public void setBook_value(String book_value) {
		this.book_value = book_value;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getGrv_number() {
		return grv_number;
	}
	public void setGrv_number(String grv_number) {
		this.grv_number = grv_number;
	}
	public String getGiv_number() {
		return giv_number;
	}
	public void setGiv_number(String giv_number) {
		this.giv_number = giv_number;
	}
	public String getSiv_date() {
		return siv_date;
	}
	public void setSiv_date(String siv_date) {
		this.siv_date = siv_date;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
