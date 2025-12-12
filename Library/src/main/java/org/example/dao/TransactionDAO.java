package org.example.dao;

import org.example.exception.*;
import org.example.model.Member;
import org.example.model.Book;
import org.example.model.Transaction;

import java.sql.*;
import org.example.constants.*;

public class TransactionDAO {
        AppErrorCode error = new AppErrorCode();


        int quantityRetrieved;
        public int getQty(Connection conn,String sql,Book book){
            try{
                try(PreparedStatement stmt = conn.prepareStatement(sql)){
                    stmt.setInt(1, book.getId());

                    ResultSet rs = stmt.executeQuery();

                    while (rs.next()){
                        quantityRetrieved   = rs.getInt(1);
                    }

                }catch(SQLException e){
                    throw new AppException(1001, "Error while fetching book quantity");
                }
            }catch(AppException e){
                System.out.println("SUB MESSAGE "+ e.getCode()+": "+e.getMessage());
            }
            return quantityRetrieved;
        }


        public void changeBookQty(Connection conn,String sql, Book book){
            try{
                try(PreparedStatement stmt = conn.prepareStatement(sql)){
                    stmt.setInt(1,book.getId());
                    stmt.executeUpdate();
                }catch(SQLException e){
                    throw new AppException(1003, "Error changing book quantity");

                }
            }catch(AppException e){
                System.out.println("SUB MESSAGE "+ e.getCode()+": "+e.getMessage());
            }

        }

        public void addTransaction(Connection conn ,String sql, Book book,Member member,Transaction transaction){
            try{
                try (PreparedStatement stmt = conn.prepareStatement(sql)){
                    stmt.setString(1,transaction.getTransactionId());
                    stmt.setInt(2,book.getId());
                    stmt.setInt(3,member.getId());
                    Date sqlDate = Date.valueOf(transaction.getTransactionDate());
                    stmt.setDate(4,sqlDate);
                    stmt.setString(5, transaction.getTransactionType().toUpperCase());

                    int rowsEffected  = stmt.executeUpdate();
                    if (rowsEffected > 0){
                        throw new AppException(AppSuccessCode.getTransactionSuccessfulCode(), AppSuccessCode.getTransactionSuccessfulMsg());

                    } else{
                        throw new AppException(error.getTransactionFailedErrorCode(),error.getTransactionFailedErrorMsg());

                    }
                }
                catch(SQLException e){
                    throw new AppException(error.getTransactionFailedErrorCode(),error.getTransactionFailedErrorMsg());


                }
            }catch(AppException e){
                System.out.println("MESSAGE "+ e.getCode()+ ": "+ transaction.getTransactionType().toUpperCase() + " "+ e.getMessage());
            }
        }


        public void addToMemberBooks(Connection conn, String sql, Book book,Member member){
            try{
                try(PreparedStatement stmt = conn.prepareStatement(sql)){
                    stmt.setInt(1,member.getId());
                    stmt.setInt(2,book.getId());
                    stmt.executeUpdate();
                }catch(SQLException e){
                    throw new AppException(1004, "Failed to insert book");
                }
            }catch(AppException e){
                System.out.println("SUB MESSAGE "+ e.getCode()+": "+e.getMessage());
            }
        }


}
