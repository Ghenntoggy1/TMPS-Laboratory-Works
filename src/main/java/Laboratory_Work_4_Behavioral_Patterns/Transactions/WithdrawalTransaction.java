package Laboratory_Work_4_Behavioral_Patterns.Transactions;

import Laboratory_Work_4_Behavioral_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.*;
import Laboratory_Work_4_Behavioral_Patterns.Transactions.TransactionStates.InProcessTransactionState;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Logging.LoggerProxy;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Validators.TransactionValidator;

import java.util.List;

public class WithdrawalTransaction implements ITransaction {
    private List<IAccount> account;
    private Double amount;
    private ILogger logger;
    private ITransactionValidator validator;
    private ITransactionState currentTransactionState;

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
        this.currentTransactionState = new InProcessTransactionState();
        this.currentTransactionState.setTransactionContext(this);
    }

    @Override
    public void executeTransaction() {
        this.currentTransactionState.setTransactionContext(this);
        this.currentTransactionState.executeTransaction();
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

    @Override
    public ITransactionValidator getValidator() {
        return this.validator;
    }

    @Override
    public void changeTransactionState(ITransactionState transactionState) {
        this.currentTransactionState = transactionState;
    }
}
