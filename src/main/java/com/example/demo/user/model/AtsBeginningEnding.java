package com.example.demo.user.model;

public class AtsBeginningEnding {
	Double beginning_balance_ats;
	Double ending_balance_ats;
	
	public AtsBeginningEnding(Double beginning_balance_ats, Double ending_balance_ats) {
		super();
		this.beginning_balance_ats = beginning_balance_ats;
		this.ending_balance_ats = ending_balance_ats;
	}
	public Double getBeginning_balance_ats() {
		return beginning_balance_ats;
	}
	public void setBeginning_balance_ats(Double beginning_balance_ats) {
		this.beginning_balance_ats = beginning_balance_ats;
	}
	public Double getEnding_balance_ats() {
		return ending_balance_ats;
	}
	public void setEnding_balance_ats(Double ending_balance_ats) {
		this.ending_balance_ats = ending_balance_ats;
	}
	@Override
	public String toString() {
		return "AtsBeginningEnding [beginning_balance_ats=" + beginning_balance_ats + ", ending_balance_ats="
				+ ending_balance_ats + "]";
	}
	
}
