package pl.testing.cart;

import pl.testing.Meal;
import pl.testing.cart.Cart;
import pl.testing.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    //@Disabled
    @DisplayName("Cart is able to process 1000 orders during 100 ms")
    @Test
    void simulateLargeOrder() {

        //given

        Cart cart = new Cart();

        //when
        //then

        assertTimeout(Duration.ofMillis(200), cart::simulateLargeOrder);

    }

    @Test
    void cartShouldNotBeEmptyAfterAddingOrderToCart(){
        Order order = new Order();
        Meal meal;
        Random random = new Random();
        //given
        int i = 0;
        while (i < 10) {
            meal = new Meal(random.nextInt(33), "pizza");
            order.addMealToOrder(meal);
            i++;
        }

        Cart cart = new Cart();

        //when
        cart.addOrdertoCart(order);


        //then
        System.out.println(cart.getOrders());


    }
    @Tag("meal")
    @Test
    void conjunctedAssertions() {

        Order order = new Order();
        Cart cart = new Cart();


        cart.addOrdertoCart(order);

        // assercja alternatywy
        assertThat(cart.getOrders(),anyOf(
                notNullValue(),
                hasSize(1),
                is(not(emptyCollectionOf(Order.class)))
        ));

        // assercja koniunkcji
        assertThat(cart.getOrders(),allOf(
                notNullValue(),
                hasSize(1),
                is(not(emptyCollectionOf(Order.class)))
        ));

        //dowolna asercja

        assertAll(
                () -> assertThat(cart.getOrders(), notNullValue()),
                () -> assertThat(cart.getOrders(), hasSize(1)),
                () -> assertThat(cart.getOrders().get(0).getMeals(), empty()),
                () -> {
                    List<Meal> mealList = cart.getOrders().get(0).getMeals();
                    assertThat(mealList, empty());
                }
        );



    }


}