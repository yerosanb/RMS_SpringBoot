package com.example.demo.user.model;

public class File_erca_core {
	private Long id;
  	private String posting_date;
  	private String transaction_reference;
  	private String branch_code;
  	private Double amount;
  	private String dr_cr;
  	private Long upload_date;
  	private String match_status;
  	private String status;
  	private String availability;
	public File_erca_core() {
		super();
		// TODO Auto-generated constructor stub
	}
	public File_erca_core(Long id, String posting_date, String transaction_reference, String branch_code, Double amount,
			String dr_cr, Long upload_date, String match_status, String status, String availability) {
		super();
		this.id = id;
		this.posting_date = posting_date;
		this.transaction_reference = transaction_reference;
		this.branch_code = branch_code;
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
	public String getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
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
		return "File_erca_core [id=" + id + ", posting_date=" + posting_date + ", transaction_reference="
				+ transaction_reference + ", branch_code=" + branch_code + ", amount=" + amount + ", dr_cr=" + dr_cr
				+ ", upload_date=" + upload_date + ", match_status=" + match_status + ", status=" + status
				+ ", availability=" + availability + "]";
	}
	public void setValue_date(int parseInt) {
		// TODO Auto-generated method stub
		
	}
  	
}


