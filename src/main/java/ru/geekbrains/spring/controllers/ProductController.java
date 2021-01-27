package ru.geekbrains.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.model.Product;
import ru.geekbrains.spring.reposisitory.ProductRepository;
import ru.geekbrains.spring.servce.ProductService;

import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

//        Сделать RestController позволяющий выполнять следующий набор операции над этой сущностью:
//        получение товара по id [ GET .../app/products/{id} ] +
//        получение всех товаров [ GET .../app/products ]  +
//        создание нового товара [ POST .../app/products ] +
//        удаление товара по id.[ GET .../app/products/delete/{id} ] +
//        (Замечание: пока делаем немного не по правилам REST API, эта тема будет разбираться на следующих занятиях, поэтому удаление выполняется через http-метод GET, а не DELETE)

    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping  // для создания новых ресурсов
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

//          * К запросу всех товаров добавьте возможность фильтрации по минимальной и максимальной цене
//          (в трех вариантах:
//            товары дороже min цены, +
//            товары дешевле max цены, +
//            или товары, цена которых находится в пределах min-max). +

    @GetMapping
    public List<Product> productsFind(
            @RequestParam(name = "min", required = false) Integer min,
            @RequestParam(name = "max", required = false) Integer max
    ) {
        if (max != null && min != null) {
            return productService.between(min,max);
        }else if (max != null){
            return productService.less(max);
        }else if (min != null){
          return productService.greater(min);
        }
        return productService.findAll();
    }
}
