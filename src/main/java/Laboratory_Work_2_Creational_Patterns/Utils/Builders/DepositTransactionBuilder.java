package Laboratory_Work_2_Creational_Patterns.Utils.Builders;

import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccountStatusValidator;
import Laboratory_Work_2_Creational_Patterns.Transactions.DepositTransaction;
import Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransactionValidator;

import java.util.List;

public class DepositTransactionBuilder implements ITransactionBuilder {
    private List<IAccount> accounts;
    private double amount;
    private IAccountStatusValidator validator;

    @Override
    public void setAccounts(List<IAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public DepositTransaction getResult() {
        return new DepositTransaction(this.accounts, this.amount);
    }
}
