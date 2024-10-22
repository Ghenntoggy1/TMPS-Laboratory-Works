package Laboratory_Work_2_Creational_Patterns.Interfaces;

import Laboratory_Work_1_SOLID_Principles.Interfaces.ITerminal;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ITransaction;

public interface IAbstractTerminalTransactionFactory {
    ITransaction createTransaction();
    ITerminal createTerminal();
}
