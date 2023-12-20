package com.example.demo;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;
@Getter
@Setter
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
