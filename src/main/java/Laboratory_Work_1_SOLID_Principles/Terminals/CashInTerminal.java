package Laboratory_Work_1_SOLID_Principles.Terminals;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITerminal;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_1_SOLID_Principles.Transactions.DepositTransaction;
import Laboratory_Work_1_SOLID_Principles.Enums.TransactionTypeEnum;

import java.util.List;

public class CashInTerminal implements ITerminal {
    private ILogger logger;

    public CashInTerminal(ILogger logger) {
        this.logger = logger;
    }

    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
        if (transactionType != TransactionTypeEnum.DEPOSIT) {
            this.logger.errorLog("Invalid Transaction Type");
            return;
        }
        int userAccountId = accounts.getFirst().getAccountId();
        this.logger.infoLog("Initiated Cash-In Transaction to Account" + userAccountId + " on Amount " + amount);
        ITransaction depositTransaction = new DepositTransaction(accounts.getFirst(), logger, amount);
        depositTransaction.executeTransaction();
        this.logger.infoLog("Closed Cash-In Transaction to Account" + userAccountId + " on Amount " + amount);
    }
}
