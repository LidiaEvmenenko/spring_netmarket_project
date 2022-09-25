package ru.geekbrains.products.mapper.cart;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.api.ApiCartItem;
import ru.geekbrains.products.entity.Cart;
import ru.geekbrains.products.model.CartDto;

@Component
@RequiredArgsConstructor
public class CartMapper {

    public CartDto mapToDto(Cart cart) {
        CartDto dto = new CartDto();
        dto.setProductTitle(cart.getProduct().getTitle());
        dto.setProductPrice(cart.getProduct().getPrice());
        dto.setAmount(cart.getAmount());
        dto.setId(cart.getId());

        return dto;
    }
    public ApiCartItem mapToView(Cart cart) {
        ApiCartItem view = new ApiCartItem();
        view.setId(cart.getId());
        view.setProductTitle(cart.getProduct().getTitle());
        view.setPrice(cart.getProduct().getPrice());
       view.setAmount(cart.getAmount());
        return view;
    }

}
