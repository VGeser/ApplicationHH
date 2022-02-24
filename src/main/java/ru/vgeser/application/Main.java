package ru.vgeser.application;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Would you like to set a ceiling of array capacity? y/n");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        Application app;
        int ceiling = 10000;
        String caution = "for better performance please use values not bigger than 10000";
        if (answer.equals("y")) {
            System.out.println("Please enter your desired ceiling"
                    + "\n" + "(" + caution + ")");
            if (scanner.hasNextInt()) {
                ceiling = scanner.nextInt();
                app = new Application(ceiling);
            } else {
                System.out.println("Wrong input. Using default ceiling of 10000");
                app = new Application();
            }
        } else {
            System.out.println("Using default ceiling of 10000");
            app = new Application();
        }
        System.out.println("Please enter number of arrays" + "\n" +
                "(" + caution + ")");
        while (scanner.hasNextInt()) {
            int current = scanner.nextInt();
            if (current <= ceiling) {
                int[][] res = app.myFunc(current);
                for (int i = 0; i < current; i++) {
                    int[] temp = res[i];
                    System.out.println(Arrays.toString(temp));
                }
            } else {
                System.out.println("Unable to create unique lengths for given number of arrays"
                        + "\n" + "Please try again");
            }

        }
    }
}
