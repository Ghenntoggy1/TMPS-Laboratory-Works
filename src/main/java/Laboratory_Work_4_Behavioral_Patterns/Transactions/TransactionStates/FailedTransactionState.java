package Laboratory_Work_4_Behavioral_Patterns.Transactions.TransactionStates;

import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ILogger;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransaction;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransactionState;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Logging.LoggerProxy;

public class FailedTransactionState implements ITransactionState {
    private ITransaction transactionContext;
    private ILogger logger = LoggerProxy.getInstance();

    @Override
    public void setTransactionContext(ITransaction transactionContext) {
        this.transactionContext = transactionContext;
    }

    @Override
    public void executeTransaction() {
        this.logger.infoLog("Transaction Status changed to Failed");
        switch (transactionContext.getTransactionType()) {
            case DEPOSIT -> {
                int userAccountId = transactionContext.getAccounts().getFirst().getAccountId();
                logger.errorLog("Failed Deposit Transaction for Account " + userAccountId);
            }
            case WITHDRAWAL -> {
                int userAccountId = transactionContext.getAccounts().getFirst().getAccountId();
                logger.errorLog("Failed Withdrawal Transaction for Account " + userAccountId);
            }
            case EXCHANGE -> {
                int senderAccountId = transactionContext.getAccounts().getFirst().getAccountId();
                int receiverAccountId = transactionContext.getAccounts().getLast().getAccountId();
                logger.errorLog("Failed Exchange Transaction from Account " + senderAccountId + " to Account " + receiverAccountId);
            }
            default -> logger.errorLog("Invalid Transaction Type");
        }
    }
}
