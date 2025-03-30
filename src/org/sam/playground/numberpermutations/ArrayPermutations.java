package org.sam.playground.numberpermutations;

import java.util.Arrays;

public class ArrayPermutations {

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5};

        permutations(input, 0);
    }

    private static void permutations(int[] input, int start) {
        if (start >= input.length) {
            return;
        }
        for (int i = 0; i < input.length; i++) {
            swap(input, start, i);
            System.out.println(Arrays.toString(input));
            permutations(input, start + 1);
            swap(input, i, start);
        }
    }


    static void swap(int[] input, int i, int j) {
        var aux = input[i];
        input[i] = input[j];
        input[j] = aux;
    }

}
