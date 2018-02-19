package com.company;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Поиск максимального слова в массиве.
 *
 * @author Golovacheva Valeriya
 * @version 1.0
 */

public class FindLongestString {
    private final Scanner scanner;
    private final PrintStream out;

    /**
     * Создание инструмента для поиска наидлинейшей строки.
     *
     * @param scanner поток ввода данных.
     * @param out     поток вывода данных.
     */
    public FindLongestString(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.out = out;
    }

    /**
     * Запуск ввода строк и поиска.
     */
    public void process() {
        int elementsCount = enterElementsCount();
        String[] elements = enterElements(elementsCount);
        String longest = findLongest(elements);
        out.println("Слово наибольшей длины= \"" + longest + "\"");
    }

    /**
     * Ввод количества элементов.
     *
     * @return количесво элементов, число > 0
     */
    private int enterElementsCount() {
        while (true) {
            out.println("Введите размер массива (целое число > 0)");
            scanner.skip(".*\\n");
            if (scanner.hasNextInt()) {
                int elementsCount = scanner.nextInt();
                if (elementsCount > 0) {
                    return elementsCount;
                }
            }
        }
    }

    /**
     * Ввод значений элементов.
     *
     * @param elementsCount количество значений, которые необходимо ввести
     * @return массив введенных значений
     */
    private String[] enterElements(int elementsCount) {
        String[] elements = new String[elementsCount];
        out.println("Введите элементы массива разделив их пробелом или переводом каретки");
        for (int i = 0; i < elements.length; i++) {
            elements[i] = scanner.next();
        }
        return elements;
    }

    /**
     * Возращает наидлинейшую строку в массиве строк.
     *
     * @param elements массив строк, в котором будет происходить поиск
     * @return если массив пустой возращает <code>null</code>, иначе наидлинейшую строку
     */
    private String findLongest(String[] elements) {
        if (elements.length == 0) {
            return null;
        }
        String longest = elements[0];
        for (int i = 1; i < elements.length; i++) {
            if (elements[i].length() > longest.length()) {
                longest = elements[i];
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        FindLongestString processor = new FindLongestString(new Scanner(System.in), System.out);
        processor.process();
    }
}
