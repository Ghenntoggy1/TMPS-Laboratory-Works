package Laboratory_Work_2_Creational_Patterns.Terminals;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITerminal;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_1_SOLID_Principles.Enums.TransactionTypeEnum;
import Laboratory_Work_1_SOLID_Principles.Transactions.TransactionFactory;
import Laboratory_Work_1_SOLID_Principles.Utils.Logging.LoggerImpl;

import java.util.List;

public class ATMTerminal implements ITerminal {
    private ILogger logger = LoggerImpl.getInstance();
    private TransactionFactory transactionFactory;

    public ATMTerminal(TransactionFactory transactionFactory) {
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
