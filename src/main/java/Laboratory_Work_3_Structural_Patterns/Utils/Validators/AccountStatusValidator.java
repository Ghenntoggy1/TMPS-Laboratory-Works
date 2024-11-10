package Laboratory_Work_3_Structural_Patterns.Utils.Validators;

import Laboratory_Work_3_Structural_Patterns.Enums.AccountStatusEnum;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccount;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccountStatusValidator;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import Laboratory_Work_3_Structural_Patterns.Utils.Logging.LoggerUtil;

public class AccountStatusValidator implements IAccountStatusValidator {
    private static AccountStatusValidator instance;
    protected final ILogger logger = LoggerUtil.getInstance();

    protected AccountStatusValidator() {}

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

    public static AccountStatusValidator getInstance() {
        ILogger logger = LoggerUtil.getInstance();

        if (instance == null) {
            instance = new AccountStatusValidator();
            logger.infoLog("Account Status Validator Initialized using Singleton Pattern");
        }
        else {
            logger.infoLog("Account Status Validator already initialized - Returning existing instance");
        }
        return instance;
    }
}
