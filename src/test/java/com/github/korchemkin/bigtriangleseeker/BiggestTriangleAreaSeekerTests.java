package com.github.korchemkin.bigtriangleseeker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BiggestTriangleAreaSeekerTests {
    private final BiggestTriangleAreaSeeker seeker = new BiggestTriangleAreaSeeker();

    @Test
    void testCalcTriangleAreaByCoords() {
        final Integer[] array = new Integer[] {-1, -1, 20, -1, -1, 20};
        final List<Integer> coords = new ArrayList<>(Arrays.asList(array));
        assertTrue(seeker.calcTriangleAreaByCoords(coords) > 0);
    }

    @Test
    void testConvertCoords() {
        final String[] coords = {"-1", "-1", "20", "-1", "-1", "20"};
        final List<Integer> result = seeker.convertCoords(coords);
        assertEquals(21, result.get(2) + 1);
    }

    @Test
    void testIsCoordsCorrect() {
        assertAll(
                () -> assertTrue(seeker.isCoordsCorrect("  -1 -1 20 -1 -1 20  ")),
                () -> assertFalse(seeker.isCoordsCorrect("-1 -1 20 X -1 20")),
                () -> assertFalse(seeker.isCoordsCorrect("   -1 -1 20   -1 -1 20     "))
        );
    }

    @Test
    void testOutput() {
        assertAll(
                () ->  assertDoesNotThrow(() -> seeker.output("")),
                () ->  assertDoesNotThrow(() -> seeker.output("src")),
                () ->  assertDoesNotThrow(() -> seeker.output("../resources"))
        );
    }

    @Test
    void testFindAndOutput() {
        assertAll(
                () -> assertDoesNotThrow(() -> seeker.findAndOutput("", "")),
                () -> assertDoesNotThrow(
                        () -> seeker.findAndOutput("../resources/in.txt", "../resources"))
        );
    }
}
