package Laboratory_Work_3_Structural_Patterns;

import Laboratory_Work_3_Structural_Patterns.ClientAbstraction.TerminalAbstraction;
import Laboratory_Work_3_Structural_Patterns.Enums.AccountStatusEnum;
import Laboratory_Work_3_Structural_Patterns.Enums.TransactionTypeEnum;
import Laboratory_Work_3_Structural_Patterns.Interfaces.BuilderInterfaces.IUserAccountBuilder;
import Laboratory_Work_3_Structural_Patterns.Interfaces.IAccount;
import Laboratory_Work_3_Structural_Patterns.User.User;
import Laboratory_Work_3_Structural_Patterns.Utils.Builders.UserAccountBuilder;
import Laboratory_Work_3_Structural_Patterns.Utils.Factories.ATMFactory;
import Laboratory_Work_3_Structural_Patterns.Utils.Factories.CashInFactory;
import Laboratory_Work_3_Structural_Patterns.Utils.Factories.POSFactory;

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

        User user2 = new User(2, "Jane Doe");
        userAccountBuilder.setAccountId(2);
        userAccountBuilder.setUser(user2);
        userAccountBuilder.setBalance(50.0);
        userAccountBuilder.setStatus(AccountStatusEnum.ACTIVE);

        IAccount userAccount2 =  userAccountBuilder.getResult();

        User user3 = new User(3, "John Smith");
        userAccountBuilder.setAccountId(3);
        userAccountBuilder.setUser(user3);

        IAccount userAccount3 =  userAccountBuilder.getResult();


        // OLD WAY
//        IAbstractTerminalTransactionFactory ATMFactory = new ATMFactory();
//        IAbstractTerminalTransactionFactory CashInFactory = new CashInFactory();
//        IAbstractTerminalTransactionFactory POSFactory = new POSFactory();

        // OLD WAY
//        ITerminal atmTerminal = ATMFactory.createTerminal();
//        ITerminal posTerminal = POSFactory.createTerminal();
//        ITerminal cashInTerminal = CashInFactory.createTerminal();


        // NEW WAY
        // Create Abstraction for Client
        TerminalAbstraction ATMTerminalClient = new TerminalAbstraction(new ATMFactory());
        TerminalAbstraction POSTerminalClient = new TerminalAbstraction(new POSFactory());
        TerminalAbstraction CashInTerminalClient = new TerminalAbstraction(new CashInFactory());

//        // Create Terminals
//        ITerminal atmTerminal = ATMTerminalClient.createTerminal();
//        ITerminal posTerminal = POSTerminalClient.createTerminal();
//        ITerminal cashInTerminal = CashInTerminalClient.createTerminal();


        // Create Transactions
        // OLD WAY
        // User 1 performs transactions
        // ERROR - User 1 tries to withdraw more than the balance
//        ITransaction depositTransaction = ATMFactory.createTransaction(List.of(userAccount1), 100.0, TransactionTypeEnum.WITHDRAWAL);
//        atmTerminal.performTransaction(depositTransaction);

        // NEW WAY
        ATMTerminalClient.initiateTransaction(List.of(userAccount1), 100.0, TransactionTypeEnum.WITHDRAWAL);

        // OLD WAY
        // ERROR - User 1 tries to withdraw negative amount
//        depositTransaction = ATMFactory.createTransaction(List.of(userAccount1), -100.0, TransactionTypeEnum.WITHDRAWAL);
//        atmTerminal.performTransaction(depositTransaction);

        // NEW WAY
        ATMTerminalClient.initiateTransaction(List.of(userAccount1), -100.0, TransactionTypeEnum.WITHDRAWAL);

        // OLD WAY
        // User 1 deposits and withdraws money
//        ITransaction depositTransaction1 = ATMFactory.createTransaction(List.of(userAccount1), 200.0, TransactionTypeEnum.DEPOSIT);
//        atmTerminal.performTransaction(depositTransaction1);

        // NEW WAY
        ATMTerminalClient.initiateTransaction(List.of(userAccount1), 200.0, TransactionTypeEnum.DEPOSIT);

        // OLD WAY
//        ITransaction withdrawalTransaction = ATMFactory.createTransaction(List.of(userAccount1), 50.0, TransactionTypeEnum.WITHDRAWAL);
//        atmTerminal.performTransaction(withdrawalTransaction);

        // NEW WAY
        ATMTerminalClient.initiateTransaction(List.of(userAccount1), 50.0, TransactionTypeEnum.WITHDRAWAL);

        // OLD WAY
        // User 1 exchanges money with User 2
//        ITransaction exchangeTransaction = ATMFactory.createTransaction(List.of(userAccount1, userAccount2), 50.0, TransactionTypeEnum.EXCHANGE);
//        atmTerminal.performTransaction(exchangeTransaction);

        // NEW WAY
        ATMTerminalClient.initiateTransaction(List.of(userAccount1, userAccount2), 50.0, TransactionTypeEnum.EXCHANGE);

        // OLD WAY
        // User 1 deposits money
//        ITransaction depositTransaction2 = CashInFactory.createTransaction(List.of(userAccount1), 100.0, TransactionTypeEnum.DEPOSIT);
//        cashInTerminal.performTransaction(depositTransaction2);

        // NEW WAY
        CashInTerminalClient.initiateTransaction(List.of(userAccount1), 100.0, TransactionTypeEnum.DEPOSIT);

        // OLD WAY
        // User 1 pays via POS
//        ITransaction withdrawalTransaction1 = POSFactory.createTransaction(List.of(userAccount1), 20.0, TransactionTypeEnum.WITHDRAWAL);
//        posTerminal.performTransaction(withdrawalTransaction1);

        // NEW WAY
        POSTerminalClient.initiateTransaction(List.of(userAccount1), 20.0, TransactionTypeEnum.WITHDRAWAL);

        // OLD WAY
        // User 1 tries to pay via POS with insufficient balance
//        ITransaction withdrawalTransaction2 = POSFactory.createTransaction(List.of(userAccount1), 500.0, TransactionTypeEnum.WITHDRAWAL);
//        posTerminal.performTransaction(withdrawalTransaction2);

        // NEW WAY
        POSTerminalClient.initiateTransaction(List.of(userAccount1), 500.0, TransactionTypeEnum.WITHDRAWAL);

        // OLD WAY
        // User 2 deposits money
//        ITransaction depositTransaction3 = CashInFactory.createTransaction(List.of(userAccount2), 200.0, TransactionTypeEnum.DEPOSIT);
//        cashInTerminal.performTransaction(depositTransaction3);

        // NEW WAY
        CashInTerminalClient.initiateTransaction(List.of(userAccount2), 200.0, TransactionTypeEnum.DEPOSIT);

        // OLD WAY
        // User 2 pays via POS
//        ITransaction withdrawalTransaction3 = POSFactory.createTransaction(List.of(userAccount2), 50.0, TransactionTypeEnum.WITHDRAWAL);
//        posTerminal.performTransaction(withdrawalTransaction3);

        // NEW WAY
        POSTerminalClient.initiateTransaction(List.of(userAccount2), 50.0, TransactionTypeEnum.WITHDRAWAL);

        // OLD WAY
        // User 2 tries to exchange via ATM with insufficient balance
//        ITransaction exchangeTransaction1 = ATMFactory.createTransaction(List.of(userAccount2, userAccount1), 500.0, TransactionTypeEnum.EXCHANGE);
//        atmTerminal.performTransaction(exchangeTransaction1);

        // NEW WAY
        ATMTerminalClient.initiateTransaction(List.of(userAccount2, userAccount1), 500.0, TransactionTypeEnum.EXCHANGE);

        // OLD WAY
        // User 3 tries to deposit money
//        ITransaction depositTransaction4 = CashInFactory.createTransaction(List.of(userAccount3), 100.0, TransactionTypeEnum.DEPOSIT);
//        cashInTerminal.performTransaction(depositTransaction4);

        // NEW WAY
        CashInTerminalClient.initiateTransaction(List.of(userAccount3), 100.0, TransactionTypeEnum.DEPOSIT);

        // OLD WAY
        // User 3 tries to withdraw money
//        ITransaction withdrawalTransaction4 = ATMFactory.createTransaction(List.of(userAccount3), 50.0, TransactionTypeEnum.WITHDRAWAL);
//        atmTerminal.performTransaction(withdrawalTransaction4);

        // NEW WAY
        ATMTerminalClient.initiateTransaction(List.of(userAccount3), 50.0, TransactionTypeEnum.WITHDRAWAL);

        // OLD WAY
        // User 3 tries to exchange money
//        ITransaction exchangeTransaction2 = ATMFactory.createTransaction(List.of(userAccount3, userAccount1), 50.0, TransactionTypeEnum.EXCHANGE);
//        atmTerminal.performTransaction(exchangeTransaction2);

        // NEW WAY
        ATMTerminalClient.initiateTransaction(List.of(userAccount3, userAccount1), 50.0, TransactionTypeEnum.EXCHANGE);


        userAccount3.setAccountStatus(AccountStatusEnum.ACTIVE);
        // OLD WAY
        // User 3 tries to deposit money
//        ITransaction depositTransaction5 = CashInFactory.createTransaction(List.of(userAccount3), 100.0, TransactionTypeEnum.DEPOSIT);
//        cashInTerminal.performTransaction(depositTransaction5);

        // NEW WAY
        CashInTerminalClient.initiateTransaction(List.of(userAccount3), 100.0, TransactionTypeEnum.DEPOSIT);

        // OLD WAY
        // User 3 tries to withdraw money
//        ITransaction withdrawalTransaction5 = ATMFactory.createTransaction(List.of(userAccount3), 50.0, TransactionTypeEnum.WITHDRAWAL);
//        atmTerminal.performTransaction(withdrawalTransaction5);

        // NEW WAY
        ATMTerminalClient.initiateTransaction(List.of(userAccount3), 50.0, TransactionTypeEnum.WITHDRAWAL);

        // OLD WAY
        // User 3 tries to exchange money
//        ITransaction exchangeTransaction3 = ATMFactory.createTransaction(List.of(userAccount3, userAccount1), 50.0, TransactionTypeEnum.EXCHANGE);
//        atmTerminal.performTransaction(exchangeTransaction3);

        // NEW WAY
        ATMTerminalClient.initiateTransaction(List.of(userAccount3, userAccount1), 50.0, TransactionTypeEnum.EXCHANGE);

        // OLD WAY
        // User 3 tries to exchange money in POS
//        ITransaction withdrawalTransaction6 = POSFactory.createTransaction(List.of(userAccount3, userAccount1), 5.0, TransactionTypeEnum.WITHDRAWAL);
//        atmTerminal.performTransaction(withdrawalTransaction6);

        // NEW WAY
        ATMTerminalClient.initiateTransaction(List.of(userAccount3, userAccount1), 5.0, TransactionTypeEnum.WITHDRAWAL);
    }
}