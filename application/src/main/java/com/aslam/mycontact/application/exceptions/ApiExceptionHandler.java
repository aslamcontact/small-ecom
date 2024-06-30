package com.aslam.mycontact.application.exceptions;

import com.aslam.mycontact.application.exceptions.category.CategoryExceptionsParser;
import com.aslam.mycontact.application.exceptions.category.CategoryExistException;
import com.aslam.mycontact.application.exceptions.category.CategoryNotExistException;
import com.aslam.mycontact.application.exceptions.category.sub_category.SubCategoryExceptionParser;
import com.aslam.mycontact.application.exceptions.category.sub_category.SubCategoryExistException;
import com.aslam.mycontact.application.exceptions.category.sub_category.SubCategoryNotExistException;
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
    @ExceptionHandler
    ResponseEntity<Object> subCategoryExistException(SubCategoryExistException exception)
    {
        HttpStatus httpStatus=HttpStatus.CONFLICT;
        SubCategoryExceptionParser exceptionsParser=new SubCategoryExceptionParser(
                exception.getMessage(),
                exception.getParentCategoryName(),
                exception.getSubCategoryName(),
                httpStatus
        );
        return new ResponseEntity<>(exceptionsParser,httpStatus);
    }
    @ExceptionHandler
    ResponseEntity<Object> subCategoryNotExistException(SubCategoryNotExistException exception)
    {
        HttpStatus httpStatus=HttpStatus.NOT_FOUND;
        SubCategoryExceptionParser exceptionsParser=new SubCategoryExceptionParser(
                exception.getMessage(),
                exception.getParentCategoryName(),
                exception.getSubCategoryName(),
                httpStatus
        );
        return new ResponseEntity<>(exceptionsParser,httpStatus);
    }


}
