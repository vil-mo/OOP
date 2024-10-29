package ru.nsu.berezin.hashtable;

/**
 * An entry in the hash table.
 * 
 * @param <K> Type of the key.
 * @param <V> Type of the value.
 */
public class Bucket<K, V> {
    K key;
    V value;

    Bucket(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Get the key of the bucket.
     * 
     * @return Key of the bucket.
     */
    public K key() {
        return key;
    }

    /**
     * Get the value of the bucket.
     * 
     * @return Value of the bucket.
     */
    public V value() {
        return value;
    }
}