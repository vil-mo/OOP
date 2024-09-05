import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;


public class Tests {
    @Test
    void first() {
        int[] my_arr = new int[] {4, 1, 5};

        Heapsort.heapsort(my_arr);

        assertArrayEquals(my_arr, new int[] {1, 4, 5});
    }

    @Test
    void second() {
        int[] my_arr = new int[] {4, 3, 1, 5};

        Heapsort.heapsort(my_arr);

        assertArrayEquals(my_arr, new int[] {1, 3, 4, 5});
    }
}
