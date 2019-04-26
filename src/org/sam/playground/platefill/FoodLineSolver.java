package org.sam.playground.platefill;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FoodLineSolver implements Solver{

    private final List<Slice> slices;
    private final SolvingListener solvingListener;
    private final int[][] matrix;
    private final int rows;
    private final int columns;
    private Stack<Slice> partialSolution = new Stack<>();

    FoodLineSolver(Plate plate, List<Sandwich> sandwiches, SolvingListener solvingListener) {
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

    @Override
    public boolean solve() {
        return solveIt(slices);
    }

    private boolean solveIt(List<Slice> slices) {
        slices.sort((a, b) -> b.getColumns() * b.getRows() - a.getColumns()* a.getRows());
        return solveIt(slices, partialSolution);
    }

    private boolean solveIt(List<Slice> slices, Stack<Slice> partialSolution) {

        boolean rowSolved = false;
        for (int i = 0; i < rows; i++) {
            rowSolved = solveRow(slices, i, columns, 0);
            System.out.println(rowSolved);
            solvingListener.stateChanged(slices, partialSolution);
        }
        return rowSolved;
    }


    private boolean solveRow(List<Slice> slices, int row, int rowSize, int rowPosition) {

        while (rowPosition < rowSize && matrix[row][rowPosition] > 0) {
            rowPosition++;
        }

        if (rowPosition >= rowSize) {
            return true;
        }

        boolean nothingToPlace = true;
        for (int i = 0; i < slices.size(); i++) {
            Slice slice = slices.get(i);
            boolean solvedRow;
            if (!slice.isPlaced()) {
                if (canPlace(slice, row, rowPosition)) {
                    nothingToPlace = false;
                    place(slice, row, rowPosition);
                    partialSolution.add(slice);
                    solvingListener.stateChanged(slices, partialSolution);
                    solvedRow = solveRow(slices, row, rowSize, rowPosition + slice.getColumns());
                    if (solvedRow) {
                        return true;
                    } else {
                        unPlace(slice, row, rowPosition);
                        partialSolution.remove(partialSolution.size() - 1);
                        solvingListener.stateChanged(slices, partialSolution);
                    }
                }
                if (canPlace(slice.rotate(), row, rowPosition)) {
                    place(slice, row, rowPosition);
                    partialSolution.add(slice);
                    solvingListener.stateChanged(slices, partialSolution);
                    solvedRow = solveRow(slices, row, rowSize, rowPosition + slice.getColumns());
                    if (solvedRow) {
                        return true;
                    } else {
                        unPlace(slice, row, rowPosition);
                        partialSolution.remove(partialSolution.size() - 1);
                        solvingListener.stateChanged(slices, partialSolution);
                    }
                }
            }
        }
        return nothingToPlace;
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
