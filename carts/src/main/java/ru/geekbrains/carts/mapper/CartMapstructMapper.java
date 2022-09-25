package ru.geekbrains.carts.mapper;


import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.carts.entity.Cart;
import ru.geekbrains.carts.entity.Product;
import ru.geekbrains.carts.model.CartDto;

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
