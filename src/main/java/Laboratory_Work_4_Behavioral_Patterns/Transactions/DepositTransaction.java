package Laboratory_Work_4_Behavioral_Patterns.Transactions;

import Laboratory_Work_4_Behavioral_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.IAccount;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.IAccountStatusValidator;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ILogger;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransaction;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Logging.LoggerProxy;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Validators.AccountStatusValidator;

import java.util.List;

public class DepositTransaction implements ITransaction {
    private List<IAccount> account;
    private Double amount;
    private ILogger logger;
    private IAccountStatusValidator validator;

    public DepositTransaction(List<IAccount> account, Double amount) {
        this.account = account;
        this.logger = LoggerProxy.getInstance();
        if (account == null) {
            logger.errorLog("Deposit Transaction failed due to invalid account - Account is null");
            throw new IllegalArgumentException("Account cannot be null");
        }
        this.amount = amount;
        if (amount <= 0) {
            logger.errorLog("Deposit Transaction failed due to invalid amount - Amount is less than or equal to 0");
            throw new IllegalArgumentException("Amount cannot be 0");
        }
        this.validator = AccountStatusValidator.getInstance();
    }

    @Override
    public void executeTransaction() {
        IAccount account = this.account.getFirst();
        int userAccountId = account.getAccountId();
        // TODO: Implement FACADE pattern
        this.logger.infoLog("Initiated Deposit Transaction for Account " + userAccountId);
        if (!this.validator.validateAccountStatus(account)) {
            this.logger.errorLog("Deposit Transaction Failed for Account " + userAccountId + " - Account is not active");
            return;
        }

        account.deposit(this.amount);
        double newBalance = account.getBalance();
        this.logger.infoLog("Deposited " + this.amount + " to Account " + userAccountId + " - New Balance: " + newBalance);
    }

    @Override
    public TransactionTypeEnum getTransactionType() {
        return TransactionTypeEnum.DEPOSIT;
    }

    @Override
    public List<IAccount> getAccounts() {
        return account;
    }


    @Override
    public Double getAmount() {
        return amount;
    }
}
