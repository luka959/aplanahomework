package com.company;

import com.company.sweets.Candy;
import com.company.sweets.JellyBean;
import com.company.sweets.SweetType;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NewYearPresent present = new NewYearPresent();

        addCandy(scanner, present);
        addJellyBeans(scanner, present);

        System.out.println(present.getShortInfo());
        System.out.println("Упс... Растеряли CANDY");
        present.removeAllSweetsByType(SweetType.CANDY);
        System.out.println(present.getShortInfo());

        System.out.println("Без CANDY грустно...");
        addCandy(scanner, present);

        System.out.printf(
                "Теперь всё на месте, вес=%.2f, стоимость=%.2f. %s",
                present.getWeight(), present.getCost(), present.getShortInfo()
        );
    }

    private static void addCandy(Scanner scanner, NewYearPresent present) {
        int candiesCount = enterSweetsCount(scanner, SweetType.CANDY);
        for (int i = 0; i < candiesCount; i++) {
            present.addSweet(new Candy());
        }
    }

    private static void addJellyBeans(Scanner scanner, NewYearPresent present) {
        int jellyBeansCount = enterSweetsCount(scanner, SweetType.JELLY_BEAN);
        for (int i = 0; i < jellyBeansCount; i++) {
            present.addSweet(new JellyBean());
        }
    }

    private static int enterSweetsCount(Scanner scanner, SweetType sweetType) {
        while (true) {
            System.out.println("Введите количество " + sweetType + "-конфет (целое число >= 0)");
            if (scanner.hasNextInt()) {
                int sweetsCount = scanner.nextInt();
                scanner.skip(".*\\n");
                if (sweetsCount >= 0) {
                    return sweetsCount;
                }
            } else {
                scanner.skip(".*\\n");
            }
        }
    }
}
