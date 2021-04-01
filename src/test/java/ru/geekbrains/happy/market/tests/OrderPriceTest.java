package ru.geekbrains.happy.market.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.happy.market.model.Cart;
import ru.geekbrains.happy.market.model.CartItem;
import ru.geekbrains.happy.market.model.Order;
import ru.geekbrains.happy.market.repositories.CartRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;


@SpringBootTest(classes = {Cart.class, CartItem.class})
@ActiveProfiles("test")
public class OrderPriceTest {
    @Autowired
    private Cart cart;

    // @MockBean
    @Autowired
    private CartItem cartItem;

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void testPrice() {
        CartItem cartItem1 = new CartItem();
        cartItem1.setPrice(10);

        CartItem cartItem2 = new CartItem();
        cartItem2.setPrice(90);

        cartItem2.setPrice(190);

        cart.setItems(Arrays.asList(cartItem1, cartItem2));
        cart.recalculate();
        Assertions.assertEquals(200, cart.getPrice());
    }

}
