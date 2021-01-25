package ru.geekbrains.spring.reposisitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceGreaterThanEqual(int min);

    List<Product> findAllByPriceLessThanEqual(int max);

    List<Product> findAllByPriceBetween(int min, int max);

}
