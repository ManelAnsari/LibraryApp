package com.ndroid.libraryapp;

import java.io.Serializable;

public class Book implements Serializable {

    private int id;
    private String title;
    private String author;
    private String image;

    public Book() {
    }

    public Book(int id, String title, String author, String image) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
