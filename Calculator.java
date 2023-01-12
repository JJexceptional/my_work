// Описание:
// Создай консольное приложение “Калькулятор”.
// Приложение должно читать из консоли введенные пользователем строки, числа, арифметические операции проводимые между ними и выводить в консоль результат их выполнения.
// Реализуй класс Main с методом public static String calc(String input).
// Метод должен принимать строку с арифметическим выражением между двумя числами и возвращать строку с результатом их выполнения.
// Ты можешь добавлять свои импорты, классы и методы. Добавленные классы не должны иметь модификаторы доступа (public или другие)

// Требования:
// Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b. Данные передаются в одну строку (смотри пример)!
// Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.
// Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
// Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не ограничиваются по величине и могут быть любыми.
// Калькулятор умеет работать только с целыми числами.
// Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
// При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно, при вводе арабских - ответ ожидается арабскими.
// При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
// При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.
// Результатом операции деления является целое число, остаток отбрасывается. 
// Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль.
// Результатом работы калькулятора с римскими числами могут быть только положительные числа, если результат работы меньше единицы, выбрасывается исключение


import java.util.Scanner;
public class Calculator{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Main result = new Main();
        System.out.println("Input:");
        String expression = input.nextLine();
        String answer = result.calc(expression);


        System.out.println("Output:\n" + answer);
    }
}

class Main{
    public static String calc(String input){
        boolean romanOrArab = false;
        String exception = "throws Exception";
        Main romanExamination = new Main();
        Main arabToRoman = new Main();
        int result = 0;
        String[] inputSplit = input.split(" ");
        if (inputSplit.length != 3){
            return exception;
        }
        Integer firstNumber = 0;
        Integer secondNumber = 0;
        try {
            firstNumber = Integer.valueOf(inputSplit[0]);
            secondNumber = Integer.valueOf(inputSplit[2]);
        } catch (NumberFormatException e) {
            try {
                firstNumber = romanExamination.romanToArab(inputSplit[0]);
                secondNumber = romanExamination.romanToArab(inputSplit[2]);
                romanOrArab = true;
            } catch (NumberFormatException ex) {
                return exception;
            }
        }

        if ((firstNumber < 1) || (firstNumber > 10) || (secondNumber < 1) || (secondNumber > 10)){
            return exception;
        }

        String sign = inputSplit[1];
        switch (sign) {
            case "+" -> result = firstNumber + secondNumber;
            case "-" -> result = firstNumber - secondNumber;
            case "*" -> result = firstNumber * secondNumber;
            case "/" -> result = firstNumber / secondNumber;
            default -> {
                return exception;
            }
        }

        String output;

        if (romanOrArab){
            if(result < 1){
                return exception;
            } else {
                output = arabToRoman.arabToRome(result);
            }
        } else {
            output = Integer.toString(result);
        }

        return output;
    }

    Integer romanToArab(String romanInput){
        int result = 0;
        int[] arab = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arab[i];
                romanInput = romanInput.substring(roman[i].length());
            }
        }

        return result;
    }

    String arabToRome(int arabInput){
        String result = "";
        int value = 0;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++){
            value = arabInput / arab[i];
            for (int j = 0; j < value; j++){
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
    return result;
    }
}