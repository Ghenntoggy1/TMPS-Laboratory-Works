package Laboratory_Work_3_Structural_Patterns.Transactions;

import Laboratory_Work_3_Structural_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccount;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ITransaction;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ITransactionValidator;
import Laboratory_Work_3_Structural_Patterns.Utils.Logging.LoggerProxy;
import Laboratory_Work_3_Structural_Patterns.Utils.Validators.TransactionValidator;

import java.util.List;

public class WithdrawalTransaction implements ITransaction {
    private List<IAccount> account;
    private Double amount;
    private ILogger logger;
    private ITransactionValidator validator;

    public WithdrawalTransaction(List<IAccount> account, double amount) {
        this.logger = LoggerProxy.getInstance();
        this.account = account;
        if (account == null) {
            this.logger.errorLog("Withdrawal Transaction failed due to invalid account - Account is null");
            throw new IllegalArgumentException("Invalid accounts");
        }
        this.amount = amount;
        if (amount <= 0) {
            this.logger.errorLog("Withdrawal Transaction failed due to invalid amount - Amount is less than or equal to 0");
            throw new IllegalArgumentException("Invalid amount");
        }
        this.validator = TransactionValidator.getInstance();
    }

    @Override
    public void executeTransaction() {
        IAccount account = this.account.getFirst();
        int userAccountId = account.getAccountId();
        this.logger.infoLog("Initiated Withdrawal Transaction for Account " + userAccountId);

        if (!this.validator.validateTransaction(account, this.amount)) {
            this.logger.errorLog("Withdrawal Transaction failed for Account " + userAccountId);
            return;
        }

        account.withdraw(this.amount);
        double newBalance = account.getBalance();
        this.logger.infoLog("Withdrew " + this.amount + " from account " + userAccountId + " - New balance: " + newBalance);
    }

    @Override
    public TransactionTypeEnum getTransactionType() {
        return TransactionTypeEnum.WITHDRAWAL;
    }

    @Override
    public List<IAccount> getAccounts() {
        return this.account;
    }

    @Override
    public Double getAmount() {
        return this.amount;
    }
}
