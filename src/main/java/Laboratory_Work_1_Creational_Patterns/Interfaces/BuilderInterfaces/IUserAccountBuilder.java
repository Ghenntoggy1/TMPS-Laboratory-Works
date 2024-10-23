package Laboratory_Work_1_Creational_Patterns.Interfaces.BuilderInterfaces;

import Laboratory_Work_1_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_1_Creational_Patterns.Interfaces.IUser;
import Laboratory_Work_1_Creational_Patterns.Enums.AccountStatusEnum;

public interface IUserAccountBuilder extends IBuilder{
    void setAccountId(int accountId);
    void setUser(IUser user);
    void setBalance(Double balance);
    void setStatus(AccountStatusEnum status);
    IAccount getResult();
}
