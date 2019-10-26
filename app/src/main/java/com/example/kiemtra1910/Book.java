package com.example.kiemtra1910;

import java.util.Date;

public class Book {
     int author_id;
      String title;
     int book_id;

    public Book(int author_id, String title, int book_id) {
        this.author_id = author_id;
        this.title = title;
        this.book_id = book_id;
    }
    public Book(){
        this.book_id=0;
        this.title=null;
        this.author_id=0;

    }


    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }










}
