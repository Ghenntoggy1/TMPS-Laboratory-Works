package Laboratory_Work_4_Behavioral_Patterns.Terminals;

import Laboratory_Work_4_Behavioral_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.*;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Factories.CashInFactory;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Logging.LoggerProxy;

import java.util.List;

public class CashInTerminal implements ITerminal {
    private ILogger logger;

    public CashInTerminal() {
        this.logger = LoggerProxy.getInstance();
    }

    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
        if (transactionType != TransactionTypeEnum.DEPOSIT) {
            this.logger.errorLog("Invalid transaction Type: " + transactionType);
            return;
        }

        try {
            IAbstractTerminalTransactionFactory cashInFactory = new CashInFactory();
            ITransaction depositTransaction = cashInFactory.createTransaction(accounts, amount, transactionType);
            int userAccountId = accounts.getFirst().getAccountId();
            this.logger.infoLog("Initiated Cash-In Transaction to Account" + userAccountId + " on Amount " + amount);
            depositTransaction.executeTransaction();
//            this.logger.infoLog("Closed Cash-In Transaction to Account" + userAccountId + " on Amount " + depositTransaction.getAmount());
        } catch (IllegalArgumentException e) {
            logger.errorLog("Invalid transaction Type: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.errorLog("Failed Cash-In Transaction: Transaction is Null - " + e.getMessage());
        }
    }

    @Override
    public void performTransaction(ITransaction depositTransaction) {
        if (depositTransaction.getTransactionType() != TransactionTypeEnum.DEPOSIT) {
            this.logger.errorLog("Invalid transaction Type: " + depositTransaction.getTransactionType());
            return;
        }
        try {
            int userAccountId = depositTransaction.getAccounts().getFirst().getAccountId();
            this.logger.infoLog("Initiated Cash-In Transaction to Account" + userAccountId + " on Amount " + depositTransaction.getAmount());
            depositTransaction.executeTransaction();
//            this.logger.infoLog("Closed Cash-In Transaction to Account" + userAccountId + " on Amount " + depositTransaction.getAmount());
        } catch (IllegalArgumentException e) {
            logger.errorLog("Invalid transaction Type: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.errorLog("Failed Cash-In Transaction: Transaction is Null - " + e.getMessage());
        }
    }
}
