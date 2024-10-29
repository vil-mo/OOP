package ru.nsu.berezin.hashtable;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * A hash table implementation.
 *
 * @param <K> Type of the keys.
 * @param <V> Type of the values.
 */
public class HashTable<K, V> {

    private int size;
    private Bucket<K, V>[] buckets;

    private final static int INITIAL_CAPACITY = 4;
    private final static double LOAD_FACTOR = 0.75;

    /**
     * Creates an empty hash table.
     */
    public HashTable() {
        buckets = new Bucket[0];
        size = 0;
    }

    /**
     * Finds fitting index for the given key in the given buckets array. Indexed
     * element will be either: null, key is null or key is equal to the passed
     * argument.
     * Buckets should have non 0 lenght or throws exception.
     */
    private int nextIndex(K key) {
        return nextIndex(key, buckets);
    }

    /**
     * Finds fitting index for the given key in the given buckets array. Indexed
     * element will be either: null, key is null or key is equal to the passed
     * argument.
     * Buckets should have non 0 lenght or throws exception.
     */
    private int nextIndex(K key, Bucket<K, V>[] buckets) {
        int index = key.hashCode() % buckets.length;
        int remembered = -1;
        while (buckets[index] != null) {
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
     * Reallocates the buckets array if the load factor is exceeded. Should be
     * called when a new element is added to the table.
     */
    private void reallocate() {
        if (size >= buckets.length * LOAD_FACTOR) {
            int newCapacity = Integer.max(buckets.length * 2, INITIAL_CAPACITY);
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

    /**
     * Add an element to the table. If the element already exists, the old value
     * is returned.
     *
     * @param key Key of the element to add.
     * @param value Value of the element to add.
     * @return Optional containing the old value if the element already exists,
     *     empty optional otherwise.
     */
    public Optional<V> put(K key, V value) {
        reallocate();
        int index = nextIndex(key);
        if (buckets[index] == null) {
            buckets[index] = new Bucket<>(key, value);
            size++;
            return Optional.empty();
        } else if (buckets[index].key == null) {
            buckets[index].key = key;
            buckets[index].value = value;
            size++;
            return Optional.empty();
        } else {
            var oldValue = buckets[index].value;
            buckets[index].value = value;
            return Optional.of(oldValue);
        }
    }

    /**
     * Get an element from the table.
     *
     * @param key Key of the element to get.
     * @return Optional containing the value of the element if it exists, empty
     *     optional otherwise.
     */
    public Optional<V> get(K key) {
        if (size == 0) {
            return Optional.empty();
        }

        int index = nextIndex(key);
        if (buckets[index] == null || buckets[index].key == null) {
            return Optional.empty();
        }
        return Optional.of(buckets[index].value);
    }

    /**
     * Remove an element from the table.
     *
     * @param key Key of the element to remove.
     * @return Optional containing the value of the element if it exists, empty
     *     optional otherwise.
     */
    public Optional<V> remove(K key) {
        if (size == 0) {
            return Optional.empty();
        }

        int index = nextIndex(key);
        if (buckets[index] == null || buckets[index].key == null) {
            return Optional.empty();
        }
        buckets[index].key = null;
        size--;
        return Optional.of(buckets[index].value);
    }

    /**
     * Check if the table contains the given key.
     *
     * @param key Key to check.
     * @return True if the table contains the key, false otherwise.
     */
    public boolean contains(K key) {
        if (size == 0) {
            return false;
        }

        int index = nextIndex(key);
        return buckets[index] != null && buckets[index].key != null;
    }

    /**
     * Amount of unique keys in the table.
     *
     * @return Amount of unique keys in the table.
     */
    public int size() {
        return size;
    }

    private class HashTableIterator implements Iterator<Bucket<K, V>> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            while (currentIndex < buckets.length) {
                if (buckets[currentIndex] != null && buckets[currentIndex].key != null) {
                    return true;
                }
                currentIndex++;
            }
            return false;
        }

        @Override
        public Bucket<K, V> next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return buckets[currentIndex++];
        }
    }

    /**
     * Returns an iterator over the elements in the table.
     *
     * @return An iterator over the elements in the table.
     */
    public Iterator<Bucket<K, V>> iterator() {
        return new HashTableIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (this == other) {
            return true;
        }

        HashTable<K, V> otherTable;

        try {
            otherTable = (HashTable<K, V>) other;
        } catch (ClassCastException e) {
            return false;
        }

        if (this.size != otherTable.size) {
            return false;
        }

        for (Bucket<K, V> bucket : buckets) {
            if (bucket != null && bucket.key != null) {
                Optional otherValue = otherTable.get(bucket.key);
                if (otherValue.isEmpty() || !otherValue.get().equals(bucket.value)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for (Bucket<K, V> bucket : buckets) {
            if (bucket != null && bucket.key != null) {
                hash += bucket.key.hashCode() ^ bucket.value.hashCode();
            }
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        boolean first = true;
        for (Bucket<K, V> bucket : buckets) {
            if (bucket != null && bucket.key != null) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(bucket.key).append(" : ").append(bucket.value);
                first = false;
            }
        }
        sb.append(" }");
        return sb.toString();
    }
}
