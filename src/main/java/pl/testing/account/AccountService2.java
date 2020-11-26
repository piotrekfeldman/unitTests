package pl.testing.account;

import java.util.List;
import java.util.stream.Collectors;

public class AccountService2 {

    public AccountRepo accountRepo;

    public AccountService2(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    List<Account> getAllActiveAccounts()  {


        return accountRepo.getACC().stream().
                filter(Account::isActive)
                .collect(Collectors.toList());

    }
}
