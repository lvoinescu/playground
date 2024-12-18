package org.sam.playground.removeelementsinplace;

import java.util.Arrays;

public class RemoveElementsInPlace {

    public static void main(String[] args) {

        var arr = new int[]{2, 3, 3, 2, 2, 7, 2};


        var x = 2;
        var k = 0;
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] != x) {
                arr[k++] = arr[i];
            }
        }

        System.out.println(Arrays.toString(arr));
        System.out.println(k);


    }
}
