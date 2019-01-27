package com.example.demo.persistance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tag {
    private String tagName;

    @Id
    @Column(name = "tag_name", nullable = false, length = 45)
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (tagName != null ? !tagName.equals(tag.tagName) : tag.tagName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return tagName != null ? tagName.hashCode() : 0;
    }
}
