package Laboratory_Work_1_SOLID_Principles.Transactions;

import Laboratory_Work_1_SOLID_Principles.Enums.TransactionTypeEnum;
import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_1_SOLID_Principles.Utils.Validators.AccountStatusValidator;
import Laboratory_Work_1_SOLID_Principles.Utils.Validators.TransactionValidator;

import java.util.List;

public class TransactionFactory {
    private ILogger logger;

    public TransactionFactory(ILogger logger) {
        this.logger = logger;
    }

    public ITransaction createTransaction(TransactionTypeEnum transactionType, List<
            IAccount> accounts, double amount) {
        return switch (transactionType) {
            case DEPOSIT ->
                    new DepositTransaction(accounts.getFirst(), logger, amount, new AccountStatusValidator(logger));
            case EXCHANGE ->
                    new ExchangeTransaction(accounts.getFirst(), accounts.getLast(), logger, amount, new TransactionValidator(logger));
            case WITHDRAWAL ->
                    new WithdrawalTransaction(accounts.getFirst(), logger, amount, new TransactionValidator(logger));
        };
    }
}

