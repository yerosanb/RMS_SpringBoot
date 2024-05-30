package com.example.demo.user.notification;

public class notificationModel {
private long id;
private String title;
private String description;
private String date;
private String view_status;
private String status;
private String availability;
public notificationModel() {
	super();
	// TODO Auto-generated constructor stub
}
public notificationModel(long id, String title, String description, String date, String view_status, String status,
		String availability) {
	super();
	this.id = id;
	this.title = title;
	this.description = description;
	this.date = date;
	this.view_status = view_status;
	this.status = status;
	this.availability = availability;
}
public long getId() {
	return id;
}
public void setId(long id) {
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
public String getView_status() {
	return view_status;
}
public void setView_status(String view_status) {
	this.view_status = view_status;
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
	return "notificationModel [id=" + id + ", title=" + title + ", description=" + description + ", date=" + date
			+ ", view_status=" + view_status + ", status=" + status + ", availability=" + availability + "]";
}

}
