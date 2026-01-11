package com.rebellion.travelblogplatform.entity;

import com.rebellion.travelblogplatform.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "blogs")
public class Blog extends Auditor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 10, max = 100)
    private String title;

    @Column(nullable = false)
    private String slug;

    @Column(nullable = false)
    @Size(min = 100, max = 2000)
    private String content;

    private String imageUrl = "";
    private String videoUrl = "";

    @Enumerated(EnumType.STRING)
    private Status status = Status.DRAFT;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private User author;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Category category;

    // CONSTRUCTOR
    public Blog() {
        // FOR JPA
    }

    public Blog(String title, String content, String imageUrl, String videoUrl, User author, Category category) {
        this.title = title.trim();
        this.content = content;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.author = author;
        this.category = category;
        this.slug = convertTitleToSlug(title);
    }

    // GETTERS
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Status getStatus() {
        return status;
    }

    public User getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }
    
    // SETTERS
    public void changeStatus(Status newStatus){
        this.status = newStatus;
    }

    public void addImage(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public void addVideo(String videoUrl){
        this.videoUrl = videoUrl;
    }

    // HELPER METHODS
    private String convertTitleToSlug(String title){
        String[] words = title.trim().toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            if(i != words.length - 1) sb.append("-");
        }
        return sb.toString();
    }
}
