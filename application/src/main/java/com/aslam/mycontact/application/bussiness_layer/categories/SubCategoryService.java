package com.aslam.mycontact.application.bussiness_layer.categories;

import com.aslam.mycontact.application.dao_layer.categories.Category;
import com.aslam.mycontact.application.dao_layer.categories.CategoryRepo;
import com.aslam.mycontact.application.exceptions.category.CategoryExistException;
import com.aslam.mycontact.application.exceptions.category.CategoryNotExistException;
import com.aslam.mycontact.application.exceptions.category.sub_category.SubCategoryExistException;
import com.aslam.mycontact.application.exceptions.category.sub_category.SubCategoryNotExistException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoryService {

    private final CategoryRepo categoryRepo;

    public SubCategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    private Category isParentCategoryExist(String parentCategoryName)
    {
        Optional<Category> parentResult;
        parentResult=categoryRepo.findByCategoryName(parentCategoryName);
        if(parentResult.isEmpty())
          throw new CategoryNotExistException(parentCategoryName);
        return parentResult.get();
    }


    public SubCategoryRequest createSubCategory(SubCategoryRequest subCategoryRequest) {
        Optional<Category> temp,result;
        Category parentResult;

        //check parent category is exist
        parentResult=isParentCategoryExist(subCategoryRequest.parentCategoryName());

        //check if sub category is already exist
        temp=categoryRepo.findSubCategoryByCategoryName(
                parentResult.getCategoryName(),
                subCategoryRequest.subCategoryName());
        if(temp.isPresent())
            throw new SubCategoryExistException(
                    temp.get().getParentCategory().getCategoryName(),
                    temp.get().getCategoryName());

        //create new sub category
        var newSubCategory=new Category(
                subCategoryRequest.subCategoryName(),
                subCategoryRequest.descriptions(),
                parentResult);
        result=Optional.of(categoryRepo.save(newSubCategory));

        return new SubCategoryRequest(
                parentResult.getCategoryName(),
                result.get().getCategoryName(),
                result.get().getCategoryDescription());

    }

    public SubCategoryRequest updateSubCategory(SubCategoryRequest subCategoryRequest)
    {
        Optional<Category> temp,result;
        Category parentResult;

        //check parent category is exist
        parentResult=isParentCategoryExist(subCategoryRequest.parentCategoryName());

        //check if sub category is Not exist
        temp=categoryRepo.findSubCategoryByCategoryName(
                parentResult.getCategoryName(),
                subCategoryRequest.subCategoryName());
        if(temp.isEmpty())
            throw new SubCategoryNotExistException(
                    subCategoryRequest.parentCategoryName(),
                    subCategoryRequest.subCategoryName());

        //updates new changes
        temp.get().setParentCategory(parentResult);
        temp.get().setCategoryDescription(subCategoryRequest.descriptions());
        result=Optional.of(categoryRepo.save(temp.get()));

        return new SubCategoryRequest(
                parentResult.getCategoryName(),
                result.get().getCategoryName(),
                result.get().getCategoryDescription());
    }

    public SubCategoryRequest readSubCategory(
            @NotNull String parentCategoryName,
            @NotNull String subCategoryName)
    {
        Optional<Category> result;
        Category parentResult;

        parentResult=isParentCategoryExist(parentCategoryName);

        result=categoryRepo.findSubCategoryByCategoryName(
                parentResult.getCategoryName(),
                subCategoryName);
        if(result.isEmpty())
            throw new SubCategoryNotExistException(
                    parentCategoryName,
                    subCategoryName);

        return new SubCategoryRequest(
                parentResult.getCategoryName(),
                result.get().getCategoryName(),
                result.get().getCategoryDescription());
    }
    public SubCategoryRequest removeSubCategory(
            @NotNull String parentCategoryName,
            @NotNull String subCategoryName)
    {
        Optional<Category> result;
        Category parentResult;
        parentResult=isParentCategoryExist(parentCategoryName);
        result=categoryRepo.findSubCategoryByCategoryName(parentCategoryName,subCategoryName);
        if(result.isEmpty())
            throw new SubCategoryNotExistException(parentCategoryName,subCategoryName);
        categoryRepo.delete(result.get());
        return new SubCategoryRequest(
                parentResult.getCategoryName(),
                result.get().getCategoryName(),
                result.get().getCategoryDescription());
    }

    public Optional<List<SubCategoryRequest>> allSubCategory(@NotNull String parentCategoryName)
    {
        List<Category> subCategories=categoryRepo.findAllSubCategory(parentCategoryName);
        List<SubCategoryRequest> result;
        Category parentResult;
        parentResult=isParentCategoryExist(parentCategoryName);
        if(subCategories.isEmpty()) return Optional.of(List.of());
        result= subCategories.stream()
                .map(subCategory ->
                        new SubCategoryRequest(
                                parentResult.getCategoryName(),
                                subCategory.getCategoryName(),
                                subCategory.getCategoryDescription()))
                .toList();
        return Optional.of(result);
    }
}