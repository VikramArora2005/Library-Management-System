package org.example.service;
import java.sql.*;
import org.example.model.*;
import org.example.util.DButil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class GeneralServices {
    public List<Book> viewAllBooks(){
        List<Book> allBooks = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");

                Book book = new Book(id, title, author, quantity);
                allBooks.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allBooks;
    }
    public Member getMemberDetails(int id){
        Member m = null;
        String sql = "SELECT * FROM members WHERE id = ?";

        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int memberId = rs.getInt("id");
                String memberName = rs.getString("name");
                String contact = rs.getString("contact");

                List<Book> memberBooks = viewMemberBooks(memberId);
                ArrayList<Integer> memberBookIds = new ArrayList<>();
                for (Book book : memberBooks) {
                    memberBookIds.add(book.getId());
                }

                m = new Member(memberId,memberName,contact,memberBookIds);
            }

        } catch (SQLException e) {
            System.out.println("cant recieve member details" + e.getMessage());
        }

        return m;
    }

    public Book getBookById(int id) {
        Book book = null;
        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");

                book = new Book(bookId, title, author, quantity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book; // returns null if no book found
    }

    public List<Member> viewAllMembers(){
        List<Member> allMembers = new ArrayList<>();
        String sql = "SELECT * FROM members";

        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String contact = rs.getString("contact");


                List<Book> memberBooks = viewMemberBooks(id);
                ArrayList<Integer> memberBookIds = new ArrayList<Integer>();
                for (Book book : memberBooks) {
                    memberBookIds.add(book.getId());
                }
                Member member = new Member(id, name, contact,memberBookIds);
                allMembers.add(member);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allMembers;
    }

    public List<Book> viewMemberBooks(int memberId){
        List<Book> mBooks = new ArrayList<>();
        String sql = "SELECT book_id FROM member_books WHERE member_id = ?";

        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, memberId);

             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("book_id");
                Book book = getBookById(id);
                mBooks.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mBooks;
    }

    public boolean checkMember(int memberId) {
        String sql = "SELECT COUNT(*) FROM members WHERE id = ?";
        boolean exists = false;

        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, memberId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    exists = (count > 0);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }


    public List<Transaction> viewAllTransactions(){
        List<Transaction> allTransactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";

        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String transaction_id = rs.getString("transaction_id");
                int bookId = rs.getInt("book_id");
                int memberId = rs.getInt("member_id");
                LocalDate transactionDate = rs.getDate("transaction_date").toLocalDate();
                String transactionType = rs.getString("transaction_type");




                Transaction t = new Transaction(transaction_id,memberId,bookId,transactionType,transactionDate);
                allTransactions.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allTransactions;
    }




}



