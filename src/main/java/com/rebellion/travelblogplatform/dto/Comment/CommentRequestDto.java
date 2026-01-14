package com.rebellion.travelblogplatform.dto.Comment;

public class CommentRequestDto {
    private String content;

    public CommentRequestDto() {
        // FOR JACKSON
    }

    public CommentRequestDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
