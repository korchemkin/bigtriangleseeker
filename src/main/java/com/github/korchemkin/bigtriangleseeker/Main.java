package com.github.korchemkin.bigtriangleseeker;

public final class Main {
    private Main() { }
    /**
     *
     * @param args
     * args[0] Should contain the path to the input file with coords of triangle (one line one triangle)
     * args[1] Should contain the path to the output file to which the result is written
     */
    public static void main(final String[] args) {
        if (args.length < 2) {
            System.out.println("\n\nPlease, pass paths of input and output files. "
                + "\nFor more info https://github.com/korchemkin/bigtriangleseeker\n\n");
            return;
        }

        BiggestTriangleAreaSeeker seeker = new BiggestTriangleAreaSeeker();
        seeker.findAndOutput(args[0], args[1]);
    }
}
