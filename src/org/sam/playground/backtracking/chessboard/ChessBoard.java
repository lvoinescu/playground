package org.sam.playground.backtracking.chessboard;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> boardMap;

    private final int rows;
    private final char columns;
    public ChessBoard(int rows, char columns) {
        this.rows = rows;
        this.columns = columns;
        boardMap = new HashMap<>();
    }

    public Piece getPieceAt(Position position) {
        return boardMap.get(position);
    }

    void pieceMoved(Piece piece, Position previousPosition, Position newPosition) {
        boardMap.remove(previousPosition);
        boardMap.put(newPosition, piece);
    }

    public Piece createPiece(PieceType pieceType, Position p) {
        switch (pieceType) {
            case KNIGHT:
                return new Knight(this, p);
        }

        throw new IllegalArgumentException("Not supported type " + pieceType);
    }

    public int getRows() {
        return rows;
    }

    public char getColumns() {
        return columns;
    }
}
