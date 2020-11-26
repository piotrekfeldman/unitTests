package pl.testing.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

/*import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;*/

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AccountTest {

    @Test
    void newlyCreatedAccountShouldNotHaveActiveFlagSet() {

        Account newAccount = new Account();

//Asercje Hamcrest:
       //assertFalse(newAccount.isActive());
        assertThat(newAccount.isActive(), equalTo(false));
//        assertThat(newAccount.isActive(), is(false));

        //Asercje assertj:
//        assertThat(newAccount.isActive()).isFalse();


    }
    @Test
    void activatedAccountShouldHaveActiveFlagSet() {

        //given
        Account newAccount = new Account();

        //when
        newAccount.activate();
     //   assertFalse(newAccount.isActive());

        //then
        assertTrue(newAccount.isActive());
        assertThat(newAccount.isActive(), equalTo(true));


    }


    @Test
    void newlyCreatedAccountShouldNotHaveDeliveryAddressSet() {

        //given
        Account account = new Account();

        //when

        Address address = account.getDefaultDeliveryAddress();

        //then
        assertNull(address);
        assertThat(address, is(nullValue()));

    }

    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet() {
        //given
        Account account = new Account();
        Address address = new Address("Lotnicza","147");
        account.setDefaultDeliveryAddress(address);

        //when
        Address defaultAddress = account.getDefaultDeliveryAddress();

        //then
        assertNotNull(address);
      //  assertThat(defaultAddress, is(notNullValue()));
        assertThat(defaultAddress, is(notNullValue()));


    }
@DisplayName("Asumpcje- asercje uruchamiane wtedy kiedy zostanie spełniony warunek")
@RepeatedTest(2)
    void newAccountWithNotNullAddressShouldBeActive1() {
        //given
        Address address = new Address("Litewska" , "14");

        //when
        Account account = new Account(address);


        //then
        assumingThat(address != null, () -> {
           // assertTrue(account.isActive());
            assertThat(account.isActive(), is(true));
        });



    }

    @Test
    public void checkIfEmailAdressIsValid(){

        Account account = new Account();

        assertThrows(IllegalArgumentException.class, () -> account.setEmail("lol@gmail.com"));

    }
}