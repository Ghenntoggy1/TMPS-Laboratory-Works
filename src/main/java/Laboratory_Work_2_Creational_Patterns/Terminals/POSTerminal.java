package Laboratory_Work_2_Creational_Patterns.Terminals;

import Laboratory_Work_2_Creational_Patterns.Interfaces.*;
import Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Transactions.TransactionFactory;
import Laboratory_Work_2_Creational_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_2_Creational_Patterns.Utils.Builders.WithdrawalTransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Utils.Factories.POSFactory;
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
            IAbstractTerminalTransactionFactory POSFactory = new POSFactory();
            ITransaction transaction = POSFactory.createTransaction(accounts, amount, transactionType);
//            ITransaction transaction = transactionFactory.createTransaction(transactionType, accounts, amount);
            int accountId = accounts.getFirst().getAccountId();
            logger.infoLog("Initiated POS Transaction from Account " + accountId + " on Amount " + amount);

            transaction.executeTransaction();
            logger.infoLog("Closed POS Transaction from Account " + accountId + " on Amount " + amount);
        } catch (IllegalArgumentException e) {
            logger.errorLog("Failed POS Transaction: " + e.getMessage());
        } catch (NullPointerException e) {
            logger.errorLog("Failed POS Transaction: Transaction is Null - " + e.getMessage());
        }
    }

    @Override
    public void performTransaction(ITransaction withdrawalTransaction) {
        if (withdrawalTransaction.getTransactionType() != TransactionTypeEnum.WITHDRAWAL) {
            logger.errorLog("Invalid transaction Type: " + withdrawalTransaction.getTransactionType());
            return;
        }

        try {
            int accountId = withdrawalTransaction.getAccounts().getFirst().getAccountId();
            logger.infoLog("Initiated POS Transaction from Account " + accountId + " on Amount " + withdrawalTransaction.getAmount());
            withdrawalTransaction.executeTransaction();
            logger.infoLog("Closed POS Transaction from Account " + accountId + " on Amount " + withdrawalTransaction.getAmount());
        } catch (IllegalArgumentException e) {
            logger.errorLog("Failed POS Transaction: " + e.getMessage());
        }
    }
}
