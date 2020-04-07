# Uber

## Scenario

Assume 200k drivers online, every driver sends location info every 4 seconds: QPS = 200k / 4 = 50k/s

Peak Driver QPS: 50k/s * 3 = 150k/s

Save every location info: 200k / 4 * 86400 * 100 bytes (every record space size) = 0.5T/day

Only save current location: 200k * 100 bytes = 20 M

## Service

TripService

* Receive ride request (customer)
* Read any pending request (driver)

GeoService

* Get all drivers around the user's location

Google S2: convert a space into a 2^64 integer, e.g. (-30.043, -51.140) -> 1074377035713...

Geohash: base32(0-9a-z, but skip a,i,l,o), 32 is 2^5 so we could use binary search. The longer prefix matches, the closer they are

## Storage

```java
class Trip {
    int tripId;
    Integer driverId, riderId;
    Double latitude, longitude;
    Integer status;
    Datetime createTime;
}
class Location {
    int driverId;
    Double latitude, longitude;
    Datetime updateTime;
}
```

SQL

Store data cannot be represented by key - value, and not write intensive operation

* Trip table

Check trip by id, check driver's history

| TripId | RiderId | DriverId | lat | lng | status | createTime |
| --- | --- | --- | --- | --- | --- | --- |
| trip123 | r455| d334 | -30.01 | -51.34 | booked | Apr.4 |

* User table

NoSQL - Redis (whose value support list and set)

Use NoSQL since we need to write location very often

* Location table

If the driver's location is 9q9hvt, then save 9q9hvt, 9q9hv, 9q9h three keys

| key | value |
| --- | ----- |
| geohash | {drive1_id, driver2_id} |

* Driver table

| key | value |
| --- | ----- |
| driver_id | (lat, lng, status, updateTime, tripId) | |

## Scale up

Redis sharding based on location

Replica

* Master - Slave
* Replica by yourself, duplicate 3 copies and retrieve from any of them
