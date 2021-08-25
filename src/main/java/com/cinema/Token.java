package com.cinema;

import java.util.UUID;

public class Token {
    String token;

    Token() {
        token = UUID.randomUUID().toString();
    }
    public static String generate() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isValidToken(Token incomingToken) {
        return token.equals(incomingToken.token);
    }
    public boolean isValidToken(String incomingToken) {
        return token.equals(incomingToken);
    }

}
