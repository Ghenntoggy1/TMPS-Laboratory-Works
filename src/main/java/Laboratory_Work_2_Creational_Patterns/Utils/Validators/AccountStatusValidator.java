package Laboratory_Work_2_Creational_Patterns.Utils.Validators;

import Laboratory_Work_2_Creational_Patterns.Enums.AccountStatusEnum;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccountStatusValidator;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ILogger;
import Laboratory_Work_2_Creational_Patterns.Utils.Logging.LoggerImpl;

public class AccountStatusValidator implements IAccountStatusValidator {
    protected final ILogger logger = LoggerImpl.getInstance();

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
}
