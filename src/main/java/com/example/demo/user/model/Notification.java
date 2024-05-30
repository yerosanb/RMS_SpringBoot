package com.example.demo.user.model;

public class Notification {
	
	private Long id;
	private String title;
	private String description;
	private String date;
	private String viewStatus;
	private int referenceId;
	private String status;
	private String availability;
	
	public Notification() {
		
	}
	 
	public Notification(Long id, String title, String description, String date, String viewStatus, int referenceId,
			String status, String availability) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.viewStatus = viewStatus;
		this.referenceId = referenceId;
		this.status = status;
		this.availability = availability;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getViewStatus() {
		return viewStatus;
	}
	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
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
	public int getReferenceId() {
		return referenceId;
	}
	public void setReferenceId(int referenceId) {
		this.referenceId = referenceId;
	}

	 
	
}
