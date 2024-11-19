package Laboratory_Work_4_Behavioral_Patterns.Transactions;

import Laboratory_Work_4_Behavioral_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.*;
import Laboratory_Work_4_Behavioral_Patterns.Transactions.TransactionStates.InProcessTransactionState;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Logging.LoggerProxy;
import Laboratory_Work_4_Behavioral_Patterns.Utils.Validators.AccountStatusValidator;

import java.util.List;

public class DepositTransaction implements ITransaction {
    private List<IAccount> account;
    private Double amount;
    private ILogger logger;
    private IAccountStatusValidator validator;
    private ITransactionState currentTransactionState;

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

    @Override
    public IAccountStatusValidator getValidator() {
        return validator;
    }

    @Override
    public void changeTransactionState(ITransactionState transactionState) {
        this.currentTransactionState = transactionState;
    }
}
