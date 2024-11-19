package Laboratory_Work_4_Behavioral_Patterns.ClientAbstraction;

import Laboratory_Work_4_Behavioral_Patterns.Enums.AccountStatusEnum;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.BuilderInterfaces.IUserAccountBuilder;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.IAccount;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.IUser;

public class UserAccountAbstraction {
    private IUserAccountBuilder userAccountBuilder;

    public UserAccountAbstraction(IUserAccountBuilder userAccountBuilder) {
        this.userAccountBuilder = userAccountBuilder;
    }

    public IAccount createInactiveUserAccount(IUser user) {
        userAccountBuilder.setUser(user);
        return userAccountBuilder.getResult();
    }

    public IAccount createEmptyActiveUserAccount(IUser user) {
        userAccountBuilder.setUser(user);
        userAccountBuilder.setStatus(AccountStatusEnum.ACTIVE);
        userAccountBuilder.setBalance(0.0);
        return userAccountBuilder.getResult();
    }

    public IAccount createUserAccountWithBalance(IUser user, double balance) {
        userAccountBuilder.setUser(user);
        userAccountBuilder.setStatus(AccountStatusEnum.ACTIVE);
        userAccountBuilder.setBalance(balance);
        return userAccountBuilder.getResult();
    }
}
