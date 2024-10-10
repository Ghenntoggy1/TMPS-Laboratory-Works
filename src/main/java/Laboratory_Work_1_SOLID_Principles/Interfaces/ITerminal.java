package Laboratory_Work_1_SOLID_Principles.Interfaces;

import Laboratory_Work_1_SOLID_Principles.Enums.TransactionTypeEnum;

import java.util.List;

public interface ITerminal {
    void performTransaction(List<IAccount> account, TransactionTypeEnum transactionType, ILogger logger, double amount);
}
