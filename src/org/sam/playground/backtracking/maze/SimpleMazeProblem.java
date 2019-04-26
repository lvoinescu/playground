package org.sam.playground.backtracking.maze;

import java.util.Stack;

public class SimpleMazeProblem {

    private static int COLUMNS = 40;
    private static int ROWS = 12;
    private int[][] matrix;
    private Point in;
    private Point out;

    private SimpleMazeProblem() {
        this.matrix = new int[ROWS][COLUMNS];
    }

    public static void main(String[] args) {
        SimpleMazeProblem simpleMazeProblem = new SimpleMazeProblem();
        //12x40 matrix
        String maze = "" +
                "########################################" +
                "#####             ##                 #  " +
                "    # ##### ######## ############### # #" +
                "### #    ## #      #          ###### # #" +
                "### #### ## # #### ##########      # # #" +
                "###    # ## # #### ##       ###### # # #" +
                "###### #  # # #    ## ##### #      # # #" +
                "#      ## # # # ##### #   # # ###### # #" +
                "###### ## # # # #     # #   #      # # #" +
                "### ## ## # # #   ##### ########## # # #" +
                "##        #   #########            #   #" +
                "########################################";
        simpleMazeProblem.solve(maze);
    }

    private void solve(String input) {
        this.matrix = parseMaze(input);
        in = getGapOnColumn(matrix, 0);
        out = getGapOnColumn(matrix, COLUMNS - 1);
        System.out.println("Unresolved maze is:");
        print(matrix, ROWS, COLUMNS);

        long start = System.currentTimeMillis();
        boolean solved = solveRecursively(in);
        long end = System.currentTimeMillis();
        System.out.println("Finished recursively in " + (end - start) + " ms");

        if (solved) {
            System.out.println("Maze escaped recursively!");
            print(matrix, ROWS, COLUMNS);

        } else {
            System.out.println("Trapped forever recursively...");
        }


        this.matrix = parseMaze(input);
        in = getGapOnColumn(matrix, 0);
        out = getGapOnColumn(matrix, COLUMNS - 1);
        System.out.println("Unresolved maze is:");
        print(matrix, ROWS, COLUMNS);

        start = System.currentTimeMillis();
        solved = solveWithStack(in);
        end = System.currentTimeMillis();
        System.out.println("Finished with stack in " + (end - start) + " ms");

        if (solved) {
            System.out.println("Maze escaped with stack!");
            print(matrix, ROWS, COLUMNS);

        } else {
            System.out.println("Trapped forever with stack...");
        }

    }

    private boolean solveRecursively(Point currentPoint) {

        if (currentPoint.row < 0 || currentPoint.row > ROWS - 1
                || currentPoint.column < 0 || currentPoint.column > COLUMNS - 1
                || matrix[currentPoint.row][currentPoint.column] == 1
                || matrix[currentPoint.row][currentPoint.column] == 2
                ) {
            return false;
        }
        matrix[currentPoint.row][currentPoint.column] = 2;

        if (currentPoint.equals(out)) {
            return true;
        }

        if (solveRecursively(new Point(currentPoint.row + 1, currentPoint.column))) {
            return true;
        }

        if (solveRecursively(new Point(currentPoint.row - 1, currentPoint.column))) {
            return true;
        }

        if (solveRecursively(new Point(currentPoint.row, currentPoint.column + 1))) {
            return true;
        }

        if (solveRecursively(new Point(currentPoint.row, currentPoint.column - 1))) {
            return true;
        }
        matrix[currentPoint.row][currentPoint.column] = 0;

        return false;
    }

    private boolean solveWithStack(Point currentPoint) {

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
        return point.row >= 0 && point.row <= ROWS - 1
                && point.column >= 0 && point.column <= COLUMNS - 1
                && matrix[point.row][point.column] == 0;
    }


    private int[][] parseMaze(String input) {
        int[][] matrix = new int[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                char c = input.charAt(i * COLUMNS + j);
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
                char c = matrix[i][j] != 0 ? (matrix[i][j] == 2 ? '.' : '#') : ' ';
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
