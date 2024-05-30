package com.example.demo.user.model;

public class Ee {
	
	private int ID;
	private String FIRSTNAME;
	private String LASTNAME;
	private String STREET;
	public Ee(int iD, String fIRSTNAME, String lASTNAME, String sTREET) {
		super();
		ID = iD;
		FIRSTNAME = fIRSTNAME;
		LASTNAME = lASTNAME;
		STREET = sTREET;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getFIRSTNAME() {
		return FIRSTNAME;
	}
	public void setFIRSTNAME(String fIRSTNAME) {
		FIRSTNAME = fIRSTNAME;
	}
	public String getLASTNAME() {
		return LASTNAME;
	}
	public void setLASTNAME(String lASTNAME) {
		LASTNAME = lASTNAME;
	}
	public String getSTREET() {
		return STREET;
	}
	public void setSTREET(String sTREET) {
		STREET = sTREET;
	}
	
	
	
	
	
//	private final Integer id;
//    private final String name;
//    private final String designation;
//    private final String department;
//	public Ee(Integer id, String name, String designation, String department) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.designation = designation;
//		this.department = department;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public String getDesignation() {
//		return designation;
//	}
//
//	public String getDepartment() {
//		return department;
//	}
//
//	@Override
//	public String toString() {
//		return "Ee [id=" + id + ", name=" + name + ", designation=" + designation + ", department=" + department + "]";
//	}
    
    
}
