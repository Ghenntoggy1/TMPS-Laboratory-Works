package Laboratory_Work_2_Creational_Patterns.Utils.Factories;

import Laboratory_Work_2_Creational_Patterns.Enums.TransactionTypeEnum;

import Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAbstractTerminalTransactionFactory;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITerminal;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransaction;

import Laboratory_Work_2_Creational_Patterns.Terminals.POSTerminal;

import Laboratory_Work_2_Creational_Patterns.Utils.Builders.WithdrawalTransactionBuilder;

import java.util.List;

public class POSFactory implements IAbstractTerminalTransactionFactory {
    @Override
    public ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType) {
        if (transactionType != TransactionTypeEnum.WITHDRAWAL) {
            return null;
        }
        ITransactionBuilder withdrawalTransactionBuilder = new WithdrawalTransactionBuilder();
        withdrawalTransactionBuilder.setAccounts(accounts);
        withdrawalTransactionBuilder.setAmount(amount);
        return withdrawalTransactionBuilder.getResult();
    }

    @Override
    public ITerminal createTerminal() {
        return new POSTerminal();
    }
}
