package ru.nsu.berezin.substring;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that hs function to search a substring in a string.
 */
public class SearchSubstring {

    private SearchSubstring() {
    }

    /**
     * Searches all occurrences of a substring in a file.
     *
     * @param path - path to the file
     * @param substr - substring to search
     * @return list of all occurrences of the substring in the file
     * @throws IOException - if the file cannot be read
     */
    public static List<Long> search(String path, String substr) throws IOException {
        if (substr.isEmpty()) {
            return new ArrayList<>();
        }

        List<Long> result = new ArrayList<>();

        Reader reader = new BufferedReader(new FileReader(path));

        long index = 0;
        long substrStartIndex = 0;
        int substrIndex = 0;
        int character;
        while ((character = reader.read()) != -1) {
            char ch = (char) character;
            if (ch == substr.charAt(substrIndex)) {
                if (substrIndex == 0) {
                    substrStartIndex = index;
                }
                substrIndex++;
                if (substrIndex == substr.length()) {
                    result.add(substrStartIndex);
                    substrIndex = 0;
                }
            } else {
                substrIndex = 0;
            }

            index++;
        }

        return result;
    }
}
