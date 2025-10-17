package org.example.controller;
import org.example.model.Book;
import org.example.model.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.model.Transaction;
import org.example.service.StaffServices;
import org.example.service.MemberServices;
import org.example.service.GeneralServices;

public class CLI {
    StaffServices staffService = new StaffServices();
    MemberServices memberService = new MemberServices();
    GeneralServices generalService = new GeneralServices();
    Scanner sc =  new Scanner(System.in);

    //HOME PAGE
    public void HomePageCLI(){
        int choice;
        while(true){
            System.out.println("======= LIBRARY MANAGEMENT SYSTEM =======");
            System.out.println("1. STAFF");
            System.out.println("2. MEMBER");
            choice = sc.nextInt();
            sc.nextLine();

            try{
                switch(choice){
                    case 1 -> StaffCLI();
                    case 2 -> MemberLoginCLI();
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("Error occured");
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

            choice = sc.nextInt();
            sc.nextLine();

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
                System.out.println("Error occured");
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
        int qty = sc.nextInt();
        Book book = new Book(title, author, qty);
        staffService.addBook(book);
        StaffCLI();
    }
    private void removeBookCLI(){
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt(); sc.nextLine();
        staffService.removeBook(id);
        StaffCLI();
    }
    private void newMemberCLI(){
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Contact: ");
        String contact = sc.nextLine();
        Member member = new Member(name,contact);
        staffService.addMember(member);
        StaffCLI();
    }
    private void removeMemberCLI(){
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt(); sc.nextLine();
        staffService.removeMember(id);
        StaffCLI();
    }

    //MEMBER LOGIN CLI

    private void MemberLoginCLI(){
        System.out.print("Enter your ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean b = generalService.checkMember(id);
        if(b){
            MemberCLI(id);
        }
        else{
        System.out.println("Invalid Member ID");
        HomePageCLI();
        }


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

            choice = sc.nextInt();
            sc.nextLine();

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
                System.out.println("Error occured");
            }
        }

    }

    private void issueBookCLI(Member m){
        System.out.print("Enter Book ID: ");
        int book_id = sc.nextInt(); sc.nextLine();
        Book b = generalService.getBookById(book_id);
        memberService.transaction(m,b,"Issue");
        MemberCLI(m.getId());

    }
    private void returnBookCLI(Member m){
        System.out.print("Enter Book ID: ");
        int book_id = sc.nextInt(); sc.nextLine();
        Book b = generalService.getBookById(book_id);
        memberService.transaction(m,b,"Return");
        MemberCLI(m.getId());

    }

    private void allBooksCLI(Member m){
        List<Book> books =  generalService.viewAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found in database.");
        } else {
            System.out.println("📚 List of Books:");
            for (Book b : books) {
                System.out.println(b);
            }
        }
        int choice;
        while(true){
            System.out.println("PRESS 1 to GO BACK");
            choice = sc.nextInt();
            sc.nextLine();

            try{
                switch(choice){
                    case 1 -> MemberCLI(m.getId());
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("Error occured");
            }
        }

    }

    private void allBooksCLI(){
        List<Book> books =  generalService.viewAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found in database.");
        } else {
            System.out.println("📚 List of Books:");
            for (Book b : books) {
                System.out.println(b);
            }
        }
        int choice;
        while(true){
            System.out.println("PRESS 1 to GO BACK");
            choice = sc.nextInt();
            sc.nextLine();

            try{
                switch(choice){
                    case 1 -> StaffCLI();
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("Error occured");
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
            choice = sc.nextInt();
            sc.nextLine();

            try{
                switch(choice){
                    case 1 -> StaffCLI();
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("Error occured");
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
            choice = sc.nextInt();
            sc.nextLine();

            try{
                switch(choice){
                    case 1 -> MemberCLI(m.getId());
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("No books");
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
            choice = sc.nextInt();
            sc.nextLine();

            try{
                switch(choice){
                    case 1 -> StaffCLI();
                    default -> System.out.println("Invalid choice");
                }
            }catch(Exception e){
                System.out.println("Error occured");
            }
        }
    }







}
