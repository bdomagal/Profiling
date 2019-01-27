package com.example.demo.persistance;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserBookPK implements Serializable {
    private Integer idUser;
    private Integer idBook;

    @Column(name = "id_user", nullable = false)
    @Id
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

        UserBookPK that = (UserBookPK) o;

        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (idBook != null ? !idBook.equals(that.idBook) : that.idBook != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser != null ? idUser.hashCode() : 0;
        result = 31 * result + (idBook != null ? idBook.hashCode() : 0);
        return result;
    }
}
