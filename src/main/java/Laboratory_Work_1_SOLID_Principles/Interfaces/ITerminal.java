package Laboratory_Work_1_SOLID_Principles.Interfaces;

public interface ITerminal {
    void performTransaction(IAccount account, ILogger logger, float amount);
}
