package org.sam.playground.backtracking.chessboard;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private static MoveStrategy MOVE_STRATEGY = (board, from) -> {
        char column;
        int row;

        List<Position> positions = new ArrayList<>();

        column = (char) (from.getColumn() - 1);
        row = from.getRow() - 1;
        while (column >= 'a' && row >= 1 && board.getPieceAt(column, row) == null) {
            positions.add(new Position(column, row));
            column--;
            row--;
        }

        column = (char) (from.getColumn() + 1);
        row = from.getRow() + 1;
        while (column <= board.getColumns() && row <= board.getRows() && board.getPieceAt(column, row) == null) {
            positions.add(new Position(column, row));
            column++;
            row++;
        }

        column = (char) (from.getColumn() - 1);
        row = from.getRow() + 1;
        while (column >= 'a' && row <= board.getRows() && board.getPieceAt(column, row) == null) {
            positions.add(new Position(column, row));
            row++;
            column--;
        }

        column = (char) (from.getColumn() + 1);
        row = from.getRow() - 1;
        while (row >= 1 && column <= board.getColumns() && board.getPieceAt(column, row) == null) {
            positions.add(new Position(column, row));
            row--;
            column++;
        }


        column = (char) (from.getColumn() - 1);
        while (column >= 'a' && board.getPieceAt(column, from.getRow()) == null) {
            positions.add(new Position(column, from.getRow()));
            column--;
        }
        column = (char) (from.getColumn() + 1);
        while (column <= board.getColumns() - 1 && board.getPieceAt(column, from.getRow()) == null) {
            positions.add(new Position(column, from.getRow()));
            column++;
        }


        row = from.getRow() - 1;
        while (row >= 1 && board.getPieceAt(from.getColumn(), row) == null) {
            positions.add(new Position(from.getColumn(), row));
            row--;
        }

        row = from.getRow() + 1;
        while (row <= board.getRows() && board.getPieceAt(from.getColumn(), row) == null) {
            positions.add(new Position(from.getColumn(), row));
            row++;
        }

        return positions.stream();
    };


    public Queen(ChessBoard chessBoard, Position p) {
        super(chessBoard, p);
    }

    @Override
    PieceType type() {
        return PieceType.QUEEN;
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return MOVE_STRATEGY;
    }

}
