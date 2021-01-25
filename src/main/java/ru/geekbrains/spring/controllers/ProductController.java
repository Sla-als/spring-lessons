package ru.geekbrains.spring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.model.Product;
import ru.geekbrains.spring.reposisitory.ProductRepository;

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

    private final ProductRepository productRepository;

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }

    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("delete/{id}")
    public List<Product> deleteById(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @PostMapping  // для создания новых ресурсов
    public Product save(@RequestBody Product product) {
        //product.setId(null);
        return productRepository.save(product);
    }

//          * К запросу всех товаров добавьте возможность фильтрации по минимальной и максимальной цене
//          (в трех вариантах:
//            товары дороже min цены, +
//            товары дешевле max цены, +
//            или товары, цена которых находится в пределах min-max). +

    // Методы к заданию со * :
//    @GetMapping("/test")
//    public List<Product> test(@RequestParam(name = "min") Integer min) {
//        return productRepository.findAllByPriceGreaterThanEqual(min);
//    }
//    @GetMapping("/test")
//    public List<Product> test(@RequestParam(name = "min") Integer min,@RequestParam(name = "max") Integer max) {
//        return productRepository.findAllByPriceBetween(min,max);
//    }
//    @GetMapping("/test")
//    public List<Product> test(@RequestParam(name = "max") Integer max) {
//        return productRepository.findAllByPriceLessThanEqual(max);
//    }


}


//
//@RestController
//@RequestMapping("/students")
//public class StudentController {
//
//    private final StudentRepository studentRepository;
//
//    @GetMapping
//    public List<Student> getAllStudents(){
//        return (List<Student>) studentRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public Student getStudentById(@PathVariable Long id){
//        return studentRepository.findById(id).get();
//    }
//
//    @GetMapping("/search_by_name")
//    public Student searchByName(@RequestParam String name) {
//        return studentRepository.findByName(name).get();
//    }
//
//    @GetMapping("/search_by_min_score")
//    public List<Student> searchByMinScore(@RequestParam (name = "min_score") Integer minScore) {
//        return studentRepository.findAllByScoreIsGreaterThanEqual(minScore);
//    }
//    @GetMapping("/test")
//    public Student testMethod() {
//        return studentRepository.customFinderById(2L, "Bob").get();
//    }
//
//    public StudentController(StudentRepository studentRepository) {
//        this.studentRepository = studentRepository;
//    }
//
//}
