# Java Interview Questions

## Core

| Type |  Size   | Range |
|:----:|:-------:|:-----:|
| byte | 8 bits | -2^7 ~ 2^47 -1 |
| char | 16-bit unsigned | 0 ~ 2^16 -1 |
| int  | 32 bits | -2^31 ~ 2^31 -1 |
| long | 64 bits | -2^63 ~ 2^63 -1 |

What are Access Modifiers?
- Private, accessible only within the class in which they are declared
- Default, accessible only within the same package
- Protected, accessible within same package or sub classes in different package
- Public, accessible everywhere

How does `HashMap` work in Java?

- Using HashCode to calculate which bucket we should store
- If there are multiple records with same HashCode, we will place them in LinkedList
- During retrieving, we will compare records in LinkedList by `key.equals()`
- If the size exceeds a given threshold defined by the load factor (0.75), a _rehashing_ will happen - re-size itself by creating a new bucket array of size twice of the previous size of HashMap and then start putting every old element into that new bucket array.
- Potential race condition exists while resizing

```txt
Yes there is potential race condition exists while resizing HashMap in Java, if two threads at the same time found that now HashMap needs resizing and they both try to resizing. on the process of resizing of HashMap in Java, the element in the bucket which is stored in linked list get reversed in order during their migration to new bucket because Java HashMap doesn't append the new element at tail instead it append new element at the head to avoid tail traversing 

(When adding a new element, we don't traverse to the tail every time, but instead we append it to the head:

10
23 10
65 23 10
44 65 23 10
12 44 65 23 10

90 12 44 65 23 10)

If race condition happens then you will end up with an infinite loop. Since the thread A make 4 -> 3 -> 2 -> 1 already, but thread 2 maybe still traversing from 3 -> 4, which make 4 <=> 3. Check https://mailinator.blogspot.com/2009/06/beautiful-race-condition.html.


Though this point, you can potentially argue that what the hell makes you think to use HashMap  in multi-threaded environment to interviewer :)
```

```
table[0] -> [key0, val0, next] -> [key1, val1, next] -> null
table[1]
...
table[n-1]
```

ArrayList

Add:
The add operation runs in amortized constant time, that is, adding n elements requires O(n) time.

Grow:
Each ArrayList instance has a capacity. The capacity is the size of the array used to store the elements in the list. It is always at least as large as the list size. As elements are added to an ArrayList, its capacity grows automatically. The details of the growth policy are not specified beyond the fact that adding an element has constant amortized time cost. [java8 docs](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)

```int newCapacity= (oldCapacity * 3)/2 +1;```

Why is String class considered immutable?

* The String class is immutable, so that once it is created a String object cannot be changed. Since String is immutable it can safely be shared between many threads ,which is considered very important for multithreaded programming.

When will the finally block not be executed?

* If the JVM exits while the try or catch code is being executed, then the finally block may not execute. Likewise, if the thread executing the try or catch code is interrupted or killed, the finally block may not execute even though the application as a whole continues.

Is java pass by reference or pass by value?

* Java uses only __call by value__. It creates a copy of references and pass them as value to the methods.

Exception

<img src="java-exception.png" alt="java exception" title="Java Exceptions" width="500" height="200" />

What is Polymorphism?

* Polymorphism is the ability of an object to take on many forms. The most common use of polymorphism in OOP occurs when a parent class reference is used to refer to a child class object.

What is Abstraction?

* It refers to the ability to make a class abstract in OOP. It helps to reduce the complexity and also improves the maintainability of the system.

What is Encapsulation?

* It is the technique of making the fields in a class private and providing access to the fields via public methods. If a field is declared private, it cannot be accessed by anyone outside the class, thereby hiding the fields within the class. Therefore encapsulation is also referred to as data hiding.

Differences between final and immutability

* final means that you can’t change the object’s reference to point to another reference or another object, but you can still mutate its state (using setter methods e.g). Whereas immutable means that the object’s actual value can’t be changed, but you can change its reference to another one.
* final modifier is applicable for variable but not for objects, Whereas immutability applicable for an object but not for variables.
By declaring a reference variable as final, we won’t get any immutability nature, Even though reference variable is final. We can perform any type of change in the corresponding Object. But we cant perform reassignment for that variable.
* final ensures that the address of the object remains the same whereas the Immutable suggests that we can’t change the state of the object once created.

What is the benefit of Composition over Inheritance?

* Any change in the superclass might affect subclass even though we might not be using the superclass methods. For example, if we have a method test() in the subclass and suddenly somebody introduces a method test() in the superclass, we will get compilation errors in the subclass. The composition will never face this issue because we are using only what methods we need.
* Inheritance exposes all the superclass methods and variables to the client and if we have no control in designing superclass, it can lead to security holes. Composition allows us to provide restricted access to the methods and hence more secure.

Can a constructor be made final?

* No

Can constructor be inherited?

* No, constructor cannot be inherited.

What is the difference between inner class and nested class?

* When a class is defined within a scope of another class, then it becomes inner class. If the access modifier of the inner class is static, then it becomes nested class.

## JVM and GC

<img src="jvm_memory.png" alt="java heap memory" title="JVM heap memory" width="500" height="200" />

* Heap = Young Gen + Old Gen + Perm (64MB)
  * Free space < 40%, increase till `-Xmx`
  * Free space > 70%, decrease till `-Xms`
* Young Gen (`-Xmn`): Space to store newly created objects, it contains __Eden__, __S0__(Survivors from last GC), __S1__(inactive, S0 and S1 will swap next time)
* Minor GC: GC against Young Gen using _Copy and Collection_ since it needs to have consistent space to store new object. When it runs, the GC hangs the program and copy all active objects from __Eden__, __S0__(active) into __S1__(inactive). All objects survive from GC will have +1 age
* Old Gen: When the age of an object reaches `XX:MaxTenuringThreshold` (default 15), the object will be moved to _Old Generation_
* Major GC: against Old Gen using _Mark and Sweep GC_ (Traverse from the root and delete space if an object is not reachable). It is also called _Full GC_ since it runs against whole _heap_
* Perm Gen: Store Class and Meta info

Algorithms for GC

1. Reference Counting: pro: effective; con: cannot handle circulate referring.
2. Mark and Sweep: traverse from the root set, if an object cannot be accessible, _mark_ it as garbage, and release its space during _sweep_. Disadvantage: if there are small amounts of objects alive, it will cost large time to mark and delete them. Used against Old Gen.
3. Copy and Collection: traverse from the root set, if an object is live, copy into into a space. Disadvantage: if there are large amounts of objects alive, copying them is expensive. Used against Young Gen.

## Multi-thread

What is the key difference between a process and a thread?

* A process is an execution of a program but a thread is a single execution sequence within the process. A process can contain multiple threads.
* A JVM runs in a single process and each process will have its own heap. Threads run within a process (i.e. a JVM process), and share the heap belonging to that process. This is why several threads may access the same object. Threads share the same heap and have their own stack space. This is how one thread’s invocation of a method and its local variables are kept thread safe from other threads.

Explain different ways of creating a thread?

1. Extending the java.lang.Thread class.
2. Implementing the java.lang.Runnable interface.
3. Implement `Callable` and use `FutureTask` to create a thread. A `FutureTask` can be created by providing its constructor with a Callable. Then the FutureTask object is provided to the constructor of Thread to create the Thread object. Thus, indirectly, the thread is created with a Callable.
4. Use `ExecutorService`, `Callable` and `Future` to return the result

```java
public class MyThread {
    public static void main(String[] args) {
        Thread t1 = new Thread(new RunnableThread());
        t1.start();

        Thread t2 = new ExtendedThread();
        t2.run();
    }
}


class RunnableThread implements Runnable {
    @Override
    public void run() {}
}


class ExtendedThread extends Thread {
    @Override
    public synchronized void start() {
        super.start();
    }
}
```

Which approach would you favor and why?

Favor Callable interface with the Executor framework for thread pooling.

1. The thread pool is more efficient. Even though the threads are light-weighted than creating a process, creating them utilizes a lot of resources. Also, creating a new thread for each task will consume more stack memory as each thread will have its own stack and also the CPU will spend more time in context switching. Creating a lot many threads with no bounds to the maximum threshold can cause application to run out of heap memory. So, creating a Thread Pool is a better solution as a finite number of threads can be pooled and reused. The runnable or callable tasks will be placed in a queue, and the finite number of threads in the pool will take turns to process the tasks in the queue.
2. The Runnable or Callable interface is preferred over extending the Thread class, as it does not require your object to inherit a thread because when you need multiple inheritance, only interfaces can help you. Java class can extend only one class, but can implement many interfaces.
3. The Runnable interface’s void run() method has no way of returning any result back to the main thread. The executor framework introduced the Callable interface that returns a value from its call() method. This means the asynchronous task will be able to return a value once it is done executing.

How to end a Thread?

1. The thread ends when the run() method finishes its execution.
2. When the thread throws an Exception or Error that is not being caught in the program.
3. Java program completes or ends.
4. Another thread calls stop() methods.

Callable vs Runnable

__Note that a thread can't be created with a Callable, it can only be created with a Runnable__

* Runnable, overrides `run()` method and return nothing
* Callable, overrides `call()` method and returns a result on completion.
* The `call()` method can throw an exception whereas `run()` cannot.

What is the difference `start()` vs `run()`

* `start()` is used to start a newly created thread, it calls `run()`
* `run()` only starts an existing thread

What is Future?

* Future interface has methods to obtain the result generated by a `Callable` object and manage its state. It represents the result of an asynchronous computation.

What is the difference between yield and sleep? What is the difference between the methods `sleep()` and `wait()`?

* When a task invokes yield(), it changes from running state to runnable state
* When a task invokes sleep(), it changes from running state to waiting/sleeping state.

`Future` vs `FutureTask`

* `Future`:
A Future interface provides methods to check if the computation is complete, to wait for its completion and to retrieve the results of the computation. The result is retrieved using Future’s get() method when the computation has completed, and it blocks until it is completed.
* `FutureTask`:
FutureTask implements `Future` interface and `RunnableFuture` interface, means one can use `FutureTask` as Runnable and can be submitted to ExecutorService for execution.

When shall we use `synchronization`?

1. Synchronization is the capability to control the access of multiple threads to shared resources. synchronized keyword in java provides locking which ensures mutual exclusive access of shared resource and prevent data race.
1. If we do not use synchronization and let two or more threads access a shared resource at the same time, it will lead to distorted results. Here is an example: Let’s assume that we have two different threads T1 and T2, T1 that start execution and save certain values in a file sample.txt which will be used to calculate some results when T1 returns. Meanwhile, T2 starts and before T1 returns, T2 changes the values saved by T1 in the file sample.txt (sample.txt is the shared resource). Now obviously T1 will give the wrong result.
1. Synchronization was introduced to prevent such problems from happening. If we use synchronization in the above-mentioned case, once T1 starts using sample.txt file, this file will be locked(LOCK mode), and no other thread will be able to access or modify it until T1 returns.

`synchronized` vs `ReentrantLock`

* They are both locks
* `synchronized` is maintained by jvm; `ReentrantLock` needs to call `lock()` and `unlock()`
* Using `ReentrantLock`, a thread could exit waiting if too long. It could help to avoid deadlock
* ReentrantLock could use fail locks. When multiple threads waiting for `ReentrantLock`, they will be granted access according to their application time
* A `ReentrantLock` could lock multiple objects

What are common locks?

* `ReentrantLock`. `ReentrantLock` is a mutually exclusive lock with the same behavior as the intrinsic/implicit lock accessed via the `synchronized` keyword. As the name suggests, possesses reentrant characteristics. That means a thread that currently owns the lock can acquire it more than once without any problem.
* `ReadWriteLock`. The read lock may be held by multiple threads simultaneously as long as the write lock is not held by any thread.

Atomic variable

* Atomic classes internally use compare-and-swap instructions. These instructions are generally much faster than locks.

ConcurrentHashMap vs Hashtable vs Synchronized Map

- All methods of Hashtable are synchronized which makes them quite slow due to contention if a number of thread increases. Synchronized Map is also not very different than Hashtable and provides similar performance in concurrent Java programs. The only difference between Hashtable and Synchronized Map is that later is not a legacy and you can wrap any Map to create it's synchronized version by using `Collections.synchronizedMap()` method.
- On the other hand, `ConcurrentHashMap` is specially designed for concurrent use i.e. more than one thread. By default it simultaneously allows 16 threads to read and write from `Map` without any external synchronization. It is also very scalable because of stripped locking technique used in the internal implementation of ConcurrentHashMap class. Unlike `Hashtable` and `Synchronized Map`, it never locks whole `Map`, instead, it divides the map into segments and locking is done on those. Though it performs better if a number of reader threads are greater than the number of writer threads.

What is deadlock?

Deadlock describes a situation where two or more threads are blocked forever, waiting for each other. Deadlock occurs when multiple threads need the same locks but obtain them in a different order. A Java multi-threaded program may suffer from the deadlock condition because the synchronized keyword causes the executing thread to block while waiting for the lock, or monitor, associated with the specified object.
In order to avoid deadlock, one should ensure that when you acquire multiple locks, you always acquire the locks in the same order in all threads.

Deadlock Conditions

1. Mutual exclusion:
The resources involved must be unshareable.The resource is only used by current thread.
2. Hold and wait or partial allocation:
The processes must hold the resources they have already been allocated while waiting for other (requested) resources.
3. No pre-emption:
The processes must not have resources taken away while that resource is being used.
4. Resource waiting or circular wait:
A circular chain of processes exists: with each process holding resources which are currently being requested by the next process in the chain

Deadlock prevention works by preventing one of the four Coffman conditions from occurring.

* Removing the mutual exclusion condition means that no process will have exclusive access to a resource. This proves impossible for resources that cannot be spooled. But even with spooled resources, the deadlock could still occur. Algorithms that avoid mutual exclusion are called non-blocking synchronization algorithms.
* The hold and wait or resource holding conditions may be removed by requiring processes to request all the resources they will need before starting up (or before embarking upon a particular set of operations). This advance knowledge is frequently difficult to satisfy and, in any case, is an inefficient use of resources. Another way is to require processes to request resources only when it has none; First they must release all their currently held resources before requesting all the resources they will need from scratch. This too is often impractical. It is so because resources may be allocated and remain unused for long periods. Also, a process requiring a popular resource may have to wait indefinitely, as such a resource may always be allocated to some process, resulting in resource starvation.
* The no preemption condition may also be difficult or impossible to avoid as a process has to be able to have a resource for a certain amount of time, or the processing outcome may be inconsistent or thrashing may occur. However, the inability to enforce preemption may interfere with a priority algorithm. Preemption of a "locked out" resource generally implies a rollback, and is to be avoided since it is very costly in overhead. Algorithms that allow preemption include lock-free and wait-free algorithms and optimistic concurrency control. If a process holding some resources and requests for some another resource(s) that cannot be immediately allocated to it, the condition may be removed by releasing all the currently being held resources of that process.
* The final condition is the circular wait condition. Approaches that avoid circular waits include disabling interrupts during critical sections and using a hierarchy to determine a partial ordering of resources. If no obvious hierarchy exists, even the memory address of resources has been used to determine ordering and resources are requested in the increasing order of the enumeration. Dijkstra's solution can also be used.

What does re-entrant mean regarding intrinsic or explicit locks?

* Re-entrant means that locks are acquired on a per-thread rather than per-invocation basis. In Java, both intrinsic and explicit locks are re-entrant.

`sleep()` vs `wait()`

* sleep() is from Thread, while wait() is from Object
* sleep() will not release the lock, but wait() will
* sleep() an object resumes after a period of time, wait() can only be waken by notify()

How does thread communicate with each other?

* Object class wait(), notify() and notifyAll() methods allows threads to communicate about the lock status of a resource

Why wait(), notify() and notifyAll() methods have to be called from synchronized method or block?

* When a Thread calls wait() on any Object, it must have the monitor on the Object that it will leave and goes in wait state until any other thread call notify() on this Object. Similarly when a thread calls notify() on any Object, it leaves the monitor on the Object and other waiting threads can get the monitor on the Object. Since all these methods require Thread to have the Object monitor, that can be achieved only by synchronization, they need to be called from synchronized method or block.

What is volatile keyword in Java

* When we use volatile keyword with a variable, all the threads read it’s value directly from the memory instead of temporary registers. This makes sure that the value read is the same as in the memory.

Can we make array volatile in Java?
* Yes, you can make an array volatile in Java but only the reference which is pointing to an array, not the whole array content.

Which is more preferred – Synchronized method or Synchronized block?

* Synchronized block is more preferred way because it doesn't lock the Object, synchronized methods lock the Object and if there are multiple synchronization blocks in the class, even though they are not related, it will stop them from execution and put them in wait state to get the lock on Object.

How to guarantee the thread order?

* By using `join()`

```java
Thread t2 = new Thread(new Runnable()) {
  @Override
  public void run() {
    try {
      t1.join();
    } catch (InterruptedException e) {}
  }
}
```

Singleton

- Early creation

Pros:

* Thread safety without synchronization
* Easy to implement

Cons:

* Early creation of resource that might not be used in the application.
* The client application can’t pass any argument, so we can’t reuse it. For example, having a generic singleton class for database connection where client application supplies database server properties.

```java
class EarlyLoad {
    private static EarlyLoad instance = new EarlyLoad();

    private EarlyLoad() {};

    public static EarlyLoad getInstance() {
        return instance;
    }
}
```

- Lazy initialization 

```java
class LazyInit {
    private static LazyInit instance;

    private LazyInit() {};

    public static LazyInit getInstance() {
        if (instance == null) {
            instance = new LazyInit();
        }
        return instance;
    }
}
```

- Double-checked locking

```java
class DoubleCheck {
    // Use volatile to avoid partially constructed object situation: A half initialize it but B uses
    // it before A finish initialization
    private volatile DoubleCheck instance;

    private DoubleCheck() {

    }

    public DoubleCheck getInstance() {
        // Still need localRef since when we check null and return it, we don't need to get
        // access to the volatile variable instance anymore
        DoubleCheck localRef = instance;
        if (localRef == null) {
            // Obtain the lock.
            synchronized (this) {
                localRef = instance;
                // Double-check whether the variable has already been initialized: if another thread
                // acquired the lock first, it may have already done the initialization. If so,
                // return the initialized variable.
                if (localRef == null) {
                    instance = localRef = new DoubleCheck();
                }
            }
        }
        return localRef;
    }
}
```

## Server

REST vs SOAP

1. SOAP is a protocol whereas REST is architecture. REST is protocol independent. It's not coupled to HTTP
1. SOAP exposes behavior which represent logic whereas REST exposes resources
1. SOAP supports both POST and GET methods. However, GET includes the request in the query string. SOAP requests (XML messages) are usually too complex and verbose to be included in the query string, so almost every implementation (for example JAX-WS) supports only POST
1. SOAP can use almost any transport to send the request but REST uses HTTP/HTTPS
1. Any SOAP envelope can be used in REST services like generated token but not vice versa. This means that if you have created a token using SOAP then that token can be used in REST (under HTTP header manager section => Authorization). But you can not use REST envelopes in a SOAP request.
1. SOAP provides good security option. Although SOAP and REST both support SSL (Secure Socket Layer) for data protection, while making the request, SOAP supports Web Services Security for enterprise-level protection which offers protection from the creation of the message to it's consumption. Security and authentication in HTTP are standardized, so that's what you use when doing REST over HTTP
1. REST is Faster. The statelessness nature of REST makes it faster than a SOAP.
1. SOAP only support XML, but REST supports different format like text, JSON, XML

REST Principles

* Stateless: Each client request to the server requires that its state be fully represented. The server must be able to completely understand the client request without using any server context or server session state. It follows that all state must be kept on the client
* Cacheable: Cache constraints may be used, thus enabling response data to be marked as cacheable or not-cacheable. Any data marked as cacheable may be reused as the response to the same subsequent request.
* Uniform Interface: All components must interact through a single uniform interface

What is payload?

When data is sent over the Internet, each unit transmitted includes both header information and the actual data being sent. The header identifies the source and destination of the packet, __while the actual data is referred to as the payload__. In general, the payload is the data that is carried on behalf of an application and the data received by the destination system.

HTTP vs HTTPS

1. HTTP is also called "a stateless system", which means that it enables connection on demand
1. HTTP can be intercepted and potentially altered. Any data you enter into the site will be sent __plaintext__ and therefore susceptible to interception or eavesdropping
1. HTTPS is HTTP but content secured by Transport Layer Security (TLS)
1. SSL/TLS provides a secure channel between two machines or devices operating over the internet or an internal network

HTTPS handshake

<img src="https_handshake.png" alt="https handshake" title="JVM heap memory" width="500" height="400" />

* Public key: used for encrypt the plain text to convert it into cipher text. Having a public key we can verify a message is created by someone with private key (verify the source)
* Private key: used by receiver to decrypt the cipher text to read the message.
* Certificate = Website info + Signature. An SSL certificate is used to authenticate the identify of a website
* Signature = encrypt(hash(all details))
* Certificate Authority: A known CA can sign web server requests so that anyone with the CA's public key can verify that requests were signed it by the CA. Browsers have these known CA's public key embedded and verify the information from the Certificate is trustable. This is to avoid _Man-In-The-Middle_ attack and we can find it out the website we are talking is not youtube.com

<img src="https_handshake_cn.jpg" alt="https handshake" title="JVM heap memory" width="600" height="1200" />

1. Client sends the request to the server's port 443, including TLS version, a random number (random1), Key(RSA), supported Cipher (AES), Hash(HMAC-MD5).
2. Server responses with confirmed TLS version, another random number (random2), confirmed encryption method (RSA), and Certificate with _public key_.
3. After the client gets the response, client authorizes the certificate by checking its CA. If the ceritificat is trustable, the client generates __Pre-Master Key__ encrypted by the _public key_ and sends back to the server.
5. The server gets the __Pre-Master Key__  by decrypting the private key. Since both client and server now have the same random1, random2, pre-master key, they calculate the session-key and mac-key based on it (__Symmetric Key__).
6. All following messages are encrypted by this symmetric key.

Status codes

* 201 Created

The request has succeeded and a new resource has been created as a result. This is typically the response sent after POST requests, or some PUT requests.

* 202 Accepted

The request has been received but not yet acted upon. It is noncommittal, since there is no way in HTTP to later send an asynchronous response indicating the outcome of the request. It is intended for cases where another process or server handles the request, or for batch processing.

* 204 No Content

There is no content to send for this request, but the headers may be useful. The user-agent may update its cached headers for this resource with the new ones.

* Redirects (300–399)

* 400 Bad Request

The server could not understand the request due to invalid syntax.

* 401 Unauthorized
* 403 Forbidden
* 408 Request Timeout

SAML (Security Assertion Markup Language)

Security Assertion Markup Language (SAML) is an open standard that allows identity providers (IdP) to pass authorization credentials to service providers (SP

OIDC (OpenID Connect)

OpenID Connect (OIDC) is an identity layer built on top of the OAuth 2.0 framework. It allows third-party applications to verify the identity of the end-user and to obtain basic user profile information. OIDC uses JSON web tokens (JWTs), which you can obtain using flows conforming to the OAuth 2.0 specifications.

OAUTH

OAuth is an open standard for access delegation, commonly used as a way for Internet users to grant websites or applications access to their information on other websites but without giving them the passwords

WsFed

Web Services Federation (WS-Federation or WS-Fed) is part of the larger WS-Security framework and an extension to the functionality of WS-Trust. The features of WS-Federation can be used directly by SOAP applications and web services. WS-Fed is a protocol that can be used to negotiate the issuance of a token. You can use this protocol for your applications (such as a Windows Identity Foundation-based app) and for identity providers (such as Active Directory Federation Services or Azure AppFabric Access Control Service).

## Spring

What do you understand by Dependency Injection?

Dependency Injection design pattern allows us to remove the hard-coded dependencies and make our application loosely coupled, extendable and maintainable. We can implement dependency injection pattern to move the dependency resolution from compile-time to runtime.

What do you understand by Aspect Oriented Programming?

* Enterprise applications have some common cross-cutting concerns that are applicable to different types of Objects and application modules, such as logging, transaction management, data validation, authentication etc. In Object Oriented Programming, modularity of application is achieved by Classes whereas in AOP application modularity is achieved by Aspects and they are configured to cut across different classes methods.
* AOP takes out the direct dependency of cross-cutting tasks from classes that are not possible in normal object-oriented programming. For example, we can have a separate class for logging but again the classes will have to call these methods for logging the data.

What is Spring IoC Container?

Inversion of Control (IoC) is the mechanism to achieve loose-coupling between Objects dependencies. To achieve loose coupling and dynamic binding of the objects at runtime, the objects define their dependencies that are being injected by other assembler objects. Spring IoC container is the program that injects dependencies into an object and makes it ready for our use.

What are different scopes of Spring Bean?
There are five scopes defined for Spring Beans.

* Singleton: only one instance of the bean will be created for each container. This is the default scope for the spring beans
* Prototype: a new instance will be created every time the bean is requested.
* Request: this is same as prototype scope, however it’s meant to be used for web applications. A new instance of the bean will be created for each HTTP request.
* Session: a new bean will be created for each HTTP session by the container.
* Global-session: this is used to create global session beans for Portlet applications

Any Disadvantages of Spring boot?

- Spring MVC uses a Synchronous Programming model, where each request has been mapped to a thread and which is responsible to take the response back to the request socket
- Requests like, fetch data from a database, fetch a response from another application, file read/write etc., a request thread has to wait to get the desired response
- The request thread here is blocked, and there is no CPU utilization in this period
- Spring Webflux has been introduced as part of Spring 5, and with this, it started to support Reactive Programming. It uses an asynchronous programming model

How does Spring WebFlux work?

1. All requests are received on a unique socket, associated with a channel, known as `SocketChannel`. 
1. There is always a single EventLoop thread associated with a range of `SocketChannels`. So, all requests to that Sockets/SocketChannels are handed over to the same EventLoop. (By default, the application starts with as many EventLoops as it has CPU cores.)
1. Requests on the EventLoop go through a Channel Pipeline, where a number of Inbound channel handlers or WebFilters are configured for the required processing.
1. EventLoop delegates the request to a new Worker Thread. Worker Thread perform these long tasks.
1. After completion, it writes the response to a task and adds that in `ScheduledTaskQueue`.
1. EventLoop poll tasks in task queue `ScheduledTaskQueue`. If there is any, EventLoop again goes through a number of Outbound Channel handlers for configured processing. 
1. In the end, EventLoop handed back the response to the same SocketChannel/Socket.
Repeat Step 1 to Step 6 in a loop.
1. If there is no any completed tasks inside `ScheduledTaskQueue`, EventLoop continues to poll new requests at `SocketChannel`.

## Linux commands

* free: get memory usage
* uname: get current user
* pushd and popd: save current directory into memory (like a stack) and return back
* locate: quick find files, need to `updatedb` first
* find /etc/ -name *.conf
* df: disk free, get space
* du: disk usage per directory level
* vmstat: get summary info of os in near-real time
* ps aux: process status
* ifconfig: get IP address
* netstat: list all connection
* wget: download a file
* telnet: like ssh, to access remote computers 

## Relational Database

Inner join vs Outer join 

* An inner join finds and returns matching data from tables
* An outer join finds and returns matching data but also includes other rows for which no corresponding match. 

How many types of outer joins?

* Left Outer Join (or Left Join)
* Right Outer Join (or Right Join)
* Full Outer Join (or Full Join)

What are all the different normalizations?

* First Normal Form (1NF):
This should remove all the duplicate columns from the table. Creation of tables for the related data and identification of unique columns.

* Second Normal Form (2NF):
Meeting all requirements of 1NF. Single column primary key (no composite key)

* Third Normal Form (3NF):
This should meet all requirements of 2NF. Removing the columns which are not dependent on primary key constraints.

* Fourth Normal Form (4NF):
Meeting all the requirements of third normal form and it should not have multi- valued dependencies.

What is the difference between DELETE, TRUNCATE and DROP commands?

* DELETE command is used to remove rows from the table, and WHERE clause can be used for conditional set of parameters. Commit and Rollback can be performed after delete statement.

* TRUNCATE removes all rows from the table. Truncate operation cannot be rolled back.

* DROP command removes a table from the database and operation cannot be rolled back.

What is a relationship?

Database Relationship is defined as the connection between the tables in a database.

* One to One
* One to Many
* Many to One
* Self-Referencing
* (Many to Many should be converted to 2 Many to One tables and use a bridge table connecting them)

What are clustered and non-clustered Indexes?

* Clustered indexes is the index according to which data is physically stored on disk. Therefore, only one clustered index can be created on a given database table.

* Non-clustered indexes don’t define physical ordering of data, but logical ordering. Typically, a tree is created whose leaf point to disk records. B-Tree or B+ tree are used for this purpos

What is the difference between primary key and unique constraints?

* Primary key cannot have NULL value, the unique constraints can have NULL values. There is only one primary key in a table, but there can be multiple unique constrains.