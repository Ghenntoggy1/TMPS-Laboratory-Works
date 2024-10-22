package Laboratory_Work_2_Creational_Patterns.Transactions;

import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccountStatusValidator;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ILogger;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransaction;
import Laboratory_Work_2_Creational_Patterns.Utils.Logging.LoggerImpl;
import Laboratory_Work_2_Creational_Patterns.Utils.Validators.AccountStatusValidator;

import java.util.List;

public class DepositTransaction implements ITransaction {
//    private IAccount account;
    private List<IAccount> account;
    private double amount;
    private ILogger logger;
    private IAccountStatusValidator validator;

//    public DepositTransaction(IAccount account, double amount, IAccountStatusValidator validator) {
//        this.account = account;
//        this.amount = amount;
//        this.validator = validator;
//        this.logger = LoggerImpl.getInstance();
//    }


    public DepositTransaction(List<IAccount> account, double amount) {
        this.account = account;
        this.amount = amount;
        this.validator = AccountStatusValidator.getInstance();
        this.logger = LoggerImpl.getInstance();
    }

    @Override
    public void executeTransaction() {
        IAccount account = this.account.getFirst();
        int userAccountId = account.getAccountId();
        this.logger.infoLog("Initiated Deposit Transaction for Account " + userAccountId);
        if (!this.validator.validateAccountStatus(account)) {
            this.logger.errorLog("Deposit Transaction Failed for Account " + userAccountId + " - Account is not active");
            return;
        }

        account.deposit(this.amount);
        double newBalance = account.getBalance();
        this.logger.infoLog("Deposited " + this.amount + " to Account " + userAccountId + " - New Balance: " + newBalance);
    }
}
