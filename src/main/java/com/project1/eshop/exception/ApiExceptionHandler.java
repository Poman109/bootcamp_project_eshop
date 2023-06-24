package com.project1.eshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(ProductNotFoundException c) {
        //1.Create payload containing exception details

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                c.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Asia/Hong_Kong"))
        );
        //2.return response entity
        return new ResponseEntity<>(apiException, badRequest);
    }


    @ExceptionHandler(value = {UpdateCartItemNotAllowedException.class})
    public ResponseEntity<Object> handleApiRequestException(UpdateCartItemNotAllowedException u) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                u.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Asia/Hong_Kong"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {TransactionNotAllowedException.class})
    public ResponseEntity<Object> handleApiRequestException(TransactionNotAllowedException t) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                t.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Asia/Hong_Kong"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }
}
