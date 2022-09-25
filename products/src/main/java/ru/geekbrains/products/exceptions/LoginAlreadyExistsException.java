package ru.geekbrains.products.exceptions;

public class LoginAlreadyExistsException extends RuntimeException{
    public LoginAlreadyExistsException(String message) {
        super(message);
    }
}
