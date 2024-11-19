package Laboratory_Work_4_Behavioral_Patterns.Transactions;

import Laboratory_Work_4_Behavioral_Patterns.Interfaces.ITransactionState;

public class CompletedTransactionState implements ITransactionState {
    @Override
    public void process() {
        System.out.println("Transaction is being processed");
    }

    @Override
    public void close() {
        System.out.println("Transaction is closed");
    }
}
