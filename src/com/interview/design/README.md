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

NoSQL: key - value, e.g. log

- Level 1: row_key (hash_key). No range query
- Level 2: column_key, range query feasible; can be combined key

```cassandra
query(row_key, column_start, column_end)
```

- Level 3: value, usually it is `string`

SQL: structural info => class

- Cache Aside, e.g. Memcached + MySql

Cache <=> Web Server <=> Database

- Cache through, e.g. Redis

Web Server <=> Cache <=> Database

## Scale

### Sharding

No sharding in SQL

#### Consistent Hashing

% n where n is a large number