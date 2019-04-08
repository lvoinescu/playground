package org.sam.playground.backtracking.chessboard;

public class ChessBoard {

    private final Piece[][] boardMatrix;

    private final int rows;
    private final char columns;

    public ChessBoard(int rows, char columns) {
        this.rows = rows;
        this.columns = columns;
        this.boardMatrix = new Piece[rows][translateCharColumnToIndex((char)(columns + 1))];
        for (int i = 0; i < rows; i++) {
            for (char j = 'a'; j < columns; j++) {
                this.boardMatrix[i] = new Piece[translateCharColumnToIndex((char)(columns + 1))];
                this.boardMatrix[i][translateCharColumnToIndex(j)] = null;
            }
        }
    }

    public Piece getPieceAt(Position position) {
        return boardMatrix[position.getRow() - 1][translateCharColumnToIndex(position.getColumn())];
    }

    void pieceMoved(Piece piece, Position previousPosition, Position newPosition) {
        removePieceAt(previousPosition);
        placePieceAt(piece, newPosition);
    }

    public void placePieceAt(Piece piece, Position position) {
        boardMatrix[position.getRow() - 1][translateCharColumnToIndex(position.getColumn())] = piece;
        piece.currentPosition = position;
    }

    public void removePieceAt(Position position) {
        boardMatrix[position.getRow() - 1][translateCharColumnToIndex(position.getColumn())] = null;
    }

    public Piece createPiece(PieceType pieceType, Position p) {
        switch (pieceType) {
            case KNIGHT:
                return new Knight(this, p);
        }

        throw new IllegalArgumentException("Not supported type " + pieceType);
    }

    private int translateCharColumnToIndex(char columns) {
        return (int) columns - 'a';
    }

    public int getRows() {
        return rows;
    }

    public char getColumns() {
        return columns;
    }
}
