package org.sam.playground.recursion.floodfill;

import java.util.Objects;

public class FloodFillProblem {

    public static void main(String[] args) {

        /*  |z|y|y|z|z|x|
            |x|z|y|z|z|x|
            |x|x|x|z|y|x|
            |y|z|x|x|x|x|
         */
        Point[][] input = new Point[][]{
                {new Point(3), new Point(2), new Point(2), new Point(3), new Point(3), new Point(1)},
                {new Point(1), new Point(3), new Point(2), new Point(3), new Point(3), new Point(1)},
                {new Point(1), new Point(1), new Point(1), new Point(3), new Point(2), new Point(1)},
                {new Point(2), new Point(3), new Point(1), new Point(1), new Point(1), new Point(1)}
        };

        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length && !input[i][j].processed; j++) {
                currentSize = 0;
                solve(input, null, i, j);
                System.out.println("Local size: " + currentSize);
            }
        }
    }


    private static int currentSize = 0;

    private static void solve(Point[][] input, Point currentPoint, int i, int j) {
        if (i < 0 || j < 0) {
            return;
        }

        if (i >= input.length) {
            return;
        }


        if (j >= input[i].length) {
            return;
        }

        if (input[i][j].processed) {
            return;
        }

        if (currentPoint != null && currentPoint.color != input[i][j].color) {
            return;
        }

        System.out.println(i + ", " + j + " -> " + input[i][j].color);

        currentSize++;
        currentPoint = input[i][j];
        currentPoint.processed = true;
        solve(input, currentPoint, i + 1, j);
        solve(input, currentPoint, i - 1, j);
        solve(input, currentPoint, i, j + 1);
        solve(input, currentPoint, i, j - 1);
    }


    private static class Point {
        final int color;
        boolean processed;

        private Point(int color) {
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return color == point.color;
        }

        @Override
        public int hashCode() {
            return Objects.hash(color);
        }
    }
}
