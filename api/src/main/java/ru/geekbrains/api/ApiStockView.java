package ru.geekbrains.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiStockView {
    @JsonProperty
    private String productTitle;

    @JsonProperty
    private Double amount;

    @JsonProperty
    private Long productId;
}
