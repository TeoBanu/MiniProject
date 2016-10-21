package com.msg.proj.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Clob;
import java.util.Objects;

@Entity
public class CV implements Serializable{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column
    private String text;

    @Column
    //@Lob
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CV)) return false;
        CV cv = (CV) o;
        return id == cv.id &&
                Objects.equals(text, cv.text) &&
                Objects.equals(title, cv.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, title);
    }

    @Override
    public String toString() {
        return "CV{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
