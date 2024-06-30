package com.aslam.mycontact.application.exceptions.category.sub_category;

public class SubCategoryNotExistException extends RuntimeException{

    private final String parentCategoryName;
    private final String subCategoryName;
    static final String errorMessage="Sub Category Not Exist";

    public SubCategoryNotExistException(
            String parentCategoryName,
            String subCategoryName) {
        super(errorMessage);
        this.parentCategoryName = parentCategoryName;
        this.subCategoryName=subCategoryName;
    }

    public SubCategoryNotExistException(
            Throwable cause,
            String parentCategoryName,
            String subCategoryName) {
        super(errorMessage, cause);
        this.parentCategoryName=parentCategoryName;
        this.subCategoryName=subCategoryName;
    }

    public String getParentCategoryName() {
        return parentCategoryName;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }
}
