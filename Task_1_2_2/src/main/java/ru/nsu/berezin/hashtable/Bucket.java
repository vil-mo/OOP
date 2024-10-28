package ru.nsu.berezin.hashtable;

class Bucket<K, V> {
    K key;
    V value;

    Bucket(K key, V value) {
        this.key = key;
        this.value = value;
    }
}