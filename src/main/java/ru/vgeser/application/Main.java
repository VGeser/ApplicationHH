package ru.vgeser.application;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Would you like to set a ceiling of array capacity? y/n");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        Application app;
        if(answer.equals("y")){
            System.out.println("Please enter your desired ceiling (not bigger than 2147483647)");
            if(scanner.hasNextInt()){
                int ceiling = scanner.nextInt();
                app = new Application(ceiling);
            }else{
                System.out.println("Wrong input. Using default ceiling of about 46341");
                app = new Application();
            }
        }else{
            System.out.println("Using default ceiling of about 46341");
            app = new Application();
        }
        System.out.println("Please enter number of arrays");
        while (scanner.hasNextInt()) {
            int current = scanner.nextInt();
            int[][] res = app.myFunc(current);
            for (int i = 0; i < current; i++) {
                int[] temp = res[i];
                System.out.println(Arrays.toString(temp));
            }
        }
    }
}
