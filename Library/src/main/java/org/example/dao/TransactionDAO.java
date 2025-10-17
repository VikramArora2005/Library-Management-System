package org.example.dao;


import org.example.model.Member;
import org.example.model.Book;
import org.example.model.Transaction;
import org.example.util.DButil;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class TransactionDAO {
        int quantityRetrieved;
        public int getQty(String sql,Book book){
            try(Connection conn = DButil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1, book.getId());

                ResultSet rs = stmt.executeQuery();
                while (rs.next()){
                    quantityRetrieved   = rs.getInt(1);
                }

            }catch(SQLException e){
                System.out.println("Couldn't get quantity");
            }
            return quantityRetrieved;
        }


        public void changeBookQty(String sql, Book book){
            try(Connection conn = DButil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1,book.getId());
                stmt.executeUpdate();
            }catch(SQLIntegrityConstraintViolationException e){
                System.out.println("Book or Member Doesn't exists");
            }catch(SQLException e){
                System.out.println(e);
            }
        }

        public void addTransaction(String sql, Book book,Member member,Transaction transaction){
            try (Connection conn = DButil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setString(1,transaction.getTransactionId());
                stmt.setInt(2,book.getId());
                stmt.setInt(3,member.getId());
                Date sqlDate = Date.valueOf(transaction.getTransactionDate());
                stmt.setDate(4,sqlDate);
                stmt.setString(5, transaction.getTransactionType().toUpperCase());

                int rowsEffected  = stmt.executeUpdate();
                if (rowsEffected > 0){
                    System.out.println(transaction.getTransactionType().toUpperCase() + "TRANSACTION SUCCESSFUL");
                } else{
                    System.out.println(transaction.getTransactionType().toUpperCase() + "TRANSACTION FAILED");
                }
            }catch(SQLIntegrityConstraintViolationException e){
                System.out.println("Book or Member Doesn't exists");
            }
            catch(SQLException e){
                System.out.println("Transaction addition to database failed");
                e.printStackTrace();

            }
        }


        public void addToMemberBooks(String sql, Book book,Member member){
            try(Connection conn = DButil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)){
                stmt.setInt(1,member.getId());
                stmt.setInt(2,book.getId());
                stmt.executeUpdate();
            }catch(SQLIntegrityConstraintViolationException e){
                System.out.println("Book or Member Doesn't exists");
            }catch(SQLException e){
                System.out.println("Failed to insert book");
            }
        }


}
