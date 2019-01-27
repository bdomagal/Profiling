package com.example.demo.persistance;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Book {
    private Integer idBook;
    private Date dateOfRelease;
    private String description;
    private String img;
    private String title;

    @Id
    @Column(name = "id_book", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    @Basic
    @Column(name = "date_of_release", nullable = true)
    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 1000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "img", nullable = true, length = 255)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (idBook != null ? !idBook.equals(book.idBook) : book.idBook != null) return false;
        if (dateOfRelease != null ? !dateOfRelease.equals(book.dateOfRelease) : book.dateOfRelease != null)
            return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (img != null ? !img.equals(book.img) : book.img != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBook != null ? idBook.hashCode() : 0;
        result = 31 * result + (dateOfRelease != null ? dateOfRelease.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
