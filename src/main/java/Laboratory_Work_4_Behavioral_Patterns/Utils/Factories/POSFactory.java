package Laboratory_Work_4_Behavioral_Patterns.Utils.Factories;

import Laboratory_Work_4_Behavioral_Patterns.Enums.TransactionTypeEnum;

import Laboratory_Work_4_Behavioral_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.IAbstractTerminalTransactionFactory;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.IAccount;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITerminal;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransaction;

import Laboratory_Work_4_Behavioral_Patterns.Terminals.POSTerminal;

import Laboratory_Work_4_Behavioral_Patterns.Utils.Builders.WithdrawalTransactionBuilder;

import java.util.List;

public class POSFactory implements IAbstractTerminalTransactionFactory {
    ITransactionBuilder transactionBuilder;

    @Override
    public ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType) {
        if (transactionType != TransactionTypeEnum.WITHDRAWAL) {
            return null;
        }
        transactionBuilder = new WithdrawalTransactionBuilder();
        setAccounts(accounts);
        setAmount(amount);
        return getTransaction();
    }

    @Override
    public ITerminal createTerminal() {
        return new POSTerminal();
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
