package org.sam.playground.backtracking.chessboard;

import java.util.stream.Stream;

public interface MoveStrategy {

    Stream<Position> getPossibleMoves(ChessBoard board, Position from);
}
