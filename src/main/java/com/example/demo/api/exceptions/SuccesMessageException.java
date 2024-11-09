package com.example.demo.api.exceptions;


import com.example.demo.api.utils.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SuccesMessageException {
    @ExceptionHandler(SuccesRequestException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<SuccessResponse> suceesClasseException(SuccesRequestException ex) {
        SuccessResponse succes = new SuccessResponse(
                HttpStatus.OK.value(),
                "Operation succes",
                ex.getMessage()
        );
        return new ResponseEntity<>(succes, HttpStatus.OK);
    }
}
