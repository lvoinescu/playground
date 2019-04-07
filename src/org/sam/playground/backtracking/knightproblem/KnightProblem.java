package org.sam.playground.backtracking.knightproblem;


import org.sam.playground.backtracking.chessboard.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The knight is placed on the first block of an empty chessboard and, moving according to the rules of chess,
 * must visit each square exactly once.
 */
public class KnightProblem {

    private final ChessBoard chessBoard;
    private final Piece knight;
    private List<Position> partialSolution = new ArrayList<>();


    private KnightProblem(int maxRows, char maxColumns, Position a) {
        chessBoard = new ChessBoard(maxRows, maxColumns);
        knight = chessBoard.createPiece(PieceType.KNIGHT,a);
        partialSolution.add(knight.getCurrentPosition());
    }

    public static void main(String[] args) {
        KnightProblem knightProblem = new KnightProblem(7, 'g', new Position('a', 1));
        knightProblem.solve();
    }
    private void solve() {
        long start = System.currentTimeMillis();

        boolean solved = solveProblem(chessBoard, knight);

        if (!solved) {
            System.out.println("No solution found!");
        }
        long end = System.currentTimeMillis();
        System.out.println("Completed in " + (end - start) + " ms");
    }


    private boolean solveProblem(ChessBoard chessBoard, Piece piece) {
        if (checkFullSolution()) {
            System.out.println("All positions covered");
            render(chessBoard, partialSolution);
            return true;
        }

        List<Position> possibleMoves = piece.getMoveStrategy().getPossibleMoves(chessBoard, piece.getCurrentPosition());
        Position rootPosition = piece.getCurrentPosition();
        for (Position nextPosition : possibleMoves) {
            if (isValid(partialSolution, nextPosition)) {
                partialSolution.add(nextPosition);
                piece.moveTo(nextPosition);
                boolean problemSolved = solveProblem(chessBoard, piece);
                if (problemSolved) {
                    return true;
                }
                //move back!
                partialSolution.remove(partialSolution.size() - 1);
                piece.moveTo(rootPosition);
            }
        }
        return false;
    }

    //this is pretty slow as it is list searching
    //should have use a matrix instead
    private boolean isValid(List<Position> partialSolution, Position p) {
        return !partialSolution.contains(p);
    }

    private boolean checkFullSolution() {
        return partialSolution.size() == chessBoard.getRows() * (chessBoard.getColumns() - 'a' + 1);
    }

    private void render(ChessBoard chessBoard, List<Position> trace) {
        renderHeader(chessBoard);
        for (int i = chessBoard.getRows(); i >= 1; i--) {
            System.out.print(i);
            System.out.print(' ');
            for (char j = 'a'; j <= chessBoard.getColumns(); j++) {
                System.out.print('|');
                Position p = new Position(j, i);
                if (trace.contains(p)) {
                    System.out.print(String.format("%02d", trace.indexOf(p) + 1));
                } else
                    System.out.print("  ");
            }
            System.out.print('|');
            System.out.println();
        }
        renderFooter(chessBoard);
    }

    private void renderHeader(ChessBoard chessBoard) {
        System.out.println();
        System.out.print("  ");
        System.out.print('|');
        for (char i = 'a'; i <= chessBoard.getColumns(); i++) {
            System.out.print(' ');
            System.out.print(i);
            System.out.print('|');
        }
        System.out.println();
    }

    private void renderFooter(ChessBoard chessBoard) {
        System.out.print("  ");
        System.out.print('|');
        for (char i = 'a'; i <= chessBoard.getColumns(); i++) {
            System.out.print(' ');
            System.out.print(i);
            System.out.print('|');
        }
        System.out.println();
    }
}
