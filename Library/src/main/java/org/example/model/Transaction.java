package org.example.model;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.time.LocalDate;
public class Transaction {
    private String transactionId;
    private int memberId;
    private int bookId;
    private String transactionType; // "borrow" or "return"
    private LocalDate transactionDate;

    // Default constructor
    public Transaction() {
    }

    // Parameterized constructor
    public Transaction(String transactionId, int memberId, int bookId, String transactionType, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    // Override toString
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", memberId=" + memberId +
                ", bookId=" + bookId +
                ", transactionType='" + transactionType + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                '}';
    }
}

