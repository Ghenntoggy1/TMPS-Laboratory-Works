package Laboratory_Work_4_Behavioral_Patterns.Interfaces;

import Laboratory_Work_4_Behavioral_Patterns.Enums.TransactionTypeEnum;

import java.util.List;

public interface ITransaction {
    TransactionTypeEnum getTransactionType();
    List<IAccount> getAccounts();
    Double getAmount();
    void executeTransaction();
}