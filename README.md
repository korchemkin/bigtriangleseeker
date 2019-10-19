# Big triangle seeker

The program finds the largest isosceles triangle by area.

## Usage

1. Build `.jar` file with gradle.

2. Execute.
```
java -jar <JAR FILE> <INPUT FILE PATH> <OUTPUT FILE PATH>
```

The input file should contain the coordinates 
of the triangle and look like this:

```
0 0 1 0 0 1
-1 -1 9 -1 -1 9
-1 -1 10 -1 -1 10
-1 -1 11 -1 -1 11
-1 -1 20 -1 -1 20
-1 -1 15 -1 -1 15
```

Data
written in a line as 6 consecutive integers, separated by a space
(each line of the file is a new set of triangle coordinates). The first 2 numbers are the coordinates
the first point, the second - the second, etc.


The coordinates of the largest triangle will be written to the output file.
