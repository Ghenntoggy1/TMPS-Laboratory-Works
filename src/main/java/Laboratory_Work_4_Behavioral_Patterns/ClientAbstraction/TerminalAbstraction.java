package Laboratory_Work_4_Behavioral_Patterns.ClientAbstraction;

import Laboratory_Work_4_Behavioral_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.IAbstractTerminalTransactionFactory;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.IAccount;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITerminal;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransaction;

import java.util.List;

public class TerminalAbstraction {
    private IAbstractTerminalTransactionFactory transactionFactory;

    public TerminalAbstraction(IAbstractTerminalTransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    public void initiateTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType) {
        ITransaction transaction = transactionFactory.createTransaction(accounts, amount, transactionType);
        ITerminal terminal = transactionFactory.createTerminal();
        terminal.performTransaction(transaction);
    }
}
