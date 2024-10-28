package ru.nsu.berezin.hashtable;

public class HashTable<K, V> {

    private final static int INITIAL_CAPACITY = 4;
    private final static double LOAD_FACTOR = 0.75;

    private int size;
    private Bucket<K, V>[] buckets;

    public HashTable() {
        buckets = new Bucket[0];
        size = 0;
    }

    private int hash(K key) {
        return hash(key, buckets.length);
    }

    private int hash(K key, int length) {
        return key.hashCode() % length;
    }

    private int nextIndex(K key) {
        return nextIndex(key, buckets);
    }

    /**
     * Finds fitting index for the given key in the given buckets array. *
     * Indexed element will be either: null, key is null or key is equal to the
     * key of the element.
     */
    private int nextIndex(K key, Bucket<K, V>[] buckets) {
        int index = hash(key);
        int remembered = -1;
        while (buckets[index] != null){
            if (buckets[index].key == null) {
                remembered = index;
            } else if (buckets[index].key.equals(key)) {
                return index;
            }
            index = (index + 1) % buckets.length;
        }
        if (remembered != -1) {
            return remembered;
        }
        return index;
    }

    /**
     * Reallocates the buckets array if the load factor is exceeded.
     * Should be called when a new element is added to the table.
     */
    private void reallocate() {
        if (size > buckets.length * LOAD_FACTOR) {
            int newCapacity = Integer.min(buckets.length * 2, INITIAL_CAPACITY);
            Bucket<K, V>[] newBuckets = new Bucket[newCapacity];

            for (Bucket<K, V> bucket : buckets) {
                if (bucket != null && bucket.key != null) {
                    int index = nextIndex(bucket.key, newBuckets);
                    newBuckets[index] = bucket;
                }
            }
            this.buckets = newBuckets;
        }
    }

    public void put(K key, V value) {
        reallocate();
        int index = nextIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new Bucket<>(key, value);
        } else {
            buckets[index].key = key;
            buckets[index].value = value;
        }
        size++;
    }

    public V get(K key) {
        int index = nextIndex(key);
        if (buckets[index] == null || buckets[index].key == null) {
            return null;
        }
        return buckets[index].value;
    }

    public boolean contains(K key) {
        int index = nextIndex(key);
        return buckets[index] != null && buckets[index].key != null;
    }

    public int size() {
        return size;
    }
}
