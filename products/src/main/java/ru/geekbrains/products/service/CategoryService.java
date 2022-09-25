package ru.geekbrains.products.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.products.entity.Category;
import ru.geekbrains.products.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    @Transactional
    public void create(String title) {
        categoryRepository.insertCategory(title);
    }

}
