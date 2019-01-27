package com.example.demo.persistance;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class UserPreferencePK implements Serializable {
    private String tagName;
    private Integer idUser;

    @Column(name = "tag_name", nullable = false, length = 45)
    @Id
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Column(name = "id_user", nullable = false)
    @Id
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPreferencePK that = (UserPreferencePK) o;

        if (tagName != null ? !tagName.equals(that.tagName) : that.tagName != null) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagName != null ? tagName.hashCode() : 0;
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        return result;
    }
}
