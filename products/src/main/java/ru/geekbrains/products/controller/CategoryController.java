package ru.geekbrains.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.api.ApiCategoryListView;
import ru.geekbrains.api.ApiCategoryView;
import ru.geekbrains.products.entity.Category;
import ru.geekbrains.products.mapper.product.ProductMapper;
import ru.geekbrains.products.service.CategoryService;
import ru.geekbrains.products.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @PostMapping()
    public void createNewCategory(@RequestBody ApiCategoryView category) {
        categoryService.create(category.getTitle());
    }

    @GetMapping
    public ApiCategoryListView findAllCategory(){
        ApiCategoryListView categoryListView = new ApiCategoryListView();
        List<Category> categories = categoryService.findAll();
        for (Category c:categories) {
            ApiCategoryView view = new ApiCategoryView();
            view.setTitle(c.getTitle());
            categoryListView.addItem(view);
        }
        return categoryListView;
    }

}
