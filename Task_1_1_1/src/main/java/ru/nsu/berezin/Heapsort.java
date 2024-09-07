package ru.nsu.berezin;
import java.util.PriorityQueue;

/**
 * Class that has method for heapsort.
 */
public class Heapsort {
    private Heapsort() {}

    /**
     * Heapsort uisng `PriorityQueue`.
     * 
     * @param array array that is being sorted. Mutates the passed array
     */
    public static void heapsort(int[] array) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int x : array) {
            queue.add(x);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = queue.remove();
        }
    }

    /**
     * Made to be able to run script
     * @param args main args
     */
    public static void main(String[] args) { }
}
