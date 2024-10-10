package Laboratory_Work_1_SOLID_Principles;

import Laboratory_Work_1_SOLID_Principles.Enums.AccountStatusEnum;
import Laboratory_Work_1_SOLID_Principles.Interfaces.ILogger;
import Laboratory_Work_1_SOLID_Principles.Terminals.ATMTerminal;
import Laboratory_Work_1_SOLID_Principles.Terminals.CashInTerminal;
import Laboratory_Work_1_SOLID_Principles.Terminals.POSTerminal;
import Laboratory_Work_1_SOLID_Principles.User.User;
import Laboratory_Work_1_SOLID_Principles.User.UserAccount;
import Laboratory_Work_1_SOLID_Principles.Utils.Logging.LoggerImpl;
import Laboratory_Work_1_SOLID_Principles.Enums.TransactionTypeEnum;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Laboratory Work 1 - SOLID Principles");

        // Create Logger
        ILogger logger = new LoggerImpl();

        // Create a new user
        User user1 = new User(1, "John Doe", logger);
        UserAccount userAccount1 = new UserAccount(1, user1, logger);
        System.out.println(userAccount1);
        User user2 = new User(2, "Jane Doe", logger);
        UserAccount userAccount2 = new UserAccount(2, user2, logger);
        System.out.println(userAccount2);

        User user3 = new User(3, "John Smith", logger);
        UserAccount userAccount3 = new UserAccount(3, user3, logger);
        userAccount3.setAccountStatus(AccountStatusEnum.INACTIVE);

        // Create Terminals
        ATMTerminal atmTerminal = new ATMTerminal(logger);
        System.out.println(atmTerminal);
        POSTerminal posTerminal = new POSTerminal(logger);
        System.out.println(posTerminal);
        CashInTerminal cashInTerminal = new CashInTerminal(logger);
        System.out.println(cashInTerminal);

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
    }
}