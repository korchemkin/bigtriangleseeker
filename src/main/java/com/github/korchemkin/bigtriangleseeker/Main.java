package com.github.korchemkin.bigtriangleseeker;

public class Main {
	public static void main(String[] args) {
		if (args.length < 2) {
			System.out.println("\n\nPlease, pass paths of input and output files. "
					+ "For more info https://github.com/korchemkin/bigtriangleseeker\n\n");
			return;
		}

		BiggestTriangleAreaSeeker seeker = new BiggestTriangleAreaSeeker();
		seeker.findAndOutput(args[0], args[1]);
	}
}
