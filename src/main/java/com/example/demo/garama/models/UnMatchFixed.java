package com.example.demo.garama.models;

 
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;

//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
 public class UnMatchFixed {
    private Long id;
    private String reference;
    private String store_code;
    private String stock_account_segment;
    private String transaction_date;
    private String additional_information;
    private Long period;
    private float debit;
    private float credit;
    private float bbf;
    private int tran_code;
    private String main_pg;
    private String store_name;
    private String category_description;
    private String status;
    private String availability;
    private String match_id;
    private String match_date;
    private String firstname;
    private String middlename;
    private String lastname;
    private String reason;
 

    public UnMatchFixed(Long id, String reference, String store_code, String stock_account_segment,
            String transaction_date, String additional_information, Long period, float debit,
            float credit, float bbf, int tran_code, String store_name, String category_description,
            String status, String availability, String match_id,
            String match_date, String firstname, String middlename, String lastname, String main_pg) {
        this.id = id;
        this.reference = reference;
        this.store_code = store_code;
        this.stock_account_segment = stock_account_segment;
        this.transaction_date = transaction_date;
        this.additional_information = additional_information;
        this.period = period;
        this.debit = debit;
        this.credit = credit;
        this.bbf = bbf;
        this.tran_code = tran_code;
        this.main_pg = main_pg;
        this.store_name = store_name;
        this.category_description = category_description;
        this.status = status;
        this.availability = availability;
        this.match_id = match_id;
        this.match_date = match_date;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getStore_code() {
        return store_code;
    }

    public void setStore_code(String store_code) {
        this.store_code = store_code;
    }

    public String getStock_account_segment() {
        return stock_account_segment;
    }

    public void setStock_account_segment(String stock_account_segment) {
        this.stock_account_segment = stock_account_segment;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getAdditional_information() {
        return additional_information;
    }

    public void setAdditional_information(String additional_information) {
        this.additional_information = additional_information;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    public float getDebit() {
        return debit;
    }

    public void setDebit(float debit) {
        this.debit = debit;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public float getBbf() {
        return bbf;
    }

    public void setBbf(float bbf) {
        this.bbf = bbf;
    }

    public int getTran_code() {
        return tran_code;
    }

    public void setTran_code(int tran_code) {
        this.tran_code = tran_code;
    }

    public String getMain_pg() {
        return main_pg;
    }

    public void setMain_pg(String main_pg) {
        this.main_pg = main_pg;
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

    // public Long getFile_id() {
    // return file_id;
    // }

    // public void setFile_id(Long file_id) {
    // this.file_id = file_id;
    // }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    // public String getType() {
    // return type;
    // }

    // public void setType(String type) {
    // this.type = type;
    // }

    // public Long get_id() {
    // return _id;
    // }

    // public void set_id(Long _id) {
    // this._id = _id;
    // }

    public String getMatch_date() {
        return match_date;
    }

    public void setMatch_date(String match_date) {
        this.match_date = match_date;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
 
   
 
}
