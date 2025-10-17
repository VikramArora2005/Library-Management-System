package org.example.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int quantity;

    public Book(){}

    public Book(int id, String title, String author , int quantity){
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }
    public Book(String title, String author , int quantity){

        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }


    //GETTERS
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | Qty: " + quantity;
    }


}
