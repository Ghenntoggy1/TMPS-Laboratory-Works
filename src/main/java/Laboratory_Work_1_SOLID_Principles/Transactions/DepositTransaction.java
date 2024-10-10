package Laboratory_Work_1_SOLID_Principles.Transactions;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_1_SOLID_Principles.Utils.Validator;

// Single Responsibility Principle - DepositTransaction class is responsible for managing deposit transactions
// Dependency Inversion Principle - DepositTransaction class depends on ITransaction interface
// Open/Closed Principle - DepositTransaction class is open for extension and closed for modification
// Liskov Substitution Principle - DepositTransaction class can be substituted for ITransaction
public class DepositTransaction implements ITransaction {
    private IAccount account;
    private double amount;
    private ILogger logger;
    private Validator validator;

    public DepositTransaction(IAccount account, ILogger logger, double amount) {
        this.account = account;
        this.logger = logger;
        this.amount = amount;
        this.validator = new Validator(this.logger);
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
