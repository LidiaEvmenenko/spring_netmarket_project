package ru.geekbrains.products.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthResponse {
    private String token;
    private String message;
    private String role;

    public AuthResponse(String token, String message, String role) {
        this.message = message;
        this.token = token;
        this.role = role;
    }
}
