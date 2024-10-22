package Laboratory_Work_2_Creational_Patterns.Transactions;

import Laboratory_Work_2_Creational_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces.IBuilder;
import Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ILogger;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransaction;
import Laboratory_Work_2_Creational_Patterns.Utils.Builders.DepositTransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Utils.Builders.ExchangeTransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Utils.Builders.WithdrawalTransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Utils.Logging.LoggerImpl;
import Laboratory_Work_2_Creational_Patterns.Utils.Validators.AccountStatusValidator;
import Laboratory_Work_2_Creational_Patterns.Utils.Validators.TransactionValidator;

import java.util.List;

public class TransactionFactory {
    public static TransactionFactory instance;
    private static ITransactionBuilder builder;

    public ITransaction createTransaction(TransactionTypeEnum transactionType,
                                          List<IAccount> accounts,
                                          double amount) {
        return switch (transactionType) {
            case DEPOSIT -> {
                builder = new DepositTransactionBuilder();
                builder.setAccounts(accounts);
                builder.setAmount(amount);
                yield builder.getResult();
            }
            case EXCHANGE -> {
                builder = new ExchangeTransactionBuilder();
                builder.setAccounts(accounts);
                builder.setAmount(amount);
                yield builder.getResult();
            }
            case WITHDRAWAL -> {
                builder = new WithdrawalTransactionBuilder();
                builder.setAccounts(accounts);
                builder.setAmount(amount);
                yield builder.getResult();
            }
        };
    }

    public static TransactionFactory getInstance() {
        ILogger logger = LoggerImpl.getInstance();

        if (instance == null) {
            instance = new TransactionFactory();
            logger.infoLog("Logger Initialized using Singleton Pattern");
        }
        else {
            logger.infoLog("Logger already initialized - Returning existing instance");
        }
        return instance;
    }
}

