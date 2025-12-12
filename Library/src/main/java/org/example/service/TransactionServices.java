package org.example.service;

import org.example.exception.AppException;
import org.example.constants.AppErrorCode;
import org.example.model.Member;
import org.example.model.Book;
import org.example.model.Transaction;
import org.example.dao.TransactionDAO;
import org.example.util.DButil;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class TransactionServices {
    AppErrorCode error = new AppErrorCode();


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

    public Transaction transaction(Member member, Book book, String transactionType) throws AppException {

        String sqlQty   = "SELECT quantity FROM books WHERE id = ?";
        String sqlTrans = "INSERT INTO transactions (transaction_id, book_id, member_id, transaction_date, transaction_type) VALUES (?,?,?,?,?)";

        String sqlBookQty;
        String sqlMemberBooks;

        if (transactionType.equalsIgnoreCase("issue")) {
            sqlBookQty = "UPDATE books SET quantity = quantity - 1 WHERE id = ?";
            sqlMemberBooks = "INSERT INTO member_books (member_id, book_id) VALUES (?,?)";
        } else {
            sqlBookQty = "UPDATE books SET quantity = quantity + 1 WHERE id = ?";
            sqlMemberBooks = "DELETE FROM member_books WHERE member_id=? AND book_id=?";
        }
        Connection conn = null;
        try {
            conn = DButil.getConnection();
            conn.setAutoCommit(false);

            // Check quantity
            int qty = transactionDAO.getQty(conn, sqlQty, book);
            if (transactionType.equalsIgnoreCase("issue") && qty <= 0) {
                throw new AppException(error.getBookIssueErrorCode(), error.getBookIssueErrorMsg());
            }

            //  Update book qty
            transactionDAO.changeBookQty(conn, sqlBookQty, book);

            // Insert transaction
            Transaction transaction = new Transaction();
            transaction.setTransactionId(generateTransactionID(member.getId(), book.getId(), transactionType));
            transaction.setMemberId(member.getId());
            transaction.setBookId(book.getId());
            transaction.setTransactionType(transactionType.toUpperCase());
            transaction.setTransactionDate(LocalDate.now());

            transactionDAO.addTransaction(conn, sqlTrans, book, member, transaction);

            // Modify MEMBER_BOOKS
            transactionDAO.addToMemberBooks(conn, sqlMemberBooks, book, member);

            conn.commit();   // commit
            return transaction;

        }catch (Exception e) {

            if (conn != null) {
                try {
                    conn.rollback();   // ROLLBACK EVERYTHING
                } catch (SQLException ex) {
                    System.out.println("Rollback failed: " + ex.getMessage());
                }
            }

            throw new AppException(error.getTransactionFailedErrorCode(), error.getTransactionFailedErrorMsg());

        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // reset connection
                    conn.close();
                } catch (SQLException ignore) {}
            }
        }
    }


}
