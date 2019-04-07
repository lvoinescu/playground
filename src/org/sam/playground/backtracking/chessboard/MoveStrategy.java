package org.sam.playground.backtracking.chessboard;

import java.util.List;

public interface MoveStrategy {

    List<Position> getPossibleMoves(ChessBoard board, Position from);
}
