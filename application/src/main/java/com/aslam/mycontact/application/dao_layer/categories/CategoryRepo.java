package com.aslam.mycontact.application.dao_layer.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Query("select c from Category c where c.parentCategory is null and categoryName=?1")
    Optional<Category> findByCategoryName(String categoryName);
    @Query("select c from Category c where c.parentCategory is null")
    List<Category> findAllMainCategory();
    @Query("select c from Category c where c.parentCategory.categoryName=?1")
    List<Category> findAllSubCategory(String parentCategoryName);
    @Query("select c from Category c where c.parentCategory.categoryName =?1 and categoryName=?2")
    Optional<Category> findSubCategoryByCategoryName(String parentCategoryName,String subCategoryName);




}
