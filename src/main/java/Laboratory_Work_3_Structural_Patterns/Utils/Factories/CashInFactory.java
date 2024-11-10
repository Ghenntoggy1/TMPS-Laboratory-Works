package Laboratory_Work_3_Structural_Patterns.Utils.Factories;

import Laboratory_Work_3_Structural_Patterns.Enums.TransactionTypeEnum;

import Laboratory_Work_3_Structural_Patterns.Interfaces.BuilderInterfaces.ITransactionBuilder;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAbstractTerminalTransactionFactory;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccount;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ITerminal;
import Laboratory_Work_3_Structural_Patterns.Interfaces.ITransaction;

import Laboratory_Work_3_Structural_Patterns.Terminals.CashInTerminal;

import Laboratory_Work_3_Structural_Patterns.Utils.Builders.DepositTransactionBuilder;

import java.util.List;

public class CashInFactory implements IAbstractTerminalTransactionFactory {
    ITransactionBuilder transactionBuilder;

    @Override
    public ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType) {
        if (transactionType != TransactionTypeEnum.DEPOSIT) {
            return null;
        }
        transactionBuilder = new DepositTransactionBuilder();
        setAccounts(accounts);
        setAmount(amount);
        return getTransaction();
    }

    @Override
    public ITerminal createTerminal() {
        return new CashInTerminal();
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
