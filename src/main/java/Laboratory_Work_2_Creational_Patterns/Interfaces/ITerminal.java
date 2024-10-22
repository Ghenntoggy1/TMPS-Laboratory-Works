package Laboratory_Work_2_Creational_Patterns.Interfaces;

import Laboratory_Work_1_SOLID_Principles.Enums.TransactionTypeEnum;
import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;

import java.util.List;

public interface ITerminal {
    void performTransaction(List<IAccount> account, TransactionTypeEnum transactionType, double amount);
}
