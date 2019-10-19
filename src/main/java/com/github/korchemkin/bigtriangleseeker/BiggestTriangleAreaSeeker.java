package com.github.korchemkin.bigtriangleseeker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * Finds the largest triangle area by coordinates in the input file.
 */
class BiggestTriangleAreaSeeker {
    /**
     * The largest triangle area in the input file.
     */
    private double biggestTriangleArea;

    String getDesiredLine() {
        return desiredLine;
    }

    /**
     * Searching result.
     */
    private String desiredLine = "";
    /** */
    private static final Logger LOGGER = LoggerFactory.getLogger(BiggestTriangleAreaSeeker.class);
    /**
     *
     * The coordinates of the first vertex of a triangle x1, y1.
     * Second vertex x2, y2.
     * Third vertex x3, y3.
     * The calculation formula is ((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)) / 2
     * @param coords [x1, y1, x2, y2, x3, y3]
     * @return triangle area
     */
    double calcTriangleAreaByCoords(final List<Integer> coords) {
        return (
            (coords.get(2) - coords.get(0)) * (coords.get(5) - coords.get(1))
            - (coords.get(4) - coords.get(0)) * (coords.get(3) - coords.get(1))
        ) / 2.0;
    }

    List<Integer> convertCoords(final String[] coords) {
        return Arrays.stream(coords)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    void calcLine(final String line) {
        double triangleArea = this.calcTriangleAreaByCoords(
                this.convertCoords(line.trim().split(" "))
        );

        if (triangleArea < this.biggestTriangleArea) {
            return;
        }

        this.biggestTriangleArea = triangleArea;
        this.desiredLine = line;
    }

    boolean isCoordsCorrect(final String line) {
        final int rightCoordsLength = 6;
        String[] arr = line.trim().split(" ");

        if (arr.length != rightCoordsLength) {
            return false;
        }

        try {
            // check if coords contains NaN
            for (String item : arr) {
                Integer.parseInt(item);
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    void output(final String outputFilePath) {
        String message = "\nSorry, can't write to file. \nExit.";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(this.desiredLine);
            message = (this.desiredLine.length() > 0 ? "Done! Please, check the file " + outputFilePath
                    : "Sorry, nothing found in input file.");
        } catch (Exception e) {
            message = e.getMessage() + message;
        } finally {
            LOGGER.info(message);
        }
    }

    void findAndOutput(final String inputFilePath, final String outputFilePath) {
        try (Stream<String> stream = Files.lines(Paths.get(inputFilePath))) {
            LOGGER.info("Working... Please wait.");

            stream.filter(this::isCoordsCorrect)
                    .forEach(this::calcLine);

            this.output(outputFilePath);
        } catch (Exception e) {
            LOGGER.info(e.getMessage() + "\nCan't read file. Please, try again. \nExit. ");
        }
    }
}
