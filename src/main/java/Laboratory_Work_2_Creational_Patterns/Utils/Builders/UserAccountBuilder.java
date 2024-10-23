package Laboratory_Work_2_Creational_Patterns.Utils.Builders;

import Laboratory_Work_2_Creational_Patterns.Enums.AccountStatusEnum;
import Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces.IUserAccountBuilder;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IUser;
import Laboratory_Work_2_Creational_Patterns.User.UserAccount;

public class UserAccountBuilder implements IUserAccountBuilder {
    private int accountId;
    private IUser user;
    private Double balance;
    private AccountStatusEnum status;

    public UserAccountBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.accountId = 0;
        this.user = null;
        this.balance = null;
        this.status = null;
    }

    @Override
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public void setUser(IUser user) {
        this.user = user;
    }

    @Override
    public void setBalance(Double balance) {
        this.balance = balance;
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
