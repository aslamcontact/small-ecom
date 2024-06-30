package com.aslam.mycontact.application.api_layer.categories;

import com.aslam.mycontact.application.bussiness_layer.categories.CategoryRequest;
import com.aslam.mycontact.application.bussiness_layer.categories.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("categories")
public class Category {
     @Autowired
    private CategoryService categoryService;

     @GetMapping("/category")
     public ResponseEntity<Optional<List<CategoryRequest>>> allCategory()
     {
         return new ResponseEntity<>(
                 categoryService.allCategory(),
                 HttpStatus.OK
         );
     }
     @PostMapping("/category")
    public ResponseEntity<Optional<CategoryRequest>> createCategory(@RequestBody CategoryRequest request)
    {
       request=  categoryService.createCategory(request);

         return new ResponseEntity<>( Optional.of(request),
                                      HttpStatus.CREATED);
    }

    @PutMapping("/category")
    public ResponseEntity<Optional<CategoryRequest>> updateCategory(@RequestBody CategoryRequest request)
    {
        request=  categoryService.updateCategory(request);

        return new ResponseEntity<>( Optional.of(request),
                HttpStatus.CREATED);
    }
    @GetMapping("/category/{name}")
    public ResponseEntity<Optional<CategoryRequest>> readCategory(@PathVariable(value = "name") String categoryName)
    {
        CategoryRequest response;
        response=  categoryService.readCategory(categoryName);

        return new ResponseEntity<>( Optional.of(response),
                HttpStatus.OK);
    }


    @DeleteMapping("/category/{name}")
    public ResponseEntity<Optional<CategoryRequest>> removeCategory(
            @PathVariable(value = "name") String categoryName)
    {
        CategoryRequest response;
        response=  categoryService.removeCategory(categoryName);

        return new ResponseEntity<>( Optional.of(response),
                HttpStatus.OK);
    }


}
