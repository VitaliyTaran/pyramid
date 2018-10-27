package com.epam.geometry.data;

public class DataParser {
    private static final String WORD_DELIMITER = " ";

    public String[] parse(String input) {
        return input.split(WORD_DELIMITER);
    }
}
