package Laboratory_Work_2_Creational_Patterns.Utils.Builders;

import Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccountStatusValidator;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransactionValidator;
import Laboratory_Work_2_Creational_Patterns.Transactions.ExchangeTransaction;

import java.util.List;

public class ExchangeTransactionBuilder implements ITransactionBuilder {
    private List<IAccount> accounts;
    private double amount;

    @Override
    public void setAccounts(List<IAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public ExchangeTransaction getResult() {
        return new ExchangeTransaction(this.accounts, this.amount);
    }
}
