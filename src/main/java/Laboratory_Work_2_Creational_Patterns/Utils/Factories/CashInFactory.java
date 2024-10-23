package Laboratory_Work_2_Creational_Patterns.Utils.Factories;

import Laboratory_Work_2_Creational_Patterns.Enums.TransactionTypeEnum;

import Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAbstractTerminalTransactionFactory;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITerminal;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransaction;

import Laboratory_Work_2_Creational_Patterns.Terminals.CashInTerminal;

import Laboratory_Work_2_Creational_Patterns.Utils.Builders.DepositTransactionBuilder;

import java.util.List;

public class CashInFactory implements IAbstractTerminalTransactionFactory {
    @Override
    public ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType) {
        if (transactionType != TransactionTypeEnum.DEPOSIT) {
            return null;
        }
        ITransactionBuilder depositTransactionBuilder = new DepositTransactionBuilder();
        depositTransactionBuilder.setAccounts(accounts);
        depositTransactionBuilder.setAmount(amount);
        return depositTransactionBuilder.getResult();
    }

    @Override
    public ITerminal createTerminal() {
        return new CashInTerminal();
    }
}
