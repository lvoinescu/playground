package org.sam.playground.platefill;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FoodSolver {

    private final List<Slice> slices;
    private final SolvingListener solvingListener;
    private final int[][] matrix;
    private final int rows;
    private final int columns;
    private Stack<Slice> partialSolution = new Stack<>();

    public FoodSolver(Plate plate, List<Sandwich> sandwiches, SolvingListener solvingListener) {
        List<Slice> list = new ArrayList<>();
        for (int i = 0; i < sandwiches.size(); i++) {
            Sandwich sandwich = sandwiches.get(i);
            Slice slice = new Slice(sandwich, i + 1);
            list.add(slice);
        }
        this.slices = list;
        this.solvingListener = solvingListener;
        this.matrix = new int[plate.getHeight()][plate.getWidth()];
        this.columns = plate.getWidth();
        this.rows = plate.getHeight();
    }

    public boolean solve() {
        return solveIt(slices);
    }

    private boolean solveIt(List<Slice> slices) {
//        slices.sort((a, b) -> b.getColumns() * b.getRows() - a.getColumns()* a.getRows());
        return solveIt(slices, partialSolution, 0, 0, 0);
    }

    private boolean solveIt(List<Slice> slices, Stack<Slice> partialSolution, int filledSquares, int lastRow, int lastColumn) {

        if (filledSquares == rows * columns) {
            solvingListener.problemSolved(partialSolution);
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

        for (Slice slice : slices) {
            if (!slice.isPlaced() && (canPlace(slice, lastRow, lastColumn) || canPlace(slice.rotate(), lastRow, lastColumn))) {
                place(slice, lastRow, lastColumn);
                partialSolution.add(slice);
                filledSquares += slice.getSquares();
                boolean solved = solveIt(slices, partialSolution, filledSquares, lastRow, lastColumn);
                if (solved) {
                    return true;
                } else {
                    //backtrack!
                    unPlace(slice, lastRow, lastColumn);
                    partialSolution.remove(partialSolution.size() - 1);
                    filledSquares -= slice.getSquares();
                }
            }
        }
        return false;
    }

    private void unPlace(Slice slice, int row, int column) {
        for (int i = 0; i < slice.getRows(); i++) {
            for (int j = 0; j < slice.getColumns(); j++) {
                matrix[row + i][column + j] = 0;
            }
        }
        slice.unPlace();
    }

    private boolean canPlace(Slice slice, int row, int column) {
        if (column + slice.getColumns() <= columns && row + slice.getRows() <= rows) {
            for (int i = 0; i < slice.getRows(); i++) {
                for (int j = 0; j < slice.getColumns(); j++) {
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

    private void place(Slice slice, int row, int column) {
        slice.place(row, column);
        for (int i = 0; i < slice.getRows(); i++) {
            for (int j = 0; j < slice.getColumns(); j++) {
                matrix[row + i][column + j] = slice.getNumber();
            }
        }
    }
}
