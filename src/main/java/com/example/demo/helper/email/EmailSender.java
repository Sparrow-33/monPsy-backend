package com.example.demo.helper.email;

public interface EmailSender {
    void send(String to, String email);
     String buildEmail(String name, String link);
}
