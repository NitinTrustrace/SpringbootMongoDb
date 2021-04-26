package com.example.userMongo.aop;

import java.sql.SQLOutput;

public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
       /// System.out.println(message+"==========mmmmmmmmmmm");
        super(message);
    }
}