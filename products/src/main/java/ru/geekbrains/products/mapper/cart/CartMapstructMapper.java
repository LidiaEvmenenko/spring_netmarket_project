package ru.geekbrains.products.mapper.cart;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.products.entity.Cart;
import ru.geekbrains.products.entity.Product;
import ru.geekbrains.products.entity.User;
import ru.geekbrains.products.model.CartDto;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CartMapstructMapper {
    @Mappings(value = {
            @Mapping(target = "id", source = "cart.id"),
            @Mapping(target = "productTitle", source = "product.title"),
            @Mapping(target = "productPrice", source = "product.price"),
            @Mapping(target = "productId", source = "product.id")
    })
    CartDto mapToDto(Product product, Cart cart);
}
