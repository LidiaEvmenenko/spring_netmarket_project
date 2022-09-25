package ru.geekbrains.products.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RegistrationRequest {
    private String username;
    private String password;
    private String email;
    private String phonenumber;
}
