package ru.geekbrains.happy.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.happy.market.dto.ProductDto;
import ru.geekbrains.happy.market.model.Product;
import ru.geekbrains.happy.market.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    // +
    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).stream().map(ProductDto::new).findFirst();
    }
//    public Optional<Product> findProductById(Long id) {
//        return productRepository.findById(id);
//    }

    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public Page<ProductDto> findAll(int page) {
        Page<Product> originalPage = productRepository.findAll(PageRequest.of(page - 1, 5));
        return new PageImpl<>(originalPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), originalPage.getPageable(), originalPage.getTotalElements());
    }

    // +
    public List<ProductDto> findAllByPrice(int min, int max) {
        return productRepository.findAllByPriceBetween(min, max).stream().map(ProductDto::new).collect(Collectors.toList());
    }
//    public List<Product> findAllByPrice(int min, int max) {
//        return productRepository.findAllByPriceBetween(min, max);
//    }

    // -
    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    // -
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
