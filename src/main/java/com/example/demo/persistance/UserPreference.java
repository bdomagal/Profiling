package com.example.demo.persistance;

import javax.persistence.*;

@Entity
@Table(name = "user_preference", schema = "user_profiling")
@IdClass(UserPreferencePK.class)
public class UserPreference {
    private String tagName;
    private Integer idUser;
    private Byte isLiked;
    private Byte isMarkedToAvoid;
    private Tag tagByTagName;
    private User userByIdUser;

    @Id
    @Column(name = "tag_name", nullable = false, length = 45)
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Id
    @Column(name = "id_user", nullable = false)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "is_liked", nullable = false)
    public Byte getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(Byte isLiked) {
        this.isLiked = isLiked;
    }

    @Basic
    @Column(name = "is_marked_to_avoid", nullable = false)
    public Byte getIsMarkedToAvoid() {
        return isMarkedToAvoid;
    }

    public void setIsMarkedToAvoid(Byte isMarkedToAvoid) {
        this.isMarkedToAvoid = isMarkedToAvoid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPreference that = (UserPreference) o;

        if (tagName != null ? !tagName.equals(that.tagName) : that.tagName != null) return false;
        if (idUser != null ? !idUser.equals(that.idUser) : that.idUser != null) return false;
        if (isLiked != null ? !isLiked.equals(that.isLiked) : that.isLiked != null) return false;
        if (isMarkedToAvoid != null ? !isMarkedToAvoid.equals(that.isMarkedToAvoid) : that.isMarkedToAvoid != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tagName != null ? tagName.hashCode() : 0;
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + (isLiked != null ? isLiked.hashCode() : 0);
        result = 31 * result + (isMarkedToAvoid != null ? isMarkedToAvoid.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tag_name", referencedColumnName = "tag_name", nullable = false, insertable = false, updatable = false)
    public Tag getTagByTagName() {
        return tagByTagName;
    }

    public void setTagByTagName(Tag tagByTagName) {
        this.tagByTagName = tagByTagName;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false)
    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
    }
}
