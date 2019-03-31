package org.sam.playground.recursion;

public class Main {

    public static void main(String[] args) {

        System.out.println(factorial(4));
        System.out.println(tailFactorial(5));
        System.out.println(tailFactorial(3));
    }

    private static int factorial(int n) {
        return n == 1 ? 1 : n * factorial(n - 1);
    }

    private static int tailFactorial(int n) {
        return factorialTailRecursion(n, 1);
    }

    //java 8 doesn't have support for tail recursion
    //compilers figure that the last call can be replaced by a GOTO operation + a decreasing operation of the variable n
    private static int factorialTailRecursion(int n, int partialResult) {
        if (n == 1)
            return partialResult;
        //first argument sends the value for the next computation decreasingly so that we reach the last value of "1"
        return factorialTailRecursion(n - 1, n * partialResult);
    }
}
