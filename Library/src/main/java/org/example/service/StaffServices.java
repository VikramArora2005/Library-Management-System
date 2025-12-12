package org.example.service;

import org.example.exception.AppException;
import org.example.model.Member;
import org.example.util.DButil;
import org.example.model.Book;
import java.sql.*;
import org.example.exception.AppException;
import org.example.constants.AppSuccessCode;
import org.example.constants.AppErrorCode;

//Staff services/dao


public class StaffServices {

    AppErrorCode error = new  AppErrorCode();
    AppSuccessCode success = new  AppSuccessCode();

    //MEMBER DAO

    public void addMember(Member member) throws AppException{
        String sql  = "INSERT INTO members (name,contact) VALUES (?,?)";
        try(Connection conn = DButil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){


            stmt.setString(1,member.getName());
            stmt.setString(2,member.getContact());

            int rowsExecuted = stmt.executeUpdate();
            if (rowsExecuted > 0){
                throw new AppException(success.getMemberAdditionSuccessfulCode(),success.getMemberAdditionSuccessfulMsg());
            }else{
                throw new AppException(error.getMemberAdditionErrorCode(),error.getMemberAdditionErrorMsg());
            }


        }catch(SQLException e){
            throw new AppException(error.getMemberAdditionErrorCode(),error.getMemberAdditionErrorMsg());
        }
    }
    public void removeMember(int member_id)throws AppException{
        String sql  = "DELETE FROM members WHERE id = ?";
        try(Connection conn = DButil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1,member_id);
            int rowsExecuted = stmt.executeUpdate();
            if (rowsExecuted > 0){
                throw new AppException(success.getMemberDeletionSuccessfulCode(),success.getMemberDeletionSuccessfulMsg());
            }else{
                throw new AppException(error.getMemberDeletionErrorCode(),error.getMemberDeletionErrorMsg());

            }

        }catch(SQLException e){
            throw new AppException(error.getMemberDeletionErrorCode(),error.getMemberDeletionErrorMsg());
            
        }
    }

    //BOOK DAO

    public void addBook(Book book) throws AppException{
        String sql = "INSERT INTO books (title, author, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DButil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getQuantity());

            int rowsExecuted = stmt.executeUpdate();

            if (rowsExecuted > 0) {
                throw new AppException(AppSuccessCode.getBookAdditionSuccessfulCode(), AppSuccessCode.getBookAdditionSuccessfulMsg());
            }else{
                throw new AppException(error.getBookAdditionErrorCode(), error.getBookAdditionErrorMsg());
            }
        } catch (SQLException e) {
            throw new AppException(error.getBookAdditionErrorCode(), error.getBookAdditionErrorMsg());
        }
    }
    private boolean isBookIssued(int bookId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM member_books WHERE book_id = ?";
        try (Connection conn = DButil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;  // true if issued
        }
    }

    public void removeBook(int id) throws AppException{
        try (Connection conn = DButil.getConnection()){
            if (isBookIssued(id)) {
                throw new AppException(
                        error.getBookDeletionCauseIssuedErrorCode(),
                        error.getBookDeletionCauseIssuedErrorMsg()
                );
            }
            String sql = "DELETE FROM books WHERE id = ?";
            try (Statement stmt = conn.createStatement()) {

                stmt.execute("SET FOREIGN_KEY_CHECKS=0");  // disable temporarily
                int rowsExecuted = stmt.executeUpdate("DELETE FROM books WHERE id = " + id);
                stmt.execute("SET FOREIGN_KEY_CHECKS=1");  // re-enable


                if (rowsExecuted > 0) {
                    throw new AppException(AppSuccessCode.getBookDeletionSuccessfulCode(), AppSuccessCode.getBookDeletionSuccessfulMsg());
                }else{
                    throw new AppException(error.getBookDeletionErrorCode(), error.getBookDeletionErrorMsg());
                }
            }catch(SQLIntegrityConstraintViolationException e){
                throw new AppException(AppErrorCode.getBookDeletionCauseIssuedErrorCode(), error.getBookDeletionCauseIssuedErrorMsg());
            }
            catch (SQLException e) {
                throw new AppException(error.getBookDeletionErrorCode(), error.getBookDeletionErrorMsg());
            }
        }catch(SQLException e){
            throw new AppException(error.getBookDeletionErrorCode(),error.getBookDeletionErrorMsg());
        }






    }
}
