package ru.nsu.berezin.primes;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Class for checking if all numbers in a list are primes.
 */
public class AllPrimes {

    /**
     * Checks if a number is prime.
     * 
     * @param n Number to check
     * @return True if the number is prime, false otherwise
     */
    public static boolean isPrime(int n) {
        int iterations = (int) Math.ceil(Math.sqrt((double) n));

        for (int i = 2; i < iterations; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if all numbers in a list are primes on a single thread.
     * 
     * @param array List of numbers to check
     * @return True if all numbers in the list are primes, false otherwise
     */
    public static boolean sequential(Iterable<Integer> array) {
        for (int x : array) {
            if (!isPrime(x)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if all numbers in a list are primes on multiple threads.
     * 
     * @param array List of numbers to check
     * @param threadAmount Amount of threads to use
     * @return True if all numbers in the list are primes, false otherwise
     * @throws InterruptedException If the threads are interrupted
     */
    public static boolean threads(List<Integer> array, int threadAmount) throws InterruptedException {
        Thread[] threads = new Thread[threadAmount];
        AtomicBoolean isPrime = new AtomicBoolean(true);

        for (int i = 0; i < threadAmount; i++) {
            final int index = i;

            threads[i] = new Thread(() -> {
                for (int j = index; j < array.size(); j += threadAmount) {
                    int x = array.get(j);
                    if (!isPrime(x)) {
                        isPrime.set(false);
                        return;
                    }
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        return isPrime.get();
    }

    /**
     * Checks if all numbers in a list are primes using the `Collection::parallelStream`.
     * 
     * @param array List of numbers to check
     * @return True if all numbers in the list are primes, false otherwise
     */
    public static boolean parallelStream(Collection<Integer> array) {
        return array.parallelStream().allMatch(AllPrimes::isPrime);
    }
}
