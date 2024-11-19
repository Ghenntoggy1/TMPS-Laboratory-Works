# Laboratory Work 3 - Behavioral Design Patterns


## Author: Gusev Roman

----

## Objectives:

* Study and understand the Behavioral Design Patterns.
* Choose a domain, define its main classes/models/entities and choose the appropriate instantiation 
mechanisms.
* Implement 1 Behavioral Design Pattern in the project.


## Theory:
In software engineering, the Behavioral patterns are concerned with algorithms and the assignment of responsibilities
between objects. Behavioral patterns describe not just patterns of objects or classes but also
the patterns of communication between them. These patterns characterize complex control
flow that's difficult to follow at run-time. They shift your focus away from flow of control to let
you concentrate just on the way objects are interconnected.

Some examples of this kind of design patterns are:
* Chain of Responsibility
* Command
* Interpreter
* Iterator
* Mediator
* Memento
* Observer
* State
* Strategy
* Template Method
* Visitor

### Chain of Responsibility
Chain of Responsibility is a behavioral design pattern that lets you pass requests along a chain of handlers. 
Upon receiving a request, each handler decides either to process the request or to pass it to the next handler 
in the chain. In other words, this pattern is intended to avoid coupling the sender of a request to its receiver by 
giving more than one object a chance to handle the request [[1]](#bib1).

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/chain-of-responsibility/structure.png?id=848f0fc8dca57a44974d63f8181f5406" alt="Chain of Responsibility Pattern Diagram">
</p>

#### Participants
* **Handler** - declares the interface, common for all concrete handlers. It usually contains just a single method for handling requests, but sometimes it may also have another method for setting the next handler on the chain.
* **BaseHandler** - is an optional class where you can put the boilerplate code that’s common to all handler classes. Usually, this class defines a field for storing a reference to the next handler. The clients can build a chain by passing a handler to the constructor or setter of the previous handler. The class may also implement the default handling behavior: it can pass execution to the next handler after checking for its existence.
* **ConcreteHandlers** - contain the actual code for processing requests. Upon receiving a request, each handler must decide whether to process it and, additionally, whether to pass it along the chain. 
Handlers are usually self-contained and immutable, accepting all necessary data just once via the constructor.
* **Client** - may compose chains just once or compose them dynamically, depending on the application’s logic. Note that a request can be sent to any handler in the chain—it doesn’t have to be the first one.


#### Applicability
Use Chain of Responsibility when:
* more than one object may handle a request, and the handler isn't known a priori .
  The handler should be ascertained automatically.
* you want to issue a request to one of several objects without specifying the receiver
  explicitly.
* the set of objects that can handle a request should be specified dynamically [[2]](#bib2).

### Command
Command is a behavioral design pattern that turns a request into a stand-alone object that contains all information 
about the request. This transformation lets you pass requests as a method arguments, delay or queue a request’s 
execution, and support undoable operations. [[3]](#bib3)

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/command/structure.png?id=1cd7833638f4c43630f4a84017d31195" alt="Command Pattern Diagram">
</p>

#### Participants
* **Invoker** - (aka sender) is responsible for initiating requests. This class must have a field for storing a reference to a command object. The sender triggers that command instead of sending the request directly to the receiver. Note that the sender isn’t responsible for creating the command object. Usually, it gets a pre-created command from the client via the constructor.
* **Command** - interface usually declares just a single method for executing the command.
* **ConcreteCommands** -  implement various kinds of requests. A concrete command isn’t supposed to perform the work on its own, but rather to pass the call to one of the business logic objects. However, for the sake of simplifying the code, these classes can be merged.
Parameters required to execute a method on a receiving object can be declared as fields in the concrete command. You can make command objects immutable by only allowing the initialization of these fields via the constructor.
* **Receiver** - contains some business logic. Almost any object may act as a receiver. Most commands only handle the details of how a request is passed to the receiver, while the receiver itself does the actual work.
* **Client** - creates and configures concrete command objects. The client must pass all of the request parameters, including a receiver instance, into the command’s constructor. After that, the resulting command may be associated with one or multiple senders.

#### Applicability
Use the Command pattern when you want to
* parameterize objects by an action to perform
* specify, queue, and execute requests at different times. A Command object can
  have a lifetime independent of the original request. If the receiver of a request can
  be represented in an address space-independent way, then you can transfer a
  command object for the request to a different process and fulfill the request there.
* support undo. The Command's Execute operation can store state for reversing its
  effects in the command itself. The Command interface must have an added
  Unexecute operation that reverses the effects of a previous call to Execute.
  Executed commands are stored in a history list. Unlimited-level undo and redo is
  achieved by traversing this list backwards and forwards calling Unexecute and
  Execute, respectively.
* support logging changes so that they can be reapplied in case of a system crash. By
  augmenting the Command interface with load and store operations, you can keep a
  persistent log of changes. Recovering from a crash involves reloading logged
  commands from disk and reexecuting them with the Execute operation.
* structure a system around high-level operations built on primitives operations. Such
  a structure is common in information systems that support transactions . A
  transaction encapsulates a set of changes to data. The Command pattern offers a
  way to model transactions. Commands have a common interface, letting you invoke
  all transactions the same way. The pattern also makes it easy to extend the system
  with new transactions. [[4]](#bib4)

### Interpreter
Interpreter is a behavioral design pattern that, given a language, defines a representation for its grammar along with an interpreter that uses
the representation to interpret sentences in the language. [[5]](#bib5)

<p align="center">
    <img src="https://reactiveprogramming.io/_next/image?url=%2Fbooks%2Fpatterns%2Fimg%2Fpatterns-articles%2Finterpreter-diagram.png&w=3840&q=75" alt="Interpreter Pattern Diagram">
</p>

#### Participants
* **AbstractExpression** - declares an abstract Interpret operation that is common to all nodes in the
  abstract syntax tree.
* **TerminalExpression** - implements an Interpret operation associated with terminal symbols in the
  grammar. An instance is required for every terminal symbol in a sentence.
* **NonterminalExpression** - one such class is required for every rule R ::= R1 R2 ... Rn in the
  grammar. Maintains instance variables of type AbstractExpression for each of the symbols R1 through Rn. Implements an Interpret operation for nonterminal symbols in the grammar. Interpret typically calls itself recursively on the variables representing R1 through Rn.
* **Context** - contains information that's global to the interpreter.
* **Client** - builds (or is given) an abstract syntax tree representing a particular
  sentence in the language that the grammar defines. The abstract syntax
  tree is assembled from instances of the NonterminalExpression and
  TerminalExpression classes. Invokes the Interpret operation.


#### Applicability
Use the Interpreter pattern when there is a language to interpret, and you can represent
statements in the language as abstract syntax trees. The Interpreter pattern works best when
* the grammar is simple. For complex grammars, the class hierarchy for the grammar
  becomes large and unmanageable. Tools such as parser generators are a better
  alternative in such cases. They can interpret expressions without building abstract
  syntax trees, which can save space and possibly time.

* efficiency is not a critical concern. The most efficient interpreters are usually not
  implemented by interpreting parse trees directly but by first translating them into
  another form. For example, regular expressions are often transformed into state
  machines. But even then, the translator can be implemented by the Interpreter
  pattern, so the pattern is still applicable. 

### Iterator
Iterator is a behavioral design pattern that lets you traverse elements of a collection without exposing its underlying representation (list, stack, tree, etc.). 
In other words, it provides a way to access the elements of an aggregate object sequentially without exposing
its underlying representation. [[6]](#bib6)

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/iterator/structure.png?id=35ea851f8f6bbe51d79eb91e6e6519d0" alt="Iterator Pattern Diagram">
</p>

#### Participants
* **Iterator** - declares the operations required for traversing a collection: fetching the next element, retrieving the current position, restarting iteration, etc.
* **ConcreteIterators** - implement specific algorithms for traversing a collection. The iterator object should track the traversal progress on its own. This allows several iterators to traverse the same collection independently of each other.
* **Collection** - declares one or multiple methods for getting iterators compatible with the collection. Note that the return type of the methods must be declared as the iterator interface so that the concrete collections can return various kinds of iterators.
* **ConcreteCollections** - return new instances of a particular concrete iterator class each time the client requests one. You might be wondering, where’s the rest of the collection’s code? Don’t worry, it should be in the same class. It’s just that these details aren’t crucial to the actual pattern, so we’re omitting them.
* **Client** - works with both collections and iterators via their interfaces. This way the client isn’t coupled to concrete classes, allowing you to use various collections and iterators with the same client code.
Typically, clients don’t create iterators on their own, but instead get them from collections. Yet, in certain cases, the client can create one directly; for example, when the client defines its own special iterator.


#### Applicability
Use the Iterator pattern
* to access an aggregate object's contents without exposing its internal
  representation.
* to support multiple traversals of aggregate objects.
* to provide a uniform interface for traversing different aggregate structures (that is, to
  support polymorphic iteration). [[7]](#bib7)

### Mediator
Mediator is a behavioral design pattern that lets you reduce chaotic dependencies between objects. The pattern restricts direct communications between the objects and forces them to collaborate only via a mediator object.
In other words, it defines an object that encapsulates how a set of objects interact. Mediator promotes loose
coupling by keeping objects from referring to each other explicitly, and it lets you vary their
interaction independently.[[8]](#bib8)

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/mediator/structure.png?id=1f2accc7820ecfe9665b6d30cbc0bc61" alt="Mediator Pattern Diagram">
</p>

#### Participants
* **Components** - are various classes that contain some business logic. Each component has a reference to a mediator, declared with the type of the mediator interface. The component isn’t aware of the actual class of the mediator, so you can reuse the component in other programs by linking it to a different mediator.
* **Mediator** - declares methods of communication with components, which usually include just a single notification method. Components may pass any context as arguments of this method, including their own objects, but only in such a way that no coupling occurs between a receiving component and the sender’s class.
* **ConcreteMediators** - encapsulate relations between various components. Concrete mediators often keep references to all components they manage and sometimes even manage their lifecycle.

#### Applicability
Use the Mediator pattern when
* a set of objects communicate in well-defined but complex ways. The resulting
  interdependencies are unstructured and difficult to understand.
* reusing an object is difficult because it refers to and communicates with many other
  objects.
* a behavior that's distributed between several classes should be customizable
  without a lot of subclassing. [[9]](#bib9)

### Memento
Memento is a behavioral design pattern that lets you save and restore the previous state of an object without revealing the details of its implementation.
In other words, without violating encapsulation, it captures and externalizes an object's internal state so that the
object can be restored to this state later. [[10]](#bib10)

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/memento/structure1.png?id=4b4a42363a005b617d4df06689787385" alt="Memento Pattern Diagram">
</p>

#### Participants
* **Originator** - can produce snapshots of its own state, as well as restore its state from snapshots when needed.
* **Memento** - is a value object that acts as a snapshot of the originator’s state. It’s a common practice to make the memento immutable and pass it the data only once, via the constructor.
* **Caretaker** - knows not only “when” and “why” to capture the originator’s state, but also when the state should be restored. 
A caretaker can keep track of the originator’s history by storing a stack of mementos. When the originator has to travel back in history, the caretaker fetches the topmost memento from the stack and passes it to the originator’s restoration method.

#### Applicability
Use the Memento pattern when
* a snapshot of (some portion of) an object's state must be saved so that it can be
  restored to that state later, and
* a direct interface to obtaining the state would expose implementation details and
  break the object's encapsulation. [[11]](#bib11)

### Observer
Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they’re observing.
In other words, it defines a one-to-many dependency between objects so that when one object changes state,
all its dependents are notified and updated automatically. [[12]](#bib12)

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/observer/structure.png?id=365b7e2b8fbecc8948f34b9f8f16f33c" alt="Observer Pattern Diagram">
</p>

#### Participants
* **Publisher** - issues events of interest to other objects. These events occur when the publisher changes its state or executes some behaviors. Publishers contain a subscription infrastructure that lets new subscribers join and current subscribers leave the list.
* **Subscriber** - declares the notification interface. In most cases, it consists of a single update method. The method may have several parameters that let the publisher pass some event details along with the update.
* **Concrete Subscribers** - perform some actions in response to notifications issued by the publisher. All of these classes must implement the same interface so the publisher isn’t coupled to concrete classes.
* **Client** - creates publisher and subscriber objects separately and then registers subscribers for publisher updates.

#### Applicability
Use the Observer pattern in any of the following situations:
* When an abstraction has two aspects, one dependent on the other. Encapsulating
  these aspects in separate objects lets you vary and reuse them independently
* When a change to one object requires changing others, and you don't know how
  many objects need to be changed.
* When an object should be able to notify other objects without making assumptions
  about whom these objects are. In other words, you don't want these objects tightly
  coupled. [[13]](#bib13)

### State
State is a behavioral design pattern that lets an object alter its behavior when its internal state changes. It appears as if the object changed its class.
In other words, it allows an object to alter its behavior when its internal state changes. The object will appear to
change its class.[[14]](#bib14)

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/state/structure-en.png?id=38c5cc3a610a201e5bc26a441c63d327" alt="State Pattern Diagram">
</p>

#### Participants
* **Context** - stores a reference to one of the concrete state objects and delegates to it all state-specific work. The context communicates with the state object via the state interface. The context exposes a setter for passing it a new state object.
* **State** - declares the state-specific methods. These methods should make sense for all concrete states because you don’t want some of your states to have useless methods that will never be called.
* **Concrete States** - provide their own implementations for the state-specific methods. To avoid duplication of similar code across multiple states, you may provide intermediate abstract classes that encapsulate some common behavior. 
State objects may store a backreference to the context object. Through this reference, the state can fetch any required info from the context object, as well as initiate state transitions.

#### Applicability
Use the State pattern in either of the following cases:
* An object's behavior depends on its state, and it must change its behavior at runtime depending on that state.
* Operations have large, multipart conditional statements that depend on the object's
  state. This state is usually represented by one or more enumerated constants.
  Often, several operations will contain this same conditional structure. The State
  pattern puts each branch of the conditional in a separate class. This lets you treat
  the object's state as an object in its own right that can vary independently of other
  objects. [[15]](#bib15)

### Strategy
Strategy is a behavioral design pattern that lets you define a family of algorithms, put each of them into a separate class, and make their objects interchangeable.
In other words, it defines a family of algorithms, encapsulate each one, and make them interchangeable.
Strategy lets the algorithm vary independently of clients that use it. [[16]](#bib16)

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/strategy/structure.png?id=c6aa910c94960f35d100bfca02810ea1" alt="Strategy Pattern Diagram">
</p>

#### Participants
* **Context** - maintains a reference to one of the concrete strategies and communicates with this object only via the strategy interface.
* **Strategy** - is common to all concrete strategies. It declares a method the context uses to execute a strategy.
* **Concrete Strategies** - implement different variations of an algorithm the context uses.
* **Client** - creates a specific strategy object and passes it to the context. The context exposes a setter which lets clients replace the strategy associated with the context at runtime.

#### Applicability
Use the Strategy pattern when:
* many related classes differ only in their behavior. Strategies provide a way to
  configure a class with one of many behaviors.
* you need different variants of an algorithm.
* an algorithm uses data that clients shouldn't know about. Use the Strategy pattern
  to avoid exposing complex, algorithm-specific data structures.
* a class defines many behaviors, and these appear as multiple conditional
  statements in its operations. Instead of many conditionals, move related conditional
  branches into their own Strategy class. [[17]](#bib17)

### Template Method
Template Method is a behavioral design pattern that defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure.
In other words, it defines the skeleton of an algorithm in an operation, deferring some steps to subclasses.
Template Method lets subclasses redefine certain steps of an algorithm without changing the
algorithm's structure. [[18]](#bib18)

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/template-method/structure.png?id=924692f994bff6578d8408d90f6fc459" alt="Template Method Pattern Diagram">
</p>

#### Participants
* **Abstract Class** - declares methods that act as steps of an algorithm, as well as the actual template method which calls these methods in a specific order. The steps may either be declared abstract or have some default implementation.
* **Concrete Classes** - can override all the steps, but not the template method itself.

#### Applicability
The Template Method pattern should be used:
* to implement the invariant parts of an algorithm once and leave it up to subclasses
  to implement the behavior that can vary.
* to control subclasses extensions. You can define a template method that calls
  "hook" operations at specific points, thereby permitting
  extensions only at those points. [[19]](#bib19)

### Visitor
Visitor is a behavioral design pattern that lets you separate algorithms from the objects on which they operate.
In other words, it represents an operation to be performed on the elements of an object structure. Visitor lets
you define a new operation without changing the classes of the elements on which it
operates. [[18]](#bib18)

<p align="center">
    <img src="https://refactoring.guru/images/patterns/diagrams/visitor/structure-en.png?id=34126311c4e0d5c9fbb970595d2f1777" alt="Visitor Pattern Diagram">
</p>

#### Participants
* **Visitor** - declares a set of visiting methods that can take concrete elements of an object structure as arguments. These methods may have the same names if the program is written in a language that supports overloading, but the type of their parameters must be different.
* **Concrete Visitor** - implements several versions of the same behaviors, tailored for different concrete element classes.
* **Element** - declares a method for “accepting” visitors. This method should have one parameter declared with the type of the visitor interface.
* **Concrete Element** - must implement the acceptance method. The purpose of this method is to redirect the call to the proper visitor’s method corresponding to the current element class. Be aware that even if a base element class implements this method, all subclasses must still override this method in their own classes and call the appropriate method on the visitor object.
* **Client** - usually represents a collection or some other complex object (for example, a Composite tree). Usually, clients aren’t aware of all the concrete element classes because they work with objects from that collection via some abstract interface.

#### Applicability
Use the Visitor pattern when:
* an object structure contains many classes of objects with differing interfaces, and
  you want to perform operations on these objects that depend on their concrete
  classes.
* many distinct and unrelated operations need to be performed on objects in an object
  structure, and you want to avoid "polluting" their classes with these operations.
  Visitor lets you keep related operations together by defining them in one class.
  When the object structure is shared by many applications, use Visitor to put
  operations in just those applications that need them.
* the classes defining the object structure rarely change, but you often want to define
  new operations over the structure. Changing the object structure classes requires
  redefining the interface to all visitors, which is potentially costly. If the object
  structure classes change often, then it's probably better to define the operations in
  those classes. [[19]](#bib19)

## Implementation

* For this Laboratory Work, I had to implement 1 Behavioral Design Pattern. 
I have chosen to implement State Design Pattern.

* I decided to build upon the topic of Terminal Interaction and Transaction / Financial Operations on Accounts and between them as 
well. I have used previous Laboratory Works that performs operations, such as: 
    * Creating User Entities,
    * Creating User Account Entities,
    * Creating Transaction Entities,
    * Creating Terminal Entities,
    * Depositing money into a User Account,
    * Withdrawing money from a User Account,
    * Transferring money between User Accounts,
    * Logs the financial operations and processes in the application (errors, successful operations, etc.),
    * Validator for Transaction operations.
    * Implements Abstract Factory for Terminal and Transaction creation.
    * Implements Builder for User Account and Transaction creation.
    * Implements Singleton for Logger, Transaction Validator and Account Status Validator.
    * Implements Adapter for Logger.
    * Implements Bridge for Terminal and Transaction creation.
    * Implements Proxy for Logger.

#### State Pattern
* State Behavioral Design Pattern - lets an object alter its behavior when its internal state changes. This is useful
when an object may have different states and may act differently depending on the state it is in.

* First, I started by modelling the UML Diagram for that purpose. I followed the classical structure of the State Pattern.
In my case, the Context part is represented by ITransaction Concrete Implementors. These classes have reference
to the State Interface, in my case - ITransactionState class. This class is implemented by several Concrete States,
that will provide the logic for the Transaction to be performed, depending on the state it is in - InProcess, Validated,
Completed, Failed.

* In the following diagram, is an example of DepositTransaction, but for each type of ITransaction, the diagram
does not differ much, therefore I present here only one of them.
<p align="center">
    <img src="ReportUML/State%20Diagram.png" alt="State Implementation Diagram - Transaction State">
</p>

* As in the diagram above, I used previously defined classes for concrete Abstract Factory implementation.
  * [ATMFactory](Utils/Factories/ATMFactory.java) - is responsible for creation of Transactions by Transaction Type (all of them),
and creation of ATM Terminal. This class is basically the Client for the ITransaction with ITransactionState classes.
```java
public class ATMFactory implements IAbstractTerminalTransactionFactory {
    private ITransactionBuilder transactionBuilder;
    ...
    @Override
    public ITransaction createTransaction(List<IAccount> accounts, double amount, TransactionTypeEnum transactionType) {
        ...
        try {
            setAccounts(accounts);
            setAmount(amount);
            return getTransaction();
        } catch (IllegalArgumentException e) {
            this.logger.errorLog("Failed ATM Transaction: " + e.getMessage());
            return null;
        }
    }
    ...
    private void setAccounts(List<IAccount> accounts) {
        this.transactionBuilder.setAccounts(accounts);
    }
    private void setAmount(Double amount) {
        this.transactionBuilder.setAmount(amount);
    }
    private ITransaction getTransaction() {
        return this.transactionBuilder.getResult();
    }
}
```
  * [ITransactionState](Interfaces/ITransactionState.java) - is the State Interface that declares the methods for the concrete
implementors. In this case, I have 2 methods - setTransactionContext and executeTransaction, that follows the standart
pattern structure.
```java
public interface ITransactionState {
  void setTransactionContext(ITransaction transactionContext);
  void executeTransaction();
}
```
  * [DepositTransaction](Transactions/DepositTransaction.java) - In this class, I added a new field - currentTransactionState,
that will hold the current state of the Transaction. This field is initialized with InProcessTransactionState, that is
the default state for a new Transaction. The actual Transaction is performed in the same method as previously - executeTransaction,
but now, the logic is moved to the State classes. The same structure is used for WithdrawalTransaction and ExchangeTransaction as well.
```java
public class DepositTransaction implements ITransaction {
    ...
    private ITransactionState currentTransactionState;

    public DepositTransaction(List<IAccount> account, Double amount) {
        ...
        this.currentTransactionState = new InProcessTransactionState();
        this.currentTransactionState.setTransactionContext(this);
    }
    
    @Override
    public void executeTransaction() {
        this.currentTransactionState.setTransactionContext(this);
        this.currentTransactionState.executeTransaction();
    }
    ...
    @Override
    public void changeTransactionState(ITransactionState transactionState) {
        this.currentTransactionState = transactionState;
    }
}
```

* [WithdrawalTransaction](Transactions/WithdrawalTransaction.java) - As it may be seen, the WithdrawalTransaction class is similarly executed as the DepositTransaction class, but the logic is different and described in the ITransactionState concrete classes.
```java
public class WithdrawalTransaction implements ITransaction {
    ...
    private ITransactionState currentTransactionState;

    public WithdrawalTransaction(List<IAccount> account, Double amount) {
        ...
        this.currentTransactionState = new InProcessTransactionState();
        this.currentTransactionState.setTransactionContext(this);
    }
    
    @Override
    public void executeTransaction() {
        this.currentTransactionState.setTransactionContext(this);
        this.currentTransactionState.executeTransaction();
    }
    ...
    @Override
    public void changeTransactionState(ITransactionState transactionState) {
        this.currentTransactionState = transactionState;
    }
}
```

* In order to better describe the possible states of a ITransaction implementation class, I designed a State Machine Diagram,
in which the ITransaction state flow is displayed and the conditions for changing the state are as well presented. First,
a Transaction is being processed via different Validators. Then, if validation is failed - Transaction fails and its state
is changed to Failed and it reaches a final state. Otherwise, the Transaction is validated and its state is being changed to Validated.
After that, unconditionally it will reach the Completed state, where the Transaction is finalized and closed.
<p align="center">
    <img src="ReportUML/Transaction%20State%20Machine.png" alt="State Implementation Diagram - Transaction State">
</p>

* [InProcessTransactionState](Transactions/TransactionStates/InProcessTransactionState.java) - This class is the first state of the Transaction, where the Transaction is created and initialized. 
The logic for the Transaction, however, is not yet executed, but the Transaction is in the process of the validation. It has a reference to the Transaction Context, that is the Transaction itself, and it is used to change the state of the Transaction
dynamically.
```java
public class InProcessTransactionState implements ITransactionState {
    private ITransaction transactionContext;
    ...
    @Override
    public void setTransactionContext(ITransaction transactionContext) {
        this.transactionContext = transactionContext;
    }

    @Override
    public void executeTransaction() {
        ...
    }
}
```
At the same time, I have a switch case block that will handle the type of Transaction and will execute the proper validation for each
encountered type. In this case, the WithdrawalTransaction is validated by the TransactionValidator.
```java
public class InProcessTransactionState implements ITransactionState {
    ...
    @Override
    public void executeTransaction() {
        this.logger.infoLog("Transaction Status changed to In Process");
        List<IAccount> accounts = this.transactionContext.getAccounts();
        switch (this.transactionContext.getTransactionType()) {
            case TransactionTypeEnum.WITHDRAWAL -> {
                int userAccountId = accounts.getFirst().getAccountId();
                this.logger.infoLog("Start Processing Withdrawal Transaction for account " + userAccountId);
                if (((ITransactionValidator) this.transactionContext.getValidator()).validateTransaction(accounts.getFirst(), transactionContext.getAmount())) {
                    transactionContext.changeTransactionState(new ValidatedTransactionState());
                    transactionContext.executeTransaction();
                }
                else {
                    transactionContext.changeTransactionState(new FailedTransactionState());
                    transactionContext.executeTransaction();
                }
        }
        ...
        }
    }
}
```
At the same time, I have a switch case block that will handle the type of Transaction and will execute the proper validation for each
encountered type. In this case, the WithdrawalTransaction is validated by the TransactionValidator.
```java
public class InProcessTransactionState implements ITransactionState {
    ...
    @Override
    public void executeTransaction() {
        this.logger.infoLog("Transaction Status changed to In Process");
        List<IAccount> accounts = this.transactionContext.getAccounts();
        switch (this.transactionContext.getTransactionType()) {
            case TransactionTypeEnum.WITHDRAWAL -> {
                int userAccountId = accounts.getFirst().getAccountId();
                this.logger.infoLog("Start Processing Withdrawal Transaction for account " + userAccountId);
                if (((ITransactionValidator) this.transactionContext.getValidator()).validateTransaction(accounts.getFirst(), transactionContext.getAmount())) {
                    transactionContext.changeTransactionState(new ValidatedTransactionState());
                    transactionContext.executeTransaction();
                }
                else {
                    transactionContext.changeTransactionState(new FailedTransactionState());
                    transactionContext.executeTransaction();
                }
        }
        ...
        }
    }
}
```
In here I have the logic for the DepositTransaction, where the Transaction is validated by the AccountStatusValidator. Similarly
to the WithdrawalTransaction, the transactionContext is modified to the next state, depending on the validation result.
```java
public class InProcessTransactionState implements ITransactionState {
    ...
    @Override
    public void executeTransaction() {
        ...
        switch (this.transactionContext.getTransactionType()) {
            ...
            case TransactionTypeEnum.DEPOSIT -> {
                int userAccountId = accounts.getFirst().getAccountId();
                this.logger.infoLog("Start Processing Deposit Transaction for account " + userAccountId);
                if (this.transactionContext.getValidator().validateAccountStatus(accounts.getFirst())) {
                    transactionContext.changeTransactionState(new ValidatedTransactionState());
                    transactionContext.executeTransaction();
                }
                else {
                    transactionContext.changeTransactionState(new FailedTransactionState());
                    transactionContext.executeTransaction();
                }
            }
        ...
        }
    }
}
```
In here I have the logic for the ExchangeTransaction, where the Transaction is validated by the AccountStatusValidator and TransactionValidator. Similarly
to the previous cases, the transactionContext is modified to the next state, depending on the validation result.
```java
public class InProcessTransactionState implements ITransactionState {
    ...
    @Override
    public void executeTransaction() {
        ...
        switch (this.transactionContext.getTransactionType()) {
            ...
            case TransactionTypeEnum.EXCHANGE -> {
                int senderAccountId = accounts.getFirst().getAccountId();
                int receiverAccountId = accounts.getLast().getAccountId();
                this.logger.infoLog("Start Processing Exchange Transaction from Account " + senderAccountId + " to Account " + receiverAccountId);
                ITransactionValidator validator = (ITransactionValidator) this.transactionContext.getValidator();
                if (validator.validateTransaction(accounts.getFirst(), transactionContext.getAmount()) && validator.validateAccountStatus(accounts.getLast())) {
                    transactionContext.changeTransactionState(new ValidatedTransactionState());
                    transactionContext.executeTransaction();
                }
                else {
                    transactionContext.changeTransactionState(new FailedTransactionState());
                    transactionContext.executeTransaction();
                }
            }
            ...
        }
    }
}
```

* [ValidatedTransactionState](Transactions/TransactionStates/ValidatedTransactionState.java) - This class is the another state of the Transaction, where the Transaction is validated and ready to be executed.
In this class I also have a switch case block that will handle the type of Transaction and will execute the proper logic for each
type of ITransaction concrete implementation. In this case, the DepositTransaction is executed by the Account class, that will deposit the amount of money into the User Account.
In the end, it will change the state of the Transaction to CompletedTransactionState and will execute it again from that 
executeTransaction method in context.
```java
public class ValidatedTransactionState implements ITransactionState {
    private ITransaction transactionContext;
    ...
    @Override
    public void setTransactionContext(ITransaction transactionContext) {
        this.transactionContext = transactionContext;
    }

    @Override
    public void executeTransaction() {
        this.logger.infoLog("Transaction Status changed to Validated");
        List<IAccount> accounts = this.transactionContext.getAccounts();
        switch (transactionContext.getTransactionType()) {
            case TransactionTypeEnum.DEPOSIT -> {
                IAccount account = this.transactionContext.getAccounts().getFirst();
                int userAccountId = account.getAccountId();
                double amount = this.transactionContext.getAmount();
                account.deposit(amount);
                this.logger.infoLog("Deposited " + amount + " to Account " + userAccountId);
                this.transactionContext.changeTransactionState(new CompletedTransactionState());
                this.transactionContext.executeTransaction();
            }
            ...
        }
    }
}
```
In this case, the WithdrawalTransaction is executed by the Account class, that will withdraw the amount of money from the User Account.
In the end, it will change the state of the Transaction to CompletedTransactionState and will execute it again from that
executeTransaction method in context.
```java
public class ValidatedTransactionState implements ITransactionState {
    ...
    @Override
    public void executeTransaction() {
        ...
        List<IAccount> accounts = this.transactionContext.getAccounts();
        switch (transactionContext.getTransactionType()) {
            case TransactionTypeEnum.WITHDRAWAL -> {
                IAccount account = this.transactionContext.getAccounts().getFirst();
                int userAccountId = account.getAccountId();
                double amount = this.transactionContext.getAmount();
                account.withdraw(amount);
                this.logger.infoLog("Withdrew " + amount + " from Account " + userAccountId);
                this.transactionContext.changeTransactionState(new CompletedTransactionState());
                this.transactionContext.executeTransaction();
            }
            ...
        }
    }
}
```
In this case, the ExchangeTransaction is executed by the Account class, that will exchange the amount of money from sender account 
to receiver account. In the end, it will change the state of the Transaction to CompletedTransactionState and will execute it again from that
executeTransaction method in context.
```java
public class ValidatedTransactionState implements ITransactionState {
    ...
    @Override
    public void executeTransaction() {
        ...
        List<IAccount> accounts = this.transactionContext.getAccounts();
        switch (transactionContext.getTransactionType()) {
            case TransactionTypeEnum.EXCHANGE -> {
                IAccount senderAccount = this.transactionContext.getAccounts().getFirst();
                IAccount receiverAccount = this.transactionContext.getAccounts().getLast();
                int senderAccountId = senderAccount.getAccountId();
                int receiverAccountId = receiverAccount.getAccountId();
                double amount = this.transactionContext.getAmount();
                senderAccount.withdraw(amount);
                receiverAccount.deposit(amount);
                this.logger.infoLog("Successfully Exchanged " + amount + " from Account " + senderAccountId + " to Account " + receiverAccountId);
                this.transactionContext.changeTransactionState(new CompletedTransactionState());
                this.transactionContext.executeTransaction();
            }
            ...
        }
    }
}
```

* [CompletedTransactionState](Transactions/TransactionStates/CompletedTransactionState.java) - This class is the last state of the Transaction, where the Transaction is completed and the final state is reached.
In the code below, each type of ITransaction implementation will have a different logic for logging mechanism, and will be the final
state of the ITransaction.
```java
public class CompletedTransactionState implements ITransactionState {
    private ITransaction transactionContext;
    ...
    @Override
    public void setTransactionContext(ITransaction transactionContext) {
        this.transactionContext = transactionContext;
    }

    @Override
    public void executeTransaction() {
        ...
        switch (transactionContext.getTransactionType()) {
            case DEPOSIT -> {
                double newBalance = this.transactionContext.getAccounts().getFirst().getBalance();
                this.logger.infoLog("Deposit Transaction completed successfully. New Balance: " + newBalance + " for Account " + this.transactionContext.getAccounts().getFirst().getAccountId());
                this.logger.infoLog("Deposit Transaction Status changed to Completed");
            }
            case WITHDRAWAL -> {
                double newBalance = this.transactionContext.getAccounts().getFirst().getBalance();
                this.logger.infoLog("Withdrawal Transaction completed successfully. New Balance: " + newBalance + " for Account " + this.transactionContext.getAccounts().getFirst().getAccountId());
                this.logger.infoLog("Withdrawal Transaction Status changed to Completed");
            }
            case EXCHANGE -> {
                double newBalanceSender = this.transactionContext.getAccounts().getFirst().getBalance();
                double newBalanceReceiver = this.transactionContext.getAccounts().getLast().getBalance();
                this.logger.infoLog("Exchange Transaction completed successfully. New Balance for Sender: " + newBalanceSender + " and Receiver: " + newBalanceReceiver);
                this.logger.infoLog("Exchange Transaction Status changed to Completed");}
            ...
        }
    }
}
```

* [FailedTransactionState](Transactions/TransactionStates/FailedTransactionState.java) - This class is another last state of the Transaction, where the Transaction is failed and the final state is reached.
  In the code below, each type of ITransaction implementation will have a different logic for logging mechanism, and will be the final
  state of the ITransaction.
```java
public class FailedTransactionState implements ITransactionState {
    private ITransaction transactionContext;
    ...
    @Override
    public void setTransactionContext(ITransaction transactionContext) {
        this.transactionContext = transactionContext;
    }
  
    @Override
    public void executeTransaction() {
        ...
        switch (transactionContext.getTransactionType()) {
            case DEPOSIT -> {
                int userAccountId = transactionContext.getAccounts().getFirst().getAccountId();
                logger.errorLog("Failed Deposit Transaction for Account " + userAccountId);
            }
            case WITHDRAWAL -> {
                int userAccountId = transactionContext.getAccounts().getFirst().getAccountId();
                logger.errorLog("Failed Withdrawal Transaction for Account " + userAccountId);
            }
            case EXCHANGE -> {
                int senderAccountId = transactionContext.getAccounts().getFirst().getAccountId();
                int receiverAccountId = transactionContext.getAccounts().getLast().getAccountId();
                logger.errorLog("Failed Exchange Transaction from Account " + senderAccountId + " to Account " + receiverAccountId);
            }
            default -> logger.errorLog("Invalid Transaction Type");
          }
      }
}
```

## Conclusions / Screenshots / Results
In conclusion, I want to emphasize that through the implementation of the State Behavioral Design Pattern, I have managed to
understand better how the mechanisms behind those patterns work, how they make code easier to maintain and extend, and how
to use them in practice.


## References
<a id="bib1"></a>[1] Refactoring Guru, “Chain of Responsibility” Refactoring.guru, 2014. https://refactoring.guru/design-patterns/chain-of-responsibility

<a id="bib2"></a>[2] “Behavioral Patterns.” pg.223 Available: https://www.javier8a.com/itc/bd1/articulo.pdf

<a id="bib3"></a>[3] Refactoring Guru, “Command” Refactoring.guru, 2014. https://refactoring.guru/design-patterns/command

<a id="bib4"></a>[4] “Behavioral Patterns.” pg.233 Available: https://www.javier8a.com/itc/bd1/articulo.pdf

<a id="bib5"></a>[5] “Behavioral Patterns.” pg.243 Available: https://www.javier8a.com/itc/bd1/articulo.pdf

<a id="bib6"></a>[6] Refactoring Guru, “Iterator” Refactoring.guru, 2014. https://refactoring.guru/design-patterns/iterator

<a id="bib7"></a>[7] “Behavioral Patterns.” pg.257 Available: https://www.javier8a.com/itc/bd1/articulo.pdf

<a id="bib8"></a>[8] Refactoring Guru, “Mediator” Refactoring.guru, 2014. https://refactoring.guru/design-patterns/mediator

<a id="bib9"></a>[9] “Behavioral Patterns.” pg.273 Available: https://www.javier8a.com/itc/bd1/articulo.pdf

<a id="bib10"></a>[10] Refactoring Guru, “Memento” Refactoring.guru, 2014. https://refactoring.guru/design-patterns/memento

<a id="bib11"></a>[11] “Behavioral Patterns.” pg.283 Available: https://www.javier8a.com/itc/bd1/articulo.pdf

<a id="bib12"></a>[12] Refactoring Guru, “Observer” Refactoring.guru, 2014. https://refactoring.guru/design-patterns/observer

<a id="bib13"></a>[13] “Behavioral Patterns.” pg.293 Available: https://www.javier8a.com/itc/bd1/articulo.pdf

<a id="bib14"></a>[14] Refactoring Guru, “State” Refactoring.guru, 2014. https://refactoring.guru/design-patterns/state

<a id="bib15"></a>[15] “Behavioral Patterns.” pg.305 Available: https://www.javier8a.com/itc/bd1/articulo.pdf

<a id="bib16"></a>[16] Refactoring Guru, “Strategy” Refactoring.guru, 2014. https://refactoring.guru/design-patterns/strategy

<a id="bib17"></a>[17] “Behavioral Patterns.” pg.315 Available: https://www.javier8a.com/itc/bd1/articulo.pdf

<a id="bib18"></a>[18] Refactoring Guru, “Template Method” Refactoring.guru, 2014. https://refactoring.guru/design-patterns/template-method

<a id="bib19"></a>[19] “Behavioral Patterns.” pg.325 Available: https://www.javier8a.com/itc/bd1/articulo.pdf

<a id="bib20"></a>[20] Refactoring Guru, “Visitor” Refactoring.guru, 2014. https://refactoring.guru/design-patterns/visitor

<a id="bib21"></a>[21] “Behavioral Patterns.” pg.331 Available: https://www.javier8a.com/itc/bd1/articulo.pdf
