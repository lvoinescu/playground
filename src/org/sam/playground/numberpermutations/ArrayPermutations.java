package org.sam.playground.numberpermutations;

import java.util.Arrays;

public class ArrayPermutations {

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3};

        permutations(input, 0);
    }

    private static void permutations(int[] input, int start) {
        if (start >= input.length) {
            System.out.println(Arrays.toString(input));
            return;
        }
        for (int i = start; i < input.length; i++) {
            swap(input, i, start);
            permutations(input, start + 1);
            swap(input, start, i);
        }
    }


    static void swap(int[] input, int i, int j) {
        var aux = input[i];
        input[i] = input[j];
        input[j] = aux;
    }

}
