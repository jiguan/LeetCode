package com.leetcode.implement;

import java.util.ArrayList;

// https://www.geeksforgeeks.org/load-factor-and-rehashing/
public class ImplementHashMap {

    public static void main(String[] args) {

        // Creating the Map
        CustomMap<Integer, String> map = new CustomMap<Integer, String>();

        // Inserting elements
        map.insert(1, "Geeks");
        map.printMap();

        map.insert(2, "forGeeks");
        map.printMap();

        map.insert(3, "A");
        map.printMap();

        map.insert(4, "Computer");
        map.printMap();

        map.insert(5, "Portal");
        map.printMap();
    }
}

class CustomMap<K, V> {

    class MapNode<K, V> {

        K key;
        V value;
        MapNode<K, V> next;

        public MapNode(K key, V value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    // The bucket array where
    // the nodes containing K-V pairs are stored
    ArrayList<MapNode<K, V>> buckets;

    // No. of pairs stored - n
    int size;

    // Size of the bucketArray - b
    int numBuckets;

    // Default loadFactor
    final double DEFAULT_LOAD_FACTOR = 0.75;

    public CustomMap() {
        numBuckets = 5;

        buckets = new ArrayList<>(numBuckets);

        for (int i = 0; i < numBuckets; i++) {
            // Initialising to null
            buckets.add(null);
        }
        System.out.println("HashMap created");
        System.out.println("Number of pairs in the Map: " + size);
        System.out.println("Size of Map: " + numBuckets);
        System.out.println("Default Load Factor : " + DEFAULT_LOAD_FACTOR + "\n");
    }

    private int getBucketInd(K key) {

        // Using the inbuilt function from the object class
        int hashCode = key.hashCode();

        // array index = hashCode%numBuckets
        return (hashCode % numBuckets);
    }

    public void insert(K key, V value) {
        // Getting the index at which it needs to be inserted
        int bucketInd = getBucketInd(key);

        // The first node at that index
        MapNode<K, V> head = buckets.get(bucketInd);

        // First, loop through all the nodes present at that index
        // to check if the key already exists
        while (head != null) {

            // If already present the value is updated
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        // new node with the K and V
        MapNode<K, V> newElementNode = new MapNode<K, V>(key, value);

        // The head node at the index
        head = buckets.get(bucketInd);

        // the new node is inserted
        // by making it the head
        // and it's next is the previous head
        newElementNode.next = head;

        buckets.set(bucketInd, newElementNode);

        System.out.println("Pair(" + key + ", " + value + ") inserted successfully.\n");

        // Incrementing size
        // as new K-V pair is added to the map
        size++;

        // Load factor calculated
        double loadFactor = (1.0 * size) / numBuckets;

        System.out.println("Current Load factor = " + loadFactor);

        // If the load factor is > 0.75, rehashing is done
        if (loadFactor > DEFAULT_LOAD_FACTOR) {
            System.out.println(loadFactor + " is greater than " + DEFAULT_LOAD_FACTOR);
            System.out.println("Therefore Rehashing will be done.\n");

            // Rehash
            rehash();

            System.out.println("New Size of Map: " + numBuckets + "\n");
        }

        System.out.println("Number of pairs in the Map: " + size);
        System.out.println("Size of Map: " + numBuckets + "\n");
    }

    private void rehash() {

        System.out.println("\n***Rehashing Started***\n");

        // The present bucket list is made temp
        ArrayList<MapNode<K, V>> temp = buckets;

        // New bucketList of double the old size is created
        buckets = new ArrayList<MapNode<K, V>>(2 * numBuckets);

        for (int i = 0; i < 2 * numBuckets; i++) {
            // Initialised to null
            buckets.add(null);
        }
        // Now size is made zero
        // and we loop through all the nodes in the original bucket list(temp)
        // and insert it into the new list
        size = 0;
        numBuckets *= 2;

        for (int i = 0; i < temp.size(); i++) {

            // head of the chain at that index
            MapNode<K, V> head = temp.get(i);

            while (head != null) {
                K key = head.key;
                V val = head.value;

                // calling the insert function for each node in temp
                // as the new list is now the bucketArray
                insert(key, val);
                head = head.next;
            }
        }

        System.out.println("\n***Rehashing Ended***\n");
    }

    public void printMap() {

        // The present bucket list is made temp
        ArrayList<MapNode<K, V>> temp = buckets;

        System.out.println("Current HashMap:");
        // loop through all the nodes and print them
        for (int i = 0; i < temp.size(); i++) {

            // head of the chain at that index
            MapNode<K, V> head = temp.get(i);

            while (head != null) {
                System.out.println("key = " + head.key + ", val = " + head.value);

                head = head.next;
            }
        }
        System.out.println();
    }
}
