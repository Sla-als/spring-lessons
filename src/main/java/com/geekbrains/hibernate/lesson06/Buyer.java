package com.geekbrains.hibernate.lesson06;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buyers")
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "buyer_cart",
            joinColumns =@JoinColumn(name ="buyer_id"),
            inverseJoinColumns =@JoinColumn(name="product_id")
    )
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Buyer() {
    }

    @Override
    public String toString() {
        return String.format("Buyer [id = %d, name = %s]", id , name);
    }
}
