package org.sam.playground.backtracking.maze;

import java.util.Stack;

public class SimpleMazeProblemNonRecursive {


    private int[][] matrix;
    private Point in;
    private Point out;

    private SimpleMazeProblemNonRecursive() {
        this.matrix = new int[12][20];
    }

    public static void main(String[] args) {
        SimpleMazeProblemNonRecursive simpleMazeProblem = new SimpleMazeProblemNonRecursive();
        //12x20 matrix
        String maze = "" +
                "####################" +
                "#####             ##" +
                "    # ##### ########" +
                "### #    ## #      #" +
                "### #### ## # #### #" +
                "###    # ## # #### #" +
                "###### #  # # #    #" +
                "#      ## # # # ####" +
                "###### ## # # # #   " +
                "### ## ## # # #   ##" +
                "##        #   ######" +
                "####################";
        simpleMazeProblem.solve(maze);
    }

    private void solve(String input) {
        this.matrix = parseMaze(input);
        in = getGapOnColumn(matrix, 0);
        out = getGapOnColumn(matrix, 19);
        System.out.println("Unresolved maze is:");
        print(matrix, 12, 20);

        long start = System.currentTimeMillis();
        boolean solved = solve(in);
        long end = System.currentTimeMillis();

        System.out.println("Finished in " + (end - start) + " ms");
        if (solved) {
            System.out.println("Maze escaped!");
            print(matrix, 12, 20);

        } else {
            System.out.println("Trapped forever...");
        }
    }

    private boolean solve(Point currentPoint) {

        Stack<Point> stack = new Stack<>();

        matrix[currentPoint.row][currentPoint.column] = 2;

        stack.push(currentPoint);
        Point parent = currentPoint;
        while (stack.size() > 0) {
            if (parent.equals(out)) {
                return true;
            }

            parent = stack.peek();

            Point child1 = new Point(parent.row + 1, parent.column);
            Point child2 = new Point(parent.row - 1, parent.column);
            Point child3 = new Point(parent.row, parent.column - 1);
            Point child4 = new Point(parent.row, parent.column + 1);


            boolean deadEnd = true;
            if (testPoint(child1)) {
                deadEnd = false;
                stack.push(child1);
            } else {
                if (testPoint(child2)) {
                    deadEnd = false;
                    stack.push(child2);
                } else {
                    if (testPoint(child3)) {
                        deadEnd = false;
                        stack.push(child3);
                    } else {
                        if (testPoint(child4)) {
                            deadEnd = false;
                            stack.push(child4);
                        }
                    }
                }
            }

            if (deadEnd) {
                Point point = stack.pop();
                matrix[point.row][point.column] = 3;

            } else {
                Point point = stack.peek();
                matrix[point.row][point.column] = 2;
            }
        }

        return false;
    }

    private boolean testPoint(Point point) {
        return point.row >= 0 && point.row <= 11
                && point.column >= 0 && point.column <= 19
                && matrix[point.row][point.column] == 0;
    }


    private int[][] parseMaze(String input) {
        int[][] matrix = new int[12][20];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 20; j++) {
                char c = input.charAt(i * 20 + j);
                matrix[i][j] = c == ' ' ? 0 : 1;
            }
        }
        return matrix;
    }

    private void print(int[][] matrix, int rows, int columns) {
        for (int k = 0; k < columns + 6; k++) {
            System.out.print("_");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            if (i == in.row) {
                System.out.print("-> ");
            } else {
                System.out.print("  |");
            }


            for (int j = 0; j < columns; j++) {
                char c = matrix[i][j] != 0 && matrix[i][j] != 3 ? (matrix[i][j] == 2 ? '.' : '#') : ' ';
                System.out.print(c);
            }
            if (i == out.row) {
                System.out.println(" ->");
            } else {
                System.out.println("|");
            }
        }
        for (int k = 0; k < columns + 6; k++) {
            System.out.print("¯");
        }
        System.out.println();
    }

    private Point getGapOnColumn(int[][] matrix, int column) {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][column] == 0) {
                return new Point(i, column);
            }
        }
        return null;
    }
}
