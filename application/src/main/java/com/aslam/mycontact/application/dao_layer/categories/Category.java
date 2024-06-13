package com.aslam.mycontact.application.dao_layer.categories;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Category {

    @Id
    @SequenceGenerator( name = "category_seq",
                        sequenceName = "category_seq",
                        allocationSize = 1)
    @GeneratedValue( strategy = GenerationType.SEQUENCE,
                     generator = "category_seq")
    private Long categoryId;
    @Column( nullable = false,
             unique=true
             )
    private String categoryName;
    private String categoryDescription;

    public Category() {
    }

    public Category(String categoryName,String categoryDescription) {

        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }
}
