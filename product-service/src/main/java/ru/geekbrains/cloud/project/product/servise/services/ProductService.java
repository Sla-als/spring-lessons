package ru.geekbrains.cloud.project.product.servise.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.cloud.project.product.servise.Model.Product;
import ru.geekbrains.cloud.project.product.servise.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
