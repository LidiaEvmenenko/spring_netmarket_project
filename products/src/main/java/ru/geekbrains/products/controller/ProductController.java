package ru.geekbrains.products.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.api.ApiCartItem;
import ru.geekbrains.api.ApiProductsListView;
import ru.geekbrains.api.ApiProductsView;
import ru.geekbrains.products.mapper.product.ProductMapper;
import ru.geekbrains.products.model.ProductDto;
import ru.geekbrains.products.service.CategoryService;
import ru.geekbrains.products.service.ProductService;

import java.util.List;


@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    @GetMapping
    public ApiProductsListView findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ApiProductsView findById(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PostMapping
    public void createNewProduct(@RequestBody ApiProductsView product) {
        productService.create(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/by_category")
    public ApiProductsListView findByCategory(@RequestParam(name = "title", defaultValue = "Все продукты") String title) {
        return productService.findProductsByCategory(title);
    }

    @GetMapping("/manufacturer")
    public ApiProductsListView findByManufacturer(@RequestParam(name = "title") String title) {
        return productService.findByManufacturer(title);
    }

    @PutMapping
    public  void update(@RequestBody ApiProductsView product){
        productService.update(product);
    }

}


















