package com.example.arpit.samplemockito;

public class StoredData {

    public String revereTheInputString(String input) {
        StringBuilder builder = null;
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input Cannot be Null");
        } else {
            builder = new StringBuilder(input);
            builder.reverse();
        }
        return builder.toString();
    }

}