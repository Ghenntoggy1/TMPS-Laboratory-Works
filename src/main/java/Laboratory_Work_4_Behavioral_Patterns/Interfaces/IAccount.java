package Laboratory_Work_4_Behavioral_Patterns.Interfaces;

import Laboratory_Work_4_Behavioral_Patterns.Enums.AccountStatusEnum;

public interface IAccount {
    int getAccountId();
    void deposit(double amount);
    double withdraw(double amount);
    double getBalance();
    AccountStatusEnum getAccountStatus();
    void setAccountStatus(AccountStatusEnum status);
}
