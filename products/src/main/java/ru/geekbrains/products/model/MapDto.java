package ru.geekbrains.products.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MapDto {
    private String title;
    private Long timeService;
}
