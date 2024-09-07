package ru.nsu.berezin;

/**
 * Class that has method for heapsort.
 */
public class Heapsort {

    private Heapsort() {
    }

    /**
     * Heapsort uisng min binary heap.
     * 
     * @param array - Array that is being sorted. Mutates the passed array.
     */
    public static void heapsort(int[] array) {
        class MinBinaryHeap {
            int[] buf;
            int size;

            public MinBinaryHeap(int capasity) {
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

            public int remove() {
                if (size == 1) {
                    size--;
                    return buf[0];
                }

                final int root = buf[0];

                buf[0] = buf[size - 1];
                size--;
                restore(0);

                return root;
            }
        }

        MinBinaryHeap heap = new MinBinaryHeap(array.length);
        for (int x : array) {
            heap.add(x);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = heap.remove();
        }
    }

    /**
     * Made to be able to run script.
     * 
     * @param args - Main args.
     */
    public static void main(String[] args) {
    }
}
