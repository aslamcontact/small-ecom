package com.aslam.mycontact.application.bussiness_layer.categories;

import com.aslam.mycontact.application.exceptions.Category.CategoryExistException;
import com.aslam.mycontact.application.exceptions.Category.CategoryNotExistException;
import com.aslam.mycontact.application.dao_layer.categories.Category;
import com.aslam.mycontact.application.dao_layer.categories.CategoryRepo;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
  private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public CategoryRequest createCategory(CategoryRequest categoryRequest) {
           Optional<Category> result;
           result=categoryRepo.findByCategoryName(categoryRequest.categoryName());
           if(result.isPresent())
               throw new CategoryExistException(result.get().getCategoryName());

           var newCategory=new Category( categoryRequest.categoryName(),
                                         categoryRequest.descriptions());



            result=Optional.of(categoryRepo.save(newCategory));
            return new CategoryRequest( result.get().getCategoryName(),
                                        result.get().getCategoryDescription());

    }

    public CategoryRequest updateCategory(CategoryRequest categoryRequest)
    {
        Optional<Category> result;
        result=categoryRepo.findByCategoryName(categoryRequest.categoryName());
        if(result.isEmpty())
            throw new CategoryNotExistException(categoryRequest.categoryName());

        result.get().setCategoryDescription(categoryRequest.descriptions());
        result.get().setCategoryName(categoryRequest.categoryName());

        result=Optional.of(categoryRepo.save(result.get()));
        return new CategoryRequest( result.get().getCategoryName(),
                result.get().getCategoryDescription());



    }

    public CategoryRequest readCategory( @NotNull String categoryName)
    {
        Optional<Category> result;
        result=categoryRepo.findByCategoryName(categoryName);
        if(result.isEmpty())
            throw new CategoryNotExistException(categoryName);
        return new CategoryRequest(result.get().getCategoryName(),
                result.get().getCategoryDescription());
    }
    public CategoryRequest removeCategory( @NotNull String categoryName)
    {
        Optional<Category> result;
        result=categoryRepo.findByCategoryName(categoryName);
        if(result.isEmpty())
            throw new CategoryNotExistException(categoryName);
         categoryRepo.delete(result.get());
        return new CategoryRequest(result.get().getCategoryName(),
                result.get().getCategoryDescription());
    }

    public Optional<List<CategoryRequest>> allCategory()
    {
       List<Category> categories=categoryRepo.findAll();
       List<CategoryRequest> result;
       if(categories.isEmpty()) return Optional.of(List.of());
      result= categories.stream()
                                  .map(category ->
                                          new CategoryRequest(
                                                  category.getCategoryName(),
                                                  category.getCategoryDescription()))
                                   .toList();
      return Optional.of(result);
    }



}