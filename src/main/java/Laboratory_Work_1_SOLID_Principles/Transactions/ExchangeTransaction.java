package Laboratory_Work_1_SOLID_Principles.Transactions;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_1_SOLID_Principles.Utils.Validator;

public class ExchangeTransaction implements ITransaction {
    private IAccount fromAccount;
    private IAccount toAccount;
    private double amount;
    private ILogger logger;
    private Validator validator;

    public ExchangeTransaction(IAccount fromAccount, ILogger logger, double amount) {
        this.fromAccount = fromAccount;
        this.logger = logger;
        this.amount = amount;
        this.validator = new Validator(this.logger);
    }

    @Override
    public void executeTransaction() {
        this.logger.infoLog("Initiated Exchange Transaction from Account " + this.fromAccount.getAccountId() +
                " to Account " + this.toAccount.getAccountId());
        if (!this.validator.validateTransaction(this.fromAccount, this.amount) && !this.validator.validateAccountStatus(this.toAccount)) {
            this.fromAccount.withdraw(this.amount);
            this.toAccount.deposit(this.amount);
            this.logger.infoLog("Exchanged " + this.amount + " from Account " + this.fromAccount.getAccountId() +
                    " to Account " + this.toAccount.getAccountId());
        }
        else {
            this.logger.errorLog("Exchange Transaction failed from Account " + this.fromAccount.getAccountId() +
                    " to Account " + this.toAccount.getAccountId());
        }
    }
}
