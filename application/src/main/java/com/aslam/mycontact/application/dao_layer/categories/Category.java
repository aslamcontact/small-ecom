package com.aslam.mycontact.application.dao_layer.categories;

import com.aslam.mycontact.application.dao_layer.categories.products.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UniqueCategoryNameAndParentCategory",
                        columnNames = { "category_name", "parent_category" }) })
public class Category {

    @Id
    @SequenceGenerator(
            name = "category_seq",
            sequenceName = "category_seq",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_seq")
    private Long categoryId;

    @Column( nullable = false)
    private String categoryName;
    private String categoryDescription;

    //reference the foreign key to same entity
    @ManyToOne(optional = true)
    @JoinColumn(
            name = "parentCategory",
            referencedColumnName = "categoryId")
    private Category parentCategory;

    @OneToMany(
            mappedBy = "parentCategory",
            cascade = CascadeType.ALL)
    private List<Category> childCategory;



    public Category() {
    }

    public Category(
            String categoryName,
            String categoryDescription) {

        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public Category(
            String categoryName,
            String categoryDescription,
            Category parentCategory) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.parentCategory = parentCategory;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    public List<Category> getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(List<Category> childCategory) {
        this.childCategory = childCategory;
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
