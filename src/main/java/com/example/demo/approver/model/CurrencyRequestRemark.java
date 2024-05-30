package com.example.demo.approver.model;

public class CurrencyRequestRemark {
	private long id;
	private long currency_request_id;
	private long remark_id;
	public CurrencyRequestRemark() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CurrencyRequestRemark(long id, long currency_request_id, long remark_id) {
		super();
		this.id = id;
		this.currency_request_id = currency_request_id;
		this.remark_id = remark_id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCurrency_request_id() {
		return currency_request_id;
	}
	public void setCurrency_request_id(long currency_request_id) {
		this.currency_request_id = currency_request_id;
	}
	public long getRemark_id() {
		return remark_id;
	}
	public void setRemark_id(long remark_id) {
		this.remark_id = remark_id;
	}
	
	

}
