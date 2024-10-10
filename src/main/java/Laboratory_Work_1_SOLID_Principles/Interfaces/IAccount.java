package Laboratory_Work_1_SOLID_Principles.Interfaces;

public interface IAccount {
    int getAccountId();
    void deposit(float amount);
    float withdraw(float amount);
    float getBalance();
    String getAccountStatus();
    void setAccountStatus(String status);
}
