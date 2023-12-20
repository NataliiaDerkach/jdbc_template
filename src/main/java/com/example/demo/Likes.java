package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Likes {

    private int postid;
    private int userid;
    private LocalDateTime timestamp;

    public Likes(int postid, int userid, LocalDateTime timestamp) {
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
