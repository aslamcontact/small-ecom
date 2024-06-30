package com.aslam.mycontact.application.bussiness_layer.categories;

public record SubCategoryRequest(
        String parentCategoryName,
        String subCategoryName,
        String descriptions) {
}
