package com.example.demo.abebayehu.entity;

public class view_fixed_mms_deleted {
	private Long id;
    private String giv_number;
    private String grv_number;
    private double original_cost;
    private String created_date;
	private String tag_number;
	private String edit_delete;
	view_fixed_mms_deleted(Long id, String giv_number, String grv_number, double original_cost, String created_date,
			String tag_number, String edit_delete) {
		super();
		this.id = id;
		this.giv_number = giv_number;
		this.grv_number = grv_number;
		this.original_cost = original_cost;
		this.created_date = created_date;
		this.tag_number = tag_number;
		this.edit_delete = edit_delete;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEdit_delete() {
		return edit_delete;
	}
	public void setEdit_delete(String edit_delete) {
		this.edit_delete = edit_delete;
	}
	
	
	}
