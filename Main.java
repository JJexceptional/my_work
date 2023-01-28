
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

