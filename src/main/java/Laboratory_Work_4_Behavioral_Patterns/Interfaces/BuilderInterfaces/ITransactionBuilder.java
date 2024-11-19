package Laboratory_Work_4_Behavioral_Patterns.Interfaces.BuilderInterfaces;

import Laboratory_Work_4_Behavioral_Patterns.Interfaces.IAccount;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransaction;

import java.util.List;

public interface ITransactionBuilder extends IBuilder {
    void setAccounts(List<IAccount> accounts);
    void setAmount(Double amount);
    ITransaction getResult();
}
