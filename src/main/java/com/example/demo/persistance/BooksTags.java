package com.example.demo.persistance;

import javax.persistence.*;

@Entity
@Table(name = "books_tags", schema = "user_profiling")
@IdClass(BooksTagsPK.class)
public class BooksTags {
    private String tagName;
    private Integer idBook;
    private Book bookByIdBook;

    @Id
    @Column(name = "tag_name", nullable = false, length = 45)
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Id
    @Column(name = "id_book", nullable = false)
    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BooksTags booksTags = (BooksTags) o;

        if (tagName != null ? !tagName.equals(booksTags.tagName) : booksTags.tagName != null) return false;
        if (idBook != null ? !idBook.equals(booksTags.idBook) : booksTags.idBook != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagName != null ? tagName.hashCode() : 0;
        result = 31 * result + (idBook != null ? idBook.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_book", referencedColumnName = "id_book", nullable = false, insertable = false, updatable = false)
    public Book getBookByIdBook() {
        return bookByIdBook;
    }

    public void setBookByIdBook(Book bookByIdBook) {
        this.bookByIdBook = bookByIdBook;
    }
}
