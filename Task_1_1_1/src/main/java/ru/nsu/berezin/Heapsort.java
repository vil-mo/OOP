package ru.nsu.berezin;

/**
 * Class that has method for heapsort.
 */
public class Heapsort {
    private Heapsort() {}

    /**
     * Heapsort uisng `BinaryHeap`.
     * 
     * @param array array that is being sorted. Mutates the passed array
     */
    public static void heapsort(int[] array) {
        BinaryHeap heap = new BinaryHeap(array.length);
        for (int x : array) {
            heap.add(x);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.remove();
        }
    }

    /**
     * Made to be able to run script
     * @param args main args
     */
    public static void main(String[] args) { }
}
