package org.sam.playground.linklistsum;

public class LinkedListNumberSum {


    public static void main(String[] args) {

        /*
         * 3 1 2 +
         * 9 8 9 3 *
         * ----------------
         * 2 0 2 4
         */


        ListNode l3 = new ListNode(2);
        ListNode l2 = new ListNode(1, l3);
        ListNode l1 = new ListNode(3, l2);


        ListNode r4 = new ListNode(3);
        ListNode r3 = new ListNode(9, r4);
        ListNode r2 = new ListNode(8, r3);
        ListNode r1 = new ListNode(9, r2);


        var op1 = r1;
        var op2 = l1;

        var d1 = 0;
        var d2 = 0;
        ListNode result = null;
        ListNode current = null;
        int carry = 0;
        while (op1 != null || op2 != null) {

            if (op1 != null) {
                d1 = op1.val;
                op1 = op1.next;
            } else {
                d1 = 0;
            }


            if (op2 != null) {
                d2 = op2.val;
                op2 = op2.next;
            } else {
                d2 = 0;
            }

            int rawSum = d1 + d2 + carry;
            var sumDigit = rawSum % 10;
//            carry = rawSum >= 10 ? 1 : 0; //TODO see how to use bitwise manipulation XOR with 5?
            carry = rawSum / 10;

            if (current == null) {
                current = new ListNode(sumDigit);
                result = current;
            } else {
                ListNode digitNode = new ListNode(sumDigit);
                current.next = digitNode;
                current = digitNode;
            }
        }

        printList(result);
    }


    public static final void printList(ListNode startNode) {

        while (startNode != null) {
            System.out.print(startNode.val);
            System.out.print(' ');
            startNode = startNode.next;
        }

    }

}
