package org.sam.playground.platefill;

import java.util.List;

public class FoodSolverService {

    private final SolvingListener solvingListener;

    public FoodSolverService(SolvingListener solvingListener) {
        this.solvingListener = solvingListener;
    }

    public void solve(Plate plate, List<Sandwich> sandwiches) {
        if (!new FitAllFoodSolver(plate, sandwiches, solvingListener).solve()) {
            new FoodLineSolver(plate, sandwiches, solvingListener).solve();
        }
    }
}
