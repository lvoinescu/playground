package org.sam.playground.recursion;

public class IterateRecursively {


    //Just for fun recursive iteration; don't ever do this :)
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        iterate(array, array.length - 1);

        System.out.println("Setting value:");
        setValue(array, array.length - 1, 4, 5555);

        iterate(array, array.length - 1);

        System.out.println("Get value:" + getValue(array, array.length - 1, 4));
    }

    private static void iterate(int[] array, int index) {
        if (index > 0) {
            iterate(array, index - 1);
        }
        System.out.println(array[index]);
    }

    private static void setValue(int[] array, int index, int indexToSet, int value) {
        if (index > 0) {
            if (index == indexToSet) {
                array[indexToSet] = value;
            } else {
                setValue(array, index - 1, indexToSet, value);
            }
        }
    }

    private static int getValue(int[] array, int index, int indexToGet) {
        if (index > 0) {
            if (index == indexToGet) {
                return array[indexToGet];
            } else {
                return getValue(array, index - 1, indexToGet);
            }
        } else {
            return -1;
        }
    }
}
