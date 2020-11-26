package pl.testing;

import pl.testing.order.Order;
import pl.testing.extensions.IAExceptionIgnoreExtension;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class MealTest {


    @Test
    void Collections23() {

        List<String> list = new ArrayList<>();
        list.add("PIOTR");
        list.add("MARTA");
        List<String> list2 = new ArrayList<>();
        list2.add("PIOTR");
        list2.add("MARTA");


       assertThat(list, equalTo(list2));
       // assertThat(list,not(equalTo(list2)));

    }

    @Test
    void NameInReverse() {

        String str = "Piotr";
        String t= "";
        List<Character> list = new ArrayList<>();
        for (int i = str.length()-1; i>=0 ; i--) {

            t= t+ str.charAt(i);

        }

        System.out.println(t);
    }


    @Test
    void shouldReturnDiscountedPrice() {

        //given
    Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(6);
        System.out.println(discountedPrice);

        //then
        assertEquals(29,discountedPrice);
        assertThat(discountedPrice, equalTo(29));
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual(){

        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

//        assertSame(meal1, meal2);
//        assertThat(meal1, sameInstance(meal2));

//        assertJ :
        assertThat(meal1).isSameAs(meal2);

    }

    @Test
    void referencesToTheDifferentObjectShouldNotBeEqual(){

        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(20);

        assertNotSame(meal1, meal2);
        assertThat(meal1, Matchers.not(sameInstance(meal2)));

    }

    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame() {

        //given
        Meal meal1 = new Meal(30, "pizza");
        Meal meal2 = meal1;

        //then

        assertEquals(meal1,meal2);
        assertThat(meal1, sameInstance(meal2));
    }

    @Test
    void exceptionShouldBeThrownIfDiscountIsHigherThanThePrice() {
//given
        Meal meal  = new Meal(10, "Soup");

//then
        // asserthrows oczekuje rzucenia wyjątkiem, czyli jeżeli tak jak w przykładzie
        //poniżej test przechodzi to znaczy że wyjątek został rzucony
        //i tym samym jest udany
        // w tym przypadku ustawiamy discounted price wyższą i test przechodzi = rzuca wyjątkiem
        assertThrows(IllegalArgumentException.class, () ->meal.getDiscountedPrice(20));



    }



    @ExtendWith(IAExceptionIgnoreExtension.class)
    @ParameterizedTest
    @ValueSource(ints = {5, 10, 12 , 16})
    void mealPricesShouldBeLowerThan20(int price){

        if ( price >15){
            throw new IllegalArgumentException();
        }

        assertThat(price, lessThan(20));
    }
    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNameAndPrice(String name, int price){
        assertThat(name, containsString("bur"));
        assertThat(price, greaterThanOrEqualTo(10));

    }


    private static Stream<Arguments> createMealsWithNameAndPrice() {

        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheesebureger", 20)
                );
    }

    @ParameterizedTest
    @MethodSource("createCake")
    void cakeNameShouldEndWithCake(String name) {
        assertThat(MealTest.createCake(), anyOf(
                notNullValue(),
                equalTo("Fruitcake")
             )
        );


    }





    private static Stream<String> createCake () {
        List<String> cakeNames = Arrays.asList("Cheesecake", "Fruitcake");
        return cakeNames.stream();
    }

    @TestFactory
    Collection<DynamicTest> dynamicTestCollection() {

        return Arrays.asList(
                DynamicTest.dynamicTest("Test Dynamiczny 1", ()->assertThat(5, lessThan(6))),
                DynamicTest.dynamicTest("Test Dynamiczny 2", ()-> assertThat(7, greaterThan(6))));
    }
    @TestFactory
    @DisplayName("Test dynamiczny")
    Collection<DynamicTest> calculateMealPrices() {
        Order order = new Order();
        order.addMealToOrder(new Meal(10, 2, "Hamburger"));
        order.addMealToOrder(new Meal(7, 5, "Pizza"));
        order.addMealToOrder(new Meal(5, 2, "Shake"));

        Collection<DynamicTest> dynamicTests = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < order.getMeals().size(); i++) {
            int price = order.getMeals().get(i).getPrice();
            int quantity = order.getMeals().get(i).getQuantity();
            String name = order.getMeals().get(i).getName();
            j++;
            Executable executable = () -> {
                assertThat(calculatePrice(price, quantity), lessThan(67));

            };

            String name1 = "Test name: "+ j;
            DynamicTest dynamicTest = DynamicTest.dynamicTest(name1, executable);
            dynamicTests.add(dynamicTest);

        }
    return dynamicTests;
    }
    private int calculatePrice(int price, int quantity){

        return price * quantity;
    }


    @Test
    void Collections2() {

        List<String> list = new ArrayList<>();
        list.add("PIOTR");
        list.add("MARTA");
        List<String> list2 = new ArrayList<>();

        for (String t: list) {

            list2.add(t);
        }

        assertThat(list, equalTo(list2));
    }

    @Test
    void testMealSumPrice() {

        //given
        Meal meal = mock(Meal.class);
        given(meal.getPrice()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);
        // wywołanie prawdziwej metody na mocku:
        given(meal.sumPrice()).willCallRealMethod();

        //when

        int result = meal.sumPrice();

        assertThat(result, equalTo(45));

    }

    @Test
    void testMealSumPriceWithSpy() {
        // wrapper- obiekt opakowującym obiekt danej klasy, ktorego działanie możemy śledzić
        // i weryfikować , podobnie jak to ma miejsce z obiektami mockowymi
        // Spy używamy kiedy chcemy zachować możliwość weryfikacji wywołania metod
        // zachowująch ich prawdziwe zachowanie
        //aby funkcja spy działała musimy użyć w danej klasie konstruktora bezargumentowego
        // lub gdy wywolanie konstruktora argumentowego jest konieczne to wtedy robimy tak
        /*Meal mealSpy = new Meal (14, 2, "burrito");
        Meal meal = spy(mealSpy);*/

        //given
        Meal meal = spy(Meal.class);
        given(meal.getPrice()).willReturn(15);
        given(meal.getQuantity()).willReturn(3);

        //when

        int result = meal.sumPrice();

        //then
        then(meal).should().getPrice();
        then(meal).should().getQuantity();
        assertThat(result, equalTo(45));

    }



}
