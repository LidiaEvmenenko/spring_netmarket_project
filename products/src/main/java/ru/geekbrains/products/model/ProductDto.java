package ru.geekbrains.products.model;

import lombok.Data;
import ru.geekbrains.products.entity.Product;

import java.util.Date;

@Data
//@NoArgsConstructor
public class ProductDto {
    private Long id;
    private int nom = 0;
    private String title;
    private String categoryTitle;
    private Double price;
    private byte[] image;
    private Date expiration_date;
    private String description;
    private Double weight;
    private String manufacturerTitle;
    private Double count;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.nom++;
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory().getTitle();
        this.count = Double.valueOf(0);
        this.expiration_date = product.getExpiration_date();
        this.weight = product.getWeight();
        this.manufacturerTitle = product.getManufacturer().getTitle();

    }

    public ProductDto() {
    }

}
