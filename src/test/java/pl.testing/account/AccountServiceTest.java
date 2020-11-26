package pl.testing.account;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.STRICT_STUBS)
public class AccountServiceTest {

    @Test
    void getAllActiveAccounts() {

        //given
        List<Account> accounts = prepareAccountData();
       AccountRepository accountRepository = mock(AccountRepository.class);

        AccountService accountService = new AccountService(accountRepository);

        when(accountRepository.getAllAccounts()).thenReturn(accounts);

        //when
        List<Account> accountList = accountService.getAllActiveAccounts();

        //then
         assertThat(accountList, hasSize(2));

    }

    @Test
    void getNoActiveAccounts() {

        //given
        AccountRepository accountRepository = mock(AccountRepository.class);

       // AccountService accountService = new AccountService(accountRepository);

        AccountService accountService = new AccountService(accountRepository);

        when(accountRepository.getAllAccounts()).thenReturn(Collections.emptyList());
        //when
        List<Account> accountList = accountService.getAllActiveAccounts();
        //then
        assertThat(accountList, hasSize(0));

    }

    @Test
    void getAccountsByName() {

        //given
        AccountRepository accountRepository = mock(AccountRepository.class);

        // AccountService accountService = new AccountService(accountRepository);

        AccountService accountService = new AccountService(accountRepository);
        given(accountRepository.getByName("piotr")).willReturn(Collections.singletonList("Feldman"));

        when(accountRepository.getByName("piotr")).thenReturn(Collections.singletonList("Feldman"));
        //when
        List<String> accountNames = accountService.findByName("piotr");
        //then
        assertThat(accountNames, Matchers.contains("Feldman"));

    }



    private List<Account> prepareAccountData() {

        Address address1 = new Address("Kwiatowa", "1");
        Account account1 = new Account(address1);
        Account account2 = new Account();

        Address address2 = new Address("Lotnicza", "147");
        Account account3 = new Account(address2);

        return Arrays.asList(account1,account2, account3);

    }


}
