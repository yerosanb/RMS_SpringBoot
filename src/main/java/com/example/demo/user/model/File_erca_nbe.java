package com.example.demo.user.model;

public class File_erca_ {

	private Long id;
  	private String value_date_type;
  	private String reference;
  	private String sender;
  	private String receiver;
  	private String additional_information;
  	private Double amount;
  	private String dr_cr;
  	private Long upload_date;
  	private String match_status;
  	private String status;
  	private String availability;
	public File_erca_() {
		super();
		// TODO Auto-generated constructor stub
	}
	public File_erca_(Long id, String value_date_type, String reference, String sender, String receiver,
			String additional_information, Double amount, String dr_cr, Long upload_date, String match_status,
			String status, String availability) {
		super();
		this.id = id;
		this.value_date_type = value_date_type;
		this.reference = reference;
		this.sender = sender;
		this.receiver = receiver;
		this.additional_information = additional_information;
		this.amount = amount;
		this.dr_cr = dr_cr;
		this.upload_date = upload_date;
		this.match_status = match_status;
		this.status = status;
		this.availability = availability;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue_date_type() {
		return value_date_type;
	}
	public void setValue_date_type(String value_date_type) {
		this.value_date_type = value_date_type;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getAdditional_information() {
		return additional_information;
	}
	public void setAdditional_information(String additional_information) {
		this.additional_information = additional_information;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getDr_cr() {
		return dr_cr;
	}
	public void setDr_cr(String dr_cr) {
		this.dr_cr = dr_cr;
	}
	public Long getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(Long upload_date) {
		this.upload_date = upload_date;
	}
	public String getMatch_status() {
		return match_status;
	}
	public void setMatch_status(String match_status) {
		this.match_status = match_status;
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
	@Override
	public String toString() {
		return "File_erca_ [id=" + id + ", value_date_type=" + value_date_type + ", reference=" + reference
				+ ", sender=" + sender + ", receiver=" + receiver + ", additional_information=" + additional_information
				+ ", amount=" + amount + ", dr_cr=" + dr_cr + ", upload_date=" + upload_date + ", match_status="
				+ match_status + ", status=" + status + ", availability=" + availability + "]";
	}

}


