package Laboratory_Work_2_Creational_Patterns.Terminals;

import Laboratory_Work_2_Creational_Patterns.Interfaces.*;
import Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_2_Creational_Patterns.Transactions.TransactionFactory;
import Laboratory_Work_2_Creational_Patterns.Utils.Factories.ATMFactory;
import Laboratory_Work_2_Creational_Patterns.Utils.Logging.LoggerImpl;

import java.util.List;

public class ATMTerminal implements ITerminal {
    private ILogger logger;
    private TransactionFactory transactionFactory;

    public ATMTerminal() {
        this.transactionFactory = TransactionFactory.getInstance();
        this.logger = LoggerImpl.getInstance();
    }

    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
        try {
            IAbstractTerminalTransactionFactory ATMFactory = new ATMFactory();
            ITransaction transaction = ATMFactory.createTransaction(accounts, amount, transactionType);
            int accountId = accounts.getFirst().getAccountId();
            logger.infoLog("Initiated ATM Transaction from Account " + accountId + " on Amount " + amount);
//            ITransaction transaction = transactionFactory.createTransaction(transactionType, accounts, amount);
            transaction.executeTransaction();
            logger.infoLog("Closed ATM Transaction from Account " + accountId + " on Amount " + amount);
        } catch (IllegalArgumentException e) {
            logger.errorLog("Invalid transaction Type: " + e.getMessage());
        }
    }

    @Override
    public void performTransaction(ITransaction transaction) {
        try {
            int accountId = transaction.getAccounts().getFirst().getAccountId();
            logger.infoLog("Initiated ATM Transaction from Account " + accountId + " on Amount " + transaction.getAmount());
            transaction.executeTransaction();
//            logger.infoLog("Closed ATM Transaction from Account " + accountId + " on Amount " + transaction.getAmount());
        } catch (IllegalArgumentException e) {
            logger.errorLog("Failed ATM Transaction: " + e.getMessage());
        }
        catch (NullPointerException e) {
            logger.errorLog("Failed ATM Transaction: Transaction is Null - " + e.getMessage());
        }
    }
}
