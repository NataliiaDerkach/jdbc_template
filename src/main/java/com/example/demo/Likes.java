package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Likes {

    private Long postid;
    private Long userid;
    private LocalDateTime timestamp;

    public Likes(Long postid, Long userid, LocalDateTime timestamp) {
        this.postid = postid;
        this.userid = userid;
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "Likes{" +
                "postid=" + postid +
                ", userid=" + userid +
                ", timestamp=" + timestamp +
                '}';
    }
}
