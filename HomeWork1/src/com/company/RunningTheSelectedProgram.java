package com.company;

import java.util.Scanner;

/**
 * Если вводим 1 - запускается калькулятор,
 * если вводим 2 - запускается поиск максимального слова в массиве слов.
 *
 * @author Golovacheva Valeriya
 * @version 1.0
 */

public class RunningTheSelectedProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "Введите:\n" +
                            " 1 - запустить калькулятор.\n" +
                            " 2 - запустить поиск масимального слова в массиве."
            );
            if (scanner.hasNextInt()) {
                int selectedProgram = scanner.nextInt();
                switch (selectedProgram) {
                    case 1:
                        new DoubleCalculator(scanner, System.out).calculate();
                        return;
                    case 2:
                        new FindLongestString(scanner, System.out).process();
                        return;
                }
            }
        }
    }
}
