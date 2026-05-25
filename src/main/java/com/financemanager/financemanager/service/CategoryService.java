package com.financemanager.financemanager.service;

import com.financemanager.financemanager.dto.request.CreateCategoryRequest;
import com.financemanager.financemanager.dto.response.CategoryResponse;
import com.financemanager.financemanager.entity.Category;
import com.financemanager.financemanager.exception.ResourceAlreadyExistsException;
import com.financemanager.financemanager.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    // GET ALL CATEGORIES
    public List<CategoryResponse> getAllCategories() {

        List<Category> categories =
                categoryRepository.findByDeletedFalse();

        return categories.stream()
                .map(category -> new CategoryResponse(
                        category.getName(),
                        category.getType(),
                        category.isCustom()
                ))
                .toList();
    }

    // CREATE CATEGORY
    public CategoryResponse createCategory(
            CreateCategoryRequest request
    ) {

        if (categoryRepository.findByName(request.getName()) != null) {

            throw new ResourceAlreadyExistsException(
                    "Category already exists"
            );
        }

        Category category = Category.builder()
                .name(request.getName())
                .type(request.getType())
                .isCustom(true)
                .deleted(false)
                .build();

        Category savedCategory =
                categoryRepository.save(category);

        return new CategoryResponse(
                savedCategory.getName(),
                savedCategory.getType(),
                savedCategory.isCustom()
        );
    }

    // DELETE CATEGORY
    public String deleteCategory(String name) {

        Category category = categoryRepository.findByName(name);

        if (category == null) {
            throw new RuntimeException("Category not found");
        }

        if (!category.isCustom()) {
            throw new RuntimeException(
                    "Default categories cannot be deleted"
            );
        }

        category.setDeleted(true);

        categoryRepository.save(category);

        return "Category deleted successfully";
    }
}