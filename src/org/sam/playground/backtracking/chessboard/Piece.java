package org.sam.playground.backtracking.chessboard;

public abstract class Piece implements Cloneable{
    protected Position currentPosition;
    private ChessBoard board;

    Piece(ChessBoard board, Position currentPosition) {
        this.board = board;
        this.currentPosition = currentPosition;
    }

    abstract PieceType type();

    public Position getCurrentPosition() {
        return currentPosition;
    }
    public abstract  MoveStrategy getMoveStrategy();

    @Override
    public Piece clone() {
        return board.createPiece(this.type(), currentPosition);
    }
}
