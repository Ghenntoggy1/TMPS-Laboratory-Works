package Laboratory_Work_0_SOLID_Principles.Interfaces;

public interface ITransactionValidator extends IAccountStatusValidator {
    boolean validateSufficientFunds(IAccount account, double amount);
    boolean validateTransaction(IAccount account, double amount);
}
