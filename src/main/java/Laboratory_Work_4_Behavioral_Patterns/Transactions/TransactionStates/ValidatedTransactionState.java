package Laboratory_Work_4_Behavioral_Patterns.Transactions.TransactionStates;

import Laboratory_Work_4_Behavioral_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.IAccount;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ILogger;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransaction;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransactionState;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Logging.LoggerProxy;

import java.util.List;

public class ValidatedTransactionState implements ITransactionState {
    private ITransaction transactionContext;
    private ILogger logger = LoggerProxy.getInstance();

    @Override
    public void setTransactionContext(ITransaction transactionContext) {
        this.transactionContext = transactionContext;
    }

    @Override
    public void executeTransaction() {
        this.logger.infoLog("Transaction Status changed to Validated");
        List<IAccount> accounts = this.transactionContext.getAccounts();
        switch (transactionContext.getTransactionType()) {
            case TransactionTypeEnum.DEPOSIT -> {
                IAccount account = this.transactionContext.getAccounts().getFirst();
                int userAccountId = account.getAccountId();
                double amount = this.transactionContext.getAmount();
                account.deposit(amount);
                this.logger.infoLog("Deposited " + amount + " to Account " + userAccountId);
                this.transactionContext.changeTransactionState(new CompletedTransactionState());
                this.transactionContext.executeTransaction();
            }
            case TransactionTypeEnum.WITHDRAWAL -> {
                IAccount account = this.transactionContext.getAccounts().getFirst();
                int userAccountId = account.getAccountId();
                double amount = this.transactionContext.getAmount();
                account.withdraw(amount);
                this.logger.infoLog("Withdrew " + amount + " from Account " + userAccountId);
                this.transactionContext.changeTransactionState(new CompletedTransactionState());
                this.transactionContext.executeTransaction();
            }
            case TransactionTypeEnum.EXCHANGE -> {
                IAccount senderAccount = this.transactionContext.getAccounts().getFirst();
                IAccount receiverAccount = this.transactionContext.getAccounts().getLast();
                int senderAccountId = senderAccount.getAccountId();
                int receiverAccountId = receiverAccount.getAccountId();
                double amount = this.transactionContext.getAmount();
                senderAccount.withdraw(amount);
                receiverAccount.deposit(amount);
                this.logger.infoLog("Successfully Exchanged " + amount + " from Account " + senderAccountId + " to Account " + receiverAccountId);
                this.transactionContext.changeTransactionState(new CompletedTransactionState());
                this.transactionContext.executeTransaction();
            }
            default -> {
                logger.errorLog("Invalid Transaction Type");
                this.transactionContext.changeTransactionState(new FailedTransactionState());
                this.transactionContext.executeTransaction();
            }
        }
    }
}
