package pl.testing.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pl.testing.Meal;
import pl.testing.order.Order;
import pl.testing.order.OrderStatus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

//zwiększa restrykcyjność naszych testów
// w ten sposób możemy sprawdzić jak korzystamy z naszych mocków i stubów w naszych testach
// co może skutkować szybszym wykryciem błędów
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
//Aby skorzystać z adnotacji mockito (inject, mock, captor) należy użyć extendwith
@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    // poniższe adnotacje umożliwiają skasowanie instacji odpowiadających im
    //instacji w metodach testowych poniżej

    //@injectmocks - określa w której klasie będą się znajdować zależności które w testach zamieniamy w mocki
    @InjectMocks
    private CartService cartService;
    //adnotacja mock określa dany obiekt mockowy
    @Mock
    private CartHandler cartHandler;
    @Captor
    private ArgumentCaptor<Cart> argumentCaptor;


    @Test
    void processCartShouldSendToPrepare() {

        //given
        Meal meal = new Meal("pizza");
        Order order = new Order();
        order.addMealToOrder(meal);
        Cart cart = new Cart();
        cart.addOrdertoCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when

        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler).canHandleCart(cart);
        then(cartHandler).should().canHandleCart(cart);
        verify(cartHandler).sendToPrepare(cart);
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
        System.out.println(resultCart.getOrders().get(0).getMeals().get(0));

        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);
    }
    @Test
    void processCartShouldNotSendToPrepare() {

        //given
        Meal meal = new Meal("pizza");
        Order order = new Order();
        order.addMealToOrder(meal);
        Cart cart = new Cart();
        cart.addOrdertoCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        //when

        Cart resultCart = cartService.processCart(cart);

        //then
      //  verify(cartHandler, never()).canHandleCart(cart);
      //  then(cartHandler).should(never()).canHandleCart(cart);
        verify(cartHandler, never()).sendToPrepare(cart);
        then(cartHandler).should(never()).sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));


    }

    @Test
    void processCartShouldSendToPrepareWithArgumentMatchers() {


        // matchery - kiedy nie wiemy z jakimi wartościami wywoływana jest dana metoda

        //given
        Meal meal = new Meal("pizza");
        Order order = new Order();
        order.addMealToOrder(meal);
        Cart cart = new Cart();
        cart.addOrdertoCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        //Argument matchers wykorzystujemy w momencie kiedy nie wiemy z jakim konkretnie argumentem
        // będzie wywoływana metoda na danym mocku
        // zostanie przekazany do canHandleCart(?cart?)
        //wtedy korzystamy z funkcji any
        //!! nie mieszamy matcherów z wartościami np matcher anyint + "test" < jako zwykły tekst
        // ew. anyint + anystring zamiast "test"

        //przekazuje dowolny argument (any)
        given(cartHandler.canHandleCart(any())).willReturn(true);

        //przekazuje  argument z klasy Cart:
        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(true);
        // przekazuje inty tylko
        //given(cartHandler.canHandleCart(anyInt())).willReturn(true);


        //when

        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler).canHandleCart((any(Cart.class)));
        then(cartHandler).should().canHandleCart((any(Cart.class)));
        verify(cartHandler).sendToPrepare((any(Cart.class)));
        then(cartHandler).should().sendToPrepare((any(Cart.class)));
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));

        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);
    }

    @Test
    void canHandleCartShouldReturnMultipleValues() {

        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrdertoCart(order);
        CartHandler cartHandler = mock(CartHandler.class);

        //wywołujemy tę samą metodę wiele razy nawet jeżeli logika nie jest tak zapisana
        given(cartHandler.canHandleCart(cart)).willReturn(true, false, false, true);

        //then

        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));



    }


    @Test
    void processCartShouldSendToPrepareWithLambdas() {

        //given
        Meal meal = new Meal("pizza");
        Meal meal2 = new Meal("burger");
        Order order = new Order();
        order.addMealToOrder(meal);
        Order order2 = new Order();

        order2.addMealToOrder(meal2);

        Cart cart = new Cart();
        cart.addOrdertoCart(order);
        cart.addOrdertoCart(order2);



        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(argThat(c -> c.getOrders().size() == 2 ))).willReturn(true);

        //when

        Cart resultCart = cartService.processCart(cart);

        //then

        then(cartHandler).should().sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(2));
     //   assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));


    }

    @Test
    void canHandleCartShouldThrowException() {

        //given
        Meal meal = new Meal("pizza");
        Order order = new Order();
        order.addMealToOrder(meal);
        Cart cart = new Cart();
        cart.addOrdertoCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);



        given(cartHandler.canHandleCart(cart)).willThrow(IllegalStateException.class);

        //when
        //then
        assertThrows(IllegalStateException.class, () -> cartService.processCart(cart));

    }

    @Test
    void processCartShouldSendToPrepareWithArgumentCaptor() {
    // sprawdzenie wartości argumentów przekazywanych do tej metody
        // z jakimi argumentami dana metoda została wywołana
        // z argument captora korzystamy w sekcji then ponieważ kłóciłoby się to z BDD



        //given
        Meal meal = new Meal("pizza");
        Order order = new Order();
        order.addMealToOrder(meal);
        Cart cart = new Cart();
        cart.addOrdertoCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);
        ArgumentCaptor<Cart> argumentCaptor = ArgumentCaptor.forClass(Cart.class);
        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);


        //then
        then(cartHandler).should().canHandleCart(argumentCaptor.capture());

        // w obydwu poniżej przytoczonych przypadkach aby test przeszedł
        // należy zmienić logikę w kodzie bazowym (czyli np. wywołać metodę dwukrotnie)
        // oraz zapisać metodę then tak aby była tożsama z tą logiką
        //get value- wykorzystujemy wtedy kiedy dana matoda wywołana jest tylko jeden raz
        then(cartHandler).should().sendToPrepare(argumentCaptor.capture());
         assertThat(argumentCaptor.getValue().getOrders().size(), equalTo(1));
        //get allvaluee- wykorzystujemy wtedy kiedy dana matoda wywołana jest więcej niż jeden raz
        //then(cartHandler).should(times(2)).sendToPrepare(argumentCaptor.capture());
        //assertThat(argumentCaptor.getAllValues().size(), equalTo(2));



        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));


    }

    @Test
    void shouldDoNothingWhenProcessCart() {

        // weryfikacja metod void czy zostały wywołane
        // innymi slowy jeżeli na danym mocku maasz wywołać metodę X to ma ona nie robić nic
        // takie zachowanie jest domyślne
        // tzn jeżeli nie zaprogramujemy na mocku żadnego zachowania lub reakcji na wywolanie metody void
        // to sie nic nie stanie i taka metoda nic nie zrobi


        //given
        Meal meal = new Meal("pizza");
        Order order = new Order();
        order.addMealToOrder(meal);
        Cart cart = new Cart();
        cart.addOrdertoCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);
        doNothing().when(cartHandler).sendToPrepare(cart);
        // metoda ma nie robić nic
        willDoNothing().given(cartHandler).sendToPrepare(cart);
        // chainowanie/  łączenie metod ; za pierwszym razem ma nie robić nic, za drugim razem
        // ma zwrócić wyjątek ; tj poniżej najpierw willDoNothing a potem zwraca Exception
        willDoNothing().willThrow(IllegalStateException.class).given(cartHandler).sendToPrepare(cart);

        //when

        Cart resultCart = cartService.processCart(cart);

        //then
        then(cartHandler).should().canHandleCart(cart);
        then(cartHandler).should().sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
     //   assertThrows(IllegalStateException.class, ()-> cartService.processCart(cart));



    }

    @Test
    void shouldAnswerWhenProcessCart() {

        //given
        Meal meal = new Meal("pizza");
        Order order = new Order();
        order.addMealToOrder(meal);
        Cart cart = new Cart();
        cart.addOrdertoCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);


        //doAnswer -> służy do przekazania argumentu ujętego w metodzie
        // w tym przypadku >> canHandleCart(cart) << argumentem jest cart
        // na którym dokonujemy w ciele doAnswer operacji na tym argumencie
        // w tym przypadku wywołujemy lambdę a następnie konkretną metodę
        // 1 sposób zapisania metody doAnswer
        // metoda bardzo przydatna kiedy wiemy jakie operacje wykonujemy na danym argumencie
        //
        doAnswer(invocationOnMock -> {
            Cart argumentCart = invocationOnMock.getArgument(0);
             argumentCart.clearCart();

           // argumentCart.getOrders();
            return true;
        }).when(cartHandler).canHandleCart(cart);

        // 2 sposób
        when(cartHandler.canHandleCart(cart)).then(invocationOnMock -> {
            Cart argumentCart = invocationOnMock.getArgument(0);
            argumentCart.clearCart();
            // argumentCart.getOrders();
            return true;
        });

        // 3 sposób - bardziej dopasowany pod BDD

        willAnswer(invocationOnMock -> {
            Cart argumentCart = invocationOnMock.getArgument(0);
            argumentCart.clearCart();
            // argumentCart.getOrders();
            return true;
        }).given(cartHandler).canHandleCart(cart);

        // 4 sposób
        given(cartHandler.canHandleCart(cart)).will(invocationOnMock -> {
            Cart argumentCart = invocationOnMock.getArgument(0);
            argumentCart.clearCart();
            // argumentCart.getOrders();
            return true;
        });

        //when

        Cart resultCart = cartService.processCart(cart);

        //then
        then(cartHandler).should().sendToPrepare(cart);
       // assertThat(resultCart.getOrders().size(), equalTo(0));
        assertThat(resultCart.getOrders(), hasSize(0));
        //assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));


    }
    @Test
    void deliveryShouldBeFree() {

        //given
        Cart cart = new Cart();
        cart.addOrdertoCart(new Order());
        cart.addOrdertoCart(new Order());
        cart.addOrdertoCart(new Order());

        CartHandler cartHandler = mock(CartHandler.class);

        given(cartHandler.isDeliveryFree(cart)).willCallRealMethod();

        //when
        boolean isDeliveryFree = cartHandler.isDeliveryFree(cart);


        //then
        assertTrue(isDeliveryFree);

    }
}