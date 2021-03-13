package ru.geekbrains.happy.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.happy.market.model.ProductEntity;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private int price;

    public ProductDto(ProductEntity p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
    }
}
