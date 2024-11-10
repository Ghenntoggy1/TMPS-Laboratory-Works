package Laboratory_Work_3_Structural_Patterns.Utils.Builders;

import Laboratory_Work_3_Structural_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccount;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ITransaction;
import Laboratory_Work_3_Structural_Patterns.Transactions.ExchangeTransaction;

import java.util.List;

public class ExchangeTransactionBuilder implements ITransactionBuilder {
    private List<IAccount> accounts;
    private Double amount;

    public ExchangeTransactionBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.accounts = null;
        this.amount = 0.0;
    }

    @Override
    public void setAccounts(List<IAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public ITransaction getResult() {
        ITransaction transaction = new ExchangeTransaction(this.accounts, this.amount);
        this.reset();
        return transaction;
    }
}
