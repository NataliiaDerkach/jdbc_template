package com.example.demo;

import java.time.LocalDateTime;

public class Friendships {

    private Long userid1;
    private Long userid2;
    private LocalDateTime timestamp;

    public Friendships(Long userid1, Long userid2, LocalDateTime timestamp) {
        this.userid1 = userid1;
        this.userid2 = userid2;
        this.timestamp = timestamp;
    }

    public Long getUserId1() {
        return userid1;
    }

    public Long getUserId2() {
        return userid2;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setUserId1(Long userid1) {
        this.userid1 = userid1;
    }

    public void setUserId2(Long userid2) {
        this.userid2 = userid2;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Friendships{" +
                "userid1=" + userid1 +
                ", userid2=" + userid2 +
                ", timestamp=" + timestamp +
                '}';
    }
}
