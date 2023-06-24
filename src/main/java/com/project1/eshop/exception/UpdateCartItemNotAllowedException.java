package com.project1.eshop.exception;

public class UpdateCartItemNotAllowedException extends RuntimeException{
    public UpdateCartItemNotAllowedException(String message) {
        super(message);
    }
}
