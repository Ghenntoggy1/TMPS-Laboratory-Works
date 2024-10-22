package Laboratory_Work_2_Creational_Patterns.Transactions;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransactionValidator;

public class ExchangeTransaction implements ITransaction {
    private IAccount senderAccount;
    private IAccount receiverAccount;
    private double amount;
    private ILogger logger;
    private ITransactionValidator validator;

    public ExchangeTransaction(IAccount senderAccount, IAccount receiverAccount, ILogger logger, double amount, ITransactionValidator validator) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.logger = logger;
        this.amount = amount;
        this.validator = validator;
    }

    @Override
    public void executeTransaction() {
        int senderAccountId = this.senderAccount.getAccountId();
        int receiverAccountId = this.receiverAccount.getAccountId();
        this.logger.infoLog("Initiated Exchange Transaction from Account " + senderAccountId +
                " to Account " + receiverAccountId);
        if (this.validator.validateTransaction(this.senderAccount, this.amount) && this.validator.validateAccountStatus(this.receiverAccount)) {
            this.senderAccount.withdraw(this.amount);
            this.receiverAccount.deposit(this.amount);
            this.logger.infoLog("Successfully Exchanged " + this.amount + " from Account " + senderAccountId +
                    " to Account " + receiverAccountId);
        }
        else {
            this.logger.errorLog("Exchange Transaction failed from Account " + senderAccountId +
                    " to Account " + receiverAccountId);
        }
    }
}
