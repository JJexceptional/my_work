Задача: программа "Строковый калькулятор"
        Описание:
        Создайте консольное приложение "Строковый калькулятор". Приложение должно читать из консоли введенные пользователем строки, числа, арифметические операции проводимые между ними и выводить в консоль результат их выполнения.

        Требования:
        Калькулятор умеет выполнять операции сложения строк, вычитания строки из строки, умножения строки на число и деления строки на число: "a" + "b", "a" - "b", "a" * b, "a" / b. Данные передаются в одну строку(смотрите пример)! Решения, в которых каждая строка, число и арифмитеческая операция передаются с новой строки считаются неверными.
        Значения строк передаваемых в выражении выделяются двойными кавычками.
        Результатом сложения двух строк, является строка состоящая из переданных.
        Результатом деления строки на число n, является строка в n раз короче исходной (смотрите пример).
        Результатом умножения строки на число n, является строка, в которой переданная строка повторяется ровно n раз.
        Результатом вычитания строки из строки, является строка, в которой удалена переданная подстрока или сама исходная строка, если в нее нет вхождения вычитаемой строки (смотрите пример).
        Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. И строки длинной не более 10 символов. Если строка, полученная в результате работы приложения длинее 40 симовлов, то в выводе после 40 символа должны стоять три точки (...)
        Калькулятор умеет работать только с целыми числами.
        Первым аргументом выражения, подаваемого на вход, должна быть строка, при вводе пользователем выражения вроде 3 + "hello", калькулятор должен выбросить исключение и прекратить свою работу.
        При вводе пользователем неподходящих чисел, строк или неподдерживаемых операций (например, деление строки на строку) приложение выбрасывает исключение и завершает свою работу.
        При вводе пользователем выражения, не соответствующего одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.
        Пример работы программы:
        Input:
        "100" + "500"

        Output:
        "100500"

        Input:
        "Hi World!" - "World!"

        Output:
        "Hi "

        Input:
        "Bye-bye!" - "World!"

        Output:
        "Bye-bye!"

        Input:
        "Java" * 5

        Output:
        "JavaJavaJavaJavaJava"

        Input:
        "Example!!!" / 3

        Output:
        "Exa"



import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static String result;
    static String data[];
    static char action;

    static public void main(String[] args) throws Exception {
        System.out.println("Введите строку в ковычках (\"\"), арифтетический знак(\"+-\") и вторую строку в ковычках или , строку , арифметический знак (\"*/\") и число без ковычек: ");
        String example = scanner.nextLine();
        action = operation(example);
        result = answer(example);
        result = proverka(example);
        System.out.println(result);
    }

    private static char operation(String example) throws Exception {
        if (example.contains(" + ")) {
            data = example.split(" \\+ ");
            action = '+';
        } else if (example.contains(" - ")) {
            data = example.split(" - ");
            action = '-';
        } else if (example.contains(" * ")) {
            data = example.split(" \\* ");
            action = '*';
        } else if (example.contains(" / ")) {
            data = example.split(" / ");
            action = '/';
        } else {
            throw new Exception("Некорректный знак действия");
        }

        return action;
    }

    private static String answer(String example) throws Exception {
        if (action == '*' || action == '/') {
            if (data[1].contains("\""))
                throw new Exception("invalid int");
        }
            if (action == '-' || action == '+') {
                if (data[0].contains("\"") && data[1].contains("\"")) {
                } else {
                    throw new Exception("Invalid Input!");
                }
            }
            for (int i = 0; i < data.length; i++) {
                data[i] = data[i].replace("\"", "");
            }
            if (action == '*') {
                int number = Integer.parseInt(data[1]);
                result = "";
                for (int i = 0; i < number; i++) {
                    result += data[0];
                }
            }
            if (action == '+') {
                result = data[0] + data[1];
            } else if (action == '-') {
                int index = data[0].indexOf(data[1]);
                if (index == -1) {
                    result = data[0];
                } else {
                    result = data[0].substring(0, index);
                    result += data[0].substring(index + data[1].length());
                }
            }
            if (action == '/') {
                int newLength = data[0].length() / Integer.parseInt(data[1]);
                result = data[0].substring(0, newLength);
            }
            return result;
        }

        private static String proverka (String example){
            if (result.length() > 40) {
                result = result.substring(0, 40) + "...";
            }
            return result;
        }
}

