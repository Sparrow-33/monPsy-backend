package com.example.demo.exception;

public class InvalidAppointmentSubmit extends Exception {
    private String message;

    public InvalidAppointmentSubmit(String message) {
        this.message = message;
    }

}
