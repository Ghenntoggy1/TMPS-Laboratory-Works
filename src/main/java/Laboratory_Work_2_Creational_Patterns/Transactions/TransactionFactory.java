package Laboratory_Work_2_Creational_Patterns.Transactions;

import Laboratory_Work_2_Creational_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransaction;
import Laboratory_Work_2_Creational_Patterns.Utils.Validators.AccountStatusValidator;
import Laboratory_Work_2_Creational_Patterns.Utils.Validators.TransactionValidator;

import java.util.List;

public class TransactionFactory {
    public ITransaction createTransaction(TransactionTypeEnum transactionType,
                                          List<IAccount> accounts,
                                          double amount) {
        return switch (transactionType) {
            case DEPOSIT ->
                    new DepositTransaction(accounts.getFirst(), amount, new AccountStatusValidator());
            case EXCHANGE ->
                    new ExchangeTransaction(accounts.getFirst(), accounts.getLast(), amount, new TransactionValidator());
            case WITHDRAWAL ->
                    new WithdrawalTransaction(accounts.getFirst(), amount, new TransactionValidator());
        };
    }
}

