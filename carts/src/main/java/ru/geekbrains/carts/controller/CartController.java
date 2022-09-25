package ru.geekbrains.carts.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.carts.model.CartDto;
import ru.geekbrains.carts.service.CartService;


@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @PostMapping("/{id},{count},{title},{price}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewProductToCart(@PathVariable Long id, @PathVariable Double count,
                                    @PathVariable String title, @PathVariable Double price) {
            cartService.addNewProductToCart(id, count,title,price);
    }

    @GetMapping
    public Page<CartDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return cartService.findAll(pageIndex - 1, 5).map(CartDto::new);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteByIdCart(@PathVariable Long id){
        cartService.deleteById(id);
    }

    @GetMapping("/sum")
    public Double sumCart(){
        return cartService.sumItogCart(1L);
    }

    @GetMapping("/inc/{id}")
    public void incCartAmount(@PathVariable Long id){
        cartService.incAmount(id);
    }

    @GetMapping("/sub/{id}")
    public void subCartAmount(@PathVariable Long id){
        cartService.subAmount(id);
    }

}
