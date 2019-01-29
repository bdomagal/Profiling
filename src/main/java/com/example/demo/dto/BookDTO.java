package com.example.demo.dto;

import com.example.demo.persistance.Book;

import java.util.ArrayList;
import java.util.Collection;

public class BookDTO {

    private String title;
    private boolean liked;
    private boolean avoid;
    private boolean recommended;
    private int id;
    private int timeSpent;
    private int timesVisited;

    public int getTimesVisited() {
        return timesVisited;
    }

    public void setTimesVisited(int timesVisited) {
        this.timesVisited = timesVisited;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
    }

    public BookDTO(Book book) {
        title = book.getTitle();
        id = book.getIdBook();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isAvoid() {
        return avoid;
    }

    public void setAvoid(boolean avoid) {
        this.avoid = avoid;
    }

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookDTO() {
    }

    public static ArrayList<BookDTO> convertToDtos(Collection<Book> books){
        ArrayList<BookDTO> bookDtos = new ArrayList<>();
        for (Book book : books) {
            bookDtos.add(new BookDTO(book));
        }
        return bookDtos;
    }
}
