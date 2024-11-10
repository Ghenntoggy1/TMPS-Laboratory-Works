package Laboratory_Work_3_Structural_Patterns.Utils.Factories;

import Laboratory_Work_3_Structural_Patterns.Enums.TransactionTypeEnum;

import Laboratory_Work_3_Structural_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAbstractTerminalTransactionFactory;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccount;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ILogger;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ITransaction;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ITerminal;

import Laboratory_Work_3_Structural_Patterns.Terminals.ATMTerminal;

import Laboratory_Work_3_Structural_Patterns.Utils.Builders.DepositTransactionBuilder;
import Laboratory_Work_3_Structural_Patterns.Utils.Builders.ExchangeTransactionBuilder;
import Laboratory_Work_3_Structural_Patterns.Utils.Builders.WithdrawalTransactionBuilder;
import Laboratory_Work_3_Structural_Patterns.Utils.Logging.LoggerImpl;

import java.util.List;

public class ATMFactory implements IAbstractTerminalTransactionFactory {
    private ILogger logger;
    private ITransactionBuilder transactionBuilder;

    public ATMFactory() {
        this.logger = LoggerImpl.getInstance();
    }

    @Override
    public ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType) {
        switch (transactionType) {
            case DEPOSIT:
                this.transactionBuilder = new DepositTransactionBuilder();
                break;
            case WITHDRAWAL:
                this.transactionBuilder = new WithdrawalTransactionBuilder();
                break;
            case EXCHANGE:
                this.transactionBuilder = new ExchangeTransactionBuilder();
                break;
            default:
                this.transactionBuilder = null;
                break;
        }
        if (transactionBuilder == null) {
            this.logger.errorLog("Invalid transaction Type: " + transactionType + " for ATM Terminal");
            return null;
        }
        try {
            setAccounts(accounts);
            setAmount(amount);
            return getTransaction();
        } catch (IllegalArgumentException e) {
            this.logger.errorLog("Failed ATM Transaction: " + e.getMessage());
            return null;
        }
    }

    @Override
    public ITerminal createTerminal() {
        return new ATMTerminal();
    }

    private void setAccounts(List<IAccount> accounts) {
        this.transactionBuilder.setAccounts(accounts);
    }

    private void setAmount(Double amount) {
        this.transactionBuilder.setAmount(amount);
    }

    private ITransaction getTransaction() {
        return this.transactionBuilder.getResult();
    }
}
