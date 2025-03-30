package org.sam.playground.stringpermutations;

import java.util.Arrays;

public class StringPermutations {

    public static void main(String[] args) {
        String input = "abc";

        permutations(input);
    }

    private static void permutations(String input) {
        var charInput = new char[input.length()];
        input.getChars(0, input.length(), charInput, 0);
        permutations(charInput, 0);
    }

    private static void permutations(char[] input, int start) {
        if (start >= input.length) {
            System.out.println(Arrays.toString(input));
            return;
        }
        for (int i = start; i < input.length; i++) {
            swap(input, start, i);
            permutations(input, start + 1);
            swap(input, i, start);
        }
    }


    static void swap(char[] input, int i, int j) {
        var aux = input[i];
        input[i] = input[j];
        input[j] = aux;
    }

}
