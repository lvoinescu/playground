package org.sam.playground.backtracking.sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find elements of a list that sum up to an given number
 */
public class FindSumProblem {

    public static void main(String[] args) {

        List<Long> numbers = Arrays.asList(4L, 1L, 1L, 2L, 10L, 2L, 4L, 6L);
        Long sum = 8L;

        solve(numbers, sum, 0);
    }

    private static final List<Long> partialSolution = new ArrayList<>();
    private static long currentSum = 0L;

    public static boolean solve(List<Long> numbers, Long sum, int index) {

        if (currentSum == sum) {
            System.out.println(partialSolution);
        } else {
            if (currentSum > sum) // skip testing next elements if the partial sum is greater
                return false;
            else {
                for (int i = index; i < numbers.size(); i++) {
                    currentSum += numbers.get(i);
                    partialSolution.add(numbers.get(i));
                    solve(numbers, sum, i + 1);
                    currentSum -= partialSolution.get(partialSolution.size() - 1);
                    partialSolution.remove(partialSolution.size() - 1);
                }
            }
        }

        return false;
    }
}
