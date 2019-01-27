package com.example.demo.persistance;

import javax.persistence.*;

@Entity
@Table(name = "user_book", schema = "user_profiling")
@IdClass(UserBookPK.class)
public class UserBook {
    private Integer idUser;
    private Integer idBook;
    private Byte isMarkedAsNotInterested;
    private Byte isMarkedAsToRead;
    private Byte review;
    private Integer timeSpentOnPage;
    private Integer timesVisited;
    private User userByIdUser;
    private Book bookByIdBook;

    @Id
    @Column(name = "id_user", nullable = false)
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Id
    @Column(name = "id_book", nullable = false)
    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    @Basic
    @Column(name = "is_marked_as_not_interested", nullable = false)
    public Byte getIsMarkedAsNotInterested() {
        return isMarkedAsNotInterested;
    }

    public void setIsMarkedAsNotInterested(Byte isMarkedAsNotInterested) {
        this.isMarkedAsNotInterested = isMarkedAsNotInterested;
    }

    @Basic
    @Column(name = "is_marked_as_to_read", nullable = false)
    public Byte getIsMarkedAsToRead() {
        return isMarkedAsToRead;
    }

    public void setIsMarkedAsToRead(Byte isMarkedAsToRead) {
        this.isMarkedAsToRead = isMarkedAsToRead;
    }

    @Basic
    @Column(name = "review", nullable = true)
    public Byte getReview() {
        return review;
    }

    public void setReview(Byte review) {
        this.review = review;
    }

    @Basic
    @Column(name = "time_spent_on_page", nullable = false)
    public Integer getTimeSpentOnPage() {
        return timeSpentOnPage;
    }

    public void setTimeSpentOnPage(Integer timeSpentOnPage) {
        this.timeSpentOnPage = timeSpentOnPage;
    }

    @Basic
    @Column(name = "times_visited", nullable = false)
    public Integer getTimesVisited() {
        return timesVisited;
    }

    public void setTimesVisited(Integer timesVisited) {
        this.timesVisited = timesVisited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserBook userBook = (UserBook) o;

        if (idUser != null ? !idUser.equals(userBook.idUser) : userBook.idUser != null) return false;
        if (idBook != null ? !idBook.equals(userBook.idBook) : userBook.idBook != null) return false;
        if (isMarkedAsNotInterested != null ? !isMarkedAsNotInterested.equals(userBook.isMarkedAsNotInterested) : userBook.isMarkedAsNotInterested != null)
            return false;
        if (isMarkedAsToRead != null ? !isMarkedAsToRead.equals(userBook.isMarkedAsToRead) : userBook.isMarkedAsToRead != null)
            return false;
        if (review != null ? !review.equals(userBook.review) : userBook.review != null) return false;
        if (timeSpentOnPage != null ? !timeSpentOnPage.equals(userBook.timeSpentOnPage) : userBook.timeSpentOnPage != null)
            return false;
        if (timesVisited != null ? !timesVisited.equals(userBook.timesVisited) : userBook.timesVisited != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUser != null ? idUser.hashCode() : 0;
        result = 31 * result + (idBook != null ? idBook.hashCode() : 0);
        result = 31 * result + (isMarkedAsNotInterested != null ? isMarkedAsNotInterested.hashCode() : 0);
        result = 31 * result + (isMarkedAsToRead != null ? isMarkedAsToRead.hashCode() : 0);
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (timeSpentOnPage != null ? timeSpentOnPage.hashCode() : 0);
        result = 31 * result + (timesVisited != null ? timesVisited.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false, insertable = false, updatable = false)
    public User getUserByIdUser() {
        return userByIdUser;
    }

    public void setUserByIdUser(User userByIdUser) {
        this.userByIdUser = userByIdUser;
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
