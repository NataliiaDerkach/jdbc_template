package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Posts {

    private Long id;
    private Long userid;
    private String text;
    private LocalDateTime timestamp;

    public Posts(Long id, Long userid, String text, LocalDateTime timestamp) {
        this.id = id;
        this.userid = userid;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userid;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userid) {
        this.userid = userid;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", userId=" + userid +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
