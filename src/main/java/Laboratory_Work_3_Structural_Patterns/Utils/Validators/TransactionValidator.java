package Laboratory_Work_3_Structural_Patterns.Utils.Validators;

import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccount;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ITransactionValidator;
import Laboratory_Work_3_Structural_Patterns.Utils.Logging.LoggerImpl;

public class TransactionValidator extends AccountStatusValidator implements ITransactionValidator {
    private static TransactionValidator instance;

    private TransactionValidator() {}

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

    public static TransactionValidator getInstance() {
        ILogger logger = LoggerImpl.getInstance();

        if (instance == null) {
            instance = new TransactionValidator();
            logger.infoLog("Transaction Validator Initialized using Singleton Pattern");
        }
        else {
            logger.infoLog("Transaction Validator already initialized - Returning existing instance");
        }
        return instance;
    }
}
