package com.aslam.mycontact.application.exceptions.category.sub_category;

import org.springframework.http.HttpStatus;

public class SubCategoryExceptionParser {
    private final String errorMessage;
    private final String parentCategoryName;
    private final  String subCategoryName;
    private final HttpStatus httpStatus;

    public SubCategoryExceptionParser(
            String errorMessage,
            String parentCategoryName,
            String subCategoryName,
            HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.parentCategoryName = parentCategoryName;
        this.subCategoryName=subCategoryName;
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
