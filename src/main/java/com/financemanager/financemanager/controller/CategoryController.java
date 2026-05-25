package com.financemanager.financemanager.controller;

import com.financemanager.financemanager.dto.request.CreateCategoryRequest;
import com.financemanager.financemanager.dto.response.CategoryResponse;
import com.financemanager.financemanager.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // GET ALL CATEGORIES
    @GetMapping
    public List<CategoryResponse> getAllCategories() {

        return categoryService.getAllCategories();
    }

    // CREATE CATEGORY
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createCategory(
            @Valid @RequestBody CreateCategoryRequest request
    ) {

        return categoryService.createCategory(request);
    }

    // DELETE CATEGORY
    @DeleteMapping("/{name}")
    public String deleteCategory(
            @PathVariable String name
    ) {

        return categoryService.deleteCategory(name);
    }
}