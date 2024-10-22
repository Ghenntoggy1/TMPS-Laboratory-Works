package Laboratory_Work_2_Creational_Patterns.Interfaces;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;

public interface ITransactionValidator extends IAccountStatusValidator {
    boolean validateSufficientFunds(Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount account, double amount);
    boolean validateTransaction(IAccount account, double amount);
}
