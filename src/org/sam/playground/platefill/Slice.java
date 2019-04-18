package org.sam.playground.platefill;


public class Slice {

    private final Sandwich sandwich;
    private int columns;
    private int rows;
    private boolean placed;
    private int number;
    private int row, column;

    Slice(Sandwich sandwich, int number) {
        this.sandwich = sandwich;
        this.number = number;
        this.columns = sandwich.getWidth();
        this.rows = sandwich.getHeight();
    }

    public Slice rotate() {
        int temp = columns;
        columns = rows;
        rows = temp;

        return this;
    }

    public Sandwich getSandwich() {
        return sandwich;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void place(int row, int column){
        this.placed = true;
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getNumber() {
        return number;
    }

    public int getSquares() {
        return this.rows * this.columns;
    }

    public void unPlace() {
        this.placed = false;
    }

    @Override
    public String toString() {
        return "{" +
                "" + rows +
                ", " + columns +
                '}';
    }
}
