# Laboratory Work 1 - SOLID Principles


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
    * Dependency Inversion Principle

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
        ITransaction withdrawalTransaction = new WithdrawalTransaction(accounts.getFirst(), logger, amount);
        withdrawalTransaction.executeTransaction();
        logger.infoLog("Closed POS Transaction from Account" + accountId + " on Amount " + amount);
    }
}
```
* Transactions - contains the classes that are responsible for the transaction operations ([DepositTransaction](Transactions/DepositTransaction.java),
[ExchangeTransaction](Transactions/ExchangeTransaction.java), [WithdrawalTransaction](Transactions/WithdrawalTransaction.java)).
These classes are responsible for the 

* Snippets from your files.

```
public void main() {

}
```

* If needed, screenshots.


## Conclusions / Screenshots / Results