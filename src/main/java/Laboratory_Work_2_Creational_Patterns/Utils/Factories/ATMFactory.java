package Laboratory_Work_2_Creational_Patterns.Utils.Factories;

import Laboratory_Work_2_Creational_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransaction;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAbstractTerminalTransactionFactory;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITerminal;
import Laboratory_Work_2_Creational_Patterns.Terminals.ATMTerminal;
import Laboratory_Work_2_Creational_Patterns.Utils.Builders.DepositTransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Utils.Builders.ExchangeTransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Utils.Builders.WithdrawalTransactionBuilder;

import java.util.List;

public class ATMFactory implements IAbstractTerminalTransactionFactory {
    @Override
    public ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType) {
        switch (transactionType) {
            case DEPOSIT:
                try {
                    DepositTransactionBuilder depositTransactionBuilder = new DepositTransactionBuilder();
                    depositTransactionBuilder.setAccounts(accounts);
                    depositTransactionBuilder.setAmount(amount);
                    return depositTransactionBuilder.getResult();
                }
                catch(IllegalArgumentException e) {
                    return null;
                }
            case WITHDRAWAL:
                try {
                WithdrawalTransactionBuilder withdrawalTransactionBuilder = new WithdrawalTransactionBuilder();
                withdrawalTransactionBuilder.setAccounts(accounts);
                withdrawalTransactionBuilder.setAmount(amount);
                return withdrawalTransactionBuilder.getResult();
                }
                catch(IllegalArgumentException e) {
                    return null;
                }
            case EXCHANGE:
                try {
                ExchangeTransactionBuilder exchangeTransactionBuilder = new ExchangeTransactionBuilder();
                exchangeTransactionBuilder.setAccounts(accounts);
                exchangeTransactionBuilder.setAmount(amount);
                return exchangeTransactionBuilder.getResult();
                }
                catch(IllegalArgumentException e) {
                    return null;
                }
            default:
                return null;
        }
    }

    @Override
    public ITerminal createTerminal() {
        return new ATMTerminal();
    }
}
