package Laboratory_Work_3_Structural_Patterns.Utils.Builders;

import Laboratory_Work_3_Structural_Patterns.Enums.AccountStatusEnum;
import Laboratory_Work_3_Structural_Patterns.Interfaces.BuilderInterfaces.IUserAccountBuilder;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccount;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IUser;
import Laboratory_Work_3_Structural_Patterns.User.UserAccount;
import Laboratory_Work_3_Structural_Patterns.Utils.Logging.LoggerProxy;

public class UserAccountBuilder implements IUserAccountBuilder {
    private ILogger logger;
    private static int idCounter = 1;
    private int accountId;
    private IUser user;
    private Double balance;
    private AccountStatusEnum status;

    public UserAccountBuilder() {
        this.logger = LoggerProxy.getInstance();
        this.reset();
    }

    @Override
    public void reset() {
        this.accountId = idCounter;
        idCounter++;
        this.user = null;
        this.balance = null;
        this.status = null;
    }

    @Override
    public void setUser(IUser user) {
        this.user = user;
    }

    @Override
    public void setBalance(Double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            logger.errorLog("Attempt to set negative balance for account " + this.accountId + " - balance set to 0.0");
            this.balance = 0.0;
        }
    }

    @Override
    public void setStatus(AccountStatusEnum status) {
        this.status = status;
    }

    @Override
    public IAccount getResult() {
        IAccount userAccount = new UserAccount(this.accountId, this.user, this.balance, this.status);
        this.reset();
        return userAccount;
    }
}
