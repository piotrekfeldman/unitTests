package pl.testing.cart;

import pl.testing.Meal;
import pl.testing.order.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {


    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    void addOrdertoCart(Order order)  {

        this.orders.add(order);
    }

    void clearCart() {
        this.orders.clear();
    }

    void simulateLargeOrder() {
        int j = 1;
        for (int i = 1; i <= 1000 ; i++) {

            Meal meal = new Meal(j%10, "Hamburger no " +i);
            Order order = new Order();
            order.addMealToOrder(meal);
            addOrdertoCart(order);
            j+=2;
        }


        System.out.println("Cart size " + orders.size());
        for (Order order : orders) {
            System.out.println(order);
        }
        clearCart();

    }
}
