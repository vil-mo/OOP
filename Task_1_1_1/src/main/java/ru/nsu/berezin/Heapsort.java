package ru.nsu.berezin;

/**
 * Class that has method for heapsort.
 */
public abstract class Heapsort {

    static private void heapify(int[] buf, int i, int size) {
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
     * Made to be able to run script.
     *
     *
     * @param args - Main args.
     */
    public static void main(String[] args) {
    }
}
