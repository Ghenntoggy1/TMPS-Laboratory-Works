package Laboratory_Work_1_Creational_Patterns.Interfaces;

import Laboratory_Work_1_Creational_Patterns.Enums.AccountStatusEnum;

public interface IAccount {
    int getAccountId();
    void deposit(double amount);
    double withdraw(double amount);
    double getBalance();
    AccountStatusEnum getAccountStatus();
    void setAccountStatus(AccountStatusEnum status);
}
