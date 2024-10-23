package Laboratory_Work_0_SOLID_Principles.User;

import Laboratory_Work_0_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_0_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_0_SOLID_Principles.Enums.AccountStatusEnum;


public class UserAccount implements IAccount {
    private int accountId;
    private User user;
    private double balance;
    private ILogger logger;
    private AccountStatusEnum status;

    public UserAccount(int accountId, User user, ILogger logger) {
        this.accountId = accountId;
        this.user = user;
        this.balance = 0;
        this.logger = logger;
        this.status = AccountStatusEnum.ACTIVE;
        this.logger.infoLog("Account " + this.accountId + " with status " + this.status + " created for user "
                + this.user.getName() + " (" + this.user.getUserId() + ")");
    }

    @Override
    public int getAccountId() {
        this.logger.infoLog("Requested account ID for account " + this.accountId);
        return this.accountId;
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
    }

    @Override
    public double withdraw(double amount) {
        this.balance -= amount;
        return amount;
    }

    @Override
    public double getBalance() {
        this.logger.infoLog("Checking balance for account " + this.accountId);
        return this.balance;
    }

    @Override
    public AccountStatusEnum getAccountStatus() {
        this.logger.infoLog("Checking account status for account " + this.accountId);
        return this.status;
    }

    @Override
    public void setAccountStatus(AccountStatusEnum status) {
        this.status = status;
        this.logger.infoLog("Account " + this.accountId + " status changed to " + status);
    }
}
