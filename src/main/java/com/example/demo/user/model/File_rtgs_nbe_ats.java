package com.example.demo.user.model;

public class File_rtgs_nbe_ats {

	private Long id;
	private Long core_id;
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
	private Long file_id;
	private String match_id;
	private String reason;
	private Long match_date;
	private String firstname;
	private String lastname;
	private String new_old;
	private String DATE;
	private String edit_delete;
	private Long edit_reason_id;
	private float amount_difference;
	private String description;
	private String type;
	private String setteled_date;

	public File_rtgs_nbe_ats() {

	}



	File_rtgs_nbe_ats(Long id, Long core_id, String value_date_type, String reference, String sender, String receiver,
			String additional_information, Double amount, String dr_cr, Long upload_date, String match_status,
			String status, String availability, Long file_id, String match_id, String reason, Long match_date,
			String firstname, String lastname, String new_old, String dATE, String edit_delete, Long edit_reason_id,
			float amount_difference, String description, String type, String setteled_date) {
		super();
		this.id = id;
		this.core_id = core_id;
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
		this.file_id = file_id;
		this.match_id = match_id;
		this.reason = reason;
		this.match_date = match_date;
		this.firstname = firstname;
		this.lastname = lastname;
		this.new_old = new_old;
		DATE = dATE;
		this.edit_delete = edit_delete;
		this.edit_reason_id = edit_reason_id;
		this.amount_difference = amount_difference;
		this.description = description;
		this.type = type;
		this.setteled_date = setteled_date;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCore_id() {
		return core_id;
	}

	public void setCore_id(Long core_id) {
		this.core_id = core_id;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

	public String getSetteled_date() {
		return setteled_date;
	}



	public void setSetteled_date(String setteled_date) {
		this.setteled_date = setteled_date;
	}



	@Override
	public String toString() {
		return "File_rtgs_nbe_ats [id=" + id + ", core_id=" + core_id + ", value_date_type=" + value_date_type
				+ ", reference=" + reference + ", sender=" + sender + ", receiver=" + receiver
				+ ", additional_information=" + additional_information + ", amount=" + amount + ", dr_cr=" + dr_cr
				+ ", upload_date=" + upload_date + ", match_status=" + match_status + ", status=" + status
				+ ", availability=" + availability + ", file_id=" + file_id + ", match_id=" + match_id + ", reason="
				+ reason + ", match_date=" + match_date + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", new_old=" + new_old + ", DATE=" + DATE + ", edit_delete=" + edit_delete + ", edit_reason_id="
				+ edit_reason_id + ", amount_difference=" + amount_difference + ", description=" + description
				+ ", type=" + type + "]";
	}


}
