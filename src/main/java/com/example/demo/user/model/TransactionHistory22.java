package com.example.demo.user.model;

public class TransactionHistory22 {
	private Long id;
 	private String additional_information;
	private Double amount;
	private String dr_cr;
	private String transaction_reference;
 	private String branch_code;
	private String value_date;
//	private String upload_date;
//	private String posting_date;
//	private String match_status;
//	private String status;
//	private String availability;
//	private String match_date;
	
	public TransactionHistory22() {
		super();
		 
	}

	public TransactionHistory22 (Long id, String additional_information, Double amount, String dr_cr,
			String transaction_reference, String branch_code, String value_date, String upload_date,
			String posting_date, String match_status, String status, String availability, String match_date) {
		super();
		this.id = id;
		this.additional_information = additional_information;
		this.amount = amount;
		this.dr_cr = dr_cr;
		this.transaction_reference = transaction_reference;
		this.branch_code = branch_code;
		this.value_date = value_date;
//		this.upload_date = upload_date;
//		this.posting_date = posting_date;
//		this.match_status = match_status;
//		this.status = status;
//		this.availability = availability;
//		this.match_date = match_date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getTransaction_reference() {
		return transaction_reference;
	}

	public void setTransaction_reference(String transaction_reference) {
		this.transaction_reference = transaction_reference;
	}

	public String getBranch_code() {
		return branch_code;
	}

	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}

	public String getValue_date() {
		return value_date;
	}

	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}

//	public String getUpload_date() {
//		return upload_date;
//	}
//
//	public void setUpload_date(String upload_date) {
//		this.upload_date = upload_date;
//	}
//
//	public String getPosting_date() {
//		return posting_date;
//	}
//
//	public void setPosting_date(String posting_date) {
//		this.posting_date = posting_date;
//	}
//
//	public String getMatch_status() {
//		return match_status;
//	}
//
//	public void setMatch_status(String match_status) {
//		this.match_status = match_status;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getAvailability() {
//		return availability;
//	}
//
//	public void setAvailability(String availability) {
//		this.availability = availability;
//	}
//
//	public String getMatch_date() {
//		return match_date;
//	}
//
//	public void setMatch_date(String match_date) {
//		this.match_date = match_date;
//	}
//
//	@Override
//	public String toString() {
//		return "TransactionHistory22 [id=" + id + ", additional_information=" + additional_information + ", amount="
//				+ amount + ", dr_cr=" + dr_cr + ", transaction_reference=" + transaction_reference + ", branch_code="
//				+ branch_code + ", value_date=" + value_date + ", upload_date=" + upload_date + ", posting_date="
//				+ posting_date + ", match_status=" + match_status + ", status=" + status + ", availability="
//				+ availability + ", match_date=" + match_date + "]";
//	}
//	
	
	
	 
 

}
