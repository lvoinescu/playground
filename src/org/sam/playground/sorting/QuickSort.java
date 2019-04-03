package org.sam.playground.sorting;

import java.util.Arrays;

/**
 * O(n log n) efficient sorting algorithm
 * Developed by British computer scientist Tony Hoare in 1959
 */
public class QuickSort {

    public static void main(String[] args) {
        long[] numbers = new long[]{1L, 4L, 6L, 2L, 7L, 3L, 9L, 4L};
        quickSort(numbers);
        Arrays.stream(numbers).forEach(System.out::println);
    }

    private static void quickSort(long[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
    }

    private static void quickSort(long[] numbers, int startIndex, int endIndex) {
        int pivot = choosePivot(startIndex, endIndex);
        if (endIndex > startIndex) {
            pivot = processPartition(numbers, startIndex, endIndex, pivot);
            quickSort(numbers, startIndex, pivot - 1);
            quickSort(numbers, pivot + 1, endIndex);
        }
    }

    //This method places tha pivot on the it's ordered position and return it;
    //everything at the left of the pivot it's smaller than the pivot;
    //everything at the right of the pivot it's greater that the pivot;
    // this is Hoare's partition scheme
    private static int processPartition(long[] numbers, int i, int j, int pIndex) {

        long pivot = numbers[pIndex];
        while (true) {
            while (numbers[i] < pivot) {
                i++;
            }

            while (numbers[j] > pivot) {
                j--;
            }
            if (i < j)
                swap(numbers, i++, j);
            else {
                return j;
            }

        }
    }

    private static void swap(long[] numbers, int i, int j) {
        long aux = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = aux;
    }


    private static int choosePivot(int startIndex, int endIndex) {
        return (startIndex + endIndex) / 2;
    }
}
