package com.example.demo.persistance;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @Column(name = "id_user", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private Byte isRegistered;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "is_registered", nullable = false)
    public Boolean getIsRegistered() {
        return isRegistered==1;
    }

    public void setIsRegistered(Boolean isRegistered) {
        if(isRegistered)this.isRegistered=1;
        else this.isRegistered=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (idUser != null ? !idUser.equals(user.idUser) : user.idUser != null) return false;
        if (isRegistered != null ? !isRegistered.equals(user.isRegistered) : user.isRegistered != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser != null ? idUser.hashCode() : 0;
        result = 31 * result + (isRegistered != null ? isRegistered.hashCode() : 0);
        return result;
    }
}
