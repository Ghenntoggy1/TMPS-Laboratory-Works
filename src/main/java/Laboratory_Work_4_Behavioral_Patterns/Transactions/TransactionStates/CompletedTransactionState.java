package Laboratory_Work_4_Behavioral_Patterns.Transactions.TransactionStates;

import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ILogger;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransaction;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransactionState;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Logging.LoggerProxy;

public class CompletedTransactionState implements ITransactionState {
    private ITransaction transactionContext;
    private ILogger logger = LoggerProxy.getInstance();

    @Override
    public void setTransactionContext(ITransaction transactionContext) {
        this.transactionContext = transactionContext;
    }

    @Override
    public void executeTransaction() {
        this.logger.infoLog("Transaction Status changed to Completed");
        switch (transactionContext.getTransactionType()) {
            case DEPOSIT -> {
                double newBalance = this.transactionContext.getAccounts().getFirst().getBalance();
                this.logger.infoLog("Deposit Transaction completed successfully. New Balance: " + newBalance + " for Account " + this.transactionContext.getAccounts().getFirst().getAccountId());
                this.logger.infoLog("Deposit Transaction Status changed to Completed");
            }
            case WITHDRAWAL -> {
                double newBalance = this.transactionContext.getAccounts().getFirst().getBalance();
                this.logger.infoLog("Withdrawal Transaction completed successfully. New Balance: " + newBalance + " for Account " + this.transactionContext.getAccounts().getFirst().getAccountId());
                this.logger.infoLog("Withdrawal Transaction Status changed to Completed");
            }
            case EXCHANGE -> {
                double newBalanceSender = this.transactionContext.getAccounts().getFirst().getBalance();
                double newBalanceReceiver = this.transactionContext.getAccounts().getLast().getBalance();
                this.logger.infoLog("Exchange Transaction completed successfully. New Balance for Sender: " + newBalanceSender + " and Receiver: " + newBalanceReceiver);
                this.logger.infoLog("Exchange Transaction Status changed to Completed");}
            default -> {
                logger.errorLog("Invalid Transaction Type");
                this.transactionContext.changeTransactionState(new FailedTransactionState());
                this.transactionContext.executeTransaction();
            }
        }
    }
}
