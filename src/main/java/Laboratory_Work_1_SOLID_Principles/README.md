# Laboratory Work 0 - SOLID Principles


## Author: Gusev Roman

----

## Objectives:

* Implement 2 Principles of SOLID.


[//]: # (## Used Design Patterns:)

[//]: # ()
[//]: # (* DP0)

[//]: # (* PD1)

[//]: # (* ...)


## Implementation

* For this Laboratory Work, I had to implement 2 principles of SOLID. 
I have chosen to implement more than 2 principles.

* The principles that I have implemented are:
    * Single Responsibility Principle
    * Open/Closed Principle
    * Liskov Substitution Principle
    * Interface Segregation Principle
    * Dependency Inversion/Injection Principle

* I decided to adhere to the topic of Terminal Interaction and Transaction / Financial Operations on Accounts and between them as 
well. I have created a simple application that performs operations, such as: 
    * Creating User Entities,
    * Creating User Account Entities,
    * Creating Transaction Entities,
    * Creating Terminal Entities,
    * Depositing money into a User Account,
    * Withdrawing money from a User Account,
    * Transferring money between User Accounts,
    * Logs the financial operations and processes in the application (errors, successful operations, etc.),
    * Validator for Transaction operations.
  
* SRP - Single Responsibility Principle - states that every module, class and function should be responsible
for only one part of the functionality that is provided by the software. More specifically, it is related not only
to the functionality of the class, module or function, but also to the reasons for changing it, since if the
previously mentioned elements have more than one responsibility, then the reasons for changing them will be more than 
one, which is a violation of this principle.

* For this principle, I separated the classes into different packages, each package containing classes that are responsible
for a specific part of the application. More specifically, I have the following packages:
  * Enums - contains the enums used in the application ([AccountStatusEnum](Enums/AccountStatusEnum.java), 
[TransactionTypeEnum](Enums/TransactionTypeEnum.java)). These enums ensure are responsible for different 
types of transactions and account statuses, which reduces the amount of redundant code and helps for maintaining a scalable
structure.
```java
public enum AccountStatusEnum {
    ACTIVE,
    INACTIVE,
    FROZEN
}
```
  * Interfaces - contains the interfaces used in the application ([IAccount](Interfaces/IAccount.java),
[ITransacation](Interfaces/ITransaction.java), [ITerminal](Interfaces/ITerminal.java), etc.). 
These interfaces are responsible for the abstraction of the project, which allows for the implementation of the
Open/Closed Principle and Dependency Inversion Principle. Their main responsibility is to provide a template / contract 
(expecation) for the classes that implement them, that being their only responsibility, and, in particular, these interfaces
are in different unique contexts, which ensures that they are not redundant and that they are not responsible for more 
than one domain.
```java
import java.util.List;
public interface ITerminal {
    void performTransaction(List<IAccount> account, TransactionTypeEnum transactionType, double amount);
}
```
  * Terminals - contains the classes that are responsible for the terminal operations ([ATMTerminal](Terminals/ATMTerminal.java), 
[CashInTerminal](Terminals/CashInTerminal.java), [POSTerminal](Terminals/POSTerminal.java)). These classes have the
responsibility to provide the terminal operations, such as Deposit, Withdraw, Exchange between 2 user accounts. They
encapsulate the Transactions that are processed by the Terminals. For example, in the class POSTerminal, that implements
ITerminal Interface, the responsibility is to perform a transaction and log the progress, but all the logic behind the
Transaction is encapsulated in the ITransaction implementation, which ensures the SRP is respected. 
```java
public class POSTerminal implements ITerminal {
    private ILogger logger;
    
    public POSTerminal(ILogger logger) {
        this.logger = logger;
    }
    
    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
        if (transactionType != TransactionTypeEnum.WITHDRAWAL) {
          logger.infoLog("Invalid Transaction Type");
          return;
        }
        int accountId = accounts.getFirst().getAccountId();
        logger.infoLog("Initiated POS Transaction from Account" + accountId + " on Amount " + amount);
        ITransaction withdrawalTransaction = new WithdrawalTransaction(accounts.getFirst(), logger, amount, new TransactionValidator(logger));
        withdrawalTransaction.executeTransaction();
        logger.infoLog("Closed POS Transaction from Account" + accountId + " on Amount " + amount);
    }
}
```
* Transactions - contains the classes that are responsible for the transaction operations ([DepositTransaction](Transactions/DepositTransaction.java),
[ExchangeTransaction](Transactions/ExchangeTransaction.java), [WithdrawalTransaction](Transactions/WithdrawalTransaction.java)).
These classes are responsible for the execution of the transactions and the manipulation with the User Accounts.
They encapsulate the logic behind the transactions and ensure that the transactions are executed. For example, in the 
class WithdrawalTransaction, the responsibility is to execute the Withdrawal Transaction and validate if the Transaction
is possible, but the logic behind the validation is encapsulated in the ITransactionValidator implementation.
```java
public class WithdrawalTransaction implements ITransaction {
    private IAccount account;
    private double amount;
    private ILogger logger;
    private ITransactionValidator validator;

    public WithdrawalTransaction(IAccount account, ILogger logger, double amount, ITransactionValidator validator) {
        this.account = account;
        this.logger = logger;
        this.amount = amount;
        this.validator = validator;
    }

    @Override
    public void executeTransaction() {
        int userAccountId = this.account.getAccountId();
        this.logger.infoLog("Initiated Withdrawal Transaction for Account " + userAccountId);

        if (!this.validator.validateTransaction(this.account, this.amount)) {
            this.logger.errorLog("Withdrawal Transaction failed for Account " + userAccountId);
            return;
        }

        this.account.withdraw(this.amount);
        double newBalance = this.account.getBalance();
        this.logger.infoLog("Withdrew " + this.amount + " from account " + userAccountId + " - New balance: " + newBalance);
    }
}
```
* User - contains the classes that are responsible for the User operations ([User](User/User.java), [UserAccount](User/UserAccount.java)).
These classes are responsible for the User and User Account entities. They encapsulate the logic behind the User 
and User Account operations, such as creating a User Account, depositing money, withdrawing money, etc. 
```java
public class UserAccount implements IAccount {
    private int accountId;
    private User user;
    private double balance;
    private ILogger logger;
    private AccountStatusEnum status;

    public UserAccount(int accountId, User user, ILogger logger) {
        this.accountId = accountId;
        this.user = user;
        this.balance = 0;
        this.logger = logger;
        this.status = AccountStatusEnum.ACTIVE;
        this.logger.infoLog("Account " + this.accountId + " with status " + this.status + " created for user "
                + this.user.getName() + " (" + this.user.getUserId() + ")");
    }

    @Override
    public int getAccountId() {
        this.logger.infoLog("Requested account ID for account " + this.accountId);
        return this.accountId;
    }

    @Override
    public void deposit(double amount) {
        this.balance += amount;
    }

    @Override
    public double withdraw(double amount) {
        this.balance -= amount;
        return amount;
    }

    @Override
    public double getBalance() {
        this.logger.infoLog("Checking balance for account " + this.accountId);
        return this.balance;
    }

    @Override
    public AccountStatusEnum getAccountStatus() {
        this.logger.infoLog("Checking account status for account " + this.accountId);
        return this.status;
    }

    @Override
    public void setAccountStatus(AccountStatusEnum status) {
        this.status = status;
        this.logger.infoLog("Account " + this.accountId + " status changed to " + status);
    }
}
```
* Utils - contains the classes that are responsible for the Utility Operations:
  * [Logging](Utils/Logging),
  * [Validators](Utils/Validators).
* These classes are responsible for different tasks.
    * Logging - is responsible for logging the operations and processes in the application. It is used in the Terminals and 
    Transactions to log the progress of the operations.
    * Validators - is responsible for validating the transactions. It is used in the Transactions to validate if the 
    transaction is possible and, for example, if the User Account has enough balance to perform the transaction.

* OCP - Open/Closed Principle - states that each module, class and function (software pieces) should
be open for extension, but closed for modification. In other words, the behavior of each component
should be able to be extended, but not modified, which means that the existing code should not be changed
when new features are added, unless it is necessary to fix bugs. This principle is implemented in the application 
by using the Interfaces or Abstract Classes. For example, the ITerminal Interface is open for extension, because
it can be implemented by different Terminals, such as ATMTerminal, POSTerminal or CashInTerminal, but it is closed
for modification, because the ITerminal Interface does not change when new Terminals are added. This ensures that the
new Terminals can be added without changing the code that is dependent on the ITerminal Interface, since it will 
work with any class that implements this interface and calls the method that is present in all concrete implementations.
At the same time, by the use of TransactionFactory, we ensure that even if new Transaction Types are added, the code
for the Terminals will not change, since the TransactionFactory will be responsible for creating the new Transactions.
```java
public interface ITerminal {
  void performTransaction(List<IAccount> account, TransactionTypeEnum transactionType, double amount);
}
```
```java
public class POSTerminal implements ITerminal {
  private ILogger logger;
  private TransactionFactory transactionFactory;

  public POSTerminal(ILogger logger, TransactionFactory transactionFactory) {
    this.logger = logger;
    this.transactionFactory = transactionFactory;
  }

  @Override
  public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
    if (transactionType != TransactionTypeEnum.WITHDRAWAL) {
      logger.errorLog("Invalid transaction Type: " + transactionType);
      return;
    }

    try {
      ITransaction transaction = transactionFactory.createTransaction(transactionType, accounts, amount);
      int accountId = accounts.getFirst().getAccountId();
      logger.infoLog("Initiated POS Transaction from Account " + accountId + " on Amount " + amount);
      transaction.executeTransaction();
      logger.infoLog("Closed POS Transaction from Account " + accountId + " on Amount " + amount);
    } catch (IllegalArgumentException e) {
      logger.errorLog("Failed POS Transaction: " + e.getMessage());
    }
  }
}
```
```java
public class CashInTerminal implements ITerminal {
  private ILogger logger;
  private TransactionFactory transactionFactory;

  public CashInTerminal(ILogger logger, TransactionFactory transactionFactory) {
    this.logger = logger;
    this.transactionFactory = transactionFactory;
  }

  @Override
  public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
    if (transactionType != TransactionTypeEnum.DEPOSIT) {
      logger.errorLog("Invalid transaction Type: " + transactionType);
      return;
    }

    try {
      ITransaction transaction = transactionFactory.createTransaction(transactionType, accounts, amount);
      int userAccountId = accounts.getFirst().getAccountId();
      this.logger.infoLog("Initiated Cash-In Transaction to Account" + userAccountId + " on Amount " + amount);
      transaction.executeTransaction();
      this.logger.infoLog("Closed Cash-In Transaction to Account" + userAccountId + " on Amount " + amount);
    } catch (IllegalArgumentException e) {
      logger.errorLog("Invalid transaction Type: " + e.getMessage());
    }
  }
}
```

* LSP - Liskov Substitution Principle - states that objects of a class should be replaceable with instances of its subclasses
without breaking the code that uses the parent class. In other words, the subclasses should be able to replace the parent
class without affecting the behavior of the application. In my case, I have Validator classes for Account Status and
Transaction Operations. AccountStatusValidator is a class that implements IAccountStatusValidator and has the responsibility
to validate the Account Status. At the same, TransactionValidator is a class that implements ITransactionValidator and extends
the AccountStatusValidator, which means that it is a subclass of AccountStatusValidator, and it should be able to replace
the AccountStatusValidator without affecting the behavior of the application. Since the TransactionValidator only adds new
functionality to the AccountStatusValidator, it will not break the code and the logic of the validation of the operations.
```java
public interface IAccountStatusValidator {
    boolean validateAccountStatus(IAccount account);
}
```
```java
public interface ITransactionValidator extends IAccountStatusValidator {
  boolean validateSufficientFunds(IAccount account, double amount);
  boolean validateTransaction(IAccount account, double amount);
}
```
```java
public class AccountStatusValidator implements IAccountStatusValidator {
    private final ILogger logger;

    public AccountStatusValidator(ILogger logger) {
        this.logger = logger;
    }

    @Override
    public boolean validateAccountStatus(IAccount userAccount) {
        AccountStatusEnum userAccountStatus = userAccount.getAccountStatus();
        int userAccountId = userAccount.getAccountId();
        if (userAccountStatus != AccountStatusEnum.ACTIVE) {
            logger.warningLog("Account " + userAccountId + " is " + userAccountStatus +
                    " and cannot perform transactions");
            return false;
        }
        logger.infoLog("Account " + userAccountId + " is " + userAccountStatus);
        return true;
    }
}
```
```java
public class TransactionValidator extends AccountStatusValidator implements ITransactionValidator {
    public TransactionValidator(ILogger logger) {
        super(logger);
    }

    @Override
    public boolean validateSufficientFunds(IAccount userAccount, double amount) {
        int userAccountId = userAccount.getAccountId();
        double userAccountBalance = userAccount.getBalance();
        if (userAccountBalance < amount) {
            super.getLogger().warningLog("Insufficient funds in account " + userAccountId +
                    ". Available: " + userAccountBalance + ", required: " + amount);
            return false;
        }
        super.getLogger().infoLog("Account " + userAccountId + " has sufficient funds");
        return true;
    }

    @Override
    public boolean validateTransaction(IAccount account, double amount) {
        return this.validateAccountStatus(account) && this.validateSufficientFunds(account, amount);
    }
}
```
* In the following code snippet, the AccountStatusValidator is being used in the TransactionFactory class in the DEPOSIT transaction
initialization. The AccountStatusValidator is used to validate the Account Status before the Deposit Transaction is executed.
Since the TransactionValidator extends the AccountStatusValidator and inherits the behavior of the AccountStatusValidator, 
it may be used instead of the original AccountStatusValidator, and it will perform the same validation that is inherited.
```java
public class TransactionFactory {
  private ILogger logger;

  public TransactionFactory(ILogger logger) {
    this.logger = logger;
  }

  public ITransaction createTransaction(TransactionTypeEnum transactionType, List<
          IAccount> accounts, double amount) {
    return switch (transactionType) {
      case DEPOSIT ->
              new DepositTransaction(accounts.getFirst(), logger, amount, new AccountStatusValidator(logger));
      case EXCHANGE ->
              new ExchangeTransaction(accounts.getFirst(), accounts.getLast(), logger, amount, new TransactionValidator(logger));
      case WITHDRAWAL ->
              new WithdrawalTransaction(accounts.getFirst(), logger, amount, new TransactionValidator(logger));
    };
  }
}
```

* ISP - Interface Segregation Principle - states that a class should not be forced to implement interfaces or methods that 
it does not need or use. In other words, if the purpose of a class is to perform a specific operation, then it should not
implement interfaces or methods that are not related to that operation. In the context of my application, this principle is 
respected in the same example as above, where DepositTransaction requires a validation of the Account Status, but it does not
require the validation of the Transaction itself (Balance amount is not an obstacle for the transaction to be made). In this 
case, I created a new Interface IAccountStatusValidator that is implemented by the AccountStatusValidator class, which 
validates the Account Status, while another Interface ITransactionValidator extends the IAccountStatusValidator and adds
new methods for validating the Transaction itself, that is required for the WithdrawalTransaction and ExchangeTransaction. 
Should be taken in account that TransactionValidator should validate the Transaction by 2 factors: Account Status and
its Balance.
```java
public interface IAccountStatusValidator {
    boolean validateAccountStatus(IAccount account);
}
```
```java
public interface ITransactionValidator extends IAccountStatusValidator {
  boolean validateSufficientFunds(IAccount account, double amount);
  boolean validateTransaction(IAccount account, double amount);
}
```

* DIP - Dependency Inversion/Injection Principle - states that high-level modules should not depend on low-level modules, but 
both should depend on abstractions. In other words, the classes behavior should be defined or, in other words, should 
be guided by the interfaces or abstract classes, not by the concrete implementations. This principle is usually respected
by the usage of the Interfaces or Abstract Classes, which are then used in constructors or methods, instead of the concrete
classes, thus enabling injection of the concrete examples, since those methods or constructors do not need to know about the
details of the concrete implementations. In the application presented here, I have used in the Terminal implementations
exactly through the usage of Transaction factory, that will provide the appropriate Transaction implementation, thus 
leaving Terminal classes unaware of details about Transaction and, as a result, respecting the DIP, since it will depend
only on the abstraction.
```java
public class POSTerminal implements ITerminal {
    private ILogger logger;
    private TransactionFactory transactionFactory;

    public POSTerminal(ILogger logger, TransactionFactory transactionFactory) {
        this.logger = logger;
        this.transactionFactory = transactionFactory;
    }

    @Override
    public void performTransaction(List<IAccount> accounts, TransactionTypeEnum transactionType, double amount) {
        if (transactionType != TransactionTypeEnum.WITHDRAWAL) {
            logger.errorLog("Invalid transaction Type: " + transactionType);
            return;
        }

        try {
            ITransaction transaction = transactionFactory.createTransaction(transactionType, accounts, amount);
            int accountId = accounts.getFirst().getAccountId();
            logger.infoLog("Initiated POS Transaction from Account " + accountId + " on Amount " + amount);
            transaction.executeTransaction();
            logger.infoLog("Closed POS Transaction from Account " + accountId + " on Amount " + amount);
        } catch (IllegalArgumentException e) {
            logger.errorLog("Failed POS Transaction: " + e.getMessage());
        }
    }
}
```
```java
public class TransactionFactory {
    private ILogger logger;

    public TransactionFactory(ILogger logger) {
        this.logger = logger;
    }

    public ITransaction createTransaction(TransactionTypeEnum transactionType, List<
            IAccount> accounts, double amount) {
        return switch (transactionType) {
            case DEPOSIT ->
                    new DepositTransaction(accounts.getFirst(), logger, amount, new AccountStatusValidator(logger));
            case EXCHANGE ->
                    new ExchangeTransaction(accounts.getFirst(), accounts.getLast(), logger, amount, new TransactionValidator(logger));
            case WITHDRAWAL ->
                    new WithdrawalTransaction(accounts.getFirst(), logger, amount, new TransactionValidator(logger));
        };
    }
}
```
## Conclusions / Screenshots / Results
In conclusion, I want to emphasize that through the implementation of the SOLID principles, the application has become
more scalable and maintainable. Through the separation of the classes by their responsibilities, the application has become
more modular, easier to understand and navigate, and the changes in the future may be easier to implement. At the same
time, by the use of the Interfaces, the application has become more flexible, since the classes are based on abstract definitions
of the classes rather than on concrete implementations, making them extensible. Also, by the use of Inheritance, application
has become much more powerful, since the classes can be replaced by their subclasses without affecting the behavior of the
software.
