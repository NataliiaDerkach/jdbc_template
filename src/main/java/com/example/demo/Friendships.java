package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Friendships {

    private Long userid1;
    private Long userid2;
    private LocalDateTime timestamp;

    public Friendships(Long userid1, Long userid2, LocalDateTime timestamp) {
        this.userid1 = userid1;
        this.userid2 = userid2;
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
