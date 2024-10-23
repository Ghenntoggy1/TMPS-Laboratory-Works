package Laboratory_Work_2_Creational_Patterns;

import Laboratory_Work_2_Creational_Patterns.Enums.AccountStatusEnum;
import Laboratory_Work_2_Creational_Patterns.Enums.TransactionTypeEnum;

import Laboratory_Work_2_Creational_Patterns.Interfaces.BuilderInterfaces.IUserAccountBuilder;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAbstractTerminalTransactionFactory;
import Laboratory_Work_2_Creational_Patterns.Interfaces.IAccount;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITerminal;
import Laboratory_Work_2_Creational_Patterns.Interfaces.ITransaction;

import Laboratory_Work_2_Creational_Patterns.User.User;

import Laboratory_Work_2_Creational_Patterns.Utils.Builders.UserAccountBuilder;
import Laboratory_Work_2_Creational_Patterns.Utils.Factories.ATMFactory;
import Laboratory_Work_2_Creational_Patterns.Utils.Factories.CashInFactory;
import Laboratory_Work_2_Creational_Patterns.Utils.Factories.POSFactory;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Laboratory Work 2 - Creational Design Patterns");

        // Create a new user
        IUserAccountBuilder userAccountBuilder = new UserAccountBuilder();

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
//        UserAccount userAccount3 = new UserAccount(3, user3, 0.0, AccountStatusEnum.INACTIVE);

        IAbstractTerminalTransactionFactory ATMFactory = new ATMFactory();
        IAbstractTerminalTransactionFactory CashInFactory = new CashInFactory();
        IAbstractTerminalTransactionFactory POSFactory = new POSFactory();

        // Create Terminals
        ITerminal atmTerminal = ATMFactory.createTerminal();
        ITerminal posTerminal = POSFactory.createTerminal();
        ITerminal cashInTerminal = CashInFactory.createTerminal();

        // Create Transactions

//        // User 1 performs transactions
//        // ERROR - User 1 tries to withdraw more than the balance
        ITransaction depositTransaction = ATMFactory.createTransaction(List.of(userAccount1), 100.0, TransactionTypeEnum.WITHDRAWAL);
//        atmTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.WITHDRAWAL, 100.0);
        atmTerminal.performTransaction(depositTransaction);

//        // ERROR - User 1 tries to withdraw negative amount
        depositTransaction = ATMFactory.createTransaction(List.of(userAccount1), -100.0, TransactionTypeEnum.WITHDRAWAL);
//        atmTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.WITHDRAWAL, 100.0);
        atmTerminal.performTransaction(depositTransaction);

        // User 1 deposits and withdraws money
        ITransaction depositTransaction1 = ATMFactory.createTransaction(List.of(userAccount1), 200.0, TransactionTypeEnum.DEPOSIT);
//        atmTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.DEPOSIT, 200.0);
        atmTerminal.performTransaction(depositTransaction1);

        ITransaction withdrawalTransaction = ATMFactory.createTransaction(List.of(userAccount1), 50.0, TransactionTypeEnum.WITHDRAWAL);
//        atmTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.WITHDRAWAL, 50.0);
        atmTerminal.performTransaction(withdrawalTransaction);


        // User 1 exchanges money with User 2
        ITransaction exchangeTransaction = ATMFactory.createTransaction(List.of(userAccount1, userAccount2), 50.0, TransactionTypeEnum.EXCHANGE);
//        atmTerminal.performTransaction(List.of(userAccount1, userAccount2), TransactionTypeEnum.EXCHANGE, 50.0);
        atmTerminal.performTransaction(exchangeTransaction);


        // User 1 deposits money
        ITransaction depositTransaction2 = CashInFactory.createTransaction(List.of(userAccount1), 100.0, TransactionTypeEnum.DEPOSIT);
//        cashInTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.DEPOSIT, 100.0);
        cashInTerminal.performTransaction(depositTransaction2);
//
        // User 1 pays via POS
        ITransaction withdrawalTransaction1 = POSFactory.createTransaction(List.of(userAccount1), 20.0, TransactionTypeEnum.WITHDRAWAL);
//        posTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.WITHDRAWAL, 20.0);
        posTerminal.performTransaction(withdrawalTransaction1);

        // User 1 tries to pay via POS with insufficient balance
        ITransaction withdrawalTransaction2 = POSFactory.createTransaction(List.of(userAccount1), 500.0, TransactionTypeEnum.WITHDRAWAL);
//        posTerminal.performTransaction(List.of(userAccount1), TransactionTypeEnum.WITHDRAWAL, 500.0);
        posTerminal.performTransaction(withdrawalTransaction2);

        // User 2 deposits money
        ITransaction depositTransaction3 = CashInFactory.createTransaction(List.of(userAccount2), 200.0, TransactionTypeEnum.DEPOSIT);
//        cashInTerminal.performTransaction(List.of(userAccount2), TransactionTypeEnum.DEPOSIT, 200.0);
        cashInTerminal.performTransaction(depositTransaction3);

        // User 2 pays via POS
        ITransaction withdrawalTransaction3 = POSFactory.createTransaction(List.of(userAccount2), 50.0, TransactionTypeEnum.WITHDRAWAL);
//        posTerminal.performTransaction(List.of(userAccount2), TransactionTypeEnum.WITHDRAWAL, 50.0);
        posTerminal.performTransaction(withdrawalTransaction3);

        // User 2 tries to exchange via ATM with insufficient balance
        ITransaction exchangeTransaction1 = ATMFactory.createTransaction(List.of(userAccount2, userAccount1), 500.0, TransactionTypeEnum.EXCHANGE);
//        atmTerminal.performTransaction(List.of(userAccount2, userAccount1), TransactionTypeEnum.EXCHANGE, 500.0);
        atmTerminal.performTransaction(exchangeTransaction1);

        // User 3 tries to deposit money
        ITransaction depositTransaction4 = CashInFactory.createTransaction(List.of(userAccount3), 100.0, TransactionTypeEnum.DEPOSIT);
//        cashInTerminal.performTransaction(List.of(userAccount3), TransactionTypeEnum.DEPOSIT, 100.0);
        cashInTerminal.performTransaction(depositTransaction4);

        // User 3 tries to withdraw money
        ITransaction withdrawalTransaction4 = ATMFactory.createTransaction(List.of(userAccount3), 50.0, TransactionTypeEnum.WITHDRAWAL);
//        atmTerminal.performTransaction(List.of(userAccount3), TransactionTypeEnum.WITHDRAWAL, 50.0);
        atmTerminal.performTransaction(withdrawalTransaction4);

        // User 3 tries to exchange money
        ITransaction exchangeTransaction2 = ATMFactory.createTransaction(List.of(userAccount3, userAccount1), 50.0, TransactionTypeEnum.EXCHANGE);
//        atmTerminal.performTransaction(List.of(userAccount3, userAccount1), TransactionTypeEnum.EXCHANGE, 50.0);
        atmTerminal.performTransaction(exchangeTransaction2);

        userAccount3.setAccountStatus(AccountStatusEnum.ACTIVE);
        // User 3 tries to deposit money
        ITransaction depositTransaction5 = CashInFactory.createTransaction(List.of(userAccount3), 100.0, TransactionTypeEnum.DEPOSIT);
//        cashInTerminal.performTransaction(List.of(userAccount3), TransactionTypeEnum.DEPOSIT, 100.0);
        cashInTerminal.performTransaction(depositTransaction5);

        // User 3 tries to withdraw money
        ITransaction withdrawalTransaction5 = ATMFactory.createTransaction(List.of(userAccount3), 50.0, TransactionTypeEnum.WITHDRAWAL);
//        atmTerminal.performTransaction(List.of(userAccount3), TransactionTypeEnum.WITHDRAWAL, 50.0);
        atmTerminal.performTransaction(withdrawalTransaction5);

        // User 3 tries to exchange money
        ITransaction exchangeTransaction3 = ATMFactory.createTransaction(List.of(userAccount3, userAccount1), 50.0, TransactionTypeEnum.EXCHANGE);
//        atmTerminal.performTransaction(List.of(userAccount3, userAccount1), TransactionTypeEnum.EXCHANGE, 50.0);
        atmTerminal.performTransaction(exchangeTransaction3);

        // User 3 tries to exchange money in POS
        ITransaction withdrawalTransaction6 = POSFactory.createTransaction(List.of(userAccount3, userAccount1), 5.0, TransactionTypeEnum.WITHDRAWAL);
//        atmTerminal.performTransaction(List.of(userAccount3, userAccount1), TransactionTypeEnum.WITHDRAWAL, 5.0);
        atmTerminal.performTransaction(withdrawalTransaction6);

    }
}