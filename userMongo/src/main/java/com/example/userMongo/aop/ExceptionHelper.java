package com.example.userMongo.aop;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException e) {
        System.out.println("ttttttttttttttttttttttttttttt");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        //return status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleHeaderException(ServletRequestBindingException e) {
        ///return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        return status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleHeaderException(InvalidHeaderException e) {
        ///return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        return status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(Exception e) {
       // return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        return status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}