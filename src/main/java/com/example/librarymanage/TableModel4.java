package com.example.librarymanage;

public class TableModel4 {
    private String customerID;
    private String Name;
    private String Address;
    private String PhoneNumber;
    private Integer Amount;

    public TableModel4(String customerID, String Name, String Address, String PhoneNumber, Integer Amount) {
        this.customerID = customerID;
        this.Name = Name;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
        this.Amount = Amount;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getName() {
        return Name;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public Integer getAmount() {
        return Amount;
    }
}
