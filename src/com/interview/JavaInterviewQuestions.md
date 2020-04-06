# Java Interview Questions

## Core

| Type |  Size   | Range |
|:----:|:-------:|:-----:|
| byte | 8 bits | -2^7 ~ 2^47 -1 |
| char | 16-bit unsigned | 0 ~ 2^16 -1 |
| int  | 32 bits | -2^31 ~ 2^31 -1 |
| long | 64 bits | -2^63 ~ 2^63 -1 |

* What is protected access modifier?

Variables, methods and constructors which are declared protected in a superclass can be accessed only by the subclasses in other package or any class within the package of the protected members' class.

* Why is String class considered immutable?

The String class is immutable, so that once it is created a String object cannot be changed. Since String is immutable it can safely be shared between many threads ,which is considered very important for multithreaded programming.

* Exception

| Throwable |Throwable|Throwable|
| ----| ---- | --- |
| Exception |Exception| Error |
| Checked exceptions |Unchecked exceptions ||
| IOException | RuntimeException ||

* What is Polymorphism?

Polymorphism is the ability of an object to take on many forms. The most common use of polymorphism in OOP occurs when a parent class reference is used to refer to a child class object.

* What is Abstraction?

It refers to the ability to make a class abstract in OOP. It helps to reduce the complexity and also improves the maintainability of the system.

* What is Encapsulation?

It is the technique of making the fields in a class private and providing access to the fields via public methods. If a field is declared private, it cannot be accessed by anyone outside the class, thereby hiding the fields within the class. Therefore encapsulation is also referred to as data hiding.

* Differences between final and immutability

  * final means that you can’t change the object’s reference to point to another reference or another object, but you can still mutate its state (using setter methods e.g). Whereas immutable means that the object’s actual value can’t be changed, but you can change its reference to another one.
  * final modifier is applicable for variable but not for objects, Whereas immutability applicable for an object but not for variables.
By declaring a reference variable as final, we won’t get any immutability nature, Even though reference variable is final. We can perform any type of change in the corresponding Object. But we cant perform reassignment for that variable.
  * final ensures that the address of the object remains the same whereas the Immutable suggests that we can’t change the state of the object once created.

* What is the benefit of Composition over Inheritance?
  * Any change in the superclass might affect subclass even though we might not be using the superclass methods. For example, if we have a method test() in the subclass and suddenly somebody introduces a method test() in the superclass, we will get compilation errors in the subclass. The composition will never face this issue because we are using only what methods we need.
  * Inheritance exposes all the superclass methods and variables to the client and if we have no control in designing superclass, it can lead to security holes. Composition allows us to provide restricted access to the methods and hence more secure.

* Can a constructor be made final?

No

* Can constructor be inherited?

No, constructor cannot be inherited.

* What is the difference between inner class and nested class?

When a class is defined within a scope of another class, then it becomes inner class. If the access modifier of the inner class is static, then it becomes nested class.

## GC

* How to tell an object is garbage?

1. Reference Counting Collector

Pro: effective Con: cannot handle circulate referring

2. Tracing Collector

Starting from the root set, if an object cannot be accessible, ma

* How to collect memory?

1. Tracing Collector

2. Compacting Collector

3. Copying Collector

## Multi-thread

* What is the key difference between a process and a thread?

A process is an execution of a program but a thread is a single execution sequence within the process. A process can contain multiple threads.

A JVM runs in a single process and each process will have its own heap. Threads run within a process (i.e. a JVM process), and share the heap belonging to that process. This is why several threads may access the same object. Threads share the same heap and have their own stack space. This is how one thread’s invocation of a method and its local variables are kept thread safe from other threads.

* Explain different ways of creating a thread?

1. Extending the java.lang.Thread class.
2. Implementing the java.lang.Runnable interface.
3. Implementing the java.util.concurrent.Callable interface with the java.util.concurrent.Executor framework to pool the threads.

* Which approach would you favor and why?

Favor Callable interface with the Executor framework for thread pooling.

1. The thread pool is more efficient. Even though the threads are light-weighted than creating a process, creating them utilizes a lot of resources. Also, creating a new thread for each task will consume more stack memory as each thread will have its own stack and also the CPU will spend more time in context switching. Creating a lot many threads with no bounds to the maximum threshold can cause application to run out of heap memory. So, creating a Thread Pool is a better solution as a finite number of threads can be pooled and reused. The runnable or callable tasks will be placed in a queue, and the finite number of threads in the pool will take turns to process the tasks in the queue.

2. The Runnable or Callable interface is preferred over extending the Thread class, as it does not require your object to inherit a thread because when you need multiple inheritance, only interfaces can help you. Java class can extend only one class, but can implement many interfaces.

3. The Runnable interface’s void run( ) method has no way of returning any result back to the main thread. The executor framework introduced the Callable interface that returns a value from its call( ) method. This means the asynchronous task will be able to return a value once it is done executing.

* What is the difference between yield and sleep? What is the difference between the methods sleep() and wait( )?

When a task invokes yield( ), it changes from running state to runnable state. When a task invokes sleep( ), it changes from running state to waiting/sleeping state.

* What does re-entrancy mean regarding intrinsic or explicit locks?

Re-entrancy means that locks are acquired on a per-thread rather than per-invocation basis. In Java, both intrinsic and explicit locks are re-entrant.

* What is synchronization?

Synchronization is the capability to control the access of multiple threads to shared resources. synchronized keyword in java provides locking which ensures mutual exclusive access of shared resource and prevent data race.

* How does thread communicate with each other?

Object class wait(), notify() and notifyAll() methods allows threads to communicate about the lock status of a resource

* Why wait(), notify() and notifyAll() methods have to be called from synchronized method or block?

When a Thread calls wait() on any Object, it must have the monitor on the Object that it will leave and goes in wait state until any other thread call notify() on this Object. Similarly when a thread calls notify() on any Object, it leaves the monitor on the Object and other waiting threads can get the monitor on the Object. Since all these methods require Thread to have the Object monitor, that can be achieved only by synchronization, they need to be called from synchronized method or block.

* What is volatile keyword in Java

When we use volatile keyword with a variable, all the threads read it’s value directly from the memory and don’t cache it. This makes sure that the value read is the same as in the memory.

* Which is more preferred – Synchronized method or Synchronized block?

Synchronized block is more preferred way because it doesn’t lock the Object, synchronized methods lock the Object and if there are multiple synchronization blocks in the class, even though they are not related, it will stop them from execution and put them in wait state to get the lock on Object.

## Server

* REST vs SOAP
  1. SOAP is a protocol whereas REST is architecture. REST is protocol independent. It's not coupled to HTTP
  1. SOAP exposes behavior which represent logic whereas REST exposes resources
  1. SOAP supports both POST and GET methods. However, GET includes the request in the query string. SOAP requests (XML messages) are usually too complex and verbose to be included in the query string, so almost every implementation (for example JAX-WS) supports only POST
  1. . SOAP can use almost any transport to send the request but REST uses HTTP/HTTPS
  1. Any SOAP envelope can be used in REST services like generated token but not vice versa. This means that if you have created a token using SOAP then that token can be used in REST (under HTTP header manager section => Authorization). But you can not use REST envelopes in a SOAP request.
  1. SOAP provides good security option. Although SOAP and REST both support SSL (Secure Socket Layer) for data protection, while making the request, SOAP supports Web Services Security for enterprise-level protection which offers protection from the creation of the message to it’s consumption. Security and authentication in HTTP are standardized, so that's what you use when doing REST over HTTP
  1. REST is Faster. The statelessness nature of REST makes it faster than a SOAP.
  1. SOAP only support XML, but REST supports different format like text, JSON, XML

* REST Principles

  * Stateless: Each client request to the server requires that its state be fully represented. The server must be able to completely understand the client request without using any server context or server session state. It follows that all state must be kept on the client
  * Cacheable: Cache constraints may be used, thus enabling response data to be marked as cacheable or not-cacheable. Any data marked as cacheable may be reused as the response to the same subsequent request.
  * Uniform Interface: All components must interact through a single uniform interface

* What is payload?

When data is sent over the Internet, each unit transmitted includes both header information and the actual data being sent. The header identifies the source and destination of the packet, __while the actual data is referred to as the payload__. In general, the payload is the data that is carried on behalf of an application and the data received by the destination system.

* HTTP vs HTTPS

  1. HTTP is also called "a stateless system", which means that it enables connection on demand
  1. HTTP can be intercepted and potentially altered. Any data you enter into the site will be sent plaintext and therefore susceptible to interception or eavesdropping
  1. HTTPS is secured by Transport Layer Security (TLS)
  1. SSL/TLS provides a secure channel between two machines or devices operating over the internet or an internal network

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

## Spring

* What do you understand by Dependency Injection?

Dependency Injection design pattern allows us to remove the hard-coded dependencies and make our application loosely coupled, extendable and maintainable. We can implement dependency injection pattern to move the dependency resolution from compile-time to runtime.

* What do you understand by Aspect Oriented Programming?

Enterprise applications have some common cross-cutting concerns that are applicable to different types of Objects and application modules, such as logging, transaction management, data validation, authentication etc. In Object Oriented Programming, modularity of application is achieved by Classes whereas in AOP application modularity is achieved by Aspects and they are configured to cut across different classes methods.

AOP takes out the direct dependency of cross-cutting tasks from classes that are not possible in normal object-oriented programming. For example, we can have a separate class for logging but again the classes will have to call these methods for logging the data.

* What is Spring IoC Container?

Inversion of Control (IoC) is the mechanism to achieve loose-coupling between Objects dependencies. To achieve loose coupling and dynamic binding of the objects at runtime, the objects define their dependencies that are being injected by other assembler objects. Spring IoC container is the program that injects dependencies into an object and makes it ready for our use.

* What are different scopes of Spring Bean?
There are five scopes defined for Spring Beans.

  * Singleton
  
  Only one instance of the bean will be created for each container. This is the default scope for the spring beans. While using this scope, make sure spring bean doesn’t have shared instance variables otherwise it might lead to data inconsistency issues because it’s not thread-safe.

  * Prototype

  A new instance will be created every time the bean is requested.

  * Request

  This is same as prototype scope, however it’s meant to be used for web applications. A new instance of the bean will be created for each HTTP request.

  * Session

   A new bean will be created for each HTTP session by the container.

  * Global-session

  This is used to create global session beans for Portlet applications


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

