package org.example.model;
import java.util.ArrayList;
public class Member {
    private int id;
    private String name;
    private String contact;
    private ArrayList<Integer> booksOwned =  new ArrayList<Integer>();

    // Default constructor
    public Member() {
    }

    // Parameterized constructor
    public Member(int id, String name, String contact, ArrayList<Integer> booksOwned) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.booksOwned = booksOwned;

    }

    public Member(String name, String contact) {

        this.name = name;
        this.contact = contact;

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public ArrayList<Integer> addBook(Integer book_id){
        this.booksOwned.add(book_id);
        return booksOwned;
    }
    public ArrayList<Integer> removeBook(Integer book_id){
        this.booksOwned.removeIf(b -> b.equals(book_id));
        return booksOwned;
    }


    // Override toString
    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contact=" + contact +
                ", booksOwned=" + booksOwned.toString() +
                '}';
    }
}

