package com.example.demo.dto;

public class TagDTO {

    String name;
    boolean liked;
    boolean recommended;
    boolean avoid;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLiked() {
        return liked;
    }

    public boolean getLiked(){
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public TagDTO() {
    }
}
