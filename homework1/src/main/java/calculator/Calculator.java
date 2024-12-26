package calculator;

import java.util.Scanner;

public class Calculator implements CalculatorService {
    @Override
    public void readTwoDigitsAndDivide(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);

        System.out.print("Введите первое число: ");
        double input1 = Double.parseDouble(scanner.nextLine());

        System.out.print("Введите второе число: ");
        double input2 = Double.parseDouble(scanner.nextLine());

        double result = calculate(input1, input2);
        System.out.println("Результат деления: " + result);
    }

    @Override
    public double divideTwoDigits(String prompt, String d1, String d2) {
        double num1 = Double.parseDouble(d1);
        double num2 = Double.parseDouble(d2);
        return calculate(num1, num2);
    }

    private double calculate(double num1, double num2) {
        if (num2 == 0) {
            throw new ArithmeticException("Деление на ноль недопустимо");
        }

        if (num1 > Double.MAX_VALUE || num2 > Double.MAX_VALUE) {
            throw new ArithmeticException("Слишком большое число для деления");
        }

        return num1 / num2;
    }
}