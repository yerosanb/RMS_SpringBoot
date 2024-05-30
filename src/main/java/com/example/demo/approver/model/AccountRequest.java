package com.example.demo.approver.model;

public class AccountRequest {
	private Long id;
	private String name;
	private String code;
	private String middlename;
	private String firstname;
	private String type;
	private String created_date;
//	private List<RegisterActorDto> user;
	private String Description;
	private String request_status;
	private String currency;
	private String status;
	private String availability;
	public AccountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public AccountRequest(Long id, String name, String code, String middlename, String firstname, String type,
			String created_date, String description, String request_status,String currency, String status, String availability) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.middlename = middlename;
		this.firstname = firstname;
		this.type = type;
		this.created_date = created_date;
		Description = description;
		this.request_status = request_status;
		this.currency=currency;
		this.status = status;
		this.availability = availability;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getMiddlename() {
		return middlename;
	}


	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getCreated_date() {
		return created_date;
	}


	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public String getRequest_status() {
		return request_status;
	}


	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
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
