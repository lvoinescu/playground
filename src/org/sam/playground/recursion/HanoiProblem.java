package org.sam.playground.recursion;

import java.util.Comparator;
import java.util.Stack;
import java.util.stream.Stream;

/*
A non-optimized solving for Hanoi problem
 */
public class HanoiProblem {

    private static Stack<Integer> source = new Stack<>();
    private static Stack<Integer> pivot = new Stack<>();
    private static Stack<Integer> destination = new Stack<>();

    public static void main(String[] args) {
        source.push(6);
        source.push(5);
        source.push(4);
        source.push(3);
        source.push(2);
        source.push(1);

        printStacks();
        solve(source, source.size(), pivot, destination);
    }

    private static boolean canMove(Stack<Integer> a, Stack<Integer> b) {
        return (a.size() > 0 && b.size() > 0 && a.peek() < b.peek())
                || (a.size() > 0 && b.size() == 0);
    }

    private static void move(Stack<Integer> a, Stack<Integer> b) {
        b.push(a.pop());
        printStacks();
    }

    private static void solve(Stack<Integer> source, int len, Stack<Integer> pivot, Stack<Integer> destination) {
        if (len > 2) {
            solve(source, len - 1, destination, pivot);

            move(source, destination);
            solve(pivot, len - 1, source, destination);
        } else {
            move(source, pivot);
            move(source, destination);
            move(pivot, destination);
        }
    }

    private static void printStacks() {
        Integer max = Stream.of(source.size(), destination.size(), pivot.size())
                .max(Comparator.comparingInt(a -> a)).get();

        Integer[] s = source.toArray(new Integer[]{});
        Integer[] p = pivot.toArray(new Integer[]{});
        Integer[] d = destination.toArray(new Integer[]{});
        for (int i = max - 1; i >= 0; i--) {
            printElement(s, i);
            printElement(p, i);
            printElement(d, i);
            System.out.println();
        }

        System.out.println("====");
    }

    private static void printElement(Integer[] s, int i) {
        if (i < s.length && i >= 0) {
            System.out.print(s[i]);
        } else {
            System.out.print(" ");
        }
    }
}