package org.sam.playground.backtracking.maze;

public class SimpleMazeProblem {


    private int[][] matrix;
    private Point in;
    private Point out;

    private SimpleMazeProblem() {
        this.matrix = new int[12][20];
    }

    public static void main(String[] args) {
        SimpleMazeProblem simpleMazeProblem = new SimpleMazeProblem();
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

        boolean solved = solve(in);

        if (solved) {
            System.out.println("Maze escaped!");
            print(matrix, 12, 20);

        } else {
            System.out.println("Trapped forever...");
        }
    }

    private boolean solve(Point currentPoint) {

        if (currentPoint.row < 0 || currentPoint.row > 11
                || currentPoint.column < 0 || currentPoint.column > 19
                || matrix[currentPoint.row][currentPoint.column] == 1
                || matrix[currentPoint.row][currentPoint.column] == 2
                ) {
            return false;
        }
        matrix[currentPoint.row][currentPoint.column] = 2;

        if (currentPoint.equals(out)) {
            return true;
        }

        if (solve(new Point(currentPoint.row + 1, currentPoint.column))) {
            return true;
        }

        if (solve(new Point(currentPoint.row - 1, currentPoint.column))) {
            return true;
        }

        if (solve(new Point(currentPoint.row, currentPoint.column + 1))) {
            return true;
        }

        if (solve(new Point(currentPoint.row, currentPoint.column - 1))) {
            return true;
        }
        matrix[currentPoint.row][currentPoint.column] = 0;

        return false;
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
