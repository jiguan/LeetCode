# System Design

- Scenario
- Service
- Storage
- Scale

## Scenario

- How many daily active users (DAU)?

100M DAU

- How many query per second? (QPS)?

Let's assume, on average, 0.1 modification per user.

100M * 0.1 / 24 / 3600 = 100

Peak = 100 * 3 = 300

On average, 100 query per user

100M * 100 / 24 / 3600 = 100K

Peak = 100 * 3 = 300K

## Service

Such as AuthService, UserService, etc

### AuthService

1. Log in and create a `session_key`
2. Server returns `session_key` as cookie to the browser
3. Every time the browser sends a request attaching with __ALL__ cookies
4. Every time the server validates the `session_key`
5. If the auth service is down, all users will be logged out and try to log in. Use DB and cache to avoid peak

| Column |
|--------|
| session_key |
| user_id |
| expire_at |

## Storage

| Database | QPS | Desc |
| -------- | --- | ---- |
| MySql | 1K | SQL |
| MongoDB / Cassandra | 10 K | NoSQL on disk |
| Redis / Memcached | 1M | NoSQL in mem |

### SQL vs NoSQL

| Feature        | SQL | NoSQL | Desc |
|----------------|:---:|:-----:|------|
| Transaction    | Yes |  No   ||
| Rich query     | Yes | No    ||
| Sequential ID  | Yes | No    ||
| Distributed    | No  | Yes   | Centralized MySQL + Distributed Memcached |
| Second index   | Yes | No    |Most NoSQL don't support |
| Data           | Structural info | key - value | |

### NoSQL

NoSQL is good for

- Insert and retrieve the whole block
- Flexible schema
- Scale up
- Aggregation stats, e.g. average age

NoSQL is bad for

- A lot of update
- High requirement on data consistency (ACID)
- Read on practical data
- Relationship is required
- Join tables

NoSQL: key - value, e.g. log

- Level 1: row_key (hash_key). No range query
- Level 2: column_key, range query feasible; can be combined key

```cassandra
query(row_key, column_start, column_end)
```

- Level 3: value, usually it is `string`

### SQL

Reliability or ACID Compliancy (Atomicity, Consistency, Isolation, Durability): The vast majority of relational databases are ACID compliant. So, when it comes to data reliability and safe guarantee of performing transactions, SQL databases are still the better bet.

- Cache Aside, e.g. Memcached + MySql

Cache <=> Web Server <=> Database

- Cache through, e.g. Redis

Web Server <=> Cache <=> Database


## Scale

### Load Balancer

Nginx, is using non-threaded, event driven architecture

- Revere proxying
- Caching
- Load Balancing

Nginx architecture
<img src="nginx_arch.png" alt="nginx arch" title="Nginx architecture" width="944" height="587" />

- One or more worker process per CUP core


Load Balancing Algorithm

- Random
- Round robin
- Load based

### Caching

Cache policy

- Least Recently Used (LRU)
- Least Frequently Used (LFU)

Disadvantage

- Data consistency
- Extra call to check cache
- Thrashing: keep putting data in and out of cache if the cache is small

Cache close to the server:

- Faster
- Inconsistent data over different servers
- A server goes down, so is its cache

Cache close to the database:

- Global cache
- Data consistency
- No associated with a certain server
- Scale up independently

Write through: update the cache and then update the database

Write back: kick current value out of the database and update the database. When the next request comes in, it will read the latest value and save it into the cache

- Updating is expensive since it needs to hit the DB
- Works well when there are distributed caches
- Works well for critical data

#### Redis vs Memcached

| Feature | Memcached | Redis |
| ------- | --------- | ----- |
| Advanced data structures (1) | N | Y |
| Multithreaded architecture (2) | Y | N |
| Snapshots (3) | N | Y |
| Replication (4) | N | Y |
| Transactions (5) | N | Y |
| Message Queue (6) | N | Y |

1. In addition to strings, Redis supports lists, sets, sorted sets, hashes, bit arrays, and hyperloglogs. Applications can use these more advanced data structures to support a variety of use cases. For example, you can use Redis Sorted Sets to easily implement a game leaderboard that keeps a list of players sorted by their rank.
1. Since Memcached is multithreaded, it can make use of multiple processing cores. This means that you can handle more operations by scaling up compute capacity. While Redis only uses single cores while Memcached utilizes multiple cores. 
1. With Redis you can keep your data on disk with a point in time snapshot which can be used for archiving or recovery.
1. Redis lets you create multiple replicas of a Redis primary. This allows you to scale database reads and to have highly available clusters.
1. Redis supports transactions which let you execute a group of commands as an isolated and atomic operation.
1. Redis supports Pub/Sub messaging with pattern matching which you can use for high performance chat rooms, real-time comment streams, social media feeds, and server intercommunication.


### Sharding

We could shard SQL over the IDs

Downsides:

- Complex queries, e.g. range query
- Join becomes unavailable

#### Consistent Hashing

- Try the whole hash as a loop
- The loop size is 2^64 (0 ~ 2^64 - 1)
- Micro shareds / Virtual nodes
- Each virtual node is a point in the hash loop
- Every time we add a new machine, distribute 1000 as virtual nodes
- When we need to know the the server storing the key-value
  - Calculate the hash value
  - Find the first new node in clock-wise direction
  - The virtual node is the server
- When we migrate the data
  - 1000 virtual nodes move to next one


### Replica

Master Slave is for SQL. Master handles __Write__ operation, then propagates data to Slaves. Slave handles __Read__ operation

Downsides:

- Write speed is not improved (only 1 master)
- Replication log

Write Ahead log

Any operation is recorded in the log. Every time there is an operation on master, master will notify slaves to update their data according to the log

NoSQL saves 3 copies on the consistent Hashing loop

- Vertical Sharding
Distribute multiple tables into multiple servers

- Horizontal Sharding

### Message Queue

How microservices communicate with each other?

Synchronous: 
- Caller waits for a response before sending the next message, and it operates as a REST protocol on top of HTTP

Asynchronous: 
1. asynchronous communication is non-blocking by definition.
1. It also supports better scaling than Synchronous operations.
1. In the event Microservice crashes, Asynchronous communication mechanisms provide various recovery techniques and is generally better at handling errors pertaining to the crash.
1.  when using brokers instead of a REST protocol, the services receiving communication don’t really need to know each other. A new service can even be introduced after an old one has been running for a long time, i.e better decoupling services.

| Feature | RabbitMQ | Kafka | Redis |
| ------- | -------- | ----- | ----- |
| Scale | 50k msg/s  | 1m msg/s | 1m msg/s |
| Persistency | Both | Yes | No |
| Message Retention | Acknowledgment based | Policy-Based (e.g. 7 days) |  Published messages are passed directly to subscribers and then dropped |
| Transactions | No| Yes | 
| Use Case | Complex Routing | Large Amounts of Data | Short-lived Messages. The latency is very high for larger messages (>1 MB) | 
| Subscription | push | pull | push |
| Design |  smart broker / dumb consumer |  dumb broker / smart consumer |  |  
| Parallelism | multithread | multithread | singlethread |
| Data Flow | order by receiver | order by sender | |


- RabbitMQ guarantees message delivery. Redis doesn’t guarantee message delivery while using its pub/sub mechanism. If a subscriber isn’t active, it won’t receive the messages it subscribed to
- RabbitMQ allows you to use an additional layer of security by using SSL certificates to encrypt your data
- Producers publish a message to a Redis Channel. There are two kinds of channels supported by Redis: __Literal Channel__ and __Pattern-Match Channel__.

## Topics

- Design a library system (Chewy)
- Design a logging system (OCI)
- Design a system to upload, search and download resumes (Indeed)

