# TinyUrl

## Scenario

* Generate TinyUrl from a long URL
* Get long URL from a tinyUrl
* TinyUrl expired after a period of time

## Service

### Generate TinyUrl

Character set: a-zA-Z0-9 = total 62

If the url has 7 characters, 62 ^ 7 = 3.5 trillion

2^42 = 4.4 trillion > 3.5 trillion, 42 bits long.

For example, we get a 42 bits long binary string, like "0 1 1 0 0 1 1" and then convert it into a decimal number, maybe "1453002023", then base 62, we can get such as "60 12 04 11", lastly convert into letters and digits

Two strategies:

* MD5: An MD5 hash is created by taking a string of an any length and encoding it into a 128-bit fingerprint. (Each hex character can represent 4 bits. 128/4 = 32 hex characters). Use MD5 and pick first 42 bits. High chance of collision. If an existing long URL comes in, return the generated the TinyUrl directly. If there is a collision, then adding some salt

* Counter: use a counter to generate a URL. Each server takes a range of counters, such as "1 ~ 1 million", "2 million ~ 3 million" until it exhaust 3.5 trillion URLs. Disadvantage: URL are predictable, we could append a random number

## Storage

Using MD5 approach:

1. Generate the TinyUrl
1. Check the TinyUrl against the database for collision
1. Put the mapping into the database
1. Get the long URL from the database and compare with the input to avoid re-written by another thread
1. If they are not same, re-generate it again

## Scale up

Use server cache the mapping from short URL to long URL

Use CDN to cache the mapping the in the local area
