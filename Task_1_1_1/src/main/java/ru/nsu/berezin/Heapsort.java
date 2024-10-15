package ru.nsu.berezin;

import java.util.Random;

/**
 * Class that has method for heapsort.
 */
public abstract class Heapsort {

    private static void heapify(int[] buf, int i, int size) {
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        int max = i;

        if (l < size && buf[l] > buf[max]) {
            max = l;
        }
        if (r < size && buf[r] > buf[max]) {
            max = r;
        }

        if (max != i) {
            int temp = buf[max];
            buf[max] = buf[i];
            buf[i] = temp;
            heapify(buf, max, size);
        }
    }

    /**
     * Heapsort uisng min binary heap.
     *
     *
     * @param array - Array that is being sorted. Mutates the passed array.
     */
    public static void heapsort(int[] array) {

        for (int i = array.length / 2 - 1; i >= 0; i--) {
            heapify(array, i, array.length);
        }

        for (int i = array.length - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, 0, i);
        }
    }

    /**
     * Does benchark.
     *
     * @param args - Main args.
     */
    public static void main(String[] args) {
        long sum = 0;
        Random random = new Random();
        for (int k = 1; k <= 10; k++) {
            for (int j = 1; j <= 1000; j++) {

                int[] array = new int[1000 * k];

                for (int i = 0; i < 1000 * k; i++) {
                    array[i] = random.nextInt();
                }

                long startTime = System.nanoTime();
                heapsort(array);
                long endTime = System.nanoTime();
                sum += endTime - startTime;
            }
            System.out.print(k + "000 elements: " + sum / 1000 + "\n");
        }
    }
}
