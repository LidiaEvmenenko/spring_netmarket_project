package ru.geekbrains.products.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.api.ApiStockView;
import ru.geekbrains.products.service.StockService;

@RestController
@RequestMapping("api/v1/stock")
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;

    @PostMapping
    public  void createNewStock(@RequestBody ApiStockView stock){
        stockService.create(stock.getProductId(), stock.getAmount());
    }
}
