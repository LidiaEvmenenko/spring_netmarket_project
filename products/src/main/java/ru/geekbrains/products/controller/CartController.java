package ru.geekbrains.products.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.api.ApiCartItemsView;
import ru.geekbrains.products.entity.Product;
import ru.geekbrains.products.exceptions.ResourceNotFoundException;
import ru.geekbrains.products.mapper.product.ProductMapper;
import ru.geekbrains.products.model.CartDto;
import ru.geekbrains.products.service.CartService;
import ru.geekbrains.products.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private ProductService productService;
    private ProductMapper productMapper;

    @PostMapping("/{id},{count}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewProductToCart(@PathVariable Long id, @PathVariable Double count) {
            cartService.addNewProductToCart(id, count);
    }

    @GetMapping
    public Page<CartDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return cartService.findAll(pageIndex - 1, 5).map(CartDto::new);
    }
}
