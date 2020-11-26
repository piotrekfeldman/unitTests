package pl.testing.order;

import org.hamcrest.MatcherAssert;
import pl.testing.Meal;
import pl.testing.extensions.BeforeAfterExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(BeforeAfterExtension.class)
class OrderTest {

    private pl.testing.order.Order order;

    @BeforeEach
    void initializeOrder(){
        order = new pl.testing.order.Order();
    }


    @AfterEach
    void Cleanup() {
        System.out.println("cancel");
        order.cancel();
    }

    @Test
    void testAssertArrayEquals(){

        //given
        int[] inst1 = {1, 2 , 3};
        int[] inst2 = {1, 2 , 3};

        //then
        assertArrayEquals(inst1, inst2);
    }

    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder() {

        //given

        //then
        assertThat(order.getMeals(),empty());
        assertThat(order.getMeals().size(), equalTo(0));
        MatcherAssert.assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/orders.csv")
    void addingMealToOrderShouldIncreaseOrderSize(int price, String name) {

        Map<Integer, String> map = new HashMap<>();
        int j = 0;
        while (j < 10) {
            map.put(price, name);
            j++;
        }

        Set set = map.entrySet();
        Iterator iterator = set.iterator();

        while (iterator.hasNext()) {
            Map.Entry mp = (Map.Entry) iterator.next();
            int key = (int) mp.getKey();
            String value = (String) mp.getValue();
            Meal meal = new Meal(key, value);
            order.addMealToOrder(meal);
            System.out.println(key + " : " + value);
        }
        System.out.println("8888888888888888888");
        System.out.println(order);
        System.out.println("8888888888888888888");

     /*   for (int i = 0; i <10 ; i++) {
            Meal meal = new Meal(price,name);
            pl.testing.pl.testing.order.addMealToOrder(meal);
        }

        System.out.println(pl.testing.pl.testing.order + " po iteracij");
*/
        //given
        Meal meal = new Meal(15, "burger");

        Meal meal2 = new Meal(30, "pizza");
        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);

        //then
        // assertThat(pl.testing.pl.testing.order.getMeals(),hasSize(2));
        int size = order.getMeals().size();

        for (int i = 0; i < size; i++) {
            System.out.println(order.getMeals().get(i).getName() + " " + order.getMeals().get(i).getPrice() + " zł");
        }

    }
    @Tag("meal")
    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder() {
        //given
        Meal meal = new Meal(15,"burger");
        Meal meal2 = new Meal(30,"pizza");
        //when
        order.addMealToOrder(meal);
        order.addMealToOrder(meal2);
        //then

        assertThat(order.getMeals(), contains(meal, meal2));

    }

    @Test
    void testIfTwoMealListAreTheSame() {
//        given
        Meal meal1 = new Meal(15,"burger");
        Meal meal2 = new Meal(30,"pizza");
        Meal meal3 = new Meal(10,"jajka na brodzie");

        List<Meal> meals1 = Arrays.asList(meal1, meal2);
        List<Meal> meals2 = Arrays.asList(meal1, meal2);

//        then

        assertThat(meals1, is(meals2));
       // assertThat(meal1, sameInstance(meal2));

    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize() {
        //given
        Meal meal = new Meal(15,"burger");
        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

//        then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal)));
    }

    @Test
    void orderTotalPriceShouldNotExceedsMaxIntValue() {

        //given
        Meal meal1 = new Meal(Integer.MAX_VALUE, "Burger");
        Meal meal2 = new Meal(Integer.MAX_VALUE, "Pizza");

        //when

        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
       // assertThrows(IllegalStateException.class, ()-> pl.testing.pl.testing.order.totalPrice());
        assertThrows(IllegalArgumentException.class, ()-> order.totalPrice());

    }

    @Test
    void emptyOrderTotalPriceShouldEqualZero() {

//        given
        //when
        //then
        assertThat(order.totalPrice(), is(0));
    }

    @Test
    void cancelingOrderShouldRemoveAllItemsFromMealsList() {

        //given
        Meal meal1 = new Meal(Integer.MAX_VALUE, "Burger");
        Meal meal2 = new Meal(Integer.MAX_VALUE, "Pizza");

        //when

        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);
        order.cancel();

        //then

        assertThat(order.getMeals().size(), is(0));

    }

}