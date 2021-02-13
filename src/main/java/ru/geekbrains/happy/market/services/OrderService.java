package ru.geekbrains.happy.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.happy.market.beans.Cart;
import ru.geekbrains.happy.market.dto.ProductDto;
import ru.geekbrains.happy.market.exceptions_handling.ResourceNotFoundException;
import ru.geekbrains.happy.market.model.Order;
import ru.geekbrains.happy.market.model.Product;
import ru.geekbrains.happy.market.model.User;
import ru.geekbrains.happy.market.repositories.OrderRepository;
import ru.geekbrains.happy.market.repositories.ProductRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;
    private String address;

    public Order createFromUserCart(User user, String address) {
        Order order = new Order(cart, user);
        order.setAddress(address);
        order = orderRepository.save(order);
        cart.clear();
        return order;
    }

    public List<Order> findAllOrdersByOwnerName(String username) {
        return orderRepository.findAllByOwnerUsername(username);
    }


}
