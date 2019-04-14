package org.sam.playground.backtracking.rectangleload;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a stacks of pieces of different sizes find out how a rectangular surface can be filled
 */
public class FillRectangleProblem {

    private final int rows, columns;
    private char matrix[][];

    private FillRectangleProblem(int rows, int columns) {
        this.matrix = new char[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public static void main(String[] args) {
        FillRectangleProblem fillRectangleProblem = new FillRectangleProblem(7, 7);
        List<Piece> pieces = Arrays.asList(
                new Piece(3, 3, 'a'),
                new Piece(2, 1, 'b'),
                new Piece(3, 1, 'c'),
                new Piece(3, 2, 'd'),
                new Piece(2, 2, 'e'),
                new Piece(1, 4, 'f'),
                new Piece(2, 2, 'g'),
                new Piece(2, 2, 'h'),
                new Piece(1, 3, 'i'),
                new Piece(2, 2, 'j'),
                new Piece(2, 2, 'k'),
                new Piece(2, 2, 'l'),
                new Piece(2, 2, 'm')
        );

        System.out.println("Total surface: " + pieces.stream().mapToInt(p -> p.sizeY * p.sizeX).sum());
        fillRectangleProblem.reset();
        long start = System.currentTimeMillis();
        boolean solved = fillRectangleProblem.solveIt(pieces);
        long end = System.currentTimeMillis();
        if (solved) {
            fillRectangleProblem.print();
            System.out.println("Solution found in " + (end - start) + " ms");
        } else {
            System.out.println("Could not place!");
        }
    }

    private void reset() {
        matrix = new char[rows][columns];
    }

    private boolean solveIt(List<Piece> pieces) {
        return solveIt(pieces, partialSolution, 0, 0, 0);
    }

    private Stack<Piece> partialSolution = new Stack<>();

    private boolean solveIt(List<Piece> pieces, Stack<Piece> partialSolution, int filledSquares, int lastRow, int lastColumn) {

        if (filledSquares == rows * columns) {
            return true;
        }

        while (lastRow < rows && lastColumn < columns && matrix[lastRow][lastColumn] != 0) {
            lastColumn++;
            if (lastColumn == columns) {
                if (lastRow == rows - 1) {
                    return true;
                }
                lastColumn = 0;
                lastRow++;
            }
        }
        if (lastRow == rows && lastColumn == columns) {
            return true;
        }

        for (Piece piece : pieces) {
            if (!piece.placed && canPlace(piece, lastRow, lastColumn)) {
                place(piece, lastRow, lastColumn);
//                print();
                partialSolution.add(piece);
                filledSquares += piece.squares;
                boolean solved = solveIt(pieces, partialSolution, filledSquares, lastRow, lastColumn);
                if (solved) {
                    return true;
                } else {
                    //backtrack!
                    unPlace(piece, lastRow, lastColumn);
                    filledSquares -= piece.squares;
                }
            }
        }
        return false;
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
                        return new Piece(width, height, (char) ('a' + i));
                    })
                    .collect(Collectors.toList());
        }
    }

    static class Piece {
        int sizeX;
        int sizeY;
        char letter;
        final int squares;
        boolean placed;

        Piece(int sizeX, int sizeY, char letter) {
            this.sizeX = sizeX;
            this.sizeY = sizeY;
            this.letter = letter;
            squares = sizeX * sizeY;
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
