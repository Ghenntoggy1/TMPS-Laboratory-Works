package Laboratory_Work_1_SOLID_Principles.Interfaces;

public interface ITransactionValidator extends IAccountStatusValidator {
    boolean validateSufficientFunds(IAccount account, double amount);
    boolean validateTransaction(IAccount account, double amount);
}
