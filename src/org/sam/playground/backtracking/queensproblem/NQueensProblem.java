package org.sam.playground.backtracking.queensproblem;


import org.sam.playground.backtracking.chessboard.ChessBoard;
import org.sam.playground.backtracking.chessboard.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other
 */
public class NQueensProblem {

    private final ChessBoard chessBoard;

    private NQueensProblem(int maxRows, char maxColumns) {
        chessBoard = new ChessBoard(maxRows, maxColumns);
    }

    public static void main(String[] args) {
        NQueensProblem nQueensProblem = new NQueensProblem(8, 'h');
        nQueensProblem.solve();
    }

    private void solve() {
        long start = System.currentTimeMillis();

        solveProblem(chessBoard, new ArrayList<>(), 1);
        long end = System.currentTimeMillis();
        System.out.println("Completed in " + (end - start) + " ms");
    }


    private boolean solveProblem(ChessBoard chessBoard, List<Position> partialSolution, int row) {
        if (checkFullSolution(partialSolution)) {
            System.out.println("Valid solution found!");
            render(chessBoard, partialSolution);
            System.out.println();
        }

        if (row > chessBoard.getRows()) {
            return false;
        }
        for (Position newPositionCandidate : candidatePositions(partialSolution, row)) {
            partialSolution.add(newPositionCandidate);

            if (solveProblem(chessBoard, partialSolution, ++row)) {
                return true;
            }

            //backtrack!
            row--;
            partialSolution.remove(partialSolution.size()-1);
        }
        return false;
    }

    private List<Position> candidatePositions(List<Position> partialSolution, int row) {
        List<Position> positionsToAvoid = partialSolution.stream()
                .map(p -> {
                    Position p1 = new Position((char) (p.getColumn() + (p.getRow() - row)), row);
                    Position p2 = new Position((char) (p.getColumn() - (p.getRow() - row)), row);
                    Position p3 = new Position(p.getColumn(), row);
                    return Arrays.asList(p1, p2, p3);
                })
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return IntStream.range('a', chessBoard.getColumns() + 1)
                .mapToObj(c -> new Position((char) c, row))
                .filter(c -> !positionsToAvoid.contains(c))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean checkFullSolution(List<Position> partialSolution) {
        return partialSolution.size() == (chessBoard.getColumns() - 'a' + 1);
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
