package Laboratory_Work_0_SOLID_Principles.Terminals;

import Laboratory_Work_0_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_0_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_0_SOLID_Principles.Interfaces.ITerminal;
import Laboratory_Work_0_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_0_SOLID_Principles.Enums.TransactionTypeEnum;
import Laboratory_Work_0_SOLID_Principles.Transactions.TransactionFactory;

import java.util.List;

public class CashInTerminal implements ITerminal {
    private ILogger logger;
    private TransactionFactory transactionFactory;

    public CashInTerminal(ILogger logger, TransactionFactory transactionFactory) {
        this.logger = logger;
        this.transactionFactory = transactionFactory;
    }

    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
        if (transactionType != TransactionTypeEnum.DEPOSIT) {
            logger.errorLog("Invalid transaction Type: " + transactionType);
            return;
        }

        try {
            ITransaction transaction = transactionFactory.createTransaction(transactionType, accounts, amount);
            int userAccountId = accounts.getFirst().getAccountId();
            this.logger.infoLog("Initiated Cash-In Transaction to Account" + userAccountId + " on Amount " + amount);
            transaction.executeTransaction();
            this.logger.infoLog("Closed Cash-In Transaction to Account" + userAccountId + " on Amount " + amount);
        } catch (IllegalArgumentException e) {
            logger.errorLog("Invalid transaction Type: " + e.getMessage());
        }
    }
}
