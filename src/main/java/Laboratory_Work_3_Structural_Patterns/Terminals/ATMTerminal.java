package Laboratory_Work_3_Structural_Patterns.Terminals;

import Laboratory_Work_3_Structural_Patterns.Interfaces.ITerminal;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccount;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAbstractTerminalTransactionFactory;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ITransaction;

import Laboratory_Work_3_Structural_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_3_Structural_Patterns.Utils.Factories.ATMFactory;
import Laboratory_Work_3_Structural_Patterns.Utils.Logging.LoggerProxy;

import java.util.List;

public class ATMTerminal implements ITerminal {
    private ILogger logger;

    public ATMTerminal() {
        this.logger = LoggerProxy.getInstance();
    }

    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
        try {
            IAbstractTerminalTransactionFactory ATMFactory = new ATMFactory();
            ITransaction transaction = ATMFactory.createTransaction(accounts, amount, transactionType);
            int accountId = accounts.getFirst().getAccountId();
            logger.infoLog("Initiated ATM Transaction from Account " + accountId + " on Amount " + amount);
            transaction.executeTransaction();
//            logger.infoLog("Closed ATM Transaction from Account " + accountId + " on Amount " + amount);
        } catch (IllegalArgumentException e) {
            logger.errorLog("Invalid transaction Type: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.errorLog("Failed ATM Transaction: Transaction is Null - " + e.getMessage());
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
        } catch (NullPointerException e) {
            logger.errorLog("Failed ATM Transaction: Transaction is Null - " + e.getMessage());
        }
    }
}
