package org.sam.playground.lexchecker;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a piece of text establish if the input can be validated as piece of code,
 * according to a given small enclosing char set.
 */
public class DummyLexChecker {

    private static Map<Character, Character> pairs = new HashMap<>();

    static {
        pairs.put('(', ')');
        pairs.put('[', ']');
        pairs.put('{', '}');
    }

    public static void main(String[] args) {
        DummyLexChecker dummyLexChecker = new DummyLexChecker();



        String input1 = "" +
                "do{\n" +
                "   while(a==1 && (b==2)){\n" +
                "       x[1]=x[a[2]]\n" +
                "   }\n" +
                "}";
        System.out.println(input1 + "\n" + " valid = " + dummyLexChecker.validate(input1));
        System.out.println();

        String input2 = "[a( \"{\"  [{}]     )";
        System.out.println(input2 + "\n" + " valid = " + dummyLexChecker.validate(input2));

        String input3 = "[a( \"{[((([[\" \"\" [{}]     )";
        System.out.println(input3 + "\n" + " valid = " + dummyLexChecker.validate(input3));
    }

    private boolean validate(String input) {

        boolean insideString = false;
        Stack<Character> activeEnclosingStack = new Stack<>();
        int i = 0;
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c != '"') {
                if (!insideString) {
                    if (isOpeningChar(c)) {
                        activeEnclosingStack.push(pairs.get(c));
                    } else {
                        if (isClosingChar(c)) {
                            if (activeEnclosingStack.size() > 0 && !activeEnclosingStack.pop().equals(c)) {
                                return false;
                            }
                        }
                    }
                }
            } else {
                if (!insideString) {
                    insideString = true;
                } else {
                    if (input.length() > 1) {
                        if (input.charAt(i - 1) != '\\') {
                            insideString = false;
                        }
                    }
                }
            }
            i++;
        }
        return true;
    }

    private boolean isOpeningChar(Character character) {
        return pairs.keySet().stream().anyMatch(c -> c.equals(character));
    }

    private boolean isClosingChar(Character character) {
        return pairs.values().stream().anyMatch(c -> c.equals(character));
    }
}
