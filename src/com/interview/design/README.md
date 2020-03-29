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

- SQL vs NoSQL

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

Load Balancing Algorithems

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

### Sharding

We could share SQL over the IDs

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

