package Laboratory_Work_1_SOLID_Principles.Utils;

import Laboratory_Work_1_SOLID_Principles.Enums.AccountStatusEnum;
import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;

public class Validator {
    private final ILogger logger;

    public Validator(ILogger logger) {
        this.logger = logger;
    }

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

    public boolean validateAccountStatus(IAccount userAccount) {
        AccountStatusEnum userAccountStatus = userAccount.getAccountStatus();
        int userAccountId = userAccount.getAccountId();
        if (userAccountStatus != AccountStatusEnum.ACTIVE) {
            logger.warningLog("Account " + userAccountId + " is " + userAccountStatus +
                    " and cannot perform transactions");
            return false;
        }
        logger.infoLog("Account " + userAccountId + " is " + userAccountStatus);
        return true;
    }

    public boolean validateTransaction(IAccount account, double amount) {
        return validateAccountStatus(account) && validateSufficientFunds(account, amount);
    }
}
