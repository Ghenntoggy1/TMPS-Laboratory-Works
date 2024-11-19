package Laboratory_Work_4_Behavioral_Patterns.Utils.Builders;

import Laboratory_Work_4_Behavioral_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.IAccount;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransaction;
import Laboratory_Work_4_Behavioral_Patterns.Transactions.WithdrawalTransaction;

import java.util.List;

public class WithdrawalTransactionBuilder implements ITransactionBuilder {
    private List<IAccount> accounts;
    private Double amount;

    public WithdrawalTransactionBuilder() {
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
        ITransaction transaction = new WithdrawalTransaction(this.accounts, this.amount);
        this.reset();
        return transaction;
    }
}
