package ru.nsu.berezin;

/**
 * Implementation of min binary heap.
 * Faster than `PriorityQueue`, because it stores `int` derectly,
 * without boxing values.
 * 
 * It also doesn't grow it's size - it alway has specified capasity.
 * 
 * For the current task it is not neccecarry to implement collection interfaces.
 */
public class BinaryHeap {
    int[] buf;
    int size;

    /**
     * New instance of heap with capasity. It cannot grow it's capasity.
     * @param capasity capasity of heap
     */
    public BinaryHeap(int capasity) {
        buf = new int[capasity];
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    /**
     * Add item to the heap. Executes in log(n)
     * 
     * @param item item to add
     */
    public void add(int item) {
        int i = size;
        size += 1;

        while (i != 0 && item < buf[parent(i)]) {
            buf[i] = buf[parent(i)];
            i = parent(i);
        }

        buf[i] = item;
    }
    private void restore(int i) {
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
            restore(min);
        }
    }

    /**
     * remove minimal element of the heap
     * 
     * @return minimal element of the heap
     */
    public int remove() {
        if (size == 1) {
            size--;
            return buf[0];
        }

        int root = buf[0];

        buf[0] = buf[size - 1];
        size--;
        restore(0);

        return root;
    }
}
