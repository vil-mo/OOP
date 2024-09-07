package ru.nsu.berezin;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class Tests {
    @Test
    void small_array() {
        int[] my_arr = new int[] { 4, 1, 5 };
        Heapsort.heapsort(my_arr);
        assertArrayEquals(my_arr, new int[] { 1, 4, 5 });
    }

    @Test
    void big_array() {
        int[] my_arr = new int[] { 4, 45, 3, 1, 5, 10, 18, 6, 19, 5 };
        Heapsort.heapsort(my_arr);
        assertArrayEquals(my_arr, new int[] { 1, 3, 4, 5, 5, 6, 10, 18, 19, 45 });
    }

    @Test
    void negative() {
        int[] my_arr = new int[] { -4, -4, 5, -4, -1, -6, 0 };
        Heapsort.heapsort(my_arr);
        assertArrayEquals(my_arr, new int[] { -6, -4, -4, -4, -1, 0, 5 });
    }
}
