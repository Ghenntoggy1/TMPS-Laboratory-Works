package Laboratory_Work_0_SOLID_Principles.Utils.Validators;

import Laboratory_Work_0_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_0_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_0_SOLID_Principles.Interfaces.ITransactionValidator;

public class TransactionValidator extends AccountStatusValidator implements ITransactionValidator {
    public TransactionValidator(ILogger logger) {
        super(logger);
    }

    @Override
    public boolean validateSufficientFunds(IAccount userAccount, double amount) {
        int userAccountId = userAccount.getAccountId();
        double userAccountBalance = userAccount.getBalance();
        if (userAccountBalance < amount) {
            super.getLogger().warningLog("Insufficient funds in account " + userAccountId +
                    ". Available: " + userAccountBalance + ", required: " + amount);
            return false;
        }
        super.getLogger().infoLog("Account " + userAccountId + " has sufficient funds");
        return true;
    }

    @Override
    public boolean validateTransaction(IAccount account, double amount) {
        return this.validateAccountStatus(account) && this.validateSufficientFunds(account, amount);
    }
}
