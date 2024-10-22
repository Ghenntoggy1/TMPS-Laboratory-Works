package Laboratory_Work_2_Creational_Patterns.Transactions;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccountStatusValidator;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;

public class DepositTransaction implements ITransaction {
    private IAccount account;
    private double amount;
    private ILogger logger;
    private IAccountStatusValidator validator;

    public DepositTransaction(IAccount account, ILogger logger, double amount, IAccountStatusValidator validator) {
        this.account = account;
        this.logger = logger;
        this.amount = amount;
        this.validator = validator;
    }

    @Override
    public void executeTransaction() {
        int userAccountId = this.account.getAccountId();
        this.logger.infoLog("Initiated Deposit Transaction for Account " + userAccountId);
        if (!this.validator.validateAccountStatus(this.account)) {
            this.logger.errorLog("Deposit Transaction Failed for Account " + userAccountId + " - Account is not active");
            return;
        }

        this.account.deposit(this.amount);
        double newBalance = this.account.getBalance();
        this.logger.infoLog("Deposited " + this.amount + " to Account " + userAccountId + " - New Balance: " + newBalance);
    }
}
