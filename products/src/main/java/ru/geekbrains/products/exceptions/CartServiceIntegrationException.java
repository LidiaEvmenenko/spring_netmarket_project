package ru.geekbrains.products.exceptions;

public class CartServiceIntegrationException extends RuntimeException {
    public CartServiceIntegrationException(String message) {
        super(message);
    }
}
