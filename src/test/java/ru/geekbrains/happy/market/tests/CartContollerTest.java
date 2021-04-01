package ru.geekbrains.happy.market.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.geekbrains.happy.market.controllers.CartController;
import ru.geekbrains.happy.market.model.Cart;
import ru.geekbrains.happy.market.repositories.CartRepository;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartContollerTest {

    @Autowired
    MockMvc mvc; // эмулятор веб-окружения

    @Autowired
    private CartRepository cartRepository;

    private UUID uuid;

    @BeforeEach
    public void getUUID() {
        uuid = cartRepository.save(new Cart()).getId();
    }

    @Test
    public void testCart() throws Exception {
        MvcResult result = mvc.perform(get("/api/v1/cart/" + uuid)).andExpect(status().isOk()).andReturn();
    }

    @Test
    public void productAddCartTest() throws Exception {
        mvc.perform(get("/api/v1/cart/" + uuid + "/add/1")).andExpect(status().isOk());

        mvc.perform(get("/api/v1/cart" + uuid)).andExpect(status().isOk())
                .andExpect(jsonPath("$.items").isArray());
             //  andExpect(jsonPath("$.items[0].title",is("Milk")));
    }

}
