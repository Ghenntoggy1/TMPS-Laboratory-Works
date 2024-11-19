package Laboratory_Work_4_Behavioral_Patterns.Transactions.TransactionStates;

import Laboratory_Work_4_Behavioral_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.*;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Logging.LoggerProxy;

import java.util.List;

public class InProcessTransactionState implements ITransactionState {
    private ITransaction transactionContext;
    private ILogger logger = LoggerProxy.getInstance();

    @Override
    public void setTransactionContext(ITransaction transactionContext) {
        this.transactionContext = transactionContext;
    }

    @Override
    public void executeTransaction() {
        this.logger.infoLog("Transaction Status changed to In Process");
        List<IAccount> accounts = this.transactionContext.getAccounts();
        switch (this.transactionContext.getTransactionType()) {
            case TransactionTypeEnum.WITHDRAWAL -> {
                int userAccountId = accounts.getFirst().getAccountId();
                this.logger.infoLog("Start Processing Withdrawal Transaction for account " + userAccountId);
//                this.logger.infoLog("Initiated Withdrawal Transaction for Account " + userAccountId);
                if (((ITransactionValidator) this.transactionContext.getValidator()).validateTransaction(accounts.getFirst(), transactionContext.getAmount())) {
                    transactionContext.changeTransactionState(new ValidatedTransactionState());
                    transactionContext.executeTransaction();
                }
                else {
                    transactionContext.changeTransactionState(new FailedTransactionState());
                    transactionContext.executeTransaction();
                }
            }
            case TransactionTypeEnum.DEPOSIT -> {
                int userAccountId = accounts.getFirst().getAccountId();
                this.logger.infoLog("Start Processing Deposit Transaction for account " + userAccountId);
//                this.logger.infoLog("Initiated Deposit Transaction for Account " + userAccountId);
                if (this.transactionContext.getValidator().validateAccountStatus(accounts.getFirst())) {
                    transactionContext.changeTransactionState(new ValidatedTransactionState());
                    transactionContext.executeTransaction();
                }
                else {
                    transactionContext.changeTransactionState(new FailedTransactionState());
                    transactionContext.executeTransaction();
                }
            }
            case TransactionTypeEnum.EXCHANGE -> {
                int senderAccountId = accounts.getFirst().getAccountId();
                int receiverAccountId = accounts.getLast().getAccountId();
                this.logger.infoLog("Start Processing Exchange Transaction from Account " + senderAccountId + " to Account " + receiverAccountId);
//                this.logger.infoLog("Initiated Exchange Transaction from Account " + senderAccountId + " to Account " + receiverAccountId);
                ITransactionValidator validator = (ITransactionValidator) this.transactionContext.getValidator();
                if (validator.validateTransaction(accounts.getFirst(), transactionContext.getAmount()) && validator.validateAccountStatus(accounts.getLast())) {
                    transactionContext.changeTransactionState(new ValidatedTransactionState());
                    transactionContext.executeTransaction();
                }
                else {
                    transactionContext.changeTransactionState(new FailedTransactionState());
                    transactionContext.executeTransaction();
                }
            }
            default -> this.logger.errorLog("Invalid Transaction Type");
        }
    }
}
