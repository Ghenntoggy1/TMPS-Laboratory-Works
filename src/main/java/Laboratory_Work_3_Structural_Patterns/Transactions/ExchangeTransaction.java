package Laboratory_Work_3_Structural_Patterns.Transactions;

import Laboratory_Work_3_Structural_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccount;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ITransaction;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ITransactionValidator;
import Laboratory_Work_3_Structural_Patterns.Utils.Logging.LoggerUtil;
import Laboratory_Work_3_Structural_Patterns.Utils.Validators.TransactionValidator;

import java.util.List;

public class ExchangeTransaction implements ITransaction {
    private List<IAccount> accounts;
    private Double amount;
    private ILogger logger;
    private ITransactionValidator validator;

    public ExchangeTransaction(List<IAccount> accounts, Double amount) {
        this.logger = LoggerUtil.getInstance();
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
    }

    @Override
    public void executeTransaction() {
        IAccount senderAccount = this.accounts.getFirst();
        IAccount receiverAccount = this.accounts.getLast();
        int senderAccountId = senderAccount.getAccountId();
        int receiverAccountId = receiverAccount.getAccountId();
        this.logger.infoLog("Initiated Exchange Transaction from Account " + senderAccountId +
                " to Account " + receiverAccountId);
        if (this.validator.validateTransaction(senderAccount, this.amount) && this.validator.validateAccountStatus(receiverAccount)) {
            senderAccount.withdraw(this.amount);
            receiverAccount.deposit(this.amount);
            this.logger.infoLog("Successfully Exchanged " + this.amount + " from Account " + senderAccountId +
                    " to Account " + receiverAccountId);
        }
        else {
            this.logger.errorLog("Exchange Transaction failed from Account " + senderAccountId +
                    " to Account " + receiverAccountId);
        }
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
}
