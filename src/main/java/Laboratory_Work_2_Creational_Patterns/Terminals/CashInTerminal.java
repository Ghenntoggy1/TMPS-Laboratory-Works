package Laboratory_Work_2_Creational_Patterns.Terminals;

import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ILogger;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITerminal;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransaction;
import Laboratory_Work_2_Creational_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_2_Creational_Patterns.Transactions.TransactionFactory;
import Laboratory_Work_2_Creational_Patterns.Utils.Logging.LoggerImpl;

import java.util.List;

public class CashInTerminal implements ITerminal {
    private ILogger logger;
    private TransactionFactory transactionFactory;

    public CashInTerminal() {
        this.transactionFactory = TransactionFactory.getInstance();
        this.logger = LoggerImpl.getInstance();
    }

    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
        if (transactionType != TransactionTypeEnum.DEPOSIT) {
            this.logger.errorLog("Invalid transaction Type: " + transactionType);
            return;
        }

        try {
            ITransaction transaction = this.transactionFactory.createTransaction(transactionType, accounts, amount);
            int userAccountId = accounts.getFirst().getAccountId();
            this.logger.infoLog("Initiated Cash-In Transaction to Account" + userAccountId + " on Amount " + amount);
            transaction.executeTransaction();
            this.logger.infoLog("Closed Cash-In Transaction to Account" + userAccountId + " on Amount " + amount);
        } catch (IllegalArgumentException e) {
            logger.errorLog("Invalid transaction Type: " + e.getMessage());
        }
    }
}
