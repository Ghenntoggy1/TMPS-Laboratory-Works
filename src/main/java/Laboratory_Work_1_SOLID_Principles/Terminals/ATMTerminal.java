package Laboratory_Work_1_SOLID_Principles.Terminals;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITerminal;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_1_SOLID_Principles.Transactions.DepositTransaction;
import Laboratory_Work_1_SOLID_Principles.Transactions.ExchangeTransaction;
import Laboratory_Work_1_SOLID_Principles.Transactions.WithdrawalTransaction;
import Laboratory_Work_1_SOLID_Principles.Enums.TransactionTypeEnum;
import Laboratory_Work_1_SOLID_Principles.Utils.Validators.AccountStatusValidator;
import Laboratory_Work_1_SOLID_Principles.Utils.Validators.TransactionValidator;
import Laboratory_Work_1_SOLID_Principles.Transactions.TransactionFactory;

import java.util.List;

public class ATMTerminal implements ITerminal {
    private ILogger logger;
    private TransactionFactory transactionFactory;

    public ATMTerminal(ILogger logger, TransactionFactory transactionFactory) {
        this.logger = logger;
        this.transactionFactory = transactionFactory;
    }

    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
        try {
            ITransaction transaction = transactionFactory.createTransaction(transactionType, accounts, amount);
            transaction.executeTransaction();
        } catch (IllegalArgumentException e) {
            logger.errorLog("Invalid transaction Type: " + e.getMessage());
        }
    }
}
