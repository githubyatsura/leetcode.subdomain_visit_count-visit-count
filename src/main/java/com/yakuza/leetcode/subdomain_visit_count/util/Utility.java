package com.yakuza.leetcode.subdomain_visit_count.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

/**
 * Utilities.
 */
public class Utility {

    private Utility() {
    }

    /**
     * Just line handler for text file handling line by line.
     */
    public interface ILineHandler {
        void handleLine(String lineValue);
    }

    /**
     * Reads text file line by line.
     *
     * @param path            path to target file
     * @param encoding        expected file encoding
     * @param nextLineHandler text file line handler
     * @throws IOException
     */
    public static void readFile(String path, String encoding, ILineHandler nextLineHandler) throws IOException {
        try (LineIterator lineIterator = FileUtils.lineIterator(new File(path), encoding)) {
            while (lineIterator.hasNext()) {
                nextLineHandler.handleLine(lineIterator.nextLine());
            }
        }
    }
}
