package ru.geekbrains.carts.mapper;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.api.ApiCartItem;
import ru.geekbrains.carts.entity.Cart;
import ru.geekbrains.carts.model.CartDto;


@Component
@RequiredArgsConstructor
public class CartMapper {

    public CartDto mapToDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setProductTitle(cart.getProduct().getTitle());//getProductTitle());
        dto.setProductPrice(cart.getProduct().getPrice());//getProductPrice());
        dto.setAmount(cart.getAmount());
        dto.setId(cart.getId());

        return dto;
    }
    public ApiCartItem mapToView(Cart cart) {
        ApiCartItem view = new ApiCartItem();
        view.setId(cart.getId());
        view.setProductTitle(cart.getProduct().getTitle());//getProductTitle());
        view.setPrice(cart.getProduct().getPrice());//getProductPrice());
       view.setAmount(cart.getAmount());
        return view;
    }

}
