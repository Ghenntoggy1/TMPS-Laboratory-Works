package Laboratory_Work_4_Behavioral_Patterns.Interfaces;

public interface ITransactionState {
    void setTransactionContext(ITransaction transactionContext);
    void executeTransaction();
}
