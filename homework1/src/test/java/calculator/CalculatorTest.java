package calculator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @DataProvider(name = "validData")
    public Object[][] validData() {
        return new Object[][]{
                {"4", "2", 2.0},
                {"-4", "-2", 2.0},
                {"4", "-2", -2.0},
                {"-4", "2", -2.0},
                {"5.5", "2.2", 2.5},
                {"20.55", "5", 4.11},
                {"5", "20.55", 0.243309002},
                {"33.333", "4.56", 7.30986842},
                {"0", "10", 0}
        };
    }

    @DataProvider(name = "wrongDataArithmeticException")
    public Object[][] wrongDataArithmeticException() {
        return new Object[][]{
                {"4", "0"},
                {"1.8E308", "4"},
                {"4", "1.8E308"}
        };
    }

    @DataProvider(name = "wrongDataNumberFormatException")
    public Object[][] wrongDataNumberFormatException() {
        return new Object[][] {
                {"abc", "2"},
                {"2", "abc"},
                {"2", "$&!"},
                {"$&!", "2"}
        };
    }

    @Test(dataProvider = "validData")
    public void testDivideTwoDigitsValid(String d1, String d2, double expected) {
        double result = calculator.divideTwoDigits("Позитивная проверка", d1, d2);
        assertEquals(result, expected,0.00000001, "Результат деления неверный");
    }

    @Test(dataProvider = "wrongDataArithmeticException", expectedExceptions = ArithmeticException.class)
    public void testDivideTwoDigitsArithmeticException(String d1, String d2) {
        calculator.divideTwoDigits("Негативная проверка", d1, d2);
    }

    @Test(dataProvider = "wrongDataNumberFormatException", expectedExceptions = NumberFormatException.class)
    public void testDivideTwoDigitsNumberFormatException(String d1, String d2) {
        calculator.divideTwoDigits("Негативная проверка", d1, d2);
    }

    @Test(dataProvider = "validData")
    public void testReadTwoDigitsAndDivideValid(String input1, String input2, double expected) {
        String input = input1 + "\n" + input2 + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        calculator.readTwoDigitsAndDivide("Введите два числа для деления:");

        String expectedOutput = "Результат деления: " + expected;
        assertTrue(outContent.toString().contains(expectedOutput),
                "Результат деления неверный");
    }

    @Test(dataProvider = "wrongDataArithmeticException", expectedExceptions = ArithmeticException.class)
    public void testReadTwoDigitsAndDivideArithmeticException(String input1, String input2) {

        String input = input1 + "\n" + input2 + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        calculator.readTwoDigitsAndDivide("Введите два числа для деления:");
    }

    @Test(dataProvider = "wrongDataNumberFormatException", expectedExceptions = NumberFormatException.class)
    public void testReadTwoDigitsAndDivideNumberFormatException(String input1, String input2) {

        String input = input1 + "\n" + input2 + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        calculator.readTwoDigitsAndDivide("Введите два числа для деления:");
    }
}