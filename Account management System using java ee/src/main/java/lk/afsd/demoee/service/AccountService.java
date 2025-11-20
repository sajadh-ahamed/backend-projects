package lk.afsd.demoee.service;

import lk.afsd.demoee.dto.AccountDTO;

public interface AccountService {
    boolean createAccount(AccountDTO accountDTO);
    AccountDTO getAccountByAccountNumber(String accountNumber);
    boolean deposit(String accountNumber, double amount);
    boolean withdraw(String accountNumber, double amount);
    boolean transferMoney(String fromAccountNumber, String toAccountNumber, double amount);
}
