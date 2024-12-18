package Laboratory_Work_4_Behavioral_Patterns.Interfaces;

import Laboratory_Work_4_Behavioral_Patterns.Enums.TransactionTypeEnum;

import java.util.List;

public interface IAbstractTerminalTransactionFactory {
    ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType);
    ITerminal createTerminal();
}
