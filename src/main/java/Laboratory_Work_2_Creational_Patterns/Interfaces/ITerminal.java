package Laboratory_Work_2_Creational_Patterns.Interfaces;

import Laboratory_Work_2_Creational_Patterns.Enums.TransactionTypeEnum;

import java.util.List;

public interface ITerminal {
    void performTransaction(List<IAccount> account, TransactionTypeEnum transactionType, double amount);
    void performTransaction(ITransaction transaction);
}
