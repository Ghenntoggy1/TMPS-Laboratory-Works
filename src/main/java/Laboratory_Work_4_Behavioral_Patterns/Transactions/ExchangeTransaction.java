package Laboratory_Work_4_Behavioral_Patterns.Transactions;

import Laboratory_Work_4_Behavioral_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.*;
import Laboratory_Work_4_Behavioral_Patterns.Transactions.TransactionStates.InProcessTransactionState;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Logging.LoggerProxy;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Validators.TransactionValidator;

import java.util.List;

public class ExchangeTransaction implements ITransaction {
    private List<IAccount> accounts;
    private Double amount;
    private ILogger logger;
    private ITransactionValidator validator;
    private ITransactionState currentTransactionState;

    public ExchangeTransaction(List<IAccount> accounts, Double amount) {
        this.logger = LoggerProxy.getInstance();
        this.accounts = accounts;
        if (accounts == null) {
            this.logger.errorLog("Exchange Transaction failed due to invalid accounts - Accounts are null");
            throw new IllegalArgumentException("Invalid accounts");
        }
        this.amount = amount;
        if (amount <= 0) {
            this.logger.errorLog("Exchange Transaction failed due to invalid amount - Amount is less than or equal to 0");
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
    public ITransactionValidator getValidator() {
        return this.validator;
    }

    @Override
    public TransactionTypeEnum getTransactionType() {
        return TransactionTypeEnum.EXCHANGE;
    }

    @Override
    public List<IAccount> getAccounts() {
        return this.accounts;
    }

    @Override
    public Double getAmount() {
        return this.amount;
    }

    @Override
    public void changeTransactionState(ITransactionState transactionState) {
        this.currentTransactionState = transactionState;
    }
}
