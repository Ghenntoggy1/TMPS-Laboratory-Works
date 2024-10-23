# Laboratory Work 1 - Creational Design Patterns


## Author: Gusev Roman

----

## Objectives:

* Study and understand the Creational Design Patterns.
* Choose a domain, define its main classes/models/entities and choose the appropriate instantiation 
mechanisms.
* Implement 3 Creational Design Patterns in the project.


## Theory:
In software engineering, the creational design patterns are the general solutions that 
deal with object creation, trying to create objects in a manner suitable to the situation. The basic 
form of object creation could result in design problems or added complexity to the design. Creational 
design patterns solve this problem by optimizing, hiding or controlling the object creation.

Some examples of this kind of design patterns are:
* Singleton
* Builder
* Prototype
* Factory Method
* Abstract Factory

### Singleton
Singleton is a creational design pattern, which ensures that only one object of its kind exists and 
provides a single point of access to it for any other code. In other words, it ensures that a class has only one instance, 
while providing a global access point to this instance.

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/singleton/structure-en.png" alt="Singleton Pattern Diagram">
</p>

#### Participants
* **Singleton** - The Singleton class declares the static method getInstance that returns the same instance of its own class.
The Singleton’s constructor should be hidden from the client code. Calling the getInstance method should be the only way of 
getting the Singleton object.

#### Applicability
* Use the Singleton pattern when a class in your program should have just a single instance available to all clients; 
for example, a single database object shared by different parts of the program.
* Use the Singleton pattern when you need stricter control over global variables.
* Use the Singleton pattern when there must be exactly one instance of a class, and it must be accessible to clients
  from a well-known access point.
* Use the Singleton pattern when the sole instance should be extensible by subclassing, and clients should be
  able to use an extended instance without modifying their code

### Builder
Builder is a creational design pattern that lets you construct complex objects step by step. The pattern allows you to 
produce different types and representations of an object using the same construction code. In other words, it separates 
the construction of a complex object from its representation so that the same construction process can create different 
representations.

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/builder/structure.png" alt="Builder Pattern Diagram">
</p>

#### Participants
* **Builder** - The Builder interface declares product construction steps that are common to all types of builders.
* **ConcreteBuilder** - Concrete Builders provide different implementations of the construction steps. Concrete builders may produce products that don’t follow the common interface.
* **Product** - Products are resulting objects. Products constructed by different builders don’t have to belong to the same class hierarchy or interface.
* **Director** - The Director class defines the order in which to call construction steps, so you can create and reuse specific configurations of products.
* **Client** - The Client must associate one of the builder objects with the director. Usually, it’s done just once, via parameters of the director’s constructor. Then the director uses that builder object for all further construction. However, there’s an alternative approach for when the client passes the builder object to the production method of the director. In this case, you can use a different builder each time you produce something with the director.

#### Applicability
* Use the Builder pattern to get rid of a “telescoping constructor”.
* Use the Builder pattern when you want your code to be able to create different representations of some product 
(for example, stone and wooden houses).
* Use the Builder to construct Composite trees or other complex objects.
* Use the Builder pattern when the algorithm for creating a complex object should be independent of the parts that
  make up the object and how they're assembled.
* Use the Builder pattern when the construction process must allow different representations for the object that's
  constructed.

### Abstract Factory
Abstract Factory is a creational design pattern that lets you produce families of related objects without specifying 
their concrete classes. In other words, it provides an interface for creating families of related or dependent objects without specifying
their concrete classes.

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/abstract-factory/structure.png" alt="Abstract Factory Pattern Diagram">
</p>

#### Participants
* **Abstract Products** - Abstract Products declare interfaces for a set of distinct but related products which make up a product family.
* **Concrete Products** - Concrete Products are various implementations of abstract products, grouped by variants. Each abstract product (chair/sofa) must be implemented in all given variants (Victorian/Modern).
* **Abstract Factory** - The Abstract Factory interface declares a set of methods for creating each of the abstract products.
* **Concrete Factories** - Concrete Factories implement creation methods of the abstract factory. Each concrete factory corresponds to a specific variant of products and creates only those product variants.
* **Client** - The Client works with factories and products only through abstract types. Although concrete factories instantiate concrete products, signatures of their creation methods must return corresponding abstract products. This way the client code that uses a factory doesn’t get coupled to the specific variant of the product it gets from a factory. The Client can work with any concrete factory/product variant, as long as it communicates with their objects via abstract interfaces.

#### Applicability
* Use the Abstract Factory when your code needs to work with various families of related products, but you don’t want it to depend on the concrete classes of those products—they might be unknown beforehand or you simply want to allow for future extensibility.
* Consider implementing the Abstract Factory when you have a class with a set of Factory Methods that blur its primary responsibility.
* Use the Abstract Factory pattern when a system should be independent of how its products are created, composed, and represented.
* Use the Abstract Factory when a system should be configured with one of multiple families of products.
* Use the Abstract Factory when a family of related product objects is designed to be used together, and you need to
  enforce this constraint.
* Use the Abstract Factory when you want to provide a class library of products, and you want to reveal just their
  interfaces, not their implementations.

### Factory Method
Factory Method is a creational design pattern that provides an interface for creating objects in a superclass, 
but allows subclasses to alter the type of objects that will be created. In other words, it defines an interface for 
creating an object, but let subclasses decide which class to instantiate. 
Factory Method lets a class defer instantiation to subclasses.

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/factory-method/structure.png?id=4cba0803f42517cfe8548c9bc7dc4c9b" alt="Factory Method Pattern Diagram">
</p>

#### Participants
* **Product** - Product declares the interface, which is common to all objects that can be produced by the creator and its subclasses.
* **Concrete Products** - Concrete Products are different implementations of the product interface.
* **Creator** - The Creator class declares the factory method that returns new product objects. It’s important that the 
return type of this method matches the product interface. You can declare the factory method as abstract to force all 
subclasses to implement their own versions of the method. As an alternative, the base factory method can return some default product type.
Note, despite its name, product creation is not the primary responsibility of the creator. Usually, the creator class 
already has some core business logic related to products. The factory method helps to decouple this logic from the 
concrete product classes.
* **Concrete Creators** - Concrete Creators override the base factory method, so it returns a different type of product. 
Note that the factory method does not have to create new instances all the time. It can also return existing objects 
from a cache, an object pool, or another source.

#### Applicability
* Use the Factory Method pattern when a class can't anticipate the class of objects it must create.
* Use the Factory Method pattern when a class wants its subclasses to specify the objects it creates
* Use the Factory Method pattern when classes delegate responsibility to one of several helper subclasses, and you want to
localize the knowledge of which helper subclass is the delegate.

### Prototype
Prototype Creational Design Pattern - Prototype is a creational design pattern that lets you copy existing objects 
without making your code dependent on their classes. In other words, it allows us to specify the kinds of objects to 
create using a prototypical instance, and create new objects by copying this prototype.

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/prototype/structure.png" alt="Prototype Pattern Diagram">
</p>

#### Participants
* **Prototype** - The Prototype interface declares the cloning methods. In most cases, it’s a single clone method.
* **Concrete Prototype** - The Concrete Prototype class implements the cloning method. In addition to copying the 
original object’s data to the clone, this method may also handle some edge cases of the cloning process related to 
cloning linked objects, untangling recursive dependencies, etc.
* **Client** - The Client can produce a copy of any object that follows the prototype interface.

#### Applicability
* Use the Prototype pattern when a system should be independent of how its products are
  created, composed, and represented; and
* when the classes to instantiate are specified at run-time, for example, by dynamic loading; or
* to avoid building a class hierarchy of factories that parallels the class hierarchy of
  products; or
* when instances of a class can have one of only a few different combinations of
  state. It may be more convenient to install a corresponding number of prototypes
  and clone them rather than instantiating the class manually, each time with the
  appropriate state.


[//]: # (## Used Design Patterns:)

[//]: # ()
[//]: # (* DP0)

[//]: # (* PD1)

[//]: # (* ...)


## Implementation

* For this Laboratory Work, I had to implement 3 Creational Design Patterns. 
I have chosen to implement Abstract Factory, Builder and Singleton Patterns.

* The principles that I have implemented are:
    * Abstract Factory Pattern
    * Builder Pattern
    * Singleton Pattern

* I decided to build upon the topic of Terminal Interaction and Transaction / Financial Operations on Accounts and between them as 
well. I have used previous Laboratory Work that performs operations, such as: 
    * Creating User Entities,
    * Creating User Account Entities,
    * Creating Transaction Entities,
    * Creating Terminal Entities,
    * Depositing money into a User Account,
    * Withdrawing money from a User Account,
    * Transferring money between User Accounts,
    * Logs the financial operations and processes in the application (errors, successful operations, etc.),
    * Validator for Transaction operations.

#### Builder Pattern
* Builder Creational Design Pattern - lets us construct complex objects step by step. The pattern allows you to 
produce different types and representations of an object using the same construction code. It suggests that we extract 
the object construction code out of its own class and move it to separate objects called builders. The pattern organizes 
object construction into a set of steps. To create an object, we execute a series of 
these steps on a builder object. The important part is that we don’t need to call all the steps. We can call only 
those steps that are necessary for producing a particular configuration of an object.

* For this pattern, I decided to implement a Builder for the IUserAccount concrete implementation and ITransaction
concrete implementation. The IUserAccountBuilder concrete implementation is responsible for creating a User Account, 
while the ITransactionBuilder implementation is responsible for each Transaction type (Deposit, Withdrawal, Exchange)
creation. 
  * [IBuilder](Interfaces/BuilderInterfaces/IBuilder.java) - is an interface that contains the reset method, which is responsible for resetting the Builder object.
```java
public interface IBuilder {
  void reset();
}
```
  * [ITransactionBuilder](Interfaces/BuilderInterfaces/ITransactionBuilder.java) - is an interface that contains the methods for setting the Transaction fields, such as List of 
Accounts, Amount, and, as specified in the Builder Pattern, the method that will return the formed Transaction.
```java
public interface ITransactionBuilder extends IBuilder {
    void setAccounts(List<IAccount> accounts);
    void setAmount(Double amount);
    ITransaction getResult();
}
```
  * [IUserAccountBuilder](Interfaces/BuilderInterfaces/IUserAccountBuilder.java) - is an interface that contains the methods for setting the User Account fields, such as AccountID, 
User Entity, Balance, Account Status, and, as specified in the Builder Pattern, the method that will return the formed 
User Account.
```java
public interface IUserAccountBuilder extends IBuilder{
    void setAccountId(int accountId);
    void setUser(IUser user);
    void setBalance(Double balance);
    void setStatus(AccountStatusEnum status);
    IAccount getResult();
}
```
  * Concrete Builder Implementations - are the specific Builders that are constructing the User Account and Transaction 
objects by implementing the IUserAccountBuilder and ITransactionBuilder interfaces and providing methods for setting the
fields of the objects, as well as returning the built object. At the same time, the Builder classes are specifying the
order of construction of the objects, as well as the fields that have to be set.
  * [UserAccountBuilder](Utils/Builders/UserAccountBuilder.java) - is a class that implements the IUserAccountBuilder interface 
and is responsible for creating the User Account object.
```java
public class UserAccountBuilder implements IUserAccountBuilder {
    private int accountId;
    private IUser user;
    private Double balance;
    private AccountStatusEnum status;

    public UserAccountBuilder() {
        this.reset();
    }
    
    @Override
    public void reset() {
        this.accountId = 0;
        this.user = null;
        this.balance = null;
        this.status = null;
    }

    @Override
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public void setUser(IUser user) {
        this.user = user;
    }

    @Override
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public void setStatus(AccountStatusEnum status) {
        this.status = status;
    }

    @Override
    public IAccount getResult() {
        IAccount userAccount = new UserAccount(this.accountId, this.user, this.balance, this.status);
        this.reset();
        return userAccount;
    }
}
```
This class provides the possibility to build the UserAccount as in the following manner:
```java
// Create User Entity
User user1 = new User(1, "John Doe");

// Instantiate UserAccountBuilder
IUserAccountBuilder userAccountBuilder = new UserAccountBuilder();

// Build User Account
userAccountBuilder.setAccountId(1);
userAccountBuilder.setUser(user1);
userAccountBuilder.setBalance(0.0);
userAccountBuilder.setStatus(AccountStatusEnum.ACTIVE);

// Retrieve the User Account
userAccountBuilder.getResult();

// Alternative creation
User user2 = new User(2, "John Smith");
userAccountBuilder.setAccountId(2);
userAccountBuilder.setUser(user2);

// Retrieve the User Account
IAccount userAccount3 =  userAccountBuilder.getResult();
```
Even though the UserAccountBuilder builds an incomplete User Account, it is possible to return a fully functional User Account,
with some limitations (in this case, it has balance=0.0 and status=INACTIVE).
```java
public class UserAccount implements IAccount {
    private int accountId;
    private IUser user;
    private Double balance;
    private ILogger logger;
    private AccountStatusEnum status;

    public UserAccount(int accountId, IUser user, Double balance, AccountStatusEnum status) {
        this.accountId = accountId;
        this.user = user;
        this.balance = balance;
        if (balance == null) {
            this.balance = 0.0;
        }
        this.status = status;
        if (this.status == null) {
            this.status = AccountStatusEnum.INACTIVE;
        }
        this.logger = LoggerImpl.getInstance();
        this.logger.infoLog("Account " + this.accountId + " with status " + this.status + " created for user "
                + this.user.getName() + " (" + this.user.getUserId() + ")");
    }
}
```

* [WithdrawalTransactionBuilder](Utils/Builders/WithdrawalTransactionBuilder) - is a class that implements the ITransactionBuilder interface
  and is responsible for creating the ITransaction implementation object - in this case, WithdrawalTransaction.
```java
public class WithdrawalTransactionBuilder implements ITransactionBuilder {
    private List<IAccount> accounts;
    private Double amount;
  
    public WithdrawalTransactionBuilder() {
        this.reset();
    }
  
    @Override
    public void reset() {
        this.accounts = null;
        this.amount = 0.0;
    }
  
    @Override
    public void setAccounts(List<IAccount> accounts) {
        this.accounts = accounts;
    }
  
    @Override
    public void setAmount(Double amount) {
        this.amount = amount;
    }
  
    @Override
    public ITransaction getResult() {
        ITransaction transaction = new WithdrawalTransaction(this.accounts, this.amount);
        this.reset();
        return transaction;
    }
}
```
This class provides the possibility to build the WithdrawalTransaction as in the following manner. The following method
is a part of a class that was used for implementing Abstract Factory Pattern and will be described further in the report:
```java
public ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType) {
    if (transactionType != TransactionTypeEnum.WITHDRAWAL) {
        return null;
    }
    WithdrawalTransactionBuilder withdrawalTransactionBuilder = new WithdrawalTransactionBuilder();
    withdrawalTransactionBuilder.setAccounts(accounts);
    withdrawalTransactionBuilder.setAmount(amount);
    return withdrawalTransactionBuilder.getResult();
}
```
Even though the UserAccountBuilder builds an incomplete WithdrawalTransaction, it will log the error and will not be
able to produce the object further.
```java
public class WithdrawalTransaction implements ITransaction {
    private List<IAccount> account;
    private Double amount;
    private ILogger logger;
    private ITransactionValidator validator;
    
    public WithdrawalTransaction(List<IAccount> account, double amount) {
        this.logger = LoggerImpl.getInstance();
        this.account = account;
        if (account == null) {
            this.logger.errorLog("Withdrawal Transaction failed due to invalid account - Account is null");
            throw new IllegalArgumentException("Invalid accounts");
        }
        this.amount = amount;
        if (amount <= 0) {
            this.logger.errorLog("Withdrawal Transaction failed due to invalid amount - Amount is less than or equal to 0");
            throw new IllegalArgumentException("Invalid amount");
        }
        this.validator = TransactionValidator.getInstance();
    }
}
```

  * [DepositTransactionBuilder](Utils/Builders/DepositTransactionBuilder.java) - is a class that implements the 
ITransactionBuilder interface and is responsible for creating the ITransaction implementation object - in this case,
DepositTransaction.
```java
public class DepositTransactionBuilder implements ITransactionBuilder {
    private List<IAccount> accounts;
    private Double amount;

    public DepositTransactionBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.accounts = null;
        this.amount = 0.0;
    }

    @Override
    public void setAccounts(List<IAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public ITransaction getResult() {
        ITransaction transaction = new DepositTransaction(this.accounts, this.amount);
        this.reset();
        return transaction;
    }
}
```
  * [ExchangeTransactionBuilder](Utils/Builders/ExchangeTransactionBuilder.java) - is a class that implements the
ITransactionBuilder interface and is responsible for creating the ITransaction implementation object - in this case, 
ExchangeTransaction.
```java
public class ExchangeTransactionBuilder implements ITransactionBuilder {
    private List<IAccount> accounts;
    private Double amount;

    public ExchangeTransactionBuilder() {
        this.reset();
    }

    @Override
    public void reset() {
        this.accounts = null;
        this.amount = 0.0;
    }

    @Override
    public void setAccounts(List<IAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public ITransaction getResult() {
        ITransaction transaction = new ExchangeTransaction(this.accounts, this.amount);
        this.reset();
        return transaction;
    }
}
```
For both DepositTransaction and ExchangeTransaction, the process is similar to the WithdrawalTransaction, but the
product they return is different.

Thus, the Builder Pattern is implemented in the application, thus providing a way to create complex objects step by step,
by using the Builder classes that are responsible for the construction of the objects. It may be possible to use a new
class called Director that will hold specific building methods for the objects instead of allowing the client operate
with those Builders directly. However, in this case, the Builders are simple enough to be used directly by the client.

#### Singleton Pattern

  * Singleton Creational Design Pattern - lets us ensure that a class has only one instance, while providing a global 
access point to this instance. Just like a global variable, the Singleton pattern lets us access some object from 
anywhere in the program. However, it also protects that instance from being overwritten by other code, since the implementation
requires that classes that adhere to Singleton pattern have a private constructor and a static method that returns the
instance of the class.

  * For this pattern, I decided to implement Singleton for ILogger concrete Implementation, ITransactionValidator and
IAccountStatusValidator concrete Implementations.

  * [ILogger](Interfaces/ILogger.java) - is an interface that contains the methods for logging information, errors, 
and warnings.
```java
public interface ILogger {
    void infoLog(String message);
    void errorLog(String message);
    void warningLog(String message);
}
```

  * [ITransactionValidator](Interfaces/ITransactionValidator.java) - is an interface that contains the method for validating
the Transaction operations.
```java
public interface ITransactionValidator extends IAccountStatusValidator {
    boolean validateSufficientFunds(IAccount account, double amount);
    boolean validateTransaction(IAccount account, double amount);
}
```

  * [IAccountStatusValidator](Interfaces/IAccountStatusValidator.java) - is an interface that contains the method for validating
the Account Status.
```java
public interface IAccountStatusValidator {
    boolean validateAccountStatus(IAccount account);
}
```

  * Concrete Implementations with Singleton Pattern - are the previously defined implementations (see [Lab-1](../Laboratory_Work_1_SOLID_Principles/README.md))
that I extended (in compliance with OCP from SOLID) with the Singleton Pattern. The Singleton pattern is implemented 
via the getInstance() method that returns the instance of the class. The constructor of the class is private, so it is
accessible only via the getInstance() method, that ensures that only one instance will be passed across the application.

  * [LoggerImpl](Utils/Logging/LoggerImpl.java) - is a class that implements the ILogger interface and is 
responsible for logging the information, errors, and warnings. In this class, the constructor is private, there is a 
private field that holds the instance of the LoggerImpl class, and the getInstance() method that returns the instance of
the LoggerImpl class by checking if the instance is null. If it is null, it creates a new instance, otherwise, it returns
the field that references the instance of the LoggerImpl class. Since Logger is an object that does not change the state
of the application and itself, it is safe to use Singleton Pattern for this class.

```java
public class LoggerImpl implements ILogger {
    private static LoggerImpl instance;
    private final Logger logger;

    private LoggerImpl() {
        this.logger = Logger.getLogger(LoggerImpl.class.getName());
        this.logger.setLevel(Level.ALL);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new LogFormatter());
        consoleHandler.setLevel(Level.ALL);

        try {

            FileHandler fileHandler = new FileHandler("src/main/java/Laboratory_Work_2_Creational_Patterns/Utils/Logging/Logs/logs.log", true);
            fileHandler.setFormatter(new LogFormatter());
            fileHandler.setLevel(Level.ALL);
            this.logger.addHandler(fileHandler);
        } catch (IOException e) {
            System.out.println("Failed to Initialize File Handler for Logging: " + e.getMessage());
            this.errorLog("Failed to Initialize File Handler for Logging: " + e.getMessage());
        }

        this.logger.addHandler(consoleHandler);
        this.logger.setUseParentHandlers(false);
    }

    // Singleton Logger
    public static LoggerImpl getInstance() {
        if (instance == null) {
            instance = new LoggerImpl();
            instance.infoLog("Logger Initialized using Singleton Pattern");
        }
        else {
            instance.infoLog("Logger already initialized - Returning existing instance");
        }
        return instance;
    }

    // Helper method to get calling class and method
    private String getCallingClassAndMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // Index 0: getStackTrace(), Index 1: getCallingClassAndMethod(), Index 2: this method (infoLog, etc.)
        // Index 3: Caller method
        StackTraceElement caller = stackTrace[3];
        return caller.getClassName() + "::" + caller.getMethodName();
    }
    ...
}
```
This approach allows us to use the logger across the application in the following manner:
```java
ILogger logger = LoggerImpl.getInstance();
```
As it may be noticed, the method is called, not the constructor, and the instance is created and returned, in case it was never created 
before at run-time, otherwise - returned the instance that was created already.

  * [TransactionValidator](Utils/Validators/TransactionValidator.java) - is a class that implements the ITransactionValidator
interface and is responsible for validating the Transaction operations. In this class, the constructor is private, there is a
private field that holds the instance of the TransactionValidator class, and the getInstance() method that returns the instance.
It follows the same mechanism as the LoggerImpl class and the same principle of usage too.
```java
public class TransactionValidator extends AccountStatusValidator implements ITransactionValidator {
    private static TransactionValidator instance;

    private TransactionValidator() {}
    ...
    public static TransactionValidator getInstance() {
        ILogger logger = LoggerImpl.getInstance();

        if (instance == null) {
            instance = new TransactionValidator();
            logger.infoLog("Transaction Validator Initialized using Singleton Pattern");
        }
        else {
            logger.infoLog("Transaction Validator already initialized - Returning existing instance");
        }
        return instance;
    }
}
```

  * [AccountStatusValidator](Utils/Validators/AccountStatusValidator.java) - is a class that implements the IAccountStatusValidator
interface and is responsible for validating the Account Status. In this class, the constructor is private, there is a
private field that holds the instance of the AccountStatusValidator class, and the getInstance() method that returns the instance.
Again, the same principle of creation and usage is used as in the previous classes.
```java
public class AccountStatusValidator implements IAccountStatusValidator {
    private static AccountStatusValidator instance;
    protected final ILogger logger = LoggerImpl.getInstance();

    protected AccountStatusValidator() {}
    ...
    public static AccountStatusValidator getInstance() {
        ILogger logger = LoggerImpl.getInstance();

        if (instance == null) {
            instance = new AccountStatusValidator();
            logger.infoLog("Account Status Validator Initialized using Singleton Pattern");
        }
        else {
            logger.infoLog("Account Status Validator already initialized - Returning existing instance");
        }
        return instance;
    }
}
```

#### Abstract Factory Pattern

  * Abstract Factory Creational Design Pattern - lets us produce families of related objects without specifying their 
concrete classes. The Abstract Factory pattern helps us control the classes of objects that an application creates. 
Because a factory encapsulates the responsibility and the process of creating product objects, it isolates clients from
implementation classes. It promotes consistency among products. When product objects in a family are designed to work 
together, it's important that an application use objects from only one family at a time. AbstractFactory makes this easy 
to enforce.

  * For this pattern, I decided to implement Abstract Factory that will group families of ITerminal and ITransaction
implementations, since they are related to cooperative tasks (POSTerminal can perform only WithdrawalTransaction, 
CashInTerminal - only DepositTransaction and ATMTerminal - all 3 types of Transactions).

  * [IAbstractTerminalTransactionFactory](Interfaces/IAbstractTerminalTransactionFactory.java) - is an interface that
specifies methods should be present in concrete implementations of the Abstract Factory. In our case, the methods are
createTransaction(...) and createTerminal(). 
```java
public interface IAbstractTerminalTransactionFactory {
    ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType);
    ITerminal createTerminal();
}
```

  * [POSFactory](Utils/Factories/POSFactory.java) - is a class that implements the IAbstractTerminalTransactionFactory
interface and is responsible for creating the POSTerminal and WithdrawalTransaction objects. In this class, the methods
are implemented to create the objects and return them. The Transaction is of type Withdrawal, since POSTerminal can only
perform Withdrawal operations, and, at the same time, the POSTerminal is created and returned.
```java
public class POSFactory implements IAbstractTerminalTransactionFactory {
    @Override
    public ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType) {
        if (transactionType != TransactionTypeEnum.WITHDRAWAL) {
            return null;
        }
        ITransactionBuilder withdrawalTransactionBuilder = new WithdrawalTransactionBuilder();
        withdrawalTransactionBuilder.setAccounts(accounts);
        withdrawalTransactionBuilder.setAmount(amount);
        return withdrawalTransactionBuilder.getResult();
    }

    @Override
    public ITerminal createTerminal() {
        return new POSTerminal();
    }
}
```
This approach allows us to use the POSFactory across the application in the following manner:

```java
IAbstractTerminalTransactionFactory factory = new POSFactory();
ITerminal atmTerminal = factory.createTerminal();
ITransaction atmTransaction = factory.createTransaction(accounts, amount, transactionType);
```
As it may be noticed, the implementation requires only the concrete type of Factory to be specified. The Factory will 
handle the creation of products by itself, thus providing a possibility to create a family of related objects without
specifying their concrete classes.

  * [ATMFactory](Utils/Factories/ATMFactory.java) - is a class that implements the IAbstractTerminalTransactionFactory
interface and is responsible for creating the ATMTerminal and all 3 types of Transactions objects, since this type of 
terminal may perform multiple types of transactions. In this class, the methods are implemented to create different
Transactions and the ATMTerminal and return them.
```java
public class ATMFactory implements IAbstractTerminalTransactionFactory {
    @Override
    public ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType) {
        switch (transactionType) {
            case DEPOSIT:
                try {
                    ITransactionBuilder depositTransactionBuilder = new DepositTransactionBuilder();
                    depositTransactionBuilder.setAccounts(accounts);
                    depositTransactionBuilder.setAmount(amount);
                    return depositTransactionBuilder.getResult();
                }
                catch(IllegalArgumentException e) {
                    return null;
                }
            case WITHDRAWAL:
                try {
                    ITransactionBuilder withdrawalTransactionBuilder = new WithdrawalTransactionBuilder();
                    withdrawalTransactionBuilder.setAccounts(accounts);
                    withdrawalTransactionBuilder.setAmount(amount);
                    return withdrawalTransactionBuilder.getResult();
                }
                catch(IllegalArgumentException e) {
                    return null;
                }
            case EXCHANGE:
                try {
                    ITransactionBuilder exchangeTransactionBuilder = new ExchangeTransactionBuilder();
                    exchangeTransactionBuilder.setAccounts(accounts);
                    exchangeTransactionBuilder.setAmount(amount);
                    return exchangeTransactionBuilder.getResult();
                }
                catch(IllegalArgumentException e) {
                    return null;
                }
            default:
                return null;
        }
    }

    @Override
    public ITerminal createTerminal() {
        return new ATMTerminal();
    }
}
```
This approach permit us to use the ATMFactory across the application in the following manner:
```java
IAbstractTerminalTransactionFactory ATMFactory = new ATMFactory();
ITerminal atmTerminal = ATMFactory.createTerminal();
ITransaction depositTransaction = ATMFactory.createTransaction(List.of(userAccount1), 100.0, TransactionTypeEnum.WITHDRAWAL);
ITransaction withdrawalTransaction = ATMFactory.createTransaction(List.of(userAccount1), 100.0, TransactionTypeEnum.DEPOSIT);
ITransaction exchangeTransaction = ATMFactory.createTransaction(List.of(userAccount1, userAccount2), 100.0, TransactionTypeEnum.EXCHANGE);
```
As it may be noticed, the transactions are created via the same method, but with different parameters. This allows us to
create different types of transactions without specifying their concrete classes, thus providing a possibility to create
a family of related objects without specifying their concrete classes.

  * [CashInFactory](Utils/Factories/CashInFactory.java) - is a class that implements the IAbstractTerminalTransactionFactory
interface and is responsible for creating the CashInTerminal and DepositTransaction objects. In this class, the methods
are implemented to create the objects and return them.

```java
public class CashInFactory implements IAbstractTerminalTransactionFactory {
    @Override
    public ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType) {
        if (transactionType != TransactionTypeEnum.DEPOSIT) {
            return null;
        }
        ITransactionBuilder depositTransactionBuilder = new DepositTransactionBuilder();
        depositTransactionBuilder.setAccounts(accounts);
        depositTransactionBuilder.setAmount(amount);
        return depositTransactionBuilder.getResult();
    }

    @Override
    public ITerminal createTerminal() {
        return new CashInTerminal();
    }
}
```
As in previous examples, this approach allows us to use the CashInFactory across the application in the following manner:
```java
IAbstractTerminalTransactionFactory CashInFactory = new CashInFactory();
ITerminal cashInTerminal = CashInFactory.createTerminal();
ITransaction depositTransaction2 = CashInFactory.createTransaction(List.of(userAccount1), 100.0, TransactionTypeEnum.DEPOSIT);
``` 


## Conclusions / Screenshots / Results
In conclusion, I want to emphasize that through the implementation of the Creational Design Patterns, I have managed to
understand better how the mechanisms behind those patterns work, how they make code easier to maintain and extend, and how
to use them in practice. The Builder Pattern allowed me to create complex objects step by step, the Singleton Pattern
to use single instance of an object across the project and reuse it easily and the Abstract Factory Pattern taught me how
to group families of related objects and create them as a batch without specifying their concrete classes. At the end,
those patterns, alongside with other Creational Design Patterns make the creation of objects easier, more flexible and
more maintainable, thus improving the overall quality of the code.
