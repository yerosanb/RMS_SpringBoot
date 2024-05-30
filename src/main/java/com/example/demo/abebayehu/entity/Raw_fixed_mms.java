package com.example.demo.abebayehu.entity;

public class Raw_fixed_mms {
	private Long id;
	private String created_date;
	private String asset_id;
	private String asset_description;
	private String tag_number;
	private String branch_name;
	private Long quantity;
	private String grv_number;
	private String giv_number;
	private double original_cost;
	private Long book_value;
	private String main_pg;
	private String old_main_pg;
	private String status;
	private String availability;
	private String match_status;
	private String match_id;
    private String match_date;
    private String firstname;
    private String middlename;
    private String lastname;
    private String reason;
	
	
	public Raw_fixed_mms() {
	}
	public Raw_fixed_mms(Long id, String created_date, String asset_id, String asset_description, String tag_number,
			String branch_name, Long quantity, String grv_number, String giv_number, double original_cost,
			Long book_value, String main_pg, String old_main_pg, String status, String availability,
			String match_status, String match_id, String match_date, String firstname, String middlename,
			String lastname, String reason) {
		super();
		this.id = id;
		this.created_date = created_date;
		this.asset_id = asset_id;
		this.asset_description = asset_description;
		this.tag_number = tag_number;
		this.branch_name = branch_name;
		this.quantity = quantity;
		this.grv_number = grv_number;
		this.giv_number = giv_number;
		this.original_cost = original_cost;
		this.book_value = book_value;
		this.main_pg = main_pg;
		this.old_main_pg = old_main_pg;
		this.status = status;
		this.availability = availability;
		this.match_status = match_status;
		this.match_id = match_id;
		this.match_date = match_date;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.reason = reason;
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
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
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
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
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
	public double getOriginal_cost() {
		return original_cost;
	}
	public void setOriginal_cost(double original_cost) {
		this.original_cost = original_cost;
	}
	public Long getBook_value() {
		return book_value;
	}
	public void setBook_value(Long book_value) {
		this.book_value = book_value;
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
	public String getMatch_status() {
		return match_status;
	}
	public void setMatch_status(String match_status) {
		this.match_status = match_status;
	}
	public String getMatch_id() {
		return match_id;
	}
	public void setMatch_id(String match_id) {
		this.match_id = match_id;
	}
	public String getMatch_date() {
		return match_date;
	}
	public void setMatch_date(String match_date) {
		this.match_date = match_date;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
//	@Override
//	public String toString() {
//		return "Raw_fixed_mms [id=" + id + ", created_date=" + created_date + ", asset_id=" + asset_id
//				+ ", asset_description=" + asset_description + ", tag_number=" + tag_number + ", branch_name=" + branch_name
//				+ ", quantity=" + quantity + ", grv_number=" + grv_number + ", giv_number=" + giv_number
//				+ ", original_cost=" + original_cost + ", book_value=" + book_value + ", main_pg=" + main_pg
//				+ ", availability=" + availability + ", file_id=" + file_id + ", match_id=" + match_id + ", status="
//				+ status + "]";
//	}
}
