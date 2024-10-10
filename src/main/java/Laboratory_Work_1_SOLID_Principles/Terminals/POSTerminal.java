package Laboratory_Work_1_SOLID_Principles.Terminals;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITerminal;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;
import Laboratory_Work_1_SOLID_Principles.Transactions.WithdrawalTransaction;
import Laboratory_Work_1_SOLID_Principles.Enums.TransactionTypeEnum;
import Laboratory_Work_1_SOLID_Principles.Utils.Validators.TransactionValidator;

import java.util.List;

public class POSTerminal implements ITerminal {
    private ILogger logger;

    public POSTerminal(ILogger logger) {
        this.logger = logger;
    }

    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
        if (transactionType != TransactionTypeEnum.WITHDRAWAL) {
            logger.infoLog("Invalid Transaction Type");
            return;
        }
        int accountId = accounts.getFirst().getAccountId();
        logger.infoLog("Initiated POS Transaction from Account" + accountId + " on Amount " + amount);
        ITransaction withdrawalTransaction = new WithdrawalTransaction(accounts.getFirst(), logger, amount, new TransactionValidator(logger));
        withdrawalTransaction.executeTransaction();
        logger.infoLog("Closed POS Transaction from Account" + accountId + " on Amount " + amount);
    }
}
