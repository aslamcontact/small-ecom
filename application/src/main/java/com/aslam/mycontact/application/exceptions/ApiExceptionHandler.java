package com.aslam.mycontact.application.exceptions;

import com.aslam.mycontact.application.exceptions.Category.CategoryExceptionsParser;
import com.aslam.mycontact.application.exceptions.Category.CategoryExistException;
import com.aslam.mycontact.application.exceptions.Category.CategoryNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    ResponseEntity<Object> categoryExistException(CategoryExistException exception)
    {
        HttpStatus httpStatus=HttpStatus.CONFLICT;
        CategoryExceptionsParser exceptionsParser=new CategoryExceptionsParser(
                exception.getMessage(),
                exception.getCategoryName(),
                httpStatus
        );
        return new ResponseEntity<>(exceptionsParser,httpStatus);
    }
    @ExceptionHandler
    ResponseEntity<Object> categoryNotExistException(CategoryNotExistException exception)
    {
        HttpStatus httpStatus=HttpStatus.NOT_FOUND;
        CategoryExceptionsParser exceptionsParser=new CategoryExceptionsParser(
                exception.getMessage(),
                exception.getCategoryName(),
                httpStatus
        );
        return new ResponseEntity<>(exceptionsParser,httpStatus);
    }


}
