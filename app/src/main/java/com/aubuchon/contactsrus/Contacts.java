package com.aubuchon.contactsrus;


public class Contacts {

    //define variables for the columns
    private int _id;
    private String firstname;
    private String lastname;
    private String address;
    private String phoneNumber;
    private String company;


    //default constructor

    public Contacts(){

    }

    //constructor
    public Contacts(String firstname, String lastname, String address, String phoneNumber, String company) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.company = company;
    }

    public int get_id() {
        return _id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() { return  address;}

    public String getPhoneNumber() { return  phoneNumber;}

    public String getCompany() { return  company;}

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
