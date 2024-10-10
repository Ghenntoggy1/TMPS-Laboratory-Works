package Laboratory_Work_1_SOLID_Principles.Terminals;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITerminal;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_1_SOLID_Principles.Transactions.DepositTransaction;
import Laboratory_Work_1_SOLID_Principles.Enums.TransactionTypeEnum;

import java.util.List;

public class CashInTerminal implements ITerminal {
//    private ILogger logger;
//
//    public CashInTerminal(ILogger logger) {
//        this.logger = logger;
//    }
//
    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, ILogger logger, double amount) {
//        this.logger.infoLog("Initiated Cash-In Transaction to Account " + account.getAccountId() + "on Amount" + amount);
//        accounts.getFirst().withdraw(amount);
//        this.logger.infoLog("Closed Cash-In Transaction to Account" + accounts.getFirst().getAccountId() + "on Amount" + amount);
        if (transactionType != TransactionTypeEnum.DEPOSIT) {
            logger.errorLog("Invalid Transaction Type");
            return;
        }
        logger.infoLog("Initiated Cash-In Transaction to Account" + accounts.getFirst().getAccountId() + " on Amount " + amount);
        ITransaction depositTransaction = new DepositTransaction(accounts.getFirst(), logger, amount);
        depositTransaction.executeTransaction();
        logger.infoLog("Closed Cash-In Transaction to Account" + accounts.getFirst().getAccountId() + " on Amount " + amount);
    }
}
