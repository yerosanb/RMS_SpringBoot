package com.example.demo.approver.model;

public class Remark {
	private long id;
	private String title;
	private String description;
	private String created_date;
	private String firstname;
	public Remark() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Remark(long id, String title, String description, String created_date, String firstname) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.created_date = created_date;
		this.firstname=firstname;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	
}
