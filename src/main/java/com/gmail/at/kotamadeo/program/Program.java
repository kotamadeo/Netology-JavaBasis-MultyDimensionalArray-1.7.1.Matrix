package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.matrix.Matrix;
import com.gmail.at.kotamadeo.utils.Utils;

import java.util.Scanner;

public class Program {
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        try {
            System.out.println(Utils.ANSI_BLUE + "Для первичной инициализации программы введите размер матрицы:"
                    + Utils.ANSI_RESET);
            String input = scanner.nextLine();
            var matrix = new Matrix(Integer.parseInt(input));
            while (true) {
                try {
                    printMenu();
                    input = scanner.nextLine();
                    if ("выход".equalsIgnoreCase(input) || "0".equals(input)) {
                        scanner.close();
                        break;
                    } else {
                        var operationNumber = Integer.parseInt(input);
                        switch (operationNumber) {
                            case 1:
                                System.out.printf("%sВаша оригинальная матрица размером %sx%s:%s%n",
                                        Utils.ANSI_BLUE, matrix.getSize(), matrix.getSize(), Utils.ANSI_RESET);
                                matrix.printOriginalMatrix(matrix);
                                break;
                            case 2:
                                System.out.println(Utils.ANSI_BLUE + "Введите 90°/180°/270°/360°, на которые " +
                                        "необходимо повернуть матрицу" + Utils.ANSI_RESET);
                                input = scanner.nextLine();
                                matrix.printRotatedMatrix(Integer.parseInt(input), matrix);
                                break;
                            default:
                                System.out.println(Utils.ANSI_RED + "Вы ввели неверный номер операции!" +
                                        Utils.ANSI_RESET);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println(Utils.ANSI_RED + "Ошибка ввода!" + Utils.ANSI_RESET);
                }
            }
        } catch (NumberFormatException e) {
            System.out.printf("%sТакой размер матрицы недопустим!%s%n", Utils.ANSI_RED, Utils.ANSI_RESET);
            start();
        }
    }

    private static void printMenu() {
        System.out.println(Utils.ANSI_YELLOW + "Эта программа способна поворачивать матрицу на заданное " +
                "количество градусов°!" + Utils.ANSI_RESET);
        System.out.println(Utils.ANSI_PURPLE + "Возможные команды программы:" + Utils.ANSI_RESET);
        System.out.println("0 или выход: чтобы выйти из программы.");
        System.out.println("1: чтобы вывести на экран оригинальную матрицу.");
        System.out.println("2: чтобы  повернуть матрицу на заданное количество градусов°.");
        System.out.print(">>>>>>>");
    }
}
