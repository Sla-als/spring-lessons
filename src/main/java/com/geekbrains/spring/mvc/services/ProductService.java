package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductList() {
        return productRepository.getProductList();
    }

    public Product getProductListById(Long id) {
        return productRepository.getProductListById(id);
    }

//    public Product saveOrUpdateStudent(Product product) {
//        return productRepository.saveOrUpdateProduct(product);
//    }

    public void saveOrUpdateStudent(Product product) {
        productRepository.saveOrUpdateProduct(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
