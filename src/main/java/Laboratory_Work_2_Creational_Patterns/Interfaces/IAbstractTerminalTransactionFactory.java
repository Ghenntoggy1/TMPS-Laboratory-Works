package Laboratory_Work_2_Creational_Patterns.Interfaces;

public interface IAbstractTerminalTransactionFactory {
    ITransaction createTransaction();
    ITerminal createTerminal();
}
