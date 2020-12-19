package ru.geekbrains.boot.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.boot.model.Product;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ProductInMemoryRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>(Arrays.asList(
                new Product(1L, "product_1", 200),
                new Product(2L, "product_2", 20),
                new Product(3L, "product_3", 20001),
                new Product(4L, "product_4", 200),
                new Product(5L, "product_5", 10),
                new Product(6L, "product_6", 200),
                new Product(7L, "product_7", 10)
        ));
    }

    public Product saveOrUpdate(Product p) {
        //        Update
        if (p.getId() != null) {
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getId() == p.getId()) {
                    products.set(i, p);
                    return p;
                }
            }
        }
        //        Save
        Long newId = products.stream().mapToLong(Product::getId).max().orElseGet(() -> 0L) + 1L;
        p.setId(newId);
        products.add(p);
        return p;
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(s -> s.getId().equals(id)).findFirst();
    }

    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
