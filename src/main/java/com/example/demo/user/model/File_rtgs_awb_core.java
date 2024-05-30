package com.example.demo.user.model;

 
public class File_rtgs_awb_core {
	
  	private Long id;
  	private String posting_date;
  	private String value_date;
  	private String transaction_reference;
  	private String additional_information;
  	private String branch_code;
  	private String branch;
  	private double amount;
  	private String dr_cr;
  	private double balance;
  	private Long upload_date;
  	private String match_status;
  	private String status;
  	private String availability;
  	private Long file_id;
  	private String match_id;
	private String type;
	private Long match_date;
  	private String firstname;
  	private String lastname;
  	private String new_old;
  	private String reason;
  	private String DATE;
  	private String edit_delete;
  	private Long edit_reason_id;
	private float amount_difference;
	private String description;
	private Long core_id;
	private String setteled_date;
 	private String branch_name;
 	private String ctr;
  	

 	
 	public File_rtgs_awb_core() {
 		
 	}

	public File_rtgs_awb_core(Long id, String posting_date, String value_date, String transaction_reference,
			String additional_information, String branch_code, String branch, double amount, String dr_cr,
			double balance, Long upload_date, String match_status, String status, String availability, Long file_id,
			String match_id, String type, Long match_date, String firstname, String lastname, String new_old,
			String reason, String dATE, String edit_delete, Long edit_reason_id, float amount_difference,
			String description, Long core_id, String setteled_date, String branch_name, String ctr) {
		super();
		this.id = id;
		this.posting_date = posting_date;
		this.value_date = value_date;
		this.transaction_reference = transaction_reference;
		this.additional_information = additional_information;
		this.branch_code = branch_code;
		this.branch = branch;
		this.amount = amount;
		this.dr_cr = dr_cr;
		this.balance = balance;
		this.upload_date = upload_date;
		this.match_status = match_status;
		this.status = status;
		this.availability = availability;
		this.file_id = file_id;
		this.match_id = match_id;
		this.type = type;
		this.match_date = match_date;
		this.firstname = firstname;
		this.lastname = lastname;
		this.new_old = new_old;
		this.reason = reason;
		DATE = dATE;
		this.edit_delete = edit_delete;
		this.edit_reason_id = edit_reason_id;
		this.amount_difference = amount_difference;
		this.description = description;
		this.core_id = core_id;
		this.setteled_date = setteled_date;
		this.branch_name = branch_name;
		this.ctr = ctr;
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



	public String getValue_date() {
		return value_date;
	}



	public void setValue_date(String value_date) {
		this.value_date = value_date;
	}



	public String getTransaction_reference() {
		return transaction_reference;
	}



	public void setTransaction_reference(String transaction_reference) {
		this.transaction_reference = transaction_reference;
	}



	public String getAdditional_information() {
		return additional_information;
	}



	public void setAdditional_information(String additional_information) {
		this.additional_information = additional_information;
	}



	public String getBranch_code() {
		return branch_code;
	}



	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}



	public String getBranch() {
		return branch;
	}



	public void setBranch(String branch) {
		this.branch = branch;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}



	public String getDr_cr() {
		return dr_cr;
	}



	public void setDr_cr(String dr_cr) {
		this.dr_cr = dr_cr;
	}



	public double getBalance() {
		return balance;
	}



	public void setBalance(double balance) {
		this.balance = balance;
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



	public Long getFile_id() {
		return file_id;
	}



	public void setFile_id(Long file_id) {
		this.file_id = file_id;
	}



	public String getMatch_id() {
		return match_id;
	}



	public void setMatch_id(String match_id) {
		this.match_id = match_id;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public Long getMatch_date() {
		return match_date;
	}



	public void setMatch_date(Long match_date) {
		this.match_date = match_date;
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

	public String getNew_old() {
		return new_old;
	}



	public void setNew_old(String new_old) {
		this.new_old = new_old;
	}



	public String getReason() {
		return reason;
	}



	public void setReason(String reason) {
		this.reason = reason;
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



	public Long getEdit_reason_id() {
		return edit_reason_id;
	}



	public void setEdit_reason_id(Long edit_reason_id) {
		this.edit_reason_id = edit_reason_id;
	}



	public float getAmount_difference() {
		return amount_difference;
	}



	public void setAmount_difference(float amount_difference) {
		this.amount_difference = amount_difference;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Long getCore_id() {
		return core_id;
	}



	public void setCore_id(Long core_id) {
		this.core_id = core_id;
	}


	public String getSetteled_date() {
		return setteled_date;
	}

	public void setSetteled_date(String setteled_date) {
		this.setteled_date = setteled_date;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getCtr() {
		return ctr;
	}

	public void setCtr(String ctr) {
		this.ctr = ctr;
	}

	@Override
	public String toString() {
		return "File_rtgs_awb_core [id=" + id + ", posting_date=" + posting_date + ", value_date=" + value_date
				+ ", transaction_reference=" + transaction_reference + ", additional_information="
				+ additional_information + ", branch_code=" + branch_code + ", branch=" + branch + ", amount=" + amount
				+ ", dr_cr=" + dr_cr + ", balance=" + balance + ", upload_date=" + upload_date + ", match_status="
				+ match_status + ", status=" + status + ", availability=" + availability + ", file_id=" + file_id
				+ ", match_id=" + match_id + ", type=" + type + ", match_date=" + match_date + ", firstname="
				+ firstname + ", lastname=" + lastname + ", new_old=" + new_old + ", reason=" + reason + ", DATE="
				+ DATE + ", edit_delete=" + edit_delete + ", edit_reason_id=" + edit_reason_id + ", amount_difference="
				+ amount_difference + ", description=" + description + ", core_id=" + core_id + ", setteled_date="
				+ setteled_date + ", branch_name=" + branch_name + ", ctr=" + ctr + "]";
	}
}
