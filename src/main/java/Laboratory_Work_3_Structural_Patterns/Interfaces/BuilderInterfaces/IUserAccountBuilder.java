package Laboratory_Work_3_Structural_Patterns.Interfaces.BuilderInterfaces;


import Laboratory_Work_3_Structural_Patterns.Enums.AccountStatusEnum;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccount;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IUser;

public interface IUserAccountBuilder extends IBuilder {
    void setAccountId(int accountId);
    void setUser(IUser user);
    void setBalance(Double balance);
    void setStatus(AccountStatusEnum status);
    IAccount getResult();
}
