package Laboratory_Work_2_Creational_Patterns.Terminals;

import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ILogger;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITerminal;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransaction;
import Laboratory_Work_2_Creational_Patterns.Transactions.TransactionFactory;
import Laboratory_Work_2_Creational_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_2_Creational_Patterns.Utils.Logging.LoggerImpl;

import java.util.List;

public class POSTerminal implements ITerminal {
    private ILogger logger;
    private TransactionFactory transactionFactory;

    public POSTerminal() {
        this.transactionFactory = TransactionFactory.getInstance();
        this.logger = LoggerImpl.getInstance();
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
