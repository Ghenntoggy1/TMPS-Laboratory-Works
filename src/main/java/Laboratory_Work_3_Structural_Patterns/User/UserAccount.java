package Laboratory_Work_3_Structural_Patterns.User;

import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccount;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import Laboratory_Work_3_Structural_Patterns.Enums.AccountStatusEnum;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IUser;
import Laboratory_Work_3_Structural_Patterns.Utils.Logging.LoggerImpl;


public class UserAccount implements IAccount {
    private int accountId;
    private IUser user;
    private Double balance;
    private ILogger logger;
    private AccountStatusEnum status;

    public UserAccount(int accountId, IUser user, Double balance, AccountStatusEnum status) {
        this.accountId = accountId;
        this.user = user;
        this.balance = balance;
        if (balance == null) {
            this.balance = 0.0;
        }
        this.status = status;
        if (this.status == null) {
            this.status = AccountStatusEnum.INACTIVE;
        }
        this.logger = LoggerImpl.getInstance();
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
