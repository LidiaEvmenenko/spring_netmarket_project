package ru.geekbrains.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCartItem {
    @JsonProperty
    private Long id;
    @JsonProperty
    private Long productId;
    @JsonProperty
    private String productTitle;
    @JsonProperty
    private Double amount;
    @JsonProperty
    private Double price;
    @JsonProperty
    private Double sum;
}
