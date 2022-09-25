package ru.geekbrains.products.mapper.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.api.ApiProductsView;
import ru.geekbrains.products.entity.Product;
import ru.geekbrains.products.model.ProductDto;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            dto.setCategoryTitle(product.getCategory().getTitle());
        }
        return dto;
    }
    public ApiProductsView mapToView(Product product) {
        ApiProductsView view = new ApiProductsView();
        view.setId(product.getId());
        view.setTitle(product.getTitle());
        view.setPrice(product.getPrice());
        if (product.getCategory() != null) {
            view.setCategoryTitle(product.getCategory().getTitle());
        }
        if (product.getManufacturer() != null) {
            view.setManufacturerTitle(product.getManufacturer().getTitle());
        }
        view.setExpiration_date(product.getExpiration_date());
        view.setWeight(product.getWeight());
        return view;
    }

    public Product mapToEntity(ApiProductsView dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setTitle(dto.getTitle());
        product.setPrice(dto.getPrice());
        if (dto.getExpiration_date() != null) { product.setExpiration_date(dto.getExpiration_date()); }
        product.setWeight(dto.getWeight());
        return product;
    }
}