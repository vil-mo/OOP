package ru.nsu.berezin.primes;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

public class AllPrimes {

    private static boolean isPrime(int n) {
        int iterations = (int) Math.ceil(Math.sqrt((double) n));

        for (int i = 2; i < iterations; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean singleThread(Iterable<Integer> array) {
        for (int x : array) {
            if (!isPrime(x)) {
                return false;
            }
        }

        return true;
    }

    public static boolean threads(int[] array, int threadAmount) throws InterruptedException {
        Thread[] threads = new Thread[threadAmount];
        AtomicBoolean isPrime = new AtomicBoolean(true);

        for (int i = 0; i < threadAmount; i++) {
            final int index = i;

            threads[i] = new Thread(() -> {
                for (int j = index; j < array.length; j += threadAmount) {
                    int x = array[j];
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

    public static boolean parallelStream(Collection<Integer> array) {
        return array.parallelStream().allMatch(AllPrimes::isPrime);
    }
}
