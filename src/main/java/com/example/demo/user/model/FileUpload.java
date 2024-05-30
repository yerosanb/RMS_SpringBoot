package com.example.demo.user.model;

public class FileUpload { 
private Long id;
private String file_name; 
private String file_type; 
private String upload_date; 
private String account_name; 
private String currency_name; 
private String first_name; 
private String middle_name; 
private String last_name; 
private String workspace_name;
private String type; 
private String email;


public FileUpload() {
	
}


public FileUpload(Long id, String file_name, String file_type, String upload_date, String account_name,
		String currency_name, String first_name, String middle_name, String last_name, String workspace_name,
		String type, String email) {
	super();
	this.id = id;
	this.file_name = file_name;
	this.file_type = file_type;
	this.upload_date = upload_date;
	this.account_name = account_name;
	this.currency_name = currency_name;
	this.first_name = first_name;
	this.middle_name = middle_name;
	this.last_name = last_name;
	this.workspace_name = workspace_name;
	this.type = type;
	this.email = email;
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getFile_name() {
	return file_name;
}


public void setFile_name(String file_name) {
	this.file_name = file_name;
}


public String getFile_type() {
	return file_type;
}


public void setFile_type(String file_type) {
	this.file_type = file_type;
}


public String getUpload_date() {
	return upload_date;
}


public void setUpload_date(String upload_date) {
	this.upload_date = upload_date;
}


public String getAccount_name() {
	return account_name;
}


public void setAccount_name(String account_name) {
	this.account_name = account_name;
}


public String getCurrency_name() {
	return currency_name;
}


public void setCurrency_name(String currency_name) {
	this.currency_name = currency_name;
}


public String getFirst_name() {
	return first_name;
}


public void setFirst_name(String first_name) {
	this.first_name = first_name;
}


public String getMiddle_name() {
	return middle_name;
}


public void setMiddle_name(String middle_name) {
	this.middle_name = middle_name;
}


public String getLast_name() {
	return last_name;
}


public void setLast_name(String last_name) {
	this.last_name = last_name;
}


public String getWorkspace_name() {
	return workspace_name;
}


public void setWorkspace_name(String workspace_name) {
	this.workspace_name = workspace_name;
}


public String getType() {
	return type;
}


public void setType(String type) {
	this.type = type;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


@Override
public String toString() {
	return "FileUpload [id=" + id + ", file_name=" + file_name + ", file_type=" + file_type + ", upload_date="
			+ upload_date + ", account_name=" + account_name + ", currency_name=" + currency_name + ", first_name="
			+ first_name + ", middle_name=" + middle_name + ", last_name=" + last_name + ", workspace_name="
			+ workspace_name + ", type=" + type + ", email=" + email + "]";
}
 

}

 
 




