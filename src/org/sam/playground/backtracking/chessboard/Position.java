package org.sam.playground.backtracking.chessboard;


import java.util.Objects;

public class Position {

    private final Character column;
    private final Integer row;

    public Position(Character column, Integer row) {
        this.column = column;
        this.row = row;
    }

    public Character getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return column.equals(position.column) && row.equals(position.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public String toString() {
        return "[" + column + ", " + row + ']';
    }
}
