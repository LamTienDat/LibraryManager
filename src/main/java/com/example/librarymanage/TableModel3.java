package com.example.librarymanage;

public class TableModel3 {
    private String bookID;
    private String bookName;
    private String author;
    private String publisher;
    private Integer amount;

    public TableModel3(String bookID, String bookName, String author, String publisher, Integer amount) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.amount = amount;
    }

    public String getBookID() {
        return bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getAmount() {
        return amount;
    }

}

