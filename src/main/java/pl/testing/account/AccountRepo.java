package pl.testing.account;

import java.util.Arrays;
import java.util.List;

public class AccountRepo {



    public List<Account> getACC(){

        Address address1 = new Address("Kwiatowa", "1");
        Account account1 = new Account(address1);
        Account account2 = new Account();

        Address address2 = new Address("Lotnicza", "147");
        Account account3 = new Account(address2);

        return Arrays.asList(account1,account2, account3);


    }
}
