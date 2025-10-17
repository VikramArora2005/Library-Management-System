package org.example.service;

import org.example.model.Member;
import org.example.util.DButil;
import org.example.model.Book;
import java.sql.*;

//Staff services/dao


public class StaffServices {

    //MEMBER DAO

    public void addMember(Member member){
        String sql  = "INSERT INTO members (name,contact) VALUES (?,?)";
        try(Connection conn = DButil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){


            stmt.setString(1,member.getName());
            stmt.setString(2,member.getContact());

            int rowsExecuted = stmt.executeUpdate();
            if (rowsExecuted > 0){
                System.out.println("MEMBER ADDED SUCCESSFULLY");
            }else{
                System.out.println("MEMBER ADDITION FAILED");
            }


        }catch(SQLException e){
            System.out.println("MEMBER ADDITION FAILED");
            e.printStackTrace();
        }
    }
    public void removeMember(int member_id){
        String sql  = "DELETE FROM members WHERE id = ?";
        try(Connection conn = DButil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1,member_id);
            int rowsExecuted = stmt.executeUpdate();
            if (rowsExecuted > 0){
                System.out.println("MEMBER DELETED SUCCESSFULLY");
            }else{
                System.out.println("MEMBER DELETION FAILED");
            }

        }catch(SQLException e){
            System.out.println("MEMBER DELETION FAILED");
            
        }
    }

    //BOOK DAO

    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getQuantity());

            int rowsExecuted = stmt.executeUpdate();

            if (rowsExecuted > 0) {
                System.out.println("Book added successfully");
            }else{
                System.out.println("Book addition failed");
            }
        } catch (SQLException e) {
            System.out.println("Book addition failed");
        }
    }

    public void removeBook(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection conn = DButil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("SET FOREIGN_KEY_CHECKS=0");  // disable temporarily
            int rowsExecuted = stmt.executeUpdate("DELETE FROM books WHERE id = " + id);
            stmt.execute("SET FOREIGN_KEY_CHECKS=1");  // re-enable


            if (rowsExecuted > 0) {
                System.out.println("Book deleted successfully");
            }else{
                System.out.println("Book deletion failed");
            }
        }catch(SQLIntegrityConstraintViolationException e){
            System.out.println(e);
            System.out.println("This book is issued, Cannot remove this book");
        }
        catch (SQLException e) {

            System.out.println("Book deletion failed");
        }



    }
}
