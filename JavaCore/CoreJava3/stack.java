package CoreJava3;

import java.util.Scanner;
import java.util.*;

public class stack {
    // Function to check the precedence of operators
    private static int uuTien(char x) {
        if (x == '(')
            return 0;
        if (x == '+' || x == '-')
            return 1;
        if (x == '*' || x == '/' || x == '%')
            return 2;
        return 3;
    }

    // Function to convert infix expression to postfix expression
    private static String chuyenTrungToSangHauTo(String infix) {
        StringBuilder hauTo = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                hauTo.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    hauTo.append(stack.pop());
                }
                stack.pop(); // Pop '('
            } else {
                while (!stack.isEmpty() && uuTien(c) <= uuTien(stack.peek())) {
                    hauTo.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            hauTo.append(stack.pop());
        }

        return hauTo.toString();
    }

    // Function to evaluate a postfix expression
    private static float evaluatePostfix(String postfix) {
        Stack<Float> stack = new Stack<>();
        for (int i = 0; i < postfix.length(); i++) {
            char token = postfix.charAt(i);
            if (Character.isDigit(token)) {
                stack.push((float) (token - '0'));
            } else {
                float op1 = stack.pop();
                float op2 = stack.pop();
                float result;
                switch (token) {
                    case '+':
                        result = op2 + op1;
                        break;
                    case '-':
                        result = op2 - op1;
                        break;
                    case '*':
                        result = op2 * op1;
                        break;
                    case '/':
                        result = op2 / op1;
                        break;
                    default:
                        System.out.println("Invalid Operator");
                        return 0;
                }
                stack.push(result);
            }
        }
        return stack.pop();
    }
    public static void main(String[] args) {
        String infixExpression = "3 + (4 * 5 - 6) / 7";
        String postfixExpression = chuyenTrungToSangHauTo(infixExpression);
        System.out.println("Postfix Expression: " + postfixExpression);

        float result = evaluatePostfix(postfixExpression);
        System.out.println("Result: " + result);
    }
}
