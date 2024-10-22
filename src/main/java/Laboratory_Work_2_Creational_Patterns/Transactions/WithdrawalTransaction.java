package Laboratory_Work_2_Creational_Patterns.Transactions;

import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ILogger;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransaction;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransactionValidator;
import Laboratory_Work_2_Creational_Patterns.Utils.Logging.LoggerImpl;

public class WithdrawalTransaction implements ITransaction {
    private IAccount account;
    private double amount;
    private ILogger logger;
    private ITransactionValidator validator;

    public WithdrawalTransaction(IAccount account, double amount, ITransactionValidator validator) {
        this.account = account;
        this.amount = amount;
        this.validator = validator;
        this.logger = LoggerImpl.getInstance();
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
