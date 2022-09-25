package ru.geekbrains.products.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.api.ApiProductsListView;
import ru.geekbrains.api.ApiProductsView;
import ru.geekbrains.products.entity.Category;
import ru.geekbrains.products.entity.Manufacturer;
import ru.geekbrains.products.entity.Product;
import ru.geekbrains.products.mapper.product.ProductMapper;
import ru.geekbrains.products.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public ApiProductsView findById(Long id) {
         Optional<Product> product = productRepository.findById(id);
        if (!product.isEmpty()) {
            ApiProductsView productsView = productMapper.mapToView(product.get());
            return productsView;
        }
        return null;
    }

    public Optional<Product> findByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    @Transactional
    public void create(ApiProductsView dto) {
        Optional<Category> category = categoryService.findByTitle(dto.getCategoryTitle());
        Optional<Manufacturer> manufacturer = manufacturerService.findByTitle(dto.getManufacturerTitle());
        productRepository.insertProduct(dto.getExpiration_date(), dto.getPrice(),dto.getTitle(),
                dto.getWeight(),category.get().getId(),manufacturer.get().getId());
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }


    public ApiProductsListView findAll() {
        List<Product> products = productRepository.findAll();

        ApiProductsListView listView = new ApiProductsListView();
        for (Product p :products) {
            ApiProductsView view = productMapper.mapToView(p);

            listView.addProductToList(view);
        }

        return listView;
    }

    @Transactional
    public ApiProductsListView findProductsByCategory(String title){
        Optional<Category> category = categoryService.findByTitle(title);
        if (!category.isEmpty()) {
            List<Product> products = productRepository.findByCategory_Id(category.get().getId());
            if (!products.isEmpty()) {
                ApiProductsListView view = new ApiProductsListView();
                for (Product p :products) {
                    view.addProductToList(productMapper.mapToView(p));
                }
                return view;
            }
        }
        return new ApiProductsListView();
    }

    @Transactional
    public ApiProductsListView findByManufacturer(String title){
        Optional<Manufacturer> manufacturer = manufacturerService.findByTitle(title);
        if (!manufacturer.isEmpty()) {
            List<Product> products = productRepository.findByManufacturer_Id(manufacturer.get().getId());
            if (!products.isEmpty()) {
                ApiProductsListView view = new ApiProductsListView();
                for (Product p :products) {
                    view.addProductToList(productMapper.mapToView(p));
                }
                return view;
            }
        }
        return new ApiProductsListView();
    }

    @Transactional
    public void update(ApiProductsView dto){
        ApiProductsView productsView = findById(dto.getId());
        if (productsView != null){
            Product product = productMapper.mapToEntity(productsView);
            Optional<Category> category = categoryService.findByTitle(dto.getCategoryTitle());
            if (!category.isEmpty()) { product.setCategory(category.get()); }
            Optional<Manufacturer> manufacturer = manufacturerService.findByTitle(dto.getManufacturerTitle());
            if (!manufacturer.isEmpty()) { product.setManufacturer(manufacturer.get()); }
            System.out.println("productsView.getId() = "+productsView.getId());
            productRepository.update(dto.getExpiration_date(), dto.getPrice(), dto.getTitle(), dto.getWeight(),
                    category.get().getId(), manufacturer.get().getId(), productsView.getId());

        }
    }

}















