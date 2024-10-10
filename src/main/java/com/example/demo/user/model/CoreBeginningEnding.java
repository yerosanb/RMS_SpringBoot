package com.example.demo.user.model;

public class BeginningEnding {
	Double beginning_balance_con;
	Double beginning_balance_ifb;
	Double ending_balance_con;
	Double ending_balance_ifb;
	public BeginningEnding(Double beginning_balance_con, Double beginning_balance_ifb, Double ending_balance_con,
			Double ending_balance_ifb) {
		super();
		this.beginning_balance_con = beginning_balance_con;
		this.beginning_balance_ifb = beginning_balance_ifb;
		this.ending_balance_con = ending_balance_con;
		this.ending_balance_ifb = ending_balance_ifb;
	}
	public Double getBeginning_balance_con() {
		return beginning_balance_con;
	}
	public void setBeginning_balance_con(Double beginning_balance_con) {
		this.beginning_balance_con = beginning_balance_con;
	}
	public Double getBeginning_balance_ifb() {
		return beginning_balance_ifb;
	}
	public void setBeginning_balance_ifb(Double beginning_balance_ifb) {
		this.beginning_balance_ifb = beginning_balance_ifb;
	}
	public Double getEnding_balance_con() {
		return ending_balance_con;
	}
	public void setEnding_balance_con(Double ending_balance_con) {
		this.ending_balance_con = ending_balance_con;
	}
	public Double getEnding_balance_ifb() {
		return ending_balance_ifb;
	}
	public void setEnding_balance_ifb(Double ending_balance_ifb) {
		this.ending_balance_ifb = ending_balance_ifb;
	}
	@Override
	public String toString() {
		return "BeginningEnding [beginning_balance_con=" + beginning_balance_con + ", beginning_balance_ifb="
				+ beginning_balance_ifb + ", ending_balance_con=" + ending_balance_con + ", ending_balance_ifb="
				+ ending_balance_ifb + "]";
	}
	
	
}
