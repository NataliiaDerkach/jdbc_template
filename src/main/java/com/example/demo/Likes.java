package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Likes {

    private Long postid;
    private Long userid;
    private LocalDateTime timestamp;

    public Likes(Long postid, Long userid, LocalDateTime timestamp) {
        this.postid = postid;
        this.userid = userid;
        this.timestamp = timestamp;
    }

    public Long getPostid() {
        return postid;
    }

    public Long getUserid() {
        return userid;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setPostid(Long postid) {
        this.postid = postid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setTimestamp(LocalDateTime timestamp) {
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
