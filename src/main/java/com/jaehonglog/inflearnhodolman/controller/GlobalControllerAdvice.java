package com.jaehonglog.inflearnhodolman.controller;


import com.jaehonglog.inflearnhodolman.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponse invalidMethodException(MethodArgumentNotValidException exception){

        final var fieldError = exception.getFieldError();
        final var field = fieldError.getField();
        final var message = fieldError.getDefaultMessage();

        final var errorResponse=new ErrorResponse("400", "잘못된 요청입니다.");
        errorResponse.addValidation(field, message);
        return errorResponse;
    }
}
