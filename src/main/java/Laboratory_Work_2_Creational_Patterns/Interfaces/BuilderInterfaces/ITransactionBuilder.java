package Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces;

import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccountStatusValidator;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransaction;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransactionValidator;

import java.util.List;

public interface ITransactionBuilder extends IBuilder {
    void setAccounts(List<IAccount> accounts);
    void setAmount(double amount);
    ITransaction getResult();
}
