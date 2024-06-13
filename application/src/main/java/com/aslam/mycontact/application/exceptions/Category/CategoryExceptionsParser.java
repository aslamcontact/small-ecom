package com.aslam.mycontact.application.exceptions.Category;

import org.springframework.http.HttpStatus;

public class CategoryExceptionsParser {
    private final String errorMessage;
    private final String categoryName;
    private final HttpStatus httpStatus;

    public CategoryExceptionsParser(String errorMessage, String categoryName, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.categoryName = categoryName;
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
