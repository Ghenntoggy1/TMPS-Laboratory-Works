package Laboratory_Work_2_Creational_Patterns;

import Laboratory_Work_2_Creational_Patterns.Enums.AccountStatusEnum;
import Laboratory_Work_2_Creational_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Terminals.ATMTerminal;
import Laboratory_Work_2_Creational_Patterns.Terminals.CashInTerminal;
import Laboratory_Work_2_Creational_Patterns.Terminals.POSTerminal;
import Laboratory_Work_2_Creational_Patterns.Transactions.TransactionFactory;
import Laboratory_Work_2_Creational_Patterns.User.User;
import Laboratory_Work_2_Creational_Patterns.User.UserAccount;
import Laboratory_Work_2_Creational_Patterns.Utils.Builders.UserAccountBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Laboratory Work 2 - Creational Design Patterns");

        // Create a new user
        UserAccountBuilder userAccountBuilder = new UserAccountBuilder();

        User user1 = new User(1, "John Doe");
        userAccountBuilder.setAccountId(1);
        userAccountBuilder.setUser(user1);
        userAccountBuilder.setBalance(0.0);
        userAccountBuilder.setStatus(AccountStatusEnum.ACTIVE);

        IAccount userAccount1 =  userAccountBuilder.getResult();
//        UserAccount userAccount1 = new UserAccount(1, user1, 0.0, AccountStatusEnum.ACTIVE);

        User user2 = new User(2, "Jane Doe");
        userAccountBuilder.setAccountId(2);
        userAccountBuilder.setUser(user2);
        userAccountBuilder.setBalance(50.0);
        userAccountBuilder.setStatus(AccountStatusEnum.ACTIVE);
        IAccount userAccount2 =  userAccountBuilder.getResult();
//        UserAccount userAccount2 = new UserAccount(2, user2, 0.0, AccountStatusEnum.ACTIVE);

        User user3 = new User(3, "John Smith");
        userAccountBuilder.setAccountId(3);
        userAccountBuilder.setUser(user3);
        IAccount userAccount3 =  userAccountBuilder.getResult();
//        UserAccount userAccount3 = new UserAccount(3, user3, 0.0, AccountStatusEnum.ACTIVE);
//        userAccount3.setAccountStatus(AccountStatusEnum.INACTIVE);

        // Create a Transaction Factory
        TransactionFactory transactionFactory = new TransactionFactory();

        // Create Terminals
        ATMTerminal atmTerminal = new ATMTerminal(transactionFactory);
        POSTerminal posTerminal = new POSTerminal(transactionFactory);
        CashInTerminal cashInTerminal = new CashInTerminal(transactionFactory);

        // User 1 performs transactions
        // ERROR - User 1 tries to withdraw more than the balance
        atmTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.WITHDRAWAL, 100.0);

        // User 1 deposits and withdraws money
        atmTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.DEPOSIT, 200.0);
        atmTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.WITHDRAWAL, 50.0);


//        // User 1 exchanges money with User 2
        atmTerminal.performTransaction(List.of(userAccount1, userAccount2), TransactionTypeEnum.EXCHANGE, 50.0);

//        // User 1 deposits money
        cashInTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.DEPOSIT, 100.0);

//        // User 1 pays via POS
        posTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.WITHDRAWAL, 20.0);

//        // User 1 tries to pay via POS with insufficient balance
        posTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.WITHDRAWAL, 500.0);

//        // User 2 deposits money
        cashInTerminal.performTransaction(List.of(userAccount2), TransactionTypeEnum.DEPOSIT, 200.0);

//        // User 2 pays via POS
        posTerminal.performTransaction(List.of(userAccount2), TransactionTypeEnum.WITHDRAWAL, 50.0);

//        // User 2 tries to exchange via ATM with insufficient balance
        atmTerminal.performTransaction(List.of(userAccount2, userAccount1), TransactionTypeEnum.EXCHANGE, 500.0);

        // User 3 tries to deposit money
        cashInTerminal.performTransaction(List.of(userAccount3), TransactionTypeEnum.DEPOSIT, 100.0);

        // User 3 tries to withdraw money
        atmTerminal.performTransaction(List.of(userAccount3), TransactionTypeEnum.WITHDRAWAL, 50.0);

        // User 3 tries to exchange money
        atmTerminal.performTransaction(List.of(userAccount3, userAccount1), TransactionTypeEnum.EXCHANGE, 50.0);

        userAccount3.setAccountStatus(AccountStatusEnum.ACTIVE);
        // User 3 tries to deposit money
        cashInTerminal.performTransaction(List.of(userAccount3), TransactionTypeEnum.DEPOSIT, 100.0);

        // User 3 tries to withdraw money
        atmTerminal.performTransaction(List.of(userAccount3), TransactionTypeEnum.WITHDRAWAL, 50.0);

        // User 3 tries to exchange money
        atmTerminal.performTransaction(List.of(userAccount3, userAccount1), TransactionTypeEnum.EXCHANGE, 50.0);

        // User 3 tries to exchange money in POS
        atmTerminal.performTransaction(List.of(userAccount3, userAccount1), TransactionTypeEnum.WITHDRAWAL, 5.0);

    }
}