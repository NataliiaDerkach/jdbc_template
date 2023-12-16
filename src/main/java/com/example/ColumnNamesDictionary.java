package com.example;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
@Component
public class ColumnNamesDictionary {

    private static final List<String> COLUMN_DICTIONARY = Arrays.asList(
            "id", "name", "surname", "birthdate", "text", "timestamp", "userid", "postid", "commentid", "groupname", "reaction", "dislike"
            // Добавьте сюда другие возможные имена столбцов
    );

    public static String getRandomColumnName() {
        Random random = new Random();
        int index = random.nextInt(COLUMN_DICTIONARY.size());
        return COLUMN_DICTIONARY.get(index);
    }
}
