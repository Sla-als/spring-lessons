package ru.geekbrains.boot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.services.ProductService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String showAll(Model model,
                          @RequestParam(required = false, name = "min_cost") Integer minCost,
                          @RequestParam(required = false, name = "max_cost") Integer maxCost
    ) {
        model.addAttribute("products", productService.findAll(minCost,maxCost));
        return "products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id, HttpServletResponse response) {
        productService.deleteByid(id);
        return "redirect:/products";

    }

    @PostMapping("/add")
    public String saveNewProduct(@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        System.out.println(
                product.getId()+" "+
                product.getTitle()+" "+
                product.getCost()
                );
        return "redirect:/products/";
    }

}
