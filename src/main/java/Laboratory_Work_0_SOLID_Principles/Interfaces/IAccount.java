package Laboratory_Work_0_SOLID_Principles.Interfaces;

import Laboratory_Work_0_SOLID_Principles.Enums.AccountStatusEnum;

public interface IAccount {
    int getAccountId();
    void deposit(double amount);
    double withdraw(double amount);
    double getBalance();
    AccountStatusEnum getAccountStatus();
    void setAccountStatus(AccountStatusEnum status);
}
