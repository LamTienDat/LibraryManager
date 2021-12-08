package com.example.librarymanage;

public class TableModel {
    private String customerID;
    private String bookID;
    private String borrowDate;
    private String returnDate;

    public TableModel(String customerID, String bookID, String borrowDate, String returnDate) {
        this.bookID = bookID;
        this.customerID = customerID;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public String getBookID() {
        return bookID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getBorrowDate() {
        return borrowDate;
    }
}

