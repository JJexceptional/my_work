
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int number;
    static char action;
    static String result;

    public static void main(String[] args) throws Exception{
        System.out.print("Введите стоку в ковычках а число без ковычек(выполняемые действия:сложение или вычетание строк,умножение и деление стоки на число: ");
        String userInput = scanner.nextLine();
        action = metodOperation(userInput);
        globalWork(userInput);
    }

    private static void globalWork(String userInput){
        String[] example = userInput.split("[+-/*\"]");
        if (example.length == 5) {
            String st1 = example[1];
            String st4 = example[4];
            result = calculated(st1, st4, action);
            if (result.length() > 40) {
                String rez = result.substring(0, 40);
                System.out.println(rez + "...");
            } else {
                System.out.println(result);
            }
        } else {
            String st1 = example[1];
            String st3 = example[3];
            number = Integer.parseInt(st3);
            number = Integer.parseInt(st3.replace(" ", ""));
            result = calculated(st1, number, action);
            if (result.length() > 40) {
                String rez = result.substring(0, 40);
                System.out.println(rez + "...");
            } else {
                System.out.println(result);
            }

        }
        }



    private static char metodOperation(String userInput) {
        char[] uchar = new char[26];
        for (int i = 0; i < userInput.length(); i++) {
            uchar[i] = userInput.charAt(i);
            if (uchar[i] == '+') {
                action = '+';
            }
            if (uchar[i] == '-') {
                action = '-';
            }
            if (uchar[i] == '*') {
                action = '*';
            }
            if (uchar[i] == '/') {
                action = '/';
            }
        }
        return action;
    }

    public static String calculated(String num1, String num2, char oper) {

        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                int resA = num1.length() - num2.length();
                if (num1.length() == num2.length()) {
                    result = "0";
                } else {
                    result = num1.substring(0, resA);
                }
                break;
            case '*':
                System.out.println("Неверный знак операции * (введите + или -)");
                break;
            case '/':
                System.out.println("Неверный знак операции / (введите + или -)");
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }

    public static String calculated(String num1, int num, char oper) {

        switch (oper) {
            case '+':
                System.out.println("Неверный знак операции + (введите * или /)");

                break;
            case '-':
                System.out.println("Неверный знак операции - (введите * или /)");
                break;
            case '*':
                result = "";
                for (int u = 0; u < num; u++) {
                    result +=  result + num1;
                }
                break;
            case '/':
                try {
                    int resB = num1.length() / num;
                    if (num1.length() == num) {
                        result = "1";
                    } else {
                        result = num1.substring(0, resB);
                    }
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Only integer non-zero parameters allowed");
                    break;
                } finally {
                    if (num1.length() < num) {
                        System.out.println("Делимое меньше делителя");
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }
}
