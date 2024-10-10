package Laboratory_Work_1_SOLID_Principles.Terminals;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITerminal;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_1_SOLID_Principles.Transactions.DepositTransaction;
import Laboratory_Work_1_SOLID_Principles.Transactions.ExchangeTransaction;
import Laboratory_Work_1_SOLID_Principles.Transactions.WithdrawalTransaction;
import Laboratory_Work_1_SOLID_Principles.Enums.TransactionTypeEnum;
import Laboratory_Work_1_SOLID_Principles.Utils.Validators.AccountStatusValidator;
import Laboratory_Work_1_SOLID_Principles.Utils.Validators.TransactionValidator;

import java.util.List;

public class ATMTerminal implements ITerminal {
    private ILogger logger;

    public ATMTerminal(ILogger logger) {
        this.logger = logger;
    }

    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {;
        switch (transactionType) {
            case DEPOSIT:
                ITransaction depositTransaction = new DepositTransaction(accounts.getFirst(), logger, amount, new AccountStatusValidator(logger));
                depositTransaction.executeTransaction();
                break;
            case EXCHANGE:
                ITransaction exchangeTransaction = new ExchangeTransaction(accounts.getFirst(), accounts.getLast(), logger, amount, new TransactionValidator(logger));
                exchangeTransaction.executeTransaction();
                break;
            case WITHDRAWAL:
                ITransaction withdrawalTransaction = new WithdrawalTransaction(accounts.getFirst(), logger, amount, new TransactionValidator(logger));
                withdrawalTransaction.executeTransaction();
                break;
            default:
                logger.errorLog("Invalid Transaction Type");
        }
    }
}
