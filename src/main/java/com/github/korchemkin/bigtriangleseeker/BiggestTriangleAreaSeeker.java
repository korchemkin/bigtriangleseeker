package com.github.korchemkin.bigtriangleseeker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class BiggestTriangleAreaSeeker {
	private final int rightCoordsLength = 6;
	private double biggestTriangleArea;
	private String desiredLine = "";

	private List<Integer> convertCoords(String[] coords) {
		return Arrays.stream(coords)
				.map(Integer::valueOf)
				.collect(Collectors.toList());
	}

	private double squareByCoords(List<Integer> coords) {
		return (
				(coords.get(2) - coords.get(0)) * (coords.get(5) - coords.get(1))
						- (coords.get(4) - coords.get(0)) * (coords.get(3) - coords.get(1))
		) / 2.0;
	}

	private void output(String outputFilePath) {
		// TODO
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
			writer.write(desiredLine);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void findAndOutput(String inputFilePath, String outputFilePath) {
		// TODO
		try (Stream<String> stream = Files.lines(Paths.get(inputFilePath))) {
			stream.filter(x -> x.split(" ").length == this.rightCoordsLength)
					.forEach(x -> {
						double triangleArea = this.squareByCoords(this.convertCoords(x.split(" ")));
						if (triangleArea > this.biggestTriangleArea) {
							this.biggestTriangleArea = triangleArea;
							this.desiredLine = x;
						}
					});
			this.output(outputFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
