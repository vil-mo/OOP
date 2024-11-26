package ru.nsu.berezin.zachetka;

import java.util.Map;
import java.util.function.Function;

/**
 * A configuration of a book.
 */
public class BookConfig {
    private final int semesters;
    private final Map<String, int[]> monitorings = new java.util.HashMap<>();

    public BookConfig(int semesters) {
        this.semesters = semesters;
    }

    public int semesterAmount() {
        return semesters;
    }

    /**
     * Adds a monitoring type with the given amount in each semester.
     *
     * @param name the name of the monitoring type
     * @param amount map from semester to amount in this semester
     */
    public void addMonitoring(String name, Function<Integer, Integer> amount) {
        int[] values = new int[semesters + 1];
        for (int i = 1; i <= semesters; i++) {
            values[i] = amount.apply(i);
            values[0] += values[i];
        }
        monitorings.put(name, values);
    }

    /**
     * Returns the amount of monitoring of the given type in a semester.
     *
     * @param name the name of the monitoring type
     * @return the amount of monitoring of the given type in each semester
     */
    public int monitoringAmount(String name, int semester) throws IllegalArgumentException {
        if (semester < 1 || semester > semesters) {
            throw new IllegalArgumentException("Invalid semester: " + semester + ". Should be in range of [1, " + semesters + "]");
        }
        int[] values = monitorings.get(name);
        if (values == null) {
            throw new IllegalArgumentException("No monitoring of type " + name);
        }

        return values[semester];
    }
}