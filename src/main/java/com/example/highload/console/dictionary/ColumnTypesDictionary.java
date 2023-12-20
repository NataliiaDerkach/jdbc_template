package com.example.highload.console.dictionary;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Component
public class ColumnTypesDictionary {

    private static final List<String> TYPE_DICTIONARY = Arrays.asList(
            "VARCHAR(255)", "TEXT"

    );

    public static String getRandomColumnsType() {
        Random random = new Random();
        int index = random.nextInt(TYPE_DICTIONARY.size());
        return TYPE_DICTIONARY.get(index);
    }
}
