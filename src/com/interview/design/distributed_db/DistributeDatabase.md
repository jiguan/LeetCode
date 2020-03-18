# Design Distributed Database

[System Design : Distributed Database System Key Value Store](https://www.youtube.com/watch?v=rnZmdmlR-2M)

## Metadata Manager

- Leader election, in replication group
- mapping of table port to replication group

```txt
stack[A-C) -> RG1
stack[C-L) -> RG2
```

- ZooKeeper, etcd, redis
- Paxos, raft
- Data is not always consistent of network split, we will have more 
- Kill all this data in the memory of Request Manager(RM) so that RM won't talk to Metadata Manager so often
- Very critical data so backup as frequent as possible
- All nodes in RG heart beating the MM so that MM knows nodes are alive

## Replication Group

- Every operation goes through the leader
- If the leader node is down, we will have another node holding the same data
- Each node only has append-only log
- Use B+ tree (read heavy) or LSM tree (write heavy)
- (We have 3 nodes), one node follower node returns OK to the leader node, the replication group returns success for the operation since majority of nodes have the data
- Even a table is large, split it into different RGs

| table1 ||
| ---| --- |
| part1 | part2 |
| RG1 | RG2|

## Controller

- Leader Management: if the current leader doesn't heart beat the MM, the remaining follower nodes will try to be the leader
- Follower falling behind. If there is a follower haven't updated for a while. Controller will replace the node with a new one from the pool of available nodes
- Split hot tables. Hot tables are the table whose size of table grows fast, or the IO input/output is very high.
  1. Controller finds which RG should store the new split or create a new RG
  2. Update the MM so that the request goes to the new RG (eventually the change will reach RM). In the meanwhile, controller notifies the current NG to redirect all write operations to the new one.
  3. Controller copies data from current RG to new one. If during the migration, a query comes in, RM will query both RGs.

## Capacity

- Replication Group: 5TB per node
- Limits on key/value: 1 MB
- Overhead size for one record: 30 bytes (16 bytes sequencer, 20 bytes for index data analysis)
- I/O: 2000 rps per replication group

## Potential issues

### Two leaders

When a new leader is generated, the new leader will talk with other nodes within the same RG. If the majority of nodes accepts it, this new leader is accepted. However, if the old leader gets a  request and tries to talk with other nodes, other nodes will reject it

### No leader

If the leader node is down, this is the Availability issue. We could try to be more aggressive. Make the heart beating more often

### Network splits at Metadata Manager / Outdated table of Metadata Manager

Metadata Manager has a leader and couple of follower nodes. If there is a firewall between some nodes, they may not be updated. If the leader of RG is changed, the request will be rejected. A force update will be done

### Nodes go down before updating the B+ tree or LSM tree

It is OK, we can re-apply the append-only log on the disk

### Bad Metadata Manager

Request Manager will get the wrong info. Replication Group will reject the request with incorrect access info.

### Bad nodes in Replication Group

It is OK since we return the record from __MAJORITY__ of the nodes
