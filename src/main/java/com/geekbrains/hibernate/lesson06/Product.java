package com.geekbrains.hibernate.lesson06;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products_new")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private String cost;

    @ManyToMany
    @JoinTable(
            name = "buyer_cart",
            joinColumns =@JoinColumn(name ="product_id"),
            inverseJoinColumns =@JoinColumn(name="buyer_id")
    )
    private List<Buyer> buyers;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("Product [id = %d, title = %s]", id , title);
    }
}

