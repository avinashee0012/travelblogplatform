package com.rebellion.travelblogplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "comments")
public class Comment extends Auditor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User commentor;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Blog blog;

    @Column(nullable = false)
    @Size(min = 5, max = 100)
    private String content;

    @Column(nullable = false)
    private boolean isVisible = true;

    // CONSTRUCTOR
    public Comment() {
        // FOR JPA
    }

    public Comment(User commentor, Blog blog, String content) {
        this.commentor = commentor;
        this.blog = blog;
        this.content = content;
    }

    // GETTERS
    public Long getId() {
        return id;
    }

    public User getCommentor() {
        return commentor;
    }

    public Blog getBlog() {
        return blog;
    }

    public String getContent() {
        return content;
    }

    public boolean isVisible() {
        return isVisible;
    }
    
    // SETTERS
    public void hide(){
        this.isVisible = false;
    }

    public void show(){
        this.isVisible = true;
    }
}
