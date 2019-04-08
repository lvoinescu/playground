package org.sam.playground.backtracking.chessboard;

public abstract class Piece implements Cloneable{
    protected Position currentPosition;
    protected ChessBoard board;

    public Piece(ChessBoard board, Position currentPosition) {
        this.board = board;
        this.currentPosition = currentPosition;
    }

    abstract PieceType type();

    public abstract Position getCurrentPosition();

    public abstract  MoveStrategy getMoveStrategy();

    public abstract void moveTo(Position p);

    @Override
    public Piece clone() {
        return board.createPiece(this.type(), currentPosition);
    }
}
