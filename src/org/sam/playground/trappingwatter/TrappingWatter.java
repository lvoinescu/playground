package org.sam.playground.trappingwatter;

public class TrappingWatter {


    /**
     * <pre>
     *
     *
     *                            Move the smaller index toward the center
     *                              When they meet, stop.
     *
     *
     *                                                        ┌────┐
     *            left=0                                      │    │           right=n-1
     *           ─────────►                                   │    │         ◄──────────             The evaluated bar will
     *                                                        │    │                                always be near max/min bar
     *                                                        │    │
     *                          ┌────┐                        │    │
     *                          │    │                        │    │                              ┌──┐                             ┌──┐
     *                          │    │         ┌────┐         │    │                              │  │                             │  │
     *                          │    │         │    │         │    │                              │  │-------┌──┐       ┌──┐-------│  │
     *                ┌────┐    │    │         │    ┌────┐    │    │                              │  │     ▲ │  │       │  │ ▲     │  │
     *                │    │    │    ┌────┐    │    │    │    │    ┌────┐                         │  │     │ │  │       │  │ │     │  │
     *                │    ┌────│    │    │    │    │    │    │    │    │    ┌────┐               │  │     │ │  │       │  │ │     │  │
     *                │    │    │    │    │    │    │    │    │    │    │    │    │               │  │----┌──┐  │       │  │─┴─┐   │  │
     *            ┌───│    │    │    │    ┌────│    │    ┌────│    │    │    │    │               │  │    │  │  │       │  │   │   │  │
     *            │   │    │    │    │    │    │    │    │    │    │    ┌────│    ┌─────┐         │  │    │  │  │       │  │   │   │  │
     *        ┌───│   │    │    │    │    │    │    │    │    │    │    │    │    │     │         │  │    │  │  │       │  │   │   │  │
     *        │   │   │    │    │    │    │    │    │    │    │    │    │    │    │     │         │  │... │  │  │       │  │   │...│  │
     *        └───└───└────└────└────└────└────└────└────└────└────└────└────└────└─────┘         └──┘    └──┘──┘       └──┘───┘   └──┘
     *
     *
     * </pre>
     */
    public static void main(String[] args) {


        var input = new int[]{0, 0, 1, 2, 4, 3, 1, 2, 3, 2, 1, 5, 4, 2, 0, 1};

        var watterTrapped = solve(input);

        System.out.println("Watter trapped: " + watterTrapped);
    }

    private static int solve(int[] input) {
        int greatestRight = 0;
        int greatestLeft = 0;

        int left = 0;
        int right = input.length - 1;

        int total = 0;

        while (left < right) {

            // you move the smaller side only, because there might be other
            // elements greater than the smaller bound (right or left) that might "catch" water between


            // you only move the index from the smaller side
            if (input[left] <= input[right]) {

                /*               leftGreatest      rightGreatest
                 *                                        X
                 *                     X                  X
                 *                     X O  <- input[i]   X
                 */

                if (greatestLeft > input[left]) {
                    total += greatestLeft - input[left];
                } else {
                    greatestLeft = input[left];
                }
                left++;
            } else {

                /*               leftGreatest      rightGreatest
                 *                     X
                 *                     X               X
                 *                     X   input[i]->O X
                 */

                if (greatestRight > input[right]) {
                    total += greatestRight - input[right];
                } else {
                    greatestRight = input[right];
                }
                right--;
            }
        }

        return total;
    }


}
