package com.example.demo.user.model;

public class GeneralTransactionHistory {
	private String type;
	private String min_amount;
	private String max_amount;
	private String min_upload_date;
	private String max_upload_date;
	private String min_upload_date2;
	private String max_upload_date2;
	private String min_match_date;
	private String max_match_date;
	private int min_match_date2;
	private int max_match_date2;
	private String reference;
	private String branch_code;
	private String value_date;
	private String account_name;
	private String match_date;
	private int match_date2;
	private String posting_date;
	private String value_date2;
	private String max_value_date;

	private String store_code;
	private String transaction_date;
	private Long period;
	private double debit;
	private double credit;
	
	
	private String source_branch;
	private String account;
	
	private String categoryStock;
	private String categoryStockMMs;

 
	private String store_name;
	private String category_description;
	private int tran_code;
	private String stock_account_segment;
	private String max_posting_date;
	private String tag_number;
	private String category;
	private String ifb;
	private String com;
	public GeneralTransactionHistory() {
		// TODO Auto-generated constructor stub
	}
	public GeneralTransactionHistory(String type, String min_amount, String max_amount, String min_upload_date,
			String max_upload_date, String min_upload_date2, String max_upload_date2, String min_match_date,
			String max_match_date, int min_match_date2, int max_match_date2, String reference, String branch_code,
			String value_date, String account_name, String match_date, int match_date2, String posting_date,
			String value_date2, String max_value_date, String store_code, String transaction_date, Long period,
			double debit, double credit, String source_branch, String account, String categoryStock,
			String categoryStockMMs, String store_name, String category_description, int tran_code,
			String stock_account_segment, String max_posting_date, String tag_number, String category, String ifb,
			String com) {
		super();
		this.type = type;
		this.min_amount = min_amount;
		this.max_amount = max_amount;
		this.min_upload_date = min_upload_date;
		this.max_upload_date = max_upload_date;
		this.min_upload_date2 = min_upload_date2;
		this.max_upload_date2 = max_upload_date2;
		this.min_match_date = min_match_date;
		this.max_match_date = max_match_date;
		this.min_match_date2 = min_match_date2;
		this.max_match_date2 = max_match_date2;
		this.reference = reference;
		this.branch_code = branch_code;
		this.value_date = value_date;
		this.account_name = account_name;
		this.match_date = match_date;
		this.match_date2 = match_date2;
		this.posting_date = posting_date;
		this.value_date2 = value_date2;
		this.max_value_date = max_value_date;
		this.store_code = store_code;
		this.transaction_date = transaction_date;
		this.period = period;
		this.debit = debit;
		this.credit = credit;
		this.source_branch = source_branch;
		this.account = account;
		this.categoryStock = categoryStock;
		this.categoryStockMMs = categoryStockMMs;
		this.store_name = store_name;
		this.category_description = category_description;
		this.tran_code = tran_code;
		this.stock_account_segment = stock_account_segment;
		this.max_posting_date = max_posting_date;
		this.tag_number = tag_number;
		this.category = category;
		this.ifb = ifb;
		this.com = com;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMin_amount() {
		return min_amount;
	}
	public void setMin_amount(String min_amount) {
		this.min_amount = min_amount;
	}
	public String getMax_amount() {
		return max_amount;
	}
	public void setMax_amount(String max_amount) {
		this.max_amount = max_amount;
	}
	public String getMin_upload_date() {
		return min_upload_date;
	}
	public void setMin_upload_date(String min_upload_date) {
		this.min_upload_date = min_upload_date;
	}
	public String getMax_upload_date() {
		return max_upload_date;
	}
	public void setMax_upload_date(String max_upload_date) {
		this.max_upload_date = max_upload_date;
	}
	public String getMin_upload_date2() {
		return min_upload_date2;
	}
	public void setMin_upload_date2(String min_upload_date2) {
		this.min_upload_date2 = min_upload_date2;
	}
	public String getMax_upload_date2() {
		return max_upload_date2;
	}
	public void setMax_upload_date2(String max_upload_date2) {
		this.max_upload_date2 = max_upload_date2;
	}
	public String getMin_match_date() {
		return min_match_date;
	}
	public void setMin_match_date(String min_match_date) {
		this.min_match_date = min_match_date;
	}
	public String getMax_match_date() {
		return max_match_date;
	}
	public void setMax_match_date(String max_match_date) {
		this.max_match_date = max_match_date;
	}
	public int getMin_match_date2() {
		return min_match_date2;
	}
	public void setMin_match_date2(int min_match_date2) {
		this.min_match_date2 = min_match_date2;
	}
	public int getMax_match_date2() {
		return max_match_date2;
	}
	public void setMax_match_date2(int max_match_date2) {
		this.max_match_date2 = max_match_date2;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
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
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getMatch_date() {
		return match_date;
	}
	public void setMatch_date(String match_date) {
		this.match_date = match_date;
	}
	public int getMatch_date2() {
		return match_date2;
	}
	public void setMatch_date2(int match_date2) {
		this.match_date2 = match_date2;
	}
	public String getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(String posting_date) {
		this.posting_date = posting_date;
	}
	public String getValue_date2() {
		return value_date2;
	}
	public void setValue_date2(String value_date2) {
		this.value_date2 = value_date2;
	}
	public String getMax_value_date() {
		return max_value_date;
	}
	public void setMax_value_date(String max_value_date) {
		this.max_value_date = max_value_date;
	}
	public String getStore_code() {
		return store_code;
	}
	public void setStore_code(String store_code) {
		this.store_code = store_code;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public Long getPeriod() {
		return period;
	}
	public void setPeriod(Long period) {
		this.period = period;
	}
	public double getDebit() {
		return debit;
	}
	public void setDebit(double debit) {
		this.debit = debit;
	}
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	public String getSource_branch() {
		return source_branch;
	}
	public void setSource_branch(String source_branch) {
		this.source_branch = source_branch;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCategoryStock() {
		return categoryStock;
	}
	public void setCategoryStock(String categoryStock) {
		this.categoryStock = categoryStock;
	}
	public String getCategoryStockMMs() {
		return categoryStockMMs;
	}
	public void setCategoryStockMMs(String categoryStockMMs) {
		this.categoryStockMMs = categoryStockMMs;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getCategory_description() {
		return category_description;
	}
	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}
	public int getTran_code() {
		return tran_code;
	}
	public void setTran_code(int tran_code) {
		this.tran_code = tran_code;
	}
	public String getStock_account_segment() {
		return stock_account_segment;
	}
	public void setStock_account_segment(String stock_account_segment) {
		this.stock_account_segment = stock_account_segment;
	}
	public String getMax_posting_date() {
		return max_posting_date;
	}
	public void setMax_posting_date(String max_posting_date) {
		this.max_posting_date = max_posting_date;
	}
	public String getTag_number() {
		return tag_number;
	}
	public void setTag_number(String tag_number) {
		this.tag_number = tag_number;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIfb() {
		return ifb;
	}
	public void setIfb(String ifb) {
		this.ifb = ifb;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	 
	 
	 

}