package com.example.highload.console.dictionary;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Component
public class TableNamesDictionary {
    public static final List<String> TABLE_DICTIONARY = Arrays.asList(
            "comments", "friends", "messages", "photos", "events", "groups"
    );

    public String getRandomTableName() {
        Random random = new Random();
        int index = random.nextInt(TABLE_DICTIONARY.size());
        return TABLE_DICTIONARY.get(index);
    }
}
