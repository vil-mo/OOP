package ru.nsu.berezin.primes;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class Tests {

    static List<Integer> test1 = Arrays.asList(new Integer[]{6, 8, 7, 13, 5, 9, 4});
    static List<Integer> test2 = Arrays.asList(new Integer[]{
        20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
        6998009, 6998029, 6998039, 20165149, 6998051, 6998053
    });

    @Test
    void sequential() {
        assertFalse(AllPrimes.sequential(test1));
        assertTrue(AllPrimes.sequential(test2));
    }

    @Test
    void hasNonPrimeThreads() throws InterruptedException {
        assertFalse(AllPrimes.threads(test1, 4));
        assertTrue(AllPrimes.threads(test2, 4));
    }

    @Test
    void hasNonPrimeParallel() {
        assertFalse(AllPrimes.parallelStream(test1));
        assertTrue(AllPrimes.parallelStream(test2));
    }

}
