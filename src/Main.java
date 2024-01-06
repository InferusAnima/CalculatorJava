import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String output = calc(input);
            System.out.println(output);
        }
    }

    public static String calc(String input) throws Exception {
        String[] inputSplit = input.split(" ");
        if (inputSplit.length > 3) {
            throw new Exception();
        }
        String method = inputSplit[1];
        int first, second;
        boolean isRoman = false;
        try {
            first = Integer.parseInt(inputSplit[0]);
            second = Integer.parseInt(inputSplit[2]);
        } catch (NumberFormatException e) {
            isRoman = true;
            first = romanToInt(inputSplit[0]);
            second = romanToInt(inputSplit[2]);
            if (first == 0 || second == 0) {
                throw new Exception();
            }
        }

        if (first > 10 || second > 10 || first < 1 || second < 1) {
            throw new Exception();
        }

        int output;

        switch (method) {
            case "+" -> output = first + second;
            case "-" -> output = first - second;
            case "*" -> output = first * second;
            case "/" -> output = first / second;
            default -> throw new Exception();
        }

        String outputStr = Integer.toString(output);

        if (isRoman) {
            outputStr = intToRoman(output);
        }

        return outputStr;
    }

    public static int romanToInt(String s) throws Exception {
        if (s == null || s.isEmpty()) {
            throw new Exception();
        }

        HashMap<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int result = 0;
        int prevValue = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            char currentChar = s.charAt(i);
            int currentValue = romanMap.getOrDefault(currentChar, 0);

            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            prevValue = currentValue;
        }

        return result;
    }

    public static String intToRoman(int num) throws Exception {
        if (num <= 0 || num > 3999) {
            throw new Exception();
        }

        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();
        int i = 0;

        while (num > 0) {
            int quotient = num / values[i];
            num %= values[i];

            for (int j = 0; j < quotient; j++) {
                result.append(symbols[i]);
            }

            i++;
        }

        return result.toString();
    }
}