package com.example.demo.abebayehu.entity;

public class Mms_trial_balance {
    private double mms_fur, mms_comp, mms_mv, mms_oe,stock_stationary,stock_tools,stock_spares,stock_uniform,stock_accessory,stock_check,stock_sanitory,stock_computer,stock_furniture,stock_office_equipment;
    private String type;
    private String add_subtract;
    private String amount;

	public Mms_trial_balance() {
	}

	public Mms_trial_balance(double mms_fur, double mms_comp, double mms_mv, double mms_oe, double stock_stationary,
			double stock_tools, double stock_spares, double stock_uniform, double stock_accessory, double stock_check,
			double stock_sanitory, double stock_computer, double stock_furniture, double stock_office_equipment,
			String type, String add_subtract, String amount) {
		super();
		this.mms_fur = mms_fur;
		this.mms_comp = mms_comp;
		this.mms_mv = mms_mv;
		this.mms_oe = mms_oe;
		this.stock_stationary = stock_stationary;
		this.stock_tools = stock_tools;
		this.stock_spares = stock_spares;
		this.stock_uniform = stock_uniform;
		this.stock_accessory = stock_accessory;
		this.stock_check = stock_check;
		this.stock_sanitory = stock_sanitory;
		this.stock_computer = stock_computer;
		this.stock_furniture = stock_furniture;
		this.stock_office_equipment = stock_office_equipment;
		this.type = type;
		this.add_subtract = add_subtract;
		this.amount = amount;
	}

	public double getMms_fur() {
		return mms_fur;
	}

	public void setMms_fur(double mms_fur) {
		this.mms_fur = mms_fur;
	}

	public double getMms_comp() {
		return mms_comp;
	}

	public void setMms_comp(double mms_comp) {
		this.mms_comp = mms_comp;
	}

	public double getMms_mv() {
		return mms_mv;
	}

	public void setMms_mv(double mms_mv) {
		this.mms_mv = mms_mv;
	}

	public double getMms_oe() {
		return mms_oe;
	}

	public void setMms_oe(double mms_oe) {
		this.mms_oe = mms_oe;
	}

	public double getStock_stationary() {
		return stock_stationary;
	}

	public void setStock_stationary(double stock_stationary) {
		this.stock_stationary = stock_stationary;
	}

	public double getStock_tools() {
		return stock_tools;
	}

	public void setStock_tools(double stock_tools) {
		this.stock_tools = stock_tools;
	}

	public double getStock_spares() {
		return stock_spares;
	}

	public void setStock_spares(double stock_spares) {
		this.stock_spares = stock_spares;
	}

	public double getStock_uniform() {
		return stock_uniform;
	}

	public void setStock_uniform(double stock_uniform) {
		this.stock_uniform = stock_uniform;
	}

	public double getStock_accessory() {
		return stock_accessory;
	}

	public void setStock_accessory(double stock_accessory) {
		this.stock_accessory = stock_accessory;
	}

	public double getStock_check() {
		return stock_check;
	}

	public void setStock_check(double stock_check) {
		this.stock_check = stock_check;
	}

	public double getStock_sanitory() {
		return stock_sanitory;
	}

	public void setStock_sanitory(double stock_sanitory) {
		this.stock_sanitory = stock_sanitory;
	}

	public double getStock_computer() {
		return stock_computer;
	}

	public void setStock_computer(double stock_computer) {
		this.stock_computer = stock_computer;
	}

	public double getStock_furniture() {
		return stock_furniture;
	}

	public void setStock_furniture(double stock_furniture) {
		this.stock_furniture = stock_furniture;
	}

	public double getStock_office_equipment() {
		return stock_office_equipment;
	}

	public void setStock_office_equipment(double stock_office_equipment) {
		this.stock_office_equipment = stock_office_equipment;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAdd_subtract() {
		return add_subtract;
	}

	public void setAdd_subtract(String add_subtract) {
		this.add_subtract = add_subtract;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Mms_trial_balance [mms_fur=" + mms_fur + ", mms_comp=" + mms_comp + ", mms_mv=" + mms_mv + ", mms_oe="
				+ mms_oe + ", stock_stationary=" + stock_stationary + ", stock_tools=" + stock_tools + ", stock_spares="
				+ stock_spares + ", stock_uniform=" + stock_uniform + ", stock_accessory=" + stock_accessory
				+ ", stock_check=" + stock_check + ", stock_sanitory=" + stock_sanitory + ", stock_computer="
				+ stock_computer + ", stock_furniture=" + stock_furniture + ", stock_office_equipment="
				+ stock_office_equipment + ", type=" + type + ", add_subtract=" + add_subtract + ", amount=" + amount
				+ "]";
	}


    
}
