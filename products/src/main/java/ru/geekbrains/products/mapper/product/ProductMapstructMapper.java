package ru.geekbrains.products.mapper.product;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.geekbrains.api.ApiProductsView;
import ru.geekbrains.products.entity.Product;
import ru.geekbrains.products.model.ProductDto;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapstructMapper {
    @Mappings(value = {
            @Mapping(target = "id", source = "product.id"),
            @Mapping(target = "categoryTitle", source = "category.title"),
            @Mapping(target = "manufacturerTitle", source = "manufacturer.title")
    })
    ProductDto mapToDto(Product product);

    @Mappings(value = {
            @Mapping(target = "id", source = "product.id"),
            @Mapping(target = "categoryTitle", source = "category.title"),
            @Mapping(target = "manufacturerTitle", source = "manufacturer.title")
    })
    ApiProductsView mapToView(Product product);

}














