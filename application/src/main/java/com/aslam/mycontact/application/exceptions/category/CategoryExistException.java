package com.aslam.mycontact.application.exceptions.category;

public class CategoryExistException extends RuntimeException{
    private final String CategoryName;
    static final String errorMessage="Category Already Exist";

    public CategoryExistException(String categoryName) {
        super(errorMessage);
        CategoryName = categoryName;
    }

    public CategoryExistException(Throwable cause, String categoryName) {
        super(errorMessage, cause);
        CategoryName = categoryName;
    }

    public String getCategoryName() {
        return CategoryName;
    }
}
