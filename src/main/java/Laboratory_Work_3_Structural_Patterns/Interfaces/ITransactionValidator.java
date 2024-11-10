package Laboratory_Work_3_Structural_Patterns.Interfaces;

public interface ITransactionValidator extends IAccountStatusValidator {
    boolean validateSufficientFunds(IAccount account, double amount);
    boolean validateTransaction(IAccount account, double amount);
}
