package pl.testing.order;

import pl.testing.Meal;

import java.util.ArrayList;
import java.util.List;

public class  Order {


    private List<Meal> meals = new ArrayList<>();
    private OrderStatus orderStatus;

    public void addMealToOrder(Meal meal) {
        this.meals.add(meal);
    }

    public void removeMealFromOrder(Meal meal){
        this.meals.remove(meal);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;

    }


    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    void cancel() {
            this.meals.clear();
            }

    @Override
    public String toString() {
        return "Order{" +
                "meals=" + meals +
                '}';
    }

    int totalPrice() {

        int sum = this.meals.stream().mapToInt(meal->meal.getPrice()).sum();

        if(sum < 0) {
           // throw  new IllegalStateException("Price limit exceeded");
            throw  new  IllegalArgumentException("fdspojf");
        } else {
            return sum;
        }
    }
}
