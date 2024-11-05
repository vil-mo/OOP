package ru.nsu.berezin.hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Tests {

    private HashTable<String, Integer> hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new HashTable<>();
    }

    @Test
    void testPutAndGet() {
        assertEquals(Optional.empty(), hashTable.put("key1", 1));
        assertEquals(Optional.of(1), hashTable.get("key1"));
        assertEquals(1, hashTable.size());
    }

    @Test
    void testUpdateExistingKey() {
        hashTable.put("key1", 1);
        assertEquals(Optional.of(1), hashTable.put("key1", 2));
        assertEquals(Optional.of(2), hashTable.get("key1"));
    }

    @Test
    void testContains() {
        hashTable.put("key1", 1);
        assertTrue(hashTable.contains("key1"));
        assertFalse(hashTable.contains("key2"));
    }

    @Test
    void testRemove() {
        hashTable.put("key1", 1);
        assertEquals(Optional.of(1), hashTable.remove("key1"));
        assertEquals(Optional.empty(), hashTable.get("key1"));
        assertFalse(hashTable.contains("key1"));
    }

    @Test
    void testRemoveNonExistentKey() {
        assertEquals(Optional.empty(), hashTable.remove("key1"));
    }

    @Test
    void testSize() {
        assertEquals(0, hashTable.size());
        hashTable.put("key1", 1);
        hashTable.put("key2", 2);
        assertEquals(2, hashTable.size());
        hashTable.remove("key1");
        assertEquals(1, hashTable.size());
    }

    @Test
    void testReallocate() {
        for (int i = 0; i < 10; i++) {
            hashTable.put("key" + i, i);
        }
        assertEquals(10, hashTable.size());
        for (int i = 0; i < 10; i++) {
            assertEquals(Optional.of(i), hashTable.get("key" + i));
        }
    }

    @Test
    void testEquality() {
        HashTable<String, Integer> table1 = new HashTable<>();
        HashTable<String, Integer> table2 = new HashTable<>();
        for (int i = 0; i < 5; i++) {
            table1.put("key" + i, i);
            table2.put("key" + i, i);
        }
        assertEquals(table1, table2);
        table2.put("newKey", 100);
        assertNotEquals(table1, table2);
    }

    @Test
    void testHashCode() {
        HashTable<String, Integer> table1 = new HashTable<>();
        HashTable<String, Integer> table2 = new HashTable<>();
        table1.put("key1", 1);
        table1.put("key2", 2);
        table2.put("key1", 1);
        table2.put("key2", 2);
        assertEquals(table1.hashCode(), table2.hashCode());

        table2.put("key3", 3);
        assertNotEquals(table1.hashCode(), table2.hashCode());
    }

    @Test
    void differentTypesEquals() {
        HashTable<String, Integer> table1 = new HashTable<>();
        HashTable<Integer, String> table2 = new HashTable<>();
        table1.put("key", 1);

        assertFalse(table1.equals(table2));
    }

    @Test
    void testToString() {
        hashTable.put("key1", 1);
        hashTable.put("key2", 2);
        String expected = "{ key1 : 1, key2 : 2 }";
        assertEquals(expected, hashTable.toString());
    }

    @Test
    void testIterator() {
        hashTable.put("key1", 1);
        hashTable.put("key2", 2);
        var iterator = hashTable.iterator();

        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());
        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());
        assertFalse(iterator.hasNext());
        iterator = hashTable.iterator();
        hashTable.put("key3", 3);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    void testIteratorThrowsNoSuchElementException() {
        var iterator = hashTable.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testGetNonExistentKey() {
        assertEquals(Optional.empty(), hashTable.get("nonexistent"));
    }

    @Test
    void testNextIndexResolvesCollisions() {
        hashTable.put("key1", 1);
        hashTable.put("key1", 2);
        assertEquals(Optional.of(2), hashTable.get("key1"));
    }

    @Test
    void testInitialSizeZero() {
        assertEquals(0, hashTable.size());
    }

    @Test
    void testPutWithResize() {
        for (int i = 0; i < 10; i++) {
            hashTable.put("key" + i, i);
        }
        assertEquals(10, hashTable.size());
    }
}
