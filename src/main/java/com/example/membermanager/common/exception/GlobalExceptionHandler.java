package com.example.membermanager.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({CustomException.class})
    protected ResponseEntity<String> handlerCustomException(CustomException e){
        return new ResponseEntity<>(e.getErrorCode().getMsg(), HttpStatus.OK);
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<?> handleServerException(Exception ex) {
        return new ResponseEntity<>("알 수 없는 에러 발생", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
