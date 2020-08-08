package com.github.miteshaghera.hackernewsapi.model;

import java.io.Serializable;

public class Comment implements Serializable {

    private static final long serialVersionUID = -878942592514676630L;

    private final long id;

    private String text;

    private String userHandle;

    private long userAge;

    public Comment(long id) {
        this.id = id;
    }

    public Comment(long id, String text, String userHandle, long userAge) {
        this(id);
        this.text = text;
        this.userHandle = userHandle;
        this.userAge = userAge;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserHandle() {
        return userHandle;
    }

    public void setUserHandle(String userHandle) {
        this.userHandle = userHandle;
    }

    public long getUserAge() {
        return userAge;
    }

    public void setUserAge(long userAge) {
        this.userAge = userAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        return id == comment.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
