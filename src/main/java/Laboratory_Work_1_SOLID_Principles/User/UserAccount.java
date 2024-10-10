package Laboratory_Work_1_SOLID_Principles.User;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.IUser;
import Laboratory_Work_1_SOLID_Principles.Utils.AccountStatusEnum;


public class UserAccount implements IAccount {
    private int accountId;
    private User user;
    private double balance;
    private ILogger logger;
    private AccountStatusEnum status;

    // Constructor - following Dependency Inversion Principle
    public UserAccount(ILogger logger, int accountId, User user) {
        this.accountId = accountId;
        this.user = user;
        this.balance = 0;
        this.logger = logger;
        this.status = AccountStatusEnum.ACTIVE;
        this.logger.infoLog("Account " + this.accountId + " with status " + this.status + " created for user "
                + this.user.getName() + " (" + this.user.getUserId() + ")");
    }

    // Method to get account ID
    @Override
    public int getAccountId() {
        this.logger.infoLog("Requested account ID for account " + this.accountId);
        return this.accountId;
    }

    // Method to deposit amount to the account
    @Override
    public void deposit(double amount) {
        this.balance += amount;
        this.logger.infoLog("Deposited " + amount + " to account " + this.accountId);
    }

    // Method to withdraw amount from the account
    @Override
    public double withdraw(double amount) {
        this.balance -= amount;
        this.logger.infoLog("Withdrew " + amount + " from account " + this.accountId);
        return amount;
    }

    // Method to get the account balance
    @Override
    public double getBalance() {
        this.logger.infoLog("Checking balance for account " + this.accountId);
        return this.balance;
    }

    // Method to get account status
    @Override
    public AccountStatusEnum getAccountStatus() {
        this.logger.infoLog("Checking account status for account " + this.accountId);
        return this.status;
    }

    // Method to set account status
    @Override
    public void setAccountStatus(AccountStatusEnum status) {
        this.status = status;
        this.logger.infoLog("Account " + this.accountId + " status changed to " + status);
    }
}
