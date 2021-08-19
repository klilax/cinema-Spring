package com.cinema;

import java.util.UUID;

public class Token {
    public static String generate() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
