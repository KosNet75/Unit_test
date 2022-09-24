package ru.kos75;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

@DisplayName("Тест: Basket")
public class BasketTest {

    @Test
    @DisplayName("Проверка добавления товара в корзину")
    void nameFile() throws Exception {

        HashMap<Integer, Integer> test = new HashMap<Integer, Integer>();
        test.put(1, 2);
        test.put(2, 2);
        test.put(3, 2);

        Basket basket = new Basket();
        basket.addToCart(1, 1);
        basket.addToCart(1, 1);
        basket.addToCart(2, 2);
        basket.addToCart(3, 2);

        System.out.println(basket.amountProduct);
        System.out.println(test);

        Assertions.assertEquals(basket.amountProduct, test);


    }
}