import java.util.PriorityQueue;

public class Heapsort {
    public static void heapsort(int[] array) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int x : array) {
            queue.add(x);
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = queue.remove();
        }
    }
}
