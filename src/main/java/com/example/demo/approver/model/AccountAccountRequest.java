package com.example.demo.approver.model;

public class AccountAccountRequest {
	private Long id;
	private Long account_id;
	private Long account_request_id;
	public AccountAccountRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AccountAccountRequest(Long id, Long account_id, Long account_request_id) {
		super();
		this.id = id;
		this.account_id = account_id;
		this.account_request_id = account_request_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	public Long getAccount_request_id() {
		return account_request_id;
	}
	public void setAccount_request_id(Long account_request_id) {
		this.account_request_id = account_request_id;
	}



}
