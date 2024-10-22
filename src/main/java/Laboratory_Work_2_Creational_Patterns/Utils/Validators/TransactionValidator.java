package Laboratory_Work_2_Creational_Patterns.Utils.Validators;

import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransactionValidator;

public class TransactionValidator extends AccountStatusValidator implements ITransactionValidator {

    @Override
    public boolean validateSufficientFunds(IAccount userAccount, double amount) {
        int userAccountId = userAccount.getAccountId();
        double userAccountBalance = userAccount.getBalance();
        if (userAccountBalance < amount) {
            logger.warningLog("Insufficient funds in account " + userAccountId +
                    ". Available: " + userAccountBalance + ", required: " + amount);
            return false;
        }
        logger.infoLog("Account " + userAccountId + " has sufficient funds");
        return true;
    }

    @Override
    public boolean validateTransaction(IAccount account, double amount) {
        return this.validateAccountStatus(account) && this.validateSufficientFunds(account, amount);
    }
}
