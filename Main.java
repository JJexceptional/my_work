
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку в ковычках , арифметический знак потом снова строку или число без ковычек: ");
        String example = scanner.nextLine();
        char action;
        String[] data;
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
        if (action == '*' || action == '/') {
            if (data[1].contains("\""))
                throw new Exception("Строку умножать и делить можно только на число(которое вводится без ковычек)");
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }

        if (action == '+') {
            printInQuotes(data[0] + data[1]); //складываем и выводим в ковычках
        } else if (action == '*') {
            int multiplier = Integer.parseInt(data[1]); //конвентируем строку в число
            String result = "";
            for (int i = 0; i < multiplier; i++) {
                result += data[0];
            }
            printInQuotes(result);
        } else if (action == '-') {
            int index = data[0].indexOf(data[1]);
            if (index == -1) {
                printInQuotes(data[0]);
            } else {
                String result = data[0].substring(0, index);
                result += data[0].substring(index + data[1].length());
                printInQuotes(result);
            }
        } else {
            int newLength = data[0].length() / Integer.parseInt(data[1]);
            String result = data[0].substring(0, newLength);
            printInQuotes(result);
        }

    }

    static void printInQuotes(String text) {
        System.out.println("\"" + text + "\"");
    }
}