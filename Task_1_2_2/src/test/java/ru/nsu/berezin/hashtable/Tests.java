package ru.nsu.berezin.hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;
import org.junit.jupiter.api.Test;

class Tests {

    @Test
    void put() {
        HashTable<String, Integer> table = new HashTable<>();
        table.put("key", 10);
        assertEquals(table.size(), 1);
        table.put("key", 20);
        assertEquals(table.size(), 1);
        assertEquals(table.get("key"), Optional.of(20));
        table.put("key3", 30);
        assertEquals(table.size(), 2);
        assertEquals(table.get("key3"), Optional.of(30));
        assertEquals(table.get("key"), Optional.of(20));
    }

    @Test
    void differentTypesEquals() {
        HashTable<String, Integer> table1 = new HashTable<>();
        HashTable<Integer, String> table2 = new HashTable<>();
        table1.put("key", 1);

        if (table1.equals(table2)) {
            fail();
        }
    }
}
