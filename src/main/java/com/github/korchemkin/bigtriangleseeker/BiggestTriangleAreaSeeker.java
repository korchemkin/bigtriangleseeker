package com.github.korchemkin.bigtriangleseeker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 *
 * Finds the largest triangle area by coordinates in the input file.
 */
class BiggestTriangleAreaSeeker {
    /**
     * The largest triangle area in the input file.
     */
    private double biggestTriangleArea;
    /**
     * Searching results.
     */
    private String desiredLine = "";
    /** */
    private static Logger logger = Logger.getLogger(BiggestTriangleAreaSeeker.class.getName());

    private List<Integer> convertCoords(final String[] coords) {
        return Arrays.stream(coords)
            .map(Integer::valueOf)
            .collect(Collectors.toList());
    }
    /**
     *
     * The coordinates of the first vertex of a triangle x1, y1.
     * Second vertex x2, y2.
     * Third vertex x3, y3.
     * The calculation formula is ((x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1)) / 2
     * @param coords [x1, y1, x2, y2, x3, y3]
     * @return triangle area
     */
    private double calcTriangleAreaByCoords(final List<Integer> coords) {
        return (
            (coords.get(2) - coords.get(0)) * (coords.get(5) - coords.get(1))
            - (coords.get(4) - coords.get(0)) * (coords.get(3) - coords.get(1))
        ) / 2.0;
    }

    private void calcLine(final String line) {
        double triangleArea = this.calcTriangleAreaByCoords(
                this.convertCoords(line.split(" "))
        );

        if (triangleArea > this.biggestTriangleArea) {
            this.biggestTriangleArea = triangleArea;
            this.desiredLine = line;
        }
    }

    private void output(final String outputFilePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(desiredLine);

            if (desiredLine.length() != 0) {
                logger.info("Done! Please, check the file " + outputFilePath);
            } else {
                logger.info("Sorry, nothing found.");
            }
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
    }

    void findAndOutput(final String inputFilePath, final String outputFilePath) {
        try (Stream<String> stream = Files.lines(Paths.get(inputFilePath))) {
            logger.info("Working... Please wait.");
            final int rightCoordsLength = 6;
            stream.filter(x -> x.split(" ").length == rightCoordsLength)
                .forEach(this::calcLine);
            this.output(outputFilePath);
        } catch (IOException e) {
            // TODO
            e.printStackTrace();
        }
    }
}
