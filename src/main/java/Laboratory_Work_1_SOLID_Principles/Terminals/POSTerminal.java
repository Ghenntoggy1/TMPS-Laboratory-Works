package Laboratory_Work_1_SOLID_Principles.Terminals;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITerminal;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_1_SOLID_Principles.Transactions.TransactionFactory;
import Laboratory_Work_1_SOLID_Principles.Enums.TransactionTypeEnum;

import java.util.List;

public class POSTerminal implements ITerminal {
    private ILogger logger;
    private TransactionFactory transactionFactory;

    public POSTerminal(ILogger logger, TransactionFactory transactionFactory) {
        this.logger = logger;
        this.transactionFactory = transactionFactory;
    }

    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
        if (transactionType != TransactionTypeEnum.WITHDRAWAL) {
            logger.errorLog("Invalid transaction Type: " + transactionType);
            return;
        }

        try {
            ITransaction transaction = transactionFactory.createTransaction(transactionType, accounts, amount);
            int accountId = accounts.getFirst().getAccountId();
            logger.infoLog("Initiated POS Transaction from Account " + accountId + " on Amount " + amount);
            transaction.executeTransaction();
            logger.infoLog("Closed POS Transaction from Account " + accountId + " on Amount " + amount);
        } catch (IllegalArgumentException e) {
            logger.errorLog("Failed POS Transaction: " + e.getMessage());
        }
    }
}
