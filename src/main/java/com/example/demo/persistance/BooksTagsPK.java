package com.example.demo.persistance;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class BooksTagsPK implements Serializable {
    private String tagName;
    private Integer idBook;

    @Column(name = "tag_name", nullable = false, length = 45)
    @Id
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Column(name = "id_book", nullable = false)
    @Id
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

        BooksTagsPK that = (BooksTagsPK) o;

        if (tagName != null ? !tagName.equals(that.tagName) : that.tagName != null) return false;
        if (idBook != null ? !idBook.equals(that.idBook) : that.idBook != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagName != null ? tagName.hashCode() : 0;
        result = 31 * result + (idBook != null ? idBook.hashCode() : 0);
        return result;
    }
}
