package Laboratory_Work_1_SOLID_Principles.Terminals;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITerminal;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_1_SOLID_Principles.Transactions.WithdrawalTransaction;
import Laboratory_Work_1_SOLID_Principles.Enums.TransactionTypeEnum;

import java.util.List;

public class POSTerminal implements ITerminal {
//    private ILogger logger;
//
//    public POS(ILogger logger) {
//        this.logger = logger;
//    }
//
    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, ILogger logger, double amount) {
//        this.logger.infoLog("Initiated POS Transaction from Account" + accounts.getFirst().getAccountId() + "on Amount" + amount);
//        accounts.getFirst().withdraw(amount);
//        this.logger.infoLog("Closed POS Transaction from Account" + accounts.getFirst().getAccountId() + "on Amount" + amount);
        if (transactionType != TransactionTypeEnum.WITHDRAWAL) {
            logger.infoLog("Invalid Transaction Type");
            return;
        }
        logger.infoLog("Initiated POS Transaction from Account" + accounts.getFirst().getAccountId() + " on Amount " + amount);
        ITransaction withdrawalTransaction = new WithdrawalTransaction(accounts.getFirst(), logger, amount);
        withdrawalTransaction.executeTransaction();
        logger.infoLog("Closed POS Transaction from Account" + accounts.getFirst().getAccountId() + " on Amount " + amount);
    }
}
