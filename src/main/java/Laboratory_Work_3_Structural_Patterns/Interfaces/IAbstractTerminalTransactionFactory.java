package Laboratory_Work_3_Structural_Patterns.Interfaces;

import Laboratory_Work_3_Structural_Patterns.Enums.TransactionTypeEnum;

import java.util.List;

public interface IAbstractTerminalTransactionFactory {
    ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType);
    ITerminal createTerminal();
}
