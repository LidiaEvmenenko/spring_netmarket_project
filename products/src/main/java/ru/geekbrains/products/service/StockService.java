package ru.geekbrains.products.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.products.repository.StockRepository;

@Service
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    @Transactional
    public void create(Long id, Double amount) {
        stockRepository.insertStock(amount, id);
    }
}
