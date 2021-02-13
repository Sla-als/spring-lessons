package ru.geekbrains.happy.market.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.geekbrains.happy.market.beans.Cart;
import ru.geekbrains.happy.market.exceptions_handling.ResourceNotFoundException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "price")
    private int price;

    @Column(name = "address")
    private String address;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Order(Cart cart, User user) {
        this.items = new ArrayList<>();
        this.owner = user;
        this.price = cart.getTotalPrice();
        cart.getItems().stream().forEach((oi) -> {
            oi.setOrder(this);
            items.add(oi);
        });
        if (items.isEmpty()){
        throw new ResourceNotFoundException("Cart empty");
        }
    }
}
