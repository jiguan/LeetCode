# Design Autocomplete for Google search

## Service

API: phrase -> weight

| Phrase | Timestamp | Sum of weights |
| :---: | :---: | :---: |
| bat | Nov 1 | 11015 |
| bat | Nov 2 | 21106 |
| bat | Nov 3 | 4326 |

## Storage

* Use trie tree
* Each node storing the top K frequent words

```text
           b {bat, bit}
        /      \
a {bat, bath}  i {bit, bill}
...
```

## Scale up

Use cache

Use multiple servers to store same trie tree to avoid failures

* a-$: S1, S2, S3

One trie could be huge. Divide into different trie trees starting from different letters and popular queries (e.g. bc is popular)

* a-bc: S1, S2, S3
* bc-k: S7, S8, S9
* k-$: S4, S5, S6

We could return more results pre-action, such as returning the result for "bat", "bad" when the user just enters "ba"
