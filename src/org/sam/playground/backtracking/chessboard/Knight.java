package org.sam.playground.backtracking.chessboard;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Knight extends Piece {

    private static MoveStrategy MOVE_STRATEGY = (board, from) -> {
        Position p1 = new Position((char) (from.getColumn() + 2), from.getRow() + 1);
        Position p2 = new Position((char) (from.getColumn() + 1), from.getRow() + 2);
        Position p3 = new Position((char) (from.getColumn() - 1), from.getRow() + 2);
        Position p4 = new Position((char) (from.getColumn() - 2), from.getRow() + 1);

        Position p5 = new Position((char) (from.getColumn() - 2), from.getRow() - 1);
        Position p6 = new Position((char) (from.getColumn() - 1), from.getRow() - 2);
        Position p7 = new Position((char) (from.getColumn() + 1), from.getRow() - 2);
        Position p8 = new Position((char) (from.getColumn() + 2), from.getRow() - 1);

        return Stream.of(p1, p2, p3, p4, p5, p6, p7, p8)
                .filter(p -> p.getRow() <= board.getRows() && p.getRow() >= 1
                        && p.getColumn() <= board.getColumns() && p.getColumn() >= 'a')
                .collect(Collectors.toList());
    };

    Knight(ChessBoard board, Position currentPosition) {
        super(board, currentPosition);
    }

    @Override
    public PieceType type() {
        return PieceType.KNIGHT;
    }

    @Override
    public Position getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return MOVE_STRATEGY;
    }

    @Override
    public void moveTo(Position position) {
        board.pieceMoved(this, currentPosition, position);
        this.currentPosition = position;
    }
}
