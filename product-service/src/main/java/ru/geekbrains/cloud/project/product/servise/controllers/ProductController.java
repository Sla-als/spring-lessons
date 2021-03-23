package ru.geekbrains.cloud.project.product.servise.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.cloud.project.product.servise.Model.Product;
import ru.geekbrains.cloud.project.product.servise.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAll(){
        return productService.findAll();
    }

}
