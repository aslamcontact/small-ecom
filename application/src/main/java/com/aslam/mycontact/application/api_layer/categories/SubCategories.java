package com.aslam.mycontact.application.api_layer.categories;

import com.aslam.mycontact.application.bussiness_layer.categories.CategoryRequest;
import com.aslam.mycontact.application.bussiness_layer.categories.SubCategoryRequest;
import com.aslam.mycontact.application.bussiness_layer.categories.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("categories")
public class SubCategories {
    @Autowired
    private SubCategoryService subCategoryService;


    @PostMapping("/subcategory")
    public ResponseEntity<Optional<SubCategoryRequest>> createSubCategory(
            @RequestBody SubCategoryRequest request)
    {
        request=  subCategoryService.createSubCategory(request);

        return new ResponseEntity<>( Optional.of(request),
                HttpStatus.CREATED);
    }
    @PutMapping("/subcategory")
    public ResponseEntity<Optional<SubCategoryRequest>> updateSubCategory(
            @RequestBody SubCategoryRequest request)
    {
        request=  subCategoryService.updateSubCategory(request);

        return new ResponseEntity<>( Optional.of(request),
                HttpStatus.CREATED);
    }


    @GetMapping("/subcategory/{parentCategory}/{subCategory}")
    public ResponseEntity<Optional<SubCategoryRequest>> readSubCategory(
            @PathVariable(value = "parentCategory") String parentCategory,
            @PathVariable(value="subCategory") String subCategory)
    {
        SubCategoryRequest response;
        response=  subCategoryService.readSubCategory(
                parentCategory,
                subCategory);

        return new ResponseEntity<>( Optional.of(response),
                HttpStatus.OK);
    }
    @DeleteMapping("/subcategory/{parentCategory}/{subCategory}")
    public ResponseEntity<Optional<SubCategoryRequest>> removeSubCategory(
            @PathVariable(value = "parentCategory") String parentCategory,
            @PathVariable(value="subCategory") String subCategory)
    {
        SubCategoryRequest response;
        response=  subCategoryService.removeSubCategory(
                parentCategory,
                subCategory);

        return new ResponseEntity<>( Optional.of(response),
                HttpStatus.OK);
    }

    @GetMapping("/subcategory/{parentCategory}")
    public ResponseEntity<Optional<List<SubCategoryRequest>>> allSubCategory(
            @PathVariable(value ="parentCategory") String parentCategory)
    {
        return new ResponseEntity<>(
                subCategoryService.allSubCategory(parentCategory),
                HttpStatus.OK
        );
    }




}
