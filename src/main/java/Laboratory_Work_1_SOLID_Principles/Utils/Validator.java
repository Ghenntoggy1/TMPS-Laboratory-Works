package Laboratory_Work_1_SOLID_Principles.Utils;

import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;

public class Validator {
    private final ILogger logger;

    public Validator(ILogger logger) {
        this.logger = logger;
    }

    public boolean validateSufficientFunds(IAccount userAccount, double amount) {
        if (userAccount.getBalance() < amount) {
            logger.warningLog("Insufficient funds in account " + userAccount.getAccountId() +
                    ". Available: " + userAccount.getBalance() + ", required: " + amount);
            return false;
        }
        logger.infoLog("Account " + userAccount.getAccountId() + " has sufficient funds");
        return true;
    }

    public boolean validateAccountStatus(IAccount userAccount) {
        // TODO check if correct .equals or ==
        if (userAccount.getAccountStatus() != AccountStatusEnum.ACTIVE) {
            logger.warningLog("Account " + userAccount.getAccountId() + " is " + userAccount.getAccountStatus() +
                    " and cannot perform transactions");
            return false;
        }
        logger.infoLog("Account " + userAccount.getAccountId() + " is " + userAccount.getAccountStatus());
        return true;
    }

    // Validate both account status and funds for transaction
    public boolean validateTransaction(IAccount account, double amount) {
        return validateAccountStatus(account) && validateSufficientFunds(account, amount);
    }
}
