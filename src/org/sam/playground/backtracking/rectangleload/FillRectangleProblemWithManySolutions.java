package org.sam.playground.backtracking.rectangleload;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a stacks of pieces of different sizes find out how a rectangular surface can be filled
 */
public class FillRectangleProblemWithManySolutions {

    private final int rows, columns;
    private final int targetSolutionNumber;
    private char matrix[][];

    private FillRectangleProblemWithManySolutions(int targetSolutionNumber, int rows, int columns) {
        this.targetSolutionNumber = targetSolutionNumber;
        this.matrix = new char[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public static void main(String[] args) {
        FillRectangleProblemWithManySolutions fillRectangleProblem = new FillRectangleProblemWithManySolutions(10, 7, 7);
        List<Piece> pieces = Arrays.asList(
                new Piece(4, 4, 'a', 1),
                new Piece(4, 3, 'b', 1),
                new Piece(4, 2, 'c', 2),
                new Piece(4, 1, 'd', 3),
                new Piece(3, 3, 'e', 7),
                new Piece(3, 2, 'f', 3),
                new Piece(3, 1, 'g', 5),
                new Piece(2, 2, 'h', 2),
                new Piece(2, 1, 'i', 1),
                new Piece(1, 1, 'j', 1)
        );

        System.out.println("Total surface: " + pieces.stream().mapToInt(p -> p.sizeY * p.sizeX).sum());
        fillRectangleProblem.reset();
        long start = System.currentTimeMillis();
        fillRectangleProblem.solve(pieces);
        long end = System.currentTimeMillis();
        System.out.println("Solution(s) found in " + (end - start) + " ms");
    }

    private void reset() {
        matrix = new char[rows][columns];
    }

    private void solve(List<Piece> pieces) {
        List<Piece> clone = new ArrayList<>(pieces);
        clone.sort((p1, p2) -> p2.priority - p1.priority);
        solveIt(clone, partialSolution, 0, 0, 0);
    }

    private Stack<Piece> partialSolution = new Stack<>();

    private int solutionNumber = 0;
    private boolean shouldStop = false;

    private void solveIt(List<Piece> pieces, Stack<Piece> partialSolution, int filledSquares, int lastRow, int lastColumn) {

        if (filledSquares == rows * columns) {
            System.out.println("Solution no. " + ++solutionNumber);
            print();
            if (solutionNumber == targetSolutionNumber) {
                shouldStop = true;
                return;
            }
        }

        while (lastRow < rows && lastColumn < columns && matrix[lastRow][lastColumn] != 0) {
            lastColumn++;
            if (lastColumn == columns) {
                if (lastRow == rows - 1) {
                    return;
                }
                lastColumn = 0;
                lastRow++;
            }
        }
        if (lastRow == rows && lastColumn == columns) {
            return;
        }

        for (Piece piece : pieces) {
            if (!piece.placed) {
                boolean canBePlaced = canPlace(piece, lastRow, lastColumn)
                        || canPlace(piece.rotate(), lastRow, lastColumn);

                if (canBePlaced) {
                    place(piece, lastRow, lastColumn);
                    partialSolution.add(piece);
                    filledSquares += piece.squares;
                    solveIt(pieces, partialSolution, filledSquares, lastRow, lastColumn);
                    //backtrack!
                    if (!shouldStop) {
                        unPlace(piece, lastRow, lastColumn);
                        filledSquares -= piece.squares;
                    }
                }
            }
        }
    }

    private void unPlace(Piece piece, int row, int column) {
        for (int i = 0; i < piece.sizeY; i++) {
            for (int j = 0; j < piece.sizeX; j++) {
                matrix[row + i][column + j] = 0;
            }
        }
        piece.placed = false;
    }

    private boolean canPlace(Piece piece, int row, int column) {
        if (column + piece.sizeX <= columns && row + piece.sizeY <= rows) {
            for (int i = 0; i < piece.sizeY; i++) {
                for (int j = 0; j < piece.sizeX; j++) {
                    if (matrix[row + i][column + j] != 0) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }

    private void place(Piece piece, int row, int column) {

        for (int i = 0; i < piece.sizeY; i++) {
            for (int j = 0; j < piece.sizeX; j++) {
                matrix[row + i][column + j] = piece.letter;
            }
        }

        piece.placed = true;
    }


    @SuppressWarnings("unused")
    static class PieceFactory {
        static List<Piece> createRandomPieces(int number, int maxRows, int maxColumns) {
            Random random = new Random();
            return IntStream.range(0, number)
                    .mapToObj(i -> {
                        int width = 0;
                        int height = 0;
                        while (width == 0 || height == 0) {
                            width = Math.abs(random.nextInt(maxRows));
                            height = Math.abs(random.nextInt(maxColumns));
                        }
                        return new Piece(width, height, (char) ('a' + i), 1);
                    })
                    .collect(Collectors.toList());
        }
    }

    static class Piece {
        int sizeX;
        int sizeY;
        char letter;
        final int priority;
        final int squares;
        boolean placed;

        Piece(int sizeX, int sizeY, char letter, int priority) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.letter = letter;
            this.priority = priority;
            squares = sizeX * sizeY;
        }

        Piece rotate() {
            System.out.println("Rotating " + this);
            int temp = sizeX;
            this.sizeX = sizeY;
            this.sizeY = temp;
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Piece piece = (Piece) o;
            return letter == piece.letter;
        }

        @Override
        public String toString() {
            return "[" + sizeX + "," + sizeY + "] " + letter;
        }
    }

    private void print() {
        for (int k = 0; k < columns + 2; k++) {
            System.out.print("_");
        }
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.print("|");
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] != 0 ? matrix[i][j] : ' ');
            }
            System.out.println("|");
        }
        for (int k = 0; k < columns + 2; k++) {
            System.out.print("¯");
        }

        System.out.println();
    }
}
