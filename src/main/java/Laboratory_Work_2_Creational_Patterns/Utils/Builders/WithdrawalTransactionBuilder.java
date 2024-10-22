package Laboratory_Work_2_Creational_Patterns.Utils.Builders;

import Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccountStatusValidator;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransactionValidator;
import Laboratory_Work_2_Creational_Patterns.Transactions.WithdrawalTransaction;
import Laboratory_Work_2_Creational_Patterns.Utils.Validators.AccountStatusValidator;
import Laboratory_Work_2_Creational_Patterns.Utils.Validators.TransactionValidator;

import java.util.List;

public class WithdrawalTransactionBuilder implements ITransactionBuilder {
//    private WithdrawalTransaction withdrawalTransaction;

//    public WithdrawalTransactionBuilder() {
//        this.reset();
//    }

//    @Override
//    public void reset() {
//        this.withdrawalTransaction = new WithdrawalTransaction();
//    }

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
    public WithdrawalTransaction getResult() {
        return new WithdrawalTransaction(this.accounts, this.amount);
    }
}
