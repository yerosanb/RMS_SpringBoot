package com.example.demo.garama.models;

public class FixedMmsDisposed {
	private Long id;
	private String unit_cost;
	private String purchase_date;
	private String main_pg;
	private String old_main_pg;
	private String disposed_date;
	private String asset_id;
	private String tag_number;
	private String asset_description;
	private String book_value;
	private String branch_name;
	private String asset_code;
	private String disposed_by;
	private String status;
	private String availability;
	public FixedMmsDisposed() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FixedMmsDisposed(Long id, String unit_cost, String purchase_date, String main_pg, String old_main_pg,
			String disposed_date, String asset_id, String tag_number, String asset_description, String book_value,
			String branch_name, String asset_code, String disposed_by, String status, String availability) {
		super();
		this.id = id;
		this.unit_cost = unit_cost;
		this.purchase_date = purchase_date;
		this.main_pg = main_pg;
		this.old_main_pg = old_main_pg;
		this.disposed_date = disposed_date;
		this.asset_id = asset_id;
		this.tag_number = tag_number;
		this.asset_description = asset_description;
		this.book_value = book_value;
		this.branch_name = branch_name;
		this.asset_code = asset_code;
		this.disposed_by = disposed_by;
		this.status = status;
		this.availability = availability;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	public String getDisposed_date() {
		return disposed_date;
	}
	public void setDisposed_date(String disposed_date) {
		this.disposed_date = disposed_date;
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
	public String getAsset_description() {
		return asset_description;
	}
	public void setAsset_description(String asset_description) {
		this.asset_description = asset_description;
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
	public String getAsset_code() {
		return asset_code;
	}
	public void setAsset_code(String asset_code) {
		this.asset_code = asset_code;
	}
	public String getDisposed_by() {
		return disposed_by;
	}
	public void setDisposed_by(String disposed_by) {
		this.disposed_by = disposed_by;
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
	
}
