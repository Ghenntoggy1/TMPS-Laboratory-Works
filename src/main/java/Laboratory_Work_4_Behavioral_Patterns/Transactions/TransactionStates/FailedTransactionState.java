package Laboratory_Work_4_Behavioral_Patterns.Transactions.TransactionStates;

import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransaction;
import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransactionState;

public class FailedTransactionState implements ITransactionState {
    private ITransaction transactionContext;

    public void setTransactionContext(ITransaction transactionContext) {
        this.transactionContext = transactionContext;
    }

    @Override
    public void executeTransaction() {
        // Transaction is already failed
    }
}
