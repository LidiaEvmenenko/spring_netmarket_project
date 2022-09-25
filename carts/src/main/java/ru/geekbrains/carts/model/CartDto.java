package ru.geekbrains.carts.model;

import lombok.Data;
import ru.geekbrains.carts.entity.Cart;


@Data
public class CartDto {
    private Long id;
    private Long userId;
    private Long productId;
    private String productTitle;
    private Double productPrice;
    private Double amount;

  public CartDto(Cart cart) {
    this.id = cart.getId();
    this.userId = cart.getUserId();
    this.productId = cart.getProduct().getId();//getProductId();
    this.productTitle = cart.getProduct().getTitle();//getProductTitle();
    this.productPrice = cart.getProduct().getPrice();//getProductPrice();
    this.amount = cart.getAmount();
  }

  public CartDto() {
  }
}
