package Laboratory_Work_2_Creational_Patterns.Utils.Validators;

import Laboratory_Work_1_SOLID_Principles.Enums.AccountStatusEnum;
import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccount;
import Laboratory_Work_1_SOLID_Principles.Interfaces.IAccountStatusValidator;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;

public class AccountStatusValidator implements IAccountStatusValidator {
    private final ILogger logger;

    public AccountStatusValidator(ILogger logger) {
        this.logger = logger;
    }

    @Override
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

    public ILogger getLogger() {
        return this.logger;
    }
}
