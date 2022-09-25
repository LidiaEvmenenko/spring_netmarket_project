package ru.geekbrains.products.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.api.ApiManufacturerListView;
import ru.geekbrains.api.ApiManufacturerView;
import ru.geekbrains.products.entity.Manufacturer;
import ru.geekbrains.products.service.ManufacturerService;

import java.util.List;

@RestController
@RequestMapping("api/v1/manufacturer")
@RequiredArgsConstructor
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    @PostMapping()
    public void createNewManufacturer(@RequestBody ApiManufacturerView manufacturer) {
        manufacturerService.create(manufacturer.getTitle());
    }


    @GetMapping
    public ApiManufacturerListView findAll(){
        ApiManufacturerListView manufacturerListView = new ApiManufacturerListView();
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        for (Manufacturer c: manufacturers) {
            ApiManufacturerView view = new ApiManufacturerView();
            view.setTitle(c.getTitle());
            manufacturerListView.addItem(view);
        }
        return manufacturerListView;
    }
}
