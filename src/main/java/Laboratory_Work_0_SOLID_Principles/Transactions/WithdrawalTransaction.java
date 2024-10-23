package Laboratory_Work_0_SOLID_Principles.Transactions;

import Laboratory_Work_0_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_0_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_0_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_0_SOLID_Principles.Interfaces.ITransactionValidator;

public class WithdrawalTransaction implements ITransaction {
    private IAccount account;
    private double amount;
    private ILogger logger;
    private ITransactionValidator validator;

    public WithdrawalTransaction(IAccount account, ILogger logger, double amount, ITransactionValidator validator) {
        this.account = account;
        this.logger = logger;
        this.amount = amount;
        this.validator = validator;
    }

    @Override
    public void executeTransaction() {
        int userAccountId = this.account.getAccountId();
        this.logger.infoLog("Initiated Withdrawal Transaction for Account " + userAccountId);

        if (!this.validator.validateTransaction(this.account, this.amount)) {
            this.logger.errorLog("Withdrawal Transaction failed for Account " + userAccountId);
            return;
        }

        this.account.withdraw(this.amount);
        double newBalance = this.account.getBalance();
        this.logger.infoLog("Withdrew " + this.amount + " from account " + userAccountId + " - New balance: " + newBalance);
    }
}
