package ru.geekbrains.products.model;

import lombok.Data;

@Data
public class FileSaveRequest {
    private String name;
    private String text;
}
