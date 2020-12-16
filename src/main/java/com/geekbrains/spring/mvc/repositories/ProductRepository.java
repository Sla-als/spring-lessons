package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {

    //  Товары необходимо хранить в репозитории (класс, в котором в виде List<Product> хранятся товары).
    List<Product> productList;
    private Long maxId;

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    //  Репозиторий должен уметь выдавать список всех товаров и товар по id;
    public Product getProductListById(Long id) {
        for (Product p : productList) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        throw new RuntimeException("Product not found");
    }


    @PostConstruct
    public void init() {
        this.productList = new ArrayList<>();
        this.maxId = 2L;
    }

//    public Product saveOrUpdateProduct (Product product) {
//        if (product.getId() == null) {
//            maxId++;
//            product.setId(maxId);
//            productList.add(product);
//            return product;
//        } else {
//            for (int i = 0; i < productList.size(); i++) {
//                if (productList.get(i).getId().equals(product.getId())) {
//                   productList.set(i, product);
//                    return product;
//                }
//            }
//        }
//        throw new RuntimeException("What???");
//    }

    public void saveOrUpdateProduct(Product product) {
        productList.add(product);
    }

    public ProductRepository() {
    }

    public void deleteById(Long id) {
        productList.removeIf(b -> b.getId().equals(id));
    }


}
