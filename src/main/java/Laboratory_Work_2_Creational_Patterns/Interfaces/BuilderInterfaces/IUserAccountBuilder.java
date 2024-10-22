package Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces;

import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IUser;
import Laboratory_Work_2_Creational_Patterns.Enums.AccountStatusEnum;

public interface IUserAccountBuilder{
    void setAccountId(int accountId);
    void setUser(IUser user);
    void setBalance(Double balance);
    void setStatus(AccountStatusEnum status);
    IAccount getResult();
}
