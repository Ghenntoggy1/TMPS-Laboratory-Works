package Laboratory_Work_2_Creational_Patterns.Transactions;

import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ILogger;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransaction;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransactionValidator;
import Laboratory_Work_2_Creational_Patterns.Utils.Logging.LoggerImpl;
import Laboratory_Work_2_Creational_Patterns.Utils.Validators.TransactionValidator;

import java.util.List;

public class ExchangeTransaction implements ITransaction {
//    private IAccount senderAccount;
//    private IAccount receiverAccount;
    private List<IAccount> accounts;
    private double amount;
    private ILogger logger;
    private ITransactionValidator validator;

//    public ExchangeTransaction(IAccount senderAccount, IAccount receiverAccount, double amount, ITransactionValidator validator) {
//        this.senderAccount = senderAccount;
//        this.receiverAccount = receiverAccount;
//        this.amount = amount;
//        this.validator = validator;
//        this.logger = LoggerImpl.getInstance();
//    }


    public ExchangeTransaction(List<IAccount> accounts, double amount) {
        this.accounts = accounts;
        this.amount = amount;
        this.validator = TransactionValidator.getInstance();
        this.logger = LoggerImpl.getInstance();
    }

    @Override
    public void executeTransaction() {
        IAccount senderAccount = this.accounts.getFirst();
        IAccount receiverAccount = this.accounts.getLast();
        int senderAccountId = senderAccount.getAccountId();
        int receiverAccountId = receiverAccount.getAccountId();
        this.logger.infoLog("Initiated Exchange Transaction from Account " + senderAccountId +
                " to Account " + receiverAccountId);
        if (validator.validateTransaction(senderAccount, this.amount) && this.validator.validateAccountStatus(receiverAccount)) {
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
}
