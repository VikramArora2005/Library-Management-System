package org.example.controller;
import org.example.constants.AppErrorCode;
import org.example.constants.AppSuccessCode;
import org.example.exception.AppException;
import org.example.model.Book;
import org.example.model.Member;

import java.util.List;
import java.util.Scanner;

import org.example.model.Transaction;
import org.example.service.StaffServices;
import org.example.service.TransactionServices;
import org.example.service.GeneralServices;


public class CLI {
    StaffServices staffService = new StaffServices();
    TransactionServices memberService = new TransactionServices();
    GeneralServices generalService = new GeneralServices();
    AppErrorCode error = new AppErrorCode();
    AppSuccessCode success = new AppSuccessCode();
    Scanner sc =  new Scanner(System.in);


    private int getIntInput() {
        while (true) {
            String input = sc.nextLine();
            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            }
            System.out.print("Invalid input. Enter a number: ");
        }
    }


    //HOME PAGE
    public void HomePageCLI(){



        int choice;
        while(true){
            System.out.println("======= LIBRARY MANAGEMENT SYSTEM =======");
            System.out.println("1. STAFF");
            System.out.println("2. MEMBER");
            choice = getIntInput();

            try{
                switch(choice){
                    case 1 -> StaffCLI();
                    case 2 -> MemberLoginCLI();
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("ERROR");
            }
        }

    }

    //StaffCLI PAGE
    private void StaffCLI(){
        int choice;
        while(true){
            System.out.println("1. ADD BOOK");
            System.out.println("2. REMOVE BOOK");
            System.out.println("3. ADD MEMBER");
            System.out.println("4. REMOVE MEMBER");
            System.out.println("5. VIEW ALL BOOKS");
            System.out.println("6. VIEW ALL MEMBERS");
            System.out.println("7. VIEW ALL TRANSACTIONS");
            System.out.println("8. GO BACK");

            choice = getIntInput();


            try{
                switch(choice){
                    case 1 -> addBookCLI();
                    case 2 -> removeBookCLI();
                    case 3 -> newMemberCLI();
                    case 4 -> removeMemberCLI();
                    case 5 -> allBooksCLI();
                    case 6 -> allMembersCLI();
                    case 7 -> allTransactionsCLI();
                    case 8 -> HomePageCLI();
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("ERROR OCCRED WHILE CHOOSING");
            }
        }
    }
    //StaffCLI PARTS
    private void addBookCLI(){
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = getIntInput();
        Book book = new Book(title, author, qty);
        try{
            staffService.addBook(book);
        } catch(AppException e){

            System.out.println("MESSAGE "+e.getCode()+": "+e.getMessage());
        }
        StaffCLI();
    }
    private void removeBookCLI(){
        System.out.print("Enter Book ID: ");
        int id = getIntInput();
        try{
            staffService.removeBook(id);
        } catch(AppException e){
            System.out.println("MESSAGE "+e.getCode()+": "+e.getMessage());
        }
        StaffCLI();
    }
    private void newMemberCLI(){
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();
        Member member = new Member(name,contact);
        try{
            staffService.addMember(member);
        } catch(AppException e){
            System.out.println("MESSAGE "+e.getCode()+": "+e.getMessage());
        }

        StaffCLI();
    }
    private void removeMemberCLI(){
        System.out.print("Enter Member ID: ");
        int id = getIntInput();
        try{
            staffService.removeMember(id);
        } catch(AppException e){
            System.out.println("MESSAGE "+e.getCode()+": "+e.getMessage());
        }
        StaffCLI();
    }

    //MEMBER LOGIN CLI

    private void MemberLoginCLI(){
        System.out.print("Enter your ID: ");
        int id = getIntInput();

        boolean exists = generalService.checkMember(id);

        if(!exists){
            System.out.println("MESSAGE " + error.getMemberNotFoundErrorCode()
                    + ": " + error.getMemberNotFoundErrorMsg());
            return; // do NOT call HomePageCLI() inside MemberLoginCLI()
        }

        MemberCLI(id);  // only call if exists
    }






    //MEMBER CLI PAGE
    private void MemberCLI(int id){
        Member m = generalService.getMemberDetails(id);

        int choice;
        while(true){
            System.out.println("1. VIEW ALL BOOKS: ");
            System.out.println("2. VIEW MY BOOKS: ");
            System.out.println("3. ISSUE BOOK: ");
            System.out.println("4. RETURN BOOK: ");
            System.out.println("5. GO BACK");

            choice = getIntInput();

            try{
                switch(choice){
                    case 1 -> allBooksCLI(m);
                    case 2 -> memberBooksCLI(m);
                    case 3 -> issueBookCLI(m);
                    case 4 -> returnBookCLI(m);
                    case 5 -> HomePageCLI();
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("ERROR OCCRED WHILE CHOOSING");

            }
        }

    }

    private void issueBookCLI(Member m){
        System.out.print("Enter Book ID: ");
        int book_id = getIntInput();

        try{
            try{
                Book b = generalService.getBookById(book_id);
                memberService.transaction(m,b,"Issue");
            }catch (AppException e){
                System.out.println("MESSAGE "+e.getCode()+": "+e.getMessage());
            }catch(Exception e){
                throw new AppException(error.getBookNotFoundErrorCode(), error.getBookNotFoundErrorMsg());
            }
        }catch(AppException e){
            System.out.println("MESSAGE "+e.getCode()+": "+e.getMessage());
        }

        MemberCLI(m.getId());

    }
    private void returnBookCLI(Member m){
        System.out.print("Enter Book ID: ");
        int book_id = getIntInput();

        try{
            try{
                Book b = generalService.getBookById(book_id);
                memberService.transaction(m,b,"Return");
            }catch (AppException e){
                System.out.println("MESSAGE "+e.getCode()+": "+e.getMessage());
            }catch(Exception e){
                throw new AppException(error.getBookNotFoundErrorCode(), error.getBookNotFoundErrorMsg());
            }
        }catch(AppException e){
            System.out.println("MESSAGE "+e.getCode()+": "+e.getMessage());
        }

        MemberCLI(m.getId());

    }

    private void allBooksCLI(Member m){
        List<Book> books =  generalService.viewAllBooks();
        try{
            if (books.isEmpty()) {
                throw new AppException(error.getNoBooksFoundErrorCode(),error.getNoBooksFoundErrorMsg());
            } else {
                System.out.println("📚 List of Books:");
                for (Book b : books) {
                    System.out.println(b);
                }
            }

        }catch(AppException e){
            System.out.println("MESSAGE "+e.getCode()+": "+e.getMessage());
        }
        int choice;
        while(true){
            System.out.println("PRESS 1 to GO BACK");
            choice = getIntInput();

            try{
                switch(choice){
                    case 1 -> MemberCLI(m.getId());
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("CANNOT GO BACK");   //EXCEPTION
            }
        }

    }

    private void allBooksCLI(){
        List<Book> books =  generalService.viewAllBooks();
        try{
            if (books.isEmpty()) {
                throw new AppException(error.getNoBooksFoundErrorCode(),error.getNoBooksFoundErrorMsg());
            } else {
                System.out.println("📚 List of Books:");
                for (Book b : books) {
                    System.out.println(b);
                }
            }

        }catch(AppException e){
            System.out.println("MESSAGE "+e.getCode()+": "+e.getMessage());
        }

        int choice;
        while(true){
            System.out.println("PRESS 1 to GO BACK");
            choice = getIntInput();

            try{
                switch(choice){
                    case 1 -> StaffCLI();
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("CANNOT GO BACK"); //EXCEPTION
            }
        }

    }

    private void allMembersCLI(){
        List<Member> members =  generalService.viewAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found in database.");
        } else {
            System.out.println("List of Members:");
            for (Member m : members) {
                System.out.println(m);
            }
        }
        int choice;
        while(true){
            System.out.println("PRESS 1 to GO BACK");
            choice = getIntInput();

            try{
                switch(choice){
                    case 1 -> StaffCLI();
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("CANNOT GO BACK"); //EXCEPTION
            }
        }
    }

    public void memberBooksCLI(Member m){
        List<Book> books =  generalService.viewMemberBooks(m.getId());
        if (books.isEmpty()) {
            System.out.println("You have no books issued. ");
        } else {
            System.out.println("📚 List of your Books:");
            for (Book b : books) {
                System.out.println(b);
            }
        }
        int choice;
        while(true){
            System.out.println("PRESS 1 to GO BACK");
            choice = getIntInput();

            try{
                switch(choice){
                    case 1 -> MemberCLI(m.getId());
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("CANNOT GO BACK"); //EXCEPTION
            }
        }

    }


    private void allTransactionsCLI(){
        List<Transaction> transactions =  generalService.viewAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found in database.");
        } else {
            System.out.println("List of Transactions:");
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
        int choice;
        while(true){
            System.out.println("PRESS 1 to GO BACK");
            choice = getIntInput();

            try{
                switch(choice){
                    case 1 -> StaffCLI();
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("CANNOT GO BACK"); //EXCEPTION
            }
        }
    }







}
