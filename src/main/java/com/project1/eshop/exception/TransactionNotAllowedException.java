package com.project1.eshop.exception;

public class TransactionNotAllowedException extends RuntimeException{
    public TransactionNotAllowedException(String message) {
        super(message);
    }
}
