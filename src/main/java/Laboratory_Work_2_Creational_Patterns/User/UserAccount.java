package Laboratory_Work_2_Creational_Patterns.User;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Enums.AccountStatusEnum;
import Laboratory_Work_1_SOLID_Principles.User.User;
import Laboratory_Work_1_SOLID_Principles.Utils.Logging.LoggerImpl;


public class UserAccount implements IAccount {
    private int accountId;
    private Laboratory_Work_1_SOLID_Principles.User.User user;
    private double balance;
    private ILogger logger = LoggerImpl.getInstance();
    private AccountStatusEnum status;

    public UserAccount(int accountId, User user) {
        this.accountId = accountId;
        this.user = user;
        this.balance = 0;
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
