package ru.geekbrains.products.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.api.exceptions.CartServiceAppError;
import ru.geekbrains.products.exceptions.CartServiceIntegrationException;
import ru.geekbrains.api.CartDto;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

    public void clearUserCart(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/0/clear")
                .header("username", username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public CartDto getUserCart(String username) {
        CartDto cart = cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                // .bodyValue(body) // for POST
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.is4xxClientError(), // HttpStatus::is4xxClientError
                        clientResponse -> clientResponse.bodyToMono(CartServiceAppError.class).map(
                                body -> {
                                    if (body.getCode().equals(CartServiceAppError.CartServiceErrors.CART_NOT_FOUND.name())) {
                                        return new CartServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: корзина не найдена");
                                    }
                                    if (body.getCode().equals(CartServiceAppError.CartServiceErrors.CART_IS_BROKEN.name())) {
                                        return new CartServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: корзина сломана");
                                    }
                                    return new CartServiceIntegrationException("Выполнен некорректный запрос к сервису корзин: причина неизвестна");
                                }
                        )
                )
//                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CartServiceIntegrationException("Выполнен некорректный запрос к сервису корзин")))
//                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new CartServiceIntegrationException("Сервис корзин сломался")))
                .bodyToMono(CartDto.class)
                .block();
        return cart;
    }
}
