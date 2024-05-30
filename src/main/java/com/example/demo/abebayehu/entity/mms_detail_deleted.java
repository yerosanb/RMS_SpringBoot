package com.example.demo.abebayehu.entity;

public class mms_detail_deleted {
	private Long id;
	private String new_old;
    private String giv_number;
    private String grv_number;
    private double original_cost;
    private String created_date;
	private String tag_number;
    private String  reason;
    private String  firstname;
    private String  lastname;
    private String  DATE;
    private String  edit_delete;
    private String edit_reason_id;
	mms_detail_deleted(Long id, String new_old, String giv_number, String grv_number, double original_cost,
			String created_date, String tag_number, String reason, String firstname, String lastname, String dATE,
			String edit_delete, String edit_reason_id) {
		super();
		this.id = id;
		this.new_old = new_old;
		this.giv_number = giv_number;
		this.grv_number = grv_number;
		this.original_cost = original_cost;
		this.created_date = created_date;
		this.tag_number = tag_number;
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
	public String getGiv_number() {
		return giv_number;
	}
	public void setGiv_number(String giv_number) {
		this.giv_number = giv_number;
	}
	public String getGrv_number() {
		return grv_number;
	}
	public void setGrv_number(String grv_number) {
		this.grv_number = grv_number;
	}
	public double getOriginal_cost() {
		return original_cost;
	}
	public void setOriginal_cost(double original_cost) {
		this.original_cost = original_cost;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getTag_number() {
		return tag_number;
	}
	public void setTag_number(String tag_number) {
		this.tag_number = tag_number;
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
