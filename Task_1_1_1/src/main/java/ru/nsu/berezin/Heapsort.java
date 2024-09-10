package ru.nsu.berezin;

/**
 * Class that has method for heapsort.
 */
public class Heapsort {

    private Heapsort() {
    }

    static private int parent(int i) {
        return (i - 1) / 2;
    }

    static private int left(int i) {
        return 2 * i + 1;
    }

    static private int right(int i) {
        return 2 * i + 2;
    }

    static private void heapify(int[] buf, int i, int size) {
        int l = left(i);
        int r = right(i);

        int min = i;

        if (l < size && buf[l] < buf[min]) {
            min = l;
        }
        if (r < size && buf[r] < buf[min]) {
            min = r;
        }

        if (min != i) {
            int temp = buf[min];
            buf[min] = buf[i];
            buf[i] = temp;
            heapify(buf, min, size);
        }
    }

    /**
     * Heapsort uisng min binary heap.
     *
     *
     * @param array - Array that is being sorted. Mutates the passed array.
     */
    public static void heapsort(int[] array) {
        int[] buf = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            int item = array[i];

            while (i != 0 && item < buf[parent(i)]) {
                buf[i] = buf[parent(i)];
                i = parent(i);
            }

            buf[i] = item;
        }

        int size = array.length;
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = buf[0];

            buf[0] = buf[size - 1];
            size--;
            heapify(buf, 0, size);

            size -= 1;
        }

        array[array.length - 1] = buf[0];
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
