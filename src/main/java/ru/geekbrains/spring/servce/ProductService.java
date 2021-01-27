package ru.geekbrains.spring.servce;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.model.Product;
import ru.geekbrains.spring.reposisitory.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void deleteById(@PathVariable Long id) {
        productRepository.deleteById(id);
    }

    public Product save(@RequestBody Product product) {
        //product.setId(null);
        return productRepository.save(product);
    }

//          * К запросу всех товаров добавьте возможность фильтрации по минимальной и максимальной цене
//          (в трех вариантах:
//            товары дороже min цены, +
//            товары дешевле max цены, +
//            или товары, цена которых находится в пределах min-max). +


    @GetMapping("/greater")
    public List<Product> greater(@RequestParam(name = "min") Integer min) {
        return productRepository.findAllByPriceGreaterThanEqual(min);
    }

    @GetMapping("/between")
    public List<Product> between(@RequestParam(name = "min") Integer min, @RequestParam(name = "max") Integer max) {
        return productRepository.findAllByPriceBetween(min, max);
    }

    @GetMapping("/less")
    public List<Product> less(@RequestParam(name = "max") Integer max) {
        return productRepository.findAllByPriceLessThanEqual(max);
    }

}
