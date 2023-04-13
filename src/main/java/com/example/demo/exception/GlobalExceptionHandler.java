package com.example.demo.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Value(value = "Invalid credentials")
    private String userNotFound;

    @Value(value = "Wrong appointment submitted data")
    private String appointmentErr;

    @ExceptionHandler(value = InvalidCredentialsException.class)
    public ResponseEntity UserNotFoundException(InvalidCredentialsException invalidCredentialsException) {
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userNotFound);
    }

    @ExceptionHandler(value = InvalidAppointmentSubmit.class)
    public ResponseEntity InvalidAppointmentSubmitException (InvalidAppointmentSubmit invalidAppointmentSubmit) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(appointmentErr);
    }
}
