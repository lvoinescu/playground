package org.sam.playground.sorting;

import java.util.Arrays;
import java.util.Stack;

/**
 * O(n log n) efficient sorting algorithm
 * Developed by British computer scientist Tony Hoare in 1959
 */
public class QuickSort {

    public static void main(String[] args) {
        long start;
        long end;

        long[] numbers = new long[]{1L, 4L, 6L, 2L, 7L, 3L, 9L, 4L};
        start = System.nanoTime();
        quickSort(numbers);
        end = System.nanoTime();
        Arrays.stream(numbers).forEach(System.out::println);

        System.out.println("Sorted in " + (end - start));

        long[] numbers2 = new long[]{1L, 4L, 6L, 2L, 7L, 3L, 9L, 4L};
        start = System.nanoTime();
        quickSortTailRecursion(numbers2);
        end = System.nanoTime();
        Arrays.stream(numbers2).forEach(System.out::println);
        System.out.println("Sorted in " + (end - start));

        long[] numbers3 = new long[]{1L, 4L, 6L, 2L, 7L, 3L, 9L, 4L};
        start = System.nanoTime();
        quickSortNoRecursion(numbers3);
        end = System.nanoTime();
        Arrays.stream(numbers3).forEach(System.out::println);
        System.out.println("Sorted in " + (end - start));
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

    private static void quickSortTailRecursion(long[] numbers) {
        quickSortTailRecursion(numbers, 0, numbers.length - 1);
    }

    private static void quickSortTailRecursion(long[] numbers, int startIndex, int endIndex) {
        int pivot = choosePivot(startIndex, endIndex);
        int auxStart;
        while (endIndex > startIndex) {
            pivot = processPartition(numbers, startIndex, endIndex, pivot);
            auxStart = startIndex + 1;

            quickSortTailRecursion(numbers, startIndex, pivot - 1);

            startIndex = auxStart;
            pivot = endIndex;
        }
    }

    private static class Interval {
        final int start;
        final int end;
        final int pivot;

        private Interval(int start, int end, int pivot) {
            this.start = start;
            this.end = end;
            this.pivot = pivot;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "] | " + pivot;
        }
    }

    private static void quickSortNoRecursion(long[] numbers) {
        quickSortNoRecursion(numbers, 0, numbers.length - 1);
    }

    private static void quickSortNoRecursion(long[] numbers, int startIndex, int endIndex) {
        int newPivot = choosePivot(startIndex, endIndex);
        Stack<Interval> intervals = new Stack<>();
        intervals.push(new Interval(startIndex, endIndex, newPivot));
        while (intervals.size() > 0) {
            Interval interval = intervals.pop();
            newPivot = processPartition(numbers, interval.start, interval.end, interval.pivot);

            if (interval.start < newPivot && interval.end > newPivot) {
                intervals.push(new Interval(interval.start, newPivot, choosePivot(interval.start, newPivot)));
                intervals.push(new Interval(newPivot + 1, interval.end, choosePivot(newPivot + 1, interval.end)));
            }
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
