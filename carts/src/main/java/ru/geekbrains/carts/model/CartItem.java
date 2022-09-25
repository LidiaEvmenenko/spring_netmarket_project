package ru.geekbrains.carts.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CartItem implements Serializable {

    @JsonProperty
    private Long productId;
    @JsonProperty
    private String productTitle;
    @JsonProperty
    private Double count;
    @JsonProperty
    private Double productPrice;
    @JsonProperty
    private Double sum;

}
