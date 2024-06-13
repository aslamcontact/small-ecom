package com.aslam.mycontact.application.exceptions.Category;

public class CategoryNotExistException extends RuntimeException{
    private final String CategoryName;
    static final String errorMessage="Category is Not Exist";

    public CategoryNotExistException(String categoryName) {
        super(errorMessage);
        CategoryName = categoryName;
    }

    public CategoryNotExistException(Throwable cause, String categoryName) {
        super(errorMessage, cause);
        CategoryName = categoryName;
    }

    public String getCategoryName() {
        return CategoryName;
    }
}
