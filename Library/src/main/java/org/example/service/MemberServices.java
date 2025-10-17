package org.example.service;

import org.example.model.Member;
import org.example.model.Book;
import org.example.model.Transaction;
import org.example.util.DButil;
import org.example.dao.TransactionDAO;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberServices {

    TransactionDAO transactionDAO = new TransactionDAO();
    public String generateTransactionID(int memberID, int bookID, String transactionType){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String dateTime = dtf.format(LocalDateTime.now());
        String transactionTypeinID;
        if (transactionType.equalsIgnoreCase("issue")){
            transactionTypeinID = "ISS";
        }else{
            transactionTypeinID = "RET";
        }
        return transactionTypeinID + dateTime + "-M" + memberID + "-B" + bookID;

    }

    public Transaction transaction(Member member,Book book,String transactionType) {
        //GET BOOK QUANTITY
        int quantityRetrieved =-1;
        String sql4 = "SELECT QUANTITY FROM BOOKS WHERE ID = ?";
        quantityRetrieved = transactionDAO.getQty(sql4,book);
        //MAKE TRANSACTION OBJECT
        Transaction transaction = new Transaction();

        //MAIN QTY CONDITIONING ON ISSUE
        if (transactionType.equalsIgnoreCase("issue") && quantityRetrieved == 0){
            System.out.println("This book cannot be issued, NO BOOKS LEFT");
        }else{

            //Book QUANTITY CHANGE
            String sql3;
            if (transactionType.equalsIgnoreCase("issue")) {
                int newQuantity = book.getQuantity() - 1;
                book.setQuantity(newQuantity);
                sql3 = "UPDATE books SET quantity = quantity - 1 WHERE id = ?";
            }else{
                int newQuantity = book.getQuantity() + 1;
                book.setQuantity(newQuantity);
                sql3 = "UPDATE books SET quantity = quantity + 1 WHERE id = ?";
            }
            transactionDAO.changeBookQty(sql3,book);



            //TRANSACTION OBJECT DEFINED

            String transactionID = generateTransactionID(member.getId(),book.getId(),"issue");
            transaction.setTransactionId(transactionID);
            transaction.setMemberId(member.getId());
            transaction.setBookId(book.getId());
            transaction.setTransactionType(transactionType.toUpperCase());
            LocalDate transactionDate = LocalDate.now();
            transaction.setTransactionDate(transactionDate);

            //Adding Transaction to database
            String sql1 = "INSERT INTO TRANSACTIONS (transaction_ID,book_ID,member_ID,transaction_date,transaction_type) VALUES (?,?,?,?,?)";
            transactionDAO.addTransaction(sql1,book,member,transaction);




            //Adding to member_books
            String sql2;
            if (transactionType.equalsIgnoreCase("issue")){

                sql2 = "INSERT INTO MEMBER_BOOKS (member_id,book_id) VALUES(?,?)";
            }else{

                sql2= "DELETE FROM MEMBER_BOOKS WHERE member_id=? AND book_id=?";
            }
            transactionDAO.addToMemberBooks(sql2,book,member);
        }

        return transaction;
    }

}
